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


/**
 * @author slimane ramdane   on : 02 fev. 2015 15:33:48
 */
@Entity
@Table(name = "projet_partenaire", schema = "recherche")
public class ProjetPartenaire implements java.io.Serializable , Identifiable<Long>  {
	

	/**
	 * serialVersionUID 
	 * @author slimane ramdane  on : 01 fev. 2015 15:30:03
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Projet projet;
	private Partenaire partenaire;
	private String description;
	
	public ProjetPartenaire() {
		
	}

	
	@Id
	@SequenceGenerator(name="projet_partenaire_id_seq_gen", sequenceName="recherche.projet_partenaire_id_seq")
	@GeneratedValue(generator="projet_partenaire_id_seq_gen")
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_projet")
	public Projet getProjet() {
		return projet;
	}


	public void setProjet(Projet projet) {
		this.projet = projet;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_partenaire")
	public Partenaire getPartenaire() {
		return partenaire;
	}


	public void setPartenaire(Partenaire partenaire) {
		this.partenaire = partenaire;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	@Transient
	@Override
	public Long getIdenfiant() {
		return this.getId();
	}

	


	/**
	 * [Identifiable<Integer>.getIdentifiantName] Method 
	 * Overridden By : @author sram  on : 02 fev. 2015  11:02:27
	 * @return
	 */
	@Transient
	@Override
	public String getIdentifiantName() {
		return "id";
	}

	
	
}
