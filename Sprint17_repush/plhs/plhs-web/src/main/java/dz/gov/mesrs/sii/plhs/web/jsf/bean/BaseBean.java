package dz.gov.mesrs.sii.plhs.web.jsf.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedProperty;
import javax.faces.model.SelectItem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dz.gov.mesrs.sii.commons.data.util.UtilConstant;
import dz.gov.mesrs.sii.commons.web.jsf.bean.CommonBaseBean;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;

public abstract class BaseBean extends CommonBaseBean implements Serializable {

	private static final long serialVersionUID = 524706601266281218L;
	private final static Logger logger = LoggerFactory.getLogger(BaseBean.class
			.getName());

	@ManagedProperty(value = "#{serviceLocatorBean}")
	protected ServiceLocatorBean serviceLocator;

	private List<SelectItem> findNomenclatureList(
			String ncFiliereProfessionnelleName) {
		List<NomenclatureDto> dtos = serviceLocator.getNomenclatureService()
				.findByName(ncFiliereProfessionnelleName);
		List<SelectItem> results = new ArrayList<>();
		if (dtos != null) {
			for (NomenclatureDto dto : dtos) {
				results.add(new SelectItem(dto.getId(), dto.getLibelleLongFr()));
			}
		}
		return results;
	}
	
	protected List<SelectItem> findTypeLieuList(){
		return this.findNomenclatureList(UtilConstant.NC_TYPE_LIEU_NAME);
	}

}
