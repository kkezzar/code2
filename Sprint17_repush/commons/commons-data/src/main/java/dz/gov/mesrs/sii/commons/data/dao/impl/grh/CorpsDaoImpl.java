package dz.gov.mesrs.sii.commons.data.dao.impl.grh;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import dz.gov.mesrs.sii.commons.data.dao.CommonDaoImpl;
import dz.gov.mesrs.sii.commons.data.dao.grh.CorpsDao;
import dz.gov.mesrs.sii.commons.data.dao.grh.StatutDao;
import dz.gov.mesrs.sii.commons.data.dao.referentiel.NomenclatureDao;
import dz.gov.mesrs.sii.commons.data.model.grh.Corps;
import dz.gov.mesrs.sii.commons.data.model.grh.Statut;
import dz.gov.mesrs.sii.commons.data.model.referentiel.Nomenclature;

/**
 * 
 * @author Salem
 * @version V1.0
 * @date 22-10-2014
 * @description Implementation DAO Corps
 * 
 */

@Repository("corpsDao")
public class CorpsDaoImpl extends CommonDaoImpl<Corps, Integer> implements CorpsDao {

	private StatutDao statutDao;
	private NomenclatureDao nomenclatureDao;
	
	@Override
	protected Class<Corps> getDomainClass() {
		return Corps.class;
	}

	@Override
	protected Criteria getCriteriaForKeyWord(String keyWord) {
		Criteria criteria = getSession().createCriteria(Corps.class);

		criteria.createAlias("nomenclatureByIdNomenclatureFiliere", "filiere");
		criteria.createAlias("nomenclatureByIdNomenclatureCorps", "corps");
		criteria.createAlias("statut", "statut");

		criteria.add(Restrictions.disjunction()
				.add(Restrictions.like("filiere.libelleLongFr", keyWord, MatchMode.ANYWHERE).ignoreCase())
				.add(Restrictions.like("corps.libelleLongFr", keyWord, MatchMode.ANYWHERE).ignoreCase()));
//				.add(Restrictions.like("statut.decret", keyWord, MatchMode.ANYWHERE).ignoreCase())
//				.add(Restrictions.like("statut.objet", keyWord, MatchMode.ANYWHERE).ignoreCase()));
		return criteria;
	}

	@Override
	protected Criteria getCriteria(Corps example) {
		Criteria criteria = super.getCriteria(example);
		if (example.getStatut() != null) {
			criteria.createAlias("statut", "statut");
			criteria.add(Restrictions.eq("statut.id", example.getStatut().getId()));
		}
		return criteria;
	}
	
	@Override
	public Corps save(Corps corps) {
		Assert.notNull(corps,"Can't persist a null entity");
		Statut statut = statutDao.findById(corps.getStatut().getId());
		Nomenclature typeFiliere = nomenclatureDao.findById(corps.getNomenclatureByIdNomenclatureFiliere().getId());
		Nomenclature typeCorps = nomenclatureDao.findById(corps.getNomenclatureByIdNomenclatureCorps().getId());
		corps.setStatut(statut);
		corps.setNomenclatureByIdNomenclatureCorps(typeCorps);
		corps.setNomenclatureByIdNomenclatureFiliere(typeFiliere);
		return super.save(corps);
	}
	@Autowired
	public void setNomenclatureDao(NomenclatureDao nomenclatureDao) {
		this.nomenclatureDao = nomenclatureDao;
	}
	@Autowired
	public void setStatutDao(StatutDao statutDao) {
		this.statutDao = statutDao;
	}
	
	
}
