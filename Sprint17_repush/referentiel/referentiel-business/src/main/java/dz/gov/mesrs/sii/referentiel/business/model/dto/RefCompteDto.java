package dz.gov.mesrs.sii.referentiel.business.model.dto;



import java.util.Date;


public class RefCompteDto implements java.io.Serializable {

	/**
	 * serialVersionUID 
	 * @author BELDI Jamel  on : 24 fevr. 2014  09:54:48
	 */
	private static final long serialVersionUID = 1L;
	private Integer idCompte;
	private String nomUtilisateur;
	private String motPasse;
	private Date dateDebut;
	private Date dateFin;
	private String reponse;
	private Boolean premiereSession;
	private Boolean changementMotPasse;
	private Boolean accessJourFerie;
	private Boolean admin;
	private String questionSecrete;
	private String situation;
	//private String motPasseNonCrypte;
	/******** Etablissement ***************/
	private Integer etabId;
	private String etabIdf;
	private String etabLcFr;
	private String etabLcAr;
	private String etabLlFr;
	private String etabLlAr;
	private String etabAncienCode;
	//private RefIndividu refIndividu;
	private Integer individuId;
	private String individuIdentifiant;
	private String individuNomArabe;
	private String individuNomLatin;
	private String individuPrenomArabe;
	private String individuPrenomLatin;
	
	//private RefPlageAdresse refPlageAdresse;
	private Integer plageAdresseId;
	private String plageAdresseIdentifiant;
	private String plageAdresseNom;
	private String plageAdresseAdresseDebut;
	private String plageAdresseAdresseFin;
	//private RefHoraireAccess refHoraireAccess;
	private Integer horaireAccessId;
	private String horaireAccessIdentifiant;
	private String horaireAccessNom;
	private Date horaireAccessHeureDebut;
	private Date horaireAccessHeureFin;
	/**
	 * Current Situation
	 * 
	 */
	private Integer idSituation;
	private Date dateSituation;
	private String llSituationAr;
	private String llSituationFr;
	
	/**
	 * confirm password
	 */
	private String confirmationMotPasse;
	/**
	 * Actual Password
	 */
	private String motPasseActuel;
	
	
	//nomenclatureByQuestionSecrete
	private Integer questionSecreteId;
	private String questionSecreteLibelleLongFr;
	private String questionSecreteLibelleLongAr;
	private String questionSecreteLibelleCourtFr;
	//affectation et role
	private Integer idAffectation;
	private Integer idRole;
	
