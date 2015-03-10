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
import dz.gov.mesrs.sii.commons.data.dao.gfc.DocumentRealisationMarcheDao;
import dz.gov.mesrs.sii.commons.data.model.gfc.DocumentRealisationMarche;
import dz.gov.mesrs.sii.gfc.business.model.dto.DocumentRealisationMarcheDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.MarcheDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEtablissementDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefStructureDto;

@RunWith(MockitoJUnitRunner.class)
public class DocumentRealisationMarcheServiceImplTest {
	@Mock
	private DocumentRealisationMarcheDao documentRealisationMarcheDao;

	@Mock
	private Mapper mapper;

	@Mock
	private SituationService situationService;

	@Mock
	private SituationEntiteDto situationEntiteDto;
	@Mock
	private DocumentRealisationMarcheDto dto;
	@Mock
	private DocumentRealisationMarche entity;
	@Mock
	private MarcheDto marcheEntiteDto;
	@Mock
	private SituationEntiteDto situationMarcheEntiteDto;
	@Mock
	private RefEtablissementDto etablissementEntiteDto;
	@Mock
	private RefStructureDto structureEntiteDto;

	@InjectMocks
	private DocumentRealisationMarcheServiceImpl documentRealisationMarcheService = new DocumentRealisationMarcheServiceImpl();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetDao() throws Exception {
		assertNotNull(documentRealisationMarcheService.getDao());
	}

	@Test
	public void testEnregistrerDocuementRealisationMarche() throws Exception {
		when(mapper.map(dto, DocumentRealisationMarche.class)).thenReturn(entity);
		when(documentRealisationMarcheDao.save(entity)).thenReturn(entity);
		when(mapper.map(entity, DocumentRealisationMarcheDto.class)).thenReturn(dto);

		DocumentRealisationMarcheDto result = documentRealisationMarcheService
				.enregistrerDocuementRealisationMarche(dto);
		verify(mapper).map(dto, DocumentRealisationMarche.class);
		verify(documentRealisationMarcheDao).save(entity);
		verify(mapper).map(entity, DocumentRealisationMarcheDto.class);
		assertNotNull("Le resultat d'enregistrement d'un nouvelle demande d'engagement ne doit pas etre null.", result);
	}

	@Test
	public void testSupprimerDocuementRealisationMarche() throws Exception {
		when(mapper.map(dto, DocumentRealisationMarche.class)).thenReturn(entity);
		when(documentRealisationMarcheDao.findById(entity.getId())).thenReturn(entity);

		documentRealisationMarcheService.supprimerDocuementRealisationMarche(dto);
		verify(mapper).map(dto, DocumentRealisationMarche.class);
		verify(documentRealisationMarcheDao).findById(entity.getId());
		verify(documentRealisationMarcheDao).remove(entity);
	}

	@Test
	public void testValiderEnregistrementDocuementRealisationMarche() throws Exception {
		when(
				situationService.findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_GFC_DOC_REALISATION,
						UtilConstants.SITUATION_VALIDEE_CODE)).thenReturn(situationEntiteDto);
		when(mapper.map(dto, DocumentRealisationMarche.class)).thenReturn(entity);
		when(documentRealisationMarcheDao.save(entity)).thenReturn(entity);
		when(mapper.map(entity, DocumentRealisationMarcheDto.class)).thenReturn(dto);

		dto = documentRealisationMarcheService.validerEnregistrementDocuementRealisationMarche(dto);

		verify(situationService).findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_GFC_DOC_REALISATION,
				UtilConstants.SITUATION_VALIDEE_CODE);
		verify(documentRealisationMarcheDao).save(entity);
		verify(situationService).saveSituationOccurrence(dto.getSituation(), dto.getId());
		Assert.assertNotEquals(
				"L'ancien situation de la demande d'engagement de marche et la nouvelle doivent etre differents apres la validation.",
				situationEntiteDto, dto.getSituation());
	}

	@Test
	public void testFindDocumentsRealisationForPaiement() throws Exception {
		when(mapper.map(dto, DocumentRealisationMarche.class)).thenReturn(entity);
		when(mapper.map(entity, DocumentRealisationMarcheDto.class)).thenReturn(dto);
		when(
				situationService.findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_GFC_DOC_REALISATION,
						UtilConstants.SITUATION_VALIDEE_CODE)).thenReturn(situationEntiteDto);
		when(
				situationService.findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_GFC_MARCHE,
						UtilConstants.SITUATION_VALIDEE_CODE)).thenReturn(situationMarcheEntiteDto);

		documentRealisationMarcheService
				.findDocumentsRealisationForPaiement(etablissementEntiteDto, structureEntiteDto);

		verify(situationService).findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_GFC_DOC_REALISATION,
				UtilConstants.SITUATION_VALIDEE_CODE);
		verify(situationService).findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_GFC_MARCHE,
				UtilConstants.SITUATION_VALIDEE_CODE);
	}
}
