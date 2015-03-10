/**
 * [dz.gov.mesrs.sii.recherche.business.service.impl.RechercheStructureServiceImpl.java] 
 * @author rlaib on : 14 d�c. 2014  17:35:31
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

import dz.gov.mesrs.sii.commons.data.dao.recherche.StructureDao;
import dz.gov.mesrs.sii.commons.data.model.recherche.Groupe;
import dz.gov.mesrs.sii.commons.data.model.recherche.Structure;
import dz.gov.mesrs.sii.recherche.business.model.dto.GroupeDto;
import dz.gov.mesrs.sii.recherche.business.model.dto.StructureDto;
import dz.gov.mesrs.sii.recherche.business.service.RechercheStructureService;

/**
 * @author rlaib  on : 14 dec. 2014  17:35:31
 */
@Service("rechercheStructureService")
public class RechercheStructureServiceImpl implements RechercheStructureService {

	@Autowired
	@Qualifier ("structureDao")
	private StructureDao structureDao;
	
	private static final Log log = LogFactory.getLog(RechercheStructureServiceImpl.class);
	
    @Autowired
	@Qualifier("mapper")
	private DozerBeanMapper mapper;
	
	/**
	 * [RechercheStructureService.insertOrUpdate] Method 
	 * Overridden By : @author rlaib  on : 16 d�c. 2014  14:39:38
	 * @param structureDto
	 * @return
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public StructureDto insertOrUpdate(StructureDto structureDto) {
		Structure structure = mapper.map(structureDto,
				Structure.class);
		try {
					if (structure.getId() == null){
						structureDao.persist(structure);
					}else{
						structure = structureDao.merge(structure);
					}
					mapper.map(structure, structureDto);
					return structureDto;
		
		} catch (RuntimeException re) {
					log.error("insertOrUpdate StructureDto failed : ", re);
					throw re;
		}
	}

	/**
	 * [RechercheStructureService.findAll] Method 
	 * Overridden By : @author rlaib  on : 16 d�c. 2014  14:45:31
	 * @return
	 */
	@Override
	public List<StructureDto> findAll() {
		
		try {
			List<Structure> structures= structureDao.findAll();
			List<StructureDto> structureDtos = new ArrayList<StructureDto>();
			for (Structure structure : structures) {
				StructureDto structureDto = new StructureDto();
				mapper.map(structure, structureDto);
				structureDtos.add(structureDto);
			}
			return structureDtos;
		} catch (RuntimeException re) {
			log.error("findAll StructureDto", re);
			throw re;
		}
	}
	
	/**
	 * [RechercheStructureService.remove] Method 
	 * Overridden By : @author rlaib  on : 17 d�c. 2014  16:59:53
	 * @param structureDto
	 */
	@Override
	@Transactional(value = "transactionManager", propagation =	Propagation.REQUIRED)
	public void remove(Integer id) {

		structureDao.remove(id);
	}

	@Override
	public List<StructureDto> findByEtablicement(Integer id) {
		
		try {
			List<Structure> structures= structureDao.findByEtablicement(id);
			List<StructureDto> structureDtos = new ArrayList<StructureDto>();
			for (Structure structure : structures) {
				StructureDto structureDto = new StructureDto();
				mapper.map(structure, structureDto);
				structureDtos.add(structureDto);
			}
			return structureDtos;
		} catch (RuntimeException re) {
			log.error("findAll StructureDto", re);
			throw re;
		}
	}
	
	@Override
	public StructureDto findById(Long id) {
			
		try {
			Structure structure = structureDao.findById(id);
			if (structure != null) {
				StructureDto structureDto = new StructureDto();
					mapper.map(structure, structureDto);
					return structureDto;
			}
			return null;

		} catch (RuntimeException re) {
			log.error("findById structureDto failed : ", re);
			throw re;
		}
	}
	
	

	
}
