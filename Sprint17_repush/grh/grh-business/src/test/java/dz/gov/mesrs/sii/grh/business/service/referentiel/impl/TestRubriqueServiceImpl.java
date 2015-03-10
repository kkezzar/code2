package dz.gov.mesrs.sii.grh.business.service.referentiel.impl;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dozer.Mapper;
import org.junit.Before;
import org.junit.Test;

import dz.gov.mesrs.sii.commons.data.dao.grh.RubriqueDao;
import dz.gov.mesrs.sii.commons.data.model.grh.Rubrique;
import dz.gov.mesrs.sii.grh.business.model.dto.RubriqueDto;
import static org.mockito.Mockito.*;


public class TestRubriqueServiceImpl {
	
	private RubriqueServiceImpl tested;
	private RubriqueDao mockDao;
	private Mapper mockMapper;
	private RubriqueDto dto;
	private Rubrique entity;
	private List<RubriqueDto> dtos;
	private List<Rubrique> entities;
	
	
	@Before
	public void setUp(){
		tested = new RubriqueServiceImpl();
		mockDao = mock(RubriqueDao.class);
		mockMapper = mock(Mapper.class);
		tested.setMapper(mockMapper);
		tested.setRubriqueDao(mockDao);
		dto = new RubriqueDto();
		entity = new Rubrique();
		dtos = new ArrayList<RubriqueDto>();
		dtos.add(dto);
		entities= new ArrayList<>();
		entities.add(entity);
		
	}
	

	@Test
	public void remove(){
		when(mockMapper.map(dto, Rubrique.class)).thenReturn(entity);
		when(mockDao.merge(entity)).thenReturn(entity);
		
		tested.remove(dto);
		
		verify(mockMapper).map(dto, Rubrique.class);
		verify(mockDao).merge(entity);
		verify(mockDao).remove(entity);
		
	}
	
	@Test
	public void findAll(){
		when(mockDao.findAll()).thenReturn(entities);
		
		List<RubriqueDto> results  = tested.findAll();
		
		verify(mockDao).findAll();
		verify(mockMapper).map(any(), any());
		assertNotNull("Le resultat de la methode n'est pas correcte", results);
		
	}
	
	
	
}
