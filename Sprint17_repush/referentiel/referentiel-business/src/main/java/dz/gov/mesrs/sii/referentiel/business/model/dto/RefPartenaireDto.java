/**
 * [dz.gov.mesrs.sii.referentiel.business.model.dto.RefPartenaireDto.java] 
 * @author MAKERRI Sofiane on : 2 janv. 2014  12:02:19
 */
package dz.gov.mesrs.sii.referentiel.business.model.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author MAKERRI Sofiane  on : 2 janv. 2014  12:02:19
 */
@XmlRootElement(name = "RefPartenaireDto")
public class RefPartenaireDto {

	private Integer id;
	private Boolean contractant;
	private String reference; 
	private String observation;
	private Date dateSiganture;
	/***** Role ****/
	private Integer idRole;
	private String codeRole;
	private String llRoleFr;
	private String llRoleAr;
	/***** Contrat *****/
	private Integer idContrat;
	/***** Structure *****/
	private Integer idStructure;
	private String llStructureLatin;
	private String llStructureArabe;
	/***** Individu *****/
	private Integer idIndividu;
	private String nomLatin;
	private String prenomLatin;
	private String nomarabe;
	private String prenomArabe;
	/***** Groupe *****/
	private Integer idGroupe;
	private String llGroupe;
	private String llGroupeArabe;
	/**
	 * [RefPartenaireDto.id] Getter 
	 * @author MAKERRI Sofiane on : 2 janv. 2014  12:09:04
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * [RefPartenaireDto.id] Setter 
	 * @author MAKERRI Sofiane on : 2 janv. 2014  12:09:04
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * [RefPartenaireDto.contractant] Getter 
	 * @author MAKERRI Sofiane on : 2 janv. 2014  12:09:04
	 * @return the contractant
	 */
	public Boolean getContractant() {
		return contractant;
	}
	/**
	 * [RefPartenaireDto.contractant] Setter 
	 * @author MAKERRI Sofiane on : 2 janv. 2014  12:09:04
	 * @param contractant the contractant to set
	 */
	public void setContractant(Boolean contractant) {
		this.contractant = contractant;
	}
	/**
	 * [RefPartenaireDto.reference] Getter 
	 * @author MAKERRI Sofiane on : 2 janv. 2014  12:09:04
	 * @return the reference
	 */
	public String getReference() {
		return reference;
	}
	/**
	 * [RefPartenaireDto.reference] Setter 
	 * @author MAKERRI Sofiane on : 2 janv. 2014  12:09:04
	 * @param reference the reference to set
	 */
	public void setReference(String reference) {
		this.reference = reference;
	}
	/**
	 * [RefPartenaireDto.observation] Getter 
	 * @author MAKERRI Sofiane on : 2 janv. 2014  12:09:04
	 * @return the observation
	 */
	public String getObservation() {
		return observation;
	}
	/**
	 * [RefPartenaireDto.observation] Setter 
	 * @author MAKERRI Sofiane on : 2 janv. 2014  12:09:04
	 * @param observation the observation to set
	 */
	public void setObservation(String observation) {
		this.observation = observation;
	}
	/**
	 * [RefPartenaireDto.dateSiganture] Getter 
	 * @author MAKERRI Sofiane on : 2 janv. 2014  12:09:04
	 * @return the dateSiganture
	 */
	public Date getDateSiganture() {
		return dateSiganture;
	}
	/**
	 * [RefPartenaireDto.dateSiganture] Setter 
	 * @author MAKERRI Sofiane on : 2 janv. 2014  12:09:04
	 * @param dateSiganture the dateSiganture to set
	 */
	public void setDateSiganture(Date dateSiganture) {
		this.dateSiganture = dateSiganture;
	}
	
