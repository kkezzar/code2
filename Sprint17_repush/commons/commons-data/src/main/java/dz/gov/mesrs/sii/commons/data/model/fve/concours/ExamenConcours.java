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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import dz.gov.mesrs.sii.commons.data.model.referentiel.Nomenclature;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefIndividu;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefLieu;

@Entity
@Table(name = "examen_concours", schema = "cursus")
public class ExamenConcours implements Serializable {

    private static final long serialVersionUID = 4592261966545943580L;

    private Long id;
    private String intitule;
    private Date dateDebut;
    private Double duree;
    private Integer noteBase;
    private Double coefficient;
    private Concours concours;
    private RefIndividu responsable;
    private RefLieu lieu;
    private Nomenclature typeExamen;
    private Nomenclature epreuve;
    private List<ResultatExamen> resultatExamens;

    public ExamenConcours() {
	super();
    }

    @Id
    @SequenceGenerator(name = "examen_concours_id_seq", initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "examen_concours_id_seq")
    public Long getId() {
	return id;
    }

    @Column(name = "intitule", nullable = false)
    public String getIntitule() {
	return intitule;
    }

    @Column(name = "date_debut", nullable = false)
    public Date getDateDebut() {
	return dateDebut;
    }

    @Column(name = "duree", nullable = false)
    public Double getDuree() {
	return duree;
    }

    @Column(name = "note_base", nullable = false)
    public Integer getNoteBase() {
	return noteBase;
    }

    @Column(name = "coefficient", nullable = false)
    public Double getCoefficient() {
	return coefficient;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "concours", nullable = false)
    public Concours getConcours() {
	return concours;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "responsable", nullable = false)
    public RefIndividu getResponsable() {
	return responsable;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "lieu", nullable = false)
    public RefLieu getLieu() {
	return lieu;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "type_examen", nullable = false)
    public Nomenclature getTypeExamen() {
	return typeExamen;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "epreuve", nullable = false)
    public Nomenclature getEpreuve() {
	return epreuve;
    }

    @OneToMany(mappedBy = "examenConcours", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<ResultatExamen> getResultatExamens() {
	if(resultatExamens==null){
	    resultatExamens = new ArrayList<>();
	}
	return resultatExamens;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public void setIntitule(String intitule) {
	this.intitule = intitule;
    }

    public void setDateDebut(Date dateDebut) {
	this.dateDebut = dateDebut;
    }

    public void setDuree(Double duree) {
	this.duree = duree;
    }

    public void setNoteBase(Integer noteBase) {
	this.noteBase = noteBase;
    }

    public void setCoefficient(Double coefficient) {
	this.coefficient = coefficient;
    }

    public void setConcours(Concours concours) {
	this.concours = concours;
    }

    public void setResponsable(RefIndividu responsable) {
	this.responsable = responsable;
    }

    public void setLieu(RefLieu lieu) {
	this.lieu = lieu;
    }

    public void setTypeExamen(Nomenclature typeExamen) {
	this.typeExamen = typeExamen;
    }

    public void setEpreuve(Nomenclature epreuve) {
	this.epreuve = epreuve;
    }

    public void setResultatExamens(List<ResultatExamen> resultatExamens) {
	this.resultatExamens = resultatExamens;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((coefficient == null) ? 0 : coefficient.hashCode());
	result = prime * result + ((concours == null) ? 0 : concours.hashCode());
	result = prime * result + ((dateDebut == null) ? 0 : dateDebut.hashCode());
	result = prime * result + ((duree == null) ? 0 : duree.hashCode());
	result = prime * result + ((epreuve == null) ? 0 : epreuve.hashCode());
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	result = prime * result + ((intitule == null) ? 0 : intitule.hashCode());
	result = prime * result + ((lieu == null) ? 0 : lieu.hashCode());
	result = prime * result + ((noteBase == null) ? 0 : noteBase.hashCode());
	result = prime * result + ((responsable == null) ? 0 : responsable.hashCode());
	result = prime * result + ((resultatExamens == null) ? 0 : resultatExamens.hashCode());
	result = prime * result + ((typeExamen == null) ? 0 : typeExamen.hashCode());
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
	ExamenConcours other = (ExamenConcours) obj;
	if (coefficient == null) {
	    if (other.coefficient != null)
		return false;
	} else if (!coefficient.equals(other.coefficient))
	    return false;
	if (concours == null) {
	    if (other.concours != null)
		return false;
	} else if (!concours.equals(other.concours))
	    return false;
	if (dateDebut == null) {
	    if (other.dateDebut != null)
		return false;
	} else if (!dateDebut.equals(other.dateDebut))
	    return false;
	if (duree == null) {
	    if (other.duree != null)
		return false;
	} else if (!duree.equals(other.duree))
	    return false;
	if (epreuve == null) {
	    if (other.epreuve != null)
		return false;
	} else if (!epreuve.equals(other.epreuve))
	    return false;
	if (id == null) {
	    if (other.id != null)
		return false;
	} else if (!id.equals(other.id))
	    return false;
	if (intitule == null) {
	    if (other.intitule != null)
		return false;
	} else if (!intitule.equals(other.intitule))
	    return false;
	if (lieu == null) {
	    if (other.lieu != null)
		return false;
	} else if (!lieu.equals(other.lieu))
	    return false;
	if (noteBase == null) {
	    if (other.noteBase != null)
		return false;
	} else if (!noteBase.equals(other.noteBase))
	    return false;
	if (responsable == null) {
	    if (other.responsable != null)
		return false;
	} else if (!responsable.equals(other.responsable))
	    return false;
	if (resultatExamens == null) {
	    if (other.resultatExamens != null)
		return false;
	} else if (!resultatExamens.equals(other.resultatExamens))
	    return false;
	if (typeExamen == null) {
	    if (other.typeExamen != null)
		return false;
	} else if (!typeExamen.equals(other.typeExamen))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "ExamenConcours [id=" + id + ", intitule=" + intitule + ", dateDebut=" + dateDebut + ", duree=" + duree
		+ ", noteBase=" + noteBase + ", coefficient=" + coefficient 
		+ ", responsable=" + responsable + ", lieu=" + lieu + ", typeExamen=" + typeExamen + ", epreuve="
		+ epreuve + ", resultatExamens=" + resultatExamens + "]";
    }

}
