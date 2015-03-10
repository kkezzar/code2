/**
 * [dz.gov.mesrs.sii.recherche.business.service.impl.RechercheProgrammeServiceImpl.java] 
 * @author rlaib on : 25 janv. 2015  12:12:22
 */
package dz.gov.mesrs.sii.recherche.business.service.impl;

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

import dz.gov.mesrs.sii.commons.data.dao.recherche.AxeDao;
import dz.gov.mesrs.sii.commons.data.dao.recherche.ProgrammeDao;
import dz.gov.mesrs.sii.commons.data.model.recherche.Axe;
import dz.gov.mesrs.sii.commons.data.model.recherche.Programme;
import dz.gov.mesrs.sii.recherche.business.model.dto.AxeDto;
import dz.gov.mesrs.sii.recherche.business.model.dto.ProgrammeDto;
import dz.gov.mesrs.sii.recherche.business.service.RechercheProgrammeService;

/**
 * @author rlaib  on : 25 janv. 2015  12:12:22
 */
@Service("rechercheProgrammeService")
public class RechercheProgrammeServiceImpl implements RechercheProgrammeService {

	@Autowired
	@Qualifier ("programmeDao")
	private ProgrammeDao programmeDao;

	@Autowired
	@Qualifier ("axeDao")
	private AxeDao axeDao;
	
	@Autowired
	@Qualifier("mapper")
	private DozerBeanMapper mapper;
	  
	private static final Log log = LogFactory.getLog(RechercheProgrammeServiceImpl.class);
	
	/**
	 * [RechercheProgrammeServiceImpl.insertOrUpdate] Method 
	 * Overridden By : @author rlaib  on : 25 janv. 2015  12:12:22
	 * @param programmeDto
	 * @return
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public ProgrammeDto insertOrUpdate(ProgrammeDto programmeDto) {

		Programme programme = mapper.map(programmeDto,
				Programme.class);
		try {
					if (programme.getId() == null){
						programmeDao.persist(programme);
					}else{
						programme = programmeDao.merge(programme);
					}
					mapper.map(programme, programmeDto);
					return programmeDto;
		
		} catch (RuntimeException re) {
					log.error("insertOrUpdate ProgrammeDto failed : ", re);
					throw re;
		}
	}

	/**
	 * [RechercheProgrammeServiceImpl.remove] Method 
	 * Overridden By : @author rlaib  on : 25 janv. 2015  12:12:22
	 * @param id
	 */
	@Override
	@Transactional(value = "transactionManager", propagation =	Propagation.REQUIRED)
	public void remove(Long id) {

		programmeDao.remove(id);
	}

	/**
	 * [RechercheProgrammeServiceImpl.findById] Method 
	 * Overridden By : @author rlaib  on : 25 janv. 2015  12:12:22
	 * @param id
	 * @return
	 */
	@Override
	public ProgrammeDto findById(Long id) {
	
		try {
			Programme programme = programmeDao.findById(id);
			if (programme != null) {
				ProgrammeDto programmeDto = new ProgrammeDto();
					mapper.map(programme, programmeDto);
					return programmeDto;
			}
			return null;

		} catch (RuntimeException re) {
			log.error("findById programmeDto failed : ", re);
			throw re;
		}
	}

	/**
	 * [RechercheProgrammeServiceImpl.findByKeyWords] Method 
	 * Overridden By : @author rlaib  on : 25 janv. 2015  12:12:22
	 * @param keyWord
	 * @return
	 */
	@Override
	public List<ProgrammeDto> findByKeyWords(String keyWord) {
		
		try {
			List<Programme> programmes= programmeDao.findByKeyWords(keyWord);
			List<ProgrammeDto> programmeDtos = new ArrayList<ProgrammeDto>();
			for (Programme programme: programmes) {
				ProgrammeDto programmeDto = new ProgrammeDto();
				mapper.map(programme, programmeDto);
				programmeDtos.add(programmeDto);
			}
			return programmeDtos;
		} catch (RuntimeException re) {
			log.error("findByKeyWords ProgrammeDto", re);
			throw re;
		}
	}

