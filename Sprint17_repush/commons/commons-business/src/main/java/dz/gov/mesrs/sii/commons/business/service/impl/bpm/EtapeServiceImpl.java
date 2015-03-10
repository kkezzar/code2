/**
 * [dz.gov.mesrs.sii.fve.business.service.impl.habilitation.EtapeServiceImpl.java] 
 * @author BELDI Jamel on : 5 mai 2014  11:21:45
 */
package dz.gov.mesrs.sii.commons.business.service.impl.bpm;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.EtapeActionDto;
import dz.gov.mesrs.sii.commons.business.model.dto.bpm.EtapeDto;
import dz.gov.mesrs.sii.commons.business.model.dto.bpm.EtapeI18nDto;
import dz.gov.mesrs.sii.commons.business.model.dto.bpm.EtapeRoleDto;
import dz.gov.mesrs.sii.commons.business.service.bpm.EtapeService;
import dz.gov.mesrs.sii.commons.data.dao.bpm.EtapeActionDao;
import dz.gov.mesrs.sii.commons.data.dao.bpm.EtapeDao;
import dz.gov.mesrs.sii.commons.data.dao.bpm.EtapeI18nDao;
import dz.gov.mesrs.sii.commons.data.dao.bpm.EtapeRoleDao;
import dz.gov.mesrs.sii.commons.data.model.bpm.Etape;
import dz.gov.mesrs.sii.commons.data.model.bpm.EtapeAction;
import dz.gov.mesrs.sii.commons.data.model.bpm.EtapeI18n;
import dz.gov.mesrs.sii.commons.data.model.bpm.EtapeRole;

/**
 * @author BELDI Jamel  on : 5 mai 2014  11:21:45
 */
@Service("etapeService")
public class EtapeServiceImpl implements EtapeService {
	private static final Log log = LogFactory
	.getLog(EtapeServiceImpl.class);
@Autowired
@Qualifier ("etapeDao")
private EtapeDao etapeDao;
@Autowired
@Qualifier("mapper")
private org.dozer.DozerBeanMapper mapper;
@Autowired
@Qualifier ("etapeI18nDao")
private EtapeI18nDao etapeI18nDao;
	
@Autowired
@Qualifier ("etapeActionDao")
private EtapeActionDao etapeActionDao;

@Autowired
@Qualifier ("etapeRoleDao")
private EtapeRoleDao etapeRoleDao;

	/**
	 * [EtapeServiceImpl.EtapeServiceImpl()] Constructor
	 * @author BELDI Jamel  on : 5 mai 2014  11:21:45	
	 */
	public EtapeServiceImpl() {
		
	}

	
	
	/**
	 * [EtapeServiceImpl.etapeDao] Getter 
	 * @author BELDI Jamel on : 5 mai 2014  11:27:10
	 * @return the etapeDao
	 */
	public EtapeDao getEtapeDao() {
		return etapeDao;
	}



	/**
	 * [EtapeServiceImpl.etapeDao] Setter 
	 * @author BELDI Jamel on : 5 mai 2014  11:27:10
	 * @param etapeDao the etapeDao to set
	 */
	public void setEtapeDao(EtapeDao etapeDao) {
		this.etapeDao = etapeDao;
	}



	/**
	 * [EtapeServiceImpl.mapper] Getter 
	 * @author BELDI Jamel on : 5 mai 2014  11:27:10
	 * @return the mapper
	 */
	public org.dozer.DozerBeanMapper getMapper() {
		return mapper;
	}



	/**
	 * [EtapeServiceImpl.mapper] Setter 
	 * @author BELDI Jamel on : 5 mai 2014  11:27:10
	 * @param mapper the mapper to set
	 */
	public void setMapper(org.dozer.DozerBeanMapper mapper) {
		this.mapper = mapper;
	}



	/**
	 * [EtapeServiceImpl.etapeI18nDao] Getter 
	 * @author BELDI Jamel on : 5 mai 2014  11:27:10
	 * @return the etapeI18nDao
	 */
	public EtapeI18nDao getEtapeI18nDao() {
		return etapeI18nDao;
	}



	/**
	 * [EtapeServiceImpl.etapeI18nDao] Setter 
	 * @author BELDI Jamel on : 5 mai 2014  11:27:10
	 * @param etapeI18nDao the etapeI18nDao to set
	 */
	public void setEtapeI18nDao(EtapeI18nDao etapeI18nDao) {
		this.etapeI18nDao = etapeI18nDao;
	}



	@Override
	public HashMap<String, EtapeI18nDto> findListEtapeI18nByEtape(
			EtapeDto etapeDto) {
		HashMap<String, EtapeI18nDto> mapEtapeI18n = new HashMap<String, EtapeI18nDto>();
		try {
			List<EtapeI18n> etapeI18nList = etapeI18nDao.findListEtapeI18nByEtape(etapeDto.getId());
			
			if (etapeI18nList != null) {				
				for (EtapeI18n currentEtapeI18n : etapeI18nList) {
					EtapeI18nDto etapeI18nDto = new EtapeI18nDto();
					mapper.map(currentEtapeI18n, etapeI18nDto);
					
					mapEtapeI18n.put(etapeI18nDto.getLangue(), etapeI18nDto);
				}
				return mapEtapeI18n;
			}
		} catch (RuntimeException e) {
			log.error("get etapeI18n list failed", e);
			throw e;
		}

		return null;
	}

	@Override
    public EtapeDto findEtapeByCode(String etapeCode) {
		try {
		Etape etape = etapeDao.findByCode(etapeCode);
		return mapper.map(etape, EtapeDto.class);
		} catch (RuntimeException e) {
			log.error("findEtapeByCode failed", e);
			throw e;
		}
	}

	@Override
    public EtapeActionDto findByCodeEtapeByCodeAction(String codeEtape, String codeAction) {
		
		try {
						EtapeAction etapeAction = etapeActionDao.findByCodeEtapeByCodeAction(codeEtape, codeAction);
						return mapper.map(etapeAction, EtapeActionDto.class);
						
			} catch (RuntimeException e) {
						
						log.error("findEtapeByCode failed", e);
						throw e;
			}
	}
	
	@Override
	 public EtapeRoleDto findByCodeEtapeByCodeRole(String codeEtape, String codeRole) {
			try {
						EtapeRole etapeRole = etapeRoleDao.findByCodeEtapeByCodeRole(codeEtape, codeRole);
						return mapper.map(etapeRole, EtapeRoleDto.class);
			
			} catch (RuntimeException e) {
			
						log.error("findByCodeEtapeByCodeRole failed", e);
						throw e;
			}
	}

}
