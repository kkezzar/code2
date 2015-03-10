package dz.gov.mesrs.sii.grh.business.service;


import java.util.Collection;
import java.util.Date;
import java.util.List;

import dz.gov.mesrs.sii.commons.business.service.CommonService;
import dz.gov.mesrs.sii.commons.data.model.grh.Carriere;
import dz.gov.mesrs.sii.grh.business.model.dto.CarriereDto;
import dz.gov.mesrs.sii.grh.business.model.dto.EmployeProposeDto;
import dz.gov.mesrs.sii.grh.business.model.dto.PropostionAvancementDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;



/**
 * 
 * @author Salem
 * @version V1.0
 * @date 22-10-2014
 * @description Interface  Service Carriere
 * 
 */
 
public interface  CarriereService extends CommonService< Carriere,CarriereDto,Integer>{
	/**
	 * for specific method
	 */
	
	

	



	List<EmployeProposeDto> agentProposableEchelon(Integer mois, Integer annee,	Integer dureeMinEchelon, Date dateEffetProposee,
			PropostionAvancementDto propostionAvancementDto,Integer refEtablissement,Collection<Integer> listNomenclatureActifDetachementID);

	List<EmployeProposeDto> agentProposableListAptitude(
			List<Long> listIDEmployer,
			PropostionAvancementDto propostionAvancementDto);

	void updateCarrierePromotion(EmployeProposeDto employeProposeDto,
			NomenclatureDto nomenclatureBytypePromotionDto);

	

}