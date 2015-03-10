package dz.gov.mesrs.sii.commons.data.model.recherche;
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
import javax.persistence.Transient;

import dz.gov.mesrs.sii.commons.data.model.Identifiable;
import dz.gov.mesrs.sii.commons.data.model.bpm.Etape;
import dz.gov.mesrs.sii.commons.data.model.referentiel.Nomenclature;


/**
 * @author rlaib  on : 15 d�c. 2014  15:33:48
 */
@Entity
@Table(name = "etape_validation", schema = "recherche")
public class EtapeValidation implements java.io.Serializable , Identifiable<Long>  {
	

	
	private static final long serialVersionUID = 1L;
	private Long id;
	private Date dateReponce;
	private String observation;
	private Nomenclature reponce;
	private Projet projet;	
	private Etape etapeCircuit;	
	private DemandeBudget demandeBudget;
	public EtapeValidation() {
		
	}
	
	@Id
	@SequenceGenerator(name="etape_validation_id_seq_gen", sequenceName="recherche.etape_validation_id_seq")
	@GeneratedValue(generator="etape_validation_id_seq_gen")
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	
	public void setId(Long id) {
		this.id = id;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_reponse")	
	public Date getDateReponce() {
		return dateReponce;
	}

	public void setDateReponce(Date dateReponce) {
		this.dateReponce = dateReponce;
	}
	@Column(name = "observation")	
	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_demande")
	public DemandeBudget getDemandeBudget() {
		return demandeBudget;
	}

	public void setDemandeBudget(DemandeBudget demandeBudget) {
		this.demandeBudget = demandeBudget;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_projet")	
	public Projet getProjet() {
		return projet;
	}

	public void setProjet(Projet projet) {
		this.projet = projet;
	}
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_etape_circuit")
	public Etape getEtapeCircuit() {
		return etapeCircuit;
	}

	public void setEtapeCircuit(Etape etapeCircuit) {
		this.etapeCircuit = etapeCircuit;
	}
	
	

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_reponse")
	public Nomenclature getReponce() {
		return reponce;
	}

	public void setReponce(Nomenclature reponce) {
		this.reponce = reponce;
	}

	@Transient
	@Override
	public Long getIdenfiant() {
		return this.getId();
	}
	

	
	@Transient
	@Override
	public String getIdentifiantName() {
		return "id";
	}

}
