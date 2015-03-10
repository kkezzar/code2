package dz.gov.mesrs.sii.commons.data.model.fve.cursus;

// Generated 21 mai 2014 09:17:10 by Hibernate Tools 3.6.0

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OrderColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.OrderBy;
import org.springframework.core.annotation.Order;

import dz.gov.mesrs.sii.commons.data.model.referentiel.RefTypePieceConstitutive;

/**
 * PieceConstitutive generated by hbm2java
 */
@Entity
@Table(name = "piece_constitutive", schema = "cursus")
public class PieceConstitutive implements java.io.Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author BELDI Jamel on : 21 mai 2014 09:19:51
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private Dossier dossier;
	private Boolean fournie;
	private Date dateFourniture;
	private String observation;
	private RefTypePieceConstitutive refTypePieceConstitutive;

	public PieceConstitutive() {
	}

	public PieceConstitutive(int id) {
		this.id = id;
	}

	public PieceConstitutive(int id, Dossier dossier, Boolean fournie,
			Date dateFourniture, String observation) {
		this.id = id;
		this.dossier = dossier;
		this.fournie = fournie;
		this.dateFourniture = dateFourniture;
		this.observation = observation;

	}

	@SequenceGenerator(name = "pieceConstitutive_id_seq_gen", sequenceName = "cursus.piece_ent_sort_id_seq")
	@Id
	@GeneratedValue(generator = "pieceConstitutive_id_seq_gen")
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_dossier")
	public Dossier getDossier() {
		return this.dossier;
	}

	public void setDossier(Dossier dossier) {
		this.dossier = dossier;
	}

	@Column(name = "fournie")
	public Boolean getFournie() {
		return this.fournie;
	}

	public void setFournie(Boolean fournie) {
		this.fournie = fournie;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_fourniture", length = 13)
	public Date getDateFourniture() {
		return this.dateFourniture;
	}

	public void setDateFourniture(Date dateFourniture) {
		this.dateFourniture = dateFourniture;
	}

	@Column(name = "observation", length = 200)
	public String getObservation() {
		return this.observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}
	

	/**
	 * [PieceConstitutive.refTypePieceConstitutive] Getter 
	 * @author MAKERRI Sofiane on : 22 déc. 2014  09:46:16
	 * @return the refTypePieceConstitutive
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_ref_type_piece", nullable = false)
	public RefTypePieceConstitutive getRefTypePieceConstitutive() {
		return refTypePieceConstitutive;
	}

	/**
	 * [PieceConstitutive.refTypePieceConstitutive] Setter 
	 * @author MAKERRI Sofiane on : 22 déc. 2014  09:46:16
	 * @param refTypePieceConstitutive the refTypePieceConstitutive to set
	 */
	public void setRefTypePieceConstitutive(RefTypePieceConstitutive refTypePieceConstitutive) {
		this.refTypePieceConstitutive = refTypePieceConstitutive;
	}
	
	

}