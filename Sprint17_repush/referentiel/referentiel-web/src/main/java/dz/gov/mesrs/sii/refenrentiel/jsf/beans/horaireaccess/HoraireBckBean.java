
package dz.gov.mesrs.sii.refenrentiel.jsf.beans.horaireaccess;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;




import dz.gov.mesrs.sii.referentiel.business.model.dto.RefHoraireAccessDto;


/**
 * @author obelbrik  on : 25 févr. 2014  09:23:30
 * horaire backing bean 
 */
@ManagedBean(name = "horaireBckBean")
@SessionScoped
public class HoraireBckBean implements Serializable {

	
	/**
	 * serialVersionUID 
	 * @author BELBRIK Oualid  on : 25 févr. 2014  09:14:39
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * listRefhoraireaccessDto 
	 * @author BELBRIK Oualid  on : 25 févr. 2014  09:14:50
	 */
	private List<RefHoraireAccessDto > listRefHoraireAccessDto;
	
	/**
	 * refHoraireAccessDto 
	 * @author BELBRIK Oualid  on : 25 févr. 2014  09:14:46
	 */
	private RefHoraireAccessDto refHoraireAccessDto;
	
	private String searchValue;
	
	

	/**
	 * 
	 * @author BELBRIK Oualid  on : 25 févr. 2014  09:28:06	
	 */
	public HoraireBckBean() {
		super();

	}





	/**
	 *
	 * @author BELBRIK Oualid on : 25 févr. 2014  09:15:11
	 * 
	 */
	public List<RefHoraireAccessDto> getListRefHoraireAccessDto() {
		return listRefHoraireAccessDto;
	}





	/**
	 * 
	 * @author BELBRIK Oualid on : 25 févr. 2014  09:15:11
	 * 
	 */
	public void setListRefHoraireAccessDto(List<RefHoraireAccessDto> listRefHoraireAccessDto) {
		this.listRefHoraireAccessDto = listRefHoraireAccessDto;
	}





	/**
	 * 
	 * @author BELBRIK Oualid on : 25 févr. 2014  09:15:11
	 * 
	 */
	public RefHoraireAccessDto getRefHoraireAccessDto() {
		return refHoraireAccessDto;
	}





	/**
	 * 
	 * @author BELBRIK Oualid on : 25 févr. 2014  09:15:11
	 * 
	 */
	public void setRefHoraireAccessDto(RefHoraireAccessDto refHoraireAccessDto) {
		this.refHoraireAccessDto = refHoraireAccessDto;
	}





	public String getSearchValue() {
		return searchValue;
	}





	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	public String back() {
		return "horaireAccessSearch";
	}


}
