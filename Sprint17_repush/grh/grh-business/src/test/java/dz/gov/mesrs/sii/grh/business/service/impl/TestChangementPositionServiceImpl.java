package dz.gov.mesrs.sii.grh.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;
import org.junit.Before;

import dz.gov.mesrs.sii.commons.data.dao.bpm.SituationEntiteDao;
import dz.gov.mesrs.sii.commons.data.dao.grh.ChangementPositionDao;
import dz.gov.mesrs.sii.commons.data.dao.grh.DossierEmployeDao;
import dz.gov.mesrs.sii.commons.data.dao.impl.fve.offreformation.NiveauDaoImpl;
import dz.gov.mesrs.sii.commons.data.dao.referentiel.NomenclatureDao;
import dz.gov.mesrs.sii.commons.data.model.bpm.SituationEntite;
import dz.gov.mesrs.sii.commons.data.model.grh.ChangementPosition;
import dz.gov.mesrs.sii.commons.data.model.grh.DossierEmploye;
import dz.gov.mesrs.sii.commons.data.model.referentiel.Nomenclature;
import dz.gov.mesrs.sii.commons.data.util.UtilConstant;
import dz.gov.mesrs.sii.grh.business.model.dto.ChangementPositionDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class TestChangementPositionServiceImpl {

	private ChangementPositionServiceImpl tested;
	private ChangementPositionDto dto;
	private ChangementPosition entity;
	private List<ChangementPositionDto> dtos;
	private List<ChangementPosition> entities;
	private Mapper mockMapper;
	private ChangementPositionDao mockDao;
	private NomenclatureDao mockNomenclatureDao;
	private SituationEntiteDao mockSituationEntiteDao;
	private DossierEmployeDao mockDossierEmployeDao;
	private SituationEntite situationEntite;
	private DossierEmploye dossierEmploye;
	private Nomenclature nomenclature;

	@Before
	public void init() {
		tested = new ChangementPositionServiceImpl();

		// mocks
		mockMapper = mock(Mapper.class);
		mockDao = mock(ChangementPositionDao.class);
		mockNomenclatureDao = mock(NomenclatureDao.class);
		mockSituationEntiteDao = mock(SituationEntiteDao.class);
		mockDossierEmployeDao = mock(DossierEmployeDao.class);
		tested.setChangementPositionDao(mockDao);
		tested.setDossierEmployeDao(mockDossierEmployeDao);
		tested.setMapper(mockMapper);
		tested.setNomenclatureDao(mockNomenclatureDao);
		tested.setSituationEntiteDao(mockSituationEntiteDao);

		// stubs
		dto = new ChangementPositionDto();
		entity = new ChangementPosition();
		dtos = new ArrayList<ChangementPositionDto>();
		entities = new ArrayList<ChangementPosition>();
		entities.add(entity);
		situationEntite = new SituationEntite();
		dossierEmploye = new DossierEmploye();
		entity.setDossierEmploye(dossierEmploye);
		nomenclature = new Nomenclature();
	}

	@Test
	public void findPosition() {
		NomenclatureDto ncDto = new NomenclatureDto();
		Nomenclature ncEntity = new Nomenclature();
		when(mockDao.findPosition(1L)).thenReturn(ncEntity);
		when(mockMapper.map(ncEntity, NomenclatureDto.class)).thenReturn(ncDto);

		ncDto = tested.findPosition(1L);

		verify(mockDao).findPosition(1L);
		verify(mockMapper).map(ncEntity, NomenclatureDto.class);

		assertNotNull("La nomenclature position de retour n'aurait pas du etre null", ncDto);
	}

	@Test(expected = IllegalArgumentException.class)
	public void findPendingChangementPositionNullParameter() {
		tested.findPendingChangementPosition(null);
	}

	@Test
	public void findPendingChangementPosition() {
		DossierEmploye dEEntity = new DossierEmploye();
		dEEntity.setChangementPositions(entities);
		entity.setAcceptee(null);

		when(mockDossierEmployeDao.findById(1L)).thenReturn(dEEntity);
		when(mockMapper.map(entity, ChangementPositionDto.class)).thenReturn(dto);
		
		ChangementPositionDto result = tested.findPendingChangementPosition(1L);
		
		verify(mockDossierEmployeDao).findById(1L);
		verify(mockMapper).map(entity, ChangementPositionDto.class);
		assertEquals("Le resultat est incorrecte", dto, result);
		
	}

	@Test
	public void findPendingChangementPositionEmpty() {
		DossierEmploye dEEntity = new DossierEmploye();

		when(mockDossierEmployeDao.findById(1L)).thenReturn(dEEntity);

		dto = tested.findPendingChangementPosition(1L);

		verify(mockDossierEmployeDao).findById(1L);
		assertNull("La methode n'aurait du rendre aucun resultat", dto);
	}

	
	@Test(expected=IllegalArgumentException.class)
	public void saveDemandeNullArgument() {
		tested.saveDemande(null);
	}
	
	@Test
	public void saveDemandeNouvelle() {
		when(mockMapper.map(dto, ChangementPosition.class)).thenReturn(entity);
		when(mockSituationEntiteDao.findByCodeSituationByCodeEntite(UtilConstant.ENTITE_DEMANDE_CHANGEMEMENT_POSITION, UtilConstant.SITUTAION_CREEE_CODE)).thenReturn(situationEntite);
		when(mockMapper.map(entity, ChangementPositionDto.class)).thenReturn(dto);
		when(mockDao.save(entity)).thenReturn(entity);
		
		ChangementPositionDto result = tested.saveDemande(dto);
				
		verify(mockMapper).map(dto, ChangementPosition.class);
		verify(mockSituationEntiteDao).findByCodeSituationByCodeEntite(UtilConstant.ENTITE_DEMANDE_CHANGEMEMENT_POSITION, UtilConstant.SITUTAION_CREEE_CODE);
		verify(mockMapper).map(entity, ChangementPositionDto.class);
		verify(mockDao).save(entity);
		
		assertEquals("Le resultat n'est pas celui",dto, result);
		assertEquals("La demande n'a pas ete affecte avec la bonne situtation", situationEntite, entity.getSituation());
	}

	@Test
	public void saveDemandeMiseAJour() {
		entity.setId(100L);
		when(mockMapper.map(dto, ChangementPosition.class)).thenReturn(entity);
		when(mockSituationEntiteDao.findByCodeSituationByCodeEntite(UtilConstant.ENTITE_DEMANDE_CHANGEMEMENT_POSITION, UtilConstant.SITUTAION_ENREGISTREE_CODE)).thenReturn(situationEntite);
		when(mockMapper.map(entity, ChangementPositionDto.class)).thenReturn(dto);
		when(mockDao.save(entity)).thenReturn(entity);
		
		ChangementPositionDto result = tested.saveDemande(dto);
				
		verify(mockMapper).map(dto, ChangementPosition.class);
		verify(mockSituationEntiteDao).findByCodeSituationByCodeEntite(UtilConstant.ENTITE_DEMANDE_CHANGEMEMENT_POSITION, UtilConstant.SITUTAION_ENREGISTREE_CODE);
		verify(mockMapper).map(entity, ChangementPositionDto.class);
		verify(mockDao).save(entity);
		
		assertEquals("Le resultat n'est pas celui",dto, result);
		assertEquals("La demande n'a pas ete affecte avec la bonne situtation", situationEntite, entity.getSituation());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void traiterDemandeNullArgument() {
		tested.traiterDemande(null);
	}

	@Test
	public void traiterDemande() {
		when(mockMapper.map(dto, ChangementPosition.class)).thenReturn(entity);
		when(mockSituationEntiteDao.findByCodeSituationByCodeEntite(UtilConstant.ENTITE_DEMANDE_CHANGEMEMENT_POSITION, UtilConstant.SITUTAION_TRAITEE_CODE)).thenReturn(situationEntite);
		
		tested.traiterDemande(dto);
				
		verify(mockMapper).map(dto, ChangementPosition.class);
		verify(mockSituationEntiteDao).findByCodeSituationByCodeEntite(UtilConstant.ENTITE_DEMANDE_CHANGEMEMENT_POSITION, UtilConstant.SITUTAION_TRAITEE_CODE);
		verify(mockDao).save(entity);
		
		assertEquals("La demande n'a pas ete affecte avec la bonne situtation", situationEntite, entity.getSituation());
	}

	
	@Test(expected=IllegalArgumentException.class)
	public void validateNullArgument() {
		tested.validate(null);
	}

	@Test
	public void validate() {
		when(mockMapper.map(dto, ChangementPosition.class)).thenReturn(entity);
		when(mockSituationEntiteDao.findByCodeSituationByCodeEntite(UtilConstant.ENTITE_DEMANDE_CHANGEMEMENT_POSITION, UtilConstant.SITUTAION_VALIDEE_CODE)).thenReturn(situationEntite);
		when(mockDao.save(entity)).thenReturn(entity);
		
		tested.validate(dto);
				
		verify(mockMapper).map(dto, ChangementPosition.class);
		verify(mockSituationEntiteDao).findByCodeSituationByCodeEntite(UtilConstant.ENTITE_DEMANDE_CHANGEMEMENT_POSITION, UtilConstant.SITUTAION_VALIDEE_CODE);
		verify(mockDao).save(entity);
		verify(mockDossierEmployeDao).save(dossierEmploye);
		
		assertEquals("La demande n'a pas ete affecte avec la bonne situtation", situationEntite, entity.getSituation());	
		assertEquals("La position courante du dossier employe n'est pas correcte", dossierEmploye.getCurrentPosition(), entity);
	}
	
	@Test
	public void saveChangementDroit() {
		when(mockMapper.map(dto, ChangementPosition.class)).thenReturn(entity);
		when(mockSituationEntiteDao.findByCodeSituationByCodeEntite(UtilConstant.ENTITE_DEMANDE_CHANGEMEMENT_POSITION, UtilConstant.SITUTAION_TRAITEE_CODE)).thenReturn(situationEntite);
		when(mockMapper.map(entity, ChangementPositionDto.class)).thenReturn(dto);
		when(mockDao.save(entity)).thenReturn(entity);
		
		ChangementPositionDto result =tested.saveChangementDroit(dto);
		
		verify(mockMapper).map(dto, ChangementPosition.class);
		verify(mockSituationEntiteDao).findByCodeSituationByCodeEntite(UtilConstant.ENTITE_DEMANDE_CHANGEMEMENT_POSITION, UtilConstant.SITUTAION_TRAITEE_CODE);
		verify(mockMapper).map(entity, ChangementPositionDto.class);
		verify(mockDao).save(entity);
		assertEquals("La demande n'a pas ete affecte avec la bonne situtation", result, dto);
		assertEquals("La demande n'a pas ete affecte avec la bonne situtation", situationEntite, entity.getSituation());
		assertEquals("Le demande n'a pas ete sette a acceptee", entity.getAcceptee(),true);
		assertEquals("Le demande n'a pas ete marquee comme etant de droit", entity.isDroit(),true);
	}
	
	@Test
	public void validateChangementDroit() {
		when(mockMapper.map(dto, ChangementPosition.class)).thenReturn(entity);
		when(mockSituationEntiteDao.findByCodeSituationByCodeEntite(UtilConstant.ENTITE_DEMANDE_CHANGEMEMENT_POSITION, UtilConstant.SITUTAION_VALIDEE_CODE)).thenReturn(situationEntite);
		when(mockDao.save(entity)).thenReturn(entity);
		
		tested.validateChangementDroit(dto);
		
		verify(mockMapper).map(dto, ChangementPosition.class);
		verify(mockSituationEntiteDao).findByCodeSituationByCodeEntite(UtilConstant.ENTITE_DEMANDE_CHANGEMEMENT_POSITION, UtilConstant.SITUTAION_VALIDEE_CODE);
		verify(mockDao).save(entity);
		verify(mockDossierEmployeDao).save(dossierEmploye);
		assertEquals("La demande n'a pas ete affecte avec la bonne situtation", situationEntite, entity.getSituation());
		assertEquals("La position courante du dossier employe n'est pas correcte", dossierEmploye.getCurrentPosition(), entity);	
		
	}

	@Test
	public void reintegrer() {
		when(mockMapper.map(dto, ChangementPosition.class)).thenReturn(entity);
		when(mockSituationEntiteDao.findByCodeSituationByCodeEntite(UtilConstant.ENTITE_DEMANDE_CHANGEMEMENT_POSITION, UtilConstant.SITUTAION_REINTEGRER_CODE)).thenReturn(situationEntite);
		when(mockSituationEntiteDao.findByCodeSituationByCodeEntite(UtilConstant.ENTITE_DEMANDE_CHANGEMEMENT_POSITION, UtilConstant.SITUTAION_VALIDEE_CODE)).thenReturn(situationEntite);
		when(mockNomenclatureDao.findByCode(UtilConstant.NC_GRH_POSITION_EMPLOYE_VALUE,UtilConstant.NC_GRH_POSITION_EMPLOYE_ACTIVE_CODE)).thenReturn(nomenclature);
		
		tested.reintegrer(dto);
		
		verify(mockMapper).map(dto, ChangementPosition.class);
		verify(mockSituationEntiteDao).findByCodeSituationByCodeEntite(UtilConstant.ENTITE_DEMANDE_CHANGEMEMENT_POSITION, UtilConstant.SITUTAION_REINTEGRER_CODE);
		verify(mockSituationEntiteDao).findByCodeSituationByCodeEntite(UtilConstant.ENTITE_DEMANDE_CHANGEMEMENT_POSITION, UtilConstant.SITUTAION_VALIDEE_CODE);
		verify(mockNomenclatureDao).findByCode(UtilConstant.NC_GRH_POSITION_EMPLOYE_VALUE,UtilConstant.NC_GRH_POSITION_EMPLOYE_ACTIVE_CODE);
		verify(mockDao).save(entity);
	}
	
	

	
	
//	@Test
	public void findListEmployesParPositionDto() {
//TODO
	}

//	@Test
	public void findAllPendingRequest() {
		//TODO
	}

	//@Test
	public void countAllPendingRequest() {
		//TODO
	}
	
	//@Test
	public void countAllAcceptedRequest() {
		//TODO
	}

	//@Test
	public void findAllAcceptedRequest() {
		//TODO
	}

	//@Test
	public void countAllValidatedRequest() {
		//TODO
	}

	//@Test
	public void findAllValidatedRequest() {
		//TODO
	}

	//@Test
	public void findAllPendingChangementDroit() {
		//TODO
	}

	//@Test
	public void countAllPendingChangementDroit() {
		//TODO
	}

	//@Test
	public void findHistorique() {
		//*TODO
	}

}
