package dz.gov.mesrs.sii.commons.data.model.fve.concours;

import java.io.Serializable;
import java.util.List;

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

import dz.gov.mesrs.sii.commons.data.model.referentiel.RefEtablissement;

@Entity
@Table(name = "etablissement_admission", schema = "cursus")
public class EtablissementAdmission implements Serializable {

    private static final long serialVersionUID = -8888832882062861699L;

    private Long id;
    private Concours concours;
    private Integer nombreAdmettre;
    private RefEtablissement etablissement;
    private List<DossierCandidat> candidats;

    @Id
    @SequenceGenerator(name = "etablissement_admission_id_seq", initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "etablissement_admission_id_seq")
    public Long getId() {
	return id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "concours", nullable = false, insertable = true, updatable = false)
    public Concours getConcours() {
	return concours;
    }

    @Column(name = "nombre_admettre", nullable = false)
    public Integer getNombreAdmettre() {
	return nombreAdmettre;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ref_etablissement", nullable = false)
    public RefEtablissement getEtablissement() {
	return etablissement;
    }

    @OneToMany(mappedBy = "etablissementAdmission", fetch = FetchType.LAZY)
    public List<DossierCandidat> getCandidats() {
	return candidats;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public void setConcours(Concours concours) {
	this.concours = concours;
    }

    public void setNombreAdmettre(Integer nombreAdmettre) {
	this.nombreAdmettre = nombreAdmettre;
    }

    public void setEtablissement(RefEtablissement etablissement) {
	this.etablissement = etablissement;
    }

    public void setCandidats(List<DossierCandidat> candidats) {
	this.candidats = candidats;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((concours == null) ? 0 : concours.hashCode());
	result = prime * result + ((etablissement == null) ? 0 : etablissement.hashCode());
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	result = prime * result + ((nombreAdmettre == null) ? 0 : nombreAdmettre.hashCode());
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
	EtablissementAdmission other = (EtablissementAdmission) obj;
	if (concours == null) {
	    if (other.concours != null)
		return false;
	} else if (!concours.equals(other.concours))
	    return false;
	if (etablissement == null) {
	    if (other.etablissement != null)
		return false;
	} else if (!etablissement.equals(other.etablissement))
	    return false;
	if (id == null) {
	    if (other.id != null)
		return false;
	} else if (!id.equals(other.id))
	    return false;
	if (nombreAdmettre == null) {
	    if (other.nombreAdmettre != null)
		return false;
	} else if (!nombreAdmettre.equals(other.nombreAdmettre))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "EtablissementAdmission [id=" + id + ", concours=" + concours.getId() + ", nombreAdmettre="
		+ nombreAdmettre + ", etablissement=" + etablissement + "]";
    }

}
