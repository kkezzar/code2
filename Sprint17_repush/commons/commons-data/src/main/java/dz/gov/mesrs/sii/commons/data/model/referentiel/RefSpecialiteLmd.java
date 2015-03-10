package dz.gov.mesrs.sii.commons.data.model.referentiel;

// Generated 21 avr. 2014 10:16:28 by Hibernate Tools 3.6.0

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

/**
 * RefSpecialiteLmd generated by hbm2java
 */
@Entity
@Table(name = "ref_specialite_lmd", schema = "ppm")
public class RefSpecialiteLmd implements java.io.Serializable {

	/**
	 * serialVersionUID 
	 * @author BELBRIK Oualid  on : 24 avr. 2014  15:31:47
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String identifiant;
	private RefFiliereLmd refFiliereLmd;
	private RefDomaineLMD refDomaineLMD;
	private String lcSpecialiteArabe;
	private String lcSpecialite;
	private String llSpecialiteArabe;
	private String llSpecialite;
	private String descriptionSpecialiteArabe;
	private String descriptionSpecialite;

	public RefSpecialiteLmd() {
	}

	public RefSpecialiteLmd(String identifiant) {
		this.identifiant = identifiant;
	}

	public RefSpecialiteLmd(String identifiant, RefFiliereLmd refFiliereLmd, RefDomaineLMD refDomaineLMD,
			String lcSpecialiteArabe, String lcSpecialite,
			String llSpecialiteArabe, String llSpecialite,
			String descriptionSpecialiteArabe, String descriptionSpecialite) {
		this.identifiant = identifiant;
		this.refFiliereLmd = refFiliereLmd;
		this.refDomaineLMD = refDomaineLMD;
		this.lcSpecialiteArabe = lcSpecialiteArabe;
		this.lcSpecialite = lcSpecialite;
		this.llSpecialiteArabe = llSpecialiteArabe;
		this.llSpecialite = llSpecialite;
		this.descriptionSpecialiteArabe = descriptionSpecialiteArabe;
		this.descriptionSpecialite = descriptionSpecialite;
	}

	@Id
	@SequenceGenerator(name = "seq_specialite_lmd", initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_specialite_lmd")
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name = "identifiant", unique = true, nullable = false, length = 50)
	public String getIdentifiant() {
		return this.identifiant;
	}

	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "filiere")
	public RefFiliereLmd getRefFiliereLmd() {
		return this.refFiliereLmd;
	}

	public void setRefFiliereLmd(RefFiliereLmd refFiliereLmd) {
		this.refFiliereLmd = refFiliereLmd;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "domainelmd")
	public RefDomaineLMD getRefDomaineLMD() {
		return this.refDomaineLMD;
	}

	public void setRefDomaineLMD(RefDomaineLMD refDomaineLMD) {
		this.refDomaineLMD = refDomaineLMD;
	}
	

	@Column(name = "lc_specialite_arabe", length = 100)
	public String getLcSpecialiteArabe() {
		return this.lcSpecialiteArabe;
	}

	public void setLcSpecialiteArabe(String lcSpecialiteArabe) {
		this.lcSpecialiteArabe = lcSpecialiteArabe;
	}

	@Column(name = "lc_specialite", length = 100)
	public String getLcSpecialite() {
		return this.lcSpecialite;
	}

	public void setLcSpecialite(String lcSpecialite) {
		this.lcSpecialite = lcSpecialite;
	}

	@Column(name = "ll_specialite_arabe", length = 100)
	public String getLlSpecialiteArabe() {
		return this.llSpecialiteArabe;
	}

	public void setLlSpecialiteArabe(String llSpecialiteArabe) {
		this.llSpecialiteArabe = llSpecialiteArabe;
	}

	@Column(name = "ll_specialite", length = 100)
	public String getLlSpecialite() {
		return this.llSpecialite;
	}

	public void setLlSpecialite(String llSpecialite) {
		this.llSpecialite = llSpecialite;
	}

	@Column(name = "description_specialite_arabe", length = 500)
	public String getDescriptionSpecialiteArabe() {
		return this.descriptionSpecialiteArabe;
	}

	public void setDescriptionSpecialiteArabe(String descriptionSpecialiteArabe) {
		this.descriptionSpecialiteArabe = descriptionSpecialiteArabe;
	}

	@Column(name = "description_specialite", length = 500)
	public String getDescriptionSpecialite() {
		return this.descriptionSpecialite;
	}

	public void setDescriptionSpecialite(String descriptionSpecialite) {
		this.descriptionSpecialite = descriptionSpecialite;
	}

}