package dz.gov.mesrs.sii.commons.data.model.fve.offreformation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import dz.gov.mesrs.sii.commons.data.model.referentiel.RefEtablissement;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefStructure;

/**
 * @author rlaib  on : 8 oct. 2014  16:07:17
 */
@Entity
@Table(name = "offre_formation_structure", schema = "lmd")
public class OffreFormationStructure implements java.io.Serializable {

	/**
	 * serialVersionUID 
	 * @author  Rafik LAIB  on : 18 avr. 2014  14:45:40
	 */
	private static final long serialVersionUID = -6185093698645946527L;
	private int id;
	private OffreFormation offreFormation;
	private RefEtablissement refEtablissement;
	private RefStructure refStructure;
	
	public OffreFormationStructure() {
	}
	
	@SequenceGenerator(name="offre_formation_structure_id_seq_gen", sequenceName="lmd.offre_formation_structure_id_seq")
	@Id @GeneratedValue(generator="offre_formation_structure_id_seq_gen")
	@Column(name = "id")
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * [OffreFormationStructure.offreFormation] Getter 
	 * @author rlaib on : 8 oct. 2014  16:04:10
	 * @return the offreFormation
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_offre_formation", nullable = false)
	public OffreFormation getOffreFormation() {
		return offreFormation;
	}

	/**
	 * [OffreFormationStructure.offreFormation] Setter 
	 * @author rlaib on : 8 oct. 2014  16:04:10
	 * @param offreFormation the offreFormation to set
	 */
	public void setOffreFormation(OffreFormation offreFormation) {
		this.offreFormation = offreFormation;
	}

	/**
	 * [OffreFormationStructure.refEtablissement] Getter 
	 * @author rlaib on : 8 oct. 2014  16:04:10
	 * @return the refEtablissement
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_etablissement", nullable = false)
	public RefEtablissement getRefEtablissement() {
		return refEtablissement;
	}

	/**
	 * [OffreFormationStructure.refEtablissement] Setter 
	 * @author rlaib on : 8 oct. 2014  16:04:10
	 * @param refEtablissement the refEtablissement to set
	 */
	public void setRefEtablissement(RefEtablissement refEtablissement) {
		this.refEtablissement = refEtablissement;
	}

	/**
	 * [OffreFormationStructure.refStructure] Getter 
	 * @author rlaib on : 8 oct. 2014  16:04:10
	 * @return the refStructure
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_structure", nullable = false)
	public RefStructure getRefStructure() {
		return refStructure;
	}

	/**
	 * [OffreFormationStructure.refStructure] Setter 
	 * @author rlaib on : 8 oct. 2014  16:04:10
	 * @param refStructure the refStructure to set
	 */
	public void setRefStructure(RefStructure refStructure) {
		this.refStructure = refStructure;
	}
	
	
}
