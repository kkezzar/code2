package dz.gov.mesrs.sii.commons.data.dao.impl.grh;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import dz.gov.mesrs.sii.commons.data.dao.CommonDaoImpl;
import dz.gov.mesrs.sii.commons.data.dao.grh.CategorieProfessionnelleDao;
import dz.gov.mesrs.sii.commons.data.dao.grh.CorpsDao;
import dz.gov.mesrs.sii.commons.data.dao.grh.GradeDao;
import dz.gov.mesrs.sii.commons.data.dao.grh.StatutDao;
import dz.gov.mesrs.sii.commons.data.dao.referentiel.NomenclatureDao;
import dz.gov.mesrs.sii.commons.data.model.grh.CategorieProfessionnelle;
import dz.gov.mesrs.sii.commons.data.model.grh.Corps;
import dz.gov.mesrs.sii.commons.data.model.grh.Grade;
import dz.gov.mesrs.sii.commons.data.model.grh.Statut;
import dz.gov.mesrs.sii.commons.data.model.referentiel.Nomenclature;

/**
 * 
 * @author Salem
 * @version V1.0
 * @date 22-10-2014
 * @description Implementation DAO Grade
 * 
 */

@Repository("gradeDao")
@Transactional
public class GradeDaoImpl extends CommonDaoImpl<Grade, Integer> implements GradeDao {

	private CategorieProfessionnelleDao categorieProfessionnelleDao;
	private NomenclatureDao nomenclatureDao;
	private CorpsDao corpsDao;
	private StatutDao statutDao;

	@Override
	protected Class<Grade> getDomainClass() {
		return Grade.class;
	}

	@Override
	protected Criteria getCriteria(Grade example) {
		Criteria criteria = super.getCriteria(example);
		if (example.getCorps() != null) {
			criteria.createAlias("corps", "corps");
			criteria.add(Restrictions.eq("corps", example.getCorps()));

		}
		return criteria;
	}

	@Override
	public Grade save(Grade grade) {
		Assert.notNull(grade,"Can't persist a null entity");
		Nomenclature typeGrade = nomenclatureDao.findById(grade.getNomenclature().getId());
		Corps corps = corpsDao.findById(grade.getCorps().getId());
		grade.setCorps(corps);
		grade.setNomenclature(typeGrade);
		if (grade.getCategorieProfessionnelle() != null && grade.getCategorieProfessionnelle().getId() != null) {
			CategorieProfessionnelle categorieProfessionnelle = categorieProfessionnelleDao.findById(grade
					.getCategorieProfessionnelle().getId());
			grade.setCategorieProfessionnelle(categorieProfessionnelle);
		}
		return super.save(grade);
	}

	@Autowired
	public void setCategorieProfessionnelleDao(CategorieProfessionnelleDao categorieProfessionnelleDao) {
		this.categorieProfessionnelleDao = categorieProfessionnelleDao;
	}

	@Autowired
	public void setNomenclatureDao(NomenclatureDao nomenclatureDao) {
		this.nomenclatureDao = nomenclatureDao;
	}

	@Autowired
	public void setCorpsDao(CorpsDao corpsDao) {
		this.corpsDao = corpsDao;
	}

	@Autowired
	public void setStatutDao(StatutDao statutDao) {
		this.statutDao = statutDao;
	}

}
