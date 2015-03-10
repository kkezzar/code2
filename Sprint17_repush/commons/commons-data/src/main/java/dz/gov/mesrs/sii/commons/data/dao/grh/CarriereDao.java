package dz.gov.mesrs.sii.commons.data.dao.grh;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import dz.gov.mesrs.sii.commons.data.dao.CommonDao;
import dz.gov.mesrs.sii.commons.data.model.grh.Carriere;
import dz.gov.mesrs.sii.commons.data.model.grh.EmployePropose;
import dz.gov.mesrs.sii.commons.data.model.grh.PropostionAvancement;
import dz.gov.mesrs.sii.commons.data.model.referentiel.Nomenclature;



 
/**
 * 
 * @author Salem
 * @version V1.0
 * @date 21-10-2014
 * @description Interface  DAO CandidatEmploye
 */
public interface CarriereDao extends CommonDao<Carriere, Integer> {


	List<EmployePropose> agentProposableEchelon(Integer mois, Integer annee,Integer dureeMinEchelon, Date dateEffetProposee,
			PropostionAvancement propostionAvancement,Integer refEtablissement,	Collection<Integer> listNomenclatureActifDetachementID);

	List<EmployePropose> agentProposableListAptitude(List<Long> listIDEmployer,	PropostionAvancement propostionAvancement);

	void updateCarrierePromotion(EmployePropose employePropose,Nomenclature nomenclatureBytypePromotion);

	

	/**
	 * for specific method
	 */

}