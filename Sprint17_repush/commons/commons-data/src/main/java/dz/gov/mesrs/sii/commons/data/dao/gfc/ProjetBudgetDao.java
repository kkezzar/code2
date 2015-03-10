package dz.gov.mesrs.sii.commons.data.dao.gfc;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.dao.CommonDao;
import dz.gov.mesrs.sii.commons.data.model.gfc.ProjetBudget;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefEtablissement;

/**
 * Dao Interface for domain model class ProjetBudget.
 * 
 * @see dz.gov.mesrs.sii.gfc.business.persistance.business.model.bo.ProjetBudget
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
public interface ProjetBudgetDao extends CommonDao<ProjetBudget, Integer> {
	/**
	 * for specific method
	 */

	/**
	 * Consolider les consolidations de tous les fiches des besoins
	 * 
	 * @author Mounir.MESSAOUDI on : 23 dÃ©c. 2014 14:27:24
	 * @param projetBudget
	 * @return
	 */
	public Boolean consoliderProjetBudget(ProjetBudget projetBudget);

	public List<Object[]> getTotalOfDetailsProjetBudget(ProjetBudget projetBudget);

	public List<Object[]> getTotalOfDetailsProjetBudgetByEtab(ProjetBudget projetBudget, RefEtablissement etablissement);

	public List<Object[]> getAllTotalOfDetailsProjetBudgetByEtab(ProjetBudget projetBudget);
}