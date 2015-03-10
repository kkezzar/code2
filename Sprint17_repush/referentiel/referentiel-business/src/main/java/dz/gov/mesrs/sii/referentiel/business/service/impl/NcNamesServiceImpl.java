package dz.gov.mesrs.sii.referentiel.business.service.impl;

import java.util.ArrayList;
import java.util.List;




import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.referentiel.NcNamesDao;
import dz.gov.mesrs.sii.commons.data.model.referentiel.NcNames;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NcNamesDto;
import dz.gov.mesrs.sii.referentiel.business.service.NcNamesService;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */

@Service("ncNamesServiceImpl")
public class NcNamesServiceImpl implements NcNamesService {

	@Autowired
	@Qualifier("ncNamesDaoImpl")
	private NcNamesDao ncNamesDaoImpl;
	@Autowired
	@Qualifier("mapper")
	private Mapper mapper;

	private static final Logger log = LoggerFactory.getLogger(NcNamesServiceImpl.class.getName());

	public NcNamesServiceImpl() {

	}

	/*
	 * (non-Javadoc) insert or update an {@link NcNamesDto}
	 * 
	 * @see
	 * dz.gov.mesrs.sii.referentiel.business.service.NcNamesService#insertOrUpdate
	 * (dz.gov.mesrs.sii.referentiel.business.model.dto.NcNamesDto)
	 */
	@Override
	public NcNamesDto insertOrUpdate(NcNamesDto ncNamesDto) {

		NcNames ncNames = mapper.map(ncNamesDto, NcNames.class);

		if (ncNames.getId() == 0)
			ncNamesDaoImpl.persist(ncNames);
		else
			ncNames = ncNamesDaoImpl.merge(ncNames);

		mapper.map(ncNames, ncNamesDto);

		return ncNamesDto;
	}

	/**
	 * remove an entry in {@link NcNamesDto}
	 * 
	 * @param ncNamesDto
	 *            the NcNamesDto
	 *
	 * @return void
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void remove(NcNamesDto ncNamesDto) {
		try {
			NcNames ncNames = mapper.map(ncNamesDto, NcNames.class);
			ncNames = ncNamesDaoImpl.merge(ncNames);
			ncNamesDaoImpl.remove(ncNames);
			log.debug("remove ncNames successful");
		} catch (RuntimeException e) {
			log.error("remove ncNames failed", e);
			throw e;
		}
	}

	/*
	 * (non-Javadoc) find {@link NcNamesDto}
	 * 
	 * @see
	 * dz.gov.mesrs.sii.referentiel.business.service.NcNamesService#findByQuery
	 * (java.lang.String)
	 */
	@Override
	public List<NcNamesDto> findByQuery(String query) {

		List<NcNames> ncNames = ncNamesDaoImpl.findByQuery(query);

		List<NcNamesDto> ncNamesDto = new ArrayList<NcNamesDto>();

		for (NcNames ncName : ncNames) {
			ncNamesDto.add(mapper.map(ncName, NcNamesDto.class));
		}

		return ncNamesDto;

	}

	@Override
	public NcNamesDto findById(int id) {

		NcNames ncNames = ncNamesDaoImpl.findById(id);

		if (ncNames != null)
			return mapper.map(ncNames, NcNamesDto.class);

		return null;
	}

	/**
	 * find all entry in NcNamesDto
	 *
	 * @return NcNamesDto collection
	 */
	@Override
	public List<NcNamesDto> findAll() {

		List<NcNames> ncNames = ncNamesDaoImpl.findAll();

		List<NcNamesDto> ncNamesDto = new ArrayList<NcNamesDto>();

		for (NcNames ncName : ncNames) {
			ncNamesDto.add(mapper.map(ncName, NcNamesDto.class));
		}

		return ncNamesDto;

	}

