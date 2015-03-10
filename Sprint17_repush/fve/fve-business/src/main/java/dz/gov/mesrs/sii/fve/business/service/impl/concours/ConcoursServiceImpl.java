package dz.gov.mesrs.sii.fve.business.service.impl.concours;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import dz.gov.mesrs.sii.commons.data.dao.bpm.SituationEntiteDao;
import dz.gov.mesrs.sii.commons.data.dao.bpm.SituationEntiteOccurrenceDao;
import dz.gov.mesrs.sii.commons.data.dao.fve.concours.ConcoursDao;
import dz.gov.mesrs.sii.commons.data.dao.fve.offreformation.AnneeAcademiqueDao;
import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefEtablissementDao;
import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefGroupeDao;
import dz.gov.mesrs.sii.commons.data.model.bpm.SituationEntite;
import dz.gov.mesrs.sii.commons.data.model.bpm.SituationEntiteOccurrence;
import dz.gov.mesrs.sii.commons.data.model.fve.concours.Concours;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.AnneeAcademique;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefAffectation;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefEtablissement;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefGroupe;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefIndividu;
import dz.gov.mesrs.sii.commons.data.util.UtilConstant;
import dz.gov.mesrs.sii.fve.business.model.dto.concours.ConcoursDto;
import dz.gov.mesrs.sii.fve.business.service.concours.ConcoursService;
//import dz.gov.mesrs.sii.fve.business.util.UtilConstant;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefIndividuDto;

@Transactional
@Service
public class ConcoursServiceImpl implements ConcoursService {

    private final static Logger logger = LoggerFactory.getLogger(ConcoursServiceImpl.class.getName());

    @Autowired
    private ConcoursDao dao;

    @Autowired
    private Mapper mapper;

    @Autowired
    private SituationEntiteDao situationEntiteDao;

    @Autowired
    private SituationEntiteOccurrenceDao situationEntiteOccurrenceDao;

    @Autowired
    private RefEtablissementDao etablissementDao;

    @Autowired
    private AnneeAcademiqueDao anneeAcademiqueDao;

    @Autowired
    private RefGroupeDao groupeDao;

    @Override
    public ConcoursDto save(ConcoursDto dto) {
	Assert.notNull(dto, "Can't persist a null element");
	try {
	    Concours concours = mapper.map(dto, Concours.class);
	    defineSitutation(concours);
	    if (concours.getId() == null) {
		logger.debug("Persisting {}", new Object[] { concours });
		genererIdentifiant(concours);
		dao.persist(concours);
	    } else {
		logger.debug("Updating {}", new Object[] { concours });
		concours = dao.merge(concours);
	    }
	    logSituation(concours);
	    return mapper.map(concours, ConcoursDto.class);
	} catch (Exception e) {
	    logger.error("An exception occured while saving concours " + dto, e);
	    throw e;
	}

    }

    @Override
    public ConcoursDto validate(ConcoursDto dto) {
	Assert.notNull(dto, "Can't validate a null element");
	try {
	    Concours concours = mapper.map(dto, Concours.class);
	    logger.debug("Validating {}", new Object[] { concours });
	    SituationEntite situationEntite = situationEntiteDao.findByCodeSituationByCodeEntite(
		    UtilConstant.ENTITE_CONCOURS, UtilConstant.SITUTAION_VALIDEE_CODE);
	    concours.setSituation(situationEntite);
	    concours = dao.merge(concours);
	    logSituation(concours);
	    return mapper.map(concours, ConcoursDto.class);
	} catch (Exception e) {
	    logger.error("An exception occured while validating concours " + dto, e);
	    throw e;
	}
    }

    private void genererIdentifiant(Concours concours) {
	RefEtablissement etablissement = etablissementDao.findById(concours.getEtablissement().getId());
	String identifiantConcours = etablissement.getIdentifiant();
	AnneeAcademique anneeAcademique = anneeAcademiqueDao.findById(concours.getAnneeAcademique().getId());
	identifiantConcours += anneeAcademique.getDeuxiemeAnnee();
	identifiantConcours += dao.getNextConcoursIdentifiantSeq();
	concours.setIdentifiant(identifiantConcours);

    }

    @Override
    public ConcoursDto findById(Long idConcours) {
	Concours concours = dao.findById(idConcours);
	if (concours != null) {
	    return mapper.map(concours, ConcoursDto.class);
	}
	return null;

    }

