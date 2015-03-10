/**
 * [dz.gov.mesrs.sii.fve.business.model.dto.scolarite.EnseignantVoeuLigneDto.java] 
 * @author rlaib on : 12 oct. 2014  15:06:05
 */
package dz.gov.mesrs.sii.fve.business.model.dto.scolarite;


/**
 * @author rlaib  on : 12 oct. 2014  15:06:05
 */
public class EnseignantVoeuLigneDto implements java.io.Serializable {

	/**
	 * serialVersionUID 
	 * @author rlaib  on : 29 oct. 2014  16:09:33
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int nbAnneeActivite;
	private String observation;
	private Boolean estValidee = false;

	private int order;
	// Enseignant voeu
	private int idEnseignantVoeu;

	// Niveau
	private int idNiveau;
	private String codeNiveau;
	private String libelleNiveau;

	// Periode
	private int idPeriode;
	private String codePeriode;
	private String libellePeriode;
	
	// MC
	private int idRattachementMc;
	private String libelleMc;

	// Type AP
	private int idAp;
	private String codeTypeAp;
	private String codeAp;
	private String typeEnseignement;

	// Priorite
	private int idPriorite;
	private boolean editable;
	
	// Offre de formation
	private Integer idOffreFormation;
	private String codeOffreFormation;
	private String libelleOffreFormation;
	
	// Etablissement
	private Integer idEtablissement;
	private String codeEtablissement;
	private String libelleEtablissement;
	
	// Special Manage 
	private int indexVoeu;
	
	public EnseignantVoeuLigneDto() {

	}

	/**
	 * [EnseignantVoeuLigneDto.id] Getter 
	 * @author rlaib on : 12 oct. 2014  15:19:23
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * [EnseignantVoeuLigneDto.id] Setter 
	 * @author rlaib on : 12 oct. 2014  15:19:23
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * [EnseignantVoeuLigneDto.nbAnneeActivite] Getter 
	 * @author rlaib on : 12 oct. 2014  15:19:23
	 * @return the nbAnneeActivite
	 */
	public int getNbAnneeActivite() {
		return nbAnneeActivite;
	}

	/**
	 * [EnseignantVoeuLigneDto.nbAnneeActivite] Setter 
	 * @author rlaib on : 12 oct. 2014  15:19:23
	 * @param nbAnneeActivite the nbAnneeActivite to set
	 */
	public void setNbAnneeActivite(int nbAnneeActivite) {
		this.nbAnneeActivite = nbAnneeActivite;
	}

	/**
	 * [EnseignantVoeuLigneDto.abservation] Getter 
	 * @author rlaib on : 12 oct. 2014  15:19:23
	 * @return the abservation
	 */
	public String getObservation() {
		return observation;
	}

	/**
	 * [EnseignantVoeuLigneDto.abservation] Setter 
	 * @author rlaib on : 12 oct. 2014  15:19:23
	 * @param abservation the abservation to set
	 */
	public void setObservation(String observation) {
		this.observation = observation;
	}
	
	/**
	 * [EnseignantVoeuLigneDto.estValidee] Getter 
	 * @author rlaib on : 26 oct. 2014  17:09:04
	 * @return the estValidee
	 */
	public Boolean getEstValidee() {
		return estValidee;
	}

	/**
	 * [EnseignantVoeuLigneDto.estValidee] Setter 
	 * @author rlaib on : 26 oct. 2014  17:09:04
	 * @param estValidee the estValidee to set
	 */
	public void setEstValidee(Boolean estValidee) {
		this.estValidee = estValidee;
	}

	/**
	 * [EnseignantVoeuLigneDto.idEnseignantVoeu] Getter 
	 * @author rlaib on : 12 oct. 2014  15:19:23
	 * @return the idEnseignantVoeu
	 */
	public int getIdEnseignantVoeu() {
		return idEnseignantVoeu;
	}

	/**
	 * [EnseignantVoeuLigneDto.idEnseignantVoeu] Setter 
	 * @author rlaib on : 12 oct. 2014  15:19:23
	 * @param idEnseignantVoeu the idEnseignantVoeu to set
	 */
	public void setIdEnseignantVoeu(int idEnseignantVoeu) {
		this.idEnseignantVoeu = idEnseignantVoeu;
	}

	/**
	 * [EnseignantVoeuLigneDto.idNiveau] Getter 
	 * @author rlaib on : 12 oct. 2014  15:19:23
	 * @return the idNiveau
	 */
	public int getIdNiveau() {
		return idNiveau;
	}

