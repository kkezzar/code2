package dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.springframework.beans.factory.annotation.Autowired;

import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.ParcoursTypeDto;
import dz.gov.mesrs.sii.fve.business.service.offreformation.ParcoursTypeService;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;


@ManagedBean (name="oFManagerBean")
@SessionScoped
public class OFManagerBean {

	
	public OFManagerBean() {
	}
	

	@Autowired
	@ManagedProperty(value = "#{parcoursTypeService}")
	private ParcoursTypeService parcoursTypeService;
	
	@Autowired
	@ManagedProperty(value = "#{etablissementService}")
	private NomenclatureDto etablissementService;
	
	
	
	private ParcoursTypeDto parcoursType;
	
	private List<ParcoursTypeDto> parcoursTypeList;
	
	private List<NomenclatureDto> etablissementList;

	private String paramSearch;

	
	//************************* getter/setter des services **********************/

	

	public String getParamSearch() {
		return paramSearch;
	}

	public void setParamSearch(String paramSearch) {
		this.paramSearch = paramSearch;
	}

	public ParcoursTypeDto getParcoursType() {
		if (parcoursType == null)
			parcoursType = new ParcoursTypeDto();

		return parcoursType;
	}

	public ParcoursTypeService getParcoursTypeService() {
		return parcoursTypeService;
	}

	public void setParcoursTypeService(ParcoursTypeService parcoursTypeService) {
		this.parcoursTypeService = parcoursTypeService;
	}


	public void setParcoursType(ParcoursTypeDto parcoursTypeDto) {
		this.parcoursType = parcoursTypeDto;
	}
	


	public void insertOrUpdate() {

		if (parcoursType == null)
			return;
		parcoursTypeService.insertOrUpdate(parcoursType);
	}

	
	public void removeUe() {
		parcoursTypeService.remove(parcoursType);
		parcoursType = null;
	}


	public ParcoursTypeDto findById(int id) {
		return parcoursTypeService.findById(id);
	}

	
	public List<ParcoursTypeDto> findAll() {
		return parcoursTypeService.findAll();
	}


	public List<ParcoursTypeDto> find(String code, String libelleFr,
			String abriviationFr, String libelleAr, String abriviationAr) {
		//return uniteEnseignementService.find(code, libelleFr, abriviationFr,
		//		libelleAr, abriviationAr);
		
		return null;
	}

	public List<ParcoursTypeDto> getParcoursTypeList() {
		return parcoursTypeList;
	}

	public void setParcoursTypeList(List<ParcoursTypeDto> parcoursTypeList) {
		this.parcoursTypeList = parcoursTypeList;
	}
	
	
	public void search(){
		
		if(paramSearch != null && paramSearch.trim()!=""){
		 parcoursTypeList = new ArrayList<ParcoursTypeDto>();		
		 parcoursTypeList.add(parcoursTypeService.findById(Integer.parseInt(paramSearch.trim())));
		}
		else 
			parcoursTypeList = findAll();
	
	}

	public List<NomenclatureDto> getEtablissementList() {
		//etablissementList = etablissementService.findAll();
		return etablissementList;
	}

	public void setEtablissementList(List<NomenclatureDto> etablissementList) {
		this.etablissementList = etablissementList;
	}

	public NomenclatureDto getEtablissementService() {
		return etablissementService;
	}

	public void setEtablissementService(NomenclatureDto etablissementService) {
		this.etablissementService = etablissementService;
	}

	

}
