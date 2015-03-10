package dz.gov.mesrs.sii.grh.business.service.referentiel.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.dozer.Mapper;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;
import dz.gov.mesrs.sii.grh.business.model.dto.CategorieProfessionnelleDto;
import dz.gov.mesrs.sii.grh.business.model.dto.GroupeDto;
import dz.gov.mesrs.sii.grh.business.service.referentiel.CategorieProfessionnelleService;
import dz.gov.mesrs.sii.grh.business.service.referentiel.impl.GroupeServiceImpl;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;

public class TestGroupeServiceImpl {

	private GroupeServiceImpl tested;
	private CategorieProfessionnelleService mockCategorieProfessionnelleService;
	private List<CategorieProfessionnelleDto> categorieDtos;

	@Before
	public void init() {
		tested = new GroupeServiceImpl();
		mockCategorieProfessionnelleService = mock(CategorieProfessionnelleService.class);
		tested.setCategorieProfessionnelleService(mockCategorieProfessionnelleService);
		initCategorieDtos();

	}

	@Test
	public void findAll() {
		when(mockCategorieProfessionnelleService.findAll()).thenReturn(categorieDtos);
		List<GroupeDto> results = tested.findAll();

		assertNotNull("La liste des groupes ne devrait pas etre null", results);
		assertNotNull("La liste des groupes devrait contenir deux groupes , elle en contient " + results.size(),results.size() == 2);
		assertTrue("Le premier groupe ne devrait pas avoir une elist de categorie vide", !results.get(0).getCategorieProfessionnelleDtos().isEmpty());
		assertTrue("Le premier groupe devrait pas avoir une categorie", results.get(0).getCategorieProfessionnelleDtos().size()==1);
		assertTrue("Le premier groupe ne devrait pas avoir une list de categorie de type hors categorie vide", !results.get(0).getHorsCategorieProfessionnelleDtos().isEmpty());
		assertTrue("Le premier groupe devrait pas avoir une categorie de type hors categorie", results.get(0).getHorsCategorieProfessionnelleDtos().size()==1);
		assertTrue("Le deuxieme groupe ne devrait pas avoir une list de categorie vide", !results.get(1).getCategorieProfessionnelleDtos().isEmpty());
		assertTrue("Le deuxieme groupe devrait pas avoir une categorie", results.get(1).getCategorieProfessionnelleDtos().size()==1);
		assertTrue("Le deuxieme groupe ne devrait pas avoir une list de categorie ", results.get(1).getHorsCategorieProfessionnelleDtos().isEmpty());
	}

	private void initCategorieDtos() {
		categorieDtos = new ArrayList<>();
		CategorieProfessionnelleDto dto11 = new CategorieProfessionnelleDto();
		CategorieProfessionnelleDto dto12 = new CategorieProfessionnelleDto();
		CategorieProfessionnelleDto dto2 = new CategorieProfessionnelleDto();
		categorieDtos.add(dto11);
		categorieDtos.add(dto12);
		categorieDtos.add(dto2);

		NomenclatureDto groupe1 = new NomenclatureDto();
		NomenclatureDto groupe2 = new NomenclatureDto();

		dto11.setNomenclatureByGroupeDto(groupe1);
		dto12.setNomenclatureByGroupeDto(groupe1);
		dto2.setNomenclatureByGroupeDto(groupe2);

		dto12.setHorsCategorie(true);

	}

}