	/**
	 * [EnseignantVoeuLigneDto.idNiveau] Setter 
	 * @author rlaib on : 12 oct. 2014  15:19:23
	 * @param idNiveau the idNiveau to set
	 */
	public void setIdNiveau(int idNiveau) {
		this.idNiveau = idNiveau;
	}

	/**
	 * [EnseignantVoeuLigneDto.codeNiveau] Getter 
	 * @author rlaib on : 12 oct. 2014  15:19:23
	 * @return the codeNiveau
	 */
	public String getCodeNiveau() {
		return codeNiveau;
	}

	/**
	 * [EnseignantVoeuLigneDto.codeNiveau] Setter 
	 * @author rlaib on : 12 oct. 2014  15:19:23
	 * @param codeNiveau the codeNiveau to set
	 */
	public void setCodeNiveau(String codeNiveau) {
		this.codeNiveau = codeNiveau;
	}

	/**
	 * [EnseignantVoeuLigneDto.libelleNiveau] Getter 
	 * @author rlaib on : 12 oct. 2014  15:19:23
	 * @return the libelleNiveau
	 */
	public String getLibelleNiveau() {
		return libelleNiveau;
	}

	/**
	 * [EnseignantVoeuLigneDto.libelleNiveau] Setter 
	 * @author rlaib on : 12 oct. 2014  15:19:23
	 * @param libelleNiveau the libelleNiveau to set
	 */
	public void setLibelleNiveau(String libelleNiveau) {
		this.libelleNiveau = libelleNiveau;
	}

	/**
	 * [EnseignantVoeuLigneDto.idRattachementMc] Getter 
	 * @author rlaib on : 12 oct. 2014  15:19:23
	 * @return the idRattachementMc
	 */
	public int getIdRattachementMc() {
		return idRattachementMc;
	}

	/**
	 * [EnseignantVoeuLigneDto.idRattachementMc] Setter 
	 * @author rlaib on : 12 oct. 2014  15:19:23
	 * @param idRattachementMc the idRattachementMc to set
	 */
	public void setIdRattachementMc(int idRattachementMc) {
		this.idRattachementMc = idRattachementMc;
	}

	/**
	 * [EnseignantVoeuLigneDto.libelleMc] Getter 
	 * @author rlaib on : 16 oct. 2014  17:06:19
	 * @return the libelleMc
	 */
	public String getLibelleMc() {
		return libelleMc;
	}

	/**
	 * [EnseignantVoeuLigneDto.libelleMc] Setter 
	 * @author rlaib on : 16 oct. 2014  17:06:19
	 * @param libelleMc the libelleMc to set
	 */
	public void setLibelleMc(String libelleMc) {
		this.libelleMc = libelleMc;
	}

	/**
	 * [EnseignantVoeuLigneDto.codeTypeAp] Getter 
	 * @author rlaib on : 12 oct. 2014  15:19:23
	 * @return the codeTypeAp
	 */
	public String getCodeTypeAp() {
		return codeTypeAp;
	}

	/**
	 * [EnseignantVoeuLigneDto.codeTypeAp] Setter 
	 * @author rlaib on : 12 oct. 2014  15:19:23
	 * @param codeTypeAp the codeTypeAp to set
	 */
	public void setCodeTypeAp(String codeTypeAp) {
		this.codeTypeAp = codeTypeAp;
	}

	/**
	 * [EnseignantVoeuLigneDto.idPriorite] Getter 
	 * @author rlaib on : 15 oct. 2014  15:57:03
	 * @return the idPriorite
	 */
	public int getIdPriorite() {
		return idPriorite;
	}

	/**
	 * [EnseignantVoeuLigneDto.idPriorite] Setter 
	 * @author rlaib on : 15 oct. 2014  15:57:03
	 * @param idPriorite the idPriorite to set
	 */
	public void setIdPriorite(int idPriorite) {
		this.idPriorite = idPriorite;
	}

	/**
	 * [EnseignantVoeuLigneDto.editable] Getter 
	 * @author rlaib on : 16 oct. 2014  09:02:03
	 * @return the editable
	 */
	public boolean isEditable() {
		return editable;
	}

	/**
	 * [EnseignantVoeuLigneDto.editable] Setter 
	 * @author rlaib on : 16 oct. 2014  09:02:03
	 * @param editable the editable to set
	 */
	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	/**
	 * [EnseignantVoeuLigneDto.idAp] Getter 
	 * @author rlaib on : 16 oct. 2014  12:29:10
	 * @return the idAp
	 */
	public int getIdAp() {
		return idAp;
	}

	/**
	 * [EnseignantVoeuLigneDto.idAp] Setter 
	 * @author rlaib on : 16 oct. 2014  12:29:10
	 * @param idAp the idAp to set
	 */
	public void setIdAp(int idAp) {
		this.idAp = idAp;
	}

