package dz.gov.mesrs.sii.commons.data.model.fve.offreformation;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * @author BELDI Jamel on : 12 mai 2014 14:12:02
 */
@Entity
@Table(name = "annee_academique", schema = "lmd")
public class AnneeAcademique implements java.io.Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author BELDI Jamel on : 12 mai 2014 14:09:19
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private short premiereAnnee;
	private short deuxiemeAnnee;
	private Date dateDebut;
	private Date dateFin;


	private Set<OuvertureOffreFormation> ouvertureOffreFormations = new HashSet<OuvertureOffreFormation>(
			0);

	public AnneeAcademique() {
	}

	public AnneeAcademique(int id) {
		this.id = id;
	}
	public AnneeAcademique(int id, short premiereAnnee, short deuxiemeAnnee) {
		this.id = id;
		this.premiereAnnee = premiereAnnee;
		this.deuxiemeAnnee = deuxiemeAnnee;
	}

	public AnneeAcademique(int id, short premiereAnnee, short deuxiemeAnnee,
			Set<OuvertureOffreFormation> ouvertureOffreFormations) {
		this.id = id;
		this.premiereAnnee = premiereAnnee;
		this.deuxiemeAnnee = deuxiemeAnnee;
		this.ouvertureOffreFormations = ouvertureOffreFormations;
	}

	@SequenceGenerator(name = "annee_academique_id_seq_gen", sequenceName = "lmd.annee_academique_id_seq")
	@Id
	@GeneratedValue(generator = "annee_academique_id_seq_gen")
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "premiere_annee", nullable = false)
	public short getPremiereAnnee() {
		return this.premiereAnnee;
	}

	public void setPremiereAnnee(short premiereAnnee) {
		this.premiereAnnee = premiereAnnee;
	}

	@Column(name = "deuxieme_annee", nullable = false)
	public short getDeuxiemeAnnee() {
		return this.deuxiemeAnnee;
	}

	public void setDeuxiemeAnnee(short deuxiemeAnnee) {
		this.deuxiemeAnnee = deuxiemeAnnee;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "anneeAcademique")
	public Set<OuvertureOffreFormation> getOuvertureOffreFormations() {
		return this.ouvertureOffreFormations;
	}

	public void setOuvertureOffreFormations(
			Set<OuvertureOffreFormation> ouvertureOffreFormations) {
		this.ouvertureOffreFormations = ouvertureOffreFormations;
	}
	
	@Transient
	public String getCode() {
		return premiereAnnee + "/" + deuxiemeAnnee;
	}

	public void setCode(String code) {
	}

	/**
	 * [AnneeAcademique.dateDebut] Getter 
	 * @author MAKERRI Sofiane on : 21 janv. 2015  09:38:10
	 * @return the dateDebut
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_debut", length = 29, nullable= false)
	public Date getDateDebut() {
		return dateDebut;
	}

	/**
	 * [AnneeAcademique.dateDebut] Setter 
	 * @author MAKERRI Sofiane on : 21 janv. 2015  09:38:10
	 * @param dateDebut the dateDebut to set
	 */
	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	/**
	 * [AnneeAcademique.dateFin] Getter 
	 * @author MAKERRI Sofiane on : 21 janv. 2015  09:38:10
	 * @return the dateFin
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_fin", length = 29, nullable= false)
	public Date getDateFin() {
		return dateFin;
	}

	/**
	 * [AnneeAcademique.dateFin] Setter 
	 * @author MAKERRI Sofiane on : 21 janv. 2015  09:38:10
	 * @param dateFin the dateFin to set
	 */
	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}
	
	
}
