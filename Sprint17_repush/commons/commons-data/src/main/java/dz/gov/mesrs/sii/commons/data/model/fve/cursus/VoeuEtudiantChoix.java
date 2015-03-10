/**
 *  
 * @author Mounir.MESSAOUDI on : 28 sept. 2014 15:28:03
 */
package dz.gov.mesrs.sii.commons.data.model.fve.cursus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.OuvertureOffreFormation;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefDomaineLMD;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefFiliereLmd;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefSpecialiteLmd;

/**
 * @author Mounir.MESSAOUDI on : 28 sept. 2014 15:28:03
 */

@Entity
@Table(name = "voeu_etudiant_choix", schema = "cursus")
public class VoeuEtudiantChoix implements java.io.Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author MESSAOUDI Mounir on : 28 septembre 2014 09:00:31
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;

	private VoeuEtudiant voeuEtudiant;
	
	private OuvertureOffreFormation ouvertureOffreFormation;
	private RefDomaineLMD domaine;
	private RefFiliereLmd filiere;
	private RefSpecialiteLmd specialite;

	private Integer priorite;

	private Boolean estRetenu;

	/**
	 * @author Mounir.MESSAOUDI on : 28 sept. 2014 15:28:03
	 */
	public VoeuEtudiantChoix() {
		// TODO Auto-generated constructor stub
	}

	@Id
	@SequenceGenerator(name = "voeu_etudiant_choix_id_seq", sequenceName = "cursus.voeu_etudiant_choix_id_seq")
	@GeneratedValue(generator = "voeu_etudiant_choix_id_seq")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_voeu_etudiant", nullable = false)
	public VoeuEtudiant getVoeuEtudiant() {
		return voeuEtudiant;
	}

	public void setVoeuEtudiant(VoeuEtudiant voeuEtudiant) {
		this.voeuEtudiant = voeuEtudiant;
	}

	@Column(name = "priorite")
	public Integer getPriorite() {
		return priorite;
	}

	public void setPriorite(Integer priorite) {
		this.priorite = priorite;
	}

	public Boolean getEstRetenu() {
		return estRetenu;
	}

	@Column(name = "est_retenu")
	public void setEstRetenu(Boolean estRetenu) {
		this.estRetenu = estRetenu;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_ouverture_of", nullable = false)
	public OuvertureOffreFormation getOuvertureOffreFormation() {
		return ouvertureOffreFormation;
	}

	public void setOuvertureOffreFormation(
			OuvertureOffreFormation ouvertureOffreFormation) {
		this.ouvertureOffreFormation = ouvertureOffreFormation;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_domaine", nullable = false)
	public RefDomaineLMD getDomaine() {
		return domaine;
	}

	public void setDomaine(RefDomaineLMD domaine) {
		this.domaine = domaine;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_filiere", nullable = false)
	public RefFiliereLmd getFiliere() {
		return filiere;
	}

	public void setFiliere(RefFiliereLmd filiere) {
		this.filiere = filiere;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_specialite", nullable = false)
	public RefSpecialiteLmd getSpecialite() {
		return specialite;
	}

	public void setSpecialite(RefSpecialiteLmd specialite) {
		this.specialite = specialite;
	}
}