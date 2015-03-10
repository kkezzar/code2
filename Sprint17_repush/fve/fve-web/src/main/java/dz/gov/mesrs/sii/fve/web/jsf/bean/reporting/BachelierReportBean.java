/**
 * [dz.gov.mesrs.sii.fve.web.jsf.bean.reporting.BachelierReportBean.java] 
 * @author MAKERRI Sofiane on : 16 juin 2014  16:42:58
 */
package dz.gov.mesrs.sii.fve.web.jsf.bean.reporting;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.dozer.DozerBeanMapper;
import org.jfree.util.Log;
import org.primefaces.model.chart.BubbleChartModel;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteDto;
import dz.gov.mesrs.sii.commons.business.service.bpm.SituationService;
import dz.gov.mesrs.sii.commons.web.jsf.bean.LoginBean;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DossierInscriptionAdministrativeDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.AnneeAcademiqueDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OffreFormationDto;
import dz.gov.mesrs.sii.fve.business.service.cursus.DossierInscriptionAdministrativeService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.AnneeAcademiqueService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OffreFormationService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OuvertureOffreFormationService;
import dz.gov.mesrs.sii.fve.business.util.UtilConstants;
import dz.gov.mesrs.sii.fve.web.jsf.bean.SessionBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.CursusConstants;
import dz.gov.mesrs.sii.fve.web.jsf.bean.utils.Const;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefAffectationDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefDomaineLMDDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEvenementDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefFiliereLmdDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefHistoriqueDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefIndividuDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefAffectationService;
import dz.gov.mesrs.sii.referentiel.business.service.RefDomaineLMDService;
import dz.gov.mesrs.sii.referentiel.business.service.RefEvenementService;
import dz.gov.mesrs.sii.referentiel.business.service.RefFiliereLmdService;
import dz.gov.mesrs.sii.referentiel.business.service.RefHistoriqueService;
import dz.gov.mesrs.sii.referentiel.business.service.RefIndividuService;

/**
 * @author MAKERRI Sofiane on : 16 juin 2014 16:42:58
 */
/**
 * @author MAKERRI Sofiane on : 21 juil. 2014 09:55:05
 */
@ManagedBean(name = "bachelierReportBean")
@RequestScoped
public class BachelierReportBean implements Serializable, Converter {

	/**
	 * serialVersionUID
	 * 
	 * @author MAKERRI Sofiane on : 16 juin 2014 16:51:50
	 */
	private static final long serialVersionUID = 1L;
	private List<OffreFormationDto> listBachelierImport;
	@ManagedProperty(value = "#{ouvertureOffreFormationService}")
	private OuvertureOffreFormationService ouvertureOffreFormationService;
	@ManagedProperty(value = "#{sessionBeanFve}")
	private SessionBean sessionBean;
	private ResourceBundle commonBundle;
	@ManagedProperty("#{offreFormationService}")
	private OffreFormationService offreFormationService;
	@ManagedProperty(value = "#{dossierInscriptionAdministrativeService}")
	private DossierInscriptionAdministrativeService dossierInscriptionAdministrativeService;
	@ManagedProperty(value = "#{anneeAcademiqueService}")
	private AnneeAcademiqueService anneeAcademiqueService;
	private PieChartModel importPieChart;
	private CartesianChartModel domaineBarChart;
	private CartesianChartModel agentBarChart;
	private PieChartModel domainePieChart;
	private PieChartModel agentPieChart;
	@ManagedProperty(value = "#{situationService}")
	private SituationService situationService;
	ChartSeries importe;
	ChartSeries inscrit;
	private BubbleChartModel bubble = new BubbleChartModel();
	ChartSeries jour;
	ChartSeries agent;
	private String importFileName;
	private String domaineFileName;
	private String agentFileName;
	private List<RefHistoriqueDto> listHistorique;

	@ManagedProperty(value = "#{loginBean}")
	private LoginBean loginBean;
	private ResourceBundle reportBundle;
	@ManagedProperty("#{param.anneeId}")
	private String anneeId;
	@ManagedProperty("#{param.groupeId}")
	private String groupeId;
	@ManagedProperty("#{param.evenmentId}")
	private String evenmentId;
	@ManagedProperty("#{param.loadStat}")
	private String loadStat;
	
	@ManagedProperty("#{refEvenementServiceImpl}")
	private RefEvenementService refEvenementService;
	
	@ManagedProperty("#{refAffectationServiceImpl}")
	private RefAffectationService refAffectationService;
	
	@ManagedProperty("#{refHistoriqueServiceImpl}")
	private RefHistoriqueService refHistoriqueService;
	
	@ManagedProperty("#{refIndividuServiceImpl}")
	private RefIndividuService refIndividuService;
	
	@ManagedProperty("#{refFiliereLmdServiceImpl}")
	private RefFiliereLmdService refFiliereLmdService;
	
	@ManagedProperty("#{refDomaineLMDServiceImpl}")
	private RefDomaineLMDService refDomaineLMDService;

	
	private boolean load;
	private DozerBeanMapper mapper;
	private List<SelectItem> refEvenementItemList;
	private List<RefEvenementDto> refEvenementList;
	private List<SelectItem> refGroupeItemList;
	private Integer idGroupe;
	private Integer idEvenement;
	private Integer idAnnee;
	private RefEvenementDto refInscriptionEvt;
	private List<AgentHistorique> agentList;
	private List<SelectItem> anneeAcademiqueList;
	private String evenementObject;
	private String llGroupe;
	private List<RefAffectationDto> refGroupeList;
	private Date dateDebut;
	private Date dateFin;
	private Date defaultDateDebut;
	private Date defaultDateFin;
	private List<Date> jourList;
	private List<SelectItem> jourItemList;
	@ManagedProperty("#{param.dateDebutId}")
	private String dateDebutId;
	@ManagedProperty("#{param.dateFinId}")
	private String dateFinId;
	private int idDateDebut;
	private int idDateFin;
	private List<ReportDomaineModel> reportImportList;
	private String header;

	public BachelierReportBean() {
		super();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		commonBundle = facesContext.getApplication().getResourceBundle(
				facesContext, Const.COMMON_RESSOURCES_BUNDLE_FILE_NAME);
		reportBundle = facesContext.getApplication().getResourceBundle(
				facesContext, Const.REPORTING_MESSAGES_FILE_NAME);
		mapper = new DozerBeanMapper();
	}

	/**
	 * [BachelierReportBean.init] Method
	 * 
	 * @author MAKERRI Sofiane on : 24 juin 2014 13:22:45
	 */
	@PostConstruct
	public void init() {
		importPieChart = new PieChartModel();
		domaineBarChart = new CartesianChartModel();
		agentPieChart = new PieChartModel();
		agentBarChart = new CartesianChartModel();
		loadAnneeAcademique();
		loadEvenement();
		loadEvenementJour();
		loadGroupe();
		loadSelectedDates();
		if (load) {

			loadReporting();
		}

	}

	/**
	 * [BachelierReportBean.loadAnneeAcademique] Method
	 * 
	 * @author MAKERRI Sofiane on : 22 juin 2014 17:52:50
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

	/**
	 * [BachelierReportBean.loadEvenement] Method
	 * 
	 * @author MAKERRI Sofiane on : 22 juin 2014 09:19:37
	 */
	public void loadEvenement() {
		try {
			if (idAnnee != null) {
				AnneeAcademiqueDto anneeAcademiqueDto = anneeAcademiqueService
						.findById(idAnnee);
				if (anneeAcademiqueDto == null) {
					return;
				}
				refEvenementList = refEvenementService.findByCodeType(
								sessionBean.getIdEtablissement(),
								CursusConstants.EVENEMENT_TYPE_INSCRIPTION_BACHELIER_CODE,
								new Integer(anneeAcademiqueDto
										.getPremiereAnnee()));
				refEvenementItemList = new ArrayList<SelectItem>();
				for (RefEvenementDto item : refEvenementList) {
					refEvenementItemList.add(new SelectItem(item.getId(), item
							.getObjet()));
				}
				if (refEvenementList != null && refEvenementList.size() == 1) {
					setEvenmentId(refEvenementList.get(0).getId().toString());
					setEvenementObject(refEvenementList.get(0).getObjet());
					loadJour(refEvenementList.get(0));

				}
			}
		} catch (Exception e) {

		}

	}

