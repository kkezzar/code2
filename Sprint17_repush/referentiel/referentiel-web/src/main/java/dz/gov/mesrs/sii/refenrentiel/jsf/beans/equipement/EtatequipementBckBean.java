
package dz.gov.mesrs.sii.refenrentiel.jsf.beans.equipement;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEtatEquipementDto;



@ManagedBean(name = "etatequipementBckBean")
@SessionScoped
public class EtatequipementBckBean implements Serializable {

	
	
	private static final long serialVersionUID = 1L;

	private List<RefEtatEquipementDto > listRefEtatEquipementDto;
	

	private RefEtatEquipementDto refEtatEquipementDto;
	

	public EtatequipementBckBean() {
		super();

	}


	public List<RefEtatEquipementDto> getListRefEtatEquipementDto() {
		return listRefEtatEquipementDto;
	}


	public void setListRefEtatEquipementDto(List<RefEtatEquipementDto> listRefEtatEquipementDto) {
		this.listRefEtatEquipementDto = listRefEtatEquipementDto;
	}


	public RefEtatEquipementDto getRefEtatEquipementDto() {
		return refEtatEquipementDto;
	}

	public void setRefEtatEquipementDto(RefEtatEquipementDto refEtatEquipementDto) {
		this.refEtatEquipementDto = refEtatEquipementDto;
	}
	

}
