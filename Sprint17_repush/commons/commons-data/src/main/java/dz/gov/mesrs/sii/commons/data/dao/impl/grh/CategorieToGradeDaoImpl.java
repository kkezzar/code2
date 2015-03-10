package dz.gov.mesrs.sii.commons.data.dao.impl.grh;


import org.springframework.stereotype.Repository;

import dz.gov.mesrs.sii.commons.data.model.ModelBaseRelationship.Id;
import dz.gov.mesrs.sii.commons.data.dao.CommonDaoImpl;
import dz.gov.mesrs.sii.commons.data.model.grh.CategorieToGrade;
import dz.gov.mesrs.sii.commons.data.dao.grh.CategorieToGradeDao;

/**
 * 
 * @author Salem
 * @version V1.0
 * @date 22-10-2014
 * @description Implementation DAO CategorieToGrade
 * 
 */


@Repository ("categorieToGradeDao")  
public class CategorieToGradeDaoImpl extends CommonDaoImpl<CategorieToGrade, Id> implements CategorieToGradeDao {
	
	@Override
	protected Class<CategorieToGrade> getDomainClass() {
		return CategorieToGrade.class;
		
	}
	
//	@Autowired
//    public CategorieToGradeDaoImpl(@Qualifier("hibernateTemplate") HibernateTemplate hibernateTemplate) {
//    	 super.setHibernateTemplate(hibernateTemplate);
//    }
	
}
