/**
 * [dz.gov.mesrs.sii.fve.business.service.impl.habilitation.ActionServiceImpl.java] 
 * @author BELDI Jamel on : 4 mai 2014  14:18:13
 */
package dz.gov.mesrs.sii.commons.business.service.impl.bpm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.ActionDto;
import dz.gov.mesrs.sii.commons.business.model.dto.bpm.ActionI18nDto;
import dz.gov.mesrs.sii.commons.business.model.dto.bpm.EtapeActionDto;
import dz.gov.mesrs.sii.commons.business.service.bpm.ActionService;
import dz.gov.mesrs.sii.commons.data.dao.bpm.ActionDao;
import dz.gov.mesrs.sii.commons.data.dao.bpm.ActionI18nDao;
import dz.gov.mesrs.sii.commons.data.dao.bpm.EtapeActionDao;
import dz.gov.mesrs.sii.commons.data.model.bpm.Action;
import dz.gov.mesrs.sii.commons.data.model.bpm.ActionI18n;
import dz.gov.mesrs.sii.commons.data.model.bpm.EtapeAction;

/**
 * @author BELDI Jamel  on : 4 mai 2014  14:18:13
 */
@Service("actionService")
public class ActionServiceImpl implements ActionService {
	private static final Log log = LogFactory
			.getLog(ActionServiceImpl.class);
	
	@Autowired
	@Qualifier ("actionDao")
	private ActionDao actionDao;
	@Autowired
	@Qualifier("mapper")
	private org.dozer.DozerBeanMapper mapper;
	@Autowired
	@Qualifier ("actionI18nDao")
	private ActionI18nDao actionI18nDao;

	@Autowired
	@Qualifier ("etapeActionDao")
	private EtapeActionDao etapeActionDao;

	
	
	
	
	/**
	 * [ActionServiceImpl.actionDao] Getter 
	 * @author BELDI Jamel on : 4 mai 2014  14:23:18
	 * @return the actionDao
	 */
	public ActionDao getActionDao() {
		return actionDao;
	}

	/**
	 * [ActionServiceImpl.actionDao] Setter 
	 * @author BELDI Jamel on : 4 mai 2014  14:23:18
	 * @param actionDao the actionDao to set
	 */
	public void setActionDao(ActionDao actionDao) {
		this.actionDao = actionDao;
	}

	/**
	 * [ActionServiceImpl.mapper] Getter 
	 * @author BELDI Jamel on : 4 mai 2014  14:23:18
	 * @return the mapper
	 */
	public org.dozer.DozerBeanMapper getMapper() {
		return mapper;
	}

	/**
	 * [ActionServiceImpl.mapper] Setter 
	 * @author BELDI Jamel on : 4 mai 2014  14:23:18
	 * @param mapper the mapper to set
	 */
	public void setMapper(org.dozer.DozerBeanMapper mapper) {
		this.mapper = mapper;
	}

	/**
	 * [ActionServiceImpl.actionI18nDao] Getter 
	 * @author BELDI Jamel on : 4 mai 2014  14:23:18
	 * @return the actionI18nDao
	 */
	public ActionI18nDao getActionI18nDao() {
		return actionI18nDao;
	}

	/**
	 * [ActionServiceImpl.actionI18nDao] Setter 
	 * @author BELDI Jamel on : 4 mai 2014  14:23:18
	 * @param actionI18nDao the actionI18nDao to set
	 */
	public void setActionI18nDao(ActionI18nDao actionI18nDao) {
		this.actionI18nDao = actionI18nDao;
	}

	/**
	 * [ActionServiceImpl.etapeActionDao] Getter 
	 * @author BELDI Jamel on : 4 mai 2014  14:23:18
	 * @return the etapeActionDao
	 */
	public EtapeActionDao getEtapeActionDao() {
		return etapeActionDao;
	}

	/**
	 * [ActionServiceImpl.etapeActionDao] Setter 
	 * @author BELDI Jamel on : 4 mai 2014  14:23:18
	 * @param etapeActionDao the etapeActionDao to set
	 */
	public void setEtapeActionDao(EtapeActionDao etapeActionDao) {
		this.etapeActionDao = etapeActionDao;
	}

