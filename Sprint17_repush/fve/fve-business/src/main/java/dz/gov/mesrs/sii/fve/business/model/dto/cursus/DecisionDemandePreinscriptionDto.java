package dz.gov.mesrs.sii.fve.business.model.dto.cursus;

import java.io.Serializable;
import java.util.Date;

public class DecisionDemandePreinscriptionDto implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1574417333247782255L;

    private Long id;

    private Date date;

    private Boolean acceptee;
    
    private String avis;

    private String motif;

    private String observation;
    
    private String reference;

    public DecisionDemandePreinscriptionDto() {
	super();
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public Date getDate() {
	return date;
    }

    public void setDate(Date date) {
	this.date = date;
    }


    public Boolean getAcceptee() {
        return acceptee;
    }

    public void setAcceptee(Boolean acceptee) {
        this.acceptee = acceptee;
    }

    public String getAvis() {
	return avis;
    }

    public void setAvis(String avis) {
	this.avis = avis;
    }

    public String getMotif() {
	return motif;
    }

    public void setMotif(String motif) {
	this.motif = motif;
    }

    public String getObservation() {
	return observation;
    }

    public void setObservation(String observation) {
	this.observation = observation;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }


}
