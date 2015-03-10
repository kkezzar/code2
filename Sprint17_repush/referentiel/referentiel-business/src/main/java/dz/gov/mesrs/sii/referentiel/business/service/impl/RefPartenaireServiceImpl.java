/**
 * [dz.gov.mesrs.sii.referentiel.business.service.impl.RefPartenaireServiceImpl.java] 
 * @author MAKERRI Sofiane on : 2 janv. 2014  12:35:05
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

import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefPartenaireDao;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefPartenaire;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefPartenaireDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefPartenaireService;

/**
 * @author MAKERRI Sofiane  on : 2 janv. 2014  12:35:05
 */
@Service("refPartenaireServiceImpl")
public class RefPartenaireServiceImpl implements RefPartenaireService {
	private static final Logger log = LoggerFactory.getLogger(RefPartenaireServiceImpl.class.getName());


	@Autowired
	@Qualifier("refPartenaireDaoImpl")
	private RefPartenaireDao refPartenaireDao;
	@Autowired
	@Qualifier("mapper")
	private org.dozer.Mapper mapper;

	/* (non-Javadoc)
	 * @see dz.gov.mesrs.sii.referentiel.business.service.RefPartenaireService#findbyId(java.lang.String, boolean)
	 */
	@Override
	public List<RefPartenaireDto> findByIdContrat(Integer value) {
		
		List<RefPartenaireDto> refPartenaireListDto = new ArrayList<RefPartenaireDto>();
		try {
			List<RefPartenaire> refPartenaireList = (List<RefPartenaire>)refPartenaireDao.findByIdContrat(value);
			if (refPartenaireList != null) {
				log.debug("Partenaire list success with size =  {}", new Object[]{refPartenaireList.size()});
				for (RefPartenaire currentRefPartenaire : refPartenaireList) {
					RefPartenaireDto refPartenaireDto = new RefPartenaireDto();
					mapper.map(currentRefPartenaire, refPartenaireDto);
					refPartenaireListDto.add(refPartenaireDto);
				}
				

			}
		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}
		return refPartenaireListDto;
		
	}

	/* (non-Javadoc)
	 * @see dz.gov.mesrs.sii.referentiel.business.service.RefPartenaireService#save(dz.gov.mesrs.sii.referentiel.business.model.dto.RefPartenaireDto, boolean)
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void save(RefPartenaireDto refPartenaireDto) {
		RefPartenaire refPartenaire = new RefPartenaire();
		if (refPartenaireDto != null && refPartenaireDto.getContractant() == null) {
			refPartenaireDto.setContractant(false);
		}
		try {
			mapper.map(refPartenaireDto, refPartenaire);
			refPartenaireDao.persist(refPartenaire);
			log.debug("save partenaire successful");
		} catch (RuntimeException e) {
			log.error("save failed", e);
			throw e;
		}

	}

	/* (non-Javadoc)
	 * @see dz.gov.mesrs.sii.referentiel.business.service.RefPartenaireService#update(dz.gov.mesrs.sii.referentiel.business.model.dto.RefPartenaireDto)
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void update(RefPartenaireDto refPartenaireDto) {
		RefPartenaire refPartenaire = new RefPartenaire();
		mapper.map(refPartenaireDto, refPartenaire);
		try {
			refPartenaireDao.merge(refPartenaire);
			log.debug("update successful");
		} catch (RuntimeException e) {
			log.error("update failed", e);
			throw e;
		}

	}

	/* (non-Javadoc)
	 * @see dz.gov.mesrs.sii.referentiel.business.service.RefPartenaireService#saveOrUpdate(dz.gov.mesrs.sii.referentiel.business.model.dto.RefPartenaireDto)
	 */
	@Override
	public void saveOrUpdate(RefPartenaireDto refPartenaireDto) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see dz.gov.mesrs.sii.referentiel.business.service.RefPartenaireService#delete(java.lang.Integer)
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void delete(RefPartenaireDto refPartenaireDto) {
		RefPartenaire refPartenaire = new RefPartenaire();
		try {
			mapper.map(refPartenaireDto, refPartenaire);
			refPartenaire = refPartenaireDao.merge(refPartenaire);
			refPartenaireDao.remove(refPartenaire);
			log.debug("remove partenaire successful");
		} catch (RuntimeException e) {
			log.error("remove failed", e);
			throw e;
		}

	}

	/**
	 * [RefPartenaireServiceImpl.refPartenaireDao] Getter 
	 * @author MAKERRI Sofiane on : 2 janv. 2014  12:43:03
	 * @return the refPartenaireDao
	 */
	public RefPartenaireDao getRefPartenaireDao() {
		return refPartenaireDao;
	}

	/**
	 * [RefPartenaireServiceImpl.refPartenaireDao] Setter 
	 * @author MAKERRI Sofiane on : 2 janv. 2014  12:43:03
	 * @param refPartenaireDao the refPartenaireDao to set
	 */
	public void setRefPartenaireDao(RefPartenaireDao refPartenaireDao) {
		this.refPartenaireDao = refPartenaireDao;
	}

	/**
	 * [RefPartenaireServiceImpl.mapper] Getter 
	 * @author MAKERRI Sofiane on : 2 janv. 2014  12:43:03
	 * @return the mapper
	 */
	public org.dozer.Mapper getMapper() {
		return mapper;
	}

	/**
	 * [RefPartenaireServiceImpl.mapper] Setter 
	 * @author MAKERRI Sofiane on : 2 janv. 2014  12:43:03
	 * @param mapper the mapper to set
	 */
	public void setMapper(org.dozer.Mapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public List<RefPartenaireDto> findByCodeContrat(String value) {
		List<RefPartenaireDto> refPartenaireListDto = new ArrayList<RefPartenaireDto>();
		try {
			List<RefPartenaire> refPartenaireList = (List<RefPartenaire>)refPartenaireDao.findByCodeContrat(value);
			if (refPartenaireList != null) {
				log.debug("Partenaire list success with size =  {}", new Object[]{refPartenaireList.size()});
				for (RefPartenaire currentRefPartenaire : refPartenaireList) {
					RefPartenaireDto refPartenaireDto = new RefPartenaireDto();
					mapper.map(currentRefPartenaire, refPartenaireDto);
					refPartenaireListDto.add(refPartenaireDto);
				}
				

			}
		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}
		return refPartenaireListDto;

	}

	/**
	 * [RefPartenaireService.findPartenairesStructure] Method 
	 * Overridden By : @author rlaib  on : 7 janv. 2015  09:49:11
	 * @param idStructure
	 * @return
	 */
	@Override
	public List<RefPartenaireDto> findPartenairesStructure(Integer idStructure) {
		try {
			List<RefPartenaire> refPartenaireList = refPartenaireDao.findPartenairesStructure(idStructure);
			List<RefPartenaireDto> refPartenaireListDto = new ArrayList<RefPartenaireDto>(); 
			if (refPartenaireList != null) {
				for (RefPartenaire currentRefPartenaire : refPartenaireList) {
					RefPartenaireDto refPartenaireDto = new RefPartenaireDto();
					mapper.map(currentRefPartenaire, refPartenaireDto);
					refPartenaireListDto.add(refPartenaireDto);
				}
			}
			return refPartenaireListDto;
		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}
	}
	

}
