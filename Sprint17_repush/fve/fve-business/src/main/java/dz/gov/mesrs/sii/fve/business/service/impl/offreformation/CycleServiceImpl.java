/**
 * [dz.gov.mesrs.sii.fve.business.service.impl.offreformation.CycleServiceImpl.java] 
 * @author rlaib on : 16 juil. 2014  10:54:44
 */
package dz.gov.mesrs.sii.fve.business.service.impl.offreformation;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.fve.offreformation.CycleDao;
import dz.gov.mesrs.sii.commons.data.dao.fve.offreformation.CycleDiplomeDao;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.Cycle;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.CycleDiplome;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.CycleDiplomeDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.CycleDto;
import dz.gov.mesrs.sii.fve.business.service.offreformation.CycleService;

/**
 * @author rlaib on : 16 juil. 2014 10:54:44
 */
@Service("cycleService")
public class CycleServiceImpl implements CycleService {

	@Autowired
	@Qualifier("cycleDao")
	private CycleDao cycleDao;

	@Autowired
	@Qualifier("cycleDiplomeDao")
	private CycleDiplomeDao cycleDiplomeDao;

	@Autowired
	@Qualifier("mapper")
	private DozerBeanMapper mapper;

	/**
	 * [CycleServiceImpl.insertOrUpdate] Method
	 * 
	 * @author rlaib on : 16 juil. 2014 10:54:44
	 * @param cycleDto
	 * @return
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public CycleDto insertOrUpdate(CycleDto cycleDto) {

		Cycle cycle = mapper.map(cycleDto, Cycle.class);
		if (cycle.getId() == 0)
			cycleDao.persist(cycle);
		else
			cycle = cycleDao.merge(cycle);

		mapper.map(cycle, cycleDto);
		return cycleDto;

	}

	/**
	 * [CycleServiceImpl.remove] Method
	 * 
	 * @author rlaib on : 16 juil. 2014 10:54:44
	 * @param cycleDto
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void remove(int idCycle) {

		cycleDao.remove(idCycle);

	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void removeCycleDiplome(int idCycleDiplome) {
		
		cycleDiplomeDao.remove(idCycleDiplome);
		
	}

	/**
	 * [CycleServiceImpl.findById] Method
	 * 
	 * @author rlaib on : 16 juil. 2014 10:54:44
	 * @param id
	 * @return
	 */
	@Override
	public CycleDto findById(int id) {
		Cycle cycle = cycleDao.findById(id);

		if (cycle != null) {
			CycleDto cycleDto = mapper.map(cycle, CycleDto.class);
			cycleDto.setNombreCycles(cycle.getNiveaux().size());
			return cycleDto;
		}

		return null;
	}

	@Override
	public CycleDto findByCode(String codeCycle) {
		Cycle cycle = cycleDao.findByCode(codeCycle);

		if (cycle != null) {
			CycleDto cycleDto = mapper.map(cycle, CycleDto.class);
			cycleDto.setNombreCycles(cycle.getNiveaux().size());
			return cycleDto;
		}

		return null;
	}

	/**
	 * [CycleServiceImpl.findAll] Method
	 * 
	 * @author rlaib on : 16 juil. 2014 10:54:44
	 * @return
	 */
	@Override
	public List<CycleDto> findAll() {

		List<Cycle> cycles = cycleDao.findAll();
		List<CycleDto> cycleDtos = new ArrayList<CycleDto>();
		for (Cycle cycle : cycles) {
			CycleDto cycleDto = new CycleDto();
			cycleDto = mapper.map(cycle, CycleDto.class);
			cycleDto.setNombreCycles(cycle.getNiveaux().size());
			cycleDtos.add(cycleDto);
		}

		return cycleDtos;
	}

	/**
	 * [CycleServiceImpl.findByTypeFormationCode] Method
	 * 
	 * @author rlaib on : 16 juil. 2014 14:22:07
	 * @param codeTypeFormation
	 * @return
	 */
	@Override
	public List<CycleDto> findByTypeFormationCode(String codeTypeFormation) {

		List<Cycle> cycles = cycleDao
				.findByTypeFormationCode(codeTypeFormation);
		List<CycleDto> cycleDtos = new ArrayList<CycleDto>();
		for (Cycle cycle : cycles) {
			CycleDto cycleDto = new CycleDto();
			cycleDto = mapper.map(cycle, CycleDto.class);
			cycleDto.setNombreCycles(cycle.getNiveaux().size());
			cycleDtos.add(cycleDto);
		}
		return cycleDtos;
	}

	@Override
	public CycleDto findByTypeFormationByCode(String codeTypeFormation,
			String codeCycle) {

		Cycle cycle = cycleDao.findByTypeFormationByCode(codeTypeFormation,
				codeCycle);
		if (cycle != null) {
			CycleDto cycleDto = mapper.map(cycle, CycleDto.class);
			cycleDto.setNombreCycles(cycle.getNiveaux().size());
			return cycleDto;
		}
		return null;
	}

	/**
	 * [CycleService.findDiplomesByCycle] Method 
	 * @author rlaib  on : 24 nov. 2014  14:20:44
	 * @param idCycle
	 * @return
	 */
	@Override
	public List<CycleDiplomeDto> findDiplomesByCycle(Integer idCycle) {
		
		List<CycleDiplome> cycleDiplomes = cycleDiplomeDao.findDiplomesByCycle(idCycle);
				
		List<CycleDiplomeDto> cycleDiplomeDtos = new ArrayList<CycleDiplomeDto>();
		for (CycleDiplome cycleDiplome : cycleDiplomes) {
			CycleDiplomeDto cycleDiplomeDto = new CycleDiplomeDto();
			cycleDiplomeDto = mapper.map(cycleDiplome, CycleDiplomeDto.class);
			cycleDiplomeDtos.add(cycleDiplomeDto);
		}
		return cycleDiplomeDtos;
	}

	/**
	 * [CycleService.insertOrUpdate] Method 
	 * @author rlaib  on : 24 nov. 2014  14:20:44
	 * @param cycleDiplomeDto
	 * @return
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public CycleDiplomeDto insertOrUpdate(CycleDiplomeDto cycleDiplomeDto) {
		
		CycleDiplome cycleDiplome = mapper.map(cycleDiplomeDto, CycleDiplome.class);
		if (cycleDiplome.getId() == 0)
			cycleDiplomeDao.persist(cycleDiplome);
		else
			cycleDiplome = cycleDiplomeDao.merge(cycleDiplome);

		mapper.map(cycleDiplome, cycleDiplomeDto);
		return cycleDiplomeDto;
	}

	/* (non-Javadoc)
	 * @see dz.gov.mesrs.sii.fve.business.service.offreformation.CycleService#findCycleDiplomeById(int)
	 */
	@Override
	public CycleDiplomeDto findCycleDiplomeById(int id) {
		
		CycleDiplome cycleDiplome = cycleDiplomeDao.findById(id);

		if (cycleDiplome != null) {
			CycleDiplomeDto cycleDiplomeDto = mapper.map(cycleDiplome, CycleDiplomeDto.class);
			return cycleDiplomeDto;
		}

		return null;
	}
}
