package dz.gov.mesrs.sii.commons.data.dao.impl.grh;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.CommonDaoImpl;
import dz.gov.mesrs.sii.commons.data.model.gfc.AffectationEtabChapitreArticle;
import dz.gov.mesrs.sii.commons.data.model.grh.ChangementPosition;
import dz.gov.mesrs.sii.commons.data.model.grh.DossierEmploye;
import dz.gov.mesrs.sii.commons.data.model.grh.EmployePropose;
import dz.gov.mesrs.sii.commons.data.model.referentiel.Nomenclature;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefEtablissement;
import dz.gov.mesrs.sii.commons.data.dao.grh.EmployeProposeDao;

/**
 * 
 * @author Salem
 * @version V1.0
 * @date 22-10-2014
 * @description Implementation DAO EmployePropose
 * 
 */


@Repository ("employeProposeDao")  @Transactional
public class EmployeProposeDaoImpl extends CommonDaoImpl<EmployePropose, Long> implements EmployeProposeDao {
	
	@Override
	protected Class<EmployePropose> getDomainClass() {
		return EmployePropose.class;
	}
	
//	@Autowired
  
	@Override
	public List<DossierEmploye> findlistEmployesDernierAvancementPromotionParList(RefEtablissement refEtablissement){	
		List<DossierEmploye> listChangementPosition = new ArrayList<DossierEmploye>();
			if(refEtablissement != null){
			Criteria criteria = getSession().createCriteria(EmployePropose.class,"emplpro");
			criteria.createAlias("dossierEmploye", "dossierEmploye");		
			criteria.add(Restrictions.eq("dossierEmploye.refEtablissement", refEtablissement));			
			criteria.createAlias("propostionAvancement", "propostionAvancement");		
			criteria.add(Restrictions.eq("propostionAvancement.nomenclatureByTypeAvancement.id", 624955));
			criteria.add(Restrictions.eq("confirm", true));					
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EmployePropose.class, "emplprodatemax");
			ProjectionList proj =Projections.projectionList();
			proj.add(Projections.distinct(Projections.max("dateEffetProposee")));			
			detachedCriteria.add(Property.forName("emplpro.dossierEmploye.id").eqProperty("emplprodatemax.dossierEmploye.id") );
			detachedCriteria.createAlias("propostionAvancement", "propostionAvancement");	
			detachedCriteria.add(Restrictions.eq("propostionAvancement.nomenclatureByTypeAvancement.id", 624955));
			detachedCriteria.add(Restrictions.eq("confirm", true));	
			detachedCriteria.setProjection(proj);		
			criteria.add( Property.forName("dateEffetProposee").eq(detachedCriteria) );
			criteria.setProjection(Projections.distinct(Projections.property("dossierEmploye")));
			listChangementPosition = criteria.list();	
	}		return listChangementPosition;
	}
	


}
