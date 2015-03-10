package dz.gov.mesrs.sii.grh.business.service.referentiel.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.dozer.Mapper;
import org.junit.Before;
import org.junit.Test;

import dz.gov.mesrs.sii.commons.data.dao.grh.StatutDao;
import dz.gov.mesrs.sii.commons.data.model.grh.Statut;
import dz.gov.mesrs.sii.grh.business.model.dto.StatutDto;
import dz.gov.mesrs.sii.grh.business.service.referentiel.impl.StatutServiceImpl;

public class TestStatutServiceImpl {

	private StatutServiceImpl tested;
	private StatutDao mockStatutDao;
	private Mapper mockMapper;
	private Calendar currentDate;

	@Before
	public void init() {
		tested = new StatutServiceImpl();
		mockStatutDao = mock(StatutDao.class);
		mockMapper = mock(Mapper.class);
		tested.setStatutDao(mockStatutDao);
		tested.setMapper(mockMapper);
		currentDate = new GregorianCalendar();
	}

	@Test
	public void getListValideStatuts() {
		List<StatutDto> results = null;
		List<Statut> entities = new ArrayList<>();

		Statut valid = new Statut();
		Calendar calendar = new GregorianCalendar();
		calendar.add(Calendar.MONTH, -2);
		valid.setDateEffet(calendar.getTime());
		calendar.add(Calendar.MONTH, +2);
		valid.setDateFinValidation(calendar.getTime());

		Statut old = new Statut();
		calendar = new GregorianCalendar();
		calendar.add(Calendar.MONTH, -12);
		old.setDateEffet(calendar.getTime());
		calendar.add(Calendar.MONTH, -2);
		old.setDateFinValidation(calendar.getTime());

		entities.add(valid);
		entities.add(old);

		when(mockStatutDao.findAll()).thenReturn(entities);

		results = tested.getListValideStatuts();

		assertNotNull("La liste devrait contenir le statut valid mais elle est null", results);
		assertTrue("La Liste ne devrait contenir que le statut valid mais elle contient " + results.size(),
				results.size() == 1);
		verify(mockStatutDao).findAll();
		verify(mockMapper).map(eq(valid), any(StatutDto.class));

	}

}
