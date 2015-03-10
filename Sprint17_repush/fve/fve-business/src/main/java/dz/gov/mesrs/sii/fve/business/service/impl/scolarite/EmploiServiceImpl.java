package dz.gov.mesrs.sii.fve.business.service.impl.scolarite;

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

import dz.gov.mesrs.sii.commons.data.dao.fve.scolarite.EmploiDao;
import dz.gov.mesrs.sii.commons.data.model.fve.scolarite.Emploi;
import dz.gov.mesrs.sii.fve.business.model.dto.scolarite.EmploiDto;
import dz.gov.mesrs.sii.fve.business.service.scolarite.EmploiService;



/**
 * 
 * @author BELDI Jamel  on : 22 oct. 2014  12:30:31
 */
 

@Service("emploiService")
public class EmploiServiceImpl implements EmploiService  {

	@Autowired
	@Qualifier ("emploiDao")
	private EmploiDao emploiDao;

	private static final Log log = LogFactory.getLog(EmploiServiceImpl.class);
	
    @Autowired
	@Qualifier("mapper")
	private DozerBeanMapper mapper;
	
	
	
	public EmploiServiceImpl(){

	}


	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public EmploiDto  insertOrUpdate(
			EmploiDto emploiDto) {
		try {
		Emploi emploi = mapper.map(emploiDto,
				Emploi.class);
		
			if (emploi.getId() == 0){
				emploiDao.persist(emploi);
			}else{
				emploi = emploiDao.merge(emploi);
			}
			mapper.map(emploi, emploiDto);
			
			log.error("insertorupdate Emploi succes..");
			
			return emploiDto;
		
		} catch (RuntimeException e) {
			log.error("insertorupdate Emploi failed--", e);
			throw e;
		}
	}
	

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void remove(EmploiDto emploiDto) {
		try {

		Emploi emploi = mapper.map(emploiDto,
				Emploi.class);
		
		emploi = emploiDao.merge(emploi);

		emploiDao.remove(emploi);
		} catch (RuntimeException e) {
			throw e;
		}
	}


	@Override
	public EmploiDto findById(int id) {
		try {
		Emploi emploi = emploiDao.findById(id);

		if (emploi != null)
			return mapper.map(emploi, EmploiDto.class);

		return null;
		} catch (RuntimeException e) {
			log.error("insertorupdate Emploi failed--", e);
			throw e;
		}
	}

	
	
	@Override
	public List<EmploiDto> findAll() {		
		try {
		List<Emploi> emplois = emploiDao.findAll();

		List<EmploiDto> emploiDtos = new ArrayList<EmploiDto>();

		for (Emploi emploi : emplois) {
			emploiDtos.add(mapper.map(emploi, EmploiDto.class));
		}

		return emploiDtos;	
	} catch (RuntimeException e) {
		log.error("insertorupdate Emploi failed--", e);
		throw e;
	}
		
	}


	@Override
	public List<EmploiDto> findAdvanced(EmploiDto emploiSearchDto) {
		try {
			List<EmploiDto> emploiDtos = new ArrayList<EmploiDto>();
			Emploi emploiSearch = new Emploi();
			mapper.map(emploiSearchDto, emploiSearch);
			List<Emploi> emplois = emploiDao.findAdvanced(emploiSearch);
			if(emplois==null || emplois.isEmpty()){
				return emploiDtos;
			}
			for (Emploi emploi : emplois) {
				emploiDtos.add(mapper.map(emploi, EmploiDto.class));
			}

			return emploiDtos;	
		} catch (RuntimeException e) {
			log.error("insertorupdate Emploi failed--", e);
			throw e;
		}
	}
}



