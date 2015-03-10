package dz.gov.mesrs.sii.fve.business.model.dto.cursus;



public class DiplomeDto implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int id;

	private String code;
	private Short anneeObtention;
	private String refCodeDiplome;
	private String diplomeLibelleLongFr;
	private String diplomeLibelleLongAr;
	
	private String equivalence;
	private String refCodeSpecialite;
	
	private String refCodeMention;
	private String mentionLibelleLongFr;
	private String mentionLibelleLongAr;
	
	private String etablissement;
	private String observation;
	
	private Integer idDossierEtudiant;
	private String numeroMatricule;
	private String refIndividu;

	private Integer idReleveDeNotes;

	public DiplomeDto() {
	}

	public DiplomeDto(int id) {
		this.id = id;
	}

	/**
	 * [DiplomeDto.id] Getter 
	 * @author yselmane on : 1 juin 2014  10:49:40
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * [DiplomeDto.id] Setter 
	 * @author yselmane on : 1 juin 2014  10:49:40
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * [DiplomeDto.code] Getter 
	 * @author yselmane on : 1 juin 2014  10:49:40
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * [DiplomeDto.code] Setter 
	 * @author yselmane on : 1 juin 2014  10:49:40
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * [DiplomeDto.anneeObtention] Getter 
	 * @author yselmane on : 1 juin 2014  10:49:40
	 * @return the anneeObtention
	 */
	public Short getAnneeObtention() {
		return anneeObtention;
	}

	/**
	 * [DiplomeDto.anneeObtention] Setter 
	 * @author yselmane on : 1 juin 2014  10:49:40
	 * @param anneeObtention the anneeObtention to set
	 */
	public void setAnneeObtention(Short anneeObtention) {
		this.anneeObtention = anneeObtention;
	}

	/**
	 * [DiplomeDto.refCodeDiplome] Getter 
	 * @author yselmane on : 1 juin 2014  10:49:40
	 * @return the refCodeDiplome
	 */
	public String getRefCodeDiplome() {
		return refCodeDiplome;
	}

	/**
	 * [DiplomeDto.refCodeDiplome] Setter 
	 * @author yselmane on : 1 juin 2014  10:49:40
	 * @param refCodeDiplome the refCodeDiplome to set
	 */
	public void setRefCodeDiplome(String refCodeDiplome) {
		this.refCodeDiplome = refCodeDiplome;
	}

	/**
	 * [DiplomeDto.diplomeLibelleLongFr] Getter 
	 * @author yselmane on : 1 juin 2014  10:49:40
	 * @return the diplomeLibelleLongFr
	 */
	public String getDiplomeLibelleLongFr() {
		return diplomeLibelleLongFr;
	}

	/**
	 * [DiplomeDto.diplomeLibelleLongFr] Setter 
	 * @author yselmane on : 1 juin 2014  10:49:40
	 * @param diplomeLibelleLongFr the diplomeLibelleLongFr to set
	 */
	public void setDiplomeLibelleLongFr(String diplomeLibelleLongFr) {
		this.diplomeLibelleLongFr = diplomeLibelleLongFr;
	}

	/**
	 * [DiplomeDto.diplomeLibelleLongAr] Getter 
	 * @author yselmane on : 1 juin 2014  10:49:40
	 * @return the diplomeLibelleLongAr
	 */
	public String getDiplomeLibelleLongAr() {
		return diplomeLibelleLongAr;
	}

	/**
	 * [DiplomeDto.diplomeLibelleLongAr] Setter 
	 * @author yselmane on : 1 juin 2014  10:49:40
	 * @param diplomeLibelleLongAr the diplomeLibelleLongAr to set
	 */
	public void setDiplomeLibelleLongAr(String diplomeLibelleLongAr) {
		this.diplomeLibelleLongAr = diplomeLibelleLongAr;
	}

	/**
	 * [DiplomeDto.equivalence] Getter 
	 * @author yselmane on : 1 juin 2014  10:49:40
	 * @return the equivalence
	 */
	public String getEquivalence() {
		return equivalence;
	}

	/**
	 * [DiplomeDto.equivalence] Setter 
	 * @author yselmane on : 1 juin 2014  10:49:40
	 * @param equivalence the equivalence to set
	 */
	public void setEquivalence(String equivalence) {
		this.equivalence = equivalence;
	}

	/**
	 * [DiplomeDto.refCodeSpecialite] Getter 
	 * @author yselmane on : 1 juin 2014  10:49:40
	 * @return the refCodeSpecialite
	 */
	public String getRefCodeSpecialite() {
		return refCodeSpecialite;
	}

	/**
	 * [DiplomeDto.refCodeSpecialite] Setter 
	 * @author yselmane on : 1 juin 2014  10:49:40
	 * @param refCodeSpecialite the refCodeSpecialite to set
	 */
	public void setRefCodeSpecialite(String refCodeSpecialite) {
		this.refCodeSpecialite = refCodeSpecialite;
	}

	/**
	 * [DiplomeDto.refCodeMention] Getter 
	 * @author yselmane on : 1 juin 2014  10:49:40
	 * @return the refCodeMention
	 */
	public String getRefCodeMention() {
		return refCodeMention;
	}

	/**
	 * [DiplomeDto.refCodeMention] Setter 
	 * @author yselmane on : 1 juin 2014  10:49:40
	 * @param refCodeMention the refCodeMention to set
	 */
	public void setRefCodeMention(String refCodeMention) {
		this.refCodeMention = refCodeMention;
	}

	/**
	 * [DiplomeDto.mentionLibelleLongFr] Getter 
	 * @author yselmane on : 1 juin 2014  10:49:40
	 * @return the mentionLibelleLongFr
	 */
	public String getMentionLibelleLongFr() {
		return mentionLibelleLongFr;
	}

	/**
	 * [DiplomeDto.mentionLibelleLongFr] Setter 
	 * @author yselmane on : 1 juin 2014  10:49:40
	 * @param mentionLibelleLongFr the mentionLibelleLongFr to set
	 */
	public void setMentionLibelleLongFr(String mentionLibelleLongFr) {
		this.mentionLibelleLongFr = mentionLibelleLongFr;
	}

	/**
	 * [DiplomeDto.mentionLibelleLongAr] Getter 
	 * @author yselmane on : 1 juin 2014  10:49:40
	 * @return the mentionLibelleLongAr
	 */
	public String getMentionLibelleLongAr() {
		return mentionLibelleLongAr;
	}

	/**
	 * [DiplomeDto.mentionLibelleLongAr] Setter 
	 * @author yselmane on : 1 juin 2014  10:49:40
	 * @param mentionLibelleLongAr the mentionLibelleLongAr to set
	 */
	public void setMentionLibelleLongAr(String mentionLibelleLongAr) {
		this.mentionLibelleLongAr = mentionLibelleLongAr;
	}

	/**
	 * [DiplomeDto.etablissement] Getter 
	 * @author yselmane on : 1 juin 2014  10:49:40
	 * @return the etablissement
	 */
	public String getEtablissement() {
		return etablissement;
	}

	/**
	 * [DiplomeDto.etablissement] Setter 
	 * @author yselmane on : 1 juin 2014  10:49:40
	 * @param etablissement the etablissement to set
	 */
	public void setEtablissement(String etablissement) {
		this.etablissement = etablissement;
	}

	/**
	 * [DiplomeDto.observation] Getter 
	 * @author yselmane on : 1 juin 2014  10:49:40
	 * @return the observation
	 */
	public String getObservation() {
		return observation;
	}

	/**
	 * [DiplomeDto.observation] Setter 
	 * @author yselmane on : 1 juin 2014  10:49:40
	 * @param observation the observation to set
	 */
	public void setObservation(String observation) {
		this.observation = observation;
	}

	/**
	 * [DiplomeDto.idDossierEtudiant] Getter 
	 * @author yselmane on : 1 juin 2014  10:49:40
	 * @return the idDossierEtudiant
	 */
	public Integer getIdDossierEtudiant() {
		return idDossierEtudiant;
	}

	/**
	 * [DiplomeDto.idDossierEtudiant] Setter 
	 * @author yselmane on : 1 juin 2014  10:49:40
	 * @param idDossierEtudiant the idDossierEtudiant to set
	 */
	public void setIdDossierEtudiant(Integer idDossierEtudiant) {
		this.idDossierEtudiant = idDossierEtudiant;
	}

	/**
	 * [DiplomeDto.numeroMatricule] Getter 
	 * @author yselmane on : 1 juin 2014  10:49:40
	 * @return the numeroMatricule
	 */
	public String getNumeroMatricule() {
		return numeroMatricule;
	}

	/**
	 * [DiplomeDto.numeroMatricule] Setter 
	 * @author yselmane on : 1 juin 2014  10:49:40
	 * @param numeroMatricule the numeroMatricule to set
	 */
	public void setNumeroMatricule(String numeroMatricule) {
		this.numeroMatricule = numeroMatricule;
	}

	/**
	 * [DiplomeDto.refIndividu] Getter 
	 * @author yselmane on : 1 juin 2014  10:49:40
	 * @return the refIndividu
	 */
	public String getRefIndividu() {
		return refIndividu;
	}

	/**
	 * [DiplomeDto.refIndividu] Setter 
	 * @author yselmane on : 1 juin 2014  10:49:40
	 * @param refIndividu the refIndividu to set
	 */
	public void setRefIndividu(String refIndividu) {
		this.refIndividu = refIndividu;
	}

	/**
	 * [DiplomeDto.idReleveDeNotes] Getter 
	 * @author yselmane on : 1 juin 2014  10:49:40
	 * @return the idReleveDeNotes
	 */
	public Integer getIdReleveDeNotes() {
		return idReleveDeNotes;
	}

	/**
	 * [DiplomeDto.idReleveDeNotes] Setter 
	 * @author yselmane on : 1 juin 2014  10:49:40
	 * @param idReleveDeNotes the idReleveDeNotes to set
	 */
	public void setIdReleveDeNotes(Integer idReleveDeNotes) {
		this.idReleveDeNotes = idReleveDeNotes;
	}

	

}
