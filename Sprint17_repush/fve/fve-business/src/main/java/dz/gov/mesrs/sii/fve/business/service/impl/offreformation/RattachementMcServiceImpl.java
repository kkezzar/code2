package dz.gov.mesrs.sii.fve.business.service.impl.offreformation;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.fve.offreformation.MatiereConstitutiveDao;
import dz.gov.mesrs.sii.commons.data.dao.fve.offreformation.ParcoursTypeDao;
import dz.gov.mesrs.sii.commons.data.dao.fve.offreformation.RattachementMcDao;
import dz.gov.mesrs.sii.commons.data.dao.fve.offreformation.RepartitionUeDao;
import dz.gov.mesrs.sii.commons.data.dao.fve.offreformation.UniteEnseignementDao;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.ParcoursType;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.RattachementMc;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.RepartitionUe;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.RattachementMcDto;
import dz.gov.mesrs.sii.fve.business.service.offreformation.RattachementMcService;

@Service("rattachementMcService")
public class RattachementMcServiceImpl implements RattachementMcService {

	@Autowired
	@Qualifier("rattachementMcDao")
	private RattachementMcDao rattachementMcDao;

	@Autowired
	@Qualifier("matiereConstitutiveDao")
	private MatiereConstitutiveDao matiereConstitutiveDao;

	@Autowired
	@Qualifier("uniteEnseignementDao")
	private UniteEnseignementDao uniteEnseignementDao;

	@Autowired
	@Qualifier("mapper")
	private DozerBeanMapper mapper;

	@Autowired
	@Qualifier("parcoursTypeDao")
	private ParcoursTypeDao parcoursTypeDao;

	@Autowired
	@Qualifier("repartitionUeDao")
	private RepartitionUeDao repartitionUeDao;

	public RattachementMcServiceImpl() {

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public RattachementMcDto insertOrUpdate(RattachementMcDto rattachementMcDto) {
		try {
			RattachementMc rattachementMc = mapper.map(rattachementMcDto,
					RattachementMc.class);
			if (rattachementMc.getId() == 0)
				rattachementMcDao.persist(rattachementMc);
			else
				rattachementMc = rattachementMcDao.merge(rattachementMc);
			rattachementMcDto = mapper.map(rattachementMc,
					RattachementMcDto.class);
			return rattachementMcDto;
		} catch (RuntimeException e) {
			throw e;
		}
	}

	@Override
	public void remove(RattachementMcDto rattachementMcDto) {

		RattachementMc rattachementMc = mapper.map(rattachementMcDto,
				RattachementMc.class);

		rattachementMc = rattachementMcDao.merge(rattachementMc);
		rattachementMcDao.remove(rattachementMc);
	}

	@Override
	public void remove(int ueId, int mcId) {
		rattachementMcDao.remove(ueId, mcId);

	}

	@Override
	public List<RattachementMcDto> find(Integer ueId, Integer mcId) {

		List<RattachementMc> _rattachements = rattachementMcDao
				.find(ueId, mcId);

		List<RattachementMcDto> _rattachementMcDtos = new ArrayList<RattachementMcDto>();

		for (RattachementMc _item : _rattachements) {
			_rattachementMcDtos.add(mapper.map(_item, RattachementMcDto.class));
		}
		return _rattachementMcDtos;
	}

	@Override
	public List<RattachementMcDto> findByOffreFormation(Integer offreFormationId) {
		try {
			if (offreFormationId == null || offreFormationId == 0) {
				return null;
			}
			List<ParcoursType> _parcoursTypeList = parcoursTypeDao
					.findByOfId(offreFormationId.intValue());
			if (_parcoursTypeList != null && !_parcoursTypeList.isEmpty()) {
				List<RepartitionUe> _repartitionUeList = repartitionUeDao
						.findByParcours(_parcoursTypeList.get(0).getId());
				if (_repartitionUeList != null && !_repartitionUeList.isEmpty()) {
					List<RattachementMcDto> result = new ArrayList<RattachementMcDto>();
					for (RepartitionUe repartitionUe : _repartitionUeList) {
						List<RattachementMc> _rattachementMcList = rattachementMcDao
								.find(repartitionUe.getUniteEnseignement()
										.getId(), null);
						if (_repartitionUeList != null
								&& !_repartitionUeList.isEmpty()) {

							for (RattachementMc rattachementMc : _rattachementMcList) {
								result.add(mapper.map(rattachementMc,
										RattachementMcDto.class));
							}

						}

					}
					return result;
				}
			}

		} catch (RuntimeException e) {

			throw e;

		}
		return null;

	}

	@Override
	public List<RattachementMcDto> findByOffreFormationAndPeriode(
			Integer offreFormationId, int idPeriode) {
		try {
			if (offreFormationId == null || offreFormationId == 0
					|| idPeriode == 0) {
				return null;
			}
			List<ParcoursType> _parcoursTypeList = parcoursTypeDao
					.findByOfId(offreFormationId.intValue());
			if (_parcoursTypeList != null && !_parcoursTypeList.isEmpty()) {
				List<RepartitionUe> _repartitionUeList = repartitionUeDao
						.findByParcoursAndPeriode(_parcoursTypeList.get(0)
								.getId(), idPeriode);
				if (_repartitionUeList != null && !_repartitionUeList.isEmpty()) {
					List<RattachementMcDto> result = new ArrayList<RattachementMcDto>();
					for (RepartitionUe repartitionUe : _repartitionUeList) {
						List<RattachementMc> _rattachementMcList = rattachementMcDao
								.find(repartitionUe.getUniteEnseignement()
										.getId(), null);
						if (_repartitionUeList != null
								&& !_repartitionUeList.isEmpty()) {

							for (RattachementMc rattachementMc : _rattachementMcList) {
								result.add(mapper.map(rattachementMc,
										RattachementMcDto.class));
							}

						}

					}
					return result;
				}
			}

		} catch (RuntimeException e) {

			throw e;

		}
		return null;

	}

	@Override
	public RattachementMcDto findById(Integer id) {
		try {
			RattachementMc rattachementMc = rattachementMcDao.findById(id);
			if (rattachementMc != null)
				return mapper.map(rattachementMc, RattachementMcDto.class);
		} catch (RuntimeException e) {

			throw e;

		}
		return null;
	}

	@Override
	public List<RattachementMcDto> findOptionnelle(Integer ueId, Integer mcId,
			Boolean optionnelle) {
		try {
			List<RattachementMc> _rattachements = rattachementMcDao
					.findOptionnelle(ueId, mcId, optionnelle);

			List<RattachementMcDto> _rattachementMcDtos = new ArrayList<RattachementMcDto>();

			for (RattachementMc _item : _rattachements) {
				_rattachementMcDtos.add(mapper.map(_item,
						RattachementMcDto.class));
			}
			return _rattachementMcDtos;
		} catch (RuntimeException e) {

			throw e;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.fve.business.service.offreformation.RattachementMcService
	 * #findUnique(int, int)
	 */
	@Override
	public RattachementMcDto findUnique(int ueId, int mcId) {
		try {
			RattachementMc rattachementMc = rattachementMcDao.findUnique(ueId,
					mcId);
			if (rattachementMc != null)
				return mapper.map(rattachementMc, RattachementMcDto.class);
		} catch (RuntimeException e) {

			throw e;

		}
		return null;
	}

}
