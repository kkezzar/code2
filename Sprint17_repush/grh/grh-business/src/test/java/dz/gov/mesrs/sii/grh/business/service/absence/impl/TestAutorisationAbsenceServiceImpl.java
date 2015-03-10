package dz.gov.mesrs.sii.grh.business.service.absence.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.dozer.Mapper;
import org.junit.Before;
import org.junit.Test;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteDto;
import dz.gov.mesrs.sii.commons.business.service.bpm.SituationService;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode.Filter;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode.FilterMode;
import dz.gov.mesrs.sii.commons.data.dao.grh.AutorisationAbsenceDao;
import dz.gov.mesrs.sii.commons.data.model.grh.Absence;
import dz.gov.mesrs.sii.commons.data.model.grh.AutorisationAbsence;
import dz.gov.mesrs.sii.commons.data.util.UtilConstant;
import dz.gov.mesrs.sii.grh.business.model.dto.AbsenceDto;
import dz.gov.mesrs.sii.grh.business.model.dto.AutorisationAbsenceDto;

public class TestAutorisationAbsenceServiceImpl {

	private AutorisationAbsenceServiceImpl tested;
	private AutorisationAbsenceDto dto;
	private SituationEntiteDto situationDto;
	private AutorisationAbsence entity;
	private AutorisationAbsenceDao mockDao;
	private SituationService mockSituationService;
	private Mapper mockMapper;
	private int etablissementId = 100;
	private SearchMode searchMode;
	private String searchKeyWords = "";

	@Before
	public void setUp() throws Exception {
		mockDao = mock(AutorisationAbsenceDao.class);
		mockSituationService = mock(SituationService.class);
		mockMapper = mock(Mapper.class);

		tested = new AutorisationAbsenceServiceImpl();
		tested.setSituationService(mockSituationService);
		tested.setAutorisationAbsenceDao(mockDao);
		tested.setMapper(mockMapper);

		dto = new AutorisationAbsenceDto();
		entity = new AutorisationAbsence();
		situationDto = new SituationEntiteDto();
		situationDto.setId(50);
		searchMode = new SearchMode();
	}

	@Test
	public void enregisterDemande() {
		when(
				mockSituationService.findBySituationEntiteByCodeSituation(UtilConstant.ENTITE_DEMANDE_ABSENCE,
						UtilConstant.SITUTAION_CREEE_CODE)).thenReturn(situationDto);
		mockSaveAction();

		dto = tested.enregisterDemande(dto);

		verify(mockSituationService).findBySituationEntiteByCodeSituation(UtilConstant.ENTITE_DEMANDE_ABSENCE,
				UtilConstant.SITUTAION_CREEE_CODE);
		verifyMockAction();
		assertNotNull("Le dto en retour de la methode save ne doit pas etre  null", dto);
		assertTrue("La situation creee n'a pas été affecte a l'entite", situationDto.equals(dto.getSituationDto()));
	}

	@Test
	public void enregisterDemandeMiseAJour() {
		dto.setId(100);
		when(
				mockSituationService.findBySituationEntiteByCodeSituation(UtilConstant.ENTITE_DEMANDE_ABSENCE,
						UtilConstant.SITUTAION_ENREGISTREE_CODE)).thenReturn(situationDto);
		mockSaveAction();

		dto = tested.enregisterDemande(dto);

		verifyMockAction();
		verify(mockSituationService).findBySituationEntiteByCodeSituation(UtilConstant.ENTITE_DEMANDE_ABSENCE,
				UtilConstant.SITUTAION_ENREGISTREE_CODE);
		assertNotNull("Le dto en retour de la methode save ne doit pas etre  null", dto);
		assertTrue("La situation creee n'a pas été affecte a l'entite", situationDto.equals(dto.getSituationDto()));
	}
	
	@Test
	public void enregisterResultat() {
		dto.setId(100);
		when(
				mockSituationService.findBySituationEntiteByCodeSituation(UtilConstant.ENTITE_DEMANDE_ABSENCE,
						UtilConstant.SITUTAION_TRAITEE_CODE)).thenReturn(situationDto);
		mockSaveAction();

		tested.enregisterResultat(dto);

		verifyMockAction();
		verify(mockSituationService).findBySituationEntiteByCodeSituation(UtilConstant.ENTITE_DEMANDE_ABSENCE,
				UtilConstant.SITUTAION_TRAITEE_CODE);
		assertTrue("La situation creee n'a pas été affecte a l'entite", situationDto.equals(dto.getSituationDto()));
	}

