package dz.gov.mesrs.sii.gfc.business.service.impl;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.dozer.Mapper;

import dz.gov.mesrs.sii.commons.business.service.impl.CommonServiceImpl;
import dz.gov.mesrs.sii.commons.data.dao.CommonDao;
import dz.gov.mesrs.sii.commons.data.model.gfc.DetailsProjetBudgetEtablissement;
import dz.gov.mesrs.sii.gfc.business.model.dto.DetailsProjetBudgetEtablissementDto;
import dz.gov.mesrs.sii.commons.data.dao.gfc.DetailsProjetBudgetEtablissementDao;
import dz.gov.mesrs.sii.gfc.business.service.DetailsProjetBudgetEtablissementService;


/**
 * Service Implementation for domain model class DetailsProjetBudget.
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
@Service("detailsProjetBudgetEtablissementService")
@Transactional
public class DetailsProjetBudgetEtablissementServiceImpl extends CommonServiceImpl<DetailsProjetBudgetEtablissement,DetailsProjetBudgetEtablissementDto, Integer> implements DetailsProjetBudgetEtablissementService {

	@Autowired
	@Qualifier ("detailsProjetBudgetEtablissementDao")
	private DetailsProjetBudgetEtablissementDao detailsProjetBudgetEtablissementDao;

    @Autowired
	private Mapper mapper;
	
	
	public DetailsProjetBudgetEtablissementServiceImpl(){

	}
	
	@Override
	protected CommonDao<DetailsProjetBudgetEtablissement, Integer> getDao() {
		return detailsProjetBudgetEtablissementDao;
	}
}