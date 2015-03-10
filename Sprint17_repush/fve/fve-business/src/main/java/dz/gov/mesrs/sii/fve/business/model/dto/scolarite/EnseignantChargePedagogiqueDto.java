/**
 * [dz.gov.mesrs.sii.fve.business.model.bo.scolarite.EnseignantChargePedagogiqueDto.java] 
 * @author rlaib on : 12 oct. 2014  14:03:13
 */
package dz.gov.mesrs.sii.fve.business.model.dto.scolarite;

/**
 * @author rlaib  on : 12 oct. 2014  14:03:13
 */

public class EnseignantChargePedagogiqueDto implements java.io.Serializable {
	
	/**
	 * serialVersionUID 
	 * @author rlaib  on : 29 oct. 2014  16:09:57
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private Integer idTypeCharge;
	private Integer idStatutServicePedagogique;
	private Integer effectifGroupeAp;
	private Double volumeHoraireAp;
	
	// Fiche Services
	private Integer ficheServiceId;
	
	// Voeu Ligne
	private Integer voeuLigneId;
	
	// Groupe Pedagogique
	private Integer groupePedagogiqueId;
	private String groupePedagogiqueLibelle;
	
	// Matiere
	private Integer matiereId;
	private String matiereLibelle;
	
	// Niveau
	private Integer niveauId;
	private String niveauCode;
	private String niveauLibelle;
	
	// Periode
	private int idPeriode;
	private String codePeriode;
	private String libellePeriode;
	
	// AP
	private Integer apId;
	private String apCodeType;
	private String apLibelle;
	private String typeEnseignement;
	
	// Situation
	private Integer idSituation;
	private String styleCssSituation;
	private String libelleSituation;
	private boolean incluse;
	
	// Offre de formation
	private Integer offreFormationId;
	private String offreFormationCode;
	private String offreFormationLibelle;
	
	// Etablissement
	private Integer idEtablissement;
	private String codeEtablissement;
	private String libelleEtablissement;
	
	public EnseignantChargePedagogiqueDto() {
		
	}


	/**
	 * [EnseignantChargePedagogiqueDto.id] Getter 
	 * @author rlaib on : 22 oct. 2014  12:23:33
	 * @return the id
	 */
	public int getId() {
		return id;
	}


	/**
	 * [EnseignantChargePedagogiqueDto.id] Setter 
	 * @author rlaib on : 22 oct. 2014  12:23:33
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}


	/**
	 * [EnseignantChargePedagogiqueDto.idTypeCharge] Getter 
	 * @author rlaib on : 22 oct. 2014  12:23:33
	 * @return the idTypeCharge
	 */
	public Integer getIdTypeCharge() {
		return idTypeCharge;
	}


	/**
	 * [EnseignantChargePedagogiqueDto.idTypeCharge] Setter 
	 * @author rlaib on : 22 oct. 2014  12:23:33
	 * @param idTypeCharge the idTypeCharge to set
	 */
	public void setIdTypeCharge(Integer idTypeCharge) {
		this.idTypeCharge = idTypeCharge;
	}


	/**
	 * [EnseignantChargePedagogiqueDto.idStatutServicePedagogique] Getter 
	 * @author rlaib on : 22 oct. 2014  12:23:33
	 * @return the idStatutServicePedagogique
	 */
	public Integer getIdStatutServicePedagogique() {
		return idStatutServicePedagogique;
	}


	/**
	 * [EnseignantChargePedagogiqueDto.idStatutServicePedagogique] Setter 
	 * @author rlaib on : 22 oct. 2014  12:23:33
	 * @param idStatutServicePedagogique the idStatutServicePedagogique to set
	 */
	public void setIdStatutServicePedagogique(Integer idStatutServicePedagogique) {
		this.idStatutServicePedagogique = idStatutServicePedagogique;
	}


	/**
	 * [EnseignantChargePedagogiqueDto.effectifGroupeAp] Getter 
	 * @author rlaib on : 22 oct. 2014  12:23:33
	 * @return the effectifGroupeAp
	 */
	public Integer getEffectifGroupeAp() {
		return effectifGroupeAp;
	}


