package dz.gov.mesrs.sii.commons.data.dao.impl.grh;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import dz.gov.mesrs.sii.commons.data.dao.CommonDaoImpl;
import dz.gov.mesrs.sii.commons.data.dao.grh.CarriereDao;
import dz.gov.mesrs.sii.commons.data.model.grh.Carriere;
import dz.gov.mesrs.sii.commons.data.model.grh.DossierEmploye;
import dz.gov.mesrs.sii.commons.data.model.grh.EmployePropose;
import dz.gov.mesrs.sii.commons.data.model.grh.GrilleIndiciaire;
import dz.gov.mesrs.sii.commons.data.model.grh.PropostionAvancement;
import dz.gov.mesrs.sii.commons.data.model.referentiel.Nomenclature;

/**
 * 
 * @author Salem
 * @version V1.0
 * @date 22-10-2014
 * @description Implementation DAO Carriere
 * 
 */

@Repository("carriereDao")
public class CarriereDaoImpl extends CommonDaoImpl<Carriere, Integer> implements CarriereDao {

	@Override
	protected Class<Carriere> getDomainClass() {
		return Carriere.class;
	}

	// @Autowired
	// public CarriereDaoImpl(@Qualifier("hibernateTemplate") HibernateTemplate
	// hibernateTemplate) {
	// super.setHibernateTemplate(hibernateTemplate);
	// }

	
	// recherche de la liste des meployer proposable pour l'avancement echelon 
	@Override
	public List<EmployePropose> agentProposableEchelon(Integer mois, Integer annee, Integer dureeMinEchelon,Date dateEffetProposee, PropostionAvancement propostionAvancement, Integer refEtablissement
			,Collection<Integer> listNomenclatureActifDetachementID) {

		List<EmployePropose> employeProposes = new ArrayList<EmployePropose>();
		////////
			Criteria criteria = getSession().createCriteria(DossierEmploye.class);		
			criteria.createAlias("carriereCourante","carriereCourante");
			criteria.add(Restrictions.not(Restrictions.isNull("carriereCourante.dateEffetEchelon")));
			criteria.createAlias("currentPosition", "currentPosition");
			criteria.add(Restrictions.in("currentPosition.nomenclatureByPosition.id",listNomenclatureActifDetachementID.toArray()));		
			criteria.add(Restrictions.eq("refEtablissement.id",refEtablissement));
			ProjectionList proj = Projections.projectionList();
			proj.add(Projections.distinct(Projections.property("carriereCourante")));
			criteria.setProjection(proj);		
			@SuppressWarnings("unchecked")
			List<Carriere> carriereMinDateEchelonEmployes =	criteria.list();		
			for (Carriere carriere :carriereMinDateEchelonEmployes) {
				if(carriere != null){
				Integer dureeDansEchelon = rechercheDureeDansEchelon(carriere.getDateEffetEchelon(), mois, annee);	
				if (dureeMinEchelon <= dureeDansEchelon) {
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(carriere.getDateEffetEchelon());
					calendar.add(Calendar.MONTH, dureeMinEchelon);
					EmployePropose employePropose = new EmployePropose();
					employePropose.setCarriere(carriere);
					employePropose.setDateEffetProposee(calendar.getTime());
					employePropose.setDossierEmploye(carriere.getDossierEmploye());
					employePropose.setDuree(dureeDansEchelon);
					employePropose.setPropostionAvancement(propostionAvancement);
					employePropose.setSelection(true);
					String echelon = carriere.getDossierEmploye().getCarriereCourante().getGrilleIndiciaire().getEchlon().toString();
					String maxEchelon =rechercheMaxEchelon(carriere.getDossierEmploye().getCarriereCourante().getGrilleIndiciaire().getCategorieProfessionnelle().getId()); 
					if((echelon!= null)&&(maxEchelon!= null))
						if(!echelon.equals(maxEchelon))	employeProposes.add(employePropose);
				}
				}				
			}
		return employeProposes;
	}
	
