package dz.gov.mesrs.sii.fve.business.model.dto.scolarite;

import java.util.Date;

public class DossierEmployeDto implements java.io.Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String matricule;
	private String numeross;
	private Date dateAffiliation;
	private String numeroCompte;
	private Date dateObtention;
	private Integer distinction;
	private Date dateTitularisation;
	
	// individu Infos
	private String individuCivilite;
	private String individuNomLatin;
	private String individuPrenomLatin;
	private String individuNomArabe;
	private String individuPrenomArabe;
	private Date individuDateNaissance;
	private String individuSituationFamilialeLibelleLongFr;
	private String individuLieuNaissance;
	
	private String grade;
	private String displayName;
	
	public DossierEmployeDto() {
	}

	public DossierEmployeDto(Long id) {
		this.id = id;
	}

	

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the matricule
	 */
	public String getMatricule() {
		return matricule;
	}

	/**
	 * @param matricule the matricule to set
	 */
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	/**
	 * @return the numeross
	 */
	public String getNumeross() {
		return numeross;
	}

	/**
	 * @param numeross the numeross to set
	 */
	public void setNumeross(String numeross) {
		this.numeross = numeross;
	}

	/**
	 * @return the dateAffiliation
	 */
	public Date getDateAffiliation() {
		return dateAffiliation;
	}

	/**
	 * @param dateAffiliation the dateAffiliation to set
	 */
	public void setDateAffiliation(Date dateAffiliation) {
		this.dateAffiliation = dateAffiliation;
	}

	/**
	 * @return the numeroCompte
	 */
	public String getNumeroCompte() {
		return numeroCompte;
	}

	/**
	 * @param numeroCompte the numeroCompte to set
	 */
	public void setNumeroCompte(String numeroCompte) {
		this.numeroCompte = numeroCompte;
	}

	/**
	 * @return the dateObtention
	 */
	public Date getDateObtention() {
		return dateObtention;
	}

	/**
	 * @param dateObtention the dateObtention to set
	 */
	public void setDateObtention(Date dateObtention) {
		this.dateObtention = dateObtention;
	}

	/**
	 * @return the distinction
	 */
	public Integer getDistinction() {
		return distinction;
	}

	/**
	 * @param distinction the distinction to set
	 */
	public void setDistinction(Integer distinction) {
		this.distinction = distinction;
	}

	/**
	 * @return the dateTitularisation
	 */
	public Date getDateTitularisation() {
		return dateTitularisation;
	}

	/**
	 * @param dateTitularisation the dateTitularisation to set
	 */
	public void setDateTitularisation(Date dateTitularisation) {
		this.dateTitularisation = dateTitularisation;
	}

	/**
	 * [DossierEmployeDto.individuCivilite] Getter 
	 * @author rlaib on : 29 oct. 2014  17:18:26
	 * @return the individuCivilite
	 */
	public String getIndividuCivilite() {
		return individuCivilite;
	}

	/**
	 * [DossierEmployeDto.individuCivilite] Setter 
	 * @author rlaib on : 29 oct. 2014  17:18:26
	 * @param individuCivilite the individuCivilite to set
	 */
	public void setIndividuCivilite(String individuCivilite) {
		this.individuCivilite = individuCivilite;
	}

	/**
	 * [DossierEmployeDto.individuNomLatin] Getter 
	 * @author rlaib on : 29 oct. 2014  17:18:26
	 * @return the individuNomLatin
	 */
	public String getIndividuNomLatin() {
		return individuNomLatin;
	}

	/**
	 * [DossierEmployeDto.individuNomLatin] Setter 
	 * @author rlaib on : 29 oct. 2014  17:18:26
	 * @param individuNomLatin the individuNomLatin to set
	 */
	public void setIndividuNomLatin(String individuNomLatin) {
		this.individuNomLatin = individuNomLatin;
	}

	/**
	 * [DossierEmployeDto.individuPrenomLatin] Getter 
	 * @author rlaib on : 29 oct. 2014  17:18:26
	 * @return the individuPrenomLatin
	 */
	public String getIndividuPrenomLatin() {
		return individuPrenomLatin;
	}

	/**
	 * [DossierEmployeDto.individuPrenomLatin] Setter 
	 * @author rlaib on : 29 oct. 2014  17:18:26
	 * @param individuPrenomLatin the individuPrenomLatin to set
	 */
	public void setIndividuPrenomLatin(String individuPrenomLatin) {
		this.individuPrenomLatin = individuPrenomLatin;
	}

	/**
	 * [DossierEmployeDto.individuNomArabe] Getter 
	 * @author rlaib on : 29 oct. 2014  17:18:26
	 * @return the individuNomArabe
	 */
	public String getIndividuNomArabe() {
		return individuNomArabe;
	}

	/**
	 * [DossierEmployeDto.individuNomArabe] Setter 
	 * @author rlaib on : 29 oct. 2014  17:18:26
	 * @param individuNomArabe the individuNomArabe to set
	 */
	public void setIndividuNomArabe(String individuNomArabe) {
		this.individuNomArabe = individuNomArabe;
	}

	/**
	 * [DossierEmployeDto.individuPrenomArabe] Getter 
	 * @author rlaib on : 29 oct. 2014  17:18:26
	 * @return the individuPrenomArabe
	 */
	public String getIndividuPrenomArabe() {
		return individuPrenomArabe;
	}

	/**
	 * [DossierEmployeDto.individuPrenomArabe] Setter 
	 * @author rlaib on : 29 oct. 2014  17:18:26
	 * @param individuPrenomArabe the individuPrenomArabe to set
	 */
	public void setIndividuPrenomArabe(String individuPrenomArabe) {
		this.individuPrenomArabe = individuPrenomArabe;
	}

	/**
	 * [DossierEmployeDto.individuDateNaissance] Getter 
	 * @author rlaib on : 29 oct. 2014  17:18:26
	 * @return the individuDateNaissance
	 */
	public Date getIndividuDateNaissance() {
		return individuDateNaissance;
	}

	/**
	 * [DossierEmployeDto.individuDateNaissance] Setter 
	 * @author rlaib on : 29 oct. 2014  17:18:26
	 * @param individuDateNaissance the individuDateNaissance to set
	 */
	public void setIndividuDateNaissance(Date individuDateNaissance) {
		this.individuDateNaissance = individuDateNaissance;
	}

	/**
	 * [DossierEmployeDto.individuSituationFamilialeLibelleLongFr] Getter 
	 * @author rlaib on : 29 oct. 2014  17:18:26
	 * @return the individuSituationFamilialeLibelleLongFr
	 */
	public String getIndividuSituationFamilialeLibelleLongFr() {
		return individuSituationFamilialeLibelleLongFr;
	}

	/**
	 * [DossierEmployeDto.individuSituationFamilialeLibelleLongFr] Setter 
	 * @author rlaib on : 29 oct. 2014  17:18:26
	 * @param individuSituationFamilialeLibelleLongFr the individuSituationFamilialeLibelleLongFr to set
	 */
	public void setIndividuSituationFamilialeLibelleLongFr(
			String individuSituationFamilialeLibelleLongFr) {
		this.individuSituationFamilialeLibelleLongFr = individuSituationFamilialeLibelleLongFr;
	}

	/**
	 * [DossierEmployeDto.individuLieuNaissance] Getter 
	 * @author rlaib on : 29 oct. 2014  17:18:26
	 * @return the individuLieuNaissance
	 */
	public String getIndividuLieuNaissance() {
		return individuLieuNaissance;
	}

	/**
	 * [DossierEmployeDto.individuLieuNaissance] Setter 
	 * @author rlaib on : 29 oct. 2014  17:18:26
	 * @param individuLieuNaissance the individuLieuNaissance to set
	 */
	public void setIndividuLieuNaissance(String individuLieuNaissance) {
		this.individuLieuNaissance = individuLieuNaissance;
	}

	/**
	 * [DossierEmployeDto.grade] Getter 
	 * @author rlaib on : 29 oct. 2014  18:22:56
	 * @return the grade
	 */
	public String getGrade() {
		return grade;
	}

	/**
	 * [DossierEmployeDto.grade] Setter 
	 * @author rlaib on : 29 oct. 2014  18:22:56
	 * @param grade the grade to set
	 */
	public void setGrade(String grade) {
		this.grade = grade;
	}

	/**
	 * [DossierEmployeDto.displayName] Getter 
	 * @author rlaib on : 11 nov. 2014  12:40:16
	 * @return the displayName
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * [DossierEmployeDto.displayName] Setter 
	 * @author rlaib on : 11 nov. 2014  12:40:16
	 * @param displayName the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	
	
}