	/**
	 * [EnseignantChargePedagogiqueDto.effectifGroupeAp] Setter 
	 * @author rlaib on : 22 oct. 2014  12:23:33
	 * @param effectifGroupeAp the effectifGroupeAp to set
	 */
	public void setEffectifGroupeAp(Integer effectifGroupeAp) {
		this.effectifGroupeAp = effectifGroupeAp;
	}


	/**
	 * [EnseignantChargePedagogiqueDto.volumeHoraireAp] Getter 
	 * @author rlaib on : 22 oct. 2014  12:23:33
	 * @return the volumeHoraireAp
	 */
	public Double getVolumeHoraireAp() {
		return volumeHoraireAp;
	}


	/**
	 * [EnseignantChargePedagogiqueDto.volumeHoraireAp] Setter 
	 * @author rlaib on : 22 oct. 2014  12:23:33
	 * @param volumeHoraireAp the volumeHoraireAp to set
	 */
	public void setVolumeHoraireAp(Double volumeHoraireAp) {
		this.volumeHoraireAp = volumeHoraireAp;
	}


	/**
	 * [EnseignantChargePedagogiqueDto.ficheServiceId] Getter 
	 * @author rlaib on : 22 oct. 2014  12:23:33
	 * @return the ficheServiceId
	 */
	public Integer getFicheServiceId() {
		return ficheServiceId;
	}


	/**
	 * [EnseignantChargePedagogiqueDto.ficheServiceId] Setter 
	 * @author rlaib on : 22 oct. 2014  12:23:33
	 * @param ficheServiceId the ficheServiceId to set
	 */
	public void setFicheServiceId(Integer ficheServiceId) {
		this.ficheServiceId = ficheServiceId;
	}


	

	/**
	 * [EnseignantChargePedagogiqueDto.voeuLigneId] Getter 
	 * @author rlaib on : 22 oct. 2014  12:23:33
	 * @return the voeuLigneId
	 */
	public Integer getVoeuLigneId() {
		return voeuLigneId;
	}


	/**
	 * [EnseignantChargePedagogiqueDto.voeuLigneId] Setter 
	 * @author rlaib on : 22 oct. 2014  12:23:33
	 * @param voeuLigneId the voeuLigneId to set
	 */
	public void setVoeuLigneId(Integer voeuLigneId) {
		this.voeuLigneId = voeuLigneId;
	}


	/**
	 * [EnseignantChargePedagogiqueDto.groupePedagogiqueId] Getter 
	 * @author rlaib on : 24 oct. 2014  16:13:18
	 * @return the groupePedagogiqueId
	 */
	public Integer getGroupePedagogiqueId() {
		return groupePedagogiqueId;
	}


	/**
	 * [EnseignantChargePedagogiqueDto.groupePedagogiqueId] Setter 
	 * @author rlaib on : 24 oct. 2014  16:13:18
	 * @param groupePedagogiqueId the groupePedagogiqueId to set
	 */
	public void setGroupePedagogiqueId(Integer groupePedagogiqueId) {
		this.groupePedagogiqueId = groupePedagogiqueId;
	}


	/**
	 * [EnseignantChargePedagogiqueDto.idSituation] Getter 
	 * @author rlaib on : 25 oct. 2014  10:57:55
	 * @return the idSituation
	 */
	public Integer getIdSituation() {
		return idSituation;
	}


	/**
	 * [EnseignantChargePedagogiqueDto.idSituation] Setter 
	 * @author rlaib on : 25 oct. 2014  10:57:55
	 * @param idSituation the idSituation to set
	 */
	public void setIdSituation(Integer idSituation) {
		this.idSituation = idSituation;
	}


	/**
	 * [EnseignantChargePedagogiqueDto.styleCssSituation] Getter 
	 * @author rlaib on : 26 oct. 2014  11:11:26
	 * @return the styleCssSituation
	 */
	public String getStyleCssSituation() {
		return styleCssSituation;
	}


	/**
	 * [EnseignantChargePedagogiqueDto.styleCssSituation] Setter 
	 * @author rlaib on : 26 oct. 2014  11:11:26
	 * @param styleCssSituation the styleCssSituation to set
	 */
	public void setStyleCssSituation(String styleCssSituation) {
		this.styleCssSituation = styleCssSituation;
	}


