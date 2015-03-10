/**
 * [dz.gov.mesrs.sii.fve.business.model.dto.scolarite.EnseignantFicheVoeuxDto.java] 
 * @author rlaib on : 12 oct. 2014  15:06:05
 */
package dz.gov.mesrs.sii.fve.business.model.dto.scolarite;

/**
 * @author rlaib  on : 12 oct. 2014  15:06:05
 */
public class EnseignantFicheVoeuxDto implements java.io.Serializable {
	
	/**
	 * serialVersionUID 
	 * @author rlaib  on : 29 oct. 2014  16:09:51
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	
	// Enseignant
	private Long idEnseignant;
	private String matriculeEnseignant;
	private String nomEnseignant;
	private String prenomEnseignant;

	// Annee academique
	private Integer idAnneeAcademique;
	private Integer  premiereAnneeAcademique;
	private Integer  deuxiemeAnneeAcademique;

	// Periode
	private Integer idPeriode;
	private String codePeriode;
	private String libellePeriode;
	
	// Periodicite
		private Integer idPeriodicite;
		private String codePeriodicite;
		private String libellePeriodicite;
	
	// Situation
	private Integer idSituation;
	private String libelleSituation;
	private String styleCssSituation;
	
	// Etablissement
	private Integer idEtablissement;
	private String codeEtablissement;
	private String libelleEtablissement;
	
	private boolean estSoumise;
	private boolean estValidee;
	
	
	public EnseignantFicheVoeuxDto() {
		
	}

	/**
	 * [EnseignantFicheVoeuxDto.id] Getter 
	 * @author rlaib on : 12 oct. 2014  15:10:30
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * [EnseignantFicheVoeuxDto.id] Setter 
	 * @author rlaib on : 12 oct. 2014  15:10:30
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	
	/**
	 * [EnseignantFicheVoeuxDto.idEnseignant] Getter 
	 * @author rlaib on : 12 oct. 2014  15:10:30
	 * @return the idEnseignant
	 */
	public Long getIdEnseignant() {
		return idEnseignant;
	}

	/**
	 * [EnseignantFicheVoeuxDto.idEnseignant] Setter 
	 * @author rlaib on : 12 oct. 2014  15:10:30
	 * @param idEnseignant the idEnseignant to set
	 */
	public void setIdEnseignant(Long idEnseignant) {
		this.idEnseignant = idEnseignant;
	}

	/**
	 * [EnseignantFicheVoeuxDto.matriculeEnseignant] Getter 
	 * @author rlaib on : 12 oct. 2014  15:10:30
	 * @return the matriculeEnseignant
	 */
	public String getMatriculeEnseignant() {
		return matriculeEnseignant;
	}

	/**
	 * [EnseignantFicheVoeuxDto.matriculeEnseignant] Setter 
	 * @author rlaib on : 12 oct. 2014  15:10:30
	 * @param matriculeEnseignant the matriculeEnseignant to set
	 */
	public void setMatriculeEnseignant(String matriculeEnseignant) {
		this.matriculeEnseignant = matriculeEnseignant;
	}

	/**
	 * [EnseignantFicheVoeuxDto.nomEnseignant] Getter 
	 * @author rlaib on : 4 nov. 2014  08:21:39
	 * @return the nomEnseignant
	 */
	public String getNomEnseignant() {
		return nomEnseignant;
	}

	/**
	 * [EnseignantFicheVoeuxDto.nomEnseignant] Setter 
	 * @author rlaib on : 4 nov. 2014  08:21:39
	 * @param nomEnseignant the nomEnseignant to set
	 */
	public void setNomEnseignant(String nomEnseignant) {
		this.nomEnseignant = nomEnseignant;
	}

	/**
	 * [EnseignantFicheVoeuxDto.prenomEnseignant] Getter 
	 * @author rlaib on : 4 nov. 2014  08:21:39
	 * @return the prenomEnseignant
	 */
	public String getPrenomEnseignant() {
		return prenomEnseignant;
	}

	/**
	 * [EnseignantFicheVoeuxDto.prenomEnseignant] Setter 
	 * @author rlaib on : 4 nov. 2014  08:21:39
	 * @param prenomEnseignant the prenomEnseignant to set
	 */
	public void setPrenomEnseignant(String prenomEnseignant) {
		this.prenomEnseignant = prenomEnseignant;
	}

	/**
	 * [EnseignantFicheVoeuxDto.idAnneeAcademique] Getter 
	 * @author rlaib on : 12 oct. 2014  15:10:30
	 * @return the idAnneeAcademique
	 */
	public Integer getIdAnneeAcademique() {
		return idAnneeAcademique;
	}

	/**
	 * [EnseignantFicheVoeuxDto.idAnneeAcademique] Setter 
	 * @author rlaib on : 12 oct. 2014  15:10:30
	 * @param idAnneeAcademique the idAnneeAcademique to set
	 */
	public void setIdAnneeAcademique(Integer idAnneeAcademique) {
		this.idAnneeAcademique = idAnneeAcademique;
	}

