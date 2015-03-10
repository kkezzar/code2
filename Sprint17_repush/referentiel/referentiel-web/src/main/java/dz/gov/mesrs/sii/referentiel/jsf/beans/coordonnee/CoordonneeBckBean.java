/**
 * [dz.gov.mesrs.sii.referentiel.jsf.beans.coordonnee.CoordonneeBckBean.java] 
 * @author MAKERRI Sofiane on : 5 mai 2014  15:23:15
 */
package dz.gov.mesrs.sii.referentiel.jsf.beans.coordonnee;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefAdresseDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefAdresseElectroniqueDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefCoordonneeDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefTelephoneDto;

/**
 * @author MAKERRI Sofiane  on : 5 mai 2014  15:23:15
 */
@ManagedBean(name = "coordonneeBckBean")
@SessionScoped
public class CoordonneeBckBean implements Serializable {

	/**
	 * serialVersionUID 
	 * @author MAKERRI Sofiane  on : 5 mai 2014  15:24:04
	 */
	private static final long serialVersionUID = 1L;
	private List<RefCoordonneeDto> listRefCoordonneeDto;
	private List<RefAdresseDto> listRefAdresseDto;
	private List<RefTelephoneDto> listRefTelephoneDto;
	private List<RefAdresseElectroniqueDto> listRefAdresseElectroniqueDto;
	private Integer id;

	/**
	 * [CoordonneeBckBean.CoordonneeBckBean()] Constructor
	 * @author MAKERRI Sofiane  on : 5 mai 2014  15:23:15	
	 */
	public CoordonneeBckBean() {
		super();
	}

	/**
	 * [CoordonneeBckBean.listRefAdresseDto] Getter 
	 * @author MAKERRI Sofiane on : 5 mai 2014  15:25:18
	 * @return the listRefAdresseDto
	 */
	public List<RefAdresseDto> getListRefAdresseDto() {
		return listRefAdresseDto;
	}

	/**
	 * [CoordonneeBckBean.listRefAdresseDto] Setter 
	 * @author MAKERRI Sofiane on : 5 mai 2014  15:25:18
	 * @param listRefAdresseDto the listRefAdresseDto to set
	 */
	public void setListRefAdresseDto(List<RefAdresseDto> listRefAdresseDto) {
		this.listRefAdresseDto = listRefAdresseDto;
	}

	/**
	 * [CoordonneeBckBean.listRefTelephoneDto] Getter 
	 * @author MAKERRI Sofiane on : 5 mai 2014  15:25:18
	 * @return the listRefTelephoneDto
	 */
	public List<RefTelephoneDto> getListRefTelephoneDto() {
		return listRefTelephoneDto;
	}

	/**
	 * [CoordonneeBckBean.listRefTelephoneDto] Setter 
	 * @author MAKERRI Sofiane on : 5 mai 2014  15:25:18
	 * @param listRefTelephoneDto the listRefTelephoneDto to set
	 */
	public void setListRefTelephoneDto(List<RefTelephoneDto> listRefTelephoneDto) {
		this.listRefTelephoneDto = listRefTelephoneDto;
	}

	/**
	 * [CoordonneeBckBean.listRefAdresseElectroniqueDto] Getter 
	 * @author MAKERRI Sofiane on : 5 mai 2014  15:25:18
	 * @return the listRefAdresseElectroniqueDto
	 */
	public List<RefAdresseElectroniqueDto> getListRefAdresseElectroniqueDto() {
		return listRefAdresseElectroniqueDto;
	}

	/**
	 * [CoordonneeBckBean.listRefAdresseElectroniqueDto] Setter 
	 * @author MAKERRI Sofiane on : 5 mai 2014  15:25:18
	 * @param listRefAdresseElectroniqueDto the listRefAdresseElectroniqueDto to set
	 */
	public void setListRefAdresseElectroniqueDto(
			List<RefAdresseElectroniqueDto> listRefAdresseElectroniqueDto) {
		this.listRefAdresseElectroniqueDto = listRefAdresseElectroniqueDto;
	}
	
	/**
	 * [CoordonneeBckBean.addAdresseDto] Method 
	 * @author MAKERRI Sofiane  on : 5 mai 2014  17:20:38
	 * @param refAdresseDto
	 */
	public void addAdresseDto(RefAdresseDto refAdresseDto) {
		if (listRefAdresseDto == null) {
			listRefAdresseDto = new ArrayList<RefAdresseDto>();
		}
		listRefAdresseDto.add(refAdresseDto);
	}
	
