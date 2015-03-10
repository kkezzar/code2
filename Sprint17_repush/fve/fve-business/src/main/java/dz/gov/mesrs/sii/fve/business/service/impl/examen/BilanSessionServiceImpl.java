/**
 * [dz.gov.mesrs.sii.fve.business.service.impl.cursus.BilanSessionServiceImpl.java] 
 * @author MAKERRI Sofiane on : 22 oct. 2014  15:52:00
 */
package dz.gov.mesrs.sii.fve.business.service.impl.examen;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.business.util.Utility;
import dz.gov.mesrs.sii.commons.data.dao.fve.examen.BilanSessionDao;
import dz.gov.mesrs.sii.commons.data.model.fve.examen.BilanMc;
import dz.gov.mesrs.sii.commons.data.model.fve.examen.BilanSession;
import dz.gov.mesrs.sii.commons.data.model.fve.examen.BilanUe;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.BilanSessionDto;
import dz.gov.mesrs.sii.fve.business.service.examen.BilanSessionService;
import dz.gov.mesrs.sii.fve.business.util.UtilConstants;

/**
 * @author MAKERRI Sofiane on : 22 oct. 2014 15:52:00
 */
@Service("bilanSessionService")
public class BilanSessionServiceImpl implements BilanSessionService {

	/**
	 * [BilanSessionServiceImpl.BilanSessionServiceImpl()] Constructor
	 * 
	 * @author MAKERRI Sofiane on : 22 oct. 2014 15:52:00
	 */
	@Autowired
	@Qualifier("bilanSessionDao")
	private BilanSessionDao bilanSessionDao;

	private static final Log log = LogFactory
			.getLog(BilanSessionServiceImpl.class);

	@Autowired
	@Qualifier("mapper")
	private DozerBeanMapper mapper;

	public BilanSessionServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public BilanSessionDto insertOrUpdate(BilanSessionDto bilanSessionDto) {

		BilanSession bilanSession = mapper.map(bilanSessionDto,
				BilanSession.class);
		// if(bilanSession.getMention() != null &&
		// bilanSession.getMention().getId() == 0) {
		// bilanSession.setMention(null);
		// }
		try {
			List<BilanSession> bilanPeriodeList = bilanSession
					.getBilanSessions();

			if (bilanSession.getId() == 0) {
				// BilanSession _bilanSession = mapper.map(bilanSession,
				// BilanSession.class);
				// bilanSession.setBilanUes(null);

				bilanSession.setBilanSessions(null);
				bilanSessionDao.persist(bilanSession);

				List<BilanUe> listUe = bilanSession.getBilanUes();
				if (listUe != null) {
					for (BilanUe _ue : listUe) {
						_ue.setBilanSession(bilanSession);
						List<BilanMc> listMc = _ue.getBilanMcs();
						if (listMc != null) {
							for (BilanMc _mc : listMc) {
								_mc.setBilanUe(_ue);
							}
						}
					}
				}
				// bilanSession.setBilanUes(_bilanSession.getBilanUes());
			} else {
				bilanSession = bilanSessionDao.merge(bilanSession);
			}
			if (bilanPeriodeList != null) {
				for (BilanSession _bilanPeriode : bilanPeriodeList) {
					_bilanPeriode.setBilanSession(bilanSession);
					bilanSessionDao.merge(_bilanPeriode);
				}

			}
			bilanSessionDto = new BilanSessionDto();
			mapper.map(bilanSession, bilanSessionDto);

			log.info("insertorupdate BilanSession succes..");

			return bilanSessionDto;

		} catch (RuntimeException e) {
			log.error("insertorupdate BilanSession failed--", e);
			throw e;
		}
	}

