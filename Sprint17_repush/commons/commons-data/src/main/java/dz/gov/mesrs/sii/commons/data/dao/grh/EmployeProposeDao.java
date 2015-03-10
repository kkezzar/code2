package dz.gov.mesrs.sii.commons.data.dao.grh;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.dao.CommonDao;
import dz.gov.mesrs.sii.commons.data.model.grh.DossierEmploye;
import dz.gov.mesrs.sii.commons.data.model.grh.EmployePropose;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefEtablissement;



/**
 * 
 * @author Salem
 * @version V1.0
 * @date 21-10-2014
 * @description Interface  DAO EmployePropose
 * 
 */

public interface EmployeProposeDao extends CommonDao<EmployePropose,Long> {

	List<DossierEmploye> findlistEmployesDernierAvancementPromotionParList(	RefEtablissement refEtablissement);

	/**
	 * for specific method
	 */


}