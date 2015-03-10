package dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.dossier.formation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import dz.gov.mesrs.sii.fve.business.model.dto.cursus.FormationDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.LigneReleveDeNotesDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.ReleveDeNotesDto;
import dz.gov.mesrs.sii.fve.business.service.cursus.FormationService;
import dz.gov.mesrs.sii.fve.business.service.cursus.LigneReleveDeNotesService;
import dz.gov.mesrs.sii.fve.business.service.cursus.ReleveDeNotesService;
import dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.dossier.DossierEtudiantBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.CursusConstants;
import dz.gov.mesrs.sii.fve.web.jsf.bean.utils.ReferentielHelper;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.service.NomenclatureService;


@ManagedBean(name = "formationBean")
@RequestScoped
public class FormationBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(FormationBean.class);

	private ResourceBundle formationBundle;

	private FormationDto formationDto;

	private List<FormationDto> listeFormationDto;

	@ManagedProperty("#{param.releveId}")
	private String releveId;

	@ManagedProperty("#{param.ligneId}")
	private String ligneId;

	@ManagedProperty("#{param.dossierEtudiantId}")
	private String dossierEtudiantId;

	@ManagedProperty("#{param.formationId}")
	private String formationId;

	@ManagedProperty(value = "#{referentielHelper}")
	private ReferentielHelper referentielHelper;

	@ManagedProperty(value = "#{formationService}")
	private FormationService formationService;

	@ManagedProperty(value = "#{releveDeNotesService}")
	private ReleveDeNotesService releveDeNotesService;

	@ManagedProperty(value = "#{ligneReleveDeNotesService}")
	private LigneReleveDeNotesService ligneReleveDeNotesService;

	@ManagedProperty(value = "#{nomenclatureServiceImpl}")
	private NomenclatureService nomenclatureService;
	
	/**
	 * [FormationBean.nomenclatureService] Getter 
	 * @author rlaib on : 20 nov. 2014  15:19:02
	 * @return the nomenclatureService
	 */
	public NomenclatureService getNomenclatureService() {
		return nomenclatureService;
	}

	/**
	 * [FormationBean.nomenclatureService] Setter 
	 * @author rlaib on : 20 nov. 2014  15:19:02
	 * @param nomenclatureService the nomenclatureService to set
	 */
	public void setNomenclatureService(NomenclatureService nomenclatureService) {
		this.nomenclatureService = nomenclatureService;
	}

	private List<SelectItem> listeMention;

	private List<SelectItem> listeTypeFormation;

	private List<LigneReleveDeNotesDto> listeLignesReleveNotes;

	private LigneReleveDeNotesDto ligneReleveDto;

	private ReleveDeNotesDto releveDeNotesDto;
	
	@ManagedProperty("#{dossierEtudiantBean}")
	private DossierEtudiantBean dossierEtudiantBean;

	public FormationBean() {
		super();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		formationBundle = facesContext.getApplication().getResourceBundle(
				facesContext, CursusConstants.FORMATION_BUNDLE_MSG_NAME);

	}

	@PostConstruct
	public void init() {

		loadParams();

	}

	private void loadParams() {
		if (dossierEtudiantId != null && !dossierEtudiantId.equals("null")
				&& !dossierEtudiantId.isEmpty()) {

			listeFormationDto = formationService
					.findFormationsByIdDossier(Integer
							.parseInt(dossierEtudiantId));

			this.addLibelleNomenclatureFotList();

			if (formationId != null && !formationId.equals("null")
					&& !formationId.isEmpty()) {

				formationDto = formationService.findById(Integer
						.parseInt(formationId));

			}

			if (formationDto != null) {
				releveId = formationDto.getIdReleveDeNotes() + "";
				loadLibellesNomenclature();

			} else {
				formationDto = new FormationDto();
			}

			if (releveId != null && !releveId.equals("null")
					&& !releveId.isEmpty()) {
				releveDeNotesDto = releveDeNotesService.findById(Integer
						.parseInt(releveId));
				// charger les lignes du relev� de notes
				listeLignesReleveNotes = ligneReleveDeNotesService
						.findLignesOfReleve(Integer.parseInt(releveId));

			} else {
				releveDeNotesDto = new ReleveDeNotesDto();
				listeLignesReleveNotes = null;
			}

		} else {
			formationDto = new FormationDto();
			listeLignesReleveNotes = null;
			releveDeNotesDto = null;
		}

		if (ligneId != null && !ligneId.equals("null") && !ligneId.isEmpty()) {
			ligneReleveDto = ligneReleveDeNotesService.findById(Integer
					.parseInt(ligneId));

		} else {
			ligneReleveDto = new LigneReleveDeNotesDto();
		}

	}

	/**
	 * 
	 * [FormationBean.loadLibelleNomenclature] Method
	 * 
	 * @author yselmane on : 28 mai 2014 17:22:51
	 */
	private void loadLibellesNomenclature() {

		try {
			NomenclatureDto typeform = nomenclatureService.findByCode(
					CursusConstants.NC_TYPE_FORMATION_ETUDIANT,
					formationDto.getRefCodeTypeFormation());

			formationDto.setTypeFormationLibelleLongFr(typeform
					.getLibelleLongFr());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void addLibelleNomenclatureFotList() {

		try {
			if (listeFormationDto != null && !listeFormationDto.isEmpty()) {

				for (FormationDto currentFormationDto : listeFormationDto) {

					NomenclatureDto nc = nomenclatureService.findByCode(
							CursusConstants.NC_TYPE_FORMATION_ETUDIANT,
							currentFormationDto.getRefCodeTypeFormation());

					currentFormationDto.setTypeFormationLibelleLongAr(nc
							.getLibelleLongAr());
					currentFormationDto.setTypeFormationLibelleLongFr(nc
							.getLibelleLongFr());

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * [FormationBean.listeFormationDto] Getter
	 * 
	 * @author yselmane on : 3 juin 2014 18:25:52
	 * @return the listeFormationDto
	 */
	public List<FormationDto> getListeFormationDto() {
		return listeFormationDto;
	}

	/**
	 * [FormationBean.listeFormationDto] Setter
	 * 
	 * @author yselmane on : 3 juin 2014 18:25:52
	 * @param listeFormationDto
	 *            the listeFormationDto to set
	 */
	public void setListeFormationDto(List<FormationDto> listeFormationDto) {
		this.listeFormationDto = listeFormationDto;
	}

	/**
	 * [FormationBean.formationId] Getter
	 * 
	 * @author yselmane on : 3 juin 2014 18:25:52
	 * @return the formationId
	 */
	public String getFormationId() {
		return formationId;
	}

	/**
	 * [FormationBean.formationId] Setter
	 * 
	 * @author yselmane on : 3 juin 2014 18:25:52
	 * @param formationId
	 *            the formationId to set
	 */
	public void setFormationId(String formationId) {
		this.formationId = formationId;
	}

	/**
	 * [FormationBean.formationDto] Getter
	 * 
	 * @author yselmane on : 1 juin 2014 10:59:45
	 * @return the formationDto
	 */
	public FormationDto getFormationDto() {
		return formationDto;
	}

	/**
	 * [FormationBean.formationDto] Setter
	 * 
	 * @author yselmane on : 1 juin 2014 10:59:45
	 * @param formationDto
	 *            the formationDto to set
	 */
	public void setFormationDto(FormationDto formationDto) {
		this.formationDto = formationDto;
	}

	/**
	 * [FormationBean.formationService] Getter
	 * 
	 * @author yselmane on : 1 juin 2014 10:59:45
	 * @return the formationService
	 */
	public FormationService getFormationService() {
		return formationService;
	}

	/**
	 * [FormationBean.formationService] Setter
	 * 
	 * @author yselmane on : 1 juin 2014 10:59:45
	 * @param formationService
	 *            the formationService to set
	 */
	public void setFormationService(FormationService formationService) {
		this.formationService = formationService;
	}

	/**
	 * [FormationBean.releveDeNotesService] Getter
	 * 
	 * @author yselmane on :04 juin 2014 18:12:35
	 * @return the releveDeNotesService
	 */
	public ReleveDeNotesService getReleveDeNotesService() {
		return releveDeNotesService;
	}

	/**
	 * [FormationBean.releveDeNotesService] Setter
	 * 
	 * @author yselmane on :04 juin 2014 18:12:35
	 * @param releveDeNotesService
	 *            the releveDeNotesService to set
	 */
	public void setReleveDeNotesService(
			ReleveDeNotesService releveDeNotesService) {
		this.releveDeNotesService = releveDeNotesService;
	}

	/**
	 * [FormationBean.ligneReleveDeNotesService] Getter
	 * 
	 * @author yselmane on :04 juin 2014 18:12:35
	 * @return the ligneReleveDeNotesService
	 */
	public LigneReleveDeNotesService getLigneReleveDeNotesService() {
		return ligneReleveDeNotesService;
	}

	/**
	 * [FormationBean.ligneReleveDeNotesService] Setter
	 * 
	 * @author yselmane on :04 juin 2014 18:12:35
	 * @param ligneReleveDeNotesService
	 *            the ligneReleveDeNotesService to set
	 */
	public void setLigneReleveDeNotesService(
			LigneReleveDeNotesService ligneReleveDeNotesService) {
		this.ligneReleveDeNotesService = ligneReleveDeNotesService;
	}

	/**
	 * [FormationBean.idDossierEtudiant] Getter
	 * 
	 * @author yselmane on : 27 mai 2014 09:39:48
	 * @return the idDossierEtudiant
	 */
	public String getDossierEtudiantId() {
		return dossierEtudiantId;
	}

	/**
	 * [FormationBean.idDossierEtudiant] Setter
	 * 
	 * @author yselmane on : 27 mai 2014 09:39:48
	 * @param idDossierEtudiant
	 *            the idDossierEtudiant to set
	 */
	public void setDossierEtudiantId(String dossierEtudiantId) {
		this.dossierEtudiantId = dossierEtudiantId;
	}

	/**
	 * [FormationBean.idfLigne] Getter
	 * 
	 * @author yselmane on :04 juin 2014 10:53:39
	 * @return the idfLigne
	 */
	public String getLigneId() {
		return ligneId;
	}

	/**
	 * [FormationBean.idfLigne] Setter
	 * 
	 * @author yselmane on :04 juin 2014 10:53:39
	 * @param idfLigne
	 *            the idfLigne to set
	 */
	public void setLigneId(String ligneId) {
		if ((ligneId != null) && (ligneId.equals("null"))) {
			this.ligneId = null;
		} else
			this.ligneId = ligneId;
	}

	/**
	 * [FormationBean.idfReleve] Getter
	 * 
	 * @author yselmane on :04 juin 2014 18:08:15
	 * @return the idfReleve
	 */
	public String getReleveId() {
		return releveId;
	}

	/**
	 * [FormationBean.idfReleve] Setter
	 * 
	 * @author yselmane on :04 juin 2014 18:08:15
	 * @param idfReleve
	 *            the idfReleve to set
	 */
	public void setReleveId(String releveId) {
		this.releveId = releveId;
	}

	/**
	 * [FormationBean.releveDeNotesDto] Getter
	 * 
	 * @author yselmane on :04 juin 2014 18:07:01
	 * @return the releveDeNotesDto
	 */
	public ReleveDeNotesDto getReleveDeNotesDto() {
		return releveDeNotesDto;
	}

	/**
	 * [FormationBean.releveDeNotesDto] Setter
	 * 
	 * @author yselmane on :04 juin 2014 18:07:01
	 * @param releveDeNotesDto
	 *            the releveDeNotesDto to set
	 */
	public void setReleveDeNotesDto(ReleveDeNotesDto releveDeNotesDto) {
		this.releveDeNotesDto = releveDeNotesDto;
	}

	/**
	 * [FormationBean.listeTypeFormation] Getter
	 * 
	 * @author yselmane on : 3 juin 2014 16:10:11
	 * @return the listeTypeFormation
	 */
	public List<SelectItem> getListeTypeFormation() {
		if (listeTypeFormation == null || listeTypeFormation.isEmpty()) {
			listeTypeFormation = new ArrayList<SelectItem>();
			listeTypeFormation = referentielHelper
					.getNomenclatureList(CursusConstants.NC_TYPE_FORMATION_ETUDIANT);
		}
		return listeTypeFormation;
	}

	/**
	 * [FormationBean.listeTypeFormation] Setter
	 * 
	 * @author yselmane on : 3 juin 2014 16:10:11
	 * @param listeTypeFormation
	 *            the listeTypeFormation to set
	 */
	public void setListeTypeFormation(List<SelectItem> listeTypeFormation) {
		this.listeTypeFormation = listeTypeFormation;
	}

	/**
	 * [FormationBean.listeMention] Getter
	 * 
	 * @author yselmane on : 25 mai 2014 10:23:19
	 * @return the listeMention
	 */
	public List<SelectItem> getListeMention() {
		if (listeMention == null || listeMention.isEmpty()) {
			listeMention = new ArrayList<SelectItem>();
			listeMention = referentielHelper
					.getNomenclatureList(CursusConstants.NC_MENTION);
		}
		return listeMention;
	}

	/**
	 * [FormationBean.listeMention] Setter
	 * 
	 * @author yselmane on : 25 mai 2014 10:23:19
	 * @param listeMention
	 *            the listeMention to set
	 */
	public void setListeMention(List<SelectItem> listeMention) {
		this.listeMention = listeMention;
	}

	/**
	 * [FormationBean.referentielHelper] Getter
	 * 
	 * @author yselmane on :04 juin 2014 09:18:03
	 * @return the referentielHelper
	 */
	public ReferentielHelper getReferentielHelper() {
		return referentielHelper;
	}

	/**
	 * [FormationBean.referentielHelper] Setter
	 * 
	 * @author yselmane on :04 juin 2014 09:18:03
	 * @param referentielHelper
	 *            the referentielHelper to set
	 */
	public void setReferentielHelper(ReferentielHelper referentielHelper) {
		this.referentielHelper = referentielHelper;
	}

	/**
	 * [FormationBean.listeLignesReleveNotes] Getter
	 * 
	 * @author yselmane on :04 juin 2014 10:39:43
	 * @return the listeLignesReleveNotes
	 */
	public List<LigneReleveDeNotesDto> getListeLignesReleveNotes() {
		return listeLignesReleveNotes;
	}

	/**
	 * [FormationBean.listeLignesReleveNotes] Setter
	 * 
	 * @author yselmane on :04 juin 2014 10:39:44
	 * @param listeLignesReleveNotes
	 *            the listeLignesReleveNotes to set
	 */
	public void setListeLignesReleveNotes(
			List<LigneReleveDeNotesDto> listeLignesReleveNotes) {
		this.listeLignesReleveNotes = listeLignesReleveNotes;
	}

	/**
	 * [FormationBean.ligneReleveDTo] Getter
	 * 
	 * @author yselmane on :04 juin 2014 10:52:34
	 * @return the ligneReleveDTo
	 */
	public LigneReleveDeNotesDto getLigneReleveDto() {
		return ligneReleveDto;
	}

	/**
	 * [FormationBean.ligneReleveDTo] Setter
	 * 
	 * @author yselmane on :04 juin 2014 10:52:34
	 * @param ligneReleveDTo
	 *            the ligneReleveDTo to set
	 */
	public void setLigneReleveDto(LigneReleveDeNotesDto ligneReleveDto) {
		this.ligneReleveDto = ligneReleveDto;
	}

	/**
	 * get the current Date [FormationBean.getCurrentDate] Method
	 * 
	 * @author yselmane on : 4 juin 2014 10:06:04
	 * @return
	 */
	public Date getCurrentDate() {
		return new Date();
	}

	/**
	 * Save or update titre acces Dto [FormationBean.saveFormation] Method
	 * 
	 * @author yselmane on :04 juin 2014 10:53:43
	 */
	public void saveFormation() {

		FacesMessage msg = new FacesMessage();

		try {

			if (dossierEtudiantId == null || dossierEtudiantId.equals("null")
					|| dossierEtudiantId.isEmpty()) {
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				msg.setSummary(formationBundle
						.getString("formation_error_persistence_formation"));
				FacesContext.getCurrentInstance().addMessage(null, msg);

				return;
			}

			if (formationDto.getDateFin().before(formationDto.getDateDebut())) {

				msg.setSeverity(FacesMessage.SEVERITY_WARN);
				msg.setSummary(formationBundle
						.getString("formation_warn_date_debut_after_date_fin"));
				FacesContext.getCurrentInstance().addMessage(null, msg);

				return;
			}
			

			if (formationId != null && !formationId.equals("null")
					&& !formationId.isEmpty()) {

				log.info(" ---------- update formation ----");

				// update formation

				formationDto = formationService.insertOrUpdate(formationDto);

				setFormationId(formationDto.getId() + "");

				listeFormationDto = formationService
						.findFormationsByIdDossier(Integer
								.parseInt(dossierEtudiantId));
				this.addLibelleNomenclatureFotList();

				// message succes update formation
				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(formationBundle
						.getString("formation_success_updated"));

				FacesContext.getCurrentInstance().addMessage(null, msg);

			}

			else {
				log.info(" ---------- save formation  ------ ");

				formationDto.setIdDossierEtudiant(Integer
						.valueOf(dossierEtudiantId));
				// save formation
				formationDto = formationService.insertOrUpdate(formationDto);

				setFormationId(formationDto.getId() + "");

				listeFormationDto = formationService
						.findFormationsByIdDossier(Integer
								.parseInt(dossierEtudiantId));
				this.addLibelleNomenclatureFotList();

				// message succes enregistrement nouvelle formation
				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(formationBundle
						.getString("formation_success_saved"));
				FacesContext.getCurrentInstance().addMessage(null, msg);

			}

		} catch (Exception e) {

			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(formationBundle
					.getString("formation_error_persistence_formation"));
			FacesContext.getCurrentInstance().addMessage(null, msg);

			log.info(e.getMessage());
		}
	}

	/**
	 * action d'ouverture de la boite de dialogue [FormationBean.openLigne]
	 * Method
	 * 
	 * @author yselmane on : 3 juin 2014 13:56:59
	 * @param ligneReleveDeNotesDto
	 */
	public void openLigne(LigneReleveDeNotesDto ligneReleveDeNotesDto) {

		setLigneReleveDto(ligneReleveDeNotesDto);
		setLigneId(ligneReleveDeNotesDto.getId() + "");

	}

	/**
	 * fermer la boite de dialogue d'ajout de ligne [FormationBean.closeLigne]
	 * Method
	 * 
	 * @author yselmane on : 3 juin 2014 13:56:36
	 */
	public void closeLigne() {

		setLigneReleveDto(null);
		setLigneId(null);

	}

	/**
	 * evenement lors de la mise � jour de la formation
	 * [FormationBean.openFormation] Method
	 * 
	 * @author yselmane on : 3 juin 2014 11:39:25
	 * @param selectedFormationDto
	 */
	public void openFormation(FormationDto selectedFormationDto) {

		setFormationDto(selectedFormationDto);
		setFormationId(selectedFormationDto.getId() + "");

		releveId = "" + selectedFormationDto.getIdReleveDeNotes();

		if (releveId != null && !releveId.equals("null") && !releveId.isEmpty()) {
			releveDeNotesDto = releveDeNotesService.findById(Integer
					.parseInt(releveId));
			// charger les lignes du relev� de notes
			listeLignesReleveNotes = ligneReleveDeNotesService
					.findLignesOfReleve(Integer.parseInt(releveId));

		}

	}

	/**
	 * supprime une formation de la liste des formations
	 * [FormationBean.removeFormation] Method
	 * 
	 * @author yselmane on : 3 juin 2014 11:39:02
	 * @param selectedFormationDto
	 */
	public void removeFormation(FormationDto selectedFormationDto) {

		FacesMessage msg = new FacesMessage();

		if ((selectedFormationDto != null)
				&& (selectedFormationDto.getId() > 0)) {
			try {

				// releveId =
				// selectedFormationDto.getIdReleveDeNotes().toString();

				// mettre a jour formation en supprimant id_releve_de_note
				formationService.remove(selectedFormationDto);

				if (releveId != null && !releveId.equals("null")
						&& !releveId.isEmpty()) {
					// supprimer toutes les lignes du relev� de notes
					ligneReleveDeNotesService.deleteAllLignesOfReleve(Integer
							.parseInt(releveId));
					
					listeLignesReleveNotes = null;

					// supprimer le relev� de notes puisqu'il contiendra plus de
					// lignes
					releveDeNotesDto = releveDeNotesService.findById(Integer
							.parseInt(releveId));
					releveDeNotesService.remove(releveDeNotesDto);
				}

				releveDeNotesDto = new ReleveDeNotesDto();
				setReleveId(null);

				// rafraichir la liste des formation du dossier etudiant

				listeFormationDto = formationService
						.findFormationsByIdDossier(Integer
								.parseInt(dossierEtudiantId));
				this.addLibelleNomenclatureFotList();

				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(formationBundle
						.getString("formation_success_delete_formation"));
				FacesContext.getCurrentInstance().addMessage(null, msg);

			} catch (Exception e) {

				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				msg.setSummary(formationBundle
						.getString("formation_error_delete_formation"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		}

	}

	/**
	 * supprimer une ligne du relev� de notes [ FormationBean.removeLigne]
	 * Method *
	 * 
	 * @author yselmane on :04 juin 2014 18:14:36
	 * @param ligneReleveDeNotesDto
	 */
	public void removeLigne(LigneReleveDeNotesDto ligneReleveDeNotesDto) {

		FacesMessage msg = new FacesMessage();

		if ((ligneReleveDeNotesDto != null)
				&& (ligneReleveDeNotesDto.getId() != 0)) {
			try {

				// deleting instance linge releve

				ligneReleveDeNotesService.remove(ligneReleveDeNotesDto);

				listeLignesReleveNotes = ligneReleveDeNotesService
						.findLignesOfReleve(Integer.parseInt(releveId));

				if (listeLignesReleveNotes == null
						|| listeLignesReleveNotes.isEmpty()) {
					// si aucune ligne du relev� restante , alors supprimer le
					// relev� de
					// notes
					formationDto.setIdReleveDeNotes(null);
					formationDto = formationService
							.insertOrUpdate(formationDto);
					releveDeNotesService.remove(releveDeNotesDto);
				}

				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(formationBundle
						.getString("formation_success_delete_ligne_releve"));
				FacesContext.getCurrentInstance().addMessage(null, msg);

			} catch (Exception e) {

				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				msg.setSummary(formationBundle
						.getString("formation_error_delete_ligne_releve"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		}

	}

	/**
	 * persister une ligne du relev� de notes [FormationBean.saveLigne] Method
	 * 
	 * @author yselmane on :04 juin 2014 11:19:27
	 */
	public void saveLigne() {

		FacesMessage msg = new FacesMessage();
		try {

			if (formationId == null || formationId.equals("null")
					|| formationId.isEmpty()) {
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				msg.setSummary(formationBundle
						.getString("formation_error_add_ligne_releve"));
				FacesContext.getCurrentInstance().addMessage(null, msg);

				return;
			}

			if (ligneId == null || ligneId.equals("null") || ligneId.isEmpty()) {

				// si ajout premiere ligne releve alors creer le releve + la
				// ligne
				releveDeNotesDto = releveDeNotesService
						.insertOrUpdate(releveDeNotesDto);

				formationDto.setIdReleveDeNotes(releveDeNotesDto.getId());

				formationDto = formationService.insertOrUpdate(formationDto);

				setReleveId(releveDeNotesDto.getId() + "");
				ligneReleveDto.setIdReleveDeNotes(releveDeNotesDto.getId());
				ligneReleveDto = ligneReleveDeNotesService
						.insertOrUpdate(ligneReleveDto);

				// rafraichir la liste des lignes apr�s suppression

				listeLignesReleveNotes = ligneReleveDeNotesService
						.findLignesOfReleve(Integer.parseInt(releveId));

				// message de succes de creation
				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(formationBundle
						.getString("formation_success_add_ligne_releve"));
				FacesContext.getCurrentInstance().addMessage(null, msg);

			} else {
				setReleveId(releveDeNotesDto.getId() + "");
				ligneReleveDto.setIdReleveDeNotes(releveDeNotesDto.getId());
				ligneReleveDto = ligneReleveDeNotesService
						.insertOrUpdate(ligneReleveDto);

				// rafraichir la liste des lignes apr�s suppression

				listeLignesReleveNotes = ligneReleveDeNotesService
						.findLignesOfReleve(Integer.parseInt(releveId));

				// message de succ�s d'update
				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(formationBundle
						.getString("formation_success_update_ligne_releve"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}

		} catch (Exception e) {

			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(formationBundle
					.getString("formation_error_add_ligne_releve"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

	}
	
	/**
	 * 
	 * [FormationBean.getDossierEtudiantBean] Method 
	 * @author yselmane  on : 5 juin 2014  10:30:55
	 * @return
	 */
	public DossierEtudiantBean getDossierEtudiantBean() {
		return dossierEtudiantBean;
	}

	/**
	 * 
	 * [FormationBean.setDossierEtudiantBean] Method 
	 * @author yselmane  on : 5 juin 2014  10:31:08
	 * @param dossierEtudiantBean
	 */
	public void setDossierEtudiantBean(DossierEtudiantBean dossierEtudiantBean) {
		if (dossierEtudiantBean != null && dossierEtudiantId == null) {
			setDossierEtudiantId(dossierEtudiantBean.getDossierEtudiantId());
		}
		this.dossierEtudiantBean = dossierEtudiantBean;
	}

}
