package dz.gov.mesrs.sii.grh.business.service.finactivite.impl;

import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode.Filter;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode.FilterMode;
import dz.gov.mesrs.sii.commons.data.dao.bpm.SituationEntiteDao;
import dz.gov.mesrs.sii.commons.data.dao.bpm.SituationEntiteOccurrenceDao;
import dz.gov.mesrs.sii.commons.data.dao.grh.ChangementPositionDao;
import dz.gov.mesrs.sii.commons.data.dao.grh.DossierEmployeDao;
import dz.gov.mesrs.sii.commons.data.dao.grh.FinActiviteDao;
import dz.gov.mesrs.sii.commons.data.dao.referentiel.NomenclatureDao;
import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefTypePieceConstitutiveDao;
import dz.gov.mesrs.sii.commons.data.model.bpm.SituationEntite;
import dz.gov.mesrs.sii.commons.data.model.grh.DossierEmploye;
import dz.gov.mesrs.sii.commons.data.model.grh.FinActivite;
import dz.gov.mesrs.sii.commons.data.model.referentiel.Nomenclature;
import dz.gov.mesrs.sii.commons.data.util.UtilConstant;
import dz.gov.mesrs.sii.grh.business.model.dto.FinActiviteDto;

public class TestFinActiviteServiceImpl {

	
	private FinActiviteServiceImpl tested;
	private SituationEntiteDao mockSituationEntiteDao;
	private SituationEntiteOccurrenceDao mockSituationEntiteOccurrenceDao;
	private NomenclatureDao mockNomenclatureDao;
	private RefTypePieceConstitutiveDao mockTypePieceConstitutiveDao;
	private DossierEmployeDao mockDossierEmployeDao;
	private ChangementPositionDao mockChangementPositionDao;
	private FinActiviteDao mockFinActiviteDao;
	private Mapper mockMapper;
	private int etablissementId = 1;
	private String searchKeyWords;
	private SearchMode searchMode;
	private Nomenclature ncDepartType;
	private Nomenclature ncRetraiteType;
	private SituationEntite situationEntite;
	private FinActivite entity;
	private FinActiviteDto dto;
	
	@Before
	public void setUp(){
		tested= new FinActiviteServiceImpl();
		mockSituationEntiteDao =mock(SituationEntiteDao.class);
		mockSituationEntiteOccurrenceDao=mock(SituationEntiteOccurrenceDao.class);
		mockNomenclatureDao=mock(NomenclatureDao.class);
		mockTypePieceConstitutiveDao=mock(RefTypePieceConstitutiveDao.class);
		mockDossierEmployeDao=mock(DossierEmployeDao.class);
		mockChangementPositionDao=mock(ChangementPositionDao.class);
		mockFinActiviteDao=mock(FinActiviteDao.class);
		mockMapper=mock(Mapper.class);
		tested.setSituationEntiteDao(mockSituationEntiteDao);
		tested.setSituationEntiteOccurrenceDao(mockSituationEntiteOccurrenceDao);
		tested.setNomenclatureDao(mockNomenclatureDao);
		tested.setTypePieceConstitutiveDao(mockTypePieceConstitutiveDao);
		tested.setDossierEmployeDao(mockDossierEmployeDao);
		tested.setFinActiviteDao(mockFinActiviteDao);
		tested.setChangementPositionDao(mockChangementPositionDao);
		tested.setMapper(mockMapper);
		
		searchMode = new SearchMode();
		ncDepartType = new Nomenclature(1,"toto");
		ncRetraiteType = new Nomenclature(2,"tata");
		situationEntite= new SituationEntite();
		situationEntite.setId(5);
		entity = new FinActivite();
		dto = new FinActiviteDto();
		
	}
	
//	@Test
	public void findAllAdmisRetraite(){
//		List<FinActiviteDto> admisRetraites  = new ArrayList<FinActiviteDto>();
//		when(mock)
//		admisRetraites = tested.findAllAdmisRetraite(etablissementId, searchKeyWords, searchMode);
//		
	}
	
//	@Test
	public void countAllAdmisRetraite(){
		
	}
	
