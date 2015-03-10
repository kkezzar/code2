package dz.gov.mesrs.sii.gfc.business.model.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;

public class EngagementMarcheDto implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private MarcheDto marche;
	private String numero;
	private Date dateDemande;
	private String objet;
	private BigDecimal montantAEngager;
	private NomenclatureDto typeEngagement;
	private NomenclatureDto natureEngagement;
	private String numeroFiche;
	private Date dateSignature;
	private String numeroVisa;
	private Date dateVisa;
	private String observation;
	private SituationEntiteDto situation;

	private List<DetailEngagementMarcheDto> detailEngagementMarches = new ArrayList<DetailEngagementMarcheDto>();

	public EngagementMarcheDto() {
		marche = new MarcheDto();
		typeEngagement = new NomenclatureDto();
		natureEngagement = new NomenclatureDto();
	}

	public EngagementMarcheDto(Integer id, MarcheDto marche, String numero, Date dateDemande, String objet,
			BigDecimal montantAEngager, NomenclatureDto typeEngagement) {
		this.id = id;
		this.marche = marche;
		this.numero = numero;
		this.dateDemande = dateDemande;
		this.objet = objet;
		this.montantAEngager = montantAEngager;
		this.typeEngagement = typeEngagement;
	}

	public EngagementMarcheDto(Integer id, MarcheDto marche, String numero, Date dateDemande, String objet,
			BigDecimal montantAEngager, NomenclatureDto typeEngagement, String numeroFiche, Date dateSignature,
			String numeroVisa, Date dateVisa, String observation, SituationEntiteDto situation,
			List<DetailEngagementMarcheDto> detailEngagementMarches) {
		this.id = id;
		this.marche = marche;
		this.numero = numero;
		this.dateDemande = dateDemande;
		this.objet = objet;
		this.montantAEngager = montantAEngager;
		this.typeEngagement = typeEngagement;
		this.numeroFiche = numeroFiche;
		this.dateSignature = dateSignature;
		this.numeroVisa = numeroVisa;
		this.dateVisa = dateVisa;
		this.observation = observation;
		this.situation = situation;
		this.detailEngagementMarches = detailEngagementMarches;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public MarcheDto getMarche() {
		return this.marche;
	}

	public void setMarche(MarcheDto marche) {
		this.marche = marche;
	}

	public String getNumero() {
		return this.numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Date getDateDemande() {
		return this.dateDemande;
	}

	public void setDateDemande(Date dateDemande) {
		this.dateDemande = dateDemande;
	}

	public String getObjet() {
		return this.objet;
	}

	public void setObjet(String objet) {
		this.objet = objet;
	}

	public BigDecimal getMontantAEngager() {
		return this.montantAEngager;
	}

	public void setMontantAEngager(BigDecimal montantAEngager) {
		this.montantAEngager = montantAEngager;
	}

	public NomenclatureDto getTypeEngagement() {
		return this.typeEngagement;
	}

	public void setTypeEngagement(NomenclatureDto typeEngagement) {
		this.typeEngagement = typeEngagement;
	}

	public NomenclatureDto getNatureEngagement() {
		return natureEngagement;
	}

	public void setNatureEngagement(NomenclatureDto natureEngagement) {
		this.natureEngagement = natureEngagement;
	}

	public String getNumeroFiche() {
		return this.numeroFiche;
	}

	public void setNumeroFiche(String numeroFiche) {
		this.numeroFiche = numeroFiche;
	}

	public Date getDateSignature() {
		return this.dateSignature;
	}

	public void setDateSignature(Date dateSignature) {
		this.dateSignature = dateSignature;
	}

	public String getNumeroVisa() {
		return this.numeroVisa;
	}

	public void setNumeroVisa(String numeroVisa) {
		this.numeroVisa = numeroVisa;
	}

	public Date getDateVisa() {
		return this.dateVisa;
	}

	public void setDateVisa(Date dateVisa) {
		this.dateVisa = dateVisa;
	}

	public String getObservation() {
		return this.observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public SituationEntiteDto getSituation() {
		return this.situation;
	}

	public void setSituation(SituationEntiteDto situation) {
		this.situation = situation;
	}

	public List<DetailEngagementMarcheDto> getDetailEngagementMarches() {
		return this.detailEngagementMarches;
	}

	public void setDetailEngagementMarches(List<DetailEngagementMarcheDto> detailEngagementMarches) {
		this.detailEngagementMarches = detailEngagementMarches;
	}
}