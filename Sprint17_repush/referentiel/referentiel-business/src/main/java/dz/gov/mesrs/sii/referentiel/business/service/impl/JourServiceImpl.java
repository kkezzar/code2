package dz.gov.mesrs.sii.referentiel.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.referentiel.JourDao;
import dz.gov.mesrs.sii.commons.data.model.referentiel.Jour;
import dz.gov.mesrs.sii.referentiel.business.model.dto.JourDto;
import dz.gov.mesrs.sii.referentiel.business.service.JourService;

/**
 * 
 * @author BELDI Jamel on : 7 oct. 2014 17:44:25
 */

@Service("jourService")
public class JourServiceImpl implements JourService {

    @Autowired
    @Qualifier("jourDao")
    private JourDao jourDao;

    private static final Log log = LogFactory.getLog(JourServiceImpl.class);

    @Autowired
    @Qualifier("mapper")
    private Mapper mapper;

    public JourServiceImpl() {

    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
    public JourDto insertOrUpdate(JourDto jourDto) {

	Jour jour = mapper.map(jourDto, Jour.class);
	try {
	    if (jour.getId() == 0) {
		jourDao.persist(jour);
	    } else {
		jour = jourDao.merge(jour);
	    }
	    mapper.map(jour, jourDto);

	    log.error("insertorupdate Jour succes..");

	    return jourDto;

	} catch (RuntimeException e) {
	    log.error("insertorupdate Jour failed--", e);
	    throw e;
	}
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
    public void remove(JourDto jourDto) {
	try {

	    Jour jour = mapper.map(jourDto, Jour.class);

	    jour = jourDao.merge(jour);

	    jourDao.remove(jour);
	} catch (RuntimeException e) {
	    throw e;
	}
    }

    @Override
    public JourDto findById(int id) {

	Jour jour = jourDao.findById(id);

	if (jour != null)
	    return mapper.map(jour, JourDto.class);

	return null;
    }

    @Override
    public List<JourDto> findAll() {

	List<Jour> jours = jourDao.findAll();

	List<JourDto> jourDtos = new ArrayList<JourDto>();

	for (Jour jour : jours) {
	    jourDtos.add(mapper.map(jour, JourDto.class));
	}

	return jourDtos;

    }
}
