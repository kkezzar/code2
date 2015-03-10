/**
 * [dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.examen.notation.BilanModel.java] 
 * @author MAKERRI Sofiane on : 26 oct. 2014  11:10:53
 */
package dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.examen.notation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dz.gov.mesrs.sii.fve.business.model.dto.examen.BilanSessionDto;

/**
 * @author MAKERRI Sofiane  on : 26 oct. 2014  11:10:53
 */
public class BilanModel implements Serializable {

	
	/**
	 * serialVersionUID 
	 * @author MAKERRI Sofiane  on : 26 oct. 2014  11:11:02
	 */
	private static final long serialVersionUID = 1L;
	private String matricule;
	private String nom;
	private String prenom;
	private Date dateNaissance;
	private String lieuNaissance;
	private String mentionLibelle;
	private Integer mentionId;
	private Integer decisionId;
	private String decisionLibelle;
	private Integer diaId;
	private Integer dossierEtudiantId;
	private Long deliberationId;
	private Long bilanSessionId;
	private boolean bilanPeriode;
	private boolean bilanAnnuel;
	private List<BilanSessionDto> session;
	private double moyenneGenerale;
	private String formattedMG;
	private double credit;
	private double creditObtenu;
	private boolean admis;
	private boolean creditMinObtenu;
	private String styleClass;
	private boolean passageL1AvecDette;

	/**
	 * [BilanModel.BilanModel()] Constructor
	 * @author MAKERRI Sofiane  on : 26 oct. 2014  11:10:53	
	 */
	public BilanModel() {
		super();
		session = new ArrayList<BilanSessionDto>();
	}

	/**
	 * [BilanModel.matricule] Getter 
	 * @author MAKERRI Sofiane on : 26 oct. 2014  11:13:04
	 * @return the matricule
	 */
	public String getMatricule() {
		return matricule;
	}

	/**
	 * [BilanModel.matricule] Setter 
	 * @author MAKERRI Sofiane on : 26 oct. 2014  11:13:04
	 * @param matricule the matricule to set
	 */
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	/**
	 * [BilanModel.nom] Getter 
	 * @author MAKERRI Sofiane on : 26 oct. 2014  11:13:04
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * [BilanModel.nom] Setter 
	 * @author MAKERRI Sofiane on : 26 oct. 2014  11:13:04
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * [BilanModel.prenom] Getter 
	 * @author MAKERRI Sofiane on : 26 oct. 2014  11:13:04
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * [BilanModel.prenom] Setter 
	 * @author MAKERRI Sofiane on : 26 oct. 2014  11:13:04
	 * @param prenom the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/**
	 * [BilanModel.dateNaissance] Getter 
	 * @author MAKERRI Sofiane on : 26 oct. 2014  11:13:04
	 * @return the dateNaissance
	 */
	public Date getDateNaissance() {
		return dateNaissance;
	}

	/**
	 * [BilanModel.dateNaissance] Setter 
	 * @author MAKERRI Sofiane on : 26 oct. 2014  11:13:04
	 * @param dateNaissance the dateNaissance to set
	 */
	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	

	/**
	 * [BilanModel.mentionLibelle] Getter 
	 * @author MAKERRI Sofiane on : 3 nov. 2014  12:19:38
	 * @return the mentionLibelle
	 */
	public String getMentionLibelle() {
		return mentionLibelle;
	}

	/**
	 * [BilanModel.mentionLibelle] Setter 
	 * @author MAKERRI Sofiane on : 3 nov. 2014  12:19:38
	 * @param mentionLibelle the mentionLibelle to set
	 */
	public void setMentionLibelle(String mentionLibelle) {
		this.mentionLibelle = mentionLibelle;
	}

	/**
	 * [BilanModel.session] Getter 
	 * @author MAKERRI Sofiane on : 26 oct. 2014  11:13:04
	 * @return the session
	 */
	public List<BilanSessionDto> getSession() {
		return session;
	}

	/**
	 * [BilanModel.session] Setter 
	 * @author MAKERRI Sofiane on : 26 oct. 2014  11:13:04
	 * @param session the session to set
	 */
	public void setSession(List<BilanSessionDto> session) {
		this.session = session;
	}

	/**
	 * [BilanModel.diaId] Getter 
	 * @author MAKERRI Sofiane on : 26 oct. 2014  11:53:04
	 * @return the diaId
	 */
	public Integer getDiaId() {
		return diaId;
	}

	/**
	 * [BilanModel.diaId] Setter 
	 * @author MAKERRI Sofiane on : 26 oct. 2014  11:53:04
	 * @param diaId the diaId to set
	 */
	public void setDiaId(Integer diaId) {
		this.diaId = diaId;
	}

	/**
	 * [BilanModel.moyenneGenerale] Getter 
	 * @author MAKERRI Sofiane on : 26 oct. 2014  13:09:52
	 * @return the moyenneGenerale
	 */
	public double getMoyenneGenerale() {
		return moyenneGenerale;
	}

