package dz.gov.mesrs.sii.commons.data.dao.impl.grh;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import dz.gov.mesrs.sii.commons.data.dao.CommonDaoImpl;
import dz.gov.mesrs.sii.commons.data.dao.grh.CategorieProfessionnelleDao;
import dz.gov.mesrs.sii.commons.data.dao.referentiel.NomenclatureDao;
import dz.gov.mesrs.sii.commons.data.model.grh.CategorieProfessionnelle;
import dz.gov.mesrs.sii.commons.data.model.referentiel.Nomenclature;

/**
 * 
 * @author Salem
 * @version V1.0
 * @date 22-10-2014
 * @description Implementation DAO CategorieProfessionnelle
 * 
 */

@Repository("categorieProfessionnelleDao")
public class CategorieProfessionnelleDaoImpl extends CommonDaoImpl<CategorieProfessionnelle, Integer> implements
		CategorieProfessionnelleDao {

	private NomenclatureDao nomenclatureDao;
	
	@Override
	protected Class<CategorieProfessionnelle> getDomainClass() {
		return CategorieProfessionnelle.class;

	}

	@Override
	protected Criteria getCriteria(CategorieProfessionnelle example) {
		Criteria criteria = super.getCriteria(example);
		Nomenclature nomenclatureTypeCategorie = example.getNomenclatureByTypeCategorie();
		if (nomenclatureTypeCategorie != null) {
			criteria.createAlias("nomenclatureByTypeCategorie", "nomenclatureByTypeCategorie");
			if (nomenclatureTypeCategorie.getCode() != null) {
				criteria.add(Restrictions.eq("nomenclatureByTypeCategorie.code", nomenclatureTypeCategorie.getCode()));
			}
		}

		return criteria;
	}
	
	@Override
	public CategorieProfessionnelle save(CategorieProfessionnelle categorie) {
		Assert.notNull(categorie,"Can't persist a null entity");
		Nomenclature typeGroupe = nomenclatureDao.findById(categorie.getNomenclatureByGroupe().getId());
		Nomenclature ncCategorie = nomenclatureDao.findById(categorie.getNomenclatureByCategorie().getId());
		categorie.setNomenclatureByGroupe(typeGroupe);
		categorie.setNomenclatureByCategorie(ncCategorie);
		return super.save(categorie);
	}
	
	@Autowired
	public void setNomenclatureDao(NomenclatureDao nomenclatureDao) {
		this.nomenclatureDao = nomenclatureDao;
	}
}
