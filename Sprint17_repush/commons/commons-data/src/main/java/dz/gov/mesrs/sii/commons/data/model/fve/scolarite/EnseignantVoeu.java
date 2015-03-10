/**
 * [dz.gov.mesrs.sii.fve.business.model.bo.scolarite.EnseignantVoeu.java] 
 * @author rlaib on : 12 oct. 2014  14:03:13
 */
package dz.gov.mesrs.sii.commons.data.model.fve.scolarite;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author rlaib  on : 12 oct. 2014  14:03:13
 */
@Entity
@Table(name = "enseignant_voeu", schema = "fve_scolarite")
public class EnseignantVoeu implements java.io.Serializable  {
	
	/**
	 * serialVersionUID 
	 * @author rlaib  on : 29 oct. 2014  16:04:52
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private EnseignantFicheVoeux enseignantFicheVoeux;
	private int priorite;
	private String description;
	private Set<EnseignantVoeuLigne> enseignantVoeuLignes = new HashSet<EnseignantVoeuLigne>(0);
	
	public EnseignantVoeu() {
		
	}

	/**
	 * [EnseignantFicheVoeux.id] Getter 
	 * @author rlaib on : 12 oct. 2014  14:12:13
	 * @return the id
	 */
	@SequenceGenerator(name="enseignant_voeu_id_seq_gen", sequenceName="fve_scolarite.enseignant_voeu_id_seq")
	@Id @GeneratedValue(generator="enseignant_voeu_id_seq_gen")
	@Column(name = "id")
	public int getId() {
		return id;
	}

	/**
	 * [EnseignantFicheVoeux.id] Setter 
	 * @author rlaib on : 12 oct. 2014  14:12:13
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * [EnseignantVoeu.enseignantFicheVoeux] Getter 
	 * @author rlaib on : 12 oct. 2014  14:22:27
	 * @return the enseignantFicheVoeux
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_fiche_enseignant", nullable = false)
	public EnseignantFicheVoeux getEnseignantFicheVoeux() {
		return enseignantFicheVoeux;
	}

	/**
	 * [EnseignantVoeu.enseignantFicheVoeux] Setter 
	 * @author rlaib on : 12 oct. 2014  14:22:27
	 * @param enseignantFicheVoeux the enseignantFicheVoeux to set
	 */
	public void setEnseignantFicheVoeux(EnseignantFicheVoeux enseignantFicheVoeux) {
		this.enseignantFicheVoeux = enseignantFicheVoeux;
	}

	/**
	 * [EnseignantVoeu.priorite] Getter 
	 * @author rlaib on : 12 oct. 2014  14:22:27
	 * @return the priorite
	 */
	@Column(name = "priorite")
	public int getPriorite() {
		return priorite;
	}

	/**
	 * [EnseignantVoeu.priorite] Setter 
	 * @author rlaib on : 12 oct. 2014  14:22:27
	 * @param priorite the priorite to set
	 */
	public void setPriorite(int priorite) {
		this.priorite = priorite;
	}

	/**
	 * [EnseignantVoeu.description] Getter 
	 * @author rlaib on : 12 oct. 2014  14:22:27
	 * @return the description
	 */
	@Column(name = "description", length = 200)
	public String getDescription() {
		return description;
	}

	/**
	 * [EnseignantVoeu.description] Setter 
	 * @author rlaib on : 12 oct. 2014  14:22:27
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * [EnseignantVoeu.enseignantVoeuLignes] Getter 
	 * @author rlaib on : 23 oct. 2014  14:13:22
	 * @return the enseignantVoeuLignes
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "enseignantVoeu", targetEntity= EnseignantVoeuLigne.class, cascade = CascadeType.ALL)
	public Set<EnseignantVoeuLigne> getEnseignantVoeuLignes() {
		return enseignantVoeuLignes;
	}

	/**
	 * [EnseignantVoeu.enseignantVoeuLignes] Setter 
	 * @author rlaib on : 23 oct. 2014  14:13:22
	 * @param enseignantVoeuLignes the enseignantVoeuLignes to set
	 */
	public void setEnseignantVoeuLignes(
			Set<EnseignantVoeuLigne> enseignantVoeuLignes) {
		this.enseignantVoeuLignes = enseignantVoeuLignes;
	}

	
}
