/**
 * [dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.grouepedagogique.GroupeAffectationBean.java] 
 * @author MAKERRI Sofiane on : 22 juil. 2014  11:58:54
 */
package dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.grouepedagogique;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.faces.model.SelectItem;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.DualListModel;
import org.primefaces.model.StreamedContent;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteDto;
import dz.gov.mesrs.sii.commons.business.service.bpm.SituationService;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.AffectationGroupePedagogiqueDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DossierInscriptionAdministrativeDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.GroupePedagogiqueDto;
import dz.gov.mesrs.sii.fve.business.service.cursus.AffectationGroupePedagogiqueService;
import dz.gov.mesrs.sii.fve.business.service.cursus.DossierEtudiantService;
import dz.gov.mesrs.sii.fve.business.service.cursus.DossierInscriptionAdministrativeService;
import dz.gov.mesrs.sii.fve.business.service.cursus.GroupePedagogiqueService;
import dz.gov.mesrs.sii.fve.business.service.cursus.ParcoursIndividualiseService;
import dz.gov.mesrs.sii.fve.business.service.impression.ImpressionService;
import dz.gov.mesrs.sii.fve.business.util.UtilConstants;
import dz.gov.mesrs.sii.fve.web.jsf.bean.SessionBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.UtilBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.impression.PrintMgrBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.CursusConstants;
import dz.gov.mesrs.sii.fve.web.jsf.bean.utils.Const;
import dz.gov.mesrs.sii.fve.web.jsf.bean.utils.Utility;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefAffectationDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefIndividuDto;
import dz.gov.mesrs.sii.referentiel.business.service.NomenclatureService;
import dz.gov.mesrs.sii.referentiel.business.service.RefAffectationService;
import dz.gov.mesrs.sii.referentiel.business.service.RefIndividuService;

/**
 * @author MAKERRI Sofiane on : 22 juil. 2014 11:58:54
 */
