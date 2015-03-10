package dz.gov.mesrs.sii.referentiel.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefVersionDocumentDao;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefVersionDocument;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefVersionDocumentDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefVersionDocumentService;

/**
 * 
 * @author yselmane  on : 9 avr. 2014  10:33:17
 */
@Service("refVersionDocumentServiceImpl")
public class RefVersionDocumentServiceImpl implements RefVersionDocumentService {

	@Autowired
	@Qualifier("refVersionDocumentDao")
	private RefVersionDocumentDao refVersionDocumentDao;

	@Autowired
	@Qualifier("mapper")
	private Mapper mapper;

	public RefVersionDocumentServiceImpl() {

	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void save(RefVersionDocumentDto refVersionDocumentDto) {
		RefVersionDocument refVersionDocument = new RefVersionDocument();
		try {
			mapper.map(refVersionDocumentDto, refVersionDocument);

			refVersionDocumentDao.persist(refVersionDocument);

		} catch (RuntimeException e) {
			throw e;
		}

	}

	@Override
	public RefVersionDocumentDto insertOrUpdate(
			RefVersionDocumentDto refVersionDocumentDto) {

		RefVersionDocument refVersionDocument = mapper.map(
				refVersionDocumentDto, RefVersionDocument.class);

		if (refVersionDocument.getId() == 0)
			refVersionDocumentDao.persist(refVersionDocument);
		else
			refVersionDocument = refVersionDocumentDao
					.merge(refVersionDocument);

		mapper.map(refVersionDocument, refVersionDocumentDto);

		return refVersionDocumentDto;
	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void remove(RefVersionDocumentDto refVersionDocumentDto) {

		RefVersionDocument refVersionDocument = new RefVersionDocument();

		try {
			mapper.map(refVersionDocumentDto, refVersionDocument);
			refVersionDocument = refVersionDocumentDao
					.merge(refVersionDocument);
			refVersionDocumentDao.remove(refVersionDocument);

		} catch (RuntimeException e) {
			throw e;
		}
	}

	@Override
	public List<RefVersionDocumentDto> findByQuery(String query) {

		List<RefVersionDocument> refVersionDocuments = refVersionDocumentDao
				.findByQuery(query);

		List<RefVersionDocumentDto> refVersionDocumentDtos = new ArrayList<RefVersionDocumentDto>();

		for (RefVersionDocument refVersionDocument : refVersionDocuments) {
			refVersionDocumentDtos.add(mapper.map(refVersionDocument,
					RefVersionDocumentDto.class));
		}

		return refVersionDocumentDtos;

	}

	@Override
	public RefVersionDocumentDto findById(int id) {

		RefVersionDocument refVersionDocument = refVersionDocumentDao
				.findById(id);

		if (refVersionDocument != null)
			return mapper.map(refVersionDocument, RefVersionDocumentDto.class);

		return null;
	}

	@Override
	public List<RefVersionDocumentDto> findAll() {

		List<RefVersionDocument> refVersionsDocument = refVersionDocumentDao
				.findAll();

		List<RefVersionDocumentDto> refVersionsDocumentDtos = new ArrayList<RefVersionDocumentDto>();

		for (RefVersionDocument refVersionDocument : refVersionsDocument) {
			refVersionsDocumentDtos.add(mapper.map(refVersionDocument,
					RefVersionDocumentDto.class));
		}

		return refVersionsDocumentDtos;

	}

	
	@Override
	public List<RefVersionDocumentDto> findVersionsOfDocument(String idDocument) {

		List<RefVersionDocument> refVersionDocuments = refVersionDocumentDao
				.findVersionsOfDocument(idDocument);

		List<RefVersionDocumentDto> refVersionDocumentDtos = new ArrayList<RefVersionDocumentDto>();

		for (RefVersionDocument refVersionDocument : refVersionDocuments) {
			refVersionDocumentDtos.add(mapper.map(refVersionDocument,
					RefVersionDocumentDto.class));
		}

		return refVersionDocumentDtos;

	}
}
