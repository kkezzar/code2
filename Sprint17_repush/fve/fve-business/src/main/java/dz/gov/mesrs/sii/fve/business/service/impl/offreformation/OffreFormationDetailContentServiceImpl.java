/**
 * [dz.gov.mesrs.sii.fve.business.service.impl.offreformation.OffreFormationDetailContentServiceImpl.java] 
 * @author rlaib on : 6 f�vr. 2014  15:00:56
 */
package dz.gov.mesrs.sii.fve.business.service.impl.offreformation;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.fve.offreformation.OffreFormationDetailContentDao;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.OffreFormationDetailContent;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OffreFormationDetailContentDto;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OffreFormationDetailContentService;

/**
 * @author rlaib  on : 6 fevr. 2014  15:00:56
 */
@Service("offreFormationDetailContentService")

public class OffreFormationDetailContentServiceImpl implements
		OffreFormationDetailContentService {
	
	@Autowired
	@Qualifier ("offreFormationDetailContentDao")
	private OffreFormationDetailContentDao offreFormationDetailContentDao;

    @Autowired
	@Qualifier("mapper")
	private DozerBeanMapper mapper;
	
	/**
	 * [OffreFormationDetailContentServiceImpl.OffreFormationDetailContentServiceImpl()] Constructor
	 * @author  Rafik LAIB  on : 5 avr. 2014  21:54:24	
	 */
	public OffreFormationDetailContentServiceImpl(){

	}
   
	/**
	 * [OffreFormationDetailContentServiceImpl.insertOrUpdate] Method 
	 * overridden By :  @author  Rafik LAIB  
	 * On : 5 avr. 2014  21:54:18
	 * @param offreFormationDetailContentDto
	 * @return
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public OffreFormationDetailContentDto  insertOrUpdate(OffreFormationDetailContentDto offreFormationDetailContentDto) {

		OffreFormationDetailContent offreFormationDetailContent = mapper.map(offreFormationDetailContentDto, OffreFormationDetailContent.class);
		if (offreFormationDetailContent.getId() == 0) 
			offreFormationDetailContentDao.persist(offreFormationDetailContent);
		else {
			offreFormationDetailContent.getOffreFormation().setDateModification(new java.util.Date());
			offreFormationDetailContent = offreFormationDetailContentDao.merge(offreFormationDetailContent);
		}

		mapper.map(offreFormationDetailContent, offreFormationDetailContentDto);
		
		return offreFormationDetailContentDto;
	}

	/**
	 * [OffreFormationDetailContentServiceImpl.remove] Method 
	 * overridden By :  @author  Rafik LAIB  
	 * On : 5 avr. 2014  21:54:03
	 * @param offreFormationDetailContentDto
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void remove(OffreFormationDetailContentDto offreFormationDetailContentDto) {

		OffreFormationDetailContent offreFormationDetailContent = mapper.map(offreFormationDetailContentDto, OffreFormationDetailContent.class);

		offreFormationDetailContentDao.remove(offreFormationDetailContent);
		
	}

	/**
	 * [OffreFormationDetailContentServiceImpl.findById] Method 
	 * overridden By :  @author  Rafik LAIB  
	 * On : 5 avr. 2014  21:53:57
	 * @param id
	 * @return
	 */
	@Override
	public OffreFormationDetailContentDto findById(int id) {

	
			//return offreFormationDetailContentDao.findById(id);
			OffreFormationDetailContent offreFormationDetailContent = offreFormationDetailContentDao.findById(id);
			
			if (offreFormationDetailContent != null)
						return mapper.map(offreFormationDetailContent, OffreFormationDetailContentDto.class);
			
			return null;
	}
	
	/**
	 * [OffreFormationDetailContentServiceImpl.findAll] Method 
	 * overridden By :  @author  Rafik LAIB  
	 * On : 5 avr. 2014  21:53:49
	 * @return
	 */
	@Override
	public List<OffreFormationDetailContentDto> findAll() {		
	
		List<OffreFormationDetailContent> offresFormationDetailContent = offreFormationDetailContentDao.findAll();

		List<OffreFormationDetailContentDto> offresFormationDetailContentDtos = new ArrayList<OffreFormationDetailContentDto>();

		for (OffreFormationDetailContent offreFormationDetailContent : offresFormationDetailContent) {
			offresFormationDetailContentDtos.add(mapper.map(offreFormationDetailContent, OffreFormationDetailContentDto.class));
		}

		return offresFormationDetailContentDtos;		
		
	}
	
	/**
	 * [OffreFormationDetailContentServiceImpl.findOfContentTreeById] Method 
	 * overridden By :  @author  Rafik LAIB  
	 * On : 5 avr. 2014  21:53:44
	 * @param ofId
	 * @return
	 */
	@Override
	public List<OffreFormationDetailContentDto> findOfContentTreeById(int ofId) {		
		
		List<OffreFormationDetailContent> offresFormationDetailContent = offreFormationDetailContentDao.findOfContentTreeById(ofId);
		
		List<OffreFormationDetailContentDto> offresFormationDetailContentDtos = new ArrayList<OffreFormationDetailContentDto>();
		
		for (OffreFormationDetailContent offreFormationDetailContent : offresFormationDetailContent) {
			offresFormationDetailContentDtos.add(mapper.map(offreFormationDetailContent, OffreFormationDetailContentDto.class));
		}
		
		return offresFormationDetailContentDtos;		
		
	}
}
