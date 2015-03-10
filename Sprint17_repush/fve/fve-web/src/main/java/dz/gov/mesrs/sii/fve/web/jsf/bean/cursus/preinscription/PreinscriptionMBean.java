package dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.preinscription;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import org.primefaces.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DecisionDemandePreinscriptionDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DemandePreinscriptionDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.TitreAccesDto;
import dz.gov.mesrs.sii.fve.business.service.cursus.PreinscriptionService;
import dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.dossier.IndividuBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.dossier.titreacces.TitreAccesBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.utils.Utility;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefIndividuDto;

@ManagedBean(name = "preinscriptionMBean")
@RequestScoped
// TODO mettre en ViewScope quand les bean individu et titre d'acces le seront
// aussi
public class PreinscriptionMBean implements Serializable {

	private final static Logger logger = LoggerFactory
			.getLogger(PreinscriptionMBean.class.getName());

	private static final long serialVersionUID = -7288872911111660452L;

	@ManagedProperty("#{preinscriptionService}")
	private PreinscriptionService preinscriptionService;

	@ManagedProperty("#{demandePreinscriptionMBean}")
	private DemandePreinscriptionMBean demandePreinscriptionMBean;

	@ManagedProperty("#{titreAccesBean}")
	private TitreAccesBean titreAccesBean;

	@ManagedProperty("#{individuBean}")
	private IndividuBean individuBean;

	private boolean demandeTabDisabled = true;

	private boolean titreAccesTabDisabled = true;

	private boolean inscriptionGenerable;

	private boolean decisionEditable;

	@ManagedProperty("#{param.numeroDemande}")
	private Long numeroDemande;

	@ManagedProperty("#{flash}")
	private Flash flash;

	private DemandePreinscriptionDto dto;

	public PreinscriptionMBean() {
		super();
		Map<String, Object> viewMap = FacesContext.getCurrentInstance()
				.getViewRoot().getViewMap();
		viewMap.put("preinscriptionMode", Boolean.TRUE);
	}

	@PostConstruct
	public void init() {
		load();
	}

	private void load() {

		if (numeroDemande != null) {
			enableTitreAccesTab();
			dto = preinscriptionService.find(numeroDemande);
			demandePreinscriptionMBean.setNumeroDemande(String
					.valueOf(numeroDemande));
			demandePreinscriptionMBean.setAnneeAcademiqueId(dto
					.getAnneeAcademiqueId());
			demandePreinscriptionMBean.setEtablissementId(dto
					.getEtablissementId());
			demandePreinscriptionMBean.setDomaineId(dto.getDomaineId());
			demandePreinscriptionMBean.setFiliereId(dto.getFiliereId());
			demandePreinscriptionMBean.setNiveauId(dto.getNiveauId());
			demandePreinscriptionMBean.setCycleId(dto.getCycleId());
			demandePreinscriptionMBean.setSpecialiteId(dto.getSpecialiteId());
			demandePreinscriptionMBean.setNiveauId(dto.getNiveauId());
			demandePreinscriptionMBean.setFiliereListForDomainAndEtablissemnt();
			demandePreinscriptionMBean.setCycleListForFiliere();
			demandePreinscriptionMBean.setSpecialiteListForFiliereAndCycle();
			demandePreinscriptionMBean.setNiveauListForCyle();
			if (dto.getTitreAccesId() != null) {
				enableDemandeTab();
			}
			demandePreinscriptionMBean.setEtablissementId(dto
					.getEtablissementId());
			if (dto.getDecisionDto() != null) {
				demandePreinscriptionMBean.setDecisionPreinscriptionDto(dto
						.getDecisionDto());
			}
			if (dto.getDomaineId() != null) {
				decisionEditable = true;
			}
			if (dto.getDecisionDto() != null
					&& dto.getDecisionDto().getAcceptee() == Boolean.TRUE
					&& (!(Boolean.TRUE == dto.getGeneree()))) {
				inscriptionGenerable = true;
			}
			putDemandePreinscriptionDtoInViewScope(dto);
		}

	}

