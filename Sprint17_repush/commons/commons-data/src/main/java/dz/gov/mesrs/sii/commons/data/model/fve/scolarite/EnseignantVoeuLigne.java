/**
 * [dz.gov.mesrs.sii.fve.business.model.bo.scolarite.enseignant_voeu_ligne.java] 
 * @author rlaib on : 12 oct. 2014  14:03:13
 */
package dz.gov.mesrs.sii.commons.data.model.fve.scolarite;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.AtomePedagogique;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.Niveau;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.OuvertureOffreFormation;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.Periode;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.RattachementMc;

/**
 * @author rlaib  on : 12 oct. 2014  14:03:13
 */
@Entity
@Table(name = "enseignant_voeu_ligne", schema = "fve_scolarite")
public class EnseignantVoeuLigne implements java.io.Serializable  {
	
	/**
	 * serialVersionUID 
	 * @author rlaib  on : 29 oct. 2014  16:05:42
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private EnseignantVoeu enseignantVoeu;
	private Niveau niveau;
	private RattachementMc rattachementMc;
	private AtomePedagogique ap;
	private int nbAnneeActivite;
	private String observation;
	private Boolean estValidee = false;
	private OuvertureOffreFormation ouvertureOffreFormation; 
	private Periode periode; 
		
	public EnseignantVoeuLigne() {
		
	}

	/**
	 * [EnseignantVoeuLigne.id] Getter 
	 * @author rlaib on : 12 oct. 2014  14:57:00
	 * @return the id
	 */
	@SequenceGenerator(name="enseignant_voeu_ligne_id_seq_gen", sequenceName="fve_scolarite.enseignant_voeu_ligne_id_seq")
	@Id @GeneratedValue(generator="enseignant_voeu_ligne_id_seq_gen")
	@Column(name = "id")
	public int getId() {
		return id;
	}

	/**
	 * [EnseignantVoeuLigne.id] Setter 
	 * @author rlaib on : 12 oct. 2014  14:57:00
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * [EnseignantVoeuLigne.enseignantVoeu] Getter 
	 * @author rlaib on : 12 oct. 2014  14:57:00
	 * @return the enseignantVoeu
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_enseignant_voeu", nullable = false)
	public EnseignantVoeu getEnseignantVoeu() {
		return enseignantVoeu;
	}

	/**
	 * [EnseignantVoeuLigne.enseignantVoeu] Setter 
	 * @author rlaib on : 12 oct. 2014  14:57:00
	 * @param enseignantVoeu the enseignantVoeu to set
	 */
	public void setEnseignantVoeu(EnseignantVoeu enseignantVoeu) {
		this.enseignantVoeu = enseignantVoeu;
	}

	/**
	 * [EnseignantVoeuLigne.niveau] Getter 
	 * @author rlaib on : 12 oct. 2014  14:57:00
	 * @return the niveau
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_niveau", nullable = false)
	public Niveau getNiveau() {
		return niveau;
	}

	/**
	 * [EnseignantVoeuLigne.niveau] Setter 
	 * @author rlaib on : 12 oct. 2014  14:57:00
	 * @param niveau the niveau to set
	 */
	public void setNiveau(Niveau niveau) {
		this.niveau = niveau;
	}

	/**
	 * [EnseignantVoeuLigne.rattachementMc] Getter 
	 * @author rlaib on : 12 oct. 2014  14:57:00
	 * @return the rattachementMc
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_rattachement_mc", nullable = false)
	public RattachementMc getRattachementMc() {
		return rattachementMc;
	}

	/**
	 * [EnseignantVoeuLigne.rattachementMc] Setter 
	 * @author rlaib on : 12 oct. 2014  14:57:00
	 * @param rattachementMc the rattachementMc to set
	 */
	public void setRattachementMc(RattachementMc rattachementMc) {
		this.rattachementMc = rattachementMc;
	}

	
	/**
	 * [EnseignantVoeuLigne.ap] Getter 
	 * @author rlaib on : 16 oct. 2014  12:21:26
	 * @return the ap
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_ap", nullable = false)
	public AtomePedagogique getAp() {
		return ap;
	}

	/**
	 * [EnseignantVoeuLigne.ap] Setter 
	 * @author rlaib on : 16 oct. 2014  12:21:26
	 * @param ap the ap to set
	 */
	public void setAp(AtomePedagogique ap) {
		this.ap = ap;
	}

	/**
	 * [EnseignantVoeuLigne.nbAnneeActivite] Getter 
	 * @author rlaib on : 12 oct. 2014  14:57:00
	 * @return the nbAnneeActivite
	 */
	@Column(name = "nb_annee_activite")
	public int getNbAnneeActivite() {
		return nbAnneeActivite;
	}

	/**
	 * [EnseignantVoeuLigne.nbAnneeActivite] Setter 
	 * @author rlaib on : 12 oct. 2014  14:57:00
	 * @param nbAnneeActivite the nbAnneeActivite to set
	 */
	public void setNbAnneeActivite(int nbAnneeActivite) {
		this.nbAnneeActivite = nbAnneeActivite;
	}

	/**
	 * [EnseignantVoeuLigne.abservation] Getter 
	 * @author rlaib on : 12 oct. 2014  14:57:00
	 * @return the abservation
	 */
	@Column(name = "observation")
	public String getObservation() {
		return observation;
	}

	/**
	 * [EnseignantVoeuLigne.abservation] Setter 
	 * @author rlaib on : 12 oct. 2014  14:57:00
	 * @param abservation the abservation to set
	 */
	public void setObservation(String observation) {
		this.observation = observation;
	}

	/**
	 * [EnseignantVoeuLigne.estValidee] Getter 
	 * @author rlaib on : 26 oct. 2014  17:07:56
	 * @return the estValidee
	 */
	@Column(name = "est_validee")
	public Boolean getEstValidee() {
		return estValidee;
	}

	/**
	 * [EnseignantVoeuLigne.estValidee] Setter 
	 * @author rlaib on : 26 oct. 2014  17:07:56
	 * @param estValidee the estValidee to set
	 */
	public void setEstValidee(Boolean estValidee) {
		this.estValidee = estValidee;
	}

	/**
	 * [EnseignantVoeuLigne.ouvertureOffreFormation] Getter 
	 * @author rlaib on : 7 déc. 2014  11:37:46
	 * @return the ouvertureOffreFormation
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_offre_formation", nullable = false)
	public OuvertureOffreFormation getOuvertureOffreFormation() {
		return ouvertureOffreFormation;
	}

	/**
	 * [EnseignantVoeuLigne.ouvertureOffreFormation] Setter 
	 * @author rlaib on : 7 déc. 2014  11:37:46
	 * @param ouvertureOffreFormation the ouvertureOffreFormation to set
	 */
	public void setOuvertureOffreFormation(
			OuvertureOffreFormation ouvertureOffreFormation) {
		this.ouvertureOffreFormation = ouvertureOffreFormation;
	}

	/**
	 * [EnseignantVoeuLigne.periode] Getter 
	 * @author rlaib on : 7 déc. 2014  13:44:30
	 * @return the periode
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_periode", nullable = false)
	public Periode getPeriode() {
		return periode;
	}

	/**
	 * [EnseignantVoeuLigne.periode] Setter 
	 * @author rlaib on : 7 déc. 2014  13:44:30
	 * @param periode the periode to set
	 */
	public void setPeriode(Periode periode) {
		this.periode = periode;
	}

	
}
