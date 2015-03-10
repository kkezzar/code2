/**
 * [dz.gov.mesrs.sii.fve.web.jsf.bean.bac.BacBB.java] 
 * @author  Rafik LAIB on : 21 mai 2014  11:18:57
 */
package dz.gov.mesrs.sii.fve.web.jsf.bean.bac;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;

import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;

import dz.gov.mesrs.sii.fve.business.model.dto.bac.CapaciteEtabDto;
import dz.gov.mesrs.sii.fve.business.model.dto.bac.NoteAccessFiliereDto;
import dz.gov.mesrs.sii.fve.business.model.dto.bac.PrioriteSerieBacDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.AnneeAcademiqueDto;
import dz.gov.mesrs.sii.fve.business.service.bac.CapaciteEtabService;
import dz.gov.mesrs.sii.fve.business.service.bac.PrioriteSerieBacService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.AnneeAcademiqueService;
import dz.gov.mesrs.sii.fve.web.jsf.bean.SessionBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.CursusConstants;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefDomaineLMDDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefFiliereLmdDto;
import dz.gov.mesrs.sii.referentiel.business.service.NomenclatureService;
import dz.gov.mesrs.sii.referentiel.business.service.RefDomaineLMDService;
import dz.gov.mesrs.sii.referentiel.business.service.RefFiliereLmdService;

/**
 * @author Mounir.MESSAOUDI on : 14 ao�t 2014 15:07:27
 */
@ManagedBean(name = "capaciteEtablissementMBean")
@ViewScoped
public class CapaciteEtablissementMBean {

	@ManagedProperty(value = "#{capaciteEtabService}")
	private CapaciteEtabService capaciteEtabService;

	@ManagedProperty(value = "#{prioriteSerieBacService}")
	private PrioriteSerieBacService prioriteSerieBacService;

	@ManagedProperty(value = "#{anneeAcademiqueService}")
	private AnneeAcademiqueService anneeAcademiqueService;

	@ManagedProperty(value = "#{sessionBeanFve}")
	private SessionBean sessionBean;

	@ManagedProperty(value = "#{refDomaineLMDServiceImpl}")
	private RefDomaineLMDService refDomaineLMDService;

	@ManagedProperty(value = "#{refFiliereLmdServiceImpl}")
	private RefFiliereLmdService refFiliereLmdService;

	@ManagedProperty(value = "#{nomenclatureServiceImpl}")
	private NomenclatureService nomenclatureService;
	
	
		/**
	 * [CapaciteEtablissementMBean.refFiliereLmdService] Getter 
	 * @author rlaib on : 20 nov. 2014  14:46:18
	 * @return the refFiliereLmdService
	 */
	public RefFiliereLmdService getRefFiliereLmdService() {
		return refFiliereLmdService;
	}

	/**
	 * [CapaciteEtablissementMBean.refFiliereLmdService] Setter 
	 * @author rlaib on : 20 nov. 2014  14:46:18
	 * @param refFiliereLmdService the refFiliereLmdService to set
	 */
	public void setRefFiliereLmdService(RefFiliereLmdService refFiliereLmdService) {
		this.refFiliereLmdService = refFiliereLmdService;
	}

		/**
	 * [CapaciteEtablissementMBean.nomenclatureService] Getter 
	 * @author rlaib on : 20 nov. 2014  14:45:19
	 * @return the nomenclatureService
	 */
	public NomenclatureService getNomenclatureService() {
		return nomenclatureService;
	}

	/**
	 * [CapaciteEtablissementMBean.nomenclatureService] Setter 
	 * @author rlaib on : 20 nov. 2014  14:45:19
	 * @param nomenclatureService the nomenclatureService to set
	 */
	public void setNomenclatureService(NomenclatureService nomenclatureService) {
		this.nomenclatureService = nomenclatureService;
	}

		/**
	 * [CapaciteEtablissementMBean.refDomaineLMDService] Getter 
	 * @author rlaib on : 20 nov. 2014  14:44:35
	 * @return the refDomaineLMDService
	 */
	public RefDomaineLMDService getRefDomaineLMDService() {
		return refDomaineLMDService;
	}

	/**
	 * [CapaciteEtablissementMBean.refDomaineLMDService] Setter 
	 * @author rlaib on : 20 nov. 2014  14:44:35
	 * @param refDomaineLMDService the refDomaineLMDService to set
	 */
	public void setRefDomaineLMDService(RefDomaineLMDService refDomaineLMDService) {
		this.refDomaineLMDService = refDomaineLMDService;
	}

