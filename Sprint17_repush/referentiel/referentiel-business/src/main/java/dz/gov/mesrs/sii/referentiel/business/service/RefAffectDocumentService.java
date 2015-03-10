package dz.gov.mesrs.sii.referentiel.business.service;

import java.util.List;

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefAffectDocumentDto;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 20-04-2014 16:44:07
 */

public interface RefAffectDocumentService {

	public RefAffectDocumentDto insertOrUpdate(
			RefAffectDocumentDto refAffectDocumentDto);

	public void remove(RefAffectDocumentDto refAffectDocumentDto);
	
	public void save(RefAffectDocumentDto refAffectDocumentDto) ;
	
	public void update(RefAffectDocumentDto refAffectDocumentDto) ;
	
	public RefAffectDocumentDto findById(int id);

	public List<RefAffectDocumentDto> findByQuery(String query);

	public List<RefAffectDocumentDto> findAll();

	public List<RefAffectDocumentDto> findStructuresOfDocument(String idEntity);

	public List<RefAffectDocumentDto> findGroupesOfDocument(String idEntity);

	public List<RefAffectDocumentDto> findIndividusOfDocument(String idEntity);

}