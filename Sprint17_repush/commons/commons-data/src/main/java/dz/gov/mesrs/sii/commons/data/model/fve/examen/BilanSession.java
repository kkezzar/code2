/**
 * [dz.gov.mesrs.sii.fve.business.model.bo.cursus.BilanSession.java] 
 * @author MAKERRI Sofiane on : 16 oct. 2014  17:09:26
 */
package dz.gov.mesrs.sii.commons.data.model.fve.examen;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
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

import dz.gov.mesrs.sii.commons.data.model.bpm.SituationEntite;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.DossierInscriptionAdministrative;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.OuvertureOffreFormation;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.Periode;
import dz.gov.mesrs.sii.commons.data.model.referentiel.Nomenclature;

/**
 * @author MAKERRI Sofiane on : 16 oct. 2014 17:09:26
 */
@Entity
@Table(name = "bilan_session", schema = "fve_examen")
public class BilanSession implements java.io.Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author MAKERRI Sofiane on : 16 oct. 2014 17:11:02
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private DeliberationSession deliberationSession;
	private DossierInscriptionAdministrative dossierInscriptionAdministrative;
	private Nomenclature typeDecision;
	private Nomenclature mention;
	private OuvertureOffreFormation oof;
	private Periode periode;
	private BilanSession bilanSessionMax;
	private List<BilanSession> bilanSessions;
	private SituationEntite situation;
	private double moyenne;
	private double credit;
	private double creditObtenu;
	private double creditAcquis;
	private boolean annuel;
	private boolean bilanFinal;
	private int type;
	private List<BilanUe> bilanUes = new ArrayList<BilanUe>();
	private boolean creditMinObtenu;
	private BilanSession bilanSession;
	
	/**
	 * [BilanSession.BilanSession()] Constructor
	 * 
	 * @author MAKERRI Sofiane on : 16 oct. 2014 17:09:26
	 */
	public BilanSession() {
		super();
	}

	/**
	 * [BilanSession.id] Getter
	 * 
	 * @author MAKERRI Sofiane on : 16 oct. 2014 17:13:23
	 * @return the id
	 */
	@SequenceGenerator(name = "bilan_session_id_seq", sequenceName = "fve_examen.bilan_session_id_seq")
	@Id
	@GeneratedValue(generator = "bilan_session_id_seq")
	@Column(name = "id", unique = true, nullable = false)
	public long getId() {
		return id;
	}

	/**
	 * [BilanSession.id] Setter
	 * 
	 * @author MAKERRI Sofiane on : 16 oct. 2014 17:13:23
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * [BilanSession.deliberationSession] Getter
	 * 
	 * @author MAKERRI Sofiane on : 23 oct. 2014 11:19:00
	 * @return the deliberationSession
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_deliberation_session", nullable = false)
	public DeliberationSession getDeliberationSession() {
		return deliberationSession;
	}

	/**
	 * [BilanSession.deliberationSession] Setter
	 * 
	 * @author MAKERRI Sofiane on : 23 oct. 2014 11:19:00
	 * @param deliberationSession
	 *            the deliberationSession to set
	 */
	public void setDeliberationSession(DeliberationSession deliberationSession) {
		this.deliberationSession = deliberationSession;
	}

	/**
	 * [BilanSession.dossierInscriptionAdministrative] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 oct. 2014 15:43:27
	 * @return the dossierInscriptionAdministrative
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_dossier_inscription", nullable = false)
	public DossierInscriptionAdministrative getDossierInscriptionAdministrative() {
		return dossierInscriptionAdministrative;
	}

	/**
	 * [BilanSession.dossierInscriptionAdministrative] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 oct. 2014 15:43:27
	 * @param dossierInscriptionAdministrative
	 *            the dossierInscriptionAdministrative to set
	 */
	public void setDossierInscriptionAdministrative(
			DossierInscriptionAdministrative dossierInscriptionAdministrative) {
		this.dossierInscriptionAdministrative = dossierInscriptionAdministrative;
	}
	
	

	/**
	 * [BilanSession.situation] Getter 
	 * @author MAKERRI Sofiane on : 27 oct. 2014  17:34:17
	 * @return the situation
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_situation", nullable = false)
	public SituationEntite getSituation() {
		return situation;
	}

	/**
	 * [BilanSession.situation] Setter 
	 * @author MAKERRI Sofiane on : 27 oct. 2014  17:34:17
	 * @param situation the situation to set
	 */
	public void setSituation(SituationEntite situation) {
		this.situation = situation;
	}

	/**
	 * [BilanSession.typeDecision] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 oct. 2014 15:43:27
	 * @return the typeDecision
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "nc_decision", nullable = false)
	public Nomenclature getTypeDecision() {
		return typeDecision;
	}

	/**
	 * [BilanSession.typeDecision] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 oct. 2014 15:43:27
	 * @param typeDecision
	 *            the typeDecision to set
	 */
	public void setTypeDecision(Nomenclature typeDecision) {
		this.typeDecision = typeDecision;
	}
	
	

	/**
	 * [BilanSession.mention] Getter 
	 * @author MAKERRI Sofiane on : 3 nov. 2014  12:10:23
	 * @return the mention
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "nc_mention", nullable = true)
	public Nomenclature getMention() {
		return mention;
	}

	/**
	 * [BilanSession.mention] Setter 
	 * @author MAKERRI Sofiane on : 3 nov. 2014  12:10:23
	 * @param mention the mention to set
	 */
	public void setMention(Nomenclature mention) {
		this.mention = mention;
	}

	/**
	 * [BilanSession.moyenne] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 oct. 2014 15:43:27
	 * @return the moyenne
	 */
	@Column(name = "moyenne")
	public double getMoyenne() {
		return moyenne;
	}

	/**
	 * [BilanSession.moyenne] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 oct. 2014 15:43:27
	 * @param moyenne
	 *            the moyenne to set
	 */
	public void setMoyenne(double moyenne) {
		this.moyenne = moyenne;
	}

	

	/**
	 * [BilanSession.credit] Getter 
	 * @author MAKERRI Sofiane on : 30 oct. 2014  14:42:58
	 * @return the credit
	 */
	@Column(name = "credit")
	public double getCredit() {
		return credit;
	}

	/**
	 * [BilanSession.credit] Setter 
	 * @author MAKERRI Sofiane on : 30 oct. 2014  14:42:58
	 * @param credit the credit to set
	 */
	public void setCredit(double credit) {
		this.credit = credit;
	}

	/**
	 * [BilanSession.annuel] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 oct. 2014 17:12:22
	 * @return the annuel
	 */
	@Column(name = "annuel")
	public boolean getAnnuel() {
		return annuel;
	}

	/**
	 * [BilanSession.annuel] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 oct. 2014 17:12:22
	 * @param annuel
	 *            the annuel to set
	 */
	public void setAnnuel(boolean annuel) {
		this.annuel = annuel;
	}

	/**
	 * [BilanSession.bilanFinal] Getter 
	 * @author MAKERRI Sofiane on : 25 oct. 2014  11:15:41
	 * @return the bilanFinal
	 */
	@Column(name = "final")
	public boolean getBilanFinal() {
		return bilanFinal;
	}

	/**
	 * [BilanSession.bilanFinal] Setter 
	 * @author MAKERRI Sofiane on : 25 oct. 2014  11:15:41
	 * @param bilanFinal the bilanFinal to set
	 */
	public void setBilanFinal(boolean bilanFinal) {
		this.bilanFinal = bilanFinal;
	}

	/**
	 * [BilanSession.oof] Getter 
	 * @author MAKERRI Sofiane on : 26 oct. 2014  08:55:04
	 * @return the oof
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_oof", nullable = false)
	public OuvertureOffreFormation getOof() {
		return oof;
	}

	/**
	 * [BilanSession.oof] Setter 
	 * @author MAKERRI Sofiane on : 26 oct. 2014  08:55:04
	 * @param oof the oof to set
	 */
	public void setOof(OuvertureOffreFormation oof) {
		this.oof = oof;
	}

	/**
	 * [BilanSession.periode] Getter 
	 * @author MAKERRI Sofiane on : 26 oct. 2014  08:55:04
	 * @return the periode
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_periode", nullable = false)
	public Periode getPeriode() {
		return periode;
	}

	/**
	 * [BilanSession.periode] Setter 
	 * @author MAKERRI Sofiane on : 26 oct. 2014  08:55:04
	 * @param periode the periode to set
	 */
	public void setPeriode(Periode periode) {
		this.periode = periode;
	}

	/**
	 * [BilanSession.type] Getter 
	 * @author MAKERRI Sofiane on : 26 oct. 2014  08:55:04
	 * @return the type
	 */
	@Column(name = "type")
	public int getType() {
		return type;
	}

	/**
	 * [BilanSession.type] Setter 
	 * @author MAKERRI Sofiane on : 26 oct. 2014  08:55:04
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}
	
	/**
	 * [BilanSession.bilanSession] Getter 
	 * @author MAKERRI Sofiane on : 27 oct. 2014  12:33:51
	 * @return the bilanSession
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_bilan_session_max", nullable = false)
	public BilanSession getBilanSessionMax() {
		return bilanSessionMax;
	}

	/**
	 * [BilanSession.bilanSessionMax] Setter 
	 * @author MAKERRI Sofiane on : 27 oct. 2014  12:33:51
	 * @param bilanSessionMax the bilanSessionMax to set
	 */
	public void setBilanSessionMax(BilanSession bilanSessionMax) {
		this.bilanSessionMax = bilanSessionMax;
	}

	/**
	 * [BilanSession.bilanSessions] Getter 
	 * @author MAKERRI Sofiane on : 28 déc. 2014  07:45:36
	 * @return the bilanSessions
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bilanSession", cascade = CascadeType.ALL)
	public List<BilanSession> getBilanSessions() {
		return bilanSessions;
	}

	/**
	 * [BilanSession.bilanSessions] Setter 
	 * @author MAKERRI Sofiane on : 28 déc. 2014  07:45:36
	 * @param bilanSessions the bilanSessions to set
	 */
	public void setBilanSessions(List<BilanSession> bilanSessions) {
		this.bilanSessions = bilanSessions;
	}


	/**
	 * [BilanSession.bilanUes] Getter 
	 * @author MAKERRI Sofiane on : 6 nov. 2014  12:11:48
	 * @return the bilanUes
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bilanSession",  orphanRemoval = true, cascade = CascadeType.ALL)
	public List<BilanUe> getBilanUes() {
		return bilanUes;
	}

	/**
	 * [BilanSession.bilanUes] Setter 
	 * @author MAKERRI Sofiane on : 6 nov. 2014  12:11:48
	 * @param bilanUes the bilanUes to set
	 */
	public void setBilanUes(List<BilanUe> bilanUes) {
		this.bilanUes = bilanUes;
	}

	/**
	 * [BilanSession.creditObtenu] Getter 
	 * @author MAKERRI Sofiane on : 10 nov. 2014  14:55:41
	 * @return the creditObtenu
	 */
	@Column(name = "credit_obtenu")
	public double getCreditObtenu() {
		return creditObtenu;
	}

	/**
	 * [BilanSession.creditObtenu] Setter 
	 * @author MAKERRI Sofiane on : 10 nov. 2014  14:55:41
	 * @param creditObtenu the creditObtenu to set
	 */
	public void setCreditObtenu(double creditObtenu) {
		this.creditObtenu = creditObtenu;
	}

	/**
	 * [BilanSession.creditMinObtenu] Getter 
	 * @author MAKERRI Sofiane on : 24 déc. 2014  08:06:17
	 * @return the creditMinObtenu
	 */
	@Column(name = "credit_min_obtenu")
	public boolean getCreditMinObtenu() {
		return creditMinObtenu;
	}

	/**
	 * [BilanSession.creditMinObtenu] Setter 
	 * @author MAKERRI Sofiane on : 24 déc. 2014  08:06:17
	 * @param creditMinObtenu the creditMinObtenu to set
	 */
	public void setCreditMinObtenu(boolean creditMinObtenu) {
		this.creditMinObtenu = creditMinObtenu;
	}

	/**
	 * [BilanSession.bilanSession] Getter 
	 * @author MAKERRI Sofiane on : 28 déc. 2014  10:47:53
	 * @return the bilanSession
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_bilan_session", nullable = false)
	public BilanSession getBilanSession() {
		return bilanSession;
	}

	/**
	 * [BilanSession.bilanSession] Setter 
	 * @author MAKERRI Sofiane on : 28 déc. 2014  10:47:53
	 * @param bilanSession the bilanSession to set
	 */
	public void setBilanSession(BilanSession bilanSession) {
		this.bilanSession = bilanSession;
	}

	/**
	 * [BilanSession.creditAcquis] Getter 
	 * @author MAKERRI Sofiane on : 15 janv. 2015  15:54:27
	 * @return the creditAcquis
	 */
	@Column(name = "credit_acquis")
	public double getCreditAcquis() {
		return creditAcquis;
	}

	/**
	 * [BilanSession.creditAcquis] Setter 
	 * @author MAKERRI Sofiane on : 15 janv. 2015  15:54:27
	 * @param creditAcquis the creditAcquis to set
	 */
	public void setCreditAcquis(double creditAcquis) {
		this.creditAcquis = creditAcquis;
	}
	
	

}
