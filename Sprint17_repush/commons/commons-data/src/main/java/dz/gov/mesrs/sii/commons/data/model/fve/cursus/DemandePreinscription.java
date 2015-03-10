package dz.gov.mesrs.sii.commons.data.model.fve.cursus;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import dz.gov.mesrs.sii.commons.data.model.bpm.SituationEntite;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.AnneeAcademique;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.Cycle;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.Niveau;
import dz.gov.mesrs.sii.commons.data.model.referentiel.NcNames;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefDomaineLMD;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefEtablissement;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefFiliereLmd;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefIndividu;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefSpecialiteLmd;

@Entity
@Table(name = "demande_preinscription", schema = "cursus")
public class DemandePreinscription implements Serializable {

    private static final long serialVersionUID = 7789623472046232894L;

    private Long id;
    private Date dateCreation;
    private RefIndividu refIndividu;
    private TitreAcces titreAcces;
    private DecisionDemandePreinscription decision;
    private NcNames statut;
    private AnneeAcademique anneeAcademique;
    private Cycle cycle;
    private Niveau niveau;
    private RefFiliereLmd filiereLmd;
    private RefSpecialiteLmd specialiteLmd;
    private RefEtablissement etablissement;
    private RefDomaineLMD domaineLMD;;
    private Boolean generee;
    private SituationEntite situationEntite;

    public DemandePreinscription() {
	super();
    }

    @Id
    @SequenceGenerator(name = "demande_preinscription_id_seq_gen", sequenceName = "cursus.demande_preinscription_id_seq_gen")
    @GeneratedValue(generator = "demande_preinscription_id_seq_gen")
    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    @Column(name = "date_creation", nullable = true)
    public Date getDateCreation() {
	return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
	this.dateCreation = dateCreation;
    }

    @OneToOne(optional = false)
    @JoinColumn(name = "ref_individu", nullable = false)
    public RefIndividu getRefIndividu() {
	return refIndividu;
    }

    public void setRefIndividu(RefIndividu refIndividu) {
	this.refIndividu = refIndividu;
    }

    @OneToOne(cascade = CascadeType.ALL, optional = true)
    @JoinColumn(name = "titre_acces", nullable = true)
    public TitreAcces getTitreAcces() {
	return titreAcces;
    }

    public void setTitreAcces(TitreAcces titreAcces) {
	this.titreAcces = titreAcces;
    }

    @OneToOne(cascade = CascadeType.ALL, optional = true)
    @JoinColumn(name = "ref_decision", nullable = true)
    public DecisionDemandePreinscription getDecision() {
	return decision;
    }

    public void setDecision(DecisionDemandePreinscription decision) {
	this.decision = decision;
    }

    @ManyToOne(optional = true)
    @JoinColumn(name = "ref_nc_names_statut", nullable = true)
    public NcNames getStatut() {
	return statut;
    }

    public void setStatut(NcNames statut) {
	this.statut = statut;
    }

    @ManyToOne(optional = true)
    @JoinColumn(name = "ref_annee_academique", nullable = true)
    public AnneeAcademique getAnneeAcademique() {
	return anneeAcademique;
    }

    public void setAnneeAcademique(AnneeAcademique anneeAcademique) {
	this.anneeAcademique = anneeAcademique;
    }

    @ManyToOne(optional = true)
    @JoinColumn(name = "ref_cycle", nullable = true)
    public Cycle getCycle() {
	return cycle;
    }

    public void setCycle(Cycle cycle) {
	this.cycle = cycle;
    }

    @ManyToOne(optional = true)
    @JoinColumn(name = "ref_niveau", nullable = true)
    public Niveau getNiveau() {
	return niveau;
    }

    public void setNiveau(Niveau niveau) {
	this.niveau = niveau;
    }

    @ManyToOne(optional = true)
    @JoinColumn(name = "ref_filiere", nullable = true)
    public RefFiliereLmd getFiliereLmd() {
	return filiereLmd;
    }

    public void setFiliereLmd(RefFiliereLmd filiereLmd) {
	this.filiereLmd = filiereLmd;
    }

    @ManyToOne(optional = true)
    @JoinColumn(name = "ref_specialite", nullable = true)
    public RefSpecialiteLmd getSpecialiteLmd() {
	return specialiteLmd;
    }

    public void setSpecialiteLmd(RefSpecialiteLmd specialiteLmd) {
	this.specialiteLmd = specialiteLmd;
    }

    @ManyToOne(optional = true)
    @JoinColumn(name = "ref_etablissement", nullable = true)
    public RefEtablissement getEtablissement() {
	return etablissement;
    }

    public void setEtablissement(RefEtablissement etablissement) {
	this.etablissement = etablissement;
    }

    @ManyToOne(optional = true)
    @JoinColumn(name = "ref_domaine_lmd", nullable = true)
    public RefDomaineLMD getDomaineLMD() {
	return domaineLMD;
    }

