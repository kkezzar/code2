package dz.gov.mesrs.sii.commons.data.dao.impl.grh;

import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import dz.gov.mesrs.sii.commons.data.dao.CommonDaoImpl;
import dz.gov.mesrs.sii.commons.data.dao.grh.CategorieProfessionnelleDao;
import dz.gov.mesrs.sii.commons.data.dao.grh.PosteSuperieureDao;
import dz.gov.mesrs.sii.commons.data.dao.referentiel.NomenclatureDao;
import dz.gov.mesrs.sii.commons.data.model.grh.CategorieProfessionnelle;
import dz.gov.mesrs.sii.commons.data.model.grh.Corps;
import dz.gov.mesrs.sii.commons.data.model.grh.PosteSuperieure;
import dz.gov.mesrs.sii.commons.data.model.grh.Statut;
import dz.gov.mesrs.sii.commons.data.model.referentiel.Nomenclature;

/**
 * 
 * @author Salem
 * @version V1.0
 * @date 22-10-2014
 * @description Implementation DAO PosteSuperieure
 * 
 */

@Repository("posteSuperieureDao")
public class PosteSuperieureDaoImpl extends CommonDaoImpl<PosteSuperieure, Integer> implements PosteSuperieureDao {

	private NomenclatureDao nomenclatureDao;
	private CategorieProfessionnelleDao categorieProfessionnelleDao;

	@Override
	protected Class<PosteSuperieure> getDomainClass() {
		return PosteSuperieure.class;
	}

	@Override
	public PosteSuperieure save(PosteSuperieure posteSuperieure) {
		Assert.notNull(posteSuperieure,"Can't persist a null entity");
		Nomenclature typePost = nomenclatureDao.findById(posteSuperieure.getNomenclature().getId());
		CategorieProfessionnelle categorieProfessionnelle = categorieProfessionnelleDao.findById(posteSuperieure
				.getCategorieProfessionnelle().getId());
		posteSuperieure.setCategorieProfessionnelle(categorieProfessionnelle);
		posteSuperieure.setNomenclature(typePost);
		return super.save(posteSuperieure);
	}

	@Override
	protected Criteria getCriteria(PosteSuperieure example) {
		Criteria criteria = super.getCriteria(example);
		Corps corps = example.getCorps();
		if (corps != null) {
			criteria.createAlias("corps", "corps", CriteriaSpecification.LEFT_JOIN);
			Statut statut = corps.getStatut();
			if (statut != null) {
				criteria.createAlias("corps.statut", "statut", CriteriaSpecification.LEFT_JOIN);
				if (statut.getId() != null) {
					criteria.add(Restrictions.eq("statut.id", statut.getId()));
				}
			}
		}
		CategorieProfessionnelle categorieProfessionnelle = example.getCategorieProfessionnelle();
		if (categorieProfessionnelle != null) {
			criteria.createAlias("categorieProfessionnelle", "categorieProfessionnelle");

			criteria.add(Restrictions.eq("categorieProfessionnelle", categorieProfessionnelle));
		}

		return criteria;
	}

	@Autowired
	public void setNomenclatureDao(NomenclatureDao nomenclatureDao) {
		this.nomenclatureDao = nomenclatureDao;
	}

	@Autowired
	public void setCategorieProfessionnelleDao(CategorieProfessionnelleDao categorieProfessionnelleDao) {
		this.categorieProfessionnelleDao = categorieProfessionnelleDao;
	}
}
