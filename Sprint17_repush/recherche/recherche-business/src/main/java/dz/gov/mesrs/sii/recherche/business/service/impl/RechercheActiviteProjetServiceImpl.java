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

import dz.gov.mesrs.sii.commons.data.dao.recherche.ActiviteProjetDao;
import dz.gov.mesrs.sii.commons.data.model.recherche.ActiviteProjet;
import dz.gov.mesrs.sii.recherche.business.model.dto.ActiviteProjetDto;
import dz.gov.mesrs.sii.recherche.business.service.RechercheActiviteProjetService;

/**
 * @author sRamdane  on : 08 fev 2015   17:35:31
 */
@Service("activiteProjetService")
public class RechercheActiviteProjetServiceImpl implements RechercheActiviteProjetService {

	@Autowired
	@Qualifier ("actProjDao")
	private ActiviteProjetDao activiteProjetDao;
	
	private static final Log log = LogFactory.getLog(RechercheActiviteProjetServiceImpl.class);
	
    @Autowired
	@Qualifier("mapper")
	private DozerBeanMapper mapper;
   
    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public ActiviteProjetDto saveActivite(ActiviteProjetDto activiteProjet) {
		
		ActiviteProjet activiteProj = mapper.map(activiteProjet,ActiviteProjet.class);
		try {
					if (activiteProj.getId() == null){
						activiteProjetDao.persist(activiteProj);
					}else{						
						activiteProj=activiteProjetDao.merge(activiteProj);						
					}					
					mapper.map(activiteProj, activiteProjet);					
					return activiteProjet;
		
		} catch (RuntimeException re) {
					log.error("insertOrUpdate ActiviteProjetDto failed : ", re);
					throw re;
		}
		
	}

	@Override
	public List<ActiviteProjetDto> finListActiviteByProjet(Long projetid) {
		try {
			List<ActiviteProjet> activiteProjets=activiteProjetDao.finListActiviteByProjet(projetid);
			
			List<ActiviteProjetDto> activiteProjetDtos = new ArrayList<ActiviteProjetDto>();
			if(activiteProjets!=null)if(!activiteProjets.isEmpty()){
			for (ActiviteProjet activiteProjet : activiteProjets) {
				ActiviteProjetDto activiteProjetDto = new ActiviteProjetDto();
				mapper.map(activiteProjet, activiteProjetDto);
				activiteProjetDtos.add(activiteProjetDto);
			}}
			return activiteProjetDtos;
		} catch (RuntimeException re) {
			log.error("findAll activiteProjetDto", re);
			throw re;
		}
	
	
	
	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.SUPPORTS)
	public void removeActiviteProjet(Long activiteProjetId) {	
		try {	if (activiteProjetId != null)
						activiteProjetDao.remove(activiteProjetId);
					
		} catch (RuntimeException re) {
					log.error("insertOrUpdate ActiviteProjetDto failed : ", re);
					throw re;
		}
	}

	@Override
	public ActiviteProjetDto findById(Long activiteId) {
		ActiviteProjetDto activiteProjet= new ActiviteProjetDto() ;
		ActiviteProjet activiteProj =activiteProjetDao.findById(activiteId);
		if (activiteProj != null) mapper.map(activiteProj, activiteProjet);
		return activiteProjet;
	}
	
	/**
	 * [RechercheGroupeService.insertOrUpdate] Method 
	 * Overridden By : @author rlaib  on : 16 dec. 2014  14:39:38
	 * @param structureDto
	 * @return
	 */
	

	
}
