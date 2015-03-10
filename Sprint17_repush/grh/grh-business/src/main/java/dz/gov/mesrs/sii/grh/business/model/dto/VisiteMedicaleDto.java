package dz.gov.mesrs.sii.grh.business.model.dto;



import java.util.Date;
import java.util.List;

/**
 * 
 * @author BELDI Jamel
 *
 */

public class VisiteMedicaleDto implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private DossierEmployeDto dossierEmployeDto;
	private Date dateDemande;
	private String objet;
	private Date dateRdv;
	private Date heureRdv;
	private Date dateConvenue;
	private Date heureConvenue;
	private Date dateVisite;
	private Date heureVisite;
	private String dignostic;
	private Boolean resultat;
	private String motifRefus;
	private Boolean avecExamen;
	private Boolean finVisite;
	private Boolean finExamen;
	private List<MedicamentDto> medicamentDtos ;
	private List<ExamenMedicalDto> examenMedicalDtos;

	public VisiteMedicaleDto() {
	}

	public VisiteMedicaleDto(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DossierEmployeDto getDossierEmployeDto() {
		return dossierEmployeDto;
	}

	public void setDossierEmployeDto(DossierEmployeDto dossierEmployeDto) {
		this.dossierEmployeDto = dossierEmployeDto;
	}

	public Date getDateDemande() {
		return dateDemande;
	}

	public void setDateDemande(Date dateDemande) {
		this.dateDemande = dateDemande;
	}

	public String getObjet() {
		return objet;
	}

	public void setObjet(String objet) {
		this.objet = objet;
	}

	public Date getDateRdv() {
		return dateRdv;
	}

	public void setDateRdv(Date dateRdv) {
		this.dateRdv = dateRdv;
	}

	public Date getHeureRdv() {
		return heureRdv;
	}

	public void setHeureRdv(Date heureRdv) {
		this.heureRdv = heureRdv;
	}

	public Date getDateConvenue() {
		return dateConvenue;
	}

	public void setDateConvenue(Date dateConvenue) {
		this.dateConvenue = dateConvenue;
	}

	public Date getHeureConvenue() {
		return heureConvenue;
	}

	public void setHeureConvenue(Date heureConvenue) {
		this.heureConvenue = heureConvenue;
	}

	public Date getDateVisite() {
		return dateVisite;
	}

	public void setDateVisite(Date dateVisite) {
		this.dateVisite = dateVisite;
	}

	public Date getHeureVisite() {
		return heureVisite;
	}

	public void setHeureVisite(Date heureVisite) {
		this.heureVisite = heureVisite;
	}

	public String getDignostic() {
		return dignostic;
	}

	public void setDignostic(String dignostic) {
		this.dignostic = dignostic;
	}

	public Boolean getResultat() {
		return resultat;
	}

	public void setResultat(Boolean resultat) {
		this.resultat = resultat;
	}

	public String getMotifRefus() {
		return motifRefus;
	}

	public void setMotifRefus(String motifRefus) {
		this.motifRefus = motifRefus;
	}
	
	public Boolean getAvecExamen() {
		return avecExamen;
	}

	public void setAvecExamen(Boolean avecExamen) {
		this.avecExamen = avecExamen;
	}

	public Boolean getFinVisite() {
		return finVisite;
	}

	public void setFinVisite(Boolean finVisite) {
		this.finVisite = finVisite;
	}

	public Boolean getFinExamen() {
		return finExamen;
	}

	public void setFinExamen(Boolean finExamen) {
		this.finExamen = finExamen;
	}


	public List<MedicamentDto> getMedicamentDtos() {
		return medicamentDtos;
	}

	public void setMedicamentDtos(List<MedicamentDto> medicamentDtos) {
		this.medicamentDtos = medicamentDtos;
	}

	public List<ExamenMedicalDto> getExamenMedicalDtos() {
		return examenMedicalDtos;
	}

	public void setExamenMedicalDtos(List<ExamenMedicalDto> examenMedicalDtos) {
		this.examenMedicalDtos = examenMedicalDtos;
	}


	

	
	
	
	

}
