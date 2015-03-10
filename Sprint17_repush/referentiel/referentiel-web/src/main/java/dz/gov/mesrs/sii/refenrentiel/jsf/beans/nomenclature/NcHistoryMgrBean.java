/**
 * [dz.gov.mesrs.sii.refenrentiel.jsf.beans.nomenclature.HistoryMgrBean.java] 
 * @author MAKERRI Sofiane on : 21 janv. 2014  12:07:28
 */
package dz.gov.mesrs.sii.refenrentiel.jsf.beans.nomenclature;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import dz.gov.mesrs.sii.referentiel.business.model.dto.NcHistoryDto;
import dz.gov.mesrs.sii.referentiel.business.service.NcHistoryService;

/**
 * @author MAKERRI Sofiane on : 21 janv. 2014 12:07:28
 */
@ManagedBean(name = "ncHistoryMgrBean")
@RequestScoped
public class NcHistoryMgrBean implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author MAKERRI Sofiane on : 21 janv. 2014 12:07:54
	 */
	private static final long serialVersionUID = 1L;
	@ManagedProperty(value = "#{ncHistoryServiceImpl}")
	private NcHistoryService ncHistoryServiceImpl;
	private List<NcHistoryDto> listNcHistoryDto;
	private Integer idNomenclature;
	private String params; 
	
	/**
	 * [HistoryMgrBean.HistoryMgrBean()] Constructor
	 * 
	 * @author MAKERRI Sofiane on : 21 janv. 2014 12:07:28
	 */
	public NcHistoryMgrBean() {
		super();
		
	}

	/**
	 * [NcHistoryMgrBean.ncHistoryServiceImpl] Getter 
	 * @author MAKERRI Sofiane on : 27 janv. 2014  14:10:34
	 * @return the ncHistoryServiceImpl
	 */
	public NcHistoryService getNcHistoryServiceImpl() {
		return ncHistoryServiceImpl;
	}

	/**
	 * [NcHistoryMgrBean.ncHistoryServiceImpl] Setter 
	 * @author MAKERRI Sofiane on : 27 janv. 2014  14:10:34
	 * @param ncHistoryServiceImpl the ncHistoryServiceImpl to set
	 */
	public void setNcHistoryServiceImpl(NcHistoryService ncHistoryServiceImpl) {
		this.ncHistoryServiceImpl = ncHistoryServiceImpl;
	}

	/**
	 * [NcHistoryMgrBean.listNcHistoryDto] Getter 
	 * @author MAKERRI Sofiane on : 27 janv. 2014  14:10:34
	 * @return the listNcHistoryDto
	 */
	public List<NcHistoryDto> getListNcHistoryDto() {
        try {		
		    listNcHistoryDto = ncHistoryServiceImpl.findByIdRef(idNomenclature);
        } catch(RuntimeException e) {
        	listNcHistoryDto = null;
        }
		return listNcHistoryDto;
	}

	/**
	 * [NcHistoryMgrBean.listNcHistoryDto] Setter 
	 * @author MAKERRI Sofiane on : 27 janv. 2014  14:10:34
	 * @param listNcHistoryDto the listNcHistoryDto to set
	 */
	public void setListNcHistoryDto(List<NcHistoryDto> listNcHistoryDto) {
		this.listNcHistoryDto = listNcHistoryDto;
	}

	
	/**
	 * [NcHistoryMgrBean.params] Method 
	 * @author MAKERRI Sofiane  on : 27 janv. 2014  14:34:32
	 * @param paramName
	 */
	public void params(Integer idNomenclature) {
		setIdNomenclature(idNomenclature);
	}

	/**
	 * [NcHistoryMgrBean.idNomenclature] Getter 
	 * @author MAKERRI Sofiane on : 27 janv. 2014  14:36:01
	 * @return the idNomenclature
	 */
	public Integer getIdNomenclature() {
		return idNomenclature;
	}

	/**
	 * [NcHistoryMgrBean.idNomenclature] Setter 
	 * @author MAKERRI Sofiane on : 27 janv. 2014  14:36:01
	 * @param idNomenclature the idNomenclature to set
	 */
	public void setIdNomenclature(Integer idNomenclature) {
		this.idNomenclature = idNomenclature;
	}

	/**
	 * [NcHistoryMgrBean.params] Getter 
	 * @author MAKERRI Sofiane on : 27 janv. 2014  15:32:52
	 * @return the params
	 */
	public String getParams() {
		return params;
	}

	/**
	 * [NcHistoryMgrBean.params] Setter 
	 * @author MAKERRI Sofiane on : 27 janv. 2014  15:32:52
	 * @param params the params to set
	 */
	public void setParams(String params) {
		this.params = params;
	}
	
	

}
