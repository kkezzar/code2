package dz.gov.mesrs.sii.commons.data.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.ejb.HibernateEntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.SearchMode.Filter;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode.SortField;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode.SortOrder;
import dz.gov.mesrs.sii.commons.data.model.Identifiable;

/**
 * 
 * @author Salem
 * @version V1.0
 * @date 21-14-2014
 * @description Classe d'implementation de l'interface commune pour les DAO
 * 
 */
@Repository("commonDao")
public abstract class CommonDaoImpl<CommonObject extends Identifiable<KeyType>, KeyType extends Serializable>
		implements CommonDao<CommonObject, KeyType> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	protected Class<CommonObject> domainClass = getDomainClass();

	protected abstract Class<CommonObject> getDomainClass();

	@PersistenceContext
	public EntityManager entityManager;

	/**
	 * Default constructor.
	 */

	public CommonDaoImpl() {
		super();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void refresh(CommonObject obj) {
		entityManager.refresh(obj);
	}

	/***
	 * for entity manager
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(CommonObject obj) {
		logger.debug("persisting commonobject instance");
		try {
			entityManager.persist(obj);
			entityManager.flush();
			entityManager.refresh(obj);
			logger.debug("persist successful");
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			throw re;
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public CommonObject merge(CommonObject obj) {
		logger.debug("merging commonObject instance");
		try {
			logger.debug("merge successful");
			return entityManager.merge(obj);
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			throw re;
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public CommonObject findById(KeyType id) {
		logger.debug("getting Common instance with id: " + id);
		try {
			CommonObject instance = entityManager.find(domainClass, id);
			logger.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			logger.error("get failed", re);
			throw re;
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public List<CommonObject> findAll() {
		try {
			String query = "select object from " + domainClass.getSimpleName() + "  object";

			Query qObject = entityManager.createQuery(query);
			List<CommonObject> instanceList = qObject.getResultList();
			logger.debug("get successful");
			return instanceList;
		} catch (RuntimeException re) {
			logger.error("get failed", re);
			throw re;
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(CommonObject persistentInstance) {
		logger.debug("removing CommonObject instance:" + domainClass.toString());
		try {
			entityManager.remove(persistentInstance);
			logger.debug("remove successful");
		} catch (RuntimeException re) {
			logger.error("remove failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<CommonObject> findAllByExample(CommonObject example) {
		Criteria criteria = getCriteria(example);
		List<CommonObject> l = criteria.list();
		return l;
		// return getCriteria(example).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<CommonObject> findAllByExample(CommonObject example, SearchMode searchMode) {
		if (searchMode == null) {
			return this.findAllByExample(example);
		}
		Criteria criteria = getCriteria(example);
		enrichCriteriaWithSearchMode(criteria, searchMode);

		return criteria.list();
	}

	protected void enrichCriteriaWithSearchMode(Criteria criteria, SearchMode searchMode) {
		if (searchMode.getPageSize() != null) {
			criteria.setMaxResults(searchMode.getPageSize());
			criteria.setFirstResult(searchMode.getStartIndex());
		}

		for (SortField sortField : searchMode.getSortFields()) {
			if (SortOrder.DESC.equals(sortField.getOrder())) {
				criteria.addOrder(Order.desc(sortField.getFieldName()));
			} else {
				criteria.addOrder(Order.asc(sortField.getFieldName()));
			}

		}

		for (Filter filter : searchMode.getFilters()) {
			String propertyName = filter.getFieldName();
			Object value1 = filter.getValue1();
			Object value2 = filter.getValue2();
			Object[] values = filter.getValues();
			switch (filter.getMode()) {
			case BETWEEN:
				criteria.add(Restrictions.between(propertyName, value1, value2));
				break;
			case GREATER:
				criteria.add(Restrictions.gt(propertyName, value1));
				break;
			case LESS:
				criteria.add(Restrictions.lt(propertyName, value1));
				break;
			case GREATER_OR_EQUAL:
				criteria.add(Restrictions.ge(propertyName, value1));
				break;
			case LESS_OR_EQUAL:
				criteria.add(Restrictions.le(propertyName, value1));
				break;
			case IS_NULL:
				criteria.add(Restrictions.isNull(propertyName));
				break;
			case IS_NOT_NULL:
				criteria.add(Restrictions.isNotNull(propertyName));
				break;
			case NOT:
				criteria.add(Restrictions.or(Restrictions.ne(propertyName, value1), Restrictions.isNull(propertyName)));
				break;
			case IN:
				criteria.add(Restrictions.in(propertyName, values));
				break;
			case NOT_IN:
				criteria.add(Restrictions.not(Restrictions.in(propertyName, values)));
				break;
			case EQUALS:
				criteria.add(Restrictions.eq(propertyName, value1));
			default:
				break;
			}
		}
	}

	@Override
	@Transactional(readOnly = true)
	public int countAllByExample(CommonObject example) {
		Criteria criteria = getCriteria(example);
		criteria.setProjection(Projections.rowCount());
		criteria.setFirstResult(new Integer(0));
		return ((Long) criteria.uniqueResult()).intValue();
	}

	@Override
	@Transactional(readOnly = true)
	public CommonObject findUniqueByExample(CommonObject example) {
		List<CommonObject> results = this.findAllByExample(example);
		if (results == null || results.size() != 1) {
			String countErrorMesage = results == null ? "none" : String.valueOf(results.size());
			throw new InvalidDataAccessApiUsageException("Developper, you expected one result but we found "
					+ countErrorMesage);
		}
		return results.get(0);
	}

	@Override
	@Transactional(readOnly = true)
	public CommonObject findUniqueOrNoneByExample(CommonObject example) {
		List<CommonObject> results = this.findAllByExample(example);
		if (results != null && results.size() > 1) {
			throw new InvalidDataAccessApiUsageException("Developper, you expected at most one result but we found "
					+ String.valueOf(results.size()));
		}
		if (results == null || results.size() == 0) {
			return null;
		} else {
			return results.get(0);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<CommonObject> findAllByKeyWord(String keyWord, SearchMode searchMode) {
		Criteria criteria = getCriteriaForKeyWord(keyWord);
		if(searchMode!=null){
		enrichCriteriaWithSearchMode(criteria, searchMode);
		}
		criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		return criteria.list();
	}

	@Override
	@Transactional(readOnly = true)
	public int countAllByKeyWord(String keyWord) {
		Criteria criteria = getCriteriaForKeyWord(keyWord);
		criteria.setProjection(Projections.rowCount());
		criteria.setFirstResult(new Integer(0));
		return ((Long) criteria.uniqueResult()).intValue();
	}

	@Override
	@Transactional(readOnly = true)
	public int countAllByExample(CommonObject example, SearchMode searchMode) {
		Criteria criteria = getCriteria(example);
		enrichCriteriaWithSearchMode(criteria, searchMode);
		criteria.setProjection(Projections.rowCount());
		criteria.setFirstResult(new Integer(0));
		return ((Long) criteria.uniqueResult()).intValue();
	}

	@Override
	public CommonObject save(CommonObject object) {
		if (object.getIdenfiant() == null) {
			entityManager.persist(object);
			// forcing flush mode and refresh the object (@author: MM)
			entityManager.flush();
			entityManager.refresh(object);
		} else {
			object = entityManager.merge(object);
		}
		return object;
	}

	protected Criteria getCriteriaForKeyWord(String keyWord) {
		throw new IllegalStateException("Developper, you must specify the Criteria For key word search !");
	}

	protected Criteria getCriteria(CommonObject example) {
		Criteria criteria = getSession().createCriteria(example.getClass()).add(Example.create(example));
		if (example.getIdenfiant() != null) {
			criteria.add(Restrictions.eq(example.getIdentifiantName(), example.getIdenfiant()));
		}

		// fix duplicate results (MM)
		criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		return criteria;
	}

	protected Session getSession() {
		HibernateEntityManager hem = entityManager.unwrap(HibernateEntityManager.class);
		return hem.getSession();
	}

	@Override
	@Transactional(readOnly = true)
	public int countAllByKeyWord(String keyWord, SearchMode searchMode) {
		Criteria criteria = getCriteriaForKeyWord(keyWord);
		enrichCriteriaWithSearchMode(criteria, searchMode);
		criteria.setProjection(Projections.rowCount());
		criteria.setFirstResult(new Integer(0));
		Long result = (Long) criteria.uniqueResult();
		return result == null ? 0 : result.intValue();
	}

	// @Override
	// public List<CommonObject> findAllByExample(CommonObject example,
	// Collection collection, String propretyName,
	// short operator) {
	// Criteria criteria = getCriteria(example);
	// enrirchCriteriaWithExample(criteria, example, collection, propretyName,
	// operator);
	// return criteria.list();
	// }
	//
	// @Override
	// public List<CommonObject> findAllByExample(CommonObject example,
	// SearchMode searchMode, Collection collection,
	// String propretyName, short operator) {
	// if (searchMode == null) {
	// return this.findAllByExample(example, collection, propretyName,
	// operator);
	// }
	// Criteria criteria = getCriteria(example);
	// enrirchCriteriaWithExample(criteria, example, collection, propretyName,
	// operator);
	// enrichCriteriaWithSearchMode(criteria, searchMode);
	// return criteria.list();
	// }

	// protected void enrirchCriteriaWithExample(Criteria criteria, CommonObject
	// example, Collection collection,
	// String propretyName, short operator) {
	// // throw new
	// //
	// IllegalStateException("Developper, you must specify the Criteria For example !");
	// }

}
