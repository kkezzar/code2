package dz.gov.mesrs.sii.fve.business.model.dto.cursus;

public class TitreAccesDto implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private String numeroTitreAcces;
    private Double moyenne;

    private String refCodeLangueEtrangere1;
    private String langueEtrangere1LibelleLongFr;
    private String langueEtrangere1LibelleLongAr;

    private String refCodeLangueEtrangere2;
    private String langueEtrangere2LibelleLongFr;
    private String langueEtrangere2LibelleLongAr;

    private String refCodeTypeTitre;
    private String typeTitreLibelleLongFr;
    private String typeTitreLibelleLongAr;

    private String refCodeMention;
    private String mentionLibelleLongFr;
    private String mentionLibelleLongAr;

    private String refCodeSpecialite;
    private String libelleFr;
    private String libelleAr;
    private Short anneeObtention;
    private String etablissementObtentionFr;
    private String etablissementObtentionAr;

    private String equivalence;

    private Integer idDossierEtudiant;
    private String numeroMatricule;
    private String refIndividu;

    private Integer idReleveDeNotes;

    private Integer paysId;
    private String libellePaysObtentionLongFr;

    /**
     * [TitreAccesDto.id] Getter
     * 
     * @author yselmane on : 21 mai 2014 11:56:48
     * @return the id
     */
    public int getId() {
	return id;
    }

    /**
     * [TitreAccesDto.id] Setter
     * 
     * @author yselmane on : 21 mai 2014 11:56:48
     * @param id
     *            the id to set
     */
    public void setId(int id) {
	this.id = id;
    }

    /**
     * [TitreAccesDto.numeroTitreAcces] Getter
     * 
     * @author yselmane on : 21 mai 2014 11:56:48
     * @return the numeroTitreAcces
     */
    public String getNumeroTitreAcces() {
	return numeroTitreAcces;
    }

    /**
     * [TitreAccesDto.numeroTitreAcces] Setter
     * 
     * @author yselmane on : 21 mai 2014 11:56:48
     * @param numeroTitreAcces
     *            the numeroTitreAcces to set
     */
    public void setNumeroTitreAcces(String numeroTitreAcces) {
	this.numeroTitreAcces = numeroTitreAcces;
    }

    /**
     * [TitreAccesDto.moyenne] Getter
     * 
     * @author yselmane on : 21 mai 2014 11:56:48
     * @return the moyenne
     */
    public Double getMoyenne() {
	return moyenne;
    }

    /**
     * [TitreAccesDto.moyenne] Setter
     * 
     * @author yselmane on : 21 mai 2014 11:56:48
     * @param moyenne
     *            the moyenne to set
     */
    public void setMoyenne(Double moyenne) {
	this.moyenne = moyenne;
    }

    /**
     * [TitreAccesDto.refCodeLangueEtrangere1] Getter
     * 
     * @author yselmane on : 21 mai 2014 11:56:48
     * @return the refCodeLangueEtrangere1
     */
    public String getRefCodeLangueEtrangere1() {
	return refCodeLangueEtrangere1;
    }

    /**
     * [TitreAccesDto.refCodeLangueEtrangere1] Setter
     * 
     * @author yselmane on : 21 mai 2014 11:56:48
     * @param refCodeLangueEtrangere1
     *            the refCodeLangueEtrangere1 to set
     */
    public void setRefCodeLangueEtrangere1(String refCodeLangueEtrangere1) {
	this.refCodeLangueEtrangere1 = refCodeLangueEtrangere1;
    }

    /**
     * [TitreAccesDto.refCodeLangueEtrangere2] Getter
     * 
     * @author yselmane on : 21 mai 2014 11:56:48
     * @return the refCodeLangueEtrangere2
     */
    public String getRefCodeLangueEtrangere2() {
	return refCodeLangueEtrangere2;
    }

    /**
     * [TitreAccesDto.refCodeLangueEtrangere2] Setter
     * 
     * @author yselmane on : 21 mai 2014 11:56:48
     * @param refCodeLangueEtrangere2
     *            the refCodeLangueEtrangere2 to set
     */
    public void setRefCodeLangueEtrangere2(String refCodeLangueEtrangere2) {
	this.refCodeLangueEtrangere2 = refCodeLangueEtrangere2;
    }

    /**
     * [TitreAccesDto.refCodeTypeTitre] Getter
     * 
     * @author yselmane on : 21 mai 2014 11:56:48
     * @return the refCodeTypeTitre
     */
    public String getRefCodeTypeTitre() {
	return refCodeTypeTitre;
    }

    /**
     * [TitreAccesDto.refCodeTypeTitre] Setter
     * 
     * @author yselmane on : 21 mai 2014 11:56:48
     * @param refCodeTypeTitre
     *            the refCodeTypeTitre to set
     */
    public void setRefCodeTypeTitre(String refCodeTypeTitre) {
	this.refCodeTypeTitre = refCodeTypeTitre;
    }

    /**
     * [TitreAccesDto.refCodeMention] Getter
     * 
     * @author yselmane on : 21 mai 2014 11:56:48
     * @return the refCodeMention
     */
    public String getRefCodeMention() {
	return refCodeMention;
    }

    /**
     * [TitreAccesDto.refCodeMention] Setter
     * 
     * @author yselmane on : 21 mai 2014 11:56:48
     * @param refCodeMention
     *            the refCodeMention to set
     */
    public void setRefCodeMention(String refCodeMention) {
	this.refCodeMention = refCodeMention;
    }

    /**
     * [TitreAccesDto.refCodeSpecialite] Getter
     * 
     * @author yselmane on : 21 mai 2014 11:56:48
     * @return the refCodeSpecialite
     */
    public String getRefCodeSpecialite() {
	return refCodeSpecialite;
    }

    /**
     * [TitreAccesDto.refCodeSpecialite] Setter
     * 
     * @author yselmane on : 21 mai 2014 11:56:48
     * @param refCodeSpecialite
     *            the refCodeSpecialite to set
     */
    public void setRefCodeSpecialite(String refCodeSpecialite) {
	this.refCodeSpecialite = refCodeSpecialite;
    }

    /**
     * [TitreAccesDto.libelleFr] Getter
     * 
     * @author yselmane on : 21 mai 2014 11:56:48
     * @return the libelleFr
     */
    public String getLibelleFr() {
	return libelleFr;
    }

    /**
     * [TitreAccesDto.libelleFr] Setter
     * 
     * @author yselmane on : 21 mai 2014 11:56:48
     * @param libelleFr
     *            the libelleFr to set
     */
    public void setLibelleFr(String libelleFr) {
	this.libelleFr = libelleFr;
    }

    /**
     * [TitreAccesDto.libelleAr] Getter
     * 
     * @author yselmane on : 21 mai 2014 11:56:48
     * @return the libelleAr
     */
    public String getLibelleAr() {
	return libelleAr;
    }

    /**
     * [TitreAccesDto.libelleAr] Setter
     * 
     * @author yselmane on : 21 mai 2014 11:56:48
     * @param libelleAr
     *            the libelleAr to set
     */
    public void setLibelleAr(String libelleAr) {
	this.libelleAr = libelleAr;
    }

    /**
     * [TitreAccesDto.anneeObtention] Getter
     * 
     * @author yselmane on : 21 mai 2014 11:56:48
     * @return the anneeObtention
     */
    public Short getAnneeObtention() {
	return anneeObtention;
    }

    /**
     * [TitreAccesDto.anneeObtention] Setter
     * 
     * @author yselmane on : 21 mai 2014 11:56:48
     * @param anneeObtention
     *            the anneeObtention to set
     */
    public void setAnneeObtention(Short anneeObtention) {
	this.anneeObtention = anneeObtention;
    }

    /**
     * [TitreAccesDto.etablissementObtentionFr] Getter
     * 
     * @author yselmane on : 21 mai 2014 11:56:48
     * @return the etablissementObtentionFr
     */
    public String getEtablissementObtentionFr() {
	return etablissementObtentionFr;
    }

    /**
     * [TitreAccesDto.etablissementObtentionFr] Setter
     * 
     * @author yselmane on : 21 mai 2014 11:56:48
     * @param etablissementObtentionFr
     *            the etablissementObtentionFr to set
     */
    public void setEtablissementObtentionFr(String etablissementObtentionFr) {
	this.etablissementObtentionFr = etablissementObtentionFr;
    }

    /**
     * [TitreAccesDto.etablissementObtentionAr] Getter
     * 
     * @author yselmane on : 21 mai 2014 11:56:48
     * @return the etablissementObtentionAr
     */
    public String getEtablissementObtentionAr() {
	return etablissementObtentionAr;
    }

    /**
     * [TitreAccesDto.etablissementObtentionAr] Setter
     * 
     * @author yselmane on : 21 mai 2014 11:56:48
     * @param etablissementObtentionAr
     *            the etablissementObtentionAr to set
     */
    public void setEtablissementObtentionAr(String etablissementObtentionAr) {
	this.etablissementObtentionAr = etablissementObtentionAr;
    }

    /**
     * [TitreAccesDto.idDossierEtudiant] Getter
     * 
     * @author yselmane on : 21 mai 2014 11:56:48
     * @return the idDossierEtudiant
     */
    public Integer getIdDossierEtudiant() {
	return idDossierEtudiant;
    }

    /**
     * [TitreAccesDto.idDossierEtudiant] Setter
     * 
     * @author yselmane on : 21 mai 2014 11:56:48
     * @param idDossierEtudiant
     *            the idDossierEtudiant to set
     */
    public void setIdDossierEtudiant(Integer idDossierEtudiant) {
	this.idDossierEtudiant = idDossierEtudiant;
    }

    /**
     * [TitreAccesDto.idReleveDeNotes] Getter
     * 
     * @author yselmane on : 21 mai 2014 11:56:48
     * @return the idReleveDeNotes
     */
    public Integer getIdReleveDeNotes() {
	return idReleveDeNotes;
    }

    /**
     * [TitreAccesDto.idReleveDeNotes] Setter
     * 
     * @author yselmane on : 21 mai 2014 11:56:48
     * @param idReleveDeNotes
     *            the idReleveDeNotes to set
     */
    public void setIdReleveDeNotes(Integer idReleveDeNotes) {
	this.idReleveDeNotes = idReleveDeNotes;
    }

    /**
     * [TitreAccesDto.numeroMatricule] Getter
     * 
     * @author yselmane on : 21 mai 2014 16:55:54
     * @return the numeroMatricule
     */
    public String getNumeroMatricule() {
	return numeroMatricule;
    }

    /**
     * [TitreAccesDto.numeroMatricule] Setter
     * 
     * @author yselmane on : 21 mai 2014 16:55:54
     * @param numeroMatricule
     *            the numeroMatricule to set
     */
    public void setNumeroMatricule(String numeroMatricule) {
	this.numeroMatricule = numeroMatricule;
    }

    /**
     * [TitreAccesDto.refIndividu] Getter
     * 
     * @author yselmane on : 21 mai 2014 16:55:54
     * @return the refIndividu
     */
    public String getRefIndividu() {
	return refIndividu;
    }

    /**
     * [TitreAccesDto.refIndividu] Setter
     * 
     * @author yselmane on : 21 mai 2014 16:55:54
     * @param refIndividu
     *            the refIndividu to set
     */
    public void setRefIndividu(String refIndividu) {
	this.refIndividu = refIndividu;
    }

    // ----------libelles des nomencllatures recupere des listebox

    /**
     * [TitreAccesDto.langueEtrangere1LibelleLongFr] Getter
     * 
     * @author yselmane on : 28 mai 2014 17:21:07
     * @return the langueEtrangere1LibelleLongFr
     */
    public String getLangueEtrangere1LibelleLongFr() {
	return langueEtrangere1LibelleLongFr;
    }

    /**
     * [TitreAccesDto.langueEtrangere1LibelleLongFr] Setter
     * 
     * @author yselmane on : 28 mai 2014 17:21:07
     * @param langueEtrangere1LibelleLongFr
     *            the langueEtrangere1LibelleLongFr to set
     */
    public void setLangueEtrangere1LibelleLongFr(String langueEtrangere1LibelleLongFr) {
	this.langueEtrangere1LibelleLongFr = langueEtrangere1LibelleLongFr;
    }

    /**
     * [TitreAccesDto.langueEtrangere1LibelleLongAr] Getter
     * 
     * @author yselmane on : 28 mai 2014 17:21:07
     * @return the langueEtrangere1LibelleLongAr
     */
    public String getLangueEtrangere1LibelleLongAr() {
	return langueEtrangere1LibelleLongAr;
    }

    /**
     * [TitreAccesDto.langueEtrangere1LibelleLongAr] Setter
     * 
     * @author yselmane on : 28 mai 2014 17:21:07
     * @param langueEtrangere1LibelleLongAr
     *            the langueEtrangere1LibelleLongAr to set
     */
    public void setLangueEtrangere1LibelleLongAr(String langueEtrangere1LibelleLongAr) {
	this.langueEtrangere1LibelleLongAr = langueEtrangere1LibelleLongAr;
    }

    /**
     * [TitreAccesDto.langueEtrangere2LibelleLongFr] Getter
     * 
     * @author yselmane on : 28 mai 2014 17:21:07
     * @return the langueEtrangere2LibelleLongFr
     */
    public String getLangueEtrangere2LibelleLongFr() {
	return langueEtrangere2LibelleLongFr;
    }

    /**
     * [TitreAccesDto.langueEtrangere2LibelleLongFr] Setter
     * 
     * @author yselmane on : 28 mai 2014 17:21:07
     * @param langueEtrangere2LibelleLongFr
     *            the langueEtrangere2LibelleLongFr to set
     */
    public void setLangueEtrangere2LibelleLongFr(String langueEtrangere2LibelleLongFr) {
	this.langueEtrangere2LibelleLongFr = langueEtrangere2LibelleLongFr;
    }

    /**
     * [TitreAccesDto.langueEtrangere2LibelleLongAr] Getter
     * 
     * @author yselmane on : 28 mai 2014 17:21:07
     * @return the langueEtrangere2LibelleLongAr
     */
    public String getLangueEtrangere2LibelleLongAr() {
	return langueEtrangere2LibelleLongAr;
    }

    /**
     * [TitreAccesDto.langueEtrangere2LibelleLongAr] Setter
     * 
     * @author yselmane on : 28 mai 2014 17:21:07
     * @param langueEtrangere2LibelleLongAr
     *            the langueEtrangere2LibelleLongAr to set
     */
    public void setLangueEtrangere2LibelleLongAr(String langueEtrangere2LibelleLongAr) {
	this.langueEtrangere2LibelleLongAr = langueEtrangere2LibelleLongAr;
    }

    /**
     * [TitreAccesDto.typeTitreLibelleLongFr] Getter
     * 
     * @author yselmane on : 28 mai 2014 17:21:07
     * @return the typeTitreLibelleLongFr
     */
    public String getTypeTitreLibelleLongFr() {
	return typeTitreLibelleLongFr;
    }

    /**
     * [TitreAccesDto.typeTitreLibelleLongFr] Setter
     * 
     * @author yselmane on : 28 mai 2014 17:21:07
     * @param typeTitreLibelleLongFr
     *            the typeTitreLibelleLongFr to set
     */
    public void setTypeTitreLibelleLongFr(String typeTitreLibelleLongFr) {
	this.typeTitreLibelleLongFr = typeTitreLibelleLongFr;
    }

    /**
     * [TitreAccesDto.typeTitreLibelleLongAr] Getter
     * 
     * @author yselmane on : 28 mai 2014 17:21:07
     * @return the typeTitreLibelleLongAr
     */
    public String getTypeTitreLibelleLongAr() {
	return typeTitreLibelleLongAr;
    }

    /**
     * [TitreAccesDto.typeTitreLibelleLongAr] Setter
     * 
     * @author yselmane on : 28 mai 2014 17:21:07
     * @param typeTitreLibelleLongAr
     *            the typeTitreLibelleLongAr to set
     */
    public void setTypeTitreLibelleLongAr(String typeTitreLibelleLongAr) {
	this.typeTitreLibelleLongAr = typeTitreLibelleLongAr;
    }

    /**
     * [TitreAccesDto.mentionLibelleLongFr] Getter
     * 
     * @author yselmane on : 28 mai 2014 17:21:07
     * @return the mentionLibelleLongFr
     */
    public String getMentionLibelleLongFr() {
	return mentionLibelleLongFr;
    }

    /**
     * [TitreAccesDto.mentionLibelleLongFr] Setter
     * 
     * @author yselmane on : 28 mai 2014 17:21:07
     * @param mentionLibelleLongFr
     *            the mentionLibelleLongFr to set
     */
    public void setMentionLibelleLongFr(String mentionLibelleLongFr) {
	this.mentionLibelleLongFr = mentionLibelleLongFr;
    }

    /**
     * [TitreAccesDto.mentionLibelleLongAr] Getter
     * 
     * @author yselmane on : 28 mai 2014 17:21:07
     * @return the mentionLibelleLongAr
     */
    public String getMentionLibelleLongAr() {
	return mentionLibelleLongAr;
    }

    /**
     * [TitreAccesDto.mentionLibelleLongAr] Setter
     * 
     * @author yselmane on : 28 mai 2014 17:21:07
     * @param mentionLibelleLongAr
     *            the mentionLibelleLongAr to set
     */
    public void setMentionLibelleLongAr(String mentionLibelleLongAr) {
	this.mentionLibelleLongAr = mentionLibelleLongAr;
    }

    /**
     * [TitreAccesDto.equivalence] Getter
     * 
     * @author yselmane on : 5 juin 2014 10:39:35
     * @return the equivalence
     */
    public String getEquivalence() {
	return equivalence;
    }

    /**
     * [TitreAccesDto.equivalence] Setter
     * 
     * @author yselmane on : 5 juin 2014 10:39:35
     * @param equivalence
     *            the equivalence to set
     */
    public void setEquivalence(String equivalence) {
	this.equivalence = equivalence;
    }

  
    public Integer getPaysId() {
        return paysId;
    }

    public void setPaysId(Integer paysId) {
        this.paysId = paysId;
    }

    public String getLibellePaysObtentionLongFr() {
	return libellePaysObtentionLongFr;
    }

    public void setLibellePaysObtentionLongFr(String libellePaysObtentionLongFr) {
	this.libellePaysObtentionLongFr = libellePaysObtentionLongFr;
    }

}
