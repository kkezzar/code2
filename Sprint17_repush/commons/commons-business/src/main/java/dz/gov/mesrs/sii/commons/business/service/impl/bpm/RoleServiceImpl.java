/**
 * [dz.gov.mesrs.sii.fve.business.service.impl.habilitation.RoleServiceImpl.java] 
 * @author BELDI Jamel on : 5 mai 2014  11:22:43
 */
package dz.gov.mesrs.sii.commons.business.service.impl.bpm;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.RoleDto;
import dz.gov.mesrs.sii.commons.business.model.dto.bpm.RoleI18nDto;
import dz.gov.mesrs.sii.commons.business.service.bpm.RoleService;
import dz.gov.mesrs.sii.commons.data.dao.bpm.RoleDao;
import dz.gov.mesrs.sii.commons.data.dao.bpm.RoleI18nDao;
import dz.gov.mesrs.sii.commons.data.model.bpm.RoleI18n;

/**
 * @author BELDI Jamel  on : 5 mai 2014  11:22:43
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {
	private static final Log log = LogFactory
			.getLog(RoleServiceImpl.class);
	@Autowired
	@Qualifier ("roleDao")
	private RoleDao roleDao;
	@Autowired
	@Qualifier("mapper")
	private org.dozer.DozerBeanMapper mapper;
	@Autowired
	@Qualifier ("roleI18nDao")
	private RoleI18nDao roleI18nDao;
	
	/**
	 * [RoleServiceImpl.RoleServiceImpl()] Constructor
	 * @author BELDI Jamel  on : 5 mai 2014  11:22:43	
	 */
	public RoleServiceImpl() {
		
	}

	
	
	
	/**
	 * [RoleServiceImpl.roleDao] Getter 
	 * @author BELDI Jamel on : 5 mai 2014  11:26:32
	 * @return the roleDao
	 */
	public RoleDao getRoleDao() {
		return roleDao;
	}




	/**
	 * [RoleServiceImpl.roleDao] Setter 
	 * @author BELDI Jamel on : 5 mai 2014  11:26:32
	 * @param roleDao the roleDao to set
	 */
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}




	/**
	 * [RoleServiceImpl.mapper] Getter 
	 * @author BELDI Jamel on : 5 mai 2014  11:26:32
	 * @return the mapper
	 */
	public org.dozer.DozerBeanMapper getMapper() {
		return mapper;
	}




	/**
	 * [RoleServiceImpl.mapper] Setter 
	 * @author BELDI Jamel on : 5 mai 2014  11:26:32
	 * @param mapper the mapper to set
	 */
	public void setMapper(org.dozer.DozerBeanMapper mapper) {
		this.mapper = mapper;
	}




	/**
	 * [RoleServiceImpl.roleI18nDao] Getter 
	 * @author BELDI Jamel on : 5 mai 2014  11:26:32
	 * @return the roleI18nDao
	 */
	public RoleI18nDao getRoleI18nDao() {
		return roleI18nDao;
	}




	/**
	 * [RoleServiceImpl.roleI18nDao] Setter 
	 * @author BELDI Jamel on : 5 mai 2014  11:26:32
	 * @param roleI18nDao the roleI18nDao to set
	 */
	public void setRoleI18nDao(RoleI18nDao roleI18nDao) {
		this.roleI18nDao = roleI18nDao;
	}




	@Override
	public HashMap<String, RoleI18nDto> findListRoleI18nByRole(RoleDto roleDto) {
		HashMap<String, RoleI18nDto> mapRoleI18n = new HashMap<String, RoleI18nDto>();
		try {
			List<RoleI18n> roleI18nList = roleI18nDao.findListRoleI18nByRole(roleDto.getId());
			
			if (roleI18nList != null) {				
				for (RoleI18n currentRoleI18n : roleI18nList) {
					RoleI18nDto roleI18nDto = new RoleI18nDto();
					mapper.map(currentRoleI18n, roleI18nDto);
					
					mapRoleI18n.put(roleI18nDto.getLangue(), roleI18nDto);
				}
				return mapRoleI18n;
			}
		} catch (RuntimeException e) {
			log.error("get roleI18n list failed", e);
			throw e;
		}

		return null;
	}

}
