/**
 * [dz.gov.mesrs.sii.referentiel.business.service.impl.RefFonctionServiceImpl.java] 
 * @author MAKERRI Sofiane on : 27 f�vr. 2014  14:05:30
 */
package dz.gov.mesrs.sii.referentiel.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefFonctionDao;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefFonction;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefFonctionDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefFonctionService;
import dz.gov.mesrs.sii.referentiel.business.util.RefUtilConstant;

/**
 * @author MAKERRI Sofiane  on : 27 f�vr. 2014  14:05:30
 */
@Service("refFonctionServiceImpl")
public class RefFonctionServiceImpl implements RefFonctionService {

	@Autowired
	@Qualifier("refFonctionDaoImpl")
	private RefFonctionDao refFonctionDaoImpl;
	@Autowired
	@Qualifier("mapper")
	private org.dozer.Mapper mapper;
	private static final Logger log = LoggerFactory.getLogger(RefFonctionServiceImpl.class.getName());
	/**
	 * [RefFonctionServiceImpl.RefFonctionServiceImpl()] Constructor
	 * @author MAKERRI Sofiane  on : 27 f�vr. 2014  14:05:30	
	 */
	public RefFonctionServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see dz.gov.mesrs.sii.referentiel.business.service.RefFonctionService#insertOrUpdate(dz.gov.mesrs.sii.referentiel.business.model.dto.RefFonctionDto)
	 */
	@Override
	public RefFonctionDto insertOrUpdate(RefFonctionDto refFonctionDto) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see dz.gov.mesrs.sii.referentiel.business.service.RefFonctionService#remove(dz.gov.mesrs.sii.referentiel.business.model.dto.RefFonctionDto)
	 */
	@Override
	public void remove(RefFonctionDto refFonctionDto) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see dz.gov.mesrs.sii.referentiel.business.service.RefFonctionService#findById(int)
	 */
	@Override
	public RefFonctionDto findById(int id) {
		RefFonctionDto refFonctionDto = null;
		try {
			RefFonction reffonction = refFonctionDaoImpl
					.findById(id);
			if (reffonction != null) {
				refFonctionDto = new RefFonctionDto();
				log.debug("get fonction found successfully with id = {}" , new Object[]{id});
				mapper.map(reffonction, refFonctionDto);

			}
		} catch (RuntimeException e) {
			log.error("get fonction failed", e);
			throw e;
		}
		return refFonctionDto;
	}

	/* (non-Javadoc)
	 * @see dz.gov.mesrs.sii.referentiel.business.service.RefFonctionService#findByQuery(java.lang.String)
	 */
	@Override
	public List<RefFonctionDto> findByQuery(String query) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see dz.gov.mesrs.sii.referentiel.business.service.RefFonctionService#findAll()
	 */
	@Override
	public List<RefFonctionDto> findAll() {
		List<RefFonctionDto> refFonctionDtoListDto = new ArrayList<RefFonctionDto>();
		try {
			List<RefFonction> refFonctionList = refFonctionDaoImpl
					.findAll();
			if (refFonctionList != null) {
				log.debug("find all fonction list success with size =  {}", new Object[]{refFonctionList.size()});

				for (RefFonction currentRefFonction : refFonctionList) {
					RefFonctionDto refFonctionDto = new RefFonctionDto();
					mapper.map(currentRefFonction, refFonctionDto);
					refFonctionDtoListDto.add(refFonctionDto);
					}
				}
		} catch (RuntimeException e) {
			log.error("find all fonction list failed", e);
			throw e;
		}
		return refFonctionDtoListDto;
	}

