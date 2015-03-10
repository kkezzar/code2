
package dz.gov.mesrs.sii.refenrentiel.jsf.beans.etatvalidation;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefSituationDto;



@ManagedBean(name = "situationBckBean")
@SessionScoped
public class SituationBckBean implements Serializable {

	
	
	private static final long serialVersionUID = 1L;

	private List<RefSituationDto > listRefSituationDto;
	

	private RefSituationDto refSituationDto;
	

	public SituationBckBean() {
		super();

	}


	public List<RefSituationDto> getListRefSituationDto() {
		return listRefSituationDto;
	}


	public void setListRefSituationDto(List<RefSituationDto> listRefSituationDto) {
		this.listRefSituationDto = listRefSituationDto;
	}


	public RefSituationDto getRefSituationDto() {
		return refSituationDto;
	}

	public void setRefSituationDto(RefSituationDto refSituationDto) {
		this.refSituationDto = refSituationDto;
	}
	

}
