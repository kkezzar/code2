package dz.gov.mesrs.sii.recherche.web.jsf.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedProperty;
import javax.faces.model.SelectItem;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import dz.gov.mesrs.sii.commons.data.util.UtilConstant;
import dz.gov.mesrs.sii.commons.web.jsf.bean.CommonBaseBean;
import dz.gov.mesrs.sii.recherche.business.model.dto.StructureDto;
import dz.gov.mesrs.sii.recherche.web.jsf.bean.locator.ServiceLocatorBean;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEtablissementDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefStructureDto;

/**
 * 
 * @author Salem
 * @version V1.0
 * @date 27-10-2014
 * @description classe abstraite pour les instanciations courantes
 * 
 */

public abstract class BaseBean extends CommonBaseBean implements Serializable {

	private static final long serialVersionUID = 1L;

	// the logger for this class
	protected final Log logger = LogFactory.getLog(BaseBean.class);

	// the service locator of the business services
	@ManagedProperty(value = "#{serviceLocatorBean}")
	protected ServiceLocatorBean serviceLocator;

	/**
	 * @author Salem
	 * @version 1.0
	 * @description Constructeur du bean de base
	 */
	public BaseBean() {

	}

	/**
	 * 
	 * @param serviceLocatorBean
	 */
	public void setServiceLocator(ServiceLocatorBean serviceLocatorBean) {
		this.serviceLocator = serviceLocatorBean;
	}

	public List<SelectItem> findNomenclatureList(String ncFiliereProfessionnelleName) {
		List<NomenclatureDto> dtos = serviceLocator.getNomenclatureService().findByName(ncFiliereProfessionnelleName);
		List<SelectItem> results = new ArrayList<>();
		if (dtos != null && !dtos.isEmpty()) {
			for (NomenclatureDto dto : dtos) {
				results.add(new SelectItem(dto.getId(), dto.getLibelleLongFr()));
			}
		}
		return results;
	}

	/**
	 * find all etablissements
	 * 
	 * @author Mounir.MESSAOUDI on : 8 dï¿½c. 2014 09:25:18
	 * @return
	 */
	public List<SelectItem> findListeEtablissements() {
		List<RefEtablissementDto> dtos = this.serviceLocator.getEtablissementService().findAll();
		List<SelectItem> result = new ArrayList<>();
		if (dtos != null && !dtos.isEmpty()) {
			for (RefEtablissementDto dto : dtos) {
				result.add(new SelectItem(dto.getId(), dto.getLlEtablissementLatin()));
			}

		}

		return result;
	}

	/**
	 * find structure list by etablissement
	 * 
	 * @author Mounir.MESSAOUDI on : 8 dï¿½c. 2014 09:27:09
	 * @param refEtablissementDto
	 * @return
	 */
	public List<SelectItem> findListeStructure(RefEtablissementDto refEtablissementDto) {
		List<RefStructureDto> dtos = this.serviceLocator.getStructureService().findAll();
		List<SelectItem> result = new ArrayList<>();
		if (dtos != null && !dtos.isEmpty()) {
			for (RefStructureDto dto : dtos) {
				result.add(new SelectItem(dto.getId(), dto.getLlStructureLatin()));
			}
		}

		return result;
	}

	/**
	 * [BaseBean.findListeStructureRecherche] Method 
	 * @author rlaib  on : 16 dec. 2014  16:27:08
	 * @return
	 */
	public List<SelectItem> findListeStructureRecherche() {
		List<StructureDto> dtos = this.serviceLocator.getRechercheStructureService().findAll();
		List<SelectItem> result = new ArrayList<>();
		if (dtos != null && !dtos.isEmpty()) {
			for (StructureDto dto : dtos) {
				result.add(new SelectItem(dto.getId(), dto.getRefStructureLibelleLatin()));
			}
		}

		return result;
	}
	
	/**
	 * [BaseBean.findStrcutureTypes] Method 
	 * @author rlaib  on : 22 déc. 2014  10:30:28
	 * @param idEtablissement
	 * @return
	 */
	public List<SelectItem> findStructureTypes(Integer idEtablissement) {
		List<NomenclatureDto> dtos = this.serviceLocator.getNomenclatureService().findByName(UtilConstant.NC_TYPE_STRUCTURE_NAME);
		
		List<SelectItem> result = new ArrayList<>();
		if (dtos != null && !dtos.isEmpty()) {
			for (NomenclatureDto dto : dtos) {
				result.add(new SelectItem(dto.getId(), dto.getLibelleLongFr()));
			}
		}

		return result;
	}
	
	/**
	 * [BaseBean.findRechercheThemes] Method 
	 * @author rlaib  on : 22 déc. 2014  10:32:00
	 * @return
	 */
	public List<SelectItem> findNomenclatureByName(String ncName) {
		List<NomenclatureDto> dtos = this.serviceLocator.getNomenclatureService().findByName(ncName);
		
		List<SelectItem> result = new ArrayList<>();
		if (dtos != null && !dtos.isEmpty()) {
			for (NomenclatureDto dto : dtos) {
				result.add(new SelectItem(dto.getId(), dto.getLibelleLongFr()));
			}
		}
		
		return result;
	}

}
