/**
 * [dz.gov.mesrs.sii.fve.business.model.bo.cursus.BilanUe.java] 
 * @author MAKERRI Sofiane on : 16 oct. 2014  17:09:26
 */
package dz.gov.mesrs.sii.commons.data.model.fve.examen;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
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

import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.RepartitionUe;

/**
 * @author MAKERRI Sofiane on : 16 oct. 2014 17:09:26
 */
@Entity
@Table(name = "bilan_ue", schema = "fve_examen")
public class BilanUe implements java.io.Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author MAKERRI Sofiane on : 16 oct. 2014 17:11:02
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private BilanSession bilanSession;
	private RepartitionUe repartitionUe;
	private double moyenne;
	private double coefficient;
	private double credit;
	private double creditObtenu;
	private double creditAcquis;
	private List<BilanMc> bilanMcs = new ArrayList<BilanMc>();

	/**
	 * [BilanUe.BilanUe()] Constructor
	 * 
	 * @author MAKERRI Sofiane on : 16 oct. 2014 17:09:26
	 */
	public BilanUe() {
		super();
	}

	/**
	 * [BilanUe.id] Getter
	 * 
	 * @author MAKERRI Sofiane on : 16 oct. 2014 17:13:23
	 * @return the id
	 */
	@SequenceGenerator(name = "bilan_ue_id_seq", sequenceName = "fve_examen.bilan_ue_id_seq")
	@Id
	@GeneratedValue(generator = "bilan_ue_id_seq")
	@Column(name = "id", unique = true, nullable = false)
	public long getId() {
		return id;
	}

	/**
	 * [BilanUe.id] Setter
	 * 
	 * @author MAKERRI Sofiane on : 16 oct. 2014 17:13:23
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * [BilanUe.bilanSession] Getter
	 * 
	 * @author MAKERRI Sofiane on : 27 oct. 2014 09:30:10
	 * @return the bilanSession
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_bilan_session", nullable = false)
	public BilanSession getBilanSession() {
		return bilanSession;
	}

	/**
	 * [BilanUe.bilanSession] Setter
	 * 
	 * @author MAKERRI Sofiane on : 27 oct. 2014 09:30:10
	 * @param bilanSession
	 *            the bilanSession to set
	 */
	public void setBilanSession(BilanSession bilanSession) {
		this.bilanSession = bilanSession;
	}

	/**
	 * [BilanUe.repartitionUe] Getter
	 * 
	 * @author MAKERRI Sofiane on : 27 oct. 2014 10:52:03
	 * @return the repartitionUe
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_repartition_ue", nullable = false)
	public RepartitionUe getRepartitionUe() {
		return repartitionUe;
	}

	/**
	 * [BilanUe.repartitionUe] Setter
	 * 
	 * @author MAKERRI Sofiane on : 27 oct. 2014 10:52:03
	 * @param repartitionUe
	 *            the repartitionUe to set
	 */
	public void setRepartitionUe(RepartitionUe repartitionUe) {
		this.repartitionUe = repartitionUe;
	}

	/**
	 * [BilanUe.moyenne] Getter
	 * 
	 * @author MAKERRI Sofiane on : 27 oct. 2014 09:30:10
	 * @return the moyenne
	 */
	@Column(name = "moyenne")
	public double getMoyenne() {
		return moyenne;
	}

	/**
	 * [BilanUe.moyenne] Setter
	 * 
	 * @author MAKERRI Sofiane on : 27 oct. 2014 09:30:10
	 * @param moyenne
	 *            the moyenne to set
	 */
	public void setMoyenne(double moyenne) {
		this.moyenne = moyenne;
	}

	/**
	 * [BilanUe.coefficient] Getter
	 * 
	 * @author MAKERRI Sofiane on : 27 oct. 2014 09:30:10
	 * @return the coefficient
	 */
	@Column(name = "coefficient")
	public double getCoefficient() {
		return coefficient;
	}

	/**
	 * [BilanUe.coefficient] Setter
	 * 
	 * @author MAKERRI Sofiane on : 27 oct. 2014 09:30:10
	 * @param coefficient
	 *            the coefficient to set
	 */
	public void setCoefficient(double coefficient) {
		this.coefficient = coefficient;
	}

	/**
	 * [BilanUe.credit] Getter
	 * 
	 * @author MAKERRI Sofiane on : 27 oct. 2014 09:31:21
	 * @return the credit
	 */
	@Column(name = "credit")
	public double getCredit() {
		return credit;
	}

	/**
	 * [BilanUe.credit] Setter
	 * 
	 * @author MAKERRI Sofiane on : 27 oct. 2014 09:31:21
	 * @param credit
	 *            the credit to set
	 */
	public void setCredit(double credit) {
		this.credit = credit;
	}

	/**
	 * [BilanUe.creditObtenu] Getter
	 * 
	 * @author MAKERRI Sofiane on : 29 oct. 2014 10:20:15
	 * @return the creditObtenu
	 */
	@Column(name = "credit_obtenu")
	public double getCreditObtenu() {
		return creditObtenu;
	}

	/**
	 * [BilanUe.creditObtenu] Setter
	 * 
	 * @author MAKERRI Sofiane on : 29 oct. 2014 10:20:15
	 * @param creditObtenu
	 *            the creditObtenu to set
	 */
	public void setCreditObtenu(double creditObtenu) {
		this.creditObtenu = creditObtenu;
	}

	/**
	 * [BilanUe.bilanMcs] Getter
	 * 
	 * @author MAKERRI Sofiane on : 6 nov. 2014 12:16:57
	 * @return the bilanMcs
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bilanUe", cascade = CascadeType.ALL, orphanRemoval = true)
	public List<BilanMc> getBilanMcs() {
		return bilanMcs;
	}

	/**
	 * [BilanUe.bilanMcs] Setter
	 * 
	 * @author MAKERRI Sofiane on : 6 nov. 2014 12:16:57
	 * @param bilanMcs
	 *            the bilanMcs to set
	 */
	public void setBilanMcs(List<BilanMc> bilanMcs) {
		this.bilanMcs = bilanMcs;
	}

	/**
	 * [BilanUe.creditAcquis] Getter 
	 * @author MAKERRI Sofiane on : 15 janv. 2015  15:56:15
	 * @return the creditAcquis
	 */
	@Column(name = "credit_acquis")
	public double getCreditAcquis() {
		return creditAcquis;
	}

	/**
	 * [BilanUe.creditAcquis] Setter 
	 * @author MAKERRI Sofiane on : 15 janv. 2015  15:56:15
	 * @param creditAcquis the creditAcquis to set
	 */
	public void setCreditAcquis(double creditAcquis) {
		this.creditAcquis = creditAcquis;
	}
	
	

}
