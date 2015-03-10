package dz.gov.mesrs.sii.commons.data.dao.impl.grh;



import org.springframework.stereotype.Repository;

import dz.gov.mesrs.sii.commons.data.dao.CommonDaoImpl;
import dz.gov.mesrs.sii.commons.data.model.grh.Distinction;
import dz.gov.mesrs.sii.commons.data.dao.grh.DistinctionDao;

/**
 * 
 * @author Salem
 * @version V1.0
 * @date 22-10-2014
 * @description Implementation DAO Distinction
 * 
 */



@Repository ("distinctionDao") 
public class DistinctionDaoImpl  extends CommonDaoImpl<Distinction, Integer> implements DistinctionDao {
	
	@Override
	protected Class<Distinction> getDomainClass() {
		return Distinction.class;
	}
	
//	@Autowired
//    public DistinctionDaoImpl(@Qualifier("hibernateTemplate") HibernateTemplate hibernateTemplate) {
//    	 super.setHibernateTemplate(hibernateTemplate);
//    }
}
