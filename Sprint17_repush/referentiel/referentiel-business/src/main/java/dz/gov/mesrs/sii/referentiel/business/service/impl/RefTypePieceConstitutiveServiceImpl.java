package dz.gov.mesrs.sii.referentiel.business.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefTypePieceConstitutiveDao;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefTypePieceConstitutive;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefTypePieceConstitutiveDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefTypePieceConstitutiveService;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 20-04-2014 16:44:07
 */

@Service("refTypePieceConstitutiveServiceImpl")
public class RefTypePieceConstitutiveServiceImpl implements
		RefTypePieceConstitutiveService {

	@Autowired
	@Qualifier("refTypePieceConstitutiveDaoImpl")
	private RefTypePieceConstitutiveDao refTypePieceConstitutiveDaoImpl;

	private static final Logger log = LoggerFactory.getLogger(RefTypePieceConstitutiveServiceImpl.class.getName());

	@Autowired
	@Qualifier("mapper")
	private Mapper mapper;

	public RefTypePieceConstitutiveServiceImpl() {

	}

	/**
	 * [RefTypePieceConstitutiveServiceImpl.mapper] Getter
	 * 
	 * @author MAKERRI Sofiane on : 21 mai 2014 10:06:41
	 * @return the mapper
	 */
	public Mapper getMapper() {
		return mapper;
	}

	/**
	 * [RefTypePieceConstitutiveServiceImpl.mapper] Setter
	 * 
	 * @author MAKERRI Sofiane on : 21 mai 2014 10:06:42
	 * @param mapper
	 *            the mapper to set
	 */
	public void setMapper(Mapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public RefTypePieceConstitutiveDto insertOrUpdate(
			RefTypePieceConstitutiveDto refTypePieceConstitutiveDto) {
		try {
			RefTypePieceConstitutive refTypePieceConstitutive = mapper
					.map(refTypePieceConstitutiveDto,
							RefTypePieceConstitutive.class);

			if (refTypePieceConstitutive.getId() == 0)
				refTypePieceConstitutiveDaoImpl
						.persist(refTypePieceConstitutive);
			else
				refTypePieceConstitutive = refTypePieceConstitutiveDaoImpl
						.merge(refTypePieceConstitutive);
			mapper.map(refTypePieceConstitutive, refTypePieceConstitutiveDto);
		} catch (RuntimeException e) {
			log.error("findAll failed", e);
			throw e;
		}

		return refTypePieceConstitutiveDto;
	}

	@Override
	public void remove(RefTypePieceConstitutiveDto refTypePieceConstitutiveDto) {
		try {
			RefTypePieceConstitutive refTypePieceConstitutive = mapper
					.map(refTypePieceConstitutiveDto,
							RefTypePieceConstitutive.class);

			refTypePieceConstitutiveDaoImpl.remove(refTypePieceConstitutive);
		} catch (RuntimeException e) {
			log.error("findAll failed", e);
			throw e;
		}
	}

	@Override
	public List<RefTypePieceConstitutiveDto> findByQuery(String query) {
		try {
			List<RefTypePieceConstitutive> refTypePieceConstitutives = refTypePieceConstitutiveDaoImpl
					.findByQuery(query);

			List<RefTypePieceConstitutiveDto> refTypePieceConstitutiveDtos = new ArrayList<RefTypePieceConstitutiveDto>();

			for (RefTypePieceConstitutive refTypePieceConstitutive : refTypePieceConstitutives) {
				refTypePieceConstitutiveDtos.add(mapper.map(
						refTypePieceConstitutive,
						RefTypePieceConstitutiveDto.class));
			}
			return refTypePieceConstitutiveDtos;
		} catch (RuntimeException e) {
			log.error("findAll failed", e);
			throw e;
		}

	}

	@Override
	public RefTypePieceConstitutiveDto findById(int id) {
		try {
			RefTypePieceConstitutive refTypePieceConstitutive = refTypePieceConstitutiveDaoImpl
					.findById(id);

			if (refTypePieceConstitutive != null)
				return mapper.map(refTypePieceConstitutive,
						RefTypePieceConstitutiveDto.class);

		} catch (RuntimeException e) {
			log.error("findAll failed", e);
			throw e;
		}
		return null;
	}

	@Override
	public List<RefTypePieceConstitutiveDto> findAll() {
		try {
			List<RefTypePieceConstitutive> refTypePieceConstitutives = refTypePieceConstitutiveDaoImpl
					.findAll();

			List<RefTypePieceConstitutiveDto> refTypePieceConstitutiveDtos = new ArrayList<RefTypePieceConstitutiveDto>();

			for (RefTypePieceConstitutive refTypePieceConstitutive : refTypePieceConstitutives) {
				refTypePieceConstitutiveDtos.add(mapper.map(
						refTypePieceConstitutive,
						RefTypePieceConstitutiveDto.class));
			}
			return refTypePieceConstitutiveDtos;
		} catch (RuntimeException e) {
			log.error("findAll failed", e);
			throw e;
		}

	}

	/**
	 * [RefTypePieceConstitutiveServiceImpl.refTypePieceConstitutiveDaoImpl]
	 * Getter
	 * 
	 * @author MAKERRI Sofiane on : 20 mai 2014 16:14:23
	 * @return the refTypePieceConstitutiveDaoImpl
	 */
	public RefTypePieceConstitutiveDao getRefTypePieceConstitutiveDaoImpl() {
		return refTypePieceConstitutiveDaoImpl;
	}

	/**
	 * [RefTypePieceConstitutiveServiceImpl.refTypePieceConstitutiveDaoImpl]
	 * Setter
	 * 
	 * @author MAKERRI Sofiane on : 20 mai 2014 16:14:23
	 * @param refTypePieceConstitutiveDaoImpl
	 *            the refTypePieceConstitutiveDaoImpl to set
	 */
	public void setRefTypePieceConstitutiveDaoImpl(
			RefTypePieceConstitutiveDao refTypePieceConstitutiveDaoImpl) {
		this.refTypePieceConstitutiveDaoImpl = refTypePieceConstitutiveDaoImpl;
	}

	@Override
	public List<RefTypePieceConstitutiveDto> findByIdTypeDossierDate(
			Integer idTypeDossier, Date annee) {
		List<RefTypePieceConstitutiveDto> refTypePieceConstitutiveDtos = new ArrayList<RefTypePieceConstitutiveDto>();
		try {
			List<RefTypePieceConstitutive> refTypePieceConstitutives = refTypePieceConstitutiveDaoImpl
					.findByIdTypeDossierDate(idTypeDossier, annee);
			if (refTypePieceConstitutives != null) {
				log.debug("findPieceConstitutive success with size = {} ", new Object[]{refTypePieceConstitutives.size()});
				for (RefTypePieceConstitutive currentRefTypePieceConstitutive : refTypePieceConstitutives) {

					RefTypePieceConstitutiveDto refTypePieceConstitutiveDto = new RefTypePieceConstitutiveDto();
					mapper.map(currentRefTypePieceConstitutive,
							refTypePieceConstitutiveDto);
					refTypePieceConstitutiveDtos
							.add(refTypePieceConstitutiveDto);
				}

			}
		} catch (RuntimeException e) {
			log.error("findPieceConstitutive failed", e);
			throw e;
		}
		return refTypePieceConstitutiveDtos;
	}

	@Override
	public List<RefTypePieceConstitutiveDto> findByTypeDossierDate(
			String typeDossier, Date annee) {
		List<RefTypePieceConstitutiveDto> refTypePieceConstitutiveDtos = new ArrayList<RefTypePieceConstitutiveDto>();
		try {
			List<RefTypePieceConstitutive> refTypePieceConstitutives = refTypePieceConstitutiveDaoImpl
					.findByTypeDossierDate(typeDossier, annee);
			if (refTypePieceConstitutives != null) {
				log.debug("findPieceConstitutive success with size = {} ", new Object[]{refTypePieceConstitutives.size()});
				for (RefTypePieceConstitutive currentRefTypePieceConstitutive : refTypePieceConstitutives) {

					RefTypePieceConstitutiveDto refTypePieceConstitutiveDto = new RefTypePieceConstitutiveDto();
					mapper.map(currentRefTypePieceConstitutive,
							refTypePieceConstitutiveDto);
					refTypePieceConstitutiveDtos
							.add(refTypePieceConstitutiveDto);
				}

			}
		} catch (RuntimeException e) {
			log.error("findPieceConstitutive failed", e);
			throw e;
		}
		return refTypePieceConstitutiveDtos;
	}

	@Override
	public List<RefTypePieceConstitutiveDto> findByCodeTypeDossierDate(
			String codeTypeDossier, Date annee) {
		List<RefTypePieceConstitutiveDto> refTypePieceConstitutiveDtos = new ArrayList<RefTypePieceConstitutiveDto>();
		try {
			List<RefTypePieceConstitutive> refTypePieceConstitutives = refTypePieceConstitutiveDaoImpl
					.findByCodeTypeDossierDate(codeTypeDossier, annee);
			if (refTypePieceConstitutives != null) {
				log.debug("findPieceConstitutive success with size = {} ", new Object[]{refTypePieceConstitutives.size()});
				for (RefTypePieceConstitutive currentRefTypePieceConstitutive : refTypePieceConstitutives) {

					RefTypePieceConstitutiveDto refTypePieceConstitutiveDto = new RefTypePieceConstitutiveDto();
					mapper.map(currentRefTypePieceConstitutive,
							refTypePieceConstitutiveDto);
					refTypePieceConstitutiveDtos
							.add(refTypePieceConstitutiveDto);
				}

			}
		} catch (RuntimeException e) {
			log.error("findPieceConstitutive failed", e);
			throw e;
		}
		return refTypePieceConstitutiveDtos;
	}

	@Override
	public List<RefTypePieceConstitutiveDto> findByIdTypeDossier(
			Integer idTypeDossier, boolean aFournir) {
		List<RefTypePieceConstitutiveDto> refTypePieceConstitutiveDtos = new ArrayList<RefTypePieceConstitutiveDto>();
		try {
			List<RefTypePieceConstitutive> refTypePieceConstitutives = refTypePieceConstitutiveDaoImpl
					.findByIdTypeDossier(idTypeDossier, aFournir);
			if (refTypePieceConstitutives != null) {
				log.debug("findPieceConstitutive success with size = {} ", new Object[]{refTypePieceConstitutives.size()});
				for (RefTypePieceConstitutive currentRefTypePieceConstitutive : refTypePieceConstitutives) {

					RefTypePieceConstitutiveDto refTypePieceConstitutiveDto = new RefTypePieceConstitutiveDto();
					mapper.map(currentRefTypePieceConstitutive,
							refTypePieceConstitutiveDto);
					refTypePieceConstitutiveDtos
							.add(refTypePieceConstitutiveDto);
				}

			}
		} catch (RuntimeException e) {
			log.error("findByIdTypeDossier failed", e);
			throw e;
		}
		return refTypePieceConstitutiveDtos;
	}

	@Override
	public RefTypePieceConstitutiveDto update(
			RefTypePieceConstitutiveDto refTypePieceConstitutiveDto) {
		try {
			RefTypePieceConstitutive refTypePieceConstitutive = mapper
					.map(refTypePieceConstitutiveDto,
							RefTypePieceConstitutive.class);

				refTypePieceConstitutiveDaoImpl
						.merge(refTypePieceConstitutive);
			mapper.map(refTypePieceConstitutive, refTypePieceConstitutiveDto);
		} catch (RuntimeException e) {
			log.error("findAll failed", e);
			throw e;
		}

		return refTypePieceConstitutiveDto;
	}

	@Override
	public int findLastRang(Integer idTypeDossier, boolean aFournir) {
		try {
				return refTypePieceConstitutiveDaoImpl
						.findLastRang(idTypeDossier, aFournir);
			
		} catch (RuntimeException e) {
			log.error("findAll failed", e);
			throw e;
		}

	}

	@Override
	public List<RefTypePieceConstitutiveDto> findByIdTypeDossier(
			Integer idTypeDossier) {
		List<RefTypePieceConstitutiveDto> refTypePieceConstitutiveDtos = new ArrayList<RefTypePieceConstitutiveDto>();
		try {
			List<RefTypePieceConstitutive> refTypePieceConstitutives = refTypePieceConstitutiveDaoImpl
					.findByIdTypeDossier(idTypeDossier);
			if (refTypePieceConstitutives != null) {
				log.debug("findPieceConstitutive success with size = {} ", new Object[]{refTypePieceConstitutives.size()});
				for (RefTypePieceConstitutive currentRefTypePieceConstitutive : refTypePieceConstitutives) {

					RefTypePieceConstitutiveDto refTypePieceConstitutiveDto = new RefTypePieceConstitutiveDto();
					mapper.map(currentRefTypePieceConstitutive,
							refTypePieceConstitutiveDto);
					refTypePieceConstitutiveDtos
							.add(refTypePieceConstitutiveDto);
				}

			}
		} catch (RuntimeException e) {
			log.error("findByIdTypeDossier failed", e);
			throw e;
		}
		return refTypePieceConstitutiveDtos;
	}

}