	/* (non-Javadoc)
	 * @see dz.gov.mesrs.sii.referentiel.business.service.RefFonctionService#findGeneric(java.lang.String)
	 */
	@Override
	public List<RefFonctionDto> findGeneric(String value) {
		List<RefFonctionDto> refFonctionDtoListDto = new ArrayList<RefFonctionDto>();
		try {
			List<RefFonction> refFonctionList = refFonctionDaoImpl
					.findGeneric(value);
			if (refFonctionList != null) {
				log.debug("fonction list success with size =  {}", new Object[]{refFonctionList.size()});

				for (RefFonction currentRefFonction : refFonctionList) {
					RefFonctionDto refFonctionDto = new RefFonctionDto();
					mapper.map(currentRefFonction, refFonctionDto);
					refFonctionDtoListDto.add(refFonctionDto);
					}
				}
		} catch (RuntimeException e) {
			log.error("get fonction list failed", e);
			throw e;
		}
		return refFonctionDtoListDto;
	}

	/* (non-Javadoc)
	 * @see dz.gov.mesrs.sii.referentiel.business.service.RefFonctionService#findByIdentifiant(java.lang.String)
	 */
	@Override
	public RefFonctionDto findByIdentifiant(String identifiant) {
		RefFonctionDto refFonctionDto = null;
		try {
			RefFonction refFonction = refFonctionDaoImpl
					.findByIdentifiant(identifiant);
			if (refFonction != null) {
				refFonctionDto = new RefFonctionDto();
				log.debug("fonction found successfully with identifiant = {}", new Object[]{identifiant});
				mapper.map(refFonction, refFonctionDto);

			}
		} catch (RuntimeException e) {
			log.error("get fonction list failed", e);
			throw e;
		}
		return refFonctionDto;
	}

	/* (non-Javadoc)
	 * @see dz.gov.mesrs.sii.referentiel.business.service.RefFonctionService#findByName(java.lang.String)
	 */
	@Override
	public RefFonctionDto findByName(Integer id, String name) {
		RefFonctionDto refFonctionDto = null;
		try {
			RefFonction refFonction = refFonctionDaoImpl
					.findByName(id, name);
			if (refFonction != null) {
				refFonctionDto = new RefFonctionDto();
				log.debug("RefFonctionDto found successfully with name = {}", new Object[]{name});
				mapper.map(refFonction, refFonctionDto);

			}
		} catch (RuntimeException e) {
			log.error("get RefFonctionDto list failed", e);
			throw e;
		}
		return refFonctionDto;
	}

	/* (non-Javadoc)
	 * @see dz.gov.mesrs.sii.referentiel.business.service.RefFonctionService#save(dz.gov.mesrs.sii.referentiel.business.model.dto.RefFonctionDto)
	 */
	@Override
	public void save(RefFonctionDto refFonctionDto) {
		RefFonction refFonction = new RefFonction();
		try {
			mapper.map(refFonctionDto, refFonction);
			refFonctionDaoImpl.persist(refFonction);
			log.debug("save refFonction refFonction");
		} catch (RuntimeException e) {
			log.error("save refFonction failed", e);
			throw e;
		}

	}

	/* (non-Javadoc)
	 * @see dz.gov.mesrs.sii.referentiel.business.service.RefFonctionService#update(dz.gov.mesrs.sii.referentiel.business.model.dto.RefFonctionDto)
	 */
	@Override
	public void update(RefFonctionDto refFonctionDto) {
		RefFonction refFonction = new RefFonction();
		mapper.map(refFonctionDto, refFonction);
		try {
			refFonctionDaoImpl.merge(refFonction);
			log.debug("update refFonction successful");
		} catch (RuntimeException e) {
			log.error("update refFonction failed", e);
			throw e;
		}

	}

	
	/* (non-Javadoc)
	 * @see dz.gov.mesrs.sii.referentiel.business.service.RefFonctionService#findFonctions(int)
	 */
	@Override
	public List<RefFonctionDto> findFonctions(int id) {
		List<RefFonctionDto> refFonctionDtoListDto = new ArrayList<RefFonctionDto>();
		try {
			List<RefFonction> refFonctionList = refFonctionDaoImpl
					.findFonctions(id);
			if (refFonctionList != null) {
				log.debug("fonction list success with size =  {}", new Object[]{refFonctionList.size()});

				for (RefFonction currentRefFonction : refFonctionList) {
					RefFonctionDto refFonctionDto = new RefFonctionDto();
					mapper.map(currentRefFonction, refFonctionDto);
					refFonctionDtoListDto.add(refFonctionDto);
					}
				}
		} catch (RuntimeException e) {
			log.error("get fonction list failed", e);
			throw e;
		}
		return refFonctionDtoListDto;
		
	}

