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

import dz.gov.mesrs.sii.commons.data.dao.referentiel.NomenclatureDao;
import dz.gov.mesrs.sii.commons.data.model.referentiel.Nomenclature;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.service.NomenclatureService;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */

@Service("nomenclatureServiceImpl")
public class NomenclatureServiceImpl implements NomenclatureService {

	@Autowired
	@Qualifier("nomenclatureDaoImpl")
	private NomenclatureDao nomenclatureDaoImpl;
	@Autowired
	@Qualifier("mapper")
	private Mapper mapper;
	private static final Log log = LogFactory
			.getLog(NomenclatureServiceImpl.class);

	public NomenclatureServiceImpl() {

	}

	@Override
	public NomenclatureDto insertOrUpdate(NomenclatureDto nomenclatureDto) {
		try {
			Nomenclature nomenclature = mapper.map(nomenclatureDto,
					Nomenclature.class);

			if (nomenclature.getId() == 0)
				nomenclatureDaoImpl.persist(nomenclature);
			else
				nomenclature = nomenclatureDaoImpl.merge(nomenclature);

			mapper.map(nomenclature, nomenclatureDto);

		} catch (RuntimeException e) {
			log.error("insertOrUpdate", e);
			throw e;
		}
		return nomenclatureDto;
	}

	@Override
	public void remove(NomenclatureDto nomenclatureDto) {
		try {
			Nomenclature nomenclature = mapper.map(nomenclatureDto,
					Nomenclature.class);

			nomenclatureDaoImpl.remove(nomenclature);
		} catch (RuntimeException e) {
			log.error("remove", e);
			throw e;
		}
	}

	@Override
	public List<NomenclatureDto> findByQuery(String query) {
		List<NomenclatureDto> nomenclaturesDto = new ArrayList<NomenclatureDto>();
		try {
			List<Nomenclature> nomenclatures = nomenclatureDaoImpl
					.findByQuery(query);
			for (Nomenclature nomenclature : nomenclatures) {
				nomenclaturesDto.add(mapper.map(nomenclature,
						NomenclatureDto.class));
			}
		} catch (RuntimeException e) {
			log.error("findByQuery", e);
			throw e;
		}
		return nomenclaturesDto;

	}

	@Override
	public NomenclatureDto findById(int id) {
		try {
			Nomenclature nomenclature = nomenclatureDaoImpl.findById(id);

			if (nomenclature != null)
				return mapper.map(nomenclature, NomenclatureDto.class);
			return null;
		} catch (RuntimeException e) {
			log.error("findById", e);
			throw e;
		}

	}

