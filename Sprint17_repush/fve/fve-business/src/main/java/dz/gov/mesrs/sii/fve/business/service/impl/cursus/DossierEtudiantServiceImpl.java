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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.fve.cursus.DossierDao;
import dz.gov.mesrs.sii.commons.data.dao.fve.cursus.DossierEtudiantDao;
import dz.gov.mesrs.sii.commons.data.model.fve.bac.DossierBachelier;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.Dossier;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.DossierEtudiant;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DossierEtudiantDto;
import dz.gov.mesrs.sii.fve.business.service.cursus.DossierEtudiantService;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */

@Service("dossierEtudiantService")
public class DossierEtudiantServiceImpl implements DossierEtudiantService {

	private static final Log log = LogFactory
			.getLog(DossierEtudiantServiceImpl.class);

	@Autowired
	@Qualifier("dossierEtudiantDao")
	private DossierEtudiantDao dossierEtudiantDao;
	@Autowired
	@Qualifier("dossierDao")
	private DossierDao dossierDao;

	@Autowired
	@Qualifier("mapper")
	private DozerBeanMapper mapper;

	public DossierEtudiantServiceImpl() {

	}

	/**
	 * [DossierEtudiantServiceImpl.dossierEtudiantDao] Getter
	 * 
	 * @author MAKERRI Sofiane on : 26 mai 2014 16:24:39
	 * @return the dossierEtudiantDao
	 */
	public DossierEtudiantDao getDossierEtudiantDao() {
		return dossierEtudiantDao;
	}

	/**
	 * [DossierEtudiantServiceImpl.dossierEtudiantDao] Setter
	 * 
	 * @author MAKERRI Sofiane on : 26 mai 2014 16:24:39
	 * @param dossierEtudiantDao
	 *            the dossierEtudiantDao to set
	 */
	public void setDossierEtudiantDao(DossierEtudiantDao dossierEtudiantDao) {
		this.dossierEtudiantDao = dossierEtudiantDao;
	}

	/**
	 * [DossierEtudiantServiceImpl.dossierDao] Getter
	 * 
	 * @author MAKERRI Sofiane on : 26 mai 2014 16:24:39
	 * @return the dossierDao
	 */
	public DossierDao getDossierDao() {
		return dossierDao;
	}

