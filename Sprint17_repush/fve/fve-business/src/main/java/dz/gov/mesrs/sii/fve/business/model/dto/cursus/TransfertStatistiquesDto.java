package dz.gov.mesrs.sii.fve.business.model.dto.cursus;

/**
 * 
 * @author Mounir.MESSAOUDI
 */
public class TransfertStatistiquesDto implements java.io.Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author Mounir.MESSAOUDI
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private Integer anneeAcademiqueId;
	private Short anneeAcademiquePremiereAnnee;
	private Short anneeAcademiqueDeuxiemeAnnee;
	private String anneeAcademiqueCode;

	private Long nbrTotalDemandes;
	private Long nbrDemandesTraitees;
	private Long nbrDemandesAccordees;

	private String refCodeEtabAccueil;
	private String libelleEtablissementFr;
	private String libelleEtablissementAr;

	private Boolean nouveauBachelier;
	private String libelleNouveauBachelierFr;
	private String libelleNouveauBachelierAr;

	public TransfertStatistiquesDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TransfertStatistiquesDto(Long id, Integer anneeAcademiqueId,
			Short anneeAcademiquePremiereAnnee,
			Short anneeAcademiqueDeuxiemeAnnee, String anneeAcademiqueCode,
			Long nbrTotalDemandes, Long nbrDemandesTraitees,
			Long nbrDemandesAccordees, String refCodeEtabAccueil,
			String libelleEtablissementFr, String libelleEtablissementAr,
			Boolean nouveauBachelier, String libelleNouveauBachelierFr,
			String libelleNouveauBachelierAr) {
		super();
		this.id = id;
		this.anneeAcademiqueId = anneeAcademiqueId;
		this.anneeAcademiquePremiereAnnee = anneeAcademiquePremiereAnnee;
		this.anneeAcademiqueDeuxiemeAnnee = anneeAcademiqueDeuxiemeAnnee;
		this.anneeAcademiqueCode = anneeAcademiqueCode;
		this.nbrTotalDemandes = nbrTotalDemandes;
		this.nbrDemandesTraitees = nbrDemandesTraitees;
		this.nbrDemandesAccordees = nbrDemandesAccordees;
		this.refCodeEtabAccueil = refCodeEtabAccueil;
		this.libelleEtablissementFr = libelleEtablissementFr;
		this.libelleEtablissementAr = libelleEtablissementAr;
		this.nouveauBachelier = nouveauBachelier;
		this.libelleNouveauBachelierFr = libelleNouveauBachelierFr;
		this.libelleNouveauBachelierAr = libelleNouveauBachelierAr;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getAnneeAcademiqueId() {
		return anneeAcademiqueId;
	}

	public void setAnneeAcademiqueId(Integer anneeAcademiqueId) {
		this.anneeAcademiqueId = anneeAcademiqueId;
	}

	public Short getAnneeAcademiquePremiereAnnee() {
		return anneeAcademiquePremiereAnnee;
	}

	public void setAnneeAcademiquePremiereAnnee(
			Short anneeAcademiquePremiereAnnee) {
		this.anneeAcademiquePremiereAnnee = anneeAcademiquePremiereAnnee;
	}

	public Short getAnneeAcademiqueDeuxiemeAnnee() {
		return anneeAcademiqueDeuxiemeAnnee;
	}

	public void setAnneeAcademiqueDeuxiemeAnnee(
			Short anneeAcademiqueDeuxiemeAnnee) {
		this.anneeAcademiqueDeuxiemeAnnee = anneeAcademiqueDeuxiemeAnnee;
	}

	public String getAnneeAcademiqueCode() {
		return anneeAcademiqueCode;
	}

	public void setAnneeAcademiqueCode(String anneeAcademiqueCode) {
		this.anneeAcademiqueCode = anneeAcademiqueCode;
	}

	public Long getNbrTotalDemandes() {
		return nbrTotalDemandes;
	}

	public void setNbrTotalDemandes(Long nbrTotalDemandes) {
		this.nbrTotalDemandes = nbrTotalDemandes;
	}

	public Long getNbrDemandesTraitees() {
		return nbrDemandesTraitees;
	}

	public void setNbrDemandesTraitees(Long nbrDemandesTraitees) {
		this.nbrDemandesTraitees = nbrDemandesTraitees;
	}

	public Long getNbrDemandesAccordees() {
		return nbrDemandesAccordees;
	}

	public void setNbrDemandesAccordees(Long nbrDemandesAccordees) {
		this.nbrDemandesAccordees = nbrDemandesAccordees;
	}

	public String getRefCodeEtabAccueil() {
		return refCodeEtabAccueil;
	}

	public void setRefCodeEtabAccueil(String refCodeEtabAccueil) {
		this.refCodeEtabAccueil = refCodeEtabAccueil;
	}

	public String getLibelleEtablissementFr() {
		return libelleEtablissementFr;
	}

	public void setLibelleEtablissementFr(String libelleEtablissementFr) {
		this.libelleEtablissementFr = libelleEtablissementFr;
	}

	public String getLibelleEtablissementAr() {
		return libelleEtablissementAr;
	}

	public void setLibelleEtablissementAr(String libelleEtablissementAr) {
		this.libelleEtablissementAr = libelleEtablissementAr;
	}

	public Boolean getNouveauBachelier() {
		return nouveauBachelier;
	}

	public void setNouveauBachelier(Boolean nouveauBachelier) {
		this.nouveauBachelier = nouveauBachelier;
	}

	public String getLibelleNouveauBachelierFr() {
		return libelleNouveauBachelierFr;
	}

	public void setLibelleNouveauBachelierFr(String libelleNouveauBachelierFr) {
		this.libelleNouveauBachelierFr = libelleNouveauBachelierFr;
	}

	public String getLibelleNouveauBachelierAr() {
		return libelleNouveauBachelierAr;
	}

	public void setLibelleNouveauBachelierAr(String libelleNouveauBachelierAr) {
		this.libelleNouveauBachelierAr = libelleNouveauBachelierAr;
	}

}
