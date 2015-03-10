package dz.gov.mesrs.sii.commons.data.model.fve.cursus;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "decision_demande_preinscription", schema = "cursus")
public class DecisionDemandePreinscription implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 8359427973739542923L;

    private Long id;
    private Date date;
    private Boolean acceptee;
    private String avis;
    private String motif;
    private String observation;
    private String reference;
    private DemandePreinscription demandePreinscription;

    public DecisionDemandePreinscription() {

    }

    @Id
    @SequenceGenerator(name = "decision_demande_preinscription_id_seq_gen", sequenceName = "cursus.decision_demande_preinscription_id_seq_gen")
    @GeneratedValue(generator = "decision_demande_preinscription_id_seq_gen")
    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    @Column(name = "acceptee", nullable = true)
    public Boolean getAcceptee() {
	return acceptee;
    }

    public void setAcceptee(Boolean acceptee) {
	this.acceptee = acceptee;
    }

    @Column(name = "date_decision", nullable = false)
    public Date getDate() {
	return date;
    }

    public void setDate(Date date) {
	this.date = date;
    }

    @Column(name = "avis", nullable = false)
    public String getAvis() {
	return avis;
    }

    public void setAvis(String avis) {
	this.avis = avis;
    }

    @Column(name = "motif", nullable = false)
    public String getMotif() {
	return motif;
    }

    public void setMotif(String motif) {
	this.motif = motif;
    }

    @Column(name = "observation", nullable = false)
    public String getObservation() {
	return observation;
    }

    public void setObservation(String observation) {
	this.observation = observation;
    }

    @Column(name = "reference", nullable = false)
    public String getReference() {
	return reference;
    }

    public void setReference(String reference) {
	this.reference = reference;
    }

    @OneToOne(mappedBy="decision")
    public DemandePreinscription getDemandePreinscription() {
	return demandePreinscription;
    }

    public void setDemandePreinscription(DemandePreinscription demandePreinscription) {
	this.demandePreinscription = demandePreinscription;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((acceptee == null) ? 0 : acceptee.hashCode());
	result = prime * result + ((avis == null) ? 0 : avis.hashCode());
	result = prime * result + ((date == null) ? 0 : date.hashCode());
	result = prime * result + ((demandePreinscription == null) ? 0 : demandePreinscription.hashCode());
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	result = prime * result + ((motif == null) ? 0 : motif.hashCode());
	result = prime * result + ((observation == null) ? 0 : observation.hashCode());
	result = prime * result + ((reference == null) ? 0 : reference.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	DecisionDemandePreinscription other = (DecisionDemandePreinscription) obj;
	if (acceptee == null) {
	    if (other.acceptee != null)
		return false;
	} else if (!acceptee.equals(other.acceptee))
	    return false;
	if (avis == null) {
	    if (other.avis != null)
		return false;
	} else if (!avis.equals(other.avis))
	    return false;
	if (date == null) {
	    if (other.date != null)
		return false;
	} else if (!date.equals(other.date))
	    return false;
	if (demandePreinscription == null) {
	    if (other.demandePreinscription != null)
		return false;
	} else if (!demandePreinscription.equals(other.demandePreinscription))
	    return false;
	if (id == null) {
	    if (other.id != null)
		return false;
	} else if (!id.equals(other.id))
	    return false;
	if (motif == null) {
	    if (other.motif != null)
		return false;
	} else if (!motif.equals(other.motif))
	    return false;
	if (observation == null) {
	    if (other.observation != null)
		return false;
	} else if (!observation.equals(other.observation))
	    return false;
	if (reference == null) {
	    if (other.reference != null)
		return false;
	} else if (!reference.equals(other.reference))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "DecisionDemandePreinscription [id=" + id + ", date=" + date + ", acceptee=" + acceptee + ", avis="
		+ avis + ", motif=" + motif + ", observation=" + observation + ", reference=" + reference
		+ ", demandePreinscription=" + demandePreinscription + "]";
    }

}
