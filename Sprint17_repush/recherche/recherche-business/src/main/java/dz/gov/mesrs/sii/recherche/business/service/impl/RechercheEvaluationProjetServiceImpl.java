/**
 * [dz.gov.mesrs.sii.recherche.business.service.impl.RechercheStructureServiceImpl.java] 
 * @author rlaib on : 14 d�c. 2014  17:35:31
 */
package dz.gov.mesrs.sii.recherche.business.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.recherche.EvaluationProjetDao;
import dz.gov.mesrs.sii.commons.data.model.recherche.EvaluationProjet;
import dz.gov.mesrs.sii.recherche.business.model.dto.EvaluationProjetDto;
import dz.gov.mesrs.sii.recherche.business.service.RechercheEvaluationProjetService;

/**
 * @author sRamdane  on : 08 fev 2015   17:35:31
 */
@Service("evaluationProjetService")
public class RechercheEvaluationProjetServiceImpl implements RechercheEvaluationProjetService {

	@Autowired
	@Qualifier ("evaluationProjDao")
	private EvaluationProjetDao evaluationProjetDao;
	
	private static final Log log = LogFactory.getLog(RechercheEvaluationProjetServiceImpl.class);
	
    @Autowired
	@Qualifier("mapper")
	private DozerBeanMapper mapper;

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public EvaluationProjetDto saveEvaluationProjet(EvaluationProjetDto evaluationProjetDto) {
		EvaluationProjet evaluationProjet =mapper.map(evaluationProjetDto, EvaluationProjet.class);
		if(evaluationProjet.getId()==null) evaluationProjetDao.persist(evaluationProjet);
		else evaluationProjetDao.merge(evaluationProjet);
		EvaluationProjetDto evaluationProjDto = new EvaluationProjetDto();
		mapper.map(evaluationProjet, evaluationProjDto);
		return evaluationProjDto;
	}

	@Override
	public EvaluationProjetDto findEvaluationProjetByIdProjet(Long id) {
		EvaluationProjet evaluationProjet=evaluationProjetDao.findEvaluationProjetByIdProjet(id);
		EvaluationProjetDto evaluationProjetDto=new EvaluationProjetDto();
		if(	evaluationProjet != null)	mapper.map( evaluationProjet,evaluationProjetDto);	else evaluationProjetDto= null;
			
		return evaluationProjetDto;
		
	}
   
    

	
}
