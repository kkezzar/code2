package dz.gov.mesrs.sii.commons.data.dao.impl.grh;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import dz.gov.mesrs.sii.commons.data.dao.CommonDaoImpl;
import dz.gov.mesrs.sii.commons.data.dao.grh.DossierEmployeDao;
import dz.gov.mesrs.sii.commons.data.model.grh.Carriere;
import dz.gov.mesrs.sii.commons.data.model.grh.DossierEmploye;
import dz.gov.mesrs.sii.commons.data.model.grh.Grade;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefEtablissement;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefIndividu;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefStructure;


/**
 * 
 * @author Salem
 * @version V1.0
 * @date 22-10-2014
 * @description Implementation DAO DossierEmploye
 * 
 */

@Repository("dossierEmployeDao")
public class DossierEmployeDaoImpl extends CommonDaoImpl<DossierEmploye, Long> implements DossierEmployeDao {

	@Override
	protected Class<DossierEmploye> getDomainClass() {
		return DossierEmploye.class;
	}

	@Override
	protected Criteria getCriteria(DossierEmploye example) {
		Criteria criteria = getSession().createCriteria(example.getClass());
		// Criteria criteria = super.getCriteria(example);
		RefEtablissement refEtablissement = example.getRefEtablissement();
		if (refEtablissement != null) {
			criteria.createAlias("refEtablissement", "refEtablissement");
			if (refEtablissement.getId() != 0) {
				criteria.add(Restrictions.disjunction().add(
						Restrictions.eq("refEtablissement.id", refEtablissement.getId())));
			}
		}
		if (!StringUtils.isEmpty(example.getMatricule())) {
			criteria.add(Restrictions.disjunction().add(
					Restrictions.eq("matricule", example.getMatricule()).ignoreCase()));

		}
		RefIndividu refIndividu = example.getRefIndividu();
		if (refIndividu != null) {

			
			criteria.createAlias("refIndividu", "refIndividu", CriteriaSpecification.LEFT_JOIN);
			
			if(refIndividu.getId()!=0){
				criteria.add(Restrictions.eq("refIndividu.id", refIndividu.getId()));
			}
			
			if (!StringUtils.isEmpty(refIndividu.getNomLatin())) {
				criteria.add(Restrictions
						.disjunction()
						.add(Restrictions.like("refIndividu.nomLatin", refIndividu.getNomLatin(), MatchMode.ANYWHERE)
								.ignoreCase())
						.add(Restrictions.like("refIndividu.nomArabe", refIndividu.getNomLatin(), MatchMode.ANYWHERE)));
			}
			if (!StringUtils.isEmpty(refIndividu.getPrenomLatin())) {
				criteria.add(Restrictions
						.disjunction()
						.add(Restrictions.like("refIndividu.prenomLatin", refIndividu.getPrenomLatin(),
								MatchMode.ANYWHERE).ignoreCase())
						.add(Restrictions.like("refIndividu.prenomArabe", refIndividu.getPrenomLatin(),
								MatchMode.ANYWHERE)));
			}
			if (!StringUtils.isEmpty(refIndividu.getPrenomArabe())) {
				criteria.add(Restrictions.like("refIndividu.prenomArabe", refIndividu.getPrenomArabe(),
						MatchMode.ANYWHERE));
			}
			if (!StringUtils.isEmpty(refIndividu.getIdentifiant())) {
				criteria.add(Restrictions.like("refIndividu.identifiant", refIndividu.getIdentifiant(),
						MatchMode.ANYWHERE));

			}
			
			

		}
		
		RefStructure refStructure = example.getRefStructure();
		if (refStructure != null) {
			criteria.createAlias("refStructure", "refStructure",CriteriaSpecification.LEFT_JOIN);
			if (!StringUtils.isEmpty(refStructure.getLlStructureLatin())) {
				criteria.add(Restrictions.like("refStructure.llStructureLatin", refStructure.getLlStructureLatin(),
						MatchMode.ANYWHERE));

			}
			
		}
		
		if (example.getIdenfiant() != null) {
			criteria.add(Restrictions.eq(example.getIdentifiantName(), example.getIdenfiant()));
		}
		criteria.createAlias("currentPosition", "currentPosition",CriteriaSpecification.LEFT_JOIN);
		if(example.getNiveauCompetence()!=null && example.getNiveauCompetence().getId()> 0){
			criteria.createAlias("niveauCompetence", "niveauCompetence");
			criteria.add(Restrictions.eq("niveauCompetence.id", example.getNiveauCompetence().getId()));
		}
		
		if(example.getNiveauQualification()!=null && example.getNiveauQualification().getId()> 0){
			criteria.createAlias("niveauQualification", "niveauQualification");
			criteria.add(Restrictions.eq("niveauQualification.id", example.getNiveauQualification().getId()));
		}
		return criteria;
	}
	
	
	

