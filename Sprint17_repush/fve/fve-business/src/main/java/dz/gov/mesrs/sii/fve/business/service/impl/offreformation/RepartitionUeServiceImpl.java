package dz.gov.mesrs.sii.fve.business.service.impl.offreformation;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.commons.data.dao.fve.offreformation.ParcoursTypeDao;
import dz.gov.mesrs.sii.commons.data.dao.fve.offreformation.PeriodeDao;
import dz.gov.mesrs.sii.commons.data.dao.fve.offreformation.RepartitionUeDao;
import dz.gov.mesrs.sii.commons.data.dao.fve.offreformation.UniteEnseignementDao;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.GroupeRepartitionUeAChoix;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.ParcoursType;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.Periode;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.RepartitionUe;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.UniteEnseignement;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.RepartitionUeDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.UniteEnseignementDto;
import dz.gov.mesrs.sii.fve.business.service.offreformation.RepartitionUeService;

@Service("repartitionUeService")
public class RepartitionUeServiceImpl implements RepartitionUeService {

	@Autowired
	@Qualifier("repartitionUeDao")
	private RepartitionUeDao repartitionUeDao;

	@Autowired
	@Qualifier("parcoursTypeDao")
	private ParcoursTypeDao parcoursTypeDao;
	@Autowired
	@Qualifier("uniteEnseignementDao")
	private UniteEnseignementDao uniteEnseignementDao;

	@Autowired
	@Qualifier("periodeDao")
	private PeriodeDao periodeDao;

	@Autowired
	@Qualifier("mapper")
	private DozerBeanMapper mapper;
	private static final Log log = LogFactory
			.getLog(RepartitionUeServiceImpl.class);

	public RepartitionUeServiceImpl() {

	}

	@Override
	public RepartitionUeDto insertOrUpdate(RepartitionUeDto repartitionUeDto) {
		//
		// ParcoursType parcoursType = parcoursTypeDao.findById(repartitionUeDto
		// .getParcoursTypeId());
		// UniteEnseignement ue = uniteEnseignementDao.findById(repartitionUeDto
		// .getUniteEnseignementId());
		// String refCodeSemestre = repartitionUeDto.getRefCodeSemestre();
		//
		// if (parcoursType == null && ue == null && refCodeSemestre == null)
		// return;
		//
		// RepartitionUe _repartition = new RepartitionUe();
		// _repartition.setParcoursType(parcoursType);
		// _repartition.setUniteEnseignement(ue);
		// _repartition.setRefCodeSemestre(refCodeSemestre);
		//
		// // parcoursType.getRepartitionUes().add(_repartition);
		// ue.getRepartitionUes().add(_repartition);
		//
		// repartitionUeDao.persist(_repartition);

		RepartitionUe repartitionUe = mapper.map(repartitionUeDto,
				RepartitionUe.class);
		ParcoursType parcoursType = parcoursTypeDao.findById(repartitionUeDto
				.getParcoursTypeId());

		UniteEnseignement ue = uniteEnseignementDao.findById(repartitionUeDto
				.getUniteEnseignementId());

		Periode periode = periodeDao.findById(repartitionUeDto.getIdPeriode());

		GroupeRepartitionUeAChoix groupeRepartitionUeAChoix = null;
		if (repartitionUeDto.getGroupAChoixId() != null)
			groupeRepartitionUeAChoix = repartitionUeDao
					.findGroupeRepartitionUeAChoixById(repartitionUeDto
							.getGroupAChoixId());

		if (parcoursType == null || ue == null || periode == null)
			return null;

		parcoursType.setI18n(null);
		parcoursType.setRepartitionUes(null);
		repartitionUe.setParcoursType(parcoursType);
		repartitionUe.setUniteEnseignement(ue);
		repartitionUe.setPeriode(periode);
		repartitionUe.setGroupeRepartitionUeAChoix(groupeRepartitionUeAChoix);
		if (repartitionUe.getId() == 0)
			repartitionUeDao.persist(repartitionUe);
		else
			repartitionUe = repartitionUeDao.merge(repartitionUe);

		mapper.map(repartitionUe, repartitionUeDto);
		return repartitionUeDto;

	}

