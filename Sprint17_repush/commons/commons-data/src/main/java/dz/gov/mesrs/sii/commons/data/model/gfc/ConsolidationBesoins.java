package dz.gov.mesrs.sii.commons.data.model.gfc;

// Generated 24 nov. 2014 16:52:54 by Hibernate Tools 3.6.0

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import dz.gov.mesrs.sii.commons.data.model.Identifiable;

/**
 * ConsolidationBesoins generated by hbm2java
 */
@Entity
@Table(name = "consolidation_besoins", schema = "gfc")
public class ConsolidationBesoins implements java.io.Serializable, Identifiable<Integer> {

	/**
	 * @author Mounir.MESSAOUDI on : 24 nov. 2014 17:32:17
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private FicheBesoins ficheBesoins;
	private Chapitre chapitre;
	private Article article;
	private BigDecimal montantDemande;
	private BigDecimal montantPropose;

	public ConsolidationBesoins() {
	}

	@SequenceGenerator(name = "consolidation_besoins_id_seq", sequenceName = "gfc.consolidation_besoins_id_seq")
	@Id
	@GeneratedValue(generator = "consolidation_besoins_id_seq")
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_fiche_besoins", nullable = false)
	public FicheBesoins getFicheBesoins() {
		return this.ficheBesoins;
	}

	public void setFicheBesoins(FicheBesoins ficheBesoins) {
		this.ficheBesoins = ficheBesoins;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_chapitre", nullable = false)
	public Chapitre getChapitre() {
		return this.chapitre;
	}

	public void setChapitre(Chapitre chapitre) {
		this.chapitre = chapitre;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_article")
	public Article getArticle() {
		return this.article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	@Column(name = "montant_demande", nullable = false, precision = 10)
	public BigDecimal getMontantDemande() {
		return this.montantDemande;
	}

	public void setMontantDemande(BigDecimal montantDemande) {
		this.montantDemande = montantDemande;
	}

	@Column(name = "montant_propose", nullable = false, precision = 10)
	public BigDecimal getMontantPropose() {
		return this.montantPropose;
	}

	public void setMontantPropose(BigDecimal montantPropose) {
		this.montantPropose = montantPropose;
	}

	@Transient
	@Override
	public Integer getIdenfiant() {
		return id;
	}

	@Transient
	@Override
	public String getIdentifiantName() {
		return "id";
	}

}
