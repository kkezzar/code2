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

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.EtapeDto;
import dz.gov.mesrs.sii.commons.data.dao.recherche.EtapeValidationDao;
import dz.gov.mesrs.sii.commons.data.model.bpm.Etape;
import dz.gov.mesrs.sii.commons.data.model.recherche.EtapeValidation;
import dz.gov.mesrs.sii.recherche.business.model.dto.EtapeValidationDto;
import dz.gov.mesrs.sii.recherche.business.model.dto.ProjetDto;
import dz.gov.mesrs.sii.recherche.business.service.RechercheEtapeValidationService;

/**
 * @author sRamdane  on : 08 fev 2015   17:35:31
 */
@Service("rechercheEtapeValidationService")
public class RechercheEtapeValidationServiceImpl implements RechercheEtapeValidationService {

	@Autowired
	@Qualifier ("etapeValidationDao")
	private EtapeValidationDao etapeValidationDao;
	
	private static final Log log = LogFactory.getLog(RechercheEtapeValidationServiceImpl.class);
	
    @Autowired
	@Qualifier("mapper")
	private DozerBeanMapper mapper;

	@Override
	public List<EtapeDto> findListEtapesByIdProcesus(Integer processusId) {
		List<EtapeDto> listEtapesDto = new ArrayList<EtapeDto>();
		List<Etape> listEtapes =etapeValidationDao.findListEtapesByIdProcesus(processusId) ;		
		if(listEtapes!=null)if(!listEtapes.isEmpty()){			
			for(Etape etape :listEtapes){
				EtapeDto etapeDto = new EtapeDto();
				mapper.map(etape, etapeDto);
				listEtapesDto.add(etapeDto);
			}
		}
		return listEtapesDto;
	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void saveListEtapeValidationProjet(List<EtapeDto> listEtapes,ProjetDto selectedProjet) {
		if((selectedProjet != null)&&(listEtapes!= null))
			if(!listEtapes.isEmpty()){
				for(EtapeDto etapeDto :listEtapes){
					EtapeValidationDto etapeValidationDto= new EtapeValidationDto();
					etapeValidationDto.setEtapeCircuitDto(etapeDto);
					etapeValidationDto.setProjetDto(selectedProjet);
					EtapeValidation etapeValidation= mapper.map(etapeValidationDto, EtapeValidation.class);
					etapeValidationDao.persist(etapeValidation);
					
				}
				
			}
		
	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void removeListEtapeValidationByProjet(Long selectedProjet) {
		if(selectedProjet != null)	etapeValidationDao.removeListEtapeValidationByProjet(selectedProjet);		
	}

	@Override
	public EtapeValidationDto findById(Long validationId) {
		EtapeValidationDto etapeValidationDto= new EtapeValidationDto() ;
		EtapeValidation etapeValidation =etapeValidationDao.findByIdValidation(validationId);
		if (etapeValidation != null) mapper.map(etapeValidation, etapeValidationDto);
		return etapeValidationDto;
	}
	
	
	@Override
	public List<EtapeValidationDto> findListEtapeValidationByProjet(Long selectedProjet) {
		List<EtapeValidationDto> listetapeValidationDto = new ArrayList<EtapeValidationDto>();
		if(selectedProjet != null){
			
			List<EtapeValidation> listetapeValidation =etapeValidationDao.findListEtapeValidationByProjet(selectedProjet);
			if((listetapeValidation != null))
				if(!listetapeValidation.isEmpty()){
					for(EtapeValidation etape :listetapeValidation){
						EtapeValidationDto etapeValidationDto= new EtapeValidationDto();
						mapper.map(etape, etapeValidationDto);
						listetapeValidationDto.add(etapeValidationDto);
					}
				}
		}		
		return listetapeValidationDto;
	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.SUPPORTS)
	public EtapeValidationDto saveValidation(
		EtapeValidationDto etapeValidationDto) {
		
		EtapeValidation etapeValidation= mapper.map(etapeValidationDto, EtapeValidation.class);
		//etapeValidation= etapeValidationDao.findById(etapeValidation.getId());
		if(etapeValidation != null) {
			etapeValidation=etapeValidationDao.merge(etapeValidation);
			//etapeValidationDao.persist(etapeValidation);
		}
		return etapeValidationDto;
	}
	
	@Override
	public boolean findNonAcceptationValidation(Long selectedProjet,String codeValidation){
		return etapeValidationDao.findNonAcceptationValidation(selectedProjet, codeValidation) ;
	}
	
	
	
	/**
	 * [RechercheGroupeService.insertOrUpdate] Method 
	 * Overridden By : @author rlaib  on : 16 dec. 2014  14:39:38
	 * @param structureDto
	 * @return
	 */
	

	
}
