package dz.gov.mesrs.sii.gfc.business.model.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEtablissementDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefStructureDto;

public class MissionDto implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String numero;
	private Date dateDemande;
	private String objet;
	private Date dateDebut;
	private Date dateFin;

	private NomenclatureDto typeMission;
	private NomenclatureDto natureMission;

	private NomenclatureDto pays;
	private NomenclatureDto wilaya;
	private String ville;

	private RefEtablissementDto etablissementNational;
	private RefStructureDto structureNational;

	private String etablissement;
	private String structure;
	private String adresse;

	private String observation;

	private List<DossierMissionDto> dossierMissions = new ArrayList<DossierMissionDto>(0);
	private SituationEntiteDto situation;

	public MissionDto() {
	}

	public MissionDto(int id, String numero, Date dateDemande, String objet, Date dateDebut, Date dateFin) {
		this.id = id;
		this.numero = numero;
		this.dateDemande = dateDemande;
		this.objet = objet;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumero() {
		return this.numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Date getDateDemande() {
		return this.dateDemande;
	}

	public void setDateDemande(Date dateDemande) {
		this.dateDemande = dateDemande;
	}

	public String getObjet() {
		return this.objet;
	}

	public void setObjet(String objet) {
		this.objet = objet;
	}

	public Date getDateDebut() {
		return this.dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return this.dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public NomenclatureDto getTypeMission() {
		return this.typeMission;
	}

	public void setTypeMission(NomenclatureDto typeMission) {
		this.typeMission = typeMission;
	}

	public NomenclatureDto getNatureMission() {
		return this.natureMission;
	}

	public void setNatureMission(NomenclatureDto natureMission) {
		this.natureMission = natureMission;
	}

	public NomenclatureDto getPays() {
		return this.pays;
	}

	public void setPays(NomenclatureDto pays) {
		this.pays = pays;
	}

	public NomenclatureDto getWilaya() {
		return this.wilaya;
	}

	public void setWilaya(NomenclatureDto wilaya) {
		this.wilaya = wilaya;
	}

	public String getVille() {
		return this.ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public RefEtablissementDto getEtablissementNational() {
		return this.etablissementNational;
	}

	public void setEtablissementNational(RefEtablissementDto etablissementNational) {
		this.etablissementNational = etablissementNational;
	}

	public RefStructureDto getStructureNational() {
		return this.structureNational;
	}

	public void setStructureNational(RefStructureDto structureNational) {
		this.structureNational = structureNational;
	}

	public String getEtablissement() {
		return this.etablissement;
	}

	public void setEtablissement(String etablissement) {
		this.etablissement = etablissement;
	}

	public String getStructure() {
		return this.structure;
	}

	public void setStructure(String structure) {
		this.structure = structure;
	}

	public String getAdresse() {
		return this.adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getObservation() {
		return this.observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public SituationEntiteDto getSituation() {
		return this.situation;
	}

	public void setSituation(SituationEntiteDto situation) {
		this.situation = situation;
	}

	public List<DossierMissionDto> getDossierMissions() {
		return this.dossierMissions;
	}

	public void setDossierMissions(List<DossierMissionDto> dossierMissions) {
		this.dossierMissions = dossierMissions;
	}
}