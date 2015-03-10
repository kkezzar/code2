package dz.gov.mesrs.sii.fve.business.service.impl.concours;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.dozer.Mapper;
import org.junit.Before;
import org.junit.Test;

import dz.gov.mesrs.sii.commons.data.dao.fve.concours.DossierCandidatDao;
import dz.gov.mesrs.sii.commons.data.dao.fve.concours.EtablissementAdmissionDao;
import dz.gov.mesrs.sii.commons.data.model.fve.concours.DossierCandidat;
import dz.gov.mesrs.sii.commons.data.model.fve.concours.EtablissementAdmission;
import dz.gov.mesrs.sii.fve.business.model.dto.concours.DossierCandidatDto;
import dz.gov.mesrs.sii.fve.business.model.dto.concours.EtablissementAdmissionDto;
import dz.gov.mesrs.sii.fve.business.service.concours.NombreAdmisAtteintException;

public class TestAdmissionServiceImpl {

    private AdmissionServiceImpl tested;
    private Mapper mapperMock;
    private EtablissementAdmissionDao etablissementAdmissionDaoMock;
    private DossierCandidatDao dossierCandidatDaoMock;
    private DossierCandidatDto candidatDto;
    private EtablissementAdmissionDto etablissementAdmissionDto;
    private DossierCandidat candidat;
    private EtablissementAdmission etablissementAdmission;

    @Before
    public void setUp() {
	tested = new AdmissionServiceImpl();

	mapperMock = mock(Mapper.class);
	etablissementAdmissionDaoMock = mock(EtablissementAdmissionDao.class);
	dossierCandidatDaoMock = mock(DossierCandidatDao.class);
	tested.setMapper(mapperMock);
	tested.setDossierCandidatDao(dossierCandidatDaoMock);
	tested.setEtablissementAdmissionDao(etablissementAdmissionDaoMock);

	candidatDto = new DossierCandidatDto();
	etablissementAdmissionDto = new EtablissementAdmissionDto();
	etablissementAdmission = new EtablissementAdmission();
	etablissementAdmission.setNombreAdmettre(20);
	candidat = new DossierCandidat();
    }

    @Test
    public void countNombreAdmis() {
	when(mapperMock.map(etablissementAdmissionDto, EtablissementAdmission.class))
		.thenReturn(etablissementAdmission);
	when(etablissementAdmissionDaoMock.countNombreAdmis(etablissementAdmission)).thenReturn(20);

	tested.countNombreAdmis(etablissementAdmissionDto);

	assertEquals("Le nombre d'admis n'a pas ete correctement recuperer",
		etablissementAdmissionDto.getNombreAdmis(), new Integer(20));

    }

    @Test
    public void admettreCandidatOK() throws Exception {
	when(mapperMock.map(candidatDto.getEtablissementAdmissionDto(), EtablissementAdmission.class)).thenReturn(
		etablissementAdmission);
	when(etablissementAdmissionDaoMock.countNombreAdmis(etablissementAdmission)).thenReturn(19);
	when(mapperMock.map(candidatDto, DossierCandidat.class)).thenReturn(candidat);

	tested.admettreCandidat(candidatDto);

	verify(dossierCandidatDaoMock).merge(candidat);

    }

    @Test(expected = NombreAdmisAtteintException.class)
    public void admettreCandidatNombreAdmisAtteint() throws Exception {
	when(mapperMock.map(candidatDto.getEtablissementAdmissionDto(), EtablissementAdmission.class)).thenReturn(
		etablissementAdmission);
	when(etablissementAdmissionDaoMock.countNombreAdmis(etablissementAdmission)).thenReturn(20);

	tested.admettreCandidat(candidatDto);
    }

}