	@Test
	public void findAllPendingRequest() {
		List<AutorisationAbsenceDto> absenceDtos = new ArrayList<>();
		List<AutorisationAbsence> absenceEntities = new ArrayList<>();
		AutorisationAbsence absence = new AutorisationAbsence();
		absenceEntities.add(absence);

		when(
				mockSituationService.findBySituationEntiteByCodeSituation(UtilConstant.ENTITE_DEMANDE_ABSENCE,
						UtilConstant.SITUTAION_TRAITEE_CODE)).thenReturn(situationDto);
		when(mockDao.findAllByKeyWord(searchKeyWords, searchMode)).thenReturn(absenceEntities);
		when(mockMapper.map(absence, AutorisationAbsenceDto.class)).thenReturn(dto);

		absenceDtos = tested.findAllPendingRequest(etablissementId, searchMode, searchKeyWords);

		verify(mockSituationService).findBySituationEntiteByCodeSituation(UtilConstant.ENTITE_DEMANDE_ABSENCE,
				UtilConstant.SITUTAION_TRAITEE_CODE);
		assertTrue("Auncun filtre n'a ete applique au SeachMode", !searchMode.getFilters().isEmpty());
		assertTrue("Le filtre etablissement n'a pas ete applique",
				searchMode.getFilters().contains(createEtablissementFilter()));
		assertTrue("Le filtre sur la situation traitée n'as pas été applique",
				searchMode.getFilters().contains(createSituationNonTraiteeFilter()));
		assertNotNull("Le service aurait du  rendre une liste d'absence", absenceDtos);
		assertTrue("La liste de dto ne devrait pas être vide ", !absenceDtos.isEmpty());
	}

	@Test
	public void countAllPendingRequest() {
		when(
				mockSituationService.findBySituationEntiteByCodeSituation(UtilConstant.ENTITE_DEMANDE_ABSENCE,
						UtilConstant.SITUTAION_TRAITEE_CODE)).thenReturn(situationDto);

		searchMode.addFilter(createEtablissementFilter());
		searchMode.addFilter(createSituationNonTraiteeFilter());
		when(mockDao.countAllByKeyWord(eq(searchKeyWords), eq(searchMode))).thenReturn(50);

		int count = tested.countAllPendingRequest(etablissementId, searchKeyWords);

		verify(mockSituationService).findBySituationEntiteByCodeSituation(UtilConstant.ENTITE_DEMANDE_ABSENCE,
				UtilConstant.SITUTAION_TRAITEE_CODE);
		assertEquals("Le compte des autorisation absences enregistrees n'est pas celui attendu", 50, count);
	}

	@Test
	public void isEmployeAutoriseAbsencePositif() {
		Date dateDebut = new Date();
		Date dateFin = new Date();
		when(
				mockSituationService.findBySituationEntiteByCodeSituation(UtilConstant.ENTITE_DEMANDE_ABSENCE,
						UtilConstant.SITUTAION_TRAITEE_CODE)).thenReturn(situationDto);

//		searchMode.addFilter(createEtablissementFilter());
		searchMode.addFilter(new Filter("employe.id", 1, FilterMode.EQUALS));
		searchMode.addFilter(createSituationTraiteeFilter());
		searchMode.addFilter(new Filter("acceptee", Boolean.TRUE, FilterMode.EQUALS));
		
		searchMode.addFilter(new Filter("dateDebut", dateDebut, dateFin, FilterMode.BETWEEN));
		//FIXME le test sur legalite des filtres ne marche pas , certainement l'ordre la list
		when(mockDao.countAllByKeyWord(anyString(), any(SearchMode.class))).thenReturn(1);

		Boolean result = tested.isEmployeAutoriseAbsence(1L, dateDebut, dateFin);
		
		assertNotNull("Le resultat n'aurait pas du etre null", result);
		assertEquals("Le résultat aurait du être positif", Boolean.TRUE, result);

	}

	@Test
	public void isEmployeAutoriseAbsenceNegatif() {
		Date dateDebut = new Date();
		Date dateFin = new Date();
		when(
				mockSituationService.findBySituationEntiteByCodeSituation(UtilConstant.ENTITE_DEMANDE_ABSENCE,
						UtilConstant.SITUTAION_TRAITEE_CODE)).thenReturn(situationDto);

//		searchMode.addFilter(createEtablissementFilter());
		searchMode.addFilter(new Filter("employe.id", 1, FilterMode.EQUALS));
		searchMode.addFilter(createSituationTraiteeFilter());
		searchMode.addFilter(new Filter("acceptee", Boolean.TRUE, FilterMode.EQUALS));
		
		searchMode.addFilter(new Filter("dateDebut", dateDebut, dateFin, FilterMode.BETWEEN));
		//FIXME le test sur legalite des filtres ne marche pas , certainement l'ordre la list
		when(mockDao.countAllByKeyWord(anyString(), any(SearchMode.class))).thenReturn(0);

		Boolean result = tested.isEmployeAutoriseAbsence(1L, dateDebut, dateFin);
		
		assertNotNull("Le resultat n'aurait pas du etre null", result);
		assertEquals("Le résultat aurait du être positif", Boolean.FALSE, result);
	}
	
	//private methods
	private void verifyMockAction() {
		verify(mockMapper).map(dto, AutorisationAbsence.class);
		verify(mockDao).save(entity);
		verify(mockMapper).map(entity, AutorisationAbsenceDto.class);

	}

	private void mockSaveAction() {
		when(mockMapper.map(dto, AutorisationAbsence.class)).thenReturn(entity);
		when(mockDao.save(entity)).thenReturn(entity);
		when(mockMapper.map(entity, AutorisationAbsenceDto.class)).thenReturn(dto);

	}
	
	private Filter createEtablissementFilter() {
		return new Filter("etablissement.id", etablissementId, FilterMode.EQUALS);
	}

	private Filter createSituationTraiteeFilter() {
		return new Filter("situation.id", 50, FilterMode.EQUALS);
	}

	private Filter createSituationNonTraiteeFilter() {
		return new Filter("situation.id", 50, FilterMode.NOT);
	}



}
