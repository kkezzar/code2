package dz.gov.mesrs.sii.fve.web.jsf.bean.test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;

import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.CursusConstants;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.service.NomenclatureService;

@ManagedBean(name = "viewTestBB")
@RequestScoped
public class ViewTestBB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public ViewTestBB() {
		super();

		beanJava = new BeanJava();
	}

	@PostConstruct
	public void init() {

//		bean = new BeanJava();
//		
//		domaineList = referentielHelper.findAllDomaines();

	}

//	private void loadFilieres() {
//
//		if (filiereList != null && !filiereList.isEmpty())
//			return;
//
//		if (bean.getDomaine() != null) {
//			filiereList = referentielHelper.findFilieresOfDomaine(bean
//					.getDomaine());
//		} else
//			filiereList = new ArrayList<SelectItem>(0);
//
//		specialiteList = new ArrayList<SelectItem>(0);
//		offreFormationList = new ArrayList<SelectItem>(0);
//
//	}
//
//	private void loadSpecilaites() {
//
//		if (specialiteList != null && !specialiteList.isEmpty())
//
//			if (bean.getFiliere() != null)
//				specialiteList = referentielHelper
//						.findSpecialitesOfFiliere(bean.getFiliere());
//			else
//				specialiteList = new ArrayList<SelectItem>(0);
//
//		offreFormationList = new ArrayList<SelectItem>(0);
//	}
//
//	private void loadOffresFormationList() {
//
//		// offreFormationList = new ArrayList<SelectItem>();
//		// try {
//		//
//		// if (selectedSpecialite == null
//		// || selectedSpecialite.trim().isEmpty()
//		// || selectedSpecialite.trim().equals("null")
//		// || selectedSpecialite.trim().equals("0"))
//		// return;
//		//
//		// List<OuvertureOffreFormationDto> _oofDtoList =
//		// ouvertureOffreFormationService
//		// .findAdvanced(null, selectedSpecialite, 1, true);
//		//
//		// for (OuvertureOffreFormationDto item : _oofDtoList) {
//		//
//		// OffreFormationDto _ofdto = offreFormationService.findById(item
//		// .getOffreFormationId());
//		//
//		// offreFormationList.add(new SelectItem(_ofdto.getId(), _ofdto
//		// .getI18nDtos().get("fr").getLibelle()));
//		// }
//		// } catch (Exception e) {
//		// e.printStackTrace();
//		// }
//	}
//
//	public void doAction(String idDom) {
//		System.out.println(bean.getDomaine());
//		System.out.println(bean.getFiliere());
//		System.out.println(bean.getSpecialite());
//	}
//
//	public void specialiteValueChanged(AjaxBehaviorEvent event) {
//		loadOffresFormationList();
//	}
//
//	@ManagedProperty(value = "#{ouvertureOffreFormationService}")
//	private OuvertureOffreFormationService ouvertureOffreFormationService;
//
//	@ManagedProperty(value = "#{offreFormationService}")
//	private OffreFormationService offreFormationService;
//
//	@ManagedProperty(value = "#{anneeAcademiqueService}")
//	private AnneeAcademiqueService anneeAcademiqueService;
//
//	@ManagedProperty(value = "#{referentielHelper}")
//	private ReferentielHelper referentielHelper;

