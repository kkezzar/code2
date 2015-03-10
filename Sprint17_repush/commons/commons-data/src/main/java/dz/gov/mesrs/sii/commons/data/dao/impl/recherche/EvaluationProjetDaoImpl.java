/**
 * [dz.gov.mesrs.sii.commons.data.dao.impl.recherche.ProgrammeDaoImpl.java] 
 * @author rlaib on : 25 janv. 2015  11:14:12
 */
package dz.gov.mesrs.sii.commons.data.dao.impl.recherche;

import java.util.List;

import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.commons.data.dao.impl.fve.scolarite.GenericFveDaoImpl;
import dz.gov.mesrs.sii.commons.data.dao.recherche.EvaluationProjetDao;
import dz.gov.mesrs.sii.commons.data.model.recherche.EtapeValidation;
import dz.gov.mesrs.sii.commons.data.model.recherche.EvaluationProjet;



/**
 * @author rlaib  on : 25 janv. 2015  11:14:12
 */
@Service ("evaluationProjDao")
public class EvaluationProjetDaoImpl extends GenericFveDaoImpl<EvaluationProjet> implements EvaluationProjetDao {

	
	private static final Log log = LogFactory.getLog(EvaluationProjetDaoImpl.class);
	/**
	 * [ProgrammeDaoImpl.findByCodeType] Method 
	 * Overridden By : @author sramdane  on : 25 janv. 2015  11:14:12
	 * @param codeTypeProgamme
	 * @return
	 */

	@Override
	public EvaluationProjet findEvaluationProjetByIdProjet(Long id) {
		Query query = entityManager.createQuery("select p from EvaluationProjet p where p.projet.id = :selectedProjet").setParameter("selectedProjet", id);		
		List<EvaluationProjet>  listEvaluationProjet=query.getResultList();
		if(listEvaluationProjet != null)if(!listEvaluationProjet.isEmpty())return listEvaluationProjet.get(0);
		else return null;
		else return null;
	}

	
	
		
	
	

	

	
}
