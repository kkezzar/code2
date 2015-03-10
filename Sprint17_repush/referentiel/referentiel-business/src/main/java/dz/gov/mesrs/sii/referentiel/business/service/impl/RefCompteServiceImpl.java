/**
 * 
 */
package dz.gov.mesrs.sii.referentiel.business.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefCompteDao;
import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefSituationDao;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefCompte;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefEntiteSituation;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefIndividu;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefSituation;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefCompteDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefIndividuDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefCompteService;
import dz.gov.mesrs.sii.referentiel.business.service.RefParametrageService;

/**
 * @author BELDI Jamel on : 24 f�vr. 2014 10:18:10
 */
/**
 * @author MAKERRI Sofiane  on : 4 sept. 2014  17:31:32
 */
@Service("refCompteServiceImpl")
public class RefCompteServiceImpl implements RefCompteService {
	private static final Logger log = LoggerFactory.getLogger(RefCompteServiceImpl.class.getName());
	@Autowired
	@Qualifier("refCompteDaoImpl")
	private RefCompteDao refCompteDao;
	@Autowired
	@Qualifier("mapper")
	private org.dozer.Mapper mapper;
	@Autowired
	@Qualifier("refSituationDaoImpl")
	private RefSituationDao refSituationDao;
	@Autowired
	@Qualifier("refParametrageServiceImpl")
	private RefParametrageService refParametrageServiceImpl;

	/**
	 * [RefEvenementServiceImpl.refParametrageServiceImpl] Getter
	 * 
	 * @author BELDI Jamel on : 7 avr. 2014 14:59:45
	 * @return the refParametrageServiceImpl
	 */
	public RefParametrageService getRefParametrageServiceImpl() {
		return refParametrageServiceImpl;
	}

	/**
	 * [RefEvenementServiceImpl.refParametrageServiceImpl] Setter
	 * 
	 * @author BELDI Jamel on : 7 avr. 2014 14:59:45
	 * @param refParametrageServiceImpl
	 *            the refParametrageServiceImpl to set
	 */
	public void setRefParametrageServiceImpl(
			RefParametrageService refParametrageServiceImpl) {
		this.refParametrageServiceImpl = refParametrageServiceImpl;
	}

	/**
	 * @return the refCompteDao
	 */
	public RefCompteDao getRefCompteDao() {
		return refCompteDao;
	}

