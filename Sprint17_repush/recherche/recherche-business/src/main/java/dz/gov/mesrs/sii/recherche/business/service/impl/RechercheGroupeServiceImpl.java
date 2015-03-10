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

import dz.gov.mesrs.sii.commons.data.dao.recherche.GroupeDao;
import dz.gov.mesrs.sii.commons.data.model.recherche.Groupe;
import dz.gov.mesrs.sii.recherche.business.model.dto.GroupeDto;
import dz.gov.mesrs.sii.recherche.business.service.RechercheGroupeService;

/**
 * @author rlaib  on : 14 dec. 2014  17:35:31
 */
@Service("rechercheGroupeService")
public class RechercheGroupeServiceImpl implements RechercheGroupeService {

	@Autowired
	@Qualifier ("groupeDao")
	private GroupeDao groupeDao;
	
	private static final Log log = LogFactory.getLog(RechercheGroupeServiceImpl.class);
	
    @Autowired
	@Qualifier("mapper")
	private DozerBeanMapper mapper;
	
	/**
	 * [RechercheGroupeService.insertOrUpdate] Method 
	 * Overridden By : @author rlaib  on : 16 dec. 2014  14:39:38
	 * @param structureDto
	 * @return
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public GroupeDto insertOrUpdate(GroupeDto groupeDto) {
		Groupe groupe = mapper.map(groupeDto,
				Groupe.class);
		try {
					if (groupe.getId() == null){
						groupeDao.persist(groupe);
					}else{
						groupe = groupeDao.merge(groupe);
					}
					mapper.map(groupe, groupeDto);
					return groupeDto;
		
		} catch (RuntimeException re) {
					log.error("insertOrUpdate GroupeDto failed : ", re);
					throw re;
		}
	}

	/**
	 * [RechercheGroupeService.findAll] Method 
	 * Overridden By : @author rlaib  on : 16 dec. 2014  14:45:31
	 * @return
	 */
	@Override
	public List<GroupeDto> findAll() {
		
		try {
			List<Groupe> groupes= groupeDao.findAll();
			List<GroupeDto> groupeDtos = new ArrayList<GroupeDto>();
			for (Groupe groupe: groupes) {
				GroupeDto groupeDto = new GroupeDto();
				mapper.map(groupe, groupeDto);
				groupeDtos.add(groupeDto);
			}
			return groupeDtos;
		} catch (RuntimeException re) {
			log.error("findAll GroupeDto", re);
			throw re;
		}
	}
	
	/**
	 * [RechercheGroupeService.remove] Method 
	 * Overridden By : @author rlaib  on : 17 dec. 2014  16:59:53
	 * @param structureDto
	 */
	@Override
	@Transactional(value = "transactionManager", propagation =	Propagation.REQUIRED)
	public void remove(Long id) {

		groupeDao.remove(id);
	}


	/**
	 * [RechercheGroupeService.findStructureRechercheGroupes] Method 
	 * @author Rafik  on : 21 déc. 2014  12:26:26
	 * @param idStructure
	 * @return
	 */
	@Override
	public List<GroupeDto> findStructureRechercheGroupes(Long idStructure) {
	
		try {
			List<Groupe> groupes= groupeDao.findStructureRechercheGroupes(idStructure);
			List<GroupeDto> groupeDtos = new ArrayList<GroupeDto>();
			for (Groupe groupe: groupes) {
				GroupeDto groupeDto = new GroupeDto();
				mapper.map(groupe, groupeDto);
				groupeDtos.add(groupeDto);
			}
			return groupeDtos;
		} catch (RuntimeException re) {
			log.error("findStructureRechercheGroupes GroupeDto", re);
			throw re;
		}
	}


	/**
	 * [RechercheGroupeService.findById] Method 
	 * Overridden By : @author rlaib  on : 4 janv. 2015  09:01:22
	 * @param id
	 * @return
	 */
	@Override
	public GroupeDto findById(Long id) {
			
		try {
			Groupe groupe = groupeDao.findById(id);
			if (groupe != null) {
					GroupeDto groupeDto = new GroupeDto();
					mapper.map(groupe, groupeDto);
					return groupeDto;
			}
			return null;

		} catch (RuntimeException re) {
			log.error("findById groupeDto failed : ", re);
			throw re;
		}
	}

	
}
