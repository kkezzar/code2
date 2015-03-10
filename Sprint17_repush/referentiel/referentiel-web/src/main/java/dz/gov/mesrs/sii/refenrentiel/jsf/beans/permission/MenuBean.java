/**
 * [dz.gov.mesrs.sii.refenrentiel.jsf.beans.permission.MenuBckBean.java] 
 * @author MAKERRI Sofiane on : 3 mars 2014  11:43:46
 */
package dz.gov.mesrs.sii.refenrentiel.jsf.beans.permission;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuElement;








import dz.gov.mesrs.sii.refenrentiel.jsf.beans.authentification.LoginBean;
import dz.gov.mesrs.sii.refenrentiel.jsf.beans.common.SessionValues;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefAffectationDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefCompteDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefFonctionDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefIndividuDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefModuleDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefPermissionDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefAffectationService;
import dz.gov.mesrs.sii.referentiel.business.service.RefDocumentService;
import dz.gov.mesrs.sii.referentiel.business.service.RefDomaineService;
import dz.gov.mesrs.sii.referentiel.business.service.RefFonctionService;
import dz.gov.mesrs.sii.referentiel.business.service.RefModuleService;
import dz.gov.mesrs.sii.referentiel.business.service.RefPermissionService;
import dz.gov.mesrs.sii.referentiel.business.util.RefUtilConstant;

/**
 * @author MAKERRI Sofiane on : 3 mars 2014 11:43:46
 */
