/**
 * [dz.gov.mesrs.sii.fve.business.service.impl.habilitation.TacheActionServiceImpl.java] 
 * @author BELDI Jamel on : 30 avr. 2014  16:09:32
 */
package dz.gov.mesrs.sii.commons.business.service.impl.bpm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.ActionDto;
import dz.gov.mesrs.sii.commons.business.model.dto.bpm.ActionI18nDto;
import dz.gov.mesrs.sii.commons.business.model.dto.bpm.TacheActionDto;
import dz.gov.mesrs.sii.commons.business.model.dto.bpm.TacheDto;
import dz.gov.mesrs.sii.commons.business.service.bpm.ActionService;
import dz.gov.mesrs.sii.commons.business.service.bpm.TacheActionService;
import dz.gov.mesrs.sii.commons.business.service.bpm.TacheService;
import dz.gov.mesrs.sii.commons.data.dao.bpm.TacheActionDao;
import dz.gov.mesrs.sii.commons.data.model.bpm.TacheAction;

/**
 * @author BELDI Jamel  on : 30 avr. 2014  16:09:32
 */
@Service("tacheActionService")
public class TacheActionServiceImpl implements TacheActionService {
	private static final Log log = LogFactory
			.getLog(TacheActionServiceImpl.class);
	
	@Autowired
	@Qualifier ("tacheActionDao")
	private TacheActionDao tacheActionDao;
	
	@Autowired
	@Qualifier("mapper")
	private DozerBeanMapper mapper;
		
	@Autowired
	@Qualifier ("tacheService")
	private TacheService tacheService;
	
	@Autowired
	@Qualifier ("actionService")
	private ActionService actionService;
	
	
	/**
	 * [TacheActionServiceImpl.tacheActionDao] Getter 
	 * @author BELDI Jamel on : 30 avr. 2014  16:11:47
	 * @return the tacheActionDao
	 */
	public TacheActionDao getTacheActionDao() {
		return tacheActionDao;
	}

	/**
	 * [TacheActionServiceImpl.tacheActionDao] Setter 
	 * @author BELDI Jamel on : 30 avr. 2014  16:11:47
	 * @param tacheActionDao the tacheActionDao to set
	 */
	public void setTacheActionDao(TacheActionDao tacheActionDao) {
		this.tacheActionDao = tacheActionDao;
	}

	/**
	 * [TacheActionServiceImpl.mapper] Getter 
	 * @author BELDI Jamel on : 30 avr. 2014  16:11:47
	 * @return the mapper
	 */
	public DozerBeanMapper getMapper() {
		return mapper;
	}

	
	
	/**
	 * [TacheActionServiceImpl.mapper] Setter 
	 * @author BELDI Jamel on : 30 avr. 2014  16:11:47
	 * @param mapper the mapper to set
	 */
	public void setMapper(DozerBeanMapper mapper) {
		this.mapper = mapper;
	}

	
	
	/**
	 * [TacheActionServiceImpl.tacheService] Getter 
	 * @author BELDI Jamel on : 6 mai 2014  10:18:26
	 * @return the tacheService
	 */
	public TacheService getTacheService() {
		return tacheService;
	}

	/**
	 * [TacheActionServiceImpl.tacheService] Setter 
	 * @author BELDI Jamel on : 6 mai 2014  10:18:26
	 * @param tacheService the tacheService to set
	 */
	public void setTacheService(TacheService tacheService) {
		this.tacheService = tacheService;
	}

	/**
	 * [TacheActionServiceImpl.actionService] Getter 
	 * @author BELDI Jamel on : 6 mai 2014  10:18:26
	 * @return the actionService
	 */
	public ActionService getActionService() {
		return actionService;
	}

	/**
	 * [TacheActionServiceImpl.actionService] Setter 
	 * @author BELDI Jamel on : 6 mai 2014  10:18:26
	 * @param actionService the actionService to set
	 */
	public void setActionService(ActionService actionService) {
		this.actionService = actionService;
	}

	/**
	 * [TacheActionServiceImpl.TacheActionServiceImpl()] Constructor
	 * @author BELDI Jamel  on : 30 avr. 2014  16:09:33	
	 */
	public TacheActionServiceImpl() {
		
	}

	/**
	 * [TacheActionServiceImpl.insertOrUpdate()] save or update Action
	 * @author BELDI Jamel  on : 30 avr. 2014 16:11:47	
	 * @param tacheActionDto
	 * @return tacheActionDto
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public TacheActionDto insertOrUpdate(TacheActionDto tacheActionDto) {
		
	try{

		TacheAction tacheAction = new TacheAction();
		mapper.map(tacheActionDto, tacheAction);
		
		if (tacheAction.getId() == 0) {
			tacheActionDao.persist(tacheAction);
		}
		else{
			tacheAction = tacheActionDao.merge(tacheAction);
		}
		mapper.map(tacheAction, tacheActionDto);

		return tacheActionDto;
	}catch (RuntimeException e) {
		log.error("save failed", e);
		throw e;
	
	}
	}

	
	@Override
	public void remove(TacheActionDto tacheActionDto) {
		

	}

	
	@Override
	public TacheActionDto findById(int id) {
		
		return null;
	}

	
	@Override
	public List<TacheActionDto> findAll() {
		
		return null;
	}

	@Override
	public List<TacheActionDto> findByEntity(String entityCode, int entityInstanceId) {
		
		List<TacheActionDto> result = new ArrayList<TacheActionDto>();
		try{
		//Find List Tache orderBy Date Cloture of entityInstance
		List<TacheDto> tacheDtos = tacheService.findFinishTacheByEntity(entityCode, entityInstanceId);
		if(tacheDtos!=null && !tacheDtos.isEmpty()){
			for (TacheDto tacheDto : tacheDtos) {
			List<TacheAction> tacheActions = tacheActionDao.findTacheActionByTache(tacheDto.getId());
			if(tacheActions!=null && !tacheActions.isEmpty()){
				for (TacheAction tacheAction : tacheActions) {
					TacheActionDto tacheActionDto = new TacheActionDto();
					HashMap<String, ActionI18nDto> actionI18nDtos = actionService.findListActionI18nByAction(new ActionDto(tacheAction.getAction().getId()));
					tacheActionDto.setActionI18nDtos(actionI18nDtos);
					tacheActionDto.setTacheDto(tacheDto);
					mapper.map(tacheAction, tacheActionDto);
					result.add(tacheActionDto);
				}
			}
			}
		}
		return result;
		}catch (RuntimeException e) {
			log.error("findByEntity failed", e);
			throw e;
		
		}
	}

	
	
	
}
