package dz.gov.mesrs.sii.recherche.business.service.impl;

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

import dz.gov.mesrs.sii.commons.data.dao.bpm.SituationEntiteOccurrenceDao;
import dz.gov.mesrs.sii.commons.data.dao.recherche.EvaluationChercheurDao;
import dz.gov.mesrs.sii.commons.data.dao.recherche.EvaluationValeurDao;
import dz.gov.mesrs.sii.commons.data.dao.recherche.GrilleEvaluationDao;
import dz.gov.mesrs.sii.commons.data.dao.recherche.GrilleEvaluationDetailDao;
import dz.gov.mesrs.sii.commons.data.model.recherche.EvaluationChercheur;
import dz.gov.mesrs.sii.commons.data.model.recherche.EvaluationValeur;
import dz.gov.mesrs.sii.commons.data.model.recherche.GrilleEvaluation;
import dz.gov.mesrs.sii.commons.data.model.recherche.GrilleEvaluationDetail;
import dz.gov.mesrs.sii.recherche.business.model.dto.EvaluationChercheurDto;
import dz.gov.mesrs.sii.recherche.business.model.dto.EvaluationValeurDto;
import dz.gov.mesrs.sii.recherche.business.model.dto.GrilleEvaluationDetailDto;
import dz.gov.mesrs.sii.recherche.business.model.dto.GrilleEvaluationDto;
import dz.gov.mesrs.sii.recherche.business.service.RechercheEvaluationChercheurService;

