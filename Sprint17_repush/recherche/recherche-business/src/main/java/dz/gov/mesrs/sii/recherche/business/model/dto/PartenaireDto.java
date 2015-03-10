/**
 * [dz.gov.mesrs.sii.recherche.business.model.dto.StructureDto.java] 
 * @author rlaib on : 16 déc. 2014  13:24:10
 */
package dz.gov.mesrs.sii.recherche.business.model.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.recherche.ProjetPartenaire;

/**
 * @author rlaib  on : 16 déc. 2014  13:24:10
 */
public class PartenaireDto implements Serializable {
	
	/**
	 * serialVersionUID 
	 * @author rlaib  on : 28 janv. 2015  10:54:59
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Date dateDebut;
	private Date dateFin;
	private String description;
	private List<ProjetPartenaireDto> projetPartenairesDto;
	
	// Structure de recherche
	private Long structureId;
	private String structureLibelleLatin;
	
	// Partenaire
	private Integer partenaireId;
	
	// Contrat 
	private Integer contratId;
	private String contratObjet;
	private String contratReference;
	private Date contratDateDebutValidite;
	private Date contratDateFinValidite;
	
	// Partenaire Individu
	private Integer partenaireIndividuId;
	private String partenaireIndividuNomLatin;
	private String partenaireIndividuPrenomLatin;
	private String partenaireIndividuNomArabe;
	private String partenaireIndividuPrenomArabe;
	
	// Partenaire Structure
	private Integer partenaireStructureId;
	private String partenaireStructureDesignationLatin;
	private String partenaireStructureDesignationArabe;
	
	// Partenaire Groupe
	private Integer partenaireGroupeId;
	private String partenaireGroupeDesignationLatin;
	private String partenaireGroupeDesignationArabe;
	
	public PartenaireDto() {
	}
	

	/**
	 * [PartenaireDto.id] Getter 
	 * @author rlaib on : 16 déc. 2014  13:40:28
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * [PartenaireDto.id] Setter 
	 * @author rlaib on : 16 déc. 2014  13:40:28
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * [PartenaireDto.dateDebut] Getter 
	 * @author rlaib on : 16 déc. 2014  13:40:28
	 * @return the dateDebut
	 */
	public Date getDateDebut() {
		return dateDebut;
	}
	
