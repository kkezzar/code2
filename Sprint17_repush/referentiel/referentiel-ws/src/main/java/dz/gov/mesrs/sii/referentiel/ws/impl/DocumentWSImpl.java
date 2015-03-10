/**
 * [dz.gov.mesrs.sii.referentiel.ws.impl.DocumentWSImpl.java] 
 * @author rlaib on : 12 mars 2014  14:38:52
 */
package dz.gov.mesrs.sii.referentiel.ws.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefDocumentDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefMotCleDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefDocumentService;
import dz.gov.mesrs.sii.referentiel.business.service.RefMotCleService;
import dz.gov.mesrs.sii.referentiel.business.service.RefParametrageService;
import dz.gov.mesrs.sii.referentiel.ws.service.DocumentWS;

/**
 * 
 * @author yselmane on : 11 juin 2014 10:59:02
 */
@Service("documentWSImpl")
public class DocumentWSImpl implements DocumentWS {

	private static final Log log = LogFactory.getLog(DocumentWSImpl.class);

	// ===================================================================
	// Constructor
	// ===================================================================
	/**
	 * 
	 * [DocumentWSImpl.DocumentWSImpl()] Constructor
	 * 
	 * @author yselmane on : 11 juin 2014 11:07:36
	 */
	public DocumentWSImpl() {

	}

	// ===================================================================
	// Properties
	// ===================================================================

	@Autowired
	@Qualifier("refDocumentServiceImpl")
	private RefDocumentService refDocumentService;

	@Autowired
	@Qualifier("refMotCleServiceImpl")
	private RefMotCleService refMotCleServiceImpl;

	@Autowired
	@Qualifier("refParametrageServiceImpl")
	private RefParametrageService refParametrageServiceImpl;

	// ===================================================================
	// Properties Getters / Setters
	// ===================================================================


	public RefDocumentService getRefDocumentService() {
		return refDocumentService;
	}


	public void setRefDocumentService(RefDocumentService refDocumentService) {
		this.refDocumentService = refDocumentService;
	}

	public RefMotCleService getRefMotCleServiceImpl() {
		return refMotCleServiceImpl;
	}

	public void setRefMotCleServiceImpl(RefMotCleService refMotCleServiceImpl) {
		this.refMotCleServiceImpl = refMotCleServiceImpl;
	}

	public RefParametrageService getRefParametrageServiceImpl() {
		return refParametrageServiceImpl;
	}

	public void setRefParametrageServiceImpl(
			RefParametrageService refParametrageServiceImpl) {
		this.refParametrageServiceImpl = refParametrageServiceImpl;
	}

	// ===================================================================
	// Implementation methods [Document]
	// ===================================================================
	/**
	 * Permet de rechercher par texte intégrales des documents
	 */
	@Override
	public List<RefDocumentDto> findGenericDocument(String value)
			throws Exception {

		List<RefDocumentDto> result = new ArrayList<RefDocumentDto>();
		try {
			result = refDocumentService.findGeneric(value);
			log.debug("findGeneric Document successful");
		} catch (Exception e) {
			log.error("findGeneric Document  failed", e);
			throw e;
		}
		return result;

	}

	/**
	 * Permet de rechercher des documents qui ressemblent à l'objet document dto
	 * passé en paramètre.
	 */
	@Override
	public List<RefDocumentDto> findAdvancedDocument(
			RefDocumentDto refDocumentDto,
			List<NomenclatureDto> nomenclatureListDto) throws Exception {

		List<RefDocumentDto> result = new ArrayList<RefDocumentDto>();
		try {
			result = refDocumentService.findAdvanced(refDocumentDto,
					nomenclatureListDto);
			log.debug("findAdvanced Document successful");
		} catch (Exception e) {
			log.error("findAdvanced Document  failed", e);
			throw e;
		}
		return result;
	}

	@Override
	public RefDocumentDto findByIdDocument(int id) throws Exception {

		RefDocumentDto refDocumentDto;
		try {
			refDocumentDto = refDocumentService.findById(id);
			log.debug("findById Document successful");
		} catch (Exception e) {
			log.error("findById Document  failed", e);
			throw e;
		}
		return refDocumentDto;
	}

