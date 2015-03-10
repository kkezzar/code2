package dz.gov.mesrs.sii.fve.ws.bo.offreformation;

//import dz.gov.mesrs.sii.referentiel.business.model.dto.nc.CycleDto;
//import dz.gov.mesrs.sii.referentiel.business.model.dto.nc.EtablissementDto;
//import dz.gov.mesrs.sii.referentiel.business.model.dto.nc.NatureOFDto;
//import dz.gov.mesrs.sii.referentiel.business.model.dto.nc.SpecialiteDto;
//import dz.gov.mesrs.sii.referentiel.business.model.dto.nc.StatutOFDto;
//import dz.gov.mesrs.sii.referentiel.business.model.dto.nc.TypeFormationDto;
//import dz.gov.mesrs.sii.referentiel.business.model.dto.nc.VocationDto;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * OffreFormationDto generated by hbm2java
 */
public class OffreFormation implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1604002171088680259L;
	private int id;
//	private SpecialiteDto specialiteDto;
//	private VocationDto vocationDto;
//	private ProgrammeFormation programmeFormationDto;
//	private EtablissementDto etablissementDto;
//	private NatureOFDto natureOFDto;
//	private TypeFormationDto typeFormationDto;
//	private StatutOFDto statutOFDto;
//	private CycleDto cycleDto;
	private Integer idTypeTarif;
	private String abreviationAr;
	private String abreviationFr;
	private String codeInt;
	private String codeNat;
	private Date dateCreation;
	private Date dateDebutHabilitation;
	private Date dateDerniereModif;
	private Date dateFinHabilitation;
	private String descriptionAr;
	private String descriptionFr;
	private String libelleAr;
	private String libelleFr;
	private String numeroArrete;
	private String specialisationAr;
	private String specialisationFr;
	private Set<OffreFormationDomaineCompetenceDto> offreFormationDomaineCompetenceDtos = new HashSet<OffreFormationDomaineCompetenceDto>(
			0);
	private Set<ParcoursType> parcoursTypeDtos = new HashSet<ParcoursType>(
			0);

	public OffreFormation() {
	}


	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

//	public SpecialiteDto getSpecialiteDto() {
//		return this.specialiteDto;
//	}
//
//	public void setSpecialiteDto(SpecialiteDto specialiteDto) {
//		this.specialiteDto = specialiteDto;
//	}
//
//	public VocationDto getVocationDto() {
//		return this.vocationDto;
//	}
//
//	public void setVocationDto(VocationDto vocationDto) {
//		this.vocationDto = vocationDto;
//	}

//	public ProgrammeFormation getProgrammeFormationDto() {
//		return this.programmeFormationDto;
//	}
//
//	public void setProgrammeFormationDto(
//			ProgrammeFormation programmeFormationDto) {
//		this.programmeFormationDto = programmeFormationDto;
//	}

//	public EtablissementDto getEtablissementDto() {
//		return this.etablissementDto;
//	}
//
//	public void setEtablissementDto(EtablissementDto etablissementDto) {
//		this.etablissementDto = etablissementDto;
//	}
//
//	public NatureOFDto getNatureOFDto() {
//		return this.natureOFDto;
//	}
//
//	public void setNatureOFDto(NatureOFDto natureOFDto) {
//		this.natureOFDto = natureOFDto;
//	}
//
//	public TypeFormationDto getTypeFormationDto() {
//		return this.typeFormationDto;
//	}
//
//	public void setTypeFormationDto(TypeFormationDto typeFormationDto) {
//		this.typeFormationDto = typeFormationDto;
//	}
//
//	public StatutOFDto getStatutOFDto() {
//		return this.statutOFDto;
//	}
//
//	public void setStatutOFDto(StatutOFDto statutOFDto) {
//		this.statutOFDto = statutOFDto;
//	}
//
//	public CycleDto getCycleDto() {
//		return this.cycleDto;
//	}
//
//	public void setCycleDto(CycleDto cycleDto) {
//		this.cycleDto = cycleDto;
//	}

	public Integer getIdTypeTarif() {
		return this.idTypeTarif;
	}

	public void setIdTypeTarif(Integer idTypeTarif) {
		this.idTypeTarif = idTypeTarif;
	}

	public String getAbreviationAr() {
		return this.abreviationAr;
	}

	public void setAbreviationAr(String abreviationAr) {
		this.abreviationAr = abreviationAr;
	}

	public String getAbreviationFr() {
		return this.abreviationFr;
	}

	public void setAbreviationFr(String abreviationFr) {
		this.abreviationFr = abreviationFr;
	}

	public String getCodeInt() {
		return this.codeInt;
	}

	public void setCodeInt(String codeInt) {
		this.codeInt = codeInt;
	}

	public String getCodeNat() {
		return this.codeNat;
	}

	public void setCodeNat(String codeNat) {
		this.codeNat = codeNat;
	}

	public Date getDateCreation() {
		return this.dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Date getDateDebutHabilitation() {
		return this.dateDebutHabilitation;
	}

	public void setDateDebutHabilitation(Date dateDebutHabilitation) {
		this.dateDebutHabilitation = dateDebutHabilitation;
	}

	public Date getDateDerniereModif() {
		return this.dateDerniereModif;
	}

	public void setDateDerniereModif(Date dateDerniereModif) {
		this.dateDerniereModif = dateDerniereModif;
	}

	public Date getDateFinHabilitation() {
		return this.dateFinHabilitation;
	}

	public void setDateFinHabilitation(Date dateFinHabilitation) {
		this.dateFinHabilitation = dateFinHabilitation;
	}

	public String getDescriptionAr() {
		return this.descriptionAr;
	}

	public void setDescriptionAr(String descriptionAr) {
		this.descriptionAr = descriptionAr;
	}

	public String getDescriptionFr() {
		return this.descriptionFr;
	}

	public void setDescriptionFr(String descriptionFr) {
		this.descriptionFr = descriptionFr;
	}

	public String getLibelleAr() {
		return this.libelleAr;
	}

	public void setLibelleAr(String libelleAr) {
		this.libelleAr = libelleAr;
	}

	public String getLibelleFr() {
		return this.libelleFr;
	}

	public void setLibelleFr(String libelleFr) {
		this.libelleFr = libelleFr;
	}

	public String getNumeroArrete() {
		return this.numeroArrete;
	}

	public void setNumeroArrete(String numeroArrete) {
		this.numeroArrete = numeroArrete;
	}

	public String getSpecialisationAr() {
		return this.specialisationAr;
	}

	public void setSpecialisationAr(String specialisationAr) {
		this.specialisationAr = specialisationAr;
	}

	public String getSpecialisationFr() {
		return this.specialisationFr;
	}

	public void setSpecialisationFr(String specialisationFr) {
		this.specialisationFr = specialisationFr;
	}

	public Set<OffreFormationDomaineCompetenceDto> getOffreFormationDomaineCompetenceDtos() {
		return this.offreFormationDomaineCompetenceDtos;
	}

	public void setOffreFormationDomaineCompetenceDtos(
			Set<OffreFormationDomaineCompetenceDto> offreFormationDomaineCompetenceDtos) {
		this.offreFormationDomaineCompetenceDtos = offreFormationDomaineCompetenceDtos;
	}

	public Set<ParcoursType> getParcoursTypeDtos() {
		return this.parcoursTypeDtos;
	}

	public void setParcoursTypeDtos(Set<ParcoursType> parcoursTypeDtos) {
		this.parcoursTypeDtos = parcoursTypeDtos;
	}

}