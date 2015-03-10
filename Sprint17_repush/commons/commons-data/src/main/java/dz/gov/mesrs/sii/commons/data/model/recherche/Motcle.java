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
 * @author slimane ramdane   on : 02 fev. 2015 15:33:48
 */
@Entity
@Table(name = "mot_cle", schema = "recherche")
public class Motcle implements java.io.Serializable , Identifiable<Long>  {
	

	/**
	 * serialVersionUID 
	 * @author slimane ramdane  on : 01 fev. 2015 15:30:03
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Nomenclature motCleNomenclature;
	private Projet projet;
		
	public Motcle() {		
	}
	
	@Id	
	@SequenceGenerator(name="mcle_id_seq_gen", sequenceName="recherche.mcle_id_seq")
	@GeneratedValue(generator="mcle_id_seq_gen")
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_nc_mcle", nullable = false)	
	public Nomenclature getMotCleNomenclature() {
		return motCleNomenclature;
	}


	public void setMotCleNomenclature(Nomenclature motCleNomenclature) {
		this.motCleNomenclature = motCleNomenclature;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_projet",nullable = false)	
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
