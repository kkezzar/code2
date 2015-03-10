package dz.gov.mesrs.sii.grh.business.service.absence.impl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.dozer.Mapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteDto;
import dz.gov.mesrs.sii.commons.business.service.bpm.SituationService;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode.Filter;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode.FilterMode;
import dz.gov.mesrs.sii.commons.data.dao.grh.AbsenceDao;
import dz.gov.mesrs.sii.commons.data.model.grh.Absence;
import dz.gov.mesrs.sii.commons.data.model.grh.DossierEmploye;
import dz.gov.mesrs.sii.commons.data.util.UtilConstant;
import dz.gov.mesrs.sii.grh.business.model.dto.AbsenceDto;
import dz.gov.mesrs.sii.grh.business.model.dto.DossierEmployeDto;

public class TestAbsenceServiceImpl {

	private AbsenceServiceImpl tested;
	private AbsenceDto dto;
	private SituationEntiteDto situationDto;
	private Absence entity;
	private AbsenceDao mockDao;
	private SituationService mockSituationService;
	private Mapper mockMapper;
	private int etablissementId = 100;
	private SearchMode searchMode;
	private String searchKeyWords = "";

	@Before
	public void setUp() throws Exception {
		mockDao = mock(AbsenceDao.class);
		mockSituationService = mock(SituationService.class);
		mockMapper = mock(Mapper.class);

		tested = new AbsenceServiceImpl();
		tested.setSituationService(mockSituationService);
		tested.setAbsenceDao(mockDao);
		tested.setMapper(mockMapper);

		dto = new AbsenceDto();
		entity = new Absence();
		situationDto = new SituationEntiteDto();
		situationDto.setId(50);
		searchMode = new SearchMode();

	}

	@Test
	public void enregisterAbsenceNouveau() {
		when(
				mockSituationService.findBySituationEntiteByCodeSituation(UtilConstant.ENTITE_GRH_ABSENCE,
						UtilConstant.SITUTAION_CREEE_CODE)).thenReturn(situationDto);
		mockSaveAction();

		dto = tested.enregisterAbsence(dto);

		verify(mockSituationService).findBySituationEntiteByCodeSituation(UtilConstant.ENTITE_GRH_ABSENCE,
				UtilConstant.SITUTAION_CREEE_CODE);
		verifyMockAction();
		assertNotNull("Le dto en retour de la methode save ne doit pas etre  null", dto);
		assertTrue("La situation creee n'a pas été affecte a l'entite", situationDto.equals(dto.getSituationDto()));

	}

	@Test
	public void enregisterAbsenceMiseAJour() {
		dto.setId(100);
		when(
				mockSituationService.findBySituationEntiteByCodeSituation(UtilConstant.ENTITE_GRH_ABSENCE,
						UtilConstant.SITUTAION_ENREGISTREE_CODE)).thenReturn(situationDto);
		mockSaveAction();

		dto = tested.enregisterAbsence(dto);

		verifyMockAction();
		verify(mockSituationService).findBySituationEntiteByCodeSituation(UtilConstant.ENTITE_GRH_ABSENCE,
				UtilConstant.SITUTAION_ENREGISTREE_CODE);
		assertNotNull("Le dto en retour de la methode save ne doit pas etre  null", dto);
		assertTrue("La situation creee n'a pas été affecte a l'entite", situationDto.equals(dto.getSituationDto()));

	}

	@Test
	public void confirmerAbsence() {
		when(
				mockSituationService.findBySituationEntiteByCodeSituation(UtilConstant.ENTITE_GRH_ABSENCE,
						UtilConstant.SITUTAION_VALIDEE_CODE)).thenReturn(situationDto);
		mockSaveAction();

		tested.confirmerAbsence(dto);

		verifyMockAction();
		verify(mockSituationService).findBySituationEntiteByCodeSituation(UtilConstant.ENTITE_GRH_ABSENCE,
				UtilConstant.SITUTAION_VALIDEE_CODE);
		assertNotNull("Le dto n'aurait pas du etre mis a null par l'implementation", dto);
		assertTrue("La situation creee n'a pas été affecte a l'entite", situationDto.equals(dto.getSituationDto()));
	}

	@Test
	public void findAllAbsencesEnregistrees() {

		List<AbsenceDto> absenceDtos = new ArrayList<>();
		List<Absence> absenceEntities = new ArrayList<>();
		Absence absence = new Absence();
		absenceEntities.add(absence);

		when(
				mockSituationService.findBySituationEntiteByCodeSituation(UtilConstant.ENTITE_GRH_ABSENCE,
						UtilConstant.SITUTAION_VALIDEE_CODE)).thenReturn(situationDto);
		when(mockDao.findAllByKeyWord(searchKeyWords, searchMode)).thenReturn(absenceEntities);
		when(mockMapper.map(absence, AbsenceDto.class)).thenReturn(dto);

		absenceDtos = tested.findAllAbsencesEnregistrees(etablissementId, searchMode, searchKeyWords);

		verify(mockSituationService).findBySituationEntiteByCodeSituation(UtilConstant.ENTITE_GRH_ABSENCE,
				UtilConstant.SITUTAION_VALIDEE_CODE);
		assertTrue("Auncun filtre n'a ete applique au SeachMode", !searchMode.getFilters().isEmpty());
		assertTrue("Le filtre etablissement n'a pas ete applique",
				searchMode.getFilters().contains(createEtablissementFilter()));
		assertTrue("Le filtre sur la situation non valide n'a pas ete applique",
				searchMode.getFilters().contains(createSituationNonValideFilter()));
		assertNotNull("Le service aurait du  rendre une liste d'absence", absenceDtos);
		assertTrue("La liste de dto ne devrait pas être vide ", !absenceDtos.isEmpty());

	}

