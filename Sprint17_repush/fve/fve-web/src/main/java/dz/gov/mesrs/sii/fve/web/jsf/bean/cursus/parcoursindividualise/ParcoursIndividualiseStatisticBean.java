/**
 * [dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.parcoursindividualise.ParcoursIndividualiseBean.java] 
 * @author BELDI Jamel on : 15 juil. 2014  10:03:05
 */
package dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.parcoursindividualise;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.model.SelectItem;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dozer.DozerBeanMapper;

import dz.gov.mesrs.sii.commons.business.service.bpm.SituationService;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DossierInscriptionAdministrativeDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.ParcoursIndividualiseDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.ParcoursIndividualiseMatiereDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.AnneeAcademiqueDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OffreFormationDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OuvertureOffreFormationDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.PeriodeDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.RattachementMcDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.UniteEnseignementDto;
import dz.gov.mesrs.sii.fve.business.service.bac.BacService;
import dz.gov.mesrs.sii.fve.business.service.cursus.DossierEtudiantService;
import dz.gov.mesrs.sii.fve.business.service.cursus.DossierInscriptionAdministrativeService;
import dz.gov.mesrs.sii.fve.business.service.cursus.DossierService;
import dz.gov.mesrs.sii.fve.business.service.cursus.ParcoursIndividualiseService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.AnneeAcademiqueService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OffreFormationService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OuvertureOffreFormationService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.ParcoursTypeService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.PeriodeService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.RattachementMcService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.RepartitionUeService;
import dz.gov.mesrs.sii.fve.web.jsf.bean.SessionBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.CursusConstants;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefDomaineLMDDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefFiliereLmdDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefIndividuDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefSpecialiteLmdDto;
import dz.gov.mesrs.sii.referentiel.business.service.NomenclatureService;
import dz.gov.mesrs.sii.referentiel.business.service.RefDomaineLMDService;
import dz.gov.mesrs.sii.referentiel.business.service.RefEtablissementService;
import dz.gov.mesrs.sii.referentiel.business.service.RefFiliereLmdService;
import dz.gov.mesrs.sii.referentiel.business.service.RefIndividuService;
import dz.gov.mesrs.sii.referentiel.business.service.RefSpecialiteLmdService;

/**
 * @author BELDI Jamel on : 11 ao�t 2014 14:41:09
 */
@ManagedBean(name = "parcoursIndividualiseStatisticBean")
@ViewScoped
public class ParcoursIndividualiseStatisticBean implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author BELDI Jamel on : 15 juil. 2014 10:03:10
	 */
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory
			.getLog(ParcoursIndividualiseStatisticBean.class);

	@ManagedProperty("#{flash}")
	private Flash flash;
	@ManagedProperty("#{sessionBeanFve}")
	private SessionBean sessionBean;
	private ResourceBundle bundleCommon;
	private ResourceBundle parcoursIndividualiseBundle;
	@ManagedProperty("#{dossierService}")
	private DossierService dossierService;
	@ManagedProperty("#{dossierEtudiantService}")
	private DossierEtudiantService dossierEtudiantService;
	@ManagedProperty("#{dossierInscriptionAdministrativeService}")
	private DossierInscriptionAdministrativeService dossierInscriptionAdministrativeService;
	@ManagedProperty("#{ouvertureOffreFormationService}")
	private OuvertureOffreFormationService ouvertureOffreFormationService;
	@ManagedProperty("#{offreFormationService}")
	private OffreFormationService offreFormationService;
	@ManagedProperty("#{anneeAcademiqueService}")
	private AnneeAcademiqueService anneeAcademiqueService;
	@ManagedProperty("#{mapper}")
	private DozerBeanMapper mapper;
	@ManagedProperty("#{bacService}")
	private BacService bacService;
	@ManagedProperty(value = "#{situationService}")
	private SituationService situationService;
	@ManagedProperty("#{parcoursTypeService}")
	private ParcoursTypeService parcoursTypeService;
	@ManagedProperty("#{parcoursIndividualiseService}")
	private ParcoursIndividualiseService parcoursIndividualiseService;
	@ManagedProperty("#{rattachementMcService}")
	private RattachementMcService rattachementMcService;
	private List<ParcoursIndividualiseMatiereDto> parcoursIndividualiseMatiereList;
	private List<ParcoursIndividualiseDto> parcoursIndividualiseList;
	private String situation;
	private ParcoursIndividualiseDto searchDto;
	private List<SelectItem> offreFormationList;
	private List<SelectItem> anneeAcademiqueList;
	private List<SelectItem> periodeList;
	private List<SelectItem> ueList;
	private Integer idUe;
	private List<ColumnModel> columns;
	// private PeriodeDto periodeDto;
	@ManagedProperty("#{periodeService}")
	private PeriodeService periodeService;
	
	@ManagedProperty("#{nomenclatureServiceImpl}")
	NomenclatureService nomenclatureService;

	@ManagedProperty("#{refIndividuServiceImpl}")
	RefIndividuService refIndividuService;
	
	@ManagedProperty("#{refSpecialiteLmdServiceImpl}")
	RefSpecialiteLmdService refSpecialiteLmdService;

	@ManagedProperty("#{refFiliereLmdServiceImpl}")
	RefFiliereLmdService refFiliereLmdService;

	@ManagedProperty("#{refDomaineLMDServiceImpl}")
	RefDomaineLMDService refDomaineLMDService;
	
	@ManagedProperty("#{refEtablissementServiceImpl}")
	private RefEtablissementService refEtablissementService;

	@ManagedProperty("#{repartitionUeService}")
	private RepartitionUeService repartitionUeService;

	private List<Integer> totals;
	
	/**
	 * [ParcoursIndividualiseBean.ParcoursIndividualiseBean()] Constructor
	 * 
	 * @author BELDI Jamel on : 15 juil. 2014 10:03:05
	 */
	public ParcoursIndividualiseStatisticBean() {
		super();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		bundleCommon = facesContext.getApplication().getResourceBundle(
				facesContext, CursusConstants.COMMON_BUNDLE_MSG_NAME);
		parcoursIndividualiseBundle = facesContext.getApplication()
				.getResourceBundle(facesContext,
						CursusConstants.PARCOURS_INDIVIDUALISE_BUNDLE_MSG_NAME);

	}

	@PostConstruct
	public void init() {
		searchDto = new ParcoursIndividualiseDto();
		loadAnneeAcademique();
		//loadPeriodes();
	}

	/**
	 * 
	 * [ParcoursIndividualiseConsultBean.loadAnneeAcademique] Method
	 * 
	 * @author BELDI Jamel on : 13 ao�t 2014 17:01:27
	 */
	public void loadAnneeAcademique() {
		try {
			List<AnneeAcademiqueDto> list = anneeAcademiqueService.findAll();
			anneeAcademiqueList = new ArrayList<SelectItem>();
			for (AnneeAcademiqueDto annee : list) {
				SelectItem item = new SelectItem(annee.getId(), annee.getCode());
				anneeAcademiqueList.add(item);
			}
		} catch (Exception e) {

		}
	}

