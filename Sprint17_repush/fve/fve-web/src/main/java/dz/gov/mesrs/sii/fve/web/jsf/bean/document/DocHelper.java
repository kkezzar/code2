package dz.gov.mesrs.sii.fve.web.jsf.bean.document;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;

import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.service.NomenclatureService;

@ManagedBean(name = "docHelper")
@RequestScoped
public class DocHelper {

	/**
	 * Service proxy de la gestion des nomenclature.
	 */
	private List<SelectItem> listLangue;
	private List<SelectItem> listTypeDocument;
	private List<SelectItem> listNatureDocument;
	private List<SelectItem> listMotCle;
	private List<SelectItem> listClassement;

	@ManagedProperty("#{nomenclatureServiceImpl}")
	private NomenclatureService nomenclatureService;
	
	public List<SelectItem> getNomenclatureList(String nomenclatureName) {

		List<SelectItem> _ncList = new ArrayList<SelectItem>();

		try {
			List<NomenclatureDto> _typeAps = nomenclatureService
					.findByName(nomenclatureName);

			for (NomenclatureDto item : _typeAps) {
				_ncList.add(new SelectItem(item.getCode(), item
						.getLibelleLongFr()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return _ncList;
	}

	public List<SelectItem> getNomenclatureListById(String nomenclatureName) {

		List<SelectItem> _ncList = new ArrayList<SelectItem>();

		try {
			List<NomenclatureDto> _typeAps = nomenclatureService
					.findByName(nomenclatureName);

			for (NomenclatureDto item : _typeAps) {
				_ncList.add(new SelectItem(item.getId(), item
						.getLibelleLongFr()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return _ncList;
	}

	/**
	 * [DocHelper.listLangue] Getter
	 * 
	 * @author yselmane on : 27 avr. 2014 10:03:34
	 * @return the listLangue
	 */
	public List<SelectItem> getListLangue() {
		if (listLangue == null || listLangue.isEmpty()) {
			listLangue = new ArrayList<SelectItem>();
			listLangue = getNomenclatureListById(DocUtilConstants.NC_LANGUE_NAME);
		}
		return listLangue;
	}

	/**
	 * [DocHelper.listLangue] Setter
	 * 
	 * @author yselmane on : 27 avr. 2014 10:03:34
	 * @param listLangue
	 *            the listLangue to set
	 */
	public void setListLangue(List<SelectItem> listLangue) {
		this.listLangue = listLangue;
	}

	/**
	 * [DocHelper.listTypeDocument] Getter
	 * 
	 * @author yselmane on : 27 avr. 2014 10:03:34
	 * @return the listTypeDocument
	 */
	public List<SelectItem> getListTypeDocument() {
		if (listTypeDocument == null || listTypeDocument.isEmpty()) {
			listTypeDocument = new ArrayList<SelectItem>();
			listTypeDocument = getNomenclatureListById(DocUtilConstants.NC_TYPE_DOCUMENT_NAME);
		}
		return listTypeDocument;
	}

	/**
	 * [DocHelper.listTypeDocument] Setter
	 * 
	 * @author yselmane on : 27 avr. 2014 10:03:34
	 * @param listTypeDocument
	 *            the listTypeDocument to set
	 */
	public void setListTypeDocument(List<SelectItem> listTypeDocument) {
		this.listTypeDocument = listTypeDocument;
	}

	/**
	 * [DocHelper.listNatureDocument] Getter
	 * 
	 * @author yselmane on : 27 avr. 2014 10:03:34
	 * @return the listNatureDocument
	 */
	public List<SelectItem> getListNatureDocument() {
		if (listNatureDocument == null || listNatureDocument.isEmpty()) {
			listNatureDocument = new ArrayList<SelectItem>();
			listNatureDocument = getNomenclatureListById(DocUtilConstants.NC_NATURE_DOCUMENT_NAME);
		}
		return listNatureDocument;
	}

	/**
	 * [DocHelper.listNatureDocument] Setter
	 * 
	 * @author yselmane on : 27 avr. 2014 10:03:34
	 * @param listNatureDocument
	 *            the listNatureDocument to set
	 */
	public void setListNatureDocument(List<SelectItem> listNatureDocument) {
		this.listNatureDocument = listNatureDocument;
	}

	/**
	 * [DocHelper.listMotCle] Getter
	 * 
	 * @author yselmane on : 27 avr. 2014 10:03:34
	 * @return the listMotCle
	 */
	public List<SelectItem> getListMotCle() {
		if (listMotCle == null || listMotCle.isEmpty()) {
			listMotCle = new ArrayList<SelectItem>();
			listMotCle = getNomenclatureListById(DocUtilConstants.NC_MOT_CLE_NAME);
		}
		return listMotCle;
	}

	/**
	 * [DocHelper.listMotCle] Setter
	 * 
	 * @author yselmane on : 27 avr. 2014 10:03:34
	 * @param listMotCle
	 *            the listMotCle to set
	 */
	public void setListMotCle(List<SelectItem> listMotCle) {
		this.listMotCle = listMotCle;
	}

	/**
	 * [DocHelper.listClassement] Getter
	 * 
	 * @author yselmane on : 27 avr. 2014 10:03:34
	 * @return the listClassement
	 */
	public List<SelectItem> getListClassement() {
		if (listClassement == null || listClassement.isEmpty()) {
			listClassement = new ArrayList<SelectItem>();
			listClassement = getNomenclatureListById(DocUtilConstants.NC_CLASSEMENT_NAME);
		}
		return listClassement;
	}

	/**
	 * [DocHelper.listClassement] Setter
	 * 
	 * @author yselmane on : 27 avr. 2014 10:03:34
	 * @param listClassement
	 *            the listClassement to set
	 */
	public void setListClassement(List<SelectItem> listClassement) {
		this.listClassement = listClassement;
	}

	/**
	 * [DocHelper.nomenclatureService] Getter 
	 * @author rlaib on : 20 nov. 2014  16:53:45
	 * @return the nomenclatureService
	 */
	public NomenclatureService getNomenclatureService() {
		return nomenclatureService;
	}

	/**
	 * [DocHelper.nomenclatureService] Setter 
	 * @author rlaib on : 20 nov. 2014  16:53:45
	 * @param nomenclatureService the nomenclatureService to set
	 */
	public void setNomenclatureService(NomenclatureService nomenclatureService) {
		this.nomenclatureService = nomenclatureService;
	}
	

}