	/**
	 * [EnseignantChargePedagogiqueDto.libelleSituation] Getter 
	 * @author rlaib on : 26 oct. 2014  11:11:26
	 * @return the libelleSituation
	 */
	public String getLibelleSituation() {
		return libelleSituation;
	}


	/**
	 * [EnseignantChargePedagogiqueDto.libelleSituation] Setter 
	 * @author rlaib on : 26 oct. 2014  11:11:26
	 * @param libelleSituation the libelleSituation to set
	 */
	public void setLibelleSituation(String libelleSituation) {
		this.libelleSituation = libelleSituation;
	}


	/**
	 * [EnseignantChargePedagogiqueDto.groupePedagogiqueLibelle] Getter 
	 * @author rlaib on : 26 oct. 2014  11:25:25
	 * @return the groupePedagogiqueLibelle
	 */
	public String getGroupePedagogiqueLibelle() {
		return groupePedagogiqueLibelle;
	}


	/**
	 * [EnseignantChargePedagogiqueDto.groupePedagogiqueLibelle] Setter 
	 * @author rlaib on : 26 oct. 2014  11:25:25
	 * @param groupePedagogiqueLibelle the groupePedagogiqueLibelle to set
	 */
	public void setGroupePedagogiqueLibelle(String groupePedagogiqueLibelle) {
		this.groupePedagogiqueLibelle = groupePedagogiqueLibelle;
	}


	/**
	 * [EnseignantChargePedagogiqueDto.matiereId] Getter 
	 * @author rlaib on : 26 oct. 2014  11:59:34
	 * @return the matiereId
	 */
	public Integer getMatiereId() {
		return matiereId;
	}


	/**
	 * [EnseignantChargePedagogiqueDto.matiereId] Setter 
	 * @author rlaib on : 26 oct. 2014  11:59:34
	 * @param matiereId the matiereId to set
	 */
	public void setMatiereId(Integer matiereId) {
		this.matiereId = matiereId;
	}


	/**
	 * [EnseignantChargePedagogiqueDto.matiereLibelle] Getter 
	 * @author rlaib on : 26 oct. 2014  11:59:34
	 * @return the matiereLibelle
	 */
	public String getMatiereLibelle() {
		return matiereLibelle;
	}


	/**
	 * [EnseignantChargePedagogiqueDto.matiereLibelle] Setter 
	 * @author rlaib on : 26 oct. 2014  11:59:34
	 * @param matiereLibelle the matiereLibelle to set
	 */
	public void setMatiereLibelle(String matiereLibelle) {
		this.matiereLibelle = matiereLibelle;
	}


	/**
	 * [EnseignantChargePedagogiqueDto.apId] Getter 
	 * @author rlaib on : 26 oct. 2014  11:59:34
	 * @return the apId
	 */
	public Integer getApId() {
		return apId;
	}


	/**
	 * [EnseignantChargePedagogiqueDto.apId] Setter 
	 * @author rlaib on : 26 oct. 2014  11:59:34
	 * @param apId the apId to set
	 */
	public void setApId(Integer apId) {
		this.apId = apId;
	}


	/**
	 * [EnseignantChargePedagogiqueDto.apCodeType] Getter 
	 * @author rlaib on : 26 oct. 2014  11:59:34
	 * @return the apCodeType
	 */
	public String getApCodeType() {
		return apCodeType;
	}


	/**
	 * [EnseignantChargePedagogiqueDto.apCodeType] Setter 
	 * @author rlaib on : 26 oct. 2014  11:59:34
	 * @param apCodeType the apCodeType to set
	 */
	public void setApCodeType(String apCodeType) {
		this.apCodeType = apCodeType;
	}


	/**
	 * [EnseignantChargePedagogiqueDto.apLibelle] Getter 
	 * @author rlaib on : 26 oct. 2014  11:59:34
	 * @return the apLibelle
	 */
	public String getApLibelle() {
		return apLibelle;
	}


	/**
	 * [EnseignantChargePedagogiqueDto.apLibelle] Setter 
	 * @author rlaib on : 26 oct. 2014  11:59:34
	 * @param apLibelle the apLibelle to set
	 */
	public void setApLibelle(String apLibelle) {
		this.apLibelle = apLibelle;
	}


