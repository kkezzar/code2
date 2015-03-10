package dz.gov.mesrs.sii.grh.business.model.dto;

import java.io.Serializable;
import java.util.Date;

import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;

public class DifficulteDto implements Serializable {

	private static final long serialVersionUID = 808948149143908717L;
	private Integer id;
	private DossierEmployeDto dossierEmploye;
	private NomenclatureDto nomenclature;
	private Date dateDebut;
	private Date dateFin;

	public DifficulteDto() {
	}

	public DifficulteDto(Integer id) {
		this.id = id;
	}

	public DifficulteDto(Integer id, DossierEmployeDto dossierEmploye,
			NomenclatureDto nomenclature, Date dateDebut, Date dateFin) {
		this.id = id;
		this.dossierEmploye = dossierEmploye;
		this.nomenclature = nomenclature;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
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

	/**
	 * @return the dossierEmploye
	 */
	public DossierEmployeDto getDossierEmploye() {
		return dossierEmploye;
	}

	/**
	 * @param dossierEmploye
	 *            the dossierEmploye to set
	 */
	public void setDossierEmploye(DossierEmployeDto dossierEmploye) {
		this.dossierEmploye = dossierEmploye;
	}

	/**
	 * @return the nomenclature
	 */
	public NomenclatureDto getNomenclature() {
		return nomenclature;
	}

	/**
	 * @param nomenclature
	 *            the nomenclature to set
	 */
	public void setNomenclature(NomenclatureDto nomenclature) {
		this.nomenclature = nomenclature;
	}

	/**
	 * @return the dateDebut
	 */
	public Date getDateDebut() {
		return dateDebut;
	}

	/**
	 * @param dateDebut
	 *            the dateDebut to set
	 */
	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	/**
	 * @return the dateFin
	 */
	public Date getDateFin() {
		return dateFin;
	}

	/**
	 * @param dateFin
	 *            the dateFin to set
	 */
	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

}
