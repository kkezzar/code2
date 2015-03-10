package dz.gov.mesrs.sii.grh.business.model.dto;

import java.io.Serializable;
import java.util.Date;

import dz.gov.mesrs.sii.commons.data.model.referentiel.Nomenclature;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;

public class CarriereDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4347433605055924328L;
	private Integer id;
	private GradeDto gradeDto;
	private Date dateEffetGrade;
	private CorpsDto corpsDto;
	private Date dateEffetCorps;
	private DossierEmployeDto dossierEmployeDto;
	private CategorieProfessionnelleDto categorieProfessionnelleDto;
	private GrilleIndiciaireDto grilleIndiciaireDto;
	private Date dateEffetEchelon;
	private Integer indice;
	
	private NomenclatureDto nomenclatureByCarriereDto;
	private Date dateEffet;
	private Date dateChangement;
	private String motif;
	private Boolean confirm;
	private String objetFormation;
	private Date dateDebutFormation;
	private Date dateFinFormation;
	private Date dateExament;
	private Date dateObtentionDiplome;
	private NomenclatureDto nomenclatureBydiplomeDto;
	private NomenclatureDto nomenclatureBytypePromotionDto;
	private NomenclatureDto nomenclatureBytitreDto;


	public CarriereDto() {
	}

	public CarriereDto(Integer id) {
		this.id = id;
	}

	/**
	 * [CarriereDto.id] Getter 
	 * @author obelbrik on : 22 déc. 2014  10:13:44
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * [CarriereDto.id] Setter 
	 * @author obelbrik on : 22 déc. 2014  10:13:44
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * [CarriereDto.gradeDto] Getter 
	 * @author obelbrik on : 22 déc. 2014  10:13:44
	 * @return the gradeDto
	 */
	public GradeDto getGradeDto() {
		return gradeDto;
	}

	/**
	 * [CarriereDto.gradeDto] Setter 
	 * @author obelbrik on : 22 déc. 2014  10:13:44
	 * @param gradeDto the gradeDto to set
	 */
	public void setGradeDto(GradeDto gradeDto) {
		this.gradeDto = gradeDto;
	}

	/**
	 * [CarriereDto.dateEffetGrade] Getter 
	 * @author obelbrik on : 22 déc. 2014  10:13:44
	 * @return the dateEffetGrade
	 */
	public Date getDateEffetGrade() {
		return dateEffetGrade;
	}

	/**
	 * [CarriereDto.dateEffetGrade] Setter 
	 * @author obelbrik on : 22 déc. 2014  10:13:44
	 * @param dateEffetGrade the dateEffetGrade to set
	 */
	public void setDateEffetGrade(Date dateEffetGrade) {
		this.dateEffetGrade = dateEffetGrade;
	}

	/**
	 * [CarriereDto.corpsDto] Getter 
	 * @author obelbrik on : 22 déc. 2014  10:13:44
	 * @return the corpsDto
	 */
	public CorpsDto getCorpsDto() {
		return corpsDto;
	}

	/**
	 * [CarriereDto.corpsDto] Setter 
	 * @author obelbrik on : 22 déc. 2014  10:13:44
	 * @param corpsDto the corpsDto to set
	 */
	public void setCorpsDto(CorpsDto corpsDto) {
		this.corpsDto = corpsDto;
	}

	/**
	 * [CarriereDto.dateEffetCorps] Getter 
	 * @author obelbrik on : 22 déc. 2014  10:13:44
	 * @return the dateEffetCorps
	 */
	public Date getDateEffetCorps() {
		return dateEffetCorps;
	}

	/**
	 * [CarriereDto.dateEffetCorps] Setter 
	 * @author obelbrik on : 22 déc. 2014  10:13:44
	 * @param dateEffetCorps the dateEffetCorps to set
	 */
	public void setDateEffetCorps(Date dateEffetCorps) {
		this.dateEffetCorps = dateEffetCorps;
	}

	/**
	 * [CarriereDto.dossierEmployeDto] Getter 
	 * @author obelbrik on : 22 déc. 2014  10:13:44
	 * @return the dossierEmployeDto
	 */
	public DossierEmployeDto getDossierEmployeDto() {
		return dossierEmployeDto;
	}

	/**
	 * [CarriereDto.dossierEmployeDto] Setter 
	 * @author obelbrik on : 22 déc. 2014  10:13:44
	 * @param dossierEmployeDto the dossierEmployeDto to set
	 */
	public void setDossierEmployeDto(DossierEmployeDto dossierEmployeDto) {
		this.dossierEmployeDto = dossierEmployeDto;
	}

	/**
	 * [CarriereDto.categorieProfessionnelleDto] Getter 
	 * @author obelbrik on : 22 déc. 2014  10:13:44
	 * @return the categorieProfessionnelleDto
	 */
	public CategorieProfessionnelleDto getCategorieProfessionnelleDto() {
		return categorieProfessionnelleDto;
	}

	/**
	 * [CarriereDto.categorieProfessionnelleDto] Setter 
	 * @author obelbrik on : 22 déc. 2014  10:13:44
	 * @param categorieProfessionnelleDto the categorieProfessionnelleDto to set
	 */
	public void setCategorieProfessionnelleDto(
			CategorieProfessionnelleDto categorieProfessionnelleDto) {
		this.categorieProfessionnelleDto = categorieProfessionnelleDto;
	}

	

	/**
	 * [CarriereDto.dateEffetEchelon] Getter 
	 * @author obelbrik on : 22 déc. 2014  10:13:44
	 * @return the dateEffetEchelon
	 */
	public Date getDateEffetEchelon() {
		return dateEffetEchelon;
	}

	/**
	 * [CarriereDto.dateEffetEchelon] Setter 
	 * @author obelbrik on : 22 déc. 2014  10:13:44
	 * @param dateEffetEchelon the dateEffetEchelon to set
	 */
	public void setDateEffetEchelon(Date dateEffetEchelon) {
		this.dateEffetEchelon = dateEffetEchelon;
	}

	/**
	 * [CarriereDto.indice] Getter 
	 * @author obelbrik on : 22 déc. 2014  10:13:44
	 * @return the indice
	 */
	public Integer getIndice() {
		return indice;
	}

	/**
	 * [CarriereDto.indice] Setter 
	 * @author obelbrik on : 22 déc. 2014  10:13:44
	 * @param indice the indice to set
	 */
	public void setIndice(Integer indice) {
		this.indice = indice;
	}

	/**
	 * [CarriereDto.nomenclatureByCarriereDto] Getter 
	 * @author obelbrik on : 22 déc. 2014  10:13:44
	 * @return the nomenclatureByCarriereDto
	 */
	public NomenclatureDto getNomenclatureByCarriereDto() {
		return nomenclatureByCarriereDto;
	}

	/**
	 * [CarriereDto.nomenclatureByCarriereDto] Setter 
	 * @author obelbrik on : 22 déc. 2014  10:13:44
	 * @param nomenclatureByCarriereDto the nomenclatureByCarriereDto to set
	 */
	public void setNomenclatureByCarriereDto(
			NomenclatureDto nomenclatureByCarriereDto) {
		this.nomenclatureByCarriereDto = nomenclatureByCarriereDto;
	}

	/**
	 * [CarriereDto.dateEffet] Getter 
	 * @author obelbrik on : 22 déc. 2014  10:13:44
	 * @return the dateEffet
	 */
	public Date getDateEffet() {
		return dateEffet;
	}

	/**
	 * [CarriereDto.dateEffet] Setter 
	 * @author obelbrik on : 22 déc. 2014  10:13:44
	 * @param dateEffet the dateEffet to set
	 */
	public void setDateEffet(Date dateEffet) {
		this.dateEffet = dateEffet;
	}
	
	public Date getDateChangement() {
		return dateChangement;
	}

	
	public void setDateChangement(Date dateChangement) {
		this.dateChangement = dateChangement;
	}

		public String getMotif() {
		return motif;
	}

	/**
	 * [CarriereDto.motif] Setter 
	 * @author obelbrik on : 22 déc. 2014  10:13:44
	 * @param motif the motif to set
	 */
	public void setMotif(String motif) {
		this.motif = motif;
	}

	/**
	 * [CarriereDto.grilleIndiciaireDto] Getter 
	 * @author obelbrik on : 22 déc. 2014  10:24:39
	 * @return the grilleIndiciaireDto
	 */
	public GrilleIndiciaireDto getGrilleIndiciaireDto() {
		return grilleIndiciaireDto;
	}

	/**
	 * [CarriereDto.grilleIndiciaireDto] Setter 
	 * @author obelbrik on : 22 déc. 2014  10:24:39
	 * @param grilleIndiciaireDto the grilleIndiciaireDto to set
	 */
	public void setGrilleIndiciaireDto(GrilleIndiciaireDto grilleIndiciaireDto) {
		this.grilleIndiciaireDto = grilleIndiciaireDto;
	}

	public Boolean getConfirm() {
		return confirm;
	}

	public void setConfirm(Boolean confirm) {
		this.confirm = confirm;
	}

	public String getObjetFormation() {
		return objetFormation;
	}

	public void setObjetFormation(String objetFormation) {
		this.objetFormation = objetFormation;
	}

	public Date getDateDebutFormation() {
		return dateDebutFormation;
	}

	public void setDateDebutFormation(Date dateDebutFormation) {
		this.dateDebutFormation = dateDebutFormation;
	}

	public Date getDateFinFormation() {
		return dateFinFormation;
	}

	public void setDateFinFormation(Date dateFinFormation) {
		this.dateFinFormation = dateFinFormation;
	}

	public Date getDateExament() {
		return dateExament;
	}

	public void setDateExament(Date dateExament) {
		this.dateExament = dateExament;
	}

	public Date getDateObtentionDiplome() {
		return dateObtentionDiplome;
	}

	public void setDateObtentionDiplome(Date dateObtentionDiplome) {
		this.dateObtentionDiplome = dateObtentionDiplome;
	}

	public NomenclatureDto getNomenclatureBydiplomeDto() {
		return nomenclatureBydiplomeDto;
	}

	public void setNomenclatureBydiplomeDto(NomenclatureDto nomenclatureBydiplomeDto) {
		this.nomenclatureBydiplomeDto = nomenclatureBydiplomeDto;
	}

	public NomenclatureDto getNomenclatureBytypePromotionDto() {
		return nomenclatureBytypePromotionDto;
	}

	public void setNomenclatureBytypePromotionDto(
			NomenclatureDto nomenclatureBytypePromotionDto) {
		this.nomenclatureBytypePromotionDto = nomenclatureBytypePromotionDto;
	}

	public NomenclatureDto getNomenclatureBytitreDto() {
		return nomenclatureBytitreDto;
	}

	public void setNomenclatureBytitreDto(NomenclatureDto nomenclatureBytitreDto) {
		this.nomenclatureBytitreDto = nomenclatureBytitreDto;
	}

	
	

	
	

}
