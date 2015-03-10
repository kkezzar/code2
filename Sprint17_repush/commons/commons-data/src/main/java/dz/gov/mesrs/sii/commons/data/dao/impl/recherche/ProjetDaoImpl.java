/**
 * [dz.gov.mesrs.sii.commons.data.dao.impl.recherche.ProgrammeDaoImpl.java] 
 * @author rlaib on : 25 janv. 2015  11:14:12
 */
package dz.gov.mesrs.sii.commons.data.dao.impl.recherche;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.commons.data.dao.impl.fve.scolarite.GenericFveDaoImpl;
import dz.gov.mesrs.sii.commons.data.dao.recherche.ProjetDao;
import dz.gov.mesrs.sii.commons.data.model.bpm.SituationEntite;
import dz.gov.mesrs.sii.commons.data.model.recherche.ActiviteProjet;
import dz.gov.mesrs.sii.commons.data.model.recherche.Programme;
import dz.gov.mesrs.sii.commons.data.model.recherche.Projet;
import dz.gov.mesrs.sii.commons.data.model.referentiel.Nomenclature;

/**
 * @author rlaib  on : 25 janv. 2015  11:14:12
 */
@Service ("projetDao")
public class ProjetDaoImpl extends GenericFveDaoImpl<Projet> implements ProjetDao {

	
	private static final Log log = LogFactory.getLog(ProjetDaoImpl.class);
	/**
	 * [ProgrammeDaoImpl.findByCodeType] Method 
	 * Overridden By : @author sramdane  on : 25 janv. 2015  11:14:12
	 * @param codeTypeProgamme
	 * @return
	 */
	

	
	@SuppressWarnings("unchecked")
	@Override
	public List<Projet> findByCodeProjet(String codeProjet,Integer idSituation) {
		try {
			
			Query query = entityManager.createQuery("select a from Projet a where a.situation.id = :idSituation").setParameter("idSituation", idSituation);
			if( query.getResultList() == null) return new ArrayList<Projet>();
			else return query.getResultList();
			
		}
		catch (RuntimeException re) {

				log.error("findByCodeProjet 'Projet' failed", re);
				throw re;
		}
	}

	
	@Override
	public List<Projet> findByKeyWords(String keyWord) {
		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<Projet> c = cb.createQuery(Projet.class);
			Root<Projet> p = c.from(Projet.class);
			c.select(p).distinct(true);
			List<Predicate> criteria = new ArrayList<Predicate>();
			if(keyWord != null) {
				criteria.add(cb.like(cb.lower(p.<String>get("code")), "%" + keyWord.toLowerCase() + "%" ));
				criteria.add(cb.like(cb.lower(p.<String>get("intituleFr")), "%" + keyWord.toLowerCase() + "%" ));
				criteria.add(cb.like(cb.lower(p.<String>get("intituleAr")), "%" + keyWord.toLowerCase() + "%" ));
			}		
			
			c.where(cb.or(criteria.toArray(new Predicate[0])));			
			TypedQuery<Projet> q = entityManager.createQuery(c);
			List<Projet> result = q.getResultList();
				for(Projet entity: result){
					if(entityManager.contains(entity)){
							entityManager.refresh(entity);
					}
				}
				return result;
		}
		catch (RuntimeException re) {

				log.error("findByKeyWords 'Projet' failed", re);
				throw re;
		}
	
	}


	@Override
	public Projet saveProjet(Projet projet, String annee, String structure) {
		Projet projetcode = projet;
		projetcode.setCode(annee+structure+numseqCodeProjet(annee, structure));
		entityManager.persist(projetcode);
		
		
		return projetcode;
	}

private String numseqCodeProjet(String annee, String structure){
	Query query=entityManager.createQuery("select c.code from Projet c where SUBSTRING(c.code, 1, 4) like :annee and SUBSTRING(c.code, 5,LENGTH(c.code)-7) like :structure");
	query.setParameter("annee", annee).setParameter("structure", structure);
	@SuppressWarnings("unchecked")
	List<String> codes=query.getResultList();
	Integer num =0;
	String numStr="000";
	if(codes != null)if(!codes.isEmpty()){
		
		for(String code : codes){
		if(code!=null)if(code.length()>3){
			numStr = code.substring(code.length()-3, code.length());
			if(numStr != null){
				if(Integer.valueOf(numStr)>num) num=Integer.valueOf(numStr);
			}
		}
		}
	}
	if (num != null){
		num++;
		numStr=num.toString();
		while(numStr.length()<3)numStr= "0"+numStr;
	}
	
	 return numStr;
	
}




@SuppressWarnings("unchecked")
@Override
public List<ActiviteProjet> finListActiviteByProjet(Long projetid) {	
	Query query = entityManager.createQuery("select a from ActiviteProjet a where a.projet.id = :projetid").setParameter("projetid", projetid);
	if( query.getResultList() == null) return new ArrayList<ActiviteProjet>();
	else return query.getResultList();
}






}