	@Override
	public List<NomenclatureDto> findAll() {
		List<NomenclatureDto> nomenclaturesDto = new ArrayList<NomenclatureDto>();
		try {
			List<Nomenclature> nomenclatures = nomenclatureDaoImpl.findAll();
			for (Nomenclature nomenclature : nomenclatures) {
				nomenclaturesDto.add(mapper.map(nomenclature,
						NomenclatureDto.class));
			}

		} catch (RuntimeException e) {
			log.error("findAll", e);
			throw e;
		}
		return nomenclaturesDto;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.referentiel.business.service.NcValuesService#findByName
	 * (java.lang.String)
	 */
	@Override
	public List<NomenclatureDto> findByName(String name) {
		List<NomenclatureDto> nomenclaturesDto = new ArrayList<NomenclatureDto>();
		try {
			List<Nomenclature> nomenclatures = nomenclatureDaoImpl
					.findByName(name);

			for (Nomenclature nomenclature : nomenclatures) {
				NomenclatureDto nomenclatureDto = new NomenclatureDto();
//				log.info("Nomenclature:"
//						+ nomenclature.getNcNames().getNomNomenclature());
				mapper.map(nomenclature, nomenclatureDto);
				nomenclaturesDto.add(nomenclatureDto);
			}
		} catch (RuntimeException e) {
			log.error("ncNameReference", e);
			throw e;
		}
		return nomenclaturesDto;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.referentiel.business.service.NcValuesService#findByName
	 * (java.lang.String, java.lang.String)
	 */
	@Override
	public List<NomenclatureDto> findByName(String name, String orderby) {
		List<NomenclatureDto> nomenclaturesDto = new ArrayList<NomenclatureDto>();
		try {
			List<Nomenclature> nomenclatures = nomenclatureDaoImpl.findByName(
					name, orderby);
			for (Nomenclature nomenclature : nomenclatures) {
				NomenclatureDto nomenclatureDto = new NomenclatureDto();
				log.info("Nomenclature:"
						+ nomenclature.getNcNames().getNomNomenclature());
				mapper.map(nomenclature, nomenclatureDto);
				nomenclaturesDto.add(nomenclatureDto);
			}
		} catch (RuntimeException e) {
			log.error("findByName", e);
			throw e;
		}
		return nomenclaturesDto;
	}

	/**
	 * [NomenclatureServiceImpl.nomenclatureDao] Getter
	 * 
	 * @author MAKERRI Sofiane on : 21 janv. 2014 17:50:17
	 * @return the nomenclatureDao
	 */
	public NomenclatureDao getNomenclatureDaoImpl() {
		return nomenclatureDaoImpl;
	}

	/**
	 * [NomenclatureServiceImpl.nomenclatureDao] Setter
	 * 
	 * @author MAKERRI Sofiane on : 21 janv. 2014 17:50:17
	 * @param nomenclatureDao
	 *            the nomenclatureDao to set
	 */
	public void setNomenclatureDaoImpl(NomenclatureDao nomenclatureDaoImpl) {
		this.nomenclatureDaoImpl = nomenclatureDaoImpl;
	}

	/**
	 * [NomenclatureServiceImpl.mapper] Getter
	 * 
	 * @author MAKERRI Sofiane on : 21 janv. 2014 17:50:51
	 * @return the mapper
	 */
	public Mapper getMapper() {
		return mapper;
	}

	/**
	 * [NomenclatureServiceImpl.mapper] Setter
	 * 
	 * @author MAKERRI Sofiane on : 21 janv. 2014 17:50:51
	 * @param mapper
	 *            the mapper to set
	 */
	public void setMapper(Mapper mapper) {
		this.mapper = mapper;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.referentiel.business.service.NcNamesService#existInNc
	 * (java.lang.String)
	 */
	@Override
	public boolean existInNc(String ncName) {
		try {
			List<Nomenclature> nomenclatures = nomenclatureDaoImpl
					.findByName(ncName);
			if ((nomenclatures == null) || (nomenclatures.size() == 0)) {
				return false;
			}
		} catch (RuntimeException e) {
			log.error("existInNc", e);
			throw e;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.referentiel.business.service.NomenclatureService#findByIdNc
	 * (int)
	 */
	@Override
	public List<NomenclatureDto> findByIdNc(int id) {

		List<NomenclatureDto> NomenclaturesDto = new ArrayList<NomenclatureDto>();
		try {
			List<Nomenclature> nomenclatures = nomenclatureDaoImpl
					.findByIdNc(id);

			if (nomenclatures != null) {
				for (Nomenclature nomenclature : nomenclatures) {
					NomenclaturesDto.add(mapper.map(nomenclature,
							NomenclatureDto.class));
				}
			}
		} catch (RuntimeException e) {
			log.error("findByIdNc", e);
			throw e;
		}
		return NomenclaturesDto;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.referentiel.business.service.NomenclatureService#save
	 * (dz.gov.mesrs.sii.referentiel.business.model.dto.NcNamesDto)
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public NomenclatureDto save(NomenclatureDto nomenclatureDto) {
		try {
			Nomenclature nomenclature = new Nomenclature();
			mapper.map(nomenclatureDto, nomenclature);
			nomenclatureDaoImpl.persist(nomenclature);
			log.debug("save nomenclature successful");
			return mapper.map(nomenclature, NomenclatureDto.class);
		} catch (RuntimeException e) {
			log.error("save nomenclature failed", e);
			throw e;

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.referentiel.business.service.NomenclatureService#update
	 * (dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto)
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void update(NomenclatureDto nomenclatureDto) {
		try {
			Nomenclature nomenclature = new Nomenclature();
			mapper.map(nomenclatureDto, nomenclature);
			nomenclature = nomenclatureDaoImpl.merge(nomenclature);
			log.debug("update nomenclature successful");
		} catch (RuntimeException e) {
			log.error("update nomenclature failed", e);
			throw e;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.referentiel.business.service.NomenclatureService#findByCode
	 * (java.lang.String)
	 */
	@Override
	public NomenclatureDto findByCode(int idNcName, String code) {
		try {
			Nomenclature nomenclature = new Nomenclature();
			nomenclature = nomenclatureDaoImpl.findByCode(idNcName, code);
			if (nomenclature != null) {
				return mapper.map(nomenclature, NomenclatureDto.class);
			}
		} catch (RuntimeException e) {
			log.error("findByCode", e);
			throw e;
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dz.gov.mesrs.sii.referentiel.business.service.NomenclatureService#
	 * findByLlLatin(java.lang.String)
	 */
	@Override
	public NomenclatureDto findByLlLatin(int idNcName, String llLatin) {
		try {
			Nomenclature nomenclature = new Nomenclature();
			nomenclature = nomenclatureDaoImpl.findByLlLatin(idNcName, llLatin);
			if (nomenclature != null) {
				return mapper.map(nomenclature, NomenclatureDto.class);
			}
		} catch (RuntimeException e) {
			log.error("findByLlLatin", e);
			throw e;
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dz.gov.mesrs.sii.referentiel.business.service.NomenclatureService#
	 * findByLlArabe(java.lang.String)
	 */
	@Override
	public NomenclatureDto findByLlArabe(int idNcName, String llArabe) {
		try {
			Nomenclature nomenclature = new Nomenclature();
			nomenclature = nomenclatureDaoImpl.findByLlArabe(idNcName, llArabe);
			if (nomenclature != null) {
				return mapper.map(nomenclature, NomenclatureDto.class);
			}
		} catch (RuntimeException e) {
			log.error("findByLlArabe", e);
			throw e;
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dz.gov.mesrs.sii.referentiel.business.service.NomenclatureService#
	 * getNcReference
	 * (dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto)
	 */
	@Override
	public List<NomenclatureDto> findNcReference(String name) {
		List<NomenclatureDto> NomenclaturesDto = new ArrayList<NomenclatureDto>();
		List<Nomenclature> nomenclatures = nomenclatureDaoImpl
				.findNcReference(name);
		if (nomenclatures != null) {
			for (Nomenclature currentNomenclature : nomenclatures) {
				NomenclaturesDto.add(mapper.map(currentNomenclature,
						NomenclatureDto.class));
			}
		}
		return NomenclaturesDto;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dz.gov.mesrs.sii.referentiel.business.service.NomenclatureService#
	 * findNcCompositeList(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public List<NomenclatureDto> findNcCompositeList(String ncName,
			String code, String ncTarget) {
		List<NomenclatureDto> NomenclaturesDto = new ArrayList<NomenclatureDto>();
		try {
			List<Nomenclature> nomenclatures = nomenclatureDaoImpl
					.findNcCompositeList(ncName, code, ncTarget);
			if (nomenclatures != null) {
				for (Nomenclature currentNomenclature : nomenclatures) {
					NomenclaturesDto.add(mapper.map(currentNomenclature,
							NomenclatureDto.class));
				}
			}
			log.debug("update findNcCompositeList successful");
		} catch (RuntimeException e) {
			log.error("findNcCompositeList failed", e);
			throw e;
		}

		return NomenclaturesDto;
	}

	/**
	 * [NomenclatureServiceImpl.findByDomaine] Find List of Nomenclature by
	 * Domain
	 * 
	 * @author BELDI Jamel on : 23 janv. 2014 18:15:56
	 * @param ncDomaine
	 *            the Domain of NC
	 * @return List of NC
	 */
	public List<NomenclatureDto> findByDomaine(String ncDomaine) {
		List<NomenclatureDto> NomenclaturesDto = new ArrayList<NomenclatureDto>();
		try {
			List<Nomenclature> nomenclatures = nomenclatureDaoImpl
					.findByDomaine(ncDomaine);
			if (nomenclatures != null) {
				for (Nomenclature currentNomenclature : nomenclatures) {
					NomenclaturesDto.add(mapper.map(currentNomenclature,
							NomenclatureDto.class));
				}
			}
		} catch (RuntimeException e) {
			log.error("findByDomaine", e);
			throw e;
		}
		return NomenclaturesDto;
	}

	/**
	 * [NomenclatureServiceImpl.findByReference] Find List of Nomenclature by
	 * Name and REFERENCE
	 * 
	 * @author BELDI Jamel on : 23 janv. 2014 18:15:56
	 * @param ncName
	 *            the name of NC, ID of Reference
	 * @return List of NC
	 */
	@Override
	public List<NomenclatureDto> findByReference(String ncName,
			Integer idReference) {
		List<NomenclatureDto> NomenclaturesDto = new ArrayList<NomenclatureDto>();
		try {
			List<Nomenclature> nomenclatures = nomenclatureDaoImpl
					.findByReference(ncName, idReference);
			if (nomenclatures != null) {
				for (Nomenclature currentNomenclature : nomenclatures) {
					NomenclaturesDto.add(mapper.map(currentNomenclature,
							NomenclatureDto.class));
				}
			}
		} catch (RuntimeException e) {
			log.error("findByDomaine", e);
			throw e;
		}
		return NomenclaturesDto;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dz.gov.mesrs.sii.referentiel.business.service.NomenclatureService#
	 * findNcCompositeList(int, java.lang.String, java.lang.String)
	 */
	@Override
	public List<NomenclatureDto> findNcCompositeList(int id, String ncTarget) {
		List<NomenclatureDto> NomenclaturesDto = new ArrayList<NomenclatureDto>();
		try {
			List<Nomenclature> nomenclatures = nomenclatureDaoImpl
					.findNcCompositeList(id, ncTarget);
			if (nomenclatures != null) {
				for (Nomenclature currentNomenclature : nomenclatures) {
					NomenclaturesDto.add(mapper.map(currentNomenclature,
							NomenclatureDto.class));
				}
			}
			log.debug("update findNcCompositeList successful");
		} catch (RuntimeException e) {
			log.error("findNcCompositeList failed", e);
			throw e;
		}

		return NomenclaturesDto;
	}

	@Override
	public boolean existIdNc(int id) {
		try {
			List<Nomenclature> nomenclatures = nomenclatureDaoImpl
					.findByIdNc(id);
			if ((nomenclatures == null) || (nomenclatures.size() == 0)) {
				return false;
			}
		} catch (RuntimeException e) {
			log.error("existInNc", e);
			throw e;
		}
		return true;
	}

	/*
	 * 
	 * 
	 */
	@Override
	public List<NomenclatureDto> findByNcNameLikeLibellefr(String ncName,
			String value) {

		List<NomenclatureDto> nomenclaturesDto = new ArrayList<NomenclatureDto>();
		try {
			List<Nomenclature> nomenclatures = nomenclatureDaoImpl
					.findByNcNameLikeLibellefr(ncName, value);

			for (Nomenclature nomenclature : nomenclatures) {
				NomenclatureDto nomenclatureDto = new NomenclatureDto();
				log.info("Nomenclature:"
						+ nomenclature.getNcNames().getNomNomenclature());
				mapper.map(nomenclature, nomenclatureDto);
				nomenclaturesDto.add(nomenclatureDto);
			}
		} catch (RuntimeException e) {
			log.error("ncNameReference", e);
			throw e;
		}
		return nomenclaturesDto;
	}

	@Override
	public List<NomenclatureDto> findGeneric(String name, String value) {
		List<NomenclatureDto> nomenclaturesDto = new ArrayList<NomenclatureDto>();
		try {
			List<Nomenclature> nomenclatures = nomenclatureDaoImpl
					.findByNameAndValue(name, value);

			for (Nomenclature nomenclature : nomenclatures) {
				NomenclatureDto nomenclatureDto = new NomenclatureDto();
				log.info("Nomenclature:"
						+ nomenclature.getNcNames().getNomNomenclature());
				mapper.map(nomenclature, nomenclatureDto);
				nomenclaturesDto.add(nomenclatureDto);
			}
		} catch (RuntimeException e) {
			log.error("ncNameReference", e);
			throw e;
		}
		return nomenclaturesDto;
	}

	@Override
	public List<NomenclatureDto> findTypePiece(Integer idTypeDossier) {
		List<NomenclatureDto> nomenclaturesDto = new ArrayList<NomenclatureDto>();
		try {
			List<Nomenclature> nomenclatures = nomenclatureDaoImpl
					.findTypePiece(idTypeDossier);

			for (Nomenclature nomenclature : nomenclatures) {
				NomenclatureDto nomenclatureDto = new NomenclatureDto();
				log.info("Nomenclature:"
						+ nomenclature.getNcNames().getNomNomenclature());
				mapper.map(nomenclature, nomenclatureDto);
				nomenclaturesDto.add(nomenclatureDto);
			}
		} catch (RuntimeException e) {
			log.error("ncNameReference", e);
			throw e;
		}
		return nomenclaturesDto;
	}

	@Override
	public NomenclatureDto findByCode(String ncName, String code) {
		try {
			Nomenclature nomenclature = new Nomenclature();
			nomenclature = nomenclatureDaoImpl.findByCode(ncName, code);
			if (nomenclature != null) {
				return mapper.map(nomenclature, NomenclatureDto.class);
			}
		} catch (RuntimeException e) {
			log.error("findByCode", e);
			throw e;
		}
		return null;
	}

	/**********************
	 * récuperer le parent de la nomenclature ncCode : par exemple trouver la
	 * wilaya d'une daira dont le code est ncCode
	 */
	@Override
	public NomenclatureDto findReference(String ncCode) {
		try {
			Nomenclature nomenclature = new Nomenclature();
			nomenclature = nomenclatureDaoImpl.findReference(ncCode);
			if (nomenclature != null) {
				return mapper.map(nomenclature, NomenclatureDto.class);
			}
		} catch (RuntimeException e) {
			log.error("findByLlLatin", e);
			throw e;
		}
		return null;
	}

}
