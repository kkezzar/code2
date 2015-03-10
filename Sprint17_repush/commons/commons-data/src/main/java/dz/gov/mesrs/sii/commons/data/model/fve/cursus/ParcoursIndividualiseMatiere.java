package dz.gov.mesrs.sii.commons.data.model.fve.cursus;

// Generated 14 juil. 2014 15:17:29 by Hibernate Tools 3.6.0

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
import javax.persistence.UniqueConstraint;

import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.RattachementMc;


/**
 * @author BELDI Jamel  on : 14 juil. 2014  15:21:15
 */
@Entity
@Table(name = "parcours_individualise_matiere", schema = "cursus", uniqueConstraints = @UniqueConstraint(columnNames = {
		"id_parcours_individualise", "id_rattachement_mc" }))
public class ParcoursIndividualiseMatiere implements java.io.Serializable {

	/**
	 * serialVersionUID 
	 * @author BELDI Jamel  on : 14 juil. 2014  15:20:53
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private RattachementMc rattachementMc;
	private ParcoursIndividualise parcoursIndividualise;
	private boolean choix;
	private String remarque;
	private Date dateRemarque;

	public ParcoursIndividualiseMatiere() {
	}

	public ParcoursIndividualiseMatiere(int id, RattachementMc rattachementMc,
			ParcoursIndividualise parcoursIndividualise, boolean choix) {
		this.id = id;
		this.rattachementMc = rattachementMc;
		this.parcoursIndividualise = parcoursIndividualise;
		this.choix = choix;
	}

	public ParcoursIndividualiseMatiere(int id, RattachementMc rattachementMc,
			ParcoursIndividualise parcoursIndividualise, boolean choix,
			String remarque, Date dateRemarque) {
		this.id = id;
		this.rattachementMc = rattachementMc;
		this.parcoursIndividualise = parcoursIndividualise;
		this.choix = choix;
		this.remarque = remarque;
		this.dateRemarque = dateRemarque;
	}

	@Id
	@SequenceGenerator(name="parcours_individualise_matiere_id_seq_gen", sequenceName="cursus.parcours_individualise_matiere_id_seq")
	@GeneratedValue(generator="parcours_individualise_matiere_id_seq_gen")
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_rattachement_mc", nullable = false)
	public RattachementMc getRattachementMc() {
		return this.rattachementMc;
	}

	public void setRattachementMc(RattachementMc rattachementMc) {
		this.rattachementMc = rattachementMc;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_parcours_individualise", nullable = false)
	public ParcoursIndividualise getParcoursIndividualise() {
		return this.parcoursIndividualise;
	}

	public void setParcoursIndividualise(
			ParcoursIndividualise parcoursIndividualise) {
		this.parcoursIndividualise = parcoursIndividualise;
	}

	@Column(name = "choix", nullable = false)
	public boolean isChoix() {
		return this.choix;
	}

	public void setChoix(boolean choix) {
		this.choix = choix;
	}

	@Column(name = "remarque")
	public String getRemarque() {
		return this.remarque;
	}

	public void setRemarque(String remarque) {
		this.remarque = remarque;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_remarque", length = 35)
	public Date getDateRemarque() {
		return this.dateRemarque;
	}

	public void setDateRemarque(Date dateRemarque) {
		this.dateRemarque = dateRemarque;
	}

}
