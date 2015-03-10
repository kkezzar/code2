package dz.gov.mesrs.sii.commons.data.dao.impl.fve.concours;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.ejb.HibernateEntityManager;
import org.springframework.stereotype.Repository;

import dz.gov.mesrs.sii.commons.data.dao.fve.concours.EtablissementAdmissionDao;
import dz.gov.mesrs.sii.commons.data.model.fve.concours.EtablissementAdmission;
@Repository
public class EtablissementAdmissionDaoImpl implements EtablissementAdmissionDao {

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public Integer countNombreAdmis(EtablissementAdmission etablissementAdmission) {
	HibernateEntityManager hem = em.unwrap(HibernateEntityManager.class);
	Session session = hem.getSession();
	Criteria criteria = session.createCriteria(EtablissementAdmission.class ,"etablissementAdmission");
	criteria.add(Restrictions.eq("etablissementAdmission.id", etablissementAdmission.getId()));
	criteria.createAlias("etablissementAdmission.candidats", "candidat");
	criteria.add(Restrictions.isNull("candidat.dateDesistement"));
	criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
	criteria.setProjection(Projections.rowCount());
	return ((Long) criteria.uniqueResult()).intValue();
    }

}
