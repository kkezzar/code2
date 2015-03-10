/**
 * 
 */
package dz.gov.mesrs.sii.referentiel.business.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefPlageAdresseDao;
import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefSituationDao;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefPlageAdresse;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefSituation;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefPlageAdresseDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefPlageAdresseService;
import dz.gov.mesrs.sii.referentiel.business.util.RefUtilConstant;

/**
 * @author BELDI Jamel on : 24 f�vr. 2014 10:18:10
 */
@Service("refPlageAdresseServiceImpl")
public class RefPlageAdresseServiceImpl implements RefPlageAdresseService {
	private static final Logger log = LoggerFactory.getLogger(RefPlageAdresseServiceImpl.class.getName());
	@Autowired
	@Qualifier("refPlageAdresseDaoImpl")
	private RefPlageAdresseDao refPlageAdresseDao;
	@Autowired
	@Qualifier("mapper")
	private org.dozer.Mapper mapper;
	@Autowired
	@Qualifier("refSituationDaoImpl")
	private RefSituationDao refSituationDao;

	/**
	 * @return the refPlageAdresseDao
	 */
	public RefPlageAdresseDao getRefPlageAdresseDao() {
		return refPlageAdresseDao;
	}

	/**
	 * @param refPlageAdresseDao
	 *            the refPlageAdresseDao to set
	 */
	public void setRefPlageAdresseDao(RefPlageAdresseDao refPlageAdresseDao) {
		this.refPlageAdresseDao = refPlageAdresseDao;
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
	public RefPlageAdresseServiceImpl() {
	}

	@Override
	public RefPlageAdresseDto findById(Integer id) {
		RefPlageAdresseDto refPlageAdresseDto = null;
		try {
			RefPlageAdresse refPlageAdresse = refPlageAdresseDao.findById(id);
			if (refPlageAdresse != null) {
				refPlageAdresseDto = new RefPlageAdresseDto();
				mapper.map(refPlageAdresse, refPlageAdresseDto);
				log.info("success get dto: "
						+ refPlageAdresseDto.getIdentifiant());
				return refPlageAdresseDto;
			}
		} catch (Exception e) {
			log.error("get failed", e);

		}
		return refPlageAdresseDto;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.referentiel.business.service.RefPlageAdresseService#
	 * findBAll()
	 */
	@Override
	public List<RefPlageAdresseDto> findAll() {
		List<RefPlageAdresseDto> refPlageAdresseListDto = new ArrayList<RefPlageAdresseDto>();
		try {
			List<RefPlageAdresse> refPlageAdresseList = refPlageAdresseDao
					.findAll();
			if (refPlageAdresseList != null) {
				log.debug("individu list success with size =  {}", new Object[]{refPlageAdresseList.size()});
				for (RefPlageAdresse currentRefPlageAdresse : refPlageAdresseList) {
					RefPlageAdresseDto refPlageAdresseDto = new RefPlageAdresseDto();
					mapper.map(currentRefPlageAdresse, refPlageAdresseDto);
					refPlageAdresseListDto.add(refPlageAdresseDto);
				}
				return refPlageAdresseListDto;
			}
		} catch (RuntimeException e) {
			log.error("get individu list failed", e);
			throw e;
		}

		return null;
	}

	/**
	 * [RefPlageAdresseServiceImpl.mapper] Getter
	 * 
	 * @author MAKERRI Sofiane on : 6 janv. 2014 17:04:29
	 * @return the mapper
	 */
	public org.dozer.Mapper getMapper() {
		return mapper;
	}

	/**
	 * [RefPlageAdresseServiceImpl.mapper] Setter
	 * 
	 * @author MAKERRI Sofiane on : 6 janv. 2014 17:04:29
	 * @param mapper
	 *            the mapper to set
	 */
	public void setMapper(org.dozer.Mapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public List<RefPlageAdresseDto> findGeneric(String value) {
		List<RefPlageAdresseDto> refPlageAdresseListDto = new ArrayList<RefPlageAdresseDto>();
		try {
			List<RefPlageAdresse> refPlageAdresseList = refPlageAdresseDao
					.findGeneric(value);
			if (refPlageAdresseList != null) {
				log.debug("Plage Adresse list success with size = {} ", new Object[]{refPlageAdresseList.size()});

				for (RefPlageAdresse currentRefPlageAdresse : refPlageAdresseList) {
					RefSituation refCurrentSituation = refSituationDao
							.findCurrentSituation(
									currentRefPlageAdresse.getId(),
									RefPlageAdresse.class.getSimpleName());
					RefPlageAdresseDto refPlageAdresseDto = new RefPlageAdresseDto();
					mapper.map(currentRefPlageAdresse, refPlageAdresseDto);
					if (refCurrentSituation != null) {
						mapper.map(refCurrentSituation, refPlageAdresseDto);
					}
					refPlageAdresseListDto.add(refPlageAdresseDto);
				}

			}
		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}
		return refPlageAdresseListDto;
	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public RefPlageAdresseDto save(RefPlageAdresseDto refPlageAdresseDto) {
		RefPlageAdresse refPlageAdresse = new RefPlageAdresse();
		mapper.map(refPlageAdresseDto, refPlageAdresse);
		try {
			refPlageAdresseDao.persist(refPlageAdresse);
			mapper.map(refPlageAdresse, refPlageAdresseDto);
			log.debug("save successful");
		} catch (RuntimeException e) {
			log.error("save failed", e);
			throw e;
		}
		return refPlageAdresseDto;
	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void update(RefPlageAdresseDto refPlageAdresseDto) {
		RefPlageAdresse refPlageAdresse = new RefPlageAdresse();
		mapper.map(refPlageAdresseDto, refPlageAdresse);
		try {
			refPlageAdresseDao.merge(refPlageAdresse);
			log.debug("update successful");
		} catch (RuntimeException e) {
			log.error("update failed", e);
			throw e;
		}

	}

	@Override
	public void saveOrUpdate(RefPlageAdresseDto refPlageAdresseDto) {
		// TODO Auto-generated method stub

	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void delete(RefPlageAdresseDto refPlageAdresseDto) {
		RefPlageAdresse refPlageAdresse = new RefPlageAdresse();
		try {
			mapper.map(refPlageAdresseDto, refPlageAdresse);
			refPlageAdresse = refPlageAdresseDao.merge(refPlageAdresse);
			refPlageAdresseDao.remove(refPlageAdresse);
			log.debug("delete successful");
		} catch (RuntimeException e) {
			log.error("delete failed", e);
			throw e;
		}

	}

	@Override
	public boolean verifyUserIp(String userAddr, String adresseDebut,
			String adresseFin) {
		try {
			if (userAddr != null && adresseDebut != null && adresseFin != null) {
				String delim = new String(".");

				StringTokenizer userAddrTab = new StringTokenizer(userAddr,
						delim);
				StringTokenizer adresseDebutTab = new StringTokenizer(
						adresseDebut, delim);
				StringTokenizer adresseFinTab = new StringTokenizer(adresseFin,
						delim);
				log.info(userAddr + ":" + userAddrTab.countTokens());
				if (userAddrTab.countTokens() == 4
						&& adresseDebutTab.countTokens() == 4
						&& adresseFinTab.countTokens() == 4) {
					Integer value;
					Integer min;
					Integer max;
					boolean skipMin = false;
					boolean skipMax = false;
					while (userAddrTab.hasMoreTokens()) {
						value = Integer.valueOf(userAddrTab.nextToken());
						min = Integer.valueOf(adresseDebutTab.nextToken());
						max = Integer.valueOf(adresseFinTab.nextToken());
						if (value != null && min != null && max != null
								&& 0 <= value && value <= 255 && 0 <= min
								&& min <= 255 && 0 <= max && max <= 255) {
							if (value < min && !skipMin) {
								return false;
							}
							if (value > max && !skipMax) {
								return false;
							}
							if (value > min && !skipMin) {
								skipMin = true;
							}
							if (value < max && !skipMin) {
								skipMax = true;
							}

						} else {
							return false;
						}
					}
					log.debug("verifyUserIp successful");
					return true;
				}

			}
			return false;

		} catch (RuntimeException e) {
			log.error("verifyUserIp failed", e);
			throw e;
		}
	}

	/***
	 * 
	 * [RefPlageAdresseServiceImpl.main] Method
	 * 
	 * @author BELDI Jamel on : 4 mars 2014 10:17:59
	 * @param test
	 */

	/***
	 * 
	 * [RefPlageAdresseServiceImpl.main] Method
	 * 
	 * @author BELDI Jamel on : 4 mars 2014 10:17:59
	 * @param test
	 */

	public static void main(String[] arg) {

		RefPlageAdresseServiceImpl service = new RefPlageAdresseServiceImpl();
		String userAdress = "192.168.3.103";
		String adrdebut = "000.000.000.000";
		String adrfin = "255.255.255.255";
		try {
			log.info(""+service.verifyUserIp(userAdress, adrdebut, adrfin));
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	@Override
	public List<RefPlageAdresseDto> findAll(Integer etabId) {
		List<RefPlageAdresseDto> refPlageAdresseListDto = new ArrayList<RefPlageAdresseDto>();
		try {
			List<RefPlageAdresse> refPlageAdresseList = refPlageAdresseDao
					.findAll(etabId);
			if (refPlageAdresseList != null) {
				log.debug("individu list success with size =  {}", new Object[]{refPlageAdresseList.size()});
				for (RefPlageAdresse currentRefPlageAdresse : refPlageAdresseList) {
					RefPlageAdresseDto refPlageAdresseDto = new RefPlageAdresseDto();
					mapper.map(currentRefPlageAdresse, refPlageAdresseDto);
					refPlageAdresseListDto.add(refPlageAdresseDto);
				}
				return refPlageAdresseListDto;
			}
		} catch (RuntimeException e) {
			log.error("get individu list failed", e);
			throw e;
		}

		return null;

	}

	@Override
	public List<RefPlageAdresseDto> findGeneric(Integer etabId, String value) {
		List<RefPlageAdresseDto> refPlageAdresseListDto = new ArrayList<RefPlageAdresseDto>();
		try {
			List<RefPlageAdresse> refPlageAdresseList = refPlageAdresseDao
					.findGeneric(etabId, value);
			if (refPlageAdresseList != null) {
				log.debug("Plage Adresse list success with size =  {}", new Object[]{refPlageAdresseList.size()});

				for (RefPlageAdresse currentRefPlageAdresse : refPlageAdresseList) {
					RefSituation refCurrentSituation = refSituationDao
							.findCurrentSituation(
									currentRefPlageAdresse.getId(),
									RefPlageAdresse.class.getSimpleName());
					RefPlageAdresseDto refPlageAdresseDto = new RefPlageAdresseDto();
					mapper.map(currentRefPlageAdresse, refPlageAdresseDto);
					if (refCurrentSituation != null) {
						mapper.map(refCurrentSituation, refPlageAdresseDto);
					}
					refPlageAdresseListDto.add(refPlageAdresseDto);
				}

			}
		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}
		return refPlageAdresseListDto;
	}

	@Override
	public String generateIdentify(String prefix, int orderLength) {
		try {
			if (prefix == null) {
				return null;
			}

			Integer lastOrder = refPlageAdresseDao.findLastOrder(prefix,
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
