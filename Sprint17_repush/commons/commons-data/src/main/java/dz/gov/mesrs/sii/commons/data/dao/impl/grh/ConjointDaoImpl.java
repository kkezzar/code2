package dz.gov.mesrs.sii.commons.data.dao.impl.grh;


import org.springframework.stereotype.Repository;

import dz.gov.mesrs.sii.commons.data.dao.CommonDaoImpl;
import dz.gov.mesrs.sii.commons.data.model.grh.Conjoint;
import dz.gov.mesrs.sii.commons.data.dao.grh.ConjointDao;
/**
 * 
 * @author Salem
 * @version V1.0
 * @date 22-10-2014
 * @description Implementation DAO Conjoint
 * 
 */


@Repository ("conjointDao")  
public class ConjointDaoImpl extends CommonDaoImpl<Conjoint, Integer> implements ConjointDao {
	
	@Override
	protected Class<Conjoint> getDomainClass() {
		return Conjoint.class;
	}
	
//	@Autowired
//    public ConjointDaoImpl(@Qualifier("hibernateTemplate") HibernateTemplate hibernateTemplate) {
//    	 super.setHibernateTemplate(hibernateTemplate);
//    }
}
