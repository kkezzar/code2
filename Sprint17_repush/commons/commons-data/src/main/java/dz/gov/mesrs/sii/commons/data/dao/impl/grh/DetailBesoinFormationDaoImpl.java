package dz.gov.mesrs.sii.commons.data.dao.impl.grh;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import dz.gov.mesrs.sii.commons.data.dao.CommonDaoImpl;
import dz.gov.mesrs.sii.commons.data.dao.grh.DetailBesoinFormationDao;
import dz.gov.mesrs.sii.commons.data.model.grh.DetailBesoinFormation;

@Repository("detailBesoinFormationDao")
public class DetailBesoinFormationDaoImpl extends CommonDaoImpl<DetailBesoinFormation, Integer> implements DetailBesoinFormationDao {

	@Override
	protected Class<DetailBesoinFormation> getDomainClass() {
		return DetailBesoinFormation.class;
	}

	@Override
	protected Criteria getCriteriaForKeyWord(String keyWord) {
		//TODO
		Criteria criteria = getSession().createCriteria(DetailBesoinFormation.class);
		String[] words = keyWord.split(" ");
		for (String word : words) {
			
		}
	
		return criteria;
	
	}
	
	
}
