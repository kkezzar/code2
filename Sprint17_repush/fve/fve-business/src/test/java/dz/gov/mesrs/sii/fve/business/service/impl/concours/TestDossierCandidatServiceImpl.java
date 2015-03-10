package dz.gov.mesrs.sii.fve.business.service.impl.concours;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.dozer.Mapper;
import org.junit.Before;
import org.junit.Test;

import dz.gov.mesrs.sii.commons.data.dao.fve.concours.DossierCandidatDao;
import dz.gov.mesrs.sii.commons.data.model.fve.concours.DossierCandidat;
import dz.gov.mesrs.sii.fve.business.model.dto.concours.ConcoursDto;
import dz.gov.mesrs.sii.fve.business.model.dto.concours.DossierCandidatDto;
import dz.gov.mesrs.sii.fve.business.model.dto.concours.ExamenConcoursDto;
import dz.gov.mesrs.sii.fve.business.model.dto.concours.ResultatExamenDto;

public class TestDossierCandidatServiceImpl {

    private DossierCandidatServiceImpl tested;
    private DossierCandidatDao dossierCandidatDaoMock;
    private Mapper mapperMock;
    private DossierCandidatDto dto;
    private DossierCandidat entity;
    private ConcoursDto concoursDto;
    private ExamenConcoursDto examen1;
    private ExamenConcoursDto examen2;

    @Before
    public void setUp() {
	tested = new DossierCandidatServiceImpl();
	dossierCandidatDaoMock = mock(DossierCandidatDao.class);
	mapperMock = mock(Mapper.class);
	tested.setDao(dossierCandidatDaoMock);
	tested.setMapper(mapperMock);
	dto = new DossierCandidatDto();
	entity = new DossierCandidat();
	concoursDto = new ConcoursDto();
	examen1 = new ExamenConcoursDto();
	examen1.setCoefficient(2.0);
	examen1.setNoteBase(20);
	concoursDto.addExamenConcoursDto(examen1);
	examen2 = new ExamenConcoursDto();
	examen2.setCoefficient(3.0);
	examen2.setNoteBase(30);
	concoursDto.addExamenConcoursDto(examen2);
	concoursDto.setNoteBase(20);

    }

    @Test
    public void save() {
	entity.setId(null);
	when(mapperMock.map(dto, DossierCandidat.class)).thenReturn(entity);
	when(mapperMock.map(entity, DossierCandidatDto.class)).thenReturn(dto);

	dto = tested.save(dto);

	assertNotNull("The result shouldn't be null", dto);
	verify(dossierCandidatDaoMock).persist(entity);

    }

    @Test
    public void calculMoyenne() {
	ResultatExamenDto res1 = new ResultatExamenDto(examen1);
	res1.setNote(10.5);
	dto.addResultatExamenDto(res1);

	tested.calculMoyenne(concoursDto, dto);

	assertNotNull("le dto ne devrait pas etre null ", dto);
	assertNotNull("La moyenne ne devrait pas etre null ", dto.getMoyenneObtenue());
	assertEquals("La moyenne a mal ete calculee", new Double(4.2), dto.getMoyenneObtenue());
    }
    
    @Test
    public void calculMoyenneZero() {
	ResultatExamenDto res1 = new ResultatExamenDto(examen1);
	res1.setNote(0.0);
	dto.addResultatExamenDto(res1);

	tested.calculMoyenne(concoursDto, dto);

	assertNotNull("le dto ne devrait pas etre null ", dto);
	assertNotNull("La moyenne ne devrait pas etre null ", dto.getMoyenneObtenue());
	assertEquals("La moyenne a mal ete calculee", new Double(0.0), dto.getMoyenneObtenue());
    }

}
