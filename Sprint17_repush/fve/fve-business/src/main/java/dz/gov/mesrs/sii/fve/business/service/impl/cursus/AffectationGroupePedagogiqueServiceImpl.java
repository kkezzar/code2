package dz.gov.mesrs.sii.fve.business.service.impl.cursus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.commons.business.util.Utility;
import dz.gov.mesrs.sii.commons.data.dao.fve.cursus.AffectationGroupePedagogiqueDao;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.AffectationGroupePedagogique;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.DossierInscriptionAdministrative;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.AffectationGroupePedagogiqueDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DossierInscriptionAdministrativeDto;
import dz.gov.mesrs.sii.fve.business.service.cursus.AffectationGroupePedagogiqueService;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 20-04-2014 16:44:07
 */

@Service("affectationGroupePedagogiqueService")
public class AffectationGroupePedagogiqueServiceImpl implements
		AffectationGroupePedagogiqueService {

	@Autowired
	@Qualifier("affectationGroupePedagogiqueDao")
	private AffectationGroupePedagogiqueDao affectationGroupePedagogiqueDao;

	private static final Log log = LogFactory
			.getLog(AffectationGroupePedagogiqueServiceImpl.class);

	@Autowired
	@Qualifier("mapper")
	private DozerBeanMapper mapper;

	public AffectationGroupePedagogiqueServiceImpl() {
		// final List<String> mappings = new ArrayList<String>(1);
		// mappings.add(UtilConstants.DOZER_GROUPE_PEDAGOGIQUE_MAPPING_NAME);
		// mapper = new DozerBeanMapper();
		// mapper.setMappingFiles(mappings);
	}

	@Override
	public AffectationGroupePedagogiqueDto insertOrUpdate(
			AffectationGroupePedagogiqueDto affectationGroupePedagogiqueDto) {

		AffectationGroupePedagogique affectationGroupePedagogique = mapper.map(
				affectationGroupePedagogiqueDto,
				AffectationGroupePedagogique.class);
		affectationGroupePedagogique.setDateAffectation(new Date());
		try {
			if (affectationGroupePedagogique.getId() == 0) {
				affectationGroupePedagogiqueDao
						.persist(affectationGroupePedagogique);
			} else {
				affectationGroupePedagogique = affectationGroupePedagogiqueDao
						.merge(affectationGroupePedagogique);
			}
			mapper.map(affectationGroupePedagogique,
					affectationGroupePedagogiqueDto);

			log.info("insertorupdate AffectationGroupePedagogique succes..");

			return affectationGroupePedagogiqueDto;

		} catch (RuntimeException e) {
			log.error("insertorupdate AffectationGroupePedagogique failed--", e);
			throw e;
		}
	}

	@Override
	public void remove(
			AffectationGroupePedagogiqueDto affectationGroupePedagogiqueDto) {
		try {

			AffectationGroupePedagogique affectationGroupePedagogique = mapper
					.map(affectationGroupePedagogiqueDto,
							AffectationGroupePedagogique.class);

			affectationGroupePedagogique = affectationGroupePedagogiqueDao
					.merge(affectationGroupePedagogique);

			affectationGroupePedagogiqueDao
					.remove(affectationGroupePedagogique);
		} catch (RuntimeException e) {
			throw e;
		}
	}

	@Override
	public AffectationGroupePedagogiqueDto findById(int id) {
		try {
			AffectationGroupePedagogique affectationGroupePedagogique = affectationGroupePedagogiqueDao
					.findById(id);

			if (affectationGroupePedagogique != null)
				return mapper.map(affectationGroupePedagogique,
						AffectationGroupePedagogiqueDto.class);

			return null;
		} catch (RuntimeException e) {
			throw e;
		}
	}

	@Override
	public List<AffectationGroupePedagogiqueDto> findAll() {
		try {
			List<AffectationGroupePedagogique> affectationGroupePedagogiques = affectationGroupePedagogiqueDao
					.findAll();

			List<AffectationGroupePedagogiqueDto> affectationGroupePedagogiqueDtos = new ArrayList<AffectationGroupePedagogiqueDto>();

			for (AffectationGroupePedagogique affectationGroupePedagogique : affectationGroupePedagogiques) {
				affectationGroupePedagogiqueDtos.add(mapper.map(
						affectationGroupePedagogique,
						AffectationGroupePedagogiqueDto.class));
			}

			return affectationGroupePedagogiqueDtos;
		} catch (RuntimeException e) {
			throw e;
		}

	}

	/**
	 * [AffectationGroupePedagogiqueServiceImpl.mapper] Getter
	 * 
	 * @author MAKERRI Sofiane on : 11 août 2014 10:40:55
	 * @return the mapper
	 */
	public DozerBeanMapper getMapper() {
		return mapper;
	}

	/**
	 * [AffectationGroupePedagogiqueServiceImpl.mapper] Setter
	 * 
	 * @author MAKERRI Sofiane on : 11 août 2014 10:40:55
	 * @param mapper
	 *            the mapper to set
	 */
	public void setMapper(DozerBeanMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public List<AffectationGroupePedagogiqueDto> findByGroupePedagogiqueId(
			Integer gpId) {
		try {
			List<AffectationGroupePedagogique> affectationGroupePedagogiques = affectationGroupePedagogiqueDao
					.findByGroupePedagogiqueId(gpId);

			List<AffectationGroupePedagogiqueDto> affectationGroupePedagogiqueDtos = new ArrayList<AffectationGroupePedagogiqueDto>();

			for (AffectationGroupePedagogique affectationGroupePedagogique : affectationGroupePedagogiques) {
				affectationGroupePedagogiqueDtos.add(mapper.map(
						affectationGroupePedagogique,
						AffectationGroupePedagogiqueDto.class));
			}

			return affectationGroupePedagogiqueDtos;
		} catch (RuntimeException e) {
			throw e;
		}
	}

	@Override
	public List<AffectationGroupePedagogiqueDto> findOnlyBySectionId(
			Integer sectionId) {
		try {
			List<AffectationGroupePedagogique> affectationGroupePedagogiques = affectationGroupePedagogiqueDao
					.findOnlyBySectionId(sectionId);

			List<AffectationGroupePedagogiqueDto> affectationGroupePedagogiqueDtos = new ArrayList<AffectationGroupePedagogiqueDto>();

			for (AffectationGroupePedagogique affectationGroupePedagogique : affectationGroupePedagogiques) {
				affectationGroupePedagogiqueDtos.add(mapper.map(
						affectationGroupePedagogique,
						AffectationGroupePedagogiqueDto.class));
			}

			return affectationGroupePedagogiqueDtos;
		} catch (RuntimeException e) {
			throw e;
		}
	}

	@Override
	public List<AffectationGroupePedagogiqueDto> findAffectationInGroupe(
			Integer diaId, Integer sectionId) {
		try {
			List<AffectationGroupePedagogique> affectationGroupePedagogiques = affectationGroupePedagogiqueDao
					.findAffectationInGroupe(diaId, sectionId);

			List<AffectationGroupePedagogiqueDto> affectationGroupePedagogiqueDtos = new ArrayList<AffectationGroupePedagogiqueDto>();

			for (AffectationGroupePedagogique affectationGroupePedagogique : affectationGroupePedagogiques) {
				affectationGroupePedagogiqueDtos.add(mapper.map(
						affectationGroupePedagogique,
						AffectationGroupePedagogiqueDto.class));
			}

			return affectationGroupePedagogiqueDtos;
		} catch (RuntimeException e) {
			throw e;
		}
	}

	@Override
	public List<AffectationGroupePedagogiqueDto> findByAssociationGpId(
			Integer assocId) {
		try {
			List<AffectationGroupePedagogique> affectationGroupePedagogiques = affectationGroupePedagogiqueDao
					.findByAssociationGpId(assocId);

			List<AffectationGroupePedagogiqueDto> affectationGroupePedagogiqueDtos = new ArrayList<AffectationGroupePedagogiqueDto>();

			for (AffectationGroupePedagogique affectationGroupePedagogique : affectationGroupePedagogiques) {
				affectationGroupePedagogiqueDtos.add(mapper.map(
						affectationGroupePedagogique,
						AffectationGroupePedagogiqueDto.class));
			}

			return affectationGroupePedagogiqueDtos;
		} catch (RuntimeException e) {
			throw e;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dz.gov.mesrs.sii.fve.business.service.cursus.
	 * AffectationGroupePedagogiqueService
	 * #findEtudiantsOfSection(java.lang.Integer)
	 */
	@Override
	public List<DossierInscriptionAdministrativeDto> findEtudiantsOfSection(
			Integer idSection) {
		try {
			List<DossierInscriptionAdministrative> dossierInscriptionAdministratives = affectationGroupePedagogiqueDao
					.findEtudiantsOfSection(idSection);
			if (dossierInscriptionAdministratives != null) {
				log.debug("findEtudiantsOfSection list success with size =  "
						+ dossierInscriptionAdministratives.size());
				return Utility.map(mapper, dossierInscriptionAdministratives,
						DossierInscriptionAdministrativeDto.class);
			}

		} catch (RuntimeException e) {
			log.error("findEtudiantsOfSection failed", e);
			throw e;

		}
		return null;
	}

}
