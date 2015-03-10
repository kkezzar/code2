package dz.gov.mesrs.sii.grh.business.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
















import dz.gov.mesrs.sii.commons.business.service.impl.CommonServiceImpl;
import dz.gov.mesrs.sii.commons.data.dao.CommonDao;
import dz.gov.mesrs.sii.commons.data.model.grh.Carriere;
import dz.gov.mesrs.sii.commons.data.model.grh.EmployePropose;
import dz.gov.mesrs.sii.commons.data.model.grh.PropostionAvancement;
import dz.gov.mesrs.sii.commons.data.model.referentiel.Nomenclature;
import dz.gov.mesrs.sii.grh.business.model.dto.CarriereDto;
import dz.gov.mesrs.sii.grh.business.model.dto.EmployeProposeDto;
import dz.gov.mesrs.sii.grh.business.model.dto.PropostionAvancementDto;
import dz.gov.mesrs.sii.commons.data.dao.grh.CarriereDao;
import dz.gov.mesrs.sii.grh.business.service.CarriereService;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;

/**
 * 
 * @author Salem
 * @version V1.0
 * @date 22-10-2014
 * @description Implementation des services
 * 
 */
@Service("carriereService")
public class CarriereServiceImpl extends CommonServiceImpl<Carriere,CarriereDto, Integer> implements CarriereService  {
	
	private CarriereDao	carriereDao;

	@Override
	protected CommonDao<Carriere, Integer> getDao() {
		return carriereDao;
	}

	/**
	 * @return the carriereDao
	 */
	public CarriereDao getCarriereDao() {
		return carriereDao;
	}

	/**
	 * @param carriereDao  to set
	 */
	@Autowired
	public void setCarriereDao(CarriereDao carriereDao) {
		this.carriereDao = carriereDao;
	}
	
	
	@Override
	public List<EmployeProposeDto> agentProposableEchelon(Integer mois,Integer annee, Integer dureeMinEchelon, Date dateEffetProposee,
		
			PropostionAvancementDto propostionAvancementDto,Integer refEtablissement,Collection<Integer> listNomenclatureActifDetachementID) {
		
		List<EmployeProposeDto> employeProposeDtos = new ArrayList<EmployeProposeDto>();
		if((mois != null)&&(annee != null)){	
		PropostionAvancement propostionAvancement=mapper.map(propostionAvancementDto, PropostionAvancement.class);
		List<EmployePropose> employeProposes=carriereDao.agentProposableEchelon(mois, annee, dureeMinEchelon, dateEffetProposee, propostionAvancement,refEtablissement,listNomenclatureActifDetachementID);
			
		for (EmployePropose employePropose : employeProposes) {
			EmployeProposeDto employeProposeDto = mapper.map(employePropose, EmployeProposeDto.class);
		employeProposeDtos.add(employeProposeDto);
		}}
			
		return employeProposeDtos;	
	
	}
	
	@Override
	public List<EmployeProposeDto> agentProposableListAptitude(List<Long> listIDEmployer, PropostionAvancementDto propostionAvancementDto) {
		List<EmployeProposeDto> employeProposeDtos = new ArrayList<EmployeProposeDto>();
		PropostionAvancement propostionAvancement=mapper.map(propostionAvancementDto, PropostionAvancement.class);
		List<EmployePropose> employeProposes=carriereDao.agentProposableListAptitude(listIDEmployer, propostionAvancement);
			
		for (EmployePropose employePropose : employeProposes) {
			EmployeProposeDto employeProposeDto = mapper.map(employePropose, EmployeProposeDto.class);
		employeProposeDtos.add(employeProposeDto);
		}
			
		return employeProposeDtos;	
	
	}
	@Override
	public void updateCarrierePromotion(EmployeProposeDto employeProposeDto,NomenclatureDto nomenclatureBytypePromotionDto){		
		EmployePropose employePropose=mapper.map(employeProposeDto, EmployePropose.class);
		Nomenclature nomenclatureBytypePromotion=mapper.map(nomenclatureBytypePromotionDto, Nomenclature.class);
		carriereDao.updateCarrierePromotion(employePropose, nomenclatureBytypePromotion);
	}
	
}



