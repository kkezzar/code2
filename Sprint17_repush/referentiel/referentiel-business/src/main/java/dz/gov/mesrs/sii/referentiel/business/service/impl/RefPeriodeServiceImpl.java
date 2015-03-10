/**
 * [dz.gov.mesrs.sii.referentiel.business.service.impl.RefPeriodeServiceImpl.java] 
 * @author MAKERRI Sofiane on : 25 mars 2014  16:46:00
 */
package dz.gov.mesrs.sii.referentiel.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefPeriodeDao;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefPeriode;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefPeriodeDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefPeriodeService;
import dz.gov.mesrs.sii.referentiel.business.util.RefUtilConstant;

/**
 * @author MAKERRI Sofiane on : 25 mars 2014 16:46:00
 */
@Service("refPeriodeServiceImpl")
public class RefPeriodeServiceImpl implements RefPeriodeService {

	@Autowired
	@Qualifier("refPeriodeDaoImpl")
	private RefPeriodeDao refPeriodeDaoImpl;
	@Autowired
	@Qualifier("mapper")
	private Mapper mapper;
	private static final Logger log = LoggerFactory.getLogger(RefPeriodeServiceImpl.class.getName());

	/**
	 * [RefPeriodeServiceImpl.RefPeriodeServiceImpl()] Constructor
	 * 
	 * @author MAKERRI Sofiane on : 25 mars 2014 16:46:00
	 */
	public RefPeriodeServiceImpl() {

	}

	/**
	 * [RefPeriodeServiceImpl.refPeriodeDaoImpl] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 mars 2014 16:47:21
	 * @return the refPeriodeDaoImpl
	 */
	public RefPeriodeDao getRefPeriodeDaoImpl() {
		return refPeriodeDaoImpl;
	}

