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
import dz.gov.mesrs.sii.commons.data.model.referentiel.Nomenclature;

/**
 * 
 * @author BELDI Jamel
 * 
 */
@Entity
@Table(name = "formation_catalogue", schema = "grh")
public class FormationCatalogue implements java.io.Serializable,
		Identifiable<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Nomenclature nomenclatureByTheme;
	private Nomenclature nomenclatureByMethode;
	private CatalogueFormation catalogueFormation;
	private String code;
	private String intitule;
	private String objectif;
	private Integer duree;
	private Integer maximunParticipant;

	public FormationCatalogue() {
	}

	public FormationCatalogue(Integer id) {
		this.id = id;
	}

	public FormationCatalogue(Integer id, Nomenclature nomenclatureByTheme,
			Nomenclature nomenclatureByMethode,
			CatalogueFormation catalogueFormation, String code,
			String intitule, String objectif, Integer duree,
			Integer maximunParticipant) {
		this.id = id;
		this.nomenclatureByTheme = nomenclatureByTheme;
		this.nomenclatureByMethode = nomenclatureByMethode;
		this.catalogueFormation = catalogueFormation;
		this.code = code;
		this.intitule = intitule;
		this.objectif = objectif;
		this.duree = duree;
		this.maximunParticipant = maximunParticipant;
	}

	@Id
	@SequenceGenerator(name = "formation_catalogue_id_seq", initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "formation_catalogue_id_seq")
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "theme")
	public Nomenclature getNomenclatureByTheme() {
		return this.nomenclatureByTheme;
	}

	public void setNomenclatureByTheme(Nomenclature nomenclatureByTheme) {
		this.nomenclatureByTheme = nomenclatureByTheme;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "methode")
	public Nomenclature getNomenclatureByMethode() {
		return this.nomenclatureByMethode;
	}

	public void setNomenclatureByMethode(Nomenclature nomenclatureByMethode) {
		this.nomenclatureByMethode = nomenclatureByMethode;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "catalogue")
	public CatalogueFormation getCatalogueFormation() {
		return this.catalogueFormation;
	}

	public void setCatalogueFormation(CatalogueFormation catalogueFormation) {
		this.catalogueFormation = catalogueFormation;
	}

	@Column(name = "code")
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "intitule")
	public String getIntitule() {
		return this.intitule;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

	@Column(name = "objectif")
	public String getObjectif() {
		return this.objectif;
	}

	public void setObjectif(String objectif) {
		this.objectif = objectif;
	}

	@Column(name = "duree")
	public Integer getDuree() {
		return this.duree;
	}

	public void setDuree(Integer duree) {
		this.duree = duree;
	}

	@Column(name = "maximun_participant")
	public Integer getMaximunParticipant() {
		return this.maximunParticipant;
	}

	public void setMaximunParticipant(Integer maximunParticipant) {
		this.maximunParticipant = maximunParticipant;
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
