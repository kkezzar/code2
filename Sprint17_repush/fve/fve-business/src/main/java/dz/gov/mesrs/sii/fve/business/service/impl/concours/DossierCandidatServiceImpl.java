package dz.gov.mesrs.sii.fve.business.service.impl.concours;

import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import dz.gov.mesrs.sii.commons.data.dao.fve.concours.DossierCandidatDao;
import dz.gov.mesrs.sii.commons.data.model.fve.concours.Concours;
import dz.gov.mesrs.sii.commons.data.model.fve.concours.DossierCandidat;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefAdresse;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefAdresseElectronique;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefCoordonnee;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefIndividu;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefTelephone;
import dz.gov.mesrs.sii.fve.business.model.dto.concours.ConcoursDto;
import dz.gov.mesrs.sii.fve.business.model.dto.concours.DossierCandidatDto;
import dz.gov.mesrs.sii.fve.business.model.dto.concours.ExamenConcoursDto;
import dz.gov.mesrs.sii.fve.business.model.dto.concours.ResultatExamenDto;
import dz.gov.mesrs.sii.fve.business.service.concours.DossierCandidatService;

@Transactional
@Service
public class DossierCandidatServiceImpl implements DossierCandidatService {

    private final static Logger logger = LoggerFactory.getLogger(DossierCandidatServiceImpl.class.getName());

    @Autowired
    private Mapper mapper;

    @Autowired
    private DossierCandidatDao dao;

    @Override
    @Transactional(readOnly = true)
    public DossierCandidatDto find(DossierCandidatDto dto) {
	Assert.notNull(dto, "Can't search for a null dto");
	logger.debug("Searching for {}", new Object[] { dto });
	DossierCandidat dossierCandidat = mapper.map(dto, DossierCandidat.class);
	dossierCandidat = dao.findUniqueOrNoneByExample(dossierCandidat);
	logger.debug("found {}", new Object[] { dossierCandidat });
	if (dossierCandidat == null) {
	    return null;
	} else {
	    DossierCandidatDto result = mapper.map(dossierCandidat, DossierCandidatDto.class);
	    mapAdresse(result, dossierCandidat);
	    return result;
	}

    }

    @Override
    public DossierCandidatDto save(DossierCandidatDto dto) {
	Assert.notNull(dto, "Can't persist a null dto");
	DossierCandidat dossierCandidat = mapper.map(dto, DossierCandidat.class);
	if (dto.getId() == null) {
	    dao.persist(dossierCandidat);
	    logger.debug("DossierCandidate {} successfully saved", new Object[] { dossierCandidat });
	} else {
	    dao.merge(dossierCandidat);
	    logger.debug("DossierCandidate {} successfully updated", new Object[] { dossierCandidat });
	}
	return mapper.map(dossierCandidat, DossierCandidatDto.class);
    }

    @Override
    public void delete(DossierCandidatDto dto) {
	Assert.notNull(dto, "Can't delete a null dto");
	logger.debug("deleting {}", new Object[] { dto });
	DossierCandidat dossierCandidat = mapper.map(dto, DossierCandidat.class);
	dao.delete(dossierCandidat);
	logger.debug("DossierCandidate {} successfully deleted", new Object[] { dossierCandidat });
    }

    @Override
    @Transactional(readOnly = true)
    public List<DossierCandidatDto> findAll(DossierCandidatDto dto) {
	Assert.notNull(dto, "Can't search for a null dto");
	logger.debug("Searching for {}", new Object[] { dto });
	DossierCandidat dossierCandidat = mapper.map(dto, DossierCandidat.class);
	List<DossierCandidat> dossierCandidats = dao.findAllByExample(dossierCandidat);
	logger.debug("found {} results ", new Object[] { dossierCandidat == null ? 0 : dossierCandidats.size() });
	List<DossierCandidatDto> results = null;
	if (dossierCandidats != null && !dossierCandidats.isEmpty()) {
	    results = new ArrayList<>();
	    DossierCandidatDto result = null;
	    for (DossierCandidat dossier : dossierCandidats) {
		result = mapper.map(dossier, DossierCandidatDto.class);
		mapAdresse(result, dossier);
		results.add(result);
	    }
	}
	return results;
    }

    private void mapAdresse(DossierCandidatDto result, DossierCandidat dossierCandidat) {
	RefIndividu individu = dossierCandidat.getIndividu();
	RefCoordonnee coordonnee = null;
	if (individu.getRefCoordonnees() != null && individu.getRefCoordonnees().size() > 0) {
	    for (RefCoordonnee coord : individu.getRefCoordonnees()) {
		if (Boolean.TRUE.equals(coord.getPrincipal())) {
		    coordonnee = coord;
		    break;
		}
	    }
	}

	if (coordonnee != null) {
	    RefAdresse refAdresse = coordonnee.getRefAdresse();
	    RefAdresseElectronique refAdresseElectronique = coordonnee.getRefAdresseElectronique();
	    RefTelephone refTelephone = coordonnee.getRefTelephone();
	    if (refTelephone != null) {
		result.setNumeroTelephone(refTelephone.getNumeroTelephone());
	    }
	    if (refAdresse != null) {
		result.setAdresse(refAdresse.getLibelleAdresseLatin());
		result.setCodePostal(refAdresse.getCodePostal());
	    }
	    if (refAdresseElectronique != null) {
		result.setEmail(refAdresseElectronique.getNomAdresse());
	    }
	}

    }

    @Override
    public DossierCandidatDto genererDossierInsicription(DossierCandidatDto dto) {
	Assert.notNull(dto, "Can't genereate a DossierInscription for a null dto");
	// TODO Auto-generated method stub
	return null;
    }

