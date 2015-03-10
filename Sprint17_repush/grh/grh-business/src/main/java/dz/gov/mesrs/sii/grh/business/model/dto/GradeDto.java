package dz.gov.mesrs.sii.grh.business.model.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;

public class GradeDto implements Serializable {

	private static final long serialVersionUID = 3038771534456141841L;

	private Integer id;
	private NomenclatureDto nomenclatureDto;
	private CorpsDto corpsDto;
	private Long rangGrade;
	private Long inidiceMinimale;
	private Set<PromotionProposeDto> promotionProposeDtos = new HashSet<PromotionProposeDto>(0);
	private Set<CarriereDto> carriereDtos = new HashSet<CarriereDto>(0);
	private Set<DetailRecrutementDto> detailRecrutementDtos = new HashSet<DetailRecrutementDto>(0);
	private CategorieProfessionnelleDto categorieProfessionnelleDto;

	public GradeDto() {
	}

	public GradeDto(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public NomenclatureDto getNomenclatureDto() {
		return nomenclatureDto;
	}

	public void setNomenclatureDto(NomenclatureDto nomenclatureDto) {
		this.nomenclatureDto = nomenclatureDto;
	}

	public CorpsDto getCorpsDto() {
		return corpsDto;
	}

	public void setCorpsDto(CorpsDto corpsDto) {
		this.corpsDto = corpsDto;
	}

	public Long getRangGrade() {
		return rangGrade;
	}

	public void setRangGrade(Long rangGrade) {
		this.rangGrade = rangGrade;
	}

	public Long getInidiceMinimale() {
		return inidiceMinimale;
	}

	public void setInidiceMinimale(Long inidiceMinimale) {
		this.inidiceMinimale = inidiceMinimale;
	}

	public Set<PromotionProposeDto> getPromotionProposeDtos() {
		return promotionProposeDtos;
	}

	public void setPromotionProposeDtos(Set<PromotionProposeDto> promotionProposeDtos) {
		this.promotionProposeDtos = promotionProposeDtos;
	}

	public Set<CarriereDto> getCarriereDtos() {
		return carriereDtos;
	}

	public void setCarriereDtos(Set<CarriereDto> carriereDtos) {
		this.carriereDtos = carriereDtos;
	}

	public Set<DetailRecrutementDto> getDetailRecrutementDtos() {
		return detailRecrutementDtos;
	}

	public void setDetailRecrutementDtos(Set<DetailRecrutementDto> detailRecrutementDtos) {
		this.detailRecrutementDtos = detailRecrutementDtos;
	}

	public CategorieProfessionnelleDto getCategorieProfessionnelleDto() {
		return categorieProfessionnelleDto;
	}

	public void setCategorieProfessionnelleDto(CategorieProfessionnelleDto categorieProfessionnelleDto) {
		this.categorieProfessionnelleDto = categorieProfessionnelleDto;
	}
}
