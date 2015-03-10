/**
 * [dz.gov.mesrs.sii.referentiel.business.service.impl.RefParametreEtabServiceImpl.java] 
 * @author MAKERRI Sofiane on : 27 avr. 2014  14:19:59
 */
package dz.gov.mesrs.sii.referentiel.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefParametreEtabDao;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefParametreEtab;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefParametreEtabDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefParametreEtabService;

/**
 * @author MAKERRI Sofiane  on : 27 avr. 2014  14:19:59
 */
@Service ("refParametreEtabServiceImpl")
public class RefParametreEtabServiceImpl implements RefParametreEtabService {

	private static final Logger log = LoggerFactory.getLogger(RefParametreEtabServiceImpl.class.getName());
	@Autowired
	@Qualifier("refParametreEtabDaoImpl")
	private RefParametreEtabDao refParametreEtabDaoImpl;
	@Autowired
	@Qualifier("mapper")
	private org.dozer.Mapper mapper;
	/**
	 * [RefParametreEtabServiceImpl.RefParametreEtabServiceImpl()] Constructor
	 * @author MAKERRI Sofiane  on : 27 avr. 2014  14:19:59	
	 */
	public RefParametreEtabServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<RefParametreEtabDto> findGeneric(Integer idEtab, String value) {
		List<RefParametreEtabDto> refParametreEtabDtos = new ArrayList<RefParametreEtabDto>();
		try {
			List<RefParametreEtab> refParametreEtabList = refParametreEtabDaoImpl
					.findGeneric(idEtab, value);
			if (refParametreEtabList != null) {
				log.debug("configuration parameters  list found success with size =  {}", new Object[]{
						refParametreEtabList.size()});

				for (RefParametreEtab current : refParametreEtabList) {
					RefParametreEtabDto refParametreEtabDto = new RefParametreEtabDto();
					mapper.map(current, refParametreEtabDto);
					refParametreEtabDtos
							.add(refParametreEtabDto);
				}

			}
		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}
		return refParametreEtabDtos;
	}

	@Override
	public RefParametreEtabDto findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RefParametreEtabDto findByKey(Integer id, String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RefParametreEtabDto findByKey(Integer id, String idfEtab, String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String findValueOfKey(String key) {
		try {
			String value = refParametreEtabDaoImpl
					.findValueOfKey(key);
			return value;
		} catch (Exception e) {
			log.error("findByKey failed", e);

		}
		return null;
	}

	@Override
	public String findValueOfKey(Integer idEtab, String key) {
		try {
			String value = refParametreEtabDaoImpl
					.findValueOfKey(idEtab, key);
			return value;
		} catch (Exception e) {
			log.error("findByKey failed", e);

		}
		return null;
	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public RefParametreEtabDto save(RefParametreEtabDto refParametreEtabDto) {
		RefParametreEtab refParametreEtab = new RefParametreEtab();
		mapper.map(refParametreEtabDto, refParametreEtab);
		try {
			refParametreEtabDaoImpl.persist(refParametreEtab);
			log.debug("save successful");
			mapper.map(refParametreEtab, refParametreEtabDto);

		} catch (RuntimeException e) {
			log.error("save failed", e);
			throw e;
		}
		return refParametreEtabDto;
	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void update(RefParametreEtabDto refParametreEtabDto) {
		RefParametreEtab refParametreEtab = new RefParametreEtab();
		mapper.map(refParametreEtabDto, refParametreEtab);
		try {
			refParametreEtabDaoImpl.merge(refParametreEtab);
			log.debug("update successful");
		} catch (RuntimeException e) {
			log.error("update failed", e);
			throw e;
		}
		
	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void delete(RefParametreEtabDto refParametreEtabDto) {
		try {
			RefParametreEtab refParametreEtab = mapper.map(
					refParametreEtabDto,
					RefParametreEtab.class);
			refParametreEtab = refParametreEtabDaoImpl
					.merge(refParametreEtab);
			refParametreEtabDaoImpl.remove(refParametreEtab);
			log.debug("remove refParametreEtabDto successful");
		} catch (RuntimeException e) {
			log.error("remove refParametreEtabDto failed", e);
			throw e;
		}
		
	}

	/**
	 * [RefParametreEtabServiceImpl.refParametreEtabDaoImpl] Getter 
	 * @author MAKERRI Sofiane on : 27 avr. 2014  14:40:33
	 * @return the refParametreEtabDaoImpl
	 */
	public RefParametreEtabDao getRefParametreEtabDaoImpl() {
		return refParametreEtabDaoImpl;
	}

	/**
	 * [RefParametreEtabServiceImpl.refParametreEtabDaoImpl] Setter 
	 * @author MAKERRI Sofiane on : 27 avr. 2014  14:40:33
	 * @param refParametreEtabDaoImpl the refParametreEtabDaoImpl to set
	 */
	public void setRefParametreEtabDaoImpl(
			RefParametreEtabDao refParametreEtabDaoImpl) {
		this.refParametreEtabDaoImpl = refParametreEtabDaoImpl;
	}

	@Override
	public List<RefParametreEtabDto> findAll(Integer idEtab) {
		List<RefParametreEtabDto> refParametreEtabDtos = new ArrayList<RefParametreEtabDto>();
		try {
			List<RefParametreEtab> refParametreEtabList = refParametreEtabDaoImpl
					.findAll(idEtab);
			if (refParametreEtabList != null) {
				log.debug("configuration parameters  list found success with size =  {}", new Object[]{
						refParametreEtabList.size()});


				for (RefParametreEtab current : refParametreEtabList) {
					RefParametreEtabDto refParametreEtabDto = new RefParametreEtabDto();
					mapper.map(current, refParametreEtabDto);
					refParametreEtabDtos
							.add(refParametreEtabDto);
				}

			}
		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}
		return refParametreEtabDtos;
	}

	@Override
	public RefParametreEtabDto findByKeyEtab(String idfEtab, String key) {
		RefParametreEtabDto refParametreEtabDto = new RefParametreEtabDto();
		
		try {
			RefParametreEtab refParametreEtab = refParametreEtabDaoImpl.findByKeyEtab(idfEtab, key);
			if(refParametreEtab == null) {
				return null;
			}
			log.debug("findByKeyEtab successful");
			mapper.map(refParametreEtab, refParametreEtabDto);

		} catch (RuntimeException e) {
			log.error("findByKeyEtab failed", e);
			throw e;
		}
		return refParametreEtabDto;
	}

}
