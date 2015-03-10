package dz.gov.mesrs.sii.grh.business.service.dossieremploye.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.dozer.Mapper;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;
import dz.gov.mesrs.sii.commons.business.util.UtilConstants;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode.Filter;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode.FilterMode;
import dz.gov.mesrs.sii.commons.data.dao.bpm.SituationEntiteDao;
import dz.gov.mesrs.sii.commons.data.dao.bpm.SituationEntiteOccurrenceDao;
import dz.gov.mesrs.sii.commons.data.dao.grh.CarriereDao;
import dz.gov.mesrs.sii.commons.data.dao.grh.DossierEmployeDao;
import dz.gov.mesrs.sii.commons.data.dao.referentiel.NomenclatureDao;
import dz.gov.mesrs.sii.commons.data.model.bpm.SituationEntite;
import dz.gov.mesrs.sii.commons.data.model.bpm.SituationEntiteOccurrence;
import dz.gov.mesrs.sii.commons.data.model.grh.Carriere;
import dz.gov.mesrs.sii.commons.data.model.grh.DossierEmploye;
import dz.gov.mesrs.sii.commons.data.model.referentiel.Nomenclature;
import dz.gov.mesrs.sii.commons.data.util.UtilConstant;
import dz.gov.mesrs.sii.grh.business.model.dto.CarriereDto;
import dz.gov.mesrs.sii.grh.business.model.dto.DossierEmployeDto;

public class TestDossierEmployeServiceImpl {
	
	private DossierEmployeServiceImpl tested;
	private DossierEmployeDao mockDao;
	private CarriereDao mockCarriereDao;
	private SituationEntiteDao mockSituationEntiteDao;
	private SituationEntiteOccurrenceDao mockSituationEntiteOccurrenceDao;
	private NomenclatureDao mockNomenclatureDao;
	private Mapper mockMapper;
	
	private SearchMode searchMode;
	private DossierEmploye entity;
	private List<DossierEmploye> entities;
	private List<DossierEmployeDto> dtos;
	private DossierEmployeDto dto;
	private Nomenclature nomenclature;
	private Carriere carriere;
	private CarriereDto carriereDto;
	private SituationEntite situation;
	
	
	
	@Before
	public void init(){
		tested = new DossierEmployeServiceImpl();
		mockDao = mock(DossierEmployeDao.class);
		mockCarriereDao = mock(CarriereDao.class);
		mockSituationEntiteOccurrenceDao = mock(SituationEntiteOccurrenceDao.class);
		mockSituationEntiteDao = mock(SituationEntiteDao.class);
		mockNomenclatureDao = mock(NomenclatureDao.class);
		mockMapper = mock(Mapper.class);
		
		
		tested.setDossierEmployeDao(mockDao);
		tested.setCarriereDao(mockCarriereDao);
		tested.setSituationEntiteDao(mockSituationEntiteDao);
		tested.setSituationEntiteOccurrenceDao(mockSituationEntiteOccurrenceDao);;
		tested.setNomenclatureDao(mockNomenclatureDao);
		tested.setMapper(mockMapper);
		
		searchMode = new SearchMode();
		entity = new DossierEmploye(10L);
		dto = new DossierEmployeDto(10L);
		nomenclature = new Nomenclature();
		nomenclature.setId(20);
		entities = new ArrayList<>();
		entities.add(entity);
		dtos = new ArrayList<DossierEmployeDto>();
		dtos.add(dto);
		carriere = new Carriere();
		carriereDto = new CarriereDto();
		situation= new SituationEntite();
		
	}
	
	@Test
	public void findListeAptitudePromotionID(){
		List<Object> params = new ArrayList<>();
		List<Integer> ncList = new ArrayList<>();
		List<Long> daoResult= new ArrayList<>();
		
		when(mockDao.findListeAptitudePromotionID(params, ncList)).thenReturn(daoResult);
		
		List<Long> result = tested.findListeAptitudePromotionID(params, ncList);
		
		verify(mockDao).findListeAptitudePromotionID(params, ncList);
		assertEquals("Le resultat n'est pas le bon",daoResult, result);
	}
	
	@Test
	public void findListePromotionParModaliteID(){
		List<Object> params = new ArrayList<>();
		List<Integer> ncList = new ArrayList<>();
		List<Integer> ncList1 = new ArrayList<>();
		List<Integer> ncList2 = new ArrayList<>();
		List<Long> daoResult= new ArrayList<>();
		
		when(mockDao.findListePromotionParModaliteID(params, ncList ,ncList1 ,ncList2 )).thenReturn(daoResult);
		
		List<Long> result = tested.findListePromotionParModaliteID(params, ncList ,ncList1 ,ncList2 );
		
		verify(mockDao).findListePromotionParModaliteID(params, ncList ,ncList1 ,ncList2 );
		assertEquals("Le resultat n'est pas le bon",daoResult, result);
		
	}
	
	@Test
	public void findListePromotionParModalConfID(){
		List<Object> params = new ArrayList<>();
		List<Integer> ncList = new ArrayList<>();
		List<Integer> ncList1 = new ArrayList<>();
		List<Integer> ncList2 = new ArrayList<>();
		List<Long> daoResult= new ArrayList<>();
		
		when(mockDao.findListePromotionParModalConfID(params, ncList ,ncList1 ,ncList2 )).thenReturn(daoResult);
		
		List<Long> result = tested.findListePromotionParModalConfID(params, ncList ,ncList1 ,ncList2 );
		
		verify(mockDao).findListePromotionParModalConfID(params, ncList ,ncList1 ,ncList2 );
		assertEquals("Le resultat n'est pas le bon",daoResult, result);
	}
	