	/**
	 * [EnseignantFicheVoeuxDto.premiereAnneeAcademique] Getter 
	 * @author rlaib on : 13 oct. 2014  11:36:19
	 * @return the premiereAnneeAcademique
	 */
	public Integer getPremiereAnneeAcademique() {
		return premiereAnneeAcademique;
	}

	/**
	 * [EnseignantFicheVoeuxDto.premiereAnneeAcademique] Setter 
	 * @author rlaib on : 13 oct. 2014  11:36:19
	 * @param premiereAnneeAcademique the premiereAnneeAcademique to set
	 */
	public void setPremiereAnneeAcademique(Integer premiereAnneeAcademique) {
		this.premiereAnneeAcademique = premiereAnneeAcademique;
	}

	/**
	 * [EnseignantFicheVoeuxDto.deuxiemeAnneeAcademique] Getter 
	 * @author rlaib on : 13 oct. 2014  11:36:19
	 * @return the deuxiemeAnneeAcademique
	 */
	public Integer getDeuxiemeAnneeAcademique() {
		return deuxiemeAnneeAcademique;
	}

	/**
	 * [EnseignantFicheVoeuxDto.deuxiemeAnneeAcademique] Setter 
	 * @author rlaib on : 13 oct. 2014  11:36:19
	 * @param deuxiemeAnneeAcademique the deuxiemeAnneeAcademique to set
	 */
	public void setDeuxiemeAnneeAcademique(Integer deuxiemeAnneeAcademique) {
		this.deuxiemeAnneeAcademique = deuxiemeAnneeAcademique;
	}

	
	/**
	 * [EnseignantFicheVoeuxDto.idPeriode] Getter 
	 * @author rlaib on : 12 oct. 2014  15:10:30
	 * @return the idPeriode
	 */
	public Integer getIdPeriode() {
		return idPeriode;
	}

	/**
	 * [EnseignantFicheVoeuxDto.idPeriode] Setter 
	 * @author rlaib on : 12 oct. 2014  15:10:30
	 * @param idPeriode the idPeriode to set
	 */
	public void setIdPeriode(Integer idPeriode) {
		this.idPeriode = idPeriode;
	}

	/**
	 * [EnseignantFicheVoeuxDto.codePeriode] Getter 
	 * @author rlaib on : 12 oct. 2014  15:10:30
	 * @return the codePeriode
	 */
	public String getCodePeriode() {
		return codePeriode;
	}

	/**
	 * [EnseignantFicheVoeuxDto.codePeriode] Setter 
	 * @author rlaib on : 12 oct. 2014  15:10:30
	 * @param codePeriode the codePeriode to set
	 */
	public void setCodePeriode(String codePeriode) {
		this.codePeriode = codePeriode;
	}

	/**
	 * [EnseignantFicheVoeuxDto.libellePeriode] Getter 
	 * @author rlaib on : 12 oct. 2014  15:10:30
	 * @return the libellePeriode
	 */
	public String getLibellePeriode() {
		return libellePeriode;
	}

	/**
	 * [EnseignantFicheVoeuxDto.libellePeriode] Setter 
	 * @author rlaib on : 12 oct. 2014  15:10:30
	 * @param libellePeriode the libellePeriode to set
	 */
	public void setLibellePeriode(String libellePeriode) {
		this.libellePeriode = libellePeriode;
	}

	
	/**
	 * [EnseignantFicheVoeuxDto.idPeriodicite] Getter 
	 * @author Rafik on : 5 déc. 2014  17:57:30
	 * @return the idPeriodicite
	 */
	public Integer getIdPeriodicite() {
		return idPeriodicite;
	}

	/**
	 * [EnseignantFicheVoeuxDto.idPeriodicite] Setter 
	 * @author Rafik on : 5 déc. 2014  17:57:30
	 * @param idPeriodicite the idPeriodicite to set
	 */
	public void setIdPeriodicite(Integer idPeriodicite) {
		this.idPeriodicite = idPeriodicite;
	}

	/**
	 * [EnseignantFicheVoeuxDto.codePeriodicite] Getter 
	 * @author Rafik on : 5 déc. 2014  17:57:30
	 * @return the codePeriodicite
	 */
	public String getCodePeriodicite() {
		return codePeriodicite;
	}

	/**
	 * [EnseignantFicheVoeuxDto.codePeriodicite] Setter 
	 * @author Rafik on : 5 déc. 2014  17:57:30
	 * @param codePeriodicite the codePeriodicite to set
	 */
	public void setCodePeriodicite(String codePeriodicite) {
		this.codePeriodicite = codePeriodicite;
	}

	/**
	 * [EnseignantFicheVoeuxDto.libellePeriodicite] Getter 
	 * @author Rafik on : 5 déc. 2014  17:57:30
	 * @return the libellePeriodicite
	 */
	public String getLibellePeriodicite() {
		return libellePeriodicite;
	}

