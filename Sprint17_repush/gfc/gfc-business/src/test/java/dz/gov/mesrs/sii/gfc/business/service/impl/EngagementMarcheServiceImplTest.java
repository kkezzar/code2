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
import dz.gov.mesrs.sii.commons.data.dao.gfc.EngagementMarcheDao;
import dz.gov.mesrs.sii.commons.data.model.gfc.EngagementMarche;
import dz.gov.mesrs.sii.gfc.business.model.dto.EngagementMarcheDto;

@RunWith(MockitoJUnitRunner.class)
public class EngagementMarcheServiceImplTest {
	@Mock
	private EngagementMarcheDao engagementMarcheDao;

	@Mock
	private Mapper mapper;

	@Mock
	private SituationService situationService;

	@Mock
	private SituationEntiteDto situationEntiteDto;
	@Mock
	private EngagementMarcheDto dto;
	@Mock
	private EngagementMarche entity;

	@InjectMocks
	private EngagementMarcheServiceImpl engagementMarcheService = new EngagementMarcheServiceImpl();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetDao() throws Exception {
		assertNotNull(engagementMarcheService.getDao());
	}

	@Test
	public void testEnregistrerDemandeEngagement() throws Exception {
		when(mapper.map(dto, EngagementMarche.class)).thenReturn(entity);
		when(engagementMarcheDao.save(entity)).thenReturn(entity);
		when(mapper.map(entity, EngagementMarcheDto.class)).thenReturn(dto);

		EngagementMarcheDto result = engagementMarcheService.enregistrerDemandeEngagement(dto);
		verify(mapper).map(dto, EngagementMarche.class);
		verify(engagementMarcheDao).save(entity);
		verify(mapper).map(entity, EngagementMarcheDto.class);
		assertNotNull("Le resultat d'enregistrement d'un nouvelle demande d'engagement ne doit pas etre null.", result);
	}

	@Test
	public void testSupprimerDemandeEngagement() throws Exception {
		when(mapper.map(dto, EngagementMarche.class)).thenReturn(entity);
		when(engagementMarcheDao.findById(entity.getId())).thenReturn(entity);

		engagementMarcheService.supprimerDemandeEngagement(dto);
		verify(mapper).map(dto, EngagementMarche.class);
		verify(engagementMarcheDao).findById(entity.getId());
		verify(engagementMarcheDao).remove(entity);
	}

	@Test
	public void testValiderDemandeEngagement() throws Exception {
		when(
				situationService.findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_GFC_ENGAGEMENT_MARCHE,
						UtilConstants.SITUATION_VALIDEE_CODE)).thenReturn(situationEntiteDto);
		when(mapper.map(dto, EngagementMarche.class)).thenReturn(entity);
		when(engagementMarcheDao.save(entity)).thenReturn(entity);
		when(mapper.map(entity, EngagementMarcheDto.class)).thenReturn(dto);

		dto = engagementMarcheService.validerDemandeEngagement(dto);

		verify(situationService).findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_GFC_ENGAGEMENT_MARCHE,
				UtilConstants.SITUATION_VALIDEE_CODE);
		verify(engagementMarcheDao).save(entity);
		verify(situationService).saveSituationOccurrence(dto.getSituation(), dto.getId());
		Assert.assertNotEquals(
				"L'ancien situation de la demande d'engagement de marche et la nouvelle doivent etre differents apres la validation.",
				situationEntiteDto, dto.getSituation());
	}

	@Test
	public void testEnvoyerDemandeEngamgent() throws Exception {
		when(
				situationService.findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_GFC_ENGAGEMENT_MARCHE,
						UtilConstants.SITUATION_ENVOYE_CODE)).thenReturn(situationEntiteDto);
		when(mapper.map(dto, EngagementMarche.class)).thenReturn(entity);
		when(engagementMarcheDao.save(entity)).thenReturn(entity);
		when(mapper.map(entity, EngagementMarcheDto.class)).thenReturn(dto);

		dto = engagementMarcheService.envoyerDemandeEngamgent(dto);

		verify(situationService).findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_GFC_ENGAGEMENT_MARCHE,
				UtilConstants.SITUATION_ENVOYE_CODE);
		verify(engagementMarcheDao).save(entity);
		verify(situationService).saveSituationOccurrence(dto.getSituation(), dto.getId());
		Assert.assertNotEquals(
				"L'ancien situation de  la demande d'engagement de marche et la nouvelle doivent etre differents apres l'envoi.",
				situationEntiteDto, dto.getSituation());
	}
}