/**
 * [dz.gov.mesrs.sii.fve.business.model.bo.habilitation.Action.java] 
 * @author rlaib on : 29 avr. 2014  08:54:09
 */
package dz.gov.mesrs.sii.commons.data.model.bpm;

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
 * @author rlaib  on : 29 avr. 2014  08:54:09
 */
@Entity
@Table(name = "demande_i18n", schema = "bpm")
public class DemandeI18n implements java.io.Serializable {
	
	
	/**
	 * serialVersionUID 
	 * @author rlaib  on : 29 avr. 2014  15:18:21
	 */
	private static final long serialVersionUID = -7665797811821474024L;
	private int id;
	private String langue;
	private String objet;
	private String observation;
	private Demande demande;

	public DemandeI18n() {
	}
	/**
	 * [Action.id] Getter 
	 * @author rlaib on : 29 avr. 2014  08:56:30
	 * @return the id
	 */
	@Id
	@SequenceGenerator(name="demande_i18n_id_seq_gen", initialValue = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "demande_i18n_id_seq_gen")
	public int getId() {
		return id;
	}
	
	/**
	 * [Action.id] Setter 
	 * @author rlaib on : 29 avr. 2014  08:56:30
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * [ActionI18n.langue] Getter 
	 * @author rlaib on : 29 avr. 2014  09:00:26
	 * @return the langue
	 */
	@Column(name = "langue", nullable = false,length=5)
	public String getLangue() {
		return langue;
	}
	
	/**
	 * [ActionI18n.langue] Setter 
	 * @author rlaib on : 29 avr. 2014  09:00:26
	 * @param langue the langue to set
	 */
	public void setLangue(String langue) {
		this.langue = langue;
	}
	
	/**
	 * [DemandeI18n.objet] Getter 
	 * @author rlaib on : 29 avr. 2014  11:08:57
	 * @return the objet
	 */
	@Column(name = "objet", nullable = false,length=250)
	public String getObjet() {
		return objet;
	}
	
	/**
	 * [DemandeI18n.objet] Setter 
	 * @author rlaib on : 29 avr. 2014  11:08:57
	 * @param objet the objet to set
	 */
	public void setObjet(String objet) {
		this.objet = objet;
	}
	
	/**
	 * [DemandeI18n.observation] Getter 
	 * @author rlaib on : 29 avr. 2014  11:08:57
	 * @return the observation
	 */
	@Column(name = "observation", nullable = true,length=500)
	public String getObservation() {
		return observation;
	}
	
	/**
	 * [DemandeI18n.observation] Setter 
	 * @author rlaib on : 29 avr. 2014  11:08:57
	 * @param observation the observation to set
	 */
	public void setObservation(String observation) {
		this.observation = observation;
	}
	
	/**
	 * [DemandeI18n.demande] Getter 
	 * @author rlaib on : 29 avr. 2014  11:08:57
	 * @return the demande
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_demande", nullable = false)
	public Demande getDemande() {
		return demande;
	}
	
	/**
	 * [DemandeI18n.demande] Setter 
	 * @author rlaib on : 29 avr. 2014  11:08:57
	 * @param demande the demande to set
	 */
	public void setDemande(Demande demande) {
		this.demande = demande;
	}
	
	
	
}