	/**
	 * [EnseignantVoeuLigneDto.codeAp] Getter 
	 * @author rlaib on : 16 oct. 2014  12:29:10
	 * @return the codeAp
	 */
	public String getCodeAp() {
		return codeAp;
	}

	/**
	 * [EnseignantVoeuLigneDto.codeAp] Setter 
	 * @author rlaib on : 16 oct. 2014  12:29:10
	 * @param codeAp the codeAp to set
	 */
	public void setCodeAp(String codeAp) {
		this.codeAp = codeAp;
	}

	
	/**
	 * [EnseignantVoeuLigneDto.typeEnseignement] Getter 
	 * @author rlaib on : 27 oct. 2014  09:10:46
	 * @return the typeEnseignement
	 */
	public String getTypeEnseignement() {
		return typeEnseignement;
	}

	/**
	 * [EnseignantVoeuLigneDto.typeEnseignement] Setter 
	 * @author rlaib on : 27 oct. 2014  09:10:46
	 * @param typeEnseignement the typeEnseignement to set
	 */
	public void setTypeEnseignement(String typeEnseignement) {
		this.typeEnseignement = typeEnseignement;
	}

	/**
	 * [EnseignantVoeuLigneDto.indexVoeu] Getter 
	 * @author rlaib on : 23 oct. 2014  15:15:31
	 * @return the indexVoeu
	 */
	public int getIndexVoeu() {
		return indexVoeu;
	}

	/**
	 * [EnseignantVoeuLigneDto.indexVoeu] Setter 
	 * @author rlaib on : 23 oct. 2014  15:15:31
	 * @param indexVoeu the indexVoeu to set
	 */
	public void setIndexVoeu(int indexVoeu) {
		this.indexVoeu = indexVoeu;
	}

	/**
	 * [EnseignantVoeuLigneDto.order] Getter 
	 * @author rlaib on : 24 oct. 2014  10:39:37
	 * @return the order
	 */
	public int getOrder() {
		return order;
	}

	/**
	 * [EnseignantVoeuLigneDto.order] Setter 
	 * @author rlaib on : 24 oct. 2014  10:39:37
	 * @param order the order to set
	 */
	public void setOrder(int order) {
		this.order = order;
	}

	
	/**
	 * [EnseignantVoeuLigneDto.idOffreFormation] Getter 
	 * @author rlaib on : 4 nov. 2014  13:21:38
	 * @return the idOffreFormation
	 */
	public Integer getIdOffreFormation() {
		return idOffreFormation;
	}

	/**
	 * [EnseignantVoeuLigneDto.idOffreFormation] Setter 
	 * @author rlaib on : 4 nov. 2014  13:21:38
	 * @param idOffreFormation the idOffreFormation to set
	 */
	public void setIdOffreFormation(Integer idOffreFormation) {
		this.idOffreFormation = idOffreFormation;
	}

	/**
	 * [EnseignantVoeuLigneDto.codeOffreFormation] Getter 
	 * @author rlaib on : 4 nov. 2014  13:21:38
	 * @return the codeOffreFormation
	 */
	public String getCodeOffreFormation() {
		return codeOffreFormation;
	}

	/**
	 * [EnseignantVoeuLigneDto.codeOffreFormation] Setter 
	 * @author rlaib on : 4 nov. 2014  13:21:38
	 * @param codeOffreFormation the codeOffreFormation to set
	 */
	public void setCodeOffreFormation(String codeOffreFormation) {
		this.codeOffreFormation = codeOffreFormation;
	}

	/**
	 * [EnseignantVoeuLigneDto.libelleOffreFormation] Getter 
	 * @author rlaib on : 4 nov. 2014  13:21:38
	 * @return the libelleOffreFormation
	 */
	public String getLibelleOffreFormation() {
		return libelleOffreFormation;
	}

	/**
	 * [EnseignantVoeuLigneDto.libelleOffreFormation] Setter 
	 * @author rlaib on : 4 nov. 2014  13:21:38
	 * @param libelleOffreFormation the libelleOffreFormation to set
	 */
	public void setLibelleOffreFormation(String libelleOffreFormation) {
		this.libelleOffreFormation = libelleOffreFormation;
	}

	/**
	 * [EnseignantVoeuLigneDto.idEtablissement] Getter 
	 * @author Rafik on : 4 déc. 2014  20:29:13
	 * @return the idEtablissement
	 */
	public Integer getIdEtablissement() {
		return idEtablissement;
	}

