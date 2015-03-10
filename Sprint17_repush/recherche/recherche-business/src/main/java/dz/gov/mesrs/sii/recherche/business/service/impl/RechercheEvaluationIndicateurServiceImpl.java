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

import dz.gov.mesrs.sii.commons.data.dao.recherche.EvaluationIndicateurDao;
import dz.gov.mesrs.sii.commons.data.model.recherche.EvaluationIndicateur;
import dz.gov.mesrs.sii.commons.data.model.recherche.EvaluationProjet;
import dz.gov.mesrs.sii.commons.data.model.recherche.IndEvaluation;
import dz.gov.mesrs.sii.recherche.business.model.dto.EvaluationIndicateurDto;
import dz.gov.mesrs.sii.recherche.business.model.dto.EvaluationProjetDto;
import dz.gov.mesrs.sii.recherche.business.model.dto.IndEvaluationDto;
import dz.gov.mesrs.sii.recherche.business.service.RechercheEvaluationIndicateurService;

/**
 * @author sRamdane  on : 08 fev 2015   17:35:31
 */
@Service("evaluationIndicateurService")
public class RechercheEvaluationIndicateurServiceImpl implements RechercheEvaluationIndicateurService {

	@Autowired
	@Qualifier ("evaluationIndicDao")
	private EvaluationIndicateurDao evaluationIndicateurDao;
	
	private static final Log log = LogFactory.getLog(RechercheEvaluationIndicateurServiceImpl.class);
	
    @Autowired
	@Qualifier("mapper")
	private DozerBeanMapper mapper;

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public EvaluationIndicateurDto saveEvaluationIndicateur(EvaluationIndicateurDto evaluationIndicateurDto) {
		EvaluationIndicateur evaluationIndicateur =mapper.map(evaluationIndicateurDto, EvaluationIndicateur.class);
		if(evaluationIndicateur.getId()==null) evaluationIndicateurDao.persist(evaluationIndicateur);
		else evaluationIndicateurDao.merge(evaluationIndicateur);
		EvaluationIndicateurDto evaluationIndicDto = new EvaluationIndicateurDto();
		mapper.map(evaluationIndicateur, evaluationIndicDto);
		return evaluationIndicateurDto;
	}

	@Override
	public List<EvaluationIndicateurDto> findListEvaluationIndicateurByIdProjet(Long id) {
		List<EvaluationIndicateur> listEvaluationIndicateur=evaluationIndicateurDao.findListEvaluationIndicateurByIdProjet(id);
		List<EvaluationIndicateurDto> listEvaluationIndicateurDto=new ArrayList<EvaluationIndicateurDto>();
		if(listEvaluationIndicateur!=null)if(!listEvaluationIndicateur.isEmpty()){
			for(EvaluationIndicateur ie:listEvaluationIndicateur){
				EvaluationIndicateurDto ieDto = new EvaluationIndicateurDto();
				mapper.map( ie,ieDto);	listEvaluationIndicateurDto.add(ieDto);
			}
		}
		return listEvaluationIndicateurDto;
	}

	@Override
	public EvaluationIndicateurDto findById(Long evaluationIndicateurDtoId) {
		EvaluationIndicateur ie =evaluationIndicateurDao.findById(evaluationIndicateurDtoId);
		EvaluationIndicateurDto ieDto = new EvaluationIndicateurDto();
		 mapper.map( ie,ieDto);
		return ieDto;
	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public EvaluationIndicateurDto saveIndEvaluation(EvaluationIndicateurDto evaluationIndicateurDto) {
		EvaluationIndicateur evaluationIndicateur = mapper.map(evaluationIndicateurDto, EvaluationIndicateur.class);
		if(evaluationIndicateurDto.getId()==null)   evaluationIndicateurDao.persist(evaluationIndicateur);
		else evaluationIndicateurDao.merge(evaluationIndicateur);		
		return evaluationIndicateurDto;
	}

	
   
    

	
}
