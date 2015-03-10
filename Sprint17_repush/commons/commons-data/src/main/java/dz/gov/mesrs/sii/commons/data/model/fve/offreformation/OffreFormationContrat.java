/**
 * [dz.gov.mesrs.sii.fve.business.model.bo.offreformation.OffreFormationEquipe.java] 
 * @author rlaib on : 6 fevr. 2014  12:22:02
 */
package dz.gov.mesrs.sii.commons.data.model.fve.offreformation;

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author rlaib  on : 25 mars 2014  09:51:17
 */
@Entity
@Table(name = "offre_formation_contrat", schema = "lmd")
public class OffreFormationContrat  implements java.io.Serializable{

	private static final long serialVersionUID = 1L;

	private int id;
	private String refCodeContrat;
	private String libelleFr;
	private String libelleAr;
	private OffreFormation offreFormation;
	private Date dateCreation;
	private Date dateDerniereModif;
	private String reference_contrat;
	private Set<OffreFormationPartenaire> offreFormationPartenaires = new HashSet<OffreFormationPartenaire>(0);

	public OffreFormationContrat() {
	}

	public OffreFormationContrat(int id, String refCodeContrat, String libelleFr, String libelleAr) {
		this.id = id;
		this.refCodeContrat = refCodeContrat;
		this.libelleFr = libelleFr;
		this.libelleAr = libelleAr;
	}

	/**
	 * [OffreFormationContrat.id] Getter 
	 * @author rlaib on : 6 fevr. 2014  12:36:52
	 * @return the id
	 */
	
	@SequenceGenerator(name="offre_formation_contrat_id_seq_gen", sequenceName="lmd.offre_formation_contrat_id_seq")
	@Id @GeneratedValue(generator="offre_formation_contrat_id_seq_gen")
	@Column(name = "id")
	public int getId() {
		return id;
	}

	/**
	 * [OffreFormationContrat.id] Setter 
	 * @author rlaib on : 6 fevr. 2014  12:36:52
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	

	/**
	 * [OffreFormationContrat.refCodeContrat] Getter 
	 * @author  Rafik LAIB on : 5 avr. 2014  20:37:58
	 * @return the refCodeContrat
	 */
	@Column(name = "ref_code_contrat", nullable = false, length = 50)
	public String getRefCodeContrat() {
		return refCodeContrat;
	}

	
	/**
	 * [OffreFormationContrat.refCodeContrat] Setter 
	 * @author  Rafik LAIB on : 5 avr. 2014  20:37:58
	 * @param refCodeContrat the refCodeContrat to set
	 */
	public void setRefCodeContrat(String refCodeContrat) {
		this.refCodeContrat = refCodeContrat;
	}
	

	/**
	 * [OffreFormationContrat.libelleFr] Getter 
	 * @author  Rafik LAIB on : 5 avr. 2014  20:37:58
	 * @return the libelleFr
	 */
	@Column(name = "libelle_fr", nullable = false, length = 255)
	public String getLibelleFr() {
		return libelleFr;
	}
	

	/**
	 * [OffreFormationContrat.libelleFr] Setter 
	 * @author  Rafik LAIB on : 5 avr. 2014  20:37:58
	 * @param libelleFr the libelleFr to set
	 */
	public void setLibelleFr(String libelleFr) {
		this.libelleFr = libelleFr;
	}
	

	/**
	 * [OffreFormationContrat.libelleAr] Getter 
	 * @author  Rafik LAIB on : 5 avr. 2014  20:37:58
	 * @return the libelleAr
	 */
	@Column(name = "libelle_ar", length = 255)
	public String getLibelleAr() {
		return libelleAr;
	}
	

	/**
	 * [OffreFormationContrat.libelleAr] Setter 
	 * @author  Rafik LAIB on : 5 avr. 2014  20:37:58
	 * @param libelleAr the libelleAr to set
	 */
	public void setLibelleAr(String libelleAr) {
		this.libelleAr = libelleAr;
	}
	

	/**
	 * [OffreFormationContrat.offreFormation] Getter 
	 * @author  Rafik LAIB on : 5 avr. 2014  20:37:58
	 * @return the offreFormation
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "  id_offre_formation")
	public OffreFormation getOffreFormation() {
		return offreFormation;
	}
	

	/**
	 * [OffreFormationContrat.offreFormation] Setter 
	 * @author  Rafik LAIB on : 5 avr. 2014  20:37:58
	 * @param offreFormation the offreFormation to set
	 */
	public void setOffreFormation(OffreFormation offreFormation) {
		this.offreFormation = offreFormation;
	}
	

	/**
	 * [OffreFormationContrat.dateCreation] Getter 
	 * @author  Rafik LAIB on : 5 avr. 2014  20:37:58
	 * @return the dateCreation
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_creation", length = 29)
	public Date getDateCreation() {
		return dateCreation;
	}
	

	/**
	 * [OffreFormationContrat.dateCreation] Setter 
	 * @author  Rafik LAIB on : 5 avr. 2014  20:37:58
	 * @param dateCreation the dateCreation to set
	 */
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	

	/**
	 * [OffreFormationContrat.dateDerniereModif] Getter 
	 * @author  Rafik LAIB on : 5 avr. 2014  20:37:58
	 * @return the dateDerniereModif
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_derniere_modif", length = 29)
	public Date getDateDerniereModif() {
		return dateDerniereModif;
	}
	

	/**
	 * [OffreFormationContrat.dateDerniereModif] Setter 
	 * @author  Rafik LAIB on : 5 avr. 2014  20:37:58
	 * @param dateDerniereModif the dateDerniereModif to set
	 */
	public void setDateDerniereModif(Date dateDerniereModif) {
		this.dateDerniereModif = dateDerniereModif;
	}
	

	/**
	 * [OffreFormationContrat.reference_contrat] Getter 
	 * @author  Rafik LAIB on : 5 avr. 2014  20:37:58
	 * @return the reference_contrat
	 */
	@Column(name = "reference_contrat", length = 50)
	public String getReference_contrat() {
		return reference_contrat;
	}
	

	/**
	 * [OffreFormationContrat.reference_contrat] Setter 
	 * @author  Rafik LAIB on : 5 avr. 2014  20:37:58
	 * @param reference_contrat the reference_contrat to set
	 */
	public void setReference_contrat(String reference_contrat) {
		this.reference_contrat = reference_contrat;
	}
	

	/**
	 * [OffreFormationContrat.offreFormationPartenaires] Getter 
	 * @author  Rafik LAIB on : 5 avr. 2014  20:37:58
	 * @return the offreFormationPartenaires
	 */
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "offreFormationContrat")
	public Set<OffreFormationPartenaire> getOffreFormationPartenaires() {
		return offreFormationPartenaires;
	}
	

	/**
	 * [OffreFormationContrat.offreFormationPartenaires] Setter 
	 * @author  Rafik LAIB on : 5 avr. 2014  20:37:58
	 * @param offreFormationPartenaires the offreFormationPartenaires to set
	 */
	public void setOffreFormationPartenaires(
			Set<OffreFormationPartenaire> offreFormationPartenaires) {
		this.offreFormationPartenaires = offreFormationPartenaires;
	}

	
}
