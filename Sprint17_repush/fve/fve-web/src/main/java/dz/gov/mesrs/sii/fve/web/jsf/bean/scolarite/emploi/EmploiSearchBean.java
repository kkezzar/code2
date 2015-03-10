package dz.gov.mesrs.sii.fve.web.jsf.bean.scolarite.emploi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.dozer.DozerBeanMapper;

import dz.gov.mesrs.sii.fve.business.model.dto.scolarite.EmploiDto;
import dz.gov.mesrs.sii.fve.business.service.scolarite.EmploiService;
import dz.gov.mesrs.sii.fve.web.jsf.bean.SessionBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.UtilBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.CursusConstants;

/**
 * 
 * @author BELDI Jamel on : 19 oct. 2014 10:04:54
 */
@ManagedBean(name = "emploiSearchBean")
@ViewScoped
public class EmploiSearchBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private ResourceBundle bundleCommon;
	private ResourceBundle examenBundle;
	@ManagedProperty("#{sessionBeanFve}")
	private SessionBean sessionBean;
	@ManagedProperty("#{utilBean}")
	private UtilBean utilBean;
	@ManagedProperty("#{mapper}")
	private DozerBeanMapper mapper;
	private List<SelectItem> anneeAcademiqueList;
	private List<SelectItem> offreFormationList;
	private EmploiDto emploiSearchDto;
	private List<EmploiDto> emploiList;
	@ManagedProperty("#{emploiService}")
	private EmploiService emploiService;

	/**
	 * 
	 * [EmploiSearchBean.EmploiSearchBean()] Constructor
	 * 
	 * @author BELDI Jamel on : 22 oct. 2014 15:08:21
	 */
	public EmploiSearchBean() {
		super();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		bundleCommon = facesContext.getApplication().getResourceBundle(
				facesContext, CursusConstants.COMMON_BUNDLE_MSG_NAME);
		examenBundle = facesContext.getApplication().getResourceBundle(
				facesContext, CursusConstants.EXAMEN_BUNDLE_MSG_NAME);

	}

	/**
	 * 
	 * [EmploiSearchBean.init] Method
	 * 
	 * @author BELDI Jamel on : 22 oct. 2014 15:08:24
	 */
	@PostConstruct
	public void init() {
		emploiSearchDto = new EmploiDto();
		emploiSearchDto.setRefCodeEtablissement(sessionBean.getCodeEtablissement());
		emploiSearchDto.setAnneeAcademiqueId(sessionBean.getIdAnneeAcademique());
		loadAnneeAcademique();
		searchEmploiList();

	}

	/**
	 * 
	 * [EmploiSearchBean.loadAnneeAcademique] Method
	 * 
	 * @author BELDI Jamel on : 22 oct. 2014 15:08:27
	 */
	private void loadAnneeAcademique() {
		anneeAcademiqueList = utilBean.loadAnneeAcademique();

	}

	/**
	 * 
	 * [EmploiSearchBean.anneeChanged] Method
	 * 
	 * @author BELDI Jamel on : 22 oct. 2014 15:08:30
	 */
	public void anneeChanged() {
		 emploiSearchDto.setOuvertureOffreFormationId(null);
		offreFormationList = utilBean.loadOffreFormationOuverte(emploiSearchDto
				.getAnneeAcademiqueId());
		ofChanged();

	}

	/**
	 * 
	 * [EmploiSearchBean.ofChanged] Method
	 * 
	 * @author BELDI Jamel on : 22 oct. 2014 15:08:33
	 */
	public void ofChanged() {
		searchEmploiList();

	}

	/**
	 * 
	 * [EmploiSearchBean.searchEmploiList] Method
	 * 
	 * @author BELDI Jamel on : 22 oct. 2014 15:08:36
	 */
	public void searchEmploiList() {
		try {
			emploiList = new ArrayList<EmploiDto>();
			emploiList = emploiService.findAdvanced(emploiSearchDto);
		} catch (Exception e) {
			emploiList = new ArrayList<EmploiDto>();
			e.printStackTrace();
		}
	}

	// getter and Setter

	/**
	 * [EmploiSearchBean.sessionBean] Getter
	 * 
	 * @author BELDI Jamel on : 22 oct. 2014 15:01:15
	 * @return the sessionBean
	 */
	public SessionBean getSessionBean() {
		return sessionBean;
	}

	/**
	 * [EmploiSearchBean.sessionBean] Setter
	 * 
	 * @author BELDI Jamel on : 22 oct. 2014 15:01:15
	 * @param sessionBean
	 *            the sessionBean to set
	 */
	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}

	/**
	 * [EmploiSearchBean.utilBean] Getter
	 * 
	 * @author BELDI Jamel on : 22 oct. 2014 15:01:15
	 * @return the utilBean
	 */
	public UtilBean getUtilBean() {
		return utilBean;
	}

	/**
	 * [EmploiSearchBean.utilBean] Setter
	 * 
	 * @author BELDI Jamel on : 22 oct. 2014 15:01:15
	 * @param utilBean
	 *            the utilBean to set
	 */
	public void setUtilBean(UtilBean utilBean) {
		this.utilBean = utilBean;
	}

	/**
	 * [EmploiSearchBean.mapper] Getter
	 * 
	 * @author BELDI Jamel on : 22 oct. 2014 15:01:15
	 * @return the mapper
	 */
	public DozerBeanMapper getMapper() {
		return mapper;
	}

	/**
	 * [EmploiSearchBean.mapper] Setter
	 * 
	 * @author BELDI Jamel on : 22 oct. 2014 15:01:15
	 * @param mapper
	 *            the mapper to set
	 */
	public void setMapper(DozerBeanMapper mapper) {
		this.mapper = mapper;
	}

	/**
	 * [EmploiSearchBean.anneeAcademiqueList] Getter
	 * 
	 * @author BELDI Jamel on : 22 oct. 2014 15:01:15
	 * @return the anneeAcademiqueList
	 */
	public List<SelectItem> getAnneeAcademiqueList() {
		return anneeAcademiqueList;
	}

	/**
	 * [EmploiSearchBean.anneeAcademiqueList] Setter
	 * 
	 * @author BELDI Jamel on : 22 oct. 2014 15:01:15
	 * @param anneeAcademiqueList
	 *            the anneeAcademiqueList to set
	 */
	public void setAnneeAcademiqueList(List<SelectItem> anneeAcademiqueList) {
		this.anneeAcademiqueList = anneeAcademiqueList;
	}

	/**
	 * [EmploiSearchBean.offreFormationList] Getter
	 * 
	 * @author BELDI Jamel on : 22 oct. 2014 15:01:15
	 * @return the offreFormationList
	 */
	public List<SelectItem> getOffreFormationList() {
		return offreFormationList;
	}

	/**
	 * [EmploiSearchBean.offreFormationList] Setter
	 * 
	 * @author BELDI Jamel on : 22 oct. 2014 15:01:15
	 * @param offreFormationList
	 *            the offreFormationList to set
	 */
	public void setOffreFormationList(List<SelectItem> offreFormationList) {
		this.offreFormationList = offreFormationList;
	}

	/**
	 * [EmploiSearchBean.emploiSearchDto] Getter
	 * 
	 * @author BELDI Jamel on : 22 oct. 2014 15:01:15
	 * @return the emploiSearchDto
	 */
	public EmploiDto getEmploiSearchDto() {
		return emploiSearchDto;
	}

	/**
	 * [EmploiSearchBean.emploiSearchDto] Setter
	 * 
	 * @author BELDI Jamel on : 22 oct. 2014 15:01:15
	 * @param emploiSearchDto
	 *            the emploiSearchDto to set
	 */
	public void setEmploiSearchDto(EmploiDto emploiSearchDto) {
		this.emploiSearchDto = emploiSearchDto;
	}

	/**
	 * [EmploiSearchBean.emploiList] Getter
	 * 
	 * @author BELDI Jamel on : 22 oct. 2014 15:01:15
	 * @return the emploiList
	 */
	public List<EmploiDto> getEmploiList() {
		return emploiList;
	}

	/**
	 * [EmploiSearchBean.emploiList] Setter
	 * 
	 * @author BELDI Jamel on : 22 oct. 2014 15:01:15
	 * @param emploiList
	 *            the emploiList to set
	 */
	public void setEmploiList(List<EmploiDto> emploiList) {
		this.emploiList = emploiList;
	}

	/**
	 * [EmploiSearchBean.emploiService] Getter
	 * 
	 * @author BELDI Jamel on : 22 oct. 2014 15:07:09
	 * @return the emploiService
	 */
	public EmploiService getEmploiService() {
		return emploiService;
	}

	/**
	 * [EmploiSearchBean.emploiService] Setter
	 * 
	 * @author BELDI Jamel on : 22 oct. 2014 15:07:09
	 * @param emploiService
	 *            the emploiService to set
	 */
	public void setEmploiService(EmploiService emploiService) {
		this.emploiService = emploiService;
	}

}
