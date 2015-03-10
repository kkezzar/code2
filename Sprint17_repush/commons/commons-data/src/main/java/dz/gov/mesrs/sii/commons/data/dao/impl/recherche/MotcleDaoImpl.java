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
import dz.gov.mesrs.sii.commons.data.dao.recherche.MotcleDao;
import dz.gov.mesrs.sii.commons.data.model.recherche.Motcle;

/**
 * @author rlaib  on : 25 janv. 2015  11:14:12
 */
@Service ("motcleDao")
public class MotcleDaoImpl extends GenericFveDaoImpl<Motcle> implements MotcleDao {

	
	private static final Log log = LogFactory.getLog(MotcleDaoImpl.class);
	/**
	 * [ProgrammeDaoImpl.findByCodeType] Method 
	 * Overridden By : @author sramdane  on : 25 janv. 2015  11:14:12
	 * @param codeTypeProgamme
	 * @return
	 */

	@SuppressWarnings("unchecked")
	@Override
	public List<Motcle> findListMotsClesByIdProjet(Long id) {
		Query query= entityManager.createQuery("select m  from Motcle m where m.projet.id = :id").setParameter("id", id);
		return query.getResultList();
	}
	

	

	
}
