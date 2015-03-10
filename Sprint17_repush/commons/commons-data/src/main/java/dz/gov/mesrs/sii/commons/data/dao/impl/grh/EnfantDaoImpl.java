package dz.gov.mesrs.sii.commons.data.dao.impl.grh;



import org.springframework.stereotype.Repository;
import dz.gov.mesrs.sii.commons.data.dao.CommonDaoImpl;
import dz.gov.mesrs.sii.commons.data.model.grh.Enfant;
import dz.gov.mesrs.sii.commons.data.dao.grh.EnfantDao;

/**
 * 
 * @author Salem
 * @version V1.0
 * @date 22-10-2014
 * @description Implementation DAO Enfant
 * 
 */


@Repository ("enfantDao")  
public class EnfantDaoImpl  extends CommonDaoImpl<Enfant, Integer> implements EnfantDao {
	
	@Override
	protected Class<Enfant> getDomainClass() {
		return Enfant.class;
	}
	
//	@Autowired
//    public EnfantDaoImpl(@Qualifier("hibernateTemplate") HibernateTemplate hibernateTemplate) {
//    	 super.setHibernateTemplate(hibernateTemplate);
//    }
}