	/**
	 * [BilanModel.moyenneGenerale] Setter 
	 * @author MAKERRI Sofiane on : 26 oct. 2014  13:09:52
	 * @param moyenneGenerale the moyenneGenerale to set
	 */
	public void setMoyenneGenerale(double moyenneGenerale) {
		this.moyenneGenerale = moyenneGenerale;
	}

	/**
	 * [BilanModel.deliberationId] Getter 
	 * @author MAKERRI Sofiane on : 26 oct. 2014  13:30:10
	 * @return the deliberationId
	 */
	public Long getDeliberationId() {
		return deliberationId;
	}

	/**
	 * [BilanModel.deliberationId] Setter 
	 * @author MAKERRI Sofiane on : 26 oct. 2014  13:30:10
	 * @param deliberationId the deliberationId to set
	 */
	public void setDeliberationId(Long deliberationId) {
		this.deliberationId = deliberationId;
	}

	/**
	 * [BilanModel.decision] Getter 
	 * @author MAKERRI Sofiane on : 26 oct. 2014  14:55:18
	 * @return the decision
	 */
	public Integer getDecisionId() {
		return decisionId;
	}

	/**
	 * [BilanModel.decision] Setter 
	 * @author MAKERRI Sofiane on : 26 oct. 2014  14:55:18
	 * @param decision the decision to set
	 */
	public void setDecisionId(Integer decisionId) {
		this.decisionId = decisionId;
	}

	/**
	 * [BilanModel.decisionLibelle] Getter 
	 * @author MAKERRI Sofiane on : 26 oct. 2014  15:05:15
	 * @return the decisionLibelle
	 */
	public String getDecisionLibelle() {
		return decisionLibelle;
	}

	/**
	 * [BilanModel.decisionLibelle] Setter 
	 * @author MAKERRI Sofiane on : 26 oct. 2014  15:05:15
	 * @param decisionLibelle the decisionLibelle to set
	 */
	public void setDecisionLibelle(String decisionLibelle) {
		this.decisionLibelle = decisionLibelle;
	}

	/**
	 * [BilanModel.bilanSessionId] Getter 
	 * @author MAKERRI Sofiane on : 27 oct. 2014  12:42:37
	 * @return the bilanSessionId
	 */
	public Long getBilanSessionId() {
		return bilanSessionId;
	}

	/**
	 * [BilanModel.bilanSessionId] Setter 
	 * @author MAKERRI Sofiane on : 27 oct. 2014  12:42:37
	 * @param bilanSessionId the bilanSessionId to set
	 */
	public void setBilanSessionId(Long bilanSessionId) {
		this.bilanSessionId = bilanSessionId;
	}

	/**
	 * [BilanModel.bilanPeriode] Getter 
	 * @author MAKERRI Sofiane on : 27 oct. 2014  12:46:05
	 * @return the bilanPeriode
	 */
	public boolean getBilanPeriode() {
		return bilanPeriode;
	}

	/**
	 * [BilanModel.bilanPeriode] Setter 
	 * @author MAKERRI Sofiane on : 27 oct. 2014  12:46:06
	 * @param bilanPeriode the bilanPeriode to set
	 */
	public void setBilanPeriode(boolean bilanPeriode) {
		this.bilanPeriode = bilanPeriode;
	}

	/**
	 * [BilanModel.bilanAnnuel] Getter 
	 * @author MAKERRI Sofiane on : 27 oct. 2014  15:28:05
	 * @return the bilanAnnuel
	 */
	public boolean getBilanAnnuel() {
		return bilanAnnuel;
	}

	/**
	 * [BilanModel.bilanAnnuel] Setter 
	 * @author MAKERRI Sofiane on : 27 oct. 2014  15:28:05
	 * @param bilanAnnuel the bilanAnnuel to set
	 */
	public void setBilanAnnuel(boolean bilanAnnuel) {
		this.bilanAnnuel = bilanAnnuel;
	}

	/**
	 * [BilanModel.credit] Getter 
	 * @author MAKERRI Sofiane on : 30 oct. 2014  14:46:20
	 * @return the credit
	 */
//	public double getCredit() {
//		return credit;
//	}

	/**
	 * [BilanModel.credit] Setter 
	 * @author MAKERRI Sofiane on : 30 oct. 2014  14:46:20
	 * @param credit the credit to set
	 */
//	public void setCredit(double credit) {
//		this.credit = credit;
//	}

	/**
	 * [BilanModel.mentionId] Getter 
	 * @author MAKERRI Sofiane on : 3 nov. 2014  12:20:05
	 * @return the mentionId
	 */
	public Integer getMentionId() {
		return mentionId;
	}

	/**
	 * [BilanModel.mentionId] Setter 
	 * @author MAKERRI Sofiane on : 3 nov. 2014  12:20:05
	 * @param mentionId the mentionId to set
	 */
	public void setMentionId(Integer mentionId) {
		this.mentionId = mentionId;
	}

	/**
	 * [BilanModel.creditObtenu] Getter 
	 * @author MAKERRI Sofiane on : 10 nov. 2014  15:21:59
	 * @return the creditObtenu
	 */
	public double getCreditObtenu() {
		return creditObtenu;
	}

