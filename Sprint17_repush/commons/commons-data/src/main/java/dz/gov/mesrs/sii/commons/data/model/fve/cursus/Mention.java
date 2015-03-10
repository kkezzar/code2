/**
 * [dz.gov.mesrs.sii.fve.business.model.bo.cursus.Mention.java] 
 * @author MAKERRI Sofiane on : 16 oct. 2014  17:09:26
 */
package dz.gov.mesrs.sii.commons.data.model.fve.cursus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.AnneeAcademique;
import dz.gov.mesrs.sii.commons.data.model.referentiel.Nomenclature;

/**
 * @author MAKERRI Sofiane  on : 16 oct. 2014  17:09:26
 */
@Entity
@Table(name = "mention", schema = "cursus")
public class Mention implements java.io.Serializable {

	/**
	 * serialVersionUID 
	 * @author MAKERRI Sofiane  on : 16 oct. 2014  17:11:02
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private AnneeAcademique anneeAcademique;
	private double noteMin;
	private double noteMax;
	private Nomenclature typeMention;
		

	/**
	 * [Mention.Mention()] Constructor
	 * @author MAKERRI Sofiane  on : 16 oct. 2014  17:09:26	
	 */
	public Mention() {
		super();
	}


	/**
	 * [Mention.id] Getter 
	 * @author MAKERRI Sofiane on : 16 oct. 2014  17:13:23
	 * @return the id
	 */
	@SequenceGenerator(name = "mention_id_seq", sequenceName = "cursus.mention_id_seq")
	@Id
	@GeneratedValue(generator = "mention_id_seq")
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}


	/**
	 * [Mention.id] Setter 
	 * @author MAKERRI Sofiane on : 16 oct. 2014  17:13:23
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}


	/**
	 * [Mention.anneeAcademique] Getter 
	 * @author MAKERRI Sofiane on : 22 oct. 2014  17:12:22
	 * @return the anneeAcademique
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_annee_academique", nullable = false)
	public AnneeAcademique getAnneeAcademique() {
		return anneeAcademique;
	}


	/**
	 * [Mention.anneeAcademique] Setter 
	 * @author MAKERRI Sofiane on : 22 oct. 2014  17:12:22
	 * @param anneeAcademique the anneeAcademique to set
	 */
	public void setAnneeAcademique(AnneeAcademique anneeAcademique) {
		this.anneeAcademique = anneeAcademique;
	}


	/**
	 * [Mention.noteMin] Getter 
	 * @author MAKERRI Sofiane on : 23 oct. 2014  11:50:35
	 * @return the noteMin
	 */
	@Column(name = "note_min")
	public double getNoteMin() {
		return noteMin;
	}


	/**
	 * [Mention.noteMin] Setter 
	 * @author MAKERRI Sofiane on : 23 oct. 2014  11:50:35
	 * @param noteMin the noteMin to set
	 */
	public void setNoteMin(double noteMin) {
		this.noteMin = noteMin;
	}


	/**
	 * [Mention.noteMax] Getter 
	 * @author MAKERRI Sofiane on : 23 oct. 2014  11:50:35
	 * @return the noteMax
	 */
	@Column(name = "note_max")
	public double getNoteMax() {
		return noteMax;
	}


	/**
	 * [Mention.noteMax] Setter 
	 * @author MAKERRI Sofiane on : 23 oct. 2014  11:50:35
	 * @param noteMax the noteMax to set
	 */
	public void setNoteMax(double noteMax) {
		this.noteMax = noteMax;
	}


	/**
	 * [Mention.typeMention] Getter 
	 * @author MAKERRI Sofiane on : 23 oct. 2014  11:50:35
	 * @return the typeMention
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "nc_mention", nullable = false)
	public Nomenclature getTypeMention() {
		return typeMention;
	}


	/**
	 * [Mention.typeMention] Setter 
	 * @author MAKERRI Sofiane on : 23 oct. 2014  11:50:35
	 * @param typeMention the typeMention to set
	 */
	public void setTypeMention(Nomenclature typeMention) {
		this.typeMention = typeMention;
	}


	
	

}
