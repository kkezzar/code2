/**
 * [dz.gov.mesrs.sii.fve.business.model.bo.cursus.BilanControleContinu.java] 
 * @author MAKERRI Sofiane on : 16 oct. 2014  17:09:26
 */
package dz.gov.mesrs.sii.commons.data.model.fve.examen;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import dz.gov.mesrs.sii.commons.data.model.bpm.SituationEntite;

/**
 * @author MAKERRI Sofiane  on : 16 oct. 2014  17:09:26
 */
@Entity
@Table(name = "deliberation_session", schema = "fve_examen")
public class DeliberationSession implements java.io.Serializable {

	/**
	 * serialVersionUID 
	 * @author MAKERRI Sofiane  on : 16 oct. 2014  17:11:02
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private Date dateDeliberation;
	private Date heureDeliberation;
	private Date dateCloture;
	private PlanningSession planningSession;
	private SituationEntite situation;
	private boolean bilanAnnuel;
	private boolean bilanPeriode;
	

	/**
	 * [BilanControleContinu.BilanControleContinu()] Constructor
	 * @author MAKERRI Sofiane  on : 16 oct. 2014  17:09:26	
	 */
	public DeliberationSession() {
		super();
	}


	/**
	 * [BilanControleContinu.id] Getter 
	 * @author MAKERRI Sofiane on : 16 oct. 2014  17:13:23
	 * @return the id
	 */
	@SequenceGenerator(name = "deliberation_session_id_seq", sequenceName = "fve_examen.deliberation_session_id_seq")
	@Id
	@GeneratedValue(generator = "deliberation_session_id_seq")
	@Column(name = "id", unique = true, nullable = false)
	public long getId() {
		return id;
	}


	/**
	 * [BilanControleContinu.id] Setter 
	 * @author MAKERRI Sofiane on : 16 oct. 2014  17:13:23
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}





	/**
	 * [DeliberationSession.dateDeliberation] Getter 
	 * @author MAKERRI Sofiane on : 19 oct. 2014  11:52:53
	 * @return the dateDeliberation
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "date_deliberation", nullable = false)
	public Date getDateDeliberation() {
		return dateDeliberation;
	}


	/**
	 * [DeliberationSession.dateDeliberation] Setter 
	 * @author MAKERRI Sofiane on : 19 oct. 2014  11:52:53
	 * @param dateDeliberation the dateDeliberation to set
	 */
	public void setDateDeliberation(Date dateDeliberation) {
		this.dateDeliberation = dateDeliberation;
	}


	/**
	 * [DeliberationSession.heureDeliberation] Getter 
	 * @author MAKERRI Sofiane on : 19 oct. 2014  11:52:53
	 * @return the heureDeliberation
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "heure_deliberation", nullable = false)
	public Date getHeureDeliberation() {
		return heureDeliberation;
	}


	/**
	 * [DeliberationSession.heureDeliberation] Setter 
	 * @author MAKERRI Sofiane on : 19 oct. 2014  11:52:53
	 * @param heureDeliberation the heureDeliberation to set
	 */
	public void setHeureDeliberation(Date heureDeliberation) {
		this.heureDeliberation = heureDeliberation;
	}

	
	

	/**
	 * [DeliberationSession.dateCloture] Getter 
	 * @author MAKERRI Sofiane on : 25 oct. 2014  09:17:48
	 * @return the dateCloture
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "date_cloture", nullable = false)
	public Date getDateCloture() {
		return dateCloture;
	}


	/**
	 * [DeliberationSession.dateCloture] Setter 
	 * @author MAKERRI Sofiane on : 25 oct. 2014  09:17:48
	 * @param dateCloture the dateCloture to set
	 */
	public void setDateCloture(Date dateCloture) {
		this.dateCloture = dateCloture;
	}


	/**
	 * [DeliberationSession.planningSession] Getter 
	 * @author MAKERRI Sofiane on : 19 oct. 2014  11:50:42
	 * @return the planningSession
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_planning_session", nullable = false)
	public PlanningSession getPlanningSession() {
		return planningSession;
	}


	/**
	 * [DeliberationSession.planningSession] Setter 
	 * @author MAKERRI Sofiane on : 19 oct. 2014  11:50:42
	 * @param planningSession the planningSession to set
	 */
	public void setPlanningSession(PlanningSession planningSession) {
		this.planningSession = planningSession;
	}


	/**
	 * [DeliberationSession.situation] Getter 
	 * @author MAKERRI Sofiane on : 19 oct. 2014  11:50:42
	 * @return the situation
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_situation", nullable = false)
	public SituationEntite getSituation() {
		return situation;
	}


	/**
	 * [DeliberationSession.situation] Setter 
	 * @author MAKERRI Sofiane on : 19 oct. 2014  11:50:42
	 * @param situation the situation to set
	 */
	public void setSituation(SituationEntite situation) {
		this.situation = situation;
	}


	/**
	 * [DeliberationSession.bilanAnnuel] Getter 
	 * @author MAKERRI Sofiane on : 25 oct. 2014  14:14:45
	 * @return the bilanAnnuel
	 */
	@Column(name = "bilan_annuel")
	public boolean getBilanAnnuel() {
		return bilanAnnuel;
	}


	/**
	 * [DeliberationSession.bilanAnnuel] Setter 
	 * @author MAKERRI Sofiane on : 25 oct. 2014  14:14:45
	 * @param bilanAnnuel the bilanAnnuel to set
	 */
	public void setBilanAnnuel(boolean bilanAnnuel) {
		this.bilanAnnuel = bilanAnnuel;
	}


	/**
	 * [DeliberationSession.bilanPeriode] Getter 
	 * @author MAKERRI Sofiane on : 26 oct. 2014  14:08:09
	 * @return the bilanPeriode
	 */
	@Column(name = "bilan_periode")
	public boolean getBilanPeriode() {
		return bilanPeriode;
	}


	/**
	 * [DeliberationSession.bilanPeriode] Setter 
	 * @author MAKERRI Sofiane on : 26 oct. 2014  14:08:09
	 * @param bilanPeriode the bilanPeriode to set
	 */
	public void setBilanPeriode(boolean bilanPeriode) {
		this.bilanPeriode = bilanPeriode;
	}
	
	


}
