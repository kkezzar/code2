package dz.gov.mesrs.sii.commons.data.dao.impl.grh;


import org.springframework.stereotype.Repository;
import dz.gov.mesrs.sii.commons.data.dao.CommonDaoImpl;
import dz.gov.mesrs.sii.commons.data.model.grh.Stage;
import dz.gov.mesrs.sii.commons.data.dao.grh.StageDao;

/**
 * 
 * @author Salem
 * @version V1.0
 * @date 22-10-2014
 * @description Implementation DAO Stage
 * 
 */


@Repository ("stageDao")  
public class StageDaoImpl extends CommonDaoImpl<Stage, Integer> implements StageDao {
	
	@Override
	protected Class<Stage> getDomainClass() {
		return Stage.class;
	}
	
//	@Autowired
//    public StageDaoImpl(@Qualifier("hibernateTemplate") HibernateTemplate hibernateTemplate) {
//    	 super.setHibernateTemplate(hibernateTemplate);
//    }
}