	/**
	 * [BachelierReportBean.loadGroupe] Method
	 * 
	 * @author MAKERRI Sofiane on : 22 juin 2014 09:32:55
	 */
	public void loadGroupe() {
		try {

			if (idEvenement != null) {

				refGroupeList =  refAffectationService.findGroupes("evenement",
						idEvenement);

				refGroupeItemList = new ArrayList<SelectItem>();
				for (RefAffectationDto item : refGroupeList) {
					refGroupeItemList.add(new SelectItem(item.getIdGroupe(),
							item.getLlGroupe()));
				}
				if (refGroupeList != null && refGroupeList.size() == 1) {
					setGroupeId(refGroupeList.get(0).getIdGroupe().toString());
					setLlGroupe(refGroupeList.get(0).getLlGroupe());
				}
			}
			if (idGroupe != null) {
				refGroupeList = new ArrayList<RefAffectationDto>();
				RefAffectationDto refAffectationDto = new RefAffectationDto();
				refAffectationDto.setIdGroupe(idGroupe);
				refGroupeList.add(refAffectationDto);
			}
		} catch (Exception e) {

		}
	}

	/**
	 * [BachelierReportBean. ] Method
	 * 
	 * @author MAKERRI Sofiane on : 24 juin 2014 08:03:08
	 */
	public void loadJour(RefEvenementDto refEvenementDto) {
		try {
			jourList = new ArrayList<Date>();
			jourItemList = new ArrayList<SelectItem>();

			Date _dateDebut = refEvenementDto.getDateDebut();
			Date _dateFin = refEvenementDto.getDateFin();
			defaultDateDebut = _dateDebut;
			defaultDateFin = _dateFin;
			dateDebut = defaultDateDebut;
			dateFin = defaultDateFin;

			if (_dateDebut != null && _dateFin != null) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(refEvenementDto.getDateDebut());
				int jourDebut = cal.get(Calendar.DAY_OF_MONTH);
				cal.setTime(refEvenementDto.getDateFin());
				int jourFin = cal.get(Calendar.DAY_OF_MONTH);
				if (dateDebutId == null) {
					setDateDebutId("1");
				}

				int idDate = 1;
				while (jourDebut <= jourFin) {
					DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
					jourList.add(_dateDebut);
					jourItemList.add(new SelectItem(idDate, df
							.format(_dateDebut)));
					idDate++;
					jourDebut++;
					Calendar c = Calendar.getInstance();
					c.setTime(_dateDebut);
					c.set(Calendar.DAY_OF_MONTH, jourDebut);
					_dateDebut = c.getTime();

				}
				if (dateFinId == null) {

					setDateFinId(new Integer(idDate - 1).toString());
				}
			}
		} catch (Exception e) {

		}
	}

	/**
	 * [BachelierReportBean.loadSelectedDates] Method
	 * 
	 * @author MAKERRI Sofiane on : 24 juin 2014 12:47:32
	 */
	public void loadSelectedDates() {
		if (jourItemList != null) {
			for (SelectItem item : jourItemList) {
				try {
					DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
					if ((int) item.getValue() == idDateDebut) {

						dateDebut = df.parse(item.getLabel());
					}
					if ((int) item.getValue() == idDateFin) {
						dateFin = df.parse(item.getLabel());
					}
				} catch (Exception e) {

				}
			}
		}
	}

	/**
	 * [BachelierReportBean.loadAgent] Method
	 * 
	 * @author MAKERRI Sofiane on : 22 juin 2014 10:33:17
	 */
	public void loadAgent() {
		try {
			agentList = new ArrayList<AgentHistorique>();

			if (dateDebut == null || dateFin == null) {
				dateDebut = defaultDateDebut;
				dateFin = defaultDateFin;
			}
			if (dateDebut == null || dateFin == null) {
				return;
			}
			for (RefAffectationDto refAffectationDto : refGroupeList) {

				List<RefAffectationDto> list =refAffectationService.findIndividus("groupe",
								refAffectationDto.getIdGroupe());

				for (RefAffectationDto current : list) {
					AgentHistorique ah = new AgentHistorique();
					mapper.map(current, ah);
					RefHistoriqueDto refHistoriqueDto = new RefHistoriqueDto();
					refHistoriqueDto.setIdEtablissement(sessionBean
							.getIdEtablissement());
					refHistoriqueDto.setIdIndividu(current.getIdIndividu());
					refHistoriqueDto.setTypeAction(Const.VALIDATE_ACTION_CODE);
					List<HistoriqueDetail> detail = new ArrayList<HistoriqueDetail>();

					List<RefHistoriqueDto> listHist = refHistoriqueService.findByPeriode(refHistoriqueDto,
									dateDebut, dateFin);
					if (listHist != null && !listHist.isEmpty()) {
						refHistoriqueDto = listHist.get(0);
						ah.setNomCompte(refHistoriqueDto.getNomCompte());
					}

					for (RefHistoriqueDto his : listHist) {
						HistoriqueDetail hd = new HistoriqueDetail();
						if (his.getNomEntite() != null
								&& his.getNomEntite()
										.equals(Const.DOSSIER_INSCRIPTION_ADMINISTRATIF_ENTITY_NAME)) {
							DossierInscriptionAdministrativeDto dia = dossierInscriptionAdministrativeService
									.findById(his.getIdOccurence());
							if (dia != null) {
								mapper.map(dia, hd);
								if (dia.getNin() != null) {
									RefIndividuDto refIndividuDto = refIndividuService
											.findByIdentifiant(dia
													.getNin());
									if (refIndividuDto != null) {
										hd.setIndividuNomLatin(refIndividuDto
												.getNomLatin());
										hd.setIndividuPrenomLatin(refIndividuDto
												.getPrenomLatin());
									}
								}

							}
						}

						mapper.map(his, hd);
						detail.add(hd);
					}
					ah.setActionNumber(detail.size());
					ah.setHistoriques(detail);
					agentList.add(ah);
				}
			}
			if (agentList.isEmpty()) {
				agentList = null;
			} else {
				createBubble();
			}

		} catch (Exception e) {
			Log.error(e.getMessage());
		}

	}

	/**
	 * [BachelierReportBean.createBubble] Method
	 * 
	 * @author MAKERRI Sofiane on : 23 juin 2014 18:08:49
	 */
	public void createBubble() {
		try {

			agentPieChart = new PieChartModel();

			for (AgentHistorique agentHistorique : agentList) {
				int actionNumber = 0;
				for (Date date : jourList) {
					DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
					for (HistoriqueDetail action : agentHistorique
							.getHistoriques()) {
						if (dateFormat.format(action.getDateAction()).equals(
								dateFormat.format(date))) {
							actionNumber++;
						}
					}
				}
				agentPieChart.set(agentHistorique.getNomLtIndividu() + "("
						+ actionNumber + ")", actionNumber);
			}

		} catch (Exception e) {

		}
	}

	/**
	 * [BachelierReportBean.loadOuvertureOfResultSearch] Method utilser avec
	 * offre de formation
	 * 
	 * @author MAKERRI Sofiane on : 16 juin 2014 16:54:23
	 */
	// private void loadBachierImport() {
	// FacesMessage msg = new FacesMessage();
	// listBachelierImport = new ArrayList<OffreFormationDto>();
	// try {
	//
	// List<OuvertureOffreFormationDto> list = ouvertureOffreFormationService
	// .findAdvanced(sessionBean.getCodeEtablissement(), null,
	// idAnnee, true);
	//
	// importe = new ChartSeries(
	// CursusConstants.GRAPHE_LEGENDE_INSCRIT_TITRE);
	// inscrit = new ChartSeries(
	// CursusConstants.GRAPHE_LEGENDE_NON_INSCRIT_TITRE);
	//
	// for (OuvertureOffreFormationDto off : list) {
	// OffreFormationDto offreFormationDto = offreFormationService
	// .findById(off.getOffreFormationId());
	// if (offreFormationDto != null) {
	// DossierInscriptionAdministrativeDto dossierInscAdmiDto = new
	// DossierInscriptionAdministrativeDto();
	// dossierInscAdmiDto.setAnneeAcademiqueId(idAnnee);
	// dossierInscAdmiDto
	// .setOuvertureOffreFormationId(off.getId());
	// dossierInscAdmiDto
	// .setRefCodeNiveau(CursusConstants.CYCLE_L1_CODE);
	// // **** recherche la liste des bacheliers (situation :
	// // importe)
	//
	// List<DossierInscriptionAdministrativeDto> listBachelier =
	// dossierInscriptionAdministrativeService
	// .findAdvanced(dossierInscAdmiDto);
	// if (listBachelier == null) {
	// offreFormationDto.setNombreEtudiantImporte(0);
	// } else {
	// offreFormationDto
	// .setNombreEtudiantImporte(listBachelier.size());
	// }
	// importPieChart.set(offreFormationDto.getI18nDtos()
	// .get("fr").getLibelleSpecialite(),
	// offreFormationDto.getNombreEtudiantImporte());
	//
	// // **** recherche la liste des bacheliers (situation :
	// // enregistre)
	// SituationEntiteDto situationEntiteDto = situationService
	// .findBySituationEntiteByCodeSituation(
	//
	//
	// UtilConstants.ENTITE_DOSSIER_CODE,
	// UtilConstants.SITUTAION_GENEREE_AUTO_CODE);
	// List<DossierInscriptionAdministrativeDto> listInscrit = new
	// ArrayList<DossierInscriptionAdministrativeDto>();
	//
	// for (DossierInscriptionAdministrativeDto dia : listBachelier) {
	// if (situationEntiteDto != null && dia.getSituationId() != null) {
	// if (dia.getSituationId().intValue() != situationEntiteDto
	// .getIdSituation()) {
	// listInscrit.add(dia);
	// }
	// }
	// }
	// offreFormationDto.setNombreEtudiantInscrit(listInscrit
	// .size());
	// inscrit.set(offreFormationDto.getI18nDtos().get("fr")
	// .getLibelleSpecialite(),
	// offreFormationDto.getNombreEtudiantInscrit());
	// importe.set(
	// offreFormationDto.getI18nDtos().get("fr")
	// .getLibelleSpecialite(),
	// offreFormationDto.getNombreEtudiantImporte()
	// - offreFormationDto
	// .getNombreEtudiantInscrit());
	//
	// listBachelierImport.add(offreFormationDto);
	// }
	//
	// }
	// domaineBarChart.addSeries(importe);
	// domaineBarChart.addSeries(inscrit);
	//
	// } catch (Exception e) {
	// msg.setSeverity(FacesMessage.SEVERITY_ERROR);
	// msg.setSummary(commonBundle.getString("error_echec") + ": "
	// + commonBundle.getString("error_echec_inconnue"));
	// FacesContext.getCurrentInstance().addMessage(null, msg);
	//
	// }
	//
	// }

	private void loadBachierImport() {
		reportImportList = new ArrayList<ReportDomaineModel>();
		importe = new ChartSeries(
				CursusConstants.GRAPHE_LEGENDE_NON_INSCRIT_TITRE);
		inscrit = new ChartSeries(CursusConstants.GRAPHE_LEGENDE_INSCRIT_TITRE);
		loadReportByFiliere();
		loadReportByDomaine();
		domaineBarChart.addSeries(importe);
		domaineBarChart.addSeries(inscrit);
		if (reportImportList.isEmpty()) {
			reportImportList = null;
		}
	}

	/**
	 * [BachelierReportBean.loadReportByFiliere] Method
	 * 
	 * @author MAKERRI Sofiane on : 25 juin 2014 11:42:52
	 */
	public void loadReportByFiliere() {
		FacesMessage msg = new FacesMessage();
		try {
			DossierInscriptionAdministrativeDto dia = new DossierInscriptionAdministrativeDto();
			dia.setRefCodeNiveau(CursusConstants.CYCLE_L1_CODE);
			dia.setAnneeAcademiqueId(idAnnee);
			dia.setRefEtablissementId(sessionBean.getIdEtablissement());
			List<Object[]> filiereList = dossierInscriptionAdministrativeService
					.findFiliere(dia);

			if (filiereList != null) {
				for (Object[] filiere : filiereList) {
					if (filiere[0] != null && filiere[1] != null) {
						loadFiliereSituation(filiere[0].toString(),
								(int) (long) filiere[1]);
					}
				}
			}
		} catch (Exception e) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(commonBundle.getString("error_echec") + ": "
					+ commonBundle.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);

		}

	}

	/**
	 * [BachelierReportBean.loadReportByDomaine] Method
	 * 
	 * @author MAKERRI Sofiane on : 25 juin 2014 11:42:59
	 */
	public void loadReportByDomaine() {

		FacesMessage msg = new FacesMessage();
		try {
			DossierInscriptionAdministrativeDto dia = new DossierInscriptionAdministrativeDto();
			dia.setRefCodeNiveau(CursusConstants.CYCLE_L1_CODE);
			dia.setAnneeAcademiqueId(idAnnee);
			dia.setRefEtablissementId(sessionBean.getIdEtablissement());
			List<Object[]> domaineList = dossierInscriptionAdministrativeService
					.findDomaine(dia);
			if (domaineList != null) {
				for (Object[] domaine : domaineList) {
					if (domaine[0] != null && domaine[1] != null) {
						loadDomaineSituation(domaine[0].toString(),
								(int) (long) domaine[1]);
					}

				}
			}

		} catch (Exception e) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(commonBundle.getString("error_echec") + ": "
					+ commonBundle.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);

		}

	}

	/**
	 * [BachelierReportBean.loadFiliereSituation] Method
	 * 
	 * @author MAKERRI Sofiane on : 25 juin 2014 12:38:50
	 * @param codeDomaineFiliere
	 * @param size
	 */
	public void loadFiliereSituation(String codeDomaineFiliere, Integer size) {
		FacesMessage msg = new FacesMessage();
		try {
			SituationEntiteDto situationGeneree = situationService
					.findBySituationEntiteByCodeSituation(
							UtilConstants.ENTITE_DOSSIER_CODE,
							UtilConstants.SITUTAION_GENEREE_AUTO_CODE);
			SituationEntiteDto situationValidee = situationService
					.findBySituationEntiteByCodeSituation(
							UtilConstants.ENTITE_DOSSIER_CODE,
							UtilConstants.SITUTAION_VALIDEE_CODE);
			int nbrInscrit = 0;
			int nbrNonInscrit = 0;
			if (situationGeneree != null) {
				DossierInscriptionAdministrativeDto diaGenere = new DossierInscriptionAdministrativeDto();
				diaGenere.setRefCodeNiveau(CursusConstants.CYCLE_L1_CODE);
				diaGenere.setAnneeAcademiqueId(idAnnee);
				diaGenere.setSituationId(situationGeneree.getId());
				diaGenere.setRefCodeFiliere(codeDomaineFiliere);
				diaGenere.setRefEtablissementId(sessionBean.getIdEtablissement());
				List<Object[]> diaGenereeList = null;

				diaGenereeList = dossierInscriptionAdministrativeService
						.findFiliere(diaGenere);
				if (diaGenereeList == null || diaGenereeList.isEmpty()) {
					importe.set(codeDomaineFiliere, 0);
				} else {
					importe.set(codeDomaineFiliere,
							(int) (long) diaGenereeList.get(0)[1]);
					nbrNonInscrit = (int) (long) diaGenereeList.get(0)[1];
				}
			}

			if (situationValidee != null) {
				DossierInscriptionAdministrativeDto diaValide = new DossierInscriptionAdministrativeDto();
				diaValide.setRefCodeNiveau(CursusConstants.CYCLE_L1_CODE);
				diaValide.setAnneeAcademiqueId(idAnnee);
				diaValide.setSituationId(situationValidee.getId());
				diaValide.setRefEtablissementId(sessionBean.getIdEtablissement());
				diaValide.setRefCodeFiliere(codeDomaineFiliere);
				List<Object[]> diaValideList = null;
				diaValideList = dossierInscriptionAdministrativeService
						.findFiliere(diaValide);
				if (diaValideList == null || diaValideList.isEmpty()) {
					inscrit.set(codeDomaineFiliere, 0);
				} else {
					inscrit.set(codeDomaineFiliere,
							(int) (long) diaValideList.get(0)[1]);
					nbrInscrit = (int) (long) diaValideList.get(0)[1];
				}

			}

			ReportDomaineModel reportDomaineModel = new ReportDomaineModel();
			RefFiliereLmdDto refFiliereLmdDto = refFiliereLmdService
					.findByIdentifiant(codeDomaineFiliere);
			if (refFiliereLmdDto != null) {
				reportDomaineModel.setDomaineFiliere(refFiliereLmdDto
						.getLlFiliere());
				reportDomaineModel.setCodeDomaineFiliere(codeDomaineFiliere);
				reportDomaineModel.setNbrBachelier(size);
				reportDomaineModel.setNbrInscrit(nbrInscrit);
				reportDomaineModel.setNbrNonInscrit(nbrNonInscrit);
				reportImportList.add(reportDomaineModel);
				importPieChart.set(refFiliereLmdDto.getLlFiliere(), size);
			}
		} catch (Exception e) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(commonBundle.getString("error_echec") + ": "
					+ commonBundle.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);

		}
	}

	/**
	 * [BachelierReportBean.loadDomaineSituation] Method
	 * 
	 * @author MAKERRI Sofiane on : 25 juin 2014 12:38:32
	 * @param codeDomaineFiliere
	 * @param size
	 */
	public void loadDomaineSituation(String codeDomaineFiliere, Integer size) {
		FacesMessage msg = new FacesMessage();
		try {
			SituationEntiteDto situationGeneree = situationService
					.findBySituationEntiteByCodeSituation(
							UtilConstants.ENTITE_DOSSIER_CODE,
							UtilConstants.SITUTAION_GENEREE_AUTO_CODE);
			SituationEntiteDto situationValidee = situationService
					.findBySituationEntiteByCodeSituation(
							UtilConstants.ENTITE_DOSSIER_CODE,
							UtilConstants.SITUTAION_VALIDEE_CODE);
			int nbrInscrit = 0;
			int nbrNonInscrit = 0;
			if (situationGeneree != null) {
				DossierInscriptionAdministrativeDto diaGenere = new DossierInscriptionAdministrativeDto();
				diaGenere.setRefCodeNiveau(CursusConstants.CYCLE_L1_CODE);
				diaGenere.setAnneeAcademiqueId(idAnnee);
				diaGenere.setSituationId(situationGeneree.getId());
				diaGenere.setRefCodeDomaine(codeDomaineFiliere);
				diaGenere.setRefEtablissementId(sessionBean.getIdEtablissement());
				List<Object[]> diaGenereeList = null;
				diaGenereeList = dossierInscriptionAdministrativeService
						.findDomaine(diaGenere);

				if (diaGenereeList == null || diaGenereeList.isEmpty()) {
					importe.set(codeDomaineFiliere, 0);
				} else {
					importe.set(codeDomaineFiliere,
							(int) (long) diaGenereeList.get(0)[1]);
					nbrNonInscrit = (int) (long) diaGenereeList.get(0)[1];
				}
			}

			if (situationValidee != null) {
				DossierInscriptionAdministrativeDto diaValide = new DossierInscriptionAdministrativeDto();
				diaValide.setRefCodeNiveau(CursusConstants.CYCLE_L1_CODE);
				diaValide.setAnneeAcademiqueId(idAnnee);
				diaValide.setSituationId(situationValidee.getId());
				diaValide.setRefCodeDomaine(codeDomaineFiliere);
				diaValide.setRefEtablissementId(sessionBean.getIdEtablissement());
				List<Object[]> diaValideList = null;
				diaValideList = dossierInscriptionAdministrativeService
						.findDomaine(diaValide);

				if (diaValideList == null || diaValideList.isEmpty()) {
					inscrit.set(codeDomaineFiliere, 0);
				} else {
					inscrit.set(codeDomaineFiliere,
							(int) (long) diaValideList.get(0)[1]);
					nbrInscrit = (int) (long) diaValideList.get(0)[1];
				}

			}

			ReportDomaineModel reportDomaineModel = new ReportDomaineModel();
			RefDomaineLMDDto refDomaineLMDDto = null;
			refDomaineLMDDto = refDomaineLMDService
					.findByIdentifiant(codeDomaineFiliere);
			if (refDomaineLMDDto != null) {
				reportDomaineModel.setDomaineFiliere(refDomaineLMDDto
						.getLlDomaine());
				reportDomaineModel.setCodeDomaineFiliere(codeDomaineFiliere);
				reportDomaineModel.setNbrBachelier(size);
				reportDomaineModel.setNbrInscrit(nbrInscrit);
				reportDomaineModel.setNbrNonInscrit(nbrNonInscrit);
				reportImportList.add(reportDomaineModel);
				importPieChart.set(refDomaineLMDDto.getLlDomaine(), size);
			}
		} catch (Exception e) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(commonBundle.getString("error_echec") + ": "
					+ commonBundle.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);

		}
	}

	/**
	 * [BachelierReportBean.listBachelierImport] Getter
	 * 
	 * @author MAKERRI Sofiane on : 17 juin 2014 12:25:06
	 * @return the listBachelierImport
	 */
	public List<OffreFormationDto> getListBachelierImport() {
		return listBachelierImport;
	}

	/**
	 * [BachelierReportBean.listBachelierImport] Setter
	 * 
	 * @author MAKERRI Sofiane on : 17 juin 2014 12:25:06
	 * @param listBachelierImport
	 *            the listBachelierImport to set
	 */
	public void setListBachelierImport(
			List<OffreFormationDto> listBachelierImport) {
		this.listBachelierImport = listBachelierImport;
	}

	/**
	 * [BachelierReportBean.ouvertureOffreFormationService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 16 juin 2014 16:53:50
	 * @return the ouvertureOffreFormationService
	 */
	public OuvertureOffreFormationService getOuvertureOffreFormationService() {
		return ouvertureOffreFormationService;
	}

	/**
	 * [BachelierReportBean.ouvertureOffreFormationService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 16 juin 2014 16:53:50
	 * @param ouvertureOffreFormationService
	 *            the ouvertureOffreFormationService to set
	 */
	public void setOuvertureOffreFormationService(
			OuvertureOffreFormationService ouvertureOffreFormationService) {
		this.ouvertureOffreFormationService = ouvertureOffreFormationService;
	}

	/**
	 * [BachelierReportBean.sessionBean] Getter
	 * 
	 * @author MAKERRI Sofiane on : 16 juin 2014 16:56:19
	 * @return the sessionBean
	 */
	public SessionBean getSessionBean() {
		return sessionBean;
	}

	/**
	 * [BachelierReportBean.sessionBean] Setter
	 * 
	 * @author MAKERRI Sofiane on : 16 juin 2014 16:56:19
	 * @param sessionBean
	 *            the sessionBean to set
	 */
	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}

	/**
	 * [BachelierReportBean.offreFormationService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 16 juin 2014 18:10:39
	 * @return the offreFormationService
	 */
	public OffreFormationService getOffreFormationService() {
		return offreFormationService;
	}

	/**
	 * [BachelierReportBean.offreFormationService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 16 juin 2014 18:10:39
	 * @param offreFormationService
	 *            the offreFormationService to set
	 */
	public void setOffreFormationService(
			OffreFormationService offreFormationService) {
		this.offreFormationService = offreFormationService;
	}

	/**
	 * [BachelierReportBean.dossierInscriptionAdministrativeService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 16 juin 2014 18:32:10
	 * @return the dossierInscriptionAdministrativeService
	 */
	public DossierInscriptionAdministrativeService getDossierInscriptionAdministrativeService() {
		return dossierInscriptionAdministrativeService;
	}

	/**
	 * [BachelierReportBean.dossierInscriptionAdministrativeService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 16 juin 2014 18:32:10
	 * @param dossierInscriptionAdministrativeService
	 *            the dossierInscriptionAdministrativeService to set
	 */
	public void setDossierInscriptionAdministrativeService(
			DossierInscriptionAdministrativeService dossierInscriptionAdministrativeService) {
		this.dossierInscriptionAdministrativeService = dossierInscriptionAdministrativeService;
	}

	/**
	 * [BachelierReportBean.importPieChart] Getter
	 * 
	 * @author MAKERRI Sofiane on : 17 juin 2014 12:37:51
	 * @return the importPieChart
	 */
	public PieChartModel getImportPieChart() {
		return importPieChart;
	}

	/**
	 * [BachelierReportBean.importPieChart] Setter
	 * 
	 * @author MAKERRI Sofiane on : 17 juin 2014 12:37:51
	 * @param importPieChart
	 *            the importPieChart to set
	 */
	public void setImportPieChart(PieChartModel importPieChart) {
		this.importPieChart = importPieChart;
	}

	/**
	 * [BachelierReportBean.situationService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 17 juin 2014 11:36:37
	 * @return the situationService
	 */
	public SituationService getSituationService() {
		return situationService;
	}

	/**
	 * [BachelierReportBean.situationService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 17 juin 2014 11:36:37
	 * @param situationService
	 *            the situationService to set
	 */
	public void setSituationService(SituationService situationService) {
		this.situationService = situationService;
	}

	/**
	 * [BachelierReportBean.domainePieChart] Getter
	 * 
	 * @author MAKERRI Sofiane on : 17 juin 2014 12:39:16
	 * @return the domainePieChart
	 */
	public PieChartModel getDomainePieChart() {
		return domainePieChart;
	}

	/**
	 * [BachelierReportBean.domainePieChart] Setter
	 * 
	 * @author MAKERRI Sofiane on : 17 juin 2014 12:39:16
	 * @param domainePieChart
	 *            the domainePieChart to set
	 */
	public void setDomainePieChart(PieChartModel domainePieChart) {
		this.domainePieChart = domainePieChart;
	}

	/**
	 * [BachelierReportBean.domaineBarChart] Getter
	 * 
	 * @author MAKERRI Sofiane on : 17 juin 2014 15:38:24
	 * @return the domaineBarChart
	 */
	public CartesianChartModel getDomaineBarChart() {
		return domaineBarChart;
	}

	/**
	 * [BachelierReportBean.domaineBarChart] Setter
	 * 
	 * @author MAKERRI Sofiane on : 17 juin 2014 15:38:24
	 * @param domaineBarChart
	 *            the domaineBarChart to set
	 */
	public void setDomaineBarChart(CartesianChartModel domaineBarChart) {
		this.domaineBarChart = domaineBarChart;
	}

	/**
	 * [BachelierReportBean.importe] Getter
	 * 
	 * @author MAKERRI Sofiane on : 17 juin 2014 14:22:52
	 * @return the importe
	 */
	public ChartSeries getImporte() {
		return importe;
	}

	/**
	 * [BachelierReportBean.importe] Setter
	 * 
	 * @author MAKERRI Sofiane on : 17 juin 2014 14:22:52
	 * @param importe
	 *            the importe to set
	 */
	public void setImporte(ChartSeries importe) {
		this.importe = importe;
	}

	/**
	 * [BachelierReportBean.inscrit] Getter
	 * 
	 * @author MAKERRI Sofiane on : 17 juin 2014 14:22:52
	 * @return the inscrit
	 */
	public ChartSeries getInscrit() {
		return inscrit;
	}

	/**
	 * [BachelierReportBean.inscrit] Setter
	 * 
	 * @author MAKERRI Sofiane on : 17 juin 2014 14:22:52
	 * @param inscrit
	 *            the inscrit to set
	 */
	public void setInscrit(ChartSeries inscrit) {
		this.inscrit = inscrit;
	}

	/**
	 * [BachelierReportBean.importFileName] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 juin 2014 08:42:13
	 * @return the importFileName
	 */
	public String getImportFileName() {
		DateFormat dateFormat = new SimpleDateFormat("dd_MM_yyyy");
		importFileName = sessionBean.getCodeEtablissement() + "_"
				+ sessionBean.getCodeAnneeAcademique() + "_AVANT_INSCRIPTION_"
				+ dateFormat.format(new Date());
		return importFileName;
	}

	/**
	 * [BachelierReportBean.importFileName] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 juin 2014 08:42:13
	 * @param importFileName
	 *            the importFileName to set
	 */
	public void setImportFileName(String importFileName) {
		this.importFileName = importFileName;
	}

	/**
	 * [BachelierReportBean.domaineFileName] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 juin 2014 08:42:13
	 * @return the domaineFileName
	 */
	public String getDomaineFileName() {
		DateFormat dateFormat = new SimpleDateFormat("dd_MM_yyyy");
		domaineFileName = sessionBean.getCodeEtablissement() + "_"
				+ sessionBean.getCodeAnneeAcademique() + "_DOMAINE_"
				+ dateFormat.format(new Date());
		return domaineFileName;
	}

	/**
	 * [BachelierReportBean.domaineFileName] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 juin 2014 08:42:13
	 * @param domaineFileName
	 *            the domaineFileName to set
	 */
	public void setDomaineFileName(String domaineFileName) {
		this.domaineFileName = domaineFileName;
	}

	/**
	 * [BachelierReportBean.listHistorique] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 juin 2014 18:01:51
	 * @return the listHistorique
	 */
	public List<RefHistoriqueDto> getListHistorique() {
		return listHistorique;
	}

	/**
	 * [BachelierReportBean.listHistorique] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 juin 2014 18:01:51
	 * @param listHistorique
	 *            the listHistorique to set
	 */
	public void setListHistorique(List<RefHistoriqueDto> listHistorique) {
		this.listHistorique = listHistorique;
	}



	/**
	 * [BachelierReportBean.agentFileName] Getter
	 * 
	 * @author MAKERRI Sofiane on : 19 juin 2014 12:30:18
	 * @return the agentFileName
	 */
	public String getAgentFileName() {
		DateFormat dateFormat = new SimpleDateFormat("dd_MM_yyyy");
		agentFileName = sessionBean.getCodeEtablissement() + "_"
				+ sessionBean.getCodeAnneeAcademique() + "_AGENT_"
				+ dateFormat.format(new Date());
		return agentFileName;
	}

	/**
	 * [BachelierReportBean.agentFileName] Setter
	 * 
	 * @author MAKERRI Sofiane on : 19 juin 2014 12:30:18
	 * @param agentFileName
	 *            the agentFileName to set
	 */
	public void setAgentFileName(String agentFileName) {
		this.agentFileName = agentFileName;
	}

	/**
	 * [BachelierReportBean.agentPieChart] Getter
	 * 
	 * @author MAKERRI Sofiane on : 19 juin 2014 12:32:21
	 * @return the agentPieChart
	 */
	public PieChartModel getAgentPieChart() {
		return agentPieChart;
	}

	/**
	 * [BachelierReportBean.agentPieChart] Setter
	 * 
	 * @author MAKERRI Sofiane on : 19 juin 2014 12:32:21
	 * @param agentPieChart
	 *            the agentPieChart to set
	 */
	public void setAgentPieChart(PieChartModel agentPieChart) {
		this.agentPieChart = agentPieChart;
	}

	/**
	 * [BachelierReportBean.refEvenementItemList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 23 juin 2014 08:29:31
	 * @return the refEvenementItemList
	 */
	public List<SelectItem> getRefEvenementItemList() {
		return refEvenementItemList;
	}

	/**
	 * [BachelierReportBean.refEvenementItemList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 23 juin 2014 08:29:31
	 * @param refEvenementItemList
	 *            the refEvenementItemList to set
	 */
	public void setRefEvenementItemList(List<SelectItem> refEvenementItemList) {
		this.refEvenementItemList = refEvenementItemList;
	}

	/**
	 * [BachelierReportBean.loadReporting] Method
	 * 
	 * @author MAKERRI Sofiane on : 22 juin 2014 09:17:32
	 */
	public void loadReporting() {
		loadAgent();
		loadBachierImport();

	}

	/**
	 * [BachelierReportBean.idGroupe] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 juin 2014 09:18:22
	 * @return the idGroupe
	 */
	public Integer getIdGroupe() {
		return idGroupe;
	}

	/**
	 * [BachelierReportBean.idGroupe] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 juin 2014 09:18:22
	 * @param idGroupe
	 *            the idGroupe to set
	 */
	public void setIdGroupe(Integer idGroupe) {
		this.idGroupe = idGroupe;
	}

	/**
	 * [BachelierReportBean.refInscriptionEvt] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 juin 2014 09:22:39
	 * @return the refInscriptionEvt
	 */
	public RefEvenementDto getRefInscriptionEvt() {
		return refInscriptionEvt;
	}

	/**
	 * [BachelierReportBean.refInscriptionEvt] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 juin 2014 09:22:39
	 * @param refInscriptionEvt
	 *            the refInscriptionEvt to set
	 */
	public void setRefInscriptionEvt(RefEvenementDto refInscriptionEvt) {
		this.refInscriptionEvt = refInscriptionEvt;
	}

	/**
	 * [BachelierReportBean.idEvenement] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 juin 2014 09:29:46
	 * @return the idEvenement
	 */
	public Integer getIdEvenement() {
		return idEvenement;
	}

	/**
	 * [BachelierReportBean.idEvenement] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 juin 2014 09:29:46
	 * @param idEvenement
	 *            the idEvenement to set
	 */
	public void setIdEvenement(Integer idEvenement) {
		this.idEvenement = idEvenement;
		if (idEvenement != null) {
			this.evenmentId = idEvenement.toString();
		}
	}

	/**
	 * [BachelierReportBean.refGroupeItemList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 23 juin 2014 08:34:14
	 * @return the refGroupeItemList
	 */
	public List<SelectItem> getRefGroupeItemList() {
		return refGroupeItemList;
	}

	/**
	 * [BachelierReportBean.refGroupeItemList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 23 juin 2014 08:34:14
	 * @param refGroupeItemList
	 *            the refGroupeItemList to set
	 */
	public void setRefGroupeItemList(List<SelectItem> refGroupeItemList) {
		this.refGroupeItemList = refGroupeItemList;
	}

	/**
	 * [BachelierReportBean.refGroupeList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 23 juin 2014 08:34:14
	 * @return the refGroupeList
	 */
	public List<RefAffectationDto> getRefGroupeList() {
		return refGroupeList;
	}

	/**
	 * [BachelierReportBean.refGroupeList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 23 juin 2014 08:34:14
	 * @param refGroupeList
	 *            the refGroupeList to set
	 */
	public void setRefGroupeList(List<RefAffectationDto> refGroupeList) {
		this.refGroupeList = refGroupeList;
	}

	/**
	 * [BachelierReportBean.agentList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 juin 2014 11:13:33
	 * @return the agentList
	 */
	public List<AgentHistorique> getAgentList() {
		return agentList;
	}

	/**
	 * [BachelierReportBean.agentList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 juin 2014 11:13:33
	 * @param agentList
	 *            the agentList to set
	 */
	public void setAgentList(List<AgentHistorique> agentList) {
		this.agentList = agentList;
	}

	/**
	 * [BachelierReportBean.groupeId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 juin 2014 13:30:47
	 * @return the groupeId
	 */
	public String getGroupeId() {
		return groupeId;
	}

	/**
	 * [BachelierReportBean.groupeId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 juin 2014 13:30:47
	 * @param groupeId
	 *            the groupeId to set
	 */
	public void setGroupeId(String groupeId) {
		if (groupeId != null && groupeId.equals("null")) {
			this.groupeId = null;
		} else {
			this.groupeId = groupeId;
			try {
				setIdGroupe(Integer.parseInt(groupeId));
			} catch (Exception e) {

			}
		}

	}

	/**
	 * [BachelierReportBean.mapper] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 juin 2014 14:13:00
	 * @return the mapper
	 */
	public DozerBeanMapper getMapper() {
		return mapper;
	}

	/**
	 * [BachelierReportBean.mapper] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 juin 2014 14:13:00
	 * @param mapper
	 *            the mapper to set
	 */
	public void setMapper(DozerBeanMapper mapper) {
		this.mapper = mapper;
	}

	/**
	 * [BachelierReportBean.loginBean] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 juin 2014 18:05:34
	 * @return the loginBean
	 */
	public LoginBean getLoginBean() {
		return loginBean;
	}

	/**
	 * [BachelierReportBean.loginBean] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 juin 2014 18:05:34
	 * @param loginBean
	 *            the loginBean to set
	 */
	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	/**
	 * [BachelierReportBean.evenmentId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 juin 2014 16:18:00
	 * @return the evenmentId
	 */
	public String getEvenmentId() {
		return evenmentId;
	}

	/**
	 * [BachelierReportBean.evenmentId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 juin 2014 16:18:00
	 * @param evenmentId
	 *            the evenmentId to set
	 */
	public void setEvenmentId(String evenmentId) {
		if (evenmentId != null && evenmentId.equals("null")) {
			this.evenmentId = null;
		} else {
			this.evenmentId = evenmentId;
			try {
				setIdEvenement(Integer.parseInt(evenmentId));
			} catch (Exception e) {

			}
		}

	}

	/**
	 * [BachelierReportBean.commonBundle] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 juin 2014 17:51:05
	 * @return the commonBundle
	 */
	public ResourceBundle getCommonBundle() {
		return commonBundle;
	}

	/**
	 * [BachelierReportBean.commonBundle] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 juin 2014 17:51:05
	 * @param commonBundle
	 *            the commonBundle to set
	 */
	public void setCommonBundle(ResourceBundle commonBundle) {
		this.commonBundle = commonBundle;
	}

	/**
	 * [BachelierReportBean.anneeId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 juin 2014 17:51:05
	 * @return the anneeId
	 */
	public String getAnneeId() {
		return anneeId;
	}

	/**
	 * [BachelierReportBean.anneeId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 juin 2014 17:51:05
	 * @param anneeId
	 *            the anneeId to set
	 */
	public void setAnneeId(String anneeId) {
		if (anneeId != null && anneeId.equals("null")) {
			this.anneeId = null;
		} else {
			this.anneeId = anneeId;
			try {
				setIdAnnee(Integer.parseInt(anneeId));
			} catch (Exception e) {

			}
		}

	}

	/**
	 * [BachelierReportBean.idAnnee] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 juin 2014 17:51:05
	 * @return the idAnnee
	 */
	public Integer getIdAnnee() {
		return idAnnee;
	}

	/**
	 * [BachelierReportBean.idAnnee] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 juin 2014 17:51:05
	 * @param idAnnee
	 *            the idAnnee to set
	 */
	public void setIdAnnee(Integer idAnnee) {
		this.idAnnee = idAnnee;
	}

	/**
	 * [BachelierReportBean.anneeAcademiqueList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 juin 2014 17:51:05
	 * @return the anneeAcademiqueList
	 */
	public List<SelectItem> getAnneeAcademiqueList() {
		return anneeAcademiqueList;
	}

	/**
	 * [BachelierReportBean.anneeAcademiqueList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 juin 2014 17:51:05
	 * @param anneeAcademiqueList
	 *            the anneeAcademiqueList to set
	 */
	public void setAnneeAcademiqueList(List<SelectItem> anneeAcademiqueList) {
		this.anneeAcademiqueList = anneeAcademiqueList;
	}

	/**
	 * [BachelierReportBean.anneeAcademiqueService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 juin 2014 17:52:16
	 * @return the anneeAcademiqueService
	 */
	public AnneeAcademiqueService getAnneeAcademiqueService() {
		return anneeAcademiqueService;
	}

	/**
	 * [BachelierReportBean.anneeAcademiqueService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 juin 2014 17:52:16
	 * @param anneeAcademiqueService
	 *            the anneeAcademiqueService to set
	 */
	public void setAnneeAcademiqueService(
			AnneeAcademiqueService anneeAcademiqueService) {
		this.anneeAcademiqueService = anneeAcademiqueService;
	}

	/**
	 * [BachelierReportBean.anneeChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 22 juin 2014 17:59:26
	 */
	public void anneeChanged() {
		loadEvenement();
		loadEvenementJour();
		loadGroupe();
	}

	/**
	 * [BachelierReportBean.jour] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 juin 2014 18:15:42
	 * @return the jour
	 */
	public ChartSeries getJour() {
		return jour;
	}

	/**
	 * [BachelierReportBean.jour] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 juin 2014 18:15:42
	 * @param jour
	 *            the jour to set
	 */
	public void setJour(ChartSeries jour) {
		this.jour = jour;
	}

	/**
	 * [BachelierReportBean.agent] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 juin 2014 18:15:42
	 * @return the agent
	 */
	public ChartSeries getAgent() {
		return agent;
	}

	/**
	 * [BachelierReportBean.agent] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 juin 2014 18:15:42
	 * @param agent
	 *            the agent to set
	 */
	public void setAgent(ChartSeries agent) {
		this.agent = agent;
	}

	/**
	 * [BachelierReportBean.evenementObject] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 juin 2014 18:15:42
	 * @return the evenementObject
	 */
	public String getEvenementObject() {
		return evenementObject;
	}

	/**
	 * [BachelierReportBean.evenementObject] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 juin 2014 18:15:42
	 * @param evenementObject
	 *            the evenementObject to set
	 */
	public void setEvenementObject(String evenementObject) {
		this.evenementObject = evenementObject;
	}

	/**
	 * [BachelierReportBean.evenementChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 22 juin 2014 18:33:36
	 */
	public void evenementChanged() {
		loadGroupe();
		loadEvenementJour();

	}

	/**
	 * [BachelierReportBean.loadEvenementJour] Method
	 * 
	 * @author MAKERRI Sofiane on : 21 juil. 2014 09:55:08
	 */
	public void loadEvenementJour() {
		if (refEvenementList != null) {
			for (RefEvenementDto item : refEvenementList) {
				if (item.getId().equals(idEvenement)) {
					loadJour(item);
				}
			}
		}
	}

	/**
	 * [BachelierReportBean.groupeChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 23 juin 2014 09:37:09
	 */
	public void groupeChanged() {
		if (idGroupe != null) {
			refGroupeList = new ArrayList<RefAffectationDto>();
			RefAffectationDto refAffectationDto = new RefAffectationDto();
			refAffectationDto.setIdGroupe(idGroupe);
			refGroupeList.add(refAffectationDto);
			setGroupeId(idGroupe.toString());

		}
	}

	/**
	 * [BachelierReportBean.dateDebutChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 24 juin 2014 08:00:50
	 */
	public void dateDebutChanged(ValueChangeEvent e) {
		if (e != null && e.getNewValue() != null) {
			setDateDebutId(e.getNewValue().toString());

		} else {
			dateDebut = defaultDateDebut;
		}

	}

	/**
	 * [BachelierReportBean.dateFinChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 24 juin 2014 11:31:06
	 * @param e
	 */
	public void dateFinChanged(ValueChangeEvent e) {
		if (e != null && e.getNewValue() != null) {
			setDateFinId(e.getNewValue().toString());
		} else {
			dateFin = defaultDateFin;
		}

	}

	/**
	 * [BachelierReportBean.llGroupe] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 juin 2014 18:36:48
	 * @return the llGroupe
	 */
	public String getLlGroupe() {
		return llGroupe;
	}

	/**
	 * [BachelierReportBean.llGroupe] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 juin 2014 18:36:48
	 * @param llGroupe
	 *            the llGroupe to set
	 */
	public void setLlGroupe(String llGroupe) {
		this.llGroupe = llGroupe;
	}

	/**
	 * [BachelierReportBean.refEvenementList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 23 juin 2014 08:30:38
	 * @return the refEvenementList
	 */
	public List<RefEvenementDto> getRefEvenementList() {
		return refEvenementList;
	}

	/**
	 * [BachelierReportBean.refEvenementList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 23 juin 2014 08:30:38
	 * @param refEvenementList
	 *            the refEvenementList to set
	 */
	public void setRefEvenementList(List<RefEvenementDto> refEvenementList) {
		this.refEvenementList = refEvenementList;
	}

	/**
	 * [BachelierReportBean.loadStat] Getter
	 * 
	 * @author MAKERRI Sofiane on : 23 juin 2014 09:56:22
	 * @return the loadStat
	 */
	public String getLoadStat() {
		return loadStat;
	}

	/**
	 * [BachelierReportBean.loadStat] Setter
	 * 
	 * @author MAKERRI Sofiane on : 23 juin 2014 09:56:22
	 * @param loadStat
	 *            the loadStat to set
	 */
	public void setLoadStat(String loadStat) {
		this.loadStat = loadStat;
		if (loadStat != null && loadStat.equals("null")) {
			this.loadStat = null;
		} else {
			this.loadStat = loadStat;
			try {
				setLoad(Boolean.parseBoolean(loadStat));
			} catch (Exception e) {

			}
		}
	}

	/**
	 * [BachelierReportBean.load] Getter
	 * 
	 * @author MAKERRI Sofiane on : 23 juin 2014 09:56:22
	 * @return the load
	 */
	public boolean isLoad() {
		return load;
	}

	/**
	 * [BachelierReportBean.load] Setter
	 * 
	 * @author MAKERRI Sofiane on : 23 juin 2014 09:56:22
	 * @param load
	 *            the load to set
	 */
	public void setLoad(boolean load) {
		this.load = load;
	}

	/**
	 * [BachelierReportBean.bubble] Getter
	 * 
	 * @author MAKERRI Sofiane on : 23 juin 2014 17:47:23
	 * @return the bubble
	 */
	public BubbleChartModel getBubble() {

		return bubble;
	}

	/**
	 * [BachelierReportBean.bubble] Setter
	 * 
	 * @author MAKERRI Sofiane on : 23 juin 2014 17:47:23
	 * @param bubble
	 *            the bubble to set
	 */
	public void setBubble(BubbleChartModel bubble) {
		this.bubble = bubble;
	}

	/**
	 * [BachelierReportBean.jourList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 24 juin 2014 08:01:43
	 * @return the jourList
	 */
	public List<Date> getJourList() {
		return jourList;
	}

	/**
	 * [BachelierReportBean.jourList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 24 juin 2014 08:01:43
	 * @param jourList
	 *            the jourList to set
	 */
	public void setJourList(List<Date> jourList) {
		this.jourList = jourList;
	}

	/**
	 * [BachelierReportBean.jourItemList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 24 juin 2014 08:58:11
	 * @return the jourItemList
	 */
	public List<SelectItem> getJourItemList() {
		return jourItemList;
	}

	/**
	 * [BachelierReportBean.jourItemList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 24 juin 2014 08:58:11
	 * @param jourItemList
	 *            the jourItemList to set
	 */
	public void setJourItemList(List<SelectItem> jourItemList) {
		this.jourItemList = jourItemList;
	}

	/**
	 * [BachelierReportBean.dateDebut] Getter
	 * 
	 * @author MAKERRI Sofiane on : 24 juin 2014 09:27:37
	 * @return the dateDebut
	 */
	public Date getDateDebut() {
		return dateDebut;
	}

	/**
	 * [BachelierReportBean.dateDebut] Setter
	 * 
	 * @author MAKERRI Sofiane on : 24 juin 2014 09:27:37
	 * @param dateDebut
	 *            the dateDebut to set
	 */
	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	/**
	 * [BachelierReportBean.dateFin] Getter
	 * 
	 * @author MAKERRI Sofiane on : 24 juin 2014 09:27:37
	 * @return the dateFin
	 */
	public Date getDateFin() {
		return dateFin;
	}

	/**
	 * [BachelierReportBean.dateFin] Setter
	 * 
	 * @author MAKERRI Sofiane on : 24 juin 2014 09:27:37
	 * @param dateFin
	 *            the dateFin to set
	 */
	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value == null || value.isEmpty()) {
			return null;
		}
		try {
			if (jourItemList != null) {
				for (SelectItem item : jourItemList) {

					DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
					Date d1 = df.parse(value);
					Date d2 = df.parse(item.getLabel());
					if (d1.equals(d2)) {

						return item.getValue();
					}

				}
			}

		} catch (Exception e) {
			Log.error(e.getMessage());
			return null;
		}
		return null;

	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value == null || value.toString().isEmpty()) {
			return null;
		}
		try {
			if (jourItemList != null) {
				for (SelectItem item : jourItemList) {

					if ((int) value == (int) item.getValue()) {

						return item.getLabel();
					}

				}
			}

		} catch (Exception e) {
			Log.error(e.getMessage());
			return null;
		}
		return null;
	}

	/**
	 * [BachelierReportBean.defaultDateDebut] Getter
	 * 
	 * @author MAKERRI Sofiane on : 24 juin 2014 11:17:54
	 * @return the defaultDateDebut
	 */
	public Date getDefaultDateDebut() {
		return defaultDateDebut;
	}

	/**
	 * [BachelierReportBean.defaultDateDebut] Setter
	 * 
	 * @author MAKERRI Sofiane on : 24 juin 2014 11:17:54
	 * @param defaultDateDebut
	 *            the defaultDateDebut to set
	 */
	public void setDefaultDateDebut(Date defaultDateDebut) {
		this.defaultDateDebut = defaultDateDebut;
	}

	/**
	 * [BachelierReportBean.defaultDateFin] Getter
	 * 
	 * @author MAKERRI Sofiane on : 24 juin 2014 11:17:54
	 * @return the defaultDateFin
	 */
	public Date getDefaultDateFin() {
		return defaultDateFin;
	}

	/**
	 * [BachelierReportBean.defaultDateFin] Setter
	 * 
	 * @author MAKERRI Sofiane on : 24 juin 2014 11:17:54
	 * @param defaultDateFin
	 *            the defaultDateFin to set
	 */
	public void setDefaultDateFin(Date defaultDateFin) {
		this.defaultDateFin = defaultDateFin;
	}

	/**
	 * [BachelierReportBean.idDateDebut] Getter
	 * 
	 * @author MAKERRI Sofiane on : 24 juin 2014 11:38:26
	 * @return the idDateDebut
	 */
	public int getIdDateDebut() {
		return idDateDebut;
	}

	/**
	 * [BachelierReportBean.idDateDebut] Setter
	 * 
	 * @author MAKERRI Sofiane on : 24 juin 2014 11:38:26
	 * @param idDateDebut
	 *            the idDateDebut to set
	 */
	public void setIdDateDebut(int idDateDebut) {
		this.idDateDebut = idDateDebut;
		loadSelectedDates();
	}

	/**
	 * [BachelierReportBean.idDateFin] Getter
	 * 
	 * @author MAKERRI Sofiane on : 24 juin 2014 11:38:26
	 * @return the idDateFin
	 */
	public int getIdDateFin() {
		return idDateFin;
	}

	/**
	 * [BachelierReportBean.idDateFin] Setter
	 * 
	 * @author MAKERRI Sofiane on : 24 juin 2014 11:38:26
	 * @param idDateFin
	 *            the idDateFin to set
	 */
	public void setIdDateFin(int idDateFin) {
		this.idDateFin = idDateFin;
		loadSelectedDates();
	}

	/**
	 * [BachelierReportBean.dateDebutId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 24 juin 2014 11:40:54
	 * @return the dateDebutId
	 */
	public String getDateDebutId() {
		return dateDebutId;
	}

	/**
	 * [BachelierReportBean.dateDebutId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 24 juin 2014 11:40:54
	 * @param dateDebutId
	 *            the dateDebutId to set
	 */
	public void setDateDebutId(String dateDebutId) {
		if (dateDebutId != null && dateDebutId.equals("null")) {
			this.dateDebutId = null;
		} else {
			this.dateDebutId = dateDebutId;
			try {
				setIdDateDebut(Integer.parseInt(dateDebutId));
			} catch (Exception e) {

			}
		}

	}

	/**
	 * [BachelierReportBean.dateFinId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 24 juin 2014 11:40:54
	 * @return the dateFinId
	 */
	public String getDateFinId() {
		return dateFinId;
	}

	/**
	 * [BachelierReportBean.dateFinId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 24 juin 2014 11:40:54
	 * @param dateFinId
	 *            the dateFinId to set
	 */
	public void setDateFinId(String dateFinId) {
		if (dateFinId != null && dateFinId.equals("null")) {
			this.dateFinId = null;
		} else {
			this.dateFinId = dateFinId;
			try {
				setIdDateFin(Integer.parseInt(dateFinId));
			} catch (Exception e) {

			}
		}

	}

	/**
	 * [BachelierReportBean.agentBarChart] Getter
	 * 
	 * @author MAKERRI Sofiane on : 24 juin 2014 15:14:56
	 * @return the agentBarChart
	 */
	public CartesianChartModel getAgentBarChart() {
		return agentBarChart;
	}

	/**
	 * [BachelierReportBean.agentBarChart] Setter
	 * 
	 * @author MAKERRI Sofiane on : 24 juin 2014 15:14:56
	 * @param agentBarChart
	 *            the agentBarChart to set
	 */
	public void setAgentBarChart(CartesianChartModel agentBarChart) {
		this.agentBarChart = agentBarChart;
	}

	/**
	 * [BachelierReportBean.reportImportList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 juin 2014 10:25:06
	 * @return the reportImportList
	 */
	public List<ReportDomaineModel> getReportImportList() {
		return reportImportList;
	}

	/**
	 * [BachelierReportBean.reportImportList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 juin 2014 10:25:06
	 * @param reportImportList
	 *            the reportImportList to set
	 */
	public void setReportImportList(List<ReportDomaineModel> reportImportList) {
		this.reportImportList = reportImportList;
	}

	/**
	 * [BachelierReportBean.header] Getter
	 * 
	 * @author MAKERRI Sofiane on : 2 aout 2014 19:18:06
	 * @return the header
	 */
	public String getHeader() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		header = sessionBean.getLlLatinEtablissement() + " - "
				+ dateFormat.format(new Date());
		return header;
	}

	/**
	 * [BachelierReportBean.header] Setter
	 * 
	 * @author MAKERRI Sofiane on : 2 aout 2014 19:18:07
	 * @param header
	 *            the header to set
	 */
	public void setHeader(String header) {
		this.header = header;
	}

	/**
	 * [BachelierReportBean.refEvenementService] Getter 
	 * @author rlaib on : 23 nov. 2014  10:37:20
	 * @return the refEvenementService
	 */
	public RefEvenementService getRefEvenementService() {
		return refEvenementService;
	}

	/**
	 * [BachelierReportBean.refEvenementService] Setter 
	 * @author rlaib on : 23 nov. 2014  10:37:20
	 * @param refEvenementService the refEvenementService to set
	 */
	public void setRefEvenementService(RefEvenementService refEvenementService) {
		this.refEvenementService = refEvenementService;
	}

	/**
	 * [BachelierReportBean.refAffectationService] Getter 
	 * @author rlaib on : 23 nov. 2014  10:37:20
	 * @return the refAffectationService
	 */
	public RefAffectationService getRefAffectationService() {
		return refAffectationService;
	}

	/**
	 * [BachelierReportBean.refAffectationService] Setter 
	 * @author rlaib on : 23 nov. 2014  10:37:20
	 * @param refAffectationService the refAffectationService to set
	 */
	public void setRefAffectationService(RefAffectationService refAffectationService) {
		this.refAffectationService = refAffectationService;
	}

	/**
	 * [BachelierReportBean.refHistoriqueService] Getter 
	 * @author rlaib on : 23 nov. 2014  10:37:20
	 * @return the refHistoriqueService
	 */
	public RefHistoriqueService getRefHistoriqueService() {
		return refHistoriqueService;
	}

	/**
	 * [BachelierReportBean.refHistoriqueService] Setter 
	 * @author rlaib on : 23 nov. 2014  10:37:20
	 * @param refHistoriqueService the refHistoriqueService to set
	 */
	public void setRefHistoriqueService(RefHistoriqueService refHistoriqueService) {
		this.refHistoriqueService = refHistoriqueService;
	}

	/**
	 * [BachelierReportBean.refIndividuService] Getter 
	 * @author rlaib on : 23 nov. 2014  10:37:20
	 * @return the refIndividuService
	 */
	public RefIndividuService getRefIndividuService() {
		return refIndividuService;
	}

	/**
	 * [BachelierReportBean.refIndividuService] Setter 
	 * @author rlaib on : 23 nov. 2014  10:37:20
	 * @param refIndividuService the refIndividuService to set
	 */
	public void setRefIndividuService(RefIndividuService refIndividuService) {
		this.refIndividuService = refIndividuService;
	}

	/**
	 * [BachelierReportBean.refFiliereLmdService] Getter 
	 * @author rlaib on : 23 nov. 2014  10:37:20
	 * @return the refFiliereLmdService
	 */
	public RefFiliereLmdService getRefFiliereLmdService() {
		return refFiliereLmdService;
	}

	/**
	 * [BachelierReportBean.refFiliereLmdService] Setter 
	 * @author rlaib on : 23 nov. 2014  10:37:20
	 * @param refFiliereLmdService the refFiliereLmdService to set
	 */
	public void setRefFiliereLmdService(RefFiliereLmdService refFiliereLmdService) {
		this.refFiliereLmdService = refFiliereLmdService;
	}

	/**
	 * [BachelierReportBean.refDomaineLMDService] Getter 
	 * @author rlaib on : 23 nov. 2014  10:37:20
	 * @return the refDomaineLMDService
	 */
	public RefDomaineLMDService getRefDomaineLMDService() {
		return refDomaineLMDService;
	}

	/**
	 * [BachelierReportBean.refDomaineLMDService] Setter 
	 * @author rlaib on : 23 nov. 2014  10:37:20
	 * @param refDomaineLMDService the refDomaineLMDService to set
	 */
	public void setRefDomaineLMDService(RefDomaineLMDService refDomaineLMDService) {
		this.refDomaineLMDService = refDomaineLMDService;
	}

	
}
