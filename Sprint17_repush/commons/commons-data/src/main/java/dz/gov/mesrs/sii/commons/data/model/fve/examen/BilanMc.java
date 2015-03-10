/**
 * [dz.gov.mesrs.sii.fve.business.model.bo.cursus.BilanMc.java] 
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

import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.RattachementMc;

/**
 * @author MAKERRI Sofiane on : 16 oct. 2014 17:09:26
 */
@Entity
@Table(name = "bilan_mc", schema = "fve_examen")
public class BilanMc implements java.io.Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author MAKERRI Sofiane on : 16 oct. 2014 17:11:02
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private BilanUe bilanUe;
	private RattachementMc rattachementMc;
	private Double moyenneControleContinu;
	private Double noteControleIntermediaire;
	private double credit;
	private double creditObtenu;
	private double creditAcquis;
	private double coefficient;
	private double coefficientControleContinu;
	private double coefficientExamen;
	private double coefficientControleIntermediaire;
	private double noteExamen;
	private double noteJury;
	private double moyenneGenerale;
	private boolean dette;
	private double noteEliminatoire;

	/**
	 * [BilanMc.BilanMc()] Constructor
	 * 
	 * @author MAKERRI Sofiane on : 16 oct. 2014 17:09:26
	 */
	public BilanMc() {
		super();
	}

	/**
	 * [BilanMc.id] Getter
	 * 
	 * @author MAKERRI Sofiane on : 16 oct. 2014 17:13:23
	 * @return the id
	 */
	@SequenceGenerator(name = "bilan_mc_id_seq", sequenceName = "fve_examen.bilan_mc_id_seq")
	@Id
	@GeneratedValue(generator = "bilan_mc_id_seq")
	@Column(name = "id", unique = true, nullable = false)
	public long getId() {
		return id;
	}

	/**
	 * [BilanMc.id] Setter
	 * 
	 * @author MAKERRI Sofiane on : 16 oct. 2014 17:13:23
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * [BilanMc.bilanUe] Getter 
	 * @author MAKERRI Sofiane on : 27 oct. 2014  09:35:08
	 * @return the bilanUe
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_bilan_ue", nullable = false)
	public BilanUe getBilanUe() {
		return bilanUe;
	}

	/**
	 * [BilanMc.bilanUe] Setter 
	 * @author MAKERRI Sofiane on : 27 oct. 2014  09:35:08
	 * @param bilanUe the bilanUe to set
	 */
	public void setBilanUe(BilanUe bilanUe) {
		this.bilanUe = bilanUe;
	}

	/**
	 * [BilanMc.rattachementMc] Getter 
	 * @author MAKERRI Sofiane on : 27 oct. 2014  09:35:08
	 * @return the rattachementMc
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_rattachement_mc", nullable = false)
	public RattachementMc getRattachementMc() {
		return rattachementMc;
	}

	/**
	 * [BilanMc.rattachementMc] Setter 
	 * @author MAKERRI Sofiane on : 27 oct. 2014  09:35:08
	 * @param rattachementMc the rattachementMc to set
	 */
	public void setRattachementMc(RattachementMc rattachementMc) {
		this.rattachementMc = rattachementMc;
	}

	/**
	 * [BilanMc.moyenneCC] Getter 
	 * @author MAKERRI Sofiane on : 27 oct. 2014  09:35:08
	 * @return the moyenneControleContinu
	 */
	@Column(name = "moyenne_cc")
	public Double getMoyenneControleContinu() {
		return moyenneControleContinu;
	}

	/**
	 * [BilanMc.moyenneCC] Setter 
	 * @author MAKERRI Sofiane on : 27 oct. 2014  09:35:08
	 * @param moyenneControleContinu the moyenneControleContinu to set
	 */
	public void setMoyenneControleContinu(Double moyenneControleContinu) {
		this.moyenneControleContinu = moyenneControleContinu;
	}

	/**
	 * [BilanMc.coefficient] Getter 
	 * @author MAKERRI Sofiane on : 27 oct. 2014  09:35:08
	 * @return the coefficient
	 */
	@Column(name = "coefficient")
	public double getCoefficient() {
		return coefficient;
	}

	/**
	 * [BilanMc.coefficient] Setter 
	 * @author MAKERRI Sofiane on : 27 oct. 2014  09:35:08
	 * @param coefficient the coefficient to set
	 */
	public void setCoefficient(double coefficient) {
		this.coefficient = coefficient;
	}
	
	

	/**
	 * [BilanMc.credit] Getter 
	 * @author MAKERRI Sofiane on : 29 oct. 2014  09:01:37
	 * @return the credit
	 */
	@Column(name = "credit")
	public double getCredit() {
		return credit;
	}

	/**
	 * [BilanMc.credit] Setter 
	 * @author MAKERRI Sofiane on : 29 oct. 2014  09:01:37
	 * @param credit the credit to set
	 */
	public void setCredit(double credit) {
		this.credit = credit;
	}
	
	

	/**
	 * [BilanMc.creditObtenu] Getter 
	 * @author MAKERRI Sofiane on : 29 oct. 2014  10:19:44
	 * @return the creditObtenu
	 */
	@Column(name = "credit_obtenu")
	public double getCreditObtenu() {
		return creditObtenu;
	}

	/**
	 * [BilanMc.creditObtenu] Setter 
	 * @author MAKERRI Sofiane on : 29 oct. 2014  10:19:44
	 * @param creditObtenu the creditObtenu to set
	 */
	public void setCreditObtenu(double creditObtenu) {
		this.creditObtenu = creditObtenu;
	}

	/**
	 * [BilanMc.noteExamen] Getter 
	 * @author MAKERRI Sofiane on : 27 oct. 2014  09:35:08
	 * @return the noteExamen
	 */
	@Column(name = "note_examen")
	public double getNoteExamen() {
		return noteExamen;
	}

	/**
	 * [BilanMc.noteExamen] Setter 
	 * @author MAKERRI Sofiane on : 27 oct. 2014  09:35:08
	 * @param noteExamen the noteExamen to set
	 */
	public void setNoteExamen(double noteExamen) {
		this.noteExamen = noteExamen;
	}
	
	

	/**
	 * [BilanMc.noteJury] Getter 
	 * @author MAKERRI Sofiane on : 16 déc. 2014  10:04:53
	 * @return the noteJury
	 */
	@Column(name = "note_jury")
	public double getNoteJury() {
		return noteJury;
	}

	/**
	 * [BilanMc.noteJury] Setter 
	 * @author MAKERRI Sofiane on : 16 déc. 2014  10:04:53
	 * @param noteJury the noteJury to set
	 */
	public void setNoteJury(double noteJury) {
		this.noteJury = noteJury;
	}

	/**
	 * [BilanMc.dette] Getter 
	 * @author MAKERRI Sofiane on : 27 oct. 2014  09:35:08
	 * @return the dette
	 */
	@Column(name = "dette")
	public boolean getDette() {
		return dette;
	}

	/**
	 * [BilanMc.dette] Setter 
	 * @author MAKERRI Sofiane on : 27 oct. 2014  09:35:08
	 * @param dette the dette to set
	 */
	public void setDette(boolean dette) {
		this.dette = dette;
	}

	/**
	 * [BilanMc.moyenneGenerale] Getter 
	 * @author MAKERRI Sofiane on : 16 déc. 2014  14:03:38
	 * @return the moyenneGenerale
	 */
	@Column(name = "moyenne_generale")
	public double getMoyenneGenerale() {
		return moyenneGenerale;
	}

	/**
	 * [BilanMc.moyenneGenerale] Setter 
	 * @author MAKERRI Sofiane on : 16 déc. 2014  14:03:38
	 * @param moyenneGenerale the moyenneGenerale to set
	 */
	public void setMoyenneGenerale(double moyenneGenerale) {
		this.moyenneGenerale = moyenneGenerale;
	}

	/**
	 * [BilanMc.coefficientControleContinu] Getter 
	 * @author MAKERRI Sofiane on : 5 janv. 2015  17:12:12
	 * @return the coefficientControleContinu
	 */
	@Column(name = "coefficient_cc")
	public double getCoefficientControleContinu() {
		return coefficientControleContinu;
	}

	/**
	 * [BilanMc.coefficientCC] Setter 
	 * @author MAKERRI Sofiane on : 5 janv. 2015  17:12:12
	 * @param coefficientControleContinu the coefficientControleContinu to set
	 */
	public void setCoefficientControleContinu(double coefficientControleContinu) {
		this.coefficientControleContinu = coefficientControleContinu;
	}

	/**
	 * [BilanMc.coefficientExamen] Getter 
	 * @author MAKERRI Sofiane on : 5 janv. 2015  17:12:12
	 * @return the coefficientExamen
	 */
	@Column(name = "coefficient_examen")
	public double getCoefficientExamen() {
		return coefficientExamen;
	}

	/**
	 * [BilanMc.coefficientExamen] Setter 
	 * @author MAKERRI Sofiane on : 5 janv. 2015  17:12:12
	 * @param coefficientExamen the coefficientExamen to set
	 */
	public void setCoefficientExamen(double coefficientExamen) {
		this.coefficientExamen = coefficientExamen;
	}

	/**
	 * [BilanMc.coefficientControleIntermediaire] Getter 
	 * @author MAKERRI Sofiane on : 5 janv. 2015  17:12:12
	 * @return the coefficientControleIntermediaire
	 */
	@Column(name = "coefficient_controle_intermediaire")
	public double getCoefficientControleIntermediaire() {
		return coefficientControleIntermediaire;
	}

	/**
	 * [BilanMc.coefficientControleIntermediaire] Setter 
	 * @author MAKERRI Sofiane on : 5 janv. 2015  17:12:12
	 * @param coefficientControleIntermediaire the coefficientControleIntermediaire to set
	 */
	public void setCoefficientControleIntermediaire(
			double coefficientControleIntermediaire) {
		this.coefficientControleIntermediaire = coefficientControleIntermediaire;
	}

	/**
	 * [BilanMc.noteControleIntermediaire] Getter 
	 * @author MAKERRI Sofiane on : 12 janv. 2015  07:59:41
	 * @return the noteControleIntermediaire
	 */
	@Column(name = "note_controle_intermediaire")
	public Double getNoteControleIntermediaire() {
		return noteControleIntermediaire;
	}

	/**
	 * [BilanMc.noteControleIntermediaire] Setter 
	 * @author MAKERRI Sofiane on : 12 janv. 2015  07:59:41
	 * @param noteControleIntermediaire the noteControleIntermediaire to set
	 */
	public void setNoteControleIntermediaire(Double noteControleIntermediaire) {
		this.noteControleIntermediaire = noteControleIntermediaire;
	}

	/**
	 * [BilanMc.creditAcquis] Getter 
	 * @author MAKERRI Sofiane on : 15 janv. 2015  15:55:44
	 * @return the creditAcquis
	 */
	@Column(name = "credit_acquis")
	public double getCreditAcquis() {
		return creditAcquis;
	}

	/**
	 * [BilanMc.creditAcquis] Setter 
	 * @author MAKERRI Sofiane on : 15 janv. 2015  15:55:44
	 * @param creditAcquis the creditAcquis to set
	 */
	public void setCreditAcquis(double creditAcquis) {
		this.creditAcquis = creditAcquis;
	}

	/**
	 * [BilanMc.noteEliminatoire] Getter 
	 * @author MAKERRI Sofiane on : 20 janv. 2015  16:15:30
	 * @return the noteEliminatoire
	 */
	@Column(name = "note_eliminatoire")
	public double getNoteEliminatoire() {
		return noteEliminatoire;
	}

	/**
	 * [BilanMc.noteEliminatoire] Setter 
	 * @author MAKERRI Sofiane on : 20 janv. 2015  16:15:30
	 * @param noteEliminatoire the noteEliminatoire to set
	 */
	public void setNoteEliminatoire(double noteEliminatoire) {
		this.noteEliminatoire = noteEliminatoire;
	}	
	
	
		
}
