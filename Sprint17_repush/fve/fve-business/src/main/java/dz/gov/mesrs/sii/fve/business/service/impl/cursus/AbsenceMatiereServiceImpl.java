package dz.gov.mesrs.sii.fve.business.service.impl.cursus;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.fve.cursus.AbsenceMatiereDao;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.AbsenceMatiere;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.AbsenceMatiereDto;
import dz.gov.mesrs.sii.fve.business.service.cursus.AbsenceMatiereService;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 
@Service("absenceMatiereService")
public class AbsenceMatiereServiceImpl implements AbsenceMatiereService  {

	private static final Log log = LogFactory
			.getLog(AbsenceMatiereServiceImpl.class);
	
	@Autowired
	@Qualifier ("absenceMatiereDao")
	private AbsenceMatiereDao absenceMatiereDao;


    @Autowired
	@Qualifier("mapper")
	private DozerBeanMapper mapper;
	
	
	
	public AbsenceMatiereServiceImpl(){

	}

	
	@Override
	public AbsenceMatiereDto  insertOrUpdate(
			 AbsenceMatiereDto  absenceMatiereDto) {
		try {
			 AbsenceMatiere  absenceMatiere = mapper.map( absenceMatiereDto, AbsenceMatiere.class);

			if ( absenceMatiere.getId() == 0) {
				absenceMatiereDao.persist(absenceMatiere);
			} else {
						
				absenceMatiere = absenceMatiereDao.merge(absenceMatiere);
			}
			mapper.map(absenceMatiere, absenceMatiereDto);
			log.info("insertOrUpdate success");
			return absenceMatiereDto;
		} catch (RuntimeException e) {
			log.error("insertOrUpdate failed", e);
			throw e;

		}
	}

	@Override
	public List<AbsenceMatiereDto> findByQuery(String query) {
	
	    List<AbsenceMatiere> absenceMatieres = absenceMatiereDao.findByQuery(query);

		List<AbsenceMatiereDto> absenceMatiereDtos = new ArrayList<AbsenceMatiereDto>();

		for (AbsenceMatiere absenceMatiere : absenceMatieres) {
			absenceMatiereDtos.add(mapper.map(absenceMatiere, AbsenceMatiereDto.class));
		}

		return absenceMatiereDtos;
	
	}
	
	
	@Override
	public void remove(AbsenceMatiereDto absenceMatiereDto) {

		AbsenceMatiere absenceMatiere = mapper.map(absenceMatiereDto,
				AbsenceMatiere.class);

		absenceMatiereDao.remove(absenceMatiere);
	}



	@Override
	public AbsenceMatiereDto findById(int id) {

		AbsenceMatiere absenceMatiere = absenceMatiereDao.findById(id);

		if (absenceMatiere != null)
			return mapper.map(absenceMatiere, AbsenceMatiereDto.class);

		return null;
	}

	@Override
	public List<AbsenceMatiereDto> findAll() {
		try {
			List<AbsenceMatiereDto> result = new ArrayList<AbsenceMatiereDto>();

			List<AbsenceMatiere> resultListDao = absenceMatiereDao.findAll();
			if (resultListDao != null && !resultListDao.isEmpty()) {
				for (AbsenceMatiere absenceMatiere : resultListDao) {
					AbsenceMatiereDto absenceMatiereDto = new AbsenceMatiereDto();
					mapper.map(absenceMatiere, absenceMatiereDto);
					result.add(absenceMatiereDto);
				}
				return result;
			}

		} catch (RuntimeException e) {
			log.error("findAll failed", e);
			throw e;

		}

		return null;
	}

	@Override
	public List<AbsenceMatiereDto> findAdvanced(AbsenceMatiereDto absenceMatiereSearchDto) {
		try {
			List<AbsenceMatiereDto> result = new ArrayList<AbsenceMatiereDto>();
			AbsenceMatiere searchBo = new AbsenceMatiere();
			mapper.map(absenceMatiereSearchDto, searchBo);
			List<AbsenceMatiere> resultListDao = absenceMatiereDao
					.findAdvanced(searchBo);
			if (resultListDao != null && !resultListDao.isEmpty()) {
				for (AbsenceMatiere absenceMatiere : resultListDao) {
					AbsenceMatiereDto absenceMatiereDto = new AbsenceMatiereDto();
					mapper.map(absenceMatiere, absenceMatiereDto);
					result.add(absenceMatiereDto);
				}
				return result;
			}

		} catch (RuntimeException e) {
			log.error("findAdvanced failed", e);
			throw e;

		}

		return null;

	}
	
	
	@Override
	public AbsenceMatiereDto findByIdDossier(int idDossier) {
		
		AbsenceMatiere absenceMatiere = absenceMatiereDao
				.findByIdDossier(idDossier);

		if (absenceMatiere != null)
			return mapper.map(absenceMatiere, AbsenceMatiereDto.class);

		return null;
	}

	
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void delete(AbsenceMatiereDto absenceMatiereDto) {
		AbsenceMatiere absenceMatiere = new AbsenceMatiere();
		try {
			mapper.map(absenceMatiereDto, absenceMatiere);
			absenceMatiere = absenceMatiereDao.merge(absenceMatiere);
			absenceMatiereDao.remove(absenceMatiere);
			log.debug("remove Absence successful");
		} catch (RuntimeException e) {
			log.error("remove failed", e);
			throw e;
		}

	}
}



