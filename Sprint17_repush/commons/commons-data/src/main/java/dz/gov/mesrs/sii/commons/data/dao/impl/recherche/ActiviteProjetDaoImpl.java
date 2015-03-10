/**
 * [dz.gov.mesrs.sii.commons.data.dao.impl.recherche.ProgrammeDaoImpl.java] 
 * @author rlaib on : 25 janv. 2015  11:14:12
 */
package dz.gov.mesrs.sii.commons.data.dao.impl.recherche;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.commons.data.dao.impl.fve.scolarite.GenericFveDaoImpl;
import dz.gov.mesrs.sii.commons.data.dao.recherche.ActiviteProjetDao;
import dz.gov.mesrs.sii.commons.data.model.recherche.ActiviteProje;
import dz.gov.mesrs.sii.commons.data.model.recherche.ActiviteProjet;



/**
 * @author rlaib  on : 25 janv. 2015  11:14:12
 */
@Service ("actProjDao")
public class ActiviteProjetDaoImpl extends GenericFveDaoImpl<ActiviteProjet> implements ActiviteProjetDao {

	
	private static final Log log = LogFactory.getLog(ActiviteProjetDaoImpl.class);
	/**
	 * [ProgrammeDaoImpl.findByCodeType] Method 
	 * Overridden By : @author sramdane  on : 25 janv. 2015  11:14:12
	 * @param codeTypeProgamme
	 * @return
	 */

	@SuppressWarnings("unchecked")
	@Override
	public List<ActiviteProjet> finListActiviteByProjet(Long projetid) {	
		Query query = entityManager.createQuery("select a from ActiviteProjet a where a.projet.id = :projetid").setParameter("projetid", projetid);
		if( query.getResultList() == null) return new ArrayList<ActiviteProjet>();
		else return query.getResultList();
	}
	
	@Override
	public void saveActiviteProjet(ActiviteProjet activiteProjet){
		ActiviteProje activiteP= new ActiviteProje();
		
	//	activiteP.setDescription("description");
		entityManager.persist(activiteP);
		if(activiteProjet!=null)entityManager.persist(activiteProjet);
		
		
		
	}

	@Override
	public void removeactiviteProjet(Long activiteProjetId) {
		ActiviteProjet activiteProjet=entityManager.find(ActiviteProjet.class, activiteProjetId);
		if(activiteProjet!=null){
			entityManager.merge(activiteProjet);
			entityManager.remove(activiteProjet);
		
		}
		
	}
	

	

	
}
