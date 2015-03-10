package dz.gov.mesrs.sii.commons.data.dao.impl.referentiel;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefCoordonneeDao;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefCoordonnee;
import dz.gov.mesrs.sii.commons.data.util.UtilConstant;

@Service ("refCoordonneeDaoImpl")
public class RefCoordonneeDaoImpl implements RefCoordonneeDao {

	private static final Logger log = LoggerFactory.getLogger(RefCoordonneeDaoImpl.class.getName());

	@PersistenceContext
	private EntityManager entityManager;

	/* (non-Javadoc)
	 * @see dz.gov.mesrs.sii.referentiel.business.persistence.jpa.impl.RefCoordonneeDao#persist(dz.gov.mesrs.sii.referentiel.business.model.bo.RefCoordonnee)
	 */
	@Override
	public void persist(RefCoordonnee transientInstance) {
		log.debug("persisting RefCoordonnee instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist RefCoordonnee successful");
		} catch (RuntimeException re) {
			log.error("persist RefCoordonnee failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see dz.gov.mesrs.sii.referentiel.business.persistence.jpa.impl.RefCoordonneeDao#remove(dz.gov.mesrs.sii.referentiel.business.model.bo.RefCoordonnee)
	 */
	@Override
	public void remove(RefCoordonnee persistentInstance) {
		log.debug("removing RefCoordonnee instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see dz.gov.mesrs.sii.referentiel.business.persistence.jpa.impl.RefCoordonneeDao#merge(dz.gov.mesrs.sii.referentiel.business.model.bo.RefCoordonnee)
	 */
	@Override
	public RefCoordonnee merge(RefCoordonnee detachedInstance) {
		log.debug("merging RefCoordonnee instance");
		try {
			RefCoordonnee result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see dz.gov.mesrs.sii.referentiel.business.persistence.jpa.impl.RefCoordonneeDao#findById(int)
	 */
	@Override
	public RefCoordonnee findById(int id) {
		log.debug("getting RefCoordonnee instance with id: " + id);
		try {
			RefCoordonnee instance = entityManager
					.find(RefCoordonnee.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* get RefCoordonnee collection for an individu with id
	 * @param identifiant of {@link RefIndividu}
	 * @@return RefCoordonnee collection
	 * @see dz.gov.mesrs.sii.referentiel.business.persistence.RefCoordonneeDao#findByRefIndividuId(java.lang.String)
	 */
	@Override
	public List<RefCoordonnee> findByRefIndividuId(int type, int id) {
		log.debug("getting List of RefCoordonnee for individu instance with : " + id);
		try {
			
			StringBuilder request = new StringBuilder(
					"select r from RefCoordonnee r ");
			request.append("where r.refIndividu.id =:id ");
			request.append("and r.typeCoordonnee = " +type);
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("id", id);
			List<RefCoordonnee> resultList = (List<RefCoordonnee>) query
					.getResultList();
			log.debug("findByRefIndividuId successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findByRefIndividuId failed", re);
			throw re;
		}
		
	}

	/* get RefCoordonnee collection for a structure with id
	 * @param identifiant of {@link RefStructure}
	 * @@return RefCoordonnee collection
	 * @see dz.gov.mesrs.sii.referentiel.business.persistence.RefCoordonneeDao#findByRefStructureId(java.lang.String)
	 */
	@Override
	public List<RefCoordonnee> findByRefStructureId(int type, int id) {
		log.debug("getting List of RefCoordonnee for structure instance with : " + id);
		try {
			
			StringBuilder request = new StringBuilder(
					"select r from RefCoordonnee r ");
			request.append("where r.refStructure.id =:id ");
			request.append("and r.typeCoordonnee = " +type);
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("id", id);
			List<RefCoordonnee> resultList = (List<RefCoordonnee>) query
					.getResultList();
			log.debug("findByRefStructureId successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findByRefStructureId failed", re);
			throw re;
		}
	}
	
	/* get RefCoordonnee collection for a Lieu with id
	 * @param identifiant of {@link RefLieu}
	 * @@return RefCoordonnee collection
	 * @see dz.gov.mesrs.sii.referentiel.business.persistence.RefCoordonneeDao#findByRefLieuId(java.lang.String)
	 */
	@Override
	public List<RefCoordonnee> findByRefLieuId(int type, int id) {
		log.debug("getting List of RefCoordonnee for Lieu instance with : " + id);
		try {
			
			StringBuilder request = new StringBuilder(
					"select r from RefCoordonnee r ");
			request.append("where r.refLieu.id =:id ");
			request.append("and r.typeCoordonnee = " +type);
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("id", id);
			List<RefCoordonnee> resultList = (List<RefCoordonnee>) query
					.getResultList();
			log.debug("findByRefLieuId successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findByRefLieuId failed", re);
			throw re;
		}
	}

	@Override
	public List<RefCoordonnee> findByRefEtablissementId(int type,
			int id) {
		log.debug("getting List of RefCoordonnee for etablissement instance with : " + id);
		try {
			
			StringBuilder request = new StringBuilder(
					"select r from RefCoordonnee r ");
			request.append("where r.refEtablissement.id =:id ");
			request.append("and r.typeCoordonnee = " +type);
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("id", id);
			List<RefCoordonnee> resultList = (List<RefCoordonnee>) query
					.getResultList();
			log.debug("findByRefEtablissementId successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findByRefEtablissementId failed", re);
			throw re;
		}
	}

	@Override
	public List<RefCoordonnee> findByRefIndividuId(int id) {
		log.debug("getting List of RefCoordonnee for individu instance with : " + id);
		try {
			
			StringBuilder request = new StringBuilder(
					"select r from RefCoordonnee r ");
			request.append("where r.refIndividu.id =:id ");
			request.append(" order by r.typeCoordonnee  ");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("id", id);
			List<RefCoordonnee> resultList = (List<RefCoordonnee>) query
					.getResultList();
			log.debug("findByRefIndividuId successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findByRefIndividuId failed", re);
			throw re;
		}
	}

	@Override
	public List<RefCoordonnee> findByRefStructureId(int id) {
		log.debug("getting List of RefCoordonnee for structure instance with : " + id);
		try {
			
			StringBuilder request = new StringBuilder(
					"select r from RefCoordonnee r ");
			request.append("where r.refStructure.id =:id ");
			request.append(" order by r.typeCoordonnee  ");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("id", id);
			List<RefCoordonnee> resultList = (List<RefCoordonnee>) query
					.getResultList();
			log.debug("findByRefStructureId successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findByRefStructureId failed", re);
			throw re;
		}
	}

	@Override
	public List<RefCoordonnee> findByRefLieuId(int id) {
		log.debug("getting List of RefCoordonnee for Lieu instance with : " + id);
		try {
			
			StringBuilder request = new StringBuilder(
					"select r from RefCoordonnee r ");
			request.append("where r.refLieu.id =:id ");
			request.append(" order by r.typeCoordonnee  ");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("id", id);
			List<RefCoordonnee> resultList = (List<RefCoordonnee>) query
					.getResultList();
			log.debug("findByRefLieuId successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findByRefLieuId failed", re);
			throw re;
		}
	}

	@Override
	public List<RefCoordonnee> findByRefEtablissementId(int id) {
		log.debug("getting List of RefCoordonnee for etablissement instance with : " + id);
		try {
			
			StringBuilder request = new StringBuilder(
					"select r from RefCoordonnee r ");
			request.append("where r.refEtablissement.id = " + UtilConstant.quotedString(String.valueOf(id)));
			request.append(" order by r.typeCoordonnee  ");
			Query query = entityManager.createQuery(new String(request));
			//query.setParameter("id", id);
			List<RefCoordonnee> resultList = (List<RefCoordonnee>) query
					.getResultList();
			log.debug("findByRefEtablissementId successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findByRefEtablissementId failed", re);
			throw re;
		}
	}
}
