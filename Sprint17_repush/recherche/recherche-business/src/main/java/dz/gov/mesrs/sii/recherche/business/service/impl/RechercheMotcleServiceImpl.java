/**
 * [dz.gov.mesrs.sii.recherche.business.service.impl.RechercheStructureServiceImpl.java] 
 * @author rlaib on : 14 d�c. 2014  17:35:31
 */
package dz.gov.mesrs.sii.recherche.business.service.impl;

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

import dz.gov.mesrs.sii.commons.data.dao.recherche.MotcleDao;
import dz.gov.mesrs.sii.commons.data.model.recherche.Motcle;
import dz.gov.mesrs.sii.recherche.business.model.dto.MotcleDto;
import dz.gov.mesrs.sii.recherche.business.service.RechercheMotcleService;

/**
 * @author sRamdane  on : 08 fev 2015   17:35:31
 */
@Service("rechercheMotcleService")
public class RechercheMotcleServiceImpl implements RechercheMotcleService {

	@Autowired
	@Qualifier ("motcleDao")
	private MotcleDao motcleDao;
	
	private static final Log log = LogFactory.getLog(RechercheMotcleServiceImpl.class);
	
    @Autowired
	@Qualifier("mapper")
	private DozerBeanMapper mapper;

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public MotcleDto saveMotcle(MotcleDto motcleDto) {
		Motcle motcle = mapper.map(motcleDto, Motcle.class);
		if(motcleDto.getId()==null)motcleDao.persist(motcle);
		else motcleDao.merge(motcle);
		
		return motcleDto;
	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void removeMotcle(Long motcleId) {		
		motcleDao.remove(motcleId);		
	}

	@Override
	public MotcleDto findById(Long motcleId) {
		Motcle mc =motcleDao.findById(motcleId);
		MotcleDto mcDto = new MotcleDto();
		 mapper.map( mc,mcDto);
		return mcDto;
	}

	@Override
	public List<MotcleDto> findListMotsClesByIdProjet(Long id) {
		List<Motcle> listMc=motcleDao.findListMotsClesByIdProjet( id);
		List<MotcleDto> listMcDto=new ArrayList<MotcleDto>();
		if(listMc!=null)if(!listMc.isEmpty()){
			for(Motcle mc:listMc){
				MotcleDto mcDto = new MotcleDto();
				mapper.map( mc,mcDto);	listMcDto.add(mcDto);
			}
		}
		return listMcDto;
	}
	
	/**
	 * [RechercheGroupeService.insertOrUpdate] Method 
	 * Overridden By : @author rlaib  on : 16 dec. 2014  14:39:38
	 * @param structureDto
	 * @return
	 */
	

	
}

