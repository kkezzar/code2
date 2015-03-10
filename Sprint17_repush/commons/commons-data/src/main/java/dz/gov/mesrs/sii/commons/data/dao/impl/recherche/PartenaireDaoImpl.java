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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.commons.data.dao.impl.fve.scolarite.GenericFveDaoImpl;
import dz.gov.mesrs.sii.commons.data.dao.recherche.PartenaireDao;
import dz.gov.mesrs.sii.commons.data.model.recherche.IndEvaluation;
import dz.gov.mesrs.sii.commons.data.model.recherche.Partenaire;
import dz.gov.mesrs.sii.commons.data.model.recherche.ProjetPartenaire;
import dz.gov.mesrs.sii.commons.data.model.recherche.Structure;

/**
 * @author rlaib  on : 16 déc. 2014  14:07:53
 */
@Service ("partenaireDao")
public class PartenaireDaoImpl extends GenericFveDaoImpl<Partenaire> implements
			PartenaireDao {
	
	private static final Logger log = LoggerFactory.getLogger(PartenaireDaoImpl.class.getName());

	/**
	 * [PartenaireDao.findStructureRechercheParteanires] Method 
	 * Overridden By : @author rlaib  on : 24 déc. 2014  09:01:03
	 * @param idStructure
	 * @return
	 */
	@Override
	public List<Partenaire> findStructureRecherchePartenaires(
			Long idStructure) {
		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<Partenaire> c = cb.createQuery(Partenaire.class);
			Root<Partenaire> p = c.from(Partenaire.class);
			Join<Partenaire, Structure> ps = p.join("structure");
			c.select(p).distinct(true);
			List<Predicate> criteria = new ArrayList<Predicate>();
			if(idStructure != null) {
				ParameterExpression<Long> param = cb.parameter(Long.class, "idStructure");
				criteria.add(cb.equal(ps.get("id"), param));
			}
			c.where(cb.and(criteria.toArray(new Predicate[0])));
			TypedQuery<Partenaire> q = entityManager.createQuery(c);
			if (idStructure != null) { 
				q.setParameter("idStructure", idStructure);
			}
			List<Partenaire> result = q.getResultList();
			for(Partenaire entity: result){
				if(entityManager.contains(entity)){
						entityManager.refresh(entity);
				}
			}
			return result;
	}
	catch (RuntimeException re) {

			log.error("findStructureRechercheParteanires 'Partenaire' failed", re);
			throw re;
	}
	}

	@Override
	public void saveProjetPartenaire(ProjetPartenaire projetPartenaire) {
		entityManager.persist(projetPartenaire);		
	}

	@Override
	public ProjetPartenaire mergeProjetPartenaire(
			ProjetPartenaire projetPartenaire) {
		entityManager.merge(projetPartenaire);		

		return null;
	}

	
	
	@SuppressWarnings("unchecked")
	@Override	
	public List<ProjetPartenaire> findListPartenaireByIdProjet(Long id) {		
			Query query= entityManager.createQuery("select m  from ProjetPartenaire m where m.projet.id = :id").setParameter("id", id);
			return query.getResultList();
		}

	@Override
	public void removeProjetPartenaire(Long partenaireId) {
		ProjetPartenaire projetPartenaire=entityManager.find(ProjetPartenaire.class, partenaireId);
		if(projetPartenaire!=null){entityManager.merge(projetPartenaire);entityManager.remove(projetPartenaire);}
		
	}


}
