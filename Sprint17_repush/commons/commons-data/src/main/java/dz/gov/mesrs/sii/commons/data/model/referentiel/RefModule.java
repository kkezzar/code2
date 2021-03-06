package dz.gov.mesrs.sii.commons.data.model.referentiel;

// Generated 17 f�vr. 2014 18:36:28 by Hibernate Tools 3.6.0

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * RefModule generated by hbm2java
 */
@Entity
@Table(name = "ref_module", schema = "ppm")
public class RefModule implements java.io.Serializable {

	/**
	 * serialVersionUID 
	 * @author MAKERRI Sofiane  on : 4 mai 2014  08:58:53
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private RefDomaine refDomaine;
	private String identifiant;
	private String nom;
	private String nomArabe;
	private int rang;
	private Set<RefFonction> refFonctions = new HashSet<RefFonction>(0);
	
	private String icon;
	private String tooltip;
	private String description;
	private String helpPage;

	public RefModule() {
	}

	public RefModule(int id, String identifiant, String nom, int rang) {
		this.id = id;
		this.identifiant = identifiant;
		this.nom = nom;
		this.rang = rang;
	}

	public RefModule(int id, RefDomaine refDomaine, String identifiant,
			String nom, int rang, Set<RefFonction> refFonctions) {
		this.id = id;
		this.refDomaine = refDomaine;
		this.identifiant = identifiant;
		this.nom = nom;
		this.rang = rang;
		this.refFonctions = refFonctions;
	}

	@Id
	@SequenceGenerator(name="seq_module", initialValue = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "seq_module")
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "domaine")
	public RefDomaine getRefDomaine() {
		return this.refDomaine;
	}

	public void setRefDomaine(RefDomaine refDomaine) {
		this.refDomaine = refDomaine;
	}

	@Column(name = "identifiant", nullable = false, length = 25)
	public String getIdentifiant() {
		return this.identifiant;
	}

	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}

	@Column(name = "nom", nullable = false, length = 120)
	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@Column(name = "rang", nullable = false)
	public int getRang() {
		return this.rang;
	}

	public void setRang(int rang) {
		this.rang = rang;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "refModule")
	public Set<RefFonction> getRefFonctions() {
		return this.refFonctions;
	}

	public void setRefFonctions(Set<RefFonction> refFonctions) {
		this.refFonctions = refFonctions;
	}
	
	@Column(name = "nom_arabe", nullable = false, length = 120)
	public String getNomArabe() {
		return nomArabe;
	}

	public void setNomArabe(String nomArabe) {
		this.nomArabe = nomArabe;
	}

	@Column(name = "icon", nullable = true, length = 100)
	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	@Column(name = "tooltip", nullable = true)
	public String getTooltip() {
		return tooltip;
	}

	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
	}

	@Column(name = "description", nullable = true)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "help_page", nullable = true)
	public String getHelpPage() {
		return helpPage;
	}
	
	public void setHelpPage(String helpPage) {
		this.helpPage = helpPage;
	}
	

}
