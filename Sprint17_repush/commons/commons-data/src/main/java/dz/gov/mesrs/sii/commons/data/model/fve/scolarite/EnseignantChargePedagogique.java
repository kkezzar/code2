/**
 * [dz.gov.mesrs.sii.fve.business.model.bo.scolarite.EnseignantChargePedagogique.java] 
 * @author rlaib on : 12 oct. 2014  14:03:13
 */
package dz.gov.mesrs.sii.commons.data.model.fve.scolarite;

import java.util.HashSet;
import java.util.Set;

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
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.GroupePedagogique;

/**
 * @author rlaib  on : 12 oct. 2014  14:03:13
 */
@Entity
@Table(name = "enseignant_charge_pedagogique", schema = "fve_scolarite")
public class EnseignantChargePedagogique implements java.io.Serializable  {
	
	/**
	 * serialVersionUID 
	 * @author rlaib  on : 29 oct. 2014  16:05:09
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private EnseignantFicheServices enseignantFicheServices;
	private EnseignantVoeuLigne enseignantVoeuLigne;
	private Integer idTypeCharge;
	private Integer idStatutServicePedagogique;
	private Integer effectifGroupeAp;
	private Double volumeHoraireAp;
	private GroupePedagogique groupePedagogique;
	private SituationEntite situation;
	private boolean incluse;
	private Set<EnseignantServiceFait> enseignantServiceFaits = new HashSet<EnseignantServiceFait>(0);
	
	public EnseignantChargePedagogique() {
		
	}

	/**
	 * [EnseignantChargePedagogique.id] Getter 
	 * @author rlaib on : 22 oct. 2014  12:07:29
	 * @return the id
	 */
	@SequenceGenerator(name="enseignant_charge_pedagogique_id_seq_gen", sequenceName="fve_scolarite.enseignant_charge_pedagogique_id_seq")
	@Id @GeneratedValue(generator="enseignant_charge_pedagogique_id_seq_gen")
	@Column(name = "id")
	public int getId() {
		return id;
	}

	/**
	 * [EnseignantChargePedagogique.id] Setter 
	 * @author rlaib on : 22 oct. 2014  12:07:29
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * [EnseignantChargePedagogique.enseignantFicheServices] Getter 
	 * @author rlaib on : 22 oct. 2014  12:07:29
	 * @return the enseignantFicheServices
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_fiche_services", nullable = false)
	public EnseignantFicheServices getEnseignantFicheServices() {
		return enseignantFicheServices;
	}

	/**
	 * [EnseignantChargePedagogique.enseignantFicheServices] Setter 
	 * @author rlaib on : 22 oct. 2014  12:07:29
	 * @param enseignantFicheServices the enseignantFicheServices to set
	 */
	public void setEnseignantFicheServices(
			EnseignantFicheServices enseignantFicheServices) {
		this.enseignantFicheServices = enseignantFicheServices;
	}

	/**
	 * [EnseignantChargePedagogique.enseignantVoeuLigne] Getter 
	 * @author rlaib on : 22 oct. 2014  12:07:29
	 * @return the enseignantVoeuLigne
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_voeu_ligne", nullable = true)
	public EnseignantVoeuLigne getEnseignantVoeuLigne() {
		return enseignantVoeuLigne;
	}

	/**
	 * [EnseignantChargePedagogique.enseignantVoeuLigne] Setter 
	 * @author rlaib on : 22 oct. 2014  12:07:29
	 * @param enseignantVoeuLigne the enseignantVoeuLigne to set
	 */
	public void setEnseignantVoeuLigne(EnseignantVoeuLigne enseignantVoeuLigne) {
		this.enseignantVoeuLigne = enseignantVoeuLigne;
	}

	/**
	 * [EnseignantChargePedagogique.idTypeCharge] Getter 
	 * @author rlaib on : 22 oct. 2014  12:07:29
	 * @return the idTypeCharge
	 */
	@Column(name = "id_type_charge")
	public Integer getIdTypeCharge() {
		return idTypeCharge;
	}

	/**
	 * [EnseignantChargePedagogique.idTypeCharge] Setter 
	 * @author rlaib on : 22 oct. 2014  12:07:29
	 * @param idTypeCharge the idTypeCharge to set
	 */
	public void setIdTypeCharge(Integer idTypeCharge) {
		this.idTypeCharge = idTypeCharge;
	}

	/**
	 * [EnseignantChargePedagogique.idStatutServicePedagogique] Getter 
	 * @author rlaib on : 22 oct. 2014  12:07:29
	 * @return the idStatutServicePedagogique
	 */
	@Column(name = "id_statut_service_pedagogique")
	public Integer getIdStatutServicePedagogique() {
		return idStatutServicePedagogique;
	}

