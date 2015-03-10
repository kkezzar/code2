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

import dz.gov.mesrs.sii.commons.data.dao.fve.offreformation.OffreFormationPartenaireDao;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.OffreFormationPartenaire;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OffreFormationPartenaireDto;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OffreFormationPartenaireService;


/**
 * @author  Rafik LAIB  on : 5 avr. 2014  21:46:22
 */
@Service("offreFormationPartenaireService")
public class OffreFormationPartenaireServiceImpl implements OffreFormationPartenaireService {

	@Autowired
	@Qualifier ("offreFormationPartenaireDao")
	private OffreFormationPartenaireDao offreFormationPartenaireDao;

	@Autowired
	@Qualifier("mapper")
	private DozerBeanMapper mapper;

	/**
	 * [OffreFormationPartenaireServiceImpl.OffreFormationPartenaireServiceImpl()] Constructor
	 * @author  Rafik LAIB  on : 5 avr. 2014  21:47:19	
	 */
	public OffreFormationPartenaireServiceImpl(){

	}

	/**
	 * [OffreFormationPartenaireServiceImpl.insertOrUpdate] Method 
	 * overridden By :  @author  Rafik LAIB  
	 * On : 5 avr. 2014  21:47:09
	 * @param offreFormationPartenaireDto
	 * @return
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public OffreFormationPartenaireDto  insertOrUpdate(OffreFormationPartenaireDto offreFormationPartenaireDto) {

		OffreFormationPartenaire offreFormationPartenaire = mapper.map(offreFormationPartenaireDto, OffreFormationPartenaire.class);
		
		if (offreFormationPartenaire.getId() == 0) 
			
			offreFormationPartenaireDao.persist(offreFormationPartenaire);
		
		else
			
			offreFormationPartenaire = offreFormationPartenaireDao.merge(offreFormationPartenaire);

		mapper.map(offreFormationPartenaire, offreFormationPartenaireDto);

		return offreFormationPartenaireDto;
	}

	/**
	 * [OffreFormationPartenaireServiceImpl.remove] Method 
	 * overridden By :  @author  Rafik LAIB  
	 * On : 5 avr. 2014  21:47:02
	 * @param id
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void remove(int id) {

		//OffreFormationEquipeMembre offreFormationEquipeMembre = mapper.map(offreFormationEquipeMembreDto, OffreFormationEquipeMembre.class);
		offreFormationPartenaireDao.remove(id);

	}

	/**
	 * [OffreFormationPartenaireServiceImpl.findById] Method 
	 * overridden By :  @author  Rafik LAIB  
	 * On : 5 avr. 2014  21:46:55
	 * @param id
	 * @return
	 */
	@Override
	public OffreFormationPartenaireDto findById(int id) {

		OffreFormationPartenaire offreFormationPartenaire = offreFormationPartenaireDao.findById(id);

		if (offreFormationPartenaire != null)
			return mapper.map(offreFormationPartenaire, OffreFormationPartenaireDto.class);

		return null;
	}

	/**
	 * [OffreFormationPartenaireServiceImpl.findAll] Method 
	 * overridden By :  @author  Rafik LAIB  
	 * On : 5 avr. 2014  21:46:36
	 * @return
	 */
	@Override
	public List<OffreFormationPartenaireDto> findAll() {		

				List<OffreFormationPartenaireDto> offreFormationPartenaireDtos = new ArrayList<OffreFormationPartenaireDto>();
		
				try {
							List<OffreFormationPartenaire> offreFormationPartenaires = offreFormationPartenaireDao.findAll();
		
					for (OffreFormationPartenaire offreFormationPartenaire : offreFormationPartenaires) {
						
						offreFormationPartenaireDtos.add(mapper.map(offreFormationPartenaire, OffreFormationPartenaireDto.class));
					}
				}
				catch (Exception e){
		
				}
		
				return offreFormationPartenaireDtos;

	}

	/**
	 * [OffreFormationPartenaireServiceImpl.findPartnersByContractId] Method 
	 * overridden By :  @author  Rafik LAIB  
	 * On : 5 avr. 2014  21:46:02
	 * @param contractId
	 * @return
	 */
	@Override
	public  List<OffreFormationPartenaireDto> findPartnersByContractId(int contractId) {
		 
		 	List<OffreFormationPartenaireDto> offreFormationPartenaireDtos = new ArrayList<OffreFormationPartenaireDto>();
					
			try {
						List<OffreFormationPartenaire> offreFormationPartenaires = offreFormationPartenaireDao.findPartnersByContractId(contractId);
	
				for (OffreFormationPartenaire offreFormationPartenaire : offreFormationPartenaires) {
					
					offreFormationPartenaireDtos.add(mapper.map(offreFormationPartenaire, OffreFormationPartenaireDto.class));
				}
			}
			catch (Exception e){
	
			}
	
			return offreFormationPartenaireDtos;
	 }

	
}
