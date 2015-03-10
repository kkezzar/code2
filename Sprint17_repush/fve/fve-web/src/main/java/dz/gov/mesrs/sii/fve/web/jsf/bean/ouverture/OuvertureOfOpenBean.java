package dz.gov.mesrs.sii.fve.web.jsf.bean.ouverture;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.event.SelectEvent;

import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.AnneeAcademiqueDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OffreFormationDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OuvertureOffreFormationDto;
import dz.gov.mesrs.sii.fve.business.service.offreformation.AnneeAcademiqueService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OffreFormationService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OuvertureOffreFormationService;
import dz.gov.mesrs.sii.fve.web.jsf.bean.SessionBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.OfConstants;
import dz.gov.mesrs.sii.fve.web.jsf.bean.utils.Utility;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefAffectDomLmdEtabDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefFiliereLmdDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefDomaineLMDService;
import dz.gov.mesrs.sii.referentiel.business.service.RefFiliereLmdService;
import dz.gov.mesrs.sii.referentiel.business.service.RefSpecialiteLmdService;

/**
 * @author BELDI Jamel on : 11 mai 2014 18:38:18
 */
@ManagedBean(name = "ouvertureOfOpenBean")
@ViewScoped
public class OuvertureOfOpenBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(OuvertureOfOpenBean.class);
	private ResourceBundle bundleOuverture;
	private int exception;
	private List<OffreFormationDto> ofResultSearch;
	private OuvertureOffreFormationDto ouvertureOffreFormationDto;
	private AnneeAcademiqueDto anneeAcademiqueDto;
	@ManagedProperty(value = "#{offreFormationService}")
	private OffreFormationService offreFormationService;
	@ManagedProperty(value = "#{anneeAcademiqueService}")
	private AnneeAcademiqueService anneeAcademiqueService;
	@ManagedProperty(value = "#{ouvertureOffreFormationService}")
	private OuvertureOffreFormationService ouvertureOffreFormationService;
	@ManagedProperty("#{flash}")
	private Flash flash;
	@ManagedProperty("#{sessionBeanFve}")
	private SessionBean sessionBean;

	@ManagedProperty("#{refSpecialiteLmdServiceImpl}")
	private RefSpecialiteLmdService refSpecialiteLmdService;

	@ManagedProperty("#{refDomaineLMDServiceImpl}")
	private RefDomaineLMDService refDomaineLMDService;

	@ManagedProperty("#{refFiliereLmdServiceImpl}")
	private RefFiliereLmdService refFiliereLmdService;

	private OffreFormationDto offreFormationSearchDto;
	private OffreFormationDto selectedOffreFormationDto;
	private List<SelectItem> listDomaines;
	private List<SelectItem> listFilieres;
	private List<SelectItem> listSpecialites;
	private boolean showDetail;

	/**
	 * [OuvertureOfBean.OuvertureOfBean()] Constructor
	 * 
	 * @author BELDI Jamel on : 11 mai 2014 18:37:58
	 */
	public OuvertureOfOpenBean() {
		super();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		bundleOuverture = facesContext.getApplication().getResourceBundle(
				facesContext, OfConstants.OUVERTURE_OF_MESSAGES_FILE_NAME);
		ouvertureOffreFormationDto = new OuvertureOffreFormationDto();
	}

	/**
	 * [OuvertureOfBean.init] Method
	 * 
	 * @author BELDI Jamel on : 11 mai 2014 18:37:54
	 */
	@PostConstruct
	public void init() {
		exception = 0;
		offreFormationSearchDto = new OffreFormationDto();
		selectedOffreFormationDto = null;
		loadAnneeAcademique();
		// loadOuvertureOf();
		loadDomaines();
		// findOffres();
		showDetail = false;;

	}

	private void loadDomaines() {
		try {
			// preparer la liste des domaines
			listDomaines = new ArrayList<>();
			List<RefAffectDomLmdEtabDto> result = new ArrayList<RefAffectDomLmdEtabDto>();

			result = refDomaineLMDService
					.findDomainesLMDByEtablissement(sessionBean
							.getIdEtablissement());
			// List<RefDomaineLMDDto> listDomainesDto = ppmWSClient
			// .findAdvancedDomaineLMD(new RefDomaineLMDDto());
			for (/* RefDomaineLMDDto */RefAffectDomLmdEtabDto ld : result/* listDomainesDto */) {

				SelectItem itemFr = new SelectItem(
						ld.getIdDomaineLMD(), ld.getLlDomaine() + " ("
								+ ld.getIdentifiantDomaineLMD() + ")"/*
																	 * ld.
																	 * getIdentifiant
																	 * (), ld.
																	 * getLlDomaine
																	 * () + " ("
																	 * + ld.
																	 * getIdentifiant
																	 * () + ")"
																	 */);
				listDomaines.add(itemFr);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void loadAnneeAcademique() {

		try {
			log.info("loadOuvertureOf");
			int currentYear = Calendar.getInstance().get(Calendar.YEAR);
			anneeAcademiqueDto = anneeAcademiqueService
					.findByFirstAndSecondYear(
							Short.valueOf(Integer.toString(currentYear)),
							Short.valueOf(Integer.toString(currentYear + 1)));
			if (anneeAcademiqueDto == null) {
				// save nouvelle annee academique
				anneeAcademiqueDto = new AnneeAcademiqueDto();
				anneeAcademiqueDto.setPremiereAnnee(Short.valueOf(Integer
						.toString(currentYear)));
				anneeAcademiqueDto.setDeuxiemeAnnee(Short.valueOf(Integer
						.toString(currentYear + 1)));
				anneeAcademiqueDto = anneeAcademiqueService
						.insertOrUpdate(anneeAcademiqueDto);
			}
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	/**
	 * 
	 * [OuvertureOfOpenBean.handleDomainesListChange] Method
	 * 
	 * @author BELDI Jamel on : 10 sept. 2014 11:24:23
	 * @param event
	 * @throws Exception_Exception
	 */
	public void handleDomainesListChange(AjaxBehaviorEvent event)
			throws Exception {
		// TODO liste des filieres du domaine
		// de l'etablissement en cours
		if (this.listDomaines != null) {
			listFilieres = new ArrayList<SelectItem>();

			List<RefFiliereLmdDto> listFilieresDto = refFiliereLmdService.findByIdDomainelmd(offreFormationSearchDto.getIdDomaine());

			for (RefFiliereLmdDto ld : listFilieresDto) {
				SelectItem itemFr = new SelectItem(ld.getIdentifiant(),
						ld.getLlFiliere() + " (" + ld.getIdentifiant() + ")");
				listFilieres.add(itemFr);

			}
		}
	}

	/**
	 * 
	 * [OuvertureOfOpenBean.onSelectOf] Method
	 * 
	 * @author BELDI Jamel on : 10 sept. 2014 15:33:10
	 * @param event
	 */
	public void onRowSelect(SelectEvent event) {

		selectedOffreFormationDto = (OffreFormationDto) event.getObject();
		try {
			log.info("loadOuvertureOf");

			selectedOf();

		} catch (Exception e) {
			e.printStackTrace();
			Utility.showErrorMessage(e.getMessage());

		}
	}

	/**
	 * [OuvertureOfOpenBean.selectedOf] Method
	 * 
	 * @author MAKERRI Sofiane on : 30 nov. 2014 15:17:28
	 */
	private void selectedOf() {
		showDetail = true;
		ouvertureOffreFormationDto = new OuvertureOffreFormationDto();
		ouvertureOffreFormationDto.setAnneeAcademiqueId(anneeAcademiqueDto
				.getId());
		ouvertureOffreFormationDto
				.setAnneeAcademiquePremiereAnnee(anneeAcademiqueDto
						.getPremiereAnnee());
		ouvertureOffreFormationDto
				.setAnneeAcademiqueDeuxiemeAnnee(anneeAcademiqueDto
						.getDeuxiemeAnnee());
		ouvertureOffreFormationDto
				.setOffreFormationId(selectedOffreFormationDto.getId());
		ouvertureOffreFormationDto.setIdEtablissement(sessionBean
				.getIdEtablissement());
		ouvertureOffreFormationDto.setRefLibelleEtablissement(sessionBean
				.getLlLatinEtablissement());
		List<OuvertureOffreFormationDto> listOuverturesOf = ouvertureOffreFormationService
				.findAdvanced(ouvertureOffreFormationDto);
		if (listOuverturesOf != null && listOuverturesOf.size() == 1) {
			ouvertureOffreFormationDto = listOuverturesOf.get(0);
		}
	}

	/**
	 * [OuvertureOfBean.getOffreFormationService] Method
	 * 
	 * @author BELDI Jamel on : 11 mai 2014 18:37:45
	 * @return
	 */
	public OffreFormationService getOffreFormationService() {
		return offreFormationService;
	}

	/**
	 * [OuvertureOfBean.setOffreFormationService] Method
	 * 
	 * @author BELDI Jamel on : 11 mai 2014 18:37:41
	 * @param offreFormationService
	 */
	public void setOffreFormationService(
			OffreFormationService offreFormationService) {
		this.offreFormationService = offreFormationService;
	}

	/**
	 * [OuvertureOfBean.getOfResultSearch] Method
	 * 
	 * @author BELDI Jamel on : 11 mai 2014 18:37:38
	 * @return
	 */
	public List<OffreFormationDto> getOfResultSearch() {
		return ofResultSearch;
	}

	/**
	 * [OuvertureOfBean.setOfResultSearch] Method
	 * 
	 * @author BELDI Jamel on : 11 mai 2014 18:37:34
	 * @param ofResultSearch
	 */
	public void setOfResultSearch(List<OffreFormationDto> ofResultSearch) {
		this.ofResultSearch = ofResultSearch;
	}

	public void findOffres() {
		try {
			log.info("findOffres");
			ofResultSearch = new ArrayList<OffreFormationDto>();
			// List<OffreFormationDto> offresHabilites= offreFormationService
			// .findByCodeSituation(UtilConstants.OFFRE_FORMATION_SITUTAION_HABILITEE_CODE);
			List<OffreFormationDto> offresHabilites = offreFormationService
					.findAdvanced(offreFormationSearchDto, null,
							sessionBean.getIdEtablissement());

			// recuperer list offre habilites et non ouvert pour l'annee en
			// cours

			if (anneeAcademiqueDto != null) {

				for (OffreFormationDto off : offresHabilites) {

					OuvertureOffreFormationDto searchDto = new OuvertureOffreFormationDto();
					searchDto.setAnneeAcademiqueId(anneeAcademiqueDto.getId());
					searchDto.setIdEtablissement(sessionBean
							.getIdEtablissement());
					searchDto.setOffreFormationId(off.getId());
					searchDto.setEstOuverte(true);
					List<OuvertureOffreFormationDto> offresOuverts = ouvertureOffreFormationService
							.findAdvanced(searchDto);
					if (offresOuverts == null || offresOuverts.isEmpty()) {
						// if (off.getRefCodeDomaine() != null) {
						// RefDomaineLMDDto _domaine = refDomaineLMDService
						// .findByIdentifiant(off.getRefCodeDomaine());
						// if (_domaine != null) {
						// off.setLibelleDomaine(_domaine.getLlDomaine());
						//
						// }
						// }

						// if (off.getRefCodeFiliere() != null) {
						// RefFiliereLmdDto _filiere = refFiliereLmdService
						// .findByIdentifiant(off.getRefCodeFiliere());
						// if (_filiere != null) {
						// off.setLibelleFiliere(_filiere.getLlFiliere());
						//
						// }
						// }

						// RefSpecialiteLmdDto _specialite = ppmWSClient
						// .findSpecialiteByIdentifiant(off
						// .getRefCodeSpecialite());
						// if (_specialite != null) {
						// off.setLibelleSpecialite(_specialite.getLlSpecialite());
						//
						// }

						// NomenclatureDto _cycle =
						// nomenclatureService.findByCode(
						// Const.LMD_CYCLE,
						// off.getRefCodeCycle());
						// if (_cycle != null) {
						// off.setLibelleCycle(_cycle.getLibelleLongFr());
						// }

						ofResultSearch.add(off);
					}

				}

			}
			selectedOffreFormationDto = new OffreFormationDto();
			showDetail = false;
			if (ofResultSearch.size() == 1) {
				selectedOffreFormationDto = ofResultSearch.get(0);
				selectedOf();
			}

		} catch (Exception e) {
			e.printStackTrace();
			Utility.showErrorMessage(e.getMessage());

		}

	}

	/**
	 * [OuvertureOfOpenBean.save] Method
	 * 
	 * @author MAKERRI Sofiane on : 30 nov. 2014 09:49:42
	 */
	public void save() {
		try {

			if (ouvertureOffreFormationDto.getEffectifMax() != null
					&& ouvertureOffreFormationDto.getEffectifMin() != null
					&& ouvertureOffreFormationDto.getEffectifMax().intValue() < ouvertureOffreFormationDto
							.getEffectifMin().intValue()) {
				Utility.showErrorMessage(bundleOuverture
						.getString("ouverture_effectif_maximum_error"));
				return;
			}

		//	if (ouvertureOffreFormationDto.getDateOuverture() == null) {
				ouvertureOffreFormationDto.setDateOuverture(new Date());
				ouvertureOffreFormationDto.setDateFermeture(null);
				ouvertureOffreFormationDto.setEstOuverte(true);
			//}
//			if (!ouvertureOffreFormationDto.isEstOuverte()
//					&& ouvertureOffreFormationDto.getDateFermeture() == null) {
//				ouvertureOffreFormationDto.setDateFermeture(new Date());
//			}

			ouvertureOffreFormationDto = ouvertureOffreFormationService
					.insertOrUpdate(ouvertureOffreFormationDto);
			if (ouvertureOffreFormationDto.getId() != 0) {
				 findOffres();
				Utility.showSuccessMessage(bundleOuverture
						.getString("ouverturen_success_ouvrir"));
			}

		} catch (Exception e) {
			exception = 2;
			Utility.showErrorMessage(e.getMessage());
		}

	}

	/**
	 * [OuvertureOfBean.getException] Method
	 * 
	 * @author BELDI Jamel on : 11 mai 2014 18:36:31
	 * @return
	 */
	public int getException() {
		return exception;
	}

	/**
	 * [OuvertureOfBean.setException] Method
	 * 
	 * @author BELDI Jamel on : 11 mai 2014 18:36:27
	 * @param exception
	 */
	public void setException(int exception) {
		this.exception = exception;
	}

	/**
	 * [OuvertureOfBean.anneeAcademiqueService] Getter
	 * 
	 * @author BELDI Jamel on : 12 mai 2014 15:49:35
	 * @return the anneeAcademiqueService
	 */
	public AnneeAcademiqueService getAnneeAcademiqueService() {
		return anneeAcademiqueService;
	}

	/**
	 * [OuvertureOfBean.anneeAcademiqueService] Setter
	 * 
	 * @author BELDI Jamel on : 12 mai 2014 15:49:35
	 * @param anneeAcademiqueService
	 *            the anneeAcademiqueService to set
	 */
	public void setAnneeAcademiqueService(
			AnneeAcademiqueService anneeAcademiqueService) {
		this.anneeAcademiqueService = anneeAcademiqueService;
	}

	/**
	 * [OuvertureOfBean.ouvertureOffreFormationService] Getter
	 * 
	 * @author BELDI Jamel on : 12 mai 2014 15:49:35
	 * @return the ouvertureOffreFormationService
	 */
	public OuvertureOffreFormationService getOuvertureOffreFormationService() {
		return ouvertureOffreFormationService;
	}

	/**
	 * [OuvertureOfBean.ouvertureOffreFormationService] Setter
	 * 
	 * @author BELDI Jamel on : 12 mai 2014 15:49:35
	 * @param ouvertureOffreFormationService
	 *            the ouvertureOffreFormationService to set
	 */
	public void setOuvertureOffreFormationService(
			OuvertureOffreFormationService ouvertureOffreFormationService) {
		this.ouvertureOffreFormationService = ouvertureOffreFormationService;
	}

	/**
	 * [OuvertureOfBean.ouvertureOffreFormationDto] Getter
	 * 
	 * @author BELDI Jamel on : 12 mai 2014 15:51:34
	 * @return the ouvertureOffreFormationDto
	 */
	public OuvertureOffreFormationDto getOuvertureOffreFormationDto() {
		return ouvertureOffreFormationDto;
	}

	/**
	 * [OuvertureOfBean.ouvertureOffreFormationDto] Setter
	 * 
	 * @author BELDI Jamel on : 12 mai 2014 15:51:34
	 * @param ouvertureOffreFormationDto
	 *            the ouvertureOffreFormationDto to set
	 */
	public void setOuvertureOffreFormationDto(
			OuvertureOffreFormationDto ouvertureOffreFormationDto) {
		this.ouvertureOffreFormationDto = ouvertureOffreFormationDto;
	}

	/**
	 * [OuvertureOfBean.flash] Getter
	 * 
	 * @author BELDI Jamel on : 12 mai 2014 17:41:21
	 * @return the flash
	 */
	public Flash getFlash() {
		return flash;
	}

	/**
	 * [OuvertureOfBean.flash] Setter
	 * 
	 * @author BELDI Jamel on : 12 mai 2014 17:41:21
	 * @param flash
	 *            the flash to set
	 */
	public void setFlash(Flash flash) {
		this.flash = flash;
	}

	/**
	 * [OuvertureOfBean.anneeAcademiqueDto] Getter
	 * 
	 * @author BELDI Jamel on : 14 mai 2014 18:47:57
	 * @return the anneeAcademiqueDto
	 */
	public AnneeAcademiqueDto getAnneeAcademiqueDto() {
		return anneeAcademiqueDto;
	}

	/**
	 * [OuvertureOfBean.anneeAcademiqueDto] Setter
	 * 
	 * @author BELDI Jamel on : 14 mai 2014 18:47:57
	 * @param anneeAcademiqueDto
	 *            the anneeAcademiqueDto to set
	 */
	public void setAnneeAcademiqueDto(AnneeAcademiqueDto anneeAcademiqueDto) {
		this.anneeAcademiqueDto = anneeAcademiqueDto;
	}

	/**
	 * [OuvertureOfBean.sessionBean] Getter
	 * 
	 * @author BELDI Jamel on : 27 juil. 2014 09:46:47
	 * @return the sessionBean
	 */
	public SessionBean getSessionBean() {
		return sessionBean;
	}

	/**
	 * [OuvertureOfBean.sessionBean] Setter
	 * 
	 * @author BELDI Jamel on : 27 juil. 2014 09:46:47
	 * @param sessionBean
	 *            the sessionBean to set
	 */
	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}

	/**
	 * [OuvertureOfOpenBean.offreFormationSearchDto] Getter
	 * 
	 * @author BELDI Jamel on : 10 sept. 2014 11:04:48
	 * @return the offreFormationSearchDto
	 */
	public OffreFormationDto getOffreFormationSearchDto() {
		return offreFormationSearchDto;
	}

	/**
	 * [OuvertureOfOpenBean.offreFormationSearchDto] Setter
	 * 
	 * @author BELDI Jamel on : 10 sept. 2014 11:04:48
	 * @param offreFormationSearchDto
	 *            the offreFormationSearchDto to set
	 */
	public void setOffreFormationSearchDto(
			OffreFormationDto offreFormationSearchDto) {
		this.offreFormationSearchDto = offreFormationSearchDto;
	}

	/**
	 * [OuvertureOfOpenBean.listDomaines] Getter
	 * 
	 * @author BELDI Jamel on : 10 sept. 2014 11:13:09
	 * @return the listDomaines
	 */
	public List<SelectItem> getListDomaines() {
		return listDomaines;
	}

	/**
	 * [OuvertureOfOpenBean.listDomaines] Setter
	 * 
	 * @author BELDI Jamel on : 10 sept. 2014 11:13:09
	 * @param listDomaines
	 *            the listDomaines to set
	 */
	public void setListDomaines(List<SelectItem> listDomaines) {
		this.listDomaines = listDomaines;
	}

	/**
	 * [OuvertureOfOpenBean.listFilieres] Getter
	 * 
	 * @author BELDI Jamel on : 10 sept. 2014 11:13:09
	 * @return the listFilieres
	 */
	public List<SelectItem> getListFilieres() {
		return listFilieres;
	}

	/**
	 * [OuvertureOfOpenBean.listFilieres] Setter
	 * 
	 * @author BELDI Jamel on : 10 sept. 2014 11:13:09
	 * @param listFilieres
	 *            the listFilieres to set
	 */
	public void setListFilieres(List<SelectItem> listFilieres) {
		this.listFilieres = listFilieres;
	}

	/**
	 * [OuvertureOfOpenBean.listSpecialites] Getter
	 * 
	 * @author BELDI Jamel on : 10 sept. 2014 11:13:09
	 * @return the listSpecialites
	 */
	public List<SelectItem> getListSpecialites() {
		return listSpecialites;
	}

	/**
	 * [OuvertureOfOpenBean.listSpecialites] Setter
	 * 
	 * @author BELDI Jamel on : 10 sept. 2014 11:13:09
	 * @param listSpecialites
	 *            the listSpecialites to set
	 */
	public void setListSpecialites(List<SelectItem> listSpecialites) {
		this.listSpecialites = listSpecialites;
	}

	/**
	 * [OuvertureOfOpenBean.selectedOffreFormationDto] Getter
	 * 
	 * @author BELDI Jamel on : 10 sept. 2014 15:06:10
	 * @return the selectedOffreFormationDto
	 */
	public OffreFormationDto getSelectedOffreFormationDto() {
		return selectedOffreFormationDto;
	}

	/**
	 * [OuvertureOfOpenBean.selectedOffreFormationDto] Setter
	 * 
	 * @author BELDI Jamel on : 10 sept. 2014 15:06:10
	 * @param selectedOffreFormationDto
	 *            the selectedOffreFormationDto to set
	 */
	public void setSelectedOffreFormationDto(
			OffreFormationDto selectedOffreFormationDto) {
		this.selectedOffreFormationDto = selectedOffreFormationDto;
	}

	/**
	 * [OuvertureOfOpenBean.refSpecialiteLmdService] Getter
	 * 
	 * @author rlaib on : 23 nov. 2014 10:46:47
	 * @return the refSpecialiteLmdService
	 */
	public RefSpecialiteLmdService getRefSpecialiteLmdService() {
		return refSpecialiteLmdService;
	}

	/**
	 * [OuvertureOfOpenBean.refSpecialiteLmdService] Setter
	 * 
	 * @author rlaib on : 23 nov. 2014 10:46:47
	 * @param refSpecialiteLmdService
	 *            the refSpecialiteLmdService to set
	 */
	public void setRefSpecialiteLmdService(
			RefSpecialiteLmdService refSpecialiteLmdService) {
		this.refSpecialiteLmdService = refSpecialiteLmdService;
	}

	/**
	 * [OuvertureOfOpenBean.refDomaineLMDService] Getter
	 * 
	 * @author rlaib on : 23 nov. 2014 10:46:47
	 * @return the refDomaineLMDService
	 */
	public RefDomaineLMDService getRefDomaineLMDService() {
		return refDomaineLMDService;
	}

	/**
	 * [OuvertureOfOpenBean.refDomaineLMDService] Setter
	 * 
	 * @author rlaib on : 23 nov. 2014 10:46:47
	 * @param refDomaineLMDService
	 *            the refDomaineLMDService to set
	 */
	public void setRefDomaineLMDService(
			RefDomaineLMDService refDomaineLMDService) {
		this.refDomaineLMDService = refDomaineLMDService;
	}

	/**
	 * [OuvertureOfOpenBean.refFiliereLmdService] Getter
	 * 
	 * @author rlaib on : 23 nov. 2014 10:46:47
	 * @return the refFiliereLmdService
	 */
	public RefFiliereLmdService getRefFiliereLmdService() {
		return refFiliereLmdService;
	}

	/**
	 * [OuvertureOfOpenBean.refFiliereLmdService] Setter
	 * 
	 * @author rlaib on : 23 nov. 2014 10:46:47
	 * @param refFiliereLmdService
	 *            the refFiliereLmdService to set
	 */
	public void setRefFiliereLmdService(
			RefFiliereLmdService refFiliereLmdService) {
		this.refFiliereLmdService = refFiliereLmdService;
	}

	/**
	 * [OuvertureOfOpenBean.showDetail] Getter 
	 * @author MAKERRI Sofiane on : 4 déc. 2014  16:57:15
	 * @return the showDetail
	 */
	public boolean isShowDetail() {
		return showDetail;
	}

	/**
	 * [OuvertureOfOpenBean.showDetail] Setter 
	 * @author MAKERRI Sofiane on : 4 déc. 2014  16:57:15
	 * @param showDetail the showDetail to set
	 */
	public void setShowDetail(boolean showDetail) {
		this.showDetail = showDetail;
	}
	
	

}
