/**
 * [dz.gov.mesrs.sii.commons.web.jsf.bean.AffectationBean.java] 
 * @author MAKERRI Sofiane on : 28 janv. 2015  14:26:41
 */
package dz.gov.mesrs.sii.commons.web.jsf.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefAffectationDto;

/**
 * @author MAKERRI Sofiane on : 28 janv. 2015 14:26:41
 */
@ManagedBean(name = "affectationBean")
@ViewScoped
public class AffectationBean extends CommonBaseBean {

	/**
	 * serialVersionUID
	 * 
	 * @author MAKERRI Sofiane on : 28 janv. 2015 14:27:52
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * [AffectationBean.AffectationBean()] Constructor
	 * 
	 * @author MAKERRI Sofiane on : 28 janv. 2015 14:26:41
	 */
	public AffectationBean() {

	}

	/**
	 * [AffectationBean.findRefAffectationByRoleCode] Method
	 * 
	 * @author MAKERRI Sofiane on : 28 janv. 2015 14:33:07
	 * @param roleCode
	 * @return
	 */
	public RefAffectationDto findRefAffectationByRoleCode(String roleCode) {
		if (roleCode == null || roleCode.isEmpty()) {
			return null;
		}
		if (sessionBean.getRefAffectationDtos() != null) {
			for (RefAffectationDto refAffectationDto : sessionBean
					.getRefAffectationDtos()) {
				if (refAffectationDto.getRoleCode() != null
						&& refAffectationDto.getRoleCode().trim().toLowerCase()
								.equals(roleCode.trim().toLowerCase())
						&& refAffectationDto.getIdRefEtablissement() != null
						&& sessionBean.getIdEtablissement() != null
						&& refAffectationDto.getIdRefEtablissement().equals(
								sessionBean.getIdEtablissement())) {
					return refAffectationDto;
				}
			}
		}
		return null;
	}
	
	
	/**
	 * [AffectationBean.affectationChanged] Method 
	 * @author MAKERRI Sofiane  on : 1 févr. 2015  14:12:59
	 */
	public void affectationChanged() {
		
	}

}
