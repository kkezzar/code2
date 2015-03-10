/**
 * 
 */
package dz.gov.mesrs.sii.referentiel.business.model.dto;
import java.io.Serializable;
import java.util.Date;

/**
 * @author jbeldi
 *
 */
public class RefSituationDto implements Serializable{

	/**
	 * serialVersionUID 
	 * @author BELDI Jamel  on : 28 janv. 2014  17:53:19
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Date dateSituation;
	private Integer idEntite;
	private String nomEntite;
	private String observation;
	
	//private RefEntiteSituation refEntiteSituation;
	private Integer entiteSituationId;
	//private Nomenclature nomenclature;
	private Integer typeSituationId;	
	private String typeSituationLibelleLongFr;
	private String typeSituationLibelleLongAr;
	private String typeSituationLibelleCourtFr;
	private String typeSituationLibelleCourtAr;
	private String typeSituationCode;


	/**
	 * 
	 */
	public RefSituationDto() {
		// TODO Auto-generated constructor stub
	}


	/**
	 * [RefSituationDto.id] Getter 
	 * @author BELDI Jamel on : 28 janv. 2014  17:57:47
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}


	/**
	 * [RefSituationDto.id] Setter 
	 * @author BELDI Jamel on : 28 janv. 2014  17:57:47
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}


	/**
	 * [RefSituationDto.dateSituation] Getter 
	 * @author BELDI Jamel on : 28 janv. 2014  17:57:47
	 * @return the dateSituation
	 */
	public Date getDateSituation() {
		return dateSituation;
	}


	/**
	 * [RefSituationDto.dateSituation] Setter 
	 * @author BELDI Jamel on : 28 janv. 2014  17:57:47
	 * @param dateSituation the dateSituation to set
	 */
	public void setDateSituation(Date dateSituation) {
		this.dateSituation = dateSituation;
	}


	/**
	 * [RefSituationDto.idEntite] Getter 
	 * @author BELDI Jamel on : 28 janv. 2014  17:57:47
	 * @return the idEntite
	 */
	public Integer getIdEntite() {
		return idEntite;
	}


	/**
	 * [RefSituationDto.idEntite] Setter 
	 * @author BELDI Jamel on : 28 janv. 2014  17:57:47
	 * @param idEntite the idEntite to set
	 */
	public void setIdEntite(Integer idEntite) {
		this.idEntite = idEntite;
	}


	/**
	 * [RefSituationDto.nomEntite] Getter 
	 * @author BELDI Jamel on : 28 janv. 2014  17:57:47
	 * @return the nomEntite
	 */
	public String getNomEntite() {
		return nomEntite;
	}


	/**
	 * [RefSituationDto.nomEntite] Setter 
	 * @author BELDI Jamel on : 28 janv. 2014  17:57:47
	 * @param nomEntite the nomEntite to set
	 */
	public void setNomEntite(String nomEntite) {
		this.nomEntite = nomEntite;
	}


	/**
	 * [RefSituationDto.observation] Getter 
	 * @author BELDI Jamel on : 28 janv. 2014  17:57:47
	 * @return the observation
	 */
	public String getObservation() {
		return observation;
	}


	/**
	 * [RefSituationDto.observation] Setter 
	 * @author BELDI Jamel on : 28 janv. 2014  17:57:47
	 * @param observation the observation to set
	 */
	public void setObservation(String observation) {
		this.observation = observation;
	}


	/**
	 * [RefSituationDto.entiteSituationId] Getter 
	 * @author BELDI Jamel on : 28 janv. 2014  17:57:47
	 * @return the entiteSituationId
	 */
	public Integer getEntiteSituationId() {
		return entiteSituationId;
	}


	/**
	 * [RefSituationDto.entiteSituationId] Setter 
	 * @author BELDI Jamel on : 28 janv. 2014  17:57:47
	 * @param entiteSituationId the entiteSituationId to set
	 */
	public void setEntiteSituationId(Integer entiteSituationId) {
		this.entiteSituationId = entiteSituationId;
	}


	/**
	 * [RefSituationDto.typeSituationId] Getter 
	 * @author BELDI Jamel on : 28 janv. 2014  17:57:47
	 * @return the typeSituationId
	 */
	public Integer getTypeSituationId() {
		return typeSituationId;
	}