	@Test
	public void saveDemandeDemissionCree(){
		when(mockNomenclatureDao.findByCode(UtilConstant.NC_GRH_TYPE_CESSATION, UtilConstant.NC_GRH_TYPE_CESSATION_DEMISSION_VALUE)).thenReturn(ncDepartType);
		when(mockSituationEntiteDao.findByCodeSituationByCodeEntite(UtilConstant.ENTITE_DEMANDE_DEMISSION, UtilConstant.SITUTAION_CREEE_CODE)).thenReturn(situationEntite);
		FinActivite sessionEntity = new FinActivite(22L);
		when(mockFinActiviteDao.save(entity)).thenReturn(sessionEntity);
		when(mockMapper.map(dto, FinActivite.class)).thenReturn(entity);
		when(mockMapper.map(sessionEntity, FinActiviteDto.class)).thenReturn(dto);
		
		dto = tested.saveDemandeDemission(dto);
		
		assertNotNull("Le retour de la methode naurait pas du etre nulle",dto);
		verify(mockNomenclatureDao).findByCode(UtilConstant.NC_GRH_TYPE_CESSATION, UtilConstant.NC_GRH_TYPE_CESSATION_DEMISSION_VALUE);
		verify(mockSituationEntiteDao).findByCodeSituationByCodeEntite(UtilConstant.ENTITE_DEMANDE_DEMISSION, UtilConstant.SITUTAION_CREEE_CODE);
		verifyNcAndSituation();
		
	}
	
	@Test
	public void saveDemandeDemissionEnregistree(){
		entity.setId(1L);
		when(mockMapper.map(dto, FinActivite.class)).thenReturn(entity);
		when(mockNomenclatureDao.findByCode(UtilConstant.NC_GRH_TYPE_CESSATION, UtilConstant.NC_GRH_TYPE_CESSATION_DEMISSION_VALUE)).thenReturn(ncDepartType);
		when(mockSituationEntiteDao.findByCodeSituationByCodeEntite(UtilConstant.ENTITE_DEMANDE_DEMISSION, UtilConstant.SITUTAION_ENREGISTREE_CODE)).thenReturn(situationEntite);
		when(mockFinActiviteDao.save(entity)).thenReturn(entity);
		when(mockMapper.map(entity, FinActiviteDto.class)).thenReturn(dto);
		
		dto = tested.saveDemandeDemission(dto);
		assertNotNull("Le retour de la methode naurait pas du etre nulle",dto);
		verify(mockNomenclatureDao).findByCode(UtilConstant.NC_GRH_TYPE_CESSATION, UtilConstant.NC_GRH_TYPE_CESSATION_DEMISSION_VALUE);
		verify(mockSituationEntiteDao).findByCodeSituationByCodeEntite(UtilConstant.ENTITE_DEMANDE_DEMISSION, UtilConstant.SITUTAION_ENREGISTREE_CODE);
		verifyNcAndSituation();
		
	}
	
	

	@Test
	public void saveDemandeDemissionTraitee(){
		entity.setId(1L);
		entity.setAccepte(Boolean.TRUE);
		when(mockMapper.map(dto, FinActivite.class)).thenReturn(entity);
		when(mockNomenclatureDao.findByCode(UtilConstant.NC_GRH_TYPE_CESSATION, UtilConstant.NC_GRH_TYPE_CESSATION_DEMISSION_VALUE)).thenReturn(ncDepartType);
		when(mockSituationEntiteDao.findByCodeSituationByCodeEntite(UtilConstant.ENTITE_DEMANDE_DEMISSION, UtilConstant.SITUTAION_TRAITEE_CODE)).thenReturn(situationEntite);
		when(mockFinActiviteDao.save(entity)).thenReturn(entity);
		when(mockMapper.map(entity, FinActiviteDto.class)).thenReturn(dto);
		
		dto = tested.saveDemandeDemission(dto);
		assertNotNull("Le retour de la methode naurait pas du etre nulle",dto);
		verify(mockNomenclatureDao).findByCode(UtilConstant.NC_GRH_TYPE_CESSATION, UtilConstant.NC_GRH_TYPE_CESSATION_DEMISSION_VALUE);
		verify(mockSituationEntiteDao).findByCodeSituationByCodeEntite(UtilConstant.ENTITE_DEMANDE_DEMISSION, UtilConstant.SITUTAION_TRAITEE_CODE);
		verifyNcAndSituation();
	}
	
