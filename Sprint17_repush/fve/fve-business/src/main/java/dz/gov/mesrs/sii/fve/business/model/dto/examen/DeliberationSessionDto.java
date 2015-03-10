/**
 * [dz.gov.mesrs.sii.fve.business.model.dto.cursus.DeliberationSessionDto.java] 
 * @author MAKERRI Sofiane on : 19 oct. 2014  11:54:25
 */
package dz.gov.mesrs.sii.fve.business.model.dto.examen;

import java.io.Serializable;
import java.util.Date;

/**
 * @author MAKERRI Sofiane  on : 19 oct. 2014  11:54:25
 */
public class DeliberationSessionDto implements Serializable {

	/**
	 * serialVersionUID 
	 * @author MAKERRI Sofiane  on : 19 oct. 2014  11:54:29
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Date dateDeliberation;
	private Date heureDeliberation;
	private Date dateCloture;
	private Long planningSessionId;
	private String psIntitule;
	private Date psDateDebut;
	private Date psDateFin;
	private Integer psNumeroSession;
	private String psRefCodeTypeSession;
	private Date psDateCreation;
	private Date psDatePublication;
	private Date psDateCloture;
	private String psCodeAnneeAcademique;
	private String psPeriodeLibelle;
	private String psOfLibelleFr;
	private double psCoefficientNoteEliminatoire;
	private Integer situationId;
	private String situationStyleCss;
	private String situationLibelleFr;
	private Integer anneeAcademiqueId;
	private Integer periodeId;
	private String periodeLibelleFr;
	private Integer oofId;
	private boolean bilanAnnuel;
	private boolean bilanPeriode;
	private Integer ofId;

	/**
	 * [DeliberationSessionDto.DeliberationSessionDto()] Constructor
	 * @author MAKERRI Sofiane  on : 19 oct. 2014  11:54:25	
	 */
	public DeliberationSessionDto() {
		super();
	}

	/**
	 * [DeliberationSessionDto.id] Getter 
	 * @author MAKERRI Sofiane on : 19 oct. 2014  11:55:22
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * [DeliberationSessionDto.id] Setter 
	 * @author MAKERRI Sofiane on : 19 oct. 2014  11:55:22
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * [DeliberationSessionDto.dateDeliberation] Getter 
	 * @author MAKERRI Sofiane on : 19 oct. 2014  11:55:22
	 * @return the dateDeliberation
	 */
	public Date getDateDeliberation() {
		return dateDeliberation;
	}

	/**
	 * [DeliberationSessionDto.dateDeliberation] Setter 
	 * @author MAKERRI Sofiane on : 19 oct. 2014  11:55:22
	 * @param dateDeliberation the dateDeliberation to set
	 */
	public void setDateDeliberation(Date dateDeliberation) {
		this.dateDeliberation = dateDeliberation;
	}

	/**
	 * [DeliberationSessionDto.heureDeliberation] Getter 
	 * @author MAKERRI Sofiane on : 19 oct. 2014  11:55:22
	 * @return the heureDeliberation
	 */
	public Date getHeureDeliberation() {
		return heureDeliberation;
	}

	/**
	 * [DeliberationSessionDto.heureDeliberation] Setter 
	 * @author MAKERRI Sofiane on : 19 oct. 2014  11:55:22
	 * @param heureDeliberation the heureDeliberation to set
	 */
	public void setHeureDeliberation(Date heureDeliberation) {
		this.heureDeliberation = heureDeliberation;
	}

	/**
	 * [DeliberationSessionDto.planningSessionId] Getter 
	 * @author MAKERRI Sofiane on : 19 oct. 2014  11:55:22
	 * @return the planningSessionId
	 */
	public Long getPlanningSessionId() {
		return planningSessionId;
	}

	/**
	 * [DeliberationSessionDto.planningSessionId] Setter 
	 * @author MAKERRI Sofiane on : 19 oct. 2014  11:55:22
	 * @param planningSessionId the planningSessionId to set
	 */
	public void setPlanningSessionId(Long planningSessionId) {
		this.planningSessionId = planningSessionId;
	}

	/**
	 * [DeliberationSessionDto.situationId] Getter 
	 * @author MAKERRI Sofiane on : 19 oct. 2014  11:55:22
	 * @return the situationId
	 */
	public Integer getSituationId() {
		return situationId;
	}

	/**
	 * [DeliberationSessionDto.situationId] Setter 
	 * @author MAKERRI Sofiane on : 19 oct. 2014  11:55:22
	 * @param situationId the situationId to set
	 */
	public void setSituationId(Integer situationId) {
		this.situationId = situationId;
	}

