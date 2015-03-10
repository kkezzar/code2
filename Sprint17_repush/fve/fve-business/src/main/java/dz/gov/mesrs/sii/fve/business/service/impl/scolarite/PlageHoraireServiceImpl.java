package dz.gov.mesrs.sii.fve.business.service.impl.scolarite;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.fve.scolarite.PlageHoraireDao;
import dz.gov.mesrs.sii.commons.data.model.fve.scolarite.PlageHoraire;
import dz.gov.mesrs.sii.fve.business.model.dto.scolarite.PlageHoraireDto;
import dz.gov.mesrs.sii.fve.business.service.scolarite.PlageHoraireService;

/**
 * 
 * @author BELDI Jamel  on : 7 oct. 2014  17:55:00
 */
 

@Service("plageHoraireService")
public class PlageHoraireServiceImpl implements PlageHoraireService  {

	@Autowired
	@Qualifier ("plageHoraireDao")
	private PlageHoraireDao plageHoraireDao;

	private static final Log log = LogFactory.getLog(PlageHoraireServiceImpl.class);
	
    @Autowired
	@Qualifier("mapper")
	private DozerBeanMapper mapper;
	
	
	
	public PlageHoraireServiceImpl(){

	}


	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public PlageHoraireDto  insertOrUpdate(
			PlageHoraireDto plageHoraireDto) {

		PlageHoraire plageHoraire = mapper.map(plageHoraireDto,
				PlageHoraire.class);
		try {
			if (plageHoraire.getId() == 0){
				plageHoraireDao.persist(plageHoraire);
			}else{
				plageHoraire = plageHoraireDao.merge(plageHoraire);
			}
			mapper.map(plageHoraire, plageHoraireDto);
			
			log.error("insertorupdate PlageHoraire succes..");
			
			return plageHoraireDto;
		
		} catch (RuntimeException e) {
			log.error("insertorupdate PlageHoraire failed--", e);
			throw e;
		}
	}
	

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void remove(PlageHoraireDto plageHoraireDto) {
		try {

		PlageHoraire plageHoraire = mapper.map(plageHoraireDto,
				PlageHoraire.class);
		
		plageHoraire = plageHoraireDao.merge(plageHoraire);

		plageHoraireDao.remove(plageHoraire);
		} catch (RuntimeException e) {
			throw e;
		}
	}


	@Override
	public PlageHoraireDto findById(int id) {

		PlageHoraire plageHoraire = plageHoraireDao.findById(id);

		if (plageHoraire != null)
			return mapper.map(plageHoraire, PlageHoraireDto.class);

		return null;
	}

	
	
	@Override
	public List<PlageHoraireDto> findAll() {		
		try {
		List<PlageHoraire> plageHoraires = plageHoraireDao.findAll();

		List<PlageHoraireDto> plageHoraireDtos = new ArrayList<PlageHoraireDto>();

		for (PlageHoraire plageHoraire : plageHoraires) {
			plageHoraireDtos.add(mapper.map(plageHoraire, PlageHoraireDto.class));
		}

		return plageHoraireDtos;		
		} catch (RuntimeException e) {
			throw e;
		}
	}
		
}