	@Override
	public List<EmployePropose> agentProposableListAptitude(List<Long> listIDEmployer, PropostionAvancement propostionAvancement) {
       if(listIDEmployer != null){ if(!listIDEmployer.isEmpty()){
		List<EmployePropose> employeProposes = new ArrayList<EmployePropose>();
			Criteria criteria = getSession().createCriteria(DossierEmploye.class);		
			criteria.add(Restrictions.in("id",listIDEmployer.toArray()));
			@SuppressWarnings("unchecked")			
			List<DossierEmploye> dEmployers=criteria.list();				
			for (DossierEmploye dEmployer :dEmployers) {
				if(dEmployer != null){
				Carriere carriere = dEmployer.getCarriereCourante();
				if(carriere != null){
					EmployePropose employePropose = new EmployePropose();
					employePropose.setCarriere(carriere);
					employePropose.setDossierEmploye(carriere.getDossierEmploye());
					employePropose.setPropostionAvancement(propostionAvancement);
					employePropose.setSelection(true);					
					employeProposes.add(employePropose);	
				}				
			}}
		return employeProposes;
		}else return new ArrayList<EmployePropose>();
       }else return new ArrayList<EmployePropose>();
	}
	
	
	
	
	

	@SuppressWarnings("deprecation")
	private Integer rechercheDureeDansEchelon(Date dateInitialEchelon, Integer mois, Integer annee) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("annee");
		stringBuilder.append(dateInitialEchelon.getYear() + 1900);
		stringBuilder.append("mois");
		stringBuilder.append(dateInitialEchelon.getMonth());
		Integer dureeDansEchelon = (annee - (dateInitialEchelon.getYear() + 1900)) * 12
				+ (mois - dateInitialEchelon.getMonth());
		return dureeDansEchelon;
	}
	
	
	
	
	
	
	
	
	
	private String rechercheMaxEchelon(Integer id){		
		if(id!= null){
		Criteria criteria = getSession().createCriteria(GrilleIndiciaire.class);		
		criteria.createAlias("categorieProfessionnelle", "categorieProfessionnelle");
		criteria.add(Restrictions.eq("categorieProfessionnelle.id",id));
		ProjectionList proj = Projections.projectionList();
		proj.add(Projections.max("indice"));
		criteria.setProjection(proj);
		@SuppressWarnings("unchecked")
		List<Long> indices = criteria.list();
		Long indice=-1l;
		if(indices != null){if(!indices.isEmpty()){	indice = indices.get(0);}}
		Criteria maxechelon = getSession().createCriteria(GrilleIndiciaire.class);
		maxechelon.createAlias("categorieProfessionnelle", "categorieProfessionnelle");
		maxechelon.add(Restrictions.eq("categorieProfessionnelle.id",id));
		maxechelon.add(Restrictions.eq("indice",indice));
			proj = Projections.projectionList();
			proj.add(Projections.property("echlon"));
			maxechelon.setProjection(proj);
			@SuppressWarnings("unchecked")
			List<String> echelons  = maxechelon.list();
			if(echelons != null){
				if(!echelons.isEmpty()){return echelons.get(0);				
				}else return null;			
		        }else return null;		
		       }else return null;
		
	}
	@Override
	public void updateCarrierePromotion(EmployePropose employePropose,Nomenclature nomenclatureBytypePromotion){
		if(employePropose!= null)if(employePropose.getNouveauGrade()!=null){
			Carriere carriere= new Carriere(),carrierecurent=employePropose.getDossierEmploye().getCarriereCourante();
			carriere.setConfirm(true);
			carriere.setDateEffet(employePropose.getDateEffetRetenue());
			carriere.setDateEffetEchelon(carrierecurent.getDateEffetEchelon());
			carriere.setDateEffetGrade(employePropose.getDateEffetRetenue());
			carriere.setDateEffetCorps(employePropose.getDateEffetRetenue());
			carriere.setGrade(employePropose.getNouveauGrade());
			carriere.setDossierEmploye(employePropose.getDossierEmploye());
			carriere.setGrilleIndiciaire(employePropose.getgrilleIndiciaire());
			carriere.setNomenclatureBytypePromotion(nomenclatureBytypePromotion);
			this.entityManager.persist(carriere);			
			carriere= this.entityManager.find(Carriere.class, carriere.getId());
			DossierEmploye dossierEmploye = this.entityManager.find(DossierEmploye.class, employePropose.getDossierEmploye().getId());
			if(dossierEmploye != null){
				dossierEmploye.setCarriereCourante(carriere);
			}
			
			
			
		}
		
		
		
		
	}
	
	
	
	

}
