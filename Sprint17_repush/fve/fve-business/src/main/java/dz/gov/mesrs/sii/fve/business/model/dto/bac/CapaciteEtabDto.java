package dz.gov.mesrs.sii.fve.business.model.dto.bac;

import java.util.List;

/**
 * @author Mounir.MESSAOUDI on : 14 aout 2014 11:55:59
 */
public class CapaciteEtabDto implements java.io.Serializable {

	/**
	 * @author Mounir.MESSAOUDI on : 14 aout 2014 11:52:48
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;

	// anneeAcademique
	private Integer idAnneeAcademique;
	private short anneeAcademiquePremiereAnnee;
	private short anneeAcademiqueDeuxiemeAnnee;
	private String anneeAcademiquelibelle;

	private Integer capaciteAccueil;
	private Integer capaciteTransfert;

	private String refCodeDomaine;
	private String domaineLibelleLt;
	private String domaineLibelleAr;

	private String refCodeFiliere;
	private String filiereLibelleLt;
	private String filiereLibelleAr;

	private String refCodeEtablissement;
	private String etablissementLibelleLt;
	private String etablissementLibelleAr;
	
	private List<PrioriteSerieBacDto> prioriteSerieBacDto;

	public CapaciteEtabDto() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCapaciteAccueil() {
		return this.capaciteAccueil;
	}

	public void setCapaciteAccueil(Integer capaciteAccueil) {
		this.capaciteAccueil = capaciteAccueil;
	}
	
	public Integer getCapaciteTransfert() {
		return this.capaciteTransfert;
	}

	public void setCapaciteTransfert(Integer capaciteTransfert) {
		this.capaciteTransfert = capaciteTransfert;
	}

	public String getRefCodeDomaine() {
		return this.refCodeDomaine;
	}

	public void setRefCodeDomaine(String refCodeDomaine) {
		this.refCodeDomaine = refCodeDomaine;
	}

	public String getRefCodeFiliere() {
		return this.refCodeFiliere;
	}

	public void setRefCodeFiliere(String refCodeFiliere) {
		this.refCodeFiliere = refCodeFiliere;
	}

	public String getRefCodeEtablissement() {
		return this.refCodeEtablissement;
	}

	public void setRefCodeEtablissement(String refCodeEtablissement) {
		this.refCodeEtablissement = refCodeEtablissement;
	}

	public Integer getIdAnneeAcademique() {
		return idAnneeAcademique;
	}

	public short getAnneeAcademiquePremiereAnnee() {
		return anneeAcademiquePremiereAnnee;
	}

	public void setAnneeAcademiquePremiereAnnee(short anneeAcademiquePremiereAnnee) {
		this.anneeAcademiquePremiereAnnee = anneeAcademiquePremiereAnnee;
	}

	public short getAnneeAcademiqueDeuxiemeAnnee() {
		return anneeAcademiqueDeuxiemeAnnee;
	}

	public void setAnneeAcademiqueDeuxiemeAnnee(short anneeAcademiqueDeuxiemeAnnee) {
		this.anneeAcademiqueDeuxiemeAnnee = anneeAcademiqueDeuxiemeAnnee;
	}

	public String getAnneeAcademiquelibelle() {
		return anneeAcademiquelibelle;
	}

	public void setAnneeAcademiquelibelle(String anneeAcademiquelibelle) {
		this.anneeAcademiquelibelle = anneeAcademiquelibelle;
	}

	public void setIdAnneeAcademique(Integer idAnneeAcademique) {
		this.idAnneeAcademique = idAnneeAcademique;
	}

	public String getDomaineLibelleLt() {
		return domaineLibelleLt;
	}

	public void setDomaineLibelleLt(String domaineLibelleLt) {
		this.domaineLibelleLt = domaineLibelleLt;
	}

	public String getDomaineLibelleAr() {
		return domaineLibelleAr;
	}

	public void setDomaineLibelleAr(String domaineLibelleAr) {
		this.domaineLibelleAr = domaineLibelleAr;
	}

	public String getFiliereLibelleLt() {
		return filiereLibelleLt;
	}

	public void setFiliereLibelleLt(String filiereLibelleLt) {
		this.filiereLibelleLt = filiereLibelleLt;
	}

	public String getFiliereLibelleAr() {
		return filiereLibelleAr;
	}

	public void setFiliereLibelleAr(String filiereLibelleAr) {
		this.filiereLibelleAr = filiereLibelleAr;
	}

	public String getEtablissementLibelleLt() {
		return etablissementLibelleLt;
	}

	public void setEtablissementLibelleLt(String etablissementLibelleLt) {
		this.etablissementLibelleLt = etablissementLibelleLt;
	}

	public String getEtablissementLibelleAr() {
		return etablissementLibelleAr;
	}

	public void setEtablissementLibelleAr(String etablissementLibelleAr) {
		this.etablissementLibelleAr = etablissementLibelleAr;
	}

	public List<PrioriteSerieBacDto> getPrioriteSerieBacDto() {
		return prioriteSerieBacDto;
	}

	public void setPrioriteSerieBacDto(List<PrioriteSerieBacDto> prioriteSerieBacDto) {
		this.prioriteSerieBacDto = prioriteSerieBacDto;
	}

}
