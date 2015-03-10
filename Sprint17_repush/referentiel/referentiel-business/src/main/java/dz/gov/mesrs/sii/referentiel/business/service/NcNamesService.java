package dz.gov.mesrs.sii.referentiel.business.service;

import java.util.List;

import dz.gov.mesrs.sii.referentiel.business.model.dto.NcNamesDto;



/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 
 
public interface  NcNamesService {

	public  NcNamesDto insertOrUpdate( NcNamesDto ncListDto);
	
	public  void remove( NcNamesDto ncListDto);	
	
	public  NcNamesDto findById(int id);
	
	public  List<NcNamesDto> findByQuery(String query) ;
	
	public  List<NcNamesDto> findAll() ;	
	
	public  List<NcNamesDto> findGeneric(String value) ;
	
	public NcNamesDto save(NcNamesDto ncNamesDto);
	
	public void update( NcNamesDto ncNamesDto);
	
	public void saveOrUpdate(NcNamesDto ncNamesDto);
	
	public  NcNamesDto findByName(String ncName) ;
	
	public boolean hasReference(Integer id);
	
	public NcNamesDto findDefaultValue(String ncName);
	
	
}