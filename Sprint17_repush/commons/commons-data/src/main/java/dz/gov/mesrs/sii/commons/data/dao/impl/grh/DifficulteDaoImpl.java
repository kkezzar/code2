package dz.gov.mesrs.sii.commons.data.dao.impl.grh;

import org.springframework.stereotype.Repository;
import dz.gov.mesrs.sii.commons.data.dao.CommonDaoImpl;
import dz.gov.mesrs.sii.commons.data.model.grh.Difficulte;
import dz.gov.mesrs.sii.commons.data.dao.grh.DifficulteDao;

/**
 * 
 * @author Salem
 * @version V1.0
 * @date 22-10-2014
 * @description Implementation DAO Difficulte
 * 
 */

@Repository ("difficulteDao")  
public class DifficulteDaoImpl extends  CommonDaoImpl<Difficulte, Integer> implements DifficulteDao {
	
	@Override
	protected Class<Difficulte> getDomainClass() {
		return Difficulte.class;
	}
	
//	@Autowired
//    public DifficulteDaoImpl(@Qualifier("hibernateTemplate") HibernateTemplate hibernateTemplate) {
//    	 super.setHibernateTemplate(hibernateTemplate);
//    }
}
