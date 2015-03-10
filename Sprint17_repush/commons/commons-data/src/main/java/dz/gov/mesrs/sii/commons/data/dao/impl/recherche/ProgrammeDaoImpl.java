/**
 * [dz.gov.mesrs.sii.commons.data.dao.impl.recherche.ProgrammeDaoImpl.java] 
 * @author rlaib on : 25 janv. 2015  11:14:12
 */
package dz.gov.mesrs.sii.commons.data.dao.impl.recherche;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.commons.data.dao.impl.fve.scolarite.GenericFveDaoImpl;
import dz.gov.mesrs.sii.commons.data.dao.recherche.ProgrammeDao;
import dz.gov.mesrs.sii.commons.data.model.recherche.Programme;
import dz.gov.mesrs.sii.commons.data.model.referentiel.Nomenclature;

/**
 * @author rlaib  on : 25 janv. 2015  11:14:12
 */
@Service ("programmeDao")
public class ProgrammeDaoImpl extends GenericFveDaoImpl<Programme> implements ProgrammeDao {

	private static final Log log = LogFactory.getLog(ProgrammeDaoImpl.class);
	/**
	 * [ProgrammeDaoImpl.findByCodeType] Method 
	 * Overridden By : @author rlaib  on : 25 janv. 2015  11:14:12
	 * @param codeTypeProgamme
	 * @return
	 */
	@Override
	public List<Programme> findByType(Integer idTypeProgamme) {
		
		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<Programme> c = cb.createQuery(Programme.class);
			Root<Programme> p = c.from(Programme.class);
			Join<Programme, Nomenclature> pn= p.join("typeProgramme");
			c.select(p).distinct(true);
			List<Predicate> criteria = new ArrayList<Predicate>();
			if(idTypeProgamme != null) {
				ParameterExpression<Integer> param = cb.parameter(Integer.class, "idTypeProgamme");
				criteria.add(cb.equal(pn.get("id"), param));
			}
			c.where(cb.and(criteria.toArray(new Predicate[0])));
			TypedQuery<Programme> q = entityManager.createQuery(c);
			if (idTypeProgamme != null) { 
				q.setParameter("idTypeProgamme", idTypeProgamme);
			}
			List<Programme> result = q.getResultList();
			for(Programme entity: result){
				if(entityManager.contains(entity)){
						entityManager.refresh(entity);
				}
			}
			return result;
		}
		catch (RuntimeException re) {

			log.error("findByType 'Programme' failed", re);
			throw re;
		}
	}

	/**
	 * [ProgrammeDaoImpl.findByKeyWords] Method 
	 * Overridden By : @author rlaib  on : 25 janv. 2015  11:14:12
	 * @param keyWord
	 * @return
	 */
	@Override
	public List<Programme> findByKeyWords(String keyWord) {
		
		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<Programme> c = cb.createQuery(Programme.class);
			Root<Programme> p = c.from(Programme.class);
			c.select(p).distinct(true);
			c.orderBy(cb.desc(p.get("dateDebut")));
			List<Predicate> criteria = new ArrayList<Predicate>();
			if(keyWord != null) {
				criteria.add(cb.like(cb.lower(p.<String>get("code")), "%" + keyWord.toLowerCase() + "%" ));
				criteria.add(cb.like(cb.lower(p.<String>get("intituleFr")), "%" + keyWord.toLowerCase() + "%" ));
				criteria.add(cb.like(cb.lower(p.<String>get("intituleAr")), "%" + keyWord.toLowerCase() + "%" ));
			}
			c.where(cb.or(criteria.toArray(new Predicate[0])));
			TypedQuery<Programme> q = entityManager.createQuery(c);
			List<Programme> result = q.getResultList();
				for(Programme entity: result){
					if(entityManager.contains(entity)){
							entityManager.refresh(entity);
					}
				}
				return result;
		}
		catch (RuntimeException re) {

				log.error("findByKeyWords 'Programme' failed", re);
				throw re;
		}
	}

	/**
	 * [ProgrammeDao.findByPeriod] Method 
	 * Overridden By : @author rlaib  on : 3 févr. 2015  11:46:11
	 * @param startYear
	 * @param endYear
	 * @return
	 */
	@Override
	public List<Programme> findByPeriod(Short startYear, Short endYear) {

		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<Programme> c = cb.createQuery(Programme.class);
			Root<Programme> p = c.from(Programme.class);
			c.select(p).distinct(true);
			List<Predicate> criteria = new ArrayList<Predicate>();
			if(startYear != null) {
				ParameterExpression<Date> param1 = cb.parameter(Date.class, "startYear");
				criteria.add(cb.greaterThanOrEqualTo(p.<Date>get("dateDebut"), param1));
			}
			if(endYear != null) {
				ParameterExpression<Date> param2 = cb.parameter(Date.class, "endYear");
				criteria.add(cb.lessThanOrEqualTo(p.<Date>get("dateFin"), param2));
			}
			c.where(cb.and(criteria.toArray(new Predicate[0])));
			TypedQuery<Programme> q = entityManager.createQuery(c);
			if (startYear != null) { 
				Calendar deginDateValue = new GregorianCalendar();
				deginDateValue.set(startYear, 1, 1);
				q.setParameter("startYear", deginDateValue.getTime());
			}
			if (endYear != null) { 
				Calendar endDateValue = new GregorianCalendar();
				endDateValue.set(endYear, 12, 31);
				q.setParameter("endYear", endDateValue.getTime());
			}
			List<Programme> result = q.getResultList();
			for(Programme entity: result){
				if(entityManager.contains(entity)){
						entityManager.refresh(entity);
				}
			}
			return result;
		}
		catch (RuntimeException re) {

			log.error("findByType 'Programme' failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Programme> findActifProgramme() {
	Date date = new Date();
	//SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");	
	//String	dateAct = formatter.format(date);
	//try {
	//	date= formatter.parse(dateAct);
	//} catch (ParseException e) {
		// TODO Auto-generated catch block
		//e.printStackTrace() ;
	//}
	Query query=entityManager.createQuery("select  p from Programme p where p.dateDebut <= :dateAct and p.dateFin >= :dateAct").setParameter("dateAct", date);
	return query.getResultList();
	
	}
}
