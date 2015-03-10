/**
 * [dz.gov.mesrs.sii.fve.business.service.impl.offreformation.CycleServiceImpl.java] 
 * @author rlaib on : 16 juil. 2014  10:54:44
 */
package dz.gov.mesrs.sii.fve.business.service.impl.offreformation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.fve.offreformation.ParamTypeDao;
import dz.gov.mesrs.sii.commons.data.dao.fve.offreformation.PeriodeDao;
import dz.gov.mesrs.sii.commons.data.dao.fve.offreformation.PeriodeParamDao;
import dz.gov.mesrs.sii.commons.data.dao.referentiel.NomenclatureDao;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.ParamType;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.Periode;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.PeriodeParam;
import dz.gov.mesrs.sii.commons.data.model.referentiel.Nomenclature;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.ParamTypeDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.PeriodeDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.PeriodeParamDto;
import dz.gov.mesrs.sii.fve.business.service.offreformation.PeriodeService;
import dz.gov.mesrs.sii.fve.business.util.UtilConstants;

/**
 * @author rlaib on : 16 juil. 2014 10:54:44
 */
@Service("periodeService")
public class PeriodeServiceImpl implements PeriodeService {

	@Autowired
	@Qualifier("periodeDao")
	private PeriodeDao periodeDao;

	@Autowired
	@Qualifier("periodeParamDao")
	private PeriodeParamDao periodeParamDao;

	@Autowired
	@Qualifier("paramTypeDao")
	private ParamTypeDao paramTypeDao;

	@Autowired
	@Qualifier("mapper")
	private DozerBeanMapper mapper;

	@Autowired
	@Qualifier("nomenclatureDaoImpl")
	private NomenclatureDao nomenclatureDao;

	/**
	 * [PeriodeServiceImpl.insertOrUpdate] Method
	 * 
	 * @author rlaib on : 16 juil. 2014 10:54:44
	 * @param cycleDto
	 * @return
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public PeriodeDto insertOrUpdate(PeriodeDto periodeDto) {

		if(periodeDto == null)
			return null;
		Periode periode = mapper.map(periodeDto, Periode.class);

		if (periode.getId() == 0)
			periodeDao.persist(periode);
		else
			periode = periodeDao.merge(periode);

		mapper.map(periode, periodeDto);
		return periodeDto;

	}

	/**
	 * [PeriodeServiceImpl.remove] Method
	 * 
	 * @author rlaib on : 16 juil. 2014 10:54:44
	 * @param cycleDto
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void remove(Integer idPeriode) {

		if(idPeriode == null)
			return;
		periodeDao.remove(idPeriode);

	}

	/**
	 * [PeriodeServiceImpl.findById] Method
	 * 
	 * @author rlaib on : 16 juil. 2014 10:54:44
	 * @param id
	 * @return
	 */
	@Override
	public PeriodeDto findById(Integer id) {

		if(id == null)
			return null;
		Periode periode = periodeDao.findById(id);
		if (periode != null)
			return mapper.map(periode, PeriodeDto.class);
		return null;
	}

	/**
	 * [PeriodeServiceImpl.findAll] Method
	 * 
	 * @author rlaib on : 16 juil. 2014 10:54:44
	 * @return
	 */
	@Override
	public List<PeriodeDto> findAll() {

		List<Periode> periodes = periodeDao.findAll();
		List<PeriodeDto> periodeDtos = new ArrayList<PeriodeDto>();
		if(periodes == null)
			return null;
		for (Periode periode : periodes) {
			PeriodeDto periodeDto = new PeriodeDto();
			periodeDto = mapper.map(periode, PeriodeDto.class);
			periodeDtos.add(periodeDto);
		}
		return periodeDtos;
	}

	/**
	 * [PeriodeServiceImpl.findByLevelId] Method
	 * 
	 * @author rlaib on : 16 juil. 2014 11:13:25
	 * @return
	 */
	@Override
	public List<PeriodeDto> findByLevelId(Integer idLevel) {

		if(idLevel == null)
			return null;
		List<Periode> periodes = periodeDao.findByLevelId(idLevel);
		if(periodes == null)
			return null;
		List<PeriodeDto> periodeDtos = new ArrayList<PeriodeDto>();
		for (Periode periode : periodes) {
			PeriodeDto periodeDto = new PeriodeDto();
			periodeDto = mapper.map(periode, PeriodeDto.class);
			periodeDtos.add(periodeDto);
		}
		return periodeDtos;
	}

