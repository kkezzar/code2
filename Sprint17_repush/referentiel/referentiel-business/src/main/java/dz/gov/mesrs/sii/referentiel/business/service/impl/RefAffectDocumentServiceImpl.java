package dz.gov.mesrs.sii.referentiel.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefAffectDocumentDao;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefAffectDocument;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefAffectDocumentDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefAffectDocumentService;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 11-05-2014 16:44:07
 */

@Service("refAffectDocumentServiceImpl")
public class RefAffectDocumentServiceImpl implements RefAffectDocumentService {

	@Autowired
	@Qualifier("refAffectDocumentDaoImpl")
	private RefAffectDocumentDao refAffectDocumentDao;

	private static final Logger log = LoggerFactory.getLogger(RefAffectDocumentServiceImpl.class.getName());

	@Autowired
	@Qualifier("mapper")
	private Mapper mapper;

	public RefAffectDocumentServiceImpl() {

	}

	@Override
	public RefAffectDocumentDto insertOrUpdate(
			RefAffectDocumentDto refAffectDocumentDto) {

		RefAffectDocument refAffectDocument = mapper.map(refAffectDocumentDto,
				RefAffectDocument.class);

		if (refAffectDocument.getId() == 0)
			refAffectDocumentDao.persist(refAffectDocument);
		else
			refAffectDocument = refAffectDocumentDao.merge(refAffectDocument);

		mapper.map(refAffectDocument, refAffectDocumentDto);

		return refAffectDocumentDto;
	}

