package dz.gov.mesrs.sii.grh.business.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;

import org.dozer.Mapper;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import dz.gov.mesrs.sii.commons.data.dao.grh.CarriereDao;
import dz.gov.mesrs.sii.commons.data.model.grh.EmployePropose;
import dz.gov.mesrs.sii.commons.data.model.grh.PropostionAvancement;
import dz.gov.mesrs.sii.commons.data.model.referentiel.Nomenclature;
import dz.gov.mesrs.sii.grh.business.model.dto.EmployeProposeDto;
import dz.gov.mesrs.sii.grh.business.model.dto.PropostionAvancementDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;

public class TestCarriereServiceImpl {
	
	private CarriereServiceImpl tested;
	private CarriereDao mockCarriereDao;
	private Mapper mockMapper;
	private Nomenclature nomenclature;
	private NomenclatureDto nomenclatureDto;
	private EmployePropose employePropose;
	private EmployeProposeDto employeProposeDto;
	private List<EmployePropose> employeProposes;
	private List<EmployeProposeDto> employeProposeDtos;
	private PropostionAvancement propostionAvancement;
	private PropostionAvancementDto propostionAvancementDto;
	
	
	@Before
	public void setUp(){
		tested = new CarriereServiceImpl();
		mockCarriereDao  = mock(CarriereDao.class);
		mockMapper = mock(Mapper.class);

		tested.setCarriereDao(mockCarriereDao);
		tested.setMapper(mockMapper);
		
		nomenclature = new Nomenclature();
		nomenclatureDto = new NomenclatureDto();
		employePropose = new EmployePropose();
		employeProposeDto = new EmployeProposeDto();
		employeProposes = new ArrayList<EmployePropose>();
		employeProposes.add(employePropose);
		employeProposeDtos =new ArrayList<EmployeProposeDto>();
		employeProposeDtos.add(employeProposeDto);
		propostionAvancement = new PropostionAvancement();
		propostionAvancementDto = new PropostionAvancementDto();
		
	}
	
	
	@Test
	public void updateCarrierePromotion(){
		when(mockMapper.map(employeProposeDto, EmployePropose.class)).thenReturn(employePropose);
		when(mockMapper.map(nomenclatureDto, Nomenclature.class)).thenReturn(nomenclature);

		tested.updateCarrierePromotion(employeProposeDto, nomenclatureDto);;
		
		verify(mockMapper).map(employeProposeDto, EmployePropose.class);
		verify(mockMapper).map(nomenclatureDto, Nomenclature.class);
		verify(mockCarriereDao).updateCarrierePromotion(employePropose, nomenclature);
	}
	
	
	@Test
	public void agentProposableListAptitude(){
		List<Long> listIDEmployer = new ArrayList<>();
		when(mockMapper.map(propostionAvancementDto, PropostionAvancement.class)).thenReturn(propostionAvancement);
		when(mockCarriereDao.agentProposableListAptitude(listIDEmployer, propostionAvancement)).thenReturn(employeProposes);
		when(mockMapper.map(employePropose, EmployeProposeDto.class)).thenReturn(employeProposeDto);
		
		List<EmployeProposeDto> results = tested.agentProposableListAptitude(listIDEmployer, propostionAvancementDto);
		
		verify(mockMapper).map(propostionAvancementDto, PropostionAvancement.class);
		verify(mockCarriereDao).agentProposableListAptitude(listIDEmployer, propostionAvancement);
		verify(mockMapper).map(employePropose, EmployeProposeDto.class);
		assertEquals("Le resultat de la methode n'est pas correcte",employeProposeDtos, results);
	}
	
	@Test
	public void agentProposableEchelon(){
		Date dateEffetProposee = new Date();
		List<Integer> listNomenclatureActifDetachementID = new ArrayList<>();
		when(mockMapper.map(propostionAvancementDto, PropostionAvancement.class)).thenReturn(propostionAvancement);
		when(mockCarriereDao.agentProposableEchelon(1, 2, 3, dateEffetProposee, propostionAvancement, 4, listNomenclatureActifDetachementID)).thenReturn(employeProposes);
		when(mockMapper.map(employePropose, EmployeProposeDto.class)).thenReturn(employeProposeDto);
		
		List<EmployeProposeDto> results = tested.agentProposableEchelon(1, 2, 3, dateEffetProposee, propostionAvancementDto, 4, listNomenclatureActifDetachementID);
		
		verify(mockMapper).map(propostionAvancementDto, PropostionAvancement.class);
		verify(mockCarriereDao).agentProposableEchelon(1, 2, 3, dateEffetProposee, propostionAvancement, 4, listNomenclatureActifDetachementID);
		verify(mockMapper).map(employePropose, EmployeProposeDto.class);
		assertEquals("Le resultat de la methode n'est pas correcte",employeProposeDtos, results);
	}
	

}
