package dz.gov.mesrs.sii.commons.data.dao.impl.grh;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.CommonDaoImpl;
import dz.gov.mesrs.sii.commons.data.model.grh.PromotionPropose;
import dz.gov.mesrs.sii.commons.data.dao.grh.PromotionProposeDao;

/**
 * 
 * @author Salem
 * @version V1.0
 * @date 22-10-2014
 * @description Implementation DAO PromotionPropose
 * 
 */


@Repository ("promotionProposeDao")  @Transactional
public class PromotionProposeDaoImpl extends CommonDaoImpl<PromotionPropose, Integer>  implements PromotionProposeDao {
	
	@Override
	protected Class<PromotionPropose> getDomainClass() {
		return PromotionPropose.class;
	}
//	
//	@Autowired
//    public PromotionProposeDaoImpl(@Qualifier("hibernateTemplate") HibernateTemplate hibernateTemplate) {
//    	 super.setHibernateTemplate(hibernateTemplate);
//    }
}
