/**
 * [dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OffreFormationDetailDto.java] 
 * @author rlaib on : 6 f�vr. 2014  12:52:41
 */
package dz.gov.mesrs.sii.fve.business.model.dto.offreformation;

import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.OffreFormationDetail;

/**
 * @author rlaib  on : 6 f�vr. 2014  12:52:41
 */
public class OffreFormationDetailDto implements java.io.Serializable  {
	
	private int id;
	private int typeDetail;
	private String code;
	private String libelleFr;
	private String libelleAr;
	private String descriptionFr;
	private String descriptionAr;
    private OffreFormationDetail offreFormationDetail;
    
	public OffreFormationDetailDto() {
	}

	public OffreFormationDetailDto(int id, String code, String libelleFr, String libelleAr) {
		this.id = id;
		this.code = code;
		this.libelleFr = libelleFr;
		this.libelleAr = libelleAr;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTypeDetail() {
		return typeDetail;
	}

	public void setTypeDetail(int typeDetail) {
		this.typeDetail = typeDetail;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLibelleFr() {
		return libelleFr;
	}

	public void setLibelleFr(String libelleFr) {
		this.libelleFr = libelleFr;
	}

	public String getLibelleAr() {
		return libelleAr;
	}

	public void setLibelleAr(String libelleAr) {
		this.libelleAr = libelleAr;
	}

	public String getDescriptionFr() {
		return descriptionFr;
	}

	public void setDescriptionFr(String descriptionFr) {
		this.descriptionFr = descriptionFr;
	}

	public String getDescriptionAr() {
		return descriptionAr;
	}

	public void setDescriptionAr(String descriptionAr) {
		this.descriptionAr = descriptionAr;
	}

	public OffreFormationDetail getOffreFormationDetail() {
		return offreFormationDetail;
	}

	public void setOffreFormationDetail(OffreFormationDetail offreFormationDetail) {
		this.offreFormationDetail = offreFormationDetail;
	}
	
}