	@Override
	public void remove(RefAffectDocumentDto refAffectDocumentDto) {

		RefAffectDocument refAffectDocument = mapper.map(refAffectDocumentDto,
				RefAffectDocument.class);
		try {
			refAffectDocument = refAffectDocumentDao.merge(refAffectDocument);
			refAffectDocumentDao.remove(refAffectDocument);
		} catch (RuntimeException e) {
			log.error("save failed", e);
			throw e;
		}
	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void save(RefAffectDocumentDto refAffectDocumentDto) {
		RefAffectDocument refAffectDocument = new RefAffectDocument();
		try {
			mapper.map(refAffectDocumentDto, refAffectDocument);

			refAffectDocumentDao.persist(refAffectDocument);
			log.debug("save successful");
		} catch (javax.persistence.PersistenceException cve) {
			throw cve;

		} catch (RuntimeException e) {
			log.error("save failed", e);
			throw e;
		}

	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void update(RefAffectDocumentDto refAffectDocumentDto) {
		RefAffectDocument refAffectDocument = new RefAffectDocument();
		try {
			mapper.map(refAffectDocumentDto, refAffectDocument);
			refAffectDocumentDao.merge(refAffectDocument);
			log.debug("update successful");
		} catch (RuntimeException e) {
			log.error("update failed", e);
			throw e;
		}

	}

	@Override
	public List<RefAffectDocumentDto> findByQuery(String query) {

		List<RefAffectDocument> refAffectDocuments = refAffectDocumentDao
				.findByQuery(query);

		List<RefAffectDocumentDto> refAffectDocumentDtos = new ArrayList<RefAffectDocumentDto>();

		for (RefAffectDocument refAffectDocument : refAffectDocuments) {
			refAffectDocumentDtos.add(mapper.map(refAffectDocument,
					RefAffectDocumentDto.class));
		}

		return refAffectDocumentDtos;

	}

	@Override
	public RefAffectDocumentDto findById(int id) {

		RefAffectDocument refAffectDocument = refAffectDocumentDao.findById(id);

		if (refAffectDocument != null)
			return mapper.map(refAffectDocument, RefAffectDocumentDto.class);

		return null;
	}

	@Override
	public List<RefAffectDocumentDto> findAll() {

		List<RefAffectDocument> refAffectDocuments = refAffectDocumentDao
				.findAll();

		List<RefAffectDocumentDto> refAffectDocumentDtos = new ArrayList<RefAffectDocumentDto>();

		for (RefAffectDocument refAffectDocument : refAffectDocuments) {
			refAffectDocumentDtos.add(mapper.map(refAffectDocument,
					RefAffectDocumentDto.class));
		}

		return refAffectDocumentDtos;

	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public List<RefAffectDocumentDto> findStructuresOfDocument(String idEntity) {
		List<RefAffectDocumentDto> refAffectationListDto = new ArrayList<RefAffectDocumentDto>();
		try {
			List<RefAffectDocument> refAffectationList = null;
			if (idEntity != null) {
				refAffectationList = (List<RefAffectDocument>) refAffectDocumentDao
						.findStructuresOfDocument(idEntity);
			} else {
				return refAffectationListDto;
			}
			if (refAffectationList != null) {
				log.debug("affectation list success with size {}  ", new Object[]{ refAffectationList.size()});
				for (RefAffectDocument currentRefAffectation : refAffectationList) {
					RefAffectDocumentDto refAffectationDto = new RefAffectDocumentDto();
					mapper.map(currentRefAffectation, refAffectationDto);
					refAffectationListDto.add(refAffectationDto);
				}

			}
		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}
		return refAffectationListDto;
	}

	/**
	 * [RefAffectDocumentServiceImpl.findGroupes] Find the GROUPES Affected to
	 * INDIVIDU or the GROUPES Affected to STRUCTURE
	 * 
	 * @author SELMANE Yazid on : 11 may 2014 10:40:26
	 * @param the
	 *            entity , the idEntity
	 * @return List of RefAffectationDto
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public List<RefAffectDocumentDto> findGroupesOfDocument(String idEntity) {
		List<RefAffectDocumentDto> refAffectationListDto = new ArrayList<RefAffectDocumentDto>();
		try {
			List<RefAffectDocument> refAffectationList = null;
			if (idEntity != null) {
				refAffectationList = (List<RefAffectDocument>) refAffectDocumentDao
						.findGroupesOfDocument(idEntity);
			} else {
				return refAffectationListDto;
			}
			if (refAffectationList != null) {
				log.debug("affectation list success with size {}  ", new Object[]{ refAffectationList.size()});
				for (RefAffectDocument currentRefAffectation : refAffectationList) {
					RefAffectDocumentDto refAffectationDto = new RefAffectDocumentDto();
					mapper.map(currentRefAffectation, refAffectationDto);
					refAffectationListDto.add(refAffectationDto);
				}

			}
		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}
		return refAffectationListDto;
	}

	/**
	 * [RefAffectDocumentServiceImpl.findIndividus] Find the INDIVIDUS of GROUPE
	 * or the INDIVIDUS of STRUCTURE
	 * 
	 * @author SELMANE Yazid on : 11 may 2014 10:40:26
	 * @param the
	 *            entity , the idEntity
	 * @return List of RefAffectationDto
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public List<RefAffectDocumentDto> findIndividusOfDocument(String idEntity) {
		List<RefAffectDocumentDto> refAffectationListDto = new ArrayList<RefAffectDocumentDto>();
		try {
			List<RefAffectDocument> refAffectationList = null;
			if (idEntity != null) {
				refAffectationList = (List<RefAffectDocument>) refAffectDocumentDao
						.findIndividusOfDocument(idEntity);
			} else {
				return refAffectationListDto;
			}
			if (refAffectationList != null && !refAffectationList.isEmpty()) {
				log.debug("affectation list success with size {}  ", new Object[]{ refAffectationList.size()});
				for (RefAffectDocument currentRefAffectation : refAffectationList) {
					RefAffectDocumentDto refAffectationDto = new RefAffectDocumentDto();
					mapper.map(currentRefAffectation, refAffectationDto);
					refAffectationListDto.add(refAffectationDto);
				}

			}
		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}
		return refAffectationListDto;
	}
}
