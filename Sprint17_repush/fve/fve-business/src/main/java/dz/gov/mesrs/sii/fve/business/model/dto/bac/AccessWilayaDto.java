package dz.gov.mesrs.sii.fve.business.model.dto.bac;

import java.util.List;

/**
 * @author Mounir.MESSAOUDI on : 14 aout 2014 11:55:51
 */
public class AccessWilayaDto implements java.io.Serializable {

	/**
	 * @author Mounir.MESSAOUDI on : 14 aout 2014 11:51:10
	 */
	private static final long serialVersionUID = 1L;
	private int id;

	// AnneeAcademique
	private Integer idAnneeAcademique;
	private short anneeAcademiquePremiereAnnee;
	private short anneeAcademiqueDeuxiemeAnnee;
	private String anneeAcademiquelibelle;

	private String refCodeDomaine;
	private String domaineLibelleLt;
	private String domaineLibelleAr;

	private String refCodeFiliere;
	private String filiereLibelleLt;
	private String filiereLibelleAr;

	private String refCodeEtablissement;
	private String etablissementLibelleLt;
	private String etablissementLibelleAr;

	private List<DroitAccessWilayaDto> droitAccessWilayaDto;

	public AccessWilayaDto() {
	}

	public AccessWilayaDto(Integer idAnneeAcademique,String refCodeEtablissement ) {
		this.idAnneeAcademique = idAnneeAcademique;
		this.refCodeEtablissement = refCodeEtablissement;
	}
	
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
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

	public void setIdAnneeAcademique(Integer idAnneeAcademique) {
		this.idAnneeAcademique = idAnneeAcademique;
	}

	public short getAnneeAcademiquePremiereAnnee() {
		return anneeAcademiquePremiereAnnee;
	}

	public void setAnneeAcademiquePremiereAnnee(
			short anneeAcademiquePremiereAnnee) {
		this.anneeAcademiquePremiereAnnee = anneeAcademiquePremiereAnnee;
	}

	public short getAnneeAcademiqueDeuxiemeAnnee() {
		return anneeAcademiqueDeuxiemeAnnee;
	}

	public void setAnneeAcademiqueDeuxiemeAnnee(
			short anneeAcademiqueDeuxiemeAnnee) {
		this.anneeAcademiqueDeuxiemeAnnee = anneeAcademiqueDeuxiemeAnnee;
	}

	public String getAnneeAcademiquelibelle() {
		return anneeAcademiquelibelle;
	}

	public void setAnneeAcademiquelibelle(String anneeAcademiquelibelle) {
		this.anneeAcademiquelibelle = anneeAcademiquelibelle;
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

	public List<DroitAccessWilayaDto> getDroitAccessWilayaDto() {
		return droitAccessWilayaDto;
	}

	public void setDroitAccessWilayaDto(
			List<DroitAccessWilayaDto> droitAccessWilayaDto) {
		this.droitAccessWilayaDto = droitAccessWilayaDto;
	}

}
