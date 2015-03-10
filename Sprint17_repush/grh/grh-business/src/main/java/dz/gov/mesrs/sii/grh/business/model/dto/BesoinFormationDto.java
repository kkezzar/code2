package dz.gov.mesrs.sii.grh.business.model.dto;



import java.util.Date;
import java.util.List;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEtablissementDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefGroupeDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefStructureDto;

/**
 * 
 * @author BELDI Jamel
 *
 */
public class BesoinFormationDto implements java.io.Serializable {

	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private SituationEntiteDto situationEntiteDto;
	private NomenclatureDto nomenclatureDto;
	private RefEtablissementDto refEtablissementDto;
	private RefGroupeDto refGroupeDto;
	private RefStructureDto refStructureDto;
	private DossierEmployeDto dossierEmployeDto;
	private Date dateDebut;
	private Date dateFin;
	private String objectif;
	private Date dateEvaluation;
	private String objectifAtteint;
	private List<DetailBesoinFormationDto> detailBesoinFormationDtos;
	

	public BesoinFormationDto() {
	}

	public BesoinFormationDto(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public SituationEntiteDto getSituationEntiteDto() {
		return situationEntiteDto;
	}

	public void setSituationEntiteDto(SituationEntiteDto situationEntiteDto) {
		this.situationEntiteDto = situationEntiteDto;
	}

	public NomenclatureDto getNomenclatureDto() {
		return nomenclatureDto;
	}

	public void setNomenclatureDto(NomenclatureDto nomenclatureDto) {
		this.nomenclatureDto = nomenclatureDto;
	}

	public RefEtablissementDto getRefEtablissementDto() {
		return refEtablissementDto;
	}

	public void setRefEtablissementDto(RefEtablissementDto refEtablissementDto) {
		this.refEtablissementDto = refEtablissementDto;
	}

	public RefGroupeDto getRefGroupeDto() {
		return refGroupeDto;
	}

	public void setRefGroupeDto(RefGroupeDto refGroupeDto) {
		this.refGroupeDto = refGroupeDto;
	}

	public RefStructureDto getRefStructureDto() {
		return refStructureDto;
	}

	public void setRefStructureDto(RefStructureDto refStructureDto) {
		this.refStructureDto = refStructureDto;
	}

	public DossierEmployeDto getDossierEmployeDto() {
		return dossierEmployeDto;
	}

	public void setDossierEmployeDto(DossierEmployeDto dossierEmployeDto) {
		this.dossierEmployeDto = dossierEmployeDto;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public String getObjectif() {
		return objectif;
	}

	public void setObjectif(String objectif) {
		this.objectif = objectif;
	}

	public Date getDateEvaluation() {
		return dateEvaluation;
	}

	public void setDateEvaluation(Date dateEvaluation) {
		this.dateEvaluation = dateEvaluation;
	}

	public String getObjectifAtteint() {
		return objectifAtteint;
	}

	public void setObjectifAtteint(String objectifAtteint) {
		this.objectifAtteint = objectifAtteint;
	}

	public List<DetailBesoinFormationDto> getDetailBesoinFormationDtos() {
		return detailBesoinFormationDtos;
	}

	public void setDetailBesoinFormationDtos(
			List<DetailBesoinFormationDto> detailBesoinFormationDtos) {
		this.detailBesoinFormationDtos = detailBesoinFormationDtos;
	}

	

	

}
