/**
 * [dz.gov.mesrs.sii.fve.business.service.impl.habilitation.EtapeRoleServiceImpl.java] 
 * @author BELDI Jamel on : 30 avr. 2014  16:09:32
 */
package dz.gov.mesrs.sii.commons.business.service.impl.bpm;

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

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.EtapeDto;
import dz.gov.mesrs.sii.commons.business.model.dto.bpm.EtapeI18nDto;
import dz.gov.mesrs.sii.commons.business.model.dto.bpm.EtapeRoleDto;
import dz.gov.mesrs.sii.commons.business.model.dto.bpm.RoleDto;
import dz.gov.mesrs.sii.commons.business.model.dto.bpm.RoleI18nDto;
import dz.gov.mesrs.sii.commons.business.service.bpm.EtapeRoleService;
import dz.gov.mesrs.sii.commons.business.service.bpm.EtapeService;
import dz.gov.mesrs.sii.commons.business.service.bpm.RoleService;
import dz.gov.mesrs.sii.commons.data.dao.bpm.EtapeRoleDao;
import dz.gov.mesrs.sii.commons.data.model.bpm.EtapeRole;


/**
 * @author BELDI Jamel  on : 5 mai 2014  12:53:09
 */
@Service("etapeRoleService")
public class EtapeRoleServiceImpl implements EtapeRoleService {
	private static final Log log = LogFactory
			.getLog(EtapeRoleServiceImpl.class);
	
	@Autowired
	@Qualifier ("etapeRoleDao")
	private EtapeRoleDao etapeRoleDao;
	
	@Autowired
	@Qualifier("mapper")
	private DozerBeanMapper mapper;
		
	@Autowired
	@Qualifier ("etapeService")
	private EtapeService etapeService;
	@Autowired
	@Qualifier ("roleService")
	private RoleService roleService;
	
	/**
	 * [EtapeRoleServiceImpl.etapeRoleDao] Getter 
	 * @author BELDI Jamel on : 30 avr. 2014  16:11:47
	 * @return the etapeRoleDao
	 */
	public EtapeRoleDao getEtapeRoleDao() {
		return etapeRoleDao;
	}

	/**
	 * [EtapeRoleServiceImpl.etapeRoleDao] Setter 
	 * @author BELDI Jamel on : 30 avr. 2014  16:11:47
	 * @param etapeRoleDao the etapeRoleDao to set
	 */
	public void setEtapeRoleDao(EtapeRoleDao etapeRoleDao) {
		this.etapeRoleDao = etapeRoleDao;
	}

	/**
	 * [EtapeRoleServiceImpl.mapper] Getter 
	 * @author BELDI Jamel on : 30 avr. 2014  16:11:47
	 * @return the mapper
	 */
	public DozerBeanMapper getMapper() {
		return mapper;
	}

	/**
	 * [EtapeRoleServiceImpl.mapper] Setter 
	 * @author BELDI Jamel on : 30 avr. 2014  16:11:47
	 * @param mapper the mapper to set
	 */
	public void setMapper(DozerBeanMapper mapper) {
		this.mapper = mapper;
	}

	
	
	/**
	 * [EtapeRoleServiceImpl.etapeService] Getter 
	 * @author BELDI Jamel on : 6 mai 2014  09:53:53
	 * @return the etapeService
	 */
	public EtapeService getEtapeService() {
		return etapeService;
	}

	/**
	 * [EtapeRoleServiceImpl.etapeService] Setter 
	 * @author BELDI Jamel on : 6 mai 2014  09:53:53
	 * @param etapeService the etapeService to set
	 */
	public void setEtapeService(EtapeService etapeService) {
		this.etapeService = etapeService;
	}

	/**
	 * [EtapeRoleServiceImpl.roleService] Getter 
	 * @author BELDI Jamel on : 6 mai 2014  09:53:53
	 * @return the roleService
	 */
	public RoleService getRoleService() {
		return roleService;
	}

	/**
	 * [EtapeRoleServiceImpl.roleService] Setter 
	 * @author BELDI Jamel on : 6 mai 2014  09:53:53
	 * @param roleService the roleService to set
	 */
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	/**
	 * [EtapeRoleServiceImpl.EtapeRoleServiceImpl()] Constructor
	 * @author BELDI Jamel  on : 30 avr. 2014  16:09:33	
	 */
	public EtapeRoleServiceImpl() {
		
	}

	/**
	 * [EtapeRoleServiceImpl.insertOrUpdate()] save or update EtapeRole
	 * @author BELDI Jamel  on : 30 avr. 2014 16:11:47	
	 * @param etapeRoleDto
	 * @return etapeRoleDto
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public EtapeRoleDto insertOrUpdate(EtapeRoleDto etapeRoleDto) {
		
	try{

		EtapeRole etapeRole = new EtapeRole();
		mapper.map(etapeRoleDto, etapeRole);
		
		if (etapeRole.getId() == 0) {
			etapeRoleDao.persist(etapeRole);
		}
		else{
			etapeRole = etapeRoleDao.merge(etapeRole);
		}
		mapper.map(etapeRole, etapeRoleDto);

		return etapeRoleDto;
	}catch (RuntimeException e) {
		log.error("save failed", e);
		throw e;
	
	}
	}

	
	@Override
	public void remove(EtapeRoleDto etapeRoleDto) {
		

	}

	
	/**
	 * [EtapeRoleServiceImpl.findById()] find EtapeRole DTO by Id
	 * @author BELDI Jamel  on : 30 avr. 2014 16:11:47	
	 * @param id
	 * @return etapeRoleDto
	 */
	public EtapeRoleDto findById(int id) {
		EtapeRoleDto etapeRoleDto = null;
		try {
			EtapeRole etapeRole = etapeRoleDao
					.findById(id);
			if (etapeRole != null) {
				etapeRoleDto = new EtapeRoleDto();
				log.debug("etapeRoleDto found successfully with id = "
						+ id);
				mapper.map(etapeRole, etapeRoleDto);
				HashMap<String, EtapeI18nDto> etapeI18nDtos = etapeService.findListEtapeI18nByEtape(new EtapeDto(etapeRoleDto.getEtapeId()));
				HashMap<String, RoleI18nDto> roleI18nDtos = roleService.findListRoleI18nByRole(new RoleDto(etapeRoleDto.getRoleId()));
				etapeRoleDto.setEtapeI18nDtos(etapeI18nDtos);
				etapeRoleDto.setRoleI18nDtos(roleI18nDtos);
			}
		} catch (RuntimeException e) {
			log.error("get etapeRoleDto list failed", e);
			throw e;
		}
		return etapeRoleDto;
	}

	
	@Override
	public List<EtapeRoleDto> findAll() {
		
		return null;
	}

	/**
	 * [EtapeRoleServiceImpl.findByCodeEtapeAndCodeRole()] find EtapeRole DTO by EtapeCode and RoleCode
	 * @author BELDI Jamel  on : 11 May. 2014 10:39:47	
	 * @param etapeCode,roleCode 
	 * @return etapeRoleDto
	 */
	public EtapeRoleDto findByCodeEtapeAndCodeRole(String etapeCode,
			String roleCode) {
		try {
			EtapeRole etapeRole = etapeRoleDao.findByCodeEtapeByCodeRole(etapeCode, roleCode);
			if(etapeRole!=null){
				EtapeRoleDto etapeRoleDto = new EtapeRoleDto();
			 mapper.map(etapeRole, etapeRoleDto);
			 return etapeRoleDto;
			}
			return null;

         } catch (RuntimeException e) {
			log.error("findByCodeEtapeByCodeRole failed", e);
			throw e;
          }
		
	}

}