	@Test
	public void findAllActif(){
		when(mockNomenclatureDao.findByCode(UtilConstant.NC_GRH_POSITION_EMPLOYE_VALUE,
				UtilConstant.NC_GRH_POSITION_EMPLOYE_FIN_ACTIVITE_CODE)).thenReturn(nomenclature);
		when(mockMapper.map(dto, DossierEmploye.class)).thenReturn(entity);
		when(mockDao.findAllByExample(entity, searchMode)).thenReturn(entities);
		when(mockMapper.map(entity, DossierEmployeDto.class)).thenReturn(dto);
		
		
		List<DossierEmployeDto> results = tested.findAllActif(dto, searchMode);
		
		verify(mockNomenclatureDao).findByCode(UtilConstant.NC_GRH_POSITION_EMPLOYE_VALUE,
				UtilConstant.NC_GRH_POSITION_EMPLOYE_FIN_ACTIVITE_CODE);
		verify(mockMapper).map(dto, DossierEmploye.class);
		verify(mockDao).findAllByExample(entity, searchMode);
		verify(mockMapper).map(entity, DossierEmployeDto.class);

		assertEquals("Le nombre de filtres du SearchMode n'est pas correcte",1, searchMode.getFilters().size());
		assertEquals("Le filtre du SearchMode n'est pas correcte",createActifFilter(nomenclature), searchMode.getFilters().get(0));
		assertEquals("Le retour de la fonction n'est pas correct",dtos ,results);
	}
	
	@Test
	public void countAllActif(){
		when(mockNomenclatureDao.findByCode(UtilConstant.NC_GRH_POSITION_EMPLOYE_VALUE,
				UtilConstant.NC_GRH_POSITION_EMPLOYE_FIN_ACTIVITE_CODE)).thenReturn(nomenclature);
		when(mockMapper.map(dto, DossierEmploye.class)).thenReturn(entity);
		when(mockDao.countAllByExample(eq(entity), eq(searchMode))).thenReturn(10);
		searchMode.addFilter(createActifFilter(nomenclature));
		
		int count = tested.countAllActif(dto);
		
		verify(mockNomenclatureDao).findByCode(UtilConstant.NC_GRH_POSITION_EMPLOYE_VALUE,
				UtilConstant.NC_GRH_POSITION_EMPLOYE_FIN_ACTIVITE_CODE);
		verify(mockMapper).map(dto, DossierEmploye.class);
		verify(mockDao).countAllByExample(eq(entity), eq(searchMode));
		
		assertEquals("Le retour de la fonction n'est pas correct",10 ,count);
	}
	
	
	@Test
	public void saveTitularisation(){
		Date dateTitularisation = new Date();
		entity.setTitularise(true);
		entity.setDateTitularisation(dateTitularisation);
		entity.setCarriereCourante(carriere);
		entity.setSituationEntite(situation);
		carriere.setDossierEmploye(entity);
		
		
		when(mockMapper.map(carriereDto, Carriere.class)).thenReturn(carriere);
		when(mockCarriereDao.save(carriere)).thenReturn(carriere);
		when(mockSituationEntiteDao.findByCodeSituationByCodeEntite(
				UtilConstants.ENTITE_DOSSIER_EMPLOYE_CODE, UtilConstants.SITUATION_TITULARISE_CODE)).thenReturn(situation);
		when(mockDao.save(entity)).thenReturn(entity);
		when(mockMapper.map(entity, DossierEmployeDto.class)).thenReturn(dto);
		
		DossierEmployeDto result  = tested.saveTitularisation(dateTitularisation, carriereDto);
		
		verify(mockMapper).map(carriereDto, Carriere.class);
		verify(mockCarriereDao).save(carriere);
		verify(mockSituationEntiteDao).findByCodeSituationByCodeEntite(
				UtilConstants.ENTITE_DOSSIER_EMPLOYE_CODE, UtilConstants.SITUATION_TITULARISE_CODE);
		verify(mockDao).save(entity);
		verify(mockMapper).map(entity, DossierEmployeDto.class);
		verify(mockSituationEntiteOccurrenceDao).persist(any(SituationEntiteOccurrence.class));
		assertEquals("Le retour de la methode n'est pas correct",dto, result);
		
	}
	
	@Test
	public void saveDossierEmployeByCarriere(){
		carriere.setDossierEmploye(entity);
		when(mockMapper.map(carriereDto, Carriere.class)).thenReturn(carriere);
		when(mockCarriereDao.save(carriere)).thenReturn(carriere);
		when(mockDao.save(entity)).thenReturn(entity);
		when(mockMapper.map(entity, DossierEmployeDto.class)).thenReturn(dto);
		
		DossierEmployeDto  result = tested.saveDossierEmployeByCarriere(carriereDto);
		
		verify(mockMapper).map(carriereDto, Carriere.class);
		verify(mockCarriereDao).save(carriere);
		verify(mockDao).save(entity);
		verify(mockMapper).map(entity, DossierEmployeDto.class);
		verify(mockSituationEntiteOccurrenceDao).persist(any(SituationEntiteOccurrence.class));
		assertEquals("Le retour de la methode n'est pas correct",dto, result);
		
	}
	
	//private
	private Filter createActifFilter(Nomenclature nc){
		return new Filter("currentPosition.nomenclatureByPosition.id", nc.getId(), FilterMode.NOT);
	}

}
