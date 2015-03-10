package dz.gov.mesrs.sii.commons.data.model.recherche;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import dz.gov.mesrs.sii.commons.data.model.Identifiable;

@Entity
@Table(name = "grille_evaluation", schema = "recherche",uniqueConstraints=@UniqueConstraint(columnNames={"code"}))
public class GrilleEvaluation implements java.io.Serializable , Identifiable<Long>  {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String code;
	private String intituleFr;
	private String intituleAr;
	
	public GrilleEvaluation() {
		
	}

	@Id
	@SequenceGenerator(name="grille_evaluation_id_seq_gen", sequenceName="recherche.grille_evaluation_id_seq")
	@GeneratedValue(generator="grille_evaluation_id_seq_gen")
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "code",length=20, nullable=false)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "intitule_fr",length=500, nullable=false)
	public String getIntituleFr() {
		return intituleFr;
	}

	public void setIntituleFr(String intituleFr) {
		this.intituleFr = intituleFr;
	}

	@Column(name = "intitule_ar",length=500)
	public String getIntituleAr() {
		return intituleAr;
	}

	public void setIntituleAr(String intituleAr) {
		this.intituleAr = intituleAr;
	}

	@Transient
	@Override
	public Long getIdenfiant() {
		return this.getId();
	}

	@Transient
	@Override
	public String getIdentifiantName() {
		return "id";
	}
}
