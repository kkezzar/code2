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

import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefAffectFiliereLmdEtabDao;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefAffectFiliereLmdEtab;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefAffectFiliereLmdEtabDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefAffectFiliereLmdEtabService;

/**
 * @author BELBRIK Oualid  on : 18 08. 2014 17=1:35:05
 */
@Service("refAffectFiliereLmdEtabServiceImpl")
public class RefAffectFiliereLmdEtabServiceImpl implements RefAffectFiliereLmdEtabService {
	private static final Logger log = LoggerFactory.getLogger(RefAffectFiliereLmdEtabServiceImpl.class.getName());

	@Autowired
	@Qualifier("refAffectFiliereLmdEtabDaoImpl")
	private RefAffectFiliereLmdEtabDao refAffectFiliereLmdEtabDao;
	@Autowired
	@Qualifier("mapper")
	private org.dozer.Mapper mapper;

	/* (non-Javadoc)
	 * @see dz.gov.mesrs.sii.referentiel.business.service.RefAffectFiliereLmdEtabService#findbyId(java.lang.String, boolean)
	 */
	@Override
	public List<RefAffectFiliereLmdEtabDto> findByCodeFilierelmd(String value) {
		
		List<RefAffectFiliereLmdEtabDto> refAffectFiliereLmdEtabListDto = new ArrayList<RefAffectFiliereLmdEtabDto>();
		try {
			List<RefAffectFiliereLmdEtab> refAffectFiliereLmdEtabList = (List<RefAffectFiliereLmdEtab>)refAffectFiliereLmdEtabDao.findByCodeFilierelmd(value);
			if (refAffectFiliereLmdEtabList != null) {
				log.debug("Affectation list success with size {}  ", new Object[]{  refAffectFiliereLmdEtabList.size()});
				for (RefAffectFiliereLmdEtab currentRefAffectFiliereLmdEtab : refAffectFiliereLmdEtabList) {
					RefAffectFiliereLmdEtabDto refAffectFiliereLmdEtabDto = new RefAffectFiliereLmdEtabDto();
					mapper.map(currentRefAffectFiliereLmdEtab, refAffectFiliereLmdEtabDto);
					refAffectFiliereLmdEtabListDto.add(refAffectFiliereLmdEtabDto);
				}
				

			}
		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}
		return refAffectFiliereLmdEtabListDto;
		
	}

	/* (non-Javadoc)
	 * @see dz.gov.mesrs.sii.referentiel.business.service.RefAffectFiliereLmdEtabService#save(dz.gov.mesrs.sii.referentiel.business.model.dto.RefAffectFiliereLmdEtabDto, boolean)
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void save(RefAffectFiliereLmdEtabDto refAffectFiliereLmdEtabDto) {
		RefAffectFiliereLmdEtab refAffectFiliereLmdEtab = new RefAffectFiliereLmdEtab();
		if (refAffectFiliereLmdEtabDto != null ) 
		try {
			mapper.map(refAffectFiliereLmdEtabDto, refAffectFiliereLmdEtab);
			refAffectFiliereLmdEtabDao.persist(refAffectFiliereLmdEtab);
			log.debug("save OccupationLieu successful");
		} catch (RuntimeException e) {
			log.error("save failed", e);
			throw e;
		}

	}

	/* (non-Javadoc)
	 * @see dz.gov.mesrs.sii.referentiel.business.service.RefAffectFiliereLmdEtabService#update(dz.gov.mesrs.sii.referentiel.business.model.dto.RefAffectFiliereLmdEtabDto)
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void update(RefAffectFiliereLmdEtabDto refAffectFiliereLmdEtabDto) {
		RefAffectFiliereLmdEtab refAffectFiliereLmdEtab = new RefAffectFiliereLmdEtab();
		mapper.map(refAffectFiliereLmdEtabDto, refAffectFiliereLmdEtab);
		try {
			refAffectFiliereLmdEtabDao.merge(refAffectFiliereLmdEtab);
			log.debug("update successful");
		} catch (RuntimeException e) {
			log.error("update failed", e);
			throw e;
		}

	}

	/* (non-Javadoc)
	 * @see dz.gov.mesrs.sii.referentiel.business.service.RefAffectFiliereLmdEtabService#saveOrUpdate(dz.gov.mesrs.sii.referentiel.business.model.dto.RefAffectFiliereLmdEtabDto)
	 */
	@Override
	public void saveOrUpdate(RefAffectFiliereLmdEtabDto refAffectFiliereLmdEtabDto) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see dz.gov.mesrs.sii.referentiel.business.service.RefAffectFiliereLmdEtabService#delete(java.lang.Integer)
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void delete(RefAffectFiliereLmdEtabDto refAffectFiliereLmdEtabDto) {
		RefAffectFiliereLmdEtab refAffectFiliereLmdEtab = new RefAffectFiliereLmdEtab();
		try {
			mapper.map(refAffectFiliereLmdEtabDto, refAffectFiliereLmdEtab);
			refAffectFiliereLmdEtab = refAffectFiliereLmdEtabDao.merge(refAffectFiliereLmdEtab);
			refAffectFiliereLmdEtabDao.remove(refAffectFiliereLmdEtab);
			log.debug("remove OccupationLieu successful");
		} catch (RuntimeException e) {
			log.error("remove failed", e);
			throw e;
		}

	}