    public void setDomaineLMD(RefDomaineLMD domaineLMD) {
	this.domaineLMD = domaineLMD;
    }

    @Column(name = "generee", nullable = true)
    public Boolean getGeneree() {
	return generee;
    }

    public void setGeneree(Boolean generee) {
	this.generee = generee;
    }

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_situation", nullable = false)
    public SituationEntite getSituationEntite() {
	return situationEntite;
    }

    public void setSituationEntite(SituationEntite situationEntite) {
	this.situationEntite = situationEntite;
    }

    @Override
    public String toString() {
	return "DemandePreinscription [id=" + id + ", dateCreation=" + dateCreation + ", refIndividu=" + refIndividu
		+ ", titreAcces=" + titreAcces + ", decision=" + decision + ", statut=" + statut + ", anneeAcademique="
		+ anneeAcademique + ", cycle=" + cycle + ", niveau=" + niveau + ", filiereLmd=" + filiereLmd
		+ ", specialiteLmd=" + specialiteLmd + ", etablissement=" + etablissement + ", domaineLMD="
		+ domaineLMD + ", generee=" + generee + ", situationEntite=" + situationEntite + "]";
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((anneeAcademique == null) ? 0 : anneeAcademique.hashCode());
	result = prime * result + ((cycle == null) ? 0 : cycle.hashCode());
	result = prime * result + ((dateCreation == null) ? 0 : dateCreation.hashCode());
	result = prime * result + ((decision == null) ? 0 : decision.hashCode());
	result = prime * result + ((domaineLMD == null) ? 0 : domaineLMD.hashCode());
	result = prime * result + ((etablissement == null) ? 0 : etablissement.hashCode());
	result = prime * result + ((filiereLmd == null) ? 0 : filiereLmd.hashCode());
	result = prime * result + ((generee == null) ? 0 : generee.hashCode());
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	result = prime * result + ((niveau == null) ? 0 : niveau.hashCode());
	result = prime * result + ((refIndividu == null) ? 0 : refIndividu.hashCode());
	result = prime * result + ((situationEntite == null) ? 0 : situationEntite.hashCode());
	result = prime * result + ((specialiteLmd == null) ? 0 : specialiteLmd.hashCode());
	result = prime * result + ((statut == null) ? 0 : statut.hashCode());
	result = prime * result + ((titreAcces == null) ? 0 : titreAcces.hashCode());
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
	DemandePreinscription other = (DemandePreinscription) obj;
	if (anneeAcademique == null) {
	    if (other.anneeAcademique != null)
		return false;
	} else if (!anneeAcademique.equals(other.anneeAcademique))
	    return false;
	if (cycle == null) {
	    if (other.cycle != null)
		return false;
	} else if (!cycle.equals(other.cycle))
	    return false;
	if (dateCreation == null) {
	    if (other.dateCreation != null)
		return false;
	} else if (!dateCreation.equals(other.dateCreation))
	    return false;
	if (decision == null) {
	    if (other.decision != null)
		return false;
	} else if (!decision.equals(other.decision))
	    return false;
	if (domaineLMD == null) {
	    if (other.domaineLMD != null)
		return false;
	} else if (!domaineLMD.equals(other.domaineLMD))
	    return false;
	if (etablissement == null) {
	    if (other.etablissement != null)
		return false;
	} else if (!etablissement.equals(other.etablissement))
	    return false;
	if (filiereLmd == null) {
	    if (other.filiereLmd != null)
		return false;
	} else if (!filiereLmd.equals(other.filiereLmd))
	    return false;
	if (generee == null) {
	    if (other.generee != null)
		return false;
	} else if (!generee.equals(other.generee))
	    return false;
	if (id == null) {
	    if (other.id != null)
		return false;
	} else if (!id.equals(other.id))
	    return false;
	if (niveau == null) {
	    if (other.niveau != null)
		return false;
	} else if (!niveau.equals(other.niveau))
	    return false;
	if (refIndividu == null) {
	    if (other.refIndividu != null)
		return false;
	} else if (!refIndividu.equals(other.refIndividu))
	    return false;
	if (situationEntite == null) {
	    if (other.situationEntite != null)
		return false;
	} else if (!situationEntite.equals(other.situationEntite))
	    return false;
	if (specialiteLmd == null) {
	    if (other.specialiteLmd != null)
		return false;
	} else if (!specialiteLmd.equals(other.specialiteLmd))
	    return false;
	if (statut == null) {
	    if (other.statut != null)
		return false;
	} else if (!statut.equals(other.statut))
	    return false;
	if (titreAcces == null) {
	    if (other.titreAcces != null)
		return false;
	} else if (!titreAcces.equals(other.titreAcces))
	    return false;
	return true;
    }
    
    

}
