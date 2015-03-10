package dz.gov.mesrs.sii.fve.business.service.impl.bac;

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

import dz.gov.mesrs.sii.commons.data.dao.fve.bac.PrioriteSerieBacDao;
import dz.gov.mesrs.sii.commons.data.model.fve.bac.PrioriteSerieBac;
import dz.gov.mesrs.sii.fve.business.model.dto.bac.PrioriteSerieBacDto;
import dz.gov.mesrs.sii.fve.business.service.bac.PrioriteSerieBacService;

/**
 * @author Mounir.MESSAOUDI on : 14 ao�t 2014 12:12:26
 */
@Service("prioriteSerieBacService")
public class PrioriteSerieBacServiceImpl implements PrioriteSerieBacService {

	@Autowired
	@Qualifier("prioriteSerieBacDao")
	private PrioriteSerieBacDao prioriteSerieBacDao;

	private static final Log log = LogFactory
			.getLog(PrioriteSerieBacServiceImpl.class);

	@Autowired
	@Qualifier("mapper")
	private DozerBeanMapper mapper;

	public PrioriteSerieBacServiceImpl() {

	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public PrioriteSerieBacDto insertOrUpdate(
			PrioriteSerieBacDto prioriteSerieBacDto) {

		PrioriteSerieBac prioriteSerieBac = mapper.map(prioriteSerieBacDto,
				PrioriteSerieBac.class);
		try {
			if (prioriteSerieBac.getId() == 0) {
				prioriteSerieBacDao.persist(prioriteSerieBac);
			} else {
				prioriteSerieBac = prioriteSerieBacDao.merge(prioriteSerieBac);
			}
			mapper.map(prioriteSerieBac, prioriteSerieBacDto);

			log.error("insertorupdate PrioriteSerieBac succes..");

			return prioriteSerieBacDto;

		} catch (RuntimeException e) {
			log.error("insertorupdate PrioriteSerieBac failed--", e);
			throw e;
		}
	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void remove(PrioriteSerieBacDto prioriteSerieBacDto) {
		try {

			PrioriteSerieBac prioriteSerieBac = mapper.map(prioriteSerieBacDto,
					PrioriteSerieBac.class);

			prioriteSerieBac = prioriteSerieBacDao.merge(prioriteSerieBac);

			prioriteSerieBacDao.remove(prioriteSerieBac);
		} catch (RuntimeException e) {
			throw e;
		}
	}

	@Override
	public PrioriteSerieBacDto findById(int id) {

		PrioriteSerieBac prioriteSerieBac = prioriteSerieBacDao.findById(id);

		if (prioriteSerieBac != null)
			return mapper.map(prioriteSerieBac, PrioriteSerieBacDto.class);

		return null;
	}

	@Override
	public List<PrioriteSerieBacDto> findAll() {

		List<PrioriteSerieBac> prioriteSerieBacs = prioriteSerieBacDao
				.findAll();

		List<PrioriteSerieBacDto> prioriteSerieBacDtos = new ArrayList<PrioriteSerieBacDto>();

		for (PrioriteSerieBac prioriteSerieBac : prioriteSerieBacs) {
			prioriteSerieBacDtos.add(mapper.map(prioriteSerieBac,
					PrioriteSerieBacDto.class));
		}

		return prioriteSerieBacDtos;

	}
}
