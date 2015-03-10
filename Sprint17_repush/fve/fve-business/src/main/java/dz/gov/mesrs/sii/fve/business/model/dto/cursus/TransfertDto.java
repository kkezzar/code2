package dz.gov.mesrs.sii.fve.business.model.dto.cursus;

/**
 * 
 * @author Mounir.MESSAOUDI
 */
public class TransfertDto implements java.io.Serializable {

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

	
	private Integer capaciteTransfert;

	private Long nbrTotalDemandes;
	private Long nbrDemandesTraitees;
	private Long nbrDemandesAcceptees;
	private Long nbrDemandesRejetees;
	private Long nbrDemandesAccordees;
	private Long nbrCasExceptionnels;


	private String refCodeEtab;
	private String libelleEtablissementFr;
	private String libelleEtablissementAr;

	private String refCodeFiliereDemandee;
	private String libelleFiliereDemandeeFr;
	private String libelleFiliereDemandeeAr;

	private String refCodeDomaineDemande;

	private String libelleDomaineDemandeFr;
	private String libelleDomaineDemandeAr;
	
	private Boolean nouveauBachelier;
	private String nouveauBachelierLibelleFr;
	private String nouveauBachelierLibelleAr;
	
	private String refCodeTypeTransfert;
	private String refCodeTypeTransfertLibelleFr;
	private String refCodeTypeTransfertLibelleAr;
	

	public TransfertDto() {
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

	public String getAnneeAcademiqueCode() {
		return anneeAcademiqueCode;
	}

	public void setAnneeAcademiqueCode(String anneeAcademiqueCode) {
		this.anneeAcademiqueCode = anneeAcademiqueCode;
	}

	public Integer getCapaciteTransfert() {
		return capaciteTransfert;
	}

	public void setCapaciteTransfert(Integer capaciteTransfert) {
		this.capaciteTransfert = capaciteTransfert;
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

	public Long getNbrDemandesAcceptees() {
		return nbrDemandesAcceptees;
	}

	public void setNbrDemandesAcceptees(Long nbrDemandesAcceptees) {
		this.nbrDemandesAcceptees = nbrDemandesAcceptees;
	}

	public Long getNbrDemandesRejetees() {
		return nbrDemandesRejetees;
	}

	public void setNbrDemandesRejetees(Long nbrDemandesRejetees) {
		this.nbrDemandesRejetees = nbrDemandesRejetees;
	}

	public Long getNbrDemandesAccordees() {
		return nbrDemandesAccordees;
	}

	public void setNbrDemandesAccordees(Long nbrDemandesAccordees) {
		this.nbrDemandesAccordees = nbrDemandesAccordees;
	}

	public Long getNbrCasExceptionnels() {
		return nbrCasExceptionnels;
	}

	public void setNbrCasExceptionnels(Long nbrCasExceptionnels) {
		this.nbrCasExceptionnels = nbrCasExceptionnels;
	}

	public String getRefCodeEtab() {
		return refCodeEtab;
	}

	public void setRefCodeEtab(String refCodeEtab) {
		this.refCodeEtab = refCodeEtab;
	}

	public String getLibelleEtablissementAr() {
		return libelleEtablissementAr;
	}

	public void setLibelleEtablissementAr(String libelleEtablissementAr) {
		this.libelleEtablissementAr = libelleEtablissementAr;
	}

	public String getRefCodeFiliereDemandee() {
		return refCodeFiliereDemandee;
	}

	public void setRefCodeFiliereDemandee(String refCodeFiliereDemandee) {
		this.refCodeFiliereDemandee = refCodeFiliereDemandee;
	}

	public String getRefCodeDomaineDemande() {
		return refCodeDomaineDemande;
	}

	public void setRefCodeDomaineDemande(String refCodeDomaineDemande) {
		this.refCodeDomaineDemande = refCodeDomaineDemande;
	}

	public Short getAnneeAcademiquePremiereAnnee() {
		return anneeAcademiquePremiereAnnee;
	}

	public void setAnneeAcademiquePremiereAnnee(Short anneeAcademiquePremiereAnnee) {
		this.anneeAcademiquePremiereAnnee = anneeAcademiquePremiereAnnee;
	}

	public Short getAnneeAcademiqueDeuxiemeAnnee() {
		return anneeAcademiqueDeuxiemeAnnee;
	}

	public void setAnneeAcademiqueDeuxiemeAnnee(Short anneeAcademiqueDeuxiemeAnnee) {
		this.anneeAcademiqueDeuxiemeAnnee = anneeAcademiqueDeuxiemeAnnee;
	}

	public String getLibelleEtablissementFr() {
		return libelleEtablissementFr;
	}

	public void setLibelleEtablissementFr(String libelleEtablissementFr) {
		this.libelleEtablissementFr = libelleEtablissementFr;
	}

	public String getLibelleFiliereDemandeeFr() {
		return libelleFiliereDemandeeFr;
	}

	public void setLibelleFiliereDemandeeFr(String libelleFiliereDemandeeFr) {
		this.libelleFiliereDemandeeFr = libelleFiliereDemandeeFr;
	}

	public String getLibelleFiliereDemandeeAr() {
		return libelleFiliereDemandeeAr;
	}

	public void setLibelleFiliereDemandeeAr(String libelleFiliereDemandeeAr) {
		this.libelleFiliereDemandeeAr = libelleFiliereDemandeeAr;
	}

	public String getLibelleDomaineDemandeFr() {
		return libelleDomaineDemandeFr;
	}

	public void setLibelleDomaineDemandeFr(String libelleDomaineDemandeFr) {
		this.libelleDomaineDemandeFr = libelleDomaineDemandeFr;
	}

	public String getLibelleDomaineDemandeAr() {
		return libelleDomaineDemandeAr;
	}

	public void setLibelleDomaineDemandeAr(String libelleDomaineDemandeAr) {
		this.libelleDomaineDemandeAr = libelleDomaineDemandeAr;
	}

	public Boolean getNouveauBachelier() {
		return nouveauBachelier;
	}

	public void setNouveauBachelier(Boolean nouveauBachelier) {
		this.nouveauBachelier = nouveauBachelier;
	}

	public String getNouveauBachelierLibelleFr() {
		return nouveauBachelierLibelleFr;
	}

	public void setNouveauBachelierLibelleFr(String nouveauBachelierLibelleFr) {
		this.nouveauBachelierLibelleFr = nouveauBachelierLibelleFr;
	}

	public String getNouveauBachelierLibelleAr() {
		return nouveauBachelierLibelleAr;
	}

	public void setNouveauBachelierLibelleAr(String nouveauBachelierLibelleAr) {
		this.nouveauBachelierLibelleAr = nouveauBachelierLibelleAr;
	}

	public String getRefCodeTypeTransfert() {
		return refCodeTypeTransfert;
	}

	public void setRefCodeTypeTransfert(String refCodeTypeTransfert) {
		this.refCodeTypeTransfert = refCodeTypeTransfert;
	}

	public String getRefCodeTypeTransfertLibelleFr() {
		return refCodeTypeTransfertLibelleFr;
	}

	public void setRefCodeTypeTransfertLibelleFr(
			String refCodeTypeTransfertLibelleFr) {
		this.refCodeTypeTransfertLibelleFr = refCodeTypeTransfertLibelleFr;
	}

	public String getRefCodeTypeTransfertLibelleAr() {
		return refCodeTypeTransfertLibelleAr;
	}

	public void setRefCodeTypeTransfertLibelleAr(
			String refCodeTypeTransfertLibelleAr) {
		this.refCodeTypeTransfertLibelleAr = refCodeTypeTransfertLibelleAr;
	}


}
