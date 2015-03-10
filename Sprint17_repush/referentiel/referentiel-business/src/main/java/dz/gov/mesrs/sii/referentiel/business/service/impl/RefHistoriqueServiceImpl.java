package dz.gov.mesrs.sii.referentiel.business.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefHistoriqueDao;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefHistorique;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefFonctionDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefHistoriqueDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefFonctionService;
import dz.gov.mesrs.sii.referentiel.business.service.RefHistoriqueService;

@Service("refHistoriqueServiceImpl")
public class RefHistoriqueServiceImpl implements RefHistoriqueService {

    @Autowired
    @Qualifier("refHistoriqueDaoImpl")
    private RefHistoriqueDao refHistoriqueDaoImpl;
    @Autowired
    @Qualifier("refFonctionServiceImpl")
    private RefFonctionService refFonctionServiceImpl;
    @Autowired
    @Qualifier("mapper")
    private Mapper mapper;

    private static final Log log = LogFactory.getLog(RefHistoriqueServiceImpl.class);

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
    public RefHistoriqueDto insertOrUpdate(RefHistoriqueDto refHistoriqueDto) {

	RefHistorique refHistorique = mapper.map(refHistoriqueDto, RefHistorique.class);
	try {
	    if (refHistorique.getId() == 0) {
		refHistoriqueDaoImpl.persist(refHistorique);
	    } else {
		refHistorique = refHistoriqueDaoImpl.merge(refHistorique);
	    }
	    mapper.map(refHistorique, refHistoriqueDto);

	    log.error("insertorupdate RefHistorique succes..");

	    return refHistoriqueDto;

	} catch (RuntimeException e) {
	    log.error("insertorupdate RefHistorique failed--", e);
	    throw e;
	}
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
    public void remove(RefHistoriqueDto refHistoriqueDto) {
	try {

	    RefHistorique refHistorique = mapper.map(refHistoriqueDto, RefHistorique.class);

	    refHistorique = refHistoriqueDaoImpl.merge(refHistorique);

	    refHistoriqueDaoImpl.remove(refHistorique);
	} catch (RuntimeException e) {
	    throw e;
	}
    }

    @Override
    public RefHistoriqueDto findById(int id) {

	RefHistorique refHistorique = refHistoriqueDaoImpl.findById(id);

	if (refHistorique != null)
	    return mapper.map(refHistorique, RefHistoriqueDto.class);

	return null;
    }

    @Override
    public List<RefHistoriqueDto> findAll() {

	List<RefHistorique> refHistoriques = refHistoriqueDaoImpl.findAll();

	List<RefHistoriqueDto> refHistoriqueDtos = new ArrayList<RefHistoriqueDto>();

	for (RefHistorique refHistorique : refHistoriques) {
	    refHistoriqueDtos.add(mapper.map(refHistorique, RefHistoriqueDto.class));
	}

	return refHistoriqueDtos;

    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
    public RefHistoriqueDto save(RefHistoriqueDto refHistoriqueDto) {
	if (refHistoriqueDto == null) {
	    return refHistoriqueDto;
	}
	if (refHistoriqueDto.getIdFonction() == null && refHistoriqueDto.getCodeFonction() != null) {
	    RefFonctionDto refFonctionDto = refFonctionServiceImpl
		    .findByIdentifiant(refHistoriqueDto.getCodeFonction());
	    if (refFonctionDto == null || refFonctionDto.getId() == null) {
		return null;
	    }
	    refHistoriqueDto.setIdFonction(refFonctionDto.getId());
	}

	RefHistorique refHistorique = mapper.map(refHistoriqueDto, RefHistorique.class);

	try {
	    refHistorique.setDateAction(new Date());
	    refHistorique.setHeureAction(new Date());

	    refHistoriqueDaoImpl.persist(refHistorique);
	    mapper.map(refHistorique, refHistoriqueDto);
	    log.error("save RefHistorique succes..");
	    return refHistoriqueDto;

	} catch (RuntimeException e) {
	    log.error("save RefHistorique failed--", e);
	    throw e;
	}
    }

    /**
     * [RefHistoriqueServiceImpl.refHistoriqueDaoImpl] Getter
     * 
     * @author MAKERRI Sofiane on : 18 juin 2014 11:55:36
     * @return the refHistoriqueDaoImpl
     */
    public RefHistoriqueDao getRefHistoriqueDaoImpl() {
	return refHistoriqueDaoImpl;
    }

    /**
     * [RefHistoriqueServiceImpl.refHistoriqueDaoImpl] Setter
     * 
     * @author MAKERRI Sofiane on : 18 juin 2014 11:55:36
     * @param refHistoriqueDaoImpl
     *            the refHistoriqueDaoImpl to set
     */
    public void setRefHistoriqueDaoImpl(RefHistoriqueDao refHistoriqueDaoImpl) {
	this.refHistoriqueDaoImpl = refHistoriqueDaoImpl;
    }

    /**
     * [RefHistoriqueServiceImpl.mapper] Getter
     * 
     * @author MAKERRI Sofiane on : 18 juin 2014 11:55:36
     * @return the mapper
     */
    public Mapper getMapper() {
	return mapper;
    }

    /**
     * [RefHistoriqueServiceImpl.mapper] Setter
     * 
     * @author MAKERRI Sofiane on : 18 juin 2014 11:55:36
     * @param mapper
     *            the mapper to set
     */
    public void setMapper(Mapper mapper) {
	this.mapper = mapper;
    }

    /**
     * [RefHistoriqueServiceImpl.refFonctionServiceImpl] Getter
     * 
     * @author MAKERRI Sofiane on : 18 juin 2014 15:33:32
     * @return the refFonctionServiceImpl
     */
    public RefFonctionService getRefFonctionServiceImpl() {
	return refFonctionServiceImpl;
    }

    /**
     * [RefHistoriqueServiceImpl.refFonctionServiceImpl] Setter
     * 
     * @author MAKERRI Sofiane on : 18 juin 2014 15:33:32
     * @param refFonctionServiceImpl
     *            the refFonctionServiceImpl to set
     */
    public void setRefFonctionServiceImpl(RefFonctionService refFonctionServiceImpl) {
	this.refFonctionServiceImpl = refFonctionServiceImpl;
    }

    @Override
    public List<RefHistoriqueDto> findAdvanced(RefHistoriqueDto refHistoriqueDto) {
	try {
	    RefHistorique refHistorique = mapper.map(refHistoriqueDto, RefHistorique.class);
	    List<RefHistorique> refHistoriques = refHistoriqueDaoImpl.findAdvanced(refHistorique);

	    List<RefHistoriqueDto> refHistoriqueDtos = new ArrayList<RefHistoriqueDto>();

	    for (RefHistorique current : refHistoriques) {
		refHistoriqueDtos.add(mapper.map(current, RefHistoriqueDto.class));
	    }
	    return refHistoriqueDtos;
	} catch (RuntimeException e) {
	    log.error("findAdvanced failed--", e);
	    throw e;
	}
    }

    @Override
    public List<RefHistoriqueDto> findByPeriode(RefHistoriqueDto refHistoriqueDto, Date dateDebut, Date dateFin) {
	try {

	    RefHistorique refHistorique = mapper.map(refHistoriqueDto, RefHistorique.class);
	    List<RefHistorique> refHistoriques = refHistoriqueDaoImpl.findByPeriode(refHistorique, dateDebut, dateFin);

	    List<RefHistoriqueDto> refHistoriqueDtos = new ArrayList<RefHistoriqueDto>();

	    for (RefHistorique current : refHistoriques) {
		refHistoriqueDtos.add(mapper.map(current, RefHistoriqueDto.class));
	    }
	    return refHistoriqueDtos;
	}

	catch (RuntimeException e) {
	    log.error("findAdvanced failed--", e);
	    throw e;
	}
    }

}