	@Override
	public void remove(Integer id) {

		repartitionUeDao.remove(id);
	}

	@Override
	public void remove(int parcoursId, String refCodeSemestre, int ueId) {
		repartitionUeDao.remove(parcoursId, refCodeSemestre, ueId);

	}

	@Override
	public List<RepartitionUeDto> find(String refCodeSemestre,
			Integer parcoursId, Integer ueId) {

		List<RepartitionUe> _repartitionUes = repartitionUeDao.find(
				refCodeSemestre, parcoursId, ueId);

		List<RepartitionUeDto> repartition_UeDtos = new ArrayList<RepartitionUeDto>();

		for (RepartitionUe repartitionUeParcours : _repartitionUes) {
			repartition_UeDtos.add(mapper.map(repartitionUeParcours,
					RepartitionUeDto.class));
		}

		return repartition_UeDtos;
	}

	@Override
	public List<UniteEnseignementDto> findUeOof(Integer oofId,
			String refCodeSemestre) {
		List<UniteEnseignement> _Ues = repartitionUeDao.findUeOof(oofId,
				refCodeSemestre);

		List<UniteEnseignementDto> ueDtos = new ArrayList<UniteEnseignementDto>();

		for (UniteEnseignement _ue : _Ues) {
			ueDtos.add(mapper.map(_ue, UniteEnseignementDto.class));
		}

		return ueDtos;
	}

	/**
	 * [RepartitionUeServiceImpl.repartitionUeDao] Getter
	 * 
	 * @author BELDI Jamel on : 10 sept. 2014 09:11:38
	 * @return the repartitionUeDao
	 */
	public RepartitionUeDao getRepartitionUeDao() {
		return repartitionUeDao;
	}

	/**
	 * [RepartitionUeServiceImpl.repartitionUeDao] Setter
	 * 
	 * @author BELDI Jamel on : 10 sept. 2014 09:11:38
	 * @param repartitionUeDao
	 *            the repartitionUeDao to set
	 */
	public void setRepartitionUeDao(RepartitionUeDao repartitionUeDao) {
		this.repartitionUeDao = repartitionUeDao;
	}

	/**
	 * [RepartitionUeServiceImpl.parcoursTypeDao] Getter
	 * 
	 * @author BELDI Jamel on : 10 sept. 2014 09:11:38
	 * @return the parcoursTypeDao
	 */
	public ParcoursTypeDao getParcoursTypeDao() {
		return parcoursTypeDao;
	}

	/**
	 * [RepartitionUeServiceImpl.parcoursTypeDao] Setter
	 * 
	 * @author BELDI Jamel on : 10 sept. 2014 09:11:38
	 * @param parcoursTypeDao
	 *            the parcoursTypeDao to set
	 */
	public void setParcoursTypeDao(ParcoursTypeDao parcoursTypeDao) {
		this.parcoursTypeDao = parcoursTypeDao;
	}

	/**
	 * [RepartitionUeServiceImpl.uniteEnseignementDao] Getter
	 * 
	 * @author BELDI Jamel on : 10 sept. 2014 09:11:38
	 * @return the uniteEnseignementDao
	 */
	public UniteEnseignementDao getUniteEnseignementDao() {
		return uniteEnseignementDao;
	}

	/**
	 * [RepartitionUeServiceImpl.uniteEnseignementDao] Setter
	 * 
	 * @author BELDI Jamel on : 10 sept. 2014 09:11:38
	 * @param uniteEnseignementDao
	 *            the uniteEnseignementDao to set
	 */
	public void setUniteEnseignementDao(
			UniteEnseignementDao uniteEnseignementDao) {
		this.uniteEnseignementDao = uniteEnseignementDao;
	}

	/**
	 * [RepartitionUeServiceImpl.mapper] Getter
	 * 
	 * @author BELDI Jamel on : 10 sept. 2014 09:11:38
	 * @return the mapper
	 */
	public DozerBeanMapper getMapper() {
		return mapper;
	}