	/**
	 * [DeliberationSessionDto.psIntitule] Getter 
	 * @author MAKERRI Sofiane on : 20 oct. 2014  10:55:17
	 * @return the psIntitule
	 */
	public String getPsIntitule() {
		return psIntitule;
	}

	/**
	 * [DeliberationSessionDto.psIntitule] Setter 
	 * @author MAKERRI Sofiane on : 20 oct. 2014  10:55:17
	 * @param psIntitule the psIntitule to set
	 */
	public void setPsIntitule(String psIntitule) {
		this.psIntitule = psIntitule;
	}

	/**
	 * [DeliberationSessionDto.psDateDebut] Getter 
	 * @author MAKERRI Sofiane on : 20 oct. 2014  10:55:17
	 * @return the psDateDebut
	 */
	public Date getPsDateDebut() {
		return psDateDebut;
	}

	/**
	 * [DeliberationSessionDto.psDateDebut] Setter 
	 * @author MAKERRI Sofiane on : 20 oct. 2014  10:55:17
	 * @param psDateDebut the psDateDebut to set
	 */
	public void setPsDateDebut(Date psDateDebut) {
		this.psDateDebut = psDateDebut;
	}

	/**
	 * [DeliberationSessionDto.psDateFin] Getter 
	 * @author MAKERRI Sofiane on : 20 oct. 2014  10:55:17
	 * @return the psDateFin
	 */
	public Date getPsDateFin() {
		return psDateFin;
	}

	/**
	 * [DeliberationSessionDto.psDateFin] Setter 
	 * @author MAKERRI Sofiane on : 20 oct. 2014  10:55:17
	 * @param psDateFin the psDateFin to set
	 */
	public void setPsDateFin(Date psDateFin) {
		this.psDateFin = psDateFin;
	}

	/**
	 * [DeliberationSessionDto.psNumeroSession] Getter 
	 * @author MAKERRI Sofiane on : 20 oct. 2014  10:55:17
	 * @return the psNumeroSession
	 */
	public Integer getPsNumeroSession() {
		return psNumeroSession;
	}

	/**
	 * [DeliberationSessionDto.psNumeroSession] Setter 
	 * @author MAKERRI Sofiane on : 20 oct. 2014  10:55:17
	 * @param psNumeroSession the psNumeroSession to set
	 */
	public void setPsNumeroSession(Integer psNumeroSession) {
		this.psNumeroSession = psNumeroSession;
	}

	/**
	 * [DeliberationSessionDto.psRefCodeTypeSession] Getter 
	 * @author MAKERRI Sofiane on : 20 oct. 2014  10:55:17
	 * @return the psRefCodeTypeSession
	 */
	public String getPsRefCodeTypeSession() {
		return psRefCodeTypeSession;
	}

	/**
	 * [DeliberationSessionDto.psRefCodeTypeSession] Setter 
	 * @author MAKERRI Sofiane on : 20 oct. 2014  10:55:17
	 * @param psRefCodeTypeSession the psRefCodeTypeSession to set
	 */
	public void setPsRefCodeTypeSession(String psRefCodeTypeSession) {
		this.psRefCodeTypeSession = psRefCodeTypeSession;
	}

	/**
	 * [DeliberationSessionDto.psDateCreation] Getter 
	 * @author MAKERRI Sofiane on : 20 oct. 2014  10:55:17
	 * @return the psDateCreation
	 */
	public Date getPsDateCreation() {
		return psDateCreation;
	}

	/**
	 * [DeliberationSessionDto.psDateCreation] Setter 
	 * @author MAKERRI Sofiane on : 20 oct. 2014  10:55:17
	 * @param psDateCreation the psDateCreation to set
	 */
	public void setPsDateCreation(Date psDateCreation) {
		this.psDateCreation = psDateCreation;
	}

	/**
	 * [DeliberationSessionDto.psDatePublication] Getter 
	 * @author MAKERRI Sofiane on : 20 oct. 2014  10:55:17
	 * @return the psDatePublication
	 */
	public Date getPsDatePublication() {
		return psDatePublication;
	}

	/**
	 * [DeliberationSessionDto.psDatePublication] Setter 
	 * @author MAKERRI Sofiane on : 20 oct. 2014  10:55:17
	 * @param psDatePublication the psDatePublication to set
	 */
	public void setPsDatePublication(Date psDatePublication) {
		this.psDatePublication = psDatePublication;
	}

	/**
	 * [DeliberationSessionDto.psDateCloture] Getter 
	 * @author MAKERRI Sofiane on : 20 oct. 2014  10:55:17
	 * @return the psDateCloture
	 */
	public Date getPsDateCloture() {
		return psDateCloture;
	}

	/**
	 * [DeliberationSessionDto.psDateCloture] Setter 
	 * @author MAKERRI Sofiane on : 20 oct. 2014  10:55:17
	 * @param psDateCloture the psDateCloture to set
	 */
	public void setPsDateCloture(Date psDateCloture) {
		this.psDateCloture = psDateCloture;
	}

