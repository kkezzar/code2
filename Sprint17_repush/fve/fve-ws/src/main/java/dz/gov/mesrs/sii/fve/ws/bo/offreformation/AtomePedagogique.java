package dz.gov.mesrs.sii.fve.ws.bo.offreformation;

//import dz.gov.mesrs.sii.referentiel.business.model.dto.nc.ModeEnseignementDto;
//import dz.gov.mesrs.sii.referentiel.business.model.dto.nc.TypeAPDto;

public class AtomePedagogique implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7476780610300969998L;
	private int id;
	//private MatiereConstitutiveDto matiereConstitutiveDto;
	//private ModeEnseignementDto modeEnseignementDto;
	//private TypeAPDto typeAPDto;
	private String code;
	private String libelleFr;
	private String libelleAr;
	private Double seuil;
	private Double volumeHoraire;
	private Integer groupesPrevus;
	private Integer groupeReels;

	public AtomePedagogique() {
	}

	public AtomePedagogique(int id, String code, String libelleFr) {
		this.id = id;
		this.code = code;
		this.libelleFr = libelleFr;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

//	public MatiereConstitutiveDto getMatiereConstitutiveDto() {
//		return this.matiereConstitutiveDto;
//	}
//
//	public void setMatiereConstitutiveDto(
//			MatiereConstitutiveDto matiereConstitutiveDto) {
//		this.matiereConstitutiveDto = matiereConstitutiveDto;
//	}
//
//	public ModeEnseignementDto getModeEnseignementDto() {
//		return this.modeEnseignementDto;
//	}
//
//	public void setModeEnseignementDto(ModeEnseignementDto modeEnseignementDto) {
//		this.modeEnseignementDto = modeEnseignementDto;
//	}
//
//	public TypeAPDto getTypeAPDto() {
//		return this.typeAPDto;
//	}
//
//	public void setTypeAPDto(TypeAPDto typeAPDto) {
//		this.typeAPDto = typeAPDto;
//	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLibelleFr() {
		return this.libelleFr;
	}

	public void setLibelleFr(String libelleFr) {
		this.libelleFr = libelleFr;
	}

	public String getLibelleAr() {
		return this.libelleAr;
	}

	public void setLibelleAr(String libelleAr) {
		this.libelleAr = libelleAr;
	}

	public Double getSeuil() {
		return this.seuil;
	}

	public void setSeuil(Double seuil) {
		this.seuil = seuil;
	}

	public Double getVolumeHoraire() {
		return this.volumeHoraire;
	}

	public void setVolumeHoraire(Double volumeHoraire) {
		this.volumeHoraire = volumeHoraire;
	}

	public Integer getGroupesPrevus() {
		return this.groupesPrevus;
	}

	public void setGroupesPrevus(Integer groupesPrevus) {
		this.groupesPrevus = groupesPrevus;
	}

	public Integer getGroupeReels() {
		return this.groupeReels;
	}

	public void setGroupeReels(Integer groupeReels) {
		this.groupeReels = groupeReels;
	}

}
