package dz.gov.mesrs.sii.grh.business.service.referentiel.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;
import org.junit.Before;
import org.junit.Test;

import dz.gov.mesrs.sii.commons.data.dao.grh.FiliereDao;
import dz.gov.mesrs.sii.commons.data.model.grh.Filiere;
import dz.gov.mesrs.sii.grh.business.model.dto.FiliereDto;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class TestFiliereServiceImpl {
	
	private FiliereServiceImpl tested;
	private Mapper mockMapper;
	private FiliereDao mockDao;
	private Filiere entity;
	private FiliereDto dto;
	private List<Filiere> entities;
	private List<FiliereDto> dtos;

	@Before
	public void setUp() throws Exception {
		tested =new FiliereServiceImpl();
		mockDao = mock(FiliereDao.class);
		mockMapper = mock(Mapper.class);
		
		tested.setFiliereDao(mockDao);
		tested.setMapper(mockMapper);
		
		entity = new Filiere();
		dto = new FiliereDto();
		
		entities = new ArrayList<>();
		entities.add(entity);
		dtos = new ArrayList<>();
		dtos.add(dto);
		
	}

	@Test
	public void removeFiliereDto() {
		when(mockMapper.map(dto, Filiere.class)).thenReturn(entity);
		when(mockDao.merge(entity)).thenReturn(entity);
		
		tested.remove(dto);
		
		verify(mockMapper).map(dto, Filiere.class);
		verify(mockDao).merge(entity);
		verify(mockDao).remove(entity);
	}

	@Test
	public void findById() {
		when(mockDao.findById(10)).thenReturn(entity);
		when(mockMapper.map(entity, FiliereDto.class)).thenReturn(dto);
		
		FiliereDto result = tested.findById(10);
		
		verify(mockDao).findById(10);
		verify(mockMapper).map(entity, FiliereDto.class);
		assertEquals("Le resultat n'est pas correcte",dto, result);
	}
	
	@Test
	public void findByIdNullResult() {
		when(mockDao.findById(10)).thenReturn(null);
		
		FiliereDto result = tested.findById(10);
		
		verify(mockDao).findById(10);
		assertNull("Le resultat devrait etre null", result);
	}

	@Test
	public void findAll() {
		when(mockDao.findAll()).thenReturn(entities);
		
		List<FiliereDto>  results = tested.findAll();
		
		verify(mockDao).findAll();
		assertNotNull("Le retour de la methode est incorrecte",results);
	}

	@Test
	public void findFiliereByIdRubrique() {
		when(mockDao.findFiliereByIdRubrique(1)).thenReturn(entities);
		
		List<FiliereDto>  results  = tested.findFiliereByIdRubrique(1);
		
		verify(mockDao).findFiliereByIdRubrique(1);
	}

	@Test
	public void insert() {
		when(mockMapper.map(dto, Filiere.class)).thenReturn(entity);
		entity.setId(0);
		
		FiliereDto result =   tested.insertOrUpdate(dto);
		
		verify(mockMapper).map(dto, Filiere.class);
		verify(mockMapper).map(entity, dto);
		verify(mockDao).persist(entity);
		assertNotNull("Le resultat ne devrait pas etre null",result);
	}
	
	@Test
	public void update() {
		when(mockMapper.map(dto, Filiere.class)).thenReturn(entity);
		entity.setId(1);
		when(mockDao.merge(entity)).thenReturn(entity);
		
		FiliereDto result =   tested.insertOrUpdate(dto);
		
		verify(mockMapper).map(dto, Filiere.class);
		verify(mockMapper).map(entity, dto);
		verify(mockDao).merge(entity);
		assertNotNull("Le resultat ne devrait pas etre null",result);	}

}
