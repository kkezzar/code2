package dz.gov.mesrs.sii.commons.data.dao.impl.grh;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import dz.gov.mesrs.sii.commons.data.dao.CommonDaoImpl;
import dz.gov.mesrs.sii.commons.data.model.grh.EmployePropose;
import dz.gov.mesrs.sii.commons.data.model.grh.PropostionAvancement;
import dz.gov.mesrs.sii.commons.data.dao.grh.PropostionAvancementDao;

/**
 * 
 * @author Salem
 * @version V1.0
 * @date 22-10-2014
 * @description Implementation DAO PropostionAvancement
 * 
 */


@Repository ("propostionAvancementDao") 
public class PropostionAvancementDaoImpl extends CommonDaoImpl<PropostionAvancement, Long> implements PropostionAvancementDao {
	
	@Override
	protected Class<PropostionAvancement> getDomainClass() {
		return PropostionAvancement.class;
	}
	@Override
	protected Criteria getCriteriaForKeyWord(String keyWord) {
		Criteria criteria = null ;
		if(keyWord != null){
		 criteria = getSession().createCriteria(PropostionAvancement.class);
         if(keyWord != ""){
	     Integer annee = Integer.valueOf(keyWord);
	     criteria.add(Restrictions.disjunction().add(Restrictions.eq("annee", annee)));
		}}		
		return criteria;
	}
	@Override
public void deleteAllEmployProposition(PropostionAvancement propostionAvancement){
	
	List<EmployePropose> employeProposes=propostionAvancement.getEmployeProposes();
	if(employeProposes != null)if(!employeProposes.isEmpty())
		for(EmployePropose employePropose:employeProposes){
			EmployePropose employeProp =this.entityManager.find(EmployePropose.class, employePropose.getId()) ;
			if(employeProp!=null)this.entityManager.remove(employeProp);
		}
	
}



	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> maxDatepropositionAvancement(Integer situation,Integer refetablicement,Integer typeavancement){
		List<String> paramettresPeriode =new ArrayList<String>();
		List<Date> maxDatesProposition= new ArrayList<Date>();		
		try{
		Query query= entityManager.createQuery("select Max(propostionAvancement.dateProposition) from PropostionAvancement propostionAvancement  "
				+ "where propostionAvancement.nomenclatureByTypeAvancement.id= :typeavancement and propostionAvancement.situation.id= :situation"
								+ " and propostionAvancement.refEtablissement.id= :refetablicement").setParameter("situation", situation).setParameter("refetablicement", refetablicement).setParameter("typeavancement", typeavancement);
		
		maxDatesProposition= query.getResultList();		
		if(maxDatesProposition!= null)if(!maxDatesProposition.isEmpty())paramettresPeriode.add(maxDatesProposition.toString());
		else  paramettresPeriode.add("1900-01-01");
		query=entityManager.createQuery("select distinct propostionAvancement from PropostionAvancement propostionAvancement  where propostionAvancement.annee = ("
			    + "select max(propostionAvancementa.annee) from PropostionAvancement propostionAvancementa  where     "
			    + "  propostionAvancementa.nomenclatureByTypeAvancement.id= :typeavancement  and propostionAvancementa.situation.id= :situation and propostionAvancementa.refEtablissement.id= :refetablicement  ) and"
			    + " propostionAvancement.mois.code = ("
			    + "select distinct max(propostionAvancementm.mois.code) from PropostionAvancement propostionAvancementm  where  propostionAvancementm.annee =propostionAvancement.annee "
			    + "    and propostionAvancementm.nomenclatureByTypeAvancement.id= :typeavancement and propostionAvancementm.situation.id= :situation and propostionAvancementm.refEtablissement.id= :refetablicement ) "
			    + " and propostionAvancement.situation.id= :situation and propostionAvancement.refEtablissement.id= :refetablicement ");
 query.setParameter("situation", situation).setParameter("refetablicement", refetablicement).setParameter("typeavancement", typeavancement);
		
		List<PropostionAvancement> propostionAvancements= query.getResultList();		
		if(propostionAvancements!= null)if(!propostionAvancements.isEmpty()){			
			paramettresPeriode.add(propostionAvancements.get(0).getAnnee().toString());
			paramettresPeriode.add(propostionAvancements.get(0).getMois().getCode().toString());}
		else{  paramettresPeriode.add("0");
		paramettresPeriode.add("0");}				
		}catch(Exception e){
			paramettresPeriode.add("0");
			paramettresPeriode.add("0");
			paramettresPeriode.add("0");
		}
		return paramettresPeriode;	
	}
	
		







}
