/**
 * [dz.gov.mesrs.sii.commons.business.service.impl.bpm.ProcessusServiceImpl.java] 
 * @author rlaib on : 15 janv. 2015  14:04:29
 */
package dz.gov.mesrs.sii.commons.business.service.impl.bpm;

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

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.ActionEntiteDto;
import dz.gov.mesrs.sii.commons.business.model.dto.bpm.EntiteDto;
import dz.gov.mesrs.sii.commons.business.model.dto.bpm.EtapeDto;
import dz.gov.mesrs.sii.commons.business.model.dto.bpm.ProcessusDto;
import dz.gov.mesrs.sii.commons.business.service.bpm.ProcessusService;
import dz.gov.mesrs.sii.commons.data.dao.bpm.ActionDao;
import dz.gov.mesrs.sii.commons.data.dao.bpm.ActionEntiteDao;
import dz.gov.mesrs.sii.commons.data.dao.bpm.EntiteDao;
import dz.gov.mesrs.sii.commons.data.dao.bpm.EtapeProcessusDao;
import dz.gov.mesrs.sii.commons.data.dao.bpm.ProcessusDao;
import dz.gov.mesrs.sii.commons.data.dao.bpm.SituationEntiteDao;
import dz.gov.mesrs.sii.commons.data.model.bpm.Action;
import dz.gov.mesrs.sii.commons.data.model.bpm.ActionEntite;
import dz.gov.mesrs.sii.commons.data.model.bpm.Entite;
import dz.gov.mesrs.sii.commons.data.model.bpm.Etape;
import dz.gov.mesrs.sii.commons.data.model.bpm.Processus;
import dz.gov.mesrs.sii.commons.data.model.bpm.SituationEntite;
import dz.gov.mesrs.sii.commons.data.util.UtilConstant;

/**
 * @author rlaib  on : 15 janv. 2015  14:04:29
 */
@Service("processusService")
public class ProcessusServiceImpl implements ProcessusService {

	private static final Log log = LogFactory	.getLog(EtapeServiceImpl.class);
	
	@Autowired
	@Qualifier ("processusDao")
	private ProcessusDao processusDao;

	@Autowired
	@Qualifier ("etapeProcessusDao")
	private EtapeProcessusDao etapeProcessusDao;

	@Autowired
	@Qualifier ("entiteDao")
	private EntiteDao entiteDao;
	
	@Autowired
	@Qualifier ("actionDao")
	private ActionDao actionDao;
	
	@Autowired
	@Qualifier ("actionEntiteDao")
	private ActionEntiteDao actionEntiteDao;
	
	@Autowired
	@Qualifier ("situationEntiteDao")
	private SituationEntiteDao situationEntiteDao;
	
	@Autowired
	@Qualifier("mapper")
	private DozerBeanMapper mapper;

	/**
	 * [ProcessusServiceImpl.insertOrUpdate] Method 

	 * Overridden By : @author rlaib  on : 15 janv. 2015  14:04:29
	 * @param processusDto
	 * @return
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public ProcessusDto insertOrUpdate(ProcessusDto processusDto) {

		Processus processus = mapper.map(processusDto, Processus.class);
		try {
					if (processus.getId() == 0){
						processusDao.persist(processus);
					}
					else{
						processus = processusDao.merge(processus);
					}
					mapper.map(processus, processusDto);
					return processusDto;
		
		} catch (RuntimeException re) {
					log.error("insertOrUpdate processusDto failed : ", re);
					throw re;
		}
	}

	/**
	 * [ProcessusServiceImpl.remove] Method 
	 * Overridden By : @author rlaib  on : 15 janv. 2015  14:04:29
	 * @param processusDto
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void remove(Integer id) {
		
		processusDao.remove(id);

	}

	/**
	 * [ProcessusServiceImpl.findById] Method 
	 * Overridden By : @author rlaib  on : 15 janv. 2015  14:04:29
	 * @param id
	 * @return
	 */
	@Override
	public ProcessusDto findById(int id) {
		
		try {
				Processus processus  = processusDao.findById(id);
				return mapper.map(processus, ProcessusDto.class);
		} catch (RuntimeException e) {
				log.error("findById ProcessusDto failed", e);
				throw e;
		}
	}

	/**
	 * [ProcessusServiceImpl.findByCodeEntite] Method 
	 * Overridden By : @author rlaib  on : 15 janv. 2015  14:04:29
	 * @param codeEntite
	 * @return
	 */
	@Override
	public List<ProcessusDto> findByCodeDomaine(String codeDomaine) {
		
		try {
			List<Processus> processusList= processusDao.findByCodeDomaine(codeDomaine);
			List<ProcessusDto> processusDtos = new ArrayList<ProcessusDto>();
			for (Processus processus : processusList) {
				ProcessusDto processusDto = new ProcessusDto();
				mapper.map(processus, processusDto);
				processusDtos.add(processusDto);
			}
			return processusDtos;
		} catch (RuntimeException re) {
			log.error("findAll ProcessusDto", re);
			throw re;
		}
	}