	/**
	 * [DeliberationSessionDto.psCodeAnneeAcademique] Getter 
	 * @author MAKERRI Sofiane on : 21 oct. 2014  12:40:27
	 * @return the psCodeAnneeAcademique
	 */
	public String getPsCodeAnneeAcademique() {
		return psCodeAnneeAcademique;
	}

	/**
	 * [DeliberationSessionDto.psCodeAnneeAcademique] Setter 
	 * @author MAKERRI Sofiane on : 21 oct. 2014  12:40:27
	 * @param psCodeAnneeAcademique the psCodeAnneeAcademique to set
	 */
	public void setPsCodeAnneeAcademique(String psCodeAnneeAcademique) {
		this.psCodeAnneeAcademique = psCodeAnneeAcademique;
	}

	/**
	 * [DeliberationSessionDto.psPeriodeLibelle] Getter 
	 * @author MAKERRI Sofiane on : 21 oct. 2014  12:42:37
	 * @return the psPeriodeLibelle
	 */
	public String getPsPeriodeLibelle() {
		return psPeriodeLibelle;
	}

	/**
	 * [DeliberationSessionDto.psPeriodeLibelle] Setter 
	 * @author MAKERRI Sofiane on : 21 oct. 2014  12:42:37
	 * @param psPeriodeLibelle the psPeriodeLibelle to set
	 */
	public void setPsPeriodeLibelle(String psPeriodeLibelle) {
		this.psPeriodeLibelle = psPeriodeLibelle;
	}

	/**
	 * [DeliberationSessionDto.psOfLibelleFr] Getter 
	 * @author MAKERRI Sofiane on : 21 oct. 2014  12:48:04
	 * @return the psOfLibelleFr
	 */
	public String getPsOfLibelleFr() {
		return psOfLibelleFr;
	}

	/**
	 * [DeliberationSessionDto.psOfLibelleFr] Setter 
	 * @author MAKERRI Sofiane on : 21 oct. 2014  12:48:04
	 * @param psOfLibelleFr the psOfLibelleFr to set
	 */
	public void setPsOfLibelleFr(String psOfLibelleFr) {
		this.psOfLibelleFr = psOfLibelleFr;
	}

	/**
	 * [DeliberationSessionDto.anneeAcademiqueId] Getter 
	 * @author MAKERRI Sofiane on : 22 oct. 2014  11:44:35
	 * @return the anneeAcademiqueId
	 */
	public Integer getAnneeAcademiqueId() {
		return anneeAcademiqueId;
	}

	/**
	 * [DeliberationSessionDto.anneeAcademiqueId] Setter 
	 * @author MAKERRI Sofiane on : 22 oct. 2014  11:44:35
	 * @param anneeAcademiqueId the anneeAcademiqueId to set
	 */
	public void setAnneeAcademiqueId(Integer anneeAcademiqueId) {
		this.anneeAcademiqueId = anneeAcademiqueId;
	}

	/**
	 * [DeliberationSessionDto.periodeId] Getter 
	 * @author MAKERRI Sofiane on : 22 oct. 2014  11:44:35
	 * @return the periodeId
	 */
	public Integer getPeriodeId() {
		return periodeId;
	}

	/**
	 * [DeliberationSessionDto.periodeId] Setter 
	 * @author MAKERRI Sofiane on : 22 oct. 2014  11:44:35
	 * @param periodeId the periodeId to set
	 */
	public void setPeriodeId(Integer periodeId) {
		this.periodeId = periodeId;
	}

	/**
	 * [DeliberationSessionDto.oofId] Getter 
	 * @author MAKERRI Sofiane on : 22 oct. 2014  11:44:35
	 * @return the oofId
	 */
	public Integer getOofId() {
		return oofId;
	}

	/**
	 * [DeliberationSessionDto.oofId] Setter 
	 * @author MAKERRI Sofiane on : 22 oct. 2014  11:44:35
	 * @param oofId the oofId to set
	 */
	public void setOofId(Integer oofId) {
		this.oofId = oofId;
	}

	/**
	 * [DeliberationSessionDto.dateCloture] Getter 
	 * @author MAKERRI Sofiane on : 25 oct. 2014  09:18:23
	 * @return the dateCloture
	 */
	public Date getDateCloture() {
		return dateCloture;
	}

	/**
	 * [DeliberationSessionDto.dateCloture] Setter 
	 * @author MAKERRI Sofiane on : 25 oct. 2014  09:18:23
	 * @param dateCloture the dateCloture to set
	 */
	public void setDateCloture(Date dateCloture) {
		this.dateCloture = dateCloture;
	}

