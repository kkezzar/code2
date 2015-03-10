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

import dz.gov.mesrs.sii.commons.data.dao.fve.offreformation.OffreFormationEquipeMembreDao;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.OffreFormationEquipe;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.OffreFormationEquipeMembre;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OffreFormationEquipeDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OffreFormationEquipeMembreDto;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OffreFormationEquipeMembreService;

/**
 * @author rlaib  on : 6 mars. 2014  14:44:30
 */

@Service("offreFormationEquipeMembreService")
public class OffreFormationEquipeMembreServiceImpl implements OffreFormationEquipeMembreService {

	@Autowired
	@Qualifier ("offreFormationEquipeMembreDao")
	private OffreFormationEquipeMembreDao offreFormationEquipeMembreDao;

	@Autowired
	@Qualifier("mapper")
	private DozerBeanMapper mapper;

	/**
	 * [OffreFormationEquipeMembreServiceImpl.OffreFormationEquipeMembreServiceImpl()] Constructor
	 * @author  Rafik LAIB  on : 5 avr. 2014  21:50:02	
	 */
	public OffreFormationEquipeMembreServiceImpl(){

	}

	/**
	 * [OffreFormationEquipeMembreServiceImpl.insertOrUpdate] Method 
	 * overridden By :  @author  Rafik LAIB  
	 * On : 5 avr. 2014  21:49:57
	 * @param offreFormationEquipeMembreDto
	 * @param offreFormationEquipeDto
	 * @return
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public OffreFormationEquipeMembreDto  insertOrUpdate(OffreFormationEquipeMembreDto offreFormationEquipeMembreDto) {

		OffreFormationEquipeMembre offreFormationEquipeMembre = mapper.map(offreFormationEquipeMembreDto, OffreFormationEquipeMembre.class);
		if (offreFormationEquipeMembre.getId() == 0) 
			offreFormationEquipeMembreDao.persist(offreFormationEquipeMembre);
		else
			offreFormationEquipeMembre = offreFormationEquipeMembreDao.merge(offreFormationEquipeMembre);
		mapper.map(offreFormationEquipeMembre, offreFormationEquipeMembreDto);
		return offreFormationEquipeMembreDto;
	}

	/**
	 * [OffreFormationEquipeMembreServiceImpl.remove] Method 
	 * overridden By :  @author  Rafik LAIB  
	 * On : 5 avr. 2014  21:49:50
	 * @param id
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void remove(int id) {

		//OffreFormationEquipeMembre offreFormationEquipeMembre = mapper.map(offreFormationEquipeMembreDto, OffreFormationEquipeMembre.class);
		offreFormationEquipeMembreDao.remove(id);

	}

	/**
	 * [OffreFormationEquipeMembreServiceImpl.findById] Method 
	 * overridden By :  @author  Rafik LAIB  
	 * On : 5 avr. 2014  21:49:46
	 * @param id
	 * @return
	 */
	@Override
	public OffreFormationEquipeMembreDto findById(int id) {

		OffreFormationEquipeMembre offreFormationEquipeMembre = offreFormationEquipeMembreDao.findById(id);

		if (offreFormationEquipeMembre != null)
			return mapper.map(offreFormationEquipeMembre, OffreFormationEquipeMembreDto.class);

		return null;
	}

	/**
	 * [OffreFormationEquipeMembreServiceImpl.findAll] Method 
	 * overridden By :  @author  Rafik LAIB  
	 * On : 5 avr. 2014  21:49:41
	 * @return
	 */
	@Override
	public List<OffreFormationEquipeMembreDto> findAll() {		

				List<OffreFormationEquipeMembreDto> offreFormationEquipeMembreDtos = new ArrayList<OffreFormationEquipeMembreDto>();
		
				try {
							List<OffreFormationEquipeMembre> offreFormationEquipeMembres = offreFormationEquipeMembreDao.findAll();
		
					for (OffreFormationEquipeMembre offreFormationEquipeMembre : offreFormationEquipeMembres) {
						
							offreFormationEquipeMembreDtos.add(mapper.map(offreFormationEquipeMembre, OffreFormationEquipeMembreDto.class));
					}
				}
				catch (Exception e){
		
				}
		
				return offreFormationEquipeMembreDtos;

	}

	/**
	 * [OffreFormationEquipeMembreServiceImpl.findMembersByTeamId] Method 
	 * overridden By :  @author  Rafik LAIB  
	 * On : 5 avr. 2014  21:49:36
	 * @param teamId
	 * @return
	 */
	@Override
	 public  List<OffreFormationEquipeMembreDto> findMembersByTeamId(int teamId) {
		 
			List<OffreFormationEquipeMembreDto> offreFormationEquipeMembreDtos = new ArrayList<OffreFormationEquipeMembreDto>();
			
			try {
						List<OffreFormationEquipeMembre> offreFormationEquipeMembres = offreFormationEquipeMembreDao.findMembersByTeamId(teamId);
	
				for (OffreFormationEquipeMembre offreFormationEquipeMembre : offreFormationEquipeMembres) {
					
						offreFormationEquipeMembreDtos.add(mapper.map(offreFormationEquipeMembre, OffreFormationEquipeMembreDto.class));
				}
			}
			catch (Exception e){
	
			}
	
			return offreFormationEquipeMembreDtos;
	 }

}