	/**
	 * [ProcessusServiceImpl.findAll] Method 
	 * Overridden By : @author rlaib  on : 15 janv. 2015  14:04:29
	 * @return
	 */
	@Override
	public List<ProcessusDto> findAll() {
	
		try {
			List<Processus> processusList= processusDao.findAll();
			List<ProcessusDto> processusDtos = new ArrayList<ProcessusDto>();
			for (Processus processus : processusList) {
				ProcessusDto processusDto = new ProcessusDto();
				mapper.map(processus, processusDto);
				processusDtos.add(processusDto);
			}
			return processusDtos;
		} catch (RuntimeException re) {
			log.error("findAll ProcessusDto", re);
			throw re;
		}
	}

	/**
	 * [ProcessusService.removeOneStep] Method 
	 * Overridden By : @author rlaib  on : 15 janv. 2015  15:35:27
	 * @param idStep
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void removeOneStep(Integer idStep) {
	
		try {
				Etape _currentStep = etapeProcessusDao.findById(idStep);
				if(_currentStep != null) {
					Etape _previous = _currentStep.getEtapePrecedente();
					Etape _next = _currentStep.getEtapeSuivante();
					if(_previous == null) {
						// this is the first step
						if(_next == null) {
							etapeProcessusDao.remove(idStep);
						}
						else {
							_next.setEtapePrecedente(null);
							etapeProcessusDao.merge(_next);
							_currentStep.setEtapeSuivante(null);
							etapeProcessusDao.remove(idStep);
						}
					}
					else {
						
						if(_next == null) {
							// this is the last step
							_previous.setEtapeSuivante(null);
							etapeProcessusDao.merge(_previous);
							_currentStep.setEtapePrecedente(null);
							etapeProcessusDao.remove(idStep);
						}
						else {
							// this is a medium step
							_previous.setEtapeSuivante(_next);
							_next.setEtapePrecedente(_previous);
							etapeProcessusDao.merge(_next);
							etapeProcessusDao.merge(_previous);
							_currentStep.setEtapeSuivante(null);
							_currentStep.setEtapePrecedente(null);
							etapeProcessusDao.remove(idStep);
						}
					}
					
				}
				
		} catch (RuntimeException re) {
				log.error("removeOneStep ProcessusDto", re);
				throw re;
		}
	}
	

	/**
	 * [ProcessusService.saveOneStep] Method 
	 * Overridden By : @author rlaib  on : 15 janv. 2015  15:35:27
	 * @param etapeDto
	 * @return
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public EtapeDto saveOneStepValidation(EtapeDto etapeDto) {
		
		Etape etape = mapper.map(etapeDto, Etape.class);
		try {
					if (etape.getId() == 0){
						ActionEntite action = actionEntiteDao.findByEntityCodeByActionCode(etapeDto.getProcessusEntiteCode(), UtilConstant.ACTION_VLDR);
						SituationEntite situationEntite = situationEntiteDao.findByCodeSituationByCodeEntite(etapeDto.getProcessusEntiteCode(), UtilConstant.SITUTAION_VALIDEE_CODE);
						etape.setActionEntite(action);
						etape.setSituationEntite(situationEntite);
						etapeProcessusDao.persist(etape);
					}
					else{
						etape = etapeProcessusDao.merge(etape);
					}
					if(etapeDto.getEtapePrecedenteId() != null) {
						Etape _etape = etapeProcessusDao.findById(etapeDto.getEtapePrecedenteId());
						_etape.setEtapeSuivante(etape);
						etapeProcessusDao.merge(_etape);
					}
					mapper.map(etape, etapeDto);
					return etapeDto;
		
		} catch (RuntimeException re) {
					log.error("saveOneStepValidation etapeDto failed : ", re);
					throw re;
		}
	}
	

	/**
	 * [ProcessusService.findStepsOfProcess] Method 
	 * Overridden By : @author rlaib  on : 15 janv. 2015  15:35:27
	 * @param idProcessus
	 * @return
	 */
	@Override
	public List<EtapeDto> findStepsOfProcess(Integer idProcessus) {
		
		try {
			List<Etape> etapes = etapeProcessusDao.findByIdProcessus(idProcessus);
			List<EtapeDto> etapeDtos = null;
			if(etapes != null && !etapes.isEmpty()) {
				etapeDtos = new ArrayList<EtapeDto>();
				for (Etape etape : etapes) {
					EtapeDto etapeDto = new EtapeDto();
					mapper.map(etape, etapeDto);
					etapeDtos.add(etapeDto);
				}
			}
			return etapeDtos;
		} catch (RuntimeException re) {
			log.error("findStepsOfProcess EtapeDto", re);
			throw re;
		}
	}

	
	/**
	 * [ProcessusService.findEntityByCode] Method 
	 * Overridden By : @author rlaib  on : 19 janv. 2015  10:23:41
	 * @param code
	 * @return
	 */
	@Override
	public EntiteDto findEntityByCode(String code) {
		
		try {
				Entite entite  = entiteDao.findByCode(code);
				return mapper.map(entite, EntiteDto.class);
		} catch (RuntimeException e) {
			log.error("findEntityByCode EntiteDto failed", e);
			throw e;
		}
	}