//	private BeanJava bean;
//
//	private List<SelectItem> domaineList;
//	private List<SelectItem> filiereList;
//	private List<SelectItem> specialiteList;
//	private List<SelectItem> offreFormationList;
//
//	public BeanJava getBean() {
//		return bean;
//	}
//
//	public void setBean(BeanJava bean) {
//		this.bean = bean;
//	}
//
//	public OuvertureOffreFormationService getOuvertureOffreFormationService() {
//		return ouvertureOffreFormationService;
//	}
//
//	public void setOuvertureOffreFormationService(
//			OuvertureOffreFormationService ouvertureOffreFormationService) {
//		this.ouvertureOffreFormationService = ouvertureOffreFormationService;
//	}
//
//	public OffreFormationService getOffreFormationService() {
//		return offreFormationService;
//	}
//
//	public void setOffreFormationService(
//			OffreFormationService offreFormationService) {
//		this.offreFormationService = offreFormationService;
//	}
//
//	public AnneeAcademiqueService getAnneeAcademiqueService() {
//		return anneeAcademiqueService;
//	}
//
//	public void setAnneeAcademiqueService(
//			AnneeAcademiqueService anneeAcademiqueService) {
//		this.anneeAcademiqueService = anneeAcademiqueService;
//	}
//
//	public ReferentielHelper getReferentielHelper() {
//		return referentielHelper;
//	}
//
//	public void setReferentielHelper(ReferentielHelper referentielHelper) {
//		this.referentielHelper = referentielHelper;
//	}
//
//	public List<SelectItem> getDomaineList() {
//		return domaineList;
//	}
//
//	public void setDomaineList(List<SelectItem> domaineList) {
//		this.domaineList = domaineList;
//	}
//
//	public List<SelectItem> getFiliereList() {
//		loadFilieres();
//		return filiereList;
//	}
//
//	public void setFiliereList(List<SelectItem> filiereList) {
//		this.filiereList = filiereList;
//	}
//
//	public List<SelectItem> getSpecialiteList() {
//		loadSpecilaites();
//		return specialiteList;
//	}
//
//	public void setSpecialiteList(List<SelectItem> specialiteList) {
//		this.specialiteList = specialiteList;
//	}
//
//	public List<SelectItem> getOffreFormationList() {
//		return offreFormationList;
//	}
//
//	public void setOffreFormationList(List<SelectItem> offreFormationList) {
//		this.offreFormationList = offreFormationList;
//	}
//	
	
	public void doAction() {
		System.out.println(beanJava.getCommuneId());
	}

	private BeanJava beanJava;
	

	private List<SelectItem> listPays;
	private List<SelectItem> listWilaya;
	private List<SelectItem> listDaira;
	private List<SelectItem> listCommune;
	@ManagedProperty("nomenclatureServiceImpl")
	private NomenclatureService nomenclatureService;
	
	public List<SelectItem> getListWilaya() {
		return loadWilaya();
	}

	
	public void setListWilaya(List<SelectItem> listWilaya) {
		this.listWilaya = listWilaya;
	}

	
	public List<SelectItem> getListCommune() {
		return loadCommune();
	}

	
	public void setListCommune(List<SelectItem> listCommune) {
		this.listCommune = listCommune;
	}

	
	public List<SelectItem> getListDaira() {
		return loadDaira();
	}

	
	public void setListDaira(List<SelectItem> listDaira) {
		this.listDaira = listDaira;
	}
	
	
	public BeanJava getBeanJava() {
		return beanJava;
	}

	public void setBeanJava(BeanJava beanJava) {
		this.beanJava = beanJava;
	}

	public List<SelectItem> loadDaira() {
		try {
		if (listDaira == null || listDaira.isEmpty()) {
			listDaira = new ArrayList<SelectItem>();
			List<NomenclatureDto> list = null;
			if (beanJava != null && beanJava.getWilayaId() != null) {
				
					list = nomenclatureService
							.findNcCompositeList(beanJava.getWilayaId(),
									CursusConstants.NC_DAIRA_NAME);
				
			} else {
				list = nomenclatureService
						.findByName(CursusConstants.NC_DAIRA_NAME);

			}
			for (NomenclatureDto nomenclatureDto : list) {
				SelectItem selectItem = new SelectItem(nomenclatureDto.getId(),
						nomenclatureDto.getLibelleLongFr());
				listDaira.add(selectItem);
			}
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listDaira;
	}

	
	public List<SelectItem> loadWilaya() {
		try {
		if (listWilaya == null || listWilaya.isEmpty()) {
			listWilaya = new ArrayList<SelectItem>();
			List<NomenclatureDto> list = null;
			if (beanJava != null && beanJava.getPaysId() != null) {
				list = nomenclatureService.findNcCompositeList(
						beanJava.getPaysId(), CursusConstants.NC_WILAYA_NAME);
			} else {
				list = nomenclatureService
						.findByName(CursusConstants.NC_WILAYA_NAME);
			}
			for (NomenclatureDto nomenclatureDto : list) {
				SelectItem selectItem = new SelectItem(nomenclatureDto.getId(),
						nomenclatureDto.getLibelleLongFr());
				listWilaya.add(selectItem);
			}
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listWilaya;
	}

	
	public List<SelectItem> loadCommune() {
		try {	
		if (listCommune == null || listCommune.isEmpty()) {
			listCommune = new ArrayList<SelectItem>();
			List<NomenclatureDto> list = null;
			if (beanJava != null && beanJava.getDairaId() != null) {
				list = nomenclatureService.findNcCompositeList(
						beanJava.getDairaId(),
						CursusConstants.NC_COMMUNE_NAME);
			} else {
				list = nomenclatureService
						.findByName(CursusConstants.NC_COMMUNE_NAME);

			}
			for (NomenclatureDto nomenclatureDto : list) {
				SelectItem selectItem = new SelectItem(nomenclatureDto.getId(),
						nomenclatureDto.getLibelleLongFr());
				listCommune.add(selectItem);
			}
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listCommune;
	}

	
	public List<SelectItem> getListPays() {
		try {
			if (listPays == null || listPays.isEmpty()) {
				listPays = new ArrayList<SelectItem>();
				List<NomenclatureDto> list = null;
				list =nomenclatureService
						.findByName(CursusConstants.NC_PAYS_NAME);
				for (NomenclatureDto nomenclatureDto : list) {
					SelectItem selectItem = new SelectItem(nomenclatureDto.getId(),
							nomenclatureDto.getLibelleLongFr());
					listPays.add(selectItem);
				}
				
			}
			} catch (Exception e) {
				e.printStackTrace();
			}
		return listPays;
	}
	/**
	 * [CoordonneeBean.listPays] Setter 
	 * @author BELDI Jamel on : 28 mai 2014  18:13:15
	 * @param listPays the listPays to set
	 */
	public void setListPays(List<SelectItem> listPays) {
		this.listPays = listPays;
	}
	
	public void paysChanged() {

	}

	
	public void wilayaChanged() {

	}

	
	public void dairaChanged() {

	}

	/**
	 * [ViewTestBB.nomenclatureService] Getter 
	 * @author rlaib on : 23 nov. 2014  10:48:37
	 * @return the nomenclatureService
	 */
	public NomenclatureService getNomenclatureService() {
		return nomenclatureService;
	}

	/**
	 * [ViewTestBB.nomenclatureService] Setter 
	 * @author rlaib on : 23 nov. 2014  10:48:37
	 * @param nomenclatureService the nomenclatureService to set
	 */
	public void setNomenclatureService(NomenclatureService nomenclatureService) {
		this.nomenclatureService = nomenclatureService;
	}


}
