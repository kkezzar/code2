/**
 * [dz.gov.mesrs.sii.commons.data.model.fve.notation.ParametrageCoefficientExamen.java] 
 * @author MAKERRI Sofiane on : 5 janv. 2015  09:09:42
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

import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.OuvertureOffreFormation;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.Periode;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.RattachementMc;

/**
 * @author MAKERRI Sofiane on : 5 janv. 2015 09:09:42
 */
@Entity
@Table(name = "coefficient_examen", schema = "fve_examen")
public class CoefficientExamen implements java.io.Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author MAKERRI Sofiane on : 5 janv. 2015 09:10:49
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private OuvertureOffreFormation ouvertureOffreFormation;
	private Periode periode;
	private RattachementMc rattachementMc;
	private double coefficientExamen;
	private double coefficientControleContinu;
	private double coefficientControleIntermediaire;

	public CoefficientExamen() {

	}

	/**
	 * [ParametrageCoefficientExamen.id] Getter
	 * 
	 * @author MAKERRI Sofiane on : 5 janv. 2015 09:12:44
	 * @return the id
	 */
	@Id
	@SequenceGenerator(name = "parametrage_coefficient_examen_id_seq_gen", sequenceName = "fve_examen.coefficient_examen_id_seq")
	@GeneratedValue(generator = "parametrage_coefficient_examen_id_seq_gen")
	@Column(name = "id", unique = true, nullable = false)
	public long getId() {
		return id;
	}

	/**
	 * [ParametrageCoefficientExamen.id] Setter
	 * 
	 * @author MAKERRI Sofiane on : 5 janv. 2015 09:12:44
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * [ParametrageCoefficientExamen.ouvertureOffreFormation] Getter
	 * 
	 * @author MAKERRI Sofiane on : 5 janv. 2015 09:12:44
	 * @return the ouvertureOffreFormation
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_oof", nullable = false)
	public OuvertureOffreFormation getOuvertureOffreFormation() {
		return ouvertureOffreFormation;
	}

	/**
	 * [ParametrageCoefficientExamen.ouvertureOffreFormation] Setter
	 * 
	 * @author MAKERRI Sofiane on : 5 janv. 2015 09:12:44
	 * @param ouvertureOffreFormation
	 *            the ouvertureOffreFormation to set
	 */
	public void setOuvertureOffreFormation(
			OuvertureOffreFormation ouvertureOffreFormation) {
		this.ouvertureOffreFormation = ouvertureOffreFormation;
	}

	/**
	 * [ParametrageCoefficientExamen.periode] Getter
	 * 
	 * @author MAKERRI Sofiane on : 5 janv. 2015 09:12:44
	 * @return the periode
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_periode", nullable = false)
	public Periode getPeriode() {
		return periode;
	}

	/**
	 * [ParametrageCoefficientExamen.periode] Setter
	 * 
	 * @author MAKERRI Sofiane on : 5 janv. 2015 09:12:44
	 * @param periode
	 *            the periode to set
	 */
	public void setPeriode(Periode periode) {
		this.periode = periode;
	}
	
	

	/**
	 * [ParametrageCoefficientExamen.rattachementMc] Getter 
	 * @author MAKERRI Sofiane on : 5 janv. 2015  09:41:05
	 * @return the rattachementMc
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_rattachement_mc", nullable = false)
	public RattachementMc getRattachementMc() {
		return rattachementMc;
	}

	/**
	 * [ParametrageCoefficientExamen.rattachementMc] Setter 
	 * @author MAKERRI Sofiane on : 5 janv. 2015  09:41:05
	 * @param rattachementMc the rattachementMc to set
	 */
	public void setRattachementMc(RattachementMc rattachementMc) {
		this.rattachementMc = rattachementMc;
	}

	/**
	 * [ParametrageCoefficientExamen.coefficientExamen] Getter
	 * 
	 * @author MAKERRI Sofiane on : 5 janv. 2015 09:12:44
	 * @return the coefficientExamen
	 */
	@Column(name = "coefficient_examen", nullable = false)
	public double getCoefficientExamen() {
		return coefficientExamen;
	}

	/**
	 * [ParametrageCoefficientExamen.coefficientExamen] Setter
	 * 
	 * @author MAKERRI Sofiane on : 5 janv. 2015 09:12:44
	 * @param coefficientExamen
	 *            the coefficientExamen to set
	 */
	public void setCoefficientExamen(double coefficientExamen) {
		this.coefficientExamen = coefficientExamen;
	}

	/**
	 * [ParametrageCoefficientExamen.coefficientControleContinu] Getter
	 * 
	 * @author MAKERRI Sofiane on : 5 janv. 2015 09:12:44
	 * @return the coefficientControleContinu
	 */
	@Column(name = "coefficient_controle_continu", nullable = false)
	public double getCoefficientControleContinu() {
		return coefficientControleContinu;
	}

	/**
	 * [ParametrageCoefficientExamen.coefficientControleContinu] Setter
	 * 
	 * @author MAKERRI Sofiane on : 5 janv. 2015 09:12:44
	 * @param coefficientControleContinu
	 *            the coefficientControleContinu to set
	 */
	public void setCoefficientControleContinu(double coefficientControleContinu) {
		this.coefficientControleContinu = coefficientControleContinu;
	}

	/**
	 * [ParametrageCoefficientExamen.coefficientControleIntermediaire] Getter
	 * 
	 * @author MAKERRI Sofiane on : 5 janv. 2015 09:12:44
	 * @return the coefficientControleIntermediaire
	 */
	@Column(name = "coefficient_controle_intermediaire", nullable = false)
	public double getCoefficientControleIntermediaire() {
		return coefficientControleIntermediaire;
	}

	/**
	 * [ParametrageCoefficientExamen.coefficientControleIntermediaire] Setter
	 * 
	 * @author MAKERRI Sofiane on : 5 janv. 2015 09:12:44
	 * @param coefficientControleIntermediaire
	 *            the coefficientControleIntermediaire to set
	 */
	public void setCoefficientControleIntermediaire(
			double coefficientControleIntermediaire) {
		this.coefficientControleIntermediaire = coefficientControleIntermediaire;
	}

}
