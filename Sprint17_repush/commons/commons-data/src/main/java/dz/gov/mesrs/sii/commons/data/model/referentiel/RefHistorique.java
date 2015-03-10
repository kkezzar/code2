package dz.gov.mesrs.sii.commons.data.model.referentiel;

// Generated 18 juin 2014 09:50:15 by Hibernate Tools 3.6.0

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * RefHistorique generated by hbm2java
 */
@Entity
@Table(name = "ref_historique", schema = "ppm")
public class RefHistorique implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private RefEtablissement refEtablissement;
	private RefFonction refFonction;
	private RefCompte refCompte;
	private Date dateAction;
	private Date heureAction;
	private int idOccurence;
	private Integer typeAction;
	private String nomEntite;

	public RefHistorique() {
	}

	public RefHistorique(int id) {
		this.id = id;
	}

	public RefHistorique(int id, RefEtablissement refEtablissement,
			RefFonction refFonction, RefCompte refCompte, Date dateAction,
			Date heureAction, Integer idOccurence, Integer typeAction) {
		this.id = id;
		this.refEtablissement = refEtablissement;
		this.refFonction = refFonction;
		this.refCompte = refCompte;
		this.dateAction = dateAction;
		this.heureAction = heureAction;
		this.idOccurence = idOccurence;
		this.typeAction = typeAction;
	}

	@Id
	@SequenceGenerator(name = "seq_historique", initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_historique")
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "etablissement", nullable = false)
	public RefEtablissement getRefEtablissement() {
		return this.refEtablissement;
	}

	public void setRefEtablissement(RefEtablissement refEtablissement) {
		this.refEtablissement = refEtablissement;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fonction", nullable = false)
	public RefFonction getRefFonction() {
		return this.refFonction;
	}

	public void setRefFonction(RefFonction refFonction) {
		this.refFonction = refFonction;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "compte", nullable = false)
	public RefCompte getRefCompte() {
		return this.refCompte;
	}

	public void setRefCompte(RefCompte refCompte) {
		this.refCompte = refCompte;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_action", nullable = false, length = 29)
	public Date getDateAction() {
		return this.dateAction;
	}

	public void setDateAction(Date dateAction) {
		this.dateAction = dateAction;
	}

	@Temporal(TemporalType.TIME)
	@Column(name = "heure_action", nullable = false, length = 15)
	public Date getHeureAction() {
		return this.heureAction;
	}

	public void setHeureAction(Date heureAction) {
		this.heureAction = heureAction;
	}
	
	@Column(name = "id_occurence", nullable = false)
	public int getIdOccurence() {
		return this.idOccurence;
	}

	public void setIdOccurence(int idOccurence) {
		this.idOccurence = idOccurence;
	}

	@Column(name = "type_action", nullable = false)
	public Integer getTypeAction() {
		return this.typeAction;
	}

	public void setTypeAction(Integer typeAction) {
		this.typeAction = typeAction;
	}

	@Column(name = "nom_entite", length = 60)
	public String getNomEntite() {
		return nomEntite;
	}

	
	public void setNomEntite(String nomEntite) {
		this.nomEntite = nomEntite;
	}
	
	

}