		/**
	 * Resultat de la recheche
	 */
	private List<CapaciteEtabDto> capaciteEtabList;

	/**
	 * Capactite d'etablissement en cours de traitement
	 */
	private CapaciteEtabDto capaciteEtabDto;

	/**
	 * Priorite serie de bac en cours de traitement
	 */
	private PrioriteSerieBacDto prioriteSerieBacDto;

	// UI
	private String refCodeCurrEtablissement;
	private Integer idCurrAnneeAcademique;
	private String refCodeCurrAnneeAcademique;
	private Integer idAnneeAcademique;
	private List<SelectItem> listAnneeAcademique;
	private List<SelectItem> listDomaines;
	private List<SelectItem> listFilieres;
	private List<SelectItem> listSeriesBac;
	private boolean isCrud;
	private boolean isView;
	private ResourceBundle bundleCommon;
	private CapaciteEtabDto searchDto;

	/**
	 * Constructeur
	 * 
	 * @author Mounir.MESSAOUDI on : 14 ao�t 2014 15:02:39
	 */
	public CapaciteEtablissementMBean() {

	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 20 ao�t 2014 11:15:36
	 */
	@PostConstruct
	public void init() {
		try {

			FacesContext facesContext = FacesContext.getCurrentInstance();
			bundleCommon = facesContext.getApplication().getResourceBundle(
					facesContext, CursusConstants.COMMON_BUNDLE_MSG_NAME);

			this.isCrud = false;

			this.refCodeCurrEtablissement = sessionBean.getCodeEtablissement();
			this.idCurrAnneeAcademique = sessionBean.getIdAnneeAcademique();
			this.refCodeCurrAnneeAcademique = sessionBean
					.getCodeAnneeAcademique();

			this.idAnneeAcademique = this.idCurrAnneeAcademique;

			searchDto = new CapaciteEtabDto();
			searchDto.setIdAnneeAcademique(this.idAnneeAcademique);
			searchDto.setRefCodeEtablissement(this.refCodeCurrEtablissement);

			this.resetCapaciteEtabDto();
			this.resetPrioriteSerieBacDto();

			this.loadSearchResult();

			// preparer la liste des domaines
			listDomaines = new ArrayList<SelectItem>();

			// recuperer la liste des domaines affectes a l'etablissement en
			// cours
			// TODO a remplacer par le code en bas
			List<RefDomaineLMDDto> listDomainesDto = refDomaineLMDService
					.findAdvanced(new RefDomaineLMDDto());
			for (RefDomaineLMDDto ld : listDomainesDto) {
				SelectItem itemFr = new SelectItem(ld.getIdentifiant(),
						ld.getLlDomaine() + " (" + ld.getIdentifiant() + ")");
				listDomaines.add(itemFr);
			}

			// List<RefAffectDomLmdEtabDto> listDomainesDto = ppmWSClient
			// .getAllDomainesLMDByCodeEtablissement(this.refCodeCurrEtablissement);
			// for (RefAffectDomLmdEtabDto ld : listDomainesDto) {
			// SelectItem itemFr = new SelectItem(
			// ld.getIdentifiantDomaineLMD(), ld.getLlDomaine() + " ("
			// + ld.getIdentifiantDomaineLMD() + ")");
			// listDomaines.add(itemFr);
			// }

			// preparer la liste des series de bac
			listSeriesBac = new ArrayList<SelectItem>();

			List<NomenclatureDto> ListSeriesBacDto = nomenclatureService
					.findByName(CursusConstants.NC_SERIES_BAC);
			for (NomenclatureDto nomenclatureDto : ListSeriesBacDto) {
				SelectItem itemFr = new SelectItem(nomenclatureDto.getCode(),
						nomenclatureDto.getLibelleLongFr() + " ("
								+ nomenclatureDto.getCode() + ")");
				listSeriesBac.add(itemFr);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 31 ao�t 2014 09:42:05
	 */
	public void handleAnneeAcademiqueChange() {
		this.isView = false;
		loadSearchResult();
	}

	/**
	 * Charger les resultats de la recherche
	 * 
	 * @author Mounir.MESSAOUDI on : 20 ao�t 2014 10:20:12
	 */
	public void loadSearchResult() {
		try {

			// liste des access wilayas
			this.capaciteEtabList = capaciteEtabService.findAdvanced(searchDto);
			for (CapaciteEtabDto capedto : capaciteEtabList) {
				// RefFiliereLmdDto refFiliereLmdDto =
				// referentielHelper.findFiliere(accessWilayaDto.getRefCodeFiliere());
				if (capedto.getRefCodeFiliere() != null) {
					RefFiliereLmdDto refFiliereLmdDto = refFiliereLmdService
							.findByIdentifiant(capedto
									.getRefCodeFiliere());
					if (refFiliereLmdDto != null) {
						capedto.setFiliereLibelleLt(refFiliereLmdDto
								.getLlFiliere());
						capedto.setFiliereLibelleAr(refFiliereLmdDto
								.getLlDomaineArabe());
					}
				}

				// RefDomaineLMDDto refDomaineLMDDto =
				// referentielHelper.findDomaine(accessWilayaDto.getRefCodeDomaine());
				RefDomaineLMDDto refDomaineLMDDto = refDomaineLMDService
						.findByIdentifiant(capedto
								.getRefCodeDomaine());
				if (refDomaineLMDDto != null) {
					capedto.setDomaineLibelleLt(refDomaineLMDDto.getLlDomaine());
					capedto.setDomaineLibelleAr(refDomaineLMDDto
							.getLlDomaineArabe());
				}
			}

			// preparer la liste des annees academique
			this.listAnneeAcademique = new ArrayList<SelectItem>();
			List<AnneeAcademiqueDto> liste = anneeAcademiqueService.findAll();
			if (liste != null && !liste.isEmpty()) {
				for (AnneeAcademiqueDto anneeAcademiqueDto : liste) {
					SelectItem si = new SelectItem(
							/* anneeAcademiqueDto.getPremiereAnnee() */anneeAcademiqueDto
									.getId(), anneeAcademiqueDto
									.getPremiereAnnee()
									+ "/"
									+ anneeAcademiqueDto.getDeuxiemeAnnee());
					this.listAnneeAcademique.add(si);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 20 ao�t 2014 09:38:07
	 * @return
	 */
	public AnneeAcademiqueService getAnneeAcademiqueService() {
		return anneeAcademiqueService;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 20 ao�t 2014 09:38:10
	 * @param anneeAcademiqueService
	 */
	public void setAnneeAcademiqueService(
			AnneeAcademiqueService anneeAcademiqueService) {
		this.anneeAcademiqueService = anneeAcademiqueService;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 20 ao�t 2014 09:38:13
	 * @return
	 */
	public SessionBean getSessionBean() {
		return sessionBean;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 20 ao�t 2014 09:38:15
	 * @param sessionBean
	 */
	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 20 ao�t 2014 09:38:27
	 * @return
	 */
	public String getRefCodeCurrEtablissement() {
		return refCodeCurrEtablissement;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 20 ao�t 2014 09:38:31
	 * @param refCodeCurrEtablissement
	 */
	public void setRefCodeCurrEtablissement(String refCodeCurrEtablissement) {
		this.refCodeCurrEtablissement = refCodeCurrEtablissement;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 20 ao�t 2014 09:38:34
	 * @return
	 */
	public Integer getIdCurrAnneeAcademique() {
		return idCurrAnneeAcademique;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 20 ao�t 2014 09:38:37
	 * @param idCurrAnneeAcademique
	 */
	public void setIdCurrAnneeAcademique(Integer idCurrAnneeAcademique) {
		this.idCurrAnneeAcademique = idCurrAnneeAcademique;
	}

	public Integer getIdAnneeAcademique() {
		return idAnneeAcademique;
	}

	public void setIdAnneeAcademique(Integer idAnneeAcademique) {
		this.idAnneeAcademique = idAnneeAcademique;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 20 ao�t 2014 09:38:40
	 * @return
	 */
	public String getRefCodeCurrAnneeAcademique() {
		return refCodeCurrAnneeAcademique;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 20 ao�t 2014 09:38:43
	 * @param refCodeCurrAnneeAcademique
	 */
	public void setRefCodeCurrAnneeAcademique(String refCodeCurrAnneeAcademique) {
		this.refCodeCurrAnneeAcademique = refCodeCurrAnneeAcademique;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 20 ao�t 2014 09:38:46
	 * @return
	 */
	public List<SelectItem> getListAnneeAcademique() {
		return listAnneeAcademique;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 20 ao�t 2014 09:38:49
	 * @param listAnneeAcademique
	 */
	public void setListAnneeAcademique(List<SelectItem> listAnneeAcademique) {
		this.listAnneeAcademique = listAnneeAcademique;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 20 ao�t 2014 09:38:52
	 * @return
	 */
	public List<SelectItem> getListDomaines() {
		return listDomaines;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 20 ao�t 2014 09:38:56
	 * @param listDomaines
	 */
	public void setListDomaines(List<SelectItem> listDomaines) {
		this.listDomaines = listDomaines;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 20 ao�t 2014 09:39:01
	 * @return
	 */
	public List<SelectItem> getListFilieres() {
		return listFilieres;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 20 ao�t 2014 09:39:08
	 * @param listFilieres
	 */
	public void setListFilieres(List<SelectItem> listFilieres) {
		this.listFilieres = listFilieres;
	}

	public List<SelectItem> getListSeriesBac() {
		return listSeriesBac;
	}

	public void setListSeriesBac(List<SelectItem> listSeriesBac) {
		this.listSeriesBac = listSeriesBac;
	}

	/**
	 * Handle changement du domaine
	 * 
	 * @author Mounir.MESSAOUDI on : 17 ao�t 2014 15:14:20
	 * @param event
	 * @throws Exception_Exception
	 */
	public void handleDomainesListChange(AjaxBehaviorEvent event)
			throws Exception {

		if (this.listDomaines != null) {
			listFilieres = new ArrayList<SelectItem>();

			List<RefFiliereLmdDto> listFilieresDto = refFiliereLmdService
					.findByCodeDomainelmd(capaciteEtabDto
							.getRefCodeDomaine());

			for (RefFiliereLmdDto ld : listFilieresDto) {
				SelectItem itemFr = new SelectItem(ld.getIdentifiant(),
						ld.getLlFiliere() + " (" + ld.getIdentifiant() + ")");
				listFilieres.add(itemFr);

			}
		}
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 17 ao�t 2014 09:28:25
	 * @return
	 */
	public CapaciteEtabService getCapaciteEtabService() {
		return capaciteEtabService;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 17 ao�t 2014 09:28:28
	 * @param capaciteAccueilService
	 */
	public void setCapaciteEtabService(CapaciteEtabService capaciteEtabService) {
		this.capaciteEtabService = capaciteEtabService;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 17 ao�t 2014 09:28:31
	 * @return
	 */
	public PrioriteSerieBacService getPrioriteSerieBacService() {
		return prioriteSerieBacService;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 17 ao�t 2014 09:28:35
	 * @param prioriteSerieBacService
	 */
	public void setPrioriteSerieBacService(
			PrioriteSerieBacService prioriteSerieBacService) {
		this.prioriteSerieBacService = prioriteSerieBacService;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 17 ao�t 2014 11:01:34
	 * @return
	 */
	public List<CapaciteEtabDto> getCapaciteEtabList() {
		return capaciteEtabList;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 17 ao�t 2014 11:02:02
	 * @param capaciteEtabList
	 */
	public void setCapaciteEtabList(List<CapaciteEtabDto> capaciteEtabList) {
		this.capaciteEtabList = capaciteEtabList;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 17 ao�t 2014 11:02:05
	 * @return
	 */
	public CapaciteEtabDto getCapaciteEtabDto() {
		return capaciteEtabDto;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 17 ao�t 2014 11:02:10
	 * @param capaciteEtab
	 */
	public void setCapaciteEtabDto(CapaciteEtabDto capaciteEtabDto) {
		this.capaciteEtabDto = capaciteEtabDto;
	}

	/**
	 * [DossierInscriptionBB.onRowSelect] Method
	 * 
	 * @author MAKERRI Sofiane on : 14 juil. 2014 13:04:27
	 * @param event
	 * @throws Exception
	 */
	public void onRowSelect(SelectEvent event) throws Exception {
		this.capaciteEtabDto = (CapaciteEtabDto) event.getObject();
		List<PrioriteSerieBacDto> l = this.capaciteEtabDto
				.getPrioriteSerieBacDto();
		// libelles des series de bac
		for (PrioriteSerieBacDto prioriteSerieBacDto : l) {
			NomenclatureDto n = nomenclatureService.findByCode(
					CursusConstants.NC_SERIES_BAC,
					prioriteSerieBacDto.getRefCodeSerieBac());
			prioriteSerieBacDto.setSerieBacLibelleLt(n.getLibelleLongFr());
			prioriteSerieBacDto.setSerieBacLibelleAr(n.getLibelleLongAr());

			// libelles des codes matieres
			List<NoteAccessFiliereDto> s = prioriteSerieBacDto
					.getNoteAccessFilieresDto();
			for (NoteAccessFiliereDto noteAccessFiliereDto : s) {
				NomenclatureDto w = nomenclatureService.findByCode(
						CursusConstants.NC_MATIERES_BAC,
						noteAccessFiliereDto.getRefCodeMatiere());
				noteAccessFiliereDto.setMatiereLibelleLt(w.getLibelleLongFr());
			}

		}
		handleDomainesListChange(null);
		this.editCapaciteEtabAction();
	}

	/**
	 * Renvoi true si une action CRUD est declanche, false sinon
	 * 
	 * @author Mounir.MESSAOUDI on : 20 ao�t 2014 09:39:14
	 * @return
	 */
	public boolean isCrud() {
		return isCrud;
	}

	public boolean isView() {
		return isView;
	}

	public void setView(boolean isView) {
		this.isView = isView;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 20 ao�t 2014 09:39:42
	 * @param isCrud
	 */
	public void setCrud(boolean isCrud) {
		this.isCrud = isCrud;
	}

	// -- priorite serie de bac
	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 20 ao�t 2014 12:01:41
	 */
	private void resetPrioriteSerieBacDto() {
		this.prioriteSerieBacDto = null;
		this.prioriteSerieBacDto = new PrioriteSerieBacDto();

		List<NoteAccessFiliereDto> noteAccessFilieresDtos = new ArrayList<NoteAccessFiliereDto>();
		try {
			List<NomenclatureDto> listMatieresBac = nomenclatureService
					.findByName(CursusConstants.NC_MATIERES_BAC);
			for (NomenclatureDto nomenclatureDto : listMatieresBac) {
				NoteAccessFiliereDto d = new NoteAccessFiliereDto();
				d.setRefCodeMatiere(nomenclatureDto.getCode());
				d.setMatiereLibelleLt(nomenclatureDto.getLibelleLongFr());
				noteAccessFilieresDtos.add(d);
			}
		} catch (Exception e) {

		}

		this.prioriteSerieBacDto
				.setNoteAccessFilieresDto(noteAccessFilieresDtos);

	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 20 ao�t 2014 11:12:48
	 * @param event
	 */
	public void onRowNoteAccesEdit(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Priorit� modif�e",
				((NoteAccessFiliereDto) event.getObject())
						.getMatiereLibelleLt());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 20 ao�t 2014 11:12:52
	 * @param event
	 */
	public void onRowNoteAccesCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Modification annul�",
				((NoteAccessFiliereDto) event.getObject())
						.getMatiereLibelleLt());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 20 ao�t 2014 12:03:58
	 */
	public void addPrioriteSerieBacAction() {
		this.resetPrioriteSerieBacDto();
	}

	public void editPrioriteSerieBacAction(
			PrioriteSerieBacDto prioriteSerieBacDto) {
		this.prioriteSerieBacDto = prioriteSerieBacDto;
	}

	/**
	 * Supprimer une priorite de serie bac
	 * @author Mounir.MESSAOUDI on : 17 sept. 2014 09:12:09 
	 * @param prioriteSerieBacDto
	 */
	public void deletePrioriteSerieBacAction(PrioriteSerieBacDto prioriteSerieBacDto) {
		FacesMessage msg = new FacesMessage();
		
		List<PrioriteSerieBacDto> listPrioriteSerieBacDtos = capaciteEtabDto
				.getPrioriteSerieBacDto();
		if (listPrioriteSerieBacDtos == null)
			listPrioriteSerieBacDtos = new ArrayList<PrioriteSerieBacDto>();
		
		int index = listPrioriteSerieBacDtos.indexOf(prioriteSerieBacDto);
		if (index != -1) {
			listPrioriteSerieBacDtos.remove(index);
//			
//			msg.setSeverity(FacesMessage.SEVERITY_INFO);
//			msg.setSummary(bundleCommon.getString("msg_success") + ": "
//					+ bundleCommon.getString("msg_success_suppression"));
//			FacesContext.getCurrentInstance().addMessage(null, msg);
			
		}
	}
	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 20 ao�t 2014 12:04:02
	 */
	public void savePrioriteSerieBacAction() {
		List<PrioriteSerieBacDto> listPrioriteSerieBacDtos = capaciteEtabDto
				.getPrioriteSerieBacDto();
		if (listPrioriteSerieBacDtos == null)
			listPrioriteSerieBacDtos = new ArrayList<PrioriteSerieBacDto>();

		int index = listPrioriteSerieBacDtos.indexOf(this.prioriteSerieBacDto);
		if (index != -1) {
			listPrioriteSerieBacDtos.set(index, this.prioriteSerieBacDto);
		} else {
			NomenclatureDto n;
			try {
				n = nomenclatureService.findByCode(
						CursusConstants.NC_SERIES_BAC,
						prioriteSerieBacDto.getRefCodeSerieBac());
				prioriteSerieBacDto.setSerieBacLibelleLt(n.getLibelleLongFr());
			} catch (Exception e) {
				e.printStackTrace();
			}
			listPrioriteSerieBacDtos.add(this.prioriteSerieBacDto);
		}

		capaciteEtabDto.setPrioriteSerieBacDto(listPrioriteSerieBacDtos);

		this.resetPrioriteSerieBacDto();

	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 20 ao�t 2014 12:04:06
	 */
	public void cancelPrioriteSerieBacAction() {
		this.prioriteSerieBacDto = null;
		// this.resetCapaciteEtabDto();
	}

	// -- capcite d'etablissement

	/**
	 * Reinitialiser le dto accessWilaya
	 * 
	 * @author Mounir.MESSAOUDI on : 19 ao�t 2014 09:47:47
	 */
	private void resetCapaciteEtabDto() {
		this.capaciteEtabDto = null;
		this.capaciteEtabDto = new CapaciteEtabDto();
		this.capaciteEtabDto.setIdAnneeAcademique(this
				.getIdCurrAnneeAcademique());
		this.capaciteEtabDto.setRefCodeEtablissement(this
				.getRefCodeCurrEtablissement());
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 19 ao�t 2014 09:48:18
	 */
	public void addCapaciteEtabAction() {
		this.resetCapaciteEtabDto();
		this.isCrud = true;

	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 19 ao�t 2014 09:48:16
	 */
	public void editCapaciteEtabAction() {
		this.isCrud = true;
		this.isView = true;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 19 ao�t 2014 09:48:12
	 */
	public void cancelCapaciteEtabAction() {
		this.resetCapaciteEtabDto();
		this.isCrud = false;
		this.isView = false;
	}

	/**
	 * Ajouter/Modifier une capacite d'etablissement
	 * 
	 * @author Mounir.MESSAOUDI on : 17 ao�t 2014 12:17:05
	 */
	public void saveCapciteEtab() {
		FacesMessage msg = new FacesMessage();
		try {
			capaciteEtabService.insertOrUpdate(this.capaciteEtabDto);

			this.resetCapaciteEtabDto();
			this.loadSearchResult();

			msg.setSeverity(FacesMessage.SEVERITY_INFO);
			msg.setSummary(bundleCommon.getString("msg_success") + ": "
					+ bundleCommon.getString("msg_success_enregistrement"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (Exception e) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundleCommon.getString("error_echec") + ": "
					+ bundleCommon.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		this.isCrud = false;

	}

	/**
	 * Supprimer une capacite d'etablissement
	 * 
	 * @author Mounir.MESSAOUDI on : 17 ao�t 2014 12:17:29
	 */
	public void deleteCapciteEtab() {
		FacesMessage msg = new FacesMessage();
		try {
			capaciteEtabService.remove(this.capaciteEtabDto);

			this.resetCapaciteEtabDto();
			this.loadSearchResult();
			msg.setSeverity(FacesMessage.SEVERITY_INFO);
			msg.setSummary(bundleCommon.getString("msg_success") + ": "
					+ bundleCommon.getString("msg_success_suppression"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (Exception e) {
			e.printStackTrace();
			
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundleCommon.getString("error_echec") + ": "
					+ bundleCommon.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		this.isCrud = false;
	}

	/**
	 * [CapaciteEtablissementMBean.prioriteSerieBacDto] Getter 
	 * @author Rafik on : 27 nov. 2014  13:50:46
	 * @return the prioriteSerieBacDto
	 */
	public PrioriteSerieBacDto getPrioriteSerieBacDto() {
		return prioriteSerieBacDto;
	}

	/**
	 * [CapaciteEtablissementMBean.prioriteSerieBacDto] Setter 
	 * @author Rafik on : 27 nov. 2014  13:50:46
	 * @param prioriteSerieBacDto the prioriteSerieBacDto to set
	 */
	public void setPrioriteSerieBacDto(PrioriteSerieBacDto prioriteSerieBacDto) {
		this.prioriteSerieBacDto = prioriteSerieBacDto;
	}

	
}
