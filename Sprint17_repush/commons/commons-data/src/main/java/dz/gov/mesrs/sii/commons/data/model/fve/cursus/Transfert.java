package dz.gov.mesrs.sii.commons.data.model.fve.cursus;

import java.io.Serializable;
import javax.persistence.*;


/**
 * 
 * @author Mounir.MESSAOUDI
 */
@Entity
@NamedQuery(name="Transfert.findAll", query="SELECT t FROM Transfert t")
@Table(name = "transfert", schema = "cursus")
public class Transfert implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="capacite_transfert", insertable=false, updatable=false)
	private Integer capaciteTransfert;

	@Id
	@Column(insertable=false, updatable=false)
	private Long id;

	@Column(name="nbr_demandes_acceptee", insertable=false, updatable=false)
	private Long nbrDemandesAcceptee;
	
	@Column(name="id_annee_academique", insertable=false, updatable=false)
	private Long anneeAcademiqueId;

	@Column(name="nbr_demandes_accordee", insertable=false, updatable=false)
	private Long nbrDemandesAccordee;

	@Column(name="nbr_demandes_rejetee", insertable=false, updatable=false)
	private Long nbrDemandesRejetee;

	@Column(name="nbr_total_demandes", insertable=false, updatable=false)
	private Long nbrTotalDemandes;

	@Column(name="ref_code_domaine_demande", insertable=false, updatable=false)
	private String refCodeDomaineDemande;

	@Column(name="ref_code_etab_accueil", insertable=false, updatable=false)
	private String refCodeEtabAccueil;

	@Column(name="ref_code_filiere_demandee", insertable=false, updatable=false)
	private String refCodeFiliereDemandee;

	public Transfert() {
	}

	public Integer getCapaciteTransfert() {
		return this.capaciteTransfert;
	}

	public void setCapaciteTransfert(Integer capaciteTransfert) {
		this.capaciteTransfert = capaciteTransfert;
	}

	public Long getId() {
		return this.id;
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

	public Long getNbrDemandesAcceptee() {
		return this.nbrDemandesAcceptee;
	}

	public void setNbrDemandesAcceptee(Long nbrDemandesAcceptee) {
		this.nbrDemandesAcceptee = nbrDemandesAcceptee;
	}

	public Long getNbrDemandesAccordee() {
		return this.nbrDemandesAccordee;
	}

	public void setNbrDemandesAccordee(Long nbrDemandesAccordee) {
		this.nbrDemandesAccordee = nbrDemandesAccordee;
	}

	public Long getNbrDemandesRejetee() {
		return this.nbrDemandesRejetee;
	}

	public void setNbrDemandesRejetee(Long nbrDemandesRejetee) {
		this.nbrDemandesRejetee = nbrDemandesRejetee;
	}

	public Long getNbrTotalDemandes() {
		return this.nbrTotalDemandes;
	}

	public void setNbrTotalDemandes(Long nbrTotalDemandes) {
		this.nbrTotalDemandes = nbrTotalDemandes;
	}

	public String getRefCodeDomaineDemande() {
		return this.refCodeDomaineDemande;
	}

	public void setRefCodeDomaineDemande(String refCodeDomaineDemande) {
		this.refCodeDomaineDemande = refCodeDomaineDemande;
	}

	public String getRefCodeEtabAccueil() {
		return this.refCodeEtabAccueil;
	}

	public void setRefCodeEtabAccueil(String refCodeEtabAccueil) {
		this.refCodeEtabAccueil = refCodeEtabAccueil;
	}

	public String getRefCodeFiliereDemandee() {
		return this.refCodeFiliereDemandee;
	}

	public void setRefCodeFiliereDemandee(String refCodeFiliereDemandee) {
		this.refCodeFiliereDemandee = refCodeFiliereDemandee;
	}

}