	@Override
	public Boolean findByRefDocumentDto(RefDocumentDto refDocumentDto)
			throws Exception {

		Boolean trouve = false;
		try {
			trouve = refDocumentService.findByRefDocumentDto(refDocumentDto);
			log.debug("findByRefDocumentDto Document successful");
		} catch (Exception e) {
			log.error("findByRefDocumentDto Document  failed", e);
			throw e;
		}

		return trouve;
	}

	@Override
	public List<RefDocumentDto> findDocumentsOfEntity(String entity,
			String idEntity) throws Exception {

		List<RefDocumentDto> result = new ArrayList<RefDocumentDto>();
		try {
			result = refDocumentService.findDocumentsOfEntity(entity, idEntity);
			log.debug("findDocumentsOfEntity Document successful");
		} catch (Exception e) {
			log.error("findDocumentsOfEntity Document  failed", e);
			throw e;
		}
		return result;
	}

	@Override
	public String generateCodeDocument(RefDocumentDto refDocumentDto)
			throws Exception {

		String code = null;
		try {
			code = refDocumentService.generateCodeDocument(refDocumentDto);
			log.debug("generateCodeDocument Document successful");
		} catch (Exception e) {
			log.error("generateCodeDocument Document  failed", e);
			throw e;
		}

		return code;
	}

	@Override
	public void removeDocument(RefDocumentDto refDocumentDto) throws Exception {

		try {
			refDocumentService.remove(refDocumentDto);
			log.debug("remove Document successful");
		} catch (Exception e) {
			log.error("remove Document  failed", e);
			throw e;
		}
	}

	@Override
	public Integer saveDocument(RefDocumentDto refDocumentDto) throws Exception {

		Integer idDocument = null;
		try {
			idDocument = refDocumentService.save(refDocumentDto);
			log.debug("save Document successful");
		} catch (Exception e) {
			log.error("save Document  failed", e);
			throw e;
		}

		return idDocument;
	}

	@Override
	public void updateDocument(RefDocumentDto refDocumentDto) throws Exception {
		try {
			refDocumentService.update(refDocumentDto);
			log.debug("update Document successful");
		} catch (Exception e) {
			log.error("update Document failed", e);
			throw e;
		}
	}

	@Override
	public List<RefMotCleDto> findMotsClesDocument(String idDocument)
			throws Exception {
		List<RefMotCleDto> result = new ArrayList<RefMotCleDto>();
		try {
			result = refMotCleServiceImpl.findMotsClesDocument(idDocument);
			log.debug("findMotsClesDocument Document successful");
		} catch (Exception e) {
			log.error("findMotsClesDocument Document  failed", e);
			throw e;
		}
		return result;
	}

	@Override
	public List<NomenclatureDto> findNomenclatureMotsClesDocument(
			String idDocument) throws Exception {
		List<NomenclatureDto> result = new ArrayList<NomenclatureDto>();
		try {
			result = refMotCleServiceImpl
					.findNomenclatureMotsClesDocument(idDocument);
			log.debug("findNomenclatureMotsClesDocument Document successful");
		} catch (Exception e) {
			log.error("findNomenclatureMotsClesDocument Document  failed", e);
			throw e;
		}
		return result;
	}

	@Override
	public void removeMotsClesDocument(String idDocument) throws Exception {
		try {
			refMotCleServiceImpl.removeMotsClesDocument(idDocument);
			log.debug("removeMotsClesDocument successful");
		} catch (Exception e) {
			log.error("removeMotsClesDocument failed", e);
			throw e;
		}

	}

	@Override
	public void saveMotCle(RefMotCleDto refMotCleDto) throws Exception {
		try {
			refMotCleServiceImpl.save(refMotCleDto);
			log.debug("saveMotCle successful");
		} catch (Exception e) {
			log.error("saveMotCle failed", e);
			throw e;
		}

	}

	@Override
	public RefDocumentDto findDocumentByCode(String code) throws Exception {
		RefDocumentDto refDocumentDto = null;
		try {
			refDocumentDto = refDocumentService.findDocumentByCode(code);
			log.debug("findDocumentByCode successful");
		} catch (Exception e) {
			log.error("findDocumentByCode  failed", e);
			throw e;
		}
		return refDocumentDto;
	}

	

}