	@Test
	public void countAllAbsencesEnregistrees() {
		when(
				mockSituationService.findBySituationEntiteByCodeSituation(UtilConstant.ENTITE_GRH_ABSENCE,
						UtilConstant.SITUTAION_VALIDEE_CODE)).thenReturn(situationDto);

		searchMode.addFilter(createEtablissementFilter());
		searchMode.addFilter(createSituationNonValideFilter());
		when(mockDao.countAllByKeyWord(eq(searchKeyWords), eq(searchMode))).thenReturn(50);

		int count = tested.countAllAbsencesEnregistrees(etablissementId, searchKeyWords);

		verify(mockSituationService).findBySituationEntiteByCodeSituation(UtilConstant.ENTITE_GRH_ABSENCE,
				UtilConstant.SITUTAION_VALIDEE_CODE);
		assertEquals("Le compte des absences enregistrees n'est pas celui attendu", 50, count);
	}

	@Test
	public void isEmployeAbsentPositif() {
		Date dateDebut = new Date();
		Date dateFin = new Date();
		when(
				mockSituationService.findBySituationEntiteByCodeSituation(UtilConstant.ENTITE_GRH_ABSENCE,
						UtilConstant.SITUTAION_VALIDEE_CODE)).thenReturn(situationDto);

//		searchMode.addFilter(createEtablissementFilter());
		searchMode.addFilter(new Filter("employe.id", 1, FilterMode.EQUALS));
		searchMode.addFilter(createSituationValideeFilter());
		
		searchMode.addFilter(new Filter("dateDebut", dateDebut, dateFin, FilterMode.BETWEEN));
		//FIXME le test sur legalite des filtres ne marche pas , certainement l'ordre la list
		when(mockDao.countAllByKeyWord(anyString(), any(SearchMode.class))).thenReturn(1);

		Boolean result = tested.isEmployeAbsent(1L, dateDebut, dateFin);
		assertNotNull("Le resultat n'aurait pas du etre null", result);
		assertEquals("Le résultat aurait du être positif", Boolean.TRUE, result);

	}

	@Test
	public void isEmployeAbsentNegatif() {
		Date dateDebut = new Date();
		Date dateFin = new Date();
		when(
				mockSituationService.findBySituationEntiteByCodeSituation(UtilConstant.ENTITE_GRH_ABSENCE,
						UtilConstant.SITUTAION_VALIDEE_CODE)).thenReturn(situationDto);

//		searchMode.addFilter(createEtablissementFilter());
		searchMode.addFilter(new Filter("employe.id", 1, FilterMode.EQUALS));
		searchMode.addFilter(createSituationValideeFilter());
		
		searchMode.addFilter(new Filter("dateDebut", dateDebut, dateFin, FilterMode.BETWEEN));
		//FIXME le test sur legalite des filtres ne marche pas , certainement l'ordre la list
		when(mockDao.countAllByKeyWord(anyString(), any(SearchMode.class))).thenReturn(0);

		Boolean result = tested.isEmployeAbsent(1L, dateDebut, dateFin);
		assertNotNull("Le resultat n'aurait pas du etre null", result);
		assertEquals("Le résultat aurait du être positif", Boolean.FALSE, result);
	}
	
	@Test
	public void findAllAbsencesEmploye(){
		AbsenceDto example = new AbsenceDto();
		DossierEmployeDto dossierEmployeDto = new DossierEmployeDto(10L);
		example.setDossierEmployeDto(dossierEmployeDto);
		List<Absence> entitiesResult = new ArrayList<Absence>();
		entitiesResult.add(entity);
//		entity.setEmploye(new DossierEmploye());
		
		when(mockMapper.map(example, Absence.class)).thenReturn(entity);
		when(mockDao.findAllByExample(entity)).thenReturn(entitiesResult);
		when(mockMapper.map(entity, AbsenceDto.class)).thenReturn(dto);
		
		List<AbsenceDto> results = tested.findAllAbsencesEmploye(10L);
		
		verify(mockMapper).map(example, Absence.class);
		verify(mockDao).findAllByExample(entity);
		verify(mockMapper).map(entity, AbsenceDto.class);
		assertNotNull("Le resultat n'aurait pas du etre null", entitiesResult);
		assertTrue("Le resultat n'est pas correct", results.size() == 1 && results.get(0).equals(dto));
	}

	private void verifyMockAction() {
		verify(mockMapper).map(dto, Absence.class);
		verify(mockDao).save(entity);
		verify(mockMapper).map(entity, AbsenceDto.class);

	}

	private void mockSaveAction() {
		when(mockMapper.map(dto, Absence.class)).thenReturn(entity);
		when(mockDao.save(entity)).thenReturn(entity);
		when(mockMapper.map(entity, AbsenceDto.class)).thenReturn(dto);

	}

	private Filter createEtablissementFilter() {
		return new Filter("etablissement.id", etablissementId, FilterMode.EQUALS);
	}

	private Filter createSituationValideeFilter() {
		return new Filter("situation.id", 50, FilterMode.EQUALS);
	}

	private Filter createSituationNonValideFilter() {
		return new Filter("situation.id", 50, FilterMode.NOT);
	}

}
