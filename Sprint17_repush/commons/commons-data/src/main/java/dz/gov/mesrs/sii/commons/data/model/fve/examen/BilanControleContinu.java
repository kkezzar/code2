/**
 * [dz.gov.mesrs.sii.fve.business.model.bo.cursus.BilanControleContinu.java] 
 * @author MAKERRI Sofiane on : 16 oct. 2014  17:09:26
 */
package dz.gov.mesrs.sii.commons.data.model.fve.examen;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import dz.gov.mesrs.sii.commons.data.model.fve.cursus.AffectationGroupePedagogique;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.AnneeAcademique;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.OuvertureOffreFormation;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.Periode;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.RattachementMc;

/**
 * @author MAKERRI Sofiane  on : 16 oct. 2014  17:09:26
 */
@Entity
@Table(name = "bilan_controle_continu", schema = "fve_examen")
public class BilanControleContinu implements java.io.Serializable {

	/**
	 * serialVersionUID 
	 * @author MAKERRI Sofiane  on : 16 oct. 2014  17:11:02
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private AffectationGroupePedagogique affectationGroupePedagogique;
	private OuvertureOffreFormation oof;
	private Periode periode;
	private RattachementMc rattachementMc;
	private AnneeAcademique anneeAcademique;
	private Double note;
	

	/**
	 * [BilanControleContinu.BilanControleContinu()] Constructor
	 * @author MAKERRI Sofiane  on : 16 oct. 2014  17:09:26	
	 */
	public BilanControleContinu() {
		// TODO Auto-generated constructor stub
	}


	/**
	 * [BilanControleContinu.id] Getter 
	 * @author MAKERRI Sofiane on : 16 oct. 2014  17:13:23
	 * @return the id
	 */
	@SequenceGenerator(name = "bilan_controle_continu_id_seq", sequenceName = "fve_examen.bilan_controle_continu_id_seq")
	@Id
	@GeneratedValue(generator = "bilan_controle_continu_id_seq")
	@Column(name = "id", unique = true, nullable = false)
	public long getId() {
		return id;
	}


	/**
	 * [BilanControleContinu.id] Setter 
	 * @author MAKERRI Sofiane on : 16 oct. 2014  17:13:23
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}


	/**
	 * [BilanControleContinu.affectationGroupePedagogique] Getter 
	 * @author MAKERRI Sofiane on : 16 oct. 2014  17:13:23
	 * @return the affectationGroupePedagogique
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_affectation_groupe_pedagogique", nullable = false)
	public AffectationGroupePedagogique getAffectationGroupePedagogique() {
		return affectationGroupePedagogique;
	}


	/**
	 * [BilanControleContinu.affectationGroupePedagogique] Setter 
	 * @author MAKERRI Sofiane on : 16 oct. 2014  17:13:23
	 * @param affectationGroupePedagogique the affectationGroupePedagogique to set
	 */
	public void setAffectationGroupePedagogique(
			AffectationGroupePedagogique affectationGroupePedagogique) {
		this.affectationGroupePedagogique = affectationGroupePedagogique;
	}


	/**
	 * [BilanControleContinu.oof] Getter 
	 * @author MAKERRI Sofiane on : 16 oct. 2014  17:13:23
	 * @return the oof
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_oof", nullable = false)
	public OuvertureOffreFormation getOof() {
		return oof;
	}


	/**
	 * [BilanControleContinu.oof] Setter 
	 * @author MAKERRI Sofiane on : 16 oct. 2014  17:13:23
	 * @param oof the oof to set
	 */
	public void setOof(OuvertureOffreFormation oof) {
		this.oof = oof;
	}


	/**
	 * [BilanControleContinu.periode] Getter 
	 * @author MAKERRI Sofiane on : 16 oct. 2014  17:13:23
	 * @return the periode
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_periode", nullable = false)
	public Periode getPeriode() {
		return periode;
	}


	/**
	 * [BilanControleContinu.periode] Setter 
	 * @author MAKERRI Sofiane on : 16 oct. 2014  17:13:23
	 * @param periode the periode to set
	 */
	public void setPeriode(Periode periode) {
		this.periode = periode;
	}


	/**
	 * [BilanControleContinu.rattachementMc] Getter 
	 * @author MAKERRI Sofiane on : 16 oct. 2014  17:13:23
	 * @return the rattachementMc
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_rattachement_mc", nullable = false)
	public RattachementMc getRattachementMc() {
		return rattachementMc;
	}


	/**
	 * [BilanControleContinu.rattachementMc] Setter 
	 * @author MAKERRI Sofiane on : 16 oct. 2014  17:13:23
	 * @param rattachementMc the rattachementMc to set
	 */
	public void setRattachementMc(RattachementMc rattachementMc) {
		this.rattachementMc = rattachementMc;
	}

	
	

	/**
	 * [BilanControleContinu.anneeAcademique] Getter 
	 * @author MAKERRI Sofiane on : 19 oct. 2014  08:53:13
	 * @return the anneeAcademique
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_annee_academique", nullable = false)
	public AnneeAcademique getAnneeAcademique() {
		return anneeAcademique;
	}


	/**
	 * [BilanControleContinu.anneeAcademique] Setter 
	 * @author MAKERRI Sofiane on : 19 oct. 2014  08:53:13
	 * @param anneeAcademique the anneeAcademique to set
	 */
	public void setAnneeAcademique(AnneeAcademique anneeAcademique) {
		this.anneeAcademique = anneeAcademique;
	}


	/**
	 * [BilanControleContinu.note] Getter 
	 * @author MAKERRI Sofiane on : 16 oct. 2014  17:13:23
	 * @return the note
	 */
	@Column(name = "note")
	public Double getNote() {
		return note;
	}


	/**
	 * [BilanControleContinu.note] Setter 
	 * @author MAKERRI Sofiane on : 16 oct. 2014  17:13:23
	 * @param note the note to set
	 */
	public void setNote(Double note) {
		this.note = note;
	}
	
	

}
