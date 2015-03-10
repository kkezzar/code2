/**
 * 
 */
package dz.gov.mesrs.sii.referentiel.business.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefHoraireAccessDao;
import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefSituationDao;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefHoraireAccess;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefSituation;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefHoraireAccessDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefHoraireAccessService;
import dz.gov.mesrs.sii.referentiel.business.util.RefUtilConstant;


/**
 * @author BELDI Jamel  on : 24 f�vr. 2014  10:18:10
 */
@Service ("refHoraireAccessServiceImpl")
public class RefHoraireAccessServiceImpl implements RefHoraireAccessService{
	private static final Log log = LogFactory.getLog(RefHoraireAccessServiceImpl.class);
	@Autowired
	@Qualifier("refHoraireAccessDaoImpl")
	private RefHoraireAccessDao refHoraireAccessDao;
	@Autowired
	@Qualifier("mapper")
	private org.dozer.Mapper mapper;
	@Autowired
	@Qualifier("refSituationDaoImpl")
	private RefSituationDao refSituationDao;

	/**
	 * @return the refHoraireAccessDao
	 */
	public RefHoraireAccessDao getRefHoraireAccessDao() {
		return refHoraireAccessDao;
	}

	/**
	 * @param refHoraireAccessDao the refHoraireAccessDao to set
	 */
	public void setRefHoraireAccessDao(RefHoraireAccessDao refHoraireAccessDao) {
		this.refHoraireAccessDao = refHoraireAccessDao;
	}
	
	/**
	 * @return the refSituationDao
	 */
	public RefSituationDao getRefSituationDao() {
		return refSituationDao;
	}

	/**
	 * @param refSituationDao
	 *            the refSituationDao to set
	 */
	public void setRefSituationDao(RefSituationDao refSituationDao) {
		this.refSituationDao = refSituationDao;
	}

	/**
	 * 
	 */
	public RefHoraireAccessServiceImpl() {
	}

	@Override
	public RefHoraireAccessDto findById(Integer id) {
		RefHoraireAccessDto refHoraireAccessDto = null;
		try{
			RefHoraireAccess refHoraireAccess = refHoraireAccessDao.findById(id);
			if(refHoraireAccess != null){
				refHoraireAccessDto = new RefHoraireAccessDto();
 				 mapper.map(refHoraireAccess, refHoraireAccessDto);
				log.info("success get dto: "+refHoraireAccessDto.getIdentifiant());
				return refHoraireAccessDto;
			}
		}catch(Exception e){
			log.error("get failed", e);
			
		}
		return refHoraireAccessDto;
	}

	/* (non-Javadoc)
	 * @see dz.gov.mesrs.sii.referentiel.business.service.RefHoraireAccessService#findBAll()
	 */
	@Override
	public List<RefHoraireAccessDto> findAll() {
		List<RefHoraireAccessDto> refHoraireAccessListDto = new ArrayList<RefHoraireAccessDto>();
		try {
			List<RefHoraireAccess> refHoraireAccessList = refHoraireAccessDao.findAll();
			if (refHoraireAccessList != null) {
				log.debug("individu list success with size =  "
						+ refHoraireAccessList.size());
				for (RefHoraireAccess currentRefHoraireAccess : refHoraireAccessList) {
					RefHoraireAccessDto refHoraireAccessDto = new RefHoraireAccessDto();
					mapper.map(currentRefHoraireAccess, refHoraireAccessDto);
					refHoraireAccessListDto.add(refHoraireAccessDto);
				}
				return refHoraireAccessListDto;
			}
		} catch (RuntimeException e) {
			log.error("get individu list failed", e);
			throw e;
		}

		return null;
	}

	/**
	 * [RefHoraireAccessServiceImpl.mapper] Getter 
	 * @author MAKERRI Sofiane on : 6 janv. 2014  17:04:29
	 * @return the mapper
	 */
	public org.dozer.Mapper getMapper() {
		return mapper;
	}

	/**
	 * [RefHoraireAccessServiceImpl.mapper] Setter 
	 * @author MAKERRI Sofiane on : 6 janv. 2014  17:04:29
	 * @param mapper the mapper to set
	 */
	public void setMapper(org.dozer.Mapper mapper) {
		this.mapper = mapper;
	}
	
	
	