	// @Override
	// protected void enrirchCriteriaWithExample(Criteria criteria,
	// DossierEmploye example, Collection collection,
	// String propretyName, short operator) {
	// if (operator == UtilConstant.OPERATOR_IN) {
	// criteria.add(Restrictions.in(propretyName, collection));
	//
	// } else if (operator == UtilConstant.OPERATOR_NOT_IN) {
	// criteria.add(Restrictions.not(Restrictions.in(propretyName,
	// collection)));
	//
	// }
	// }
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Long> findListeAptitudePromotionID(List<Object> paramettres,Collection<Integer> listNomenclatureActifDetachementID) {
		Criteria criteria = getSession().createCriteria(DossierEmploye.class);		
		criteria.createAlias("carriereCourante","carriereCourante");		
		criteria.add(Restrictions.le("carriereCourante.dateEffetGrade",(Date) paramettres.get(0)));
		Disjunction or = Restrictions.disjunction();
		or.add(Restrictions.isNull("carriereCourante.nomenclatureBytypePromotion.id"));
		or.add(Restrictions.ne("carriereCourante.nomenclatureBytypePromotion.id",(Integer) paramettres.get(1)));
		criteria.add(or);
		criteria.createAlias("currentPosition", "currentPosition");
		criteria.add(Restrictions.in("currentPosition.nomenclatureByPosition.id",listNomenclatureActifDetachementID.toArray()));
		criteria.add(Restrictions.eq("refEtablissement.id",(Integer) paramettres.get(2)));
		ProjectionList proj = Projections.projectionList();
		proj.add(Projections.distinct(Projections.property("id")));
		criteria.setProjection(proj);		
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Long> findListePromotionParModaliteID(List<Object> paramettres,Collection<Integer> listNomenclatureActifDetachementID
			,Collection<Integer> listnomenclatureBytypePromotionModaliteID,Collection<Integer> listNomenclatureModaliteRecrutementID) {
		
		Long maxInidiceMinimale=recherchemaxInidiceMinimale();
		if(maxInidiceMinimale != null){
		Criteria criteria = getSession().createCriteria(DossierEmploye.class);		
		criteria.createAlias("carriereCourante","carriereCourante");
		criteria.createAlias("carriereCourante.grade","grade");
		criteria.createAlias("grade.categorieProfessionnelle","categorieProfessionnelle");
        criteria.createAlias("currentPosition", "currentPosition");
        criteria.add(Restrictions.in("currentPosition.nomenclatureByPosition.id",listNomenclatureActifDetachementID.toArray()));		
        criteria.add(Restrictions.eq("refEtablissement.id",(Integer) paramettres.get(0)));
       // criteria.add(Restrictions.lt("categorieProfessionnelle.indiceMin", maxInidiceMinimale));
		////////////////////////
        Criteria employeNONconcerne  = getSession().createCriteria(Carriere.class);	
        employeNONconcerne.createAlias("dossierEmploye","dossierEmploye");
        employeNONconcerne.add(Restrictions.in("nomenclatureBytypePromotion.id",listnomenclatureBytypePromotionModaliteID.toArray()));
        employeNONconcerne.add(Restrictions.eq("dossierEmploye.refEtablissement.id",(Integer) paramettres.get(0)));
        ProjectionList proj = Projections.projectionList();
        proj.add(Projections.distinct(Projections.property("dossierEmploye.id")));
        employeNONconcerne.setProjection(proj);				
	    /////////////////////////
        if(employeNONconcerne.list() != null)if(!employeNONconcerne.list().isEmpty())
        criteria.add(Restrictions.not((Restrictions.in("id",employeNONconcerne.list()))));
        proj = Projections.projectionList();
        proj.add(Projections.distinct(Projections.property("id")));
		criteria.setProjection(proj);		
		
		List<Long> result=criteria.list(); 
		return result;
		
		
		}else return new ArrayList<Long>();
	}
	
	private Long recherchemaxInidiceMinimale() {
		Criteria criteria = getSession().createCriteria(Grade.class);
		criteria.createAlias("categorieProfessionnelle", "categorieProfessionnelle");		
		ProjectionList proj = Projections.projectionList();
        proj.add(Projections.distinct(Projections.max("categorieProfessionnelle.indiceMin")));
        criteria.setProjection(proj);
        @SuppressWarnings("unchecked")
		List<Long> indices = criteria.list();
        if(indices != null){if(!indices.isEmpty())return indices.get(0);
        else return null;}else return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Long> findListePromotionParModalConfID(List<Object> paramettres,Collection<Integer> listNomenclatureActifDetachementID
			,Collection<Integer> listnomenclatureBytypePromotionModaliteID,Collection<Integer> listNomenclatureModaliteRecrutementID) {
		Criteria criteria = getSession().createCriteria(Carriere.class);
		criteria.createAlias("dossierEmploye","dossierEmploye");
		criteria.createAlias("dossierEmploye.currentPosition", "currentPosition");
        criteria.add(Restrictions.in("currentPosition.nomenclatureByPosition.id",listNomenclatureActifDetachementID.toArray()));		
        criteria.add(Restrictions.eq("dossierEmploye.refEtablissement.id",(Integer) paramettres.get(0)));
		criteria.add(Restrictions.in("nomenclatureBytypePromotion.id",listnomenclatureBytypePromotionModaliteID.toArray()));
		criteria.add(Restrictions.eq("dossierEmploye.refEtablissement.id",(Integer) paramettres.get(0)));
		criteria.add(Restrictions.isNull("confirm"));
		ProjectionList proj = Projections.projectionList();
		proj.add(Projections.distinct(Projections.property("dossierEmploye.id")));
		criteria.setProjection(proj);	
		return criteria.list();
	}
	
	
	
	////COMMIT 15	:  40

	
		
	
	
}
