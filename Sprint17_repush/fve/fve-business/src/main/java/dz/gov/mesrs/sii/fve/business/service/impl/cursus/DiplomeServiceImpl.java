package dz.gov.mesrs.sii.fve.business.service.impl.cursus;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.fve.cursus.DiplomeDao;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.Diplome;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DiplomeDto;
import dz.gov.mesrs.sii.fve.business.service.cursus.DiplomeService;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 

@Service("diplomeService")
public class DiplomeServiceImpl implements DiplomeService  {

	@Autowired
	@Qualifier ("diplomeDao")
	private DiplomeDao diplomeDao;
	

	private static final Log log = LogFactory
			.getLog(DiplomeServiceImpl.class);


    @Autowired
	@Qualifier("mapper")
	private DozerBeanMapper mapper;
	
	
	
	public DiplomeServiceImpl(){

	}

 

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public DiplomeDto  insertOrUpdate(
			DiplomeDto diplomeDto) {

		try{
			
		 Diplome diplome = mapper.map(diplomeDto,
				Diplome.class);
		
		  if (diplome.getId() == 0){
			diplomeDao.persist(diplome);
		  }
		  else{
		 	diplome = diplomeDao.merge(diplome);
		  }

		mapper.map(diplome, diplomeDto);
		log.error("insertorupdate DiplomeDto succes..");
		
		return diplomeDto;
		
	   } catch (RuntimeException e) {
		log.error("insertorupdate DiplomeDto failed--", e);
	 	throw e;
	  }
	}
	

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void remove(DiplomeDto diplomeDto) {

		Diplome diplome = mapper.map(diplomeDto, Diplome.class);
		
		diplome = diplomeDao.merge(diplome);

		diplomeDao.remove(diplome);
	}



	@Override
	public DiplomeDto findById(int id) {

		Diplome diplome = diplomeDao.findById(id);

		if (diplome != null)
			return mapper.map(diplome, DiplomeDto.class);

		return null;
	}

	
	
	@Override
	public List<DiplomeDto> findAll() {		
	
		List<Diplome> diplomes = diplomeDao.findAll();

		List<DiplomeDto> diplomeDtos = new ArrayList<DiplomeDto>();

		for (Diplome diplome : diplomes) {
			diplomeDtos.add(mapper.map(diplome, DiplomeDto.class));
		}

		return diplomeDtos;		
		
	}
	
	
	@Override
	public List<DiplomeDto> findDiplomesByIdDossier(int idDossier) {
		List<Diplome> diplomes = diplomeDao.findDiplomesByIdDossier(idDossier);

		
		if(diplomes==null || diplomes.isEmpty()) return null;
		
		List<DiplomeDto> diplomeDtos = new ArrayList<DiplomeDto>();

		for (Diplome diplome : diplomes) {
			diplomeDtos.add(mapper.map(diplome, DiplomeDto.class));
		}

		return diplomeDtos;		
	}
}



