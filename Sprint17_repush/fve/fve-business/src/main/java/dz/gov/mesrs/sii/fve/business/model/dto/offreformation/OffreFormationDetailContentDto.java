/**
 * [dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OffreFormationDetailContentDto.java] 
 * @author rlaib on : 6 f�vr. 2014  12:55:43
 */
package dz.gov.mesrs.sii.fve.business.model.dto.offreformation;

import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.OffreFormation;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.OffreFormationDetail;

/**
 * @author rlaib  on : 6 f�vr. 2014  12:55:43
 */
public class OffreFormationDetailContentDto implements java.io.Serializable  {
	
	private int id;
	private String contentFr;
	private String contentAr;
    private OffreFormationDetail offreFormationDetail;
    private OffreFormation offreFormation;
    
    public OffreFormationDetailContentDto() {
	}

	public OffreFormationDetailContentDto(int id, String contentFr, String contentAr) {
		this.id = id;
		this.contentFr = contentFr;
		this.contentAr = contentAr;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContentFr() {
		return contentFr;
	}

	public void setContentFr(String contentFr) {
		this.contentFr = contentFr;
	}

	public String getContentAr() {
		return contentAr;
	}

	public void setContentAr(String contentAr) {
		this.contentAr = contentAr;
	}

	public OffreFormationDetail getOffreFormationDetail() {
		return offreFormationDetail;
	}

	public void setOffreFormationDetail(OffreFormationDetail offreFormationDetail) {
		this.offreFormationDetail = offreFormationDetail;
	}

	public OffreFormation getOffreFormation() {
		return offreFormation;
	}

	public void setOffreFormation(OffreFormation offreFormation) {
		this.offreFormation = offreFormation;
	}
	
	
}
