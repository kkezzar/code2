package dz.gov.mesrs.sii.grh.business.model.dto;

import java.io.Serializable;
import java.util.List;

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefStructureDto;

public class DetailRecrutementDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5555236988950573004L;
	private Integer id;
	private String reference;
	private BesoinRecrutementDto besoinRecrutementDto;
	private CorpsDto corpsDto;
	private GradeDto gradeDto;
	private CategorieProfessionnelleDto categorieProfessionnelleDto;
	private PosteSuperieureDto posteSuperieureDto;
	private RefStructureDto refStructureDto;
	private List<CandidatEmployeDto> candidatEmployeDtos;
	private Integer nbrePoste;

	public DetailRecrutementDto() {
		besoinRecrutementDto = new BesoinRecrutementDto();
		corpsDto = new CorpsDto();
		gradeDto = new GradeDto();
		posteSuperieureDto = new PosteSuperieureDto();
		refStructureDto = new RefStructureDto();

	}

	public DetailRecrutementDto(Integer id) {
		this.id = id;
	}

	/**
	 * [DetailRecrutementDto.DetailRecrutementDto()] Constructor
	 * 
	 * @author BELDI Jamel on : 3 déc. 2014 14:23:19
	 * @param id
	 * @param reference
	 * @param besoinRecrutementDto
	 * @param corpsDto
	 * @param gradeDto
	 * @param categorieProfessionnelleDto
	 * @param posteSuperieureDto
	 * @param refStructureDto
	 */
	public DetailRecrutementDto(Integer id, String reference, BesoinRecrutementDto besoinRecrutementDto,
			CorpsDto corpsDto, GradeDto gradeDto, CategorieProfessionnelleDto categorieProfessionnelleDto,
			PosteSuperieureDto posteSuperieureDto, RefStructureDto refStructureDto) {
		super();
		this.id = id;
		this.reference = reference;
		this.besoinRecrutementDto = besoinRecrutementDto;
		this.corpsDto = corpsDto;
		this.gradeDto = gradeDto;
		this.categorieProfessionnelleDto = categorieProfessionnelleDto;
		this.posteSuperieureDto = posteSuperieureDto;
		this.refStructureDto = refStructureDto;
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

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public BesoinRecrutementDto getBesoinRecrutementDto() {
		return besoinRecrutementDto;
	}

	public void setBesoinRecrutementDto(BesoinRecrutementDto besoinRecrutementDto) {
		this.besoinRecrutementDto = besoinRecrutementDto;
	}

	public CorpsDto getCorpsDto() {
		return corpsDto;
	}

	public void setCorpsDto(CorpsDto corpsDto) {
		this.corpsDto = corpsDto;
	}

	public GradeDto getGradeDto() {
		return gradeDto;
	}

	public void setGradeDto(GradeDto gradeDto) {
		this.gradeDto = gradeDto;
	}

	public PosteSuperieureDto getPosteSuperieureDto() {
		return posteSuperieureDto;
	}

	public void setPosteSuperieureDto(PosteSuperieureDto posteSuperieureDto) {
		this.posteSuperieureDto = posteSuperieureDto;
	}

	public RefStructureDto getRefStructureDto() {
		return refStructureDto;
	}

	public void setRefStructureDto(RefStructureDto refStructureDto) {
		this.refStructureDto = refStructureDto;
	}

	public CategorieProfessionnelleDto getCategorieProfessionnelleDto() {
		return categorieProfessionnelleDto;
	}

	public void setCategorieProfessionnelleDto(CategorieProfessionnelleDto categorieProfessionnelleDto) {
		this.categorieProfessionnelleDto = categorieProfessionnelleDto;
	}

	public List<CandidatEmployeDto> getCandidatEmployeDtos() {
		return candidatEmployeDtos;
	}

	public void setCandidatEmployeDtos(List<CandidatEmployeDto> candidatEmployeDtos) {
		this.candidatEmployeDtos = candidatEmployeDtos;
	}

	public Integer getNbrePoste() {
		return nbrePoste;
	}

	public void setNbrePoste(Integer nbrePoste) {
		this.nbrePoste = nbrePoste;
	}

	
	
}
