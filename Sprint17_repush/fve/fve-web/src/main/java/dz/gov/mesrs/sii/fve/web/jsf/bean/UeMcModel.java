/**
 * [dz.gov.mesrs.sii.fve.web.jsf.bean.UeMcModel.java] 
 * @author MAKERRI Sofiane on : 13 oct. 2014  18:04:23
 */
package dz.gov.mesrs.sii.fve.web.jsf.bean;

import java.io.Serializable;
import java.util.List;

import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.RattachementMcDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.UniteEnseignementDto;

/**
 * @author MAKERRI Sofiane  on : 13 oct. 2014  18:04:23
 */
public class UeMcModel implements Serializable{

	/**
	 * serialVersionUID 
	 * @author MAKERRI Sofiane  on : 13 oct. 2014  18:04:34
	 */
	private static final long serialVersionUID = 1L;
	private UniteEnseignementDto ue;
	private List<RattachementMcDto> mc;

	/**
	 * [UeMcModel.UeMcModel()] Constructor
	 * @author MAKERRI Sofiane  on : 13 oct. 2014  18:04:23	
	 */
	public UeMcModel() {
		super();
	}
	
	/**
	 * [UeMcModel.ue] Getter 
	 * @author MAKERRI Sofiane on : 13 oct. 2014  18:06:20
	 * @return the ue
	 */
	public UniteEnseignementDto getUe() {
		return ue;
	}

	/**
	 * [UeMcModel.ue] Setter 
	 * @author MAKERRI Sofiane on : 13 oct. 2014  18:06:20
	 * @param ue the ue to set
	 */
	public void setUe(UniteEnseignementDto ue) {
		this.ue = ue;
	}

	/**
	 * [UeMcModel.mc] Getter 
	 * @author MAKERRI Sofiane on : 13 oct. 2014  18:06:20
	 * @return the mc
	 */
	public List<RattachementMcDto> getMc() {
		return mc;
	}

	/**
	 * [UeMcModel.mc] Setter 
	 * @author MAKERRI Sofiane on : 13 oct. 2014  18:06:20
	 * @param mc the mc to set
	 */
	public void setMc(List<RattachementMcDto> mc) {
		this.mc = mc;
	}
	
	

}
