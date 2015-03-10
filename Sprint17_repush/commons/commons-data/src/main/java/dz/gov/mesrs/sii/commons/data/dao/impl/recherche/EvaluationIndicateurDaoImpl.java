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
import dz.gov.mesrs.sii.commons.data.dao.recherche.EvaluationIndicateurDao;
import dz.gov.mesrs.sii.commons.data.model.recherche.EvaluationIndicateur;



/**
 * @author rlaib  on : 25 janv. 2015  11:14:12
 */
@Service ("evaluationIndicDao")
public class EvaluationIndicateurDaoImpl extends GenericFveDaoImpl<EvaluationIndicateur> implements EvaluationIndicateurDao {

	
	private static final Log log = LogFactory.getLog(EvaluationIndicateurDaoImpl.class);
	/**
	 * [ProgrammeDaoImpl.findByCodeType] Method 
	 * Overridden By : @author sramdane  on : 25 janv. 2015  11:14:12
	 * @param codeTypeProgamme
	 * @return
	 */

	@SuppressWarnings("unchecked")
	@Override
	public List<EvaluationIndicateur> findListEvaluationIndicateurByIdProjet(Long id) {
		Query query= entityManager.createQuery("select m  from EvaluationIndicateur m where m.evaluationProjet.projet.id = :id").setParameter("id", id);
		
		return query.getResultList();
	}

	
	
		
	
	

	

	
}
