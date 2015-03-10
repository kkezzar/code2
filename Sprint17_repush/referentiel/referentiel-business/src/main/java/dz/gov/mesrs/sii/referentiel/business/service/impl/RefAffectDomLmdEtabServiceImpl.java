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

import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefAffectDomLmdEtabDao;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefAffectDomLmdEtab;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefAffectDomLmdEtabDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefAffectDomLmdEtabService;

/**
 * @author BELBRIK Oualid  on : 20 04. 2014 17=1:35:05
 */
@Service("refAffectDomLmdEtabServiceImpl")
public class RefAffectDomLmdEtabServiceImpl implements RefAffectDomLmdEtabService {
	private static final Logger log = LoggerFactory.getLogger(RefAffectDomLmdEtabServiceImpl.class.getName());

	@Autowired
	@Qualifier("refAffectDomLmdEtabDaoImpl")
	private RefAffectDomLmdEtabDao refAffectDomLmdEtabDao;
	@Autowired
	@Qualifier("mapper")
	private org.dozer.Mapper mapper;

	/* (non-Javadoc)
	 * @see dz.gov.mesrs.sii.referentiel.business.service.RefAffectDomLmdEtabService#findbyId(java.lang.String, boolean)
	 */
	@Override
	public List<RefAffectDomLmdEtabDto> findByCodeDomainelmd(String value) {
		
		List<RefAffectDomLmdEtabDto> refAffectDomLmdEtabListDto = new ArrayList<RefAffectDomLmdEtabDto>();
		try {
			List<RefAffectDomLmdEtab> refAffectDomLmdEtabList = (List<RefAffectDomLmdEtab>)refAffectDomLmdEtabDao.findByCodeDomainelmd(value);
			if (refAffectDomLmdEtabList != null) {
				log.debug("Affectation list success with size {} ", new Object[]{ refAffectDomLmdEtabList.size()});
				for (RefAffectDomLmdEtab currentRefAffectDomLmdEtab : refAffectDomLmdEtabList) {
					RefAffectDomLmdEtabDto refAffectDomLmdEtabDto = new RefAffectDomLmdEtabDto();
					mapper.map(currentRefAffectDomLmdEtab, refAffectDomLmdEtabDto);
					refAffectDomLmdEtabListDto.add(refAffectDomLmdEtabDto);
				}
				

			}
		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}
		return refAffectDomLmdEtabListDto;
		
	}

	/* (non-Javadoc)
	 * @see dz.gov.mesrs.sii.referentiel.business.service.RefAffectDomLmdEtabService#save(dz.gov.mesrs.sii.referentiel.business.model.dto.RefAffectDomLmdEtabDto, boolean)
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void save(RefAffectDomLmdEtabDto refAffectDomLmdEtabDto) {
		RefAffectDomLmdEtab refAffectDomLmdEtab = new RefAffectDomLmdEtab();
		if (refAffectDomLmdEtabDto != null ) 
		try {
			mapper.map(refAffectDomLmdEtabDto, refAffectDomLmdEtab);
			refAffectDomLmdEtabDao.persist(refAffectDomLmdEtab);
			log.debug("save OccupationLieu successful");
		} catch (RuntimeException e) {
			log.error("save failed", e);
			throw e;
		}

	}

	/* (non-Javadoc)
	 * @see dz.gov.mesrs.sii.referentiel.business.service.RefAffectDomLmdEtabService#update(dz.gov.mesrs.sii.referentiel.business.model.dto.RefAffectDomLmdEtabDto)
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void update(RefAffectDomLmdEtabDto refAffectDomLmdEtabDto) {
		RefAffectDomLmdEtab refAffectDomLmdEtab = new RefAffectDomLmdEtab();
		mapper.map(refAffectDomLmdEtabDto, refAffectDomLmdEtab);
		try {
			refAffectDomLmdEtabDao.merge(refAffectDomLmdEtab);
			log.debug("update successful");
		} catch (RuntimeException e) {
			log.error("update failed", e);
			throw e;
		}

	}

	/* (non-Javadoc)
	 * @see dz.gov.mesrs.sii.referentiel.business.service.RefAffectDomLmdEtabService#saveOrUpdate(dz.gov.mesrs.sii.referentiel.business.model.dto.RefAffectDomLmdEtabDto)
	 */
	@Override
	public void saveOrUpdate(RefAffectDomLmdEtabDto refAffectDomLmdEtabDto) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see dz.gov.mesrs.sii.referentiel.business.service.RefAffectDomLmdEtabService#delete(java.lang.Integer)
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void delete(RefAffectDomLmdEtabDto refAffectDomLmdEtabDto) {
		RefAffectDomLmdEtab refAffectDomLmdEtab = new RefAffectDomLmdEtab();
		try {
			mapper.map(refAffectDomLmdEtabDto, refAffectDomLmdEtab);
			refAffectDomLmdEtab = refAffectDomLmdEtabDao.merge(refAffectDomLmdEtab);
			refAffectDomLmdEtabDao.remove(refAffectDomLmdEtab);
			log.debug("remove OccupationLieu successful");
		} catch (RuntimeException e) {
			log.error("remove failed", e);
			throw e;
		}

	}

	/**
	 * [RefAffectDomLmdEtabServiceImpl.refAffectDomLmdEtabDao] Getter 
	 * @author BELBRIK Oualid on : 19 02. 2014 17:43:03
	 * @return the refAffectDomLmdEtabDao
	 */
	public RefAffectDomLmdEtabDao getRefAffectDomLmdEtabDao() {
		return refAffectDomLmdEtabDao;
	}