	@Override
	public List<RefHoraireAccessDto> findGeneric(String value) {
		List<RefHoraireAccessDto> refHoraireAccessListDto = new ArrayList<RefHoraireAccessDto>();
		try {
			List<RefHoraireAccess> refHoraireAccessList = refHoraireAccessDao.findGeneric(value);
			if (refHoraireAccessList != null) {
				log.debug("Plage Horaire list success with size =  "
						+ refHoraireAccessList.size());

				for (RefHoraireAccess currentRefHoraireAccess : refHoraireAccessList) {
					RefSituation refCurrentSituation = refSituationDao
							.findCurrentSituation(
									currentRefHoraireAccess.getId(),
									RefHoraireAccess.class.getSimpleName());
					RefHoraireAccessDto refHoraireAccessDto = new RefHoraireAccessDto();
					mapper.map(currentRefHoraireAccess, refHoraireAccessDto);
					if (refCurrentSituation != null) {
						mapper.map(refCurrentSituation, refHoraireAccessDto);
					}
					refHoraireAccessListDto.add(refHoraireAccessDto);
				}

			}
		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}
		return refHoraireAccessListDto;
	}
	

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void save(RefHoraireAccessDto refHoraireAccessDto) {
		RefHoraireAccess refHoraireAccess = new RefHoraireAccess();
		mapper.map(refHoraireAccessDto, refHoraireAccess);
		try {
			refHoraireAccessDao.persist(refHoraireAccess);
			log.debug("save successful");
		} catch (RuntimeException e) {
			log.error("save failed", e);
			throw e;
		}
		}
		

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void update(RefHoraireAccessDto refHoraireAccessDto) {
		RefHoraireAccess refHoraireAccess = new RefHoraireAccess();
		mapper.map(refHoraireAccessDto, refHoraireAccess);
		try {
			refHoraireAccessDao.merge(refHoraireAccess);
			log.debug("update successful");
		} catch (RuntimeException e) {
			log.error("update failed", e);
			throw e;
		}
	
	}

	@Override
	public void saveOrUpdate(RefHoraireAccessDto refHoraireAccessDto) {
		// TODO Auto-generated method stub
		
	}


	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void delete(RefHoraireAccessDto refHoraireAccessDto) {
		RefHoraireAccess refHoraireAccess = new RefHoraireAccess();
		try {
			mapper.map(refHoraireAccessDto, refHoraireAccess);
			refHoraireAccess=refHoraireAccessDao.merge(refHoraireAccess);
			refHoraireAccessDao.remove(refHoraireAccess);
			log.debug("delete successful");
		} catch (RuntimeException e) {
			log.error("delete failed", e);
			throw e;
		}

	}

	@Override
	public boolean verifyHoraireAcces(Date heureAcces, Date heureDebut,
			Date heureFin) {
		  log.info("verifyHoraireAcces" );
		  try {
			  if(heureAcces!=null && heureDebut!=null && heureFin!=null){
				  if(heureAcces.after(heureDebut)&&heureAcces.before(heureFin)){
					  return true;
				  }
			  }
					return false;
			} catch (RuntimeException e) {
				log.error("verifyHoraireAcces failed", e);
				throw e;
			}
	}


	/***
	 * 
	 * [RefPlageAdresseServiceImpl.main] Method 
	 * @author BELDI Jamel  on : 4 mars 2014  10:17:59
	 * @param test
	 */
	
	  public static void main (String [] arg){
		 

		  RefHoraireAccessServiceImpl service = new RefHoraireAccessServiceImpl();
		  Date heureAcces = new Date("1970/01/01 15:12:00");
		  Date heureDebut = new Date("1970/01/01 00:00:00");
		  Date heureFin = new Date("1970/03/01 23:59:00");
	    	try {
				log.info(service.verifyHoraireAcces(heureAcces, heureDebut, heureFin));
			} catch (Exception e) {
				
				e.printStackTrace();
			}
	    }

	@Override
	public String generateIdentify(String prefix, int orderLength) {
		try {
			if (prefix == null) {
				return null;
			}

			Integer lastOrder = refHoraireAccessDao.findLastOrder(prefix,
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
}
