package dz.gov.mesrs.sii.grh.business.service.conges.impl;

import org.dozer.Mapper;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;
import dz.gov.mesrs.sii.commons.business.util.UtilConstants;
import dz.gov.mesrs.sii.commons.data.dao.bpm.SituationEntiteDao;
import dz.gov.mesrs.sii.commons.data.dao.bpm.SituationEntiteOccurrenceDao;
import dz.gov.mesrs.sii.commons.data.dao.grh.CongeEmployeDao;
import dz.gov.mesrs.sii.commons.data.dao.referentiel.NomenclatureDao;
import dz.gov.mesrs.sii.commons.data.model.bpm.SituationEntite;
import dz.gov.mesrs.sii.commons.data.model.bpm.SituationEntiteOccurrence;
import dz.gov.mesrs.sii.commons.data.model.grh.CongeEmploye;
import dz.gov.mesrs.sii.grh.business.model.dto.CongeEmployeDto;

public class TestCongeEmployeServiceImpl {

	private CongeEmployeServiceImpl tested;
	private CongeEmploye entity;
	private CongeEmployeDto dto;
	private SituationEntite situation;
	private Mapper mockMapper;
	private CongeEmployeDao mockDao;
	private SituationEntiteDao mockSituationDao;
	private SituationEntiteOccurrenceDao mockSituationOccurrenceDao;
	private NomenclatureDao mockNomenclatureDao;
	
	
	@Before
	public void setUp(){
		tested  = new CongeEmployeServiceImpl();
		entity = new CongeEmploye();
		entity.setId(15L);
		dto =new CongeEmployeDto();
		situation = new SituationEntite();
		mockMapper = mock(Mapper.class);
		mockDao = mock(CongeEmployeDao.class);
		mockSituationDao = mock(SituationEntiteDao.class);
		mockSituationOccurrenceDao = mock(SituationEntiteOccurrenceDao.class);
		mockNomenclatureDao = mock(NomenclatureDao.class);
		
		tested.setMapper(mockMapper);
		tested.setSituationEntiteDao(mockSituationDao);
		tested.setSituationEntiteOccurrenceDao(mockSituationOccurrenceDao);
		tested.setCongeEmployeDao(mockDao);
		tested.setNomenclatureDao(mockNomenclatureDao);
		
	}
	
	
	@Test
	public void saveAnnulationConges() {
		when(mockMapper.map(dto, CongeEmploye.class)).thenReturn(entity);
		when(
				mockSituationDao.findByCodeSituationByCodeEntite(UtilConstants.ENTITE_CONGE_EMPLOYE_CODE,
						UtilConstants.SITUATION_ANNULEE_CODE)).thenReturn(situation);
		when(mockDao.save(entity)).thenReturn(entity);

		tested.saveAnnulationConge(dto);
		
		verify(mockMapper).map(dto, CongeEmploye.class);
		verify(mockSituationDao).findByCodeSituationByCodeEntite(UtilConstants.ENTITE_CONGE_EMPLOYE_CODE,
				UtilConstants.SITUATION_ANNULEE_CODE);
		verify(mockDao).save(entity);
		verify(mockSituationOccurrenceDao).persist(any(SituationEntiteOccurrence.class));
	}
	
	
}
