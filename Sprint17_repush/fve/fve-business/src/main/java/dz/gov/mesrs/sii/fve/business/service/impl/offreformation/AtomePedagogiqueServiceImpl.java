package dz.gov.mesrs.sii.fve.business.service.impl.offreformation;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.commons.data.dao.fve.offreformation.AtomePedagogiqueDao;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.AtomePedagogique;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.AtomePedagogiqueDto;
import dz.gov.mesrs.sii.fve.business.service.offreformation.AtomePedagogiqueService;

@Service("atomePedagogiqueService")
public class AtomePedagogiqueServiceImpl implements AtomePedagogiqueService {

	@Autowired
	@Qualifier("atomePedagogiqueDao")
	private AtomePedagogiqueDao atomePedagogiqueDao;

	@Autowired
	@Qualifier("mapper")
	private DozerBeanMapper mapper;
	private static final Log log = LogFactory
			.getLog(AtomePedagogiqueServiceImpl.class);

	public AtomePedagogiqueServiceImpl() {

	}

	@Override
	public AtomePedagogiqueDto insertOrUpdate(
			AtomePedagogiqueDto atomePedagogiqueDto) {

		AtomePedagogique atomePedagogique = mapper.map(atomePedagogiqueDto,
				AtomePedagogique.class);

		if (atomePedagogique.getId() == 0)
			atomePedagogiqueDao.persist(atomePedagogique);
		else
			atomePedagogique = atomePedagogiqueDao.merge(atomePedagogique);

		mapper.map(atomePedagogique, atomePedagogiqueDto);

		return atomePedagogiqueDto;
	}

	@Override
	public void remove(AtomePedagogiqueDto atomePedagogiqueDto) {
		atomePedagogiqueDao.remove(atomePedagogiqueDto.getId());
	}

	@Override
	public List<AtomePedagogiqueDto> findByQuery(String query) {

		List<AtomePedagogique> atomePedagogiques = atomePedagogiqueDao
				.findByQuery(query);

		List<AtomePedagogiqueDto> atomePedagogiqueDtos = new ArrayList<AtomePedagogiqueDto>();

		for (AtomePedagogique atomePedagogique : atomePedagogiques) {
			atomePedagogiqueDtos.add(mapper.map(atomePedagogique,
					AtomePedagogiqueDto.class));
		}

		return atomePedagogiqueDtos;
	}

	@Override
	public AtomePedagogiqueDto findById(int id) {

		AtomePedagogique atomePedagogique = atomePedagogiqueDao.findById(id);

		if (atomePedagogique != null)
			return mapper.map(atomePedagogique, AtomePedagogiqueDto.class);

		return null;
	}

	@Override
	public List<AtomePedagogiqueDto> findAll() {
		List<AtomePedagogique> atomePedagogiques = atomePedagogiqueDao
				.findAll();

		List<AtomePedagogiqueDto> atomePedagogiqueDtos = new ArrayList<AtomePedagogiqueDto>();

		for (AtomePedagogique atomePedagogique : atomePedagogiques) {
			atomePedagogiqueDtos.add(mapper.map(atomePedagogique,
					AtomePedagogiqueDto.class));
		}

		return atomePedagogiqueDtos;
	}

	@Override
	public List<AtomePedagogiqueDto> find(String code,
			Integer idMatiereConstitutive, String libelleFr, String libelleAr) {

		List<AtomePedagogique> atomePedagogiques = atomePedagogiqueDao.find(
				code, idMatiereConstitutive, libelleFr, libelleAr);

		List<AtomePedagogiqueDto> atomePedagogiqueDtos = new ArrayList<AtomePedagogiqueDto>();

		for (AtomePedagogique atomePedagogique : atomePedagogiques) {
			atomePedagogiqueDtos.add(mapper.map(atomePedagogique,
					AtomePedagogiqueDto.class));
		}

		return atomePedagogiqueDtos;
	}

	@Override
	public List<AtomePedagogiqueDto> findAPOfMC(int idMatiereConstitutive) {
		return find(null, idMatiereConstitutive, null, null);
	}

	@Override
	public List<AtomePedagogiqueDto> findByRattachementMC(int idRattachementMc) {
		try {
			List<AtomePedagogique> atomePedagogiques = atomePedagogiqueDao
					.findByRattachementMC(idRattachementMc);

			List<AtomePedagogiqueDto> atomePedagogiqueDtos = new ArrayList<AtomePedagogiqueDto>();

			for (AtomePedagogique atomePedagogique : atomePedagogiques) {
				atomePedagogiqueDtos.add(mapper.map(atomePedagogique,
						AtomePedagogiqueDto.class));
			}

			return atomePedagogiqueDtos;
		} catch (RuntimeException e) {
			log.error("findByRattachementMC failed", e);
			throw e;

		}
	}

	@Override
	public AtomePedagogiqueDto findByTypeAndRattachementMC(String codeType,
			int idRattachementMc) {
		try {
			AtomePedagogique atomePedagogique = atomePedagogiqueDao
					.findByTypeAndRattachementMC(codeType, idRattachementMc);
			return mapper.map(atomePedagogique, AtomePedagogiqueDto.class);
		} catch (RuntimeException e) {
			log.error("findByRattachementMC failed", e);
			throw e;

		}
	}

	@Override
	public List<AtomePedagogiqueDto> findApByGroupeId(Integer sectionId) {
		try {
			List<AtomePedagogique> atomePedagogiques = atomePedagogiqueDao
					.findApByGroupeId(sectionId);

			List<AtomePedagogiqueDto> atomePedagogiqueDtos = new ArrayList<AtomePedagogiqueDto>();

			for (AtomePedagogique atomePedagogique : atomePedagogiques) {
				atomePedagogiqueDtos.add(mapper.map(atomePedagogique,
						AtomePedagogiqueDto.class));
			}

			return atomePedagogiqueDtos;
		} catch (RuntimeException e) {
			throw e;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.fve.business.service.offreformation.AtomePedagogiqueService
	 * #findUniqueByType(int, int)
	 */
	@Override
	public AtomePedagogiqueDto findUniqueByType(int idMc, int ncTypeId) {
		try {
			AtomePedagogique atomePedagogique = atomePedagogiqueDao
					.findUniqueByType(idMc, ncTypeId);

			if (atomePedagogique != null) {
				return mapper.map(atomePedagogique, AtomePedagogiqueDto.class);
			}

			return null;

		} catch (RuntimeException e) {
			throw e;
		}
	}

	/* (non-Javadoc)
	 * @see dz.gov.mesrs.sii.fve.business.service.offreformation.AtomePedagogiqueService#findUniqueByCode(java.lang.String, int)
	 */
	@Override
	public AtomePedagogiqueDto findUniqueByCode(String codeAp, int idMc) {
		List<AtomePedagogiqueDto> aps = find(codeAp, idMc, null, null);
		if(aps != null && !aps.isEmpty()) {
			return aps.get(0);
		}
		return null;
	}


}
