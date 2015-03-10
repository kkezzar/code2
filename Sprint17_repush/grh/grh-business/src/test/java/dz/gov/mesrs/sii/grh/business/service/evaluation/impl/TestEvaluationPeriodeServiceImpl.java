package dz.gov.mesrs.sii.grh.business.service.evaluation.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.dozer.Mapper;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode.Filter;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode.FilterMode;
import dz.gov.mesrs.sii.commons.data.dao.bpm.SituationEntiteOccurrenceDao;
import dz.gov.mesrs.sii.commons.data.dao.grh.EvaluationPeriodeDao;
import dz.gov.mesrs.sii.commons.data.model.bpm.SituationEntite;
import dz.gov.mesrs.sii.commons.data.model.bpm.SituationEntiteOccurrence;
import dz.gov.mesrs.sii.commons.data.model.grh.EvaluationPeriode;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefEtablissement;
import dz.gov.mesrs.sii.grh.business.model.dto.EvaluationPeriodeDto;

public class TestEvaluationPeriodeServiceImpl {

	private EvaluationPeriodeServiceImpl tested;
	private EvaluationPeriodeDao mockDao;
	private Mapper mockMapper;
	private EvaluationPeriodeDto dto;
	private List<EvaluationPeriodeDto> dtos;
	private EvaluationPeriode entity;
	private List<EvaluationPeriode> entities;
	private SituationEntiteOccurrenceDao mockSituationEntiteOccurrenceDao;
	private SituationEntite situationCreee;
	private SituationEntite situationEnregistree;
	private SituationEntite situationCloturee;
	private SearchMode searchMode;
	private int etablissementId = 1;

	@Before
	public void setUp() {
		tested = new EvaluationPeriodeServiceImpl();
		situationCloturee = new SituationEntite();
		situationCloturee.setId(1);
		situationCreee = new SituationEntite();
		situationCloturee.setId(2);
		situationEnregistree = new SituationEntite();
		situationEnregistree.setId(3);
		mockDao = mock(EvaluationPeriodeDao.class);
		mockSituationEntiteOccurrenceDao = mock(SituationEntiteOccurrenceDao.class);
		mockMapper = mock(Mapper.class);

		tested.setEvaluationPeriodeDao(mockDao);
		tested.setSituationEntiteOccurrenceDao(mockSituationEntiteOccurrenceDao);
		tested.setMapper(mockMapper);
		tested.setSituationCloturee(situationCloturee);
		tested.setSituationCreee(situationCreee);
		tested.setSituationEnregistree(situationEnregistree);

		dto = new EvaluationPeriodeDto();
		dtos = new ArrayList<>();
		dtos.add(dto);

		entity = new EvaluationPeriode();
		entity.setId(100L);
		entities = new ArrayList<>();
		entities.add(entity);

		searchMode = new SearchMode();

	}

	@Test
	public void saveEvaluationPeriodeCree() {
		mockMapSaveAndReturnDto();
		
		dto = tested.saveEvaluationPeriode(dto);

		verifyMapSaveAndReturnDto();
		verifySituationOccurencePersisted();
		assertNotNull("Le resultat de la methode n'aurait pas du etre null", dto);
		assertEquals("La situtation du dto aurait du etre a cree", entity.getSituation(), situationCreee);

	}

	@Test
	public void saveEvaluationPeriodeEnregistree() {
		mockMapSaveAndReturnDto();
		dto.setId(1L);

		dto = tested.saveEvaluationPeriode(dto);

		verifyMapSaveAndReturnDto();
		verifySituationOccurencePersisted();
		assertNotNull("Le resultat de la methode n'aurait pas du etre null", dto);
		assertEquals("La situtation du dto aurait du etre a enregristree", entity.getSituation(), situationEnregistree);
	}

	@Test
	public void cloturerEvaluationPeriode() {
		mockMapSaveAndReturnDto();

		dto = tested.cloturerEvaluationPeriode(dto);

		verifyMapSaveAndReturnDto();
		verifySituationOccurencePersisted();
		assertNotNull("Le resultat de la methode n'aurait pas du etre null", dto);
		assertEquals("La situtation du dto aurait du etre a cloturee", entity.getSituation(), situationCloturee);
	}

	@Test
	public void findAllEvalutionPeriodeNonCloture() {
		Date dateDebutPeriode = new Date();
		Date dateFinPeriode = new Date();
		RefEtablissement etablissement = new RefEtablissement();
		etablissement.setId(10);
		entity.setEtablissement(etablissement);
		when(mockDao.findAllByExample(any(EvaluationPeriode.class), any(SearchMode.class))).thenReturn(entities);
		when(mockMapper.map(any(EvaluationPeriode.class), eq(EvaluationPeriodeDto.class))).thenReturn(dto);

		List<EvaluationPeriodeDto> results = tested.findAllEvalutionPeriodeNonCloture(dateDebutPeriode, dateFinPeriode,
				etablissementId, searchMode);

		verify(mockDao).findAllByExample(any(EvaluationPeriode.class), any(SearchMode.class));
		verify(mockMapper).map(any(EvaluationPeriode.class), eq(EvaluationPeriodeDto.class));
		assertNotNull("La liste de retour n'aurait pas du etre null", results);
		assertTrue("La liste de retour ne contient pas les bons resultats", results.size() == 1
				&& results.get(0).equals(dto));
		assertNotNull("Le searchMode n'aurait pas du etre sette a null", searchMode);
		SearchMode expectedSearchMode = new SearchMode();
		expectedSearchMode.addFilter(createNonClotureeFilter());
		expectedSearchMode.addFilter(createAfterDateFilter(dateDebutPeriode));
		expectedSearchMode.addFilter(createBeforeDateFilter(dateFinPeriode));
		
		assertEquals("Les filtres du searchMode sont incorrectes", expectedSearchMode, searchMode);

	}

	// private
	private void mockMapSaveAndReturnDto() {

		when(mockMapper.map(dto, EvaluationPeriode.class)).thenReturn(entity);
		when(mockDao.save(entity)).thenReturn(entity);
		when(mockMapper.map(entity, EvaluationPeriodeDto.class)).thenReturn(dto);
	}

	private void verifyMapSaveAndReturnDto() {
		verify(mockMapper).map(dto, EvaluationPeriode.class);
		verify(mockDao).save(entity);
		verify(mockMapper).map(entity, EvaluationPeriodeDto.class);
	}

	private void verifySituationOccurencePersisted() {
		verify(mockSituationEntiteOccurrenceDao).persist(any(SituationEntiteOccurrence.class));
	}

	private Filter createAfterDateFilter(Date date) {
		return new Filter("dateDebutPeriode", date ,FilterMode.GREATER_OR_EQUAL);
	}
	
	private Filter createBeforeDateFilter(Date date) {
		return new Filter("dateFinPeriode", date ,FilterMode.LESS_OR_EQUAL);
	}

	private Filter createNonClotureeFilter() {
		return new Filter("situation.id", situationCloturee.getId(), FilterMode.NOT);

	}

}