	@Test
	public void saveDemandeRetraiteAnticipeeCree(){
		when(mockMapper.map(dto, FinActivite.class)).thenReturn(entity);
		when(mockNomenclatureDao.findByCode(UtilConstant.NC_GRH_TYPE_CESSATION, UtilConstant.NC_GRH_TYPE_CESSATION_RETRAITE_VALUE)).thenReturn(ncDepartType);
		when(mockSituationEntiteDao.findByCodeSituationByCodeEntite(UtilConstant.ENTITE_DEMANDE_RETRAITE_ANTICIPE, UtilConstant.SITUTAION_CREEE_CODE)).thenReturn(situationEntite);
		FinActivite sessionEntity = new FinActivite(22L);
		when(mockFinActiviteDao.save(entity)).thenReturn(sessionEntity);
		when(mockMapper.map(sessionEntity, FinActiviteDto.class)).thenReturn(dto);
		
		dto = tested.saveDemandeRetraiteAnticipe(dto);
		
		assertNotNull("Le retour de la methode naurait pas du etre nulle",dto);
		verify(mockNomenclatureDao).findByCode(UtilConstant.NC_GRH_TYPE_CESSATION, UtilConstant.NC_GRH_TYPE_CESSATION_RETRAITE_VALUE);
		verify(mockSituationEntiteDao).findByCodeSituationByCodeEntite(UtilConstant.ENTITE_DEMANDE_RETRAITE_ANTICIPE, UtilConstant.SITUTAION_CREEE_CODE);
		verifyNcAndSituation();
		
	}
	
	@Test
	public void saveDemandeRetraiteAnticipeeEnregistree(){
		entity.setId(1L);
		when(mockNomenclatureDao.findByCode(UtilConstant.NC_GRH_TYPE_CESSATION, UtilConstant.NC_GRH_TYPE_CESSATION_RETRAITE_VALUE)).thenReturn(ncDepartType);
		when(mockSituationEntiteDao.findByCodeSituationByCodeEntite(UtilConstant.ENTITE_DEMANDE_RETRAITE_ANTICIPE, UtilConstant.SITUTAION_ENREGISTREE_CODE)).thenReturn(situationEntite);
		when(mockFinActiviteDao.save(entity)).thenReturn(entity);
		mockMapUnMap();
		dto = tested.saveDemandeRetraiteAnticipe(dto);
		assertNotNull("Le retour de la methode naurait pas du etre nulle",dto);
		verify(mockNomenclatureDao).findByCode(UtilConstant.NC_GRH_TYPE_CESSATION, UtilConstant.NC_GRH_TYPE_CESSATION_RETRAITE_VALUE);
		verify(mockSituationEntiteDao).findByCodeSituationByCodeEntite(UtilConstant.ENTITE_DEMANDE_RETRAITE_ANTICIPE, UtilConstant.SITUTAION_ENREGISTREE_CODE);
		verifyNcAndSituation();
		
	}
	

