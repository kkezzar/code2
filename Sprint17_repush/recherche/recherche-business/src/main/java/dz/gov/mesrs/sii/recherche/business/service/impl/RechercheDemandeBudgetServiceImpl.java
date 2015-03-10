/**
 * [dz.gov.mesrs.sii.recherche.business.service.impl.RechercheDemandeBudgetServiceImpl.java] 
 * @author rlaib on : 3 févr. 2015  13:01:12
 */
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

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteOccurrenceDto;
import dz.gov.mesrs.sii.commons.data.dao.bpm.SituationEntiteDao;
import dz.gov.mesrs.sii.commons.data.dao.bpm.SituationEntiteOccurrenceDao;
import dz.gov.mesrs.sii.commons.data.dao.recherche.ActiviteBudgetDao;
import dz.gov.mesrs.sii.commons.data.dao.recherche.DemandeBudgetDao;
import dz.gov.mesrs.sii.commons.data.dao.recherche.DemandeCreditDao;
import dz.gov.mesrs.sii.commons.data.dao.recherche.DemandeEquipementDao;
import dz.gov.mesrs.sii.commons.data.dao.recherche.DemandeOperationDao;
import dz.gov.mesrs.sii.commons.data.dao.recherche.ProgrammeDao;
import dz.gov.mesrs.sii.commons.data.dao.recherche.ResultatAttenduDao;
import dz.gov.mesrs.sii.commons.data.model.bpm.SituationEntite;
import dz.gov.mesrs.sii.commons.data.model.bpm.SituationEntiteOccurrence;
import dz.gov.mesrs.sii.commons.data.model.gfc.Article;
import dz.gov.mesrs.sii.commons.data.model.gfc.Chapitre;
import dz.gov.mesrs.sii.commons.data.model.recherche.ActiviteBudget;
import dz.gov.mesrs.sii.commons.data.model.recherche.DemandeBudget;
import dz.gov.mesrs.sii.commons.data.model.recherche.DemandeCredit;
import dz.gov.mesrs.sii.commons.data.model.recherche.DemandeEquipement;
import dz.gov.mesrs.sii.commons.data.model.recherche.DemandeOperation;
import dz.gov.mesrs.sii.commons.data.model.recherche.Programme;
import dz.gov.mesrs.sii.commons.data.model.recherche.ResultatAttendu;
import dz.gov.mesrs.sii.commons.data.util.UtilConstant;
import dz.gov.mesrs.sii.recherche.business.model.dto.ActiviteBudgetDto;
import dz.gov.mesrs.sii.recherche.business.model.dto.DemandeBudgetDto;
import dz.gov.mesrs.sii.recherche.business.model.dto.DemandeCreditDto;
import dz.gov.mesrs.sii.recherche.business.model.dto.DemandeEquipementDto;
import dz.gov.mesrs.sii.recherche.business.model.dto.DemandeOperationDto;
import dz.gov.mesrs.sii.recherche.business.model.dto.ResultatAttenduDto;
import dz.gov.mesrs.sii.recherche.business.service.RechercheDemandeBudgetService;

/**
 * @author rlaib  on : 3 févr. 2015  13:01:12
 */
