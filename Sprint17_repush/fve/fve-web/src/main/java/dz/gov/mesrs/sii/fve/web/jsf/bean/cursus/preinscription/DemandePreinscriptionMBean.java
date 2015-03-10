package dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.preinscription;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DecisionDemandePreinscriptionDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.AnneeAcademiqueDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.NiveauDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OuvertureOffreFormationDto;
import dz.gov.mesrs.sii.fve.business.service.cursus.PreinscriptionService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.AnneeAcademiqueService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.NiveauService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OuvertureOffreFormationService;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefAffectDomLmdEtabDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefDomaineLMDDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEtablissementDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefFiliereLmdDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefSpecialiteLmdDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefAffectDomLmdEtabService;
import dz.gov.mesrs.sii.referentiel.business.service.RefAffectFiliereLmdEtabService;
import dz.gov.mesrs.sii.referentiel.business.service.RefDomaineLMDService;
import dz.gov.mesrs.sii.referentiel.business.service.RefEtablissementService;
import dz.gov.mesrs.sii.referentiel.business.service.RefFiliereLmdService;
import dz.gov.mesrs.sii.referentiel.business.service.RefSpecialiteLmdService;

@ViewScoped
@ManagedBean(name = "demandePreinscriptionMBean")
public class DemandePreinscriptionMBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -5694624391741137476L;

    private String numeroDemande;

    @ManagedProperty("#{anneeAcademiqueService}")
    private AnneeAcademiqueService anneeAcademiqueService;

    @ManagedProperty("#{preinscriptionService}")
    private PreinscriptionService preinscriptionService;

    @ManagedProperty("#{refEtablissementServiceImpl}")
    private RefEtablissementService etablissementService;

    @ManagedProperty("#{refDomaineLMDServiceImpl}")
    private RefDomaineLMDService domaineService;

    @ManagedProperty("#{refAffectDomLmdEtabServiceImpl}")
    private RefAffectDomLmdEtabService affectDomLmdEtabService;

    @ManagedProperty("#{refAffectFiliereLmdEtabServiceImpl}")
    private RefAffectFiliereLmdEtabService affectFiliereLmdEtabService;

    @ManagedProperty("#{ouvertureOffreFormationService}")
    private OuvertureOffreFormationService formationService;

    @ManagedProperty("#{niveauService}")
    private NiveauService niveauService;

    @ManagedProperty("#{refFiliereLmdServiceImpl}")
    private RefFiliereLmdService refFiliereLmdService;

    @ManagedProperty("#{refSpecialiteLmdServiceImpl}")
    private RefSpecialiteLmdService specialiteLmdService;

    private List<SelectItem> anneeAcademiqueList;

    private List<SelectItem> niveauList;

    private List<SelectItem> filiereList;

    private List<SelectItem> specialiteList;

    private List<SelectItem> etablissementList;

    private List<SelectItem> domaineList;

    private List<SelectItem> cycleList;

    private Integer anneeAcademiqueId;

    private Integer niveauId;

    private Integer etablissementId;

    private Integer filiereId;

    private Integer cycleId;

    private Integer specialiteId;

    private Integer domaineId;

    private DecisionDemandePreinscriptionDto decisionPreinscriptionDto;

    @PostConstruct
    public void init() {
	// initCycleList();
	initAnneeAcademiqueList();
	initDomaineList();
	initEtablissementList();
	decisionPreinscriptionDto = new DecisionDemandePreinscriptionDto();
    }

    public void onFiliereSelected() {
	if (filiereId == null) {
	    clearCycleList();
	    clearNiveauList();
	    clearSpecialiteList();
	}
	setCycleListForFiliere();
	// setSpecialiteListForFiliereAndCycle();

    }

    public void setSpecialiteListForFiliereAndCycle() {
	if (filiereId == null) {
	    return;
	}
	OuvertureOffreFormationDto searchDto = new OuvertureOffreFormationDto();
	searchDto.setAnneeAcademiqueId(anneeAcademiqueId);
	searchDto.setEstOuverte(true);
	searchDto.setOfRefCodeDomaine(domaineService.findById(domaineId).getIdentifiant());
	searchDto.setIdEtablissement(etablissementService.findById(etablissementId).getId());
	searchDto.setIdFiliere(filiereId);
	List<OuvertureOffreFormationDto> dtos = formationService.findAdvanced(searchDto);
	if (dtos != null) {
	    specialiteList = new ArrayList<>();
	    List<String> specialiteCodes = new ArrayList<>();
	    for (OuvertureOffreFormationDto dto : dtos) {

		if (!specialiteCodes.contains(dto.getOfRefCodeSpecialite()) && dto.getCycleId().equals( cycleId)) {
		    specialiteCodes.add(dto.getOfRefCodeSpecialite());
		    RefSpecialiteLmdDto specialiteDto = specialiteLmdService.findByIdentifiant(dto
			    .getOfRefCodeSpecialite());
		    if (specialiteDto.getIdFiliere().equals(filiereId)) {
			specialiteList.add(new SelectItem(specialiteDto.getId(), specialiteDto.getLlSpecialite()));
		    }

		}
	    }
	} else {
	    specialiteList = null;
	    specialiteId = null;

	}
    }

    public void setCycleListForFiliere() {
	if (filiereId == null) {
	    return;
	}
	OuvertureOffreFormationDto searchDto = new OuvertureOffreFormationDto();
	searchDto.setAnneeAcademiqueId(anneeAcademiqueId);
	searchDto.setEstOuverte(true);
	searchDto.setOfRefCodeDomaine(domaineService.findById(domaineId).getIdentifiant());
	searchDto.setIdEtablissement(etablissementService.findById(etablissementId).getId());
	searchDto.setIdFiliere(filiereId);
	List<OuvertureOffreFormationDto> dtos = formationService.findAdvanced(searchDto);

	if (dtos != null) {
	    List<Integer> cycleIds = new ArrayList<Integer>();
	    cycleList = new ArrayList<>();
	    for (OuvertureOffreFormationDto dto : dtos) {
		if (!cycleIds.contains(dto.getCycleId())) {
		    cycleIds.add(dto.getCycleId());
		    cycleList.add(new SelectItem(dto.getCycleId(), dto.getCycleLibelleLongLt()));
		}
	    }
	} else {
	    cycleId = null;
	    cycleList = null;
	}

    }

    public void setFiliereListForDomainAndEtablissemnt() {
	if (domaineId == null) {
	    return;
	}
	OuvertureOffreFormationDto searchDto = new OuvertureOffreFormationDto();
	searchDto.setAnneeAcademiqueId(anneeAcademiqueId);
	searchDto.setEstOuverte(true);
	searchDto.setOfRefCodeDomaine(domaineService.findById(domaineId).getIdentifiant());
	searchDto.setIdEtablissement(etablissementService.findById(etablissementId).getId());
	List<OuvertureOffreFormationDto> dtos = formationService.findAdvanced(searchDto);
	if (dtos != null) {
	    filiereList = new ArrayList<>();
	    List<String> filiereCodes = new ArrayList<>();
	    for (OuvertureOffreFormationDto dto : dtos) {
		String codeFiliere = dto.getOfRefCodeFiliere();
		if (!filiereCodes.contains(codeFiliere)) {
		    RefFiliereLmdDto filiereLmdDto = refFiliereLmdService.findByIdentifiant(codeFiliere);
		    filiereList.add(new SelectItem(filiereLmdDto.getId(), filiereLmdDto.getLlFiliere()));
		    filiereCodes.add(codeFiliere);
		}

	    }
	}

    }

    public void onAnneeAcademiqueSelected() {
	clearCycleList();
	clearFiliereList();
	clearNiveauList();
	clearSpecialiteList();
	initDomaineList();
	initEtablissementList();
    }

    public void onCycleSelected() {
	if (cycleId == null) {
	    clearNiveauList();
	    clearSpecialiteList();
	} else {
	    niveauId = null;
	    setSpecialiteListForFiliereAndCycle();
	    setNiveauListForCyle();
	}

    }

    public void setNiveauListForCyle() {
	if (cycleId == null) {
	    return;
	}
	List<NiveauDto> dtos = niveauService.findByCycleId(cycleId);
	if (dtos != null) {
	    niveauList = new ArrayList<>();
	    for (NiveauDto dto : dtos) {
		niveauList.add(new SelectItem(dto.getId(), dto.getLibelleLongLt()));
	    }
	}
    }

    //
    // public void onSpecialiteSelected() {
    // if (specialiteId != null) {
    // etablissementList = new ArrayList<>();
    // // TODO trouver les etablissement qui on cette specialite
    // }
    // }

    private void clearFiliereList() {
	filiereId = null;
	filiereList = null;
    }

    private void clearNiveauList() {
	niveauId = null;
	niveauList = null;
    }

    private void clearCycleList() {
	cycleId = null;
	cycleList = null;
    }

    private void clearSpecialiteList() {
	specialiteId = null;
	specialiteList = null;
    }

    public void onEtablissementSelected() {
	if (etablissementId != null && domaineId == null) {
	    setDomainListForEtablissement();
	    clearFiliereList();
	    clearSpecialiteList();
	    clearCycleList();
	    clearNiveauList();
	    clearSpecialiteList();
	} else if (etablissementId == null) {
	    initDomaineList();
	    initEtablissementList();
	    clearFiliereList();
	    clearSpecialiteList();
	    clearCycleList();
	    clearNiveauList();
	    clearSpecialiteList();
	} else if (etablissementId != null) {
	    filiereId = null;
	    setFiliereListForDomainAndEtablissemnt();
	}
    }

    public void onDomaineSelected() {
	if (domaineId != null) {
	    setEtablissementListForDomain();
	    clearFiliereList();
	    clearCycleList();
	    clearNiveauList();
	    clearSpecialiteList();
	} else {
	    initDomaineList();
	    initEtablissementList();
	    clearFiliereList();
	    clearCycleList();
	    clearNiveauList();
	    clearSpecialiteList();
	}
    }

    private void setDomainListForEtablissement() {
	domaineId = null;
	domaineList = new ArrayList<>();
	List<RefAffectDomLmdEtabDto> dtos = affectDomLmdEtabService.findByIdEtablissement(etablissementId);
	for (RefAffectDomLmdEtabDto dto : dtos) {
	    domaineList.add(new SelectItem(dto.getIdDomaineLMD(), dto.getLlDomaine()));
	}

    }

    private void setEtablissementListForDomain() {
	etablissementId = null;
	etablissementList = new ArrayList<>();
	List<RefAffectDomLmdEtabDto> dtos = affectDomLmdEtabService.findByIdDomainelmd(domaineId);
	for (RefAffectDomLmdEtabDto dto : dtos) {
	    etablissementList.add(new SelectItem(dto.getIdEtablissement(), dto.getLlEtablissementLatin()));
	}
    }

    private void initEtablissementList() {
	etablissementList = new ArrayList<>();
	etablissementId = null;
	List<RefEtablissementDto> dtos = etablissementService.findAll();
	for (RefEtablissementDto dto : dtos) {
	    etablissementList.add(new SelectItem(dto.getId(), dto.getLlEtablissementLatin()));
	}

    }

    private void initDomaineList() {
	domaineList = new ArrayList<>();
	domaineId = null;
	List<RefDomaineLMDDto> dtos = domaineService.findAll();
	for (RefDomaineLMDDto dto : dtos) {
	    domaineList.add(new SelectItem(dto.getId(), dto.getLlDomaine()));
	}

    }

    private void initAnneeAcademiqueList() {
	anneeAcademiqueId = null;
	anneeAcademiqueList = new ArrayList<>();
	List<AnneeAcademiqueDto> dtos = anneeAcademiqueService.findAll();
	for (AnneeAcademiqueDto dto : dtos) {
	    anneeAcademiqueList.add(new SelectItem(dto.getId(), dto.getCode()));
	}

    }

    public String cancel() {
	return "toSearchPreinscription";
    }

    public String getNumeroDemande() {
	return numeroDemande;
    }

    public void setNumeroDemande(String numeroDemande) {
	this.numeroDemande = numeroDemande;
    }

    public AnneeAcademiqueService getAnneeAcademiqueService() {
	return anneeAcademiqueService;
    }

    public void setAnneeAcademiqueService(AnneeAcademiqueService anneeAcademiqueService) {
	this.anneeAcademiqueService = anneeAcademiqueService;
    }

    public PreinscriptionService getPreinscriptionService() {
	return preinscriptionService;
    }

    public void setPreinscriptionService(PreinscriptionService preinscriptionService) {
	this.preinscriptionService = preinscriptionService;
    }

    public List<SelectItem> getAnneeAcademiqueList() {
	return anneeAcademiqueList;
    }

    public void setAnneeAcademiqueList(List<SelectItem> anneeAcademiqueList) {
	this.anneeAcademiqueList = anneeAcademiqueList;
    }

    public List<SelectItem> getFiliereList() {
	return filiereList;
    }

    public void setFiliereList(List<SelectItem> filiereList) {
	this.filiereList = filiereList;
    }

    public List<SelectItem> getSpecialiteList() {
	return specialiteList;
    }

    public void setSpecialiteList(List<SelectItem> specialiteList) {
	this.specialiteList = specialiteList;
    }

    public List<SelectItem> getEtablissementList() {
	return etablissementList;
    }

    public void setEtablissementList(List<SelectItem> etablissementList) {
	this.etablissementList = etablissementList;
    }

    public Integer getAnneeAcademiqueId() {
	return anneeAcademiqueId;
    }

    public void setAnneeAcademiqueId(Integer anneeAcademiqueId) {
	this.anneeAcademiqueId = anneeAcademiqueId;
    }

    public Integer getNiveauId() {
	return niveauId;
    }

    public void setNiveauId(Integer niveauId) {
	this.niveauId = niveauId;
    }

    public Integer getEtablissementId() {
	return etablissementId;
    }

    public void setEtablissementId(Integer etablissementId) {
	this.etablissementId = etablissementId;
    }

    public DecisionDemandePreinscriptionDto getDecisionPreinscriptionDto() {
	return decisionPreinscriptionDto;
    }

    public void setDecisionPreinscriptionDto(DecisionDemandePreinscriptionDto decisionPreinscriptionDto) {
	this.decisionPreinscriptionDto = decisionPreinscriptionDto;
    }

    public Integer getFiliereId() {
	return filiereId;
    }

    public void setFiliereId(Integer filiereId) {
	this.filiereId = filiereId;
    }

    public Integer getSpecialiteId() {
	return specialiteId;
    }

    public void setSpecialiteId(Integer specialiteId) {
	this.specialiteId = specialiteId;
    }

    public List<SelectItem> getDomaineList() {
	return domaineList;
    }

    public void setDomaineList(List<SelectItem> domaineList) {
	this.domaineList = domaineList;
    }

    public Integer getDomaineId() {
	return domaineId;
    }

    public void setDomaineId(Integer domaineId) {
	this.domaineId = domaineId;
    }

    public void setEtablissementService(RefEtablissementService etablissementService) {
	this.etablissementService = etablissementService;
    }

    public RefEtablissementService getEtablissementService() {
	return etablissementService;
    }

    public void setAffectDomLmdEtabService(RefAffectDomLmdEtabService affectDomLmdEtabService) {
	this.affectDomLmdEtabService = affectDomLmdEtabService;
    }

    public RefAffectDomLmdEtabService getAffectDomLmdEtabService() {
	return affectDomLmdEtabService;
    }

    public RefDomaineLMDService getDomaineService() {
	return domaineService;
    }

    public void setDomaineService(RefDomaineLMDService domaineService) {
	this.domaineService = domaineService;
    }

    public void setAffectFiliereLmdEtabService(RefAffectFiliereLmdEtabService affectFiliereLmdEtabService) {
	this.affectFiliereLmdEtabService = affectFiliereLmdEtabService;
    }

    public RefAffectFiliereLmdEtabService getAffectFiliereLmdEtabService() {
	return affectFiliereLmdEtabService;
    }

    public void setFormationService(OuvertureOffreFormationService formationService) {
	this.formationService = formationService;
    }

    public OuvertureOffreFormationService getFormationService() {
	return formationService;
    }

    public void setRefFiliereLmdService(RefFiliereLmdService refFiliereLmdService) {
	this.refFiliereLmdService = refFiliereLmdService;
    }

    public RefFiliereLmdService getRefFiliereLmdService() {
	return refFiliereLmdService;
    }

    public List<SelectItem> getCycleList() {
	return cycleList;
    }

    public void setCycleList(List<SelectItem> cycleList) {
	this.cycleList = cycleList;
    }

    public Integer getCycleId() {
	return cycleId;
    }

    public void setCycleId(Integer cycleId) {
	this.cycleId = cycleId;
    }

    public List<SelectItem> getNiveauList() {
	return niveauList;
    }

    public NiveauService getNiveauService() {
	return niveauService;
    }

    public void setNiveauService(NiveauService niveauService) {
	this.niveauService = niveauService;
    }

    public void setNiveauList(List<SelectItem> niveauList) {
	this.niveauList = niveauList;
    }

    public void setSpecialiteLmdService(RefSpecialiteLmdService specialiteLmdService) {
	this.specialiteLmdService = specialiteLmdService;
    }

    public RefSpecialiteLmdService getSpecialiteLmdService() {
	return specialiteLmdService;
    }

}