	/**
	 * [RefAffectDomLmdEtabServiceImpl.refAffectDomLmdEtabDao] Setter 
	 * @author BELBRIK Oualid on : 19 02. 2014 17:43:03
	 * @param refAffectDomLmdEtabDao the refAffectDomLmdEtabDao to set
	 */
	public void setRefAffectDomLmdEtabDao(RefAffectDomLmdEtabDao refAffectDomLmdEtabDao) {
		this.refAffectDomLmdEtabDao = refAffectDomLmdEtabDao;
	}

	/**
	 * [RefAffectDomLmdEtabServiceImpl.mapper] Getter 
	 * @author BELBRIK Oualid on : 19 02. 2014 17:43:03
	 * @return the mapper
	 */
	public org.dozer.Mapper getMapper() {
		return mapper;
	}

	/**
	 * [RefAffectDomLmdEtabServiceImpl.mapper] Setter 
	 * @author BELBRIK Oualid on : 19 02. 2014 17:43:03
	 * @param mapper the mapper to set
	 */
	public void setMapper(org.dozer.Mapper mapper) {
		this.mapper = mapper;
	}

	
	@Override
	public List<RefAffectDomLmdEtabDto> findByIdDomainelmd(Integer value) {
		List<RefAffectDomLmdEtabDto> refAffectDomLmdEtabListDto = new ArrayList<RefAffectDomLmdEtabDto>();
		try {
			List<RefAffectDomLmdEtab> refAffectDomLmdEtabList = (List<RefAffectDomLmdEtab>)refAffectDomLmdEtabDao.findByIdDomainelmd(value);
			if (refAffectDomLmdEtabList != null) {
				log.debug("Affectation list success with size {}  ", new Object[]{ refAffectDomLmdEtabList.size()});
				for (RefAffectDomLmdEtab currentRefAffectDomLmdEtab : refAffectDomLmdEtabList) {
					RefAffectDomLmdEtabDto refAffectDomLmdEtabDto = new RefAffectDomLmdEtabDto();
					mapper.map(currentRefAffectDomLmdEtab, refAffectDomLmdEtabDto);
					refAffectDomLmdEtabListDto.add(refAffectDomLmdEtabDto);
				}
				

			}
		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}
		return refAffectDomLmdEtabListDto;
	}

	@Override
	public List<RefAffectDomLmdEtabDto> findByCodeEtablissement(String value) {
		
		List<RefAffectDomLmdEtabDto> refAffectDomLmdEtabListDto = new ArrayList<RefAffectDomLmdEtabDto>();
		try {
			List<RefAffectDomLmdEtab> refAffectDomLmdEtabList = (List<RefAffectDomLmdEtab>)refAffectDomLmdEtabDao.findByCodeEtablissement(value);
			if (refAffectDomLmdEtabList != null) {
				log.debug("Affectation list success with size {}  ", new Object[]{ refAffectDomLmdEtabList.size()});
				for (RefAffectDomLmdEtab currentRefAffectDomLmdEtab : refAffectDomLmdEtabList) {
					RefAffectDomLmdEtabDto refAffectDomLmdEtabDto = new RefAffectDomLmdEtabDto();
					mapper.map(currentRefAffectDomLmdEtab, refAffectDomLmdEtabDto);
					refAffectDomLmdEtabListDto.add(refAffectDomLmdEtabDto);
				}
				

			}
		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}
		return refAffectDomLmdEtabListDto;
		
	}

	
	
	@Override
	public List<RefAffectDomLmdEtabDto> findByIdEtablissement(String value) {
		
		List<RefAffectDomLmdEtabDto> refAffectDomLmdEtabListDto = new ArrayList<RefAffectDomLmdEtabDto>();
		try {
			List<RefAffectDomLmdEtab> refAffectDomLmdEtabList = (List<RefAffectDomLmdEtab>)refAffectDomLmdEtabDao.findByIdEtablissement(value);
			if (refAffectDomLmdEtabList != null) {
				log.debug("Affectation list success with size {}  ", new Object[]{ refAffectDomLmdEtabList.size()});
				for (RefAffectDomLmdEtab currentRefAffectDomLmdEtab : refAffectDomLmdEtabList) {
					RefAffectDomLmdEtabDto refAffectDomLmdEtabDto = new RefAffectDomLmdEtabDto();
					mapper.map(currentRefAffectDomLmdEtab, refAffectDomLmdEtabDto);
					refAffectDomLmdEtabListDto.add(refAffectDomLmdEtabDto);
				}
				

			}
		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}
		return refAffectDomLmdEtabListDto;
		
	}

	@Override
	public List<RefAffectDomLmdEtabDto> findByIdEtablissement(Integer idEtablissement) {

		List<RefAffectDomLmdEtabDto> refAffectDomLmdEtabListDto = new ArrayList<RefAffectDomLmdEtabDto>();
		try {
			List<RefAffectDomLmdEtab> refAffectDomLmdEtabList = (List<RefAffectDomLmdEtab>)refAffectDomLmdEtabDao.findByIdEtablissement(idEtablissement);
			if (refAffectDomLmdEtabList != null) {
				log.debug("Affectation list success with size {}  ", new Object[]{ refAffectDomLmdEtabList.size()});
				for (RefAffectDomLmdEtab currentRefAffectDomLmdEtab : refAffectDomLmdEtabList) {
					RefAffectDomLmdEtabDto refAffectDomLmdEtabDto = new RefAffectDomLmdEtabDto();
					mapper.map(currentRefAffectDomLmdEtab, refAffectDomLmdEtabDto);
					refAffectDomLmdEtabListDto.add(refAffectDomLmdEtabDto);
				}
				

			}
		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}
		return refAffectDomLmdEtabListDto;
	}
	
	

}
