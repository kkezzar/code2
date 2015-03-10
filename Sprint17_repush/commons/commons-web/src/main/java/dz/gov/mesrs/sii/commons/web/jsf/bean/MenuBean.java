package dz.gov.mesrs.sii.commons.web.jsf.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;

import dz.gov.mesrs.sii.commons.security.userdetails.ProgresUser;
import dz.gov.mesrs.sii.commons.web.util.CommonConfigHolder;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefCompteDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefFonctionDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefModuleDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefPermissionDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefDomaineService;
import dz.gov.mesrs.sii.referentiel.business.service.RefFonctionService;
import dz.gov.mesrs.sii.referentiel.business.service.RefModuleService;
import dz.gov.mesrs.sii.referentiel.business.service.RefPermissionService;

/**
 * Gere le menu de l'application
 * 
 * @author MAKERRI Sofiane on : 3 févr. 2015 09:16:31
 * @author Mounir.MESSAOUDI on : 08 Fev. 2015 15:53:25
 */
@ManagedBean(name = "menuBean")
@SessionScoped
public class MenuBean implements Serializable {

	private static final long serialVersionUID = 1L;

	protected final Logger logger = LoggerFactory.getLogger(MenuBean.class.getName());

	private List<DefaultSubMenu> listSubMenu;

	private String language;

	@ManagedProperty(value = "#{applicationBean}")
	private ApplicationBean applicationBean;

	private RefCompteDto compte;

	@ManagedProperty(value = "#{refPermissionServiceImpl}")
	private RefPermissionService refPermissionService;

	@ManagedProperty(value = "#{refFonctionServiceImpl}")
	private RefFonctionService refFonctionService;

	@ManagedProperty(value = "#{refDomaineServiceImpl}")
	private RefDomaineService refDomaineService;

	@ManagedProperty(value = "#{refModuleServiceImpl}")
	private RefModuleService refModuleService;

	@ManagedProperty(value = "#{commonConfigHolder}")
	private CommonConfigHolder configHolder;

	public MenuBean() {
		super();
		try {
			ProgresUser currentUser = (ProgresUser) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
			compte = currentUser.getCompte();
		} catch (Exception ex) {
			logger.error("Une erreur s'est produite lors de l'instanciation", ex);
		}
	}