	/**
	 * [PartenaireDto.dateDebut] Setter 
	 * @author rlaib on : 16 déc. 2014  13:40:28
	 * @param dateDebut the dateDebut to set
	 */
	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}
	
	/**
	 * [PartenaireDto.dateFin] Getter 
	 * @author rlaib on : 16 déc. 2014  13:40:28
	 * @return the dateFin
	 */
	public Date getDateFin() {
		return dateFin;
	}
	
	/**
	 * [PartenaireDto.dateFin] Setter 
	 * @author rlaib on : 16 déc. 2014  13:40:28
	 * @param dateFin the dateFin to set
	 */
	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}
	
	/**
	 * [PartenaireDto.description] Getter 
	 * @author rlaib on : 16 déc. 2014  13:40:28
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * [PartenaireDto.description] Setter 
	 * @author rlaib on : 16 déc. 2014  13:40:28
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * [PartenaireDto.structureId] Getter 
	 * @author rlaib on : 16 déc. 2014  13:40:28
	 * @return the structureId
	 */
	public Long getStructureId() {
		return structureId;
	}
	
	/**
	 * [PartenaireDto.structureId] Setter 
	 * @author rlaib on : 16 déc. 2014  13:40:28
	 * @param structureId the structureId to set
	 */
	public void setStructureId(Long structureId) {
		this.structureId = structureId;
	}
	
	
	/**
	 * [PartenaireDto.structureLibelleLatin] Getter 
	 * @author rlaib on : 24 déc. 2014  09:10:24
	 * @return the structureLibelleLatin
	 */
	public String getStructureLibelleLatin() {
		return structureLibelleLatin;
	}


	/**
	 * [PartenaireDto.structureLibelleLatin] Setter 
	 * @author rlaib on : 24 déc. 2014  09:10:24
	 * @param structureLibelleLatin the structureLibelleLatin to set
	 */
	public void setStructureLibelleLatin(String structureLibelleLatin) {
		this.structureLibelleLatin = structureLibelleLatin;
	}


	/**
	 * [PartenaireDto.contratId] Getter 
	 * @author rlaib on : 16 déc. 2014  13:40:28
	 * @return the contratId
	 */
	public Integer getContratId() {
		return contratId;
	}
	
	/**
	 * [PartenaireDto.contratId] Setter 
	 * @author rlaib on : 16 déc. 2014  13:40:28
	 * @param contratId the contratId to set
	 */
	public void setContratId(Integer contratId) {
		this.contratId = contratId;
	}
	
	/**
	 * [PartenaireDto.contratObjet] Getter 
	 * @author rlaib on : 24 déc. 2014  09:11:29
	 * @return the contratObjet
	 */
	public String getContratObjet() {
		return contratObjet;
	}


	/**
	 * [PartenaireDto.contratObjet] Setter 
	 * @author rlaib on : 24 déc. 2014  09:11:29
	 * @param contratObjet the contratObjet to set
	 */
	public void setContratObjet(String contratObjet) {
		this.contratObjet = contratObjet;
	}


	/**
	 * [PartenaireDto.contratReference] Getter 
	 * @author rlaib on : 24 déc. 2014  08:42:00
	 * @return the contratReference
	 */
	public String getContratReference() {
		return contratReference;
	}

	/**
	 * [PartenaireDto.contratReference] Setter 
	 * @author rlaib on : 24 déc. 2014  08:42:00
	 * @param contratReference the contratReference to set
	 */
	public void setContratReference(String contratReference) {
		this.contratReference = contratReference;
	}

	/**
	 * [PartenaireDto.contratDateDebutValidite] Getter 
	 * @author rlaib on : 24 déc. 2014  08:42:00
	 * @return the contratDateDebutValidite
	 */
	public Date getContratDateDebutValidite() {
		return contratDateDebutValidite;
	}

	/**
	 * [PartenaireDto.contratDateDebutValidite] Setter 
	 * @author rlaib on : 24 déc. 2014  08:42:00
	 * @param contratDateDebutValidite the contratDateDebutValidite to set
	 */
	public void setContratDateDebutValidite(Date contratDateDebutValidite) {
		this.contratDateDebutValidite = contratDateDebutValidite;
	}

	/**
	 * [PartenaireDto.contratDateFinValidite] Getter 
	 * @author rlaib on : 24 déc. 2014  08:42:00
	 * @return the contratDateFinValidite
	 */
	public Date getContratDateFinValidite() {
		return contratDateFinValidite;
	}

	/**
	 * [PartenaireDto.contratDateFinValidite] Setter 
	 * @author rlaib on : 24 déc. 2014  08:42:00
	 * @param contratDateFinValidite the contratDateFinValidite to set
	 */
	public void setContratDateFinValidite(Date contratDateFinValidite) {
		this.contratDateFinValidite = contratDateFinValidite;
	}


	/**
	 * [PartenaireDto.partenaireIndividuId] Getter 
	 * @author rlaib on : 24 déc. 2014  09:20:42
	 * @return the partenaireIndividuId
	 */
	public Integer getPartenaireIndividuId() {
		return partenaireIndividuId;
	}


	/**
	 * [PartenaireDto.partenaireIndividuId] Setter 
	 * @author rlaib on : 24 déc. 2014  09:20:42
	 * @param partenaireIndividuId the partenaireIndividuId to set
	 */
	public void setPartenaireIndividuId(Integer partenaireIndividuId) {
		this.partenaireIndividuId = partenaireIndividuId;
	}


	/**
	 * [PartenaireDto.partenaireIndividuNomLatin] Getter 
	 * @author rlaib on : 24 déc. 2014  09:19:23
	 * @return the partenaireIndividuNomLatin
	 */
	public String getPartenaireIndividuNomLatin() {
		return partenaireIndividuNomLatin;
	}


	/**
	 * [PartenaireDto.partenaireIndividuNomLatin] Setter 
	 * @author rlaib on : 24 déc. 2014  09:19:23
	 * @param partenaireIndividuNomLatin the partenaireIndividuNomLatin to set
	 */
	public void setPartenaireIndividuNomLatin(String partenaireIndividuNomLatin) {
		this.partenaireIndividuNomLatin = partenaireIndividuNomLatin;
	}


	/**
	 * [PartenaireDto.partenaireIndividuPrenomLatin] Getter 
	 * @author rlaib on : 24 déc. 2014  09:19:23
	 * @return the partenaireIndividuPrenomLatin
	 */
	public String getPartenaireIndividuPrenomLatin() {
		return partenaireIndividuPrenomLatin;
	}


	/**
	 * [PartenaireDto.partenaireIndividuPrenomLatin] Setter 
	 * @author rlaib on : 24 déc. 2014  09:19:23
	 * @param partenaireIndividuPrenomLatin the partenaireIndividuPrenomLatin to set
	 */
	public void setPartenaireIndividuPrenomLatin(
			String partenaireIndividuPrenomLatin) {
		this.partenaireIndividuPrenomLatin = partenaireIndividuPrenomLatin;
	}


	/**
	 * [PartenaireDto.partenaireIndividuNomArabe] Getter 
	 * @author rlaib on : 24 déc. 2014  09:19:23
	 * @return the partenaireIndividuNomArabe
	 */
	public String getPartenaireIndividuNomArabe() {
		return partenaireIndividuNomArabe;
	}


	/**
	 * [PartenaireDto.partenaireIndividuNomArabe] Setter 
	 * @author rlaib on : 24 déc. 2014  09:19:23
	 * @param partenaireIndividuNomArabe the partenaireIndividuNomArabe to set
	 */
	public void setPartenaireIndividuNomArabe(String partenaireIndividuNomArabe) {
		this.partenaireIndividuNomArabe = partenaireIndividuNomArabe;
	}


	/**
	 * [PartenaireDto.partenaireIndividuPrenomArabe] Getter 
	 * @author rlaib on : 24 déc. 2014  09:19:23
	 * @return the partenaireIndividuPrenomArabe
	 */
	public String getPartenaireIndividuPrenomArabe() {
		return partenaireIndividuPrenomArabe;
	}


	/**
	 * [PartenaireDto.partenaireIndividuPrenomArabe] Setter 
	 * @author rlaib on : 24 déc. 2014  09:19:23
	 * @param partenaireIndividuPrenomArabe the partenaireIndividuPrenomArabe to set
	 */
	public void setPartenaireIndividuPrenomArabe(
			String partenaireIndividuPrenomArabe) {
		this.partenaireIndividuPrenomArabe = partenaireIndividuPrenomArabe;
	}


	/**
	 * [PartenaireDto.partenaireStructureId] Getter 
	 * @author rlaib on : 24 déc. 2014  09:19:23
	 * @return the partenaireStructureId
	 */
	public Integer getPartenaireStructureId() {
		return partenaireStructureId;
	}


	/**
	 * [PartenaireDto.partenaireStructureId] Setter 
	 * @author rlaib on : 24 déc. 2014  09:19:23
	 * @param partenaireStructureId the partenaireStructureId to set
	 */
	public void setPartenaireStructureId(Integer partenaireStructureId) {
		this.partenaireStructureId = partenaireStructureId;
	}


	/**
	 * [PartenaireDto.partenaireStructureDesignationLatin] Getter 
	 * @author rlaib on : 24 déc. 2014  09:19:23
	 * @return the partenaireStructureDesignationLatin
	 */
	public String getPartenaireStructureDesignationLatin() {
		return partenaireStructureDesignationLatin;
	}


	/**
	 * [PartenaireDto.partenaireStructureDesignationLatin] Setter 
	 * @author rlaib on : 24 déc. 2014  09:19:23
	 * @param partenaireStructureDesignationLatin the partenaireStructureDesignationLatin to set
	 */
	public void setPartenaireStructureDesignationLatin(
			String partenaireStructureDesignationLatin) {
		this.partenaireStructureDesignationLatin = partenaireStructureDesignationLatin;
	}


	/**
	 * [PartenaireDto.partenaireStructureDesignationArabe] Getter 
	 * @author rlaib on : 24 déc. 2014  09:19:23
	 * @return the partenaireStructureDesignationArabe
	 */
	public String getPartenaireStructureDesignationArabe() {
		return partenaireStructureDesignationArabe;
	}


	/**
	 * [PartenaireDto.partenaireStructureDesignationArabe] Setter 
	 * @author rlaib on : 24 déc. 2014  09:19:23
	 * @param partenaireStructureDesignationArabe the partenaireStructureDesignationArabe to set
	 */
	public void setPartenaireStructureDesignationArabe(
			String partenaireStructureDesignationArabe) {
		this.partenaireStructureDesignationArabe = partenaireStructureDesignationArabe;
	}


	/**
	 * [PartenaireDto.partenaireGroupeId] Getter 
	 * @author rlaib on : 24 déc. 2014  09:19:23
	 * @return the partenaireGroupeId
	 */
	public Integer getPartenaireGroupeId() {
		return partenaireGroupeId;
	}


	/**
	 * [PartenaireDto.partenaireGroupeId] Setter 
	 * @author rlaib on : 24 déc. 2014  09:19:23
	 * @param partenaireGroupeId the partenaireGroupeId to set
	 */
	public void setPartenaireGroupeId(Integer partenaireGroupeId) {
		this.partenaireGroupeId = partenaireGroupeId;
	}


	/**
	 * [PartenaireDto.partenaireGroupeDesignationLatin] Getter 
	 * @author rlaib on : 24 déc. 2014  09:19:23
	 * @return the partenaireGroupeDesignationLatin
	 */
	public String getPartenaireGroupeDesignationLatin() {
		return partenaireGroupeDesignationLatin;
	}


	/**
	 * [PartenaireDto.partenaireGroupeDesignationLatin] Setter 
	 * @author rlaib on : 24 déc. 2014  09:19:23
	 * @param partenaireGroupeDesignationLatin the partenaireGroupeDesignationLatin to set
	 */
	public void setPartenaireGroupeDesignationLatin(
			String partenaireGroupeDesignationLatin) {
		this.partenaireGroupeDesignationLatin = partenaireGroupeDesignationLatin;
	}


	/**
	 * [PartenaireDto.partenaireGroupeDesignationArabe] Getter 
	 * @author rlaib on : 24 déc. 2014  09:19:23
	 * @return the partenaireGroupeDesignationArabe
	 */
	public String getPartenaireGroupeDesignationArabe() {
		return partenaireGroupeDesignationArabe;
	}


	/**
	 * [PartenaireDto.partenaireGroupeDesignationArabe] Setter 
	 * @author rlaib on : 24 déc. 2014  09:19:23
	 * @param partenaireGroupeDesignationArabe the partenaireGroupeDesignationArabe to set
	 */
	public void setPartenaireGroupeDesignationArabe(
			String partenaireGroupeDesignationArabe) {
		this.partenaireGroupeDesignationArabe = partenaireGroupeDesignationArabe;
	}

	/**
	 * [PartenaireDto.partenaireId] Getter 
	 * @author rlaib on : 24 déc. 2014  10:22:26
	 * @return the partenaireId
	 */
	public Integer getPartenaireId() {
		return partenaireId;
	}


	/**
	 * [PartenaireDto.partenaireId] Setter 
	 * @author rlaib on : 24 déc. 2014  10:22:26
	 * @param partenaireId the partenaireId to set
	 */
	public void setPartenaireId(Integer partenaireId) {
		this.partenaireId = partenaireId;
	}


	public List<ProjetPartenaireDto> getProjetPartenairesDto() {
		return projetPartenairesDto;
	}


	public void setProjetPartenairesDto(
			List<ProjetPartenaireDto> projetPartenairesDto) {
		this.projetPartenairesDto = projetPartenairesDto;
	}


	/**
	 * [dz.gov.mesrs.sii.recherche.business.model.dto.PartenaireDto.toString] Method 
	 * Overridden By : @author rlaib  on : 24 déc. 2014  09:20:07
	 * @return
	 */
	@Override
	public String toString() {
		return "PartenaireDto [id=" + id + ", structureLibelleLatin="
				+ structureLibelleLatin + ", contratObjet=" + contratObjet
				+ ", partenaireId=" + partenaireIndividuId
				+ ", partenaireIndividuNomLatin=" + partenaireIndividuNomLatin
				+ ", partenaireIndividuPrenomLatin="
				+ partenaireIndividuPrenomLatin
				+ ", partenaireStructureDesignationLatin="
				+ partenaireStructureDesignationLatin
				+ ", partenaireGroupeDesignationLatin="
				+ partenaireGroupeDesignationLatin + "]";
	}
	
	
	
}
