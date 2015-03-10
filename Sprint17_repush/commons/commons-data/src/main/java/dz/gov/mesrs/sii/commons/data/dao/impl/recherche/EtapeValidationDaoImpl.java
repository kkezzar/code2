/**
 * [dz.gov.mesrs.sii.commons.data.dao.impl.recherche.ProgrammeDaoImpl.java] 
 * @author rlaib on : 25 janv. 2015  11:14:12
 */
package dz.gov.mesrs.sii.commons.data.dao.impl.recherche;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.commons.data.dao.impl.fve.scolarite.GenericFveDaoImpl;
import dz.gov.mesrs.sii.commons.data.dao.recherche.EtapeValidationDao;
import dz.gov.mesrs.sii.commons.data.model.bpm.Etape;
import dz.gov.mesrs.sii.commons.data.model.recherche.EtapeValidation;

/**
 * @author rlaib  on : 25 janv. 2015  11:14:12
 */
@Service ("etapeValidationDao")
public class EtapeValidationDaoImpl extends GenericFveDaoImpl<EtapeValidation> implements EtapeValidationDao {

	
	private static final Log log = LogFactory.getLog(EtapeValidationDaoImpl.class);
	/**
	 * [ProgrammeDaoImpl.findByCodeType] Method 
	 * Overridden By : @author sramdane  on : 25 janv. 2015  11:14:12
	 * @param codeTypeProgamme
	 * @return
	 */

	@SuppressWarnings("unchecked")
	@Override
	public List<Etape> findListEtapesByIdProcesus(int processusId) {		
		Query query = entityManager.createQuery("select p from Etape p where p.processus.id = :processusId").setParameter("processusId", processusId);
		return query.getResultList();
	}
	@Override	
	public void removeListEtapeValidationByProjet(Long selectedProjet) {
		Query query = entityManager.createQuery("select p from EtapeValidation p where p.projet.id = :selectedProjet").setParameter("selectedProjet", selectedProjet);
		@SuppressWarnings("unchecked")
		List<EtapeValidation> listEtapeValidation = query.getResultList();
		if(listEtapeValidation!= null) if(!listEtapeValidation.isEmpty()){
			for(EtapeValidation etapeValidation : listEtapeValidation){
				entityManager.merge(etapeValidation);
				entityManager.remove(etapeValidation);
			}
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<EtapeValidation> findListEtapeValidationByProjet(Long selectedProjet) {		
		boolean  realise= false;
		List<EtapeValidation> etape = new ArrayList<EtapeValidation>();
		while(!realise){
		try{		
		Query query = entityManager.createQuery("select p from EtapeValidation p where p.projet.id = :selectedProjet").setParameter("selectedProjet", selectedProjet);		
		etape = query.getResultList();
		boolean realiseint = true;
		for(EtapeValidation ev :etape) {if(ev.getEtapeCircuit()==null)realiseint = false;} 
		if (realiseint)realise= true;
		return etape;
		}catch(Exception e){
		}}
		return etape;
	}
	@Override
	public EtapeValidation findByIdValidation(Long validationId) {
		
		return entityManager.find(EtapeValidation.class, validationId);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean findNonAcceptationValidation(Long selectedProjet,String codeValidation) {		
		boolean  realise= false,accepte= false;
		List<EtapeValidation> etape = new ArrayList<EtapeValidation>();
		//while(!realise){
		//try{		
		Query query = entityManager.createQuery("select p from EtapeValidation p where p.projet.id = :selectedProjet and p.reponce.code not like :codeValidation").setParameter("selectedProjet", selectedProjet).setParameter("codeValidation", codeValidation);		
		etape = query.getResultList();
		
		
		if(etape == null) accepte= true;
		else if(etape.size()==0) accepte= true;
		
		boolean realiseint = true;
		for(EtapeValidation ev :etape) {if(ev.getEtapeCircuit()==null)realiseint = false;} 
		if (realiseint)realise= true;
		
		//}catch(Exception e){
		//}
	//}
		return accepte;
	}
	

	
}
