package dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.parcours;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.component.selectbooleancheckbox.SelectBooleanCheckbox;

import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.RepartitionUeDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.UniteEnseignementDto;
import dz.gov.mesrs.sii.fve.business.service.impl.offreformation.RepartitionUeServiceImpl;
import dz.gov.mesrs.sii.fve.business.service.impl.offreformation.UniteEnseignementServiceImpl;
import dz.gov.mesrs.sii.fve.business.service.offreformation.UniteEnseignementService;



/**
 * @author obelbrik  on : 01/06. 2014  14:23:30
 * horaire backing bean 
 */


@ManagedBean(name = "parcoursBckBean")
@RequestScoped
public class ParcoursBckBean implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author BELBRIK Oualid on : 01/06. 2014 09:14:39
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * listRefParcoursDto
	 * 
	 * @author BELBRIK Oualid on : 01/06. 2014 09:14:50
	 */
	private List<UniteEnseignementDto> listUniteEnseignementDto;

	/**
	 * 
	 * @author BELBRIK Oualid on : 01/06. 2014 09:14:46
	 */
	

	

	@ManagedProperty("#{param.dossierEtudiantId}")
	private int dossierEtudiantId;
	@ManagedProperty("#{param.codeSemestre}")
	private String codeSemestre;
	@ManagedProperty("#{param.parcoursId}")
	private int parcoursId;
	@ManagedProperty("#{param.ueId}")
	private int ueId;
	@ManagedProperty("#{param.searchValue}")
	private String searchValue;
	private ResourceBundle bundle;
	
	@ManagedProperty(value = "#{uniteEnseignementServiceImpl}")
	private UniteEnseignementService uniteEnseignementServiceImpl;
	
	@ManagedProperty(value = "#{repartitionUeParcoursServiceImpl}")
	private RepartitionUeServiceImpl repartitionUeParcoursServiceImpl;
	
	private List<RepartitionUeDto> listUniteEnseignements;
	
	private static final Log log = LogFactory.getLog(ParcoursBckBean.class);
	

	/**
	 * 
	 * @author BELBRIK Oualid on : 01/06. 2014 09:28:06
	 */
	public ParcoursBckBean() {
		super();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		
		}

	@PostConstruct
	public void init() {
		    loadOuvertureOffre();
		    loadOffreFormation();
		    loadParcours();
		    loadRepartitionUeParcours();
			
		
			listUniteEnseignements = repartitionUeParcoursServiceImpl.find(codeSemestre, parcoursId, ueId);
		}
	

	private void loadRepartitionUeParcours() {
		// TODO Auto-generated method stub
		
	}

	private void loadParcours() {
		// TODO Auto-generated method stub
		
	}

	private void loadOffreFormation() {
		// TODO Auto-generated method stub
		
	}

	private void loadOuvertureOffre() {
		// TODO Auto-generated method stub
		
	}


	


	/**
	 * [ParcoursBckBean.bundle] Getter
	 * 
	 * @author BELBRIK Oualid on : 01 06 2014 11:27:14
	 * @return the bundle
	 */
	public ResourceBundle getBundle() {
		return bundle;
	}

	/**
	 * [ParcoursBckBean.bundle] Setter
	 * 
	 * @author BELBRIK Oualid on : 01 06 2014 11:27:14
	 * @param bundle
	 *            the bundle to set
	 */
	public void setBundle(ResourceBundle bundle) {
		this.bundle = bundle;
	}

	/**
	 * 
	 * @author BELBRIK Oualid on : 01/06. 2014 09:15:11
	 * 
	 */
	public List<UniteEnseignementDto> getListUniteEnseignementDto() {
		return listUniteEnseignementDto;
	}

	/**
	 * 
	 * @author BELBRIK Oualid on : 01/06. 2014 09:15:11
	 * 
	 */
	public void setListUniteEnseignementDto(
			List<UniteEnseignementDto> listUniteEnseignementDto) {
		this.listUniteEnseignementDto = listUniteEnseignementDto;
	}

	

	/**
	 * [ParcoursBckBean.getSearchValue] Method
	 * 
	 * @author BELBRIK Oualid on : 01 06 2014 10:36:17
	 * @return
	 */
	public String getSearchValue() {
		return searchValue;
	}

	/**
	 * [ParcoursBckBean.setSearchValue] Method
	 * 
	 * @author BELBRIK Oualid on : 01 06 2014 10:36:14
	 * @param searchValue
	 */
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	/**
	 * [ParcoursBckBean.back] Method
	 * 
	 * @author BELBRIK Oualid on : 01 06 2014 10:36:11
	 * @return
	 */
	public String back() {
		return "toSearch";
	}

	/**
	 * [ParcoursBckBean.refParcoursServiceImpl] Getter
	 * 
	 * @author BELBRIK Oualid on : 01 06 2014 11:03:48
	 * @return the refParcoursServiceImpl
	 */
	public UniteEnseignementService getUniteEnseignementServiceImpl() {
		return uniteEnseignementServiceImpl;
	}

	/**
	 * [ParcoursBckBean.refParcoursServiceImpl] Setter
	 * 
	 * @author BELBRIK Oualid on : 01 06 2014 11:03:48
	 * @param refParcoursServiceImpl
	 *            the refParcoursServiceImpl to set
	 */
	public void setUniteEnseignementServiceImpl(
			UniteEnseignementService uniteEnseignementServiceImpl) {
		this.uniteEnseignementServiceImpl = uniteEnseignementServiceImpl;
	}

	/**
	 * [ParcoursBckBean.listParcourss] Getter
	 * 
	 * @author BELBRIK Oualid on : 01 06 2014 11:05:42
	 * @return the listParcourss
	 */
	public List<RepartitionUeDto> getListUniteEnseignements() {
		return listUniteEnseignements;
	}

	/**
	 * [ParcoursBckBean.listParcourss] Setter
	 * 
	 * @author BELBRIK Oualid on : 01 06 2014 11:05:42
	 * @param listParcourss
	 *            the listParcourss to set
	 */
	public void setListUniteEnseignements(List<RepartitionUeDto> listUniteEnseignements) {
		this.listUniteEnseignements = listUniteEnseignements;
	}

	
	

	/**
	 * [ParcoursBckBean.save] Method
	 * 
	 * @author BELBRIK Oualid on : 01 06 2014 16:07:30
	 */
	public void save() {
		FacesMessage msg = new FacesMessage();
		msg.setSeverity(FacesMessage.SEVERITY_INFO);
		msg.setSummary(bundle.getString("msg_success") + ": "
				+ bundle.getString("msg_success_modification"));
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}


	/**
	 * [ParcoursBckBean.readCheckChange] Method
	 * 
	 * @author BELBRIK Oualid on : 10 juin 2014 10:22:03
	 * @param event
	 */
	public void readCheckChange(AjaxBehaviorEvent event) {
		SelectBooleanCheckbox permit = (SelectBooleanCheckbox) event
				.getComponent();
		boolean checked = (Boolean) permit.getValue();
		UniteEnseignementDto _uniteEnseignementDto = (UniteEnseignementDto) permit
				.getAttributes().get("selectedRecord");
		Boolean b = (Boolean) permit.getAttributes().get("create");
		System.out.println(b);
		_uniteEnseignementDto.setChoisie(checked);
		
		uniteEnseignementServiceImpl.insertOrUpdate(_uniteEnseignementDto);
	}

		

}
