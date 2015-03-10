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
import dz.gov.mesrs.sii.commons.data.dao.recherche.IndEvaluationDao;
import dz.gov.mesrs.sii.commons.data.model.recherche.IndEvaluation;
import dz.gov.mesrs.sii.commons.data.model.recherche.Motcle;

/**
 * @author rlaib  on : 25 janv. 2015  11:14:12
 */
@Service ("indEvaluationDao")
public class IndEvaluationDaoImpl extends GenericFveDaoImpl<IndEvaluation> implements IndEvaluationDao {

	
	private static final Log log = LogFactory.getLog(IndEvaluationDaoImpl.class);
	/**
	 * [ProgrammeDaoImpl.findByCodeType] Method 
	 * Overridden By : @author sramdane  on : 25 janv. 2015  11:14:12
	 * @param codeTypeProgamme
	 * @return
	 */
@SuppressWarnings("unchecked")
	@Override	
	public List<IndEvaluation> findListMotsClesByIdProjet(Long id) {		
			Query query= entityManager.createQuery("select m  from IndEvaluation m where m.projet.id = :id").setParameter("id", id);
			return query.getResultList();
		}

	

	
}
