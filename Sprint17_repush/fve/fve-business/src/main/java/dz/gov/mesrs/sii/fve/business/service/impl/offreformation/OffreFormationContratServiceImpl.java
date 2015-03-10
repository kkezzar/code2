/**
 * [dz.gov.mesrs.sii.fve.business.service.impl.offreformation.OffreFormationDetailServiceImpl.java] 
 * @author rlaib on : 6 f�vr. 2014  14:44:30
 */
package dz.gov.mesrs.sii.fve.business.service.impl.offreformation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.fve.offreformation.OffreFormationContratDao;
import dz.gov.mesrs.sii.commons.data.dao.fve.offreformation.OffreFormationPartenaireDao;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.OffreFormation;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.OffreFormationContrat;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.OffreFormationPartenaire;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OffreFormationContratDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OffreFormationDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OffreFormationPartenaireDto;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OffreFormationContratService;


/**
 * @author  Rafik LAIB  on : 5 avr. 2014  21:18:53
 */

@Service("offreFormationContratService")
public class OffreFormationContratServiceImpl implements OffreFormationContratService {

	@Autowired
	@Qualifier ("offreFormationContratDao")
	private OffreFormationContratDao offreFormationContratDao;
	
	@Autowired
	@Qualifier ("offreFormationPartenaireDao")
	private OffreFormationPartenaireDao offreFormationPartenaireDao;

	@Autowired
	@Qualifier("mapper")
	private DozerBeanMapper mapper;

	/**
	 * [OffreFormationContratServiceImpl.OffreFormationContratServiceImpl()] Constructor
	 * @author  Rafik LAIB  on : 5 avr. 2014  21:55:21	
	 */
	public OffreFormationContratServiceImpl(){

	}

	/**
	 * [OffreFormationContratServiceImpl.insertOrUpdate] Method 
	 * overridden By :  @author  Rafik LAIB  
	 * On : 5 avr. 2014  21:55:13
	 * @param offreFormationContratDto
	 * @return
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public OffreFormationContratDto  insertOrUpdate(OffreFormationContratDto offreFormationContratDto) {

		OffreFormationContrat offreFormationContrat = mapper.map(offreFormationContratDto, OffreFormationContrat.class);
		
		if (offreFormationContrat.getId() == 0) 
			offreFormationContratDao.persist(offreFormationContrat);
		else
			offreFormationContrat = offreFormationContratDao.merge(offreFormationContrat);

		mapper.map(offreFormationContrat, offreFormationContratDto);

		return offreFormationContratDto;
	}

	/**
	 * [OffreFormationContratServiceImpl.remove] Method 
	 * overridden By :  @author  Rafik LAIB  
	 * On : 5 avr. 2014  21:55:07
	 * @param offreFormationContratDto
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void remove(OffreFormationContratDto offreFormationContratDto) {

		OffreFormationContrat offreFormationContrat = mapper.map(offreFormationContratDto, OffreFormationContrat.class);

		offreFormationContratDao.remove(offreFormationContrat);

	}

	/**
	 * [OffreFormationContratServiceImpl.findById] Method 
	 * overridden By :  @author  Rafik LAIB  
	 * On : 5 avr. 2014  21:55:01
	 * @param id
	 * @return
	 */
	@Override
	public OffreFormationContratDto findById(int id) {

		OffreFormationContrat offreFormationContrat = offreFormationContratDao.findById(id);

		if (offreFormationContrat != null)
			return mapper.map(offreFormationContrat, OffreFormationContratDto.class);

		return null;
	}
	
	public  OffreFormationContratDto findByRefCode(String refCode){
		
				OffreFormationContrat offreFormationContrat = offreFormationContratDao.findByRefCode(refCode);

				if (offreFormationContrat != null)
						return mapper.map(offreFormationContrat, OffreFormationContratDto.class);

				return null;
	
	}
	
	public  List<OffreFormationContratDto> findByOfId(int ofId) {
		
				List<OffreFormationContratDto> offreFormationContratDtos = new ArrayList<OffreFormationContratDto>();
		
				try {
						List<OffreFormationContrat> offreFormationContrats = offreFormationContratDao.findByOfId(ofId);
		
						for (OffreFormationContrat offreFormationContrat : offreFormationContrats) {
							offreFormationContratDtos.add(mapper.map(offreFormationContrat, OffreFormationContratDto.class));
						}
				}
				catch (Exception e){
							
					return null; 
				}
		
				return offreFormationContratDtos;
	}
	
	/**
	 * [OffreFormationContratServiceImpl.findAll] Method 
	 * overridden By :  @author  Rafik LAIB  
	 * On : 5 avr. 2014  21:54:55
	 * @return
	 */
	@Override
	public List<OffreFormationContratDto> findAll() {		


		List<OffreFormationContratDto> offreFormationContratDtos = new ArrayList<OffreFormationContratDto>();

		try {
			List<OffreFormationContrat> offreFormationContrats = offreFormationContratDao.findAll();

			for (OffreFormationContrat offreFormationContrat : offreFormationContrats) {
				offreFormationContratDtos.add(mapper.map(offreFormationContrat, OffreFormationContratDto.class));
			}
		}
		catch (Exception e){

		}

		return offreFormationContratDtos;

	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
    public  OffreFormationContratDto saveContract(OffreFormationContratDto offreFormationContratDto, List<OffreFormationPartenaireDto> offreFormationPartenaireDtos, OffreFormationDto offreFormationDto) {
    	
		try {
				OffreFormationContrat offreFormationContrat = mapper.map(offreFormationContratDto, OffreFormationContrat.class);
				OffreFormationContrat offreFormationContratExisting = offreFormationContratDao.findByRefCode(offreFormationContrat.getRefCodeContrat());
				
				if(offreFormationContratExisting == null){
				
					OffreFormation offreFormation = mapper.map(offreFormationDto, OffreFormation.class);
					offreFormationContrat.setOffreFormation(offreFormation);
					offreFormationContratDao.persist(offreFormationContrat);
				}
				else
					offreFormationContrat = offreFormationContratExisting;
				
				for (OffreFormationPartenaireDto offreFormationPartenaireDto : offreFormationPartenaireDtos) {
					
					OffreFormationPartenaire offreFormationPartenaire = mapper.map(offreFormationPartenaireDto, OffreFormationPartenaire.class);
					offreFormationPartenaire.setOffreFormationContrat(offreFormationContrat);
					
					offreFormationPartenaireDao.persist(offreFormationPartenaire);
					
				}
		
				offreFormationContratDto = mapper.map(offreFormationContrat, OffreFormationContratDto.class);
				
				List<OffreFormationPartenaire> partners = offreFormationPartenaireDao.findPartnersByContractId(offreFormationContrat.getId());
				List<OffreFormationPartenaireDto> partnersDto = new ArrayList<OffreFormationPartenaireDto>();
				for (OffreFormationPartenaire p : partners) {
					
					partnersDto.add(mapper.map(p, OffreFormationPartenaireDto.class));
				}
				
				offreFormationContratDto.setOffreFormationPartenaireDtos(new HashSet<>(partnersDto));
				return offreFormationContratDto;
		}
		catch (Exception e) {
			
				return null;
		}

    }
}