	/**
	 * [EnseignantChargePedagogiqueDto.typeEnseignement] Getter 
	 * @author rlaib on : 27 oct. 2014  09:14:37
	 * @return the typeEnseignement
	 */
	public String getTypeEnseignement() {
		return typeEnseignement;
	}


	/**
	 * [EnseignantChargePedagogiqueDto.typeEnseignement] Setter 
	 * @author rlaib on : 27 oct. 2014  09:14:37
	 * @param typeEnseignement the typeEnseignement to set
	 */
	public void setTypeEnseignement(String typeEnseignement) {
		this.typeEnseignement = typeEnseignement;
	}


	/**
	 * [EnseignantChargePedagogiqueDto.niveauId] Getter 
	 * @author rlaib on : 26 oct. 2014  14:57:02
	 * @return the niveauId
	 */
	public Integer getNiveauId() {
		return niveauId;
	}


	/**
	 * [EnseignantChargePedagogiqueDto.niveauId] Setter 
	 * @author rlaib on : 26 oct. 2014  14:57:02
	 * @param niveauId the niveauId to set
	 */
	public void setNiveauId(Integer niveauId) {
		this.niveauId = niveauId;
	}


	/**
	 * [EnseignantChargePedagogiqueDto.niveauCode] Getter 
	 * @author rlaib on : 26 oct. 2014  14:57:02
	 * @return the niveauCode
	 */
	public String getNiveauCode() {
		return niveauCode;
	}


	/**
	 * [EnseignantChargePedagogiqueDto.niveauCode] Setter 
	 * @author rlaib on : 26 oct. 2014  14:57:02
	 * @param niveauCode the niveauCode to set
	 */
	public void setNiveauCode(String niveauCode) {
		this.niveauCode = niveauCode;
	}


	/**
	 * [EnseignantChargePedagogiqueDto.niveauLibelle] Getter 
	 * @author rlaib on : 26 oct. 2014  14:57:02
	 * @return the niveauLibelle
	 */
	public String getNiveauLibelle() {
		return niveauLibelle;
	}


	/**
	 * [EnseignantChargePedagogiqueDto.niveauLibelle] Setter 
	 * @author rlaib on : 26 oct. 2014  14:57:02
	 * @param niveauLibelle the niveauLibelle to set
	 */
	public void setNiveauLibelle(String niveauLibelle) {
		this.niveauLibelle = niveauLibelle;
	}



	/**
	 * [EnseignantChargePedagogiqueDto.incluse] Getter 
	 * @author rlaib on : 28 oct. 2014  17:17:37
	 * @return the incluse
	 */
	public boolean isIncluse() {
		return incluse;
	}


	/**
	 * [EnseignantChargePedagogiqueDto.incluse] Setter 
	 * @author rlaib on : 28 oct. 2014  17:17:37
	 * @param incluse the incluse to set
	 */
	public void setIncluse(boolean incluse) {
		this.incluse = incluse;
	}


	/**
	 * [EnseignantChargePedagogiqueDto.offreFormationId] Getter 
	 * @author rlaib on : 4 nov. 2014  13:28:14
	 * @return the offreFormationId
	 */
	public Integer getOffreFormationId() {
		return offreFormationId;
	}


	/**
	 * [EnseignantChargePedagogiqueDto.offreFormationId] Setter 
	 * @author rlaib on : 4 nov. 2014  13:28:14
	 * @param offreFormationId the offreFormationId to set
	 */
	public void setOffreFormationId(Integer offreFormationId) {
		this.offreFormationId = offreFormationId;
	}


	/**
	 * [EnseignantChargePedagogiqueDto.offreFormationCode] Getter 
	 * @author rlaib on : 4 nov. 2014  13:28:14
	 * @return the offreFormationCode
	 */
	public String getOffreFormationCode() {
		return offreFormationCode;
	}


	/**
	 * [EnseignantChargePedagogiqueDto.offreFormationCode] Setter 
	 * @author rlaib on : 4 nov. 2014  13:28:14
	 * @param offreFormationCode the offreFormationCode to set
	 */
	public void setOffreFormationCode(String offreFormationCode) {
		this.offreFormationCode = offreFormationCode;
	}