@ManagedBean(name = "groupeAffectationBean")
@ViewScoped
public class GroupeAffectationBean implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author MAKERRI Sofiane on : 22 juil. 2014 11:58:59
	 */
	private static final long serialVersionUID = 1L;
	private DualListModel<Etudiant> etudiants;
	private List<DossierInscriptionAdministrativeDto> dossierInscriptionDtoList;
	private List<Etudiant> source;
	private List<Etudiant> target;
	private List<Etudiant> oldTarget;
	@ManagedProperty(value = "#{dossierInscriptionAdministrativeService}")
	private DossierInscriptionAdministrativeService dossierInscriptionAdministrativeService;
	@ManagedProperty(value = "#{dossierEtudiantService}")
	private DossierEtudiantService dossierEtudiantService;
	@ManagedProperty(value = "#{sessionBeanFve}")
	private SessionBean sessionBean;
	@ManagedProperty(value = "#{utilBean}")
	private UtilBean utilBean;
	@ManagedProperty(value = "#{refIndividuServiceImpl}")
	private RefIndividuService refIndividuService;
	@ManagedProperty(value = "#{refAffectationServiceImpl}")
	private RefAffectationService refAffectationService;
	private static final Log log = LogFactory
			.getLog(GroupeAffectationBean.class);
	private Integer idGp;
	private Integer refGroupeId;
	private Integer idOof;
	private Integer idAnnee;
	private Integer idSectionType;
	private Integer idPeriode;
	@ManagedProperty(value = "#{affectationGroupePedagogiqueService}")
	private AffectationGroupePedagogiqueService affectationGroupePedagogiqueService;
	@ManagedProperty(value = "#{groupePedagogiqueService}")
	private GroupePedagogiqueService groupePedagogiqueService;
	private ResourceBundle bundleCommon;
	private ResourceBundle bundleGroupe;
	private NomenclatureDto roleEtudiant;
	private List<RefAffectationDto> individuGroupeList;
	private GroupePedagogiqueDto groupePedagogiqueDto;
	private boolean sectionEmpty;
	private List<SelectItem> groupeOfSectionList;
	private Integer groupeModelId;
	private boolean loadFromModel;
	private List<GroupeInfo> groupeInfoList;
	private List<Etudiant> etudiantsList;
	private boolean loadGroupes;
	@ManagedProperty("#{printMgrBean}")
	private PrintMgrBean printMgrBean;
	@ManagedProperty("#{impressionService}")
	private ImpressionService impressionService;
	@ManagedProperty("#{parcoursIndividualiseService}")
	private ParcoursIndividualiseService parcoursIndividualiseService;
	private SituationEntiteDto situationValidee;
	@ManagedProperty(value = "#{situationService}")
	private SituationService situationService;
	private String sourceCaption;
	private String targetCaption;
	private boolean editMode;
	private boolean onlyAffectation;
	@ManagedProperty(value = "#{nomenclatureServiceImpl}")
	private NomenclatureService nomenclatureService;

	/**
	 * [GroupeAffectationBean.GroupeAffectationBean()] Constructor
	 * 
	 * @author MAKERRI Sofiane on : 22 juil. 2014 11:58:54
	 */
	public GroupeAffectationBean() {
		super();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		bundleCommon = facesContext.getApplication().getResourceBundle(
				facesContext, CursusConstants.COMMON_BUNDLE_MSG_NAME);
		bundleGroupe = facesContext.getApplication().getResourceBundle(
				facesContext,
				CursusConstants.GROUPE_PEDAGOGIQUE_BUNDLE_MSG_NAME);
	}

	@PostConstruct
	public void init() {
		try {
			roleEtudiant = nomenclatureService.findByCode(Const.NC_ROLE,
					Const.REF_CODE_ROLE_ETUDIANT);

			situationValidee = situationService
					.findBySituationEntiteByCodeSituation(
							UtilConstants.ENTITE_PARCOURS_INDIVIDUALISE_CODE,
							UtilConstants.SITUTAION_VALIDEE_CODE);
		} catch (Exception e) {

		}

	}

	/**
	 * [GroupeAffectationBean.loading] Method
	 * 
	 * @author MAKERRI Sofiane on : 12 ao�t 2014 11:44:37
	 */
	public void loading() {
		source = new ArrayList<Etudiant>();
		target = new ArrayList<Etudiant>();
		oldTarget = new ArrayList<Etudiant>();
		etudiants = new DualListModel<Etudiant>(source, target);
		loadFromModel = false;
		groupeModelId = null;
		onlyAffectation = false;
		if (groupePedagogiqueDto.getId() != null) {
			loadEtudiantAffecte(groupePedagogiqueDto.getId());
		}
		if (editMode) {
			loadEtudiantNonAffecte();
			// loadGroupesOfSection();
		}
		// loadIndividuAffecte();

		if (loadGroupes) {
			loadGroupeInfo();
		}
		sectionEmpty = false;
		if (source.size() == 0 && target.size() == 0
				&& groupePedagogiqueDto.getIdSection() != null) {
			sectionEmpty = true;
		}
	}

	/**
	 * [GroupeAffectationBean.loadEtudiants] Method
	 * 
	 * @author MAKERRI Sofiane on : 22 juil. 2014 12:50:02
	 */
	public void loadEtudiantNonAffecte() {
		try {
			source = new ArrayList<Etudiant>();
			if (idAnnee == null || idOof == null
					|| groupePedagogiqueDto.getId() == null
					|| situationValidee == null) {
				return;
			}
			DossierInscriptionAdministrativeDto dto = new DossierInscriptionAdministrativeDto();
			dto.setAnneeAcademiqueId(idAnnee);
			dto.setNiveauId(groupePedagogiqueDto.getPeriodeNiveauId());
			// dto.setRefCodeNiveau(groupePedagogiqueDto.getPeriodeNiveauCode());
			dto.setOuvertureOffreFormationId(idOof);
			dto.setRefEtablissementId(sessionBean.getIdEtablissement());
			if (groupePedagogiqueDto.getMcOptionnelle() != null
					&& !groupePedagogiqueDto.getMcOptionnelle()) {
				dossierInscriptionDtoList = dossierInscriptionAdministrativeService
						._findEtudiant(dto, groupePedagogiqueDto);
			} else {
				dossierInscriptionDtoList = parcoursIndividualiseService
						.findDossierInscription(dto, groupePedagogiqueDto,
								situationValidee.getId());
			}
			if (dossierInscriptionDtoList != null) {
				for (DossierInscriptionAdministrativeDto _dia : dossierInscriptionDtoList) {

					boolean exist = false;
					if (target != null) {
						for (Etudiant etudiant : target) {
							if (_dia.getId() == etudiant.getIdDossier()) {
								exist = true;
								break;
							}

						}
						if (exist) {
							continue;
						}
					}
					Etudiant etudiant = new Etudiant();
					RefIndividuDto individu = refIndividuService
							.findByIdentifiant(_dia.getNin());
					if (individu != null) {
						etudiant.setNom(individu.getNomLatin());
						etudiant.setPrenom(individu.getPrenomLatin());
						etudiant.setIndividuId(individu.getId());
						etudiant.setDateNaissance(Utility.formatDate(
								individu.getDateNaissance(), "dd/MM/yyyy"));
					}
					// if (_dia.getNumeroMatricule() == null) {
					// DossierEtudiantDto _de = dossierEtudiantService
					// .findById(_dia.getDossierEtudiantId());
					// if (_de != null) {
					// etudiant.setMatricule(_de.getNumeroMatricule());
					// }
					// } else {
					// etudiant.setMatricule(_dia.getNumeroMatricule());
					// }
					etudiant.setNumeroInscription(_dia.getNumeroInscription());
					etudiant.setPhotoName(_dia.getPhoto());
					if (sessionBean.getFolderPhoto() != null
							&& _dia.getPhoto() != null) {
						etudiant.setUrlPhoto(sessionBean.getFolderPhoto()
								+ _dia.getPhoto());
					}
					etudiant.setIdDossier(_dia.getId());
					source.add(etudiant);
				}
			}

			/**
			 * * dans le cas d'utilisation de modele d'affectation, restaurer
			 * les etudiant dej� affect� * avant l'application de modele
			 * dans source
			 */

			// if (oldTarget != null) {
			// for (Etudiant oldEtudiant : oldTarget) {
			// boolean exist = false;
			// for (Etudiant etudiant : source)
			// if (oldEtudiant.getId() == etudiant.getId()) {
			// exist = true;
			// break;
			// }
			// if (!exist) {
			// source.add(oldEtudiant);
			// }
			// }
			//
			// }

			etudiants = new DualListModel<Etudiant>(source, target);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * [GroupeAffectationBean.loadEtudiantAffecte] Method
	 * 
	 * @author MAKERRI Sofiane on : 11 ao�t 2014 11:51:44
	 */
	public void loadEtudiantAffecte(Integer groupeId) {
		try {
			// target = new ArrayList<Etudiant>();
			if (groupeId == null) {
				return;
			}
			List<AffectationGroupePedagogiqueDto> _diaList = affectationGroupePedagogiqueService
					.findByGroupePedagogiqueId(groupeId);
			if (_diaList != null) {

				for (AffectationGroupePedagogiqueDto _agp : _diaList) {
					DossierInscriptionAdministrativeDto dia = dossierInscriptionAdministrativeService
							.findById(_agp.getDossierInscriptionId());
					if (dia == null) {
						continue;
					}
					RefIndividuDto _refIndividuDto = refIndividuService
							.findByIdentifiant(dia.getNin());
					if (_refIndividuDto != null) {
						Etudiant etudiant = new Etudiant();
						// if (_agp.getNumeroMatricule() == null) {
						// DossierEtudiantDto _de = dossierEtudiantService
						// .findById(_agp.getDossierInscriptionId());
						// if (_de != null) {
						// etudiant.setMatricule(_de.getNumeroMatricule());
						// }
						// } else {
						// etudiant.setMatricule(_agp.getNumeroMatricule());
						// }
						etudiant.setNumeroInscription(_agp
								.getNumeroInscription());
						// etudiant.setMatricule(_agp.getNumeroMatricule());
						etudiant.setIdDossier(_agp.getDossierInscriptionId());
						etudiant.setNom(_refIndividuDto.getNomLatin());
						etudiant.setPrenom(_refIndividuDto.getPrenomLatin());
						etudiant.setIndividuId(_refIndividuDto.getId());
						etudiant.setDateNaissance(Utility.formatDate(
								_refIndividuDto.getDateNaissance(),
								"dd/MM/yyyy"));
						etudiant.setPhotoName(_agp.getUrlPhoto());
						if (sessionBean.getFolderPhoto() != null
								&& _agp.getUrlPhoto() != null) {
							etudiant.setUrlPhoto(sessionBean.getFolderPhoto()
									+ _agp.getUrlPhoto());
						}
						etudiant.setId(_agp.getId());
						target.add(etudiant);

					}

				}

				if (!loadFromModel) {
					oldTarget = target;
				}
			}
			etudiants.setTarget(target);
		} catch (Exception e) {
			Utility.showErrorMessage(e.getMessage());
		}
	}

	/**
	 * [GroupeAffectationBean.loadGroupeInfo] Method
	 * 
	 * @author MAKERRI Sofiane on : 9 sept. 2014 10:11:57
	 */
	public void loadGroupeInfo() {
		try {
			groupeInfoList = new ArrayList<GroupeInfo>();
			List<AffectationGroupePedagogiqueDto> etudiantSectionNonAffecte = null;
			List<GroupePedagogiqueDto> groupes = new ArrayList<GroupePedagogiqueDto>();
			if (groupePedagogiqueDto.getNcTypeGroupeId() != null
					&& groupePedagogiqueDto.getNcTypeGroupeId().equals(
							idSectionType)) {
				groupes = groupePedagogiqueService
						.findGroupesOfSection(groupePedagogiqueDto.getId());

				/***
				 * charger les etudiant qui font partie de la section et qui non
				 * pas encore affect� a un groupe
				 */
				etudiantSectionNonAffecte = affectationGroupePedagogiqueService
						.findOnlyBySectionId(groupePedagogiqueDto.getId());
			} else {
				groupes.add(groupePedagogiqueDto);
				if (groupePedagogiqueDto.getIdSection() == null) {
					/***** groupe sans section ******/
					groupePedagogiqueDto.setNomSection(groupePedagogiqueDto
							.getNomAffichage());
					groupePedagogiqueDto.setSectionSansGroupe(true);
				}
			}

			for (GroupePedagogiqueDto item : groupes) {
				GroupeInfo groupeInfo = new GroupeInfo();

				List<AffectationGroupePedagogiqueDto> affectations = affectationGroupePedagogiqueService
						.findByGroupePedagogiqueId(item.getId());
				List<Etudiant> etudiants = new ArrayList<Etudiant>();
				for (AffectationGroupePedagogiqueDto _agp : affectations) {
					DossierInscriptionAdministrativeDto dia = dossierInscriptionAdministrativeService
							.findById(_agp.getDossierInscriptionId());
					if (dia == null) {
						continue;
					}
					RefIndividuDto _refIndividuDto = refIndividuService
							.findByIdentifiant(dia.getNin());
					if (_refIndividuDto != null) {
						Etudiant etudiant = new Etudiant();
						// if (_agp.getNumeroMatricule() == null) {
						// DossierEtudiantDto _de = dossierEtudiantService
						// .findById(_agp.getDossierInscriptionId());
						// if (_de != null) {
						// etudiant.setMatricule(_de.getNumeroMatricule());
						// }
						// } else {
						// etudiant.setMatricule(_agp.getNumeroMatricule());
						// }
						etudiant.setNumeroInscription(_agp
								.getNumeroInscription());
						etudiant.setIdDossier(_agp.getDossierInscriptionId());
						etudiant.setNom(_refIndividuDto.getNomLatin());
						etudiant.setPrenom(_refIndividuDto.getPrenomLatin());
						etudiant.setDateNaissance(Utility.formatDate(
								_refIndividuDto.getDateNaissance(),
								"dd/MM/yyyy"));
						etudiant.setIndividuId(_refIndividuDto.getId());
						etudiant.setPhotoName(_agp.getUrlPhoto());
						if (sessionBean.getFolderPhoto() != null
								&& _agp.getUrlPhoto() != null) {
							etudiant.setUrlPhoto(sessionBean.getFolderPhoto()
									+ _agp.getUrlPhoto());
						}
						etudiant.setId(_agp.getId());
						etudiants.add(etudiant);
					}

				}
				item.setSectionSansGroupe(false);
				groupeInfo.setGroupePedagogiqueDto(item);
				groupeInfo.setEtudiants(etudiants);
				groupeInfo.setId(item.getId());
				groupeInfoList.add(groupeInfo);
			}

			/***
			 * charger les etudiant de la section qui ne sont pas encore
			 * affect�s
			 ***/
			if (etudiantSectionNonAffecte != null
					&& !etudiantSectionNonAffecte.isEmpty()) {
				List<Etudiant> etudiants = new ArrayList<Etudiant>();

				for (AffectationGroupePedagogiqueDto _agp : etudiantSectionNonAffecte) {
					DossierInscriptionAdministrativeDto dia = dossierInscriptionAdministrativeService
							.findById(_agp.getDossierInscriptionId());
					if (dia == null) {
						continue;
					}

					RefIndividuDto _refIndividuDto = refIndividuService
							.findByIdentifiant(dia.getNin());
					if (_refIndividuDto != null) {
						Etudiant etudiant = new Etudiant();
						// if (_agp.getNumeroMatricule() == null) {
						// DossierEtudiantDto _de = dossierEtudiantService
						// .findById(_agp.getDossierInscriptionId());
						// if (_de != null) {
						// etudiant.setMatricule(_de.getNumeroMatricule());
						// }
						// } else {
						// etudiant.setMatricule(_agp.getNumeroMatricule());
						// }
						etudiant.setNumeroInscription(_agp
								.getNumeroInscription());
						etudiant.setIdDossier(_agp.getDossierInscriptionId());
						etudiant.setNom(_refIndividuDto.getNomLatin());
						etudiant.setPrenom(_refIndividuDto.getPrenomLatin());
						etudiant.setDateNaissance(Utility.formatDate(
								_refIndividuDto.getDateNaissance(),
								"dd/MM/yyyy"));
						etudiant.setIndividuId(_refIndividuDto.getId());
						etudiant.setPhotoName(_agp.getUrlPhoto());
						if (sessionBean.getFolderPhoto() != null
								&& _agp.getUrlPhoto() != null) {
							etudiant.setUrlPhoto(sessionBean.getFolderPhoto()
									+ _agp.getUrlPhoto());
						}
						etudiant.setId(_agp.getId());
						etudiants.add(etudiant);
					}

				}
				GroupeInfo groupeInfo = new GroupeInfo();
				if (groupes.isEmpty()) {
					/***** section sans groupe ******/
					groupePedagogiqueDto.setSectionSansGroupe(true);
					groupePedagogiqueDto.setNomSection(groupePedagogiqueDto
							.getNomAffichage());
				}
				groupeInfo.setGroupePedagogiqueDto(groupePedagogiqueDto);
				groupeInfo.setId(groupePedagogiqueDto.getId());
				groupeInfo.setEtudiants(etudiants);
				groupeInfoList.add(groupeInfo);
			}
			if (groupeInfoList.size() == 1) {
				GroupeInfo _gi = groupeInfoList.get(0);
				if (_gi != null) {
					GroupePedagogiqueDto _groupe = groupeInfoList.get(0)
							.getGroupePedagogiqueDto();
					if (_groupe != null
							&& !_groupe.getNcTypeGroupeId().equals(
									idSectionType)) {
						etudiantsList = _gi.getEtudiants();
						onlyAffectation = true;
					}
				}
			}

		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	/**
	 * [GroupeAffectationBean.loadIndividuAffecte] Method
	 * 
	 * @author MAKERRI Sofiane on : 12 ao�t 2014 09:40:11
	 */
	public void loadIndividuAffecte() {
		try {
			individuGroupeList = refAffectationService.findIndividus("groupe",
					refGroupeId);
		} catch (Exception e) {

		}
	}

	/**
	 * [GroupeAffectationBean.findAffectation] Method
	 * 
	 * @author MAKERRI Sofiane on : 12 ao�t 2014 09:43:11
	 * @param individuId
	 * @return
	 */
	private RefAffectationDto findAffectation(Integer individuId) {
		if (individuGroupeList != null) {
			for (RefAffectationDto current : individuGroupeList) {
				if (current.getIdIndividu().equals(individuId)) {
					return current;
				}
			}
		}
		return null;
	}

	/**
	 * [GroupeAffectationBean.dossierInscriptionAdministrativeService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 juil. 2014 12:49:19
	 * @return the dossierInscriptionAdministrativeService
	 */
	public DossierInscriptionAdministrativeService getDossierInscriptionAdministrativeService() {
		return dossierInscriptionAdministrativeService;
	}

	/**
	 * [GroupeAffectationBean.dossierInscriptionAdministrativeService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 juil. 2014 12:49:19
	 * @param dossierInscriptionAdministrativeService
	 *            the dossierInscriptionAdministrativeService to set
	 */
	public void setDossierInscriptionAdministrativeService(
			DossierInscriptionAdministrativeService dossierInscriptionAdministrativeService) {
		this.dossierInscriptionAdministrativeService = dossierInscriptionAdministrativeService;
	}

	/**
	 * [GroupeAffectationBean.sessionBean] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 juil. 2014 13:38:28
	 * @return the sessionBean
	 */
	public SessionBean getSessionBean() {
		return sessionBean;
	}

	/**
	 * [GroupeAffectationBean.sessionBean] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 juil. 2014 13:38:28
	 * @param sessionBean
	 *            the sessionBean to set
	 */
	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}

	/**
	 * [GroupeAffectationBean.etudiants] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 juil. 2014 14:24:35
	 * @return the etudiants
	 */
	public DualListModel<Etudiant> getEtudiants() {
		return etudiants;
	}

	/**
	 * [GroupeAffectationBean.etudiants] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 juil. 2014 14:24:35
	 * @param etudiants
	 *            the etudiants to set
	 */
	public void setEtudiants(DualListModel<Etudiant> etudiants) {
		this.etudiants = etudiants;
	}

	/**
	 * [GroupeAffectationBean.ppmWSClient] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 juil. 2014 15:42:59
	 * @return the ppmWSClient
	 */
	// public PpmWS getPpmWSClient() {
	// return ppmWSClient;
	// }

	/**
	 * [GroupeAffectationBean.ppmWSClient] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 juil. 2014 15:42:59
	 * @param ppmWSClient
	 *            the ppmWSClient to set
	 */
	// public void setPpmWSClient(PpmWS ppmWSClient) {
	// this.ppmWSClient = ppmWSClient;
	// }

	/**
	 * [GroupeAffectationBean.dossierInscriptionDtoList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 11 ao�t 2014 11:38:53
	 * @return the dossierInscriptionDtoList
	 */
	public List<DossierInscriptionAdministrativeDto> getDossierInscriptionDtoList() {
		return dossierInscriptionDtoList;
	}

	/**
	 * [GroupeAffectationBean.dossierInscriptionDtoList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 11 ao�t 2014 11:38:53
	 * @param dossierInscriptionDtoList
	 *            the dossierInscriptionDtoList to set
	 */
	public void setDossierInscriptionDtoList(
			List<DossierInscriptionAdministrativeDto> dossierInscriptionDtoList) {
		this.dossierInscriptionDtoList = dossierInscriptionDtoList;
	}

	/**
	 * [GroupeAffectationBean.idGp] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 juil. 2014 15:47:58
	 * @return the idGp
	 */
	public Integer getIdGp() {
		return idGp;
	}

	/**
	 * [GroupeAffectationBean.idGp] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 juil. 2014 15:47:58
	 * @param idGp
	 *            the idGp to set
	 */
	public void setIdGp(Integer idGp) {
		this.idGp = idGp;
	}

	/**
	 * [GroupeAffectationBean.idAnnee] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 juil. 2014 15:47:58
	 * @return the idAnnee
	 */
	public Integer getIdAnnee() {
		return idAnnee;
	}

	/**
	 * [GroupeAffectationBean.idAnnee] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 juil. 2014 15:47:58
	 * @param idAnnee
	 *            the idAnnee to set
	 */
	public void setIdAnnee(Integer idAnnee) {
		this.idAnnee = idAnnee;
	}

	/**
	 * [GroupeAffectationBean.semestre] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 juil. 2014 15:47:58
	 * @return the semestre
	 */
	// public String getSemestre() {
	// return semestre;
	// }

	/**
	 * [GroupeAffectationBean.semestre] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 juil. 2014 15:47:58
	 * @param semestre
	 *            the semestre to set
	 */
	// public void setSemestre(String semestre) {
	// this.semestre = semestre;
	// if ((semestre != null) && (semestre.equals("null"))) {
	// this.semestre = null;
	// } else {
	// this.semestre = semestre;
	// }
	// }

	/**
	 * [GroupeAffectationBean.idPeriode] Getter
	 * 
	 * @author MAKERRI Sofiane on : 8 sept. 2014 10:06:57
	 * @return the idPeriode
	 */
	public Integer getIdPeriode() {
		return idPeriode;
	}

	/**
	 * [GroupeAffectationBean.idPeriode] Setter
	 * 
	 * @author MAKERRI Sofiane on : 8 sept. 2014 10:06:57
	 * @param idPeriode
	 *            the idPeriode to set
	 */
	public void setIdPeriode(Integer idPeriode) {
		this.idPeriode = idPeriode;
	}

	/**
	 * [GroupeAffectationBean.idOof] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 juil. 2014 15:50:24
	 * @return the idOof
	 */
	public Integer getIdOof() {
		return idOof;
	}

	/**
	 * [GroupeAffectationBean.idOof] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 juil. 2014 15:50:24
	 * @param idOof
	 *            the idOof to set
	 */
	public void setIdOof(Integer idOof) {
		this.idOof = idOof;
	}

	/**
	 * [GroupeAffectationBean.groupePedagogiqueSearchBean] Getter
	 * 
	 * @author MAKERRI Sofiane on : 10 ao�t 2014 14:57:57
	 * @return the groupePedagogiqueSearchBean
	 */
	// public GroupePedagogiqueSearchBean getGroupePedagogiqueSearchBean() {
	// return groupePedagogiqueSearchBean;
	// }

	/**
	 * [GroupeAffectationBean.groupePedagogiqueSearchBean] Setter
	 * 
	 * @author MAKERRI Sofiane on : 10 ao�t 2014 14:57:57
	 * @param groupePedagogiqueSearchBean
	 *            the groupePedagogiqueSearchBean to set
	 */
	// public void setGroupePedagogiqueSearchBean(
	// GroupePedagogiqueSearchBean groupePedagogiqueSearchBean) {
	// this.groupePedagogiqueSearchBean = groupePedagogiqueSearchBean;
	// if (groupePedagogiqueSearchBean != null) {
	// this.idAnnee = groupePedagogiqueSearchBean.getIdAnnee();
	// this.idOof = groupePedagogiqueSearchBean.getIdOof();
	// if (groupePedagogiqueSearchBean.getCurrent() != null) {
	// this.idGp = groupePedagogiqueSearchBean.getCurrent().getId();
	// this.refGroupeId = groupePedagogiqueSearchBean.getCurrent()
	// .getIdGroupe();
	// }
	// }
	// }

	/**
	 * [GroupeAffectationBean.affectationGroupePedagogiqueService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 11 ao�t 2014 08:44:39
	 * @return the affectationGroupePedagogiqueService
	 */
	public AffectationGroupePedagogiqueService getAffectationGroupePedagogiqueService() {
		return affectationGroupePedagogiqueService;
	}

	/**
	 * [GroupeAffectationBean.affectationGroupePedagogiqueService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 11 ao�t 2014 08:44:39
	 * @param affectationGroupePedagogiqueService
	 *            the affectationGroupePedagogiqueService to set
	 */
	public void setAffectationGroupePedagogiqueService(
			AffectationGroupePedagogiqueService affectationGroupePedagogiqueService) {
		this.affectationGroupePedagogiqueService = affectationGroupePedagogiqueService;
	}

	/**
	 * [GroupeAffectationBean.save] Method
	 * 
	 * @author MAKERRI Sofiane on : 11 ao�t 2014 08:33:26
	 */
	public void save() {
		boolean change = false;
		try {
			List<Etudiant> etudiantTarget = etudiants.getTarget();
			if (etudiantTarget != null
					&& etudiantTarget.size() > groupePedagogiqueDto
							.getCapaciteMax()) {
				Utility.showErrorMessage(bundleGroupe
						.getString("groupePedagogique_groupe_capacite_max_depasse"));
				return;
			}
			if (etudiantTarget != null
					&& etudiantTarget.size() != 0
					&& etudiantTarget.size() < groupePedagogiqueDto
							.getCapaciteMin()) {
				Utility.showWarningMessage(bundleGroupe
						.getString("groupePedagogique_groupe_capacite_min_non_depasse"));
			}

			if (oldTarget != null) {
				for (Etudiant _etudiant : oldTarget) {
					boolean exist = false;
					for (Etudiant etudiant : etudiantTarget) {
						if (_etudiant.getId() == etudiant.getId()) {
							exist = true;
							break;
						}
					}
					if (!exist) {
						if (groupePedagogiqueDto.getNcTypeGroupeCode() != null
								&& groupePedagogiqueDto
										.getNcTypeGroupeCode()
										.equals(CursusConstants.CODE_TYPE_GROUPE_SECTION)) {
							List<AffectationGroupePedagogiqueDto> affGroupe = affectationGroupePedagogiqueService
									.findAffectationInGroupe(
											_etudiant.getIdDossier(),
											groupePedagogiqueDto.getId());
							if (affGroupe != null && !affGroupe.isEmpty()) {
								Utility.showErrorMessage(bundleGroupe
										.getString("groupePedagogique_groupe_etudiant_affecte_au_groupe")
										+ "[" + _etudiant.getNomPrenom() + "]");
								return;
							}
						}
						AffectationGroupePedagogiqueDto affectationGroupePedagogiqueDto = getAffectationGroupePedagogiqueDto(_etudiant);
						affectationGroupePedagogiqueService
								.remove(affectationGroupePedagogiqueDto);
						RefAffectationDto refAffectationDto = findAffectation(_etudiant
								.getIndividuId());
						if (refAffectationDto != null) {
							refAffectationService.delete(refAffectationDto);
						}
						change = true;
					}

				}
			}
			if (etudiantTarget != null) {
				for (Etudiant etudiant : etudiantTarget) {
					// if (etudiant.getId() == null)
					{
						AffectationGroupePedagogiqueDto agp = getAffectationGroupePedagogiqueDto(etudiant);
						affectationGroupePedagogiqueService.insertOrUpdate(agp);
						String result = saveAffecation(etudiant.getIndividuId());
						if (result != null) {
							Utility.showErrorMessage(result);
							return;
						}
						etudiant.setId(agp.getId());
						change = true;
					}
				}
			}
			loadFromModel = false;
			if (change) {
				Utility.showSuccessSaveMessage();
			}
		} catch (Exception e) {
			Utility.showErrorMessage(e.getMessage());
			log.info(e.getMessage());
		}
	}

	/**
	 * [GroupeAffectationBean.bundleCommon] Getter
	 * 
	 * @author MAKERRI Sofiane on : 11 ao�t 2014 09:02:46
	 * @return the bundleCommon
	 */
	public ResourceBundle getBundleCommon() {
		return bundleCommon;
	}

	/**
	 * [GroupeAffectationBean.bundleCommon] Setter
	 * 
	 * @author MAKERRI Sofiane on : 11 ao�t 2014 09:02:46
	 * @param bundleCommon
	 *            the bundleCommon to set
	 */
	public void setBundleCommon(ResourceBundle bundleCommon) {
		this.bundleCommon = bundleCommon;
	}

	/**
	 * [GroupeAffectationBean.getAffectationGroupePedagogiqueDto] Method
	 * 
	 * @author MAKERRI Sofiane on : 11 ao�t 2014 13:36:53
	 * @param etudiant
	 * @return
	 */
	private AffectationGroupePedagogiqueDto getAffectationGroupePedagogiqueDto(
			Etudiant _etudiant) {
		AffectationGroupePedagogiqueDto agp = new AffectationGroupePedagogiqueDto();
		agp.setDossierInscriptionId(_etudiant.getIdDossier());
		agp.setGroupePedagogiqueId(groupePedagogiqueDto.getId());
		agp.setId(_etudiant.getId());
		return agp;
	}

	/**
	 * [GroupeAffectationBean.saveAffecation] Method
	 * 
	 * @author MAKERRI Sofiane on : 12 ao�t 2014 08:59:47
	 */
	public String saveAffecation(Integer individuId) {
		try {
			RefAffectationDto refAffectationDto = new RefAffectationDto();
			refAffectationDto.setDateDebut(new Date());
			refAffectationDto.setIdGroupe(refGroupeId);
			refAffectationDto.setIdIndividu(individuId);

			if (roleEtudiant != null) {
				refAffectationDto.setRoleId(roleEtudiant.getId());
			}

			refAffectationDto = refAffectationService
					.saveOrUpdate(refAffectationDto);
			return null;

		} catch (Exception e) {
			return e.getMessage();
		}
	}

	/**
	 * [GroupeAffectationBean.refGroupeId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 12 ao�t 2014 08:50:12
	 * @return the refGroupeId
	 */
	public Integer getRefGroupeId() {
		return refGroupeId;
	}

	/**
	 * [GroupeAffectationBean.refGroupeId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 12 ao�t 2014 08:50:12
	 * @param refGroupeId
	 *            the refGroupeId to set
	 */
	public void setRefGroupeId(Integer refGroupeId) {
		this.refGroupeId = refGroupeId;
	}

	/**
	 * [GroupeAffectationBean.roleEtudiant] Getter
	 * 
	 * @author MAKERRI Sofiane on : 12 ao�t 2014 09:04:01
	 * @return the roleEtudiant
	 */
	public NomenclatureDto getRoleEtudiant() {
		return roleEtudiant;
	}

	/**
	 * [GroupeAffectationBean.roleEtudiant] Setter
	 * 
	 * @author MAKERRI Sofiane on : 12 ao�t 2014 09:04:01
	 * @param roleEtudiant
	 *            the roleEtudiant to set
	 */
	public void setRoleEtudiant(NomenclatureDto roleEtudiant) {
		this.roleEtudiant = roleEtudiant;
	}

	/**
	 * [GroupeAffectationBean.individuGroupeList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 12 ao�t 2014 09:39:42
	 * @return the individuGroupeList
	 */
	public List<RefAffectationDto> getIndividuGroupeList() {
		return individuGroupeList;
	}

	/**
	 * [GroupeAffectationBean.individuGroupeList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 12 ao�t 2014 09:39:42
	 * @param individuGroupeList
	 *            the individuGroupeList to set
	 */
	public void setIndividuGroupeList(List<RefAffectationDto> individuGroupeList) {
		this.individuGroupeList = individuGroupeList;
	}

	/**
	 * [GroupeAffectationBean.getImage] Method
	 * 
	 * @author MAKERRI Sofiane on : 12 ao�t 2014 10:03:46
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public StreamedContent getImage(String url) throws IOException {
		FacesContext context = FacesContext.getCurrentInstance();

		if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
			return new DefaultStreamedContent();
		} else {
			String contentType = FacesContext.getCurrentInstance()
					.getExternalContext().getMimeType(url);

			InputStream inputStream = new FileInputStream(url);
			return new DefaultStreamedContent(inputStream, contentType);

		}
	}

	/**
	 * [GroupeAffectationBean.groupePedagogiqueDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 12 ao�t 2014 10:44:38
	 * @return the groupePedagogiqueDto
	 */
	public GroupePedagogiqueDto getGroupePedagogiqueDto() {
		return groupePedagogiqueDto;
	}

	/**
	 * [GroupeAffectationBean.groupePedagogiqueDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 12 ao�t 2014 10:44:38
	 * @param groupePedagogiqueDto
	 *            the groupePedagogiqueDto to set
	 */
	public void setGroupePedagogiqueDto(
			GroupePedagogiqueDto groupePedagogiqueDto) {
		this.groupePedagogiqueDto = groupePedagogiqueDto;
		if (groupePedagogiqueDto != null) {
			this.idAnnee = groupePedagogiqueDto.getIdAnneeAcademique();
			this.idOof = groupePedagogiqueDto.getOofId();
			this.idGp = groupePedagogiqueDto.getId();
			this.refGroupeId = groupePedagogiqueDto.getIdGroupe();
		}

	}

	/**
	 * [GroupeAffectationBean.sectionEmpty] Getter
	 * 
	 * @author MAKERRI Sofiane on : 12 ao�t 2014 12:23:45
	 * @return the sectionEmpty
	 */
	public boolean isSectionEmpty() {
		return sectionEmpty;
	}

	/**
	 * [GroupeAffectationBean.sectionEmpty] Setter
	 * 
	 * @author MAKERRI Sofiane on : 12 ao�t 2014 12:23:45
	 * @param sectionEmpty
	 *            the sectionEmpty to set
	 */
	public void setSectionEmpty(boolean sectionEmpty) {
		this.sectionEmpty = sectionEmpty;
	}

	/**
	 * [GroupeAffectationBean.groupeOfSectionList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 31 ao�t 2014 12:01:47
	 * @return the groupeOfSectionList
	 */
	public List<SelectItem> getGroupeOfSectionList() {
		return groupeOfSectionList;
	}

	/**
	 * [GroupeAffectationBean.groupeOfSectionList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 31 ao�t 2014 12:01:47
	 * @param groupeOfSectionList
	 *            the groupeOfSectionList to set
	 */
	public void setGroupeOfSectionList(List<SelectItem> groupeOfSectionList) {
		this.groupeOfSectionList = groupeOfSectionList;
	}

	/**
	 * [GroupeAffectationBean.groupePedagogiqueService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 31 ao�t 2014 12:06:21
	 * @return the groupePedagogiqueService
	 */
	public GroupePedagogiqueService getGroupePedagogiqueService() {
		return groupePedagogiqueService;
	}

	/**
	 * [GroupeAffectationBean.groupePedagogiqueService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 31 ao�t 2014 12:06:21
	 * @param groupePedagogiqueService
	 *            the groupePedagogiqueService to set
	 */
	public void setGroupePedagogiqueService(
			GroupePedagogiqueService groupePedagogiqueService) {
		this.groupePedagogiqueService = groupePedagogiqueService;
	}

	/**
	 * [GroupeAffectationBean.groupeModelId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 31 ao�t 2014 12:16:31
	 * @return the groupeModelId
	 */
	public Integer getGroupeModelId() {
		return groupeModelId;
	}

	/**
	 * [GroupeAffectationBean.groupeModelId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 31 ao�t 2014 12:16:31
	 * @param groupeModelId
	 *            the groupeModelId to set
	 */
	public void setGroupeModelId(Integer groupeModelId) {
		this.groupeModelId = groupeModelId;
	}

	/**
	 * [GroupeAffectationBean.loadFromModel] Getter
	 * 
	 * @author MAKERRI Sofiane on : 1 sept. 2014 08:10:25
	 * @return the loadFromModel
	 */
	public boolean isLoadFromModel() {
		return loadFromModel;
	}

	/**
	 * [GroupeAffectationBean.loadFromModel] Setter
	 * 
	 * @author MAKERRI Sofiane on : 1 sept. 2014 08:10:25
	 * @param loadFromModel
	 *            the loadFromModel to set
	 */
	public void setLoadFromModel(boolean loadFromModel) {
		this.loadFromModel = loadFromModel;
	}

	/**
	 * [GroupeAffectationBean.groupeModelChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 31 ao�t 2014 13:18:29
	 */
	public void groupeModelChanged() {
		if (groupeModelId != null) {
			loadFromModel = true;
			source = new ArrayList<Etudiant>();
			target = new ArrayList<Etudiant>();
			oldTarget = etudiants.getTarget();
			loadEtudiantAffecte(groupeModelId);
			loadEtudiantNonAffecte();
		}
	}

	/**
	 * [GroupeAffectationBean.oldTarget] Getter
	 * 
	 * @author MAKERRI Sofiane on : 1 sept. 2014 08:58:32
	 * @return the oldTarget
	 */
	public List<Etudiant> getOldTarget() {
		return oldTarget;
	}

	/**
	 * [GroupeAffectationBean.oldTarget] Setter
	 * 
	 * @author MAKERRI Sofiane on : 1 sept. 2014 08:58:32
	 * @param oldTarget
	 *            the oldTarget to set
	 */
	public void setOldTarget(List<Etudiant> oldTarget) {
		this.oldTarget = oldTarget;
	}

	/**
	 * [GroupeAffectationBean.source] Getter
	 * 
	 * @author MAKERRI Sofiane on : 8 sept. 2014 17:43:59
	 * @return the source
	 */
	public List<Etudiant> getSource() {
		return source;
	}

	/**
	 * [GroupeAffectationBean.source] Setter
	 * 
	 * @author MAKERRI Sofiane on : 8 sept. 2014 17:43:59
	 * @param source
	 *            the source to set
	 */
	public void setSource(List<Etudiant> source) {
		this.source = source;
	}

	/**
	 * [GroupeAffectationBean.target] Getter
	 * 
	 * @author MAKERRI Sofiane on : 8 sept. 2014 17:43:59
	 * @return the target
	 */
	public List<Etudiant> getTarget() {
		return target;
	}

	/**
	 * [GroupeAffectationBean.target] Setter
	 * 
	 * @author MAKERRI Sofiane on : 8 sept. 2014 17:43:59
	 * @param target
	 *            the target to set
	 */
	public void setTarget(List<Etudiant> target) {
		this.target = target;
	}

	/**
	 * [GroupeAffectationBean.groupeInfoList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 9 sept. 2014 09:57:35
	 * @return the groupeInfoList
	 */
	public List<GroupeInfo> getGroupeInfoList() {
		return groupeInfoList;
	}

	/**
	 * [GroupeAffectationBean.groupeInfoList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 9 sept. 2014 09:57:35
	 * @param groupeInfoList
	 *            the groupeInfoList to set
	 */
	public void setGroupeInfoList(List<GroupeInfo> groupeInfoList) {
		this.groupeInfoList = groupeInfoList;
	}

	/**
	 * [GroupeAffectationBean.showGroupeInfo] Method
	 * 
	 * @author MAKERRI Sofiane on : 9 sept. 2014 10:32:06
	 * @param b
	 */
	public void showGroupeInfo(boolean b) {
		setLoadGroupes(b);
	}

	/**
	 * [GroupeAffectationBean.loadGroupes] Getter
	 * 
	 * @author MAKERRI Sofiane on : 9 sept. 2014 10:32:03
	 * @return the loadGroupes
	 */
	public boolean isLoadGroupes() {
		return loadGroupes;
	}

	/**
	 * [GroupeAffectationBean.loadGroupes] Setter
	 * 
	 * @author MAKERRI Sofiane on : 9 sept. 2014 10:32:03
	 * @param loadGroupes
	 *            the loadGroupes to set
	 */
	public void setLoadGroupes(boolean loadGroupes) {
		this.loadGroupes = loadGroupes;
	}

	/**
	 * [GroupeAffectationBean.imprimerTrombinoscope] Method
	 * 
	 * @author MAKERRI Sofiane on : 15 sept. 2014 10:08:18
	 */
	public void imprimerTrombinoscope() {
		try {

			// String name = groupePedagogiqueDto.getLlGroupe() +
			// "_trombinoscope";
			String name = groupePedagogiqueDto.getNomAffichage() + "_"
					+ groupePedagogiqueDto.getOfLibelleFr() + "_trombinoscope";
			FacesContext facesContext = FacesContext.getCurrentInstance();
			String SUBREPORT_DIR = facesContext.getExternalContext()
					.getRealPath("/WEB-INF/classes")
					+ "/jasper/groupepedagogique/";
			String RESOURCE_PATH_TO_INPUT_FILE_JASPER = SUBREPORT_DIR
					+ "groupe_pedagogique_trombinoscope.jrxml";
			Map<String, Object> params = new HashMap<String, Object>();
			params.put(Const.JASPER_PARAM_IMG_PAPS_KEY, facesContext
					.getExternalContext().getRealPath("images")
					+ "/logopaps.png");
			params.put(Const.JASPER_PARAM_IMG_LOGO_KEY, facesContext
					.getExternalContext().getRealPath("images")
					+ "/logo"
					+ sessionBean.getCodeEtablissement() + ".png");
			params.put(Const.JASPER_PARAM_OFFRE_FORMATION_KEY,
					groupePedagogiqueDto.getOfLibelleFr());
			params.put(Const.JASPER_PARAM_ANNEE_ACADEMIQUE_KEY,
					groupePedagogiqueDto.getCodeAnneeAcademique());
			params.put(Const.JASPER_PARAM_PERIODE_KEY,
					groupePedagogiqueDto.getPeriodeLibelleLongLt());
			params.put(Const.JASPER_PARAM_SUBREPORT_DIR_KEY, SUBREPORT_DIR);
			byte[] bytes = impressionService.viewPDFWithDataSource(
					RESOURCE_PATH_TO_INPUT_FILE_JASPER, params, groupeInfoList);
			printMgrBean.imprimer(bytes, name, "pdf");

		} catch (Exception e) {
			e.printStackTrace();
			Utility.showErrorMessage(e.getMessage());
		}
	}

	/**
	 * [GroupeAffectationBean.imprimerGroupe] Method
	 * 
	 * @author MAKERRI Sofiane on : 15 sept. 2014 10:08:46
	 */
	public void imprimerGroupe() {
		try {

			// String name = groupePedagogiqueDto.getLlGroupe();
			String name = groupePedagogiqueDto.getNomAffichage() + "_"
					+ groupePedagogiqueDto.getOfLibelleFr();
			FacesContext facesContext = FacesContext.getCurrentInstance();
			String SUBREPORT_DIR = facesContext.getExternalContext()
					.getRealPath("/WEB-INF/classes")
					+ "/jasper/groupepedagogique/";
			String RESOURCE_PATH_TO_INPUT_FILE_JASPER = SUBREPORT_DIR
					+ "groupe_pedagogique.jrxml";
			Map<String, Object> params = new HashMap<String, Object>();
			params.put(Const.JASPER_PARAM_IMG_PAPS_KEY, facesContext
					.getExternalContext().getRealPath("images")
					+ "/logopaps.png");
			params.put(Const.JASPER_PARAM_IMG_LOGO_KEY, facesContext
					.getExternalContext().getRealPath("images")
					+ "/logo"
					+ sessionBean.getCodeEtablissement() + ".png");
			params.put(Const.JASPER_PARAM_OFFRE_FORMATION_KEY,
					groupePedagogiqueDto.getOfLibelleFr());
			params.put(Const.JASPER_PARAM_ANNEE_ACADEMIQUE_KEY,
					groupePedagogiqueDto.getCodeAnneeAcademique());
			params.put(Const.JASPER_PARAM_PERIODE_KEY,
					groupePedagogiqueDto.getPeriodeLibelleLongLt());
			params.put(Const.JASPER_PARAM_SUBREPORT_DIR_KEY, SUBREPORT_DIR);

			byte[] bytes = impressionService.viewPDFWithDataSource(
					RESOURCE_PATH_TO_INPUT_FILE_JASPER, params, groupeInfoList);
			printMgrBean.imprimer(bytes, name, "pdf");

		} catch (Exception e) {
			e.printStackTrace();
			Utility.showErrorMessage(e.getMessage());
		}
	}

	/**
	 * [GroupeAffectationBean.printMgrBean] Getter
	 * 
	 * @author MAKERRI Sofiane on : 10 sept. 2014 11:49:08
	 * @return the printMgrBean
	 */
	public PrintMgrBean getPrintMgrBean() {
		return printMgrBean;
	}

	/**
	 * [GroupeAffectationBean.printMgrBean] Setter
	 * 
	 * @author MAKERRI Sofiane on : 10 sept. 2014 11:49:08
	 * @param printMgrBean
	 *            the printMgrBean to set
	 */
	public void setPrintMgrBean(PrintMgrBean printMgrBean) {
		this.printMgrBean = printMgrBean;
	}

	/**
	 * [GroupeAffectationBean.impressionService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 10 sept. 2014 11:49:34
	 * @return the impressionService
	 */
	public ImpressionService getImpressionService() {
		return impressionService;
	}

	/**
	 * [GroupeAffectationBean.impressionService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 10 sept. 2014 11:49:34
	 * @param impressionService
	 *            the impressionService to set
	 */
	public void setImpressionService(ImpressionService impressionService) {
		this.impressionService = impressionService;
	}

	/**
	 * [GroupeAffectationBean.parcoursIndividualiseService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 15 sept. 2014 17:18:36
	 * @return the parcoursIndividualiseService
	 */
	public ParcoursIndividualiseService getParcoursIndividualiseService() {
		return parcoursIndividualiseService;
	}

	/**
	 * [GroupeAffectationBean.parcoursIndividualiseService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 15 sept. 2014 17:18:36
	 * @param parcoursIndividualiseService
	 *            the parcoursIndividualiseService to set
	 */
	public void setParcoursIndividualiseService(
			ParcoursIndividualiseService parcoursIndividualiseService) {
		this.parcoursIndividualiseService = parcoursIndividualiseService;
	}

	/**
	 * [GroupeAffectationBean.situationValidee] Getter
	 * 
	 * @author MAKERRI Sofiane on : 16 sept. 2014 10:42:47
	 * @return the situationValidee
	 */
	public SituationEntiteDto getSituationValidee() {
		return situationValidee;
	}

	/**
	 * [GroupeAffectationBean.situationValidee] Setter
	 * 
	 * @author MAKERRI Sofiane on : 16 sept. 2014 10:42:47
	 * @param situationValidee
	 *            the situationValidee to set
	 */
	public void setSituationValidee(SituationEntiteDto situationValidee) {
		this.situationValidee = situationValidee;
	}

	/**
	 * [GroupeAffectationBean.situationService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 16 sept. 2014 10:42:47
	 * @return the situationService
	 */
	public SituationService getSituationService() {
		return situationService;
	}

	/**
	 * [GroupeAffectationBean.situationService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 16 sept. 2014 10:42:47
	 * @param situationService
	 *            the situationService to set
	 */
	public void setSituationService(SituationService situationService) {
		this.situationService = situationService;
	}

	/**
	 * [GroupeAffectationBean.sourceCaption] Getter
	 * 
	 * @author MAKERRI Sofiane on : 16 sept. 2014 16:10:52
	 * @return the sourceCaption
	 */
	public String getSourceCaption() {
		sourceCaption = bundleGroupe
				.getString("groupePedagogique_affectation_etudiant_picklist_source_titre");
		if (etudiants.getSource() != null) {
			sourceCaption = sourceCaption + "(" + etudiants.getSource().size()
					+ ")";
		}
		return sourceCaption;
	}

	/**
	 * [GroupeAffectationBean.sourceCaption] Setter
	 * 
	 * @author MAKERRI Sofiane on : 16 sept. 2014 16:10:52
	 * @param sourceCaption
	 *            the sourceCaption to set
	 */
	public void setSourceCaption(String sourceCaption) {
		this.sourceCaption = sourceCaption;
	}

	/**
	 * [GroupeAffectationBean.targetCaption] Getter
	 * 
	 * @author MAKERRI Sofiane on : 16 sept. 2014 16:10:52
	 * @return the targetCaption
	 */
	public String getTargetCaption() {
		targetCaption = bundleGroupe
				.getString("groupePedagogique_affectation_etudiant_picklist_target_titre");
		if (etudiants.getTarget() != null
				&& groupePedagogiqueDto.getCapaciteMax() != null) {
			Integer reste = groupePedagogiqueDto.getCapaciteMax()
					- etudiants.getTarget().size();
			targetCaption = targetCaption + "(" + etudiants.getTarget().size()
					+ ")" + " / Reste (" + reste.toString() + ")";
		}

		return targetCaption;
	}

	/**
	 * [GroupeAffectationBean.targetCaption] Setter
	 * 
	 * @author MAKERRI Sofiane on : 16 sept. 2014 16:10:52
	 * @param targetCaption
	 *            the targetCaption to set
	 */
	public void setTargetCaption(String targetCaption) {
		this.targetCaption = targetCaption;
	}

	/**
	 * [GroupeAffectationBean.onTransfer] Method
	 * 
	 * @author MAKERRI Sofiane on : 16 sept. 2014 16:21:22
	 * @param event
	 */
	public void onTransfer(TransferEvent event) {

		if (etudiants.getTarget() != null && event.isAdd()
				&& groupePedagogiqueDto.getCapaciteMax() != null
				&& event.getItems() != null) {
			Integer reste = groupePedagogiqueDto.getCapaciteMax()
					- etudiants.getTarget().size();
			if (reste < 0) {
				List<Etudiant> target = etudiants.getTarget();
				List<Etudiant> toAdd = (List<Etudiant>) event.getItems();
				if (toAdd != null) {
					for (Etudiant _etudiant : toAdd) {
						for (Etudiant _target : target) {
							if (_etudiant.getIdDossier().equals(
									_target.getIdDossier())) {
								target.remove(_target);
								etudiants.getSource().add(_target);
								break;
							}
						}
					}
				}
				etudiants.setTarget(target);
				Utility.showErrorMessage(bundleGroupe
						.getString("groupePedagogique_groupe_capacite_max_depasse"));
			}
		}

	}

	/**
	 * [GroupeAffectationBean.editMode] Getter
	 * 
	 * @author MAKERRI Sofiane on : 17 sept. 2014 14:54:41
	 * @return the editMode
	 */
	public boolean isEditMode() {
		return editMode;
	}

	/**
	 * [GroupeAffectationBean.editMode] Setter
	 * 
	 * @author MAKERRI Sofiane on : 17 sept. 2014 14:54:41
	 * @param editMode
	 *            the editMode to set
	 */
	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}

	/**
	 * [GroupeAffectationBean.idSectionType] Getter
	 * 
	 * @author MAKERRI Sofiane on : 7 oct. 2014 11:59:51
	 * @return the idSectionType
	 */
	public Integer getIdSectionType() {
		return idSectionType;
	}

	/**
	 * [GroupeAffectationBean.idSectionType] Setter
	 * 
	 * @author MAKERRI Sofiane on : 7 oct. 2014 11:59:51
	 * @param idSectionType
	 *            the idSectionType to set
	 */
	public void setIdSectionType(Integer idSectionType) {
		this.idSectionType = idSectionType;
	}

	/**
	 * [GroupeAffectationBean.refIndividuService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 9 oct. 2014 09:25:03
	 * @return the refIndividuService
	 */
	public RefIndividuService getRefIndividuService() {
		return refIndividuService;
	}

	/**
	 * [GroupeAffectationBean.refIndividuService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 9 oct. 2014 09:25:03
	 * @param refIndividuService
	 *            the refIndividuService to set
	 */
	public void setRefIndividuService(RefIndividuService refIndividuService) {
		this.refIndividuService = refIndividuService;
	}

	/**
	 * [GroupeAffectationBean.refAffectationService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 9 oct. 2014 09:28:28
	 * @return the refAffectationService
	 */
	public RefAffectationService getRefAffectationService() {
		return refAffectationService;
	}

	/**
	 * [GroupeAffectationBean.refAffectationService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 9 oct. 2014 09:28:28
	 * @param refAffectationService
	 *            the refAffectationService to set
	 */
	public void setRefAffectationService(
			RefAffectationService refAffectationService) {
		this.refAffectationService = refAffectationService;
	}

	/**
	 * [GroupeAffectationBean.dossierEtudiantService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 11 oct. 2014 10:52:48
	 * @return the dossierEtudiantService
	 */
	public DossierEtudiantService getDossierEtudiantService() {
		return dossierEtudiantService;
	}

	/**
	 * [GroupeAffectationBean.dossierEtudiantService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 11 oct. 2014 10:52:48
	 * @param dossierEtudiantService
	 *            the dossierEtudiantService to set
	 */
	public void setDossierEtudiantService(
			DossierEtudiantService dossierEtudiantService) {
		this.dossierEtudiantService = dossierEtudiantService;
	}

	/**
	 * [GroupeAffectationBean.etudiantsList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 11 oct. 2014 15:09:31
	 * @return the etudiantsList
	 */
	public List<Etudiant> getEtudiantsList() {
		return etudiantsList;
	}

	/**
	 * [GroupeAffectationBean.etudiantsList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 11 oct. 2014 15:09:31
	 * @param etudiantsList
	 *            the etudiantsList to set
	 */
	public void setEtudiantsList(List<Etudiant> etudiantsList) {
		this.etudiantsList = etudiantsList;
	}

	/**
	 * [GroupeAffectationBean.onlyAffectation] Getter
	 * 
	 * @author MAKERRI Sofiane on : 11 oct. 2014 15:19:07
	 * @return the onlyAffectation
	 */
	public boolean isOnlyAffectation() {
		return onlyAffectation;
	}

	/**
	 * [GroupeAffectationBean.onlyAffectation] Setter
	 * 
	 * @author MAKERRI Sofiane on : 11 oct. 2014 15:19:07
	 * @param onlyAffectation
	 *            the onlyAffectation to set
	 */
	public void setOnlyAffectation(boolean onlyAffectation) {
		this.onlyAffectation = onlyAffectation;
	}

	/**
	 * [GroupeAffectationBean.utilBean] Getter
	 * 
	 * @author MAKERRI Sofiane on : 6 nov. 2014 08:50:47
	 * @return the utilBean
	 */
	public UtilBean getUtilBean() {
		return utilBean;
	}

	/**
	 * [GroupeAffectationBean.utilBean] Setter
	 * 
	 * @author MAKERRI Sofiane on : 6 nov. 2014 08:50:47
	 * @param utilBean
	 *            the utilBean to set
	 */
	public void setUtilBean(UtilBean utilBean) {
		this.utilBean = utilBean;
	}

	/**
	 * [GroupeAffectationBean.nomenclatureService] Getter
	 * 
	 * @author rlaib on : 20 nov. 2014 15:37:24
	 * @return the nomenclatureService
	 */
	public NomenclatureService getNomenclatureService() {
		return nomenclatureService;
	}

	/**
	 * [GroupeAffectationBean.nomenclatureService] Setter
	 * 
	 * @author rlaib on : 20 nov. 2014 15:37:24
	 * @param nomenclatureService
	 *            the nomenclatureService to set
	 */
	public void setNomenclatureService(NomenclatureService nomenclatureService) {
		this.nomenclatureService = nomenclatureService;
	}

}
