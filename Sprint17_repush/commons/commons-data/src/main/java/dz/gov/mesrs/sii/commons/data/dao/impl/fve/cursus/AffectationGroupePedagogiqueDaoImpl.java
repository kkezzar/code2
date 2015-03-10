/**
 * [dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.cursus.AffectationGroupePedagogiqueDaoImpl.java] 
 * @author MAKERRI Sofiane on : 22 juil. 2014  13:06:41
 */
package dz.gov.mesrs.sii.commons.data.dao.impl.fve.cursus;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;





import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.fve.cursus.AffectationGroupePedagogiqueDao;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.AffectationGroupePedagogique;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.DossierInscriptionAdministrative;

/**
 * @author MAKERRI Sofiane on : 22 juil. 2014 13:06:41
 */
@Repository("affectationGroupePedagogiqueDao")
@Transactional
public class AffectationGroupePedagogiqueDaoImpl implements
		AffectationGroupePedagogiqueDao {

	private static final Logger log = LoggerFactory.getLogger(AffectationGroupePedagogiqueDaoImpl.class.getName());


	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	/**
	 * [AffectationGroupePedagogiqueDaoImpl.AffectationGroupePedagogiqueDaoImpl(
	 * )] Constructor
	 * 
	 * @author MAKERRI Sofiane on : 22 juil. 2014 13:06:41
	 */
	public AffectationGroupePedagogiqueDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dz.gov.mesrs.sii.fve.business.persistence.cursus.
	 * AffectationGroupePedagogiqueDao
	 * #persist(dz.gov.mesrs.sii.fve.business.model
	 * .bo.cursus.AffectationGroupePedagogique)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(AffectationGroupePedagogique transientInstance) {
		log.debug("persisting CongeAcademique instance");
		try {
			entityManager.persist(transientInstance);
			entityManager.flush();
			entityManager.refresh(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dz.gov.mesrs.sii.fve.business.persistence.cursus.
	 * AffectationGroupePedagogiqueDao
	 * #remove(dz.gov.mesrs.sii.fve.business.model
	 * .bo.cursus.AffectationGroupePedagogique)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(AffectationGroupePedagogique persistentInstance) {
		log.debug("removing CongeAcademique instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dz.gov.mesrs.sii.fve.business.persistence.cursus.
	 * AffectationGroupePedagogiqueDao
	 * #merge(dz.gov.mesrs.sii.fve.business.model.
	 * bo.cursus.AffectationGroupePedagogique)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public AffectationGroupePedagogique merge(
			AffectationGroupePedagogique detachedInstance) {
		log.debug("merging CongeAcademique instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dz.gov.mesrs.sii.fve.business.persistence.cursus.
	 * AffectationGroupePedagogiqueDao#findById(int)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public AffectationGroupePedagogique findById(int id) {
		log.debug("getting CongeAcademique instance with id: " + id);
		try {
			log.debug("get successful");
			return entityManager.find(AffectationGroupePedagogique.class, id);
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dz.gov.mesrs.sii.fve.business.persistence.cursus.
	 * AffectationGroupePedagogiqueDao#findAll()
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public List<AffectationGroupePedagogique> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AffectationGroupePedagogique> findByGroupePedagogiqueId(int gpId) {
		if (gpId == 0) {
			return null;
		}
		log.debug("findByGroupePedagogiqueId with  id: " + gpId);
		try {
			StringBuilder request = new StringBuilder(
					"select r from AffectationGroupePedagogique r ");

			request.append(" where r.groupePedagogique.id = :gpId ");
			request.append(" order by r.dossierInscriptionAdministrative.dossierEtudiant.numeroMatricule ");
			TypedQuery<AffectationGroupePedagogique> query = entityManager
					.createQuery(new String(request),
							AffectationGroupePedagogique.class);
			query.setParameter("gpId", gpId);
			List<AffectationGroupePedagogique> resultList = (List<AffectationGroupePedagogique>) query
					.getResultList();
			log.debug("findByGroupePedagogiqueId successful");
			entityManager.flush();
			return resultList;

		} catch (RuntimeException re) {
			log.error("findByGroupePedagogiqueId failed", re);
			throw re;
		}
	}

	@Override
	public List<AffectationGroupePedagogique> findOnlyBySectionId(int sectionId) {
		if (sectionId == 0) {
			return null;
		}
		log.debug("findOnlyBySectionId with  id: " + sectionId);
		try {
			StringBuilder request = new StringBuilder(
					"select r from AffectationGroupePedagogique r ");

			request.append(" where r.groupePedagogique.id =:sectionId ");
			request.append(" and r.dossierInscriptionAdministrative.id NOT IN ");
			request.append(" (select g.dossierInscriptionAdministrative.id from AffectationGroupePedagogique g");
			request.append(" where g.groupePedagogique.section.id =:sectionId )");
			request.append(" order by r.dossierInscriptionAdministrative.dossierEtudiant.numeroMatricule ");
			TypedQuery<AffectationGroupePedagogique> query = entityManager
					.createQuery(new String(request),
							AffectationGroupePedagogique.class);
			query.setParameter("sectionId", sectionId);
			List<AffectationGroupePedagogique> resultList = (List<AffectationGroupePedagogique>) query
					.getResultList();
			log.debug("findByGroupePedagogiqueId successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findOnlyBySectionId failed", re);
			throw re;
		}
	}

	@Override
	public List<AffectationGroupePedagogique> findAffectationInGroupe(
			Integer diaId, Integer sectionId) {
		if (sectionId == null) {
			return null;
		}
		log.debug("findAffectationInGroupe with  id: " + sectionId);
		try {
			StringBuilder request = new StringBuilder(
					"select r from AffectationGroupePedagogique r ");

			request.append(" where r.groupePedagogique.section.id =:sectionId ");
			if (diaId != null) {
				request.append(" and r.dossierInscriptionAdministrative.id = "
						+ diaId);
			}
			request.append(" order by r.dossierInscriptionAdministrative.dossierEtudiant.numeroMatricule ");
			TypedQuery<AffectationGroupePedagogique> query = entityManager
					.createQuery(new String(request),
							AffectationGroupePedagogique.class);
			query.setParameter("sectionId", sectionId);
			List<AffectationGroupePedagogique> resultList = (List<AffectationGroupePedagogique>) query
					.getResultList();
			log.debug("findAffectationInGroupe successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findAffectationInGroupe failed", re);
			throw re;
		}
	}

	@Override
	public List<AffectationGroupePedagogique> findByAssociationGpId(
			Integer assocId) {
		if (assocId == null) {
			return null;
		}
		log.debug("findByAssociationGpId with  id: " + assocId);
		try {
			StringBuilder request = new StringBuilder(
					"select r from AffectationGroupePedagogique r, AssociationGroupePedagogique a ");

			request.append(" where r.groupePedagogique.id = a.groupePedagogique.id ");
			request.append(" and a.id = " + assocId);
			TypedQuery<AffectationGroupePedagogique> query = entityManager
					.createQuery(new String(request),
							AffectationGroupePedagogique.class);
			List<AffectationGroupePedagogique> resultList = (List<AffectationGroupePedagogique>) query
					.getResultList();
			log.debug("findByAssociationGpId successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findByAssociationGpId failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see dz.gov.mesrs.sii.commons.data.dao.fve.cursus.AffectationGroupePedagogiqueDao#findEtudiantsOfSection(java.lang.Integer)
	 */
	@Override
	public List<DossierInscriptionAdministrative> findEtudiantsOfSection(
			Integer idSection) {
		if (idSection == null) {
			return null;
		}
		log.debug("findEtudiantsOfSection with  id: " + idSection);
		try {
			StringBuilder request = new StringBuilder(
					"select r.dossierInscriptionAdministrative from AffectationGroupePedagogique r ");

			request.append(" where r.groupePedagogique.id =  " + idSection);
			TypedQuery<DossierInscriptionAdministrative> query = entityManager
					.createQuery(new String(request),
							DossierInscriptionAdministrative.class);
			List<DossierInscriptionAdministrative> resultList = (List<DossierInscriptionAdministrative>) query
					.getResultList();
			log.debug("findEtudiantsOfSection successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findEtudiantsOfSection failed", re);
			throw re;
		}
	}

}
