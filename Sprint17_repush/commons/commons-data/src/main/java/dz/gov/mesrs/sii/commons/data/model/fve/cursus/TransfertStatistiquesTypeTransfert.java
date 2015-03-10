package dz.gov.mesrs.sii.commons.data.model.fve.cursus;

import java.io.Serializable;
import javax.persistence.*;

/**
 * @TODO to be removed
 * @author mounir
 *
 */
@Entity
@NamedQuery(name = "TransfertStatistiquesTypeTransfert.findAll", query = "SELECT t FROM TransfertStatistiquesTypeTransfert t")
@Table(name = "transfert_statistiques_type_transfert", schema = "cursus")
public class TransfertStatistiquesTypeTransfert implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(insertable = false, updatable = false)
	private Long id;
	
	@Column(name = "nouveau_bachelier", insertable = false, updatable = false)
	private Boolean nouveauBachelier;

	@Column(name = "ref_code_type_transfert", insertable = false, updatable = false)
	private String refCodeTypeTransfert;
	
	@Column(name = "id_annee_academique", insertable = false, updatable = false)
	private Long anneeAcademiqueId;

	@Column(name = "ref_code_etab_accueil", insertable = false, updatable = false)
	private String refCodeEtabAccueil;

	@Column(name = "nbr_total_demandes", insertable = false, updatable = false)
	private Long nbrTotalDemandes;

	@Column(name = "nbr_demandes_traitees", insertable = false, updatable = false)
	private String nbrDemandesTraitees;

	@Column(name = "nbr_demandes_accordees", insertable = false, updatable = false)
	private Long nbrDemandesAccordees;

	public TransfertStatistiquesTypeTransfert() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getNouveauBachelier() {
		return nouveauBachelier;
	}

	public void setNouveauBachelier(Boolean nouveauBachelier) {
		this.nouveauBachelier = nouveauBachelier;
	}

	public Long getAnneeAcademiqueId() {
		return anneeAcademiqueId;
	}

	public void setAnneeAcademiqueId(Long anneeAcademiqueId) {
		this.anneeAcademiqueId = anneeAcademiqueId;
	}

	public String getRefCodeEtabAccueil() {
		return refCodeEtabAccueil;
	}

	public void setRefCodeEtabAccueil(String refCodeEtabAccueil) {
		this.refCodeEtabAccueil = refCodeEtabAccueil;
	}

	public String getRefCodeTypeTransfert() {
		return refCodeTypeTransfert;
	}

	public void setRefCodeTypeTransfert(String refCodeTypeTransfert) {
		this.refCodeTypeTransfert = refCodeTypeTransfert;
	}

	public Long getNbrTotalDemandes() {
		return nbrTotalDemandes;
	}

	public void setNbrTotalDemandes(Long nbrTotalDemandes) {
		this.nbrTotalDemandes = nbrTotalDemandes;
	}

	public String getNbrDemandesTraitees() {
		return nbrDemandesTraitees;
	}

	public void setNbrDemandesTraitees(String nbrDemandesTraitees) {
		this.nbrDemandesTraitees = nbrDemandesTraitees;
	}

	public Long getNbrDemandesAccordees() {
		return nbrDemandesAccordees;
	}

	public void setNbrDemandesAccordees(Long nbrDemandesAccordees) {
		this.nbrDemandesAccordees = nbrDemandesAccordees;
	}

}