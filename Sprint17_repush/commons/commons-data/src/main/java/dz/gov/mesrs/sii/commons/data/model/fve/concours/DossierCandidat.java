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

import dz.gov.mesrs.sii.commons.data.model.fve.cursus.DossierInscriptionAdministrative;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefEtablissement;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefIndividu;

@Entity
@Table(name = "dossier_candidat", schema = "cursus")
public class DossierCandidat implements Serializable {
    private static final long serialVersionUID = 5872163460564366131L;

    private Long id;
    private Concours concours;
    private RefIndividu individu;
    private RefEtablissement etablissement;
    private Date dateDepot;
    private Boolean etudeDossier;
    private String motifEtude;
    private Boolean accepte;
    private String motifRejet;
    private Boolean admis;
    private Date dateAdmission;
    private Double moyenneObtenue;
    private Integer classement;
    private Boolean listeComplementaire;
    private Boolean desistement;
    private Date dateDesistement;
    private String motifDesistement;
    private EtablissementAdmission etablissementAdmission;
    private List<ResultatExamen> resultatExamens;
    private List<PieceFournie> pieceFournies;
    private DossierInscriptionAdministrative dossierInscriptionAdministrative;

    // TODO dossierEtudiant + dossierInstricption

    @Id
    @SequenceGenerator(name = "dossier_candidat_id_seq", initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dossier_candidat_id_seq")
    public Long getId() {
	return id;
    }

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "concours", nullable = false)
    public Concours getConcours() {
	return concours;
    }

