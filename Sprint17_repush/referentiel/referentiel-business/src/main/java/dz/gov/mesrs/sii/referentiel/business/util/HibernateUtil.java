/**
 * [dz.gov.mesrs.sii.referentiel.business.util.HibernateUtil.java] 
 * @author rlaib on : 11 mars 2014  15:54:05
 */
package dz.gov.mesrs.sii.referentiel.business.util;

import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;

/**
 * @author rlaib  on : 11 mars 2014  15:54:05
 */
public class HibernateUtil {
	@SuppressWarnings("unchecked")
	public static <T> T unproxy(T entity) {
        if (entity == null) {
            return null;
        }
  
        if (entity instanceof HibernateProxy) {
            Hibernate.initialize(entity);
            entity = (T) ((HibernateProxy) entity).getHibernateLazyInitializer().getImplementation();
        }
 
        return entity;
    }
}
