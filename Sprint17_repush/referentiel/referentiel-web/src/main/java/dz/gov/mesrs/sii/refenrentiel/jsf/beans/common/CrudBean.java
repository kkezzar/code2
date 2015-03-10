/**
 * [dz.gov.mesrs.sii.refenrentiel.jsf.common.UtilConstant.java] 
 * @author MAKERRI Sofiane on : 4 mars 2014  16:58:43
 */
package dz.gov.mesrs.sii.refenrentiel.jsf.beans.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import dz.gov.mesrs.sii.refenrentiel.jsf.beans.permission.MenuBean;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefFonctionDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefPermissionDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefFonctionService;
import dz.gov.mesrs.sii.referentiel.business.service.RefPermissionService;

/**
 * @author MAKERRI Sofiane  on : 4 mars 2014  16:58:43
 */
/**
 * @author MAKERRI Sofiane  on : 20 mars 2014  12:28:23
 */
/**
 * @author MAKERRI Sofiane  on : 20 mars 2014  12:28:26
 */
/**
 * @author MAKERRI Sofiane  on : 20 mars 2014  12:28:29
 */
/**
 * @author MAKERRI Sofiane on : 20 mars 2014 12:28:52
 */
@ManagedBean(name = "crudBean")
@SessionScoped
public class CrudBean implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author MAKERRI Sofiane on : 5 mars 2014 10:50:03
	 */
	private static final long serialVersionUID = 1L;
	@ManagedProperty(value = "#{refPermissionServiceImpl}")
	private RefPermissionService refPermissionServiceImpl;
	@ManagedProperty(value = "#{refFonctionServiceImpl}")
	private RefFonctionService refFonctionServiceImpl;
	private RefPermissionDto refPermissionDto;
	@ManagedProperty(value = "#{menuBean}")
	private MenuBean menuBean;
	private List<RefPermissionDto> listActions;
	private Integer idFonction;

	/**
	 * [CrudBean.CrudBean()] Constructor
	 * 
	 * @author MAKERRI Sofiane on : 4 mars 2014 16:58:43
	 */
	public CrudBean() {
		menuBean = new MenuBean();
	}

	/**
	 * [CrudBean.loadCrudDroit] Method
	 * 
	 * @author MAKERRI Sofiane on : 20 mars 2014 15:22:27
	 * @return
	 */
	public List<Boolean> loadCrudDroit() {
		List<Boolean> list = new ArrayList<Boolean>();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		Map<String, String> parameters = facesContext.getExternalContext()
				.getRequestParameterMap();

		idFonction = Integer.parseInt(parameters.get("idf"));
		String idRole = parameters.get("idr");
		destroySessionBean();

		if (menuBean.isAdmin()) {
			list.add(true);
			list.add(true);
			list.add(true);
			list.add(true);
			if (idFonction != null) {
				listActions = new ArrayList<RefPermissionDto>();
				List<RefFonctionDto> listFonctions = refFonctionServiceImpl
						.findActions(idFonction);
				for (RefFonctionDto refFonctionDto : listFonctions) {
					RefPermissionDto _refPermissionDto = new RefPermissionDto();
					_refPermissionDto.setIdFonction(refFonctionDto.getId());
					// _refPermissionDto.setNomFonction(refFonctionDto.getNom());
					_refPermissionDto.setNomFonction(menuBean
							.findNomFonction(refFonctionDto));
					_refPermissionDto.setNomLatinFonction(refFonctionDto
							.getNom());
					_refPermissionDto.setNomArabeFonction(refFonctionDto
							.getNomArabe());
					_refPermissionDto.setUrlFonction(refFonctionDto.getUrl());
					_refPermissionDto.setRangFonction(refFonctionDto.getRang());
					_refPermissionDto.setConsulter(true);
					_refPermissionDto.setCreer(true);
					_refPermissionDto.setModifier(true);
					_refPermissionDto.setSupprimer(true);
					listActions.add(_refPermissionDto);
				}
			}
			return list;
		}

		try {
			if (idFonction != null && idRole != null) {
				refPermissionDto = refPermissionServiceImpl.findByIdFonction(
						Integer.parseInt(idRole), idFonction);
				if (refPermissionDto == null) {
					return null;
				}

				listActions = refPermissionServiceImpl.findActions(
						Integer.parseInt(idRole), idFonction);

			}
		} catch (Exception e) {
			return null;
		}

		String viewId = facesContext.getViewRoot().getViewId();
		if (!refPermissionDto.getUrlFonction().equals(viewId)) {
			// le idFonction ne correspond pas � l'url go to page not found
			facesContext.getExternalContext().setResponseStatus(404);
			facesContext.responseComplete();
			return null;
		}

		boolean createAllow = refPermissionDto.getCreer();
		boolean editAllow = refPermissionDto.getModifier();
		boolean deleteAllow = refPermissionDto.getSupprimer();
		boolean readAllow = refPermissionDto.getConsulter();
		list.add(createAllow);
		list.add(editAllow);
		list.add(deleteAllow);
		list.add(readAllow);
		return list;
	}

	/**
	 * [UtilConstant.refPermissionServiceImpl] Getter
	 * 
	 * @author MAKERRI Sofiane on : 5 mars 2014 10:42:36
	 * @return the refPermissionServiceImpl
	 */
	public RefPermissionService getRefPermissionServiceImpl() {
		return refPermissionServiceImpl;
	}

	/**
	 * [UtilConstant.refPermissionServiceImpl] Setter
	 * 
	 * @author MAKERRI Sofiane on : 5 mars 2014 10:42:36
	 * @param refPermissionServiceImpl
	 *            the refPermissionServiceImpl to set
	 */
	public void setRefPermissionServiceImpl(
			RefPermissionService refPermissionServiceImpl) {
		this.refPermissionServiceImpl = refPermissionServiceImpl;
	}

	/**
	 * [UtilConstant.refPermissionDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 5 mars 2014 10:42:36
	 * @return the refPermissionDto
	 */
	public RefPermissionDto getRefPermissionDto() {
		return refPermissionDto;
	}

	/**
	 * [UtilConstant.refPermissionDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 5 mars 2014 10:42:36
	 * @param refPermissionDto
	 *            the refPermissionDto to set
	 */
	public void setRefPermissionDto(RefPermissionDto refPermissionDto) {
		this.refPermissionDto = refPermissionDto;
	}

	/**
	 * [CrudBean.menuBean] Getter
	 * 
	 * @author MAKERRI Sofiane on : 11 mars 2014 11:26:39
	 * @return the menuBean
	 */
	public MenuBean getMenuBean() {
		return menuBean;
	}

	/**
	 * [CrudBean.menuBean] Setter
	 * 
	 * @author MAKERRI Sofiane on : 11 mars 2014 11:26:39
	 * @param menuBean
	 *            the menuBean to set
	 */
	public void setMenuBean(MenuBean menuBean) {
		this.menuBean = menuBean;
	}

	/**
	 * [CrudBean.listActions] Getter
	 * 
	 * @author MAKERRI Sofiane on : 20 mars 2014 11:31:58
	 * @return the listActions
	 */
	public List<RefPermissionDto> getListActions() {
		if (menuBean.isLanguageChanged()) {
			updateLanguge();
		}
		return listActions;
	}

	/**
	 * [CrudBean.listActions] Setter
	 * 
	 * @author MAKERRI Sofiane on : 20 mars 2014 11:31:58
	 * @param listActions
	 *            the listActions to set
	 */
	public void setListActions(List<RefPermissionDto> listActions) {
		this.listActions = listActions;
	}

	/**
	 * [CrudBean.refFonctionServiceImpl] Getter
	 * 
	 * @author MAKERRI Sofiane on : 20 mars 2014 14:45:38
	 * @return the refFonctionServiceImpl
	 */
	public RefFonctionService getRefFonctionServiceImpl() {
		return refFonctionServiceImpl;
	}

	/**
	 * [CrudBean.refFonctionServiceImpl] Setter
	 * 
	 * @author MAKERRI Sofiane on : 20 mars 2014 14:45:38
	 * @param refFonctionServiceImpl
	 *            the refFonctionServiceImpl to set
	 */
	public void setRefFonctionServiceImpl(
			RefFonctionService refFonctionServiceImpl) {
		this.refFonctionServiceImpl = refFonctionServiceImpl;
	}

	/**
	 * [CrudBean.updateLanguge] Method
	 * 
	 * @author MAKERRI Sofiane on : 4 mai 2014 14:12:00
	 */
	public void updateLanguge() {
		for (RefPermissionDto _refPermissionDto : listActions) {
			if (menuBean.getLanguage() != null
					&& menuBean.getLanguage().equals("ar")) {
				_refPermissionDto.setNomFonction(_refPermissionDto
						.getNomArabeFonction());
			} else {
				_refPermissionDto.setNomFonction(_refPermissionDto
						.getNomLatinFonction());
			}
		}
	}

	/**
	 * [CrudBean.destroyIndividuBean] Method destroy individu session bean after
	 * page exit
	 * 
	 * @author MAKERRI Sofiane on : 5 mai 2014 09:08:50
	 */
	public void destroySessionBean() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		Object request = facesContext.getExternalContext().getRequest();
		if (request instanceof HttpServletRequest) {
			String url = ((HttpServletRequest) request).getRequestURL()
					.toString();
			/* if(url != null && !url.contains("individuSearch")) */{
				facesContext.getExternalContext().getSessionMap()
						.remove("individuBckBean");
				facesContext.getExternalContext().getSessionMap()
						.remove("coordonneeBckBean");
			}

		}
	}

	/**
	 * [CrudBean.idFonction] Getter 
	 * @author MAKERRI Sofiane on : 18 juin 2014  11:28:17
	 * @return the idFonction
	 */
	public Integer getIdFonction() {
		return idFonction;
	}

	/**
	 * [CrudBean.idFonction] Setter 
	 * @author MAKERRI Sofiane on : 18 juin 2014  11:28:17
	 * @param idFonction the idFonction to set
	 */
	public void setIdFonction(Integer idFonction) {
		this.idFonction = idFonction;
	}
	
	

}
