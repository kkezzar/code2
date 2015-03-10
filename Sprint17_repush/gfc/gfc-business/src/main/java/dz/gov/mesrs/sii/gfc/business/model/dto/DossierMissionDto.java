package dz.gov.mesrs.sii.gfc.business.model.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteDto;

public class DossierMissionDto implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private MissionDto mission;
	private TarifMissionDto tarifMission;
	private DossierEmployeDto dossierEmploye;

	private Date dateDebut;
	private Date dateFin;
	private Integer nombreResauration;
	private BigDecimal fraisRestauration;
	private Integer nombreHebergement;
	private BigDecimal fraisHebergement;
	private BigDecimal fraisVoyage;
	private BigDecimal montantTotal;
	private BigDecimal montantAvance;
	private Date dateSignature;
	private Integer nombreResaurationReel;
	private BigDecimal fraisRestaurationReel;
	private Integer nombreHebergementReel;
	private BigDecimal fraisHebergementReel;
	private BigDecimal fraisVoyageReel;
	private Date dateDebutReel;
	private Date dateFinReel;
	private BigDecimal resteAPayer;
	private BigDecimal montantARembourser;

	private SituationEntiteDto situation;

	private List<DossierPaiementDto> dossierPaiements = new ArrayList<DossierPaiementDto>(0);

	public DossierMissionDto() {
	}

	public DossierMissionDto(Integer id, MissionDto mission, TarifMissionDto tarifMission,
			DossierEmployeDto dossierEmploye, Date dateDebut, Date dateFin, BigDecimal montantTotal,
			BigDecimal montantAvance) {
		this.id = id;
		this.mission = mission;
		this.tarifMission = tarifMission;
		this.dossierEmploye = dossierEmploye;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.montantTotal = montantTotal;
		this.montantAvance = montantAvance;
	}

	public DossierMissionDto(Integer id, MissionDto mission, TarifMissionDto tarifMission,
			DossierEmployeDto dossierEmploye, Date dateDebut, Date dateFin, Integer nombreResauration,
			BigDecimal fraisRestauration, Integer nombreHebergement, BigDecimal fraisHebergement,
			BigDecimal fraisVoyage, BigDecimal montantTotal, BigDecimal montantAvance, Date dateSignature,
			Integer nombreResaurationReel, BigDecimal fraisRestaurationReel, Integer nombreHebergementReel,
			BigDecimal fraisHebergementReel, BigDecimal fraisVoyageReel, Date dateDebutReel, Date dateFinReel,
			BigDecimal resteAPayer, BigDecimal montantARembourser, SituationEntiteDto situation,
			List<DossierPaiementDto> dossierPaiements) {
		this.id = id;
		this.mission = mission;
		this.tarifMission = tarifMission;
		this.dossierEmploye = dossierEmploye;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.nombreResauration = nombreResauration;
		this.fraisRestauration = fraisRestauration;
		this.nombreHebergement = nombreHebergement;
		this.fraisHebergement = fraisHebergement;
		this.fraisVoyage = fraisVoyage;
		this.montantTotal = montantTotal;
		this.montantAvance = montantAvance;
		this.dateSignature = dateSignature;
		this.nombreResaurationReel = nombreResaurationReel;
		this.fraisRestaurationReel = fraisRestaurationReel;
		this.nombreHebergementReel = nombreHebergementReel;
		this.fraisHebergementReel = fraisHebergementReel;
		this.fraisVoyageReel = fraisVoyageReel;
		this.dateDebutReel = dateDebutReel;
		this.dateFinReel = dateFinReel;
		this.resteAPayer = resteAPayer;
		this.montantARembourser = montantARembourser;
		this.situation = situation;
		this.dossierPaiements = dossierPaiements;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public MissionDto getMission() {
		return this.mission;
	}

	public void setMission(MissionDto mission) {
		this.mission = mission;
	}

	public TarifMissionDto getTarifMission() {
		return this.tarifMission;
	}

	public void setTarifMission(TarifMissionDto tarifMission) {
		this.tarifMission = tarifMission;
	}

	public DossierEmployeDto getDossierEmploye() {
		return dossierEmploye;
	}

	public void setDossierEmploye(DossierEmployeDto dossierEmploye) {
		this.dossierEmploye = dossierEmploye;
	}

	public Date getDateDebut() {
		return this.dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return this.dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public Integer getNombreResauration() {
		return this.nombreResauration;
	}

	public void setNombreResauration(Integer nombreResauration) {
		this.nombreResauration = nombreResauration;
	}

	public BigDecimal getFraisRestauration() {
		return this.fraisRestauration;
	}

	public void setFraisRestauration(BigDecimal fraisRestauration) {
		this.fraisRestauration = fraisRestauration;
	}

	public Integer getNombreHebergement() {
		return this.nombreHebergement;
	}

	public void setNombreHebergement(Integer nombreHebergement) {
		this.nombreHebergement = nombreHebergement;
	}

	public BigDecimal getFraisHebergement() {
		return this.fraisHebergement;
	}

	public void setFraisHebergement(BigDecimal fraisHebergement) {
		this.fraisHebergement = fraisHebergement;
	}

	public BigDecimal getFraisVoyage() {
		return this.fraisVoyage;
	}

	public void setFraisVoyage(BigDecimal fraisVoyage) {
		this.fraisVoyage = fraisVoyage;
	}

	public BigDecimal getMontantTotal() {
		return this.montantTotal;
	}

	public void setMontantTotal(BigDecimal montantTotal) {
		this.montantTotal = montantTotal;
	}

	public BigDecimal getMontantAvance() {
		return this.montantAvance;
	}

	public void setMontantAvance(BigDecimal montantAvance) {
		this.montantAvance = montantAvance;
	}

	public Date getDateSignature() {
		return this.dateSignature;
	}

	public void setDateSignature(Date dateSignature) {
		this.dateSignature = dateSignature;
	}

	public Integer getNombreResaurationReel() {
		return this.nombreResaurationReel;
	}

	public void setNombreResaurationReel(Integer nombreResaurationReel) {
		this.nombreResaurationReel = nombreResaurationReel;
	}

	public BigDecimal getFraisRestaurationReel() {
		return this.fraisRestaurationReel;
	}

	public void setFraisRestaurationReel(BigDecimal fraisRestaurationReel) {
		this.fraisRestaurationReel = fraisRestaurationReel;
	}

	public Integer getNombreHebergementReel() {
		return this.nombreHebergementReel;
	}

	public void setNombreHebergementReel(Integer nombreHebergementReel) {
		this.nombreHebergementReel = nombreHebergementReel;
	}

	public BigDecimal getFraisHebergementReel() {
		return this.fraisHebergementReel;
	}

	public void setFraisHebergementReel(BigDecimal fraisHebergementReel) {
		this.fraisHebergementReel = fraisHebergementReel;
	}

	public BigDecimal getFraisVoyageReel() {
		return this.fraisVoyageReel;
	}

	public void setFraisVoyageReel(BigDecimal fraisVoyageReel) {
		this.fraisVoyageReel = fraisVoyageReel;
	}

	public Date getDateDebutReel() {
		return this.dateDebutReel;
	}

	public void setDateDebutReel(Date dateDebutReel) {
		this.dateDebutReel = dateDebutReel;
	}

	public Date getDateFinReel() {
		return this.dateFinReel;
	}

	public void setDateFinReel(Date dateFinReel) {
		this.dateFinReel = dateFinReel;
	}

	public BigDecimal getResteAPayer() {
		return this.resteAPayer;
	}

	public void setResteAPayer(BigDecimal resteAPayer) {
		this.resteAPayer = resteAPayer;
	}

	public BigDecimal getMontantARembourser() {
		return this.montantARembourser;
	}

	public void setMontantARembourser(BigDecimal montantARembourser) {
		this.montantARembourser = montantARembourser;
	}

	public SituationEntiteDto getSituation() {
		return this.situation;
	}

	public void setSituation(SituationEntiteDto situation) {
		this.situation = situation;
	}

	public List<DossierPaiementDto> getDossierPaiements() {
		return this.dossierPaiements;
	}

	public void setDossierPaiements(List<DossierPaiementDto> dossierPaiements) {
		this.dossierPaiements = dossierPaiements;
	}

}
