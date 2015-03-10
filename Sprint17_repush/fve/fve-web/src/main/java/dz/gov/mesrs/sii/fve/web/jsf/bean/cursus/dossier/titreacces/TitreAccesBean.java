package dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.dossier.titreacces;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import dz.gov.mesrs.sii.fve.business.model.dto.cursus.LigneReleveDeNotesDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.ReleveDeNotesDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.TitreAccesDto;
import dz.gov.mesrs.sii.fve.business.service.cursus.LigneReleveDeNotesService;
import dz.gov.mesrs.sii.fve.business.service.cursus.ReleveDeNotesService;
import dz.gov.mesrs.sii.fve.business.service.cursus.TitreAccesService;
import dz.gov.mesrs.sii.fve.web.jsf.bean.UtilBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.dossier.DossierEtudiantBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.CursusConstants;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;


@ManagedBean(name = "titreAccesBean")
@ViewScoped
public class TitreAccesBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Log log = LogFactory.getLog(TitreAccesBean.class);

    private static ResourceBundle titreAccesBundle;

    private TitreAccesDto titreAccesDto;

    private String releveId;

    private String ligneId;

    private String dossierEtudiantId;

    @ManagedProperty(value = "#{utilBean}")
    private UtilBean utilBean;

    @ManagedProperty(value = "#{titreAccesService}")
    private TitreAccesService titreAccesService;

    @ManagedProperty(value = "#{releveDeNotesService}")
    private ReleveDeNotesService releveDeNotesService;

    @ManagedProperty(value = "#{ligneReleveDeNotesService}")
    private LigneReleveDeNotesService ligneReleveDeNotesService;

    private List<SelectItem> listeTypeTitreAcces;
    private List<SelectItem> listeLangueEtrangere1;
    private List<SelectItem> listeLangueEtrangere2;
    private List<SelectItem> listeMention;

    private List<LigneReleveDeNotesDto> listeLignesReleveNotes;

    private LigneReleveDeNotesDto ligneReleveDto;

    private ReleveDeNotesDto releveDeNotesDto;

    @ManagedProperty("#{dossierEtudiantBean}")
    private DossierEtudiantBean dossierEtudiantBean;

    // private int titreAccesId;

    public TitreAccesBean() {
	super();
	FacesContext facesContext = FacesContext.getCurrentInstance();
	titreAccesBundle = facesContext.getApplication().getResourceBundle(facesContext,
		CursusConstants.TITRE_ACCES_BUNDLE_MSG_NAME);

    }

    @PostConstruct
    public void init() {
	loadParams();

    }

    private void loadParams() {
	if (dossierEtudiantId != null && !dossierEtudiantId.equals("null") && !dossierEtudiantId.isEmpty()) {

	    titreAccesDto = titreAccesService.findByIdDossier(Integer.parseInt(dossierEtudiantId));

	    if (titreAccesDto != null) {
		releveId = titreAccesDto.getIdReleveDeNotes() + "";

		loadLibellesNomenclature();

	    } else {
		titreAccesDto = new TitreAccesDto();
	    }

	    if (releveId != null && !releveId.equals("null") && !releveId.isEmpty()) {
		releveDeNotesDto = releveDeNotesService.findById(Integer.parseInt(releveId));
		// charger les lignes du relev� de notes
		listeLignesReleveNotes = ligneReleveDeNotesService.findLignesOfReleve(Integer.parseInt(releveId));

	    } else {
		releveDeNotesDto = new ReleveDeNotesDto();
		listeLignesReleveNotes = null;
	    }

	} else {
	    titreAccesDto = new TitreAccesDto();
	    listeLignesReleveNotes = null;
	    releveDeNotesDto = null;
	}

	if (ligneId != null && !ligneId.equals("null") && !ligneId.isEmpty()) {
	    ligneReleveDto = ligneReleveDeNotesService.findById(Integer.parseInt(ligneId));

	} else {
	    ligneReleveDto = new LigneReleveDeNotesDto();
	}

    }

    /**
     * 
     * [TitreAccesBean.loadLibelleNomenclature] Method
     * 
     * @author yselmane on : 28 mai 2014 17:22:51
     */
    public void loadLibellesNomenclature() {

	try {
	    NomenclatureDto mention = utilBean.getNomenclatureItemValue(CursusConstants.NC_MENTION,
		    titreAccesDto.getRefCodeMention());
	    NomenclatureDto langue1 = utilBean.getNomenclatureItemValue(CursusConstants.NC_LANGUE_NAME,
		    titreAccesDto.getRefCodeLangueEtrangere1());
	    NomenclatureDto langue2 = utilBean.getNomenclatureItemValue(CursusConstants.NC_LANGUE_NAME,
		    titreAccesDto.getRefCodeLangueEtrangere2());
	    NomenclatureDto typeTitre = utilBean.getNomenclatureItemValue(CursusConstants.NC_TYPE_TITRE_ACCES,
		    titreAccesDto.getRefCodeTypeTitre());

	    titreAccesDto.setLangueEtrangere1LibelleLongFr(langue1.getLibelleLongFr());
	    titreAccesDto.setLangueEtrangere2LibelleLongFr(langue2.getLibelleLongFr());
	    titreAccesDto.setMentionLibelleLongFr(mention.getLibelleLongFr());
	    titreAccesDto.setTypeTitreLibelleLongFr(typeTitre.getLibelleLongFr());

	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    /**
     * [TitreAccesBean.titreAccesDto] Getter
     * 
     * @author yselmane on : 22 mai 2014 12:01:25
     * @return the titreAccesDto
     */
    public TitreAccesDto getTitreAccesDto() {
	return titreAccesDto;
    }

    /**
     * [TitreAccesBean.titreAccesDto] Setter
     * 
     * @author yselmane on : 22 mai 2014 12:01:25
     * @param titreAccesDto
     *            the titreAccesDto to set
     */
    public void setTitreAccesDto(TitreAccesDto titreAccesDto) {
	this.titreAccesDto = titreAccesDto;
    }

    /**
     * [TitreAccesBean.titreAccesService] Getter
     * 
     * @author yselmane on : 22 mai 2014 12:16:29
     * @return the titreAccesService
     */
    public TitreAccesService getTitreAccesService() {
	return titreAccesService;
    }

    /**
     * [TitreAccesBean.titreAccesService] Setter
     * 
     * @author yselmane on : 22 mai 2014 12:16:29
     * @param titreAccesService
     *            the titreAccesService to set
     */
    public void setTitreAccesService(TitreAccesService titreAccesService) {
	this.titreAccesService = titreAccesService;
    }

    /**
     * [TitreAccesBean.releveDeNotesService] Getter
     * 
     * @author yselmane on : 26 mai 2014 18:12:35
     * @return the releveDeNotesService
     */
    public ReleveDeNotesService getReleveDeNotesService() {
	return releveDeNotesService;
    }

    /**
     * [TitreAccesBean.releveDeNotesService] Setter
     * 
     * @author yselmane on : 26 mai 2014 18:12:35
     * @param releveDeNotesService
     *            the releveDeNotesService to set
     */
    public void setReleveDeNotesService(ReleveDeNotesService releveDeNotesService) {
	this.releveDeNotesService = releveDeNotesService;
    }

    /**
     * [TitreAccesBean.ligneReleveDeNotesService] Getter
     * 
     * @author yselmane on : 26 mai 2014 18:12:35
     * @return the ligneReleveDeNotesService
     */
    public LigneReleveDeNotesService getLigneReleveDeNotesService() {
	return ligneReleveDeNotesService;
    }

    /**
     * [TitreAccesBean.ligneReleveDeNotesService] Setter
     * 
     * @author yselmane on : 26 mai 2014 18:12:35
     * @param ligneReleveDeNotesService
     *            the ligneReleveDeNotesService to set
     */
    public void setLigneReleveDeNotesService(LigneReleveDeNotesService ligneReleveDeNotesService) {
	this.ligneReleveDeNotesService = ligneReleveDeNotesService;
    }

    /**
     * [TitreAccesBean.idDossierEtudiant] Getter
     * 
     * @author yselmane on : 27 mai 2014 09:39:48
     * @return the idDossierEtudiant
     */
    public String getDossierEtudiantId() {
	return dossierEtudiantId;
    }

    /**
     * [TitreAccesBean.idDossierEtudiant] Setter
     * 
     * @author yselmane on : 27 mai 2014 09:39:48
     * @param idDossierEtudiant
     *            the idDossierEtudiant to set
     */
    public void setDossierEtudiantId(String dossierEtudiantId) {
	if ((dossierEtudiantId != null) && (dossierEtudiantId.equals("null"))) {
	    this.dossierEtudiantId = null;
	} else {
	    this.dossierEtudiantId = dossierEtudiantId;
	}
    }

    /**
     * [TitreAccesBean.idfLigne] Getter
     * 
     * @author yselmane on : 26 mai 2014 10:53:39
     * @return the idfLigne
     */
    public String getLigneId() {
	return ligneId;
    }

    /**
     * [TitreAccesBean.idfLigne] Setter
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
     * [TitreAccesBean.idfReleve] Getter
     * 
     * @author yselmane on : 26 mai 2014 18:08:15
     * @return the idfReleve
     */
    public String getReleveId() {
	return releveId;
    }

    /**
     * [TitreAccesBean.idfReleve] Setter
     * 
     * @author yselmane on : 26 mai 2014 18:08:15
     * @param idfReleve
     *            the idfReleve to set
     */
    public void setReleveId(String releveId) {
	this.releveId = releveId;
    }

    /**
     * [TitreAccesBean.releveDeNotesDto] Getter
     * 
     * @author yselmane on : 26 mai 2014 18:07:01
     * @return the releveDeNotesDto
     */
    public ReleveDeNotesDto getReleveDeNotesDto() {
	return releveDeNotesDto;
    }

    /**
     * [TitreAccesBean.releveDeNotesDto] Setter
     * 
     * @author yselmane on : 26 mai 2014 18:07:01
     * @param releveDeNotesDto
     *            the releveDeNotesDto to set
     */
    public void setReleveDeNotesDto(ReleveDeNotesDto releveDeNotesDto) {
	this.releveDeNotesDto = releveDeNotesDto;
    }

    /**
     * [TitreAccesBean.listeTypeTitreAcces] Getter
     * 
     * @author yselmane on : 25 mai 2014 10:12:20
     * @return the listeTypeTitreAcces
     */
    public List<SelectItem> getListeTypeTitreAcces() {
	if (listeTypeTitreAcces == null || listeTypeTitreAcces.isEmpty()) {
	    listeTypeTitreAcces = new ArrayList<SelectItem>();
	    listeTypeTitreAcces = utilBean.loadNomenclatureCodeItem(CursusConstants.NC_TYPE_TITRE_ACCES);
	}

	return listeTypeTitreAcces;
    }

    /**
     * [TitreAccesBean.listeTypeTitreAcces] Setter
     * 
     * @author yselmane on : 25 mai 2014 10:12:20
     * @param listeTypeTitreAcces
     *            the listeTypeTitreAcces to set
     */
    public void setListeTypeTitreAcces(List<SelectItem> listeTypeTitreAcces) {
	this.listeTypeTitreAcces = listeTypeTitreAcces;
    }

    /**
     * [TitreAccesBean.listeLangueEtrangere1] Getter
     * 
     * @author yselmane on : 25 mai 2014 10:23:19
     * @return the listeLangueEtrangere1
     */
    public List<SelectItem> getListeLangueEtrangere1() {
	if (listeLangueEtrangere1 == null || listeLangueEtrangere1.isEmpty()) {
	    listeLangueEtrangere1 = new ArrayList<SelectItem>();
	    listeLangueEtrangere1 = utilBean.loadNomenclatureCodeItem(CursusConstants.NC_LANGUE_NAME);
	}
	return listeLangueEtrangere1;
    }

    /**
     * l'ann�e actuelle [TitreAccesBean.getNowYear] Method
     * 
     * @author yselmane on : 28 mai 2014 17:46:16
     * @return
     */
    public int getPresentYear() {

	return Calendar.getInstance().get(Calendar.YEAR);
    }

    /**
     * [TitreAccesBean.listeLangueEtrangere1] Setter
     * 
     * @author yselmane on : 25 mai 2014 10:23:19
     * @param listeLangueEtrangere1
     *            the listeLangueEtrangere1 to set
     */
    public void setListeLangueEtrangere1(List<SelectItem> listeLangueEtrangere1) {
	this.listeLangueEtrangere1 = listeLangueEtrangere1;
    }

    /**
     * [TitreAccesBean.listeLangueEtrangere2] Getter
     * 
     * @author yselmane on : 25 mai 2014 10:23:19
     * @return the listeLangueEtrangere2
     */
    public List<SelectItem> getListeLangueEtrangere2() {
	if (listeLangueEtrangere2 == null || listeLangueEtrangere2.isEmpty()) {
	    listeLangueEtrangere2 = new ArrayList<SelectItem>();
	    listeLangueEtrangere2 = utilBean.loadNomenclatureCodeItem(CursusConstants.NC_LANGUE_NAME);
	}
	return listeLangueEtrangere2;
    }

    /**
     * [TitreAccesBean.listeLangueEtrangere2] Setter
     * 
     * @author yselmane on : 25 mai 2014 10:23:19
     * @param listeLangueEtrangere2
     *            the listeLangueEtrangere2 to set
     */
    public void setListeLangueEtrangere2(List<SelectItem> listeLangueEtrangere2) {
	this.listeLangueEtrangere2 = listeLangueEtrangere2;
    }

    /**
     * [TitreAccesBean.listeMention] Getter
     * 
     * @author yselmane on : 25 mai 2014 10:23:19
     * @return the listeMention
     */
    public List<SelectItem> getListeMention() {
	if (listeMention == null || listeMention.isEmpty()) {
	    listeMention = new ArrayList<SelectItem>();
	    listeMention = utilBean.loadNomenclatureCodeItem(CursusConstants.NC_MENTION);
	}
	return listeMention;
    }

    /**
     * [TitreAccesBean.listeMention] Setter
     * 
     * @author yselmane on : 25 mai 2014 10:23:19
     * @param listeMention
     *            the listeMention to set
     */
    public void setListeMention(List<SelectItem> listeMention) {
	this.listeMention = listeMention;
    }

 

    /**
     * [TitreAccesBean.listeLignesReleveNotes] Getter
     * 
     * @author yselmane on : 26 mai 2014 10:39:43
     * @return the listeLignesReleveNotes
     */
    public List<LigneReleveDeNotesDto> getListeLignesReleveNotes() {
	return listeLignesReleveNotes;
    }

    /**
     * [TitreAccesBean.listeLignesReleveNotes] Setter
     * 
     * @author yselmane on : 26 mai 2014 10:39:44
     * @param listeLignesReleveNotes
     *            the listeLignesReleveNotes to set
     */
    public void setListeLignesReleveNotes(List<LigneReleveDeNotesDto> listeLignesReleveNotes) {
	this.listeLignesReleveNotes = listeLignesReleveNotes;
    }

    /**
     * [TitreAccesBean.ligneReleveDTo] Getter
     * 
     * @author yselmane on : 26 mai 2014 10:52:34
     * @return the ligneReleveDTo
     */
    public LigneReleveDeNotesDto getLigneReleveDto() {
	return ligneReleveDto;
    }

    /**
     * [TitreAccesBean.ligneReleveDTo] Setter
     * 
     * @author yselmane on : 26 mai 2014 10:52:34
     * @param ligneReleveDTo
     *            the ligneReleveDTo to set
     */
    public void setLigneReleveDto(LigneReleveDeNotesDto ligneReleveDto) {
	this.ligneReleveDto = ligneReleveDto;
    }

    /**
     * Save or update titre acces Dto [TitreAccesBean.saveTitre] Method
     * 
     * @author yselmane on : 26 mai 2014 10:53:43
     */
    public TitreAccesDto saveTitre() {

	FacesMessage msg = new FacesMessage();
	if (dossierEtudiantId == null || dossierEtudiantId.equals("null") || dossierEtudiantId.isEmpty()) {

	    msg.setSeverity(FacesMessage.SEVERITY_ERROR);
	    msg.setSummary(titreAccesBundle.getString("titre_acces_error_persistence_titre"));
	    FacesContext.getCurrentInstance().addMessage(null, msg);
	    return null;

	}

	if (titreAccesDto.getRefCodeLangueEtrangere1() != null && titreAccesDto.getRefCodeLangueEtrangere2() != null
		&& titreAccesDto.getRefCodeLangueEtrangere1().equals(titreAccesDto.getRefCodeLangueEtrangere2())) {
	    msg.setSeverity(FacesMessage.SEVERITY_WARN);
	    msg.setSummary(titreAccesBundle.getString("titre_acces_error_langue_etrangere_identique"));
	    FacesContext.getCurrentInstance().addMessage(null, msg);
	    return null;
	}

	// verification de l'annee, ce test remplace f:validateLongRange qui
	// pose prob�me dans le cas de nouveau dossier etudiant
	if (titreAccesDto.getAnneeObtention() > getPresentYear()) {
	    msg.setSeverity(FacesMessage.SEVERITY_WARN);
	    msg.setSummary(titreAccesBundle.getString("titre_acces_annee_obtention_future"));
	    FacesContext.getCurrentInstance().addMessage(null, msg);
	    return null;
	}

	if (titreAccesDto.getAnneeObtention() < 1900) {
	    msg.setSeverity(FacesMessage.SEVERITY_WARN);
	    msg.setSummary(titreAccesBundle.getString("titre_acces_annee_obtention_passe"));
	    FacesContext.getCurrentInstance().addMessage(null, msg);
	    return null;
	}

	try {

	    if (titreAccesDto.getId() > 0) {

		log.info(" ---------- update titre d'acc�s ----");

		// update titre acces

		titreAccesDto = titreAccesService.insertOrUpdate(titreAccesDto);

		msg.setSeverity(FacesMessage.SEVERITY_INFO);
		msg.setSummary(titreAccesBundle.getString("titre_acces_success_updated"));

		FacesContext.getCurrentInstance().addMessage(null, msg);
		return titreAccesDto;

	    }

	    else {
		log.info(" ---------- save titre acc�s  ------ ");
		titreAccesDto.setIdDossierEtudiant(Integer.valueOf(dossierEtudiantId));
		// save titre acces
		titreAccesDto = titreAccesService.insertOrUpdate(titreAccesDto);

		msg.setSeverity(FacesMessage.SEVERITY_INFO);
		msg.setSummary(titreAccesBundle.getString("titre_acces_success_saved"));
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return titreAccesDto;

	    }

	} catch (Exception e) {

	    msg.setSeverity(FacesMessage.SEVERITY_ERROR);
	    msg.setSummary(titreAccesBundle.getString("titre_acces_error_persistence_titre"));
	    FacesContext.getCurrentInstance().addMessage(null, msg);

	    log.info(e.getMessage());
	    return null;
	}
    }

    public void open(LigneReleveDeNotesDto ligneReleveDeNotesDto) {

	setLigneReleveDto(ligneReleveDeNotesDto);
	setLigneId(ligneReleveDeNotesDto.getId() + "");

    }

    public void close() {

	setLigneReleveDto(null);
	setLigneId(null);

    }

    /**
     * supprimer une ligne du relev� de notes [TitreAccesBean.removeLigne]
     * Method
     * 
     * @author yselmane on : 26 mai 2014 18:14:36
     * @param ligneReleveDeNotesDto
     */
    public void removeLigne(LigneReleveDeNotesDto ligneReleveDeNotesDto) {

	FacesMessage msg = new FacesMessage();

	if ((ligneReleveDeNotesDto != null) && (ligneReleveDeNotesDto.getId() != 0)) {
	    try {

		// deleting instance document and its mots cles

		ligneReleveDeNotesService.remove(ligneReleveDeNotesDto);

		listeLignesReleveNotes = ligneReleveDeNotesService.findLignesOfReleve(Integer.parseInt(releveId));

		if (listeLignesReleveNotes == null || listeLignesReleveNotes.isEmpty()) {
		    // si aucune ligne du relev� , alors supprimer le relev� de
		    // notes
		    titreAccesDto.setIdReleveDeNotes(null);
		    titreAccesDto = titreAccesService.insertOrUpdate(titreAccesDto);
		    releveDeNotesService.remove(releveDeNotesDto);
		}

		msg.setSeverity(FacesMessage.SEVERITY_INFO);
		msg.setSummary(titreAccesBundle.getString("titre_acces_success_delete_ligne_releve"));
		FacesContext.getCurrentInstance().addMessage(null, msg);

	    } catch (Exception e) {

		msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		msg.setSummary(titreAccesBundle.getString("titre_acces_error_delete_ligne_releve"));
		FacesContext.getCurrentInstance().addMessage(null, msg);
	    }
	}

    }

    /**
     * persister une ligne du relev� de notes [TitreAccesBean.saveLigne] Method
     * 
     * @author yselmane on : 26 mai 2014 11:19:27
     */
    public void saveLigne() {

	FacesMessage msg = new FacesMessage();

	try {

	    if (ligneId == null || ligneId.equals("null") || ligneId.isEmpty()) {

		releveDeNotesDto = releveDeNotesService.insertOrUpdate(releveDeNotesDto);

		titreAccesDto.setIdReleveDeNotes(releveDeNotesDto.getId());

		titreAccesDto = titreAccesService.insertOrUpdate(titreAccesDto);

		setReleveId(releveDeNotesDto.getId() + "");
		ligneReleveDto.setIdReleveDeNotes(releveDeNotesDto.getId());
		ligneReleveDto = ligneReleveDeNotesService.insertOrUpdate(ligneReleveDto);

		// rafraichir la liste des lignes apr�s suppression

		listeLignesReleveNotes = ligneReleveDeNotesService.findLignesOfReleve(Integer.parseInt(releveId));

		msg.setSeverity(FacesMessage.SEVERITY_INFO);
		msg.setSummary(titreAccesBundle.getString("titre_acces_success_add_ligne_releve"));
		FacesContext.getCurrentInstance().addMessage(null, msg);
	    } else {

		setReleveId(releveDeNotesDto.getId() + "");
		ligneReleveDto.setIdReleveDeNotes(releveDeNotesDto.getId());
		ligneReleveDto = ligneReleveDeNotesService.insertOrUpdate(ligneReleveDto);

		// rafraichir la liste des lignes apr�s suppression

		listeLignesReleveNotes = ligneReleveDeNotesService.findLignesOfReleve(Integer.parseInt(releveId));

		msg.setSeverity(FacesMessage.SEVERITY_INFO);
		msg.setSummary(titreAccesBundle.getString("titre_acces_success_update_ligne_releve"));
		FacesContext.getCurrentInstance().addMessage(null, msg);
	    }

	} catch (Exception e) {
	    msg.setSeverity(FacesMessage.SEVERITY_ERROR);
	    msg.setSummary(titreAccesBundle.getString("titre_acces_error_add_ligne_releve"));
	    FacesContext.getCurrentInstance().addMessage(null, msg);
	}

    }

    /**
     * 
     * [TitreAccesBean.getDossierEtudiantBean] Method
     * 
     * @author yselmane on : 5 juin 2014 10:29:57
     * @return
     */
    public DossierEtudiantBean getDossierEtudiantBean() {
	return dossierEtudiantBean;
    }

    /**
     * 
     * [TitreAccesBean.setDossierEtudiantBean] Method
     * 
     * @author yselmane on : 5 juin 2014 10:30:03
     * @param dossierEtudiantBean
     */
    public void setDossierEtudiantBean(DossierEtudiantBean dossierEtudiantBean) {
	if (dossierEtudiantBean != null && dossierEtudiantId == null) {
	    setDossierEtudiantId(dossierEtudiantBean.getDossierEtudiantId());
	}
	this.dossierEtudiantBean = dossierEtudiantBean;
    }

	/**
	 * [TitreAccesBean.utilBean] Getter 
	 * @author MAKERRI Sofiane on : 9 déc. 2014  14:06:47
	 * @return the utilBean
	 */
	public UtilBean getUtilBean() {
		return utilBean;
	}

	/**
	 * [TitreAccesBean.utilBean] Setter 
	 * @author MAKERRI Sofiane on : 9 déc. 2014  14:06:47
	 * @param utilBean the utilBean to set
	 */
	public void setUtilBean(UtilBean utilBean) {
		this.utilBean = utilBean;
	}




}