	/**
	 * [RefPartenaireDto.codeRole] Getter 
	 * @author MAKERRI Sofiane on : 6 janv. 2014  10:06:38
	 * @return the codeRole
	 */
	public String getCodeRole() {
		return codeRole;
	}
	/**
	 * [RefPartenaireDto.codeRole] Setter 
	 * @author MAKERRI Sofiane on : 6 janv. 2014  10:06:38
	 * @param codeRole the codeRole to set
	 */
	public void setCodeRole(String codeRole) {
		this.codeRole = codeRole;
	}
	/**
	 * [RefPartenaireDto.llRoleFr] Getter 
	 * @author MAKERRI Sofiane on : 2 janv. 2014  12:09:04
	 * @return the llRoleFr
	 */
	public String getLlRoleFr() {
		return llRoleFr;
	}
	/**
	 * [RefPartenaireDto.llRoleFr] Setter 
	 * @author MAKERRI Sofiane on : 2 janv. 2014  12:09:04
	 * @param llRoleFr the llRoleFr to set
	 */
	public void setLlRoleFr(String llRoleFr) {
		this.llRoleFr = llRoleFr;
	}
	/**
	 * [RefPartenaireDto.llRoleAr] Getter 
	 * @author MAKERRI Sofiane on : 2 janv. 2014  12:09:04
	 * @return the llRoleAr
	 */
	public String getLlRoleAr() {
		return llRoleAr;
	}
	/**
	 * [RefPartenaireDto.llRoleAr] Setter 
	 * @author MAKERRI Sofiane on : 2 janv. 2014  12:09:04
	 * @param llRoleAr the llRoleAr to set
	 */
	public void setLlRoleAr(String llRoleAr) {
		this.llRoleAr = llRoleAr;
	}
	/**
	 * [RefPartenaireDto.idContrat] Getter 
	 * @author MAKERRI Sofiane on : 2 janv. 2014  12:09:04
	 * @return the idContrat
	 */
	public Integer getIdContrat() {
		return idContrat;
	}
	/**
	 * [RefPartenaireDto.idContrat] Setter 
	 * @author MAKERRI Sofiane on : 2 janv. 2014  12:09:04
	 * @param idContrat the idContrat to set
	 */
	public void setIdContrat(Integer idContrat) {
		this.idContrat = idContrat;
	}
	/**
	 * [RefPartenaireDto.llStructureLatin] Getter 
	 * @author MAKERRI Sofiane on : 2 janv. 2014  12:09:04
	 * @return the llStructureLatin
	 */
	public String getLlStructureLatin() {
		return llStructureLatin;
	}
	/**
	 * [RefPartenaireDto.llStructureLatin] Setter 
	 * @author MAKERRI Sofiane on : 2 janv. 2014  12:09:04
	 * @param llStructureLatin the llStructureLatin to set
	 */
	public void setLlStructureLatin(String llStructureLatin) {
		this.llStructureLatin = llStructureLatin;
	}
	/**
	 * [RefPartenaireDto.llStructureArabe] Getter 
	 * @author MAKERRI Sofiane on : 2 janv. 2014  12:09:04
	 * @return the llStructureArabe
	 */
	public String getLlStructureArabe() {
		return llStructureArabe;
	}
	/**
	 * [RefPartenaireDto.llStructureArabe] Setter 
	 * @author MAKERRI Sofiane on : 2 janv. 2014  12:09:04
	 * @param llStructureArabe the llStructureArabe to set
	 */
	public void setLlStructureArabe(String llStructureArabe) {
		this.llStructureArabe = llStructureArabe;
	}
	/**
	 * [RefPartenaireDto.nomLatin] Getter 
	 * @author MAKERRI Sofiane on : 2 janv. 2014  12:09:04
	 * @return the nomLatin
	 */
	public String getNomLatin() {
		return nomLatin;
	}
	/**
	 * [RefPartenaireDto.nomLatin] Setter 
	 * @author MAKERRI Sofiane on : 2 janv. 2014  12:09:04
	 * @param nomLatin the nomLatin to set
	 */
	public void setNomLatin(String nomLatin) {
		this.nomLatin = nomLatin;
	}
	/**
	 * [RefPartenaireDto.prenomLatin] Getter 
	 * @author MAKERRI Sofiane on : 2 janv. 2014  12:09:04
	 * @return the prenomLatin
	 */
	public String getPrenomLatin() {
		return prenomLatin;
	}
	/**
	 * [RefPartenaireDto.prenomLatin] Setter 
	 * @author MAKERRI Sofiane on : 2 janv. 2014  12:09:04
	 * @param prenomLatin the prenomLatin to set
	 */
	public void setPrenomLatin(String prenomLatin) {
		this.prenomLatin = prenomLatin;
	}
	/**
	 * [RefPartenaireDto.nomarabe] Getter 
	 * @author MAKERRI Sofiane on : 2 janv. 2014  12:09:04
	 * @return the nomarabe
	 */
	public String getNomarabe() {
		return nomarabe;
	}
	/**
	 * [RefPartenaireDto.nomarabe] Setter 
	 * @author MAKERRI Sofiane on : 2 janv. 2014  12:09:04
	 * @param nomarabe the nomarabe to set
	 */
	public void setNomarabe(String nomarabe) {
		this.nomarabe = nomarabe;
	}
	/**
	 * [RefPartenaireDto.prenomArabe] Getter 
	 * @author MAKERRI Sofiane on : 2 janv. 2014  12:09:04
	 * @return the prenomArabe
	 */
	public String getPrenomArabe() {
		return prenomArabe;
	}
	/**
	 * [RefPartenaireDto.prenomArabe] Setter 
	 * @author MAKERRI Sofiane on : 2 janv. 2014  12:09:04
	 * @param prenomArabe the prenomArabe to set
	 */
	public void setPrenomArabe(String prenomArabe) {
		this.prenomArabe = prenomArabe;
	}
	/**
	 * [RefPartenaireDto.llGroupe] Getter 
	 * @author MAKERRI Sofiane on : 2 janv. 2014  12:09:04
	 * @return the llGroupe
	 */
	public String getLlGroupe() {
		return llGroupe;
	}
	/**
	 * [RefPartenaireDto.llGroupe] Setter 
	 * @author MAKERRI Sofiane on : 2 janv. 2014  12:09:04
	 * @param llGroupe the llGroupe to set
	 */
	public void setLlGroupe(String llGroupe) {
		this.llGroupe = llGroupe;
	}
	/**
	 * [RefPartenaireDto.llGroupeArabe] Getter 
	 * @author MAKERRI Sofiane on : 2 janv. 2014  12:09:04
	 * @return the llGroupeArabe
	 */
	public String getLlGroupeArabe() {
		return llGroupeArabe;
	}
	/**
	 * [RefPartenaireDto.llGroupeArabe] Setter 
	 * @author MAKERRI Sofiane on : 2 janv. 2014  12:09:04
	 * @param llGroupeArabe the llGroupeArabe to set
	 */
	public void setLlGroupeArabe(String llGroupeArabe) {
		this.llGroupeArabe = llGroupeArabe;
	}
	/**
	 * [RefPartenaireDto.idStructure] Getter 
	 * @author MAKERRI Sofiane on : 2 janv. 2014  17:47:09
	 * @return the idStructure
	 */
	public Integer getIdStructure() {
		return idStructure;
	}
	/**
	 * [RefPartenaireDto.idStructure] Setter 
	 * @author MAKERRI Sofiane on : 2 janv. 2014  17:47:09
	 * @param idStructure the idStructure to set
	 */
	public void setIdStructure(Integer idStructure) {
		this.idStructure = idStructure;
	}
	/**
	 * [RefPartenaireDto.idIndividu] Getter 
	 * @author MAKERRI Sofiane on : 2 janv. 2014  17:47:09
	 * @return the idIndividu
	 */
	public Integer getIdIndividu() {
		return idIndividu;
	}
	/**
	 * [RefPartenaireDto.idIndividu] Setter 
	 * @author MAKERRI Sofiane on : 2 janv. 2014  17:47:09
	 * @param idIndividu the idIndividu to set
	 */
	public void setIdIndividu(Integer idIndividu) {
		this.idIndividu = idIndividu;
	}
	/**
	 * [RefPartenaireDto.idGroupe] Getter 
	 * @author MAKERRI Sofiane on : 2 janv. 2014  17:47:09
	 * @return the idGroupe
	 */
	public Integer getIdGroupe() {
		return idGroupe;
	}
	/**
	 * [RefPartenaireDto.idGroupe] Setter 
	 * @author MAKERRI Sofiane on : 2 janv. 2014  17:47:09
	 * @param idGroupe the idGroupe to set
	 */
	public void setIdGroupe(Integer idGroupe) {
		this.idGroupe = idGroupe;
	}
	/**
	 * [RefPartenaireDto.idRole] Getter 
	 * @author MAKERRI Sofiane on : 9 f�vr. 2014  13:02:25
	 * @return the idRole
	 */
	public Integer getIdRole() {
		return idRole;
	}
	/**
	 * [RefPartenaireDto.idRole] Setter 
	 * @author MAKERRI Sofiane on : 9 f�vr. 2014  13:02:25
	 * @param idRole the idRole to set
	 */
	public void setIdRole(Integer idRole) {
		this.idRole = idRole;
	}
	
	
	
}