	/**
	 * [EnseignantFicheVoeuxDto.libellePeriodicite] Setter 
	 * @author Rafik on : 5 déc. 2014  17:57:30
	 * @param libellePeriodicite the libellePeriodicite to set
	 */
	public void setLibellePeriodicite(String libellePeriodicite) {
		this.libellePeriodicite = libellePeriodicite;
	}

	/**
	 * [EnseignantFicheVoeuxDto.idSituation] Getter 
	 * @author rlaib on : 12 oct. 2014  15:10:30
	 * @return the idSituation
	 */
	public Integer getIdSituation() {
		return idSituation;
	}

	/**
	 * [EnseignantFicheVoeuxDto.idSituation] Setter 
	 * @author rlaib on : 12 oct. 2014  15:10:30
	 * @param idSituation the idSituation to set
	 */
	public void setIdSituation(Integer idSituation) {
		this.idSituation = idSituation;
	}

	/**
	 * [EnseignantFicheVoeuxDto.libelleSituation] Getter 
	 * @author rlaib on : 15 oct. 2014  08:38:32
	 * @return the libelleSituation
	 */
	public String getLibelleSituation() {
		return libelleSituation;
	}

	/**
	 * [EnseignantFicheVoeuxDto.libelleSituation] Setter 
	 * @author rlaib on : 15 oct. 2014  08:38:32
	 * @param libelleSituation the libelleSituation to set
	 */
	public void setLibelleSituation(String libelleSituation) {
		this.libelleSituation = libelleSituation;
	}

	/**
	 * [EnseignantFicheVoeuxDto.styleCssSituation] Getter 
	 * @author rlaib on : 20 oct. 2014  10:27:15
	 * @return the styleCssSituation
	 */
	public String getStyleCssSituation() {
		return styleCssSituation;
	}

	/**
	 * [EnseignantFicheVoeuxDto.styleCssSituation] Setter 
	 * @author rlaib on : 20 oct. 2014  10:27:15
	 * @param styleCssSituation the styleCssSituation to set
	 */
	public void setStyleCssSituation(String styleCssSituation) {
		this.styleCssSituation = styleCssSituation;
	}

	/**
	 * [EnseignantFicheVoeuxDto.idEtablissement] Getter 
	 * @author rlaib on : 18 oct. 2014  10:37:26
	 * @return the idEtablissement
	 */
	public Integer getIdEtablissement() {
		return idEtablissement;
	}

	/**
	 * [EnseignantFicheVoeuxDto.idEtablissement] Setter 
	 * @author rlaib on : 18 oct. 2014  10:37:26
	 * @param idEtablissement the idEtablissement to set
	 */
	public void setIdEtablissement(Integer idEtablissement) {
		this.idEtablissement = idEtablissement;
	}

	/**
	 * [EnseignantFicheVoeuxDto.codeEtablissement] Getter 
	 * @author Rafik on : 4 déc. 2014  20:20:16
	 * @return the codeEtablissement
	 */
	public String getCodeEtablissement() {
		return codeEtablissement;
	}

	/**
	 * [EnseignantFicheVoeuxDto.codeEtablissement] Setter 
	 * @author Rafik on : 4 déc. 2014  20:20:16
	 * @param codeEtablissement the codeEtablissement to set
	 */
	public void setCodeEtablissement(String codeEtablissement) {
		this.codeEtablissement = codeEtablissement;
	}

	/**
	 * [EnseignantFicheVoeuxDto.libelleEtablissement] Getter 
	 * @author Rafik on : 4 déc. 2014  20:20:16
	 * @return the libelleEtablissement
	 */
	public String getLibelleEtablissement() {
		return libelleEtablissement;
	}

	/**
	 * [EnseignantFicheVoeuxDto.libelleEtablissement] Setter 
	 * @author Rafik on : 4 déc. 2014  20:20:16
	 * @param libelleEtablissement the libelleEtablissement to set
	 */
	public void setLibelleEtablissement(String libelleEtablissement) {
		this.libelleEtablissement = libelleEtablissement;
	}

	/**
	 * [EnseignantFicheVoeuxDto.estSoumise] Getter 
	 * @author rlaib on : 19 oct. 2014  09:50:57
	 * @return the estSoumise
	 */
	public boolean isEstSoumise() {
		return estSoumise;
	}

	/**
	 * [EnseignantFicheVoeuxDto.estSoumise] Setter 
	 * @author rlaib on : 19 oct. 2014  09:50:57
	 * @param estSoumise the estSoumise to set
	 */
	public void setEstSoumise(boolean estSoumise) {
		this.estSoumise = estSoumise;
	}

	/**
	 * [EnseignantFicheVoeuxDto.estValidee] Getter 
	 * @author rlaib on : 19 oct. 2014  09:50:57
	 * @return the estValidee
	 */
	public boolean isEstValidee() {
		return estValidee;
	}

	/**
	 * [EnseignantFicheVoeuxDto.estValidee] Setter 
	 * @author rlaib on : 19 oct. 2014  09:50:57
	 * @param estValidee the estValidee to set
	 */
	public void setEstValidee(boolean estValidee) {
		this.estValidee = estValidee;
	}
	
}
