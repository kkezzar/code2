package dz.gov.mesrs.sii.grh.business.service.impl;

import org.dozer.Mapper;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;
import dz.gov.mesrs.sii.commons.data.dao.grh.PropostionAvancementDao;
import dz.gov.mesrs.sii.commons.data.model.grh.PropostionAvancement;
import dz.gov.mesrs.sii.grh.business.model.dto.PropostionAvancementDto;

public class TestPropostionAvancementServiceImpl {

	private PropostionAvancementServiceImpl tested;
	private PropostionAvancementDao mockPropostionAvancementDao;
	private Mapper mockMapper;
	private PropostionAvancement entity;
	private PropostionAvancementDto dto;

	@Before
	public void setUp() {
		tested = new PropostionAvancementServiceImpl();
		mockPropostionAvancementDao = mock(PropostionAvancementDao.class);
		mockMapper = mock(Mapper.class);
		tested.setMapper(mockMapper);
		tested.setPropostionAvancementDao(mockPropostionAvancementDao);
		entity = new PropostionAvancement();
		dto = new PropostionAvancementDto();
	}

	@Test
	public void maxDatepropositionAvancement() {
		tested.maxDatepropositionAvancement(1, 1, 1);
		verify(mockPropostionAvancementDao).maxDatepropositionAvancement(1, 1, 1);
	}
	
	@Test
	public void deleteAllEmployProposition(){
		when(mockMapper.map(dto, PropostionAvancement.class)).thenReturn(entity);
		
		tested.deleteAllEmployProposition(dto);
		
		verify(mockMapper).map(dto, PropostionAvancement.class);
		verify(mockPropostionAvancementDao).deleteAllEmployProposition(entity);
	}

}
