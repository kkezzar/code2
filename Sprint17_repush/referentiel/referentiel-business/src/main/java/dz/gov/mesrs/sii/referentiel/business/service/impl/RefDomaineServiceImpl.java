/**
 * [dz.gov.mesrs.sii.referentiel.business.service.impl.RefDomaineServiceImpl.java] 
 * @author MAKERRI Sofiane on : 18 f�vr. 2014  09:40:33
 */
package dz.gov.mesrs.sii.referentiel.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefDomaineDao;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefDomaine;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefDomaineDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefDomaineService;
import dz.gov.mesrs.sii.referentiel.business.util.RefUtilConstant;

/**
 * @author MAKERRI Sofiane on : 18 f�vr. 2014 09:40:33
 */
@Service("refDomaineServiceImpl")
public class RefDomaineServiceImpl implements RefDomaineService {

	@Autowired
	@Qualifier("refDomaineDaoImpl")
	private RefDomaineDao refDomaineDaoImpl;
	@Autowired
	@Qualifier("mapper")
	private org.dozer.Mapper mapper;
	private static final Logger log = LoggerFactory.getLogger(RefDomaineServiceImpl.class.getName());

	/**
	 * [RefDomaineServiceImpl.RefDomaineServiceImpl()] Constructor
	 * 
	 * @author MAKERRI Sofiane on : 18 f�vr. 2014 09:40:33
	 */
	public RefDomaineServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * [RefDomaineServiceImpl.refDomaineDao] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 f�vr. 2014 09:43:25
	 * @return the refDomaineDao
	 */
	public RefDomaineDao getRefDomaineDaoImpl() {
		return refDomaineDaoImpl;
	}

	/**
	 * [RefDomaineServiceImpl.refDomaineDao] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 f�vr. 2014 09:43:25
	 * @param refDomaineDao
	 *            the refDomaineDao to set
	 */
	public void setRefDomaineDaoImpl(RefDomaineDao refDomaineDaoImpl) {
		this.refDomaineDaoImpl = refDomaineDaoImpl;
	}

	/**
	 * [RefDomaineServiceImpl.mapper] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 f�vr. 2014 09:43:25
	 * @return the mapper
	 */
	public org.dozer.Mapper getMapper() {
		return mapper;
	}

