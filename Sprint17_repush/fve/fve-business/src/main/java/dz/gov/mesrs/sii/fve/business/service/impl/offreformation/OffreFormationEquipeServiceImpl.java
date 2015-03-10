/**
 * [dz.gov.mesrs.sii.fve.business.service.impl.offreformation.OffreFormationDetailServiceImpl.java] 
 * @author rlaib on : 6 f�vr. 2014  14:44:30
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

import dz.gov.mesrs.sii.commons.data.dao.fve.offreformation.OffreFormationEquipeDao;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.OffreFormation;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.OffreFormationEquipe;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OffreFormationDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OffreFormationEquipeDto;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OffreFormationEquipeService;

/**
 * @author rlaib  on : 6 mars. 2014  14:44:30
 */

@Service("offreFormationEquipeService")
public class OffreFormationEquipeServiceImpl implements OffreFormationEquipeService {

	@Autowired
	@Qualifier ("offreFormationEquipeDao")
	private OffreFormationEquipeDao offreFormationEquipeDao;

	@Autowired
	@Qualifier("mapper")
	private DozerBeanMapper mapper;

	/**
	 * [OffreFormationEquipeServiceImpl.OffreFormationEquipeServiceImpl()] Constructor
	 * @author  Rafik LAIB  on : 5 avr. 2014  21:57:08	
	 */
	public OffreFormationEquipeServiceImpl(){

	}

	/**
	 * [OffreFormationEquipeServiceImpl.insertOrUpdate] Method 
	 * overridden By :  @author  Rafik LAIB  
	 * On : 5 avr. 2014  21:57:03
	 * @param offreFormationEquipeDto
	 * @param offreFormationDto
	 * @return
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public OffreFormationEquipeDto  insertOrUpdate(OffreFormationEquipeDto offreFormationEquipeDto) {

		OffreFormationEquipe offreFormationEquipe = mapper.map(offreFormationEquipeDto, OffreFormationEquipe.class);
		if (offreFormationEquipe.getId() == 0) 
			offreFormationEquipeDao.persist(offreFormationEquipe);
		else
			offreFormationEquipe = offreFormationEquipeDao.merge(offreFormationEquipe);

		mapper.map(offreFormationEquipe, offreFormationEquipeDto);

		return offreFormationEquipeDto;
	}

	/**
	 * [OffreFormationEquipeServiceImpl.remove] Method 
	 * overridden By :  @author  Rafik LAIB  
	 * On : 5 avr. 2014  21:56:58
	 * @param offreFormationEquipeDto
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void remove(OffreFormationEquipeDto offreFormationEquipeDto) {

		OffreFormationEquipe offreFormationEquipe = mapper.map(offreFormationEquipeDto, OffreFormationEquipe.class);

		offreFormationEquipeDao.remove(offreFormationEquipe);

	}

	/**
	 * [OffreFormationEquipeServiceImpl.findById] Method 
	 * overridden By :  @author  Rafik LAIB  
	 * On : 5 avr. 2014  21:56:53
	 * @param id
	 * @return
	 */
	@Override
	public OffreFormationEquipeDto findById(int id) {

		OffreFormationEquipe offreFormationEquipe = offreFormationEquipeDao.findById(id);

		if (offreFormationEquipe != null)
			return mapper.map(offreFormationEquipe, OffreFormationEquipeDto.class);

		return null;
	}

	/**
	 * [OffreFormationEquipeServiceImpl.findAll] Method 
	 * overridden By :  @author  Rafik LAIB  
	 * On : 5 avr. 2014  21:56:48
	 * @return
	 */
	@Override
	public List<OffreFormationEquipeDto> findAll() {		


		List<OffreFormationEquipeDto> offreFormationEquipeDtos = new ArrayList<OffreFormationEquipeDto>();

		try {
			List<OffreFormationEquipe> offreFormationEquipes = offreFormationEquipeDao.findAll();

			for (OffreFormationEquipe offreFormationEquipe : offreFormationEquipes) {
				offreFormationEquipeDtos.add(mapper.map(offreFormationEquipe, OffreFormationEquipeDto.class));
			}
		}
		catch (Exception e){

		}

		return offreFormationEquipeDtos;

	}

	/**
	 * [OffreFormationEquipeServiceImpl.findOfEquipeById] Method 
	 * overridden By :  @author  Rafik LAIB  
	 * On : 5 avr. 2014  21:56:39
	 * @param ofId
	 * @return
	 */
	@Override
	public OffreFormationEquipeDto findOfEquipeById(int ofId) {

		OffreFormationEquipe offreFormationEquipe = offreFormationEquipeDao.findOfEquipeById(ofId);
		if (offreFormationEquipe != null)
			return mapper.map(offreFormationEquipe, OffreFormationEquipeDto.class);

		return null;
	}
}
