package dz.gov.mesrs.sii.grh.business.model.dto;




/**
 * 
 * @author BELDI Jamel
 *
 */

public class DetailBesoinFormationDto implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private GradeDto gradeDto;
	private FormationCatalogueDto formationCatalogueDto;
	private BesoinFormationDto besoinFormationDto;
	private CorpsDto corpsDto;
	private Integer nbCandidat;
	private Integer nbForme;
	private Boolean effectue;

	public DetailBesoinFormationDto() {
	}

	public DetailBesoinFormationDto(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public GradeDto getGradeDto() {
		return gradeDto;
	}

	public void setGradeDto(GradeDto gradeDto) {
		this.gradeDto = gradeDto;
	}

	public FormationCatalogueDto getFormationCatalogueDto() {
		return formationCatalogueDto;
	}

	public void setFormationCatalogueDto(FormationCatalogueDto formationCatalogueDto) {
		this.formationCatalogueDto = formationCatalogueDto;
	}

	public BesoinFormationDto getBesoinFormationDto() {
		return besoinFormationDto;
	}

	public void setBesoinFormationDto(BesoinFormationDto besoinFormationDto) {
		this.besoinFormationDto = besoinFormationDto;
	}

	public CorpsDto getCorpsDto() {
		return corpsDto;
	}

	public void setCorpsDto(CorpsDto corpsDto) {
		this.corpsDto = corpsDto;
	}

	public Integer getNbCandidat() {
		return nbCandidat;
	}

	public void setNbCandidat(Integer nbCandidat) {
		this.nbCandidat = nbCandidat;
	}

	public Integer getNbForme() {
		return nbForme;
	}

	public void setNbForme(Integer nbForme) {
		this.nbForme = nbForme;
	}

	public Boolean getEffectue() {
		return effectue;
	}

	public void setEffectue(Boolean effectue) {
		this.effectue = effectue;
	}

	

	

}
