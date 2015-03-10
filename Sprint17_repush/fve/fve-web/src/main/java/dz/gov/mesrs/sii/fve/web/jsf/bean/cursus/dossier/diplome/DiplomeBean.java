package dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.dossier.diplome;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
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

import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DiplomeDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.LigneReleveDeNotesDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.ReleveDeNotesDto;
import dz.gov.mesrs.sii.fve.business.service.cursus.DiplomeService;
import dz.gov.mesrs.sii.fve.business.service.cursus.LigneReleveDeNotesService;
import dz.gov.mesrs.sii.fve.business.service.cursus.ReleveDeNotesService;
import dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.dossier.DossierEtudiantBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.CursusConstants;
import dz.gov.mesrs.sii.fve.web.jsf.bean.utils.ReferentielHelper;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.service.NomenclatureService;

@ManagedBean(name = "diplomeBean")
@RequestScoped
public class DiplomeBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(DiplomeBean.class);

	private ResourceBundle diplomeBundle;

	private DiplomeDto diplomeDto;

	private List<DiplomeDto> listeDiplomesDto;

	@ManagedProperty("#{param.releveId}")
	private String releveId;

	@ManagedProperty("#{param.ligneId}")
	private String ligneId;

	@ManagedProperty("#{param.dossierEtudiantId}")
	private String dossierEtudiantId;

	@ManagedProperty("#{param.diplomeId}")
	private String diplomeId;

	@ManagedProperty(value = "#{referentielHelper}")
	private ReferentielHelper referentielHelper;

	@ManagedProperty(value = "#{nomenclatureServiceImpl}")
	private NomenclatureService nomenclatureService;
	
	@ManagedProperty(value = "#{diplomeService}")
	private DiplomeService diplomeService;

	@ManagedProperty(value = "#{releveDeNotesService}")
	private ReleveDeNotesService releveDeNotesService;

	@ManagedProperty(value = "#{ligneReleveDeNotesService}")
	private LigneReleveDeNotesService ligneReleveDeNotesService;

	private List<SelectItem> listeMention;

	private List<SelectItem> listeDiplome;

	private List<LigneReleveDeNotesDto> listeLignesReleveNotes;

	private LigneReleveDeNotesDto ligneReleveDto;

	private ReleveDeNotesDto releveDeNotesDto;
	
	@ManagedProperty("#{dossierEtudiantBean}")
	private DossierEtudiantBean dossierEtudiantBean;

	public DiplomeBean() {
		super();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		diplomeBundle = facesContext.getApplication().getResourceBundle(
				facesContext, CursusConstants.DIPLOME_BUNDLE_MSG_NAME);

	}

	@PostConstruct
	public void init() {

		loadParams();

	}

	private void loadParams() {
		if (dossierEtudiantId != null && !dossierEtudiantId.equals("null")
				&& !dossierEtudiantId.isEmpty()) {

			listeDiplomesDto = diplomeService.findDiplomesByIdDossier(Integer
					.parseInt(dossierEtudiantId));

			this.addLibelleNomenclatureFotList();

			if (diplomeId != null && !diplomeId.equals("null")
					&& !diplomeId.isEmpty()) {

				diplomeDto = diplomeService.findById(Integer
						.parseInt(diplomeId));

			}

			if (diplomeDto != null) {
				releveId = diplomeDto.getIdReleveDeNotes() + "";
				loadLibellesNomenclature();

			} else {
				diplomeDto = new DiplomeDto();
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
			diplomeDto = new DiplomeDto();
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
	 * [DiplomeBean.loadLibelleNomenclature] Method
	 * 
	 * @author yselmane on : 28 mai 2014 17:22:51
	 */
	private void loadLibellesNomenclature() {

		try {
			NomenclatureDto mention = nomenclatureService.findByCode(
					CursusConstants.NC_MENTION, diplomeDto.getRefCodeMention());

			diplomeDto.setMentionLibelleLongFr(mention.getLibelleLongFr());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void addLibelleNomenclatureFotList() {

		try {
			if (listeDiplomesDto != null && !listeDiplomesDto.isEmpty()) {

				for (DiplomeDto currentDiplomeDto : listeDiplomesDto) {

					NomenclatureDto nc = nomenclatureService.findByCode(
							CursusConstants.NC_DIPLOME,
							currentDiplomeDto.getRefCodeDiplome());

					currentDiplomeDto.setDiplomeLibelleLongFr(nc
							.getLibelleLongFr());
					currentDiplomeDto.setDiplomeLibelleLongAr(nc
							.getLibelleLongAr());

					nc = nomenclatureService.findByCode(
							CursusConstants.NC_MENTION,
							currentDiplomeDto.getRefCodeMention());

					currentDiplomeDto.setMentionLibelleLongFr(nc
							.getLibelleLongFr());
					currentDiplomeDto.setMentionLibelleLongAr(nc
							.getLibelleLongAr());

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * [DiplomeBean.diplomeDto] Getter
	 * 
	 * @author yselmane on : 1 juin 2014 10:59:45
	 * @return the diplomeDto
	 */
	public DiplomeDto getDiplomeDto() {
		return diplomeDto;
	}

	/**
	 * [DiplomeBean.diplomeDto] Setter
	 * 
	 * @author yselmane on : 1 juin 2014 10:59:45
	 * @param diplomeDto
	 *            the diplomeDto to set
	 */
	public void setDiplomeDto(DiplomeDto diplomeDto) {
		this.diplomeDto = diplomeDto;
	}

	/**
	 * [DiplomeBean.listeDiplomes] Getter
	 * 
	 * @author yselmane on : 1 juin 2014 11:17:09
	 * @return the listeDiplomes
	 */
	public List<DiplomeDto> getListeDiplomesDto() {
		return listeDiplomesDto;
	}

	/**
	 * [DiplomeBean.listeDiplomes] Setter
	 * 
	 * @author yselmane on : 1 juin 2014 11:17:09
	 * @param listeDiplomes
	 *            the listeDiplomes to set
	 */
	public void setListeDiplomesDto(List<DiplomeDto> listeDiplomesDto) {
		this.listeDiplomesDto = listeDiplomesDto;
	}

	/**
	 * [DiplomeBean.diplomeService] Getter
	 * 
	 * @author yselmane on : 1 juin 2014 10:59:45
	 * @return the diplomeService
	 */
	public DiplomeService getDiplomeService() {
		return diplomeService;
	}

	/**
	 * [DiplomeBean.diplomeService] Setter
	 * 
	 * @author yselmane on : 1 juin 2014 10:59:45
	 * @param diplomeService
	 *            the diplomeService to set
	 */
	public void setDiplomeService(DiplomeService diplomeService) {
		this.diplomeService = diplomeService;
	}

	/**
	 * [DiplomeBean.releveDeNotesService] Getter
	 * 
	 * @author yselmane on : 26 mai 2014 18:12:35
	 * @return the releveDeNotesService
	 */
	public ReleveDeNotesService getReleveDeNotesService() {
		return releveDeNotesService;
	}

	/**
	 * [DiplomeBean.releveDeNotesService] Setter
	 * 
	 * @author yselmane on : 26 mai 2014 18:12:35
	 * @param releveDeNotesService
	 *            the releveDeNotesService to set
	 */
	public void setReleveDeNotesService(
			ReleveDeNotesService releveDeNotesService) {
		this.releveDeNotesService = releveDeNotesService;
	}

	/**
	 * [DiplomeBean.ligneReleveDeNotesService] Getter
	 * 
	 * @author yselmane on : 26 mai 2014 18:12:35
	 * @return the ligneReleveDeNotesService
	 */
	public LigneReleveDeNotesService getLigneReleveDeNotesService() {
		return ligneReleveDeNotesService;
	}

	/**
	 * [DiplomeBean.ligneReleveDeNotesService] Setter
	 * 
	 * @author yselmane on : 26 mai 2014 18:12:35
	 * @param ligneReleveDeNotesService
	 *            the ligneReleveDeNotesService to set
	 */
	public void setLigneReleveDeNotesService(
			LigneReleveDeNotesService ligneReleveDeNotesService) {
		this.ligneReleveDeNotesService = ligneReleveDeNotesService;
	}

	/**
	 * [DiplomeBean.idDossierEtudiant] Getter
	 * 
	 * @author yselmane on : 27 mai 2014 09:39:48
	 * @return the idDossierEtudiant
	 */
	public String getDossierEtudiantId() {
		return dossierEtudiantId;
	}

	/**
	 * [DiplomeBean.idDossierEtudiant] Setter
	 * 
	 * @author yselmane on : 27 mai 2014 09:39:48
	 * @param idDossierEtudiant
	 *            the idDossierEtudiant to set
	 */
	public void setDossierEtudiantId(String dossierEtudiantId) {
		this.dossierEtudiantId = dossierEtudiantId;
	}

	/**
	 * [DiplomeBean.diplomeId] Getter
	 * 
	 * @author yselmane on : 1 juin 2014 15:59:53
	 * @return the diplomeId
	 */
	public String getDiplomeId() {
		return diplomeId;
	}

	/**
	 * [DiplomeBean.diplomeId] Setter
	 * 
	 * @author yselmane on : 1 juin 2014 15:59:53
	 * @param diplomeId
	 *            the diplomeId to set
	 */
	public void setDiplomeId(String diplomeId) {
		this.diplomeId = diplomeId;
	}

	/**
	 * [DiplomeBean.idfLigne] Getter
	 * 
	 * @author yselmane on : 26 mai 2014 10:53:39
	 * @return the idfLigne
	 */
	public String getLigneId() {
		return ligneId;
	}

	/**
	 * [DiplomeBean.idfLigne] Setter
	 * 
	 * @author yselmane on : 26 mai 2014 10:53:39
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
	 * [DiplomeBean.idfReleve] Getter
	 * 
	 * @author yselmane on : 26 mai 2014 18:08:15
	 * @return the idfReleve
	 */
	public String getReleveId() {
		return releveId;
	}

	/**
	 * [DiplomeBean.idfReleve] Setter
	 * 
	 * @author yselmane on : 26 mai 2014 18:08:15
	 * @param idfReleve
	 *            the idfReleve to set
	 */
	public void setReleveId(String releveId) {
		this.releveId = releveId;
	}

	/**
	 * [DiplomeBean.releveDeNotesDto] Getter
	 * 
	 * @author yselmane on : 26 mai 2014 18:07:01
	 * @return the releveDeNotesDto
	 */
	public ReleveDeNotesDto getReleveDeNotesDto() {
		return releveDeNotesDto;
	}

	/**
	 * [DiplomeBean.releveDeNotesDto] Setter
	 * 
	 * @author yselmane on : 26 mai 2014 18:07:01
	 * @param releveDeNotesDto
	 *            the releveDeNotesDto to set
	 */
	public void setReleveDeNotesDto(ReleveDeNotesDto releveDeNotesDto) {
		this.releveDeNotesDto = releveDeNotesDto;
	}

	/**
	 * Save or update titre acces Dto [DiplomeBean.saveDiplome] Method
	 * 
	 * @author yselmane on : 26 mai 2014 10:53:43
	 */
	public void saveDiplome() {

		FacesMessage msg = new FacesMessage();

		try {

			if (dossierEtudiantId == null || dossierEtudiantId.equals("null")
					|| dossierEtudiantId.isEmpty()) {
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				msg.setSummary(diplomeBundle
						.getString("diplome_error_persistence_titre"));
				FacesContext.getCurrentInstance().addMessage(null, msg);

				return;
			}
			
			//verification de l'annee, ce test remplace  f:validateLongRange qui pose prob�me dans le cas de nouveau dossier etudiant
			if(diplomeDto.getAnneeObtention() > getPresentYear() ){
				msg.setSeverity(FacesMessage.SEVERITY_WARN);
				msg.setSummary(diplomeBundle
						.getString("diplome_annee_obtention_future"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
				return;
			}
			
			if(diplomeDto.getAnneeObtention() < 1900 ){
				msg.setSeverity(FacesMessage.SEVERITY_WARN);
				msg.setSummary(diplomeBundle
						.getString("diplome_annee_obtention_passe"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
				return;
			}

			if (diplomeId != null && !diplomeId.equals("null")
					&& !diplomeId.isEmpty()) {

				log.info(" ---------- update diplome ----");

				// update diplome

				diplomeDto = diplomeService.insertOrUpdate(diplomeDto);

				setDiplomeId(diplomeDto.getId() + "");

				listeDiplomesDto = diplomeService
						.findDiplomesByIdDossier(Integer
								.parseInt(dossierEtudiantId));
				this.addLibelleNomenclatureFotList();

				// message succes update diplome
				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(diplomeBundle
						.getString("diplome_success_updated"));

				FacesContext.getCurrentInstance().addMessage(null, msg);

			}

			else {
				log.info(" ---------- save diplome  ------ ");

				diplomeDto.setIdDossierEtudiant(Integer
						.valueOf(dossierEtudiantId));
				// save diplome
				diplomeDto = diplomeService.insertOrUpdate(diplomeDto);

				setDiplomeId(diplomeDto.getId() + "");

				listeDiplomesDto = diplomeService
						.findDiplomesByIdDossier(Integer
								.parseInt(dossierEtudiantId));
				this.addLibelleNomenclatureFotList();

				// message succes enregistrement nouveau diplome
				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(diplomeBundle.getString("diplome_success_saved"));
				FacesContext.getCurrentInstance().addMessage(null, msg);

			}

		} catch (Exception e) {

			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(diplomeBundle
					.getString("diplome_error_persistence_titre"));
			FacesContext.getCurrentInstance().addMessage(null, msg);

			log.info(e.getMessage());
		}
	}

	/**
	 * l'ann�e actuelle [DiplomeBean.getNowYear] Method
	 * 
	 * @author yselmane on : 28 mai 2014 17:46:16
	 * @return
	 */
	public int getPresentYear() {

		return Calendar.getInstance().get(Calendar.YEAR);
	}

	/**
	 * [DiplomeBean.listeMention] Getter
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
	 * [DiplomeBean.listeMention] Setter
	 * 
	 * @author yselmane on : 25 mai 2014 10:23:19
	 * @param listeMention
	 *            the listeMention to set
	 */
	public void setListeMention(List<SelectItem> listeMention) {
		this.listeMention = listeMention;
	}

	/**
	 * [DiplomeBean.listeDiplome] Getter
	 * 
	 * @author yselmane on : 1 juin 2014 11:28:24
	 * @return the listeDiplome
	 */
	public List<SelectItem> getListeDiplome() {
		if (listeDiplome == null || listeDiplome.isEmpty()) {
			listeDiplome = new ArrayList<SelectItem>();
			listeDiplome = referentielHelper
					.getNomenclatureList(CursusConstants.NC_DIPLOME);
		}
		return listeDiplome;
	}

	/**
	 * [DiplomeBean.listeDiplome] Setter
	 * 
	 * @author yselmane on : 1 juin 2014 11:28:24
	 * @param listeDiplome
	 *            the listeDiplome to set
	 */
	public void setListeDiplome(List<SelectItem> listeDiplome) {
		this.listeDiplome = listeDiplome;
	}

	/**
	 * [DiplomeBean.referentielHelper] Getter
	 * 
	 * @author yselmane on : 26 mai 2014 09:18:03
	 * @return the referentielHelper
	 */
	public ReferentielHelper getReferentielHelper() {
		return referentielHelper;
	}

	/**
	 * [DiplomeBean.referentielHelper] Setter
	 * 
	 * @author yselmane on : 26 mai 2014 09:18:03
	 * @param referentielHelper
	 *            the referentielHelper to set
	 */
	public void setReferentielHelper(ReferentielHelper referentielHelper) {
		this.referentielHelper = referentielHelper;
	}

	/**
	 * [DiplomeBean.listeLignesReleveNotes] Getter
	 * 
	 * @author yselmane on : 26 mai 2014 10:39:43
	 * @return the listeLignesReleveNotes
	 */
	public List<LigneReleveDeNotesDto> getListeLignesReleveNotes() {
		return listeLignesReleveNotes;
	}

	/**
	 * [DiplomeBean.listeLignesReleveNotes] Setter
	 * 
	 * @author yselmane on : 26 mai 2014 10:39:44
	 * @param listeLignesReleveNotes
	 *            the listeLignesReleveNotes to set
	 */
	public void setListeLignesReleveNotes(
			List<LigneReleveDeNotesDto> listeLignesReleveNotes) {
		this.listeLignesReleveNotes = listeLignesReleveNotes;
	}

	/**
	 * [DiplomeBean.ligneReleveDTo] Getter
	 * 
	 * @author yselmane on : 26 mai 2014 10:52:34
	 * @return the ligneReleveDTo
	 */
	public LigneReleveDeNotesDto getLigneReleveDto() {
		return ligneReleveDto;
	}

	/**
	 * [DiplomeBean.ligneReleveDTo] Setter
	 * 
	 * @author yselmane on : 26 mai 2014 10:52:34
	 * @param ligneReleveDTo
	 *            the ligneReleveDTo to set
	 */
	public void setLigneReleveDto(LigneReleveDeNotesDto ligneReleveDto) {
		this.ligneReleveDto = ligneReleveDto;
	}

	/**
	 * action d'ouverture de la boite de dialogue [DiplomeBean.openLigne] Method
	 * 
	 * @author yselmane on : 3 juin 2014 13:56:59
	 * @param ligneReleveDeNotesDto
	 */
	public void openLigne(LigneReleveDeNotesDto ligneReleveDeNotesDto) {

		setLigneReleveDto(ligneReleveDeNotesDto);
		setLigneId(ligneReleveDeNotesDto.getId() + "");

	}

	/**
	 * fermer la boite de dialogue d'ajout de ligne [DiplomeBean.closeLigne]
	 * Method
	 * 
	 * @author yselmane on : 3 juin 2014 13:56:36
	 */
	public void closeLigne() {

		setLigneReleveDto(null);
		setLigneId(null);

	}

	/**
	 * evenement lors de la mise � jour du diplome [DiplomeBean.openDiplome]
	 * Method
	 * 
	 * @author yselmane on : 3 juin 2014 11:39:25
	 * @param selectedDiplomeDto
	 */
	public void openDiplome(DiplomeDto selectedDiplomeDto) {

		setDiplomeDto(selectedDiplomeDto);
		setDiplomeId(selectedDiplomeDto.getId() + "");

		releveId = "" + selectedDiplomeDto.getIdReleveDeNotes();

		if (releveId != null && !releveId.equals("null") && !releveId.isEmpty()) {
			releveDeNotesDto = releveDeNotesService.findById(Integer
					.parseInt(releveId));
			// charger les lignes du relev� de notes
			listeLignesReleveNotes = ligneReleveDeNotesService
					.findLignesOfReleve(Integer.parseInt(releveId));

		}

	}

	/**
	 * supprime un diplome de la liste des diplomes [DiplomeBean.removeDiplome]
	 * Method
	 * 
	 * @author yselmane on : 3 juin 2014 11:39:02
	 * @param selectedDiplomeDto
	 */
	public void removeDiplome(DiplomeDto selectedDiplomeDto) {

		FacesMessage msg = new FacesMessage();

		if ((selectedDiplomeDto != null)
				&& (selectedDiplomeDto.getId() > 0)) {
			try {

				// releveId =
				// selectedDiplomeDto.getIdReleveDeNotes().toString();

				// mettre a jour diplome en supprimant id_releve_de_note
				diplomeService.remove(selectedDiplomeDto);

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

				// rafraichir la liste des diplomes du dossier etudiant

				listeDiplomesDto = diplomeService
						.findDiplomesByIdDossier(Integer
								.parseInt(dossierEtudiantId));
				this.addLibelleNomenclatureFotList();

				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(diplomeBundle
						.getString("diplome_success_delete_diplome"));
				FacesContext.getCurrentInstance().addMessage(null, msg);

			} catch (Exception e) {

				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				msg.setSummary(diplomeBundle
						.getString("diplome_error_delete_diplome"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		}

	}

	/**
	 * supprimer une ligne du relev� de notes [ DiplomeBean.removeLigne] Method
	 * *
	 * 
	 * @author yselmane on : 26 mai 2014 18:14:36
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
					diplomeDto.setIdReleveDeNotes(null);
					diplomeDto = diplomeService.insertOrUpdate(diplomeDto);
					releveDeNotesService.remove(releveDeNotesDto);
				}

				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(diplomeBundle
						.getString("diplome_success_delete_ligne_releve"));
				FacesContext.getCurrentInstance().addMessage(null, msg);

			} catch (Exception e) {

				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				msg.setSummary(diplomeBundle
						.getString("diplome_error_delete_ligne_releve"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		}

	}

	/**
	 * persister une ligne du relev� de notes [DiplomeBean.saveLigne] Method
	 * 
	 * @author yselmane on : 26 mai 2014 11:19:27
	 */
	public void saveLigne() {

		FacesMessage msg = new FacesMessage();
		try {

			if (diplomeId == null || diplomeId.equals("null")
					|| diplomeId.isEmpty()) {
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				msg.setSummary(diplomeBundle
						.getString("diplome_error_add_ligne_releve"));
				FacesContext.getCurrentInstance().addMessage(null, msg);

				return;
			}

			if (ligneId == null || ligneId.equals("null") || ligneId.isEmpty()) {

				// si ajout premiere ligne releve alors creer le releve + la
				// ligne
				releveDeNotesDto = releveDeNotesService
						.insertOrUpdate(releveDeNotesDto);

				diplomeDto.setIdReleveDeNotes(releveDeNotesDto.getId());

				diplomeDto = diplomeService.insertOrUpdate(diplomeDto);

				setReleveId(releveDeNotesDto.getId() + "");
				ligneReleveDto.setIdReleveDeNotes(releveDeNotesDto.getId());
				ligneReleveDto = ligneReleveDeNotesService
						.insertOrUpdate(ligneReleveDto);

				// rafraichir la liste des lignes apr�s suppression

				listeLignesReleveNotes = ligneReleveDeNotesService
						.findLignesOfReleve(Integer.parseInt(releveId));

				// message de succes de creation
				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(diplomeBundle
						.getString("diplome_success_add_ligne_releve"));
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
				msg.setSummary(diplomeBundle
						.getString("diplome_success_update_ligne_releve"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}

		} catch (Exception e) {

			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(diplomeBundle
					.getString("diplome_error_add_ligne_releve"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

	}
	
	/**
	 * 
	 * [DiplomeBean.getDossierEtudiantBean] Method 
	 * @author yselmane  on : 5 juin 2014  10:30:24
	 * @return
	 */
	public DossierEtudiantBean getDossierEtudiantBean() {
		return dossierEtudiantBean;
	}

	/**
	 * 
	 * [DiplomeBean.setDossierEtudiantBean] Method 
	 * @author yselmane  on : 5 juin 2014  10:30:28
	 * @param dossierEtudiantBean
	 */
	public void setDossierEtudiantBean(DossierEtudiantBean dossierEtudiantBean) {
		if (dossierEtudiantBean != null && dossierEtudiantId == null) {
			setDossierEtudiantId(dossierEtudiantBean.getDossierEtudiantId());
		}
		this.dossierEtudiantBean = dossierEtudiantBean;
	}

}
