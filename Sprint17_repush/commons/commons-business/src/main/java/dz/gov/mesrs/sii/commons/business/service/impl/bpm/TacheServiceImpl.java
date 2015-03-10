/**
 * [dz.gov.mesrs.sii.fve.business.service.impl.habilitation.TacheServiceImpl.java] 
 * @author rlaib on : 29 avr. 2014  16:36:28
 */
package dz.gov.mesrs.sii.commons.business.service.impl.bpm;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.EtapeDto;
import dz.gov.mesrs.sii.commons.business.model.dto.bpm.EtapeRoleDto;
import dz.gov.mesrs.sii.commons.business.model.dto.bpm.TacheDto;
import dz.gov.mesrs.sii.commons.business.service.bpm.EtapeRoleService;
import dz.gov.mesrs.sii.commons.business.service.bpm.TacheService;
import dz.gov.mesrs.sii.commons.data.dao.bpm.TacheDao;
import dz.gov.mesrs.sii.commons.data.model.bpm.Tache;

/**
 * @author rlaib  on : 29 avr. 2014  16:36:28
 */

@Service("tacheService")
public class TacheServiceImpl implements TacheService {

	
	@Autowired
	@Qualifier ("tacheDao")
	private TacheDao tacheDao;
	
	@Autowired
	@Qualifier("mapper")
	private DozerBeanMapper mapper;
	
	@Autowired
	@Qualifier ("etapeRoleService")
	private EtapeRoleService etapeRoleService;
	
	public TacheServiceImpl(){

	}
	
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public TacheDto  insertOrUpdate(TacheDto tacheDto) {

		Tache tache = mapper.map(tacheDto, Tache.class);
		
		if (tache.getId() == 0) 
			tacheDao.persist(tache);
		else
			tache = tacheDao.merge(tache);

		mapper.map(tache, tacheDto);

		return tacheDto;
	}
	
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void remove(TacheDto tacheDto) {

		Tache tache = mapper.map(tacheDto, Tache.class);
		tacheDao.remove(tache);

	}
	
	
	@Override
	public TacheDto findById(int id) {

		Tache tache = tacheDao.findById(id);

		if (tache != null)
			return mapper.map(tache, TacheDto.class);

		return null;
	}
	
	@Override
	public List<TacheDto> findAll() {		


		List<TacheDto> tacheDtos = new ArrayList<TacheDto>();

		try {
			List<Tache> taches = tacheDao.findAll();

			for (Tache tache : taches) {
				tacheDtos.add(mapper.map(tache, TacheDto.class));
			}
		}
		catch (Exception e){

		}

		return tacheDtos;

	}

	@Override
	public List<TacheDto> findCurrentTacheByEtape(EtapeDto etapeDto) {
		List<TacheDto> tacheDtos = new ArrayList<TacheDto>();
       
		try {
			if(etapeDto!=null){
			List<Tache> taches = tacheDao.findCurrentTacheByEtapeId(etapeDto.getId());

			for (Tache tache : taches) {
				TacheDto tacheDto =  new TacheDto();
				EtapeRoleDto etapeRoleDto=etapeRoleService.findById(tache.getEtapeRole().getId());
				mapper.map(etapeRoleDto, tacheDto);
				mapper.map(tache, tacheDto);
				
				tacheDtos.add(tacheDto);
			}
			}
		}
		catch (Exception e){

		}

		return tacheDtos;
	}

	/**
	 * [TacheServiceImpl.tacheDao] Getter 
	 * @author BELDI Jamel on : 5 mai 2014  14:10:08
	 * @return the tacheDao
	 */
	public TacheDao getTacheDao() {
		return tacheDao;
	}

	/**
	 * [TacheServiceImpl.tacheDao] Setter 
	 * @author BELDI Jamel on : 5 mai 2014  14:10:08
	 * @param tacheDao the tacheDao to set
	 */
	public void setTacheDao(TacheDao tacheDao) {
		this.tacheDao = tacheDao;
	}

	/**
	 * [TacheServiceImpl.mapper] Getter 
	 * @author BELDI Jamel on : 5 mai 2014  14:10:08
	 * @return the mapper
	 */
	public DozerBeanMapper getMapper() {
		return mapper;
	}

	/**
	 * [TacheServiceImpl.mapper] Setter 
	 * @author BELDI Jamel on : 5 mai 2014  14:10:08
	 * @param mapper the mapper to set
	 */
	public void setMapper(DozerBeanMapper mapper) {
		this.mapper = mapper;
	}

	/**
	 * [TacheServiceImpl.etapeRoleService] Getter 
	 * @author BELDI Jamel on : 5 mai 2014  14:10:08
	 * @return the etapeRoleService
	 */
	public EtapeRoleService getEtapeRoleService() {
		return etapeRoleService;
	}

	/**
	 * [TacheServiceImpl.etapeRoleService] Setter 
	 * @author BELDI Jamel on : 5 mai 2014  14:10:08
	 * @param etapeRoleService the etapeRoleService to set
	 */
	public void setEtapeRoleService(EtapeRoleService etapeRoleService) {
		this.etapeRoleService = etapeRoleService;
	}

	@Override
	public List<TacheDto> findFinishTacheByEntity(String entityCode,
			int entityInstanceId) {
		
		List<TacheDto> tacheDtos = new ArrayList<TacheDto>();
	       
		try {
			if(entityCode!=null && entityInstanceId!=0){
			List<Tache> taches = tacheDao.findFinishTacheByEntity(entityCode, entityInstanceId);

			for (Tache tache : taches) {
				TacheDto tacheDto =  new TacheDto();
				EtapeRoleDto etapeRoleDto=etapeRoleService.findById(tache.getEtapeRole().getId());
				mapper.map(etapeRoleDto, tacheDto);
				mapper.map(tache, tacheDto);
				tacheDto.setEtapeRoleDto(etapeRoleDto);
				
				tacheDtos.add(tacheDto);
			}
			}
		}
		catch (Exception e){

		}

		return tacheDtos;
	}
	
	
}
