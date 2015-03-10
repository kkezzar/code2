package dz.gov.mesrs.sii.commons.data.model.fve.offreformation;

import java.util.List;

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

import dz.gov.mesrs.sii.commons.data.model.fve.examen.CoefficientExamen;

/**
 * Classe d'entité représentant le rattachement d'une MC à une UE.
 * 
 * @author Kheireddine OMRANI
 * 
 */
@Entity
@Table(name = "rattachement_mc", schema = "lmd")
public class RattachementMc implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3624725482676563906L;

	private int id;
	private UniteEnseignement uniteEnseignement;
	private MatiereConstitutive matiereConstitutive;
	private GroupeRattachementMcAChoix groupeRattachementMcAChoix;

	private double coefficient;
	private double credit;
	private double noteObtension;
	private double noteDeBase;
	private double noteEliminatoire;

	private boolean optionnelle;
	private boolean aChoix;
	private List<CoefficientExamen> coefficientExamens;

	public RattachementMc() {
	}

	public RattachementMc(int id) {
		this.id = id;
	}
	
	@SequenceGenerator(name = "rattachement_mc_id_seq", sequenceName = "lmd.rattachement_mc_id_seq")
	@Id
	@GeneratedValue(generator = "rattachement_mc_id_seq")
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "coefficient", precision = 17, scale = 17)
	public double getCoefficient() {
		return coefficient;
	}

	public void setCoefficient(double coefficient) {
		this.coefficient = coefficient;
	}

	@Column(name = "note_obtension", precision = 17, scale = 17)
	public double getNoteObtension() {
		return this.noteObtension;
	}

	public void setNoteObtension(double noteObtension) {
		this.noteObtension = noteObtension;
	}
	
	
	@Column(name = "note_de_base", precision = 17, scale = 17)
	public double getNoteDeBase() {
		return this.noteDeBase;
	}

	public void setNoteDeBase(double noteDeBase) {
		this.noteDeBase = noteDeBase;
	}

	@Column(name = "note_eliminatoire", precision = 17, scale = 17)
	public double getNoteEliminatoire() {
		return this.noteEliminatoire;
	}

	public void setNoteEliminatoire(double noteEliminatoire) {
		this.noteEliminatoire = noteEliminatoire;
	}

	@Column(name = "optionnelle")
	public boolean getOptionnelle() {
		return optionnelle;
	}

	public void setOptionnelle(boolean optionnelle) {
		this.optionnelle = optionnelle;
	}

	@Column(name = "a_choix")
	public boolean getAChoix() {
		return aChoix;
	}

	public void setAChoix(boolean aChoix) {
		this.aChoix = aChoix;
	}
	
	

	
	/**
	 * [RattachementMc.credit] Getter 
	 * @author MAKERRI Sofiane on : 29 oct. 2014  09:17:35
	 * @return the credit
	 */
	@Column(name = "credit")
	public double getCredit() {
		return credit;
	}

	/**
	 * [RattachementMc.credit] Setter 
	 * @author MAKERRI Sofiane on : 29 oct. 2014  09:17:35
	 * @param credit the credit to set
	 */
	public void setCredit(double credit) {
		this.credit = credit;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_mc", nullable = false)
	public MatiereConstitutive getMatiereConstitutive() {
		return matiereConstitutive;
	}

	public void setMatiereConstitutive(MatiereConstitutive matiereConstitutive) {
		this.matiereConstitutive = matiereConstitutive;
	} 

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_ue", nullable = false)
	public UniteEnseignement getUniteEnseignement() {
		return this.uniteEnseignement;
	}

	public void setUniteEnseignement(UniteEnseignement uniteEnseignement) {
		this.uniteEnseignement = uniteEnseignement;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_groupe_a_choix")
	public GroupeRattachementMcAChoix getGroupeRattachementMcAChoix() {
		return groupeRattachementMcAChoix;
	}

	public void setGroupeRattachementMcAChoix(GroupeRattachementMcAChoix groupeRattachementMcAChoix) {
		this.groupeRattachementMcAChoix = groupeRattachementMcAChoix;
	}

	/**
	 * [RattachementMc.coefficientExamens] Getter 
	 * @author MAKERRI Sofiane on : 6 janv. 2015  09:35:25
	 * @return the coefficientExamens
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "rattachementMc", cascade = CascadeType.ALL, orphanRemoval = true)
	public List<CoefficientExamen> getCoefficientExamens() {
		return coefficientExamens;
	}

	/**
	 * [RattachementMc.coefficientExamens] Setter 
	 * @author MAKERRI Sofiane on : 6 janv. 2015  09:35:25
	 * @param coefficientExamens the coefficientExamens to set
	 */
	public void setCoefficientExamens(List<CoefficientExamen> coefficientExamens) {
		this.coefficientExamens = coefficientExamens;
	}
	
	

	
}