	/**
	 * [DossierEtudiantServiceImpl.dossierDao] Setter
	 * 
	 * @author MAKERRI Sofiane on : 26 mai 2014 16:24:39
	 * @param dossierDao
	 *            the dossierDao to set
	 */
	public void setDossierDao(DossierDao dossierDao) {
		this.dossierDao = dossierDao;
	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public DossierEtudiantDto insertOrUpdate(
			DossierEtudiantDto dossierEtudiantDto) {
		try {
			Dossier dossier = new Dossier();
			mapper.map(dossierEtudiantDto, dossier);
			DossierEtudiant dossierEtudiant = mapper.map(dossierEtudiantDto,
					DossierEtudiant.class);

			if (dossierEtudiant.getId() == 0) {
				dossier.setDateCreation(new Date());
				dossierDao.persist(dossier);
				mapper.map(dossier, dossierEtudiant);

				dossierEtudiant.setDossier(null);

				// Verifier si le id de dossier bachelier n'est pas vide
				// (nouveau dossier etrange)
				DossierBachelier dossierBachelier = dossierEtudiant
						.getDossierBachelier();
				if (dossierBachelier != null
						&& dossierEtudiant.getDossierBachelier().getId() == 0) {
					dossierEtudiant.setDossierBachelier(null);
				}
				dossierEtudiantDao.persist(dossierEtudiant);
			} else {
				dossierDao.merge(dossier);
				dossierEtudiant.setDossier(null);
				dossierEtudiant = dossierEtudiantDao.merge(dossierEtudiant);
			}
			mapper.map(dossierEtudiant, dossierEtudiantDto);
			log.info("insertOrUpdate success");
			return dossierEtudiantDto;
		} catch (RuntimeException e) {
			log.error("insertOrUpdate failed", e);
			throw e;

		}
	}

	@Override
	public void remove(DossierEtudiantDto dossierEtudiantDto) {

		DossierEtudiant dossierEtudiant = mapper.map(dossierEtudiantDto,
				DossierEtudiant.class);

		dossierEtudiantDao.remove(dossierEtudiant);
	}

	@Override
	public List<DossierEtudiantDto> findByQuery(String query) {

		List<DossierEtudiant> dossierEtudiants = dossierEtudiantDao
				.findByQuery(query);

		List<DossierEtudiantDto> dossierEtudiantDtos = new ArrayList<DossierEtudiantDto>();

		for (DossierEtudiant dossierEtudiant : dossierEtudiants) {
			dossierEtudiantDtos.add(mapper.map(dossierEtudiant,
					DossierEtudiantDto.class));
		}

		return dossierEtudiantDtos;

	}

	@Override
	public DossierEtudiantDto findById(int id) {

		DossierEtudiant dossierEtudiant = dossierEtudiantDao.findById(id);

		if (dossierEtudiant != null && dossierEtudiant.getId() > 0)
			return mapper.map(dossierEtudiant, DossierEtudiantDto.class);

		return null;
	}

	@Override
	public List<DossierEtudiantDto> findAll() {
		try {
			List<DossierEtudiantDto> result = new ArrayList<DossierEtudiantDto>();

			List<DossierEtudiant> resultListDao = dossierEtudiantDao.findAll();
			if (resultListDao != null && !resultListDao.isEmpty()) {
				for (DossierEtudiant dossierEtudiant : resultListDao) {
					DossierEtudiantDto dossierEtudiantDto = new DossierEtudiantDto();
					mapper.map(dossierEtudiant, dossierEtudiantDto);
					result.add(dossierEtudiantDto);
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
	public List<DossierEtudiantDto> findAdvanced(DossierEtudiantDto searchDto) {

		try {
			List<DossierEtudiantDto> result = new ArrayList<DossierEtudiantDto>();
			DossierEtudiant searchBo = new DossierEtudiant();
			mapper.map(searchDto, searchBo);
			List<DossierEtudiant> resultListDao = dossierEtudiantDao
					.findAdvanced(searchBo);
			if (resultListDao != null && !resultListDao.isEmpty()) {
				for (DossierEtudiant dossierEtudiant : resultListDao) {
					DossierEtudiantDto dossierEtudiantDto = new DossierEtudiantDto();
					mapper.map(dossierEtudiant, dossierEtudiantDto);
					result.add(dossierEtudiantDto);
				}
				return result;
			}

		} catch (RuntimeException e) {
			log.error("findAdvanced failed", e);
			throw e;

		}
		return null;
	}

	/**
	 * Rechercher le derniere dossier d'etudiant enregistr� par l'id de
	 * l'individu
	 * 
	 * @author Mounir.MESSAOUDI on : 30 oct. 2014 07:33:42
	 * @param idIndividu
	 * @return
	 */
	@Override
	public DossierEtudiantDto findLastByIdIndividu(Integer idIndividu) {
		try {

			DossierEtudiant dossierEtudiant = dossierEtudiantDao
					.findLastByIdIndividu(idIndividu);

			DossierEtudiantDto dossierEtudiantDto = null;

			if (dossierEtudiant != null) {
				dossierEtudiantDto = new DossierEtudiantDto();
				mapper.map(dossierEtudiant, dossierEtudiantDto);
			}

			return dossierEtudiantDto;

		} catch (RuntimeException e) {
			log.error("findByRefIndividu failed", e);
			throw e;

		}
	}

	/**
	 * Recherche avancee avec pagination
	 * 
	 * @author Mounir.MESSAOUDI on : 5 nov. 2014 15:41:08
	 * @param searchDto
	 * @return
	 */
	@Override
	public List<DossierEtudiantDto> findAdvanced(DossierEtudiantDto searchDto,
			String sortField, Integer pageSize, Integer first,
			Boolean descending) {

		try {
			List<DossierEtudiantDto> result = new ArrayList<DossierEtudiantDto>();
			DossierEtudiant searchBo = new DossierEtudiant();
			mapper.map(searchDto, searchBo);
			List<DossierEtudiant> resultListDao = dossierEtudiantDao
					.findAdvanced(searchBo, sortField, pageSize, first,
							descending);
			if (resultListDao != null && !resultListDao.isEmpty()) {
				for (DossierEtudiant dossierEtudiant : resultListDao) {
					DossierEtudiantDto dossierEtudiantDto = new DossierEtudiantDto();
					mapper.map(dossierEtudiant, dossierEtudiantDto);
					result.add(dossierEtudiantDto);
				}
				return result;
			}

		} catch (RuntimeException e) {
			log.error("findAdvanced failed", e);
			throw e;

		}
		return null;
	}

	/**
	 * Nombre de resultats de la recherche avancee
	 * 
	 * @author Mounir.MESSAOUDI on : 5 nov. 2014 15:42:53
	 * @param searchDto
	 * @return
	 */
	@Override
	public Long count(DossierEtudiantDto searchDto) {
		try {
			DossierEtudiant searchBo = new DossierEtudiant();
			mapper.map(searchDto, searchBo);
			return dossierEtudiantDao.count(searchBo);
		} catch (Exception e) {
			log.error("count failed", e);
			throw e;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dz.gov.mesrs.sii.fve.business.service.cursus.DossierEtudiantService#
	 * findByIdIndividu(java.lang.Integer, java.lang.String)
	 */
	@Override
	public List<DossierEtudiantDto> findByIdIndividu(Integer id,
			Integer idEtablissment) {
		try {
			List<DossierEtudiantDto> result = new ArrayList<DossierEtudiantDto>();

			List<DossierEtudiant> resultListDao = dossierEtudiantDao
					.findByIdIndividu(id, idEtablissment);
			if (resultListDao != null && !resultListDao.isEmpty()) {
				for (DossierEtudiant dossierEtudiant : resultListDao) {
					DossierEtudiantDto dossierEtudiantDto = new DossierEtudiantDto();
					mapper.map(dossierEtudiant, dossierEtudiantDto);
					result.add(dossierEtudiantDto);
				}
				return result;
			}

		} catch (RuntimeException e) {
			log.error("findByIdIndividu failed", e);
			throw e;

		}

		return null;
	}

}
