package dz.gov.mesrs.sii.plhs.web.jsf.bean.lieu;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;





import dz.gov.mesrs.sii.plhs.web.jsf.bean.BaseBean;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefLieuDto;

@ManagedBean(name="lieuMBean")
public class LieuMBean extends BaseBean implements Serializable{
	
	private static Logger logger = LoggerFactory.getLogger(LieuMBean.class.getName());
	private int etablissementId;
	private RefLieuDto searchDto;
	private List<RefLieuDto> dtos;
	private List<SelectItem> typeList;
	
	//init
	@PostConstruct
	public void init(){
		etablissementId = sessionBean.getRefEtablissementDto().getId();
		searchDto = new RefLieuDto();
		typeList = findTypeLieuList();
		onSearch();
	}
	
	//Ajax
	
	public void onSearch(){
		dtos = null;
		dtos = commonServiceLocator.getRefLieuService().findAll(etablissementId);
		
	}
	
	//getters/setters
	public RefLieuDto getSearchDto() {
		return searchDto;
	}
	
	public void setSearchDto(RefLieuDto searchDto) {
		this.searchDto = searchDto;
	}
	
	public List<SelectItem> getTypeList() {
		return typeList;
	}
	public void setTypeList(List<SelectItem> typeList) {
		this.typeList = typeList;
	}
	
	public List<RefLieuDto> getDtos() {
		return dtos;
	}
	
	
	
	

}
