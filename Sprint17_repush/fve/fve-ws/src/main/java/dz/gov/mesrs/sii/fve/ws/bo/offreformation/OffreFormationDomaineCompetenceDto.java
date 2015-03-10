package dz.gov.mesrs.sii.fve.ws.bo.offreformation;

//import dz.gov.mesrs.sii.referentiel.business.model.dto.nc.DomaineCompetenceDto;


public class OffreFormationDomaineCompetenceDto implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2475967833266246553L;
	private int id;
	private OffreFormation offreFormationDto;
	//private DomaineCompetenceDto domaineCompetenceDto;

	public OffreFormationDomaineCompetenceDto() {
	}


	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public OffreFormation getOffreFormationDto() {
		return this.offreFormationDto;
	}

	public void setOffreFormationDto(OffreFormation offreFormationDto) {
		this.offreFormationDto = offreFormationDto;
	}

//	public DomaineCompetenceDto getDomaineCompetenceDto() {
//		return this.domaineCompetenceDto;
//	}
//
//	public void setDomaineCompetenceDto(
//			DomaineCompetenceDto domaineCompetenceDto) {
//		this.domaineCompetenceDto = domaineCompetenceDto;
//	}

}
