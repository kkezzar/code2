package dz.gov.mesrs.sii.commons.data.model.fve.concours;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "dossier_candidat_piece", schema = "cursus")
public class PieceFournie implements Serializable {
    private static final long serialVersionUID = -7612616015144512898L;

    private Long id;
    private DossierCandidat dossierCandidat;
    private PieceConcours pieceConcours;

    public PieceFournie() {
	super();
    }

    @Id
    @SequenceGenerator(name = "dossier_candidat_piece_id_seq", initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dossier_candidat_piece_id_seq")
    public Long getId() {
	return id;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "dossier_candidat", nullable = false)
    public DossierCandidat getDossierCandidat() {
	return dossierCandidat;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "piece_concours", nullable = false)
    public PieceConcours getPieceConcours() {
	return pieceConcours;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public void setDossierCandidat(DossierCandidat dossierCandidat) {
	this.dossierCandidat = dossierCandidat;
    }

    public void setPieceConcours(PieceConcours pieceConcours) {
	this.pieceConcours = pieceConcours;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((dossierCandidat == null) ? 0 : dossierCandidat.hashCode());
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	result = prime * result + ((pieceConcours == null) ? 0 : pieceConcours.hashCode());
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
	PieceFournie other = (PieceFournie) obj;
	if (dossierCandidat == null) {
	    if (other.dossierCandidat != null)
		return false;
	} else if (!dossierCandidat.equals(other.dossierCandidat))
	    return false;
	if (id == null) {
	    if (other.id != null)
		return false;
	} else if (!id.equals(other.id))
	    return false;
	if (pieceConcours == null) {
	    if (other.pieceConcours != null)
		return false;
	} else if (!pieceConcours.equals(other.pieceConcours))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "PieceFournie [id=" + id + ", dossierCandidat=" + dossierCandidat.getId() + ", pieceConcours="
		+ pieceConcours.getId() + "]";
    }

}