	public void saveDemandeur() {
		RefIndividuDto result = individuBean.saveIndividu();
		DemandePreinscriptionDto demandePreinscriptionDto = getDemandePreinscriptionDtoFromViewScope();
		if (demandePreinscriptionDto.getIndividuId() == null && result != null
				&& result.getId() != null) {
			demandePreinscriptionDto.setIndividuId(result.getId());
			try {
				demandePreinscriptionDto = preinscriptionService
						.save(demandePreinscriptionDto);
			} catch (Exception e) {
				Utility.showErrorMessage("Une erreur inconnue est survenue");
				throw e;
			}

		}
		putDemandePreinscriptionDtoInViewScope(demandePreinscriptionDto);
		enableTitreAccesTab();

	}

	public void saveTitreAcces() {
		TitreAccesDto result = titreAccesBean.saveTitre();
		DemandePreinscriptionDto demandePreinscriptionDto = getDemandePreinscriptionDtoFromViewScope();
		if (demandePreinscriptionDto.getTitreAccesId() == null
				&& result != null) {
			demandePreinscriptionDto.setTitreAccesId(result.getId());
			try {
				demandePreinscriptionDto = preinscriptionService
						.save(demandePreinscriptionDto);
			} catch (Exception e) {
				Utility.showErrorMessage("Une erreur inconnue est survenue");
				throw e;
			}

		}
		putDemandePreinscriptionDtoInViewScope(demandePreinscriptionDto);
		if (result != null) {
			enableDemandeTab();
		}

	}

	public void saveDemande() {
		DemandePreinscriptionDto demandePreinscriptionDto = getDemandePreinscriptionDtoFromViewScope();
		demandePreinscriptionDto
				.setAnneeAcademiqueId(demandePreinscriptionMBean
						.getAnneeAcademiqueId());
		demandePreinscriptionDto.setNiveauId(demandePreinscriptionMBean
				.getNiveauId());
		demandePreinscriptionDto.setFiliereId(demandePreinscriptionMBean
				.getFiliereId());
		demandePreinscriptionDto.setSpecialiteId(demandePreinscriptionMBean
				.getSpecialiteId());
		demandePreinscriptionDto.setDomaineId(demandePreinscriptionMBean
				.getDomaineId());
		demandePreinscriptionDto.setEtablissementId(demandePreinscriptionMBean
				.getEtablissementId());
		demandePreinscriptionDto.setCycleId(demandePreinscriptionMBean
				.getCycleId());
		try {
			preinscriptionService.save(demandePreinscriptionDto);
			decisionEditable = true;
		} catch (Exception e) {
			Utility.showErrorMessage("Une erreur inconnue est survenue");
			throw e;
		}
		Utility.showSuccessMessage("Demande enregistr�e avec succ�s");

	}

	public void saveDecision() {
		DemandePreinscriptionDto demandePreinscriptionDto = getDemandePreinscriptionDtoFromViewScope();
		DecisionDemandePreinscriptionDto deicisionDto = demandePreinscriptionMBean
				.getDecisionPreinscriptionDto();
		demandePreinscriptionDto.setDecisionDto(deicisionDto);
		try {
			preinscriptionService.save(demandePreinscriptionDto);
		} catch (Exception e) {
			Utility.showErrorMessage("Une erreur inconnue est survenue");
			throw e;
		}

		if (true == deicisionDto.getAcceptee()) {
			inscriptionGenerable = true;
		}
		Utility.showSuccessMessage("D�cision enregistr�e avec succ�s");

	}

	public String genererInscription() throws IOException {
		DemandePreinscriptionDto demandePreinscriptionDto = getDemandePreinscriptionDtoFromViewScope();
		if (demandePreinscriptionDto.getDecisionDto() == null) {
			Utility.showWarningMessage("Vous devez enregister une d�cision positive");
			return null;
		} else if (Boolean.FALSE == demandePreinscriptionDto.getDecisionDto()
				.getAcceptee()) {
			Utility.showWarningMessage("Vous devez accepter la pr�inscritpion");
			return null;
		}
		preinscriptionService.genereDossierEtudiant(demandePreinscriptionDto);
		inscriptionGenerable = false;
		Utility.showSuccessMessage("Inscription g�n�r�");
		flash.setKeepMessages(true);
		return "PreinscriptionSearch?faces-redirect=true";

	}

	public String cancel() {
		return "toSearchPreinscription";
	}