	/**
	 * [RefPeriodeServiceImpl.refPeriodeDaoImpl] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 mars 2014 16:47:21
	 * @param refPeriodeDaoImpl
	 *            the refPeriodeDaoImpl to set
	 */
	public void setRefPeriodeDaoImpl(RefPeriodeDao refPeriodeDaoImpl) {
		this.refPeriodeDaoImpl = refPeriodeDaoImpl;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.referentiel.business.service.RefPeriodeService#findAll()
	 */
	@Override
	public List<RefPeriodeDto> findAll() {
		List<RefPeriodeDto> refPeriodeDtos = new ArrayList<RefPeriodeDto>();
		try {
			List<RefPeriode> refPeriodes = refPeriodeDaoImpl.findAll();
			for (RefPeriode refPeriode : refPeriodes) {
				RefPeriodeDto refPeriodeDto = new RefPeriodeDto();
				mapper.map(refPeriode, refPeriodeDto);
				refPeriodeDtos.add(refPeriodeDto);
			}
		} catch (RuntimeException e) {
			log.error("RefPeriodeDto", e);
			throw e;
		}
		return refPeriodeDtos;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.referentiel.business.service.RefPeriodeService#findGeneric
	 * (java.lang.String)
	 */
	@Override
	public List<RefPeriodeDto> findGeneric(String value) {
		List<RefPeriodeDto> refPeriodeDtos = new ArrayList<RefPeriodeDto>();
		try {
			List<RefPeriode> refPeriodes = refPeriodeDaoImpl.findGeneric(value);
			for (RefPeriode refPeriode : refPeriodes) {
				RefPeriodeDto refPeriodeDto = new RefPeriodeDto();
				mapper.map(refPeriode, refPeriodeDto);
				refPeriodeDtos.add(refPeriodeDto);
			}
		} catch (RuntimeException e) {
			log.error("RefPeriodeDto", e);
			throw e;
		}
		return refPeriodeDtos;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.referentiel.business.service.RefPeriodeService#save(
	 * dz.gov.mesrs.sii.referentiel.business.model.dto.RefPeriodeDto)
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public RefPeriodeDto save(RefPeriodeDto refPeriodeDto) {
		RefPeriode refPeriode = new RefPeriode();
		try {
			mapper.map(refPeriodeDto, refPeriode);
			refPeriodeDaoImpl.persist(refPeriode);
			mapper.map(refPeriode, refPeriodeDto);
			log.debug("save refPeriodeDto successful");
		} catch (RuntimeException e) {
			log.error("save refPeriodeDto failed", e);
			throw e;
		}
		return refPeriodeDto;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.referentiel.business.service.RefPeriodeService#update
	 * (dz.gov.mesrs.sii.referentiel.business.model.dto.RefPeriodeDto)
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void update(RefPeriodeDto refPeriodeDto) {
		RefPeriode refPeriode = new RefPeriode();
		try {
			mapper.map(refPeriodeDto, refPeriode);
			refPeriodeDaoImpl.merge(refPeriode);
			log.debug("save refPeriodeDto successful");
		} catch (RuntimeException e) {
			log.error("save refPeriodeDto failed", e);
			throw e;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.referentiel.business.service.RefPeriodeService#findById
	 * (java.lang.Integer)
	 */
	@Override
	public RefPeriodeDto findById(Integer id) {
		try {
			RefPeriode refPeriode = refPeriodeDaoImpl.findById(id);
			if (refPeriode != null) {
				log.debug(" findById success with id = {} ", new Object[]{ id});
				RefPeriodeDto refPeriodeDto = new RefPeriodeDto();
				mapper.map(refPeriode, refPeriodeDto);
				return refPeriodeDto;
			}

		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.referentiel.business.service.RefPeriodeService#delete
	 * (dz.gov.mesrs.sii.referentiel.business.model.dto.RefPeriodeDto)
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void delete(RefPeriodeDto refPeriodeDto) {
		try {

			RefPeriode refPeriode = mapper.map(refPeriodeDto, RefPeriode.class);
			refPeriode = refPeriodeDaoImpl.merge(refPeriode);
			refPeriodeDaoImpl.remove(refPeriode);
			log.debug("remove refPeriode successful");
		} catch (RuntimeException e) {
			log.error("remove refPeriode failed", e);
			throw e;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.referentiel.business.service.RefPeriodeService#saveOrUpdate
	 * (dz.gov.mesrs.sii.referentiel.business.model.dto.RefPeriodeDto)
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void saveOrUpdate(RefPeriodeDto refPeriodeDto) {
		// TODO Auto-generated method stub

	}

	@Override
	public RefPeriodeDto findByIdentifiant(String identifiant) {
		try {
			RefPeriode refPeriode = refPeriodeDaoImpl
					.findByIdentifiant(identifiant);
			if (refPeriode != null) {
				log.debug(" findById success with id = {} ", new Object[]{ identifiant});
				RefPeriodeDto refPeriodeDto = new RefPeriodeDto();
				mapper.map(refPeriode, refPeriodeDto);
				return refPeriodeDto;
			}

		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}

		return null;
	}

	@Override
	public RefPeriodeDto findByName(String name) {
		try {
			RefPeriode refPeriode = refPeriodeDaoImpl.findByName(name);
			if (refPeriode != null) {
				log.debug(" findByName success with id =  {}", new Object[] {name});
				RefPeriodeDto refPeriodeDto = new RefPeriodeDto();
				mapper.map(refPeriode, refPeriodeDto);
				return refPeriodeDto;
			}

		} catch (RuntimeException e) {
			log.error("findByName failed", e);
			throw e;
		}

		return null;
	}

	@Override
	public String generateIdentify(String prefix, int orderLength) {
		try {
			if (prefix == null) {
				return null;
			}

			Integer lastOrder = refPeriodeDaoImpl.findLastOrder(prefix,
					orderLength);
			if (lastOrder == null) {
				return null;
			}
			lastOrder++;
			prefix = prefix
					+ RefUtilConstant.formatOrder(lastOrder.toString(),
							orderLength);
			log.debug("calculateIdentify successful");
		} catch (RuntimeException e) {
			log.error("calculateIdentify failed", e);
			throw e;
		}
		return prefix;
	}

}