	/**
	 * [RefAffectFiliereLmdEtabServiceImpl.refAffectFiliereLmdEtabDao] Getter 
	 * @author BELBRIK Oualid on : 19 02. 2014 17:43:03
	 * @return the refAffectFiliereLmdEtabDao
	 */
	public RefAffectFiliereLmdEtabDao getRefAffectFiliereLmdEtabDao() {
		return refAffectFiliereLmdEtabDao;
	}

	/**
	 * [RefAffectFiliereLmdEtabServiceImpl.refAffectFiliereLmdEtabDao] Setter 
	 * @author BELBRIK Oualid on : 19 02. 2014 17:43:03
	 * @param refAffectFiliereLmdEtabDao the refAffectFiliereLmdEtabDao to set
	 */
	public void setRefAffectFiliereLmdEtabDao(RefAffectFiliereLmdEtabDao refAffectFiliereLmdEtabDao) {
		this.refAffectFiliereLmdEtabDao = refAffectFiliereLmdEtabDao;
	}

	/**
	 * [RefAffectFiliereLmdEtabServiceImpl.mapper] Getter 
	 * @author BELBRIK Oualid on : 19 02. 2014 17:43:03
	 * @return the mapper
	 */
	public org.dozer.Mapper getMapper() {
		return mapper;
	}

	/**
	 * [RefAffectFiliereLmdEtabServiceImpl.mapper] Setter 
	 * @author BELBRIK Oualid on : 19 02. 2014 17:43:03
	 * @param mapper the mapper to set
	 */
	public void setMapper(org.dozer.Mapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public List<RefAffectFiliereLmdEtabDto> findByIdEtablissement(String value) {
		
		List<RefAffectFiliereLmdEtabDto> refAffectFiliereLmdEtabListDto = new ArrayList<RefAffectFiliereLmdEtabDto>();
		try {
			List<RefAffectFiliereLmdEtab> refAffectFiliereLmdEtabList = (List<RefAffectFiliereLmdEtab>)refAffectFiliereLmdEtabDao.findByIdEtablissement(value);
			if (refAffectFiliereLmdEtabList != null) {
				log.debug("Affectation list success with size {}  ", new Object[]{ refAffectFiliereLmdEtabList.size()});
				for (RefAffectFiliereLmdEtab currentRefAffectFiliereLmdEtab : refAffectFiliereLmdEtabList) {
					RefAffectFiliereLmdEtabDto refAffectFiliereLmdEtabDto = new RefAffectFiliereLmdEtabDto();
					mapper.map(currentRefAffectFiliereLmdEtab, refAffectFiliereLmdEtabDto);
					refAffectFiliereLmdEtabListDto.add(refAffectFiliereLmdEtabDto);
				}
				

			}
		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}
		return refAffectFiliereLmdEtabListDto;
		
	}
	
	@Override
	public List<RefAffectFiliereLmdEtabDto> findByIdEtablissement(Integer etablissementId) {
		
		List<RefAffectFiliereLmdEtabDto> refAffectFiliereLmdEtabListDto = new ArrayList<RefAffectFiliereLmdEtabDto>();
		try {
			List<RefAffectFiliereLmdEtab> refAffectFiliereLmdEtabList = (List<RefAffectFiliereLmdEtab>)refAffectFiliereLmdEtabDao.findByIdEtablissement(etablissementId);
			if (refAffectFiliereLmdEtabList != null) {
				log.debug("Affectation list success with size {}  ", new Object[]{ refAffectFiliereLmdEtabList.size()});
				for (RefAffectFiliereLmdEtab currentRefAffectFiliereLmdEtab : refAffectFiliereLmdEtabList) {
					RefAffectFiliereLmdEtabDto refAffectFiliereLmdEtabDto = new RefAffectFiliereLmdEtabDto();
					mapper.map(currentRefAffectFiliereLmdEtab, refAffectFiliereLmdEtabDto);
					refAffectFiliereLmdEtabListDto.add(refAffectFiliereLmdEtabDto);
				}
				

			}
		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}
		return refAffectFiliereLmdEtabListDto;
		
	}

	@Override
	public List<RefAffectFiliereLmdEtabDto> findByIdFilierelmd(Integer value) {
		List<RefAffectFiliereLmdEtabDto> refAffectFiliereLmdEtabListDto = new ArrayList<RefAffectFiliereLmdEtabDto>();
		try {
			List<RefAffectFiliereLmdEtab> refAffectFiliereLmdEtabList = (List<RefAffectFiliereLmdEtab>)refAffectFiliereLmdEtabDao.findByIdFilierelmd(value);
			if (refAffectFiliereLmdEtabList != null) {
				log.debug("Affectation list success with size {}  ", new Object[]{ refAffectFiliereLmdEtabList.size()});
				for (RefAffectFiliereLmdEtab currentRefAffectFiliereLmdEtab : refAffectFiliereLmdEtabList) {
					RefAffectFiliereLmdEtabDto refAffectFiliereLmdEtabDto = new RefAffectFiliereLmdEtabDto();
					mapper.map(currentRefAffectFiliereLmdEtab, refAffectFiliereLmdEtabDto);
					refAffectFiliereLmdEtabListDto.add(refAffectFiliereLmdEtabDto);
				}
				

			}
		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}
		return refAffectFiliereLmdEtabListDto;
	}

	

}
