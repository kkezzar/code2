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

import dz.gov.mesrs.sii.commons.data.dao.recherche.IndEvaluationDao;
import dz.gov.mesrs.sii.commons.data.model.recherche.IndEvaluation;
import dz.gov.mesrs.sii.recherche.business.model.dto.IndEvaluationDto;
import dz.gov.mesrs.sii.recherche.business.service.RechercheIndEvaluationService;

/**
 * @author sRamdane  on : 08 fev 2015   17:35:31
 */
@Service("rechercheIndEvaluationService")
public class RechercheIndEvaluationServiceImpl implements RechercheIndEvaluationService {

	@Autowired
	@Qualifier ("indEvaluationDao")
	private IndEvaluationDao indEvaluationDao;
	
	private static final Log log = LogFactory.getLog(RechercheIndEvaluationServiceImpl.class);
	
    @Autowired
	@Qualifier("mapper")
	private DozerBeanMapper mapper;

		
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public IndEvaluationDto saveIndEvaluation(IndEvaluationDto indEvaluationDto) {
		IndEvaluation indEvaluation = mapper.map(indEvaluationDto, IndEvaluation.class);
		if(indEvaluationDto.getId()==null)   indEvaluationDao.persist(indEvaluation);
		else indEvaluationDao.merge(indEvaluation);		
		return indEvaluationDto;
	}


		
	@Override
	public List<IndEvaluationDto> findListIndicateurByIdProjet(Long id) {
		List<IndEvaluation> listIndEvaluation=indEvaluationDao.findListMotsClesByIdProjet(id);
		List<IndEvaluationDto> listIndEvaluationDto=new ArrayList<IndEvaluationDto>();
		if(listIndEvaluation!=null)if(!listIndEvaluation.isEmpty()){
			for(IndEvaluation indEvaluation:listIndEvaluation){
				IndEvaluationDto indEvaluationDto = new IndEvaluationDto();
				mapper.map( indEvaluation,indEvaluationDto);	listIndEvaluationDto.add(indEvaluationDto);
			}
		}
		return listIndEvaluationDto;
	}



	@Override
	public IndEvaluationDto findById(Long indevaluationId) {
		IndEvaluation ie =indEvaluationDao.findById(indevaluationId);
		IndEvaluationDto ieDto = new IndEvaluationDto();
		 mapper.map( ie,ieDto);
		return ieDto;
		
	}
	
	
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void removeIndEvaluation(Long indevaluationId) {
		indEvaluationDao.remove(indevaluationId);
		
	}
	
	
	

	
	
	
	

	
}