	/**
	 * [RefDomaineServiceImpl.mapper] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 f�vr. 2014 09:43:25
	 * @param mapper
	 *            the mapper to set
	 */
	public void setMapper(org.dozer.Mapper mapper) {
		this.mapper = mapper;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dz.gov.mesrs.sii.referentiel.business.service.RefDomaineService#
	 * insertOrUpdate
	 * (dz.gov.mesrs.sii.referentiel.business.model.dto.RefDomaineDto)
	 */
	@Override
	public RefDomaineDto insertOrUpdate(RefDomaineDto refDomaineDto) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.referentiel.business.service.RefDomaineService#remove
	 * (dz.gov.mesrs.sii.referentiel.business.model.dto.RefDomaineDto)
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void remove(RefDomaineDto refDomaineDto) {
		try {
			RefDomaine refDomaine = mapper.map(refDomaineDto, RefDomaine.class);
			refDomaine = refDomaineDaoImpl.merge(refDomaine);
			refDomaineDaoImpl.remove(refDomaine);
			log.debug("remove refDomaineDto successful");
		} catch (RuntimeException e) {
			log.error("remove refDomaineDto failed", e);
			throw e;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.referentiel.business.service.RefDomaineService#findById
	 * (int)
	 */
	@Override
	public RefDomaineDto findById(int id) {
		RefDomaineDto refDomaineDto = null;
		try {
			RefDomaine refDomaine = refDomaineDaoImpl
					.findById(id);
			if (refDomaine != null) {
				refDomaineDto = new RefDomaineDto();
				log.debug("domaine found successfully with id = "
						+ id);
				mapper.map(refDomaine, refDomaineDto);

			}
		} catch (RuntimeException e) {
			log.error("get domaine list failed", e);
			throw e;
		}
		return refDomaineDto;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.referentiel.business.service.RefDomaineService#findByQuery
	 * (java.lang.String)
	 */
	@Override
	public List<RefDomaineDto> findByQuery(String query) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.referentiel.business.service.RefDomaineService#findAll()
	 */
	@Override
	public List<RefDomaineDto> findAll() {
		List<RefDomaineDto> refDomaineDtos = null;
		try {
			List<RefDomaine> refDomaines = refDomaineDaoImpl.findAll();

			refDomaineDtos = new ArrayList<RefDomaineDto>();

			for (RefDomaine refDomaine : refDomaines) {
				refDomaineDtos.add(mapper.map(refDomaine, RefDomaineDto.class));
			}
		} catch (RuntimeException e) {
			log.error("get domaine list failed", e);
			throw e;
		}

		return refDomaineDtos;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.referentiel.business.service.RefDomaineService#save(
	 * dz.gov.mesrs.sii.referentiel.business.model.dto.RefDomaineDto)
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public RefDomaineDto save(RefDomaineDto refDomaineDto) {
		RefDomaine refDomaine = new RefDomaine();
		try {
			mapper.map(refDomaineDto, refDomaine);
			refDomaineDaoImpl.persist(refDomaine);
			mapper.map(refDomaine, refDomaineDto);
			log.debug("save refDomaine successful");
		} catch (RuntimeException e) {
			log.error("save refDomaine failed", e);
			throw e;
		}
		return refDomaineDto;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.referentiel.business.service.RefDomaineService#update
	 * (dz.gov.mesrs.sii.referentiel.business.model.dto.RefDomaineDto)
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void update(RefDomaineDto refDomaineDto) {
		RefDomaine refDomaine = new RefDomaine();
		mapper.map(refDomaineDto, refDomaine);
		try {
			refDomaineDaoImpl.merge(refDomaine);
			log.debug("update refDomaine successful");
		} catch (RuntimeException e) {
			log.error("update refDomaine failed", e);
			throw e;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.referentiel.business.service.RefDomaineService#findByIdRef
	 * (int)
	 */
	@Override
	public List<RefDomaineDto> findByIdRef(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RefDomaineDto> findGeneric(String value) {
		List<RefDomaineDto> refDomaineDtoListDto = new ArrayList<RefDomaineDto>();
		try {
			List<RefDomaine> refDomaineList = refDomaineDaoImpl
					.findGeneric(value);
			if (refDomaineList != null) {
				log.debug("domaine list success with size =  "
						+ refDomaineList.size());

				for (RefDomaine currentRefDomaine : refDomaineList) {
					RefDomaineDto refDomaineDto = new RefDomaineDto();
					mapper.map(currentRefDomaine, refDomaineDto);
					List<RefDomaine> refSubDomaineList = refDomaineDaoImpl
							.findSubDomaine(currentRefDomaine.getId());
					if (refSubDomaineList != null
							&& refSubDomaineList.size() > 0) {
						List<RefDomaineDto> refSubDomaineDtoListDto = new ArrayList<RefDomaineDto>();
						for (RefDomaine currentSubRefDomaine : refSubDomaineList) {
							RefDomaineDto refSubDomaineDto = new RefDomaineDto();
							mapper.map(currentSubRefDomaine, refSubDomaineDto);
							refSubDomaineDtoListDto.add(refSubDomaineDto);
						}
						refDomaineDto.setListSubDomaine(refSubDomaineDtoListDto);
					}

					refDomaineDtoListDto.add(refDomaineDto);
				}
			}
		} catch (RuntimeException e) {
			log.error("get domaine list failed", e);
			throw e;
		}
		return refDomaineDtoListDto;
	}

	@Override
	public RefDomaineDto findByIdentifiant(String identifiant) {
		RefDomaineDto refDomaineDto = null;
		try {
			RefDomaine refDomaine = refDomaineDaoImpl
					.findByIdentifiant(identifiant);
			if (refDomaine != null) {
				refDomaineDto = new RefDomaineDto();
				log.debug("domaine found successfully with identifiant = {}",new Object[]{identifiant});
				mapper.map(refDomaine, refDomaineDto);

			}
		} catch (RuntimeException e) {
			log.error("get domaine list failed", e);
			throw e;
		}
		return refDomaineDto;
	}

	@Override
	public List<RefDomaineDto> findSubDomaine(int id) {
		List<RefDomaineDto> refDomaineDtoListDto = new ArrayList<RefDomaineDto>();
		try {
			List<RefDomaine> refDomaineList = refDomaineDaoImpl
					.findSubDomaine(id);
			if (refDomaineList != null) {
				log.debug("domaine list success with size =  {}", new Object[]{refDomaineList.size()});

				for (RefDomaine currentRefDomaine : refDomaineList) {
					RefDomaineDto refDomaineDto = new RefDomaineDto();
					mapper.map(currentRefDomaine, refDomaineDto);
					refDomaineDtoListDto.add(refDomaineDto);
				}
			}
		} catch (RuntimeException e) {
			log.error("get domaine list failed", e);
			throw e;
		}
		return refDomaineDtoListDto;
	}

	@Override
	public int findSubDomaineLastRang(int id) {
		try {
			int rang = refDomaineDaoImpl
					.findSubDomaineLastRang(id);
			log.info("get last rang of sub-domaine success");
		    return rang;
		} catch (RuntimeException e) {
			log.error("get last rang of sub-domaine failed", e);
			throw e;
		}
		
	}

	@Override
	public RefDomaineDto findByName(String name) {
		RefDomaineDto refDomaineDto = null;
		try {
			RefDomaine refDomaine = refDomaineDaoImpl
					.findByName(name);
			if (refDomaine != null) {
				refDomaineDto = new RefDomaineDto();
				log.debug("domaine found successfully with name = {}",new Object[]{
						name});
				mapper.map(refDomaine, refDomaineDto);

			}
		} catch (RuntimeException e) {
			log.error("get domaine list failed", e);
			throw e;
		}
		return refDomaineDto;
	}

	@Override
	public List<RefDomaineDto> findDomaines() {
		List<RefDomaineDto> refDomaineDtoListDto = new ArrayList<RefDomaineDto>();
		try {
			List<RefDomaine> refDomaineList = refDomaineDaoImpl
					.findDomaines();
			if (refDomaineList != null) {
				log.debug("domaine list success with size =  {}", new Object[]{refDomaineList.size()});

				for (RefDomaine currentRefDomaine : refDomaineList) {
					RefDomaineDto refDomaineDto = new RefDomaineDto();
					mapper.map(currentRefDomaine, refDomaineDto);
					refDomaineDtoListDto.add(refDomaineDto);
				}
			}
		} catch (RuntimeException e) {
			log.error("get domaine list failed", e);
			throw e;
		}
		return refDomaineDtoListDto;
	}

	@Override
	public int findDomaineLastRang() {
		try {
			int rang = refDomaineDaoImpl
					.findDomaineLastRang();
			log.info("get last rang of domaine success");
		    return rang;
		} catch (RuntimeException e) {
			log.error("get last rang of domaine failed", e);
			throw e;
		}
	}

	@Override
	public List<RefDomaineDto> findSubDomaine(String identifiant) {
		List<RefDomaineDto> refDomaineDtoListDto = new ArrayList<RefDomaineDto>();
		try {
			List<RefDomaine> refDomaineList = refDomaineDaoImpl
					.findSubDomaine(identifiant);
			if (refDomaineList != null) {
				log.debug("sub-domaine list success with size =  {}",new Object[]{refDomaineList.size()});

				for (RefDomaine currentRefDomaine : refDomaineList) {
					RefDomaineDto refDomaineDto = new RefDomaineDto();
					mapper.map(currentRefDomaine, refDomaineDto);
					refDomaineDtoListDto.add(refDomaineDto);
				}
			}
		} catch (RuntimeException e) {
			log.error("get sub-domaine list failed", e);
			throw e;
		}
		return refDomaineDtoListDto;
	}

	@Override
	public void updateRangSubDomaine(String identifiant) {
		try {
		    List<RefDomaineDto> refDomaineDtoListDto = findSubDomaine(identifiant);
		    if (refDomaineDtoListDto != null && refDomaineDtoListDto.size() > 0) {
		    	int rang = 1;
		    	for (RefDomaineDto refDomaineDto : refDomaineDtoListDto) {
		    		refDomaineDto.setRang(rang);
		    		update(refDomaineDto);
		    		rang++;
				}
		    	
		    }
		} catch (RuntimeException e) {
			log.error("updateRangSubDomaine list failed", e);
			throw e;
		}
		
	}

	@Override
	public List<RefDomaineDto> findDomainesAndSubDomaines() {
		List<RefDomaineDto> refDomaineDtoListDto = new ArrayList<RefDomaineDto>();
		try {
			List<RefDomaine> refDomaineList = refDomaineDaoImpl
					.findDomainesAndSubDomaine();
			if (refDomaineList != null) {
				log.debug("get domaine and subDomaine list success with size = {}  ",new Object[]{
						refDomaineList.size()});

				for (RefDomaine currentRefDomaine : refDomaineList) {
					RefDomaineDto refDomaineDto = new RefDomaineDto();
					mapper.map(currentRefDomaine, refDomaineDto);
					refDomaineDtoListDto.add(refDomaineDto);
				}
			}
		} catch (RuntimeException e) {
			log.error("get domaine and subDomaine list failed", e);
			throw e;
		}
		return refDomaineDtoListDto;
	}

	@Override
	public String generateIdentify(String prefix, int orderLength) {
		try {
			if (prefix == null) {
				return null;
			}

			Integer lastOrder = refDomaineDaoImpl.findLastOrder(prefix,
					orderLength);
			if (lastOrder == null) {
				return null;
			}
			lastOrder++;
			prefix = prefix
					+ RefUtilConstant.formatOrder(lastOrder.toString(),
							orderLength);
			log.debug("calculateIdentify successful");
		} catch (RuntimeException e) {
			log.error("calculateIdentify failed", e);
			throw e;
		}
		return prefix;	
		
	}

	@Override
	public RefDomaineDto findByNameArabe(String nameArabe) {
		RefDomaineDto refDomaineDto = null;
		try {
			RefDomaine refDomaine = refDomaineDaoImpl
					.findByNameArabe(nameArabe);
			if (refDomaine != null) {
				refDomaineDto = new RefDomaineDto();
				log.debug("domaine found successfully with name = {}",new Object[]{nameArabe});
				mapper.map(refDomaine, refDomaineDto);

			}
		} catch (RuntimeException e) {
			log.error("get domaine list failed", e);
			throw e;
		}
		return refDomaineDto;
	}

}