	/**
	 * [RefFonctionServiceImpl.refFonctionDaoImpl] Getter 
	 * @author MAKERRI Sofiane on : 27 f�vr. 2014  14:59:54
	 * @return the refFonctionDaoImpl
	 */
	public RefFonctionDao getRefFonctionDaoImpl() {
		return refFonctionDaoImpl;
	}

	/**
	 * [RefFonctionServiceImpl.refFonctionDaoImpl] Setter 
	 * @author MAKERRI Sofiane on : 27 f�vr. 2014  14:59:54
	 * @param refFonctionDaoImpl the refFonctionDaoImpl to set
	 */
	public void setRefFonctionDaoImpl(RefFonctionDao refFonctionDaoImpl) {
		this.refFonctionDaoImpl = refFonctionDaoImpl;
	}

	/**
	 * [RefFonctionServiceImpl.mapper] Getter 
	 * @author MAKERRI Sofiane on : 27 f�vr. 2014  14:59:54
	 * @return the mapper
	 */
	public org.dozer.Mapper getMapper() {
		return mapper;
	}

	/**
	 * [RefFonctionServiceImpl.mapper] Setter 
	 * @author MAKERRI Sofiane on : 27 f�vr. 2014  14:59:54
	 * @param mapper the mapper to set
	 */
	public void setMapper(org.dozer.Mapper mapper) {
		this.mapper = mapper;
	}
	
	@Override
	public int findFonctionLastRang(int id) {
		try {
			int rang = refFonctionDaoImpl
					.findFonctionLastRang(id);
			log.info("get last rang of fonction success");
		    return rang;
		} catch (RuntimeException e) {
			log.error("get last rang of fonction failed", e);
			throw e;
		}
		
	}

	@Override
	public List<RefFonctionDto> findFonctionsOfDomaine(int idDomaine) {
		List<RefFonctionDto> refFonctionDtoListDto = new ArrayList<RefFonctionDto>();
		try {
			List<RefFonction> refFonctionList = refFonctionDaoImpl
					.findFonctionsOfDomaine(idDomaine);
			if (refFonctionList != null) {
				log.debug("fonction list success with size =  {}", new Object[]{refFonctionList.size()});

				for (RefFonction currentRefFonction : refFonctionList) {
					RefFonctionDto refFonctionDto = new RefFonctionDto();
					mapper.map(currentRefFonction, refFonctionDto);
					refFonctionDtoListDto.add(refFonctionDto);
					}
				}
		} catch (RuntimeException e) {
			log.error("get fonction list failed", e);
			throw e;
		}
		return refFonctionDtoListDto;
	}

	@Override
	public List<RefFonctionDto> findAllFonctions(String domaine) {
		List<RefFonctionDto> refFonctionDtoListDto = new ArrayList<RefFonctionDto>();
		try {
			List<RefFonction> refFonctionList = refFonctionDaoImpl
					.findAllFonctions(domaine);
			if (refFonctionList != null) {
				log.debug("fonction list success with size =  {}", new Object[]{refFonctionList.size()});

				for (RefFonction currentRefFonction : refFonctionList) {
					RefFonctionDto refFonctionDto = new RefFonctionDto();
					mapper.map(currentRefFonction, refFonctionDto);
					refFonctionDtoListDto.add(refFonctionDto);
					}
				}
		} catch (RuntimeException e) {
			log.error("get fonction list failed", e);
			throw e;
		}
		return refFonctionDtoListDto;
	}