	/**
	 * [ActionServiceImpl.ActionServiceImpl()] Constructor
	 * @author BELDI Jamel  on : 4 mai 2014  14:18:14	
	 */
	public ActionServiceImpl() {
		
	}

	/**
	 * [ActionServiceImpl.findById] Find Action by Id 
	 * @author BELDI Jamel on : 4 mai 2014  11:59:03
	 * @param id
	 * @return ActionDto
	 */
	public ActionDto findById(int id) {
		ActionDto actionDto = null;
		try {
			Action action = actionDao
					.findById(id);
			if (action != null) {
				actionDto = new ActionDto();
				log.debug("actionDto found successfully with id = "
						+ id);
				mapper.map(action, actionDto);

			}
		} catch (RuntimeException e) {
			log.error("get actionDto list failed", e);
			throw e;
		}
		return actionDto;
	}

	
	/**
	 * [ActionServiceImpl.findListActionI18nByAction] Find List ActionI18n by action 
	 * @author BELDI Jamel on : 4 mai 2014  11:59:03
	 * @param ActionDto
	 * @return HashMap<String, ActionI18nDto>
	 */
	public HashMap<String, ActionI18nDto> findListActionI18nByAction(
			ActionDto actionDto) {
		HashMap<String, ActionI18nDto> mapActionI18n = new HashMap<String, ActionI18nDto>();
		try {
			List<ActionI18n> actionI18nList = actionI18nDao.findListActionI18nByAction(actionDto.getId());
			
			if (actionI18nList != null) {				
				for (ActionI18n currentActionI18n : actionI18nList) {
					ActionI18nDto actionI18nDto = new ActionI18nDto();
					mapper.map(currentActionI18n, actionI18nDto);			
					mapActionI18n.put(actionI18nDto.getLangue(), actionI18nDto);
				}
				return mapActionI18n;
			}
		} catch (RuntimeException e) {
			log.error("findListActionI18nByAction  failed", e);
			throw e;
		}

		return null;
	}
	
	/**
	 * [ActionServiceImpl.findListActioni18nByEtape] Find List EtapeActionDto by etape 
	 * @author BELDI Jamel on : 4 mai 2014  11:59:03
	 * @param EtapeActionDto
	 * @return List<ActionI18nDto>
	 */
	public List<ActionI18nDto> findListActioni18nByEtape(
			EtapeActionDto etapeActionDto) {
		try {
		List<ActionI18nDto> result = new ArrayList<ActionI18nDto>();
		List<EtapeAction> etapeActions =  etapeActionDao.findByEtapeId(etapeActionDto.getEtapeId());
		if(etapeActions!=null && !etapeActions.isEmpty()){
			for (EtapeAction  etapeAction : etapeActions) {
				ActionDto actionDto = new ActionDto();
				actionDto.setId(etapeAction.getAction().getId());
				HashMap<String, ActionI18nDto> mapActionI18nDtos = findListActionI18nByAction(actionDto);
			   if(mapActionI18nDtos!=null){
				   for(Entry<String, ActionI18nDto> entry : mapActionI18nDtos.entrySet()) {
					   ActionI18nDto element = entry.getValue();
					   element.setLangue(entry.getKey());
					   result.add(element);
					}
			   }
			}
		}
		return result;
	
	} catch (RuntimeException e) {
		log.error("findListActioni18nByEtape failed", e);
		throw e;
	}

	
	}
	
	@Override
	 public  List<ActionI18nDto> findListActionI18nByCodeEtape(String codeEtape)  {
		 
		return null;
		 
	 }

	@Override
	public ActionDto findByCode(String code) {
		try {
			Action action = actionDao.findByCode(code);
			if(action!=null){
			ActionDto actionDto = new ActionDto();
			mapper.map(action, actionDto);
			return actionDto;
			}
			return null;
		
		} catch (RuntimeException e) {
			log.error("findByCode failed", e);
			throw e;
		}

	}

}