	@Override
	public void remove(BilanSessionDto bilanSessionDto) {
		try {

			BilanSession bilanSession = mapper.map(bilanSessionDto,
					BilanSession.class);

			bilanSession = bilanSessionDao.merge(bilanSession);

			bilanSessionDao.remove(bilanSession);
		} catch (RuntimeException e) {
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public BilanSessionDto findById(long id) {

		BilanSession bilanSession = bilanSessionDao.findById(id);

		if (bilanSession != null)
			return mapper.map(bilanSession, BilanSessionDto.class);

		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public List<BilanSessionDto> findAll() {

		List<BilanSession> bilanSessions = bilanSessionDao.findAll();

		List<BilanSessionDto> bilanSessionDtos = new ArrayList<BilanSessionDto>();

		for (BilanSession bilanSession : bilanSessions) {
			bilanSessionDtos.add(mapper
					.map(bilanSession, BilanSessionDto.class));
		}

		return bilanSessionDtos;

	}

	@Override
	@Transactional(readOnly = true)
	public List<BilanSessionDto> findByDeliberation(Long deliberationId) {
		try {
			List<BilanSession> bilanSessions = bilanSessionDao
					.findByDeliberation(deliberationId);

			List<BilanSessionDto> bilanSessionDtos = new ArrayList<BilanSessionDto>();
			if (bilanSessions != null) {
				for (BilanSession bilanSession : bilanSessions) {
					bilanSessionDtos.add(mapper.map(bilanSession,
							BilanSessionDto.class));
				}
			}

			return bilanSessionDtos;

		} catch (RuntimeException e) {
			throw e;
		}

	}

	@Override
	@Transactional(readOnly = true)
	public List<BilanSessionDto> findByOofAndNiveau(Integer oofId,
			Integer niveauId) {
		try {
			List<BilanSession> bilanSessions = bilanSessionDao
					.findByOofAndNiveau(oofId, niveauId);

			List<BilanSessionDto> bilanSessionDtos = new ArrayList<BilanSessionDto>();
			if (bilanSessions != null) {
				for (BilanSession bilanSession : bilanSessions) {
					bilanSessionDtos.add(mapper.map(bilanSession,
							BilanSessionDto.class));
				}
			}

			return bilanSessionDtos;

		} catch (RuntimeException e) {
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<BilanSessionDto> findByPeriode(Integer oofId, Integer periodeId) {
		try {
			List<BilanSession> bilanSessions = bilanSessionDao.findByPeriode(
					oofId, periodeId);

			List<BilanSessionDto> bilanSessionDtos = new ArrayList<BilanSessionDto>();
			if (bilanSessions != null) {
				for (BilanSession bilanSession : bilanSessions) {
					bilanSessionDtos.add(mapper.map(bilanSession,
							BilanSessionDto.class));
				}
			}

			return bilanSessionDtos;

		} catch (RuntimeException e) {
			throw e;
		}
	}

	/**
	 * Recherche avancee dans la table des bilans annuel
	 * 
	 * @author Mounir.MESSAOUDI on : 26 oct. 2014 11:29:27
	 * @param searchDto
	 * @return
	 */
	@Override
	@Transactional(readOnly = true)
	public List<BilanSessionDto> findAdvanced(BilanSessionDto searchDto) {
		try {
			BilanSession searchBo = mapper.map(searchDto, BilanSession.class);

			List<BilanSession> bilanSessions = bilanSessionDao
					.findAdvanced(searchBo);

			List<BilanSessionDto> bilanSessionDtos = new ArrayList<BilanSessionDto>();
			if (bilanSessions != null && !bilanSessions.isEmpty()) {
				for (BilanSession bilanSession : bilanSessions) {
					bilanSessionDtos.add(mapper.map(bilanSession,
							BilanSessionDto.class));
				}
			}

			return bilanSessionDtos;
		} catch (RuntimeException e) {
			throw e;
		}
	}

	/**
	 * Rechercher le bilan finale par id dossier inscription administrative
	 * 
	 * @author Mounir.MESSAOUDI on : 26 oct. 2014 11:33:11
	 * @param diaId
	 * @return
	 */
	@Override
	@Transactional(readOnly = true)
	public BilanSessionDto findBilanFinalByIdDossierInscrAdmin(Integer diaId) {
		try {

			BilanSession bilanSession = bilanSessionDao
					.findBilanFinalByIdDossierInscrAdmin(diaId);

			if (bilanSession != null) {
				return mapper.map(bilanSession, BilanSessionDto.class);
			}

		} catch (RuntimeException e) {
			throw e;
		}

		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public BilanSessionDto findUniquePeriode(Long deliberationId, Integer diaId) {
		try {
			return findUnique(deliberationId, diaId,
					UtilConstants.BILAN_TYPE_PERIODE);

		} catch (RuntimeException e) {
			throw e;
		}

	}

	@Override
	@Transactional(readOnly = true)
	public BilanSessionDto findUniqueSession(Long deliberationId, Integer diaId) {
		try {
			return findUnique(deliberationId, diaId,
					UtilConstants.BILAN_TYPE_SESSION);

		} catch (RuntimeException e) {
			throw e;
		}
	}

	/**
	 * [BilanSessionServiceImpl.findUnique] Method
	 * 
	 * @author MAKERRI Sofiane on : 27 oct. 2014 11:08:24
	 * @param deliberationId
	 * @param diaId
	 * @param type
	 * @return
	 */
	private BilanSessionDto findUnique(Long deliberationId, Integer diaId,
			int type) {
		try {
			BilanSession bilanSession = bilanSessionDao.findUnique(
					deliberationId, diaId, type);
			if (bilanSession != null) {
				return mapper.map(bilanSession, BilanSessionDto.class);
			}

		} catch (RuntimeException e) {
			throw e;
		}
		return null;
	}

	@Override
	public List<BilanSessionDto> findBilanPeriodeByNiveau(Integer oofId,
			Integer niveauId) {
		try {
			List<BilanSession> bilanSessions = bilanSessionDao
					.findBilanPeriodeByNiveau(oofId, niveauId);

			List<BilanSessionDto> bilanSessionDtos = new ArrayList<BilanSessionDto>();
			if (bilanSessions != null) {
				for (BilanSession bilanSession : bilanSessions) {
					bilanSessionDtos.add(mapper.map(bilanSession,
							BilanSessionDto.class));
				}
			}

			return bilanSessionDtos;

		} catch (RuntimeException e) {
			throw e;
		}
	}

	@Override
	public BilanSessionDto findUniqueAnnuel(Long deliberationId, Integer diaId) {
		try {
			return findUnique(deliberationId, diaId,
					UtilConstants.BILAN_TYPE_ANNUEL);

		} catch (RuntimeException e) {
			throw e;
		}
	}

	@Override
	public List<BilanSessionDto> findBilanFinal(Integer oofId, Integer niveauId) {
		try {
			List<BilanSession> bilanSessions = bilanSessionDao.findBilanFinal(
					oofId, niveauId);

			List<BilanSessionDto> bilanSessionDtos = new ArrayList<BilanSessionDto>();
			if (bilanSessions != null) {
				for (BilanSession bilanSession : bilanSessions) {
					bilanSessionDtos.add(mapper.map(bilanSession,
							BilanSessionDto.class));
				}
			}

			return bilanSessionDtos;

		} catch (RuntimeException e) {
			throw e;
		}
	}

	@Override
	public List<BilanSessionDto> findByOffAnnee(Integer anneeId, Integer oofId) {
		try {
			List<BilanSession> bilanSessions = bilanSessionDao.findByOffAnnee(
					anneeId, oofId);

			List<BilanSessionDto> bilanSessionDtos = new ArrayList<BilanSessionDto>();
			if (bilanSessions != null) {
				for (BilanSession bilanSession : bilanSessions) {
					bilanSessionDtos.add(mapper.map(bilanSession,
							BilanSessionDto.class));
				}
			}

			return bilanSessionDtos;

		} catch (RuntimeException e) {
			throw e;
		}
	}

	@Override
	public List<BilanSessionDto> findBilanFinalDiplomeNonGenerer(Integer oofId,
			Integer niveauId) {
		try {
			List<BilanSession> bilanSessions = bilanSessionDao
					.findBilanFinalDiplomeNonGenerer(oofId, niveauId);

			List<BilanSessionDto> bilanSessionDtos = new ArrayList<BilanSessionDto>();
			if (bilanSessions != null) {
				for (BilanSession bilanSession : bilanSessions) {
					bilanSessionDtos.add(mapper.map(bilanSession,
							BilanSessionDto.class));
				}
			}

			return bilanSessionDtos;

		} catch (RuntimeException e) {
			throw e;
		}
	}

	@Override
	public List<BilanSessionDto> findBilanPeriode(Integer oofId,
			Integer periodeId) {
		try {
			List<BilanSession> bilanSessions = bilanSessionDao
					.findBilanPeriode(oofId, periodeId);

			List<BilanSessionDto> bilanSessionDtos = new ArrayList<BilanSessionDto>();
			if (bilanSessions != null) {
				for (BilanSession bilanSession : bilanSessions) {
					BilanSessionDto _bilanSessionDto = mapper.map(bilanSession,
							BilanSessionDto.class);
					List<BilanSessionDto> _bilanSessionDtos = _bilanSessionDto
							.getBilanSessionDtos();
					if (_bilanSessionDtos != null) {
						for (BilanSessionDto _bilan : _bilanSessionDtos) {
							_bilan.setColumnIntitule(_bilan.getPsIntitule());
						}
					}
					bilanSessionDtos.add(_bilanSessionDto);
				}
			}

			return bilanSessionDtos;

		} catch (RuntimeException e) {
			throw e;
		}
	}

	@Override
	public List<BilanSessionDto> findByDeliberationAnDia(Long deliberationId,
			Integer diaId) {
		try {
			List<BilanSession> bilanSessions = bilanSessionDao
					.findByDeliberationAnDia(deliberationId, diaId);

			List<BilanSessionDto> bilanSessionDtos = new ArrayList<BilanSessionDto>();
			if (bilanSessions != null) {
				for (BilanSession bilanSession : bilanSessions) {
					bilanSessionDtos.add(mapper.map(bilanSession,
							BilanSessionDto.class));
				}
			}

			return bilanSessionDtos;

		} catch (RuntimeException e) {
			throw e;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dz.gov.mesrs.sii.fve.business.service.cursus.BilanSessionService#
	 * findBilanSession(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<BilanSessionDto> findBilanSession(Integer oofId,
			Integer periodeId) {
		try {
			List<BilanSession> bilanSessions = bilanSessionDao.findByPeriode(
					oofId, periodeId);

			List<BilanSessionDto> bilanSessionDtos = new ArrayList<BilanSessionDto>();
			if (bilanSessions != null) {
				for (BilanSession bilanSession : bilanSessions) {
					bilanSessionDtos.add(mapper.map(bilanSession,
							BilanSessionDto.class));
				}
			}

			return bilanSessionDtos;

		} catch (RuntimeException e) {
			throw e;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dz.gov.mesrs.sii.fve.business.service.cursus.BilanSessionService#
	 * findBilanAnnuel(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<BilanSessionDto> findBilanAnnuel(Integer oofId, Integer niveauId) {
		try {
			List<BilanSession> bilanSessions = bilanSessionDao.findBilanAnnuel(
					oofId, niveauId);

			List<BilanSessionDto> bilanSessionDtos = new ArrayList<BilanSessionDto>();
			if (bilanSessions != null) {
				for (BilanSession bilanSession : bilanSessions) {
					BilanSessionDto _bilanSessionDto = mapper.map(bilanSession,
							BilanSessionDto.class);
					_bilanSessionDto.setColumnIntitule(_bilanSessionDto
							.getPeriodeLibelleFr());
					bilanSessionDtos.add(_bilanSessionDto);
				}
			}

			return bilanSessionDtos;

		} catch (RuntimeException e) {
			throw e;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dz.gov.mesrs.sii.fve.business.service.examen.BilanSessionService#
	 * findBilanFinCycle(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<BilanSessionDto> findBilanFinCycle(Integer oofId,
			Integer niveauId) {
		try {
			List<BilanSession> bilanSessions = bilanSessionDao
					.findBilanFinCycle(oofId, niveauId);

			return Utility.map(mapper, bilanSessions, BilanSessionDto.class);
		} catch (RuntimeException e) {
			throw e;
		}
	}

}
