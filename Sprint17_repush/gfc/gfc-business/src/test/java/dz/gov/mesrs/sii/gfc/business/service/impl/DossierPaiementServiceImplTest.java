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
import dz.gov.mesrs.sii.commons.data.dao.gfc.DossierPaiementDao;
import dz.gov.mesrs.sii.commons.data.model.gfc.DossierPaiement;
import dz.gov.mesrs.sii.gfc.business.model.dto.DossierPaiementDto;

@RunWith(MockitoJUnitRunner.class)
public class DossierPaiementServiceImplTest {
	@Mock
	private DossierPaiementDao dossierPaiementDao;

	@Mock
	private Mapper mapper;

	@Mock
	private SituationService situationService;

	@Mock
	private SituationEntiteDto situationEntiteDto;
	@Mock
	private DossierPaiementDto dto;
	@Mock
	private DossierPaiement entity;

	@InjectMocks
	private DossierPaiementServiceImpl dossierPaiementService = new DossierPaiementServiceImpl();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetDao() throws Exception {
		assertNotNull(dossierPaiementService.getDao());
	}

	@Test
	public void testEnregistrerDossierPaiement() throws Exception {
		when(mapper.map(dto, DossierPaiement.class)).thenReturn(entity);
		when(dossierPaiementDao.save(entity)).thenReturn(entity);
		when(mapper.map(entity, DossierPaiementDto.class)).thenReturn(dto);

		DossierPaiementDto result = dossierPaiementService.enregistrerDossierPaiement(dto);
		verify(mapper).map(dto, DossierPaiement.class);
		verify(dossierPaiementDao).save(entity);
		verify(mapper).map(entity, DossierPaiementDto.class);
		assertNotNull("Le resultat d'enregistrement d'un nouveau dossier de paiement ne doit pas etre null.", result);
	}

	@Test
	public void testSupprimerDossierPaiement() throws Exception {
		when(mapper.map(dto, DossierPaiement.class)).thenReturn(entity);
		when(dossierPaiementDao.findById(entity.getId())).thenReturn(entity);

		dossierPaiementService.supprimerDossierPaiement(dto);
		verify(mapper).map(dto, DossierPaiement.class);
		verify(dossierPaiementDao).findById(entity.getId());
		verify(dossierPaiementDao).remove(entity);
	}

	@Test
	public void testEnvoyerDossierPaiement() throws Exception {
		when(
				situationService.findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_GFC_DOSSIER_PAIEMENT,
						UtilConstants.SITUATION_ENVOYE_CODE)).thenReturn(situationEntiteDto);
		when(mapper.map(dto, DossierPaiement.class)).thenReturn(entity);
		when(dossierPaiementDao.save(entity)).thenReturn(entity);
		when(mapper.map(entity, DossierPaiementDto.class)).thenReturn(dto);

		dto = dossierPaiementService.envoyerDossierPaiement(dto);

		verify(situationService).findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_GFC_DOSSIER_PAIEMENT,
				UtilConstants.SITUATION_ENVOYE_CODE);
		verify(dossierPaiementDao).save(entity);
		verify(situationService).saveSituationOccurrence(dto.getSituation(), dto.getId());
		Assert.assertNotEquals(
				"L'ancien situation du dossier de paiement et la nouvelle doivent etre differents apres l'envoi.",
				situationEntiteDto, dto.getSituation());
	}

	@Test
	public void testValiderDossierPaiement() throws Exception {
		when(
				situationService.findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_GFC_DOSSIER_PAIEMENT,
						UtilConstants.SITUATION_VALIDEE_CODE)).thenReturn(situationEntiteDto);
		when(mapper.map(dto, DossierPaiement.class)).thenReturn(entity);
		when(dossierPaiementDao.save(entity)).thenReturn(entity);
		when(mapper.map(entity, DossierPaiementDto.class)).thenReturn(dto);

		dto = dossierPaiementService.validerDossierPaiement(dto);

		verify(situationService).findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_GFC_DOSSIER_PAIEMENT,
				UtilConstants.SITUATION_VALIDEE_CODE);
		verify(dossierPaiementDao).save(entity);
		verify(situationService).saveSituationOccurrence(dto.getSituation(), dto.getId());
		Assert.assertNotEquals(
				"L'ancien situation du dossier de paiement et la nouvelle doivent etre differents apres la validation.",
				situationEntiteDto, dto.getSituation());
	}

}