	/**
	 * [EnseignantVoeuLigneDto.idEtablissement] Setter 
	 * @author Rafik on : 4 déc. 2014  20:29:13
	 * @param idEtablissement the idEtablissement to set
	 */
	public void setIdEtablissement(Integer idEtablissement) {
		this.idEtablissement = idEtablissement;
	}

	/**
	 * [EnseignantVoeuLigneDto.codeEtablissement] Getter 
	 * @author Rafik on : 4 déc. 2014  20:29:13
	 * @return the codeEtablissement
	 */
	public String getCodeEtablissement() {
		return codeEtablissement;
	}

	/**
	 * [EnseignantVoeuLigneDto.codeEtablissement] Setter 
	 * @author Rafik on : 4 déc. 2014  20:29:13
	 * @param codeEtablissement the codeEtablissement to set
	 */
	public void setCodeEtablissement(String codeEtablissement) {
		this.codeEtablissement = codeEtablissement;
	}

	/**
	 * [EnseignantVoeuLigneDto.libelleEtablissement] Getter 
	 * @author Rafik on : 4 déc. 2014  20:29:13
	 * @return the libelleEtablissement
	 */
	public String getLibelleEtablissement() {
		return libelleEtablissement;
	}

	/**
	 * [EnseignantVoeuLigneDto.libelleEtablissement] Setter 
	 * @author Rafik on : 4 déc. 2014  20:29:13
	 * @param libelleEtablissement the libelleEtablissement to set
	 */
	public void setLibelleEtablissement(String libelleEtablissement) {
		this.libelleEtablissement = libelleEtablissement;
	}

	/**
	 * [EnseignantVoeuLigneDto.serialversionuid] Getter 
	 * @author Rafik on : 4 déc. 2014  20:29:13
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * [dz.gov.mesrs.sii.fve.business.model.dto.scolarite.EnseignantVoeuLigneDto.hashCode] Method 
	 * @author rlaib  on : 4 nov. 2014  14:58:54
	 * @return
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idAp;
		result = prime * result + idNiveau;
		result = prime
				* result
				+ ((idOffreFormation == null) ? 0 : idOffreFormation.hashCode());
		result = prime * result + idRattachementMc;
		return result;
	}

	/**
	 * [dz.gov.mesrs.sii.fve.business.model.dto.scolarite.EnseignantVoeuLigneDto.equals] Method 
	 * @author rlaib  on : 4 nov. 2014  14:58:54
	 * @param obj
	 * @return
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EnseignantVoeuLigneDto other = (EnseignantVoeuLigneDto) obj;
		if (idAp != other.idAp)
			return false;
		if (idNiveau != other.idNiveau)
			return false;
		if (idOffreFormation == null) {
			if (other.idOffreFormation != null)
				return false;
		} else if (!idOffreFormation.equals(other.idOffreFormation))
			return false;
		if (idRattachementMc != other.idRattachementMc)
			return false;
		return true;
	}

	
	/**
	 * [EnseignantVoeuLigneDto.idPeriode] Getter 
	 * @author rlaib on : 7 d�c. 2014  14:07:33
	 * @return the idPeriode
	 */
	public int getIdPeriode() {
		return idPeriode;
	}

	/**
	 * [EnseignantVoeuLigneDto.idPeriode] Setter 
	 * @author rlaib on : 7 d�c. 2014  14:07:33
	 * @param idPeriode the idPeriode to set
	 */
	public void setIdPeriode(int idPeriode) {
		this.idPeriode = idPeriode;
	}

	/**
	 * [EnseignantVoeuLigneDto.codePeriode] Getter 
	 * @author rlaib on : 7 d�c. 2014  14:07:33
	 * @return the codePeriode
	 */
	public String getCodePeriode() {
		return codePeriode;
	}

	/**
	 * [EnseignantVoeuLigneDto.codePeriode] Setter 
	 * @author rlaib on : 7 d�c. 2014  14:07:33
	 * @param codePeriode the codePeriode to set
	 */
	public void setCodePeriode(String codePeriode) {
		this.codePeriode = codePeriode;
	}

	/**
	 * [EnseignantVoeuLigneDto.libellePeriode] Getter 
	 * @author rlaib on : 7 d�c. 2014  14:07:33
	 * @return the libellePeriode
	 */
	public String getLibellePeriode() {
		return libellePeriode;
	}

	/**
	 * [EnseignantVoeuLigneDto.libellePeriode] Setter 
	 * @author rlaib on : 7 d�c. 2014  14:07:33
	 * @param libellePeriode the libellePeriode to set
	 */
	public void setLibellePeriode(String libellePeriode) {
		this.libellePeriode = libellePeriode;
	}


}
