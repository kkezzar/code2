package dz.gov.mesrs.sii.referentiel.business.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.referentiel.NomenclatureDao;
import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefDocumentDao;
import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefMotCleDao;
import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefVersionDocumentDao;
import dz.gov.mesrs.sii.commons.data.model.referentiel.Nomenclature;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefDocument;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefMotCle;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefVersionDocument;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefDocumentDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefDocumentService;

/**
 * 
 * @author yselmane  on : 9 avr. 2014  10:32:39
 */
@Service("refDocumentServiceImpl")
public class RefDocumentServiceImpl implements RefDocumentService {

	private static final Logger log = LoggerFactory.getLogger(RefDocumentServiceImpl.class.getName());
	@Autowired
	@Qualifier("refDocumentDao")
	private RefDocumentDao refDocumentDao;

	@Autowired
	@Qualifier("refMotCleDao")
	private RefMotCleDao refMotCleDao;

	@Autowired
	@Qualifier("refVersionDocumentDao")
	private RefVersionDocumentDao refVersionDocumentDao;
	
	
	@Autowired
	@Qualifier("nomenclatureDaoImpl")
	private NomenclatureDao nomenclatureDaoImpl;

	@Autowired
	@Qualifier("mapper")
	private Mapper mapper;

	public RefDocumentServiceImpl() {

	}

	public RefDocumentDao getRefDocumentDao() {
		return refDocumentDao;
	}

	public void setRefDocumentDao(RefDocumentDao refDocumentDao) {
		this.refDocumentDao = refDocumentDao;
	}

	public RefMotCleDao getRefMotCleDao() {
		return refMotCleDao;
	}

	public void setRefMotCleDao(RefMotCleDao refMotCleDao) {
		this.refMotCleDao = refMotCleDao;
	}

	public RefVersionDocumentDao getRefVersionDocumentDao() {
		return refVersionDocumentDao;
	}

	public void setRefVersionDocumentDao(
			RefVersionDocumentDao refVersionDocumentDao) {
		this.refVersionDocumentDao = refVersionDocumentDao;
	}

	
	public NomenclatureDao getNomenclatureDaoImpl() {
		return nomenclatureDaoImpl;
	}

	public void setNomenclatureDaoImpl(NomenclatureDao nomenclatureDaoImpl) {
		this.nomenclatureDaoImpl = nomenclatureDaoImpl;
	}

	public Mapper getMapper() {
		return mapper;
	}