	/**
	 * [PeriodeServiceImpl.findCurrentPeriodsByLevelId] Method
	 * 
	 * @author rlaib on : 21 oct. 2014 08:20:41
	 * @param idLevel
	 * @return
	 */
	@Override
	public List<PeriodeDto> findCurrentPeriodsByLevelId(Integer idLevel, Integer idAnnee) {

		if(idLevel == null || idAnnee == null)
			return null;
		
		List<Periode> periodes = periodeDao.findByLevelId(idLevel);
		if(periodes == null)
			return null;
		List<PeriodeDto> periodeDtos = new ArrayList<PeriodeDto>();
		for (Periode periode : periodes) {

			SimpleDateFormat dateFormat = null;
			PeriodeParam _param = periodeParamDao.findByCodeByPeriodByYear(
					UtilConstants.PERIODE_PARAM_CODE_DATE_DEBUT, idAnnee,
					periode.getId());
			if (_param != null) {

				Date beginDate = null;
				try {
					dateFormat = new SimpleDateFormat("dd/MM/yyyy");
					if (_param.getValeur() != null
							&& _param.getParamType().getCode().equals("Date")) {
						beginDate = dateFormat.parse(_param.getValeur());
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (beginDate != null && beginDate.before(new java.util.Date())) {
					PeriodeDto periodeDto = new PeriodeDto();
					periodeDto = mapper.map(periode, PeriodeDto.class);
					periodeDtos.add(periodeDto);
				}
			}

		}
		return periodeDtos;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dz.gov.mesrs.sii.fve.business.service.offreformation.PeriodeService#
	 * findByCycleId(int)
	 */
	@Override
	public List<PeriodeDto> findByCycleId(Integer idCycle) {

		if(idCycle == null)
			return null;
		List<Periode> periodes = periodeDao.findByCycleId(idCycle);
		List<PeriodeDto> periodeDtos = new ArrayList<PeriodeDto>();
		for (Periode periode : periodes) {
			PeriodeDto periodeDto = new PeriodeDto();
			periodeDto = mapper.map(periode, PeriodeDto.class);
			periodeDtos.add(periodeDto);
		}
		return periodeDtos;
	}

	@Override
	public PeriodeParamDto findPeriodParamById(Integer idPeriodeParam) {
		if(idPeriodeParam == null)
			return null;
		PeriodeParam periodeParam = periodeParamDao.findById(idPeriodeParam);
		if (periodeParam != null)
			return mapper.map(periodeParam, PeriodeParamDto.class);
		return null;
	}

	@Override
	public List<PeriodeParamDto> findPeriodParamByPeriodByYear(Integer idYear,
			Integer idPeriod) {

		if(idYear == null ||idPeriod ==null )
			return null;
		List<PeriodeParam> periodeParams = periodeParamDao.findByPeriodByYear(idPeriod ,idYear);
		if (periodeParams == null)
			return null;
		
		List<PeriodeParamDto> periodeParamDtos = new ArrayList<PeriodeParamDto>();
		for (PeriodeParam periodeParam : periodeParams) {
			PeriodeParamDto periodeParamDto = new PeriodeParamDto();
			periodeParamDto = mapper.map(periodeParam, PeriodeParamDto.class);
			periodeParamDtos.add(periodeParamDto);
		}
		return periodeParamDtos;
	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public PeriodeParamDto insertOrUpdate(PeriodeParamDto periodeParamDto) {

		if(periodeParamDto == null)
			return null;
		PeriodeParam periodeParam = mapper.map(periodeParamDto,
				PeriodeParam.class);

		if (periodeParam.getId() == 0)
			periodeParamDao.persist(periodeParam);
		else
			periodeParam = periodeParamDao.merge(periodeParam);

		mapper.map(periodeParam, periodeParamDto);
		return periodeParamDto;
	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void removePeriodeParam(Integer idPeriodeParam) {

		if(idPeriodeParam == null)
			return;
		periodeParamDao.remove(idPeriodeParam);
	}

	@Override
	public List<ParamTypeDto> getAllParamsTypes() {

		List<ParamType> paramTypes = paramTypeDao.findAll();
		List<ParamTypeDto> paramTypeDtos = new ArrayList<ParamTypeDto>();
		for (ParamType paramType : paramTypes) {
			ParamTypeDto paramTypeDto = new ParamTypeDto();
			paramTypeDto = mapper.map(paramType, ParamTypeDto.class);
			paramTypeDtos.add(paramTypeDto);
		}
		return paramTypeDtos;
	}

	@Override
	public ParamTypeDto findParamTypeById(Integer idType) {

		if(idType == null)
			return null;
		ParamType paramType = paramTypeDao.findParamTypeById(idType);
		if (paramType != null)
			return mapper.map(paramType, ParamTypeDto.class);
		return null;
	}

	@Override
	public PeriodeParamDto findParamByCodeByPeriodByYear(String codeParam,
			Integer idYear, Integer idPeriod) {

		if(codeParam == null ||idYear==null || idPeriod==null )
			return null;
		PeriodeParam periodeParam = periodeParamDao.findByCodeByPeriodByYear(
				codeParam, idYear, idPeriod);
		if (periodeParam != null)
			return mapper.map(periodeParam, PeriodeParamDto.class);
		return null;
	}

	@Override
	public PeriodeDto findByLevelAndYearPeriod(Integer idLevel, Integer idNcPeriode) {
		try {
			if (idLevel == null || idNcPeriode == null) {
				return null;
			}
			/**
			 * Not properly solution
			 *  Can be changed if we add idNcperiode for corresponding Period on Database
			 */
			List<Periode> periodes = periodeDao.findByLevelId(idLevel);
			if (periodes == null || periodes.isEmpty()) {
				return null;
			}
			Nomenclature yearPeriod = nomenclatureDao.findById(idNcPeriode);
			if (yearPeriod == null) {
				return null;
			}
			switch (yearPeriod.getCode()) {
			case "SMST1":
				return mapper.map(periodes.get(0), PeriodeDto.class);

			case "SMST2":
				return mapper.map(periodes.get(1), PeriodeDto.class);

			case "TRMT1":
				return mapper.map(periodes.get(0), PeriodeDto.class);

			case "TRMT2":
				return mapper.map(periodes.get(1), PeriodeDto.class);

			case "TRMT3":
				return mapper.map(periodes.get(2), PeriodeDto.class);

			case "TTAN":
				return mapper.map(periodes.get(0), PeriodeDto.class);

			default:
				return null;

			}

		} catch (RuntimeException e) {
			throw e;
		}
	}

	/**
	 * [PeriodeService.findPeriodParamByPeriodiciteByYear] Method 
	 * @author rlaib  on : 1 nov. 2014  16:07:42
	 * @param idYear
	 * @param codePeriodicite
	 * @return
	 */
	@Override
	public List<PeriodeParamDto> findPeriodParamByPeriodiciteByYear(Integer idYear,
			String codePeriodicite) {
		
		if (idYear == null || codePeriodicite == null) {
			return null;
		}
		List<Periode> _periodes = periodeDao.findByPeriodiciteCode(codePeriodicite);
		if(_periodes == null)
			return null;
		if(_periodes != null && _periodes.size()>0) {
			
				Integer idPeriode =_periodes.get(0).getId(); 
				List<PeriodeParam> periodeParams = periodeParamDao.findByPeriodByYear(idPeriode ,idYear);
				List<PeriodeParamDto> periodeParamDtos = new ArrayList<PeriodeParamDto>();
		
				for (PeriodeParam periodeParam : periodeParams) {
					PeriodeParamDto periodeParamDto = new PeriodeParamDto();
					periodeParamDto = mapper.map(periodeParam, PeriodeParamDto.class);
					periodeParamDtos.add(periodeParamDto);
				}
				return periodeParamDtos;
		}
		return null;
	}

	/**
	 * [PeriodeService.findParamByCodeByPeriodiciteByYear] Method 
	 * @author rlaib  on : 1 nov. 2014  16:12:26
	 * @param codeParam
	 * @param idYear
	 * @param codePeriodicite
	 * @return
	 */
	@Override
	public PeriodeParamDto findParamByCodeByPeriodiciteByYear(String codeParam,
			Integer idYear, String codePeriodicite) {
		
		if (codeParam == null || idYear ==null || codePeriodicite == null) {
			return null;
		}
				PeriodeParam periodeParam = periodeParamDao.findByCodeByPeriodiciteByYear(
							codeParam, idYear, codePeriodicite);
				if (periodeParam != null)
					return mapper.map(periodeParam, PeriodeParamDto.class);
		return null;
	}


	@Override
	public PeriodeDto findByLevelIdByIdNcPeriode(Integer idLevel, String idNcPeriode) {
		
		if(idLevel == null || idNcPeriode == null)
			return null;
		Periode periode = periodeDao.findByLevelIdByIdNcPeriode(idLevel, idNcPeriode);
		if (periode != null)
			return mapper.map(periode, PeriodeDto.class);
		return null;
	}

}