	/**
	 * [EnseignantChargePedagogiqueDto.offreFormationLibelle] Getter 
	 * @author rlaib on : 4 nov. 2014  13:28:14
	 * @return the offreFormationLibelle
	 */
	public String getOffreFormationLibelle() {
		return offreFormationLibelle;
	}


	/**
	 * [EnseignantChargePedagogiqueDto.offreFormationLibelle] Setter 
	 * @author rlaib on : 4 nov. 2014  13:28:14
	 * @param offreFormationLibelle the offreFormationLibelle to set
	 */
	public void setOffreFormationLibelle(String offreFormationLibelle) {
		this.offreFormationLibelle = offreFormationLibelle;
	}


	/**
	 * [EnseignantChargePedagogiqueDto.idEtablissement] Getter 
	 * @author Rafik on : 5 déc. 2014  13:01:37
	 * @return the idEtablissement
	 */
	public Integer getIdEtablissement() {
		return idEtablissement;
	}


	/**
	 * [EnseignantChargePedagogiqueDto.idEtablissement] Setter 
	 * @author Rafik on : 5 déc. 2014  13:01:37
	 * @param idEtablissement the idEtablissement to set
	 */
	public void setIdEtablissement(Integer idEtablissement) {
		this.idEtablissement = idEtablissement;
	}


	/**
	 * [EnseignantChargePedagogiqueDto.codeEtablissement] Getter 
	 * @author Rafik on : 5 déc. 2014  13:01:37
	 * @return the codeEtablissement
	 */
	public String getCodeEtablissement() {
		return codeEtablissement;
	}


	/**
	 * [EnseignantChargePedagogiqueDto.codeEtablissement] Setter 
	 * @author Rafik on : 5 déc. 2014  13:01:37
	 * @param codeEtablissement the codeEtablissement to set
	 */
	public void setCodeEtablissement(String codeEtablissement) {
		this.codeEtablissement = codeEtablissement;
	}


	/**
	 * [EnseignantChargePedagogiqueDto.libelleEtablissement] Getter 
	 * @author Rafik on : 5 déc. 2014  13:01:37
	 * @return the libelleEtablissement
	 */
	public String getLibelleEtablissement() {
		return libelleEtablissement;
	}


	/**
	 * [EnseignantChargePedagogiqueDto.libelleEtablissement] Setter 
	 * @author Rafik on : 5 déc. 2014  13:01:37
	 * @param libelleEtablissement the libelleEtablissement to set
	 */
	public void setLibelleEtablissement(String libelleEtablissement) {
		this.libelleEtablissement = libelleEtablissement;
	}


	/**
	 * [EnseignantChargePedagogiqueDto.idPeriode] Getter 
	 * @author Rafik on : 8 déc. 2014  21:47:23
	 * @return the idPeriode
	 */
	public int getIdPeriode() {
		return idPeriode;
	}


	/**
	 * [EnseignantChargePedagogiqueDto.idPeriode] Setter 
	 * @author Rafik on : 8 déc. 2014  21:47:23
	 * @param idPeriode the idPeriode to set
	 */
	public void setIdPeriode(int idPeriode) {
		this.idPeriode = idPeriode;
	}


	/**
	 * [EnseignantChargePedagogiqueDto.codePeriode] Getter 
	 * @author Rafik on : 8 déc. 2014  21:47:23
	 * @return the codePeriode
	 */
	public String getCodePeriode() {
		return codePeriode;
	}


	/**
	 * [EnseignantChargePedagogiqueDto.codePeriode] Setter 
	 * @author Rafik on : 8 déc. 2014  21:47:23
	 * @param codePeriode the codePeriode to set
	 */
	public void setCodePeriode(String codePeriode) {
		this.codePeriode = codePeriode;
	}


	/**
	 * [EnseignantChargePedagogiqueDto.libellePeriode] Getter 
	 * @author Rafik on : 8 déc. 2014  21:47:23
	 * @return the libellePeriode
	 */
	public String getLibellePeriode() {
		return libellePeriode;
	}


	/**
	 * [EnseignantChargePedagogiqueDto.libellePeriode] Setter 
	 * @author Rafik on : 8 déc. 2014  21:47:23
	 * @param libellePeriode the libellePeriode to set
	 */
	public void setLibellePeriode(String libellePeriode) {
		this.libellePeriode = libellePeriode;
	}

	

}