	public void setMapper(Mapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public RefDocumentDto insertOrUpdate(RefDocumentDto refDocumentDto) {

		RefDocument refDocument = mapper.map(refDocumentDto, RefDocument.class);

		if (refDocument.getId() == 0)
			refDocumentDao.persist(refDocument);
		else
			refDocument = refDocumentDao.merge(refDocument);

		mapper.map(refDocument, refDocumentDto);

		return refDocumentDto;
	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void remove(RefDocumentDto refDocumentDto) {

		try {

						
			// supprimer les mots cles du document

			List<RefMotCle> refMotCles = refMotCleDao
					.findMotsClesDocument(refDocumentDto.getId());

			for (RefMotCle refMotCle : refMotCles) {
				refMotCleDao.remove(refMotCle);
			}
			
			
			// supprimer les versions du refDocument
			
				List<RefVersionDocument> refVersions = refVersionDocumentDao
						.findVersionsOfDocument(refDocumentDto.getId());

				for (RefVersionDocument refVersionDocument : refVersions) {
									
					refVersionDocumentDao.remove(refVersionDocument);
			
				}
			
			
			// enlever le lien des documents qui ont le parent  refDocument
				
			RefDocument refDocument = new RefDocument();
			mapper.map(refDocumentDto, refDocument);
						
			List<RefDocument> refDocuments = refDocumentDao
					.findFilsOfDocument(refDocument);
			
			for (RefDocument currentRefDocument : refDocuments) {
				currentRefDocument.setRefDocument(null);
				currentRefDocument = refDocumentDao.merge(currentRefDocument);
				System.err.println(currentRefDocument.getRefDocument()+"");
			}
			
			refDocument = new RefDocument();
			mapper.map(refDocumentDto, refDocument);
			refDocument = refDocumentDao.merge(refDocument);
			refDocumentDao.remove(refDocument);

		} catch (RuntimeException e) {
			throw e;
		}

	}

	@Override
	public List<RefDocumentDto> findByQuery(String query) {

		List<RefDocument> refDocuments = refDocumentDao.findByQuery(query);

		List<RefDocumentDto> refDocumentDtos = new ArrayList<RefDocumentDto>();

		for (RefDocument refDocument : refDocuments) {
			refDocumentDtos.add(mapper.map(refDocument, RefDocumentDto.class));
		}

		return refDocumentDtos;

	}

	@Override
	public RefDocumentDto findById(int id) {

		RefDocument refDocument = refDocumentDao.findById(id);

		if (refDocument != null){
						
			return mapper.map(refDocument, RefDocumentDto.class);
		}

		return null;
	}

	@Override
	public List<RefDocumentDto> findAll() {

		List<RefDocument> refDocuments = refDocumentDao.findAll();

		List<RefDocumentDto> refDocumentDtos = new ArrayList<RefDocumentDto>();

		for (RefDocument refDocument : refDocuments) {
			refDocumentDtos.add(mapper.map(refDocument, RefDocumentDto.class));
		}

		return refDocumentDtos;

	}

	@Override
	public List<RefDocumentDto> findGeneric(String value) {

		List<RefDocumentDto> refDocumentDtos = new ArrayList<RefDocumentDto>();
		try {
			List<RefDocument> refDocuments = refDocumentDao.findGeneric(value);
			if (refDocuments != null) {
				log.debug("document list success with size {} ", new Object[]{refDocuments.size()});

				for (RefDocument currentRefDocument : refDocuments) {
					refDocumentDtos.add( mapper.map(currentRefDocument,
							RefDocumentDto.class));
				}

			}
		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}
		return refDocumentDtos;
	}

	@Override
	public List<RefDocumentDto> findAdvanced(RefDocumentDto refDocumentDto, List<NomenclatureDto> nomenclatureListDto) {
		List<RefDocumentDto> refDocumentDtos = new ArrayList<RefDocumentDto>();
		try {
			
			List<Nomenclature> nomenclatureList = new ArrayList<Nomenclature>(); 
			
			if(nomenclatureListDto!= null && !nomenclatureListDto.isEmpty()){
				
				for (NomenclatureDto nomenclatureDto : nomenclatureListDto) {
					nomenclatureList.add(mapper.map(nomenclatureDto,
							Nomenclature.class));
				}
				
			}
			
			
			RefDocument refDocument = new RefDocument();
			mapper.map(refDocumentDto, refDocument);
			
			
			List<RefDocument> refDocuments = refDocumentDao
					.findAdvanced(refDocument, nomenclatureList);
			if (refDocuments != null && !refDocuments.isEmpty()) {
				log.debug("document list success with size {} ", new Object[]{refDocuments.size()});

				for (RefDocument currentRefDocument : refDocuments) {
					
					refDocumentDtos.add( mapper.map(currentRefDocument,
							RefDocumentDto.class));
					
				}

			}
		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}
		return refDocumentDtos;
	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public Integer save(RefDocumentDto refDocumentDto) {
		RefDocument refDocument = new RefDocument();

		mapper.map(refDocumentDto, refDocument);

		try {
			refDocumentDao.persist(refDocument);
			log.debug("save successful");
			return refDocument.getId();

		} catch (RuntimeException e) {
			log.error("save failed", e);
			throw e;
		}
	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void update(RefDocumentDto refDocumentDto) {

		RefDocument refDocument = new RefDocument();
		mapper.map(refDocumentDto, refDocument);

		if (refDocumentDto.getIdParentDocument() == null) {
			refDocument.setRefDocument(null);
		}

		if (refDocumentDto.getUrl() == null
				|| refDocumentDto.getUrl().isEmpty()) {
			refDocument.setUrl(null);
		}

		try {
			refDocumentDao.merge(refDocument);
			log.debug("update successful");
		} catch (RuntimeException e) {
			log.error("update failed", e);
			throw e;
		}

	}

	@Override
	public Boolean findByRefDocumentDto(RefDocumentDto refDocumentDto) {

		try {
			RefDocument refDocument = new RefDocument();
			mapper.map(refDocumentDto, refDocument);
			return refDocumentDao.findByRefDocument(refDocument);
			/*
			 * if (refDocument != null) {
			 * log.debug("document found successfuly"); mapper.map(refDocument,
			 * refDocumentDto); return refDocumentDto;
			 * 
			 * }
			 */
		} catch (RuntimeException e) {
			log.error("findByRefDocumentDto failed", e);
			return false;
		}

	}

	@Override
	public List<RefDocumentDto> findDocumentsOfEntity(String entity,
			String idEntity) {

		List<RefDocumentDto> refDocumentDtos = new ArrayList<RefDocumentDto>();
		try {

			List<RefDocument> refDocuments = refDocumentDao
					.findDocumentsOfEntity(entity, idEntity);

			if (refDocuments != null && !refDocuments.isEmpty()) {
				log.debug("document list success with size {} ", new Object[]{refDocuments.size()});

				for (RefDocument currentRefDocument : refDocuments) {
					refDocumentDtos.add(mapper.map(currentRefDocument,
							RefDocumentDto.class));
				}

			}
		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}
		return refDocumentDtos;

	}
	
	
	@Override
	public List<RefDocumentDto> findFilsOfDocument(RefDocumentDto refDocumentDto) {

		List<RefDocumentDto> refDocumentDtos = new ArrayList<RefDocumentDto>();
		try {
			
			RefDocument refDocument = new RefDocument();
			mapper.map(refDocumentDto, refDocument);
			
			List<RefDocument> refDocuments = refDocumentDao
					.findFilsOfDocument(refDocument);

			if (refDocuments != null && !refDocuments.isEmpty()) {
				log.debug("document list success with size {} ", new Object[]{refDocuments.size()});

				for (RefDocument currentRefDocument : refDocuments) {
					refDocumentDtos.add(mapper.map(currentRefDocument,
							RefDocumentDto.class));
				}

			}
		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}
		return refDocumentDtos;

	}
	
	@Override
	public RefDocumentDto findDocumentByCode(String code){
		
			RefDocument refDocument = refDocumentDao
				.findDocumentByCode(code);

		if (refDocument != null){
			return mapper.map(refDocument, RefDocumentDto.class);
		}

		return null;
	}
	
	
	@Override
	public RefDocumentDto findMaxDocumentByType(RefDocumentDto refDocumentDto){
		
		RefDocument refDoc = refDocumentDao
				.findMaxDocumentByType(	mapper.map(refDocumentDto, RefDocument.class)); 

		if (refDoc != null){
			return mapper.map(refDoc, RefDocumentDto.class);
		}

		return null;
		
		
	}

	@Override
	public List<RefDocumentDto> findGeneric(String etabCode, String value) {
		List<RefDocumentDto> refDocumentDtos = new ArrayList<RefDocumentDto>();
		try {
			List<RefDocument> refDocuments = refDocumentDao.findGeneric(etabCode, value);
			if (refDocuments != null) {
				log.debug("document list success with size {} ", new Object[]{refDocuments.size()});

				for (RefDocument currentRefDocument : refDocuments) {
					refDocumentDtos.add( mapper.map(currentRefDocument,
							RefDocumentDto.class));
				}

			}
		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}
		return refDocumentDtos;
	}

	@Override
	public List<RefDocumentDto> findAdvanced(String etabLcLatin,
			RefDocumentDto refDocumentDto,
			List<NomenclatureDto> nomenclatureListDto) {
		List<RefDocumentDto> refDocumentDtos = new ArrayList<RefDocumentDto>();
		try {
			
			List<Nomenclature> nomenclatureList = new ArrayList<Nomenclature>(); 
			
			if(nomenclatureListDto!= null && !nomenclatureListDto.isEmpty()){
				
				for (NomenclatureDto nomenclatureDto : nomenclatureListDto) {
					nomenclatureList.add(mapper.map(nomenclatureDto,
							Nomenclature.class));
				}				
			}
			
			
			RefDocument refDocument = new RefDocument();
			mapper.map(refDocumentDto, refDocument);
			
			
			List<RefDocument> refDocuments = refDocumentDao
					.findAdvanced(etabLcLatin, refDocument, nomenclatureList);
			if (refDocuments != null && !refDocuments.isEmpty()) {
				log.debug("document list success with size {} ", new Object[]{refDocuments.size()});

				for (RefDocument currentRefDocument : refDocuments) {
					
					refDocumentDtos.add( mapper.map(currentRefDocument,
							RefDocumentDto.class));					
				}

			}
		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}
		return refDocumentDtos;
	}
	
	
    @Override
	public String generateCodeDocument(RefDocumentDto refDocumentDto) {

		// recherche dans la base du dernier document enregistré avec le même type que documentDto
		RefDocument lastDocFound = refDocumentDao
				.findMaxDocumentByType(mapper.map(refDocumentDto, RefDocument.class)); 
		
		String code = null;
		
		Calendar cal=Calendar.getInstance();
		int year=cal.get(Calendar.YEAR)%100;

		if (lastDocFound != null && lastDocFound.getId()>0) {
			
			String oldCode = lastDocFound.getIdentifiant();

			int oldSerial = Integer.parseInt(oldCode.substring(5));
			String codeTypeDocument=oldCode.substring(0,3);

				++oldSerial;

				int x = String.valueOf(oldSerial).length();
				String temp = "";

				if (x < 8) {
					for (int i = 0; i < 8 - x; i++) {
						temp += "0";
					}
				}

				temp += oldSerial;

				code = codeTypeDocument + year + temp;

		} else {// si non trouvé, on enregistre le premier document avec ce type
			Nomenclature nomclature = nomenclatureDaoImpl
					.findById(refDocumentDto.getTypeDocumentId());

			code = nomclature.getCode() + year + "00000001";

		}

		return code;
	}

	

}
