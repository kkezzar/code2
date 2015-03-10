package dz.gov.mesrs.sii.commons.data.model.fve.offreformation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Classe d'entité représentant une répartition d'une unités d'enseignement
 * afféctées une offre de formation dans un semestre.
 * 
 * @author Kheireddine OMRANI
 * 
 */
@Entity
@Table(name = "repartition_ue", schema = "lmd")
public class RepartitionUe implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3624725482676563906L;

	private int id;
	private ParcoursType parcoursType;
	private String refCodeSemestre;
	private UniteEnseignement uniteEnseignement;
	private GroupeRepartitionUeAChoix groupeRepartitionUeAChoix;

	private double coefficient;
	private double noteObtension;
	private double noteDeBase;
	private Double noteEliminatoire;

	private Boolean optionnelle;
	private Boolean aChoix;
	private Periode periode;

	public RepartitionUe() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	public Double getNoteEliminatoire() {
		return this.noteEliminatoire;
	}

	public void setNoteEliminatoire(Double noteEliminatoire) {
		this.noteEliminatoire = noteEliminatoire;
	}

	@Column(name = "optionnelle")
	public Boolean getOptionnelle() {
		return optionnelle;
	}

	public void setOptionnelle(Boolean optionnelle) {
		this.optionnelle = optionnelle;
	}

	@Column(name = "a_choix")
	public Boolean getAChoix() {
		return aChoix;
	}

	public void setAChoix(Boolean aChoix) {
		this.aChoix = aChoix;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_parcours_type", nullable = false)
	public ParcoursType getParcoursType() {
		return this.parcoursType;
	}

	public void setParcoursType(ParcoursType parcoursType) {
		this.parcoursType = parcoursType;
	}

	@Column(name = "ref_code_semestre")
	public String getRefCodeSemestre() {
		return refCodeSemestre;
	}

	public void setRefCodeSemestre(String refCodeSemestre) {
		this.refCodeSemestre = refCodeSemestre;
	}

@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_unite_enseignement", nullable = false)
	public UniteEnseignement getUniteEnseignement() {
		return this.uniteEnseignement;
	}

	public void setUniteEnseignement(UniteEnseignement uniteEnseignement) {
		this.uniteEnseignement = uniteEnseignement;
	}	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_groupe_a_choix")
	public GroupeRepartitionUeAChoix getGroupeRepartitionUeAChoix() {
		return groupeRepartitionUeAChoix;
	}

	public void setGroupeRepartitionUeAChoix(GroupeRepartitionUeAChoix groupeRepartitionUeAChoix) {
		this.groupeRepartitionUeAChoix = groupeRepartitionUeAChoix;
	}
	
	/**
	 * [UniteEnseignement.periode] Getter 
	 * @author rlaib on : 14 août 2014  16:51:50
	 * @return the periode
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_periode")
	public Periode getPeriode() {
		return periode;
	}

	/**
	 * [UniteEnseignement.periode] Setter 
	 * @author rlaib on : 14 août 2014  16:51:50
	 * @param periode the periode to set
	 */
	public void setPeriode(Periode periode) {
		this.periode = periode;
	}

}