	public void load() {
		ProgresUser currentUser = (ProgresUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		compte = currentUser.getCompte();

		if (compte.getAdmin()) {
			loadAdminMenu();
		} else {
			loadMenu(currentUser.getCurrentRole());
		}
	}

	public String getViewId() {
		FacesContext fc = FacesContext.getCurrentInstance();
		String viewId = fc.getViewRoot().getViewId();
		String selectedComponent;
		if (viewId != null) {
			selectedComponent = viewId.substring(viewId.lastIndexOf("/") + 1, viewId.lastIndexOf("."));
		} else {
			selectedComponent = null;
		}
		return selectedComponent;
	}

	private String getViewId(String url) {
		if (url != null) {
			if (url != null) {
				return url.substring(url.lastIndexOf("/") + 1, url.lastIndexOf("."));
			}
		}
		return null;
	}

	public String getMenuitemStyleClass(String page) {
		String viewId = getViewId();
		if (viewId != null && viewId.equals(page)) {
			return "ui-state-active active";
		}
		return "";
	}

	public List<DefaultSubMenu> getListSubMenu() {
		if (listSubMenu == null)
			load();

		return listSubMenu;
	}

	public void setListSubMenu(List<DefaultSubMenu> listSubMenu) {
		this.listSubMenu = listSubMenu;
	}

	private void loadMenu(NomenclatureDto role) {
		try {
			if (role != null) {
				String appCode = applicationBean.getAppCode();
				Integer idRole = role.getId();

				List<RefPermissionDto> listRefPermissionDto = refPermissionService.findByIdRole(appCode, idRole);
				listSubMenu = new ArrayList<DefaultSubMenu>();
				for (RefPermissionDto refPermissionDto : listRefPermissionDto) {
					if (refPermissionDto.getConsulter()) {

						DefaultMenuItem item = new DefaultMenuItem(findNomFonction(refPermissionDto));
						item.setId(refPermissionDto.getIdFonction().toString());
						item.setUrl(refPermissionDto.getUrlFonction() + "?idf=" + refPermissionDto.getIdFonction()
								+ "&idr=" + idRole);
						item.setStyleClass(getViewId(refPermissionDto.getUrlFonction()));
						DefaultSubMenu subMenu = findSubMenu(findNomModule(refPermissionDto));
						if (subMenu == null) {
							subMenu = new DefaultSubMenu(findNomModule(refPermissionDto));
							subMenu.setIcon(refPermissionDto.getIconModule());
							subMenu.setId(refPermissionDto.getIdModule().toString());
							listSubMenu.add(subMenu);
						}
						List<MenuElement> items = subMenu.getElements();
						boolean itemAlreadyAdded = false;
						for (MenuElement _item : items) {
							if (_item.getId().equals(item.getId())) {
								itemAlreadyAdded = true;
								break;
							}
						}
						if (!itemAlreadyAdded) {
							subMenu.addElement(item);
						}

					}
				}

			}
		} catch (Exception e) {
			logger.error("Une erreur s'est produite lors de la contruction du menu", e);
		}
	}

	private void loadAdminMenu() {
		try {
			listSubMenu = new ArrayList<DefaultSubMenu>();
			// model = new DefaultMenuModel();
			String appCode = applicationBean.getAppCode();
			List<RefFonctionDto> listRefFonctionDto = refFonctionService.findAllFonctions(appCode);
			// RefMenuInfo.init();
			for (RefFonctionDto refFonctionDto : listRefFonctionDto) {

				DefaultMenuItem item = new DefaultMenuItem(findNomFonction(refFonctionDto));
				item.setId(refFonctionDto.getId().toString());
				item.setUrl(refFonctionDto.getUrl() + "?idf=" + refFonctionDto.getId());
				item.setStyleClass(getViewId(refFonctionDto.getUrl()));
				DefaultSubMenu subMenu = findSubMenu(findNomModule(refFonctionDto));
				if (subMenu == null) {
					subMenu = new DefaultSubMenu(findNomModule(refFonctionDto));

					// item.setStyleClass(getViewId(refPermissionDto.getUrlFonction()));
					subMenu.setIcon(refFonctionDto.getIconModule());
					// itemq.setTitle(refPermissionDto.getTooltipModule());
					if (refFonctionDto.getIdModule() != null) {
						subMenu.setId(refFonctionDto.getIdModule().toString());
					}
					listSubMenu.add(subMenu);

				}
				List<MenuElement> items = subMenu.getElements();
				boolean itemAlreadyAdded = false;
				for (MenuElement _item : items) {
					if (_item.getId().equals(item.getId())) {
						itemAlreadyAdded = true;
						break;
					}
				}
				if (!itemAlreadyAdded) {
					subMenu.addElement(item);
				}
			}
		} catch (Exception e) {
			logger.error("Une erreur s'est produite lors de la contruction du menu", e);
		}
	}

	public String findNomFonction(RefPermissionDto _refPermissionDto) {
		if (_refPermissionDto != null) {
			// if (language!= null && language.equals("ar")) {
			// return _refPermissionDto.getNomArabeFonction();
			// } else
			{
				return _refPermissionDto.getNomLatinFonction();
			}
		}
		return null;
	}

	public String findNomFonction(RefFonctionDto _refFonctionDto) {
		if (_refFonctionDto != null) {
			if (language != null && language.equals("ar")) {
				return _refFonctionDto.getNomArabe();
			} else {
				return _refFonctionDto.getNom();
			}
		}
		return null;
	}

	public String findNomModule(RefPermissionDto _refPermissionDto) {
		if (_refPermissionDto != null) {
			// if (language!= null && language.equals("ar")) {
			// return _refPermissionDto.getNomArabeModule();
			// } else
			{
				return _refPermissionDto.getNomLatinModule();
			}
		}
		return null;
	}

	public String findNomModule(RefFonctionDto _refFonctionDto) {
		if (_refFonctionDto != null) {
			if (language != null && language.equals("ar")) {
				return _refFonctionDto.getNomArabeModule();
			} else {
				return _refFonctionDto.getNomModule();
			}
		}
		return null;
	}

	public DefaultSubMenu findSubMenu(String label) {
		if (listSubMenu != null) {
			for (DefaultSubMenu subMenu : listSubMenu) {
				if (StringEscapeUtils.escapeJava(subMenu.getLabel()).equals(StringEscapeUtils.escapeJava(label))) {
					return subMenu;
				}
			}
		}
		return null;
	}

	public String getHelpUrl() {
		String result = "";
		// String wikiBaseUrl = "http://192.168.3.135/help/index.php/";
		String wikiBaseUrl = configHolder.getHelpSiteUrl();
		String domaineWikiTemplateName = refDomaineService.findByName(applicationBean.getAppCode())
				.getHelpTemplateName();
		if (StringUtils.isEmpty(domaineWikiTemplateName)) {
			return wikiBaseUrl;
		} else {
			result += wikiBaseUrl + domaineWikiTemplateName + ":";
			String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
			if (viewId.contains("index.html")) {
				result += domaineWikiTemplateName;
				return result;
			}
			String moduleHelpPage = retrieveModuleHelpPage(viewId);
			if (StringUtils.isEmpty(moduleHelpPage)) {
				result += domaineWikiTemplateName;
				return result;
			} else {
				result += moduleHelpPage;
			}

			String fonctionAnchor = retrieveFonctionAnchor(viewId);
			if (!StringUtils.isEmpty(fonctionAnchor)) {
				result += "#" + fonctionAnchor;
			}

		}

		return result;

	}

	private String retrieveFonctionAnchor(String viewId) {
		String result = null;
		if (listSubMenu == null) {
			return result;
		}
		for (DefaultSubMenu subMenu : listSubMenu) {
			Integer fonctionId = null;
			for (MenuElement menuItem : subMenu.getElements()) {

				if (((DefaultMenuItem) menuItem).getUrl().contains(viewId)) {
					fonctionId = Integer.valueOf(((DefaultMenuItem) menuItem).getId());
					break;
				}
			}
			if (fonctionId != null) {
				RefFonctionDto fonctionDto = refFonctionService.findById(fonctionId);
				result = fonctionDto.getHelpAnchor();
			}

		}
		return result;
	}

	private String retrieveModuleHelpPage(String viewId) {
		String result = null;
		if (listSubMenu == null) {
			return result;
		}
		for (DefaultSubMenu subMenu : listSubMenu) {
			boolean found = false;
			for (MenuElement menuItem : subMenu.getElements()) {

				if (((DefaultMenuItem) menuItem).getUrl().contains(viewId)) {
					found = true;
					break;
				}
			}
			if (found) {
				RefModuleDto moduleDto = refModuleService.findById(Integer.valueOf(subMenu.getId()));
				result = moduleDto.getHelpPage();
				break;
			}
		}
		return result;
	}

	// Getters & Setters

	public void setApplicationBean(ApplicationBean applicationBean) {
		this.applicationBean = applicationBean;
	}

	public RefPermissionService getRefPermissionService() {
		return refPermissionService;
	}

	public void setRefPermissionService(RefPermissionService refPermissionService) {
		this.refPermissionService = refPermissionService;
	}

	public RefFonctionService getRefFonctionService() {
		return refFonctionService;
	}

	public void setRefFonctionService(RefFonctionService refFonctionService) {
		this.refFonctionService = refFonctionService;
	}

	public void setRefDomaineService(RefDomaineService refDomaineService) {
		this.refDomaineService = refDomaineService;
	}

	public void setRefModuleService(RefModuleService refModuleService) {
		this.refModuleService = refModuleService;
	}

	public void setConfigHolder(CommonConfigHolder configHolder) {
		this.configHolder = configHolder;
	}
}