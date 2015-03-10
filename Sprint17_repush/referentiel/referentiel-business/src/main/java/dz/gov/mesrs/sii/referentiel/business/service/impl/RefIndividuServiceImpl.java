/**
 * 
 */
package dz.gov.mesrs.sii.referentiel.business.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefIndividuDao;
import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefSituationDao;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefEntiteSituation;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefIndividu;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefSituation;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefIndividuDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefIndividuService;
import dz.gov.mesrs.sii.referentiel.business.service.RefParametrageService;
import dz.gov.mesrs.sii.referentiel.business.util.RefUtilConstant;

/**
 * @author obelbrik
 * 
 */
@Service("refIndividuServiceImpl")
public class RefIndividuServiceImpl implements RefIndividuService {
    private static final Log log = LogFactory.getLog(RefIndividuServiceImpl.class);
    @Autowired
    @Qualifier("refIndividuDaoImpl")
    private RefIndividuDao refIndividuDao;
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
     * 
     * @author BELDI Jamel on : 7 avr. 2014 14:59:45
     * @return the refParametrageServiceImpl
     */
    public RefParametrageService getRefParametrageServiceImpl() {
	return refParametrageServiceImpl;
    }

    /**
     * [RefEvenementServiceImpl.refParametrageServiceImpl] Setter
     * 
     * @author BELDI Jamel on : 7 avr. 2014 14:59:45
     * @param refParametrageServiceImpl
     *            the refParametrageServiceImpl to set
     */
    public void setRefParametrageServiceImpl(RefParametrageService refParametrageServiceImpl) {
	this.refParametrageServiceImpl = refParametrageServiceImpl;
    }

    /**
     * @return the refIndividuDao
     */
    public RefIndividuDao getRefIndividuDao() {
	return refIndividuDao;
    }

    /**
     * @param refIndividuDao
     *            the refIndividuDao to set
     */
    public void setRefIndividuDao(RefIndividuDao refIndividuDao) {
	this.refIndividuDao = refIndividuDao;
    }

    /**
     * @return the refSituationDao
     */
    public RefSituationDao getRefSituationDao() {
	return refSituationDao;
    }

    /**
     * @param refSituationDao
     *            the refSituationDao to set
     */
    public void setRefSituationDao(RefSituationDao refSituationDao) {
	this.refSituationDao = refSituationDao;
    }

    /**
	 * 
	 */
    public RefIndividuServiceImpl() {
    }

