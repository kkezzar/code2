package dz.gov.mesrs.sii.grh.business.service.evaluation.impl;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;
import org.junit.Before;
import org.junit.Test;

import dz.gov.mesrs.sii.commons.data.dao.grh.EvaluationEmployeDao;
import dz.gov.mesrs.sii.commons.data.model.grh.DossierEmploye;
import dz.gov.mesrs.sii.commons.data.model.grh.EvaluationEmploye;
import dz.gov.mesrs.sii.grh.business.model.dto.DossierEmployeDto;
import dz.gov.mesrs.sii.grh.business.model.dto.EvaluationEmployeDto;

public class TestEvaluationEmployeServiceImpl {

	private EvaluationEmployeServiceImpl tested;
	private EvaluationEmployeDto dto;
	private EvaluationEmploye entity;
	private List<EvaluationEmployeDto> dtos;
	private List<EvaluationEmploye> entities;
	private EvaluationEmployeDao mockDao;
	private Mapper mockMapper;
	private DossierEmployeDto dossierEmployeDto;
	private DossierEmploye dossierEmployeEntity;

	@Before
	public void setUp() {
		tested = new EvaluationEmployeServiceImpl();
		mockDao = mock(EvaluationEmployeDao.class);
		mockMapper = mock(Mapper.class);
		tested.setMapper(mockMapper);
		tested.setEvaluationEmployeDao(mockDao);

		dto = new EvaluationEmployeDto();
		entity = new EvaluationEmploye();
		entities = new ArrayList<>();
		entities.add(entity);
		dtos.add(dto);

		dossierEmployeDto = new DossierEmployeDto();
		dossierEmployeEntity = new DossierEmploye();
	}

	@Test
	public void findAllEvaluationEmployeByPeriode() {
		when(mockMapper.map(dossierEmployeDto, DossierEmploye.class)).thenReturn(dossierEmployeEntity);
		
//		tested.findAllEvaluationEmployeByPeriode(evaluationPeriodeDto, dossierEmployeDto)
		
		
		verify(mockMapper).map(dossierEmployeDto, DossierEmploye.class);

	}
}