	/**
	 * [RefSituationDto.typeSituationId] Setter 
	 * @author BELDI Jamel on : 28 janv. 2014  17:57:47
	 * @param typeSituationId the typeSituationId to set
	 */
	public void setTypeSituationId(Integer typeSituationId) {
		this.typeSituationId = typeSituationId;
	}


	/**
	 * [RefSituationDto.typeSituationLibelleLongFr] Getter 
	 * @author BELDI Jamel on : 28 janv. 2014  17:57:47
	 * @return the typeSituationLibelleLongFr
	 */
	public String getTypeSituationLibelleLongFr() {
		return typeSituationLibelleLongFr;
	}


	/**
	 * [RefSituationDto.typeSituationLibelleLongFr] Setter 
	 * @author BELDI Jamel on : 28 janv. 2014  17:57:47
	 * @param typeSituationLibelleLongFr the typeSituationLibelleLongFr to set
	 */
	public void setTypeSituationLibelleLongFr(String typeSituationLibelleLongFr) {
		this.typeSituationLibelleLongFr = typeSituationLibelleLongFr;
	}


	/**
	 * [RefSituationDto.typeSituationLibelleLongAr] Getter 
	 * @author BELDI Jamel on : 28 janv. 2014  17:57:47
	 * @return the typeSituationLibelleLongAr
	 */
	public String getTypeSituationLibelleLongAr() {
		return typeSituationLibelleLongAr;
	}


	/**
	 * [RefSituationDto.typeSituationLibelleLongAr] Setter 
	 * @author BELDI Jamel on : 28 janv. 2014  17:57:47
	 * @param typeSituationLibelleLongAr the typeSituationLibelleLongAr to set
	 */
	public void setTypeSituationLibelleLongAr(String typeSituationLibelleLongAr) {
		this.typeSituationLibelleLongAr = typeSituationLibelleLongAr;
	}


	/**
	 * [RefSituationDto.typeSituationLibelleCourtFr] Getter 
	 * @author BELDI Jamel on : 28 janv. 2014  17:57:47
	 * @return the typeSituationLibelleCourtFr
	 */
	public String getTypeSituationLibelleCourtFr() {
		return typeSituationLibelleCourtFr;
	}


	/**
	 * [RefSituationDto.typeSituationLibelleCourtFr] Setter 
	 * @author BELDI Jamel on : 28 janv. 2014  17:57:47
	 * @param typeSituationLibelleCourtFr the typeSituationLibelleCourtFr to set
	 */
	public void setTypeSituationLibelleCourtFr(String typeSituationLibelleCourtFr) {
		this.typeSituationLibelleCourtFr = typeSituationLibelleCourtFr;
	}


	/**
	 * [RefSituationDto.typeSituationLibelleCourtAr] Getter 
	 * @author BELDI Jamel on : 28 janv. 2014  17:57:47
	 * @return the typeSituationLibelleCourtAr
	 */
	public String getTypeSituationLibelleCourtAr() {
		return typeSituationLibelleCourtAr;
	}


	/**
	 * [RefSituationDto.typeSituationLibelleCourtAr] Setter 
	 * @author BELDI Jamel on : 28 janv. 2014  17:57:47
	 * @param typeSituationLibelleCourtAr the typeSituationLibelleCourtAr to set
	 */
	public void setTypeSituationLibelleCourtAr(String typeSituationLibelleCourtAr) {
		this.typeSituationLibelleCourtAr = typeSituationLibelleCourtAr;
	}


	/**
	 * [RefSituationDto.typeSituationCode] Getter 
	 * @author BELDI Jamel on : 28 janv. 2014  17:57:47
	 * @return the typeSituationCode
	 */
	public String getTypeSituationCode() {
		return typeSituationCode;
	}


	/**
	 * [RefSituationDto.typeSituationCode] Setter 
	 * @author BELDI Jamel on : 28 janv. 2014  17:57:47
	 * @param typeSituationCode the typeSituationCode to set
	 */
	public void setTypeSituationCode(String typeSituationCode) {
		this.typeSituationCode = typeSituationCode;
	}

	
	
	

}
