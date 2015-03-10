package dz.gov.mesrs.sii.referentiel.business.service;

import java.util.List;

import dz.gov.mesrs.sii.referentiel.business.model.dto.NcHistoryDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NcNamesDto;



/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 
 
public interface  NcHistoryService {

	public  NcHistoryDto insertOrUpdate( NcHistoryDto ncHistoryDto);
	
	public  void remove( NcHistoryDto ncHistoryDto);	
	
	public  NcHistoryDto findById(int id);
	
	public  List<NcHistoryDto> findByQuery(String query) ;
	
	public  List<NcHistoryDto> findAll() ;	
	
    public void save(NcHistoryDto ncHistoryDto);
	
	public void update(NcHistoryDto ncHistoryDto);
	
	public  List<NcHistoryDto> findByIdRef(int id) ;


}