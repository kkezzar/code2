package dz.gov.mesrs.sii.commons.data.dao.impl.fve.offreformation;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.fve.offreformation.OuvertureOffreFormationDao;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.AnneeAcademique;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.Cycle;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.OffreFormation;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.OuvertureOffreFormation;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefDomaineLMD;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefEtablissement;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefFiliereLmd;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefSpecialiteLmd;

/**
 * @author BELDI Jamel on : 12 mai 2014 14:17:00
 */
@Repository("ouvertureOffreFormationDao")
public class OuvertureOffreFormationDaoImpl implements
		OuvertureOffreFormationDao {

	private static final Logger log = LoggerFactory.getLogger(OuvertureOffreFormationDaoImpl.class.getName());

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.OuvertureOffreFormationDao#persist(dz.gov.mesrs.sii.OuvertureOffreFormationService.business.model.bo.OuvertureOffreFormation)
	 * 
	 * @param transientInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(OuvertureOffreFormation transientInstance) {
		log.debug("persisting OuvertureOffreFormation instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.OuvertureOffreFormationDao#remove(dz.gov.mesrs.sii.OuvertureOffreFormationService.business.model.bo.OuvertureOffreFormation)
	 * @param persistentInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(OuvertureOffreFormation persistentInstance) {
		log.debug("removing OuvertureOffreFormation instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.OuvertureOffreFormationDao#merge(dz.gov.mesrs.sii.OuvertureOffreFormationService.business.model.bo.OuvertureOffreFormation)
	 * @param detachedInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public OuvertureOffreFormation merge(
			OuvertureOffreFormation detachedInstance) {
		log.debug("merging OuvertureOffreFormation instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.NOuvertureOffreFormationDao#findById(int)
	 * @param id
	 */
	@Override
	public OuvertureOffreFormation findById(int id) {
		log.debug("getting OuvertureOffreFormation instance with id: " + id);
		try {
			log.debug("get successful");
			return entityManager.find(OuvertureOffreFormation.class, id);
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/**
	 * 
	 * @param query
	 */

	@SuppressWarnings("unchecked")
	@Override
	public List<OuvertureOffreFormation> findByQuery(String query) {
		return entityManager.createQuery(query).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OuvertureOffreFormation> findAll() {
		log.debug("getting all OuvertureOffreFormation instances");
		try {
			Query query = entityManager
					.createQuery("from OuvertureOffreFormation ouvertureOffreFormation");
			log.debug("findAll 'OuvertureOffreFormation' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'OuvertureOffreFormation' failed", re);
			return new ArrayList<OuvertureOffreFormation>();
		}
	}

	@Override
	public List<OuvertureOffreFormation> findAdvanced(
			OuvertureOffreFormation searchBo) {

		try {
			if (searchBo != null) {
				CriteriaBuilder cb = entityManager.getCriteriaBuilder();
				CriteriaQuery<OuvertureOffreFormation> c = cb
						.createQuery(OuvertureOffreFormation.class);
				Root<OuvertureOffreFormation> ouvertureOf = c
						.from(OuvertureOffreFormation.class);

				c.select(ouvertureOf).distinct(true);
				Predicate predicate = null;

				if (searchBo.getOffreFormation() != null) {
					Join<OuvertureOffreFormation, OffreFormation> offreFormation = ouvertureOf
							.join("offreFormation");
					if (searchBo.getOffreFormation().getId() != 0) {
						predicate = cb.equal(offreFormation.get("id"), searchBo
								.getOffreFormation().getId());
					}
					if (searchBo.getOffreFormation().getRefDomaineLMD() != null) {
						Join<OffreFormation, RefDomaineLMD> offreFormationDomaine = offreFormation
								.join("refDomaineLMD");
						if (predicate == null) {
							predicate = cb.equal(offreFormationDomaine
									.get("identifiant"), searchBo
									.getOffreFormation().getRefDomaineLMD().getIdentifiant());
						} else {
							predicate = cb.and(predicate, cb.equal(offreFormationDomaine
									.get("identifiant"), searchBo
									.getOffreFormation().getRefDomaineLMD().getIdentifiant()));
						}
					}
					if (searchBo.getOffreFormation().getRefFiliereLmd() != null) {
						Join<OffreFormation, RefFiliereLmd> offreFormationFiliere = offreFormation
								.join("refFiliereLmd");
						if (predicate == null) {
							predicate = cb.equal(offreFormationFiliere
									.get("identifiant"), searchBo
									.getOffreFormation().getRefFiliereLmd().getIdentifiant());
						} else {
							predicate = cb.and(predicate, cb.equal(offreFormationFiliere
									.get("identifiant"), searchBo
									.getOffreFormation().getRefFiliereLmd().getIdentifiant()));
						}
					}
					if (searchBo.getOffreFormation().getRefSpecialiteLmd() != null) {
						Join<OffreFormation, RefSpecialiteLmd> offreFormationSpecialite = offreFormation
								.join("refSpecialiteLmd");
						if (predicate == null) {
							predicate = cb
									.equal(offreFormationSpecialite
											.get("identifiant"), searchBo
											.getOffreFormation()
											.getRefSpecialiteLmd().getIdentifiant());
						} else {
							predicate = cb.and(predicate, cb
									.equal(offreFormationSpecialite
											.get("identifiant"), searchBo
											.getOffreFormation()
											.getRefSpecialiteLmd().getIdentifiant()));
						}
					}
                    if (searchBo.getOffreFormation().getCycle() != null) {
                    		Join<OffreFormation, Cycle> cycle = offreFormation.join("cycle");
                        			if (predicate == null) {
                        			    predicate = cb.equal(cycle.get("id"), searchBo.getOffreFormation().getCycle().getId());
                        			} else {
                        			    predicate = cb.and(predicate,cb.equal(cycle.get("id"), searchBo.getOffreFormation().getCycle().getId()));
                        			}
                        
                    }
					
				}
				if (searchBo.getAnneeAcademique() != null) {
					Join<OuvertureOffreFormation, AnneeAcademique> anneeAcademique = ouvertureOf
							.join("anneeAcademique");
					if (predicate == null) {
						predicate = cb.equal(anneeAcademique.get("id"),
								searchBo.getAnneeAcademique().getId());
					} else {
						predicate = cb.and(predicate, cb.equal(anneeAcademique
								.get("id"), searchBo.getAnneeAcademique()
								.getId()));
					}
				}
				
				if (searchBo.getRefEtablissement() != null) {
					Join<OuvertureOffreFormation, RefEtablissement> refEtablissement = ouvertureOf
							.join("refEtablissement");
					if (predicate == null) {
						predicate = cb.equal(refEtablissement.get("id"),
								searchBo.getRefEtablissement().getId());
					} else {
						predicate = cb.and(predicate, cb.equal(refEtablissement
								.get("id"), searchBo.getRefEtablissement()
								.getId()));
					}
				}
				if (predicate == null) {
					predicate = cb.equal(ouvertureOf.get("estOuverte"),
							searchBo.isEstOuverte());
				} else {
					predicate = cb.and(
							predicate,
							cb.equal(ouvertureOf.get("estOuverte"),
									searchBo.isEstOuverte()));
				}

				if (predicate != null) {
					c.where(predicate);
				}

				TypedQuery<OuvertureOffreFormation> q = entityManager
						.createQuery(c);
				List<OuvertureOffreFormation> resultList = (List<OuvertureOffreFormation>) q
						.getResultList();
				if (resultList != null && !resultList.isEmpty()) {
					return resultList;
				}
			}

			return null;

		} catch (RuntimeException re) {

			log.error("findAdvanced  failed", re);
			throw re;
		}
	}

	@Override
	public List<OuvertureOffreFormation> findAdvanced(
			String refCodeEtablissement, String refCodeSpecialite,
			Integer idAnneeAcademique, Boolean estOuverte) {

		try {

			CriteriaBuilder _cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<OuvertureOffreFormation> _cq = _cb
					.createQuery(OuvertureOffreFormation.class);
			Root<OuvertureOffreFormation> ouvertureOf = _cq
					.from(OuvertureOffreFormation.class);

			_cq.select(ouvertureOf).distinct(true);
			Predicate _predicate = null;

			if (refCodeSpecialite != null) {
				Join<OuvertureOffreFormation, OffreFormation> offreFormation = ouvertureOf
						.join("offreFormation");
				_predicate = _cb.equal(offreFormation.get("refCodeSpecialite"),
						refCodeSpecialite);
			}

			if (refCodeEtablissement != null) {
				if (_predicate == null) {
					_predicate = _cb.equal(
							ouvertureOf.get("refCodeEtablissement"),
							refCodeEtablissement);
				} else {
					_predicate = _cb.and(_predicate, _cb.equal(
							ouvertureOf.get("refCodeEtablissement"),
							refCodeEtablissement));
				}
			}

			if (idAnneeAcademique != null) {
				Join<OuvertureOffreFormation, AnneeAcademique> anneeAcademique = ouvertureOf
						.join("anneeAcademique");
				if (_predicate == null) {
					_predicate = _cb.equal(anneeAcademique.get("id"),
							idAnneeAcademique);
				} else {
					_predicate = _cb.and(_predicate, _cb.equal(
							anneeAcademique.get("id"), idAnneeAcademique));
				}
			}

			if (estOuverte != null) {
				if (_predicate == null) {
					_predicate = _cb.equal(ouvertureOf.get("estOuverte"),
							estOuverte);
				} else {
					_predicate = _cb.and(_predicate, _cb.equal(
							ouvertureOf.get("estOuverte"), estOuverte));
				}
			}

			if (_predicate != null) {
				_cq.where(_predicate);
			}

			TypedQuery<OuvertureOffreFormation> _tq = entityManager
					.createQuery(_cq);

			return (List<OuvertureOffreFormation>) _tq.getResultList();

		} catch (RuntimeException re) {

			log.error("findAdvanced 2  failed", re);

			return new ArrayList<OuvertureOffreFormation>(0);
		}

	}
}
