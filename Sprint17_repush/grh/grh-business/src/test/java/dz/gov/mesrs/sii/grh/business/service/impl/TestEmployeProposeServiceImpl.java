package dz.gov.mesrs.sii.grh.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;
import org.junit.Before;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;
import dz.gov.mesrs.sii.commons.data.dao.grh.EmployeProposeDao;
import dz.gov.mesrs.sii.commons.data.model.grh.DossierEmploye;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefEtablissement;
import dz.gov.mesrs.sii.grh.business.model.dto.DossierEmployeDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEtablissementDto;

public class TestEmployeProposeServiceImpl {
	
	private EmployeProposeServiceImpl tested;
	private EmployeProposeDao mockDao;
	private Mapper mockMapper;
	private DossierEmployeDto dto;
	private DossierEmploye entity;
	private List<DossierEmployeDto> dtos;
	private List<DossierEmploye> entities;
	
	@Before
	public void setUp(){
		tested = new EmployeProposeServiceImpl();
		mockDao = mock(EmployeProposeDao.class);
		mockMapper = mock(Mapper.class);
		tested.setEmployeProposeDao(mockDao);
		tested.setMapper(mockMapper);
		dto =new DossierEmployeDto();
		entity = new DossierEmploye();
		entities = new ArrayList<>();
		dtos = new ArrayList<>();
		entities.add(entity);
		dtos.add(dto);
	}
	
	
	@Test
	public void findlistEmployesDernierAvancementPromotionParList(){
		RefEtablissementDto etablissementDto = new RefEtablissementDto();
		RefEtablissement etablissement = new RefEtablissement();
		when(mockMapper.map(etablissementDto, RefEtablissement.class)).thenReturn(etablissement);
		when(mockDao.findlistEmployesDernierAvancementPromotionParList(etablissement)).thenReturn(entities);
		when(mockMapper.map(entity, DossierEmployeDto.class)).thenReturn(dto);
		
		List<DossierEmployeDto> results  = tested.findlistEmployesDernierAvancementPromotionParList(etablissementDto);
		
		verify(mockMapper).map(etablissementDto, RefEtablissement.class);
		verify(mockDao).findlistEmployesDernierAvancementPromotionParList(etablissement);
		verify(mockMapper).map(entity, DossierEmployeDto.class);
		assertEquals("Le resultat de la methode n'est pas correct",dtos, results);
		
	}
	

}