@Service("rechercheDemandeBudgetService")
public class RechercheDemandeBudgetServiceImpl implements
		RechercheDemandeBudgetService {

	@Autowired
	@Qualifier ("demandeBudgetDao")
	private DemandeBudgetDao demandeBudgetDao;

	@Autowired
	@Qualifier ("activiteBudgetDao")
	private ActiviteBudgetDao activiteBudgetDao;
	
	@Autowired
	@Qualifier ("demandeCreditDao")
	private DemandeCreditDao demandeCreditDao;

	@Autowired
	@Qualifier ("demandeEquipementDao")
	private DemandeEquipementDao demandeEquipementDao;

	@Autowired
	@Qualifier ("demandeOperationDao")
	private DemandeOperationDao demandeOperationDao;

	@Autowired
	@Qualifier ("resultatAttenduDao")
	private ResultatAttenduDao resultatAttenduDao;

	@Autowired
	@Qualifier ("situationEntiteDao")
	private SituationEntiteDao situationEntiteDao;

	@Autowired
	@Qualifier ("programmeDao")
	private ProgrammeDao programmeDao;
	
	@Autowired
	@Qualifier ("situationEntiteOccurrenceDao")
	private SituationEntiteOccurrenceDao  situationEntiteOccurrenceDao;
	
	@Autowired
	@Qualifier("mapper")
	private DozerBeanMapper mapper;
	  
	private static final Log log = LogFactory.getLog(RechercheDemandeBudgetServiceImpl.class);

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public DemandeBudgetDto insertOrUpdate(DemandeBudgetDto demandeBudgetDto , Integer idCompte) {

		DemandeBudget demande = mapper.map(demandeBudgetDto, DemandeBudget.class);
		try {
					SituationEntite _situation = null; 
					if (demande.getId() == null){
						  _situation = situationEntiteDao.findByCodeSituationByCodeEntite(UtilConstant.ENTITE_RCH_DEMANDE_BUDGET,
								UtilConstant.SITUTAION_CREEE_CODE);
						demande.setSituationEntite(_situation);
						demandeBudgetDao.persist(demande);
					}else{
						_situation = situationEntiteDao.findByCodeSituationByCodeEntite(UtilConstant.ENTITE_RCH_DEMANDE_BUDGET, 
								UtilConstant.SITUTAION_ENREGISTREE_CODE);
						demande.setSituationEntite(_situation);
						demande = demandeBudgetDao.merge(demande);
					}
					SituationEntiteOccurrenceDto situationEntiteOccurrenceDto = new SituationEntiteOccurrenceDto();
					situationEntiteOccurrenceDto.setIdOccurrence(demande.getId().intValue());
					situationEntiteOccurrenceDto.setIdSituation(_situation.getId());
					situationEntiteOccurrenceDto.setDateSituation(new java.util.Date());
					situationEntiteOccurrenceDto.setRefCompteId(idCompte);
					SituationEntiteOccurrence situationEntiteOccurrence = mapper.map(situationEntiteOccurrenceDto, SituationEntiteOccurrence.class);
					situationEntiteOccurrenceDao.persist(situationEntiteOccurrence);
					Programme programme  = programmeDao.findById(demandeBudgetDto.getProgrammeId());
					demande.setProgramme(programme);
					mapper.map(demande, demandeBudgetDto);
					return demandeBudgetDto;
		
		} catch (RuntimeException re) {
					log.error("insertOrUpdate DemandeBudgetDto failed : ", re);
					throw re;
		}
	}

	@Override
	public DemandeBudgetDto findById(Long id) {
		
		try {
			DemandeBudget demande = demandeBudgetDao.findById(id);
			if (demande != null) {
				DemandeBudgetDto demandeBudgetDto = new DemandeBudgetDto();
					mapper.map(demande, demandeBudgetDto);
					return demandeBudgetDto;
			}
			return null;

		} catch (RuntimeException re) {
			log.error("findById DemandeBudgetDto failed : ", re);
			throw re;
		}
	}

	@Override
	public List<DemandeBudgetDto> findByKeyWords(String keyWord) {

		try {
			List<DemandeBudget> demandes= demandeBudgetDao.findByKeyWords(keyWord);
			List<DemandeBudgetDto> demandeBudgetDtos = new ArrayList<DemandeBudgetDto>();
			for (DemandeBudget demande: demandes) {
				DemandeBudgetDto demandeBudgetDto = new DemandeBudgetDto();
				mapper.map(demande, demandeBudgetDto);
				demandeBudgetDtos.add(demandeBudgetDto);
			}
			return demandeBudgetDtos;
		} catch (RuntimeException re) {
			log.error("findByKeyWords DemandeBudgetDto", re);
			throw re;
		}
	}

	@Override
	public List<DemandeBudgetDto> findDemandsOfProgram(Long idProgramme) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public Object saveItem(Object itemDto , Integer idCompte) {
		try {
				boolean saved = false;
				Long _idDemande = null;
				if(itemDto.getClass().getName().equals(ActiviteBudgetDto.class.getName())) {
					// Save ActiviteBudget
					ActiviteBudget activiteBudget = mapper.map(itemDto, ActiviteBudget.class);
					_idDemande = activiteBudget.getDemandeBudget().getId();
					if (activiteBudget.getId() == null){
						activiteBudgetDao.persist(activiteBudget);
					}else{
						activiteBudget = activiteBudgetDao.merge(activiteBudget);
					}
					mapper.map(activiteBudget, itemDto);
					saved = true;
				}
				if(itemDto.getClass().getName().equals(DemandeCreditDto.class.getName())) {
					// Save DemandeCredit
					DemandeCredit demandeCredit = mapper.map(itemDto, DemandeCredit.class);
					_idDemande = demandeCredit.getDemandeBudget().getId();
					if (demandeCredit.getId() == null){
						demandeCreditDao.persist(demandeCredit);
					}else{
						demandeCredit = demandeCreditDao.merge(demandeCredit);
					}
					mapper.map(demandeCredit, itemDto);
					saved = true;
				}
				
				if(itemDto.getClass().getName().equals(DemandeEquipementDto.class.getName())) {
					// Save DemandeEquipement
					DemandeEquipement demandeEquipement = mapper.map(itemDto, DemandeEquipement.class);
					_idDemande = demandeEquipement.getDemandeBudget().getId();
					if (demandeEquipement.getId() == null){
						demandeEquipementDao.persist(demandeEquipement);
					}else{
						demandeEquipement = demandeEquipementDao.merge(demandeEquipement);
					}
					mapper.map(demandeEquipement, itemDto);
					saved = true;
				}
				if(itemDto.getClass().getName().equals(DemandeOperationDto.class.getName())) {
					// Save DemandeOperation
					DemandeOperation demandeOperation = mapper.map(itemDto, DemandeOperation.class);
					_idDemande = demandeOperation.getDemandeBudget().getId();
					if (demandeOperation.getId() == null){
						demandeOperationDao.persist(demandeOperation);
					}else{
						demandeOperation = demandeOperationDao.merge(demandeOperation);
					}
					mapper.map(demandeOperation, itemDto);
					saved = true;
				}
				if(itemDto.getClass().getName().equals(ResultatAttenduDto.class.getName())) {
					// Save ResultatAttendu
					ResultatAttendu resultatAttendu = mapper.map(itemDto, ResultatAttendu.class);
					_idDemande = resultatAttendu.getDemandeBudget().getId();
					if (resultatAttendu.getId() == null){
						resultatAttenduDao.persist(resultatAttendu);
					}else{
						resultatAttendu = resultatAttenduDao.merge(resultatAttendu);
					}
					mapper.map(resultatAttendu, itemDto);
					saved = true;
				}
				if(saved) {

					SituationEntite _situation = situationEntiteDao.findByCodeSituationByCodeEntite(UtilConstant.ENTITE_RCH_DEMANDE_BUDGET, 
							UtilConstant.SITUTAION_ENREGISTREE_CODE);
					SituationEntiteOccurrenceDto situationEntiteOccurrenceDto = new SituationEntiteOccurrenceDto();
					situationEntiteOccurrenceDto.setIdOccurrence(_idDemande.intValue());
					situationEntiteOccurrenceDto.setIdSituation(_situation.getId());
					situationEntiteOccurrenceDto.setDateSituation(new java.util.Date());
					situationEntiteOccurrenceDto.setRefCompteId(idCompte);
					SituationEntiteOccurrence situationEntiteOccurrence = mapper.map(situationEntiteOccurrenceDto, SituationEntiteOccurrence.class);
					situationEntiteOccurrenceDao.persist(situationEntiteOccurrence);
					DemandeBudget demandeBudget = demandeBudgetDao.findById(_idDemande);
					demandeBudget.setSituationEntite(_situation);
					demandeBudgetDao.merge(demandeBudget);
					return itemDto;
				}
				else {
					return null;
				}
			
		} catch (RuntimeException re) {
				log.error("saveItem Object failed : ", re);
				throw re;
		}
	}

	@Override
	public List<ActiviteBudgetDto> findDemandActivities(Long demandId) {

		try {
			List<ActiviteBudget> activities = activiteBudgetDao.findDemandActivities(demandId);
			List<ActiviteBudgetDto> activiteBudgetDtos = new ArrayList<ActiviteBudgetDto>();
			for (ActiviteBudget activite: activities) {
				ActiviteBudgetDto activiteBudgetDto = new ActiviteBudgetDto();
				mapper.map(activite, activiteBudgetDto);
				activiteBudgetDtos.add(activiteBudgetDto);
			}
			return activiteBudgetDtos;
		} catch (RuntimeException re) {
			log.error("findDemandActivities ActiviteBudgetDto", re);
			throw re;
		}
	}

	@Override
	public ActiviteBudgetDto findDemandActivityById(Long activityId) {
		try {
			ActiviteBudget activite= activiteBudgetDao.findById(activityId);
			if (activite != null) {
				ActiviteBudgetDto activiteBudgetDto = new ActiviteBudgetDto();
					mapper.map(activite, activiteBudgetDto);
					return activiteBudgetDto;
			}
			return null;

		} catch (RuntimeException re) {
			log.error("findDemandActivityById activiteBudgetDto failed : ", re);
			throw re;
		}
	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void removeDemandActivityById(Long activityId, Long idDemand, Integer idCompte) {

		activiteBudgetDao.remove(activityId);
		SituationEntite _situation = situationEntiteDao.findByCodeSituationByCodeEntite(UtilConstant.ENTITE_RCH_DEMANDE_BUDGET, 
				UtilConstant.SITUTAION_ENREGISTREE_CODE);
		SituationEntiteOccurrenceDto situationEntiteOccurrenceDto = new SituationEntiteOccurrenceDto();
		situationEntiteOccurrenceDto.setIdOccurrence(idDemand.intValue());
		situationEntiteOccurrenceDto.setIdSituation(_situation.getId());
		situationEntiteOccurrenceDto.setDateSituation(new java.util.Date());
		situationEntiteOccurrenceDto.setRefCompteId(idCompte);
		SituationEntiteOccurrence situationEntiteOccurrence = mapper.map(situationEntiteOccurrenceDto, SituationEntiteOccurrence.class);
		situationEntiteOccurrenceDao.persist(situationEntiteOccurrence);
	}

	@Override
	public DemandeCreditDto findDemandCreditById(Long creditId) {
		
		try {
			DemandeCredit credit= demandeCreditDao.findById(creditId);
			if (credit != null) {
				DemandeCreditDto demandeCreditDto = new DemandeCreditDto();
					mapper.map(credit, demandeCreditDto);
					return demandeCreditDto;
			}
			return null;

		} catch (RuntimeException re) {
			log.error("removeDemandActivityById demandeCreditDto failed : ", re);
			throw re;
		}
	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void removeDemandCreditById(Long creditId, Long idDemand, Integer idCompte) {

		demandeCreditDao.remove(creditId);
		SituationEntite _situation = situationEntiteDao.findByCodeSituationByCodeEntite(UtilConstant.ENTITE_RCH_DEMANDE_BUDGET, 
				UtilConstant.SITUTAION_ENREGISTREE_CODE);
		SituationEntiteOccurrenceDto situationEntiteOccurrenceDto = new SituationEntiteOccurrenceDto();
		situationEntiteOccurrenceDto.setIdOccurrence(idDemand.intValue());
		situationEntiteOccurrenceDto.setIdSituation(_situation.getId());
		situationEntiteOccurrenceDto.setDateSituation(new java.util.Date());
		situationEntiteOccurrenceDto.setRefCompteId(idCompte);
		SituationEntiteOccurrence situationEntiteOccurrence = mapper.map(situationEntiteOccurrenceDto, SituationEntiteOccurrence.class);
		situationEntiteOccurrenceDao.persist(situationEntiteOccurrence);
	}

	@Override
	public List<DemandeCreditDto> findDemandCredits(Long demandId) {

		try {
			List<DemandeCredit> credits = demandeCreditDao.findByIdDemand(demandId);
			List<DemandeCreditDto> demandeCreditDtos = new ArrayList<DemandeCreditDto>();
			for (DemandeCredit credit: credits) {
				DemandeCreditDto demandeCreditDto = new DemandeCreditDto();
				mapper.map(credit, demandeCreditDto);
				demandeCreditDtos.add(demandeCreditDto);
			}
			return demandeCreditDtos;
		} catch (RuntimeException re) {
			log.error("findDemandCredits DemandeCreditDto", re);
			throw re;
		}
	}

	@Override
	public DemandeEquipementDto findDemandEquipmentById(Long equipmentId) {
	
		try {
			DemandeEquipement equipement = demandeEquipementDao.findById(equipmentId);
			if (equipement != null) {
					DemandeEquipementDto demandeEquipementDto = new DemandeEquipementDto();
					mapper.map(equipement, demandeEquipementDto);
					return demandeEquipementDto;
			}
			return null;

		} catch (RuntimeException re) {
			log.error("findDemandEquipmentById demandeEquipementDto failed : ", re);
			throw re;
		}
	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void removeDemandEquipmentById(Long equipmentId, Long idDemand, Integer idCompte) {
		
		demandeEquipementDao.remove(equipmentId);
		SituationEntite _situation = situationEntiteDao.findByCodeSituationByCodeEntite(UtilConstant.ENTITE_RCH_DEMANDE_BUDGET, 
				UtilConstant.SITUTAION_ENREGISTREE_CODE);
		SituationEntiteOccurrenceDto situationEntiteOccurrenceDto = new SituationEntiteOccurrenceDto();
		situationEntiteOccurrenceDto.setIdOccurrence(idDemand.intValue());
		situationEntiteOccurrenceDto.setIdSituation(_situation.getId());
		situationEntiteOccurrenceDto.setDateSituation(new java.util.Date());
		situationEntiteOccurrenceDto.setRefCompteId(idCompte);
		SituationEntiteOccurrence situationEntiteOccurrence = mapper.map(situationEntiteOccurrenceDto, SituationEntiteOccurrence.class);
		situationEntiteOccurrenceDao.persist(situationEntiteOccurrence);
		
	}

	@Override
	public List<DemandeEquipementDto> findDemandEquipments(Long demandId) {
		
		try {
			List<DemandeEquipement> equipments = demandeEquipementDao.findByIdDemand(demandId);
			List<DemandeEquipementDto> demandeEquipementDtos = new ArrayList<DemandeEquipementDto>();
			for (DemandeEquipement equipment : equipments) {
				DemandeEquipementDto demandeEquipementDto = new DemandeEquipementDto();
				mapper.map(equipment, demandeEquipementDto);
				demandeEquipementDtos.add(demandeEquipementDto);
			}
			return demandeEquipementDtos;
		} catch (RuntimeException re) {
			log.error("findDemandEquipments DemandeEquipementDto", re);
			throw re;
		}
	}

	@Override
	public List<Chapitre> findChapters() {
		return demandeBudgetDao.findChapters();
	}

	@Override
	public List<Article> findArticlesByChapterId(Integer chapterId) {
		return demandeBudgetDao.findArticlesByChapterId(chapterId);
	}

	@Override
	public DemandeOperationDto findDemandOperationById(Long operationId) {

		try {
			DemandeOperation operation = demandeOperationDao.findById(operationId);
			if (operation != null) {
					DemandeOperationDto demandeOperationDto = new DemandeOperationDto();
					mapper.map(operation, demandeOperationDto);
					return demandeOperationDto;
			}
			return null;

		} catch (RuntimeException re) {
			log.error("findDemandOperationById demandeOperationDto failed : ", re);
			throw re;
		}
	}
	
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void removeDemandOperationById(Long operationId, Long idDemand, Integer idCompte) {
		demandeOperationDao.remove(operationId);	
		SituationEntite _situation = situationEntiteDao.findByCodeSituationByCodeEntite(UtilConstant.ENTITE_RCH_DEMANDE_BUDGET, 
				UtilConstant.SITUTAION_ENREGISTREE_CODE);
		SituationEntiteOccurrenceDto situationEntiteOccurrenceDto = new SituationEntiteOccurrenceDto();
		situationEntiteOccurrenceDto.setIdOccurrence(idDemand.intValue());
		situationEntiteOccurrenceDto.setIdSituation(_situation.getId());
		situationEntiteOccurrenceDto.setDateSituation(new java.util.Date());
		situationEntiteOccurrenceDto.setRefCompteId(idCompte);
		SituationEntiteOccurrence situationEntiteOccurrence = mapper.map(situationEntiteOccurrenceDto, SituationEntiteOccurrence.class);
		situationEntiteOccurrenceDao.persist(situationEntiteOccurrence);
	}

	@Override
	public List<DemandeOperationDto> findDemandOperations(Long demandId) {
		try {
			List<DemandeOperation> operations = demandeOperationDao.findByIdDemand(demandId);
			List<DemandeOperationDto> demandeOperationDtos = new ArrayList<DemandeOperationDto>();
			for (DemandeOperation operation : operations) {
				DemandeOperationDto demandeOperationDto = new DemandeOperationDto();
				mapper.map(operation, demandeOperationDto);
				demandeOperationDtos.add(demandeOperationDto);
			}
			return demandeOperationDtos;
		} catch (RuntimeException re) {
			log.error("findDemandOperations DemandeOperationDto", re);
			throw re;
		}
	}

	@Override
	public ResultatAttenduDto findDemandExpectedResultById(Long resultatId) {
		try {
			ResultatAttendu resultat = resultatAttenduDao.findById(resultatId);
			if (resultat != null) {
					ResultatAttenduDto resultatAttenduDto = new ResultatAttenduDto();
					mapper.map(resultat, resultatAttenduDto);
					return resultatAttenduDto;
			}
			return null;

		} catch (RuntimeException re) {
			log.error("findDemandResultatAttenduById resultatAttenduDto failed : ", re);
			throw re;
		}
	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void removeDemandExpectedResultById(Long resultatId, Long idDemand, Integer idCompte) {

		resultatAttenduDao.remove(resultatId);		
		SituationEntite _situation = situationEntiteDao.findByCodeSituationByCodeEntite(UtilConstant.ENTITE_RCH_DEMANDE_BUDGET, 
				UtilConstant.SITUTAION_ENREGISTREE_CODE);
		SituationEntiteOccurrenceDto situationEntiteOccurrenceDto = new SituationEntiteOccurrenceDto();
		situationEntiteOccurrenceDto.setIdOccurrence(idDemand.intValue());
		situationEntiteOccurrenceDto.setIdSituation(_situation.getId());
		situationEntiteOccurrenceDto.setDateSituation(new java.util.Date());
		situationEntiteOccurrenceDto.setRefCompteId(idCompte);
		SituationEntiteOccurrence situationEntiteOccurrence = mapper.map(situationEntiteOccurrenceDto, SituationEntiteOccurrence.class);
		situationEntiteOccurrenceDao.persist(situationEntiteOccurrence);
	}

	@Override
	public List<ResultatAttenduDto> findDemandExpectedResults(Long demandId) {
		try {
			List<ResultatAttendu> resultats= resultatAttenduDao.findByIdDemand(demandId);
			List<ResultatAttenduDto> resultatAttenduDtos = new ArrayList<ResultatAttenduDto>();
			for (ResultatAttendu resultat : resultats) {
				ResultatAttenduDto resultatAttenduDto = new ResultatAttenduDto();
				mapper.map(resultat, resultatAttenduDto);
				resultatAttenduDtos.add(resultatAttenduDto);
			}
			return resultatAttenduDtos;
		} catch (RuntimeException re) {
			log.error("findDemandResultatAttendus ResultatAttenduDto", re);
			throw re;
		}
	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public DemandeBudgetDto submitDemand(DemandeBudgetDto demandeBudgetDto , Integer idCompte) {
		
		DemandeBudget demande = mapper.map(demandeBudgetDto, DemandeBudget.class);
		try {
					SituationEntite _situation = situationEntiteDao.findByCodeSituationByCodeEntite(UtilConstant.ENTITE_RCH_DEMANDE_BUDGET,
								UtilConstant.SITUTAION_SOUMISE_VALIDATION_CODE);
					demande.setSituationEntite(_situation);
					demande = demandeBudgetDao.merge(demande);
					SituationEntiteOccurrenceDto situationEntiteOccurrenceDto = new SituationEntiteOccurrenceDto();
					situationEntiteOccurrenceDto.setIdOccurrence(demandeBudgetDto.getId().intValue());
					situationEntiteOccurrenceDto.setIdSituation(_situation.getId());
					situationEntiteOccurrenceDto.setDateSituation(new java.util.Date());
					situationEntiteOccurrenceDto.setRefCompteId(idCompte);
					SituationEntiteOccurrence situationEntiteOccurrence = mapper.map(situationEntiteOccurrenceDto, SituationEntiteOccurrence.class);
					situationEntiteOccurrenceDao.persist(situationEntiteOccurrence);
					mapper.map(demande, demandeBudgetDto);
					return demandeBudgetDto;
		
		} catch (RuntimeException re) {
					log.error("submitDemand DemandeBudgetDto failed : ", re);
					throw re;
		}
	}
	
	@Override
	public List<DemandeBudgetDto> findDemandsToValidate(String roleCode) {

		try {
			List<DemandeBudget> demandes= demandeBudgetDao.findDemandsToValidate(roleCode);
			List<DemandeBudgetDto> demandeBudgetDtos = new ArrayList<DemandeBudgetDto>();
			for (DemandeBudget demande: demandes) {
				DemandeBudgetDto demandeBudgetDto = new DemandeBudgetDto();
				mapper.map(demande, demandeBudgetDto);
				demandeBudgetDtos.add(demandeBudgetDto);
			}
			return demandeBudgetDtos;
		} catch (RuntimeException re) {
			log.error("findDemandsToValidate DemandeBudgetDto", re);
			throw re;
		}
	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public DemandeBudgetDto validateDemand(DemandeBudgetDto demandeBudgetDto,
			Integer idCompte) {
		DemandeBudget demande = mapper.map(demandeBudgetDto, DemandeBudget.class);
		try {
					SituationEntite _situation = situationEntiteDao.findByCodeSituationByCodeEntite(UtilConstant.ENTITE_RCH_DEMANDE_BUDGET,
								UtilConstant.SITUTAION_VALIDEE_CODE);
					demande.setSituationEntite(_situation);
					demande = demandeBudgetDao.merge(demande);
					SituationEntiteOccurrenceDto situationEntiteOccurrenceDto = new SituationEntiteOccurrenceDto();
					situationEntiteOccurrenceDto.setIdOccurrence(demandeBudgetDto.getId().intValue());
					situationEntiteOccurrenceDto.setIdSituation(_situation.getId());
					situationEntiteOccurrenceDto.setDateSituation(new java.util.Date());
					situationEntiteOccurrenceDto.setRefCompteId(idCompte);
					SituationEntiteOccurrence situationEntiteOccurrence = mapper.map(situationEntiteOccurrenceDto, SituationEntiteOccurrence.class);
					situationEntiteOccurrenceDao.persist(situationEntiteOccurrence);
					mapper.map(demande, demandeBudgetDto);
					return demandeBudgetDto;
		
		} catch (RuntimeException re) {
					log.error("validateDemand DemandeBudgetDto failed : ", re);
					throw re;
		}
	}

	
}
