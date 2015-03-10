/**
 * [dz.gov.mesrs.sii.fve.business.persistence.scolarite.GenericFveDao.java] 
 * @author rlaib on : 12 oct. 2014  15:30:39
 */
package dz.gov.mesrs.sii.commons.data.dao.fve.scolarite;

import java.util.List;


/**
 * @author rlaib  on : 12 oct. 2014  15:30:39
 */
public interface GenericFveDao<T> {

    T persist(T t);

    void remove(Object id);

    T findById(Object id);

    T merge(T t);   
    
    List<T> findAll();
     
    void flushAndClear();
}