	/**
	 * [EnseignantChargePedagogique.idStatutServicePedagogique] Setter 
	 * @author rlaib on : 22 oct. 2014  12:07:29
	 * @param idStatutServicePedagogique the idStatutServicePedagogique to set
	 */
	public void setIdStatutServicePedagogique(Integer idStatutServicePedagogique) {
		this.idStatutServicePedagogique = idStatutServicePedagogique;
	}

	/**
	 * [EnseignantChargePedagogique.effectifGroupeAp] Getter 
	 * @author rlaib on : 22 oct. 2014  12:07:29
	 * @return the effectifGroupeAp
	 */
	public Integer getEffectifGroupeAp() {
		return effectifGroupeAp;
	}

	/**
	 * [EnseignantChargePedagogique.effectifGroupeAp] Setter 
	 * @author rlaib on : 22 oct. 2014  12:07:29
	 * @param effectifGroupeAp the effectifGroupeAp to set
	 */
	public void setEffectifGroupeAp(Integer effectifGroupeAp) {
		this.effectifGroupeAp = effectifGroupeAp;
	}

	/**
	 * [EnseignantChargePedagogique.volumeHoraireAp] Getter 
	 * @author rlaib on : 22 oct. 2014  12:07:29
	 * @return the volumeHoraireAp
	 */
	public Double getVolumeHoraireAp() {
		return volumeHoraireAp;
	}

	/**
	 * [EnseignantChargePedagogique.volumeHoraireAp] Setter 
	 * @author rlaib on : 22 oct. 2014  12:07:29
	 * @param volumeHoraireAp the volumeHoraireAp to set
	 */
	public void setVolumeHoraireAp(Double volumeHoraireAp) {
		this.volumeHoraireAp = volumeHoraireAp;
	}

	/**
	 * [EnseignantChargePedagogique.groupePedagogique] Getter 
	 * @author rlaib on : 24 oct. 2014  15:28:12
	 * @return the groupePedagogique
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_groupe_pedagogique", nullable = false)
	public GroupePedagogique getGroupePedagogique() {
		return groupePedagogique;
	}

	/**
	 * [EnseignantChargePedagogique.groupePedagogique] Setter 
	 * @author rlaib on : 24 oct. 2014  15:28:12
	 * @param groupePedagogique the groupePedagogique to set
	 */
	public void setGroupePedagogique(GroupePedagogique groupePedagogique) {
		this.groupePedagogique = groupePedagogique;
	}

	/**
	 * [EnseignantChargePedagogique.situation] Getter 
	 * @author rlaib on : 25 oct. 2014  10:55:08
	 * @return the situation
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_situation", nullable = false)
	public SituationEntite getSituation() {
		return situation;
	}

	/**
	 * [EnseignantChargePedagogique.situation] Setter 
	 * @author rlaib on : 25 oct. 2014  10:55:08
	 * @param situation the situation to set
	 */
	public void setSituation(SituationEntite situation) {
		this.situation = situation;
	}

	/**
	 * [EnseignantChargePedagogique.incluse] Getter 
	 * @author rlaib on : 28 oct. 2014  18:04:16
	 * @return the incluse
	 */
	@Column(name = "incluse")
	public boolean isIncluse() {
		return incluse;
	}

	/**
	 * [EnseignantChargePedagogique.incluse] Setter 
	 * @author rlaib on : 28 oct. 2014  18:04:16
	 * @param incluse the incluse to set
	 */
	public void setIncluse(boolean incluse) {
		this.incluse = incluse;
	}

	
	/**
	 * [EnseignantChargePedagogique.enseignantServiceFaits] Getter 
	 * @author Rafik on : 8 déc. 2014  20:46:50
	 * @return the enseignantServiceFaits
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "enseignantChargePedagogique", targetEntity= EnseignantServiceFait.class)
	public Set<EnseignantServiceFait> getEnseignantServiceFaits() {
		return enseignantServiceFaits;
	}

	/**
	 * [EnseignantChargePedagogique.enseignantServiceFaits] Setter 
	 * @author Rafik on : 8 déc. 2014  20:46:50
	 * @param enseignantServiceFaits the enseignantServiceFaits to set
	 */
	public void setEnseignantServiceFaits(
			Set<EnseignantServiceFait> enseignantServiceFaits) {
		this.enseignantServiceFaits = enseignantServiceFaits;
	}


	
}