	/**
	 * [BilanModel.creditObtenu] Setter 
	 * @author MAKERRI Sofiane on : 10 nov. 2014  15:22:00
	 * @param creditObtenu the creditObtenu to set
	 */
	public void setCreditObtenu(double creditObtenu) {
		this.creditObtenu = creditObtenu;
	}

	/**
	 * [BilanModel.credit] Getter 
	 * @author MAKERRI Sofiane on : 10 nov. 2014  15:47:52
	 * @return the credit
	 */
	public double getCredit() {
		return credit;
	}

	/**
	 * [BilanModel.credit] Setter 
	 * @author MAKERRI Sofiane on : 10 nov. 2014  15:47:52
	 * @param credit the credit to set
	 */
	public void setCredit(double credit) {
		this.credit = credit;
	}

	/**
	 * [BilanModel.lieuNaissance] Getter 
	 * @author MAKERRI Sofiane on : 12 nov. 2014  15:32:56
	 * @return the lieuNaissance
	 */
	public String getLieuNaissance() {
		return lieuNaissance;
	}

	/**
	 * [BilanModel.lieuNaissance] Setter 
	 * @author MAKERRI Sofiane on : 12 nov. 2014  15:32:56
	 * @param lieuNaissance the lieuNaissance to set
	 */
	public void setLieuNaissance(String lieuNaissance) {
		this.lieuNaissance = lieuNaissance;
	}

	/**
	 * [BilanModel.formattedMG] Getter 
	 * @author MAKERRI Sofiane on : 15 déc. 2014  09:57:53
	 * @return the formattedMG
	 */
	public String getFormattedMG() {
		return formattedMG;
	}

	/**
	 * [BilanModel.formattedMG] Setter 
	 * @author MAKERRI Sofiane on : 15 déc. 2014  09:57:53
	 * @param formattedMG the formattedMG to set
	 */
	public void setFormattedMG(String formattedMG) {
		this.formattedMG = formattedMG;
	}

	/**
	 * [BilanModel.admis] Getter 
	 * @author MAKERRI Sofiane on : 22 déc. 2014  15:52:28
	 * @return the admis
	 */
	public boolean isAdmis() {
		admis = (moyenneGenerale >= 10 ? true : false);
		return admis;
	}

	/**
	 * [BilanModel.admis] Setter 
	 * @author MAKERRI Sofiane on : 22 déc. 2014  15:52:28
	 * @param admis the admis to set
	 */
	public void setAdmis(boolean admis) {
		this.admis = admis;
	}

	/**
	 * [BilanModel.creditMinObtenu] Getter 
	 * @author MAKERRI Sofiane on : 23 déc. 2014  10:02:31
	 * @return the creditMinObtenu
	 */
	public boolean isCreditMinObtenu() {
		return creditMinObtenu;
	}

	/**
	 * [BilanModel.creditMinObtenu] Setter 
	 * @author MAKERRI Sofiane on : 23 déc. 2014  10:02:31
	 * @param creditMinObtenu the creditMinObtenu to set
	 */
	public void setCreditMinObtenu(boolean creditMinObtenu) {
		this.creditMinObtenu = creditMinObtenu;
	}

	/**
	 * [BilanModel.styleClass] Getter 
	 * @author MAKERRI Sofiane on : 23 déc. 2014  16:06:03
	 * @return the styleClass
	 */
	public String getStyleClass() {
		return styleClass;
	}

	/**
	 * [BilanModel.styleClass] Setter 
	 * @author MAKERRI Sofiane on : 23 déc. 2014  16:06:03
	 * @param styleClass the styleClass to set
	 */
	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}

	/**
	 * [BilanModel.dossierEtudiantId] Getter 
	 * @author MAKERRI Sofiane on : 25 déc. 2014  11:51:00
	 * @return the dossierEtudiantId
	 */
	public Integer getDossierEtudiantId() {
		return dossierEtudiantId;
	}

	/**
	 * [BilanModel.dossierEtudiantId] Setter 
	 * @author MAKERRI Sofiane on : 25 déc. 2014  11:51:00
	 * @param dossierEtudiantId the dossierEtudiantId to set
	 */
	public void setDossierEtudiantId(Integer dossierEtudiantId) {
		this.dossierEtudiantId = dossierEtudiantId;
	}

	/**
	 * [BilanModel.passageL1AvecDette] Getter 
	 * @author MAKERRI Sofiane on : 25 déc. 2014  16:56:36
	 * @return the passageL1AvecDette
	 */
	public boolean isPassageL1AvecDette() {
		return passageL1AvecDette;
	}

	/**
	 * [BilanModel.passageL1AvecDette] Setter 
	 * @author MAKERRI Sofiane on : 25 déc. 2014  16:56:36
	 * @param passageL1AvecDette the passageL1AvecDette to set
	 */
	public void setPassageL1AvecDette(boolean passageL1AvecDette) {
		this.passageL1AvecDette = passageL1AvecDette;
	}
	
	

}
