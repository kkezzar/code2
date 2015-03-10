package dz.gov.mesrs.sii.commons.data.model.grh;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import dz.gov.mesrs.sii.commons.data.model.Identifiable;

/**
 * 
 * @author BELDI Jamel
 *
 */
@Entity
@Table(name = "detail_besoin_formation", schema = "grh")
public class DetailBesoinFormation implements java.io.Serializable,
Identifiable<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Grade grade;
	private FormationCatalogue formationCatalogue;
	private BesoinFormation besoinFormation;
	private Corps corps;
	private Integer nbCandidat;
	private Integer nbForme;
	private Boolean effectue;

	public DetailBesoinFormation() {
	}

	public DetailBesoinFormation(Integer id) {
		this.id = id;
	}

	

	@Id
	@SequenceGenerator(name = "detail_besoin_formation_id_seq", initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "detail_besoin_formation_id_seq")
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "grade")
	public Grade getGrade() {
		return this.grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "formation")
	public FormationCatalogue getFormationCatalogue() {
		return this.formationCatalogue;
	}

	public void setFormationCatalogue(FormationCatalogue formationCatalogue) {
		this.formationCatalogue = formationCatalogue;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "besoin_formation")
	public BesoinFormation getBesoinFormation() {
		return this.besoinFormation;
	}

	public void setBesoinFormation(BesoinFormation besoinFormation) {
		this.besoinFormation = besoinFormation;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "corps")
	public Corps getCorps() {
		return this.corps;
	}

	public void setCorps(Corps corps) {
		this.corps = corps;
	}

	@Column(name = "nb_candidat")
	public Integer getNbCandidat() {
		return this.nbCandidat;
	}

	public void setNbCandidat(Integer nbCandidat) {
		this.nbCandidat = nbCandidat;
	}

	@Column(name = "nb_forme")
	public Integer getNbForme() {
		return this.nbForme;
	}

	public void setNbForme(Integer nbForme) {
		this.nbForme = nbForme;
	}

	@Column(name = "effectue")
	public Boolean getEffectue() {
		return this.effectue;
	}

	public void setEffectue(Boolean effectue) {
		this.effectue = effectue;
	}

	@Transient
	@Override
	public Integer getIdenfiant() {
		return this.getId();
	}

	@Transient
	@Override
	public String getIdentifiantName() {
		return "id";
	}

}
