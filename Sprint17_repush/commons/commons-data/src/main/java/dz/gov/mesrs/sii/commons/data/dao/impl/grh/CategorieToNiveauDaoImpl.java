package dz.gov.mesrs.sii.commons.data.dao.impl.grh;

import org.springframework.stereotype.Repository;

import dz.gov.mesrs.sii.commons.data.model.ModelBaseRelationship.Id;
import dz.gov.mesrs.sii.commons.data.dao.CommonDaoImpl;
import dz.gov.mesrs.sii.commons.data.model.grh.CategorieToNiveau;
import dz.gov.mesrs.sii.commons.data.dao.grh.CategorieToNiveauDao;

/**
 * 
 * @author Salem
 * @version V1.0
 * @date 22-10-2014
 * @description Implementation DAO CategorieProfessionnelle
 * 
 */


@Repository ("categorieToNiveauDao")  
public class CategorieToNiveauDaoImpl extends CommonDaoImpl<CategorieToNiveau, Id> implements CategorieToNiveauDao {
	
	@Override
	protected Class<CategorieToNiveau> getDomainClass() {
		return CategorieToNiveau.class;
		
	}
	
//	@Autowired
//    public CategorieToNiveauDaoImpl(@Qualifier("hibernateTemplate") HibernateTemplate hibernateTemplate) {
//    	 super.setHibernateTemplate(hibernateTemplate);
//    }
}
