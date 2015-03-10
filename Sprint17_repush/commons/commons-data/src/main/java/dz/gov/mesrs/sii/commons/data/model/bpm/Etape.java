/**
 * [dz.gov.mesrs.sii.fve.business.model.bo.habilitation.Action.java] 
 * @author rlaib on : 29 avr. 2014  08:54:09
 */
package dz.gov.mesrs.sii.commons.data.model.bpm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import dz.gov.mesrs.sii.commons.data.model.referentiel.Nomenclature;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefGroupe;


/**
 * @author rlaib  on : 29 avr. 2014  08:54:09
 */
@Entity
@Table(name = "etape", schema = "bpm")
public class Etape implements java.io.Serializable {
	
	
	/**
	 * serialVersionUID 
	 * @author rlaib  on : 29 avr. 2014  15:18:42
	 */
	private static final long serialVersionUID = -8779718557375979693L;
	private int id;
	private String code;
	private String libelleFr;
	private String libelleAr;
	private Boolean estMultiple= false;
	private Integer multiplicite;
	private Processus processus;
	private RefGroupe refGroupe; 
	private Nomenclature roleGroupe; 
	private Etape etapePrecedente; 
	private Etape etapeSuivante; 
	private ActionEntite actionEntite; 
	private SituationEntite situationEntite; 
	