    @OneToOne(optional = false)
    @JoinColumn(name = "ref_individu", nullable = false)
    public RefIndividu getIndividu() {
	return individu;
    }

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "ref_etablissement", nullable = false)
    public RefEtablissement getEtablissement() {
	return etablissement;
    }

    @Column(name = "date_depot", nullable = false)
    public Date getDateDepot() {
	return dateDepot;
    }

    @Column(name = "etude_dossier", nullable = true)
    public Boolean getEtudeDossier() {
	return etudeDossier;
    }

    @Column(name = "motif_etude", nullable = true)
    public String getMotifEtude() {
	return motifEtude;
    }

    @Column(name = "accepte", nullable = true)
    public Boolean getAccepte() {
	return accepte;
    }

    @Column(name = "motif_rejet", nullable = true)
    public String getMotifRejet() {
	return motifRejet;
    }

    @Column(name = "admis", nullable = true)
    public Boolean getAdmis() {
	return admis;
    }

    @Column(name = "date_admission", nullable = true)
    public Date getDateAdmission() {
	return dateAdmission;
    }

    @Column(name = "moyenne_obtenue", nullable = true)
    public Double getMoyenneObtenue() {
	return moyenneObtenue;
    }

    @Column(name = "classement", nullable = true)
    public Integer getClassement() {
	return classement;
    }

    @Column(name = "liste_complementaire", nullable = true)
    public Boolean getListeComplementaire() {
	return listeComplementaire;
    }

    @Column(name = "desistement", nullable = true)
    public Boolean getDesistement() {
	return desistement;
    }

    @Column(name = "date_decisistement", nullable = true)
    public Date getDateDesistement() {
	return dateDesistement;
    }

    @Column(name = "motif_decisistement", nullable = true)
    public String getMotifDesistement() {
	return motifDesistement;
    }

    @ManyToOne(optional = true)
    @JoinColumn(name = "etablissement_admission", nullable = true)
    public EtablissementAdmission getEtablissementAdmission() {
	return etablissementAdmission;
    }

    @OneToMany(mappedBy = "dossierCandidat", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    public List<ResultatExamen> getResultatExamens() {
	if (resultatExamens == null) {
	    resultatExamens = new ArrayList<>();
	}
	return resultatExamens;
    }

    @OneToMany(mappedBy = "dossierCandidat", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    public List<PieceFournie> getPieceFournies() {
	return pieceFournies;
    }

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_dia", nullable = false)
    public DossierInscriptionAdministrative getDossierInscriptionAdministrative() {
	return dossierInscriptionAdministrative;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public void setConcours(Concours concours) {
	this.concours = concours;
    }

    public void setIndividu(RefIndividu individu) {
	this.individu = individu;
    }

    public void setEtablissement(RefEtablissement etablissement) {
	this.etablissement = etablissement;
    }

    public void setDateDepot(Date dateDepot) {
	this.dateDepot = dateDepot;
    }

    public void setEtudeDossier(Boolean etudeDossier) {
	this.etudeDossier = etudeDossier;
    }

    public void setMotifEtude(String motifEtude) {
	this.motifEtude = motifEtude;
    }

    public void setAccepte(Boolean accepte) {
	this.accepte = accepte;
    }

    public void setMotifRejet(String motifRejet) {
	this.motifRejet = motifRejet;
    }

    public void setAdmis(Boolean admis) {
	this.admis = admis;
    }

    public void setDateAdmission(Date dateAdmission) {
	this.dateAdmission = dateAdmission;
    }

    public void setMoyenneObtenue(Double moyenneObtenue) {
	this.moyenneObtenue = moyenneObtenue;
    }

    public void setClassement(Integer classement) {
	this.classement = classement;
    }

    public void setListeComplementaire(Boolean listeComplementaire) {
	this.listeComplementaire = listeComplementaire;
    }

    public void setDesistement(Boolean desistement) {
	this.desistement = desistement;
    }

    public void setDateDesistement(Date dateDesistement) {
	this.dateDesistement = dateDesistement;
    }

    public void setMotifDesistement(String motifDesistement) {
	this.motifDesistement = motifDesistement;
    }

    public void setEtablissementAdmission(EtablissementAdmission etablissementAdmission) {
	this.etablissementAdmission = etablissementAdmission;
    }

    public void setResultatExamens(List<ResultatExamen> resultatExamens) {
	this.resultatExamens = resultatExamens;
    }

    public void setDossierInscriptionAdministrative(DossierInscriptionAdministrative dossierInscriptionAdministrative) {
	this.dossierInscriptionAdministrative = dossierInscriptionAdministrative;
    }

    public void setPieceFournies(List<PieceFournie> pieceFournies) {
	this.pieceFournies = pieceFournies;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((accepte == null) ? 0 : accepte.hashCode());
	result = prime * result + ((admis == null) ? 0 : admis.hashCode());
	result = prime * result + ((classement == null) ? 0 : classement.hashCode());
	result = prime * result + ((concours == null) ? 0 : concours.hashCode());
	result = prime * result + ((dateAdmission == null) ? 0 : dateAdmission.hashCode());
	result = prime * result + ((dateDepot == null) ? 0 : dateDepot.hashCode());
	result = prime * result + ((dateDesistement == null) ? 0 : dateDesistement.hashCode());
	result = prime * result + ((desistement == null) ? 0 : desistement.hashCode());
	result = prime * result + ((etablissement == null) ? 0 : etablissement.hashCode());
	result = prime * result + ((etablissementAdmission == null) ? 0 : etablissementAdmission.hashCode());
	result = prime * result + ((etudeDossier == null) ? 0 : etudeDossier.hashCode());
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	result = prime * result + ((individu == null) ? 0 : individu.hashCode());
	result = prime * result + ((listeComplementaire == null) ? 0 : listeComplementaire.hashCode());
	result = prime * result + ((motifDesistement == null) ? 0 : motifDesistement.hashCode());
	result = prime * result + ((motifEtude == null) ? 0 : motifEtude.hashCode());
	result = prime * result + ((motifRejet == null) ? 0 : motifRejet.hashCode());
	result = prime * result + ((moyenneObtenue == null) ? 0 : moyenneObtenue.hashCode());
	result = prime * result + ((resultatExamens == null) ? 0 : resultatExamens.hashCode());
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
	DossierCandidat other = (DossierCandidat) obj;
	if (accepte == null) {
	    if (other.accepte != null)
		return false;
	} else if (!accepte.equals(other.accepte))
	    return false;
	if (admis == null) {
	    if (other.admis != null)
		return false;
	} else if (!admis.equals(other.admis))
	    return false;
	if (classement == null) {
	    if (other.classement != null)
		return false;
	} else if (!classement.equals(other.classement))
	    return false;
	if (concours == null) {
	    if (other.concours != null)
		return false;
	} else if (!concours.equals(other.concours))
	    return false;
	if (dateAdmission == null) {
	    if (other.dateAdmission != null)
		return false;
	} else if (!dateAdmission.equals(other.dateAdmission))
	    return false;
	if (dateDepot == null) {
	    if (other.dateDepot != null)
		return false;
	} else if (!dateDepot.equals(other.dateDepot))
	    return false;
	if (dateDesistement == null) {
	    if (other.dateDesistement != null)
		return false;
	} else if (!dateDesistement.equals(other.dateDesistement))
	    return false;
	if (desistement == null) {
	    if (other.desistement != null)
		return false;
	} else if (!desistement.equals(other.desistement))
	    return false;
	if (etablissement == null) {
	    if (other.etablissement != null)
		return false;
	} else if (!etablissement.equals(other.etablissement))
	    return false;
	if (etablissementAdmission == null) {
	    if (other.etablissementAdmission != null)
		return false;
	} else if (!etablissementAdmission.equals(other.etablissementAdmission))
	    return false;
	if (etudeDossier == null) {
	    if (other.etudeDossier != null)
		return false;
	} else if (!etudeDossier.equals(other.etudeDossier))
	    return false;
	if (id == null) {
	    if (other.id != null)
		return false;
	} else if (!id.equals(other.id))
	    return false;
	if (individu == null) {
	    if (other.individu != null)
		return false;
	} else if (!individu.equals(other.individu))
	    return false;
	if (listeComplementaire == null) {
	    if (other.listeComplementaire != null)
		return false;
	} else if (!listeComplementaire.equals(other.listeComplementaire))
	    return false;
	if (motifDesistement == null) {
	    if (other.motifDesistement != null)
		return false;
	} else if (!motifDesistement.equals(other.motifDesistement))
	    return false;
	if (motifEtude == null) {
	    if (other.motifEtude != null)
		return false;
	} else if (!motifEtude.equals(other.motifEtude))
	    return false;
	if (motifRejet == null) {
	    if (other.motifRejet != null)
		return false;
	} else if (!motifRejet.equals(other.motifRejet))
	    return false;
	if (moyenneObtenue == null) {
	    if (other.moyenneObtenue != null)
		return false;
	} else if (!moyenneObtenue.equals(other.moyenneObtenue))
	    return false;
	if (resultatExamens == null) {
	    if (other.resultatExamens != null)
		return false;
	} else if (!resultatExamens.equals(other.resultatExamens))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "DossierCandidat [id=" + id + ", concours=" + concours + ", individu=" + individu + ", etablissement="
		+ etablissement + ", dateDepot=" + dateDepot + ", etudeDossier=" + etudeDossier + ", motifEtude="
		+ motifEtude + ", accepte=" + accepte + ", motifRejet=" + motifRejet + ", admis=" + admis
		+ ", dateAdmission=" + dateAdmission + ", moyenneObtenue=" + moyenneObtenue + ", classement="
		+ classement + ", listeComplementaire=" + listeComplementaire + ", desistement=" + desistement
		+ ", dateDesistement=" + dateDesistement + ", motifDesistement=" + motifDesistement
		+ ", etablissementAdmission=" + etablissementAdmission + ", resultatExamens=" + resultatExamens + "]";
    }

}
