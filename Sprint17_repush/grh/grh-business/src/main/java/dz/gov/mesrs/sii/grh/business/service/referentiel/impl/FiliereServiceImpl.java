package dz.gov.mesrs.sii.grh.business.service.referentiel.impl;


import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.commons.business.service.impl.CommonServiceImpl;
import dz.gov.mesrs.sii.commons.data.dao.CommonDao;
import dz.gov.mesrs.sii.commons.data.model.grh.Filiere;
import dz.gov.mesrs.sii.grh.business.model.dto.FiliereDto;
import dz.gov.mesrs.sii.commons.data.dao.grh.FiliereDao;
import dz.gov.mesrs.sii.commons.data.dao.impl.grh.FiliereDaoImpl;
import dz.gov.mesrs.sii.grh.business.service.referentiel.FiliereService;

/**
 * 
 * @author BELBRIK
 * @version V1.0
 * @date 17-11-2014
 * @description Implementation des services
 * 
 */

@Service("filiereService")
public class FiliereServiceImpl extends CommonServiceImpl<Filiere,FiliereDto, Integer> implements FiliereService  {
	
	private static final Logger log = LoggerFactory.getLogger(FiliereDaoImpl.class);
	
	@Autowired
	@Qualifier ("filiereDao")
	private FiliereDao filiereDao;
	
    @Autowired
	@Qualifier("mapper")
	private Mapper mapper;
	

	
	
	@Override
	protected CommonDao<Filiere, Integer> getDao() {
		return filiereDao;
	}

	/**
	 * @return the FiliereDao
	 */
	public FiliereDao getFilierefDao() {
		return filiereDao;
	}

	
	
	
	
	/**
	 * [FiliereServiceImpl.filiereDao] Getter 
	 * @author BELBRIK Oualid on : 20 nov. 2014  15:06:21
	 * @return the filiereDao
	 */
	public FiliereDao getFiliereDao() {
		return filiereDao;
	}

	/**
	 * [FiliereServiceImpl.filiereDao] Setter 
	 * @author BELBRIK Oualid on : 20 nov. 2014  15:06:21
	 * @param filiereDao the filiereDao to set
	 */
	public void setFiliereDao(FiliereDao filiereDao) {
		this.filiereDao = filiereDao;
	}

	/**
	 * [FiliereServiceImpl.mapper] Getter 
	 * @author BELBRIK Oualid on : 20 nov. 2014  15:06:21
	 * @return the mapper
	 */
	public Mapper getMapper() {
		return mapper;
	}

	/**
	 * [FiliereServiceImpl.mapper] Setter 
	 * @author BELBRIK Oualid on : 20 nov. 2014  15:06:21
	 * @param mapper the mapper to set
	 */
	public void setMapper(Mapper mapper) {
		this.mapper = mapper;
	}

	
	

	
	@Override
	public void remove(FiliereDto filiereDto) {

		Filiere filiere = mapper.map(filiereDto,
				Filiere.class);
		
		filiere = filiereDao.merge(filiere);

		filiereDao.remove(filiere);
	}



	@Override
	public FiliereDto findById(int id) {

		Filiere filiere = filiereDao.findById(id);

		if (filiere != null)
			return mapper.map(filiere, FiliereDto.class);

		return null;
	}

	@Override
	public List<FiliereDto> findAll() {
		try {
			List<FiliereDto> result = new ArrayList<FiliereDto>();

			List<Filiere> resultListDao = filiereDao.findAll();
			if (resultListDao != null && !resultListDao.isEmpty()) {
				for (Filiere filiere : resultListDao) {
					FiliereDto filiereDto = new FiliereDto();
					mapper.map(filiere, filiereDto);
					result.add(filiereDto);
				}
				return result;
			}

		} catch (RuntimeException e) {
			log.error("findAll failed", e);
			throw e;

		}

		return null;
	}


	
	
	@Override
	public List<FiliereDto> findFiliereByIdRubrique(int idRubrique) {

			List<Filiere> filieres = filiereDao.findFiliereByIdRubrique(idRubrique);

			
			if(filieres==null || filieres.isEmpty()) return null;
			
			List<FiliereDto> filiereDtos = new ArrayList<FiliereDto>();

			for (Filiere filiere : filieres) {
				filiereDtos.add(mapper.map(filiere, FiliereDto.class));
			}

			return filiereDtos;		
	
	}

	
	@Override
	public FiliereDto insertOrUpdate(FiliereDto filiereDto) {
		try {
	
			Filiere filiere = mapper.map(filiereDto,
					Filiere.class);

			if (filiere.getId() == 0) {
				filiereDao.persist(filiere);
			} else {
				filiere = filiereDao.merge(filiere);
			}
			
			mapper.map(filiere, filiereDto);

			log.info("insertOrUpdate success");
			return filiereDto;
		} catch (Exception e) {
			log.error("insertOrUpdate failed", e);
			throw e;
		}
	}
	
}



