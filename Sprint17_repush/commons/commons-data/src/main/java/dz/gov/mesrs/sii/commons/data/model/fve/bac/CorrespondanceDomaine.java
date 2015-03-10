package dz.gov.mesrs.sii.commons.data.model.fve.bac;

// Generated 22 mai 2014 12:17:42 by Hibernate Tools 3.6.0

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * AffectationBachelier generated by hbm2java
 */
@Entity
@Table(name = "correspondance_domaine", schema = "bac")
public class CorrespondanceDomaine implements java.io.Serializable {


	/**
	 * serialVersionUID 
	 * @author rlaib  on : 18 juin 2014  14:22:18
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String codeNiveau1;
	private String libelleNiveau1;
	private String codeNiveau2;
	private String libelleNiveau2;
	private Boolean estClassique;
	private Boolean recrutementNational;
	private String etablissement;
	private String nouveauCode;
	private String ancienCode;

	public CorrespondanceDomaine() {
	}

	/**
	 * [CorrespondanceDomaine.id] Getter 
	 * @author rlaib on : 18 juin 2014  14:17:18
	 * @return the id
	 */
	@SequenceGenerator(name="correspondance_domaine_id_seq_gen", sequenceName="bac.correspondance_domaine_id_seq")
	@Id @GeneratedValue(generator="correspondance_domaine_id_seq_gen")
	@Column(name = "id")
	public int getId() {
		return id;
	}

	/**
	 * [CorrespondanceDomaine.id] Setter 
	 * @author rlaib on : 18 juin 2014  14:17:18
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * [CorrespondanceDomaine.codeNiveau1] Getter 
	 * @author rlaib on : 18 juin 2014  14:17:18
	 * @return the codeNiveau1
	 */
	@Column(name = "code_niveau1", length = 5)
	public String getCodeNiveau1() {
		return codeNiveau1;
	}

	/**
	 * [CorrespondanceDomaine.codeNiveau1] Setter 
	 * @author rlaib on : 18 juin 2014  14:17:18
	 * @param codeNiveau1 the codeNiveau1 to set
	 */
	public void setCodeNiveau1(String codeNiveau1) {
		this.codeNiveau1 = codeNiveau1;
	}

	/**
	 * [CorrespondanceDomaine.libelleNiveau1] Getter 
	 * @author rlaib on : 18 juin 2014  14:17:18
	 * @return the libelleNiveau1
	 */
	@Column(name = "libelle_niveau1", length = 150)
	public String getLibelleNiveau1() {
		return libelleNiveau1;
	}

	/**
	 * [CorrespondanceDomaine.libelleNiveau1] Setter 
	 * @author rlaib on : 18 juin 2014  14:17:18
	 * @param libelleNiveau1 the libelleNiveau1 to set
	 */
	public void setLibelleNiveau1(String libelleNiveau1) {
		this.libelleNiveau1 = libelleNiveau1;
	}

	/**
	 * [CorrespondanceDomaine.codeNiveau2] Getter 
	 * @author rlaib on : 18 juin 2014  14:17:18
	 * @return the codeNiveau2
	 */
	@Column(name = "code_niveau2", length = 5)
	public String getCodeNiveau2() {
		return codeNiveau2;
	}

	/**
	 * [CorrespondanceDomaine.codeNiveau2] Setter 
	 * @author rlaib on : 18 juin 2014  14:17:18
	 * @param codeNiveau2 the codeNiveau2 to set
	 */
	public void setCodeNiveau2(String codeNiveau2) {
		this.codeNiveau2 = codeNiveau2;
	}

	/**
	 * [CorrespondanceDomaine.libelleNiveau2] Getter 
	 * @author rlaib on : 18 juin 2014  14:17:18
	 * @return the libelleNiveau2
	 */
	@Column(name = "libelle_niveau2", length = 150)
	public String getLibelleNiveau2() {
		return libelleNiveau2;
	}

	/**
	 * [CorrespondanceDomaine.libelleNiveau2] Setter 
	 * @author rlaib on : 18 juin 2014  14:17:18
	 * @param libelleNiveau2 the libelleNiveau2 to set
	 */
	public void setLibelleNiveau2(String libelleNiveau2) {
		this.libelleNiveau2 = libelleNiveau2;
	}

	/**
	 * [CorrespondanceDomaine.estClassique] Getter 
	 * @author rlaib on : 18 juin 2014  14:17:18
	 * @return the estClassique
	 */
	@Column(name = "est_classique")
	public Boolean getEstClassique() {
		return estClassique;
	}

	/**
	 * [CorrespondanceDomaine.estClassique] Setter 
	 * @author rlaib on : 18 juin 2014  14:17:18
	 * @param estClassique the estClassique to set
	 */
	public void setEstClassique(Boolean estClassique) {
		this.estClassique = estClassique;
	}

	/**
	 * [CorrespondanceDomaine.recrutementNational] Getter 
	 * @author rlaib on : 18 juin 2014  14:17:18
	 * @return the recrutementNational
	 */
	@Column(name = "recrutement_national")
	public Boolean getRecrutementNational() {
		return recrutementNational;
	}

	/**
	 * [CorrespondanceDomaine.recrutementNational] Setter 
	 * @author rlaib on : 18 juin 2014  14:17:18
	 * @param recrutementNational the recrutementNational to set
	 */
	public void setRecrutementNational(Boolean recrutementNational) {
		this.recrutementNational = recrutementNational;
	}

	/**
	 * [CorrespondanceDomaine.etablissment] Getter 
	 * @author rlaib on : 18 juin 2014  14:17:18
	 * @return the etablissment
	 */
	@Column(name = "etablissement", length = 150)
	public String getEtablissement() {
		return etablissement;
	}

	/**
	 * [CorrespondanceDomaine.etablissment] Setter 
	 * @author rlaib on : 18 juin 2014  14:17:18
	 * @param etablissment the etablissment to set
	 */
	public void setEtablissement(String etablissement) {
		this.etablissement = etablissement;
	}

	/**
	 * [CorrespondanceDomaine.nouveauCode] Getter 
	 * @author rlaib on : 18 juin 2014  14:17:18
	 * @return the nouveauCode
	 */
	@Column(name = "nouveau_code", length = 10)
	public String getNouveauCode() {
		return nouveauCode;
	}

	/**
	 * [CorrespondanceDomaine.nouveauCode] Setter 
	 * @author rlaib on : 18 juin 2014  14:17:18
	 * @param nouveauCode the nouveauCode to set
	 */
	public void setNouveauCode(String nouveauCode) {
		this.nouveauCode = nouveauCode;
	}

	/**
	 * [CorrespondanceDomaine.ancienCode] Getter 
	 * @author rlaib on : 18 juin 2014  14:17:18
	 * @return the ancienCode
	 */
	@Column(name = "ancien_code", length = 10)
	public String getAncienCode() {
		return ancienCode;
	}

	/**
	 * [CorrespondanceDomaine.ancienCode] Setter 
	 * @author rlaib on : 18 juin 2014  14:17:18
	 * @param ancienCode the ancienCode to set
	 */
	public void setAncienCode(String ancienCode) {
		this.ancienCode = ancienCode;
	}

	/**
	 * [CorrespondanceDomaine.toString] Method 
	 * @author rlaib  on : 24 juin 2014  16:01:02
	 * @return
	 */
	@Override
	public String toString() {
		return ((libelleNiveau1 == null) ? "" : libelleNiveau1) + " "+((libelleNiveau2 == null) ? "" : libelleNiveau2) + " "+((etablissement == null) ? "" : etablissement);
	}

	

}
