package dz.gov.mesrs.sii.commons.data.dao.impl.grh;



import org.springframework.stereotype.Repository;

import dz.gov.mesrs.sii.commons.data.dao.CommonDaoImpl;
import dz.gov.mesrs.sii.commons.data.model.grh.PieceDossierRetraite;
import dz.gov.mesrs.sii.commons.data.dao.grh.PieceDossierRetraiteDao;

/**
 * 
 * @author Salem
 * @version V1.0
 * @date 22-10-2014
 * @description Implementation DAO PieceDossierRetraite
 * 
 */


@Repository ("pieceDossierRetraiteDao")  
public class PieceDossierRetraiteDaoImpl extends CommonDaoImpl<PieceDossierRetraite, Integer> implements PieceDossierRetraiteDao {
	
	@Override
	protected Class<PieceDossierRetraite> getDomainClass() {
		return PieceDossierRetraite.class;
	}
	
//	@Autowired
//    public PieceDossierRetraiteDaoImpl(@Qualifier("hibernateTemplate") HibernateTemplate hibernateTemplate) {
//    	 super.setHibernateTemplate(hibernateTemplate);
//    }
}