@ManagedBean(name = "menuBean")
@SessionScoped
public class MenuBean implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author MAKERRI Sofiane on : 3 mars 2014 11:57:40
	 */
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(MenuBean.class);
	@ManagedProperty(value = "#{refPermissionServiceImpl}")
	private RefPermissionService refPermissionServiceImpl;
	@ManagedProperty(value = "#{refFonctionServiceImpl}")
	private RefFonctionService refFonctionServiceImpl;
	private RefPermissionDto refPermissionDto;
	private List<RefPermissionDto> listRefPermissionDto;
	private List<RefFonctionDto> listRefFonctionDto;
	private String searchValue;
	//private MenuModel model;
	private List<DefaultSubMenu> listSubMenu;
	private RefIndividuDto user;
	private RefCompteDto compte;
	private List<RefMenuItem> dockItems;
	private boolean admin;
	@ManagedProperty(value = "#{refAffectationServiceImpl}")
	private RefAffectationService refAffectationServiceImpl;
	private List<SelectItem> listItemAffectation;
	private List<RefAffectationDto> listAffectation;
	private Integer idRole;
	private Integer idAffectation;
	private RefAffectationDto selectedAffectation;
	/*@ManagedProperty(value = "#{refPeriodeServiceImpl}")
	private RefPeriodeService refPeriodeServiceImpl;*/
	HashMap<String, Boolean>  mapModuleRendered = new HashMap<String, Boolean>();
	HashMap<String, Boolean>  mapFctRendered = new HashMap<String, Boolean>();
	HashMap<String, String>  mapFctUrl = new HashMap<String, String>();
	private static final String MESSAGES_FILE_NAME = "commonmsgs";
	private ResourceBundle bundle;
	private String language;
	private boolean languageChanged;
	@ManagedProperty(value = "#{loginBean}")
	private LoginBean loginBean;
	@ManagedProperty(value = "#{refDomaineServiceImpl}")
	private RefDomaineService refDomaineService;
	@ManagedProperty(value = "#{refModuleServiceImpl}")
	private RefModuleService refModuleService;

	/**
	 * [MenuBckBean.MenuBckBean()] Constructor
	 * 
	 * @author MAKERRI Sofiane on : 3 mars 2014 11:43:46
	 */
	public MenuBean() {
		super();
		
		
	}
	
	@PostConstruct
	public void initMenu(){
		compte = loginBean.getCompteDto();
		if (compte.getAdmin()) {
			setAdmin(true);
			loadAdminMenu();
		} else {
			setAdmin(false);
			loadAffectation();
			loadMenu();
		}
		
	}

	/**
	 * [MenuBckBean.refPermissionServiceImpl] Getter
	 * 
	 * @author MAKERRI Sofiane on : 3 mars 2014 11:46:30
	 * @return the refPermissionServiceImpl
	 */
	public RefPermissionService getRefPermissionServiceImpl() {
		return refPermissionServiceImpl;
	}

	/**
	 * [MenuBckBean.refPermissionServiceImpl] Setter
	 * 
	 * @author MAKERRI Sofiane on : 3 mars 2014 11:46:30
	 * @param refPermissionServiceImpl
	 *            the refPermissionServiceImpl to set
	 */
	public void setRefPermissionServiceImpl(
			RefPermissionService refPermissionServiceImpl) {
		this.refPermissionServiceImpl = refPermissionServiceImpl;
	}

	/**
	 * [MenuBckBean.refPermissionDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 3 mars 2014 11:46:30
	 * @return the refPermissionDto
	 */
	public RefPermissionDto getRefPermissionDto() {
		return refPermissionDto;
	}

	/**
	 * [MenuBckBean.refPermissionDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 3 mars 2014 11:46:30
	 * @param refPermissionDto
	 *            the refPermissionDto to set
	 */
	public void setRefPermissionDto(RefPermissionDto refPermissionDto) {
		this.refPermissionDto = refPermissionDto;
	}

	/**
	 * [MenuBckBean.listRefPermissionDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 3 mars 2014 11:58:56
	 * @return the listRefPermissionDto
	 */
	public List<RefPermissionDto> getListRefPermissionDto() {
		return listRefPermissionDto;
	}

	/**
	 * [MenuBckBean.listRefPermissionDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 3 mars 2014 11:58:56
	 * @param listRefPermissionDto
	 *            the listRefPermissionDto to set
	 */
	public void setListRefPermissionDto(
			List<RefPermissionDto> listRefPermissionDto) {
		this.listRefPermissionDto = listRefPermissionDto;
	}

	/**
	 * [MenuBckBean.loadMenu] Method
	 * 
	 * @author MAKERRI Sofiane on : 3 mars 2014 14:25:43
	 */
	public void loadMenu() {
		mapModuleRendered = new HashMap<String, Boolean>();
		mapFctRendered = new HashMap<String, Boolean>();
		mapFctUrl = new HashMap<String, String>();
		if (compte != null && idRole != null) {
			listRefPermissionDto = refPermissionServiceImpl.findByIdRole(
					RefUtilConstant.DOMAINE_REFERENTIEL_NAME, idRole);
			listSubMenu = new ArrayList<DefaultSubMenu>();
			dockItems = new ArrayList<RefMenuItem>();
			//model = new DefaultMenuModel();
			RefMenuInfo.init();
			for (RefPermissionDto refPermissionDto : listRefPermissionDto) {
				if (!periodeAllowed(refPermissionDto)) {
                      continue;
				}
				if (refPermissionDto.getConsulter()) {
					DefaultMenuItem item = new DefaultMenuItem(/*findLabel(refPermissionDto.getIdfFonction())*/
							findNomFonction(refPermissionDto));
					item.setId(refPermissionDto.getIdFonction().toString());
					RefMenuItem dockItem = new RefMenuItem();
					//dockItem.setInfobul(findLabel(refPermissionDto.getIdfFonction()));
					dockItem.setInfobul(findNomFonction(refPermissionDto));
					item.setUrl(refPermissionDto.getUrlFonction() + "?idf="
							+ refPermissionDto.getIdFonction() + "&idr="
							+ idRole);
					dockItem.setUrl(refPermissionDto.getUrlFonction() + "?idf="
							+ refPermissionDto.getIdFonction() + "&idr="
							+ idRole);
					mapFctRendered.put(refPermissionDto.getIdfFonction(), true);
					mapFctUrl.put(refPermissionDto.getIdfFonction(), refPermissionDto.getUrlFonction() + "?idf="
							+ refPermissionDto.getIdFonction() + "&idr="
							+ idRole);
					RefMenuItem refMenuItem = RefMenuInfo
							.findItemInfo(refPermissionDto.getUrlFonction());
					if (refMenuItem != null) {
						if (refMenuItem.getIcon() != null) {
							item.setIcon(refMenuItem.getIcon());

						}

						if (refMenuItem.getImage() != null) {
							dockItem.setImage(refMenuItem.getImage());
						}

						if (refMenuItem.getInfobul() != null) {
							dockItem.setInfobul(refMenuItem.getInfobul());
						}

						boolean dockAlreadyAdded = false;
						for (RefMenuItem _dock : dockItems) {
							if (_dock.getUrl().equals(dockItem.getUrl())) {
								dockAlreadyAdded = true;
								break;
							}
						}
						if (!dockAlreadyAdded && refMenuItem.isDock()) {
							dockItems.add(dockItem);
						}

					}
					
					DefaultSubMenu subMenu = findSubMenu(/*findLabel(refPermissionDto.getIdfModule())*/findNomModule(refPermissionDto));
					if (subMenu == null) {
						subMenu = new DefaultSubMenu(findLabel(refPermissionDto.getIdfModule()));
						subMenu = new DefaultSubMenu(findNomFonction(refPermissionDto));
						//model.addElement(subMenu);
						subMenu.setId(refPermissionDto.getIdModule().toString());
						listSubMenu.add(subMenu);
						mapModuleRendered.put(refPermissionDto.getIdfModule(), true);
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
	}

	/**
	 * [MenuBean.periodeAllowed] Method 
	 * @author MAKERRI Sofiane  on : 3 avr. 2014  12:39:13
	 * @param _refPermissionDto
	 * @return
	 */
	public boolean periodeAllowed(RefPermissionDto _refPermissionDto) {
		if (_refPermissionDto.getIdPeriode() != null) {
			try {
				boolean result = refPermissionServiceImpl.verifyPeriode(
						_refPermissionDto.getDateDebutPeriode(),
						_refPermissionDto.getDateFinPeriode(),
						_refPermissionDto.getPeriodique());
				return result;
			} catch (Exception e) {
				return false;
			}
		}
		return true;
	}

	/**
	 * [MenuBean.loadAdminMenu] Method
	 * 
	 * @author MAKERRI Sofiane on : 9 mars 2014 09:08:39
	 */
	public void loadAdminMenu() {
		mapModuleRendered = new HashMap<String, Boolean>();
		mapFctRendered = new HashMap<String, Boolean>();
		mapFctUrl = new HashMap<String, String>();
		if (compte != null && compte.getIndividuId() != null) {
			listSubMenu = new ArrayList<DefaultSubMenu>();
			dockItems = new ArrayList<RefMenuItem>();
			//model = new DefaultMenuModel();
			listRefFonctionDto = refFonctionServiceImpl
					.findAllFonctions(RefUtilConstant.DOMAINE_REFERENTIEL_NAME);
			RefMenuInfo.init();
			for (RefFonctionDto refFonctionDto : listRefFonctionDto) {

				DefaultMenuItem item = new DefaultMenuItem(/*findLabel(refFonctionDto.getIdentifiant())*/
						findNomFonction(refFonctionDto));
				item.setId(refFonctionDto.getId().toString());
				RefMenuItem dockItem = new RefMenuItem();
				//dockItem.setInfobul(findLabel(refFonctionDto.getIdentifiant()));
				dockItem.setInfobul(findNomFonction(refFonctionDto));
				item.setUrl(refFonctionDto.getUrl() + "?idf="
						+ refFonctionDto.getId());
				dockItem.setUrl(refFonctionDto.getUrl() + "?idf="
						+ refFonctionDto.getId());

				mapFctRendered.put(refFonctionDto.getIdentifiant(), true);
				mapFctUrl.put(refFonctionDto.getIdentifiant(),refFonctionDto.getUrl() + "?idf="
						+ refFonctionDto.getId());
				RefMenuItem refMenuItem = RefMenuInfo
						.findItemInfo(refFonctionDto.getUrl());
				if (refMenuItem != null) {
					if (refMenuItem.getIcon() != null) {
						item.setIcon(refMenuItem.getIcon());

					}

					if (refMenuItem.getImage() != null) {
						dockItem.setImage(refMenuItem.getImage());
					}

//					if (refMenuItem.getInfobul() != null) {
//						dockItem.setInfobul(refMenuItem.getInfobul());
//					}

					boolean dockAlreadyAdded = false;
					for (RefMenuItem _dock : dockItems) {
						if (_dock.getUrl().equals(dockItem.getUrl())) {
							dockAlreadyAdded = true;
							break;
						}
					}
					if (!dockAlreadyAdded && refMenuItem.isDock()) {
						dockItems.add(dockItem);
					}

				}
				
				//DefaultSubMenu subMenu = findSubMenu(findLabel(refFonctionDto.getIdfModule()));
				DefaultSubMenu subMenu = findSubMenu(findNomModule(refFonctionDto));
				if (subMenu == null) {
					subMenu = new DefaultSubMenu(/*findLabel(refFonctionDto.getIdfModule())*//*refFonctionDto.getNomModule(*/
							findNomModule(refFonctionDto));
					//model.addElement(subMenu);
					if (refFonctionDto.getIdModule() != null) {
						subMenu.setId(refFonctionDto.getIdModule().toString());
					}
					listSubMenu.add(subMenu);
					mapModuleRendered.put(refFonctionDto.getIdfModule(), true);

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
	public String getHelpUrl() {
        String result = "";
        String wikiBaseUrl = "http://192.168.3.135/help/index.php/";
        String domaineWikiTemplateName = refDomaineService.findByName("Referentiel").getHelpTemplateName();
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
            if(StringUtils.isEmpty(moduleHelpPage)){
                result += domaineWikiTemplateName;
                return result;
            }else{
                result += moduleHelpPage;
            }
           
            String fonctionAnchor = retrieveFonctionAnchor(viewId);
            if(!StringUtils.isEmpty(fonctionAnchor)){
                result += "#"+fonctionAnchor;
            }
           
        }

        return result;

    }

    private String retrieveFonctionAnchor(String viewId) {
        String result = null;
        if(listSubMenu == null){
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
                RefFonctionDto fonctionDto = refFonctionServiceImpl.findById(fonctionId);
                result = fonctionDto.getHelpAnchor();
            }

        }
        return result;
    }

    private String retrieveModuleHelpPage(String viewId) {
        String result = null;
        if(listSubMenu == null){
        	return result;
        }
        for(DefaultSubMenu subMenu : listSubMenu){
            boolean found = false;
            for(MenuElement menuItem : subMenu.getElements()){
               
                if(((DefaultMenuItem)menuItem).getUrl().contains(viewId)){
                    found = true;
                    break;
                }
            }
            if(found){
                RefModuleDto moduleDto = refModuleService.findById(Integer.valueOf(subMenu.getId()));
                result = moduleDto.getHelpPage();
                break;
            }
        }
        return result;
    }
	/**
	 * [MenuBckBean.findSubMenu] Method
	 * 
	 * @author MAKERRI Sofiane on : 3 mars 2014 14:25:39
	 * @param label
	 * @return
	 */
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

	/**
	 * [MenuBckBean.searchValue] Getter
	 * 
	 * @author MAKERRI Sofiane on : 3 mars 2014 12:04:33
	 * @return the searchValue
	 */
	public String getSearchValue() {
		return searchValue;
	}

	/**
	 * [MenuBckBean.searchValue] Setter
	 * 
	 * @author MAKERRI Sofiane on : 3 mars 2014 12:04:33
	 * @param searchValue
	 *            the searchValue to set
	 */
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

//	/**
//	 * [MenuBckBean.model] Getter
//	 * 
//	 * @author MAKERRI Sofiane on : 3 mars 2014 12:44:23
//	 * @return the model
//	 */
//	public MenuModel getModel() {
//		return model;
//	}
//
//	/**
//	 * [MenuBckBean.model] Setter
//	 * 
//	 * @author MAKERRI Sofiane on : 3 mars 2014 12:44:23
//	 * @param model
//	 *            the model to set
//	 */
//	public void setModel(MenuModel model) {
//		this.model = model;
//	}

	/**
	 * [MenuBckBean.listSubMenu] Getter
	 * 
	 * @author MAKERRI Sofiane on : 3 mars 2014 14:21:40
	 * @return the lisSubMenu
	 */
	public List<DefaultSubMenu> getListSubMenu() {
		return listSubMenu;
	}

	/**
	 * [MenuBckBean.listSubMenu] Setter
	 * 
	 * @author MAKERRI Sofiane on : 3 mars 2014 14:21:40
	 * @param lisSubMenu
	 *            the lisSubMenu to set
	 */
	public void setListSubMenu(List<DefaultSubMenu> listSubMenu) {
		this.listSubMenu = listSubMenu;
	}

	/**
	 * [MenuBean.user] Getter
	 * 
	 * @author MAKERRI Sofiane on : 4 mars 2014 08:41:32
	 * @return the user
	 */
	public RefIndividuDto getUser() {
		return user;
	}

	/**
	 * [MenuBean.user] Setter
	 * 
	 * @author MAKERRI Sofiane on : 4 mars 2014 08:41:32
	 * @param user
	 *            the user to set
	 */
	public void setUser(RefIndividuDto user) {
		this.user = user;
	}

	/**
	 * [MenuBean.dockItems] Getter
	 * 
	 * @author MAKERRI Sofiane on : 5 mars 2014 15:45:29
	 * @return the dockItems
	 */
	public List<RefMenuItem> getDockItems() {
		return dockItems;
	}

	/**
	 * [MenuBean.dockItems] Setter
	 * 
	 * @author MAKERRI Sofiane on : 5 mars 2014 15:45:29
	 * @param dockItems
	 *            the dockItems to set
	 */
	public void setDockItems(List<RefMenuItem> dockItems) {
		this.dockItems = dockItems;
	}

	/**
	 * [MenuBean.listRefFonctionDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 9 mars 2014 08:35:03
	 * @return the listRefFonctionDto
	 */
	public List<RefFonctionDto> getListRefFonctionDto() {
		return listRefFonctionDto;
	}

	/**
	 * [MenuBean.listRefFonctionDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 9 mars 2014 08:35:03
	 * @param listRefFonctionDto
	 *            the listRefFonctionDto to set
	 */
	public void setListRefFonctionDto(List<RefFonctionDto> listRefFonctionDto) {
		this.listRefFonctionDto = listRefFonctionDto;
	}

	/**
	 * [MenuBean.refFonctionServiceImpl] Getter
	 * 
	 * @author MAKERRI Sofiane on : 9 mars 2014 08:36:17
	 * @return the refFonctionServiceImpl
	 */
	public RefFonctionService getRefFonctionServiceImpl() {
		return refFonctionServiceImpl;
	}

	/**
	 * [MenuBean.refFonctionServiceImpl] Setter
	 * 
	 * @author MAKERRI Sofiane on : 9 mars 2014 08:36:17
	 * @param refFonctionServiceImpl
	 *            the refFonctionServiceImpl to set
	 */
	public void setRefFonctionServiceImpl(
			RefFonctionService refFonctionServiceImpl) {
		this.refFonctionServiceImpl = refFonctionServiceImpl;
	}

	/**
	 * [MenuBean.compte] Getter
	 * 
	 * @author MAKERRI Sofiane on : 9 mars 2014 09:05:13
	 * @return the compte
	 */
	public RefCompteDto getCompte() {
		return compte;
	}

	/**
	 * [MenuBean.compte] Setter
	 * 
	 * @author MAKERRI Sofiane on : 9 mars 2014 09:05:13
	 * @param compte
	 *            the compte to set
	 */
	public void setCompte(RefCompteDto compte) {
		this.compte = compte;
		RefUtilConstant.setIdGroupe(null);
		RefUtilConstant.setIdStructure(null);
		if (compte.getChangementMotPasse()) {
			return;
		}
		if (compte.getAdmin()) {
			setAdmin(true);
			loadAdminMenu();
		} else {
			setAdmin(false);
			loadAffectation();
			loadMenu();
		}
	}

	/**
	 * [MenuBean.admin] Getter
	 * 
	 * @author MAKERRI Sofiane on : 11 mars 2014 11:24:43
	 * @return the admin
	 */
	public boolean isAdmin() {
		return admin;
	}

	/**
	 * [MenuBean.admin] Setter
	 * 
	 * @author MAKERRI Sofiane on : 11 mars 2014 11:24:43
	 * @param admin
	 *            the admin to set
	 */
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	/**
	 * [MenuBean.loadAffectation] Method
	 * 
	 * @author MAKERRI Sofiane on : 17 mars 2014 13:46:46
	 */
	public void loadAffectation() {
		if (compte != null && compte.getIndividuId() != null) {
			listAffectation = refAffectationServiceImpl
					.findAffectationByIdIndividu(SessionValues.getIdEtablissement(), compte
							.getIndividuId());
			if (listAffectation != null && listAffectation.size() > 0) {
				loadListItemAffectation();
				setIdRole(listAffectation.get(0).getRoleId());
				setIdAffectation(listAffectation.get(0).getId());

			}
		}
	}

	/**
	 * [MenuBean.loadListItemAffectation] Method
	 * 
	 * @author MAKERRI Sofiane on : 17 mars 2014 12:32:38
	 */
	public void loadListItemAffectation() {
		listItemAffectation = new ArrayList<SelectItem>();
		for (RefAffectationDto refAffectationDto : listAffectation) {
			if (refAffectationDto.getIdGroupe() != null) {
				SelectItem selectItem = new SelectItem(
						refAffectationDto.getId(),
						refAffectationDto.getLlGroupe() + " (groupe)");
				listItemAffectation.add(selectItem);
			} else if (refAffectationDto.getIdStructure() != null) {
				SelectItem selectItem = new SelectItem(
						refAffectationDto.getId(),
						refAffectationDto.getLlStructureLatin()
								+ " (structure)");
				listItemAffectation.add(selectItem);
			}

		}

	}

	/**
	 * [MenuBean.affectationChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 17 mars 2014 13:07:03
	 */
	public void affectationChanged() {
		log.info("affectation changed ");
		setIdAffectation(selectedAffectation.getId());
		loadMenu();

	}

	/**
	 * [MenuBean.refAffectationServiceImpl] Getter
	 * 
	 * @author MAKERRI Sofiane on : 17 mars 2014 13:46:24
	 * @return the refAffectationServiceImpl
	 */
	public RefAffectationService getRefAffectationServiceImpl() {
		return refAffectationServiceImpl;
	}

	/**
	 * [MenuBean.refAffectationServiceImpl] Setter
	 * 
	 * @author MAKERRI Sofiane on : 17 mars 2014 13:46:24
	 * @param refAffectationServiceImpl
	 *            the refAffectationServiceImpl to set
	 */
	public void setRefAffectationServiceImpl(
			RefAffectationService refAffectationServiceImpl) {
		this.refAffectationServiceImpl = refAffectationServiceImpl;
	}

	/**
	 * [MenuBean.listItemAffectation] Getter
	 * 
	 * @author MAKERRI Sofiane on : 17 mars 2014 13:46:24
	 * @return the listItemAffectation
	 */
	public List<SelectItem> getListItemAffectation() {
		return listItemAffectation;
	}

	/**
	 * [MenuBean.listItemAffectation] Setter
	 * 
	 * @author MAKERRI Sofiane on : 17 mars 2014 13:46:24
	 * @param listItemAffectation
	 *            the listItemAffectation to set
	 */
	public void setListItemAffectation(List<SelectItem> listItemAffectation) {
		this.listItemAffectation = listItemAffectation;
	}

	/**
	 * [MenuBean.listAffectation] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 mars 2014 14:20:09
	 * @return the listAffectation
	 */
	public List<RefAffectationDto> getListAffectation() {
		return listAffectation;
	}

	/**
	 * [MenuBean.listAffectation] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 mars 2014 14:20:09
	 * @param listAffectation
	 *            the listAffectation to set
	 */
	public void setListAffectation(List<RefAffectationDto> listAffectation) {
		this.listAffectation = listAffectation;
	}

	/**
	 * [MenuBean.idRole] Getter
	 * 
	 * @author MAKERRI Sofiane on : 17 mars 2014 15:53:18
	 * @return the idRole
	 */
	public Integer getIdRole() {
		return idRole;
	}

	/**
	 * [MenuBean.idRole] Setter
	 * 
	 * @author MAKERRI Sofiane on : 17 mars 2014 15:53:18
	 * @param idRole
	 *            the idRole to set
	 */
	public void setIdRole(Integer idRole) {
		this.idRole = idRole;
	}

	/**
	 * [MenuBean.idAffectation] Getter
	 * 
	 * @author MAKERRI Sofiane on : 17 mars 2014 17:52:18
	 * @return the idAffectation
	 */
	public Integer getIdAffectation() {
		return idAffectation;
	}

	/**
	 * [MenuBean.idAffectation] Setter
	 * 
	 * @author MAKERRI Sofiane on : 17 mars 2014 17:52:18
	 * @param idAffectation
	 *            the idAffectation to set
	 */
	public void setIdAffectation(Integer idAffectation) {
		RefAffectationDto _refAffectationDto = refAffectationServiceImpl
				.findById(idAffectation);
		if (_refAffectationDto != null) {
			idRole = _refAffectationDto.getRoleId();
			RefUtilConstant.setIdStructure(_refAffectationDto
					.getIdStructure());
			RefUtilConstant.setIdGroupe(_refAffectationDto.getIdGroupe());
		}
		this.idAffectation = idAffectation;
	}

	/**
	 * [MenuBean.selectedAffectation] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 mars 2014 13:37:41
	 * @return the selectedAffectation
	 */
	public RefAffectationDto getSelectedAffectation() {
		return selectedAffectation;
	}

	/**
	 * [MenuBean.selectedAffectation] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 mars 2014 13:37:41
	 * @param selectedAffectation
	 *            the selectedAffectation to set
	 */
	public void setSelectedAffectation(RefAffectationDto selectedAffectation) {
		this.selectedAffectation = selectedAffectation;
	}

	/**
	 * [MenuBean.mapModuleRendered] Getter 
	 * @author BELDI Jamel on : 13 avr. 2014  11:45:36
	 * @return the mapModuleRendered
	 */
	public HashMap<String, Boolean> getMapModuleRendered () {
		return mapModuleRendered;
	}

	/**
	 * [MenuBean.mapModuleRendered] Setter 
	 * @author BELDI Jamel on : 13 avr. 2014  11:45:36
	 * @param mapModuleRendered the mapModuleRendered to set
	 */
	public void setMapModuleRendered (HashMap<String, Boolean> mapModuleRendered ) {
		this.mapModuleRendered  = mapModuleRendered ;
	}


	private String findLabel(String key){
		FacesContext facesContext = FacesContext.getCurrentInstance();
		bundle = facesContext.getApplication().getResourceBundle(facesContext,
				MenuBean.MESSAGES_FILE_NAME);
		String label;
		try{
			label=bundle.getString(key);	
		}catch(Exception e){
			return "??????????";
		}
		return label;
		
	}


	
	/**
	 * [MenuBean.findNomFonvtion] Method 
	 * @author MAKERRI Sofiane  on : 4 mai 2014  11:24:10
	 * @return
	 */
	public String findNomFonction(RefPermissionDto _refPermissionDto) {
		if (_refPermissionDto != null) {
			if (language!= null && language.equals("ar")) {
				return _refPermissionDto.getNomArabeFonction();
			} else {
				return _refPermissionDto.getNomLatinFonction();
			}
		}
		return null;
	}
	
	/**
	 * [MenuBean.findNomFonction] Method 
	 * @author MAKERRI Sofiane  on : 4 mai 2014  11:59:07
	 * @param _refFonctionDto
	 * @return
	 */
	public String findNomFonction(RefFonctionDto _refFonctionDto) {
		if (_refFonctionDto != null) {
			if (language!= null && language.equals("ar")) {
				return _refFonctionDto.getNomArabe();
			} else {
				return _refFonctionDto.getNom();
			}
		}
		return null;
	}
	
	/**
	 * [MenuBean.findNomModule] Method 
	 * @author MAKERRI Sofiane  on : 4 mai 2014  11:26:22
	 * @return
	 */
	public String findNomModule(RefPermissionDto _refPermissionDto) {
		if (_refPermissionDto != null) {
			if (language!= null && language.equals("ar")) {
				return _refPermissionDto.getNomArabeModule();
			} else {
				return _refPermissionDto.getNomLatinModule();
			}
		}
		return null;
	}
	
	/**
	 * [MenuBean.findNomModule] Method 
	 * @author MAKERRI Sofiane  on : 4 mai 2014  12:01:10
	 * @param _refFonctionDto
	 * @return
	 */
	public String findNomModule(RefFonctionDto _refFonctionDto) {
		if (_refFonctionDto != null) {
			if (language!= null && language.equals("ar")) {
				return _refFonctionDto.getNomArabeModule();
			} else {
				return _refFonctionDto.getNomModule();
			}
		}
		return null;
	}

	/**
	 * [MenuBean.language] Getter 
	 * @author MAKERRI Sofiane on : 4 mai 2014  11:53:19
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * [MenuBean.language] Setter 
	 * @author MAKERRI Sofiane on : 4 mai 2014  11:53:19
	 * @param language the language to set
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * [MenuBean.languageChanged] Getter 
	 * @author MAKERRI Sofiane on : 4 mai 2014  14:47:00
	 * @return the languageChanged
	 */
	public boolean isLanguageChanged() {
		return languageChanged;
	}

	/**
	 * [MenuBean.languageChanged] Setter 
	 * @author MAKERRI Sofiane on : 4 mai 2014  14:47:00
	 * @param languageChanged the languageChanged to set
	 */
	public void setLanguageChanged(boolean languageChanged) {
		this.languageChanged = languageChanged;
	}
	
	/**
	 * [MenuBean.mapFctRendered] Getter 
	 * @author BELDI Jamel on : 13 avr. 2014  12:56:34
	 * @return the mapFctRendered
	 */
	public HashMap<String, Boolean> getMapFctRendered() {
		return mapFctRendered;
	}

	/**
	 * [MenuBean.mapFctRendered] Setter 
	 * @author BELDI Jamel on : 13 avr. 2014  12:56:34
	 * @param mapFctRendered the mapFctRendered to set
	 */
	public void setMapFctRendered(HashMap<String, Boolean> mapFctRendered) {
		this.mapFctRendered = mapFctRendered;
	}

	/**
	 * [MenuBean.mapFctUrl] Getter 
	 * @author BELDI Jamel on : 13 avr. 2014  12:56:34
	 * @return the mapFctUrl
	 */
	public HashMap<String, String> getMapFctUrl() {
		return mapFctUrl;
	}

	/**
	 * [MenuBean.mapFctUrl] Setter 
	 * @author BELDI Jamel on : 13 avr. 2014  12:56:34
	 * @param mapFctUrl the mapFctUrl to set
	 */
	public void setMapFctUrl(HashMap<String, String> mapFctUrl) {
		this.mapFctUrl = mapFctUrl;
	}

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}
	
	public void setRefDomaineService(RefDomaineService refDomaineService) {
		this.refDomaineService = refDomaineService;
	}
	
	public void setRefModuleService(RefModuleService refModuleService) {
		this.refModuleService = refModuleService;
	}
	
}
