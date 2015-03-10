package dz.gov.mesrs.sii.commons.data.model.recherche;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import dz.gov.mesrs.sii.commons.data.model.Identifiable;
import dz.gov.mesrs.sii.commons.data.model.referentiel.Nomenclature;


/**
 * @author slimane ramdane  on : 04. fev 2015  15:33:48
 */
@Entity
@Table(name = "ind_evaluation", schema = "recherche")
public class IndEvaluation implements java.io.Serializable , Identifiable<Long>  {
	

	/**
	 * serialVersionUID 
	 * @author rlaib  on : 15 d�c. 2014  15:30:03
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long numero;
	private Nomenclature typeAppreciation;
	private Double valeurPrev;
	private Nomenclature ncIndicateur;	
	private Projet projet;
		
	public IndEvaluation() {
		
	}
	
	
	@Id
	@SequenceGenerator(name="ind_avaluation_id_seq_gen", sequenceName="recherche.ind_evaluation_id_seq")
	@GeneratedValue(generator="ind_avaluation_id_seq_gen")
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	
	public void setId(Long id) {
		this.id = id;
	}

	
	@Column(name = "numero")
	public Long getNumero() {
		return numero;
	}


	public void setNumero(Long numero) {
		this.numero = numero;
	}

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "type_appreciation")
	public Nomenclature getTypeAppreciation() {
		return typeAppreciation;
	}


	public void setTypeAppreciation(Nomenclature typeAppreciation) {
		this.typeAppreciation = typeAppreciation;
	}

	@Column(name = "valeur_prev")
	public Double getValeurPrev() {
		return valeurPrev;
	}


	public void setValeurPrev(Double valeurPrev) {
		this.valeurPrev = valeurPrev;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_nc_indicateur")
	public Nomenclature getNcIndicateur() {
		return ncIndicateur;
	}


	public void setNcIndicateur(Nomenclature ncIndicateur) {
		this.ncIndicateur = ncIndicateur;
	}


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_projet")	
	public Projet getProjet() {
		return projet;
	}

	public void setProjet(Projet projet) {
		this.projet = projet;
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
