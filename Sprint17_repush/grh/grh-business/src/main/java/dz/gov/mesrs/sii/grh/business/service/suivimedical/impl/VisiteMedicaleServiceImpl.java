package dz.gov.mesrs.sii.grh.business.service.suivimedical.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.commons.business.service.impl.CommonServiceImpl;
import dz.gov.mesrs.sii.commons.data.dao.CommonDao;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode.Filter;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode.FilterMode;
import dz.gov.mesrs.sii.commons.data.dao.grh.VisiteMedicaleDao;
import dz.gov.mesrs.sii.commons.data.model.grh.VisiteMedicale;
import dz.gov.mesrs.sii.grh.business.model.dto.DossierEmployeDto;
import dz.gov.mesrs.sii.grh.business.model.dto.VaccinDto;
import dz.gov.mesrs.sii.grh.business.model.dto.VisiteMedicaleDto;
import dz.gov.mesrs.sii.grh.business.service.suivimedical.VisiteMedicaleService;


@Service("visiteMedicaleService")
public class VisiteMedicaleServiceImpl extends CommonServiceImpl<VisiteMedicale , VisiteMedicaleDto,Long> implements VisiteMedicaleService  {
	
	private VisiteMedicaleDao	visiteMedicaleDao;

	@Override
	protected CommonDao<VisiteMedicale, Long> getDao() {
		return visiteMedicaleDao;
	}

	public VisiteMedicaleDao getVisiteMedicaleDao() {
		return visiteMedicaleDao;
	}
	
    @Autowired
	public void setVisiteMedicaleDao(VisiteMedicaleDao visiteMedicaleDao) {
		this.visiteMedicaleDao = visiteMedicaleDao;
	}

	@Override
	public List<VisiteMedicaleDto> findAllRdvVisiteMedicales(
			int etablissmentId,  String searchKeyWords) {
		SearchMode searchMode = new SearchMode();
		applyFilterEtablissment(searchMode, etablissmentId);
		applyFilterRdv(searchMode);
		return super.findAllByKeyWord(searchKeyWords, searchMode);
	}

	

	@Override
	public List<VisiteMedicaleDto> findAllVisiteMedicalesAcceptees(
			int etablissmentId, String searchKeyWords) {
		SearchMode searchMode = new SearchMode();
		applyFilterEtablissment(searchMode, etablissmentId);
		applyFilterRdvAccepte(searchMode);
		applyFilterNotFinVisite(searchMode);
		return super.findAllByKeyWord(searchKeyWords, searchMode);
		
	}
	
	
	@Override
	public List<VisiteMedicaleDto> findAllVisiteAvecExamenNonEncoreRenseignes(
			int etablissmentId, String searchKeyWords) {
		SearchMode searchMode = new SearchMode();
		applyFilterEtablissment(searchMode, etablissmentId);
		applyFilterAvecExamen(searchMode);
		applyFilterNotFinExamen(searchMode);
		return super.findAllByKeyWord(searchKeyWords, searchMode);
	}
	
	
	@Override
	public List<VisiteMedicaleDto> findAllVisitesEmploye(Long employeId) {
		SearchMode searchMode = new SearchMode();
		VisiteMedicaleDto example = new VisiteMedicaleDto();
		DossierEmployeDto employe = new DossierEmployeDto(employeId);
		example.setDossierEmployeDto(employe);
		applyFilterFinVisite(searchMode);
		return super.findAllByExample(example ,searchMode);
	}
	
	
	private void applyFilterEtablissment(SearchMode searchMode, int etablissmentId) {
		searchMode.addFilter(new Filter("refEtablissement.id", etablissmentId, FilterMode.EQUALS));
	}
	
	private void applyFilterRdv(SearchMode searchMode) {
		searchMode.addFilter(new Filter("resultat", FilterMode.IS_NULL));
	}
	
	private void applyFilterRdvAccepte(SearchMode searchMode) {
		searchMode.addFilter(new Filter("resultat", Boolean.TRUE, FilterMode.EQUALS));
	}

	private void applyFilterNotFinVisite(SearchMode searchMode) {
		searchMode.addFilter(new Filter("finVisite", Boolean.TRUE, FilterMode.NOT));
	}

	private void applyFilterAvecExamen(SearchMode searchMode) {
		searchMode.addFilter(new Filter("avecExamen", Boolean.TRUE, FilterMode.EQUALS));
	}

	private void applyFilterNotFinExamen(SearchMode searchMode) {
		searchMode.addFilter(new Filter("finExamen", Boolean.TRUE, FilterMode.NOT));
	}

	private void applyFilterFinVisite(SearchMode searchMode) {
		searchMode.addFilter(new Filter("finVisite", Boolean.TRUE, FilterMode.EQUALS));
	}
	
	
	
	
}



