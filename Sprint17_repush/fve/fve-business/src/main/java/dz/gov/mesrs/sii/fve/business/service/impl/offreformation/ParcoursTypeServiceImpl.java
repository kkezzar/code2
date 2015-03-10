package dz.gov.mesrs.sii.fve.business.service.impl.offreformation;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.fve.offreformation.ParcoursTypeDao;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.ParcoursType;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.ParcoursTypeDto;
import dz.gov.mesrs.sii.fve.business.service.offreformation.ParcoursTypeService;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 

@Service("parcoursTypeService")
public class ParcoursTypeServiceImpl implements ParcoursTypeService  {

	@Autowired
	@Qualifier ("parcoursTypeDao")
	private ParcoursTypeDao parcoursTypeDao;

    @Autowired
	@Qualifier("mapper")
	private DozerBeanMapper mapper;
	
	/**
	 * [ParcoursTypeServiceImpl.ParcoursTypeServiceImpl()] Constructor
	 * @author  Rafik LAIB  on : 5 avr. 2014  21:59:51	
	 */
	public ParcoursTypeServiceImpl(){

	}

	/**
	 * [ParcoursTypeServiceImpl.insertOrUpdate] Method 
	 * overridden By :  @author  Rafik LAIB  
	 * On : 5 avr. 2014  21:59:46
	 * @param parcoursTypeDto
	 * @return
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public ParcoursTypeDto  insertOrUpdate(
			ParcoursTypeDto parcoursTypeDto) {

		ParcoursType parcoursType = mapper.map(parcoursTypeDto,
				ParcoursType.class);
		
		if (parcoursType.getId() == 0)
			parcoursTypeDao.persist(parcoursType);
		else
			parcoursType = parcoursTypeDao.merge(parcoursType);

		mapper.map(parcoursType, parcoursTypeDto);
		
		return parcoursTypeDto;
	}

	/**
	 * [ParcoursTypeServiceImpl.remove] Method 
	 * overridden By :  @author  Rafik LAIB  
	 * On : 5 avr. 2014  21:59:37
	 * @param parcoursTypeDto
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void remove(ParcoursTypeDto parcoursTypeDto) {

		ParcoursType parcoursType = mapper.map(parcoursTypeDto,
				ParcoursType.class);

		parcoursTypeDao.remove(parcoursType);
	}

	/**
	 * [ParcoursTypeServiceImpl.findByQuery] Method 
	 * overridden By :  @author  Rafik LAIB  
	 * On : 5 avr. 2014  21:59:08
	 * @param query
	 * @return
	 */
	@Override
	public List<ParcoursTypeDto> findByQuery(String query) {
	
	    List<ParcoursType> parcoursTypes = parcoursTypeDao.findByQuery(query);

		List<ParcoursTypeDto> parcoursTypeDtos = new ArrayList<ParcoursTypeDto>();

		for (ParcoursType parcoursType : parcoursTypes) {
			parcoursTypeDtos.add(mapper.map(parcoursType, ParcoursTypeDto.class));
		}

		return parcoursTypeDtos;
	
	}

	/**
	 * [ParcoursTypeServiceImpl.findById] Method 
	 * overridden By :  @author  Rafik LAIB  
	 * On : 5 avr. 2014  21:59:02
	 * @param id
	 * @return
	 */
	@Override
	public ParcoursTypeDto findById(int id) {

		ParcoursType parcoursType = parcoursTypeDao.findById(id);

		if (parcoursType != null)
			return mapper.map(parcoursType, ParcoursTypeDto.class);

		return null;
	}
	
	/**
	 * [ParcoursTypeServiceImpl.findAll] Method 
	 * overridden By :  @author  Rafik LAIB  
	 * On : 5 avr. 2014  21:58:56
	 * @return
	 */
	@Override
	public List<ParcoursTypeDto> findAll() {		
	
		List<ParcoursType> parcoursTypes = parcoursTypeDao.findAll();

		List<ParcoursTypeDto> parcoursTypeDtos = new ArrayList<ParcoursTypeDto>();

		for (ParcoursType parcoursType : parcoursTypes) {
			parcoursTypeDtos.add(mapper.map(parcoursType, ParcoursTypeDto.class));
		}

		return parcoursTypeDtos;		
		
	}
	/**
	 * [ParcoursTypeServiceImpl.findByOfId] Method 
	 * @author rlaib  on : 12 sept. 2014  16:11:54
	 * @param ofId
	 * @return
	 */
	@Override
	public  List<ParcoursTypeDto> findByOfId(int ofId) {
		
		List<ParcoursType> parcoursTypes = parcoursTypeDao.findByOfId(ofId);

		List<ParcoursTypeDto> parcoursTypeDtos = new ArrayList<ParcoursTypeDto>();

		for (ParcoursType parcoursType : parcoursTypes) {
			parcoursTypeDtos.add(mapper.map(parcoursType, ParcoursTypeDto.class));
		}

		return parcoursTypeDtos;		
	}
}



