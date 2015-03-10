package dz.gov.mesrs.sii.gfc.business.service.impl;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.dozer.Mapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteDto;
import dz.gov.mesrs.sii.commons.business.service.bpm.SituationService;
import dz.gov.mesrs.sii.commons.business.util.UtilConstants;
import dz.gov.mesrs.sii.commons.data.dao.gfc.EvenementMarcheDao;
import dz.gov.mesrs.sii.commons.data.model.gfc.EvenementMarche;
import dz.gov.mesrs.sii.gfc.business.model.dto.EvenementMarcheDto;

@RunWith(MockitoJUnitRunner.class)
public class EvenementMarcheServiceImplTest {
	@Mock
	private EvenementMarcheDao evenementMarcheDao;

	@Mock
	private Mapper mapper;

	@Mock
	private SituationService situationService;

	@Mock
	private SituationEntiteDto situationEntiteDto;
	@Mock
	private EvenementMarcheDto dto;
	@Mock
	private EvenementMarche entity;

	@InjectMocks
	private EvenementMarcheServiceImpl evenementMarcheService = new EvenementMarcheServiceImpl();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetDaoShouldNotReturnNull() throws Exception {
		assertNotNull(evenementMarcheService.getDao());
	}

	@Test
	public void testEnregistrerEvenementMarche() throws Exception {
		when(mapper.map(dto, EvenementMarche.class)).thenReturn(entity);
		when(evenementMarcheDao.save(entity)).thenReturn(entity);
		when(mapper.map(entity, EvenementMarcheDto.class)).thenReturn(dto);

		EvenementMarcheDto result = evenementMarcheService.enregistrerEvenementMarche(dto);
		verify(mapper).map(dto, EvenementMarche.class);
		verify(evenementMarcheDao).save(entity);
		verify(mapper).map(entity, EvenementMarcheDto.class);
		assertNotNull("Le resultat d'enregistrement d'un nouveau marche ne doit pas etre null.", result);
	}

	@Test
	public void testSupprimerEvenementMarche() throws Exception {
		when(mapper.map(dto, EvenementMarche.class)).thenReturn(entity);
		when(evenementMarcheDao.findById(entity.getId())).thenReturn(entity);

		evenementMarcheService.supprimerEvenementMarche(dto);
		verify(mapper).map(dto, EvenementMarche.class);
		verify(evenementMarcheDao).findById(entity.getId());
		verify(evenementMarcheDao).remove(entity);
	}

	@Test
	public void testValiderEnregistrementEvenementMarche() throws Exception {
		when(
				situationService.findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_GFC_EVENT_MARCHE,
						UtilConstants.SITUATION_VALIDEE_CODE)).thenReturn(situationEntiteDto);
		when(mapper.map(dto, EvenementMarche.class)).thenReturn(entity);
		when(evenementMarcheDao.save(entity)).thenReturn(entity);
		when(mapper.map(entity, EvenementMarcheDto.class)).thenReturn(dto);

		dto = evenementMarcheService.validerEnregistrementEvenementMarche(dto);

		verify(situationService).findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_GFC_EVENT_MARCHE,
				UtilConstants.SITUATION_VALIDEE_CODE);
		verify(evenementMarcheDao).save(entity);
		verify(situationService).saveSituationOccurrence(dto.getSituation(), dto.getId());
		Assert.assertNotEquals(
				"L'ancien situation de l'evenement de marche et la nouvelle doivent etre differents apres la validation.",
				situationEntiteDto, dto.getSituation());
	}

}