	/**
	 * @param refCompteDao
	 *            the refCompteDao to set
	 */
	public void setRefCompteDao(RefCompteDao refCompteDao) {
		this.refCompteDao = refCompteDao;
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
	public RefCompteServiceImpl() {
	}

	/**
	 * find account BY Id
	 * 
	 * @see dz.gov.mesrs.sii.referentiel.business.service.RefCompteService#findById(java.lang.Integer)
	 */
	@Override
	public RefCompteDto findById(Integer id) {
		RefCompteDto refCompteDto = null;
		try {
			RefCompte refCompte = refCompteDao.findById(id);
			if (refCompte != null) {
				refCompteDto = new RefCompteDto();
				mapper.map(refCompte, refCompteDto);
				log.info("success get dto: " + refCompteDto.getNomUtilisateur());
				return refCompteDto;
			}
		} catch (Exception e) {
			log.error("get failed", e);

		}
		return refCompteDto;
	}

	/**
	 * find all account
	 * 
	 * @see dz.gov.mesrs.sii.referentiel.business.service.RefCompteService#findBAll()
	 */
	@Override
	public List<RefCompteDto> findAll() {
		List<RefCompteDto> refCompteListDto = new ArrayList<RefCompteDto>();
		try {
			List<RefCompte> refCompteList = refCompteDao.findAll();
			if (refCompteList != null) {
				log.debug("individu list success with size {}  ", new Object[]{ refCompteList.size()});
				for (RefCompte currentRefCompte : refCompteList) {
					RefCompteDto refCompteDto = new RefCompteDto();
					mapper.map(currentRefCompte, refCompteDto);
					refCompteListDto.add(refCompteDto);
				}
				return refCompteListDto;
			}
		} catch (RuntimeException e) {
			log.error("get individu list failed", e);
			throw e;
		}

		return null;
	}

	/**
	 * [RefCompteServiceImpl.mapper] Getter
	 * 
	 * @author MAKERRI Sofiane on : 6 janv. 2014 17:04:29
	 * @return the mapper
	 */
	public org.dozer.Mapper getMapper() {
		return mapper;
	}

	/**
	 * [RefCompteServiceImpl.mapper] Setter
	 * 
	 * @author MAKERRI Sofiane on : 6 janv. 2014 17:04:29
	 * @param mapper
	 *            the mapper to set
	 */
	public void setMapper(org.dozer.Mapper mapper) {
		this.mapper = mapper;
	}

	/**
	 * find list of account by string query
	 * 
	 * @see dz.gov.mesrs.sii.referentiel.business.service.RefCompteService#findGeneric(java.lang.String)
	 */
	@Override
	public List<RefCompteDto> findGeneric(String value) {
		List<RefCompteDto> refCompteListDto = new ArrayList<RefCompteDto>();
		try {
			List<RefCompte> refCompteList = refCompteDao.findGeneric(value);
			if (refCompteList != null) {
				log.debug("compte list success with size {} ", new Object[]{refCompteList.size()});

				for (RefCompte currentRefCompte : refCompteList) {
					RefSituation refCurrentSituation = refSituationDao
							.findCurrentSituation(currentRefCompte.getId(),
									RefCompte.class.getSimpleName());
					RefCompteDto refCompteDto = new RefCompteDto();
					mapper.map(currentRefCompte, refCompteDto);
					if (refCurrentSituation != null) {
						mapper.map(refCurrentSituation, refCompteDto);
					}
					refCompteListDto.add(refCompteDto);
				}

			}
		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}
		return refCompteListDto;

	}

	/**
	 * find list of account by many criteria
	 * 
	 * @see dz.gov.mesrs.sii.referentiel.business.service.RefCompteService#findAdvanced(dz.gov.mesrs.sii.referentiel.business.model.dto.RefCompteDto)
	 */
	@Override
	public List<RefCompteDto> findAdvanced(RefCompteDto refCompteDto) {

		List<RefCompteDto> refCompteListDto = new ArrayList<RefCompteDto>();
		try {
			RefCompte refCompte = new RefCompte();
			mapper.map(refCompteDto, refCompte);
			List<RefCompte> refCompteList = refCompteDao
					.findAdvanced(refCompte);
			if (refCompteList != null && !refCompteList.isEmpty()) {
				log.debug("compte list success with size {}  ", new Object[]{ refCompteList.size()});

				for (RefCompte currentRefCompte : refCompteList) {
					RefCompteDto currentRefCompteDto = new RefCompteDto();
					RefSituation refCurrentSituation = refSituationDao
							.findCurrentSituation(currentRefCompte.getId(),
									RefCompte.class.getSimpleName());
					mapper.map(currentRefCompte, currentRefCompteDto);
					if (refCurrentSituation != null) {
						mapper.map(refCurrentSituation, currentRefCompteDto);
					}
					refCompteListDto.add(currentRefCompteDto);
				}

			}
		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}
		return refCompteListDto;
	}

	/**
	 * save account
	 * 
	 * @see dz.gov.mesrs.sii.referentiel.business.service.RefCompteService#save(dz.gov.mesrs.sii.referentiel.business.model.dto.RefCompteDto)
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public Integer save(RefCompteDto refCompteDto) {
		RefCompte refCompte = new RefCompte();
		String username = generateUsername(
				refCompteDto.getIndividuNomLatin(),
				refCompteDto.getIndividuPrenomLatin());
		if (username == null) {
			return null;
		}
		refCompteDto.setNomUtilisateur(username);
		mapper.map(refCompteDto, refCompte);
		try {
			RefSituation refSituation = new RefSituation();
			
			refCompteDao.persist(refCompte);
			refSituation.setDateSituation(new Date());
			refSituation.setIdEntite(refCompte.getId());
			refSituation.setNomEntite(RefCompte.class.getSimpleName());
			RefEntiteSituation refEntiteSituation = new RefEntiteSituation();
			refEntiteSituation.setId(21);
			/*Integer.parseInt(refParametrageServiceImpl
					.getParamConfiguration(
							UtilConstant.COMPTE_SITUATION_CREE_ID_KEY,
							UtilConstant.CONFIGURATION_UTIL).getValue()));*/
			refSituation.setRefEntiteSituation(refEntiteSituation);
			refSituationDao.persist(refSituation);
			log.debug("save successful");
			return refCompte.getId();
		} catch (RuntimeException e) {
			log.error("save failed", e);
			throw e;
		}
	}

	/**
	 * update account
	 * 
	 * @see dz.gov.mesrs.sii.referentiel.business.service.RefCompteService#update(dz.gov.mesrs.sii.referentiel.business.model.dto.RefCompteDto)
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void update(RefCompteDto refCompteDto) {
		RefCompte refCompte = new RefCompte();
		mapper.map(refCompteDto, refCompte);
		try {
			if (refCompte.getRefIndividu().getId() == 0) {
				refCompte.setRefIndividu(null);
			}
			refCompteDao.merge(refCompte);
			log.debug("update successful");
		} catch (RuntimeException e) {
			log.error("update failed", e);
			throw e;
		}

	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub

	}

	/**
	 * get compte of user
	 * 
	 * @see dz.gov.mesrs.sii.referentiel.business.service.RefCompteService#findByUser(java.lang.String)
	 */
	@Override
	public RefCompteDto findByUser(Integer individuId) {

		try {
			RefCompte refCompte = refCompteDao.findByUser(individuId);
			if (refCompte != null) {
				log.debug("find compte of user success");

				RefSituation refCurrentSituation = refSituationDao
						.findCurrentSituation(refCompte.getId(),
								RefCompte.class.getSimpleName());
				RefCompteDto refCompteDto = new RefCompteDto();
				mapper.map(refCompte, refCompteDto);
				
				//refCompteDto.setMotPasseNonCrypte(decryptAES(refCompteDto.getMotPasse()));
				if (refCurrentSituation != null) {
					mapper.map(refCurrentSituation, refCompteDto);
				}

				return refCompteDto;
			}
		} catch (RuntimeException e) {
			log.error("get compte of user failed", e);
			throw e;
		}
		catch (Exception ex) {
			log.error("get compte of user failed", ex);
			return null;
		}
		return null;
	}

	/**
	 * find compte by login
	 * 
	 * @see dz.gov.mesrs.sii.referentiel.business.service.RefCompteService#findByLogin(java.lang.String)
	 */
	@Override
	public RefCompteDto findByLogin(String nomUtilisateur) {
		try {
			RefCompte refCompte = refCompteDao.findByLogin(nomUtilisateur);
			if (refCompte != null) {
				log.debug("find compte by login success");
				RefSituation refCurrentSituation = refSituationDao
						.findCurrentSituation(refCompte.getId(),
								RefCompte.class.getSimpleName());
				RefCompteDto refCompteDto = new RefCompteDto();
				mapper.map(refCompte, refCompteDto);
				if (refCurrentSituation != null) {
					mapper.map(refCurrentSituation, refCompteDto);
				}

				return refCompteDto;
			}
		} catch (RuntimeException e) {
			log.error("get compte by login failed", e);
			throw e;
		}
		return null;
	}

	// @Override
	// public String generateUsername(String prefix) {
	// try {
	// if (prefix == null) {
	// return null;
	// }
	// Integer lastOrder = refCompteDao.generateUsername(prefix);
	// if (lastOrder == null) {
	// return null;
	// }
	// if (lastOrder == -1) {
	// return prefix;
	// }
	// lastOrder++;
	// prefix = prefix + lastOrder;
	// log.debug("calculateIdentify successful");
	// } catch (RuntimeException e) {
	// log.error("calculateIdentify failed", e);
	// throw e;
	// }
	// return prefix;
	// }

	@Override
	public List<RefIndividuDto> findByEtablissement(String nom, String prenom,
			Integer idEtab) {
		List<RefIndividuDto> refIndividuDtos = new ArrayList<RefIndividuDto>();
		try {
			if (nom == null || prenom == null) {
				return null;
			}
			List<RefIndividu> refIndividuList = refCompteDao
					.findByEtablissement(nom, prenom, idEtab);
			if (refIndividuList != null && !refIndividuList.isEmpty()) {
				log.debug("findByEtablissement success with size {}  ", new Object[]{ refIndividuList.size()});

				for (RefIndividu currentRefIndividu : refIndividuList) {
					RefIndividuDto currentRefIndividuDto = new RefIndividuDto();
					// RefSituation refCurrentSituation = refSituationDao
					// .findCurrentSituation(
					// currentRefIndividu.getId(),
					// RefIndividu.class.getSimpleName());
					mapper.map(currentRefIndividu, currentRefIndividuDto);
					// if (refCurrentSituation != null) {
					// mapper.map(refCurrentSituation, currentRefIndividuDto);
					// }
					refIndividuDtos.add(currentRefIndividuDto);
				}

			}
		} catch (RuntimeException e) {
			log.error("findByEtablissement failed", e);
			throw e;
		}
		return refIndividuDtos;
	}

	@Override
	public List<RefCompteDto> findGeneric(Integer etabId, String value) {
		List<RefCompteDto> refCompteListDto = new ArrayList<RefCompteDto>();
		try {
			List<RefCompte> refCompteList = refCompteDao.findGeneric(etabId,
					value);
			if (refCompteList != null) {
				log.debug("compte list success with size {}  ", new Object[]{ refCompteList.size()});

				for (RefCompte currentRefCompte : refCompteList) {
					RefSituation refCurrentSituation = refSituationDao
							.findCurrentSituation(currentRefCompte.getId(),
									RefCompte.class.getSimpleName());
					RefCompteDto refCompteDto = new RefCompteDto();
					mapper.map(currentRefCompte, refCompteDto);
					if (refCurrentSituation != null) {
						mapper.map(refCurrentSituation, refCompteDto);
					}
					refCompteListDto.add(refCompteDto);
				}

			}
		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}
		return refCompteListDto;
	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public RefCompteDto insertOrUpdate(RefCompteDto refCompteDto) {
		try {
			if (refCompteDto.getIdCompte() == null) {
				String username = generateUsername(
						refCompteDto.getIndividuNomLatin(),
						refCompteDto.getIndividuPrenomLatin());
				if (username == null) {
					return null;
				}
				refCompteDto.setNomUtilisateur(username);
			}
			RefCompte refCompte = mapper.map(refCompteDto, RefCompte.class);
			String motPassenonNonCrypte = refCompteDto.getMotPasse();
			refCompte.setMotPasse(encryptAES(refCompteDto.getMotPasse()));
			if (refCompte.getId() == 0) {
				refCompteDao.persist(refCompte);
				RefSituation refSituation = new RefSituation();
				refSituation.setDateSituation(new Date());
				refSituation.setIdEntite(refCompte.getId());
				refSituation.setNomEntite(RefCompte.class.getSimpleName());
				RefEntiteSituation refEntiteSituation = new RefEntiteSituation();
				refEntiteSituation
						.setId(21);/*Integer
								.parseInt(refParametrageServiceImpl
										.getParamConfiguration(
												UtilConstant.COMPTE_SITUATION_CREE_ID_KEY,
												UtilConstant.CONFIGURATION_UTIL)
										.getValue()));*/
				refSituation.setRefEntiteSituation(refEntiteSituation);
				refSituationDao.persist(refSituation);
				if(refCompteDto.isActivate()) {
					refSituation = new RefSituation();
					refSituation.setDateSituation(new Date());
					refSituation.setIdEntite(refCompte.getId());
					refSituation.setNomEntite(RefCompte.class.getSimpleName());
					refEntiteSituation = new RefEntiteSituation();
					refEntiteSituation
							.setId(22);/*Integer
									.parseInt(refParametrageServiceImpl
											.getParamConfiguration(
													UtilConstant.COMPTE_SITUATION_VALIDEE_ID_KEY,
													UtilConstant.CONFIGURATION_UTIL)
											.getValue()));*/
					refSituation.setRefEntiteSituation(refEntiteSituation);
					refSituationDao.persist(refSituation);
				}
			} else {
				refCompte = refCompteDao.merge(refCompte);
			}

			mapper.map(refCompte, refCompteDto);
            //refCompteDto.setMotPasseNonCrypte(motPassenonNonCrypte);
			return refCompteDto;
		} catch (RuntimeException e) {
			log.error("insertOrUpdate failed", e);
			throw e;

		}
		catch (Exception ex) {
			log.error("insertOrUpdate failed", ex);
			return null;

		}
	}

	/**
	 * [RefCompteServiceImpl.generateUsername] Method
	 * 
	 * @author MAKERRI Sofiane on : 4 sept. 2014 11:22:30
	 * @param nom
	 * @param prenom
	 * @return
	 */
	public String generateUsername(String nom, String prenom) {
		try {
			if (prenom == null || nom == null) {
				return null;
			}
			String prefix = prenom.substring(0, 1).toLowerCase() + "."
					+ nom.toLowerCase().trim().replaceAll(" ", "");

			if (prefix.trim().isEmpty()) {
				return null;
			}
			Integer lastOrder = refCompteDao.generateUsername(prefix);
			if (lastOrder == null) {
				return null;
			}
			if (lastOrder == -1) {
				return prefix;
			}
			lastOrder++;
			prefix = prefix + lastOrder;
			log.debug("calculateIdentify successful");
			return prefix;
		} catch (RuntimeException e) {
			log.error("calculateIdentify failed", e);
			throw e;
		}

	}

	/**
	 * key 
	 * @author MAKERRI Sofiane  on : 4 sept. 2014  17:31:50
	 */
	private static byte[] key = { 0x74, 0x68, 0x69, 0x73, 0x49, 0x73, 0x41,
			0x53, 0x65, 0x63, 0x72, 0x65, 0x74, 0x4b, 0x65, 0x79 };// "thisIsASecretKey";

	/**
	 * [RefCompteServiceImpl.encryptAES] Method 
	 * @author MAKERRI Sofiane  on : 4 sept. 2014  17:31:54
	 * @param strToEncrypt
	 * @return
	 * @throws Exception
	 */
	private  String encryptAES(String strToEncrypt) throws Exception {
		try {
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			final SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);

			return new String(cipher.doFinal(strToEncrypt.getBytes())).replaceAll("[\\p{Cc}\\p{Cf}\\p{Co}\\p{Cn}]", " ");
		} catch (Exception e) {
			log.error("Error while encrypting", e);
			throw e;
		}

	}
	
	 /**
	 * [RefCompteServiceImpl.decryptAES] Method 
	 * @author MAKERRI Sofiane  on : 4 sept. 2014  17:31:44
	 * @param strToDecrypt
	 * @return
	 * @throws Exception
	 */
	public static String decryptAES(String strToDecrypt) throws Exception
	    {
	        try
	        {
	            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
	            final SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
	            cipher.init(Cipher.DECRYPT_MODE, secretKey);
	            return new String(cipher.doFinal(strToDecrypt.getBytes()));
	        }
	        catch (Exception e)
	        {
	            log.error("Error while decrypting", e);
	            throw e;

	        }
	    }

}
