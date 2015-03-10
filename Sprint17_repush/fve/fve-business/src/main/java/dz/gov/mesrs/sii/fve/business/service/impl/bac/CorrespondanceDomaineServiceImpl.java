/**
 * [dz.gov.mesrs.sii.fve.business.service.impl.bac.CorrespondanceDomaineServiceImpl.java] 
 * @author rlaib on : 18 juin 2014  15:01:27
 */
package dz.gov.mesrs.sii.fve.business.service.impl.bac;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.commons.data.dao.fve.bac.CorrespondanceDomaineDao;
import dz.gov.mesrs.sii.commons.data.model.fve.bac.CorrespondanceDomaine;
import dz.gov.mesrs.sii.fve.business.model.dto.bac.CorrespondanceDomaineDto;
import dz.gov.mesrs.sii.fve.business.service.bac.CorrespondanceDomaineService;

/**
 * @author rlaib  on : 18 juin 2014  15:01:27
 */
@Service("correspondanceDomaineService")
public class CorrespondanceDomaineServiceImpl implements
		CorrespondanceDomaineService {

	private static final Log log = LogFactory.getLog(CorrespondanceDomaineServiceImpl.class);
	
	@Autowired
	@Qualifier ("correspondanceDomaineDao")
	private CorrespondanceDomaineDao correspondanceDomaineDao;
	
	@Autowired
	@Qualifier("mapper")
	private DozerBeanMapper mapper;
	
	/**
	 * [CorrespondanceDomaineServiceImpl.getTableCorrespondanceDomaines] Method 
	 * @author rlaib  on : 18 juin 2014  15:01:27
	 * @return
	 */
	@Override
	public List<CorrespondanceDomaineDto> getTableCorrespondanceDomaines() {

		try {
						List<CorrespondanceDomaine> correspondancesDomaine = correspondanceDomaineDao.findAll();
						List<CorrespondanceDomaineDto> correspondanceDomaineDtos = new ArrayList<CorrespondanceDomaineDto>();
						for (CorrespondanceDomaine correspondanceDomaine : correspondancesDomaine) {
						
								CorrespondanceDomaineDto correspondanceDomaineDto  = new CorrespondanceDomaineDto();
								mapper.map(correspondanceDomaine, correspondanceDomaineDto);
								correspondanceDomaineDtos.add(correspondanceDomaineDto);
						}
						return correspondanceDomaineDtos;		
		}
		catch (RuntimeException e) {
		
					log.error("getTableCorrespondanceDomaines failed", e);
					throw e;
		}
	}
	
	@Override
	public  List<CorrespondanceDomaineDto> getFilieres() {
		
			try {
					List<CorrespondanceDomaine> correspondancesDomaine = correspondanceDomaineDao.getFilieres();
					List<CorrespondanceDomaineDto> correspondanceDomaineDtos = new ArrayList<CorrespondanceDomaineDto>();
					for (CorrespondanceDomaine correspondanceDomaine : correspondancesDomaine) {
					
							CorrespondanceDomaineDto correspondanceDomaineDto  = new CorrespondanceDomaineDto();
							mapper.map(correspondanceDomaine, correspondanceDomaineDto);
							correspondanceDomaineDtos.add(correspondanceDomaineDto);
					}
					return correspondanceDomaineDtos;		
			}
			catch (RuntimeException e) {
		
				log.error("getFilieres failed", e);
				throw e;
			}
	}

	@Override
	public  List<CorrespondanceDomaineDto> getFilieresByEtab(String codeEtab, String year) {
		
			try {
					List<CorrespondanceDomaine> correspondancesDomaine = correspondanceDomaineDao.getFilieresByEtab(codeEtab,year);
					List<CorrespondanceDomaineDto> correspondanceDomaineDtos = new ArrayList<CorrespondanceDomaineDto>();
					for (CorrespondanceDomaine correspondanceDomaine : correspondancesDomaine) {
					
							CorrespondanceDomaineDto correspondanceDomaineDto  = new CorrespondanceDomaineDto();
							mapper.map(correspondanceDomaine, correspondanceDomaineDto);
							correspondanceDomaineDtos.add(correspondanceDomaineDto);
					}
					return correspondanceDomaineDtos;		
			}
			catch (RuntimeException e) {
		
				log.error("getFilieres failed", e);
				throw e;
			}
	}

}
