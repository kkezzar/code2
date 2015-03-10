/**
 *  
 * @author Mounir.MESSAOUDI on : 28 sept. 2014 15:28:03
 */
package dz.gov.mesrs.sii.fve.business.model.dto.cursus;

import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OuvertureOffreFormationDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefDomaineLMDDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefFiliereLmdDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefSpecialiteLmdDto;

/**
 * @author Mounir.MESSAOUDI on : 28 sept. 2014 15:28:03
 */
public class VoeuEtudiantChoixDto implements java.io.Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author MESSAOUDI Mounir on : 28 septembre 2014 09:00:31
	 */
	private static final long serialVersionUID = 1L;

	private int id;

	private Integer idVoeuEtudiant;

//	private Integer ouvertureOfId;
//	private String ofLibelleFr;
//	private String ofLibelleFiliereFr;
//	private String ofLibelleAr;
//	private String ofLibelleFiliereAr;
//
//	private String refCodeDomaine;
//	private String domaineLibelleFr;
//	private String domaineLibelleFiliereFr;
//	private String domaineLibelleAr;
//	private String domaineLibelleFiliereAr;
//
//	private String refCodeFiliere;
//	private String filiereLibelleFr;
//	private String filiereLibelleFiliereFr;
//	private String filiereLibelleAr;
//	private String filiereLibelleFiliereAr;
//
//	private String refCodeSpecialite;
//	private String specialiteLibelleFr;
//	private String specialiteLibelleFiliereFr;
//	private String specialiteLibelleAr;
//	private String specialiteLibelleFiliereAr;
	
	private OuvertureOffreFormationDto ouvertureOffreFormation;
	private RefDomaineLMDDto domaine;
	private RefFiliereLmdDto filiere;
	private RefSpecialiteLmdDto specialite;
	
	private Integer priorite;

	private Boolean estRetenu;

	/**
	 * @author Mounir.MESSAOUDI on : 28 sept. 2014 15:28:03
	 */
	public VoeuEtudiantChoixDto() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getIdVoeuEtudiant() {
		return idVoeuEtudiant;
	}

	public void setIdVoeuEtudiant(Integer idVoeuEtudiant) {
		this.idVoeuEtudiant = idVoeuEtudiant;
	}

	public OuvertureOffreFormationDto getOuvertureOffreFormation() {
		return ouvertureOffreFormation;
	}

	public void setOuvertureOffreFormation(
			OuvertureOffreFormationDto ouvertureOffreFormation) {
		this.ouvertureOffreFormation = ouvertureOffreFormation;
	}

	public RefDomaineLMDDto getDomaine() {
		return domaine;
	}

	public void setDomaine(RefDomaineLMDDto domaine) {
		this.domaine = domaine;
	}

	public RefFiliereLmdDto getFiliere() {
		return filiere;
	}

	public void setFiliere(RefFiliereLmdDto filiere) {
		this.filiere = filiere;
	}

	public RefSpecialiteLmdDto getSpecialite() {
		return specialite;
	}

	public void setSpecialite(RefSpecialiteLmdDto specialite) {
		this.specialite = specialite;
	}

	public Integer getPriorite() {
		return priorite;
	}

	public void setPriorite(Integer priorite) {
		this.priorite = priorite;
	}

	public Boolean getEstRetenu() {
		return estRetenu;
	}

	public void setEstRetenu(Boolean estRetenu) {
		this.estRetenu = estRetenu;
	}

}