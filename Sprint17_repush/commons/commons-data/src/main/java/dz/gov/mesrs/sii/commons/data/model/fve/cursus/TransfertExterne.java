package dz.gov.mesrs.sii.commons.data.model.fve.cursus;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Vue sur les demandes de transfert entrantes par domaine/filiere
 * 
 * @author Mounir.MESSAOUDI
 */
@Entity
@NamedQuery(name = "TransfertExterne.findAll", query = "SELECT t FROM TransfertExterne t")
@Table(name = "transfert_externe", schema = "cursus")
public class TransfertExterne implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(insertable = false, updatable = false)
	private Long id;

	@Column(name = "id_annee_academique", insertable = false, updatable = false)
	private Long anneeAcademiqueId;

	@Column(name = "ref_code_domaine_demande", insertable = false, updatable = false)
	private String refCodeDomaineDemande;

	@Column(name = "ref_code_etab", insertable = false, updatable = false)
	private String refCodeEtab;

	@Column(name = "ref_code_filiere_demandee", insertable = false, updatable = false)
	private String refCodeFiliereDemandee;

	@Column(name = "capacite_transfert", insertable = false, updatable = false)
	private Integer capaciteTransfert;

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

	public TransfertExterne() {
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

	public String getRefCodeDomaineDemande() {
		return refCodeDomaineDemande;
	}

	public void setRefCodeDomaineDemande(String refCodeDomaineDemande) {
		this.refCodeDomaineDemande = refCodeDomaineDemande;
	}

	public String getRefCodeEtab() {
		return refCodeEtab;
	}

	public void setRefCodeEtab(String refCodeEtab) {
		this.refCodeEtab = refCodeEtab;
	}

	public String getRefCodeFiliereDemandee() {
		return refCodeFiliereDemandee;
	}

	public void setRefCodeFiliereDemandee(String refCodeFiliereDemandee) {
		this.refCodeFiliereDemandee = refCodeFiliereDemandee;
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

}