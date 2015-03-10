package dz.gov.mesrs.sii.grh.business.model.dto;

import java.io.Serializable;
import java.util.Date;

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefIndividuDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefStructureDto;

public class CandidatEmployeDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5916812517993697238L;
	private Integer id;
	private RefIndividuDto refIndividuDto;
	private DetailRecrutementDto detailRecrutementDto;
	private Date dateDepot;
	private String observation;
	// private DossierEmployeDto dossierEmployeDto;
	private Date dateSelection;
	private Boolean retenu;
	private Date dateConvocation;
	private String motifConvocation;
	private Date dateDecesion;
	private Date dateConvocationRec;
	private Integer classement;
	private Boolean desistement;
	private Date dateDesistement;
	private String motifDesistement;
	private Boolean admis;
	private Date dateSignature;
	private Date dateInstallation;
	private String matricule;
	private String referenceDecision;
	private Boolean installe;
	private Boolean valide;
	private Date dateValidation;
	private RefStructureDto refStructureDto;

	public CandidatEmployeDto() {
		refIndividuDto = new RefIndividuDto();
		detailRecrutementDto = new DetailRecrutementDto();
		refStructureDto = new RefStructureDto();
		// dossierEmployeDto = new DossierEmployeDto();
	}

	public CandidatEmployeDto(Integer id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the dateDepot
	 */
	public Date getDateDepot() {
		return dateDepot;
	}

	/**
	 * @param dateDepot
	 *            the dateDepot to set
	 */
	public void setDateDepot(Date dateDepot) {
		this.dateDepot = dateDepot;
	}

	/**
	 * @return the observation
	 */
	public String getObservation() {
		return observation;
	}

	/**
	 * @param observation
	 *            the observation to set
	 */
	public void setObservation(String observation) {
		this.observation = observation;
	}

	/**
	 * @return the dateSelection
	 */
	public Date getDateSelection() {
		return dateSelection;
	}

	/**
	 * @param dateSelection
	 *            the dateSelection to set
	 */
	public void setDateSelection(Date dateSelection) {
		this.dateSelection = dateSelection;
	}

	/**
	 * @return the retenu
	 */
	public Boolean getRetenu() {
		return retenu;
	}

	/**
	 * @param retenu
	 *            the retenu to set
	 */
	public void setRetenu(Boolean retenu) {
		this.retenu = retenu;
	}

	/**
	 * @return the dateConvocation
	 */
	public Date getDateConvocation() {
		return dateConvocation;
	}

	/**
	 * @param dateConvocation
	 *            the dateConvocation to set
	 */
	public void setDateConvocation(Date dateConvocation) {
		this.dateConvocation = dateConvocation;
	}

	/**
	 * @return the motifConvocation
	 */
	public String getMotifConvocation() {
		return motifConvocation;
	}

	/**
	 * @param motifConvocation
	 *            the motifConvocation to set
	 */
	public void setMotifConvocation(String motifConvocation) {
		this.motifConvocation = motifConvocation;
	}

	/**
	 * @return the dateDecesion
	 */
	public Date getDateDecesion() {
		return dateDecesion;
	}

	/**
	 * @param dateDecesion
	 *            the dateDecesion to set
	 */
	public void setDateDecesion(Date dateDecesion) {
		this.dateDecesion = dateDecesion;
	}

	/**
	 * @return the dateConvocationRec
	 */
	public Date getDateConvocationRec() {
		return dateConvocationRec;
	}

	/**
	 * @param dateConvocationRec
	 *            the dateConvocationRec to set
	 */
	public void setDateConvocationRec(Date dateConvocationRec) {
		this.dateConvocationRec = dateConvocationRec;
	}

	/**
	 * @return the classement
	 */
	public Integer getClassement() {
		return classement;
	}

	/**
	 * @param classement
	 *            the classement to set
	 */
	public void setClassement(Integer classement) {
		this.classement = classement;
	}

	/**
	 * @return the desistement
	 */
	public Boolean getDesistement() {
		return desistement;
	}

	/**
	 * @param desistement
	 *            the desistement to set
	 */
	public void setDesistement(Boolean desistement) {
		this.desistement = desistement;
	}

	/**
	 * @return the dateDesistement
	 */
	public Date getDateDesistement() {
		return dateDesistement;
	}

	/**
	 * @param dateDesistement
	 *            the dateDesistement to set
	 */
	public void setDateDesistement(Date dateDesistement) {
		this.dateDesistement = dateDesistement;
	}

	/**
	 * @return the motifDesistement
	 */
	public String getMotifDesistement() {
		return motifDesistement;
	}

	/**
	 * @param motifDesistement
	 *            the motifDesistement to set
	 */
	public void setMotifDesistement(String motifDesistement) {
		this.motifDesistement = motifDesistement;
	}

	/**
	 * @return the admis
	 */
	public Boolean getAdmis() {
		return admis;
	}

	/**
	 * @param admis
	 *            the admis to set
	 */
	public void setAdmis(Boolean admis) {
		this.admis = admis;
	}

	/**
	 * @return the dateSignature
	 */
	public Date getDateSignature() {
		return dateSignature;
	}

	/**
	 * @param dateSignature
	 *            the dateSignature to set
	 */
	public void setDateSignature(Date dateSignature) {
		this.dateSignature = dateSignature;
	}

	/**
	 * @return the dateInstallation
	 */
	public Date getDateInstallation() {
		return dateInstallation;
	}

	/**
	 * @param dateInstallation
	 *            the dateInstallation to set
	 */
	public void setDateInstallation(Date dateInstallation) {
		this.dateInstallation = dateInstallation;
	}

	/**
	 * @return the matricule
	 */
	public String getMatricule() {
		return matricule;
	}

	/**
	 * @param matricule
	 *            the matricule to set
	 */
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public RefIndividuDto getRefIndividuDto() {
		return refIndividuDto;
	}

	public void setRefIndividuDto(RefIndividuDto refIndividuDto) {
		this.refIndividuDto = refIndividuDto;
	}

	public DetailRecrutementDto getDetailRecrutementDto() {
		return detailRecrutementDto;
	}

	public void setDetailRecrutementDto(DetailRecrutementDto detailRecrutementDto) {
		this.detailRecrutementDto = detailRecrutementDto;
	}

	public String getReferenceDecision() {
		return referenceDecision;
	}

	public void setReferenceDecision(String referenceDecision) {
		this.referenceDecision = referenceDecision;
	}

	public Boolean getInstalle() {
		return installe;
	}

	public void setInstalle(Boolean installe) {
		this.installe = installe;
	}

	public RefStructureDto getRefStructureDto() {
		return refStructureDto;
	}

	public void setRefStructureDto(RefStructureDto refStructureDto) {
		this.refStructureDto = refStructureDto;
	}

	// public DossierEmployeDto getDossierEmployeDto() {
	// return dossierEmployeDto;
	// }
	//
	// public void setDossierEmployeDto(DossierEmployeDto dossierEmployeDto) {
	// this.dossierEmployeDto = dossierEmployeDto;
	// }

	public Boolean getValide() {
		return valide;
	}

	public void setValide(Boolean valide) {
		this.valide = valide;
	}

	public Date getDateValidation() {
		return dateValidation;
	}

	public void setDateValidation(Date dateValidation) {
		this.dateValidation = dateValidation;
	}

}
