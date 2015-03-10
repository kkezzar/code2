package dz.gov.mesrs.sii.commons.data.model.fve.concours;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import dz.gov.mesrs.sii.commons.data.model.bpm.SituationEntite;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.AnneeAcademique;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.OuvertureOffreFormation;
import dz.gov.mesrs.sii.commons.data.model.referentiel.Nomenclature;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefDomaineLMD;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefEtablissement;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefFiliereLmd;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefSpecialiteLmd;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefStructure;

@Entity
@Table(name = "concours", schema = "cursus")
public class Concours implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 4383347368049781118L;

    private Long id;
    private String identifiant;
    private String intituleLatin;
    private String intituleArabe;
    private Date dateDebut;
    private Date dateFin;
    private Boolean national;
    private Date datePublicationConcours;
    private Date datePublicationResultats;
    private Date datePublicationListeComplementaire;
    private Date dateLimite;
    private Integer noteBase;
    private Nomenclature typeConcours;
    private AnneeAcademique anneeAcademique;
    private RefDomaineLMD domaine;
    private RefFiliereLmd filiere;
    private RefSpecialiteLmd specialite;
    private OuvertureOffreFormation ouvertureOffreFormation;
    private RefStructure structure;
    private RefEtablissement etablissement;
    private SituationEntite situation;
    private List<EtablissementAdmission> etablissementAdmissions;
    private List<DossierCandidat> candidats;
    private List<ExamenConcours> examens;
    private List<PieceConcours> pieces;

    @Id
    @SequenceGenerator(name = "concours_id_seq", initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "concours_id_seq")
    public Long getId() {
	return id;
    }
    
    @Column(name="identifiant",nullable=false)
    public String getIdentifiant() {
	return identifiant;
    }

    @Column(name = "intitule_latin", nullable = false)
    public String getIntituleLatin() {
	return intituleLatin;
    }

    @Column(name = "intitule_arabe", nullable = false)
    public String getIntituleArabe() {
	return intituleArabe;
    }

    @Column(name = "date_debut", nullable = false)
    public Date getDateDebut() {
	return dateDebut;
    }

    @Column(name = "date_fin", nullable = false)
    public Date getDateFin() {
	return dateFin;
    }

    @Column(name = "national", nullable = false)
    public Boolean getNational() {
	return national;
    }

    @Column(name = "date_publication_concours", nullable = true)
    public Date getDatePublicationConcours() {
	return datePublicationConcours;
    }

    @Column(name = "date_publication_resultats", nullable = true)
    public Date getDatePublicationResultats() {
	return datePublicationResultats;
    }

    @Column(name = "date_publication_liste_complementaire", nullable = true)
    public Date getDatePublicationListeComplementaire() {
	return datePublicationListeComplementaire;
    }

    @Column(name = "date_limite", nullable = true)
    public Date getDateLimite() {
	return dateLimite;
    }

    @Column(name = "note_base", nullable = false)
    public Integer getNoteBase() {
	return noteBase;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "nc_type_concours", nullable = false)
    public Nomenclature getTypeConcours() {
	return typeConcours;
    }

    @ManyToOne(optional = true)
    @JoinColumn(name = "annee_academique", nullable = true)
    public AnneeAcademique getAnneeAcademique() {
	return anneeAcademique;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ref_domaine", nullable = false)
    public RefDomaineLMD getDomaine() {
	return domaine;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ref_filiere", nullable = true)
    public RefFiliereLmd getFiliere() {
	return filiere;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ref_specialite", nullable = true)
    public RefSpecialiteLmd getSpecialite() {
	return specialite;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ouverture_offre_formation", nullable = true)
    public OuvertureOffreFormation getOuvertureOffreFormation() {
	return ouvertureOffreFormation;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ref_structure", nullable = true)
    public RefStructure getStructure() {
	return structure;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ref_etablissement", nullable = true)
    public RefEtablissement getEtablissement() {
	return etablissement;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "situation_entite", nullable = false)
    public SituationEntite getSituation() {
	return situation;
    }

    @OneToMany(mappedBy = "concours", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<EtablissementAdmission> getEtablissementAdmissions() {
	if (etablissementAdmissions == null) {
	    etablissementAdmissions = new ArrayList<>();
	}
	return etablissementAdmissions;
    }

    @OneToMany(mappedBy = "concours", orphanRemoval = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<DossierCandidat> getCandidats() {
	if (candidats == null) {
	    candidats = new ArrayList<>();
	}
	return candidats;
    }

    @OneToMany(mappedBy = "concours", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<ExamenConcours> getExamens() {
	if (examens == null) {
	    examens = new ArrayList<>();
	}
	return examens;
    }

    @OneToMany(mappedBy = "concours", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<PieceConcours> getPieces() {
	return pieces;
    }

    public void setId(Long id) {
	this.id = id;
    }
    
    public void setIdentifiant(String identifiant) {
	this.identifiant = identifiant;
    }

    public void setIntituleLatin(String intituleLatin) {
	this.intituleLatin = intituleLatin;
    }

    public void setIntituleArabe(String intituleArabe) {
	this.intituleArabe = intituleArabe;
    }

    public void setDateDebut(Date dateDebut) {
	this.dateDebut = dateDebut;
    }

    public void setDateFin(Date dateFin) {
	this.dateFin = dateFin;
    }

    public void setNational(Boolean national) {
	this.national = national;
    }

    public void setDatePublicationConcours(Date datePublicationConcours) {
	this.datePublicationConcours = datePublicationConcours;
    }

    public void setDatePublicationResultats(Date datePublicationResultats) {
	this.datePublicationResultats = datePublicationResultats;
    }

    public void setDatePublicationListeComplementaire(Date datePublicationListeComplementaire) {
	this.datePublicationListeComplementaire = datePublicationListeComplementaire;
    }

    public void setDateLimite(Date dateLimite) {
	this.dateLimite = dateLimite;
    }

    public void setNoteBase(Integer noteBase) {
	this.noteBase = noteBase;
    }

    public void setTypeConcours(Nomenclature typeConcours) {
	this.typeConcours = typeConcours;
    }

    public void setAnneeAcademique(AnneeAcademique anneeAcademique) {
	this.anneeAcademique = anneeAcademique;
    }

    public void setDomaine(RefDomaineLMD domaine) {
	this.domaine = domaine;
    }

    public void setFiliere(RefFiliereLmd filiere) {
	this.filiere = filiere;
    }

    public void setSpecialite(RefSpecialiteLmd specialite) {
	this.specialite = specialite;
    }

    public void setOuvertureOffreFormation(OuvertureOffreFormation ouvertureOffreFormation) {
	this.ouvertureOffreFormation = ouvertureOffreFormation;
    }

    public void setStructure(RefStructure structure) {
	this.structure = structure;
    }

    public void setEtablissement(RefEtablissement etablissement) {
	this.etablissement = etablissement;
    }

    public void setSituation(SituationEntite situation) {
	this.situation = situation;
    }

    public void setEtablissementAdmissions(List<EtablissementAdmission> etablissementAdmissions) {
	this.etablissementAdmissions = etablissementAdmissions;
    }

    public void setCandidats(List<DossierCandidat> candidats) {
	this.candidats = candidats;
    }

    public void setExamens(List<ExamenConcours> examens) {
	this.examens = examens;
    }

    public void setPieces(List<PieceConcours> pieces) {
	this.pieces = pieces;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((anneeAcademique == null) ? 0 : anneeAcademique.hashCode());
	result = prime * result + ((candidats == null) ? 0 : candidats.hashCode());
	result = prime * result + ((dateDebut == null) ? 0 : dateDebut.hashCode());
	result = prime * result + ((dateFin == null) ? 0 : dateFin.hashCode());
	result = prime * result + ((dateLimite == null) ? 0 : dateLimite.hashCode());
	result = prime * result + ((datePublicationConcours == null) ? 0 : datePublicationConcours.hashCode());
	result = prime * result
		+ ((datePublicationListeComplementaire == null) ? 0 : datePublicationListeComplementaire.hashCode());
	result = prime * result + ((datePublicationResultats == null) ? 0 : datePublicationResultats.hashCode());
	result = prime * result + ((domaine == null) ? 0 : domaine.hashCode());
	result = prime * result + ((etablissement == null) ? 0 : etablissement.hashCode());
	result = prime * result + ((etablissementAdmissions == null) ? 0 : etablissementAdmissions.hashCode());
	result = prime * result + ((examens == null) ? 0 : examens.hashCode());
	result = prime * result + ((filiere == null) ? 0 : filiere.hashCode());
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	result = prime * result + ((identifiant == null) ? 0 : identifiant.hashCode());
	result = prime * result + ((intituleArabe == null) ? 0 : intituleArabe.hashCode());
	result = prime * result + ((intituleLatin == null) ? 0 : intituleLatin.hashCode());
	result = prime * result + ((national == null) ? 0 : national.hashCode());
	result = prime * result + ((noteBase == null) ? 0 : noteBase.hashCode());
	result = prime * result + ((ouvertureOffreFormation == null) ? 0 : ouvertureOffreFormation.hashCode());
	result = prime * result + ((pieces == null) ? 0 : pieces.hashCode());
	result = prime * result + ((situation == null) ? 0 : situation.hashCode());
	result = prime * result + ((specialite == null) ? 0 : specialite.hashCode());
	result = prime * result + ((structure == null) ? 0 : structure.hashCode());
	result = prime * result + ((typeConcours == null) ? 0 : typeConcours.hashCode());
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
	Concours other = (Concours) obj;
	if (anneeAcademique == null) {
	    if (other.anneeAcademique != null)
		return false;
	} else if (!anneeAcademique.equals(other.anneeAcademique))
	    return false;
	if (candidats == null) {
	    if (other.candidats != null)
		return false;
	} else if (!candidats.equals(other.candidats))
	    return false;
	if (dateDebut == null) {
	    if (other.dateDebut != null)
		return false;
	} else if (!dateDebut.equals(other.dateDebut))
	    return false;
	if (dateFin == null) {
	    if (other.dateFin != null)
		return false;
	} else if (!dateFin.equals(other.dateFin))
	    return false;
	if (dateLimite == null) {
	    if (other.dateLimite != null)
		return false;
	} else if (!dateLimite.equals(other.dateLimite))
	    return false;
	if (datePublicationConcours == null) {
	    if (other.datePublicationConcours != null)
		return false;
	} else if (!datePublicationConcours.equals(other.datePublicationConcours))
	    return false;
	if (datePublicationListeComplementaire == null) {
	    if (other.datePublicationListeComplementaire != null)
		return false;
	} else if (!datePublicationListeComplementaire.equals(other.datePublicationListeComplementaire))
	    return false;
	if (datePublicationResultats == null) {
	    if (other.datePublicationResultats != null)
		return false;
	} else if (!datePublicationResultats.equals(other.datePublicationResultats))
	    return false;
	if (domaine == null) {
	    if (other.domaine != null)
		return false;
	} else if (!domaine.equals(other.domaine))
	    return false;
	if (etablissement == null) {
	    if (other.etablissement != null)
		return false;
	} else if (!etablissement.equals(other.etablissement))
	    return false;
	if (etablissementAdmissions == null) {
	    if (other.etablissementAdmissions != null)
		return false;
	} else if (!etablissementAdmissions.equals(other.etablissementAdmissions))
	    return false;
	if (examens == null) {
	    if (other.examens != null)
		return false;
	} else if (!examens.equals(other.examens))
	    return false;
	if (filiere == null) {
	    if (other.filiere != null)
		return false;
	} else if (!filiere.equals(other.filiere))
	    return false;
	if (id == null) {
	    if (other.id != null)
		return false;
	} else if (!id.equals(other.id))
	    return false;
	if (identifiant == null) {
	    if (other.identifiant != null)
		return false;
	} else if (!identifiant.equals(other.identifiant))
	    return false;
	if (intituleArabe == null) {
	    if (other.intituleArabe != null)
		return false;
	} else if (!intituleArabe.equals(other.intituleArabe))
	    return false;
	if (intituleLatin == null) {
	    if (other.intituleLatin != null)
		return false;
	} else if (!intituleLatin.equals(other.intituleLatin))
	    return false;
	if (national == null) {
	    if (other.national != null)
		return false;
	} else if (!national.equals(other.national))
	    return false;
	if (noteBase == null) {
	    if (other.noteBase != null)
		return false;
	} else if (!noteBase.equals(other.noteBase))
	    return false;
	if (ouvertureOffreFormation == null) {
	    if (other.ouvertureOffreFormation != null)
		return false;
	} else if (!ouvertureOffreFormation.equals(other.ouvertureOffreFormation))
	    return false;
	if (pieces == null) {
	    if (other.pieces != null)
		return false;
	} else if (!pieces.equals(other.pieces))
	    return false;
	if (situation == null) {
	    if (other.situation != null)
		return false;
	} else if (!situation.equals(other.situation))
	    return false;
	if (specialite == null) {
	    if (other.specialite != null)
		return false;
	} else if (!specialite.equals(other.specialite))
	    return false;
	if (structure == null) {
	    if (other.structure != null)
		return false;
	} else if (!structure.equals(other.structure))
	    return false;
	if (typeConcours == null) {
	    if (other.typeConcours != null)
		return false;
	} else if (!typeConcours.equals(other.typeConcours))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "Concours [id=" + id + ", identifiant=" + identifiant + ", intituleLatin=" + intituleLatin
		+ ", intituleArabe=" + intituleArabe + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin
		+ ", national=" + national + ", datePublicationConcours=" + datePublicationConcours
		+ ", datePublicationResultats=" + datePublicationResultats + ", datePublicationListeComplementaire="
		+ datePublicationListeComplementaire + ", dateLimite=" + dateLimite + ", noteBase=" + noteBase
		+ ", typeConcours=" + typeConcours + ", anneeAcademique=" + anneeAcademique + ", domaine=" + domaine
		+ ", filiere=" + filiere + ", specialite=" + specialite + ", ouvertureOffreFormation="
		+ ouvertureOffreFormation + ", structure=" + structure + ", etablissement=" + etablissement
		+ ", situation=" + situation + ", etablissementAdmissions=" + etablissementAdmissions + ", candidats="
		+ candidats + ", examens=" + examens + ", pieces=" + pieces + "]";
    }

   
}
