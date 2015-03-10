/**
 * [dz.gov.mesrs.sii.referentiel.business.service.impl.RefReservationServiceImpl.java] 
 * @author BELBRIK Oualid on : 17 03. 2014  17:35:05
 */
package dz.gov.mesrs.sii.referentiel.business.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefReservationDao;
import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefSituationDao;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefEntiteSituation;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefReservation;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefSituation;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefReservationDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefParametrageService;
import dz.gov.mesrs.sii.referentiel.business.service.RefReservationService;

/**
 * @author BELBRIK Oualid  on : 17 03. 2014 17:35:05
 */
@Service("refReservationServiceImpl")
public class RefReservationServiceImpl implements RefReservationService {
	private static final Logger log = LoggerFactory.getLogger(RefReservationServiceImpl.class.getName());

	@Autowired
	@Qualifier("refReservationDaoImpl")
	private RefReservationDao refReservationDao;
	@Autowired
	@Qualifier("mapper")
	private org.dozer.Mapper mapper;
	@Autowired
	@Qualifier("refSituationDaoImpl")
	private RefSituationDao refSituationDao;
	@Autowired
	@Qualifier("refParametrageServiceImpl")
	private RefParametrageService refParametrageServiceImpl;
	

	/**
	 * [RefEvenementServiceImpl.refParametrageServiceImpl] Getter 
	 * @author BELDI Jamel on : 7 avr. 2014  14:59:45
	 * @return the refParametrageServiceImpl
	 */
	public RefParametrageService getRefParametrageServiceImpl() {
		return refParametrageServiceImpl;
	}

	/**
	 * [RefEvenementServiceImpl.refParametrageServiceImpl] Setter 
	 * @author BELDI Jamel on : 7 avr. 2014  14:59:45
	 * @param refParametrageServiceImpl the refParametrageServiceImpl to set
	 */
	public void setRefParametrageServiceImpl(
			RefParametrageService refParametrageServiceImpl) {
		this.refParametrageServiceImpl = refParametrageServiceImpl;
	}

	/* (non-Javadoc)
	 * @see dz.gov.mesrs.sii.referentiel.business.service.RefReservationService#findbyId(java.lang.String, boolean)
	 */
	@Override
	public List<RefReservationDto> findByIdLieu(Integer value) {
		
		List<RefReservationDto> refReservationListDto = new ArrayList<RefReservationDto>();
		try {
			List<RefReservation> refReservationList = (List<RefReservation>)refReservationDao.findByIdLieu(value);
			if (refReservationList != null) {
				log.debug("OccupationLieu list success with size = {} ",new Object[]{refReservationList.size()});
				for (RefReservation currentRefReservation : refReservationList) {
					RefReservationDto refReservationDto = new RefReservationDto();
					mapper.map(currentRefReservation, refReservationDto);
					refReservationListDto.add(refReservationDto);
				}
				

			}
		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}
		return refReservationListDto;
		
	}

	/* (non-Javadoc)
	 * @see dz.gov.mesrs.sii.referentiel.business.service.RefReservationService#save(dz.gov.mesrs.sii.referentiel.business.model.dto.RefReservationDto, boolean)
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void save(RefReservationDto refReservationDto) {
		RefReservation refReservation = new RefReservation();
		if (refReservationDto != null ) 
		try {
			mapper.map(refReservationDto, refReservation);
			refReservationDao.persist(refReservation);
			RefSituation refSituation = new RefSituation();
			refSituation.setDateSituation(new Date());
			refSituation.setIdEntite(refReservation.getId());
			refSituation.setNomEntite(RefReservation.class.getSimpleName());
			RefEntiteSituation refEntiteSituation = new RefEntiteSituation();
			refEntiteSituation.setId(27/*Integer.parseInt(refParametrageServiceImpl.getParamConfiguration(UtilConstant.RESERVATION_SITUATION_CREE_ID_KEY, UtilConstant.CONFIGURATION_UTIL).getValue())*/);
			refSituation.setRefEntiteSituation(refEntiteSituation);
			refSituationDao.persist(refSituation);	
			log.debug("save OccupationLieu successful");
		} catch (RuntimeException e) {
			log.error("save failed", e);
			throw e;
		}

	}

	/* (non-Javadoc)
	 * @see dz.gov.mesrs.sii.referentiel.business.service.RefReservationService#update(dz.gov.mesrs.sii.referentiel.business.model.dto.RefReservationDto)
	 */

		@Override
		@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
		public void update(RefReservationDto refReservationDto) {
			RefReservation refReservation = new RefReservation();
			mapper.map(refReservationDto, refReservation);
			try {
				refReservationDao.merge(refReservation);
				log.debug("update successful");
			} catch (RuntimeException e) {
				log.error("update failed", e);
				throw e;
			}
		
		}


	/* (non-Javadoc)
	 * @see dz.gov.mesrs.sii.referentiel.business.service.RefReservationService#saveOrUpdate(dz.gov.mesrs.sii.referentiel.business.model.dto.RefReservationDto)
	 */
	@Override
	public void saveOrUpdate(RefReservationDto refReservationDto) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see dz.gov.mesrs.sii.referentiel.business.service.RefReservationService#delete(java.lang.Integer)
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void delete(RefReservationDto refReservationDto) {
		RefReservation refReservation = new RefReservation();
		try {
			mapper.map(refReservationDto, refReservation);
			refReservation = refReservationDao.merge(refReservation);
			refReservationDao.remove(refReservation);
			log.debug("remove OccupationLieu successful");
		} catch (RuntimeException e) {
			log.error("remove failed", e);
			throw e;
		}

	}

	/**
	 * [RefReservationServiceImpl.refReservationDao] Getter 
	 * @author BELBRIK Oualid on : 17 03. 2014 17:43:03
	 * @return the refReservationDao
	 */
	public RefReservationDao getRefReservationDao() {
		return refReservationDao;
	}

	/**
	 * [RefReservationServiceImpl.refReservationDao] Setter 
	 * @author BELBRIK Oualid on : 17 03. 2014 17:43:03
	 * @param refReservationDao the refReservationDao to set
	 */
	public void setRefReservationDao(RefReservationDao refReservationDao) {
		this.refReservationDao = refReservationDao;
	}

	/**
	 * [RefReservationServiceImpl.mapper] Getter 
	 * @author BELBRIK Oualid on : 17 03. 2014 17:43:03
	 * @return the mapper
	 */
	public org.dozer.Mapper getMapper() {
		return mapper;
	}

	/**
	 * [RefReservationServiceImpl.mapper] Setter 
	 * @author BELBRIK Oualid on : 17 03. 2014 17:43:03
	 * @param mapper the mapper to set
	 */
	public void setMapper(org.dozer.Mapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public List<RefReservationDto> findByIdEquipement(Integer value) {
		
		List<RefReservationDto> refReservationListDto = new ArrayList<RefReservationDto>();
		try {
			List<RefReservation> refReservationList = (List<RefReservation>)refReservationDao.findByIdEquipement(value);
			if (refReservationList != null) {
				log.debug("OccupationLieu list success with size = {} ", new Object[]{refReservationList.size()});
				for (RefReservation currentRefReservation : refReservationList) {
					RefReservationDto refReservationDto = new RefReservationDto();
					mapper.map(currentRefReservation, refReservationDto);
					refReservationListDto.add(refReservationDto);
				}
				

			}
		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}
		return refReservationListDto;
		
	}
	
	public RefSituationDao getRefSituationDao() {
		return refSituationDao;
	}


	public void setRefSituationDao(RefSituationDao refSituationDao) {
		this.refSituationDao = refSituationDao;
	}
	

}
