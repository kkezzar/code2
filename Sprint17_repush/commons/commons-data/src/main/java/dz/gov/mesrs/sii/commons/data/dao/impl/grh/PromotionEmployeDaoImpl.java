package dz.gov.mesrs.sii.commons.data.dao.impl.grh;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.CommonDaoImpl;
import dz.gov.mesrs.sii.commons.data.model.grh.PromotionEmploye;
import dz.gov.mesrs.sii.commons.data.dao.grh.PromotionEmployeDao;

/**
 * 
 * @author Salem
 * @version V1.0
 * @date 22-10-2014
 * @description Implementation DAO Corps
 * 
 */



@Repository ("promotionEmployeDao")  @Transactional
public class PromotionEmployeDaoImpl extends CommonDaoImpl<PromotionEmploye, Integer> implements PromotionEmployeDao {
	
	@Override
	protected Class<PromotionEmploye> getDomainClass() {
		return PromotionEmploye.class;
	}
	
//	@Autowired
//    public PromotionEmployeDaoImpl(@Qualifier("hibernateTemplate") HibernateTemplate hibernateTemplate) {
//    	 super.setHibernateTemplate(hibernateTemplate);
//    }
}