	/**
	 * [DeliberationSessionDto.situationStyleCss] Getter 
	 * @author MAKERRI Sofiane on : 25 oct. 2014  11:33:23
	 * @return the situationStyleCss
	 */
	public String getSituationStyleCss() {
		return situationStyleCss;
	}

	/**
	 * [DeliberationSessionDto.situationStyleCss] Setter 
	 * @author MAKERRI Sofiane on : 25 oct. 2014  11:33:23
	 * @param situationStyleCss the situationStyleCss to set
	 */
	public void setSituationStyleCss(String situationStyleCss) {
		this.situationStyleCss = situationStyleCss;
	}

	/**
	 * [DeliberationSessionDto.bilanAnnuel] Getter 
	 * @author MAKERRI Sofiane on : 25 oct. 2014  14:15:50
	 * @return the bilanAnnuel
	 */
	public boolean getBilanAnnuel() {
		return bilanAnnuel;
	}

	/**
	 * [DeliberationSessionDto.bilanAnnuel] Setter 
	 * @author MAKERRI Sofiane on : 25 oct. 2014  14:15:50
	 * @param bilanAnnuel the bilanAnnuel to set
	 */
	public void setBilanAnnuel(boolean bilanAnnuel) {
		this.bilanAnnuel = bilanAnnuel;
	}

	/**
	 * [DeliberationSessionDto.situationLibelleFr] Getter 
	 * @author MAKERRI Sofiane on : 25 oct. 2014  14:31:02
	 * @return the situationLibelleFr
	 */
	public String getSituationLibelleFr() {
		return situationLibelleFr;
	}

	/**
	 * [DeliberationSessionDto.situationLibelleFr] Setter 
	 * @author MAKERRI Sofiane on : 25 oct. 2014  14:31:02
	 * @param situationLibelleFr the situationLibelleFr to set
	 */
	public void setSituationLibelleFr(String situationLibelleFr) {
		this.situationLibelleFr = situationLibelleFr;
	}

	/**
	 * [DeliberationSessionDto.bilanPeriode] Getter 
	 * @author MAKERRI Sofiane on : 26 oct. 2014  14:12:51
	 * @return the bilanPeriode
	 */
	public boolean getBilanPeriode() {
		return bilanPeriode;
	}

	/**
	 * [DeliberationSessionDto.bilanPeriode] Setter 
	 * @author MAKERRI Sofiane on : 26 oct. 2014  14:12:51
	 * @param bilanPeriode the bilanPeriode to set
	 */
	public void setBilanPeriode(boolean bilanPeriode) {
		this.bilanPeriode = bilanPeriode;
	}

	/**
	 * [DeliberationSessionDto.periodeLibelleFr] Getter 
	 * @author MAKERRI Sofiane on : 27 oct. 2014  16:24:28
	 * @return the periodeLibelleFr
	 */
	public String getPeriodeLibelleFr() {
		return periodeLibelleFr;
	}

	/**
	 * [DeliberationSessionDto.periodeLibelleFr] Setter 
	 * @author MAKERRI Sofiane on : 27 oct. 2014  16:24:28
	 * @param periodeLibelleFr the periodeLibelleFr to set
	 */
	public void setPeriodeLibelleFr(String periodeLibelleFr) {
		this.periodeLibelleFr = periodeLibelleFr;
	}

	/**
	 * [DeliberationSessionDto.ofId] Getter 
	 * @author MAKERRI Sofiane on : 6 nov. 2014  11:50:15
	 * @return the ofId
	 */
	public Integer getOfId() {
		return ofId;
	}

	/**
	 * [DeliberationSessionDto.ofId] Setter 
	 * @author MAKERRI Sofiane on : 6 nov. 2014  11:50:15
	 * @param ofId the ofId to set
	 */
	public void setOfId(Integer ofId) {
		this.ofId = ofId;
	}

	/**
	 * [DeliberationSessionDto.psCoefficientNoteEliminatoire] Getter 
	 * @author MAKERRI Sofiane on : 20 janv. 2015  15:13:02
	 * @return the psCoefficientNoteEliminatoire
	 */
	public double getPsCoefficientNoteEliminatoire() {
		return psCoefficientNoteEliminatoire;
	}

	/**
	 * [DeliberationSessionDto.psCoefficientNoteEliminatoire] Setter 
	 * @author MAKERRI Sofiane on : 20 janv. 2015  15:13:02
	 * @param psCoefficientNoteEliminatoire the psCoefficientNoteEliminatoire to set
	 */
	public void setPsCoefficientNoteEliminatoire(
			double psCoefficientNoteEliminatoire) {
		this.psCoefficientNoteEliminatoire = psCoefficientNoteEliminatoire;
	}


	

}