	private void putDemandePreinscriptionDtoInViewScope(
			DemandePreinscriptionDto demandePreinscriptionDto) {
		Map<String, Object> viewMap = FacesContext.getCurrentInstance()
				.getViewRoot().getViewMap();
		viewMap.put("demandePreinscriptionDto", demandePreinscriptionDto);
		this.dto = demandePreinscriptionDto;
	}

	private DemandePreinscriptionDto getDemandePreinscriptionDtoFromViewScope() {
		Map<String, Object> viewMap = FacesContext.getCurrentInstance()
				.getViewRoot().getViewMap();
		DemandePreinscriptionDto dtoInSession = (DemandePreinscriptionDto) viewMap
				.get("demandePreinscriptionDto");
		if (dtoInSession == null) {
			dtoInSession = new DemandePreinscriptionDto();
		}
		this.dto = dtoInSession;
		return dtoInSession;
	}

	private void enableTitreAccesTab() {
		RequestContext.getCurrentInstance().execute(
				"preinscriptionTabView.enable(1)");
	}

	private void enableDemandeTab() {
		RequestContext.getCurrentInstance().execute(
				"preinscriptionTabView.enable(2)");
	}

	public boolean getDemandeTabDisabled() {
		return demandeTabDisabled;
	}

	public void setDemandeTabDisabled(boolean demandeTabDisabled) {
		this.demandeTabDisabled = demandeTabDisabled;
	}

	public boolean getTitreAccesTabDisabled() {
		return titreAccesTabDisabled;
	}

	public void setTitreAccesTabDisabled(boolean titreAccesTabDisabled) {
		this.titreAccesTabDisabled = titreAccesTabDisabled;
	}

	public void setPreinscriptionService(
			PreinscriptionService preinscriptionService) {
		this.preinscriptionService = preinscriptionService;
	}

	public PreinscriptionService getPreinscriptionService() {
		return preinscriptionService;
	}

	public void setTitreAccesBean(TitreAccesBean titreAccesBean) {
		this.titreAccesBean = titreAccesBean;
	}

	public TitreAccesBean getTitreAccesBean() {
		return titreAccesBean;
	}

	public void setIndividuBean(IndividuBean individuBean) {
		this.individuBean = individuBean;
	}

	public IndividuBean getIndividuBean() {
		return individuBean;
	}

	public DemandePreinscriptionDto getDto() {
		return dto;
	}

	public void setDto(DemandePreinscriptionDto dto) {
		this.dto = dto;
	}

	public DemandePreinscriptionMBean getDemandePreinscriptionMBean() {
		return demandePreinscriptionMBean;
	}

	public void setDemandePreinscriptionMBean(
			DemandePreinscriptionMBean demandePreinscriptionMBean) {
		this.demandePreinscriptionMBean = demandePreinscriptionMBean;
	}

	public void setNumeroDemande(Long numeroDemande) {
		this.numeroDemande = numeroDemande;
	}

	public Long getNumeroDemande() {
		return numeroDemande;
	}

	public boolean isInscriptionGenerable() {
		return inscriptionGenerable;
	}

	public void setInscriptionGenerable(boolean inscriptionGenerable) {
		this.inscriptionGenerable = inscriptionGenerable;
	}

	public boolean isDecisionEditable() {
		return decisionEditable;
	}

	public void setDecisionEditable(boolean decisionEditable) {
		this.decisionEditable = decisionEditable;
	}

	public String getDecisionLabel() {
		dto = getDemandePreinscriptionDtoFromViewScope();
		if (dto.getDecisionDto() == null) {
			return "En attente";
		} else if (Boolean.TRUE == dto.getDecisionDto().getAcceptee()) {
			return "Accept�e";
		} else {
			return "Refus�e";
		}
	}

	public String getStatutLabel() {
		dto = getDemandePreinscriptionDtoFromViewScope();
		if (Boolean.TRUE.equals(dto.getGeneree())) {
			return "G�n�r�e";
		} else if (dto.getDecisionDto() == null) {
			return "Enregistr�e";
		} else if (Boolean.TRUE.equals(dto.getDecisionDto().getAcceptee())) {
			return "En attente";
		} else {
			return "Rejet�e";
		}

	}

	public Flash getFlash() {
		return flash;
	}

	public void setFlash(Flash flash) {
		this.flash = flash;
	}

}
