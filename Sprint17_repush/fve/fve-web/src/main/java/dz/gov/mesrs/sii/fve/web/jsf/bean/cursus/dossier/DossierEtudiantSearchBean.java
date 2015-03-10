package dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.dossier;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DossierEtudiantDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DossierInscriptionAdministrativeDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DossierTransfertDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.SituationHandicapDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.SituationSportiveDto;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.BilanSessionDto;
import dz.gov.mesrs.sii.fve.business.service.cursus.DossierEtudiantService;
import dz.gov.mesrs.sii.fve.business.service.cursus.DossierInscriptionAdministrativeService;
import dz.gov.mesrs.sii.fve.business.service.cursus.DossierTransfertService;
import dz.gov.mesrs.sii.fve.business.service.cursus.SituationHandicapService;
import dz.gov.mesrs.sii.fve.business.service.cursus.SituationSportiveService;
import dz.gov.mesrs.sii.fve.web.jsf.bean.SessionBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.CursusConstants;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.OfConstants;
import dz.gov.mesrs.sii.fve.web.jsf.bean.utils.Utility;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefIndividuDto;

/**
 * @author BELBRIK Oualid on : 25 mai 2014 12:18:07
 */
@ManagedBean(name = "dossierEtudiantSearchBean")
@ViewScoped
public class DossierEtudiantSearchBean implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author BELBRIK Oualid on : 25 mai 2014 12:19:04
	 */
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(DossierEtudiantBean.class);
	private ResourceBundle bundleDossier;

	private List<DossierEtudiantDto> dossierEtudiantResultSearch;
	private DossierEtudiantDto dossierEtudiantSearchDto;
	@ManagedProperty(value = "#{dossierEtudiantService}")
	private DossierEtudiantService dossierEtudiantService;

	@ManagedProperty("#{sessionBeanFve}")
	private SessionBean sessionBean;
	private String codeTypeDossier;

	@ManagedProperty(value = "#{dossierInscriptionAdministrativeService}")
	private DossierInscriptionAdministrativeService dossierInscriptionAdministrativeService;
	private DossierInscriptionAdministrativeDto dossierInscriptionAdministrativeDto;
	private List<DossierInscriptionAdministrativeDto> listDossiersInscriptionAdministrativeDto;

	@ManagedProperty(value = "#{dossierTransfertService}")
	private DossierTransfertService dossierTransfertService;
	private DossierTransfertDto dossierTransfertDto;
	private List<DossierTransfertDto> listDossiersTransfertDto;

	@ManagedProperty(value = "#{situationSportiveService}")
	SituationSportiveService situationSportiveService;
	private SituationSportiveDto situationSportiveDto;
	private List<SituationSportiveDto> listSituationsSportiveDto;

	@ManagedProperty("#{situationHandicapService}")
	private SituationHandicapService situationHandicapService;
	private SituationHandicapDto situationHandicapDto;
	private List<SituationHandicapDto> listSituationsHandicapDto;

	private LazyDataModel<DossierEtudiantDto> dossiersEtudiantsModel;

	private DossierEtudiantDto dossierEtudiant;
	private DossierInscriptionAdministrativeDto dossierInscriptionAministrativeDto;

	// UI
	private boolean isView;
	private boolean isCrud;

	private String viewId;

	// UI Recherche
	private String searchByNIN;
	private String searchByMatricule;
	private String searchByNom;
	private String searchByPrenom;

	/**
	 * [DossierEtudiantSearchBean.DossierEtudiantSearchBean()] Constructor
	 * 
	 * @author BELBRIK Oualid on : 25 mai 2014 12:18:07
	 */
	public DossierEtudiantSearchBean() {
		super();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		bundleDossier = facesContext.getApplication()
				.getResourceBundle(facesContext,
						OfConstants.DOSSIERETUDIANT_OF_MESSAGES_FILE_NAME);
	}

	@PostConstruct
	public void init() {
		// loadSearchDto();
		loadDossierEtudiantResultSearch();

	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 9 nov. 2014 16:36:03
	 */
	public void searchAction() {
		// TODO Important: Fix le probleme de de recherche apres avoir faire la
		// pagination (first = toujours la page en cours)
		// http://stackoverflow.com/questions/5616943/reset-primefaces-pagination-selected-page-on-ajax-request
		DataTable dataTable = (DataTable) FacesContext.getCurrentInstance()
				.getViewRoot()
				.findComponent("form_search:ResultSearchDataTable");
		dataTable.setFirst(0);
		// dataTable.reset();

		loadDossierEtudiantResultSearch();
		isCrud = false;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 9 nov. 2014 16:36:06
	 */
	public void searchAdvancedAction() {

	}

	private void loadDossierEtudiantResultSearch() {

		dossierEtudiantResultSearch = new ArrayList<DossierEtudiantDto>();
		try {

			dossierEtudiantSearchDto = new DossierEtudiantDto();

			dossierEtudiantSearchDto.setNumeroMatricule(searchByMatricule);

			RefIndividuDto r = new RefIndividuDto();
			r.setId(0);
			r.setIdentifiant(searchByNIN);
			r.setNomLatin(searchByNom);
			r.setNomArabe(searchByNom);
			r.setPrenomLatin(searchByPrenom);
			r.setPrenomArabe(searchByPrenom);

			dossierEtudiantSearchDto.setIndividu(r);

			dossierEtudiantSearchDto.setRefEtablissementId(sessionBean
					.getIdEtablissement());

			dossiersEtudiantsModel = new LazyDataModel<DossierEtudiantDto>() {
				private static final long serialVersionUID = -3025574151722364485L;

				@Override
				public List<DossierEtudiantDto> load(int first, int pageSize,
						String sortField, SortOrder sortOrder,
						Map<String, String> filters) {
					Boolean descending = null;

					if (sortOrder != null) {
						if (sortOrder.equals(SortOrder.DESCENDING)) {
							descending = Boolean.TRUE;
						} else {
							descending = Boolean.FALSE;
						}

					}

					dossiersEtudiantsModel.setRowCount(dossierEtudiantService
							.count(dossierEtudiantSearchDto).intValue());

					dossierEtudiantResultSearch = dossierEtudiantService
							.findAdvanced(dossierEtudiantSearchDto, sortField,
									pageSize, first, descending);
					try {

						if (dossierEtudiantResultSearch != null
								&& dossierEtudiantResultSearch.size() == 1) {
							dossierEtudiant = dossierEtudiantResultSearch
									.get(0);
							selectDossier();
							// simuler un row select event
							// SelectEvent event = new SelectEvent(null, null,
							// dossierEtudiantResultSearch.get(0));
							// onRowSelect(event);
						}

					} catch (Exception e) {
						e.printStackTrace();
						Utility.showErrorMessage(e.getMessage());
						log.error(e.getMessage());
					}

					return dossierEtudiantResultSearch;
				}
			};
		} catch (Exception e) {
			e.printStackTrace();
			Utility.showErrorMessage(e.getMessage());
			log.error(e.getMessage());
		}

	}

	/**
	 * Evenement de selection d'un dossier etudiant
	 * 
	 * @author Mounir.MESSAOUDI on : 5 nov. 2014 18:04:03
	 */
	public void onRowSelect(SelectEvent event) {
		try {
			dossierEtudiant = (DossierEtudiantDto) event.getObject();
			selectDossier();

		} catch (Exception e) {
			Utility.showErrorMessage(e.getMessage());
			log.error(e.getMessage());
		}
	}

	/**
	 * [DossierEtudiantSearchBean.selectDossier] Method
	 * 
	 * @author MAKERRI Sofiane on : 9 déc. 2014 12:33:41
	 */
	private void selectDossier() {
		dossierEtudiant.setRefAncienCodeEtablissement(sessionBean
				.getAncienCodeEtablissement());
//		dossierEtudiant.setRefCodeEtablissement(sessionBean
//				.getCodeEtablissement());
		List<DossierInscriptionAdministrativeDto> _inscriptionList = dossierEtudiant
				.getInscriptionAdministrativeDtos();
		if (_inscriptionList != null && !_inscriptionList.isEmpty()) {
			// recuperer le dossier d'inscription actuel / ou le derniere
			 dossierInscriptionAdministrativeDto = _inscriptionList.get(0);
			 // recuperer le bilans annuel fineaux de chaque dossier
			List<BilanSessionDto> bilanSessionDtos = new ArrayList<BilanSessionDto>();
			for (DossierInscriptionAdministrativeDto inscription : _inscriptionList) {
				List<BilanSessionDto> _bilanSessionList = inscription
						.getBilanSessionDtos();
				if (_bilanSessionList != null) {
					for (BilanSessionDto bilanSessionDto : _bilanSessionList) {
						if (Utility.isBilanAnnuel(bilanSessionDto.getType())) {
							bilanSessionDtos.add(bilanSessionDto);
						}
					}
				}
			}
			dossierEtudiant.setBilansSessionDtos(bilanSessionDtos);
		}
		// // recuperer le dossier d'inscription actuel / ou le derniere
		// dossierInscriptionAdministrativeDto =
		// dossierInscriptionAdministrativeService
		// .findDernierDossierInscriptionForEtudiant(dossierEtudiant
		// .getId());
		// if (dossierInscriptionAdministrativeDto == null)
		// dossierInscriptionAdministrativeDto = new
		// DossierInscriptionAdministrativeDto();
		//
		// // recuperer la liste des dossiers d'inscriptions
		// DossierInscriptionAdministrativeDto s = new
		// DossierInscriptionAdministrativeDto();
		// s.setMatriculeBac(dossierEtudiant.getDossierBachelierMatricule());
		// listDossiersInscriptionAdministrativeDto =
		// dossierInscriptionAdministrativeService
		// .findDossierInscriptionsBy(dossierEtudiant.getId());
		//
		// // recuperer le bilans annuel fineaux de chaque dossier
		// // d'inscription
		// listBilansSessionDto = new ArrayList<>();
		// if (listDossiersInscriptionAdministrativeDto != null) {
		// for (DossierInscriptionAdministrativeDto diaDto :
		// listDossiersInscriptionAdministrativeDto) {
		// BilanSessionDto b = bilanSessionService
		// .findBilanFinalByIdDossierInscrAdmin(diaDto.getId());
		// if (b != null)
		// listBilansSessionDto.add(b);
		// }
		// }

		// recuperer la liste des dossiers de transfert
		listDossiersTransfertDto = new ArrayList<>();
		DossierTransfertDto t = new DossierTransfertDto();
		t.setDossierEtudiantId(dossierEtudiant.getId());
		t.setDossierEtudiantMatricule(dossierEtudiant.getNumeroMatricule());
		listDossiersTransfertDto = dossierTransfertService.findAdvanced(t);

		isCrud = true;
		isView = true;
	}

	/**
	 * [DossierEtudiantSearchBean.DossierEtudiantResultSearch] Getter
	 * 
	 * @author BELBRIK Oualid on : 25 mai 2014 12:21:37
	 * @return the DossierEtudiantResultSearch
	 */
	public List<DossierEtudiantDto> getDossierEtudiantResultSearch() {
		return dossierEtudiantResultSearch;
	}

	/**
	 * [DossierEtudiantSearchBean.DossierEtudiantResultSearch] Setter
	 * 
	 * @author BELBRIK Oualid on : 25 mai 2014 12:21:37
	 * @param DossierEtudiantResultSearch
	 *            the DossierEtudiantResultSearch to set
	 */
	public void setDossierEtudiantResultSearch(
			List<DossierEtudiantDto> dossierEtudiantResultSearch) {
		this.dossierEtudiantResultSearch = dossierEtudiantResultSearch;
	}

	/**
	 * [DossierEtudiantSearchBean.DossierEtudiantSearchDto] Getter
	 * 
	 * @author BELBRIK Oualid on : 25 mai 2014 12:21:37
	 * @return the DossierEtudiantSearchDto
	 */
	public DossierEtudiantDto getDossierEtudiantSearchDto() {
		// if(sessionBean.containsKey("dossierEtudiantSearchDto")){
		// dossierEtudiantSearchDto = (DossierEtudiantDto)
		// sessionBean.getValueForKey("dossierEtudiantSearchDto");
		// }else{
		// dossierEtudiantSearchDto = new DossierEtudiantDto();
		//
		// }

		return dossierEtudiantSearchDto;
	}

	/**
	 * [DossierEtudiantSearchBean.DossierEtudiantSearchDto] Setter
	 * 
	 * @author BELBRIK Oualid on : 25 mai 2014 12:21:37
	 * @param DossierEtudiantSearchDto
	 *            the DossierEtudiantSearchDto to set
	 */
	public void setDossierEtudiantSearchDto(
			DossierEtudiantDto dossierEtudiantSearchDto) {
		this.dossierEtudiantSearchDto = dossierEtudiantSearchDto;
	}

	/**
	 * [DossierEtudiantSearchBean.DossierEtudiantfreFormationService] Getter
	 * 
	 * @author BELBRIK Oualid on : 25 mai 2014 12:21:37
	 * @return the DossierEtudiantfreFormationService
	 */
	public DossierEtudiantService getDossierEtudiantService() {
		return dossierEtudiantService;
	}

	/**
	 * [DossierEtudiantSearchBean.DossierEtudiantfreFormationService] Setter
	 * 
	 * @author BELBRIK Oualid on : 25 mai 2014 12:21:37
	 * @param DossierEtudiantfreFormationService
	 *            the DossierEtudiantfreFormationService to set
	 */
	public void setDossierEtudiantService(
			DossierEtudiantService dossierEtudiantService) {
		this.dossierEtudiantService = dossierEtudiantService;
	}

	/**
	 * [DossierEtudiantSearchBean.sessionBean] Getter
	 * 
	 * @author BELBRIK Oualid on : 25 mai 2014 12:29:37
	 * @return the sessionBean
	 */
	public SessionBean getSessionBean() {
		return sessionBean;
	}

	/**
	 * [DossierEtudiantSearchBean.sessionBean] Setter
	 * 
	 * @author BELBRIK Oualid on : 25 mai 2014 12:29:37
	 * @param sessionBean
	 *            the sessionBean to set
	 */
	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}

	/**
	 * [DossierEtudiantSearchBean.codeTypeDossier] Getter
	 * 
	 * @author MAKERRI Sofiane on : 28 mai 2014 08:34:11
	 * @return the codeTypeDossier
	 */
	public String getCodeTypeDossier() {
		codeTypeDossier = CursusConstants.CODE_TYPE_DOSSIER_ETUDIANT;
		return codeTypeDossier;
	}

	/**
	 * [DossierEtudiantSearchBean.codeTypeDossier] Setter
	 * 
	 * @author MAKERRI Sofiane on : 28 mai 2014 08:34:11
	 * @param codeTypeDossier
	 *            the codeTypeDossier to set
	 */
	public void setCodeTypeDossier(String codeTypeDossier) {
		this.codeTypeDossier = codeTypeDossier;
	}

	public LazyDataModel<DossierEtudiantDto> getDossiersEtudiantsModel() {
		return dossiersEtudiantsModel;
	}

	public void setDossiersEtudiantsModel(
			LazyDataModel<DossierEtudiantDto> dossiersEtudiantsModel) {
		this.dossiersEtudiantsModel = dossiersEtudiantsModel;
	}

	public boolean isView() {
		return isView;
	}

	public void setView(boolean isView) {
		this.isView = isView;
	}

	public boolean isCrud() {
		return isCrud;
	}

	public void setCrud(boolean isCrud) {
		this.isCrud = isCrud;
	}

	public DossierEtudiantDto getDossierEtudiant() {
		return dossierEtudiant;
	}

	public void setDossierEtudiant(DossierEtudiantDto dossierEtudiant) {
		this.dossierEtudiant = dossierEtudiant;
	}


	public DossierInscriptionAdministrativeService getDossierInscriptionAdministrativeService() {
		return dossierInscriptionAdministrativeService;
	}

	public void setDossierInscriptionAdministrativeService(
			DossierInscriptionAdministrativeService dossierInscriptionAdministrativeService) {
		this.dossierInscriptionAdministrativeService = dossierInscriptionAdministrativeService;
	}

	public DossierInscriptionAdministrativeDto getDossierInscriptionAdministrativeDto() {
		return dossierInscriptionAdministrativeDto;
	}

	public void setDossierInscriptionAdministrativeDto(
			DossierInscriptionAdministrativeDto dossierInscriptionAdministrativeDto) {
		this.dossierInscriptionAdministrativeDto = dossierInscriptionAdministrativeDto;
	}

	public List<DossierInscriptionAdministrativeDto> getListDossiersInscriptionAdministrativeDto() {
		return listDossiersInscriptionAdministrativeDto;
	}

	public void setListDossiersInscriptionAdministrativeDto(
			List<DossierInscriptionAdministrativeDto> listDossiersInscriptionAdministrativeDto) {
		this.listDossiersInscriptionAdministrativeDto = listDossiersInscriptionAdministrativeDto;
	}

	public DossierTransfertService getDossierTransfertService() {
		return dossierTransfertService;
	}

	public void setDossierTransfertService(
			DossierTransfertService dossierTransfertService) {
		this.dossierTransfertService = dossierTransfertService;
	}

	public DossierTransfertDto getDossierTransfertDto() {
		return dossierTransfertDto;
	}

	public void setDossierTransfertDto(DossierTransfertDto dossierTransfertDto) {
		this.dossierTransfertDto = dossierTransfertDto;
	}

	public List<DossierTransfertDto> getListDossiersTransfertDto() {
		return listDossiersTransfertDto;
	}

	public void setListDossiersTransfertDto(
			List<DossierTransfertDto> listDossiersTransfertDto) {
		this.listDossiersTransfertDto = listDossiersTransfertDto;
	}

	public SituationSportiveService getSituationSportiveService() {
		return situationSportiveService;
	}

	public void setSituationSportiveService(
			SituationSportiveService situationSportiveService) {
		this.situationSportiveService = situationSportiveService;
	}

	public SituationSportiveDto getSituationSportiveDto() {
		return situationSportiveDto;
	}

	public void setSituationSportiveDto(
			SituationSportiveDto situationSportiveDto) {
		this.situationSportiveDto = situationSportiveDto;
	}

	public List<SituationSportiveDto> getListSituationsSportiveDto() {
		return listSituationsSportiveDto;
	}

	public void setListSituationsSportiveDto(
			List<SituationSportiveDto> listSituationsSportiveDto) {
		this.listSituationsSportiveDto = listSituationsSportiveDto;
	}

	public SituationHandicapService getSituationHandicapService() {
		return situationHandicapService;
	}

	public void setSituationHandicapService(
			SituationHandicapService situationHandicapService) {
		this.situationHandicapService = situationHandicapService;
	}

	public SituationHandicapDto getSituationHandicapDto() {
		return situationHandicapDto;
	}

	public void setSituationHandicapDto(
			SituationHandicapDto situationHandicapDto) {
		this.situationHandicapDto = situationHandicapDto;
	}

	public List<SituationHandicapDto> getListSituationsHandicapDto() {
		return listSituationsHandicapDto;
	}

	public void setListSituationsHandicapDto(
			List<SituationHandicapDto> listSituationsHandicapDto) {
		this.listSituationsHandicapDto = listSituationsHandicapDto;
	}

	public DossierInscriptionAdministrativeDto getDossierInscriptionAministrativeDto() {
		return dossierInscriptionAministrativeDto;
	}

	public void setDossierInscriptionAministrativeDto(
			DossierInscriptionAdministrativeDto dossierInscriptionAministrativeDto) {
		this.dossierInscriptionAministrativeDto = dossierInscriptionAministrativeDto;
	}

	public String getViewId() {
		return viewId;
	}

	public void setViewId(String viewId) {
		this.viewId = viewId;
	}

	public String getSearchByNIN() {
		return searchByNIN;
	}

	public void setSearchByNIN(String searchByNIN) {
		this.searchByNIN = searchByNIN;
	}

	public String getSearchByMatricule() {
		return searchByMatricule;
	}

	public void setSearchByMatricule(String searchByMatricule) {
		this.searchByMatricule = searchByMatricule;
	}

	public String getSearchByNom() {
		return searchByNom;
	}

	public void setSearchByNom(String searchByNom) {
		this.searchByNom = searchByNom;
	}

	public String getSearchByPrenom() {
		return searchByPrenom;
	}

	public void setSearchByPrenom(String searchByPrenom) {
		this.searchByPrenom = searchByPrenom;
	}

}