	private boolean activate;
	
	
	/**
	 * [RefCompteDto.questionSecreteId] Getter 
	 * @author BELDI Jamel on : 1 mars 2014  11:36:10
	 * @return the questionSecreteId
	 */
	public Integer getQuestionSecreteId() {
		return questionSecreteId;
	}
	/**
	 * [RefCompteDto.questionSecreteId] Setter 
	 * @author BELDI Jamel on : 1 mars 2014  11:36:10
	 * @param questionSecreteId the questionSecreteId to set
	 */
	public void setQuestionSecreteId(Integer questionSecreteId) {
		this.questionSecreteId = questionSecreteId;
	}
	/**
	 * [RefCompteDto.questionSecreteLibelleLongFr] Getter 
	 * @author BELDI Jamel on : 1 mars 2014  11:36:10
	 * @return the questionSecreteLibelleLongFr
	 */
	public String getQuestionSecreteLibelleLongFr() {
		return questionSecreteLibelleLongFr;
	}
	/**
	 * [RefCompteDto.questionSecreteLibelleLongFr] Setter 
	 * @author BELDI Jamel on : 1 mars 2014  11:36:10
	 * @param questionSecreteLibelleLongFr the questionSecreteLibelleLongFr to set
	 */
	public void setQuestionSecreteLibelleLongFr(String questionSecreteLibelleLongFr) {
		this.questionSecreteLibelleLongFr = questionSecreteLibelleLongFr;
	}
	/**
	 * [RefCompteDto.questionSecreteLibelleLongAr] Getter 
	 * @author BELDI Jamel on : 1 mars 2014  11:36:10
	 * @return the questionSecreteLibelleLongAr
	 */
	public String getQuestionSecreteLibelleLongAr() {
		return questionSecreteLibelleLongAr;
	}
	/**
	 * [RefCompteDto.questionSecreteLibelleLongAr] Setter 
	 * @author BELDI Jamel on : 1 mars 2014  11:36:10
	 * @param questionSecreteLibelleLongAr the questionSecreteLibelleLongAr to set
	 */
	public void setQuestionSecreteLibelleLongAr(String questionSecreteLibelleLongAr) {
		this.questionSecreteLibelleLongAr = questionSecreteLibelleLongAr;
	}
	/**
	 * [RefCompteDto.questionSecreteLibelleCourtFr] Getter 
	 * @author BELDI Jamel on : 1 mars 2014  11:36:10
	 * @return the questionSecreteLibelleCourtFr
	 */
	public String getQuestionSecreteLibelleCourtFr() {
		return questionSecreteLibelleCourtFr;
	}
	/**
	 * [RefCompteDto.questionSecreteLibelleCourtFr] Setter 
	 * @author BELDI Jamel on : 1 mars 2014  11:36:10
	 * @param questionSecreteLibelleCourtFr the questionSecreteLibelleCourtFr to set
	 */
	public void setQuestionSecreteLibelleCourtFr(
			String questionSecreteLibelleCourtFr) {
		this.questionSecreteLibelleCourtFr = questionSecreteLibelleCourtFr;
	}
	/**
	 * [RefCompteDto.questionSecreteLibelleCourtAr] Getter 
	 * @author BELDI Jamel on : 1 mars 2014  11:36:10
	 * @return the questionSecreteLibelleCourtAr
	 */
	public String getQuestionSecreteLibelleCourtAr() {
		return questionSecreteLibelleCourtAr;
	}
	/**
	 * [RefCompteDto.questionSecreteLibelleCourtAr] Setter 
	 * @author BELDI Jamel on : 1 mars 2014  11:36:10
	 * @param questionSecreteLibelleCourtAr the questionSecreteLibelleCourtAr to set
	 */
	public void setQuestionSecreteLibelleCourtAr(
			String questionSecreteLibelleCourtAr) {
		this.questionSecreteLibelleCourtAr = questionSecreteLibelleCourtAr;
	}
	/**
	 * [RefCompteDto.questionSecreteCode] Getter 
	 * @author BELDI Jamel on : 1 mars 2014  11:36:10
	 * @return the questionSecreteCode
	 */
	public String getQuestionSecreteCode() {
		return questionSecreteCode;
	}
	/**
	 * [RefCompteDto.questionSecreteCode] Setter 
	 * @author BELDI Jamel on : 1 mars 2014  11:36:10
	 * @param questionSecreteCode the questionSecreteCode to set
	 */
	public void setQuestionSecreteCode(String questionSecreteCode) {
		this.questionSecreteCode = questionSecreteCode;
	}
	private String questionSecreteLibelleCourtAr;
	private String questionSecreteCode;
	
