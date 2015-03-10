/**
 * [dz.gov.mesrs.sii.fve.business.model.bo.habilitation.Demande.java] 
 * @author rlaib on : 29 avr. 2014  09:12:22
 */
package dz.gov.mesrs.sii.commons.data.model.bpm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.OffreFormation;

/**
 * @author rlaib  on : 29 avr. 2014  09:12:22
 */
@Entity
@Table(name = "demande", schema = "bpm")
public class Demande implements java.io.Serializable {
	
	/**
	 * serialVersionUID 
	 * @author rlaib  on : 29 avr. 2014  15:18:10
	 */
	private static final long serialVersionUID = 4253528206947710642L;
	private int id;
	private String code;
	private Date dateDemande;
	private Date dateCreation;
	//private int idOffreFormation;
	private OffreFormation offreFormation;
	private TypeDemande typeDemande;
	private SituationEntite situationEntite;
	private Map<String,DemandeI18n> i18n = new HashMap<String, DemandeI18n>();

	
	public Demande() {
	}

	/**
	 * [Demande.id] Getter 
	 * @author rlaib on : 29 avr. 2014  10:57:20
	 * @return the id
	 */
	@SequenceGenerator(name="demande_id_seq_gen", sequenceName="bpm.demande_id_seq")
	@Id @GeneratedValue(generator="demande_id_seq_gen")
	@Column(name = "id")
	public int getId() {
		return id;
	}

	/**
	 * [Demande.id] Setter 
	 * @author rlaib on : 29 avr. 2014  10:57:20
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * [Demande.code] Getter 
	 * @author rlaib on : 29 avr. 2014  10:57:20
	 * @return the code
	 */
	@Column(name = "code", nullable = false,length=10)
	public String getCode() {
		return code;
	}

	/**
	 * [Demande.code] Setter 
	 * @author rlaib on : 29 avr. 2014  10:57:20
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	
	/**
	 * [Demande.typeDemande] Getter 
	 * @author rlaib on : 29 avr. 2014  10:57:20
	 * @return the typeDemande
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_type_demande", nullable = false)
	public TypeDemande getTypeDemande() {
		return typeDemande;
	}

	/**
	 * [Demande.typeDemande] Setter 
	 * @author rlaib on : 29 avr. 2014  10:57:20
	 * @param typeDemande the typeDemande to set
	 */
	public void setTypeDemande(TypeDemande typeDemande) {
		this.typeDemande = typeDemande;
	}

	/**
	 * [Demande.dateDemande] Getter 
	 * @author rlaib on : 29 avr. 2014  11:02:25
	 * @return the dateDemande
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_demande", length = 29)
	public Date getDateDemande() {
		return dateDemande;
	}

	/**
	 * [Demande.dateDemande] Setter 
	 * @author rlaib on : 29 avr. 2014  11:02:25
	 * @param dateDemande the dateDemande to set
	 */
	public void setDateDemande(Date dateDemande) {
		this.dateDemande = dateDemande;
	}

	/**
	 * [Demande.dateCreation] Getter 
	 * @author rlaib on : 29 avr. 2014  11:02:25
	 * @return the dateCreation
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_creation", length = 29)
	public Date getDateCreation() {
		return dateCreation;
	}

	/**
	 * [Demande.dateCreation] Setter 
	 * @author rlaib on : 29 avr. 2014  11:02:25
	 * @param dateCreation the dateCreation to set
	 */
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

//	/**
//	 * [Demande.idOffreFormation] Getter 
//	 * @author rlaib on : 29 avr. 2014  11:02:25
//	 * @return the idOffreFormation
//	 */
//	@Column(name = "id_offre_formation")
//	public int getIdOffreFormation() {
//		return idOffreFormation;
//	}
//
//	/**
//	 * [Demande.idOffreFormation] Setter 
//	 * @author rlaib on : 29 avr. 2014  11:02:25
//	 * @param idOffreFormation the idOffreFormation to set
//	 */
//	public void setIdOffreFormation(int idOffreFormation) {
//		this.idOffreFormation = idOffreFormation;
//	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_offre_formation")
	public OffreFormation getOffreFormation() {
		return this.offreFormation;
	}

	public void setOffreFormation(OffreFormation offreFormation) {
		this.offreFormation = offreFormation;
	}


	/**
	 * [Demande.situationEntite] Getter 
	 * @author rlaib on : 29 avr. 2014  11:29:06
	 * @return the situationEntite
	 */
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_situation", nullable = false)
	public SituationEntite getSituationEntite() {
		return situationEntite;
	}

	/**
	 * [Demande.situationEntite] Setter 
	 * @author rlaib on : 29 avr. 2014  11:29:06
	 * @param situationEntite the situationEntite to set
	 */
	public void setSituationEntite(SituationEntite situationEntite) {
		this.situationEntite = situationEntite;
	}

	/**
	 * [Demande.i18n] Getter 
	 * @author rlaib on : 29 avr. 2014  11:35:28
	 * @return the i18n
	 */
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "demande", targetEntity= DemandeI18n.class)
	@MapKey(name="langue")
	public Map<String, DemandeI18n> getI18n() {
		return i18n;
	}

	/**
	 * [Demande.i18n] Setter 
	 * @author rlaib on : 29 avr. 2014  11:35:28
	 * @param i18n the i18n to set
	 */
	public void setI18n(Map<String, DemandeI18n> i18n) {
		this.i18n = i18n;
	}
	
	
	
}