	/**
	 * [RepartitionUeServiceImpl.mapper] Setter
	 * 
	 * @author BELDI Jamel on : 10 sept. 2014 09:11:38
	 * @param mapper
	 *            the mapper to set
	 */
	public void setMapper(DozerBeanMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public List<UniteEnseignementDto> findUeOofAndPeriode(Integer oofId,
			int idPeriode) {

		List<UniteEnseignement> _Ues = repartitionUeDao
				.findUeOoffindUeOofAndPeriode(oofId, idPeriode);

		List<UniteEnseignementDto> ueDtos = new ArrayList<UniteEnseignementDto>();

		for (UniteEnseignement _ue : _Ues) {
			ueDtos.add(mapper.map(_ue, UniteEnseignementDto.class));
		}

		return ueDtos;

	}

	@Override
	public List<UniteEnseignementDto> findUeOof(Integer oofId, Integer periodeId) {
		List<UniteEnseignement> _Ues = repartitionUeDao.findUeOof(oofId,
				periodeId);

		List<UniteEnseignementDto> ueDtos = new ArrayList<UniteEnseignementDto>();

		for (UniteEnseignement _ue : _Ues) {
			ueDtos.add(mapper.map(_ue, UniteEnseignementDto.class));
		}

		return ueDtos;
	}

	/**
	 * [RepartitionUeServiceImpl.findByParcoursAndPeriode] Method
	 * 
	 * @author rlaib on : 12 sept. 2014 16:06:56
	 * @param parcoursId
	 * @param idPeriode
	 * @return
	 */
	@Override
	public List<RepartitionUeDto> findByParcoursAndPeriode(int parcoursId,
			int idPeriode) {

		List<RepartitionUe> _repartitionUes = repartitionUeDao
				.findByParcoursAndPeriode(parcoursId, idPeriode);

		List<RepartitionUeDto> repartitionUeDtos = new ArrayList<RepartitionUeDto>();

		for (RepartitionUe _repartitionUe : _repartitionUes) {
			repartitionUeDtos.add(mapper.map(_repartitionUe,
					RepartitionUeDto.class));
		}

		return repartitionUeDtos;
	}

	/**
	 * [RepartitionUeServiceImpl.findById] Method
	 * 
	 * @author rlaib on : 12 sept. 2014 16:06:50
	 * @param id
	 * @return
	 */
	@Override
	public RepartitionUeDto findById(int id) {

		RepartitionUe repartitionUe = repartitionUeDao.findById(id);
		if (repartitionUe != null) {
			RepartitionUeDto repartitionUeDto = new RepartitionUeDto();
			repartitionUeDto = mapper
					.map(repartitionUe, RepartitionUeDto.class);
			return repartitionUeDto;
		}
		return null;
	}

	@Override
	public List<UniteEnseignementDto> getAvailableUesPeriod(int parcoursId,
			int idPeriode) {

		List<UniteEnseignement> _Ues = repartitionUeDao.getAvailableUesPeriod(
				parcoursId, idPeriode);
		List<UniteEnseignementDto> ueDtos = new ArrayList<UniteEnseignementDto>();

		for (UniteEnseignement _ue : _Ues) {
			ueDtos.add(mapper.map(_ue, UniteEnseignementDto.class));
		}

		return ueDtos;
	}

	@Override
	public List<RepartitionUeDto> findReparationUe(Integer oofId,
			Integer periodeId) {
		try {
			List<RepartitionUe> _Ues = repartitionUeDao.findReparationUe(oofId,
					periodeId);

			List<RepartitionUeDto> ueDtos = new ArrayList<RepartitionUeDto>();

			for (RepartitionUe _ue : _Ues) {
				ueDtos.add(mapper.map(_ue, RepartitionUeDto.class));
			}

			return ueDtos;
		} catch (RuntimeException e) {
			log.error("findReparationUe failed", e);
			throw e;

		}
	}
}
