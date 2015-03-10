package dz.gov.mesrs.sii.commons.data.dao.impl.referentiel;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefCompteDao;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefCompte;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefIndividu;
import dz.gov.mesrs.sii.commons.data.util.UtilConstant;

/**
 * Dao object for domain model class RefCompte.
 * 
 * @see dz.gov.mesrs.sii.RefCompteDto.business.model.bo.RefCompte
 * @author BELDI JAmel
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */

@Repository("refCompteDaoImpl")
@Transactional
public class RefCompteDaoImpl implements RefCompteDao {

	private static final Logger log = LoggerFactory.getLogger(RefCompteDaoImpl.class.getName());

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.RefCompteDao#persist(dz.gov.mesrs.sii.RefCompteDto.business.model.bo.RefCompte)
	 * 
	 * @param transientInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(RefCompte transientInstance) {
		log.debug("persisting RefCompte instance");
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

	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.RefCompteDao#remove(dz.gov.mesrs.sii.RefCompteDto.business.model.bo.RefCompte)
	 * @param persistentInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(RefCompte persistentInstance) {
		log.debug("removing RefCompte instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.RefCompteDao#merge(dz.gov.mesrs.sii.RefCompteDto.business.model.bo.RefCompte)
	 * @param detachedInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public RefCompte merge(RefCompte detachedInstance) {
		log.debug("merging RefCompte instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.NRefCompteDao#findById(int)
	 * @param id
	 */
	@Override
	@Transactional(readOnly = true)
	public RefCompte findById(int id) {
		log.debug("getting RefCompte instance with id: " + id);
		try {
			log.debug("get successful");
			return entityManager.find(RefCompte.class, id);
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
	@Transactional(readOnly = true)
	public List<RefCompte> findByQuery(String query) {
		return entityManager.createQuery(query).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<RefCompte> findAll() {
		log.debug("getting all RefCompte instances");
		try {
			Query query = entityManager.createQuery("from RefCompte refCompte");
			log.debug("findAll 'RefCompte' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'RefCompte' failed", re);
			return new ArrayList<RefCompte>();
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.referentiel.business.persistence.RefCompteDao#findGeneric(java.lang.String)
	 */
	@Override
	public List<RefCompte> findGeneric(String value) {

		log.debug("getting List of RefCompte instance with : " + value);
		List<RefCompte> resultList = new ArrayList<RefCompte>();
		try {
			int id;
			try {
				id = Integer.parseInt(value);
			} catch (NumberFormatException formatException) {
				id = 0;
			}
			if (value != null && !value.trim().isEmpty()) {
				value = "%" + value + "%";
				StringBuilder request = new StringBuilder(
						"select r from RefCompte r ");
				request.append("left join r.refIndividu as individu ");
				request.append("where r.id = :id ");
				request.append("or lower(r.nomUtilisateur) like :value ");
				request.append("or cast(r.dateDebut as text) like :value ");
				request.append("or cast(r.dateFin as text) like :value ");
				request.append("or lower(individu.nomArabe) like :value ");
				request.append("or lower(individu.nomLatin) like :value ");
				request.append("or lower(individu.prenomArabe) like :value ");
				request.append("or lower(individu.prenomLatin) like :value ");
				Query query = entityManager.createQuery(new String(request));
				query.setParameter("id", id);
				query.setParameter("value", value);
				resultList = (List<RefCompte>) query.getResultList();
				log.debug("findGeneric successful");
				return resultList;
			}
		} catch (RuntimeException re) {
			log.error("findGeneric failed", re);
			throw re;
		}
		return resultList;
	}

	/**
	 * @see dz.gov.mesrs.sii.referentiel.business.persistence.RefCompteDao#findAdvanced(dz.gov.mesrs.sii.referentiel.business.model.bo.RefCompte)
	 */
	@Override
	public List<RefCompte> findAdvanced(RefCompte refCompte) {
		List<RefCompte> resultList = new ArrayList<RefCompte>();
		log.debug("findAdvanced: getting List of refCompte instance : ");

		String firstOpr = UtilConstant.WHERE_SQL_STR;

		try {
			if (refCompte != null
					&& (((refCompte.getNomUtilisateur() != null) && (!refCompte
							.getNomUtilisateur().trim().isEmpty()))
							|| ((refCompte.getDateDebut() != null))
							|| ((refCompte.getDateFin() != null)) || ((refCompte
							.getRefIndividu() != null)
							&& (((refCompte.getRefIndividu().getNomLatin() != null) && (!refCompte
									.getRefIndividu().getNomLatin().trim()
									.isEmpty())))
							|| (((refCompte.getRefIndividu().getNomArabe() != null) && (!refCompte
									.getRefIndividu().getNomArabe().trim()
									.isEmpty())))
							|| (((refCompte.getRefIndividu().getPrenomLatin() != null) && (!refCompte
									.getRefIndividu().getPrenomLatin().trim()
									.isEmpty()))) || (((refCompte
							.getRefIndividu().getPrenomArabe() != null) && (!refCompte
							.getRefIndividu().getPrenomArabe().trim().isEmpty())))))) {
				StringBuilder request = new StringBuilder(
						"select r from RefCompte r ");
				request.append("left join r.refIndividu as individu ");

				if ((refCompte.getNomUtilisateur() != null)
						&& (!refCompte.getNomUtilisateur().trim().isEmpty())) {
					request.append(firstOpr)
							.append(" r.nomUtilisateur like ")
							.append(UtilConstant.LikeContain(refCompte
									.getNomUtilisateur()));
					firstOpr = UtilConstant.OR_SQL_STR;
				}

				if (refCompte.getDateDebut() != null) {
					request.append(firstOpr)
							.append(" r.dateDebut = ")
							.append(UtilConstant.quotedString(refCompte
									.getDateDebut().toString()));
					firstOpr = UtilConstant.OR_SQL_STR;
				}

				if (refCompte.getDateFin() != null) {
					request.append(firstOpr)
							.append(" r.dateFin = ")
							.append(UtilConstant.quotedString(refCompte
									.getDateFin().toString()));
					firstOpr = UtilConstant.OR_SQL_STR;
				}

				if (refCompte.getRefIndividu() != null) {
					if ((refCompte.getRefIndividu().getNomLatin() != null)
							&& (!refCompte.getRefIndividu().getNomLatin()
									.trim().isEmpty())) {
						request.append(firstOpr)
								.append(" individu.nomLatin like ")
								.append(UtilConstant.LikeContain(refCompte
										.getRefIndividu().getNomLatin()));
						firstOpr = UtilConstant.OR_SQL_STR;
					}

					if ((refCompte.getRefIndividu().getNomArabe() != null)
							&& (!refCompte.getRefIndividu().getNomArabe()
									.trim().isEmpty())) {
						request.append(firstOpr)
								.append(" individu.nomArabe like ")
								.append(UtilConstant.LikeContain(refCompte
										.getRefIndividu().getNomArabe()));
						firstOpr = UtilConstant.OR_SQL_STR;
					}
					if ((refCompte.getRefIndividu().getPrenomLatin() != null)
							&& (!refCompte.getRefIndividu().getPrenomLatin()
									.trim().isEmpty())) {
						request.append(firstOpr)
								.append(" individu.prenomLatin like ")
								.append(UtilConstant.LikeContain(refCompte
										.getRefIndividu().getPrenomLatin()));
						firstOpr = UtilConstant.OR_SQL_STR;
					}
					if ((refCompte.getRefIndividu().getPrenomArabe() != null)
							&& (!refCompte.getRefIndividu().getPrenomArabe()
									.trim().isEmpty())) {
						request.append(firstOpr)
								.append(" individu.prenomArabe like ")
								.append(UtilConstant.LikeContain(refCompte
										.getRefIndividu().getPrenomArabe()));
						firstOpr = UtilConstant.OR_SQL_STR;
					}
				}

				log.debug("request successful " + new String(request));
				Query query = entityManager.createQuery(new String(request));
				resultList = (List<RefCompte>) query.getResultList();
				log.debug("findAdvanced successful");
				return resultList;
			}
		} catch (RuntimeException re) {
			log.error("findAdvanced failed", re);
			System.out.println("findAdvanced failed");

			throw re;
		}
		return resultList;
	}

	/**
	 * @see dz.gov.mesrs.sii.referentiel.business.persistence.RefCompteDao#findByUser(java.lang.String)
	 */
	@Override
	public RefCompte findByUser(Integer individuId) {
		log.debug("getting  RefCompte of individu instance with : "
				+ individuId);

		try {

			if (individuId != null) {

				StringBuilder request = new StringBuilder(
						"select r from RefCompte r left join r.refIndividu as individu where individu.id = :id ");

				Query query = entityManager.createQuery(new String(request));
				query.setParameter("id", individuId);
				List resultList = (List<RefCompte>) query.getResultList();
				if (resultList != null && !resultList.isEmpty()) {
					log.debug("findByUser successful:  " + resultList.size()
							+ " compte");
					return (RefCompte) resultList.get(0);
				}
			}
		} catch (RuntimeException re) {
			log.error("findByUser failed", re);
			throw re;
		}
		log.debug("findByUser successful: No Compte");
		return null;
	}

	/**
	 * @see dz.gov.mesrs.sii.referentiel.business.persistence.RefCompteDao#findByLogin(java.lang.String)
	 */
	@Override
	public RefCompte findByLogin(String nomUtilisateur) {
		log.debug("getting  RefCompte By Login instance with : "
				+ nomUtilisateur);

		try {

			if (nomUtilisateur != null && !nomUtilisateur.trim().isEmpty()) {

				StringBuilder request = new StringBuilder(
						"select r from RefCompte r where r.nomUtilisateur = :nomUtilisateur ");
				Query query = entityManager.createQuery(new String(request));
				query.setParameter("nomUtilisateur", nomUtilisateur);
				List resultList = (List<RefCompte>) query.getResultList();
				if (resultList != null && !resultList.isEmpty()) {
					log.debug("findByLogin successful:  " + resultList.size()
							+ " compte");
					return (RefCompte) resultList.get(0);
				}
			}
		} catch (RuntimeException re) {
			log.error("findByLogin failed", re);
			throw re;
		}
		log.debug("findByLogin successful: No Compte");
		return null;
	}

	@Override
	public Integer generateUsername(String prefix) {
		if (prefix == null) {
			return null;
		}
		log.debug("findLastOrder instance with : " + prefix);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefCompte r ");
			request.append(" where lower(r.nomUtilisateur) like "
					+ UtilConstant.startWith(prefix.toLowerCase()));
			request.append(" order by r.nomUtilisateur desc");
			Query query = entityManager.createQuery(new String(request));
			List<RefCompte> resultList = (List<RefCompte>) query
					.getResultList();
			if (resultList == null || resultList.isEmpty()) {
				return -1;
			} else {
				log.debug("findLastOrder successful");
				String lastUsername = resultList.get(0).getNomUtilisateur();
				if (lastUsername == null) {
					return null;
				}
				String lastOrderStr = lastUsername.substring(prefix.length());
				if (lastOrderStr == null) {
					return null;
				}
				if (lastOrderStr.trim().isEmpty()) {
					return 0;
				}
				Integer lastOrder = Integer.parseInt(lastOrderStr);
				return lastOrder;
			}

		} catch (RuntimeException re) {
			log.error("findLastOrder failed", re);
			throw re;
		}
	}

	@Override
	public List<RefIndividu> findByEtablissement(String nom, String prenom,
			Integer idEtab) {
		List<RefIndividu> resultList = new ArrayList<RefIndividu>();
		log.debug("findByEtablissement: getting List of RefIndividu instance : ");
		if (nom == null || prenom == null) {
			return null;
		}
		try {
			StringBuilder request = new StringBuilder(
					"select distinct i from RefCompte r, RefIndividu i ");
			// if (idEtab != null) {
			// request.append(", RefAffectation a");
			// }
			request.append(" where i.id != r.refIndividu.id ");
			request.append(" and ((lower(i.nomLatin) like ");
			request.append(UtilConstant.LikeContain(nom.toLowerCase().trim()));
			request.append(" and lower(i.prenomLatin) like  ");
			request.append(
					UtilConstant.LikeContain(prenom.toLowerCase().trim()))
					.append(")");
			request.append(" or (lower(i.nomArabe) like ");
			request.append(UtilConstant.LikeContain(nom.toLowerCase().trim()));
			request.append(" and lower(i.prenomArabe) like  ");
			request.append(
					UtilConstant.LikeContain(prenom.toLowerCase().trim()))
					.append("))");
			if (idEtab != null) {
				// request.append(" and ((i.id = a.refIndividu.id) ");
				// request.append(" and (a.refStructure.refEtablissement.id =:idEtab ");
				// request.append(" or a.refGroupeByGroupe.refEtablissement.id =:idEtab)) ");
				request.append(" and ((i.id IN(select distinct a.refIndividu.id from RefAffectation a where a.refIndividu.id = i.id and a.refStructure.refEtablissement.id =:idEtab)) ");
				request.append(" or (i.id IN(select distinct a.refIndividu.id from RefAffectation a where  a.refGroupeByGroupe.refEtablissement.id =:idEtab))) ");

			}

			log.debug("request successful " + new String(request));
			TypedQuery<RefIndividu> query = entityManager.createQuery(
					new String(request), RefIndividu.class);
			if (idEtab != null) {
				query.setParameter("idEtab", idEtab);
			}
			resultList = (List<RefIndividu>) query.getResultList();
			log.debug("findAdvanced successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findAdvanced failed", re);
			System.out.println("findAdvanced failed");

			throw re;
		}

	}

	@Override
	public List<RefCompte> findGeneric(Integer etabId, String value) {
		log.debug("getting List of RefCompte instance with : " + value);
		List<RefCompte> resultList = new ArrayList<RefCompte>();
		try {
			int id;
			try {
				id = Integer.parseInt(value);
			} catch (NumberFormatException formatException) {
				id = 0;
			}
			if (value != null && !value.trim().isEmpty()) {
				value = "%" + value + "%";
				StringBuilder request = new StringBuilder(
						"select r from RefCompte r ");
				request.append("left join r.refIndividu as individu ");
				request.append("where 1=1 ");
				if (etabId != null) {
					request.append("and r.refEtablissement.id =:etabId ");
				}
				request.append("and (lower(r.nomUtilisateur) like :value ");
				request.append("or cast(r.dateDebut as text) like :value ");
				request.append("or cast(r.dateFin as text) like :value ");
				request.append("or lower(individu.nomArabe) like :value ");
				request.append("or lower(individu.nomLatin) like :value ");
				request.append("or lower(individu.prenomArabe) like :value ");
				request.append("or lower(individu.prenomLatin) like :value) ");
				Query query = entityManager.createQuery(new String(request));
				// query.setParameter("id", id);
				if (etabId != null) {
					query.setParameter("etabId", etabId);
				}
				query.setParameter("value", value);
				resultList = (List<RefCompte>) query.getResultList();
				log.debug("findGeneric successful");
				return resultList;
			}
		} catch (RuntimeException re) {
			log.error("findGeneric failed", re);
			throw re;
		}
		return resultList;
	}

}