    @Override
    public RefIndividuDto findByIdentifiant(String id) {
	RefIndividuDto refIndividuDto = null;
	try {
	    RefIndividu refIndividu = refIndividuDao.findByIdentifiant(id);
	    if (refIndividu != null) {
		refIndividuDto = new RefIndividuDto();
		mapper.map(refIndividu, refIndividuDto);
		log.info("success get dto: " + refIndividuDto.getNomLatin());
		return refIndividuDto;
	    }
	} catch (Exception e) {
	    log.error("get failed", e);

	}
	return refIndividuDto;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * dz.gov.mesrs.sii.referentiel.business.service.RefIndividuService#findBAll
     * ()
     */
    @Override
    public List<RefIndividuDto> findAll() {
	List<RefIndividuDto> refIndividuListDto = new ArrayList<RefIndividuDto>();
	try {
	    List<RefIndividu> refIndividuList = refIndividuDao.findAll();
	    if (refIndividuList != null) {
		log.debug("individu list success with size =  " + refIndividuList.size());
		for (RefIndividu currentRefIndividu : refIndividuList) {
		    RefIndividuDto refIndividuDto = new RefIndividuDto();
		    mapper.map(currentRefIndividu, refIndividuDto);
		    refIndividuListDto.add(refIndividuDto);
		}
		return refIndividuListDto;
	    }
	} catch (RuntimeException e) {
	    log.error("get individu list failed", e);
	    throw e;
	}

	return null;
    }

    /**
     * [RefIndividuServiceImpl.mapper] Getter
     * 
     * @author MAKERRI Sofiane on : 6 janv. 2014 17:04:29
     * @return the mapper
     */
    public org.dozer.Mapper getMapper() {
	return mapper;
    }

    /**
     * [RefIndividuServiceImpl.mapper] Setter
     * 
     * @author MAKERRI Sofiane on : 6 janv. 2014 17:04:29
     * @param mapper
     *            the mapper to set
     */
    public void setMapper(org.dozer.Mapper mapper) {
	this.mapper = mapper;
    }

    @Override
    public List<RefIndividuDto> findGeneric(String value) {
	List<RefIndividuDto> refIndividuListDto = new ArrayList<RefIndividuDto>();
	try {
	    List<RefIndividu> refIndividuList = refIndividuDao.findGeneric(value);
	    if (refIndividuList != null) {
		log.debug("contrat list success with size =  " + refIndividuList.size());

		for (RefIndividu currentRefIndividu : refIndividuList) {
		    RefSituation refCurrentSituation = refSituationDao.findCurrentSituation(currentRefIndividu.getId(),
			    RefIndividu.class.getSimpleName());
		    RefIndividuDto refIndividuDto = new RefIndividuDto();
		    mapper.map(currentRefIndividu, refIndividuDto);
		    if (refCurrentSituation != null) {
			mapper.map(refCurrentSituation, refIndividuDto);
		    }
		    refIndividuListDto.add(refIndividuDto);
		}

	    }
	} catch (RuntimeException e) {
	    log.error("get failed", e);
	    throw e;
	}
	return refIndividuListDto;

    }

    @Override
    public List<RefIndividuDto> findAdvanced(RefIndividuDto refIndividuDto) {

	List<RefIndividuDto> refIndividuListDto = new ArrayList<RefIndividuDto>();
	try {
	    RefIndividu refIndividu = new RefIndividu();
	    mapper.map(refIndividuDto, refIndividu);
	    List<RefIndividu> refIndividuList = refIndividuDao.findAdvanced(refIndividu);
	    if (refIndividuList != null && !refIndividuList.isEmpty()) {
		log.debug("contrat list success with size =  " + refIndividuList.size());

		for (RefIndividu currentRefIndividu : refIndividuList) {
		    RefIndividuDto currentRefIndividuDto = new RefIndividuDto();
		    RefSituation refCurrentSituation = refSituationDao.findCurrentSituation(currentRefIndividu.getId(),
			    RefIndividu.class.getSimpleName());
		    mapper.map(currentRefIndividu, currentRefIndividuDto);
		    if (refCurrentSituation != null) {
			mapper.map(refCurrentSituation, currentRefIndividuDto);
		    }
		    refIndividuListDto.add(currentRefIndividuDto);
		}

	    }
	} catch (RuntimeException e) {
	    log.error("get failed", e);
	    throw e;
	}
	return refIndividuListDto;
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
    public RefIndividuDto save(RefIndividuDto refIndividuDto) {
	RefIndividu refIndividu = new RefIndividu();
	mapper.map(refIndividuDto, refIndividu);
	try {
	    RefSituation refSituation = new RefSituation();
	    refIndividuDao.persist(refIndividu);
	    refSituation.setDateSituation(new Date());
	    refSituation.setIdEntite(refIndividu.getId());
	    refSituation.setNomEntite(RefIndividu.class.getSimpleName());
	    RefEntiteSituation refEntiteSituation = new RefEntiteSituation();
	    refEntiteSituation.setId(1/*Integer.parseInt(refParametrageServiceImpl.getParamConfiguration(
		    UtilConstant.INDIVIDU_SITUATION_CREE_ID_KEY, UtilConstant.CONFIGURATION_UTIL).getValue())*/);
	    refSituation.setRefEntiteSituation(refEntiteSituation);
	    refSituationDao.persist(refSituation);
	    mapper.map(refIndividu, refIndividuDto);
	    log.debug("save successful");
	} catch (RuntimeException e) {
	    log.error("save failed", e);
	    throw e;
	}
	return refIndividuDto;
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
    public void update(RefIndividuDto refIndividuDto) {
	RefIndividu refIndividu = new RefIndividu();
	mapper.map(refIndividuDto, refIndividu);
	try {
	    refIndividuDao.merge(refIndividu);
	    log.debug("update successful");
	} catch (RuntimeException e) {
	    log.error("update failed", e);
	    throw e;
	}

    }

    @Override
    public void saveOrUpdate(RefIndividuDto refIndividuDto) {
	// TODO Auto-generated method stub

    }

    @Override
    public void delete(String id) {
	// TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see dz.gov.mesrs.sii.referentiel.business.service.RefIndividuService#
     * findByRefIndividuDto
     * (dz.gov.mesrs.sii.referentiel.business.model.dto.RefIndividuDto)
     */
    @Override
    public RefIndividuDto findByRefIndividuDto(RefIndividuDto refIndividuDto) {

	try {
	    RefIndividu refIndividu = new RefIndividu();
	    mapper.map(refIndividuDto, refIndividu);
	    refIndividu = refIndividuDao.findByRefIndividu(refIndividu);
	    if (refIndividu != null) {
		log.debug("individu found successfuly");
		mapper.map(refIndividu, refIndividuDto);
		return refIndividuDto;

	    }
	} catch (RuntimeException e) {
	    log.error("findByRefIndividuDto failed", e);
	    throw e;
	}
	return null;
    }

    @Override
    public List<RefIndividuDto> findGeneric(String value, int max) {
	List<RefIndividuDto> refIndividuListDto = new ArrayList<RefIndividuDto>();
	try {
	    List<RefIndividu> refIndividuList = refIndividuDao.findGeneric(value, max);
	    if (refIndividuList != null) {
		log.debug("contrat list success with size =  " + refIndividuList.size());

		for (RefIndividu currentRefIndividu : refIndividuList) {
		    /*
		     * RefSituation refCurrentSituation = refSituationDao
		     * .findCurrentSituation(
		     * currentRefIndividu.getIdentifiant(),
		     * RefIndividu.class.getSimpleName());
		     */
		    RefIndividuDto refIndividuDto = new RefIndividuDto();
		    mapper.map(currentRefIndividu, refIndividuDto);
		    /*
		     * if (refCurrentSituation != null) {
		     * mapper.map(refCurrentSituation, refIndividuDto); }
		     */
		    refIndividuListDto.add(refIndividuDto);
		}

	    }
	} catch (RuntimeException e) {
	    log.error("get failed", e);
	    throw e;
	}
	return refIndividuListDto;
    }

    @Override
    public List<RefIndividuDto> findAdvanced(RefIndividuDto refIndividuDto, int max) {
	List<RefIndividuDto> refIndividuListDto = new ArrayList<RefIndividuDto>();
	try {
	    RefIndividu refIndividu = new RefIndividu();
	    mapper.map(refIndividuDto, refIndividu);
	    List<RefIndividu> refIndividuList = refIndividuDao.findAdvanced(refIndividu, max);
	    if (refIndividuList != null && !refIndividuList.isEmpty()) {
		log.debug("contrat list success with size =  " + refIndividuList.size());

		for (RefIndividu currentRefIndividu : refIndividuList) {
		    RefIndividuDto currentRefIndividuDto = new RefIndividuDto();
		    /*
		     * RefSituation refCurrentSituation = refSituationDao
		     * .findCurrentSituation(
		     * currentRefIndividu.getIdentifiant(),
		     * RefIndividu.class.getSimpleName());
		     */
		    mapper.map(currentRefIndividu, currentRefIndividuDto);
		    /*
		     * if (refCurrentSituation != null) {
		     * mapper.map(refCurrentSituation, currentRefIndividuDto); }
		     */
		    refIndividuListDto.add(currentRefIndividuDto);
		}

	    }
	} catch (RuntimeException e) {
	    log.error("get failed", e);
	    throw e;
	}
	return refIndividuListDto;
    }

    @Override
    public List<RefIndividuDto> findByNames(String name, String surname) {
	List<RefIndividuDto> refIndividuListDto = new ArrayList<RefIndividuDto>();
	try {
	    List<RefIndividu> refIndividuList = refIndividuDao.findByNames(name, surname);
	    if (refIndividuList != null) {
		log.debug("contrat list success with size =  " + refIndividuList.size());

		for (RefIndividu currentRefIndividu : refIndividuList) {
		    RefIndividuDto refIndividuDto = new RefIndividuDto();
		    mapper.map(currentRefIndividu, refIndividuDto);
		    refIndividuListDto.add(refIndividuDto);
		}

	    }
	} catch (RuntimeException e) {
	    log.error("get failed", e);
	    throw e;
	}
	return refIndividuListDto;
    }

    @Override
    public List<RefIndividuDto> findByNames(String name, String surname, int max) {
	List<RefIndividuDto> refIndividuListDto = new ArrayList<RefIndividuDto>();
	try {
	    List<RefIndividu> refIndividuList = refIndividuDao.findByNames(name, surname, max);
	    if (refIndividuList != null) {
		log.debug("contrat list success with size =  " + refIndividuList.size());
		for (RefIndividu currentRefIndividu : refIndividuList) {
		    RefIndividuDto refIndividuDto = new RefIndividuDto();
		    mapper.map(currentRefIndividu, refIndividuDto);
		    refIndividuListDto.add(refIndividuDto);
		}

	    }
	} catch (RuntimeException e) {
	    log.error("get failed", e);
	    throw e;
	}
	return refIndividuListDto;
    }

    @Override
    public RefIndividuDto findById(Integer id) {
	RefIndividuDto refIndividuDto = null;
	try {
	    if (id == null) {
		return null;
	    }
	    RefIndividu refIndividu = refIndividuDao.findById(id);
	    if (refIndividu != null) {
		refIndividuDto = new RefIndividuDto();
		mapper.map(refIndividu, refIndividuDto);
		log.info("success get dto: " + refIndividuDto.getNomLatin());
		return refIndividuDto;
	    }
	} catch (Exception e) {
	    log.error("get failed", e);

	}
	return refIndividuDto;
    }

    public boolean checkIfExist(RefIndividuDto refIndividuDto) {

	RefIndividu refIndividu = new RefIndividu();
	mapper.map(refIndividu, refIndividuDto);
	return refIndividuDao.checkIfExist(refIndividu);
    }

    @Override
    public List<RefIndividuDto> saveIndividus(List<RefIndividuDto> refIndividuDtos) {

	log.debug("start saveIndividus" + refIndividuDtos);
	List<RefIndividuDto> result = new ArrayList<RefIndividuDto>();
	try {
	    long index = 0;
	    for (RefIndividuDto refIndividuDto : refIndividuDtos) {
		// Test si l individu existe deja
		index++;
		if (refIndividuDao.findByIdentifiant(refIndividuDto.getIdentifiant()) == null) {

		    RefIndividu refIndividu = new RefIndividu();
		    mapper.map(refIndividuDto, refIndividu);
		    refIndividuDao.persist(refIndividu);
		    if (refIndividu instanceof RefIndividu)
			result.add(refIndividuDto);

		    // System.out.println("Generation Individu : "
		    // + String.valueOf(index) + " [CREE] " +
		    // refIndividuDto.getIdentifiant() + " - " +
		    // refIndividuDto.getNomLatin() + "  " +
		    // refIndividuDto.getPrenomLatin() + "\t\t");
		    // log.debug("save Individu successful : " +
		    // refIndividuDto.getIdentifiant() + " - " +
		    // refIndividuDto.getNomLatin() + "  " +
		    // refIndividuDto.getPrenomLatin());
		} else {
		    // System.out.println("Generation Individu  : "
		    // + String.valueOf(index) + " [EXISTANT] " +
		    // refIndividuDto.getIdentifiant() + " - " +
		    // refIndividuDto.getNomLatin() + "  " +
		    // refIndividuDto.getPrenomLatin() + "\t\t");
		    // log.debug(" Individu Existant : : " +
		    // refIndividuDto.getIdentifiant() + " - " +
		    // refIndividuDto.getNomLatin() + "  " +
		    // refIndividuDto.getPrenomLatin());
		}
	    }
	    log.debug("saveIndividus successful");
	    return result;

	} catch (RuntimeException e) {

	    log.error("saveIndividus failed", e);
	    throw e;
	}
    }

    @Override
    public List<RefIndividuDto> findByExample(RefIndividuDto dto, String sortField, Integer pageSize, Integer first, Boolean descending) {
	RefIndividu example = mapper.map(dto, RefIndividu.class);
	List<RefIndividu> entities = refIndividuDao.findByExample(example, sortField ,pageSize, first, descending);
	List<RefIndividuDto> results = null;
	if (entities != null) {
	    results = new ArrayList<RefIndividuDto>();
	    for (RefIndividu entity : entities) {
		results.add(mapper.map(entity, RefIndividuDto.class));
	    }
	}
	return results;
    }

    @Override
    public int countByExample(RefIndividuDto dto) {
	RefIndividu individu = mapper.map(dto, RefIndividu.class);
	return refIndividuDao.countByExample(individu);
    }

	@Override
	public List<RefIndividuDto> findByNameOrSurname(String query) {
		List<RefIndividuDto> refIndividuListDto = new ArrayList<RefIndividuDto>();
		try {
		    List<RefIndividu> refIndividuList = refIndividuDao.findByNameOrSurname(query);
		    if (refIndividuList != null) {
			log.debug("contrat list success with size =  " + refIndividuList.size());

			for (RefIndividu currentRefIndividu : refIndividuList) {
			    RefIndividuDto refIndividuDto = new RefIndividuDto();
			    mapper.map(currentRefIndividu, refIndividuDto);
			    refIndividuListDto.add(refIndividuDto);
			}

		    }
		} catch (RuntimeException e) {
		    log.error("get failed", e);
		    throw e;
		}
		return refIndividuListDto;
	}

}