	/**
	 * [RechercheProgrammeService.saveOneAxe] Method 
	 * Overridden By : @author rlaib  on : 27 janv. 2015  14:04:24
	 * @param axeDto
	 * @return
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public AxeDto saveOneAxe(AxeDto axeDto) {
		
		Axe axe = mapper.map(axeDto,
				Axe.class);
		try {
					if (axe.getId() == null){
						axeDao.persist(axe);
					}else{
						axe = axeDao.merge(axe);
					}
					mapper.map(axe, axeDto);
					return axeDto;
		
		} catch (RuntimeException re) {
					log.error("saveOneAxe axeDto failed : ", re);
					throw re;
		}
	}

	/**
	 * [RechercheProgrammeService.removeOneAxe] Method 
	 * Overridden By : @author rlaib  on : 27 janv. 2015  14:04:24
	 * @param id
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void removeOneAxe(Long id) {
		axeDao.remove(id);
		
	}

	/**
	 * [RechercheProgrammeService.findAxesOfProgram] Method 
	 * Overridden By : @author rlaib  on : 27 janv. 2015  14:04:24
	 * @param idProgramme
	 * @return
	 */
	@Override
	public List<AxeDto> findAxesOfProgram(Long idProgramme) {
		
		try {
			List<Axe> axes= axeDao.findByIdProgramme(idProgramme);
			List<AxeDto> axeDtos = new ArrayList<AxeDto>();
			for (Axe axe: axes) {
				AxeDto axeDto = new AxeDto();
				mapper.map(axe, axeDto);
				axeDtos.add(axeDto);
			}
			return axeDtos;
		} catch (RuntimeException re) {
			log.error("findAxesOfProgram AxeDto", re);
			throw re;
		}
	}

	/**
	 * [RechercheProgrammeService.findOneAxeById] Method 
	 * Overridden By : @author rlaib  on : 28 janv. 2015  10:10:30
	 * @param id
	 * @return
	 */
	@Override
	public AxeDto findOneAxeById(Long id) {
	
		try {
				Axe axe = axeDao.findById(id);
				if (axe != null) {
					AxeDto axeDto = new AxeDto();
					mapper.map(axe, axeDto);
					return axeDto;
				}
				return null;

		} catch (RuntimeException re) {
			log.error("findOneAxeById AxeDto failed : ", re);
			throw re;
		}
	}

	/**
	 * [RechercheProgrammeService.findByPeriod] Method 
	 * Overridden By : @author rlaib  on : 3 févr. 2015  11:45:31
	 * @param startYear
	 * @param endYear
	 * @return
	 */
	@Override
	public List<ProgrammeDto> findByPeriod(Short startYear, Short endYear) {
		
		try {
			if(startYear == null || endYear==null)
				return null;
			
			List<Programme> programmes= programmeDao.findByPeriod(startYear, endYear);
			List<ProgrammeDto> programmeDtos = new ArrayList<ProgrammeDto>();
			for (Programme programme: programmes) {
				ProgrammeDto programmeDto = new ProgrammeDto();
				mapper.map(programme, programmeDto);
				programmeDtos.add(programmeDto);
			}
			return programmeDtos;
		} catch (RuntimeException re) {
			log.error("findByKeyWords ProgrammeDto", re);
			throw re;
		}
	}

	@Override
	public List<ProgrammeDto> findActifProgramme() {
		
		try {
			
			
			List<Programme> programmes= programmeDao.findActifProgramme();
			List<ProgrammeDto> programmeDtos = new ArrayList<ProgrammeDto>();
			for (Programme programme: programmes) {
				ProgrammeDto programmeDto = new ProgrammeDto();
				mapper.map(programme, programmeDto);
				programmeDtos.add(programmeDto);
			}
			return programmeDtos;
		} catch (RuntimeException re) {
			log.error("findByKeyWords ProgrammeDto", re);
			throw re;
		}
	}
}
