package dz.gov.mesrs.sii.fve.business.service.impl.concours;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.fve.concours.DossierCandidatDao;
import dz.gov.mesrs.sii.commons.data.dao.fve.concours.EtablissementAdmissionDao;
import dz.gov.mesrs.sii.commons.data.model.fve.concours.DossierCandidat;
import dz.gov.mesrs.sii.commons.data.model.fve.concours.EtablissementAdmission;
import dz.gov.mesrs.sii.fve.business.model.dto.concours.DossierCandidatDto;
import dz.gov.mesrs.sii.fve.business.model.dto.concours.EtablissementAdmissionDto;
import dz.gov.mesrs.sii.fve.business.service.concours.AdmissionService;
import dz.gov.mesrs.sii.fve.business.service.concours.NombreAdmisAtteintException;

@Service
@Transactional
public class AdmissionServiceImpl implements AdmissionService {
    private final static Logger logger = LoggerFactory.getLogger(AdmissionServiceImpl.class.getName());

    @Autowired
    private Mapper mapper;
    @Autowired
    private EtablissementAdmissionDao etablissementAdmissionDao;
    @Autowired
    private DossierCandidatDao dossierCandidatDao;

    @Override
    public void countNombreAdmis(EtablissementAdmissionDto dto) {
	EtablissementAdmission etablissement = mapper.map(dto, EtablissementAdmission.class);
	Integer nbAdmis = etablissementAdmissionDao.countNombreAdmis(etablissement);
	dto.setNombreAdmis(nbAdmis);
    }

    @Override
    public void admettreCandidat(DossierCandidatDto dto) throws NombreAdmisAtteintException {
	EtablissementAdmission etablissement = mapper.map(dto.getEtablissementAdmissionDto(),
		EtablissementAdmission.class);
	Integer nbAdmis = etablissementAdmissionDao.countNombreAdmis(etablissement);
	if (etablissement.getNombreAdmettre().equals(nbAdmis)) {
	    logger.debug("Le candidat {} ne peut etre inscrit car le nombre d'admis {} a ete atteint", new Object[] {
		    dto, nbAdmis });
	    throw new NombreAdmisAtteintException();
	} else {
	    DossierCandidat candidat = mapper.map(dto, DossierCandidat.class);
	    candidat.setAdmis(true);
	    dossierCandidatDao.merge(candidat);
	    logger.debug("Le candidat {} a ete admis", new Object[] { dto });
	}

    }

    public void setMapper(Mapper mapper) {
	this.mapper = mapper;
    }

    public void setEtablissementAdmissionDao(EtablissementAdmissionDao etablissementAdmissionDao) {
	this.etablissementAdmissionDao = etablissementAdmissionDao;
    }

    public void setDossierCandidatDao(DossierCandidatDao dossierCandidatDao) {
	this.dossierCandidatDao = dossierCandidatDao;
    }

}