@Service("rechercheEvaluationChercheurService")
public class RechercheEvaluationChercheurServiceImpl implements
								RechercheEvaluationChercheurService {

	@Autowired
	@Qualifier ("grilleEvaluationDao")
	private GrilleEvaluationDao  grilleEvaluationDao;
	
	@Autowired
	@Qualifier ("grilleEvaluationDetailDao")
	private GrilleEvaluationDetailDao  grilleEvaluationDetailDao;
	
	@Autowired
	@Qualifier ("evaluationChercheurDao")
	private EvaluationChercheurDao  evaluationChercheurDao;
	
	@Autowired
	@Qualifier ("evaluationValeurDao")
	private EvaluationValeurDao  evaluationValeurDao;
	
	@Autowired
	@Qualifier ("situationEntiteOccurrenceDao")
	private SituationEntiteOccurrenceDao  situationEntiteOccurrenceDao;
	
	@Autowired
	@Qualifier("mapper")
	private DozerBeanMapper mapper;
	
	private static final Log log = LogFactory.getLog(RechercheProgrammeServiceImpl.class);
	
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public GrilleEvaluationDto insertOrUpdate(GrilleEvaluationDto grilleEvaluationDto) {
		
		GrilleEvaluation grilleEvaluation = mapper.map(grilleEvaluationDto,
				GrilleEvaluation.class);
		try {
					if (grilleEvaluation.getId() == null){
						grilleEvaluationDao.persist(grilleEvaluation);
					}else{
						grilleEvaluation = grilleEvaluationDao.merge(grilleEvaluation);
					}
					mapper.map(grilleEvaluation , grilleEvaluationDto);
					return grilleEvaluationDto;
		
		} catch (RuntimeException re) {
					log.error("insertOrUpdate grilleEvaluationDto failed : ", re);
					throw re;
		}
	}

	@Override
	public GrilleEvaluationDto findById(Long id) {
		
		try {
			GrilleEvaluation grilleEvaluation = grilleEvaluationDao.findById(id);
			if (grilleEvaluation != null) {
				GrilleEvaluationDto grilleEvaluationDto = new GrilleEvaluationDto();
					mapper.map(grilleEvaluation, grilleEvaluationDto);
					return grilleEvaluationDto;
			}
			return null;

		} catch (RuntimeException re) {
			log.error("findById GrilleEvaluationDto failed : ", re);
			throw re;
		}
	}

	@Override
	public List<GrilleEvaluationDto> findByKeyWords(String keyWord) {
		
		try {
			List<GrilleEvaluation> grilleEvaluations= grilleEvaluationDao.findByKeyWords(keyWord);
			List<GrilleEvaluationDto> grilleEvaluationDtos = new ArrayList<GrilleEvaluationDto>();
			for (GrilleEvaluation grilleEvaluation : grilleEvaluations) {
				GrilleEvaluationDto grilleEvaluationDto = new GrilleEvaluationDto();
				mapper.map(grilleEvaluation, grilleEvaluationDto);
				grilleEvaluationDtos.add(grilleEvaluationDto);
			}
			return grilleEvaluationDtos;
		} catch (RuntimeException re) {
			log.error("findByKeyWords GrilleEvaluationDto", re);
			throw re;
		}
	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public GrilleEvaluationDetailDto saveDetail(GrilleEvaluationDetailDto grilleEvaluationDetailDto) {

		GrilleEvaluationDetail grilleEvaluationDetail = mapper.map(grilleEvaluationDetailDto,
				GrilleEvaluationDetail.class);
		try {
					if(grilleEvaluationDetailDto.getNcCritereId()==null){
						grilleEvaluationDetail.setNcCritere(null);
					}
					if (grilleEvaluationDetail.getId() == null){
						grilleEvaluationDetailDao.persist(grilleEvaluationDetail);
					}else{
						grilleEvaluationDetail = grilleEvaluationDetailDao.merge(grilleEvaluationDetail);
					}
					mapper.map(grilleEvaluationDetail , grilleEvaluationDetailDto);
					return grilleEvaluationDetailDto;
		
		} catch (RuntimeException re) {
					log.error("insertOrUpdate grilleEvaluationDetailDto failed : ", re);
					throw re;
		}
	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void removeDetail(Long idDetail) {
		grilleEvaluationDetailDao.remove(idDetail);
		
	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void removeOneGrid(Long idEvaluationGrid) {
		
		grilleEvaluationDao.remove(idEvaluationGrid);
		
	}

	@Override
	public List<GrilleEvaluationDetailDto> findGridDetails(Long idGrid) {
		try {
			List<GrilleEvaluationDetail> grilleEvaluationDetails= grilleEvaluationDetailDao.findGridDetails(idGrid);
			List<GrilleEvaluationDetailDto> grilleEvaluationDetailDtos = new ArrayList<GrilleEvaluationDetailDto>();
			for (GrilleEvaluationDetail grilleEvaluationDetail : grilleEvaluationDetails) {
				GrilleEvaluationDetailDto grilleEvaluationDetailDto = new GrilleEvaluationDetailDto();
				if(grilleEvaluationDetail.getNcCritere()==null){
					grilleEvaluationDetailDto.setNcCritereId(0);
					grilleEvaluationDetailDto.setNumeroChapitre(grilleEvaluationDetail.getNumero());
					grilleEvaluationDetailDto.setNumeroCritere("");
				}
				else {
					grilleEvaluationDetailDto.setNumeroChapitre("");
					grilleEvaluationDetailDto.setNumeroCritere(grilleEvaluationDetail.getNumero());
				}
				mapper.map(grilleEvaluationDetail, grilleEvaluationDetailDto);
				grilleEvaluationDetailDtos.add(grilleEvaluationDetailDto);
			}
			return grilleEvaluationDetailDtos;
		} catch (RuntimeException re) {
			log.error("findGridDetails GrilleEvaluationDetailDto", re);
			throw re;
		}
	
	}

	@Override
	public GrilleEvaluationDetailDto findOneDetailById(Long id) {
		
		try {
				GrilleEvaluationDetail grilleEvaluationDetail = grilleEvaluationDetailDao.findById(id);
				if (grilleEvaluationDetail != null) {
					GrilleEvaluationDetailDto grilleEvaluationDetailDto = new GrilleEvaluationDetailDto();
					mapper.map(grilleEvaluationDetail, grilleEvaluationDetailDto);
					return grilleEvaluationDetailDto;
				}
				return null;

		} catch (RuntimeException re) {
			log.error("findById grilleEvaluationDetailDto failed : ", re);
			throw re;
		}
	
	}

	@Override
	public List<GrilleEvaluationDto> findAllGrids() {
		
		try {
			List<GrilleEvaluation> grilleEvaluations= grilleEvaluationDao.findAll();
			List<GrilleEvaluationDto> grilleEvaluationDtos = new ArrayList<GrilleEvaluationDto>();
			for (GrilleEvaluation grilleEvaluation : grilleEvaluations) {
				GrilleEvaluationDto grilleEvaluationDto = new GrilleEvaluationDto();
				mapper.map(grilleEvaluation, grilleEvaluationDto);
				grilleEvaluationDtos.add(grilleEvaluationDto);
			}
			return grilleEvaluationDtos;
		} catch (RuntimeException re) {
			log.error("findAllGrids GrilleEvaluationDto", re);
			throw re;
		}
	}

	@Override
	public List<EvaluationChercheurDto> findEvaluationsByKeyWords(String keyWord) {
		
		try {
			List<EvaluationChercheur> evaluationsChercheurs= evaluationChercheurDao.findByKeyWords(keyWord);
			List<EvaluationChercheurDto> evaluationsChercheursDtos = new ArrayList<EvaluationChercheurDto>();
			for (EvaluationChercheur evaluationChercheur : evaluationsChercheurs) {
				EvaluationChercheurDto evaluationChercheurDto = new EvaluationChercheurDto();
				mapper.map(evaluationChercheur, evaluationChercheurDto);
				evaluationsChercheursDtos.add(evaluationChercheurDto);
			}
			return evaluationsChercheursDtos;
		} catch (RuntimeException re) {
			log.error("findEvaluationsByKeyWords EvaluationChercheurDto", re);
			throw re;
		}
	}

	@Override
	public EvaluationChercheurDto findEvaluationById(Long idEvaluation) {

		try {
				EvaluationChercheur evaluationChercheur = evaluationChercheurDao.findById(idEvaluation);
				if (evaluationChercheur != null) {
					EvaluationChercheurDto evaluationChercheurDto = new EvaluationChercheurDto();
					mapper.map(evaluationChercheur, evaluationChercheurDto);
					return evaluationChercheurDto;
				}
				return null;

		} catch (RuntimeException re) {
			log.error("findEvaluationById EvaluationChercheurDto failed : ", re);
			throw re;
		}
	}

	@Override
	public List<EvaluationValeurDto> findEvaluationValuesByEvaluationId(
			Long idEvaluation) {
		try {
			List<EvaluationValeur> evaluationValeurs= evaluationValeurDao.findEvaluationValues(idEvaluation);
			List<EvaluationValeurDto> evaluationValeurDtos = new ArrayList<EvaluationValeurDto>();
			for (EvaluationValeur evaluationValeur : evaluationValeurs) {
				EvaluationValeurDto evaluationValeurDto = new EvaluationValeurDto();
				mapper.map(evaluationValeur, evaluationValeurDto);
				evaluationValeurDtos.add(evaluationValeurDto);
			}
			return evaluationValeurDtos;
		} catch (RuntimeException re) {
			log.error("findEvaluationValuesByEvaluationId EvaluationValeurDto", re);
			throw re;
		}
	}

	@Override
	public EvaluationValeurDto findOneEvaluationValue(Long idEvaluationValue) {
		
		try {
			EvaluationValeur evaluationValeur = evaluationValeurDao.findById(idEvaluationValue);
			if (evaluationValeur != null) {
				EvaluationValeurDto evaluationValeurDto = new EvaluationValeurDto();
				mapper.map(evaluationValeur, evaluationValeurDto);
				return evaluationValeurDto;
			}
			return null;

	} catch (RuntimeException re) {
		log.error("findOneEvaluationValue EvaluationValeurDto failed : ", re);
		throw re;
	}
	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public EvaluationChercheurDto saveOneEvaluation(
			EvaluationChercheurDto evaluationChercheurDto) {
		
		try {
					EvaluationChercheur evaluationChercheur = mapper.map(evaluationChercheurDto,EvaluationChercheur.class);
					if (evaluationChercheur.getId() == null){
						evaluationChercheurDao.persist(evaluationChercheur);
					}else{
						evaluationChercheur = evaluationChercheurDao.merge(evaluationChercheur);
					}
					mapper.map(evaluationChercheur , evaluationChercheurDto);
					return evaluationChercheurDto;
		
		} catch (RuntimeException re) {
					log.error("saveOneEvaluation evaluationChercheurDto failed : ", re);
					throw re;
		}
	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public EvaluationValeurDto saveOneEvaluationValue(
			EvaluationValeurDto evaluationValeurDto) {
		
		try {
				EvaluationValeur evaluationValeur = mapper.map(evaluationValeurDto, EvaluationValeur.class);
				if (evaluationValeur.getId() == null){
					evaluationValeurDao.persist(evaluationValeur);
				}else{
					evaluationValeur = evaluationValeurDao.merge(evaluationValeur);
				}
				mapper.map(evaluationValeur , evaluationValeurDto);
				return evaluationValeurDto;

		} catch (RuntimeException re) {
			log.error("saveOneEvaluationValue evaluationValeurDto failed : ", re);
			throw re;
		}
	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void removeOneEvaluationValue(Long idEvaluationValue) {
		evaluationValeurDao.remove(idEvaluationValue);
	}

}