	/**
	 * find all entry with full-text(searching on all columns)
	 *
	 * @return NcNamesDto collection
	 */
	@Override
	public List<NcNamesDto> findGeneric(String value) {
		List<NcNamesDto> ncNamesDto = new ArrayList<NcNamesDto>();
		try {
			List<NcNames> ncNames = ncNamesDaoImpl.findGeneric(value);
			if (ncNames != null) {
				log.debug("ncNames success with size =  {}",new Object[]{ ncNames.size()});

				for (NcNames currentNcNames : ncNames) {
					NcNamesDto ncNameDto = new NcNamesDto();
					mapper.map(currentNcNames, ncNameDto);
					ncNamesDto.add(ncNameDto);
				}

			}
		} catch (RuntimeException e) {
			log.error("findGeneric failed", e);
			throw e;
		}
		return ncNamesDto;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.referentiel.business.service.NcNamesService#update(dz
	 * .gov.mesrs.sii.referentiel.business.model.dto.NcNamesDto)
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void update(NcNamesDto ncNamesDto) {
		NcNames ncNames = new NcNames();
		mapper.map(ncNamesDto, ncNames);
		try {
			ncNamesDaoImpl.merge(ncNames);
			log.debug("save ncNames successful");
		} catch (RuntimeException e) {
			log.error("save ncNames failed", e);
			throw e;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.referentiel.business.service.NcNamesService#saveOrUpdate
	 * (dz.gov.mesrs.sii.referentiel.business.model.dto.NcNamesDto)
	 */
	@Override
	public void saveOrUpdate(NcNamesDto ncNamesDto) {
		// TODO Auto-generated method stub

	}

	/**
	 * [NcNamesServiceImpl.ncNamesDaoImpl] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 janv. 2014 11:21:30
	 * @return the ncNamesDaoImpl
	 */
	public NcNamesDao getNcNamesDaoImpl() {
		return ncNamesDaoImpl;
	}

	/**
	 * [NcNamesServiceImpl.ncNamesDaoImpl] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 janv. 2014 11:21:30
	 * @param ncNamesDaoImpl
	 *            the ncNamesDaoImpl to set
	 */
	public void setNcNamesDaoImpl(NcNamesDao ncNamesDaoImpl) {
		this.ncNamesDaoImpl = ncNamesDaoImpl;
	}

	/**
	 * [NcNamesServiceImpl.mapper] Getter
	 * 
	 * @author MAKERRI Sofiane on : 21 janv. 2014 17:50:37
	 * @return the mapper
	 */
	public Mapper getMapper() {
		return mapper;
	}

	/**
	 * [NcNamesServiceImpl.mapper] Setter
	 * 
	 * @author MAKERRI Sofiane on : 21 janv. 2014 17:50:37
	 * @param mapper
	 *            the mapper to set
	 */
	public void setMapper(Mapper mapper) {
		this.mapper = mapper;
	}

	/**
	 * search an entry in {@link NcNamesDto} with name
	 * 
	 * @param ncNamesDto
	 *            the NcNamesDto
	 *
	 * @return NcNamesDto found or null
	 */
	@Override
	public NcNamesDto findByName(String ncName) {
		NcNamesDto ncNamesDto = null;
		try {
			NcNames ncNames = ncNamesDaoImpl.findByName(ncName);
			if (ncNames != null) {
				ncNamesDto = new NcNamesDto();
				log.debug("findByName success");
				mapper.map(ncNames, ncNamesDto);

			}
		} catch (RuntimeException e) {
			log.error("findByName failed", e);
			// throw e;
			return null;
		}
		return ncNamesDto;
	}

	/* (non-Javadoc)
	 * @see dz.gov.mesrs.sii.referentiel.business.service.NcNamesService#HasReference(int)
	 */
	@Override
	public boolean hasReference(Integer id) {
		if(id == null) return false;
		return ncNamesDaoImpl.hasReference(id);
	}

	
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public NcNamesDto save(NcNamesDto ncNamesDto) {
		NcNames ncNames = new NcNames();
		mapper.map(ncNamesDto, ncNames);
		try {
			ncNamesDaoImpl.persist(ncNames);
			log.debug("save ncNames successful");
			return mapper.map(ncNames, NcNamesDto.class);
		} catch (RuntimeException e) {
			log.error("save ncNames failed", e);
			throw e;
		}
		
		
	}

	@Override
	public NcNamesDto findDefaultValue(String ncName) {
		if(ncName == null) return null;
		NcNames  ncNames = ncNamesDaoImpl.findDefaultValue(ncName);
		return mapper.map(ncNames, NcNamesDto.class);
	}

}
