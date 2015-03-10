package dz.gov.mesrs.sii.refenrentiel.jsf.beans.permission;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.component.selectbooleancheckbox.SelectBooleanCheckbox;

import dz.gov.mesrs.sii.commons.data.util.UtilConstant;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefDomaineDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefFonctionDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefModuleDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefPermissionDto;
import dz.gov.mesrs.sii.referentiel.business.service.NomenclatureService;
import dz.gov.mesrs.sii.referentiel.business.service.RefDomaineService;
import dz.gov.mesrs.sii.referentiel.business.service.RefFonctionService;
import dz.gov.mesrs.sii.referentiel.business.service.RefModuleService;
import dz.gov.mesrs.sii.referentiel.business.service.RefPermissionService;
import dz.gov.mesrs.sii.referentiel.business.util.RefUtilConstant;

/**
 * @author obelbrik  on : 04 mars. 2014  14:23:30
 * horaire backing bean 
 */
/**
 * @author MAKERRI Sofiane on : 6 mars 2014 11:11:25
 */

@ManagedBean(name = "permissionBckBean")
@ViewScoped
public class PermissionBckBean implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author BELBRIK Oualid on : 04 mars. 2014 09:14:39
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * listRefPermissionDto
	 * 
	 * @author BELBRIK Oualid on : 04 mars. 2014 09:14:50
	 */
	private List<RefPermissionDto> listRefPermissionDto;

	/**
	 * refPermissionDto
	 * 
	 * @author BELBRIK Oualid on : 04 mars. 2014 09:14:46
	 */
	private RefPermissionDto refPermissionDto;

	// private Integer idDomaine;
	// private Integer idModule;
	// private Integer idFonction;

	private ResourceBundle bundle;
	@ManagedProperty(value = "#{refPermissionServiceImpl}")
	private RefPermissionService refPermissionServiceImpl;
	@ManagedProperty(value = "#{refDomaineServiceImpl}")
	private RefDomaineService refDomaineServiceImpl;
	@ManagedProperty(value = "#{refModuleServiceImpl}")
	private RefModuleService refModuleServiceImpl;
	@ManagedProperty(value = "#{refFonctionServiceImpl}")
	private RefFonctionService refFonctionServiceImpl;
	@ManagedProperty(value = "#{nomenclatureServiceImpl}")
	private NomenclatureService nomenclatureServiceImpl;
	private List<RefPermissionDto> listPermissions;
	private List<RefDomaineDto> listDomaines;
	private List<RefDomaineDto> listSubDomaines;
	private List<RefModuleDto> listModules;
	private List<RefFonctionDto> listFonctions;
	private List<SelectItem> listItemDomaines;
	private List<SelectItem> listItemSubDomaines;
	private List<SelectItem> listItemModules;
	private List<SelectItem> listItemFonctions;
	private static final Log log = LogFactory.getLog(PermissionBckBean.class);
	private NomenclatureDto ncRoleDto;

	/**
	 * 
	 * @author BELBRIK Oualid on : 04 mars. 2014 09:28:06
	 */
	public PermissionBckBean() {
		super();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		bundle = facesContext.getApplication().getResourceBundle(facesContext,
				RefUtilConstant.COMMON_MESSAGES_FILE_NAME);
		refPermissionDto = new RefPermissionDto();
	}

	@PostConstruct
	public void init() {
		ncRoleDto = (NomenclatureDto) FacesContext.getCurrentInstance()
				.getExternalContext().getFlash().get("role");
		if (ncRoleDto == null) {
			ncRoleDto = new NomenclatureDto();
		}
		if (ncRoleDto.getId() != null) {

			loadFonctions();
			listDomaines = refDomaineServiceImpl.findDomaines();
			loadListItemDomaine();

			if (refPermissionDto.getIdDomaine() != null) {
				listModules = refModuleServiceImpl.findModules(refPermissionDto
						.getIdDomaine());
				loadListItemModule();
			} else if (listDomaines != null && listDomaines.size() > 0) {
				listModules = refModuleServiceImpl.findModules(listDomaines
						.get(0).getId());
				loadListItemModule();
			}
			if (refPermissionDto.getIdModule() != null) {
				listFonctions = refFonctionServiceImpl
						.findFonctions(refPermissionDto.getIdModule());
				loadListItemFonction();
			}

			loadPermissions();

		}
	}

	/**
	 * [PermissionBckBean.loadPermissions] Method
	 * 
	 * @author MAKERRI Sofiane on : 11 mars 2014 11:11:20
	 */
	public void loadPermissions() {
		// if (refPermissionDto.getIdModule() != null) {
		// refPermissionDto.setIdModule(refPermissionDto.getIdModule());
		// }
		//
		// if (refPermissionDto.getIdDomaine() != null) {
		// refPermissionDto.setIdDomaine(refPermissionDto.getIdDomaine());
		// }

		refPermissionDto.setIdRole(ncRoleDto.getId());

		// if (refPermissionDto.getIdFonction() != null) {
		// refPermissionDto.setIdFonction(refPermissionDto.getIdFonction());
		// }

		if (refPermissionDto.getIdFonction() != null
				&& ncRoleDto.getId() != null) {
			listPermissions = refPermissionServiceImpl.findByIdAction(
					ncRoleDto.getId(), refPermissionDto.getIdFonction());

		} else if (refPermissionDto.getIdModule() != null
				&& ncRoleDto.getId() != null) {
			listPermissions = refPermissionServiceImpl.findByIdModule(
					ncRoleDto.getId(), refPermissionDto.getIdModule());

		} else if (refPermissionDto.getIdDomaine() != null
				&& ncRoleDto.getId() != null) {
			listPermissions = refPermissionServiceImpl.findByIdDomaine(
					ncRoleDto.getId(), refPermissionDto.getIdDomaine());

		} else if (ncRoleDto.getId() != null) {

			listPermissions = refPermissionServiceImpl
					.findByIdRole(RefUtilConstant.DOMAINE_REFERENTIEL_NAME,
							ncRoleDto.getId());

		}
	}

	/**
	 * [PermissionBckBean.loadFonctions] Method
	 * 
	 * @author MAKERRI Sofiane on : 10 mars 2014 09:14:35
	 */
	public void loadFonctions() {
		try {
			if (ncRoleDto != null) {

				listFonctions = refFonctionServiceImpl.findAllActions();
				for (RefFonctionDto _RefFonctionDto : listFonctions) {
					RefPermissionDto _refPermissionDto = refPermissionServiceImpl
							.findByIdFonction(ncRoleDto.getId(),
									_RefFonctionDto.getId());
					if (_refPermissionDto == null) {
						_refPermissionDto = new RefPermissionDto();
						_refPermissionDto.setIdRole(ncRoleDto.getId());
						_refPermissionDto
								.setIdFonction(_RefFonctionDto.getId());
						_refPermissionDto.setConsulter(false);
						_refPermissionDto.setCreer(false);
						_refPermissionDto.setModifier(false);
						_refPermissionDto.setSupprimer(false);
						refPermissionServiceImpl.save(_refPermissionDto);
					}

				}

			}
		} catch (Exception e) {
			log.info(e.getMessage());
		}

	}

	/**
	 * [PermissionBckBean.loadListItemDomaine] Method
	 * 
	 * @author MAKERRI Sofiane on : 6 mars 2014 13:11:34
	 */
	public void loadListItemDomaine() {
		listItemDomaines = new ArrayList<SelectItem>();
		for (RefDomaineDto refDomaineDto : listDomaines) {
			SelectItem selectItem = new SelectItem(refDomaineDto.getId(),
					refDomaineDto.getNom());
			listItemDomaines.add(selectItem);
		}

	}

	/**
	 * [PermissionBckBean.loadListItemSubDomaine] Method
	 * 
	 * @author MAKERRI Sofiane on : 6 mars 2014 13:12:17
	 */
	public void loadListItemSubDomaine() {
		listItemSubDomaines = new ArrayList<SelectItem>();
		for (RefDomaineDto refDomaineDto : listSubDomaines) {
			SelectItem selectItem = new SelectItem(refDomaineDto.getId(),
					refDomaineDto.getNom());
			listItemSubDomaines.add(selectItem);
		}

	}

	/**
	 * [PermissionBckBean.loadListItemModule] Method
	 * 
	 * @author MAKERRI Sofiane on : 6 mars 2014 13:13:08
	 */
	public void loadListItemModule() {
		listItemModules = new ArrayList<SelectItem>();
		for (RefModuleDto refModuleDto : listModules) {
			SelectItem selectItem = new SelectItem(refModuleDto.getId(),
					refModuleDto.getNom());
			listItemModules.add(selectItem);
		}

	}

	/**
	 * [PermissionBckBean.loadListItemFonction] Method
	 * 
	 * @author MAKERRI Sofiane on : 6 mars 2014 13:14:07
	 */
	public void loadListItemFonction() {
		listItemFonctions = new ArrayList<SelectItem>();
		for (RefFonctionDto refFonctionDto : listFonctions) {
			SelectItem selectItem = new SelectItem(refFonctionDto.getId(),
					refFonctionDto.getNom());
			listItemFonctions.add(selectItem);
		}

	}

	/**
	 * [PermissionBckBean.bundle] Getter
	 * 
	 * @author MAKERRI Sofiane on : 6 mars 2014 11:27:14
	 * @return the bundle
	 */
	public ResourceBundle getBundle() {
		return bundle;
	}

	/**
	 * [PermissionBckBean.bundle] Setter
	 * 
	 * @author MAKERRI Sofiane on : 6 mars 2014 11:27:14
	 * @param bundle
	 *            the bundle to set
	 */
	public void setBundle(ResourceBundle bundle) {
		this.bundle = bundle;
	}

	/**
	 * 
	 * @author BELBRIK Oualid on : 04 mars. 2014 09:15:11
	 * 
	 */
	public List<RefPermissionDto> getListRefPermissionDto() {
		return listRefPermissionDto;
	}

	/**
	 * 
	 * @author BELBRIK Oualid on : 04 mars. 2014 09:15:11
	 * 
	 */
	public void setListRefPermissionDto(
			List<RefPermissionDto> listRefPermissionDto) {
		this.listRefPermissionDto = listRefPermissionDto;
	}

	/**
	 * 
	 * @author BELBRIK Oualid on : 04 mars. 2014 09:15:11
	 * 
	 */
	public RefPermissionDto getRefPermissionDto() {
		return refPermissionDto;
	}

	/**
	 * 
	 * @author BELBRIK Oualid on : 04 mars. 2014 09:15:11
	 * 
	 */
	public void setRefPermissionDto(RefPermissionDto refPermissionDto) {
		this.refPermissionDto = refPermissionDto;
	}

	/**
	 * [PermissionBckBean.back] Method
	 * 
	 * @author MAKERRI Sofiane on : 6 mars 2014 10:36:11
	 * @return
	 */
	public String back() {
		return "toSearch";
	}

	/**
	 * [PermissionBckBean.refPermissionServiceImpl] Getter
	 * 
	 * @author MAKERRI Sofiane on : 6 mars 2014 11:03:48
	 * @return the refPermissionServiceImpl
	 */
	public RefPermissionService getRefPermissionServiceImpl() {
		return refPermissionServiceImpl;
	}

	/**
	 * [PermissionBckBean.refPermissionServiceImpl] Setter
	 * 
	 * @author MAKERRI Sofiane on : 6 mars 2014 11:03:48
	 * @param refPermissionServiceImpl
	 *            the refPermissionServiceImpl to set
	 */
	public void setRefPermissionServiceImpl(
			RefPermissionService refPermissionServiceImpl) {
		this.refPermissionServiceImpl = refPermissionServiceImpl;
	}

	/**
	 * [PermissionBckBean.listPermissions] Getter
	 * 
	 * @author MAKERRI Sofiane on : 6 mars 2014 11:05:42
	 * @return the listPermissions
	 */
	public List<RefPermissionDto> getListPermissions() {
		return listPermissions;
	}

	/**
	 * [PermissionBckBean.listPermissions] Setter
	 * 
	 * @author MAKERRI Sofiane on : 6 mars 2014 11:05:42
	 * @param listPermissions
	 *            the listPermissions to set
	 */
	public void setListPermissions(List<RefPermissionDto> listPermissions) {
		this.listPermissions = listPermissions;
	}

	/**
	 * [PermissionBckBean.domaineChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 6 mars 2014 11:43:32
	 */
	public void domaineChanged() {
		if (refPermissionDto != null && refPermissionDto.getIdDomaine() != null
				&& ncRoleDto != null && ncRoleDto.getId() != null) {
			// setIdDomaine(refPermissionDto.getIdDomaine());
			listPermissions = refPermissionServiceImpl.findByIdDomaine(
					ncRoleDto.getId(), refPermissionDto.getIdDomaine());
			listSubDomaines = refDomaineServiceImpl
					.findSubDomaine(refPermissionDto.getIdDomaine());
			loadListItemSubDomaine();
			listModules = refModuleServiceImpl.findModules(refPermissionDto
					.getIdDomaine());
			loadListItemModule();
		}
	}

	/**
	 * [PermissionBckBean.moduleChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 6 mars 2014 11:43:30
	 */
	public void moduleChanged() {
		if (refPermissionDto != null && refPermissionDto.getIdModule() != null
				&& ncRoleDto != null && ncRoleDto.getId() != null) {
			// setIdModule(refPermissionDto.getIdModule());
			listPermissions = refPermissionServiceImpl.findByIdModule(
					ncRoleDto.getId(), refPermissionDto.getIdModule());
			listFonctions = refFonctionServiceImpl
					.findFonctions(refPermissionDto.getIdModule());
			loadListItemFonction();
		}
	}

	/**
	 * [PermissionBckBean.fonctionChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 20 mars 2014 15:12:08
	 */
	public void fonctionChanged() {
		if (refPermissionDto != null
				&& refPermissionDto.getIdFonction() != null
				&& refPermissionDto.getIdModule() != null && ncRoleDto != null
				&& ncRoleDto.getId() != null) {
			// setIdFonction(refPermissionDto.getIdFonction());
			listPermissions = refPermissionServiceImpl.findByIdAction(
					ncRoleDto.getId(), refPermissionDto.getIdFonction());
		}
	}

	/**
	 * [PermissionBckBean.subDomaineChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 6 mars 2014 11:43:27
	 */
	public void subDomaineChanged() {

	}

	/**
	 * [PermissionBckBean.listDomaines] Getter
	 * 
	 * @author MAKERRI Sofiane on : 6 mars 2014 11:25:29
	 * @return the listDomaines
	 */
	public List<RefDomaineDto> getListDomaines() {
		return listDomaines;
	}

	/**
	 * [PermissionBckBean.listDomaines] Setter
	 * 
	 * @author MAKERRI Sofiane on : 6 mars 2014 11:25:29
	 * @param listDomaines
	 *            the listDomaines to set
	 */
	public void setListDomaines(List<RefDomaineDto> listDomaines) {
		this.listDomaines = listDomaines;
	}

	/**
	 * [PermissionBckBean.listModules] Getter
	 * 
	 * @author MAKERRI Sofiane on : 6 mars 2014 11:25:29
	 * @return the listModules
	 */
	public List<RefModuleDto> getListModules() {
		return listModules;
	}

	/**
	 * [PermissionBckBean.listModules] Setter
	 * 
	 * @author MAKERRI Sofiane on : 6 mars 2014 11:25:29
	 * @param listModules
	 *            the listModules to set
	 */
	public void setListModules(List<RefModuleDto> listModules) {
		this.listModules = listModules;
	}

	/**
	 * [PermissionBckBean.listFonctions] Getter
	 * 
	 * @author MAKERRI Sofiane on : 6 mars 2014 11:25:29
	 * @return the listFonctions
	 */
	public List<RefFonctionDto> getListFonctions() {
		return listFonctions;
	}

	/**
	 * [PermissionBckBean.listFonctions] Setter
	 * 
	 * @author MAKERRI Sofiane on : 6 mars 2014 11:25:29
	 * @param listFonctions
	 *            the listFonctions to set
	 */
	public void setListFonctions(List<RefFonctionDto> listFonctions) {
		this.listFonctions = listFonctions;
	}

	/**
	 * [PermissionBckBean.refDomaineServiceImpl] Getter
	 * 
	 * @author MAKERRI Sofiane on : 6 mars 2014 11:27:03
	 * @return the refDomaineServiceImpl
	 */
	public RefDomaineService getRefDomaineServiceImpl() {
		return refDomaineServiceImpl;
	}

	/**
	 * [PermissionBckBean.refDomaineServiceImpl] Setter
	 * 
	 * @author MAKERRI Sofiane on : 6 mars 2014 11:27:03
	 * @param refDomaineServiceImpl
	 *            the refDomaineServiceImpl to set
	 */
	public void setRefDomaineServiceImpl(RefDomaineService refDomaineServiceImpl) {
		this.refDomaineServiceImpl = refDomaineServiceImpl;
	}

	/**
	 * [PermissionBckBean.refModuleServiceImpl] Getter
	 * 
	 * @author MAKERRI Sofiane on : 6 mars 2014 11:27:03
	 * @return the refModuleServiceImpl
	 */
	public RefModuleService getRefModuleServiceImpl() {
		return refModuleServiceImpl;
	}

	/**
	 * [PermissionBckBean.refModuleServiceImpl] Setter
	 * 
	 * @author MAKERRI Sofiane on : 6 mars 2014 11:27:03
	 * @param refModuleServiceImpl
	 *            the refModuleServiceImpl to set
	 */
	public void setRefModuleServiceImpl(RefModuleService refModuleServiceImpl) {
		this.refModuleServiceImpl = refModuleServiceImpl;
	}

	/**
	 * [PermissionBckBean.refFonctionServiceImpl] Getter
	 * 
	 * @author MAKERRI Sofiane on : 6 mars 2014 11:27:03
	 * @return the refFonctionServiceImpl
	 */
	public RefFonctionService getRefFonctionServiceImpl() {
		return refFonctionServiceImpl;
	}

	/**
	 * [PermissionBckBean.refFonctionServiceImpl] Setter
	 * 
	 * @author MAKERRI Sofiane on : 6 mars 2014 11:27:03
	 * @param refFonctionServiceImpl
	 *            the refFonctionServiceImpl to set
	 */
	public void setRefFonctionServiceImpl(
			RefFonctionService refFonctionServiceImpl) {
		this.refFonctionServiceImpl = refFonctionServiceImpl;
	}

	/**
	 * [PermissionBckBean.listSubDomaines] Getter
	 * 
	 * @author MAKERRI Sofiane on : 6 mars 2014 11:28:32
	 * @return the listSubDomaines
	 */
	public List<RefDomaineDto> getListSubDomaines() {
		return listSubDomaines;
	}

	/**
	 * [PermissionBckBean.listSubDomaines] Setter
	 * 
	 * @author MAKERRI Sofiane on : 6 mars 2014 11:28:32
	 * @param listSubDomaines
	 *            the listSubDomaines to set
	 */
	public void setListSubDomaines(List<RefDomaineDto> listSubDomaines) {
		this.listSubDomaines = listSubDomaines;
	}

	/**
	 * [PermissionBckBean.listItemDomaines] Getter
	 * 
	 * @author MAKERRI Sofiane on : 6 mars 2014 13:06:06
	 * @return the listItemDomaines
	 */
	public List<SelectItem> getListItemDomaines() {
		return listItemDomaines;
	}

	/**
	 * [PermissionBckBean.listItemDomaines] Setter
	 * 
	 * @author MAKERRI Sofiane on : 6 mars 2014 13:06:06
	 * @param listItemDomaines
	 *            the listItemDomaines to set
	 */
	public void setListItemDomaines(List<SelectItem> listItemDomaines) {
		this.listItemDomaines = listItemDomaines;
	}

	/**
	 * [PermissionBckBean.listItemSubDomaines] Getter
	 * 
	 * @author MAKERRI Sofiane on : 6 mars 2014 13:06:06
	 * @return the listItemSubDomaines
	 */
	public List<SelectItem> getListItemSubDomaines() {
		return listItemSubDomaines;
	}

	/**
	 * [PermissionBckBean.listItemSubDomaines] Setter
	 * 
	 * @author MAKERRI Sofiane on : 6 mars 2014 13:06:06
	 * @param listItemSubDomaines
	 *            the listItemSubDomaines to set
	 */
	public void setListItemSubDomaines(List<SelectItem> listItemSubDomaines) {
		this.listItemSubDomaines = listItemSubDomaines;
	}

	/**
	 * [PermissionBckBean.listItemModules] Getter
	 * 
	 * @author MAKERRI Sofiane on : 6 mars 2014 13:06:06
	 * @return the listItemModules
	 */
	public List<SelectItem> getListItemModules() {
		return listItemModules;
	}

	/**
	 * [PermissionBckBean.listItemModules] Setter
	 * 
	 * @author MAKERRI Sofiane on : 6 mars 2014 13:06:06
	 * @param listItemModules
	 *            the listItemModules to set
	 */
	public void setListItemModules(List<SelectItem> listItemModules) {
		this.listItemModules = listItemModules;
	}

	/**
	 * [PermissionBckBean.listItemFonctions] Getter
	 * 
	 * @author MAKERRI Sofiane on : 6 mars 2014 13:06:06
	 * @return the listItemFonctions
	 */
	public List<SelectItem> getListItemFonctions() {
		return listItemFonctions;
	}

	/**
	 * [PermissionBckBean.listItemFonctions] Setter
	 * 
	 * @author MAKERRI Sofiane on : 6 mars 2014 13:06:06
	 * @param listItemFonctions
	 *            the listItemFonctions to set
	 */
	public void setListItemFonctions(List<SelectItem> listItemFonctions) {
		this.listItemFonctions = listItemFonctions;
	}

	/**
	 * [PermissionBckBean.save] Method
	 * 
	 * @author MAKERRI Sofiane on : 6 mars 2014 16:07:30
	 */
	public void save() {
		try {
			if (listPermissions != null) {
				for (RefPermissionDto _permission : listPermissions) {
					refPermissionServiceImpl.update(_permission);
				}
			}
			FacesMessage msg = new FacesMessage();
			msg.setSeverity(FacesMessage.SEVERITY_INFO);
			msg.setSummary(bundle.getString("msg_success") + ": "
					+ bundle.getString("msg_success_modification"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (Exception e) {
			FacesMessage msg = new FacesMessage();
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	/**
	 * [PermissionBckBean.readCheckChange] Method
	 * 
	 * @author MAKERRI Sofiane on : 10 mars 2014 10:22:03
	 * @param event
	 */
	public void readCheckChange(AjaxBehaviorEvent event) {
		SelectBooleanCheckbox permit = (SelectBooleanCheckbox) event
				.getComponent();
		boolean checked = (Boolean) permit.getValue();
		RefPermissionDto _refPermissionDto = (RefPermissionDto) permit
				.getAttributes().get("selectedRecord");
		Boolean b = (Boolean) permit.getAttributes().get("create");
		System.out.println(b);
		_refPermissionDto.setConsulter(checked);
		if (!checked) {
			_refPermissionDto.setCreer(false);
			_refPermissionDto.setModifier(false);
			_refPermissionDto.setSupprimer(false);
		}
		refPermissionServiceImpl.update(_refPermissionDto);
	}

	/**
	 * [PermissionBckBean.allCheckChange] Method
	 * 
	 * @author MAKERRI Sofiane on : 10 mars 2014 10:22:42
	 * @param event
	 */
	public void allCheckChange(AjaxBehaviorEvent event) {
		SelectBooleanCheckbox permit = (SelectBooleanCheckbox) event
				.getComponent();
		boolean checked = (Boolean) permit.getValue();
		RefPermissionDto _refPermissionDto = (RefPermissionDto) permit
				.getAttributes().get("selectedRecord");
		_refPermissionDto.setCreer(checked);
		_refPermissionDto.setModifier(checked);
		_refPermissionDto.setSupprimer(checked);
		_refPermissionDto.setConsulter(checked);
		// refPermissionServiceImpl.update(_refPermissionDto);

	}

	/**
	 * [PermissionBckBean.checkChange] Method
	 * 
	 * @author MAKERRI Sofiane on : 10 mars 2014 12:07:31
	 * @param event
	 */
	public void checkChange(AjaxBehaviorEvent event) {
		SelectBooleanCheckbox permit = (SelectBooleanCheckbox) event
				.getComponent();
		RefPermissionDto _refPermissionDto = (RefPermissionDto) permit
				.getAttributes().get("selectedRecord");
		boolean checked = (Boolean) permit.getValue();
		if (checked) {
			_refPermissionDto.setConsulter(true);
		}

		_refPermissionDto.setAllAccess(_refPermissionDto.getConsulter()
				&& _refPermissionDto.getCreer()
				&& _refPermissionDto.getModifier()
				&& _refPermissionDto.getSupprimer());
		refPermissionServiceImpl.update(_refPermissionDto);

	}

	/**
	 * [PermissionBckBean.nomenclatureServiceImpl] Getter
	 * 
	 * @author MAKERRI Sofiane on : 16 mars 2014 18:14:17
	 * @return the nomenclatureServiceImpl
	 */
	public NomenclatureService getNomenclatureServiceImpl() {
		return nomenclatureServiceImpl;
	}

	/**
	 * [PermissionBckBean.nomenclatureServiceImpl] Setter
	 * 
	 * @author MAKERRI Sofiane on : 16 mars 2014 18:14:17
	 * @param nomenclatureServiceImpl
	 *            the nomenclatureServiceImpl to set
	 */
	public void setNomenclatureServiceImpl(
			NomenclatureService nomenclatureServiceImpl) {
		this.nomenclatureServiceImpl = nomenclatureServiceImpl;
	}

	/**
	 * [PermissionBckBean.ncRoleDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 17 mars 2014 10:47:17
	 * @return the ncRoleDto
	 */
	public NomenclatureDto getNcRoleDto() {
		return ncRoleDto;
	}

	/**
	 * [PermissionBckBean.ncRoleDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 17 mars 2014 10:47:17
	 * @param ncRoleDto
	 *            the ncRoleDto to set
	 */
	public void setNcRoleDto(NomenclatureDto ncRoleDto) {
		this.ncRoleDto = ncRoleDto;
	}

}
