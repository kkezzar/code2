package dz.gov.mesrs.sii.commons.data.model.gfc;

// Generated 24 nov. 2014 16:52:54 by Hibernate Tools 3.6.0

import java.util.ArrayList;
import java.util.List;

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
import javax.persistence.Transient;

import dz.gov.mesrs.sii.commons.data.model.Identifiable;

/**
 * Paragraphe generated by hbm2java
 */
@Entity
@Table(name = "paragraphe", schema = "gfc")
public class Paragraphe implements java.io.Serializable, Identifiable<Integer> {

	/**
	 * @author Mounir.MESSAOUDI on : 24 nov. 2014 17:33:31
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private Article article;
	private String code;
	private String intituleFr;
	private String intituleAr;
	private String description;

	private String intituleFrCourt;
	private String intituleArCourt;

	private List<DetailsFicheBesoins> detailsFicheBesoinses = new ArrayList<DetailsFicheBesoins>(0);

	public Paragraphe() {
	}

	public Paragraphe(Integer id, String code, String intituleFr, String intituleAr) {
		this.id = id;
		this.code = code;
		this.intituleFr = intituleFr;
		this.intituleAr = intituleAr;
	}

	public Paragraphe(Integer id, Article article, String code, String intituleFr, String intituleAr,String intituleFrCourt, String intituleArCourt,
			String description, List<DetailsFicheBesoins> detailsFicheBesoinses) {
		this.id = id;
		this.article = article;
		this.code = code;
		this.intituleFr = intituleFr;
		this.intituleAr = intituleAr;
		this.description = description;
		this.intituleFrCourt = intituleFrCourt;
		this.intituleArCourt = intituleArCourt;
		this.detailsFicheBesoinses = detailsFicheBesoinses;
	}

	@SequenceGenerator(name = "paragraphe_id_seq", sequenceName = "gfc.paragraphe_id_seq")
	@Id
	@GeneratedValue(generator = "paragraphe_id_seq")
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_article")
	public Article getArticle() {
		return this.article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	@Column(name = "code", nullable = false, length = 10)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "intitule_fr", nullable = false, length = 200)
	public String getIntituleFr() {
		return this.intituleFr;
	}

	public void setIntituleFr(String intituleFr) {
		this.intituleFr = intituleFr;
	}

	@Column(name = "intitule_ar", nullable = false, length = 200)
	public String getIntituleAr() {
		return this.intituleAr;
	}

	public void setIntituleAr(String intituleAr) {
		this.intituleAr = intituleAr;
	}

	@Column(name = "description", length = 200)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "paragraphe")
	public List<DetailsFicheBesoins> getDetailsFicheBesoinses() {
		return this.detailsFicheBesoinses;
	}

	public void setDetailsFicheBesoinses(List<DetailsFicheBesoins> detailsFicheBesoinses) {
		this.detailsFicheBesoinses = detailsFicheBesoinses;
	}

	@Transient
	@Override
	public Integer getIdenfiant() {
		return id;
	}

	@Transient
	@Override
	public String getIdentifiantName() {
		return "id";
	}

	/**
	 * @return the intituleFrCourt
	 */

	@Column(name = "intitule_fr_court", length = 200)
	public String getIntituleFrCourt() {
		return intituleFrCourt;
	}

	/**
	 * @param intituleFrCourt the intituleFrCourt to set
	 */
	public void setIntituleFrCourt(String intituleFrCourt) {
		this.intituleFrCourt = intituleFrCourt;
	}

	/**
	 * @return the intituleArCourt
	 */

	@Column(name = "intitule_ar_court",  length = 200)
	public String getIntituleArCourt() {
		return intituleArCourt;
	}

	/**
	 * @param intituleArCourt the intituleArCourt to set
	 */
	public void setIntituleArCourt(String intituleArCourt) {
		this.intituleArCourt = intituleArCourt;
	}

}