	@Test
	public void saveDemandeRetraiteAnticipeeTraitee(){
		entity.setId(1L);
		entity.setAccepte(Boolean.TRUE);
		when(mockNomenclatureDao.findByCode(UtilConstant.NC_GRH_TYPE_CESSATION, UtilConstant.NC_GRH_TYPE_CESSATION_RETRAITE_VALUE)).thenReturn(ncDepartType);
		when(mockSituationEntiteDao.findByCodeSituationByCodeEntite(UtilConstant.ENTITE_DEMANDE_RETRAITE_ANTICIPE, UtilConstant.SITUTAION_TRAITEE_CODE)).thenReturn(situationEntite);
		when(mockFinActiviteDao.save(entity)).thenReturn(entity);
		mockMapUnMap();
		
		dto = tested.saveDemandeRetraiteAnticipe(dto);
		assertNotNull("Le retour de la methode naurait pas du etre nulle",dto);
		verify(mockNomenclatureDao).findByCode(UtilConstant.NC_GRH_TYPE_CESSATION, UtilConstant.NC_GRH_TYPE_CESSATION_RETRAITE_VALUE);
		verify(mockSituationEntiteDao).findByCodeSituationByCodeEntite(UtilConstant.ENTITE_DEMANDE_RETRAITE_ANTICIPE, UtilConstant.SITUTAION_TRAITEE_CODE);
		verifyNcAndSituation();
	}
	
//	@Test
	public void saveCessationFinalDemission(){
		mockMapUnMap();
		
		dto = tested.saveCessationFinal(dto);
		assertNotNull("Le retour de la methode naurait pas du etre nulle",dto);
	}
	
	
	@Test
	public void saveCessationFinalRetraiteAutre(){
		mockMapUnMap();
		when(mockSituationEntiteDao.findByCodeSituationByCodeEntite(UtilConstant.ENTITE_CESSATION_ACTIVITE, UtilConstant.SITUTAION_CREEE_CODE)).thenReturn(situationEntite);
		when(mockFinActiviteDao.save(entity)).thenReturn(entity);
		entity.setNomenclature(ncDepartType);
		entity.setId(110L);
		
		dto = tested.saveCessationFinal(dto);
		assertNotNull("Le retour de la methode naurait pas du etre nulle",dto);
		verify(mockSituationEntiteDao).findByCodeSituationByCodeEntite(UtilConstant.ENTITE_CESSATION_ACTIVITE, UtilConstant.SITUTAION_CREEE_CODE);
		verify(mockFinActiviteDao).save(entity);
	}
	@Test
	public void validerCessationFinal(){
		mockMapUnMap();
		when(mockSituationEntiteDao.findByCodeSituationByCodeEntite(UtilConstant.ENTITE_CESSATION_ACTIVITE, UtilConstant.SITUTAION_VALIDEE_CODE)).thenReturn(situationEntite);
		when(mockFinActiviteDao.save(entity)).thenReturn(entity);
		DossierEmploye dossierEmploye = new DossierEmploye(15L);
		entity.setEmploye(dossierEmploye);
		when(mockDossierEmployeDao.findById(entity.getEmploye().getId())).thenReturn(dossierEmploye);
		when(mockNomenclatureDao.findByCode(UtilConstant.NC_GRH_POSITION_EMPLOYE_VALUE, UtilConstant.NC_GRH_POSITION_EMPLOYE_FIN_ACTIVITE_CODE)).thenReturn(ncDepartType);
		entity.setNomenclature(ncDepartType);
		entity.setId(110L);
		FinActivite finActivite = new FinActivite();
		
		tested.validerCessationFinal(dto);
		verify(mockSituationEntiteDao).findByCodeSituationByCodeEntite(UtilConstant.ENTITE_CESSATION_ACTIVITE, UtilConstant.SITUTAION_VALIDEE_CODE);
		verify(mockFinActiviteDao).save(entity);
		
		
	}
	//private
	
	private void mockMapUnMap(){
		when(mockMapper.map(dto, FinActivite.class)).thenReturn(entity);
		when(mockMapper.map(entity, FinActiviteDto.class)).thenReturn(dto);
	}
	private void verifyNcAndSituation() {
		assertEquals("Le type de depart na pas ete affecte",entity.getNomenclature().getId() , ncDepartType.getId());
		assertEquals("Le type de depart na pas ete affecte",entity.getSituation().getId() , situationEntite.getId());		
	}
	
	private Filter createFilterEtablisementId(){
		return new Filter("etablissement.id", etablissementId, FilterMode.EQUALS);
	}
	
	private Filter createDateCessationNullFilter(){
		return new Filter("dateCessationFinale", FilterMode.IS_NULL);
	}
	private Filter createNomenclatureTypeFilter(){
		return  new Filter("nomenclature.id", ncDepartType.getId(), FilterMode.EQUALS);
	}
	
	private Filter createRetraiteTypeFilter(){
		return new Filter("typeDepartRetraite.id", ncRetraiteType.getId(), FilterMode.EQUALS);
	}
	
	
}
