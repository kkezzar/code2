package dz.gov.mesrs.sii.commons.data.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;




import org.apache.commons.lang3.StringUtils;
//import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.ejb.HibernateEntityManager;
import org.springframework.stereotype.Repository;

import dz.gov.mesrs.sii.commons.data.dao.NotifcationTraceDao;
import dz.gov.mesrs.sii.commons.data.model.NotificationTrace;


@Repository("notificationTraceDao")
public class NotificationTraceDaoImpl implements NotifcationTraceDao {
	
	@PersistenceContext (type = PersistenceContextType.EXTENDED)
	private EntityManager em;

	@Override
	public void persist(NotificationTrace notificationTrace) {
		em.merge(notificationTrace);
	}
	
	@Override
	public List<NotificationTrace> findByEtablissementAndCriteria(int idEtablissement, String researchKeyWord) {
		HibernateEntityManager hem = em.unwrap(HibernateEntityManager.class);
		Session session = hem.getSession();
		Criteria criteria = session.createCriteria(NotificationTrace.class , "notificationTrace");
		criteria.createAlias("notificationTrace.refEtablissement", "refEtablissement");
		criteria.add(Restrictions.eq("refEtablissement.id", idEtablissement));

		if(StringUtils.isNotEmpty(researchKeyWord)){
			String likeString = "%"+researchKeyWord+"%";
			criteria.createAlias("notificationTrace.modeNotification", "modeNotification");
			criteria.createAlias("notificationTrace.refIndividuNotifie", "refIndividuNotifie");
			criteria.add(Restrictions.disjunction()
					.add(Restrictions.like("expediteur", likeString))
					.add(Restrictions.like("objet", likeString))
					.add(Restrictions.like("message", likeString))
					.add(Restrictions.like("modeNotification.libelleLongFr", likeString))
					.add(Restrictions.like("refIndividuNotifie.nomLatin", likeString))
					.add(Restrictions.like("refIndividuNotifie.prenomLatin", likeString)));
		}
		criteria.addOrder(Order.desc("notificationDate"));
		return criteria.list();
	}
	
	@Override
	public List<NotificationTrace> findByUserAndCriteria(int userId, String researchKeyWord) {
		HibernateEntityManager hem = em.unwrap(HibernateEntityManager.class);
		Session session = hem.getSession();
		Criteria criteria = session.createCriteria(NotificationTrace.class , "notificationTrace");
		criteria.createAlias("notificationTrace.refIndividuNotifie", "refIndividuNotifie");
		criteria.add(Restrictions.eq("refIndividuNotifie.id", userId));

		if(StringUtils.isNotEmpty(researchKeyWord)){
			String likeString = "%"+researchKeyWord+"%";
			criteria.createAlias("notificationTrace.modeNotification", "modeNotification");
			criteria.add(Restrictions.disjunction()
					.add(Restrictions.like("expediteur", likeString))
					.add(Restrictions.like("objet", likeString))
					.add(Restrictions.like("message", likeString))
					.add(Restrictions.like("modeNotification.libelleLongFr", likeString)));
					
		}
		criteria.addOrder(Order.desc("notificationDate"));
		return criteria.list();
	}

	
	public void setEm(EntityManager em) {
		this.em = em;
	}

	


	
}
