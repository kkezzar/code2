package dz.gov.mesrs.sii.fve.business.model.dto.cursus;

import java.io.Serializable;
import java.util.Date;

public class SearchPreinscriptionResultDto implements Serializable {

    private static final long serialVersionUID = 4662721211751110910L;

    private long demandeId;

    private String individuId;

    private Integer titreAccesId;

    private String libelleFiliere;

    private String libelleSpecialite;

    private Date dateDemande;

    private String nomDemandeur;

    private String prenomDemandeur;

    private String etablissement;

    private String libelleCursus;

    private String libelleDomaine;

    private String libelleNiveau;

    private String libelleNationalite;

    private String anneeAcademique;
    
    private boolean generee;

    public SearchPreinscriptionResultDto() {
	super();

    }

    public Integer getTitreAccesId() {
	return titreAccesId;
    }

    public void setTitreAccesId(Integer titreAccesId) {
	this.titreAccesId = titreAccesId;
    }

    public long getDemandeId() {
	return demandeId;
    }

    public void setDemandeId(long demandeId) {
	this.demandeId = demandeId;
    }

    public String getLibelleFiliere() {
	return libelleFiliere;
    }

    public void setLibelleFiliere(String libelleFiliere) {
	this.libelleFiliere = libelleFiliere;
    }

    public String getLibelleSpecialite() {
	return libelleSpecialite;
    }

    public void setLibelleSpecialite(String libelleSpecialite) {
	this.libelleSpecialite = libelleSpecialite;
    }

    public Date getDateDemande() {
	return dateDemande;
    }

    public void setDateDemande(Date dateDemande) {
	this.dateDemande = dateDemande;
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

    public String getEtablissement() {
	return etablissement;
    }

    public void setEtablissement(String etablissement) {
	this.etablissement = etablissement;
    }

    public String getLibelleCursus() {
	return libelleCursus;
    }

    public void setLibelleCursus(String libelleCursus) {
	this.libelleCursus = libelleCursus;
    }

    public String getLibelleNiveau() {
	return libelleNiveau;
    }

    public void setLibelleNiveau(String libelleNiveau) {
	this.libelleNiveau = libelleNiveau;
    }

    public String getLibelleNationalite() {
	return libelleNationalite;
    }

    public void setLibelleNationalite(String libelleNationalite) {
	this.libelleNationalite = libelleNationalite;
    }

    public void setLibelleDomaine(String libelleDomaine) {
	this.libelleDomaine = libelleDomaine;
    }

    public String getLibelleDomaine() {
	return libelleDomaine;
    }

    public String getIndividuId() {
	return individuId;
    }

    public void setIndividuId(String individuId) {
	this.individuId = individuId;
    }

    public String getAnneeAcademique() {
	return anneeAcademique;
    }

    public void setAnneeAcademique(String anneeAcademique) {
	this.anneeAcademique = anneeAcademique;
    }

    public boolean isGeneree() {
        return generee;
    }

    public void setGeneree(boolean generee) {
        this.generee = generee;
    }

    @Override
    public String toString() {
	return "SearchPreinscriptionResultDto [demandeId=" + demandeId + ", individuId=" + individuId
		+ ", titreAccesId=" + titreAccesId + ", libelleFiliere=" + libelleFiliere + ", libelleSpecialite="
		+ libelleSpecialite + ", dateDemande=" + dateDemande + ", nomDemandeur=" + nomDemandeur
		+ ", prenomDemandeur=" + prenomDemandeur + ", etablissement=" + etablissement + ", libelleCursus="
		+ libelleCursus + ", libelleDomaine=" + libelleDomaine + ", libelleNiveau=" + libelleNiveau
		+ ", libelleNationalite=" + libelleNationalite + ", anneeAcademique=" + anneeAcademique + ", generee="
		+ generee + "]";
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((anneeAcademique == null) ? 0 : anneeAcademique.hashCode());
	result = prime * result + ((dateDemande == null) ? 0 : dateDemande.hashCode());
	result = prime * result + (int) (demandeId ^ (demandeId >>> 32));
	result = prime * result + ((etablissement == null) ? 0 : etablissement.hashCode());
	result = prime * result + (generee ? 1231 : 1237);
	result = prime * result + ((individuId == null) ? 0 : individuId.hashCode());
	result = prime * result + ((libelleCursus == null) ? 0 : libelleCursus.hashCode());
	result = prime * result + ((libelleDomaine == null) ? 0 : libelleDomaine.hashCode());
	result = prime * result + ((libelleFiliere == null) ? 0 : libelleFiliere.hashCode());
	result = prime * result + ((libelleNationalite == null) ? 0 : libelleNationalite.hashCode());
	result = prime * result + ((libelleNiveau == null) ? 0 : libelleNiveau.hashCode());
	result = prime * result + ((libelleSpecialite == null) ? 0 : libelleSpecialite.hashCode());
	result = prime * result + ((nomDemandeur == null) ? 0 : nomDemandeur.hashCode());
	result = prime * result + ((prenomDemandeur == null) ? 0 : prenomDemandeur.hashCode());
	result = prime * result + ((titreAccesId == null) ? 0 : titreAccesId.hashCode());
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
	SearchPreinscriptionResultDto other = (SearchPreinscriptionResultDto) obj;
	if (anneeAcademique == null) {
	    if (other.anneeAcademique != null)
		return false;
	} else if (!anneeAcademique.equals(other.anneeAcademique))
	    return false;
	if (dateDemande == null) {
	    if (other.dateDemande != null)
		return false;
	} else if (!dateDemande.equals(other.dateDemande))
	    return false;
	if (demandeId != other.demandeId)
	    return false;
	if (etablissement == null) {
	    if (other.etablissement != null)
		return false;
	} else if (!etablissement.equals(other.etablissement))
	    return false;
	if (generee != other.generee)
	    return false;
	if (individuId == null) {
	    if (other.individuId != null)
		return false;
	} else if (!individuId.equals(other.individuId))
	    return false;
	if (libelleCursus == null) {
	    if (other.libelleCursus != null)
		return false;
	} else if (!libelleCursus.equals(other.libelleCursus))
	    return false;
	if (libelleDomaine == null) {
	    if (other.libelleDomaine != null)
		return false;
	} else if (!libelleDomaine.equals(other.libelleDomaine))
	    return false;
	if (libelleFiliere == null) {
	    if (other.libelleFiliere != null)
		return false;
	} else if (!libelleFiliere.equals(other.libelleFiliere))
	    return false;
	if (libelleNationalite == null) {
	    if (other.libelleNationalite != null)
		return false;
	} else if (!libelleNationalite.equals(other.libelleNationalite))
	    return false;
	if (libelleNiveau == null) {
	    if (other.libelleNiveau != null)
		return false;
	} else if (!libelleNiveau.equals(other.libelleNiveau))
	    return false;
	if (libelleSpecialite == null) {
	    if (other.libelleSpecialite != null)
		return false;
	} else if (!libelleSpecialite.equals(other.libelleSpecialite))
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
	if (titreAccesId == null) {
	    if (other.titreAccesId != null)
		return false;
	} else if (!titreAccesId.equals(other.titreAccesId))
	    return false;
	return true;
    }


  
}
