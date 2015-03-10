package dz.gov.mesrs.sii.commons.data.dao.gfc;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.dao.CommonDao;
import dz.gov.mesrs.sii.commons.data.model.gfc.FicheBesoins;

/**
 * Dao Interface for domain model class FicheBesoins.
 * 
 * @see dz.gov.mesrs.sii.gfc.business.persistance.business.model.bo.FicheBesoins
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
public interface FicheBesoinsDao extends CommonDao<FicheBesoins, Integer> {

	/**
	 * Consolider la fiche des besoins
	 * 
	 * @author Mounir.MESSAOUDI on : 23 déc. 2014 14:27:24
	 * @param ficheBesoins
	 * @return
	 */
	public FicheBesoins consoliderFicheBesoins(FicheBesoins ficheBesoins);

	public List<Object[]> getTotalOfFicheBesoins(FicheBesoins ficheBesoins);

	public List<Object[]> getTotalOfConsolidationBesoins(FicheBesoins ficheBesoins);
}