package dz.gov.mesrs.sii.commons.data.model.fve.concours;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "resultat_examen", schema = "cursus")
public class ResultatExamen implements Serializable {
    private static final long serialVersionUID = 1367104313231556258L;

    private Long id;
    private ExamenConcours examenConcours;
    private DossierCandidat dossierCandidat;
    private Double note;
    private Boolean admis;

    public ResultatExamen() {
	super();
    }

    @Id
    @SequenceGenerator(name = "resultat_examen_id_seq", initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "resultat_examen_id_seq")
    public Long getId() {
	return id;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "examen_concours", nullable = false)
    public ExamenConcours getExamenConcours() {
	return examenConcours;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "dossier_candidat", nullable = false)
    public DossierCandidat getDossierCandidat() {
	return dossierCandidat;
    }

    @Column(name = "note", nullable = false)
    public Double getNote() {
	return note;
    }
    
    @Column(name = "admis", nullable = false)
    public Boolean getAdmis() {
	return admis;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setExamenConcours(ExamenConcours examenConcours) {
        this.examenConcours = examenConcours;
    }

    public void setDossierCandidat(DossierCandidat dossierCandidat) {
        this.dossierCandidat = dossierCandidat;
    }

    public void setNote(Double note) {
        this.note = note;
    }

    public void setAdmis(Boolean admis) {
        this.admis = admis;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((admis == null) ? 0 : admis.hashCode());
	result = prime * result + ((dossierCandidat == null) ? 0 : dossierCandidat.hashCode());
	result = prime * result + ((examenConcours == null) ? 0 : examenConcours.hashCode());
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	result = prime * result + ((note == null) ? 0 : note.hashCode());
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
	ResultatExamen other = (ResultatExamen) obj;
	if (admis == null) {
	    if (other.admis != null)
		return false;
	} else if (!admis.equals(other.admis))
	    return false;
	if (dossierCandidat == null) {
	    if (other.dossierCandidat != null)
		return false;
	} else if (!dossierCandidat.equals(other.dossierCandidat))
	    return false;
	if (examenConcours == null) {
	    if (other.examenConcours != null)
		return false;
	} else if (!examenConcours.equals(other.examenConcours))
	    return false;
	if (id == null) {
	    if (other.id != null)
		return false;
	} else if (!id.equals(other.id))
	    return false;
	if (note == null) {
	    if (other.note != null)
		return false;
	} else if (!note.equals(other.note))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "ResultatExamen [id=" + id + ", examenConcours=" + examenConcours + ", dossierCandidat="
		+ dossierCandidat + ", note=" + note + ", admis=" + admis + "]";
    }

    
}
