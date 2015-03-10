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

import dz.gov.mesrs.sii.commons.data.model.referentiel.Nomenclature;

@Entity
@Table(name = "piece_concours", schema = "cursus")
public class PieceConcours implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 6314734197255197318L;

    private Long id;
    private Integer rang;
    private String intitule;
    private Boolean obligatoire;
    private Nomenclature typePiece;
    private Concours concours;

    public PieceConcours() {
	super();
    }

    @Id
    @SequenceGenerator(name = "piece_concours_id_seq", initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "piece_concours_id_seq")
    public Long getId() {
	return id;
    }

    @Column(name = "rang", nullable = false)
    public Integer getRang() {
	return rang;
    }

    @Column(name = "intitule", nullable = false)
    public String getIntitule() {
	return intitule;
    }

    @Column(name = "obligatoire", nullable = false)
    public Boolean getObligatoire() {
	return obligatoire;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "type_piece", nullable = false)
    public Nomenclature getTypePiece() {
	return typePiece;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "concours", nullable = false)
    public Concours getConcours() {
	return concours;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public void setRang(Integer rang) {
	this.rang = rang;
    }

    public void setIntitule(String intitule) {
	this.intitule = intitule;
    }

    public void setObligatoire(Boolean obligatoire) {
	this.obligatoire = obligatoire;
    }

    public void setTypePiece(Nomenclature typePiece) {
	this.typePiece = typePiece;
    }

    public void setConcours(Concours concours) {
	this.concours = concours;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((concours == null) ? 0 : concours.hashCode());
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	result = prime * result + ((intitule == null) ? 0 : intitule.hashCode());
	result = prime * result + ((obligatoire == null) ? 0 : obligatoire.hashCode());
	result = prime * result + ((rang == null) ? 0 : rang.hashCode());
	result = prime * result + ((typePiece == null) ? 0 : typePiece.hashCode());
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
	PieceConcours other = (PieceConcours) obj;
	if (concours == null) {
	    if (other.concours != null)
		return false;
	} else if (!concours.equals(other.concours))
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
	if (obligatoire == null) {
	    if (other.obligatoire != null)
		return false;
	} else if (!obligatoire.equals(other.obligatoire))
	    return false;
	if (rang == null) {
	    if (other.rang != null)
		return false;
	} else if (!rang.equals(other.rang))
	    return false;
	if (typePiece == null) {
	    if (other.typePiece != null)
		return false;
	} else if (!typePiece.equals(other.typePiece))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "PieceConcours [id=" + id + ", rang=" + rang + ", intitule=" + intitule + ", obligatoire=" + obligatoire
		+ ", typePiece=" + typePiece + "]";
    }

}