    public void setMapper(Mapper mapper) {
	this.mapper = mapper;
    }

    public void setDao(DossierCandidatDao dao) {
	this.dao = dao;
    }

    /**
     * Retourne la liste des admis dans le concours dont le id = idConcours
     * 
     * @author Mounir.MESSAOUDI on : 2 nov. 2014 09:22:00
     * @param idConcours
     * @return
     */
    @Override
    public List<DossierCandidatDto> findAllAdmisByIdConcours(Long idConcours) {
	List<DossierCandidatDto> results = null;
	try {
	    List<DossierCandidat> dossierCandidats = dao.findAllAdmisByIdConcoursIdEtabAdmission(idConcours, null);

	    if (dossierCandidats != null && !dossierCandidats.isEmpty()) {
		results = new ArrayList<>();
		for (DossierCandidat dossier : dossierCandidats) {
		    results.add(mapper.map(dossier, DossierCandidatDto.class));
		}
	    }

	} catch (Exception e) {
	}
	return results;

    }

    /**
     * Retourne la liste des admis dans le concours dont le id = @id et
     * l'etablissement d'admision est idEtabAdmission
     * 
     * @author Mounir.MESSAOUDI on : 2 nov. 2014 09:22:00
     * @param id
     * @param idEtabAdmission
     * @return
     */
    @Override
    public List<DossierCandidatDto> findAllAdmisByIdConcoursIdEtabAdmission(Long idConcours, Integer idEtabAdmission) {
	List<DossierCandidatDto> results = null;
	try {
	    List<DossierCandidat> dossierCandidats = dao.findAllAdmisByIdConcoursIdEtabAdmission(idConcours,
		    idEtabAdmission);

	    if (dossierCandidats != null && !dossierCandidats.isEmpty()) {
		results = new ArrayList<>();
		for (DossierCandidat dossier : dossierCandidats) {
		    results.add(mapper.map(dossier, DossierCandidatDto.class));
		}
	    }

	} catch (Exception e) {
	}
	return results;

    }

    @Override
    public void calculMoyenne(ConcoursDto concoursDto, DossierCandidatDto candidat) {
	List<ResultatExamenDto> resultatDtos = candidat.getResultatExamenDtos();
	Integer noteBaseConcours = concoursDto.getNoteBase();
	Double sommeCoefficientConcours = getSommeCoefficientConcours(concoursDto);
	Double moyenne = new Double(0);
	for (ResultatExamenDto resultatDto : resultatDtos) {
	    Double note = resultatDto.getNote();
	    if (note != null) {
		Double coeffcient = resultatDto.getExamenConcoursDto().getCoefficient();
		Integer noteBaseExamen = resultatDto.getExamenConcoursDto().getNoteBase();
		Double resultatExamen = resultatDto.getNote();
		moyenne += ((resultatExamen * noteBaseConcours) / noteBaseExamen) * coeffcient;
	    }
	}
	if (moyenne != 0) {
	    moyenne = moyenne / sommeCoefficientConcours;
	    moyenne = (Math.ceil(moyenne * 100)) / 100;
	}

	candidat.setMoyenneObtenue(moyenne);

    }

    @Override
    public DossierCandidatDto enregisterResultat(DossierCandidatDto dto) {
	Assert.notNull(dto, "Can't persist a null dto");
	DossierCandidat dossierCandidat = mapper.map(dto, DossierCandidat.class);
	dao.merge(dossierCandidat);
	DossierCandidat candidatExample = new DossierCandidat();
	Concours concoursExample = new Concours();
	concoursExample.setId(dto.getConcoursId());
	candidatExample.setConcours(concoursExample);
	List<DossierCandidat> entities = dao.findAllByExample(candidatExample, "moyenneObtenue", null, null, true);
	int rang = 1;
	for (DossierCandidat entity : entities) {
	    if (entity.getMoyenneObtenue() == null) {
		continue;
	    } else {
		entity.setClassement(rang++);
		DossierCandidat dc = dao.merge(entity);
		if (dc.getId().equals(dto.getId().longValue())) {
		    dossierCandidat = dc;
		}
	    }

	}

	logger.debug("Results for  DossierCandidate {} successfully updated", new Object[] { dossierCandidat });
	return mapper.map(dossierCandidat, DossierCandidatDto.class);
    }

    private Double getSommeCoefficientConcours(ConcoursDto concoursDto) {
	Double result = new Double(0);
	for (ExamenConcoursDto dto : concoursDto.getExamenConcoursDtos()) {
	    result += dto.getCoefficient();
	}
	return result;
    }

    @Override
    public int countByExample(DossierCandidatDto dto) {
	DossierCandidat dossierCandidat = mapper.map(dto, DossierCandidat.class);
	return dao.countByExample(dossierCandidat);
    }

    @Override
    public List<DossierCandidatDto> findByExample(DossierCandidatDto dto, String sortField, int pageSize, int first,
	    Boolean descending) {
	DossierCandidat dossierCandidat = mapper.map(dto, DossierCandidat.class);
	List<DossierCandidat> results = dao.findAllByExample(dossierCandidat, sortField, pageSize, first, descending);
	List<DossierCandidatDto> resultDtos = null;
	if (results != null && results.size() > 0) {
	    resultDtos = new ArrayList<DossierCandidatDto>();
	    DossierCandidatDto resultDto = null;
	    for (DossierCandidat dossier : results) {
		resultDto = mapper.map(dossier, DossierCandidatDto.class);
		mapAdresse(resultDto, dossier);
		resultDtos.add(resultDto);
	    }
	}
	return resultDtos;
    }
}
