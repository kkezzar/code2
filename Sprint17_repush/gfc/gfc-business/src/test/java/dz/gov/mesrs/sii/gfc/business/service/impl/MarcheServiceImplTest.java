/**
 * 
 */
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
import dz.gov.mesrs.sii.commons.data.dao.gfc.MarcheDao;
import dz.gov.mesrs.sii.commons.data.model.gfc.Marche;
import dz.gov.mesrs.sii.gfc.business.model.dto.MarcheDto;

/**
 * Unit test for marcheService.
 * 
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 15/02/2015 15:54:25
 */
@RunWith(MockitoJUnitRunner.class)
public class MarcheServiceImplTest {
	@Mock
	private Mapper mapper;

	@Mock
	private MarcheDao marcheDao;

	@Mock
	private SituationService situationService;

	@Mock
	private SituationEntiteDto situationEntiteDto;

	@Mock
	private MarcheDto dto;
	@Mock
	private Marche entity;

	@InjectMocks
	private MarcheServiceImpl marcheService = new MarcheServiceImpl();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetDaoShouldNotReturnNull() throws Exception {
		assertNotNull(marcheService.getDao());
	}

	@Test
	public void testEnregistrerMarche() throws Exception {
		when(mapper.map(dto, Marche.class)).thenReturn(entity);
		when(marcheDao.save(entity)).thenReturn(entity);
		when(mapper.map(entity, MarcheDto.class)).thenReturn(dto);

		MarcheDto result = marcheService.enregistrerMarche(dto);
		verify(mapper).map(dto, Marche.class);
		verify(marcheDao).save(entity);
		verify(mapper).map(entity, MarcheDto.class);
		assertNotNull("Le resultat d'enregistrement d'un nouveau marche ne doit pas etre null.", result);
	}

	@Test
	public void testSupprimerMarche() throws Exception {
		when(mapper.map(dto, Marche.class)).thenReturn(entity);
		when(marcheDao.findById(entity.getId())).thenReturn(entity);

		marcheService.supprimerMarche(dto);
		verify(mapper).map(dto, Marche.class);
		verify(marcheDao).findById(entity.getId());
		verify(marcheDao).remove(entity);

	}

	@Test
	public void testValiderEnregistrementMarche() throws Exception {
		when(
				situationService.findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_GFC_MARCHE,
						UtilConstants.SITUATION_VALIDEE_CODE)).thenReturn(situationEntiteDto);
		when(mapper.map(dto, Marche.class)).thenReturn(entity);
		when(marcheDao.save(entity)).thenReturn(entity);
		when(mapper.map(entity, MarcheDto.class)).thenReturn(dto);

		dto = marcheService.validerEnregistrementMarche(dto);

		verify(situationService).findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_GFC_MARCHE,
				UtilConstants.SITUATION_VALIDEE_CODE);
		verify(marcheDao).save(entity);
		verify(situationService).saveSituationOccurrence(dto.getSituation(), dto.getId());
		Assert.assertNotEquals(
				"L'ancien situation du marche et la nouvelle doivent etre differents apres la validation.",
				situationEntiteDto, dto.getSituation());
	}

	@Test
	public void testCloturerMarche() throws Exception {
		situationEntiteDto = new SituationEntiteDto();
		when(
				situationService.findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_GFC_MARCHE,
						UtilConstants.SITUATION_CLOTUREE_CODE)).thenReturn(situationEntiteDto);

		when(mapper.map(dto, Marche.class)).thenReturn(entity);
		when(marcheDao.save(entity)).thenReturn(entity);
		when(mapper.map(entity, MarcheDto.class)).thenReturn(dto);

		dto = marcheService.cloturerMarche(dto);

		verify(situationService).findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_GFC_MARCHE,
				UtilConstants.SITUATION_CLOTUREE_CODE);
		verify(marcheDao).save(entity);
		verify(situationService).saveSituationOccurrence(dto.getSituation(), dto.getId());
		Assert.assertNotEquals("L'ancien situation du marche et la nouvelle doivent etre differents apres la cloture.",
				situationEntiteDto, dto.getSituation());
	}
}
