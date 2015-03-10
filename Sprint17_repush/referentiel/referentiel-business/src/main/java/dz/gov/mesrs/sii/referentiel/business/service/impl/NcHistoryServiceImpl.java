package dz.gov.mesrs.sii.referentiel.business.service.impl;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dozer.Mapper;

import dz.gov.mesrs.sii.commons.data.dao.referentiel.NcHistoryDao;
import dz.gov.mesrs.sii.commons.data.model.referentiel.NcHistory;
import dz.gov.mesrs.sii.commons.data.model.referentiel.NcNames;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NcHistoryDto;
import dz.gov.mesrs.sii.referentiel.business.service.NcHistoryService;
/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 

@Service("ncHistoryServiceImpl")
public class NcHistoryServiceImpl implements NcHistoryService  {

	@Autowired
	@Qualifier ("ncHistoryDaoImpl")
	private NcHistoryDao ncHistoryDao;
	private static final Log log = LogFactory.getLog(NcHistoryServiceImpl.class);
    @Autowired
	@Qualifier("mapper")
	private Mapper mapper;
	
	public NcHistoryServiceImpl(){

	}

	@Override
	public NcHistoryDto  insertOrUpdate(
			NcHistoryDto ncHistoryDto) {

		NcHistory ncHistory = mapper.map(ncHistoryDto,
				NcHistory.class);
		
		if (ncHistory.getId() == 0)
			ncHistoryDao.persist(ncHistory);
		else
			ncHistory = ncHistoryDao.merge(ncHistory);

		mapper.map(ncHistory, ncHistoryDto);
		
		return ncHistoryDto;
	}

	@Override
	public void remove(NcHistoryDto ncHistoryDto) {

		NcHistory ncHistory = mapper.map(ncHistoryDto,
				NcHistory.class);

		ncHistoryDao.remove(ncHistory);
	}

   @Override
	public List<NcHistoryDto> findByQuery(String query) {
	
	    List<NcHistory> ncHistorys = ncHistoryDao.findByQuery(query);

		List<NcHistoryDto> ncHistoryDtos = new ArrayList<NcHistoryDto>();

		for (NcHistory ncHistory : ncHistorys) {
			ncHistoryDtos.add(mapper.map(ncHistory, NcHistoryDto.class));
		}

		return ncHistoryDtos;
	
	}
	
	@Override
	public NcHistoryDto findById(int id) {

		NcHistory ncHistory = ncHistoryDao.findById(id);

		if (ncHistory != null)
			return mapper.map(ncHistory, NcHistoryDto.class);

		return null;
	}

	@Override
	public List<NcHistoryDto> findAll() {		
	
		List<NcHistory> ncHistorys = ncHistoryDao.findAll();

		List<NcHistoryDto> ncHistoryDtos = new ArrayList<NcHistoryDto>();

		for (NcHistory ncHistory : ncHistorys) {
			ncHistoryDtos.add(mapper.map(ncHistory, NcHistoryDto.class));
		}

		return ncHistoryDtos;		
		
	}


	/* (non-Javadoc)
	 * @see dz.gov.mesrs.sii.referentiel.business.service.NcHistoryService#save(dz.gov.mesrs.sii.referentiel.business.model.dto.NcHistoryDto)
	 */
	@Override
	public void save(NcHistoryDto ncHistoryDto) {
		NcHistory ncHistory = new NcHistory();
		mapper.map(ncHistoryDto, ncHistory);
		try {
			ncHistoryDao.persist(ncHistory);
			log.debug("save ncHistory successful");
		} catch (RuntimeException e) {
			log.error("save ncHistory failed", e);
			throw e;
		}
		
	}

    /* (non-Javadoc)
	 * @see dz.gov.mesrs.sii.referentiel.business.service.NcHistoryService#update(dz.gov.mesrs.sii.referentiel.business.model.dto.NcHistoryDto)
	 */
	@Override
	public void update(NcHistoryDto ncHistoryDto) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see dz.gov.mesrs.sii.referentiel.business.service.NcHistoryService#findByIdRef()
	 */
	@Override
	public List<NcHistoryDto> findByIdRef(int id) {
		List<NcHistoryDto> ncHistoryDtos = null;
		try {
		log.info("findByIdRef id = "+id);	
		List<NcHistory> ncHistorys = ncHistoryDao.findByIdRef(id);
		ncHistoryDtos = new ArrayList<NcHistoryDto>();

		for (NcHistory ncHistory : ncHistorys) {
			ncHistoryDtos.add(mapper.map(ncHistory, NcHistoryDto.class));
		}
		}
		catch(RuntimeException e) {
			log.error("findByIdRef failed", e);
			throw e;
		}
		return ncHistoryDtos;	
	}
}



