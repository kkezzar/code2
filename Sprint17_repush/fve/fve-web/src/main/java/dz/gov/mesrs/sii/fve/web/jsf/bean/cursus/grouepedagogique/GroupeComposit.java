/**
 * [dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.grouepedagogique.GroupeComposit.java] 
 * @author MAKERRI Sofiane on : 11 oct. 2014  18:17:32
 */
package dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.grouepedagogique;

import java.io.Serializable;
import java.util.List;

import dz.gov.mesrs.sii.fve.business.model.dto.cursus.GroupePedagogiqueDto;

/**
 * @author MAKERRI Sofiane  on : 11 oct. 2014  18:17:32
 */
public class GroupeComposit implements Serializable {

	/**
	 * serialVersionUID 
	 * @author MAKERRI Sofiane  on : 11 oct. 2014  18:17:37
	 */
	private static final long serialVersionUID = 1L;
	private GroupePedagogiqueDto section;
	private List<GroupePedagogiqueDto> groupes;

	/**
	 * [GroupeComposit.GroupeComposit()] Constructor
	 * @author MAKERRI Sofiane  on : 11 oct. 2014  18:17:32	
	 */
	public GroupeComposit() {
		super();
	}

	/**
	 * [GroupeComposit.section] Getter 
	 * @author MAKERRI Sofiane on : 11 oct. 2014  18:18:21
	 * @return the section
	 */
	public GroupePedagogiqueDto getSection() {
		return section;
	}

	/**
	 * [GroupeComposit.section] Setter 
	 * @author MAKERRI Sofiane on : 11 oct. 2014  18:18:21
	 * @param section the section to set
	 */
	public void setSection(GroupePedagogiqueDto section) {
		this.section = section;
	}

	/**
	 * [GroupeComposit.groupes] Getter 
	 * @author MAKERRI Sofiane on : 11 oct. 2014  18:18:21
	 * @return the groupes
	 */
	public List<GroupePedagogiqueDto> getGroupes() {
		return groupes;
	}

	/**
	 * [GroupeComposit.groupes] Setter 
	 * @author MAKERRI Sofiane on : 11 oct. 2014  18:18:21
	 * @param groupes the groupes to set
	 */
	public void setGroupes(List<GroupePedagogiqueDto> groupes) {
		this.groupes = groupes;
	}

}