	/**
	 * [RefCompteDto.confirmationMotPasse] Getter 
	 * @author BELDI Jamel on : 25 fevr. 2014  09:34:31
	 * @return the confirmationMotPasse
	 */
	public String getConfirmationMotPasse() {
		return confirmationMotPasse;
	}
	/**
	 * [RefCompteDto.motPasseActuel] Getter 
	 * @author BELDI Jamel on : 1 mars 2014  10:23:27
	 * @return the motPasseActuel
	 */
	public String getMotPasseActuel() {
		return motPasseActuel;
	}
	/**
	 * [RefCompteDto.motPasseActuel] Setter 
	 * @author BELDI Jamel on : 1 mars 2014  10:23:27
	 * @param motPasseActuel the motPasseActuel to set
	 */
	public void setMotPasseActuel(String motPasseActuel) {
		this.motPasseActuel = motPasseActuel;
	}
	/**
	 * [RefCompteDto.confirmationMotPasse] Setter 
	 * @author BELDI Jamel on : 25 fevr. 2014  09:34:31
	 * @param confirmationMotPasse the confirmationMotPasse to set
	 */
	public void setConfirmationMotPasse(String confirmationMotPasse) {
		this.confirmationMotPasse = confirmationMotPasse;
	}
	/**
	 * [RefCompteDto.dateSituation] Getter 
	 * @author BELDI Jamel on : 24 fevr. 2014  10:13:11
	 * @return the dateSituation
	 */
	public Date getDateSituation() {
		return dateSituation;
	}
	/**
	 * [RefCompteDto.dateSituation] Setter 
	 * @author BELDI Jamel on : 24 fevr. 2014  10:13:11
	 * @param dateSituation the dateSituation to set
	 */
	public void setDateSituation(Date dateSituation) {
		this.dateSituation = dateSituation;
	}
	/**
	 * [RefCompteDto.llSituationAr] Getter 
	 * @author BELDI Jamel on : 24 fevr. 2014  10:13:11
	 * @return the llSituationAr
	 */
	public String getLlSituationAr() {
		return llSituationAr;
	}
	/**
	 * [RefCompteDto.llSituationAr] Setter 
	 * @author BELDI Jamel on : 24 fevr. 2014  10:13:11
	 * @param llSituationAr the llSituationAr to set
	 */
	public void setLlSituationAr(String llSituationAr) {
		this.llSituationAr = llSituationAr;
	}
	/**
	 * [RefCompteDto.llSituationFr] Getter 
	 * @author BELDI Jamel on : 24 fevr. 2014  10:13:11
	 * @return the llSituationFr
	 */
	public String getLlSituationFr() {
		return llSituationFr;
	}
	/**
	 * [RefCompteDto.llSituationFr] Setter 
	 * @author BELDI Jamel on : 24 fevr. 2014  10:13:11
	 * @param llSituationFr the llSituationFr to set
	 */
	public void setLlSituationFr(String llSituationFr) {
		this.llSituationFr = llSituationFr;
	}
	public RefCompteDto() {
		super();
	}
	/**
	 * [RefCompte.idCompte] Getter 
	 * @author BELDI Jamel on : 24 fevr. 2014  09:54:38
	 * @return the idCompte
	 */
	public Integer getIdCompte() {
		return idCompte;
	}
	/**
	 * [RefCompte.idCompte] Setter 
	 * @author BELDI Jamel on : 24 fevr. 2014  09:54:38
	 * @param idCompte the idCompte to set
	 */
	public void setIdCompte(Integer idCompte) {
		this.idCompte = idCompte;
	}
	/**
	 * [RefCompte.nomUtilisateur] Getter 
	 * @author BELDI Jamel on : 24 fevr. 2014  09:54:38
	 * @return the nomUtilisateur
	 */
	public String getNomUtilisateur() {
		return nomUtilisateur;
	}
	/**
	 * [RefCompte.nomUtilisateur] Setter 
	 * @author BELDI Jamel on : 24 fevr. 2014  09:54:38
	 * @param nomUtilisateur the nomUtilisateur to set
	 */
	public void setNomUtilisateur(String nomUtilisateur) {
		this.nomUtilisateur = nomUtilisateur;
	}
	/**
	 * [RefCompte.motPasse] Getter 
	 * @author BELDI Jamel on : 24 fevr. 2014  09:54:38
	 * @return the motPasse
	 */
	public String getMotPasse() {
		return motPasse;
	}
	/**
	 * [RefCompte.motPasse] Setter 
	 * @author BELDI Jamel on : 24 fevr. 2014  09:54:38
	 * @param motPasse the motPasse to set
	 */
	public void setMotPasse(String motPasse) {
		this.motPasse = motPasse;
	}
	/**
	 * [RefCompte.dateDebut] Getter 
	 * @author BELDI Jamel on : 24 fevr. 2014  09:54:38
	 * @return the dateDebut
	 */
	public Date getDateDebut() {
		return dateDebut;
	}
	/**
	 * [RefCompte.dateDebut] Setter 
	 * @author BELDI Jamel on : 24 fevr. 2014  09:54:38
	 * @param dateDebut the dateDebut to set
	 */
	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}
	/**
	 * [RefCompte.dateFin] Getter 
	 * @author BELDI Jamel on : 24 fevr. 2014  09:54:38
	 * @return the dateFin
	 */
	public Date getDateFin() {
		return dateFin;
	}
	/**
	 * [RefCompte.dateFin] Setter 
	 * @author BELDI Jamel on : 24 fevr. 2014  09:54:38
	 * @param dateFin the dateFin to set
	 */
	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}
	/**
	 * [RefCompte.reponse] Getter 
	 * @author BELDI Jamel on : 24 fevr. 2014  09:54:38
	 * @return the reponse
	 */
	public String getReponse() {
		return reponse;
	}
	/**
	 * [RefCompte.reponse] Setter 
	 * @author BELDI Jamel on : 24 fevr. 2014  09:54:38
	 * @param reponse the reponse to set
	 */
	public void setReponse(String reponse) {
		this.reponse = reponse;
	}
	/**
	 * [RefCompte.premiereSession] Getter 
	 * @author BELDI Jamel on : 24 fevr. 2014  09:54:38
	 * @return the premiereSession
	 */
	public Boolean getPremiereSession() {
		return premiereSession;
	}
	/**
	 * [RefCompte.premiereSession] Setter 
	 * @author BELDI Jamel on : 24 fevr. 2014  09:54:38
	 * @param premiereSession the premiereSession to set
	 */
	public void setPremiereSession(Boolean premiereSession) {
		this.premiereSession = premiereSession;
	}
	/**
	 * [RefCompte.changementMotPasse] Getter 
	 * @author BELDI Jamel on : 24 fevr. 2014  09:54:38
	 * @return the changementMotPasse
	 */
	public Boolean getChangementMotPasse() {
		return changementMotPasse;
	}
	/**
	 * [RefCompte.changementMotPasse] Setter 
	 * @author BELDI Jamel on : 24 fevr. 2014  09:54:38
	 * @param changementMotPasse the changementMotPasse to set
	 */
	public void setChangementMotPasse(Boolean changementMotPasse) {
		this.changementMotPasse = changementMotPasse;
	}
	/**
	 * [RefCompte.accessJourFerie] Getter 
	 * @author BELDI Jamel on : 24 fevr. 2014  09:54:38
	 * @return the accessJourFerie
	 */
	public Boolean getAccessJourFerie() {
		return accessJourFerie;
	}
	/**
	 * [RefCompte.accessJourFerie] Setter 
	 * @author BELDI Jamel on : 24 fevr. 2014  09:54:38
	 * @param accessJourFerie the accessJourFerie to set
	 */
	public void setAccessJourFerie(Boolean accessJourFerie) {
		this.accessJourFerie = accessJourFerie;
	}
	/**
	 * [RefCompte.questionSecrete] Getter 
	 * @author BELDI Jamel on : 24 fevr. 2014  09:54:38
	 * @return the questionSecrete
	 */
	public String getQuestionSecrete() {
		return questionSecrete;
	}
	/**
	 * [RefCompte.questionSecrete] Setter 
	 * @author BELDI Jamel on : 24 fevr. 2014  09:54:38
	 * @param questionSecrete the questionSecrete to set
	 */
	public void setQuestionSecrete(String questionSecrete) {
		this.questionSecrete = questionSecrete;
	}
	/**
	 * [RefCompte.situation] Getter 
	 * @author BELDI Jamel on : 24 fevr. 2014  09:54:38
	 * @return the situation
	 */
	public String getSituation() {
		return situation;
	}
	/**
	 * [RefCompte.situation] Setter 
	 * @author BELDI Jamel on : 24 fevr. 2014  09:54:38
	 * @param situation the situation to set
	 */
	public void setSituation(String situation) {
		this.situation = situation;
	}
	
	/**
	 * [RefCompteDto.individuIdentifiant] Getter 
	 * @author BELDI Jamel on : 24 fevr. 2014  12:30:22
	 * @return the individuIdentifiant
	 */
	public String getIndividuIdentifiant() {
		return individuIdentifiant;
	}
	/**
	 * [RefCompteDto.individuIdentifiant] Setter 
	 * @author BELDI Jamel on : 24 fevr. 2014  12:30:22
	 * @param individuIdentifiant the individuIdentifiant to set
	 */
	public void setIndividuIdentifiant(String individuIdentifiant) {
		this.individuIdentifiant = individuIdentifiant;
	}
	/**
	 * [RefCompteDto.individuNomArabe] Getter 
	 * @author BELDI Jamel on : 24 fevr. 2014  12:30:22
	 * @return the individuNomArabe
	 */
	public String getIndividuNomArabe() {
		return individuNomArabe;
	}
	/**
	 * [RefCompteDto.individuNomArabe] Setter 
	 * @author BELDI Jamel on : 24 fevr. 2014  12:30:22
	 * @param individuNomArabe the individuNomArabe to set
	 */
	public void setIndividuNomArabe(String individuNomArabe) {
		this.individuNomArabe = individuNomArabe;
	}
	/**
	 * [RefCompteDto.individuNomLatin] Getter 
	 * @author BELDI Jamel on : 24 fevr. 2014  12:30:22
	 * @return the individuNomLatin
	 */
	public String getIndividuNomLatin() {
		return individuNomLatin;
	}
	/**
	 * [RefCompteDto.individuNomLatin] Setter 
	 * @author BELDI Jamel on : 24 fevr. 2014  12:30:22
	 * @param individuNomLatin the individuNomLatin to set
	 */
	public void setIndividuNomLatin(String individuNomLatin) {
		this.individuNomLatin = individuNomLatin;
	}
	/**
	 * [RefCompteDto.individuPrenomArabe] Getter 
	 * @author BELDI Jamel on : 24 fevr. 2014  12:30:22
	 * @return the individuPrenomArabe
	 */
	public String getIndividuPrenomArabe() {
		return individuPrenomArabe;
	}
	/**
	 * [RefCompteDto.individuPrenomArabe] Setter 
	 * @author BELDI Jamel on : 24 fevr. 2014  12:30:22
	 * @param individuPrenomArabe the individuPrenomArabe to set
	 */
	public void setIndividuPrenomArabe(String individuPrenomArabe) {
		this.individuPrenomArabe = individuPrenomArabe;
	}
	/**
	 * [RefCompteDto.individuPrenomLatin] Getter 
	 * @author BELDI Jamel on : 24 fevr. 2014  12:30:22
	 * @return the individuPrenomLatin
	 */
	public String getIndividuPrenomLatin() {
		return individuPrenomLatin;
	}
	/**
	 * [RefCompteDto.individuPrenomLatin] Setter 
	 * @author BELDI Jamel on : 24 fevr. 2014  12:30:22
	 * @param individuPrenomLatin the individuPrenomLatin to set
	 */
	public void setIndividuPrenomLatin(String individuPrenomLatin) {
		this.individuPrenomLatin = individuPrenomLatin;
	}
	/**
	 * [RefCompteDto.plageAdresseId] Getter 
	 * @author BELDI Jamel on : 24 fevr. 2014  10:10:40
	 * @return the plageAdresseId
	 */
	public Integer getPlageAdresseId() {
		return plageAdresseId;
	}
	/**
	 * [RefCompteDto.plageAdresseId] Setter 
	 * @author BELDI Jamel on : 24 fevr. 2014  10:10:40
	 * @param plageAdresseId the plageAdresseId to set
	 */
	public void setPlageAdresseId(Integer plageAdresseId) {
		this.plageAdresseId = plageAdresseId;
	}
	/**
	 * [RefCompteDto.plageAdresseIdentifiant] Getter 
	 * @author BELDI Jamel on : 24 fevr. 2014  10:10:40
	 * @return the plageAdresseIdentifiant
	 */
	public String getPlageAdresseIdentifiant() {
		return plageAdresseIdentifiant;
	}
	/**
	 * [RefCompteDto.plageAdresseIdentifiant] Setter 
	 * @author BELDI Jamel on : 24 fevr. 2014  10:10:40
	 * @param plageAdresseIdentifiant the plageAdresseIdentifiant to set
	 */
	public void setPlageAdresseIdentifiant(String plageAdresseIdentifiant) {
		this.plageAdresseIdentifiant = plageAdresseIdentifiant;
	}
	/**
	 * [RefCompteDto.plageAdresseNom] Getter 
	 * @author BELDI Jamel on : 24 fevr. 2014  10:10:40
	 * @return the plageAdresseNom
	 */
	public String getPlageAdresseNom() {
		return plageAdresseNom;
	}
	/**
	 * [RefCompteDto.plageAdresseNom] Setter 
	 * @author BELDI Jamel on : 24 fevr. 2014  10:10:40
	 * @param plageAdresseNom the plageAdresseNom to set
	 */
	public void setPlageAdresseNom(String plageAdresseNom) {
		this.plageAdresseNom = plageAdresseNom;
	}
	/**
	 * [RefCompteDto.plageAdresseAdresseDebut] Getter 
	 * @author BELDI Jamel on : 24 fevr. 2014  10:10:40
	 * @return the plageAdresseAdresseDebut
	 */
	public String getPlageAdresseAdresseDebut() {
		return plageAdresseAdresseDebut;
	}
	/**
	 * [RefCompteDto.plageAdresseAdresseDebut] Setter 
	 * @author BELDI Jamel on : 24 fevr. 2014  10:10:40
	 * @param plageAdresseAdresseDebut the plageAdresseAdresseDebut to set
	 */
	public void setPlageAdresseAdresseDebut(String plageAdresseAdresseDebut) {
		this.plageAdresseAdresseDebut = plageAdresseAdresseDebut;
	}
	/**
	 * [RefCompteDto.plageAdresseAdresseFin] Getter 
	 * @author BELDI Jamel on : 24 fevr. 2014  10:10:40
	 * @return the plageAdresseAdresseFin
	 */
	public String getPlageAdresseAdresseFin() {
		return plageAdresseAdresseFin;
	}
	/**
	 * [RefCompteDto.plageAdresseAdresseFin] Setter 
	 * @author BELDI Jamel on : 24 fevr. 2014  10:10:40
	 * @param plageAdresseAdresseFin the plageAdresseAdresseFin to set
	 */
	public void setPlageAdresseAdresseFin(String plageAdresseAdresseFin) {
		this.plageAdresseAdresseFin = plageAdresseAdresseFin;
	}
	/**
	 * [RefCompteDto.horaireAccessId] Getter 
	 * @author BELDI Jamel on : 24 fevr. 2014  10:10:40
	 * @return the horaireAccessId
	 */
	public Integer getHoraireAccessId() {
		return horaireAccessId;
	}
	/**
	 * [RefCompteDto.horaireAccessId] Setter 
	 * @author BELDI Jamel on : 24 fevr. 2014  10:10:40
	 * @param horaireAccessId the horaireAccessId to set
	 */
	public void setHoraireAccessId(Integer horaireAccessId) {
		this.horaireAccessId = horaireAccessId;
	}
	/**
	 * [RefCompteDto.horaireAccessIdentifiant] Getter 
	 * @author BELDI Jamel on : 24 fevr. 2014  10:10:40
	 * @return the horaireAccessIdentifiant
	 */
	public String getHoraireAccessIdentifiant() {
		return horaireAccessIdentifiant;
	}
	/**
	 * [RefCompteDto.horaireAccessIdentifiant] Setter 
	 * @author BELDI Jamel on : 24 fevr. 2014  10:10:40
	 * @param horaireAccessIdentifiant the horaireAccessIdentifiant to set
	 */
	public void setHoraireAccessIdentifiant(String horaireAccessIdentifiant) {
		this.horaireAccessIdentifiant = horaireAccessIdentifiant;
	}
	/**
	 * [RefCompteDto.horaireAccessNom] Getter 
	 * @author BELDI Jamel on : 24 fevr. 2014  10:10:40
	 * @return the horaireAccessNom
	 */
	public String getHoraireAccessNom() {
		return horaireAccessNom;
	}
	/**
	 * [RefCompteDto.horaireAccessNom] Setter 
	 * @author BELDI Jamel on : 24 fevr. 2014  10:10:40
	 * @param horaireAccessNom the horaireAccessNom to set
	 */
	public void setHoraireAccessNom(String horaireAccessNom) {
		this.horaireAccessNom = horaireAccessNom;
	}
	/**
	 * [RefCompteDto.horaireAccessHeureDebut] Getter 
	 * @author BELDI Jamel on : 24 fevr. 2014  10:10:40
	 * @return the horaireAccessHeureDebut
	 */
	public Date getHoraireAccessHeureDebut() {
		return horaireAccessHeureDebut;
	}
	/**
	 * [RefCompteDto.horaireAccessHeureDebut] Setter 
	 * @author BELDI Jamel on : 24 fevr. 2014  10:10:40
	 * @param horaireAccessHeureDebut the horaireAccessHeureDebut to set
	 */
	public void setHoraireAccessHeureDebut(Date horaireAccessHeureDebut) {
		this.horaireAccessHeureDebut = horaireAccessHeureDebut;
	}
	/**
	 * [RefCompteDto.horaireAccessHeureFin] Getter 
	 * @author BELDI Jamel on : 24 fevr. 2014  10:10:40
	 * @return the horaireAccessHeureFin
	 */
	public Date getHoraireAccessHeureFin() {
		return horaireAccessHeureFin;
	}
	/**
	 * [RefCompteDto.horaireAccessHeureFin] Setter 
	 * @author BELDI Jamel on : 24 fevr. 2014  10:10:40
	 * @param horaireAccessHeureFin the horaireAccessHeureFin to set
	 */
	public void setHoraireAccessHeureFin(Date horaireAccessHeureFin) {
		this.horaireAccessHeureFin = horaireAccessHeureFin;
	}
	

	/**
	 * [RefCompteDto.idSituation] Getter 
	 * @author BELDI Jamel on : 3 mars 2014  11:28:16
	 * @return the idSituation
	 */
	public Integer getIdSituation() {
		return idSituation;
	}
	/**
	 * [RefCompteDto.idSituation] Setter 
	 * @author BELDI Jamel on : 3 mars 2014  11:28:16
	 * @param idSituation the idSituation to set
	 */
	public void setIdSituation(Integer idSituation) {
		this.idSituation = idSituation;
	}
	/**
	 * [RefCompteDto.admin] Getter 
	 * @author MAKERRI Sofiane on : 16 mars 2014  11:12:01
	 * @return the admin
	 */
	public Boolean getAdmin() {
		return admin;
	}
	/**
	 * [RefCompteDto.admin] Setter 
	 * @author MAKERRI Sofiane on : 16 mars 2014  11:12:01
	 * @param admin the admin to set
	 */
	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}
	/**
	 * [RefCompteDto.idAffectation] Getter 
	 * @author MAKERRI Sofiane on : 17 mars 2014  13:05:43
	 * @return the idAffectation
	 */
	public Integer getIdAffectation() {
		return idAffectation;
	}
	/**
	 * [RefCompteDto.idAffectation] Setter 
	 * @author MAKERRI Sofiane on : 17 mars 2014  13:05:43
	 * @param idAffectation the idAffectation to set
	 */
	public void setIdAffectation(Integer idAffectation) {
		this.idAffectation = idAffectation;
	}
	/**
	 * [RefCompteDto.idRole] Getter 
	 * @author MAKERRI Sofiane on : 17 mars 2014  13:26:10
	 * @return the idRole
	 */
	public Integer getIdRole() {
		return idRole;
	}
	/**
	 * [RefCompteDto.idRole] Setter 
	 * @author MAKERRI Sofiane on : 17 mars 2014  13:26:10
	 * @param idRole the idRole to set
	 */
	public void setIdRole(Integer idRole) {
		this.idRole = idRole;
	}
	/**
	 * [RefCompteDto.individuId] Getter 
	 * @author MAKERRI Sofiane on : 8 mai 2014  14:45:49
	 * @return the individuId
	 */
	public Integer getIndividuId() {
		return individuId;
	}
	/**
	 * [RefCompteDto.individuId] Setter 
	 * @author MAKERRI Sofiane on : 8 mai 2014  14:45:49
	 * @param individuId the individuId to set
	 */
	public void setIndividuId(Integer individuId) {
		this.individuId = individuId;
	}
	/**
	 * [RefCompteDto.etabId] Getter 
	 * @author MAKERRI Sofiane on : 16 juil. 2014  10:33:32
	 * @return the etabId
	 */
	public Integer getEtabId() {
		return etabId;
	}
	/**
	 * [RefCompteDto.etabId] Setter 
	 * @author MAKERRI Sofiane on : 16 juil. 2014  10:33:32
	 * @param etabId the etabId to set
	 */
	public void setEtabId(Integer etabId) {
		this.etabId = etabId;
	}
	/**
	 * [RefCompteDto.etabIdf] Getter 
	 * @author MAKERRI Sofiane on : 16 juil. 2014  10:33:32
	 * @return the etabIdf
	 */
	public String getEtabIdf() {
		return etabIdf;
	}
	/**
	 * [RefCompteDto.etabIdf] Setter 
	 * @author MAKERRI Sofiane on : 16 juil. 2014  10:33:32
	 * @param etabIdf the etabIdf to set
	 */
	public void setEtabIdf(String etabIdf) {
		this.etabIdf = etabIdf;
	}
	/**
	 * [RefCompteDto.etabLcFr] Getter 
	 * @author MAKERRI Sofiane on : 16 juil. 2014  10:33:32
	 * @return the etabLcFr
	 */
	public String getEtabLcFr() {
		return etabLcFr;
	}
	/**
	 * [RefCompteDto.etabLcFr] Setter 
	 * @author MAKERRI Sofiane on : 16 juil. 2014  10:33:32
	 * @param etabLcFr the etabLcFr to set
	 */
	public void setEtabLcFr(String etabLcFr) {
		this.etabLcFr = etabLcFr;
	}
	/**
	 * [RefCompteDto.etabLcAr] Getter 
	 * @author MAKERRI Sofiane on : 16 juil. 2014  10:33:32
	 * @return the etabLcAr
	 */
	public String getEtabLcAr() {
		return etabLcAr;
	}
	/**
	 * [RefCompteDto.etabLcAr] Setter 
	 * @author MAKERRI Sofiane on : 16 juil. 2014  10:33:32
	 * @param etabLcAr the etabLcAr to set
	 */
	public void setEtabLcAr(String etabLcAr) {
		this.etabLcAr = etabLcAr;
	}
	/**
	 * [RefCompteDto.etabLlFr] Getter 
	 * @author MAKERRI Sofiane on : 16 juil. 2014  10:33:32
	 * @return the etabLlFr
	 */
	public String getEtabLlFr() {
		return etabLlFr;
	}
	/**
	 * [RefCompteDto.etabLlFr] Setter 
	 * @author MAKERRI Sofiane on : 16 juil. 2014  10:33:32
	 * @param etabLlFr the etabLlFr to set
	 */
	public void setEtabLlFr(String etabLlFr) {
		this.etabLlFr = etabLlFr;
	}
	/**
	 * [RefCompteDto.etabLlAr] Getter 
	 * @author MAKERRI Sofiane on : 16 juil. 2014  10:33:32
	 * @return the etabLlAr
	 */
	public String getEtabLlAr() {
		return etabLlAr;
	}
	/**
	 * [RefCompteDto.etabLlAr] Setter 
	 * @author MAKERRI Sofiane on : 16 juil. 2014  10:33:32
	 * @param etabLlAr the etabLlAr to set
	 */
	public void setEtabLlAr(String etabLlAr) {
		this.etabLlAr = etabLlAr;
	}
	/**
	 * [RefCompteDto.etabAncienCode] Getter 
	 * @author MAKERRI Sofiane on : 23 juil. 2014  13:53:09
	 * @return the etabAncienCode
	 */
	public String getEtabAncienCode() {
		return etabAncienCode;
	}
	/**
	 * [RefCompteDto.etabAncienCode] Setter 
	 * @author MAKERRI Sofiane on : 23 juil. 2014  13:53:09
	 * @param etabAncienCode the etabAncienCode to set
	 */
	public void setEtabAncienCode(String etabAncienCode) {
		this.etabAncienCode = etabAncienCode;
	}
	/**
	 * [RefCompteDto.activate] Getter 
	 * @author MAKERRI Sofiane on : 3 sept. 2014  13:13:59
	 * @return the activate
	 */
	public boolean isActivate() {
		return activate;
	}
	/**
	 * [RefCompteDto.activate] Setter 
	 * @author MAKERRI Sofiane on : 3 sept. 2014  13:13:59
	 * @param activate the activate to set
	 */
	public void setActivate(boolean activate) {
		this.activate = activate;
	}
	/**
	 * [RefCompteDto.motPasseNonCrypte] Getter 
	 * @author MAKERRI Sofiane on : 4 sept. 2014  17:09:30
	 * @return the motPasseNonCrypte
	 */
//	public String getMotPasseNonCrypte() {
//		return motPasseNonCrypte;
//	}
	/**
	 * [RefCompteDto.motPasseNonCrypte] Setter 
	 * @author MAKERRI Sofiane on : 4 sept. 2014  17:09:30
	 * @param motPasseNonCrypte the motPasseNonCrypte to set
	 */
//	public void setMotPasseNonCrypte(String motPasseNonCrypte) {
//		this.motPasseNonCrypte = motPasseNonCrypte;
//	}
	
	
	
	
}
