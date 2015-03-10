/**
 * [dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.grouepedagogique.AffectationMasseBean.java] 
 * @author MAKERRI Sofiane on : 22 janv. 2015  07:58:49
 */
package dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.grouepedagogique;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.collections.comparators.ComparatorChain;
import org.primefaces.model.DualListModel;

import dz.gov.mesrs.sii.commons.web.util.CommonMessagesUtils;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.AffectationGroupePedagogiqueDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DossierInscriptionAdministrativeDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.GroupePedagogiqueDto;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.BilanSessionDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.PeriodeDto;
import dz.gov.mesrs.sii.fve.business.service.cursus.AffectationGroupePedagogiqueService;
import dz.gov.mesrs.sii.fve.business.service.cursus.DossierInscriptionAdministrativeService;
import dz.gov.mesrs.sii.fve.web.jsf.bean.SearchBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.SessionBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.UtilBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.CursusConstants;
import dz.gov.mesrs.sii.fve.web.jsf.bean.utils.Const;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefAffectationDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefAffectationService;

/**
 * @author MAKERRI Sofiane on : 22 janv. 2015 07:58:49
 */
@ManagedBean(name = "affectationMasseBean")
@ViewScoped
public class AffectationMasseBean implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author MAKERRI Sofiane on : 22 janv. 2015 07:59:11
	 */
	private static final long serialVersionUID = 1L;
	@ManagedProperty(value = "#{dossierInscriptionAdministrativeService}")
	private DossierInscriptionAdministrativeService dossierInscriptionAdministrativeService;
	@ManagedProperty(value = "#{affectationGroupePedagogiqueService}")
	private AffectationGroupePedagogiqueService affectationGroupePedagogiqueService;
	@ManagedProperty(value = "#{refAffectationServiceImpl}")
	private RefAffectationService refAffectationService;
	@ManagedProperty(value = "#{sessionBeanFve}")
	private SessionBean sessionBean;
	@ManagedProperty(value = "#{utilBean}")
	private UtilBean utilBean;
	@ManagedProperty(value = "#{searchBean}")
	private SearchBean searchBean;
	private List<DossierInscriptionAdministrativeDto> dossierInscriptionDtoList;
	private boolean showDetail;
	// private List<String> selectedGroupe;
	private List<String> groupeList;
	private List<GroupePedagogiqueDto> groupePedagogiqueDtos;
	private List<GroupePedagogiqueDto> selectedGroupePedagogiqueDtos;
	private boolean checkAll;
	private boolean showCheckAll;
	private ResourceBundle bundleGroupe;
	private Integer palier;
	private Integer maxPalier;
	private int index;
	private NomenclatureDto roleEtudiant;
	private PeriodeDto periodeDto;
	private boolean showAffectation;
	// private List<OrderCritere> criteres;

	private DualListModel<OrderCritere> criteres;
	private List<OrderCritere> source;
	private List<OrderCritere> target;

	public AffectationMasseBean() {
		super();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		bundleGroupe = facesContext.getApplication().getResourceBundle(
				facesContext,
				CursusConstants.GROUPE_PEDAGOGIQUE_BUNDLE_MSG_NAME);
	}

	@PostConstruct
	public void init() {
		showDetail = false;
		showCheckAll = false;
		showAffectation = false;
		palier = 1;
		roleEtudiant = utilBean.getNomenclatureService().findByCode(
				Const.NC_ROLE, Const.REF_CODE_ROLE_ETUDIANT);
		loadCriteres();

	}

	public void loadCriteres() {
		source = new ArrayList<OrderCritere>();
		target = new ArrayList<OrderCritere>();
		criteres = new DualListModel<OrderCritere>(source, target);
		Comparator<DossierInscriptionAdministrativeDto> comparator = new Comparator<DossierInscriptionAdministrativeDto>() {
			@Override
			public int compare(DossierInscriptionAdministrativeDto o1,
					DossierInscriptionAdministrativeDto o2) {
				return o1.getNumeroMatricule().compareToIgnoreCase(
						o2.getNumeroMatricule());
			}
		};
		source.add(new OrderCritere("Matricule", comparator));

		comparator = new Comparator<DossierInscriptionAdministrativeDto>() {
			@Override
			public int compare(DossierInscriptionAdministrativeDto o1,
					DossierInscriptionAdministrativeDto o2) {
				return o1.getIndividuNomLatin().compareToIgnoreCase(
						o2.getIndividuNomLatin());
			}
		};
		source.add(new OrderCritere("Nom", comparator));

		comparator = new Comparator<DossierInscriptionAdministrativeDto>() {
			@Override
			public int compare(DossierInscriptionAdministrativeDto o1,
					DossierInscriptionAdministrativeDto o2) {
				return o1.getIndividuPrenomLatin().compareToIgnoreCase(
						o2.getIndividuPrenomLatin());
			}
		};
		source.add(new OrderCritere("Prénom", comparator));

		comparator = new Comparator<DossierInscriptionAdministrativeDto>() {
			@Override
			public int compare(DossierInscriptionAdministrativeDto o1,
					DossierInscriptionAdministrativeDto o2) {
				return o1.getIndividuDateNaissance().compareTo(
						o2.getIndividuDateNaissance());
			}
		};
		source.add(new OrderCritere("Date naissance", comparator));

		comparator = new Comparator<DossierInscriptionAdministrativeDto>() {
			@Override
			public int compare(DossierInscriptionAdministrativeDto o1,
					DossierInscriptionAdministrativeDto o2) {
				return o1.getEtudiantSexe().compareToIgnoreCase(
						o2.getEtudiantSexe());
			}
		};
		source.add(new OrderCritere("Sexe", comparator));

		comparator = new Comparator<DossierInscriptionAdministrativeDto>() {
			@Override
			public int compare(DossierInscriptionAdministrativeDto o1,
					DossierInscriptionAdministrativeDto o2) {
				if (o1.getLastMoyenne() == (o2.getLastMoyenne())) {
					return 0;
				}
				if (o1.getLastMoyenne() < (o2.getLastMoyenne())) {
					return 1;
				}
				if (o1.getLastMoyenne() > (o2.getLastMoyenne())) {
					return -1;
				}
				return -1;
			}
		};
		source.add(new OrderCritere("Moyenne", comparator));
	}

	/**
	 * [AffectationMasseBean.calculMaxPalier] Method
	 * 
	 * @author MAKERRI Sofiane on : 28 janv. 2015 10:54:33
	 */
	private void calculMaxPalier() {
		if (selectedGroupePedagogiqueDtos != null
				&& selectedGroupePedagogiqueDtos.size() > 1
				&& dossierInscriptionDtoList != null
				&& !dossierInscriptionDtoList.isEmpty()) {
			maxPalier = dossierInscriptionDtoList.size()
					/ (selectedGroupePedagogiqueDtos.size() - 1);
			if (dossierInscriptionDtoList.size()
					% (selectedGroupePedagogiqueDtos.size() - 1) == 0) {
				maxPalier = maxPalier - 1;
			}
		} else {
			maxPalier = dossierInscriptionDtoList.size();
		}
	}

	/**
	 * [AffectationMasseBean.groupePedagogiqueChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 22 janv. 2015 08:59:36
	 */
	public void groupePedagogiqueChanged() {
		findEtudiant();
	}

	/**
	 * [AffectationMasseBean.loadGroupeList] Method
	 * 
	 * @author MAKERRI Sofiane on : 22 janv. 2015 11:27:01
	 */
	public void loadGroupeList() {
		showCheckAll = false;
		groupeList = new ArrayList<String>();
		groupePedagogiqueDtos = null;
		if (searchBean.getGroupePedagogiqueId() == null) {
			groupePedagogiqueDtos = utilBean.loadSection(searchBean.getOofId(),
					searchBean.getPeriodeId());
		} else {
			groupePedagogiqueDtos = utilBean.loadGroupeOfSection(searchBean
					.getGroupePedagogiqueId());
		}
		if (groupePedagogiqueDtos != null) {
			for (GroupePedagogiqueDto _groupe : groupePedagogiqueDtos) {
				groupeList.add(_groupe.getLlGroupe());
				// selectedGroupe.add(_groupe.getLlGroupe());
			}
		}
		if (groupeList.size() > 1) {
			showCheckAll = true;
		}
		// checkAll = (groupeList.size() == selectedGroupe.size());
	}

	/**
	 * [AffectationMasseBean.findEtudiant] Method
	 * 
	 * @author MAKERRI Sofiane on : 22 janv. 2015 09:17:52
	 */
	public void findEtudiant() {
		showDetail = false;
		loadGroupeList();
		if (groupePedagogiqueDtos == null || groupePedagogiqueDtos.isEmpty()) {
			CommonMessagesUtils
					.showWarningMessage(bundleGroupe
							.getString("groupepedagogique_data_table_search_result_no_result"));
			return;
		}
		if (searchBean.getOofId() != null && searchBean.getPeriodeId() != null) {
			if (searchBean.getGroupePedagogiqueId() != null) {
				dossierInscriptionDtoList = utilBean
						.getAffectationGroupePedagogiqueService()
						.findEtudiantsOfSection(
								searchBean.getGroupePedagogiqueId());
			} else {
				DossierInscriptionAdministrativeDto dto = new DossierInscriptionAdministrativeDto();
				dto.setAnneeAcademiqueId(sessionBean.getIdAnneeAcademique());
				PeriodeDto periodeDto = utilBean.findPeriodeById(searchBean
						.getPeriodeId());
				if (periodeDto != null && periodeDto.getIdNiveau() != 0) {
					dto.setNiveauId(periodeDto.getIdNiveau());
					dto.setOuvertureOffreFormationId(searchBean.getOofId());
					dto.setRefEtablissementId(sessionBean.getIdEtablissement());

					dossierInscriptionDtoList = dossierInscriptionAdministrativeService
							.findAdvanced(dto, false);
				}
			}
			findMoyenneLastYear();
			fillMoyenneLastYearAffectation();
			if (dossierInscriptionDtoList == null
					|| dossierInscriptionDtoList.isEmpty()) {
				CommonMessagesUtils
						.showWarningMessage(bundleGroupe
								.getString("groupepedagogique_data_table_inscription_search_result_no_result"));
				return;
			}
			showDetail = true;

		}
	}

	/**
	 * [AffectationMasseBean.findMoyenneLastYear] Method
	 * 
	 * @author MAKERRI Sofiane on : 28 janv. 2015 11:50:51
	 */
	private void findMoyenneLastYear() {
		if (searchBean.getPeriodeId() != null) {
			periodeDto = utilBean.findPeriodeById(searchBean.getPeriodeId());
			if (periodeDto != null) {
				if (dossierInscriptionDtoList != null) {
					for (DossierInscriptionAdministrativeDto dia : dossierInscriptionDtoList) {
						if (periodeDto.getRangNiveau() == 1) {
							dia.setLastMoyenne(dia.getMoyenneBac());
						} else {
							List<DossierInscriptionAdministrativeDto> inscriptions = dia
									.getDossierInscriptionAdministrativeDtos();
							boolean sortir = false;
							if (inscriptions != null) {
								for (DossierInscriptionAdministrativeDto inscription : inscriptions) {
									if (sortir) {
										break;
									}
									if (inscription.getNiveauRang() != null
											&& inscription
													.getNiveauRang()
													.equals(periodeDto
															.getRangNiveau() - 1)) {
										List<BilanSessionDto> bilanSessionDtos = inscription
												.getBilanSessionDtos();
										if (bilanSessionDtos != null) {
											for (BilanSessionDto bilanSessionDto : bilanSessionDtos) {
												if (dz.gov.mesrs.sii.fve.web.jsf.bean.utils.Utility
														.isBilanAnnuel(bilanSessionDto
																.getType())) {
													dia.setLastMoyenne(bilanSessionDto
															.getMoyenne());
													sortir = true;
													break;
												}

											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}

	/**
	 * [AffectationMasseBean.fillMoyenneLastYearAffectation] Method
	 * 
	 * @author MAKERRI Sofiane on : 28 janv. 2015 12:01:46
	 */
	private void fillMoyenneLastYearAffectation() {
		showAffectation = false;
		if (groupePedagogiqueDtos != null && dossierInscriptionDtoList != null) {
			for (GroupePedagogiqueDto gp : groupePedagogiqueDtos) {
				List<AffectationGroupePedagogiqueDto> affectations = gp
						.getAffectationGroupePedagogiqueDtos();
				if (affectations != null) {
					for (AffectationGroupePedagogiqueDto affectation : affectations) {
						if (periodeDto.getRangNiveau() == 1) {
							// première année : last moyenne = moyenne du bac
							affectation.setLastMoyenne(affectation
									.getMoyenneBac());
							for (DossierInscriptionAdministrativeDto dia : dossierInscriptionDtoList) {
								if (affectation.getDossierInscriptionId() != null
										&& dia.getId() == affectation
												.getDossierInscriptionId()) {
									dia.setGroupePedagogiqueIntitule(gp
											.getNomAffichage());
									showAffectation = true;
									break;
								}
							}
						} else {
							for (DossierInscriptionAdministrativeDto dia : dossierInscriptionDtoList) {
								if (affectation.getDossierInscriptionId() != null
										&& dia.getId() == affectation
												.getDossierInscriptionId()) {
									dia.setGroupePedagogiqueIntitule(gp
											.getNomAffichage());
									affectation.setLastMoyenne(dia
											.getLastMoyenne());
									break;
								}
							}
						}
					}
				}
			}
		}
	}

	/**
	 * [AffectationMasseBean.affectation] Method
	 * 
	 * @author MAKERRI Sofiane on : 25 janv. 2015 18:06:04
	 */
	public void affectation() {

		try {
			if (selectedGroupePedagogiqueDtos == null
					|| selectedGroupePedagogiqueDtos.isEmpty()) {
				CommonMessagesUtils
						.showErrorMessage(bundleGroupe
								.getString("groupepedagogique_affectation_masse_erreur_list_groupe_vide"));
				return;
			}
			if (palier <= 0) {
				CommonMessagesUtils
						.showErrorMessage(bundleGroupe
								.getString("groupepedagogique_affectation_masse_erreur_palier"));
				return;
			}
			calculMaxPalier();
			if (palier > maxPalier) {
				CommonMessagesUtils
						.showErrorMessage(bundleGroupe
								.getString("groupepedagogique_affectation_masse_erreur_palier_max_depasse")
								+ "[" + maxPalier + "]");
				return;
			}

			if (selectedGroupePedagogiqueDtos != null
					&& dossierInscriptionDtoList != null && palier != 0) {
				// findSelectedGroupePedagogique();
				if (!checkCapaicite()) {
					return;
				}
				deleteAffectation();
				int i = 0;
				GroupePedagogiqueDto groupePedagogiqueDto = null;
				int etudiantReste = dossierInscriptionDtoList.size()
						% (selectedGroupePedagogiqueDtos.size() * palier);
				int iterationEgale = dossierInscriptionDtoList.size()
						- etudiantReste;
				// affectation égale : si nombre d'etudiant = 26, nbr de section
				// est 3, palier = 3 alors iterationEgale = 26-(26/9) = 24
				// dans ce la on respecte le palier: on dispatch palier par
				// palier.
				for (int index = 0; index < iterationEgale; index++) {
					DossierInscriptionAdministrativeDto dia = dossierInscriptionDtoList
							.get(index);
					if (i == palier || i == 0) {
						groupePedagogiqueDto = findNextGroupePedagogique();
						i = 0;
					}
					if (groupePedagogiqueDto == null) {
						continue;
					}
					AffectationGroupePedagogiqueDto agp = fillAffectationGroupePedagogique(
							groupePedagogiqueDto, dia);
					affectationGroupePedagogiqueService.insertOrUpdate(agp);
					saveAffecationIndividu(groupePedagogiqueDto.getIdGroupe(),
							dia.getIndividuId());
					List<AffectationGroupePedagogiqueDto> affectationGroupePedagogiqueDtos = groupePedagogiqueDto
							.getAffectationGroupePedagogiqueDtos();
					if (affectationGroupePedagogiqueDtos == null) {
						affectationGroupePedagogiqueDtos = new ArrayList<AffectationGroupePedagogiqueDto>();
					}
					affectationGroupePedagogiqueDtos.add(agp);
					i++;
				}

				// affectation reste : reste egale à 2
				// dans ce la on ne doit pas respecte le palier: on dispatch 1
				// par 1
				if (etudiantReste != 0) {
					index = 0;
					for (int index = iterationEgale; index < dossierInscriptionDtoList
							.size(); index++) {
						DossierInscriptionAdministrativeDto dia = dossierInscriptionDtoList
								.get(index);
						groupePedagogiqueDto = findNextGroupePedagogique();
						if (groupePedagogiqueDto == null) {
							continue;
						}
						AffectationGroupePedagogiqueDto agp = fillAffectationGroupePedagogique(
								groupePedagogiqueDto, dia);
						affectationGroupePedagogiqueService.insertOrUpdate(agp);
						saveAffecationIndividu(
								groupePedagogiqueDto.getIdGroupe(),
								dia.getIndividuId());
						List<AffectationGroupePedagogiqueDto> affectationGroupePedagogiqueDtos = groupePedagogiqueDto
								.getAffectationGroupePedagogiqueDtos();
						if (affectationGroupePedagogiqueDtos == null) {
							affectationGroupePedagogiqueDtos = new ArrayList<AffectationGroupePedagogiqueDto>();
						}
						affectationGroupePedagogiqueDtos.add(agp);
					}
				}
				fillMoyenneLastYearAffectation();
				CommonMessagesUtils.showSuccessSaveMessage();
			}
		} catch (Exception e) {
			CommonMessagesUtils.showErrorMessage(e.getMessage());
		}

	}

	/**
	 * [AffectationMasseBean.fillAffectationGroupePedagogique] Method
	 * 
	 * @author MAKERRI Sofiane on : 28 janv. 2015 08:52:29
	 * @param gp
	 * @param dia
	 * @return
	 */
	private AffectationGroupePedagogiqueDto fillAffectationGroupePedagogique(
			GroupePedagogiqueDto gp, DossierInscriptionAdministrativeDto dia) {
		AffectationGroupePedagogiqueDto agp = new AffectationGroupePedagogiqueDto();
		agp.setDateAffectation(new Date());
		agp.setNumeroMatricule(dia.getNumeroMatricule());
		agp.setMoyenneBac(dia.getMoyenneBac());
		agp.setLastMoyenne(dia.getLastMoyenne());
		agp.setDossierInscriptionId(dia.getId());
		agp.setGroupePedagogiqueId(gp.getId());
		agp.setDateNaissanceEtudiant(dia.getIndividuDateNaissance());
		agp.setNomEtudiant(dia.getIndividuNomLatin());
		agp.setPrenomEtudiant(dia.getIndividuPrenomLatin());
		agp.setEtudiantCivilite(dia.getEtudiantCivilite());
		return agp;
	}

	/**
	 * [AffectationMasseBean.critereValue] Method
	 * 
	 * @author MAKERRI Sofiane on : 1 févr. 2015 16:33:03
	 * @param line
	 * @return
	 */
	public Boolean critereValue(HashMap<String, Boolean> line) {
		if (line != null) {
			List<Boolean> list = new ArrayList<Boolean>(line.values());
			if (list != null) {
				return list.get(0);
			}
		}
		return false;

	}

	/**
	 * [AffectationMasseBean.isCritereValue] Method
	 * 
	 * @author MAKERRI Sofiane on : 1 févr. 2015 16:38:08
	 * @return
	 */
	public Boolean getCritereValue(HashMap<String, Boolean> line) {
		if (line != null) {
			List<Boolean> list = new ArrayList<Boolean>(line.values());
			if (list != null) {
				return list.get(0);
			}
		}
		return false;
	}

	/**
	 * [AffectationMasseBean.critereKey] Method
	 * 
	 * @author MAKERRI Sofiane on : 1 févr. 2015 16:34:30
	 * @param line
	 * @return
	 */
	public String critereKey(HashMap<String, Boolean> line) {
		if (line != null) {
			List<String> list = new ArrayList<String>(line.keySet());
			if (list != null) {
				return list.get(0);
			}
		}
		return null;
	}

	/**
	 * [AffectationMasseBean.checkCritere] Method
	 * 
	 * @author MAKERRI Sofiane on : 1 févr. 2015 15:49:02
	 * @param critere
	 * @return
	 */
	public boolean checkCritere(String critere) {
		return false;
	}

	/**
	 * [AffectationMasseBean.checkCapaicite] Method
	 * 
	 * @author MAKERRI Sofiane on : 27 janv. 2015 11:14:07
	 * @return
	 */
	private boolean checkCapaicite() {
		if (selectedGroupePedagogiqueDtos != null
				&& dossierInscriptionDtoList != null
				&& !dossierInscriptionDtoList.isEmpty() && palier > 0) {
			int i = 0;
			int nbrEtudiant = dossierInscriptionDtoList.size() / palier;
			int resteDivision = dossierInscriptionDtoList.size() % palier;
			for (GroupePedagogiqueDto _gp : selectedGroupePedagogiqueDtos) {
				int _gpNbrEtudiant = nbrEtudiant;
				if (i == 0) {
					// le premier groupe peut avoir plus de que
					// dossierInscriptionDtoList.size()/palier (il prend le
					// reste de la division)
					_gpNbrEtudiant += resteDivision;
				}
				i++;
				if (_gp.getCapaciteMax() != null
						&& _gpNbrEtudiant > _gp.getCapaciteMax()) {
					CommonMessagesUtils
							.showErrorMessage(bundleGroupe
									.getString("groupepedagogique_affectation_masse_erreur_capacite_max_depasse")
									+ "["
									+ _gp.getNomAffichage()
									+ "("
									+ _gp.getCapaciteMax() + ")" + "]");
					return false;
				}
				if (_gp.getCapaciteMin() != null
						&& _gpNbrEtudiant < _gp.getCapaciteMin()) {
					CommonMessagesUtils
							.showWarningMessage(bundleGroupe
									.getString("groupepedagogique_affectation_masse_erreur_capacite_min_non_atteinte")
									+ "["
									+ _gp.getNomAffichage()
									+ "("
									+ _gp.getCapaciteMin() + ")" + "]");
				}
			}
		}
		return true;
	}

	/**
	 * [AffectationMasseBean.deleteAffectation] Method
	 * 
	 * @author MAKERRI Sofiane on : 27 janv. 2015 08:11:43
	 */
	private void deleteAffectation() {
		if (selectedGroupePedagogiqueDtos != null) {
			for (GroupePedagogiqueDto gp : selectedGroupePedagogiqueDtos) {
				List<AffectationGroupePedagogiqueDto> affectations = gp
						.getAffectationGroupePedagogiqueDtos();
				if (affectations != null) {
					for (AffectationGroupePedagogiqueDto _affectation : affectations) {
						affectationGroupePedagogiqueService
								.remove(_affectation);
					}
					// supprimer l'affectation dans le referentiel
					removeAffectationIndividu(gp);
					gp.setAffectationGroupePedagogiqueDtos(new ArrayList<AffectationGroupePedagogiqueDto>());
				}
			}
		}
	}

	/**
	 * [AffectationMasseBean.removeAffectationIndividu] Method
	 * 
	 * @author MAKERRI Sofiane on : 27 janv. 2015 08:20:52
	 * @param gp
	 */
	private void removeAffectationIndividu(GroupePedagogiqueDto gp) {
		if (gp.getIdGroupe() != null) {
			List<RefAffectationDto> individuGroupeList = refAffectationService
					.findIndividus("groupe", gp.getIdGroupe());
			if (individuGroupeList != null) {
				for (RefAffectationDto refAffectationDto : individuGroupeList) {
					refAffectationService.delete(refAffectationDto);
				}
			}
		}
	}

	/**
	 * [AffectationMasseBean.saveAffecationIndividu] Method
	 * 
	 * @author MAKERRI Sofiane on : 27 janv. 2015 09:22:36
	 * @param refGroupeId
	 * @param individuId
	 * @return
	 */
	public String saveAffecationIndividu(Integer refGroupeId, Integer individuId) {
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

	public void tri() {
	
		if (criteres != null) {
			target = criteres.getTarget();
			if (target != null) {
				ComparatorChain chain = new ComparatorChain();
				for (OrderCritere oc : target) {
					chain.addComparator(oc.getComparator());
				}
			Collections.sort(dossierInscriptionDtoList, chain);
			}
		}
		System.out.println(dossierInscriptionDtoList);
	}

	/**
	 * [AffectationMasseBean.findNextGroupePedagogique] Method
	 * 
	 * @author MAKERRI Sofiane on : 26 janv. 2015 10:30:45
	 * @return
	 */
	private GroupePedagogiqueDto findNextGroupePedagogique() {
		if (selectedGroupePedagogiqueDtos != null) {
			if (index == selectedGroupePedagogiqueDtos.size()) {
				index = 0; // revenir au premier element : c'est circulaire
			}
			GroupePedagogiqueDto gp = selectedGroupePedagogiqueDtos.get(index);
			index++;
			return gp;
		}
		return null;
	}

	/**
	 * [AffectationMasseBean.dossierInscriptionDtoList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 janv. 2015 07:59:41
	 * @return the dossierInscriptionDtoList
	 */
	public List<DossierInscriptionAdministrativeDto> getDossierInscriptionDtoList() {
		return dossierInscriptionDtoList;
	}

	/**
	 * [AffectationMasseBean.dossierInscriptionDtoList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 janv. 2015 07:59:41
	 * @param dossierInscriptionDtoList
	 *            the dossierInscriptionDtoList to set
	 */
	public void setDossierInscriptionDtoList(
			List<DossierInscriptionAdministrativeDto> dossierInscriptionDtoList) {
		this.dossierInscriptionDtoList = dossierInscriptionDtoList;
	}

	/**
	 * [AffectationMasseBean.dossierInscriptionAdministrativeService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 janv. 2015 09:21:42
	 * @return the dossierInscriptionAdministrativeService
	 */
	public DossierInscriptionAdministrativeService getDossierInscriptionAdministrativeService() {
		return dossierInscriptionAdministrativeService;
	}

	/**
	 * [AffectationMasseBean.dossierInscriptionAdministrativeService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 janv. 2015 09:21:42
	 * @param dossierInscriptionAdministrativeService
	 *            the dossierInscriptionAdministrativeService to set
	 */
	public void setDossierInscriptionAdministrativeService(
			DossierInscriptionAdministrativeService dossierInscriptionAdministrativeService) {
		this.dossierInscriptionAdministrativeService = dossierInscriptionAdministrativeService;
	}

	/**
	 * [AffectationMasseBean.sessionBean] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 janv. 2015 09:27:49
	 * @return the sessionBean
	 */
	public SessionBean getSessionBean() {
		return sessionBean;
	}

	/**
	 * [AffectationMasseBean.sessionBean] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 janv. 2015 09:27:49
	 * @param sessionBean
	 *            the sessionBean to set
	 */
	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}

	/**
	 * [AffectationMasseBean.utilBean] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 janv. 2015 09:29:47
	 * @return the utilBean
	 */
	public UtilBean getUtilBean() {
		return utilBean;
	}

	/**
	 * [AffectationMasseBean.utilBean] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 janv. 2015 09:29:47
	 * @param utilBean
	 *            the utilBean to set
	 */
	public void setUtilBean(UtilBean utilBean) {
		this.utilBean = utilBean;
	}

	/**
	 * [AffectationMasseBean.showDetail] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 janv. 2015 09:43:06
	 * @return the showDetail
	 */
	public boolean isShowDetail() {
		return showDetail;
	}

	/**
	 * [AffectationMasseBean.showDetail] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 janv. 2015 09:43:06
	 * @param showDetail
	 *            the showDetail to set
	 */
	public void setShowDetail(boolean showDetail) {
		this.showDetail = showDetail;
	}

	/**
	 * [AffectationMasseBean.searchBean] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 janv. 2015 09:46:28
	 * @return the searchBean
	 */
	public SearchBean getSearchBean() {
		return searchBean;
	}

	/**
	 * [AffectationMasseBean.searchBean] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 janv. 2015 09:46:28
	 * @param searchBean
	 *            the searchBean to set
	 */
	public void setSearchBean(SearchBean searchBean) {
		this.searchBean = searchBean;
	}

	/**
	 * [AffectationMasseBean.selectedGroupe] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 janv. 2015 10:21:42
	 * @return the selectedGroupe
	 */
	// public List<String> getSelectedGroupe() {
	// return selectedGroupe;
	// }

	/**
	 * [AffectationMasseBean.selectedGroupe] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 janv. 2015 10:21:42
	 * @param selectedGroupe
	 *            the selectedGroupe to set
	 */
	// public void setSelectedGroupe(List<String> selectedGroupe) {
	// this.selectedGroupe = selectedGroupe;
	// }

	/**
	 * [AffectationMasseBean.groupeList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 janv. 2015 10:21:42
	 * @return the groupeList
	 */
	public List<String> getGroupeList() {
		return groupeList;
	}

	/**
	 * [AffectationMasseBean.groupeList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 janv. 2015 10:21:42
	 * @param groupeList
	 *            the groupeList to set
	 */
	public void setGroupeList(List<String> groupeList) {
		this.groupeList = groupeList;
	}

	/**
	 * [AffectationMasseBean.checkAll] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 janv. 2015 16:30:58
	 * @return the checkAll
	 */
	public boolean isCheckAll() {
		return checkAll;
	}

	/**
	 * [AffectationMasseBean.checkAll] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 janv. 2015 16:30:58
	 * @param checkAll
	 *            the checkAll to set
	 */
	public void setCheckAll(boolean checkAll) {
		this.checkAll = checkAll;
	}

	/**
	 * [AffectationMasseBean.showCheckAll] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 janv. 2015 17:51:57
	 * @return the showCheckAll
	 */
	public boolean isShowCheckAll() {
		return showCheckAll;
	}

	/**
	 * [AffectationMasseBean.showCheckAll] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 janv. 2015 17:51:57
	 * @param showCheckAll
	 *            the showCheckAll to set
	 */
	public void setShowCheckAll(boolean showCheckAll) {
		this.showCheckAll = showCheckAll;
	}

	/**
	 * [AffectationMasseBean.palier] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 janv. 2015 17:59:41
	 * @return the pallier
	 */
	public Integer getPalier() {
		return palier;
	}

	/**
	 * [AffectationMasseBean.palier] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 janv. 2015 17:59:41
	 * @param palier
	 *            the palier to set
	 */
	public void setPalier(Integer palier) {
		this.palier = palier;
	}

	/**
	 * [AffectationMasseBean.groupePedagogiqueDtos] Getter
	 * 
	 * @author MAKERRI Sofiane on : 26 janv. 2015 09:31:45
	 * @return the groupePedagogiqueDtos
	 */
	public List<GroupePedagogiqueDto> getGroupePedagogiqueDtos() {
		return groupePedagogiqueDtos;
	}

	/**
	 * [AffectationMasseBean.groupePedagogiqueDtos] Setter
	 * 
	 * @author MAKERRI Sofiane on : 26 janv. 2015 09:31:45
	 * @param groupePedagogiqueDtos
	 *            the groupePedagogiqueDtos to set
	 */
	public void setGroupePedagogiqueDtos(
			List<GroupePedagogiqueDto> groupePedagogiqueDtos) {
		this.groupePedagogiqueDtos = groupePedagogiqueDtos;
	}

	/**
	 * [AffectationMasseBean.affectationGroupePedagogiqueService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 26 janv. 2015 10:04:05
	 * @return the affectationGroupePedagogiqueService
	 */
	public AffectationGroupePedagogiqueService getAffectationGroupePedagogiqueService() {
		return affectationGroupePedagogiqueService;
	}

	/**
	 * [AffectationMasseBean.affectationGroupePedagogiqueService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 26 janv. 2015 10:04:05
	 * @param affectationGroupePedagogiqueService
	 *            the affectationGroupePedagogiqueService to set
	 */
	public void setAffectationGroupePedagogiqueService(
			AffectationGroupePedagogiqueService affectationGroupePedagogiqueService) {
		this.affectationGroupePedagogiqueService = affectationGroupePedagogiqueService;
	}

	/**
	 * [AffectationMasseBean.index] Getter
	 * 
	 * @author MAKERRI Sofiane on : 26 janv. 2015 10:29:40
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * [AffectationMasseBean.index] Setter
	 * 
	 * @author MAKERRI Sofiane on : 26 janv. 2015 10:29:40
	 * @param index
	 *            the index to set
	 */
	public void setIndex(int index) {
		this.index = index;
	}

	/**
	 * [AffectationMasseBean.selectedGroupePedagogiqueDtos] Getter
	 * 
	 * @author MAKERRI Sofiane on : 26 janv. 2015 10:32:43
	 * @return the selectedGroupePedagogiqueDtos
	 */
	public List<GroupePedagogiqueDto> getSelectedGroupePedagogiqueDtos() {
		return selectedGroupePedagogiqueDtos;
	}

	/**
	 * [AffectationMasseBean.selectedGroupePedagogiqueDtos] Setter
	 * 
	 * @author MAKERRI Sofiane on : 26 janv. 2015 10:32:43
	 * @param selectedGroupePedagogiqueDtos
	 *            the selectedGroupePedagogiqueDtos to set
	 */
	public void setSelectedGroupePedagogiqueDtos(
			List<GroupePedagogiqueDto> selectedGroupePedagogiqueDtos) {
		this.selectedGroupePedagogiqueDtos = selectedGroupePedagogiqueDtos;
	}

	/**
	 * [AffectationMasseBean.refAffectationService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 27 janv. 2015 08:16:20
	 * @return the refAffectationService
	 */
	public RefAffectationService getRefAffectationService() {
		return refAffectationService;
	}

	/**
	 * [AffectationMasseBean.refAffectationService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 27 janv. 2015 08:16:20
	 * @param refAffectationService
	 *            the refAffectationService to set
	 */
	public void setRefAffectationService(
			RefAffectationService refAffectationService) {
		this.refAffectationService = refAffectationService;
	}

	/**
	 * [AffectationMasseBean.roleEtudiant] Getter
	 * 
	 * @author MAKERRI Sofiane on : 27 janv. 2015 08:26:07
	 * @return the roleEtudiant
	 */
	public NomenclatureDto getRoleEtudiant() {
		return roleEtudiant;
	}

	/**
	 * [AffectationMasseBean.roleEtudiant] Setter
	 * 
	 * @author MAKERRI Sofiane on : 27 janv. 2015 08:26:07
	 * @param roleEtudiant
	 *            the roleEtudiant to set
	 */
	public void setRoleEtudiant(NomenclatureDto roleEtudiant) {
		this.roleEtudiant = roleEtudiant;
	}

	/**
	 * [AffectationMasseBean.maxPalier] Getter
	 * 
	 * @author MAKERRI Sofiane on : 28 janv. 2015 10:49:55
	 * @return the maxPalier
	 */
	public Integer getMaxPalier() {
		return maxPalier;
	}

	/**
	 * [AffectationMasseBean.maxPalier] Setter
	 * 
	 * @author MAKERRI Sofiane on : 28 janv. 2015 10:49:55
	 * @param maxPalier
	 *            the maxPalier to set
	 */
	public void setMaxPalier(Integer maxPalier) {
		this.maxPalier = maxPalier;
	}

	/**
	 * [AffectationMasseBean.periodeDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 28 janv. 2015 11:36:33
	 * @return the periodeDto
	 */
	public PeriodeDto getPeriodeDto() {
		return periodeDto;
	}

	/**
	 * [AffectationMasseBean.periodeDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 28 janv. 2015 11:36:33
	 * @param periodeDto
	 *            the periodeDto to set
	 */
	public void setPeriodeDto(PeriodeDto periodeDto) {
		this.periodeDto = periodeDto;
	}

	/**
	 * [AffectationMasseBean.showAffectation] Getter
	 * 
	 * @author MAKERRI Sofiane on : 1 févr. 2015 13:34:43
	 * @return the showAffectation
	 */
	public boolean isShowAffectation() {
		return showAffectation;
	}

	/**
	 * [AffectationMasseBean.showAffectation] Setter
	 * 
	 * @author MAKERRI Sofiane on : 1 févr. 2015 13:34:43
	 * @param showAffectation
	 *            the showAffectation to set
	 */
	public void setShowAffectation(boolean showAffectation) {
		this.showAffectation = showAffectation;
	}

	/**
	 * [AffectationMasseBean.criteres] Getter
	 * 
	 * @author MAKERRI Sofiane on : 2 févr. 2015 12:07:14
	 * @return the criteres
	 */
	public DualListModel<OrderCritere> getCriteres() {
		return criteres;
	}

	/**
	 * [AffectationMasseBean.criteres] Setter
	 * 
	 * @author MAKERRI Sofiane on : 2 févr. 2015 12:07:14
	 * @param criteres
	 *            the criteres to set
	 */
	public void setCriteres(DualListModel<OrderCritere> criteres) {
		this.criteres = criteres;
	}

	/**
	 * [AffectationMasseBean.source] Getter
	 * 
	 * @author MAKERRI Sofiane on : 2 févr. 2015 12:07:14
	 * @return the source
	 */
	public List<OrderCritere> getSource() {
		return source;
	}

	/**
	 * [AffectationMasseBean.source] Setter
	 * 
	 * @author MAKERRI Sofiane on : 2 févr. 2015 12:07:14
	 * @param source
	 *            the source to set
	 */
	public void setSource(List<OrderCritere> source) {
		this.source = source;
	}

	/**
	 * [AffectationMasseBean.target] Getter
	 * 
	 * @author MAKERRI Sofiane on : 2 févr. 2015 12:07:14
	 * @return the target
	 */
	public List<OrderCritere> getTarget() {
		return target;
	}

	/**
	 * [AffectationMasseBean.target] Setter
	 * 
	 * @author MAKERRI Sofiane on : 2 févr. 2015 12:07:14
	 * @param target
	 *            the target to set
	 */
	public void setTarget(List<OrderCritere> target) {
		this.target = target;
	}

}
