package dz.gov.mesrs.sii.fve.business.model.dto.cursus;

import java.io.Serializable;

public class SearchPreinscriptionDto implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 4940746283114089753L;

    private String nomDemandeur;

    private String prenomDemandeur;

    private String anneeAcademiqueId;

    private Integer nationaliteId;

    private Integer etablissementId;

    public SearchPreinscriptionDto() {
	super();
    }

    public String getNomDemandeur() {
	return nomDemandeur;
    }

    public void setNomDemandeur(String nomDemandeur) {
	this.nomDemandeur = nomDemandeur;
    }

    public String getPrenomDemandeur() {
	return prenomDemandeur;
    }

    public void setPrenomDemandeur(String prenomDemandeur) {
	this.prenomDemandeur = prenomDemandeur;
    }

    public String getAnneeAcademiqueId() {
	return anneeAcademiqueId;
    }

    public void setAnneeAcademiqueId(String anneeAcademiqueId) {
	this.anneeAcademiqueId = anneeAcademiqueId;
    }

    public Integer getNationaliteId() {
	return nationaliteId;
    }

    public void setNationaliteId(Integer nationaliteId) {
	this.nationaliteId = nationaliteId;
    }

    public Integer getEtablissementId() {
	return etablissementId;
    }

    public void setEtablissementId(Integer etablissementId) {
	this.etablissementId = etablissementId;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((anneeAcademiqueId == null) ? 0 : anneeAcademiqueId.hashCode());
	result = prime * result + ((etablissementId == null) ? 0 : etablissementId.hashCode());
	result = prime * result + ((nationaliteId == null) ? 0 : nationaliteId.hashCode());
	result = prime * result + ((nomDemandeur == null) ? 0 : nomDemandeur.hashCode());
	result = prime * result + ((prenomDemandeur == null) ? 0 : prenomDemandeur.hashCode());
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
	SearchPreinscriptionDto other = (SearchPreinscriptionDto) obj;
	if (anneeAcademiqueId == null) {
	    if (other.anneeAcademiqueId != null)
		return false;
	} else if (!anneeAcademiqueId.equals(other.anneeAcademiqueId))
	    return false;
	if (etablissementId == null) {
	    if (other.etablissementId != null)
		return false;
	} else if (!etablissementId.equals(other.etablissementId))
	    return false;
	if (nationaliteId == null) {
	    if (other.nationaliteId != null)
		return false;
	} else if (!nationaliteId.equals(other.nationaliteId))
	    return false;
	if (nomDemandeur == null) {
	    if (other.nomDemandeur != null)
		return false;
	} else if (!nomDemandeur.equals(other.nomDemandeur))
	    return false;
	if (prenomDemandeur == null) {
	    if (other.prenomDemandeur != null)
		return false;
	} else if (!prenomDemandeur.equals(other.prenomDemandeur))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "SearchPreinscriptionDto [nomDemandeur=" + nomDemandeur + ", prenomDemandeur=" + prenomDemandeur
		+ ", anneeAcademiqueId=" + anneeAcademiqueId + ", nationaliteId=" + nationaliteId
		+ ", etablissementId=" + etablissementId + "]";
    }


}
