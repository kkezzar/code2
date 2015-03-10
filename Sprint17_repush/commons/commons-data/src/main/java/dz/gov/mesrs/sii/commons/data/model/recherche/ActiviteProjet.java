package dz.gov.mesrs.sii.commons.data.model.recherche;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import dz.gov.mesrs.sii.commons.data.model.Identifiable;
import dz.gov.mesrs.sii.commons.data.model.referentiel.Nomenclature;



/**
 * @author slimane ramdane   on : 02 fev. 2015 15:33:48
 */
@Entity
@Table(name = "activite_projet", schema = "recherche")
public class ActiviteProjet implements java.io.Serializable , Identifiable<Long>  {
	

	/**
	 * serialVersionUID 
	 * @author slimane ramdane  on : 02 fev. 2015 15:30:03
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Integer numero;
	private String description;
	private Date dateDebutPrev;
	private Date dateFinPrev;
	private Date dateFinReel;
	private String resultatAttendu;
	private String resultatAtteint;
	
	
	private Nomenclature activiteNomenclature;
	private Projet projet;
		
	public ActiviteProjet() {		
	}
	
	
	@Id	
	@SequenceGenerator(name="activite_projet_id_seq_gen", sequenceName="recherche.activite_projet_id_seq")
	@GeneratedValue(generator="activite_projet_id_seq_gen")
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

	
	@Column(name = "numero")
	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "date_debut_prev")
	public Date getDateDebutPrev() {
		return dateDebutPrev;
	}

	public void setDateDebutPrev(Date dateDebutPrev) {
		this.dateDebutPrev = dateDebutPrev;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "date_fin_prev")
	public Date getDateFinPrev() {
		return dateFinPrev;
	}

	public void setDateFinPrev(Date dateFinPrev) {
		this.dateFinPrev = dateFinPrev;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "date_fin_reel")
	public Date getDateFinReel() {
		return dateFinReel;
	}

	public void setDateFinReel(Date dateFinReel) {
		this.dateFinReel = dateFinReel;
	}
	@Column(name = "resultat_attendu")
	public String getResultatAttendu() {
		return resultatAttendu;
	}

	public void setResultatAttendu(String resultatAttendu) {
		this.resultatAttendu = resultatAttendu;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_nc_activite")		
	public Nomenclature getActiviteNomenclature() {
		return activiteNomenclature;
	}

	public void setActiviteNomenclature(Nomenclature activiteNomenclature) {
		this.activiteNomenclature = activiteNomenclature;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_projet")	
	public Projet getProjet() {
		return projet;
	}

	public void setProjet(Projet projet) {
		this.projet = projet;
	}
	@Column(name = "resultat_atteint")	
	public String getResultatAtteint() {
		return resultatAtteint;
	}


	public void setResultatAtteint(String resultatAtteint) {
		this.resultatAtteint = resultatAtteint;
	}


	@Transient
	@Override
	public Long getIdenfiant() {
		return this.getId();
	}

	@Transient
	@Override
	public String getIdentifiantName() {
		return "id";
	}

	
	
}
