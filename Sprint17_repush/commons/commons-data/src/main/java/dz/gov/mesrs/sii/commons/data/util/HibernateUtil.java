package dz.gov.mesrs.sii.commons.data.util;

import java.lang.reflect.Field;

import org.hibernate.Criteria;
import org.hibernate.engine.LoadQueryInfluencers;
import org.hibernate.engine.SessionFactoryImplementor;
import org.hibernate.impl.CriteriaImpl;
import org.hibernate.impl.SessionImpl;
import org.hibernate.loader.OuterJoinLoader;
import org.hibernate.loader.criteria.CriteriaLoader;
import org.hibernate.persister.entity.OuterJoinLoadable;

/**
 * Class utils pour Hibernate
 * 
 * @author Mounir.MESSAOUDI(CCM-CG)
 * 
 */
public class HibernateUtil {

	public static String getSQL(Criteria criteria) {

		try {
			CriteriaImpl c = (CriteriaImpl) criteria;
			SessionImpl s = (SessionImpl) c.getSession();
			SessionFactoryImplementor factory = (SessionFactoryImplementor) s.getSessionFactory();
			String[] implementors = factory.getImplementors(c.getEntityOrClassName());
			LoadQueryInfluencers lqis = new LoadQueryInfluencers();
			CriteriaLoader loader = new CriteriaLoader((OuterJoinLoadable) factory.getEntityPersister(implementors[0]),
					factory, c, implementors[0], lqis);
			Field f = OuterJoinLoader.class.getDeclaredField("sql");
			f.setAccessible(true);
			String sql = (String) f.get(loader);
			return sql;
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