    @Override
    public ConcoursDto find(ConcoursDto dto) {
	Assert.notNull(dto, "Can't search for a null element");
	logger.debug("Searching for {} ", new Object[] { dto });
	Concours concours = mapper.map(dto, Concours.class);
	concours = dao.findUniqueOrNoneByExample(concours);
	logger.debug("Found {} ", new Object[] { concours });
	return mapper.map(concours, ConcoursDto.class);
    }

    @Override
    public List<ConcoursDto> findAll(ConcoursDto dto) {
	Assert.notNull(dto, "Can't search for a null element");
	logger.debug("Searching for {} ", new Object[] { dto });
	List<ConcoursDto> dtos = null;
	Concours concours = mapper.map(dto, Concours.class);
	List<Concours> results = dao.findAllByExample(concours);
	if (results != null && results.size() > 0) {
	    logger.debug("Found {} results", new Object[] { results.size() });
	    dtos = new ArrayList<>();
	    for (Concours result : results) {
		dtos.add(mapper.map(result, ConcoursDto.class));
	    }
	} else {
	    logger.debug("No result found");
	}

	return dtos;
    }

    @Override
    public void delete(ConcoursDto dto) {
	Assert.notNull(dto, "Can't delete a null element");
	logger.debug("Deleting {} ", new Object[] { dto });
	Concours concours = new Concours();
	concours.setId(dto.getId());
	concours = dao.findUniqueOrNoneByExample(concours);
	dao.delete(concours);
	logger.debug("{} Deleted ", new Object[] { dto });

    }

    @Override
    public List<RefIndividuDto> findResponsableExamens(int etablissementId) {
	RefGroupe groupe = new RefGroupe();
	List<RefIndividuDto> results = null;
	// RefEtablissement etablissement = new RefEtablissement();
	// etablissement.setId(etablissementId);
	// groupe.setRefEtablissement(etablissement);
	groupe.setLcGroupe(UtilConstant.GROUPE_RESPONSABLE_EXAMEN_NAME);
	List<RefGroupe> groupes = groupeDao.findAdvanced(etablissementId, groupe);
	if (groupes != null && groupes.size() > 1) {
	    throw new IllegalStateException("Il y'a trop de groupe responsable examen : " + groupes.size());
	}
	if (groupe != null) {
	    results = new ArrayList<>();
	    if (groupes != null && groupes.size() > 0) { // added by Rafik
	    for (RefAffectation affectation : groupes.get(0).getRefAffectationsForGroupe()) {
		RefIndividu individu = affectation.getRefIndividu();
		if (individu != null) {
		    RefIndividuDto dto = mapper.map(individu, RefIndividuDto.class);
		    results.add(dto);
		}
	    }
	    }
	}
	return results;
    }

    private void defineSitutation(Concours concours) {
	SituationEntite situationEntite = null;
	if (concours.getId() == null) {
	    situationEntite = situationEntiteDao.findByCodeSituationByCodeEntite(UtilConstant.ENTITE_CONCOURS,
		    UtilConstant.SITUTAION_CREEE_CODE);
	} else if (concours.getDatePublicationConcours() != null) {
	    situationEntite = situationEntiteDao.findByCodeSituationByCodeEntite(UtilConstant.ENTITE_CONCOURS,
		    UtilConstant.SITUTAION_PUBLIEE_CODE);
	}

	else {
	    situationEntite = situationEntiteDao.findByCodeSituationByCodeEntite(UtilConstant.ENTITE_CONCOURS,
		    UtilConstant.SITUTAION_ENREGISTREE_CODE);
	}
	concours.setSituation(situationEntite);
    }

    private void logSituation(Concours concours) {
	SituationEntiteOccurrence entiteOccurrence = new SituationEntiteOccurrence();
	entiteOccurrence.setDateSituation(new Date());
	entiteOccurrence.setIdOccurrence(concours.getId().intValue());
	entiteOccurrence.setSituationEntite(concours.getSituation());
	situationEntiteOccurrenceDao.persist(entiteOccurrence);

    }

    public void setDao(ConcoursDao dao) {
	this.dao = dao;
    }

    public void setMapper(Mapper mapper) {
	this.mapper = mapper;
    }

    public void setSituationEntiteDao(SituationEntiteDao situationEntiteDao) {
	this.situationEntiteDao = situationEntiteDao;
    }

    public void setSituationEntiteOccurrenceDao(SituationEntiteOccurrenceDao situationEntiteOccurrenceDao) {
	this.situationEntiteOccurrenceDao = situationEntiteOccurrenceDao;
    }

    public void setGroupeDao(RefGroupeDao groupeDao) {
	this.groupeDao = groupeDao;
    }

}
