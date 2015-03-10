package dz.gov.mesrs.sii.commons.data.model.fve.cursus;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Vue sur les demandes de transfert entrantes par type de transfert
 * @author Mounir.MESSAOUDI
 */
@Entity
@NamedQuery(name = "TransfertTypeTransfert.findAll", query = "SELECT t FROM TransfertTypeTransfert t")
@Table(name = "transfert_type_transfert", schema = "cursus")
public class TransfertTypeTransfert implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(insertable = false, updatable = false)
	private Long id;

	@Column(name = "id_annee_academique", insertable = false, updatable = false)
	private Long anneeAcademiqueId;

	@Column(name = "ref_code_etab", insertable = false, updatable = false)
	private String refCodeEtab;

	@Column(name = "nbr_total_demandes", insertable = false, updatable = false)
	private Long nbrTotalDemandes;

	@Column(name = "nbr_demandes_traitees", insertable = false, updatable = false)
	private Long nbrDemandesTraitees;

	@Column(name = "nbr_demandes_acceptees", insertable = false, updatable = false)
	private Long nbrDemandesAcceptees;

	@Column(name = "nbr_demandes_rejetees", insertable = false, updatable = false)
	private Long nbrDemandesRejetees;

	@Column(name = "nbr_demandes_accordees", insertable = false, updatable = false)
	private Long nbrDemandesAccordees;

	@Column(name = "nbr_cas_exceptionnels", insertable = false, updatable = false)
	private Long nbrCasExceptionnels;
	
	@Column(name = "ref_code_type_transfert", insertable = false, updatable = false)
	private String refCodeTypeTransfert;

	public TransfertTypeTransfert() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAnneeAcademiqueId() {
		return anneeAcademiqueId;
	}

	public void setAnneeAcademiqueId(Long anneeAcademiqueId) {
		this.anneeAcademiqueId = anneeAcademiqueId;
	}
	
	public String getRefCodeEtab() {
		return refCodeEtab;
	}

	public void setRefCodeEtab(String refCodeEtab) {
		this.refCodeEtab = refCodeEtab;
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

	public String getRefCodeTypeTransfert() {
		return refCodeTypeTransfert;
	}

	public void setRefCodeTypeTransfert(String refCodeTypeTransfert) {
		this.refCodeTypeTransfert = refCodeTypeTransfert;
	}

}