	public Etape() {
	}
	/**
	 * [Etape.id] Getter 
	 * @author rlaib on : 29 avr. 2014  08:56:30
	 * @return the id
	 */
	@SequenceGenerator(name="etape_id_seq_gen", sequenceName="bpm.etape_id_seq")
	@Id @GeneratedValue(generator="etape_id_seq_gen")
	@Column(name = "id")
	public int getId() {
		return id;
	}
	/**
	 * [Etape.id] Setter 
	 * @author rlaib on : 29 avr. 2014  08:56:30
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * [Etape.code] Getter 
	 * @author rlaib on : 29 avr. 2014  08:56:30
	 * @return the code
	 */
	@Column(name = "code", nullable = false,length=10)
	public String getCode() {
		return code;
	}
	/**
	 * [Etape.code] Setter 
	 * @author rlaib on : 29 avr. 2014  08:56:30
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * [Etape.libelleFr] Getter 
	 * @author rlaib on : 15 janv. 2015  10:31:37
	 * @return the libelleFr
	 */
	@Column(name = "libelle_fr", nullable = false,length=255)
	public String getLibelleFr() {
		return libelleFr;
	}
	/**
	 * [Etape.libelleFr] Setter 
	 * @author rlaib on : 15 janv. 2015  10:31:37
	 * @param libelleFr the libelleFr to set
	 */
	public void setLibelleFr(String libelleFr) {
		this.libelleFr = libelleFr;
	}
	/**
	 * [Etape.libelleAr] Getter 
	 * @author rlaib on : 15 janv. 2015  10:31:38
	 * @return the libelleAr
	 */
	@Column(name = "libelle_ar", length=255)
	public String getLibelleAr() {
		return libelleAr;
	}
	/**
	 * [Etape.libelleAr] Setter 
	 * @author rlaib on : 15 janv. 2015  10:31:38
	 * @param libelleAr the libelleAr to set
	 */
	public void setLibelleAr(String libelleAr) {
		this.libelleAr = libelleAr;
	}
	/**
	 * [Etape.estMultiple] Getter 
	 * @author rlaib on : 15 janv. 2015  10:31:38
	 * @return the estMultiple
	 */
	@Column(name = "est_multiple")
	public Boolean getEstMultiple() {
		return estMultiple;
	}
	/**
	 * [Etape.estMultiple] Setter 
	 * @author rlaib on : 15 janv. 2015  10:31:38
	 * @param estMultiple the estMultiple to set
	 */
	public void setEstMultiple(Boolean estMultiple) {
		this.estMultiple = estMultiple;
	}
	/**
	 * [Etape.multiplicite] Getter 
	 * @author rlaib on : 15 janv. 2015  10:31:38
	 * @return the multiplicite
	 */
	@Column(name = "multiplicite")
	public Integer getMultiplicite() {
		return multiplicite;
	}
	/**
	 * [Etape.multiplicite] Setter 
	 * @author rlaib on : 15 janv. 2015  10:31:38
	 * @param multiplicite the multiplicite to set
	 */
	public void setMultiplicite(Integer multiplicite) {
		this.multiplicite = multiplicite;
	}
	/**
	 * [Etape.processus] Getter 
	 * @author rlaib on : 15 janv. 2015  10:31:38
	 * @return the processus
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_processus", nullable = false)
	public Processus getProcessus() {
		return processus;
	}
	/**
	 * [Etape.processus] Setter 
	 * @author rlaib on : 15 janv. 2015  10:31:38
	 * @param processus the processus to set
	 */
	public void setProcessus(Processus processus) {
		this.processus = processus;
	}
	/**
	 * [Etape.refGroupe] Getter 
	 * @author rlaib on : 15 janv. 2015  10:31:38
	 * @return the refGroupe
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_groupe", nullable = false)
	public RefGroupe getRefGroupe() {
		return refGroupe;
	}
	/**
	 * [Etape.refGroupe] Setter 
	 * @author rlaib on : 15 janv. 2015  10:31:38
	 * @param refGroupe the refGroupe to set
	 */
	public void setRefGroupe(RefGroupe refGroupe) {
		this.refGroupe = refGroupe;
	}
	/**
	 * [Etape.roleGroupe] Getter 
	 * @author rlaib on : 15 janv. 2015  10:31:38
	 * @return the roleGroupe
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_role", nullable = false)
	public Nomenclature getRoleGroupe() {
		return roleGroupe;
	}
	/**
	 * [Etape.roleGroupe] Setter 
	 * @author rlaib on : 15 janv. 2015  10:31:38
	 * @param roleGroupe the roleGroupe to set
	 */
	public void setRoleGroupe(Nomenclature roleGroupe) {
		this.roleGroupe = roleGroupe;
	}
	/**
	 * [Etape.etapePrecedente] Getter 
	 * @author rlaib on : 15 janv. 2015  10:31:38
	 * @return the etapePrecedente
	 */
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_etape_precedente", nullable = false)
	public Etape getEtapePrecedente() {
		return etapePrecedente;
	}
	/**
	 * [Etape.etapePrecedente] Setter 
	 * @author rlaib on : 15 janv. 2015  10:31:38
	 * @param etapePrecedente the etapePrecedente to set
	 */
	public void setEtapePrecedente(Etape etapePrecedente) {
		this.etapePrecedente = etapePrecedente;
	}
	/**
	 * [Etape.etapeSuivante] Getter 
	 * @author rlaib on : 15 janv. 2015  10:31:38
	 * @return the etapeSuivante
	 */
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_etape_suivante", nullable = false)
	public Etape getEtapeSuivante() {
		return etapeSuivante;
	}
	/**
	 * [Etape.etapeSuivante] Setter 
	 * @author rlaib on : 15 janv. 2015  10:31:38
	 * @param etapeSuivante the etapeSuivante to set
	 */
	public void setEtapeSuivante(Etape etapeSuivante) {
		this.etapeSuivante = etapeSuivante;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_action", nullable = false)
	public ActionEntite getActionEntite() {
		return actionEntite;
	}
	public void setActionEntite(ActionEntite actionEntite) {
		this.actionEntite = actionEntite;
	}
	/**
	 * [Etape.situationEntite] Getter 
	 * @author rlaib on : 15 janv. 2015  10:31:38
	 * @return the situationEntite
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_situation", nullable = false)
	public SituationEntite getSituationEntite() {
		return situationEntite;
	}
	/**
	 * [Etape.situationEntite] Setter 
	 * @author rlaib on : 15 janv. 2015  10:31:38
	 * @param situationEntite the situationEntite to set
	 */
	public void setSituationEntite(SituationEntite situationEntite) {
		this.situationEntite = situationEntite;
	}

	
}