	@Override
	public List<RefFonctionDto> findActionGeneric(String value) {
		List<RefFonctionDto> refFonctionDtoListDto = new ArrayList<RefFonctionDto>();
		try {
			List<RefFonction> refFonctionList = refFonctionDaoImpl
					.findActionGeneric(value);
			if (refFonctionList != null) {
				log.debug("action list success with size =  "
						+ refFonctionList.size());

				for (RefFonction currentRefFonction : refFonctionList) {
					RefFonctionDto refFonctionDto = new RefFonctionDto();
					mapper.map(currentRefFonction, refFonctionDto);
					refFonctionDtoListDto.add(refFonctionDto);
					}
				}
		} catch (RuntimeException e) {
			log.error("get action list failed", e);
			throw e;
		}
		return refFonctionDtoListDto;
	}

	@Override
	public int findActionLastRang(int id) {
		try {
			int rang = refFonctionDaoImpl
					.findActionLastRang(id);
			log.info("get last rang of action success");
		    return rang;
		} catch (RuntimeException e) {
			log.error("get last rang of action failed", e);
			throw e;
		}
		
	}

	@Override
	public List<RefFonctionDto> findActions(int id) {
		List<RefFonctionDto> refFonctionDtoListDto = new ArrayList<RefFonctionDto>();
		try {
			List<RefFonction> refFonctionList = refFonctionDaoImpl
					.findActions(id);
			if (refFonctionList != null) {
				log.debug("fonction list success with size =  {}", new Object[]{refFonctionList.size()});
				for (RefFonction currentRefFonction : refFonctionList) {
					RefFonctionDto refFonctionDto = new RefFonctionDto();
					mapper.map(currentRefFonction, refFonctionDto);
					refFonctionDtoListDto.add(refFonctionDto);
					}
				}
		} catch (RuntimeException e) {
			log.error("get fonction list failed", e);
			throw e;
		}
		return refFonctionDtoListDto;
	}

	@Override
	public List<RefFonctionDto> findAllActions() {
		List<RefFonctionDto> refFonctionDtoListDto = new ArrayList<RefFonctionDto>();
		try {
			List<RefFonction> refFonctionList = refFonctionDaoImpl
					.findAllActions();
			if (refFonctionList != null) {
				log.debug("fonction list success with size =  {}", new Object[]{refFonctionList.size()});

				for (RefFonction currentRefFonction : refFonctionList) {
					RefFonctionDto refFonctionDto = new RefFonctionDto();
					mapper.map(currentRefFonction, refFonctionDto);
					refFonctionDtoListDto.add(refFonctionDto);
					}
				}
		} catch (RuntimeException e) {
			log.error("get fonction list failed", e);
			throw e;
		}
		return refFonctionDtoListDto;
	}

	@Override
	public RefFonctionDto findPeriode(int idPeriode) {
		RefFonctionDto refFonctionDto = null;
		try {
			RefFonction refFonction = refFonctionDaoImpl
					.findPeriode(idPeriode);
			if (refFonction != null) {
				refFonctionDto = new RefFonctionDto();
				log.debug("RefFonctionDto found successfully with id = "
						+ idPeriode);
				mapper.map(refFonction, refFonctionDto);

			}
		} catch (RuntimeException e) {
			log.error("get RefFonctionDto list failed", e);
			throw e;
		}
		return refFonctionDto;
	}

	@Override
	public String generateIdentify(String prefix, int orderLength) {
		try {
			if (prefix == null) {
				return null;
			}

			Integer lastOrder = refFonctionDaoImpl.findLastOrder(prefix,
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
	public RefFonctionDto findByNameArabe(Integer id, String name) {
		RefFonctionDto refFonctionDto = null;
		try {
			RefFonction refFonction = refFonctionDaoImpl
					.findByNameArabe(id, name);
			if (refFonction != null) {
				refFonctionDto = new RefFonctionDto();
				log.debug("RefFonctionDto found successfully with name = "
						+ name);
				mapper.map(refFonction, refFonctionDto);

			}
		} catch (RuntimeException e) {
			log.error("get RefFonctionDto list failed", e);
			throw e;
		}
		return refFonctionDto;
	}
	

}
