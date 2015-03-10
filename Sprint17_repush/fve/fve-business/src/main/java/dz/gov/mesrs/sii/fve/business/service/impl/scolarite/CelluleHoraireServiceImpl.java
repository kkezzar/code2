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

import dz.gov.mesrs.sii.commons.data.dao.fve.scolarite.CelluleHoraireDao;
import dz.gov.mesrs.sii.commons.data.model.fve.scolarite.CelluleHoraire;
import dz.gov.mesrs.sii.fve.business.model.dto.scolarite.CelluleHoraireDto;
import dz.gov.mesrs.sii.fve.business.service.scolarite.CelluleHoraireService;

/**
 * 
 * @author BELDI Jamel  on : 7 oct. 2014  17:54:53
 */
 

@Service("celluleHoraireService")
public class CelluleHoraireServiceImpl implements CelluleHoraireService  {

	@Autowired
	@Qualifier ("celluleHoraireDao")
	private CelluleHoraireDao celluleHoraireDao;

	private static final Log log = LogFactory.getLog(CelluleHoraireServiceImpl.class);
	
    @Autowired
	@Qualifier("mapper")
	private DozerBeanMapper mapper;
	
	
	
	public CelluleHoraireServiceImpl(){

	}


	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public CelluleHoraireDto  insertOrUpdate(
			CelluleHoraireDto celluleHoraireDto) {

		CelluleHoraire celluleHoraire = mapper.map(celluleHoraireDto,
				CelluleHoraire.class);
		try {
			if (celluleHoraire.getId() == 0){
				celluleHoraireDao.persist(celluleHoraire);
			}else{
				celluleHoraire = celluleHoraireDao.merge(celluleHoraire);
			}
			mapper.map(celluleHoraire, celluleHoraireDto);
			
			log.error("insertorupdate CelluleHoraire succes..");
			
			return celluleHoraireDto;
		
		} catch (RuntimeException e) {
			log.error("insertorupdate CelluleHoraire failed--", e);
			throw e;
		}
	}
	

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void remove(CelluleHoraireDto celluleHoraireDto) {
		try {

		CelluleHoraire celluleHoraire = mapper.map(celluleHoraireDto,
				CelluleHoraire.class);
		
		celluleHoraire = celluleHoraireDao.merge(celluleHoraire);

		celluleHoraireDao.remove(celluleHoraire);
		} catch (RuntimeException e) {
			throw e;
		}
	}


	@Override
	public CelluleHoraireDto findById(int id) {
		try {
		CelluleHoraire celluleHoraire = celluleHoraireDao.findById(id);

		if (celluleHoraire != null)
			return mapper.map(celluleHoraire, CelluleHoraireDto.class);
		} catch (RuntimeException e) {
			throw e;
		}
		return null;
	}

	
	
	@Override
	public List<CelluleHoraireDto> findAll() {		
		try {
		List<CelluleHoraire> celluleHoraires = celluleHoraireDao.findAll();

		List<CelluleHoraireDto> celluleHoraireDtos = new ArrayList<CelluleHoraireDto>();

		for (CelluleHoraire celluleHoraire : celluleHoraires) {
			celluleHoraireDtos.add(mapper.map(celluleHoraire, CelluleHoraireDto.class));
		}
		return celluleHoraireDtos;	
		} catch (RuntimeException e) {
			throw e;
		}
			
		
	}


	@Override
	public CelluleHoraireDto findByJourAndPlageHoraire(int idJour,
			int idPlageHoraire) {
		try {
			if(idJour!=0 && idPlageHoraire!=0){
		
				CelluleHoraire celluleHoraire = celluleHoraireDao.findByJourAndPlageHoraire(idJour, idPlageHoraire);


			if (celluleHoraire != null)
				return mapper.map(celluleHoraire, CelluleHoraireDto.class);
			}
			} catch (RuntimeException e) {
				throw e;
			}
			return null;
	}
	
}



