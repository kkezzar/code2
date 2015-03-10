
package dz.gov.mesrs.sii.refenrentiel.jsf.beans.equipement;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEquipementDto;

@ManagedBean(name = "equipementBckBean")
@SessionScoped
public class EquipementBckBean implements Serializable {

	

	private static final long serialVersionUID = 1L;

	private List<RefEquipementDto > listRefEquipementDto;
	/** @ManagedProperty("#{param.searchValue}")**/
	private String searchValue;
	
	private RefEquipementDto refEquipementDto;
	

	public EquipementBckBean() {
		super();

	}


	public List<RefEquipementDto> getListRefEquipementDto() {
		return listRefEquipementDto;
	}

	public void setListRefEquipementDto(List<RefEquipementDto> listRefEquipementDto) {
		this.listRefEquipementDto = listRefEquipementDto;
	}


	public RefEquipementDto getRefEquipementDto() {
		return refEquipementDto;
	}


	public void setRefEquipementDto(RefEquipementDto refEquipementDto) {
		this.refEquipementDto = refEquipementDto;
	}
	
	public String getSearchValue() {
		return searchValue;
	}


	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	public String back() {
		return "equipementSearch";
	}
	
	public String backback() {
		return "toEquipementReservation";
	}
	


}