//	/**
//	 * 
//	 * [ParcoursIndividualiseConsultBean.loadAnneeAcademique] Method
//	 * 
//	 * @author BELDI Jamel on : 8 sept. 2014 16:16:00
//	 */
//	public void loadPeriodes() {
//		try {
//			List<PeriodeDto> list = periodeService.findAll();
//			periodeList = new ArrayList<SelectItem>();
//			for (PeriodeDto periode : list) {
//				SelectItem item = new SelectItem(periode.getId(),
//						periode.getLibelleLongLt());
//				periodeList.add(item);
//			}
//		} catch (Exception e) {
//
//		}
//	}

	public void loadPeriodes() {
		try {
			periodeList = new ArrayList<SelectItem>();
			if(searchDto.getOuvertureOffreFormationId()==null){
				return;
			}
			OuvertureOffreFormationDto _oof = ouvertureOffreFormationService.findById(searchDto.getOuvertureOffreFormationId());
			List<PeriodeDto> list = periodeService.findByCycleId(_oof.getCycleId());
			
			for (PeriodeDto periode : list) {
				SelectItem item = new SelectItem(periode.getId(),
						periode.getLibelleLongLt());
				periodeList.add(item);
			}
		} catch (Exception e) {

		}
	}
	


	/**
	 * [ParcoursIndividualiseBean.loadDossierInscription] Method
	 * 
	 * @author BELDI Jamel on : 16 juil. 2014 14:15:28
	 * @throws Exception_Exception
	 */
	private DossierInscriptionAdministrativeDto loadDossierInscription(int id)
			throws Exception {
		DossierInscriptionAdministrativeDto _dossierInscriptionAdministrativeDto = dossierInscriptionAdministrativeService
				.findById(id);

		// Initialisation de la sp�cialit�
		// dossierInscription.setRefCodeSpecialite(_of.getRefCodeSpecialite());
		if (_dossierInscriptionAdministrativeDto.getRefCodeSpecialite() != null) {
			RefSpecialiteLmdDto _specialite = refSpecialiteLmdService
					.findByIdentifiant(_dossierInscriptionAdministrativeDto
							.getRefCodeSpecialite());
			if (_specialite != null) {
				_dossierInscriptionAdministrativeDto
						.setRefLibelleSpecialite(_specialite.getLlSpecialite());
				_dossierInscriptionAdministrativeDto
						.setRefLibelleSpecialiteArabe(_specialite
								.getLlSpecialiteArabe());
			}
		}
		// Initialisation de la fili�re
		// dossierInscription.setRefCodeFiliere(_of.getRefCodeFiliere());
		if (_dossierInscriptionAdministrativeDto.getRefCodeFiliere() != null) {
			RefFiliereLmdDto _filiere = refFiliereLmdService
					.findByIdentifiant(_dossierInscriptionAdministrativeDto
							.getRefCodeFiliere());
			if (_filiere != null) {
				_dossierInscriptionAdministrativeDto
						.setRefLibelleFiliere(_filiere.getLlFiliere());
				_dossierInscriptionAdministrativeDto
						.setRefLibelleFiliereArabe(_filiere.getLlFiliereArabe());
			}
		}
		// Initialisation du domaine
		// dossierInscription.setRefCodeDomaine(_of.getRefCodeDomaine());
		if (_dossierInscriptionAdministrativeDto.getRefCodeDomaine() != null) {
			RefDomaineLMDDto _domaine = refDomaineLMDService
					.findByIdentifiant(_dossierInscriptionAdministrativeDto
							.getRefCodeDomaine());
			if (_domaine != null) {
				_dossierInscriptionAdministrativeDto
						.setRefLibelleDomaine(_domaine.getLlDomaine());
				_dossierInscriptionAdministrativeDto
						.setRefLibelleDomaineArabe(_domaine.getLlDomaineArabe());
			}
		}

		RefIndividuDto _refIndividuDto = refIndividuService
				.findByIdentifiant(_dossierInscriptionAdministrativeDto
						.getNin());
		map(_refIndividuDto, _dossierInscriptionAdministrativeDto);
		return _dossierInscriptionAdministrativeDto;

	}

	/**
	 * [ParcoursIndividualiseBean.map] Method
	 * 
	 * @author BELDI Jamel on : 16 juil. 2014 14:15:33
	 * @param refIndividuDto
	 * @param dossierInscriptionAdministrativeDto
	 */
	private void map(
			RefIndividuDto refIndividuDto,
			DossierInscriptionAdministrativeDto dossierInscriptionAdministrativeDto) {
		if (dossierInscriptionAdministrativeDto == null) {
			dossierInscriptionAdministrativeDto = new DossierInscriptionAdministrativeDto();
		}
		dossierInscriptionAdministrativeDto.setIndividuId(refIndividuDto
				.getId());
		dossierInscriptionAdministrativeDto.setNin(refIndividuDto
				.getIdentifiant());
		dossierInscriptionAdministrativeDto.setIndividuNomLatin(refIndividuDto
				.getNomLatin());
		dossierInscriptionAdministrativeDto.setIndividuNomArabe(refIndividuDto
				.getNomArabe());
		dossierInscriptionAdministrativeDto
				.setIndividuPrenomArabe(refIndividuDto.getPrenomArabe());
		dossierInscriptionAdministrativeDto
				.setIndividuPrenomLatin(refIndividuDto.getPrenomLatin());
		dossierInscriptionAdministrativeDto
				.setIndividuLieuNaissance(refIndividuDto.getLieuNaissance());
		if (refIndividuDto.getDateNaissance() != null) {
			dossierInscriptionAdministrativeDto
					.setIndividuDateNaissance(refIndividuDto.getDateNaissance());
		}

	}

	/**
	 * 
	 * [ParcoursIndividualiseConsultBean.anneeChanged] Method
	 * 
	 * @author BELDI Jamel on : 13 ao�t 2014 16:35:53
	 */
	public void anneeChanged() {
		loadOffreFormationOuverte();

	}

	/**
	 * 
	 * [ParcoursIndividualiseConsultBean.loadOffreFormationOuverte] Method
	 * 
	 * @author BELDI Jamel on : 13 ao�t 2014 16:37:29
	 */
	public void loadOffreFormationOuverte() {
		offreFormationList = new ArrayList<SelectItem>();

		try {

			if (searchDto.getAnneeAcademiqueId() == null) {
				return;
			}

			OuvertureOffreFormationDto searchDto = new OuvertureOffreFormationDto();

			searchDto.setIdEtablissement(sessionBean
					.getIdEtablissement());
			searchDto.setAnneeAcademiqueId(searchDto.getAnneeAcademiqueId());
			searchDto.setEstOuverte(true);
			List<OuvertureOffreFormationDto> list = ouvertureOffreFormationService
					.findAdvanced(searchDto);
			for (OuvertureOffreFormationDto ouvertureOffreFormationDto : list) {
				OffreFormationDto offreFormationDto = offreFormationService
						.findById(ouvertureOffreFormationDto
								.getOffreFormationId());
				SelectItem selectItem = new SelectItem(
						ouvertureOffreFormationDto.getId(), offreFormationDto
								.getLibelleLongFr());
				offreFormationList.add(selectItem);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * [ParcoursIndividualiseStatisticBean.ofChanged] Method
	 * 
	 * @author BELDI Jamel on : 14 ao�t 2014 12:34:36
	 */
	public void ofChanged() {
		
		loadPeriodes();
		 searchDto.setIdPeriode(null);
		loadUebyOfAndPeriode();

	}

	/**
	 * 
	 * [ParcoursIndividualiseStatisticBean.ofChanged] Method
	 * 
	 * @author BELDI Jamel on : 14 ao�t 2014 12:34:36
	 */
	public void periodeChanged() {
		loadUebyOfAndPeriode();

	}

	/**
	 * 
	 * [ParcoursIndividualiseStatisticBean.loadUebyOf] Method
	 * 
	 * @author BELDI Jamel on : 14 ao�t 2014 12:34:40
	 */
	public void loadUebyOfAndPeriode() {
		ueList = new ArrayList<SelectItem>();

		try {
			if (searchDto.getOuvertureOffreFormationId() == null
					|| searchDto.getIdPeriode() == null) {
				return;
			}

			List<UniteEnseignementDto> _listUes = repartitionUeService
					.findUeOofAndPeriode(searchDto
							.getOuvertureOffreFormationId(), searchDto
							.getIdPeriode().intValue());
			for (UniteEnseignementDto ue : _listUes) {

				SelectItem selectItem = new SelectItem(ue.getId(),
						ue.getLibelleFr());
				ueList.add(selectItem);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * [ParcoursIndividualiseStatisticBean.createDynamicColumns] Method
	 * 
	 * @author BELDI Jamel on : 14 ao�t 2014 12:39:21
	 */
	private void createDynamicColumns() {
		List<RattachementMcDto> columnKeys = rattachementMcService.find(idUe,
				null);

		columns = new ArrayList<ColumnModel>();
		totals = new ArrayList<Integer>();
       
		for (RattachementMcDto columnKey : columnKeys) {

			columns.add(new ColumnModel(columnKey.getMcLibelleFr(), String
					.valueOf(columnKeys.indexOf(columnKey))));
			totals.add(0);

		}
	}

	/**
	 * 
	 * [ParcoursIndividualiseStatisticBean.search] Method
	 * 
	 * @author BELDI Jamel on : 14 ao�t 2014 12:39:25
	 */
	public void search() {
		FacesMessage msg = new FacesMessage();
		try {
			// update columns
			createDynamicColumns();

			parcoursIndividualiseList = new ArrayList<ParcoursIndividualiseDto>();
			searchDto.setRefCodeEtablissement(sessionBean
					.getCodeEtablissement());

			List<ParcoursIndividualiseDto> _parcoursIndividualiseList = parcoursIndividualiseService
					.findAdvanced(searchDto);
			if (_parcoursIndividualiseList != null
					&& !_parcoursIndividualiseList.isEmpty()) {

				for (ParcoursIndividualiseDto parcoursIndividualiseDto : _parcoursIndividualiseList) {
					DossierInscriptionAdministrativeDto _dossierInscriptionDto = loadDossierInscription(parcoursIndividualiseDto
							.getDossierInscriptionAdministrativeId().intValue());
					mapper.map(_dossierInscriptionDto, parcoursIndividualiseDto);
					//ParcoursIndividualiseDto _parcoursIndividualiseDto = parcoursIndividualiseService.findById(parcoursIndividualiseDto.getId());							
					 List<ParcoursIndividualiseMatiereDto> _parcoursIndividualiseMatiereList = parcoursIndividualiseDto.getParcoursIndividualiseMatiereList(); 
					List<ParcoursIndividualiseMatiereDto> _parcoursIndividualiseMatiereList2 = new ArrayList<ParcoursIndividualiseMatiereDto>();
					for (ParcoursIndividualiseMatiereDto parcoursIndividualiseMatiereDto : _parcoursIndividualiseMatiereList) {
						if (parcoursIndividualiseMatiereDto.getUeId()
								.intValue() == idUe){
							_parcoursIndividualiseMatiereList2
									.add(parcoursIndividualiseMatiereDto);
										if((parcoursIndividualiseMatiereDto.getOptionnelle()!=null && parcoursIndividualiseMatiereDto.getOptionnelle()==true && parcoursIndividualiseMatiereDto.isChoix()) || (parcoursIndividualiseMatiereDto.getOptionnelle()==null || parcoursIndividualiseMatiereDto.getOptionnelle()==false)){
											int index = findIndex(parcoursIndividualiseMatiereDto.getMcLibelleFr());
											totals.set(index, totals.get(index)+1);
					}
						}
					}

					parcoursIndividualiseDto
							.setParcoursIndividualiseMatiereList(_parcoursIndividualiseMatiereList2);
					parcoursIndividualiseList.add(parcoursIndividualiseDto);
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			parcoursIndividualiseList = new ArrayList<ParcoursIndividualiseDto>();
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundleCommon.getString("error_echec") + ": "
					+ bundleCommon.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);

		}
	}

	/**
	 * 
	 * @author BELDI Jamel on : 14 ao�t 2014 12:39:29
	 */
	static public class ColumnModel implements Serializable {

		private String header;
		private String property;

		public ColumnModel(String header, String property) {
			this.header = header;
			this.property = property;
		}

		public String getHeader() {
			return header;
		}

		public String getProperty() {
			return property;
		}
	}

	/**
	 * [ParcoursIndividualiseBean.sessionBean] Getter
	 * 
	 * @author BELDI Jamel on : 15 juil. 2014 10:10:09
	 * @return the sessionBean
	 */
	public SessionBean getSessionBean() {
		return sessionBean;
	}

	/**
	 * [ParcoursIndividualiseBean.sessionBean] Setter
	 * 
	 * @author BELDI Jamel on : 15 juil. 2014 10:10:09
	 * @param sessionBean
	 *            the sessionBean to set
	 */
	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}

	/**
	 * [ParcoursIndividualiseBean.dossierService] Getter
	 * 
	 * @author BELDI Jamel on : 15 juil. 2014 10:10:09
	 * @return the dossierService
	 */
	public DossierService getDossierService() {
		return dossierService;
	}

	/**
	 * [ParcoursIndividualiseBean.dossierService] Setter
	 * 
	 * @author BELDI Jamel on : 15 juil. 2014 10:10:09
	 * @param dossierService
	 *            the dossierService to set
	 */
	public void setDossierService(DossierService dossierService) {
		this.dossierService = dossierService;
	}

	/**
	 * [ParcoursIndividualiseBean.dossierEtudiantService] Getter
	 * 
	 * @author BELDI Jamel on : 15 juil. 2014 10:10:09
	 * @return the dossierEtudiantService
	 */
	public DossierEtudiantService getDossierEtudiantService() {
		return dossierEtudiantService;
	}

	/**
	 * [ParcoursIndividualiseBean.dossierEtudiantService] Setter
	 * 
	 * @author BELDI Jamel on : 15 juil. 2014 10:10:09
	 * @param dossierEtudiantService
	 *            the dossierEtudiantService to set
	 */
	public void setDossierEtudiantService(
			DossierEtudiantService dossierEtudiantService) {
		this.dossierEtudiantService = dossierEtudiantService;
	}

	/**
	 * [ParcoursIndividualiseBean.dossierInscriptionAdministrativeService]
	 * Getter
	 * 
	 * @author BELDI Jamel on : 15 juil. 2014 10:10:09
	 * @return the dossierInscriptionAdministrativeService
	 */
	public DossierInscriptionAdministrativeService getDossierInscriptionAdministrativeService() {
		return dossierInscriptionAdministrativeService;
	}

	/**
	 * [ParcoursIndividualiseBean.dossierInscriptionAdministrativeService]
	 * Setter
	 * 
	 * @author BELDI Jamel on : 15 juil. 2014 10:10:09
	 * @param dossierInscriptionAdministrativeService
	 *            the dossierInscriptionAdministrativeService to set
	 */
	public void setDossierInscriptionAdministrativeService(
			DossierInscriptionAdministrativeService dossierInscriptionAdministrativeService) {
		this.dossierInscriptionAdministrativeService = dossierInscriptionAdministrativeService;
	}

	/**
	 * [ParcoursIndividualiseBean.ouvertureOffreFormationService] Getter
	 * 
	 * @author BELDI Jamel on : 15 juil. 2014 10:10:09
	 * @return the ouvertureOffreFormationService
	 */
	public OuvertureOffreFormationService getOuvertureOffreFormationService() {
		return ouvertureOffreFormationService;
	}

	/**
	 * [ParcoursIndividualiseBean.ouvertureOffreFormationService] Setter
	 * 
	 * @author BELDI Jamel on : 15 juil. 2014 10:10:09
	 * @param ouvertureOffreFormationService
	 *            the ouvertureOffreFormationService to set
	 */
	public void setOuvertureOffreFormationService(
			OuvertureOffreFormationService ouvertureOffreFormationService) {
		this.ouvertureOffreFormationService = ouvertureOffreFormationService;
	}

	/**
	 * [ParcoursIndividualiseBean.offreFormationService] Getter
	 * 
	 * @author BELDI Jamel on : 15 juil. 2014 10:10:09
	 * @return the offreFormationService
	 */
	public OffreFormationService getOffreFormationService() {
		return offreFormationService;
	}

	/**
	 * [ParcoursIndividualiseBean.offreFormationService] Setter
	 * 
	 * @author BELDI Jamel on : 15 juil. 2014 10:10:09
	 * @param offreFormationService
	 *            the offreFormationService to set
	 */
	public void setOffreFormationService(
			OffreFormationService offreFormationService) {
		this.offreFormationService = offreFormationService;
	}

	/**
	 * [ParcoursIndividualiseBean.anneeAcademiqueService] Getter
	 * 
	 * @author BELDI Jamel on : 15 juil. 2014 10:10:09
	 * @return the anneeAcademiqueService
	 */
	public AnneeAcademiqueService getAnneeAcademiqueService() {
		return anneeAcademiqueService;
	}

	/**
	 * [ParcoursIndividualiseBean.anneeAcademiqueService] Setter
	 * 
	 * @author BELDI Jamel on : 15 juil. 2014 10:10:09
	 * @param anneeAcademiqueService
	 *            the anneeAcademiqueService to set
	 */
	public void setAnneeAcademiqueService(
			AnneeAcademiqueService anneeAcademiqueService) {
		this.anneeAcademiqueService = anneeAcademiqueService;
	}

	/**
	 * [ParcoursIndividualiseBean.mapper] Getter
	 * 
	 * @author BELDI Jamel on : 15 juil. 2014 10:10:09
	 * @return the mapper
	 */
	public DozerBeanMapper getMapper() {
		return mapper;
	}

	/**
	 * [ParcoursIndividualiseBean.mapper] Setter
	 * 
	 * @author BELDI Jamel on : 15 juil. 2014 10:10:09
	 * @param mapper
	 *            the mapper to set
	 */
	public void setMapper(DozerBeanMapper mapper) {
		this.mapper = mapper;
	}

	/**
	 * [ParcoursIndividualiseBean.bacService] Getter
	 * 
	 * @author BELDI Jamel on : 15 juil. 2014 10:10:09
	 * @return the bacService
	 */
	public BacService getBacService() {
		return bacService;
	}

	/**
	 * [ParcoursIndividualiseBean.bacService] Setter
	 * 
	 * @author BELDI Jamel on : 15 juil. 2014 10:10:09
	 * @param bacService
	 *            the bacService to set
	 */
	public void setBacService(BacService bacService) {
		this.bacService = bacService;
	}

	/**
	 * [ParcoursIndividualiseBean.situationService] Getter
	 * 
	 * @author BELDI Jamel on : 15 juil. 2014 10:10:09
	 * @return the situationService
	 */
	public SituationService getSituationService() {
		return situationService;
	}

	/**
	 * [ParcoursIndividualiseBean.situationService] Setter
	 * 
	 * @author BELDI Jamel on : 15 juil. 2014 10:10:09
	 * @param situationService
	 *            the situationService to set
	 */
	public void setSituationService(SituationService situationService) {
		this.situationService = situationService;
	}

	/**
	 * [ParcoursIndividualiseBean.flash] Getter
	 * 
	 * @author BELDI Jamel on : 15 juil. 2014 11:41:38
	 * @return the flash
	 */
	public Flash getFlash() {
		return flash;
	}

	/**
	 * [ParcoursIndividualiseBean.flash] Setter
	 * 
	 * @author BELDI Jamel on : 15 juil. 2014 11:41:38
	 * @param flash
	 *            the flash to set
	 */
	public void setFlash(Flash flash) {
		this.flash = flash;
	}

	/**
	 * [ParcoursIndividualiseBean.parcoursTypeService] Getter
	 * 
	 * @author BELDI Jamel on : 15 juil. 2014 14:26:53
	 * @return the parcoursTypeService
	 */
	public ParcoursTypeService getParcoursTypeService() {
		return parcoursTypeService;
	}

	/**
	 * [ParcoursIndividualiseBean.parcoursTypeService] Setter
	 * 
	 * @author BELDI Jamel on : 15 juil. 2014 14:26:53
	 * @param parcoursTypeService
	 *            the parcoursTypeService to set
	 */
	public void setParcoursTypeService(ParcoursTypeService parcoursTypeService) {
		this.parcoursTypeService = parcoursTypeService;
	}

	/**
	 * [ParcoursIndividualiseBean.parcoursIndividualiseMatiereList] Getter
	 * 
	 * @author BELDI Jamel on : 15 juil. 2014 14:26:53
	 * @return the parcoursIndividualiseMatiereList
	 */
	public List<ParcoursIndividualiseMatiereDto> getParcoursIndividualiseMatiereList() {
		return parcoursIndividualiseMatiereList;
	}

	/**
	 * [ParcoursIndividualiseBean.parcoursIndividualiseMatiereList] Setter
	 * 
	 * @author BELDI Jamel on : 15 juil. 2014 14:26:53
	 * @param parcoursIndividualiseMatiereList
	 *            the parcoursIndividualiseMatiereList to set
	 */
	public void setParcoursIndividualiseMatiereList(
			List<ParcoursIndividualiseMatiereDto> parcoursIndividualiseMatiereList) {
		this.parcoursIndividualiseMatiereList = parcoursIndividualiseMatiereList;
	}

	/**
	 * [ParcoursIndividualiseBean.parcoursIndividualiseService] Getter
	 * 
	 * @author BELDI Jamel on : 16 juil. 2014 11:04:45
	 * @return the parcoursIndividualiseService
	 */
	public ParcoursIndividualiseService getParcoursIndividualiseService() {
		return parcoursIndividualiseService;
	}

	/**
	 * [ParcoursIndividualiseBean.parcoursIndividualiseService] Setter
	 * 
	 * @author BELDI Jamel on : 16 juil. 2014 11:04:45
	 * @param parcoursIndividualiseService
	 *            the parcoursIndividualiseService to set
	 */
	public void setParcoursIndividualiseService(
			ParcoursIndividualiseService parcoursIndividualiseService) {
		this.parcoursIndividualiseService = parcoursIndividualiseService;
	}

	/**
	 * [ParcoursIndividualiseBean.rattachementMcService] Getter
	 * 
	 * @author BELDI Jamel on : 16 juil. 2014 12:37:36
	 * @return the rattachementMcService
	 */
	public RattachementMcService getRattachementMcService() {
		return rattachementMcService;
	}

	/**
	 * [ParcoursIndividualiseBean.rattachementMcService] Setter
	 * 
	 * @author BELDI Jamel on : 16 juil. 2014 12:37:36
	 * @param rattachementMcService
	 *            the rattachementMcService to set
	 */
	public void setRattachementMcService(
			RattachementMcService rattachementMcService) {
		this.rattachementMcService = rattachementMcService;
	}

	/**
	 * [ParcoursIndividualiseBean.situation] Getter
	 * 
	 * @author BELDI Jamel on : 20 juil. 2014 14:12:16
	 * @return the situation
	 */
	public String getSituation() {
		return situation;
	}

	/**
	 * [ParcoursIndividualiseBean.situation] Setter
	 * 
	 * @author BELDI Jamel on : 20 juil. 2014 14:12:16
	 * @param situation
	 *            the situation to set
	 */
	public void setSituation(String situation) {
		this.situation = situation;
	}

	/**
	 * [ParcoursIndividualiseValidateBean.parcoursIndividualiseList] Getter
	 * 
	 * @author BELDI Jamel on : 10 ao�t 2014 13:53:10
	 * @return the parcoursIndividualiseList
	 */
	public List<ParcoursIndividualiseDto> getParcoursIndividualiseList() {
		return parcoursIndividualiseList;
	}

	/**
	 * [ParcoursIndividualiseValidateBean.parcoursIndividualiseList] Setter
	 * 
	 * @author BELDI Jamel on : 10 ao�t 2014 13:53:10
	 * @param parcoursIndividualiseList
	 *            the parcoursIndividualiseList to set
	 */
	public void setParcoursIndividualiseList(
			List<ParcoursIndividualiseDto> parcoursIndividualiseList) {
		this.parcoursIndividualiseList = parcoursIndividualiseList;
	}

	/**
	 * [ParcoursIndividualiseConsultBean.searchDto] Getter
	 * 
	 * @author BELDI Jamel on : 13 ao�t 2014 11:21:48
	 * @return the searchDto
	 */
	public ParcoursIndividualiseDto getSearchDto() {
		return searchDto;
	}

	/**
	 * [ParcoursIndividualiseConsultBean.searchDto] Setter
	 * 
	 * @author BELDI Jamel on : 13 ao�t 2014 11:21:48
	 * @param searchDto
	 *            the searchDto to set
	 */
	public void setSearchDto(ParcoursIndividualiseDto searchDto) {
		this.searchDto = searchDto;
	}

	/**
	 * [ParcoursIndividualiseConsultBean.offreFormationList] Getter
	 * 
	 * @author BELDI Jamel on : 13 ao�t 2014 16:34:27
	 * @return the offreFormationList
	 */
	public List<SelectItem> getOffreFormationList() {
		return offreFormationList;
	}

	/**
	 * [ParcoursIndividualiseConsultBean.offreFormationList] Setter
	 * 
	 * @author BELDI Jamel on : 13 ao�t 2014 16:34:27
	 * @param offreFormationList
	 *            the offreFormationList to set
	 */
	public void setOffreFormationList(List<SelectItem> offreFormationList) {
		this.offreFormationList = offreFormationList;
	}

	/**
	 * [ParcoursIndividualiseConsultBean.anneeAcademiqueList] Getter
	 * 
	 * @author BELDI Jamel on : 13 ao�t 2014 16:34:27
	 * @return the anneeAcademiqueList
	 */
	public List<SelectItem> getAnneeAcademiqueList() {
		return anneeAcademiqueList;
	}

	/**
	 * [ParcoursIndividualiseConsultBean.anneeAcademiqueList] Setter
	 * 
	 * @author BELDI Jamel on : 13 ao�t 2014 16:34:27
	 * @param anneeAcademiqueList
	 *            the anneeAcademiqueList to set
	 */
	public void setAnneeAcademiqueList(List<SelectItem> anneeAcademiqueList) {
		this.anneeAcademiqueList = anneeAcademiqueList;
	}

	/**
	 * [ParcoursIndividualiseConsultBean.periodeList] Getter
	 * 
	 * @author BELDI Jamel on : 13 ao�t 2014 16:34:27
	 * @return the periodeList
	 */
	public List<SelectItem> getPeriodeList() {
		return periodeList;
	}

	/**
	 * [ParcoursIndividualiseConsultBean.periodeList] Setter
	 * 
	 * @author BELDI Jamel on : 13 ao�t 2014 16:34:27
	 * @param periodeList
	 *            the periodeList to set
	 */
	public void setPeriodeList(List<SelectItem> periodeList) {
		this.periodeList = periodeList;
	}

	/**
	 * [ParcoursIndividualiseStatisticBean.ueList] Getter
	 * 
	 * @author BELDI Jamel on : 14 ao�t 2014 11:11:23
	 * @return the ueList
	 */
	public List<SelectItem> getUeList() {
		return ueList;
	}

	/**
	 * [ParcoursIndividualiseStatisticBean.ueList] Setter
	 * 
	 * @author BELDI Jamel on : 14 ao�t 2014 11:11:23
	 * @param ueList
	 *            the ueList to set
	 */
	public void setUeList(List<SelectItem> ueList) {
		this.ueList = ueList;
	}

	/**
	 * [ParcoursIndividualiseStatisticBean.idUe] Getter
	 * 
	 * @author BELDI Jamel on : 14 ao�t 2014 12:01:03
	 * @return the idUe
	 */
	public Integer getIdUe() {
		return idUe;
	}

	/**
	 * [ParcoursIndividualiseStatisticBean.idUe] Setter
	 * 
	 * @author BELDI Jamel on : 14 ao�t 2014 12:01:03
	 * @param idUe
	 *            the idUe to set
	 */
	public void setIdUe(Integer idUe) {
		this.idUe = idUe;
	}

	/**
	 * [ParcoursIndividualiseStatisticBean.columns] Getter
	 * 
	 * @author BELDI Jamel on : 14 ao�t 2014 12:51:30
	 * @return the columns
	 */
	public List<ColumnModel> getColumns() {
		return columns;
	}

	/**
	 * [ParcoursIndividualiseStatisticBean.columns] Setter
	 * 
	 * @author BELDI Jamel on : 14 ao�t 2014 12:51:30
	 * @param columns
	 *            the columns to set
	 */
	public void setColumns(List<ColumnModel> columns) {
		this.columns = columns;
	}

	/**
	 * [ParcoursIndividualiseConsultBean.periodeService] Getter
	 * 
	 * @author BELDI Jamel on : 8 sept. 2014 16:15:29
	 * @return the periodeService
	 */
	public PeriodeService getPeriodeService() {
		return periodeService;
	}

	/**
	 * [ParcoursIndividualiseConsultBean.periodeService] Setter
	 * 
	 * @author BELDI Jamel on : 8 sept. 2014 16:15:29
	 * @param periodeService
	 *            the periodeService to set
	 */
	public void setPeriodeService(PeriodeService periodeService) {
		this.periodeService = periodeService;
	}

	/**
	 * [ParcoursIndividualiseStatisticBean.repartitionUeService] Getter
	 * 
	 * @author BELDI Jamel on : 10 sept. 2014 09:52:17
	 * @return the repartitionUeService
	 */
	public RepartitionUeService getRepartitionUeService() {
		return repartitionUeService;
	}

	/**
	 * [ParcoursIndividualiseStatisticBean.repartitionUeService] Setter
	 * 
	 * @author BELDI Jamel on : 10 sept. 2014 09:52:18
	 * @param repartitionUeService
	 *            the repartitionUeService to set
	 */
	public void setRepartitionUeService(
			RepartitionUeService repartitionUeService) {
		this.repartitionUeService = repartitionUeService;
	}

	/**
	 * [ParcoursIndividualiseStatisticBean.totals] Getter 
	 * @author BELDI Jamel on : 16 sept. 2014  10:39:05
	 * @return the totals
	 */
	public List<Integer> getTotals() {
		return totals;
	}

	/**
	 * [ParcoursIndividualiseStatisticBean.totals] Setter 
	 * @author BELDI Jamel on : 16 sept. 2014  10:39:05
	 * @param totals the totals to set
	 */
	public void setTotals(List<Integer> totals) {
		this.totals = totals;
	}
	
	/**
	 * 
	 * [ParcoursIndividualiseStatisticBean.findIndex] Method 
	 * @author BELDI Jamel  on : 16 sept. 2014  11:46:58
	 * @param mcLibelleFr
	 * @return
	 */
	 private int findIndex (String mcLibelleFr){
		 for (ColumnModel columnModel : columns) {
			if(columnModel.getHeader().equals(mcLibelleFr)){
				return columns.indexOf(columnModel); 
			}
		}
		 return 500;//en cas d'inexistance;
		 
	 }

	/**
	 * [ParcoursIndividualiseStatisticBean.nomenclatureService] Getter 
	 * @author rlaib on : 20 nov. 2014  16:16:49
	 * @return the nomenclatureService
	 */
	public NomenclatureService getNomenclatureService() {
		return nomenclatureService;
	}

	/**
	 * [ParcoursIndividualiseStatisticBean.nomenclatureService] Setter 
	 * @author rlaib on : 20 nov. 2014  16:16:49
	 * @param nomenclatureService the nomenclatureService to set
	 */
	public void setNomenclatureService(NomenclatureService nomenclatureService) {
		this.nomenclatureService = nomenclatureService;
	}

	/**
	 * [ParcoursIndividualiseStatisticBean.refIndividuService] Getter 
	 * @author rlaib on : 20 nov. 2014  16:16:49
	 * @return the refIndividuService
	 */
	public RefIndividuService getRefIndividuService() {
		return refIndividuService;
	}

	/**
	 * [ParcoursIndividualiseStatisticBean.refIndividuService] Setter 
	 * @author rlaib on : 20 nov. 2014  16:16:49
	 * @param refIndividuService the refIndividuService to set
	 */
	public void setRefIndividuService(RefIndividuService refIndividuService) {
		this.refIndividuService = refIndividuService;
	}

	/**
	 * [ParcoursIndividualiseStatisticBean.refSpecialiteLmdService] Getter 
	 * @author rlaib on : 20 nov. 2014  16:16:49
	 * @return the refSpecialiteLmdService
	 */
	public RefSpecialiteLmdService getRefSpecialiteLmdService() {
		return refSpecialiteLmdService;
	}

	/**
	 * [ParcoursIndividualiseStatisticBean.refSpecialiteLmdService] Setter 
	 * @author rlaib on : 20 nov. 2014  16:16:49
	 * @param refSpecialiteLmdService the refSpecialiteLmdService to set
	 */
	public void setRefSpecialiteLmdService(
			RefSpecialiteLmdService refSpecialiteLmdService) {
		this.refSpecialiteLmdService = refSpecialiteLmdService;
	}

	/**
	 * [ParcoursIndividualiseStatisticBean.refFiliereLmdService] Getter 
	 * @author rlaib on : 20 nov. 2014  16:16:49
	 * @return the refFiliereLmdService
	 */
	public RefFiliereLmdService getRefFiliereLmdService() {
		return refFiliereLmdService;
	}

	/**
	 * [ParcoursIndividualiseStatisticBean.refFiliereLmdService] Setter 
	 * @author rlaib on : 20 nov. 2014  16:16:49
	 * @param refFiliereLmdService the refFiliereLmdService to set
	 */
	public void setRefFiliereLmdService(RefFiliereLmdService refFiliereLmdService) {
		this.refFiliereLmdService = refFiliereLmdService;
	}

	/**
	 * [ParcoursIndividualiseStatisticBean.refDomaineLMDService] Getter 
	 * @author rlaib on : 20 nov. 2014  16:16:49
	 * @return the refDomaineLMDService
	 */
	public RefDomaineLMDService getRefDomaineLMDService() {
		return refDomaineLMDService;
	}

	/**
	 * [ParcoursIndividualiseStatisticBean.refDomaineLMDService] Setter 
	 * @author rlaib on : 20 nov. 2014  16:16:49
	 * @param refDomaineLMDService the refDomaineLMDService to set
	 */
	public void setRefDomaineLMDService(RefDomaineLMDService refDomaineLMDService) {
		this.refDomaineLMDService = refDomaineLMDService;
	}

	/**
	 * [ParcoursIndividualiseStatisticBean.refEtablissementService] Getter 
	 * @author Rafik on : 27 nov. 2014  13:46:06
	 * @return the refEtablissementService
	 */
	public RefEtablissementService getRefEtablissementService() {
		return refEtablissementService;
	}

	/**
	 * [ParcoursIndividualiseStatisticBean.refEtablissementService] Setter 
	 * @author Rafik on : 27 nov. 2014  13:46:06
	 * @param refEtablissementService the refEtablissementService to set
	 */
	public void setRefEtablissementService(
			RefEtablissementService refEtablissementService) {
		this.refEtablissementService = refEtablissementService;
	}

}