	/**
	 * [CoordonneeBckBean.deleteAdresseDto] Method 
	 * @author MAKERRI Sofiane  on : 5 mai 2014  17:37:13
	 * @param refAdresseDto
	 */
	public void deleteAdresseDto(RefAdresseDto refAdresseDto) {
		if (listRefAdresseDto != null) {
			listRefAdresseDto.remove(refAdresseDto);
		}
	}
	
	/**
	 * [CoordonneeBckBean.addTelephoneDto] Method 
	 * @author MAKERRI Sofiane  on : 5 mai 2014  17:21:23
	 * @param refTelephoneDto
	 */
	public void addTelephoneDto(RefTelephoneDto refTelephoneDto) {
		if (listRefTelephoneDto == null) {
			listRefTelephoneDto = new ArrayList<RefTelephoneDto>();
		}
		listRefTelephoneDto.add(refTelephoneDto);
	}
	
	/**
	 * [CoordonneeBckBean.deleteTelephoneDto] Method 
	 * @author MAKERRI Sofiane  on : 5 mai 2014  17:38:27
	 * @param refTelephoneDto
	 */
	public void deleteTelephoneDto(RefTelephoneDto refTelephoneDto) {
		if (listRefTelephoneDto != null) {
			listRefTelephoneDto.remove(refTelephoneDto);
		}
	}
	
	/**
	 * [CoordonneeBckBean.addAdresseElectroniqueDto] Method 
	 * @author MAKERRI Sofiane  on : 5 mai 2014  17:22:18
	 * @param refAdresseElectroniqueDto
	 */
	public void addAdresseElectroniqueDto(RefAdresseElectroniqueDto refAdresseElectroniqueDto) {
		if (listRefAdresseElectroniqueDto == null) {
			listRefAdresseElectroniqueDto = new ArrayList<RefAdresseElectroniqueDto>();
		}
		listRefAdresseElectroniqueDto.add(refAdresseElectroniqueDto);
	}
	
	/**
	 * [CoordonneeBckBean.deleteAdresseElectroniqueDto] Method 
	 * @author MAKERRI Sofiane  on : 5 mai 2014  17:40:04
	 * @param refAdresseElectroniqueDto
	 */
	public void deleteAdresseElectroniqueDto(RefAdresseElectroniqueDto refAdresseElectroniqueDto) {
		if (listRefAdresseElectroniqueDto != null) {
			listRefAdresseElectroniqueDto.remove(refAdresseElectroniqueDto);
		}
		
	}
	
	
	
	/**
	 * [CoordonneeBckBean.listRefCoordonneeDto] Getter 
	 * @author MAKERRI Sofiane on : 6 mai 2014  08:45:19
	 * @return the listRefCoordonneeDto
	 */
	public List<RefCoordonneeDto> getListRefCoordonneeDto() {
		return listRefCoordonneeDto;
	}

	/**
	 * [CoordonneeBckBean.listRefCoordonneeDto] Setter 
	 * @author MAKERRI Sofiane on : 6 mai 2014  08:45:19
	 * @param listRefCoordonneeDto the listRefCoordonneeDto to set
	 */
	public void setListRefCoordonneeDto(List<RefCoordonneeDto> listRefCoordonneeDto) {
		this.listRefCoordonneeDto = listRefCoordonneeDto;
	}
	
	
	/**
	 * [CoordonneeBckBean.addCoordonneeDto] Method 
	 * @author MAKERRI Sofiane  on : 6 mai 2014  08:46:47
	 * @param refCoordonneeDto
	 */
	public void addCoordonneeDto(RefCoordonneeDto refCoordonneeDto) {
		if (listRefCoordonneeDto == null) {
			listRefCoordonneeDto = new ArrayList<RefCoordonneeDto>();
		}
		listRefCoordonneeDto.add(refCoordonneeDto);
	}
	
	/**
	 * [CoordonneeBckBean.deleteCoordonneeDto] Method 
	 * @author MAKERRI Sofiane  on : 6 mai 2014  08:47:42
	 * @param refCoordonneeDto
	 */
	public void deleteCoordonneeDto(RefCoordonneeDto refCoordonneeDto) {
		if (listRefCoordonneeDto != null) {
			listRefCoordonneeDto.remove(refCoordonneeDto);
		}
		
	}
	
	

	/**
	 * [CoordonneeBckBean.id] Getter 
	 * @author MAKERRI Sofiane on : 18 mai 2014  09:58:54
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * [CoordonneeBckBean.id] Setter 
	 * @author MAKERRI Sofiane on : 18 mai 2014  09:58:54
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	@PreDestroy
	public void destroy() {
		System.out.println("destroy coordonneeBckBean");
	}

}