	/**
	 * [ProcessusService.findStepById] Method 
	 * Overridden By : @author rlaib  on : 20 janv. 2015  18:05:12
	 * @param id
	 * @return
	 */
	@Override
	public EtapeDto findStepById(Integer id) {
	
		try {
				Etape etape  = etapeProcessusDao.findById(id);
				return mapper.map(etape, EtapeDto.class);
		} catch (RuntimeException e) {
				log.error("findStepById EtapeDto failed", e);
				throw e;
		}
	}

	/**
	 * [ProcessusService.findEntityByCodeDomaine] Method 
	 * Overridden By : @author rlaib  on : 21 janv. 2015  16:24:11
	 * @param codeDomaine
	 * @return
	 */
	@Override
	public List<EntiteDto> findEntityByCodeDomaine(String codeDomaine) {
		
		try {
			List<Entite> entites = entiteDao.findByCodeDomaine(codeDomaine);
			List<EntiteDto> entiteDtos = null;
			if(entites != null && !entites.isEmpty()) {
				entiteDtos = new ArrayList<EntiteDto>();
				for (Entite entite : entites) {
					EntiteDto entiteDto = new EntiteDto();
					mapper.map(entite, entiteDto);
					entiteDtos.add(entiteDto);
				}
			}
			return entiteDtos;
		} catch (RuntimeException re) {
			log.error("findEntityByCodeDomaine EntiteDto", re);
			throw re;
		}
	}

	/**
	 * [ProcessusService.findByCodeEntite] Method 
	 * Overridden By : @author rlaib  on : 21 janv. 2015  16:42:35
	 * @param codeEntite
	 * @return
	 */
	@Override
	public List<ProcessusDto> findByCodeEntite(String codeEntite) {
		
		try {
			List<Processus> processusList= processusDao.findByCodeEntite(codeEntite);
			List<ProcessusDto> processusDtos = new ArrayList<ProcessusDto>();
			for (Processus processus : processusList) {
				ProcessusDto processusDto = new ProcessusDto();
				mapper.map(processus, processusDto);
				processusDtos.add(processusDto);
			}
			return processusDtos;
		} catch (RuntimeException re) {
			log.error("findAll ProcessusDto", re);
			throw re;
		}
	}

	/**
	 * [ProcessusService.findByKeyWords] Method 
	 * Overridden By : @author rlaib  on : 25 janv. 2015  15:30:59
	 * @param keyWord
	 * @return
	 */
	@Override
	public List<ProcessusDto> findByKeyWords(String keyWord) {
		try {
			List<Processus> processusList= processusDao.findByKeyWords(keyWord);
			List<ProcessusDto> processusDtos = new ArrayList<ProcessusDto>();
			for (Processus processus : processusList) {
				ProcessusDto processusDto = new ProcessusDto();
				mapper.map(processus, processusDto);
				processusDtos.add(processusDto);
			}
			return processusDtos;
		} catch (RuntimeException re) {
			log.error("findAll ProcessusDto", re);
			throw re;
		}
	
	}

	@Override
	public List<ActionEntiteDto> findActionsOfEntity(String entityCode) {
		try {
			List<ActionEntite> actionEntites= actionEntiteDao.findByEntityCode(entityCode);
			List<ActionEntiteDto> actionEntiteDtos = new ArrayList<ActionEntiteDto>();
			if(actionEntites != null) {
				for (ActionEntite actionEntite : actionEntites) {
					ActionEntiteDto actionEntiteDto = new ActionEntiteDto();
					mapper.map(actionEntite, actionEntiteDto);
					actionEntiteDtos.add(actionEntiteDto);
				}
			}
			
			return actionEntiteDtos;
		} catch (RuntimeException re) {
			log.error("findActionsOfEntity ActionEntiteDto", re);
			throw re;
		}
	}
}
