/**
 * [dz.gov.mesrs.sii.fve.business.service.impl.scolarite.EnseignantFicheVoeuxServiceImpl.java] 
 * @author rlaib on : 12 oct. 2014  16:31:31
 */
package dz.gov.mesrs.sii.fve.business.service.impl.scolarite;

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
import dz.gov.mesrs.sii.commons.data.dao.fve.offreformation.RattachementMcDao;
import dz.gov.mesrs.sii.commons.data.dao.fve.scolarite.EnseignantChargePedagogiqueDao;
import dz.gov.mesrs.sii.commons.data.dao.fve.scolarite.EnseignantFicheServicesDao;
import dz.gov.mesrs.sii.commons.data.dao.fve.scolarite.EnseignantFicheVoeuxDao;
import dz.gov.mesrs.sii.commons.data.dao.fve.scolarite.EnseignantServiceFaitDao;
import dz.gov.mesrs.sii.commons.data.dao.fve.scolarite.EnseignantVoeuLigneDao;
import dz.gov.mesrs.sii.commons.data.model.bpm.SituationEntite;
import dz.gov.mesrs.sii.commons.data.model.bpm.SituationEntiteOccurrence;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.RattachementMc;
import dz.gov.mesrs.sii.commons.data.model.fve.scolarite.EnseignantChargePedagogique;
import dz.gov.mesrs.sii.commons.data.model.fve.scolarite.EnseignantFicheServices;
import dz.gov.mesrs.sii.commons.data.model.fve.scolarite.EnseignantFicheVoeux;
import dz.gov.mesrs.sii.commons.data.model.fve.scolarite.EnseignantVoeuLigne;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.RattachementMcDto;
import dz.gov.mesrs.sii.fve.business.model.dto.scolarite.EnseignantChargePedagogiqueDto;
import dz.gov.mesrs.sii.fve.business.model.dto.scolarite.EnseignantFicheServicesDto;
import dz.gov.mesrs.sii.fve.business.model.dto.scolarite.EnseignantFicheVoeuxDto;
import dz.gov.mesrs.sii.fve.business.model.dto.scolarite.EnseignantVoeuLigneDto;
import dz.gov.mesrs.sii.fve.business.service.scolarite.EnseignantFicheServicesService;
import dz.gov.mesrs.sii.fve.business.util.UtilConstants;

/**
 * @author rlaib  on : 12 oct. 2014  16:31:31
 */
@Service("enseignantFicheServicesService")
public class EnseignantFicheServicesServiceImpl implements
EnseignantFicheServicesService {

	@Autowired
	@Qualifier ("enseignantFicheVoeuxDao")
	private EnseignantFicheVoeuxDao enseignantFicheVoeuxDao;
	
	@Autowired
	@Qualifier ("situationEntiteDao")
	private SituationEntiteDao situationEntiteDao;
	
	@Autowired
	@Qualifier ("situationEntiteOccurrenceDao")
	private SituationEntiteOccurrenceDao  situationEntiteOccurrenceDao;
	
	@Autowired
	@Qualifier ("enseignantFicheServicesDao")
	private EnseignantFicheServicesDao  enseignantFicheServicesDao;
	
	@Autowired
	@Qualifier ("enseignantChargePedagogiqueDao")
	private EnseignantChargePedagogiqueDao  enseignantChargePedagogiqueDao;
	
	@Autowired
	@Qualifier ("enseignantVoeuLigneDao")
	private EnseignantVoeuLigneDao  enseignantVoeuLigneDao;
	
	@Autowired
	@Qualifier ("enseignantServiceFaitDao")
	private EnseignantServiceFaitDao  enseignantServiceFaitDao;
	
	@Autowired
	@Qualifier ("rattachementMcDao")
	private RattachementMcDao  rattachementMcDao;
	
	
	private static final Log log = LogFactory.getLog(EnseignantFicheServicesServiceImpl.class);
	
    @Autowired
	@Qualifier("mapper")
	private DozerBeanMapper mapper;
    
    public EnseignantFicheServicesServiceImpl(){

	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public EnseignantFicheServicesDto insertOrUpdate(
			EnseignantFicheServicesDto enseignantFicheServicesDto) {
		
		EnseignantFicheServices enseignantFicheServices = mapper.map(enseignantFicheServicesDto,
				EnseignantFicheServices.class);
		try {
					if (enseignantFicheServices.getId() == 0){
						enseignantFicheServices = enseignantFicheServicesDao.persist(enseignantFicheServices);
							
					}else{
						enseignantFicheServices = enseignantFicheServicesDao.merge(enseignantFicheServices);
					}
				
					SituationEntite  situationEntiteGeneree = new SituationEntite();
					situationEntiteGeneree = situationEntiteDao.findByCodeSituationByCodeEntite(UtilConstants.ENTITE_FICHE_SERVICES_ENSEIGNANT
									, UtilConstants.SITUTAION_GENEREE_AUTO_CODE);
					
					if(situationEntiteGeneree != null && situationEntiteGeneree.getId() == enseignantFicheServicesDto.getIdSituation())  {
						enseignantFicheServicesDto.setEstGeneree(true);
					}
					else  {
							enseignantFicheServicesDto.setEstSoumise(false);
							SituationEntite  situationEntiteValidee= new SituationEntite();
							situationEntiteValidee = situationEntiteDao.findByCodeSituationByCodeEntite(UtilConstants.ENTITE_FICHE_SERVICES_ENSEIGNANT
										, UtilConstants.SITUTAION_VALIDEE_CODE);
								if(situationEntiteValidee != null && situationEntiteValidee.getId() == enseignantFicheServicesDto.getIdSituation()) 
									enseignantFicheServicesDto.setValidee(true);
								else
									enseignantFicheServicesDto.setValidee(false);
					}
					
					mapper.map(enseignantFicheServices, enseignantFicheServicesDto);
					
					// Sauvegarde historique de la situation de la fiche de services
					SituationEntiteOccurrenceDto situationEntiteOccurrenceDto = new SituationEntiteOccurrenceDto();
					situationEntiteOccurrenceDto.setIdOccurrence(enseignantFicheServicesDto.getFicheVoeuxId());
					situationEntiteOccurrenceDto.setIdSituation(enseignantFicheServicesDto.getIdSituation());
					situationEntiteOccurrenceDto.setDateSituation(new java.util.Date());
					SituationEntiteOccurrence  situationEntiteOccurrence = mapper.map(situationEntiteOccurrenceDto, SituationEntiteOccurrence.class);
					situationEntiteOccurrenceDao.persist(situationEntiteOccurrence);
				
					return enseignantFicheServicesDto;
		
		} catch (RuntimeException re) {
					log.error("insertOrUpdate EnseignantFicheServicesDto failed : ", re);
					throw re;
		}
	}

	/**
	 * [EnseignantFicheServicesService.findFichesServicesByEtabByUserByOfByPeriodeByYear] Method 
	 * @author rlaib  on : 25 oct. 2014  14:44:07
	 * @param idEtab
	 * @param idUser
	 * @param idAnnee
	 * @param idOf
	 * @param idPeriode
	 * @return
	 */
	@Override
	public List<EnseignantFicheServicesDto> findFichesServicesByEtabByUserByPeriodeByYear(
			Integer idEtab, Long idUser, Integer idAnnee, 
			Integer idPeriode) {

				
		try {
			List<EnseignantFicheServices> enseignantServices= enseignantFicheServicesDao.findFichesServicesByEtabByUserByPeriodeByYear(idEtab,idUser,idAnnee, idPeriode);
			List<EnseignantFicheServicesDto> enseignantFicheServicesDtos = new ArrayList<EnseignantFicheServicesDto>();
			for (EnseignantFicheServices fiche : enseignantServices) {
				
				EnseignantFicheServicesDto ficheDto = new EnseignantFicheServicesDto();
				mapper.map(fiche, ficheDto);
				ficheDto.setStyleCssSituation(fiche.getSituation().getSituation().getStyleCss());
				
				SituationEntite  situationEntiteGeneree = new SituationEntite();
				situationEntiteGeneree = situationEntiteDao.findByCodeSituationByCodeEntite(UtilConstants.ENTITE_FICHE_SERVICES_ENSEIGNANT
								, UtilConstants.SITUTAION_GENEREE_AUTO_CODE);
				
				if(situationEntiteGeneree != null && situationEntiteGeneree.getId() == ficheDto.getIdSituation())  {
					ficheDto.setEstGeneree(true);
				}
				else  {
						ficheDto.setEstGeneree(false);
						SituationEntite  situationEntiteValidee= new SituationEntite();
						situationEntiteValidee = situationEntiteDao.findByCodeSituationByCodeEntite(UtilConstants.ENTITE_FICHE_SERVICES_ENSEIGNANT
									, UtilConstants.SITUTAION_VALIDEE_CODE);
							if(situationEntiteValidee != null && situationEntiteValidee.getId() == ficheDto.getIdSituation()) 
									ficheDto.setValidee(true);
							else
								ficheDto.setValidee(false);
				}
				
				EnseignantFicheVoeuxDto  enseignantFicheVoeuxDto = new EnseignantFicheVoeuxDto();
				EnseignantFicheVoeux  enseignantFicheVoeux = new EnseignantFicheVoeux();
				enseignantFicheVoeux = fiche.getEnseignantFicheVoeux();
				mapper.map(enseignantFicheVoeux, enseignantFicheVoeuxDto);
				mapper.map(enseignantFicheVoeuxDto, ficheDto);
				enseignantFicheServicesDtos.add(ficheDto);
			}
			return enseignantFicheServicesDtos;
		} catch (RuntimeException re) {
			log.error("findFichesServicesByEtabByUserByOfByPeriodeByYear EnseignantFicheServicesDto", re);
			throw re;
		}
	}

	/**
	 * [EnseignantFicheServicesService.findFichesServicesByEtabBySituation] Method 
	 * @author rlaib  on : 25 oct. 2014  14:44:07
	 * @param idEtab
	 * @param idSituation
	 * @return
	 */
	@Override
	public List<EnseignantFicheServicesDto> findFichesServicesByEtabBySituation(
			Integer idEtab, Integer idSituation) {
		
		
		try {
			List<EnseignantFicheServices> enseignantServices= enseignantFicheServicesDao.findFichesServicesByEtabBySituation(idEtab, idSituation);
			List<EnseignantFicheServicesDto> enseignantFicheServicesDtos = new ArrayList<EnseignantFicheServicesDto>();
			for (EnseignantFicheServices fiche : enseignantServices) {
				
				EnseignantFicheServicesDto ficheDto = new EnseignantFicheServicesDto();
				mapper.map(fiche, ficheDto);
				ficheDto.setStyleCssSituation(fiche.getSituation().getSituation().getStyleCss());
				SituationEntite  situationEntiteSoumise = new SituationEntite();
				situationEntiteSoumise = situationEntiteDao.findByCodeSituationByCodeEntite(UtilConstants.ENTITE_FICHE_SERVICES_ENSEIGNANT
								, UtilConstants.SITUTAION_SOUMISE_VALIDATION_CODE);
				
				if(situationEntiteSoumise != null && situationEntiteSoumise.getId() == ficheDto.getIdSituation())  {
						ficheDto.setEstSoumise(true);
				}
				else  {
						ficheDto.setEstSoumise(false);
						SituationEntite  situationEntiteValidee= new SituationEntite();
						situationEntiteValidee = situationEntiteDao.findByCodeSituationByCodeEntite(UtilConstants.ENTITE_FICHE_SERVICES_ENSEIGNANT
									, UtilConstants.SITUTAION_VALIDEE_CODE);
							if(situationEntiteValidee != null && situationEntiteValidee.getId() == ficheDto.getIdSituation()) 
									ficheDto.setValidee(true);
							else
								ficheDto.setValidee(false);
				}
				
				enseignantFicheServicesDtos.add(ficheDto);
			}
			return enseignantFicheServicesDtos;
		} catch (RuntimeException re) {
			log.error("findFichesServicesByEtabBySituation EnseignantFicheServicesDto", re);
			throw re;
		}
	}

	/**
	 * [EnseignantFicheServicesService.findChargesPedagogiquesByFicheServicesId] Method 
	 * @author rlaib  on : 26 oct. 2014  10:37:28
	 * @param idFicheService
	 * @return
	 */
	@Override
	public List<EnseignantChargePedagogiqueDto> findChargesPedagogiquesByFicheServicesId(
			Integer idFicheService) {
		try {
				List<EnseignantChargePedagogique> enseignantChargePedagogiques = enseignantChargePedagogiqueDao.findByIdFicheServices(idFicheService);
				List<EnseignantChargePedagogiqueDto> enseignantChargePedagogiqueDtos = new ArrayList<EnseignantChargePedagogiqueDto>();
				
				EnseignantFicheVoeux EnseignantFicheVoeux = enseignantFicheVoeuxDao.findById(idFicheService);
				EnseignantFicheVoeuxDto EnseignantFicheVoeuxDto = new EnseignantFicheVoeuxDto();
				mapper.map(EnseignantFicheVoeux, EnseignantFicheVoeuxDto);
				
				for (EnseignantChargePedagogique enseignantChargePedagogique : enseignantChargePedagogiques) {
				
					EnseignantChargePedagogiqueDto enseignantChargePedagogiqueDto = new EnseignantChargePedagogiqueDto();
				
					enseignantChargePedagogiqueDto.setStyleCssSituation(enseignantChargePedagogique.getSituation().getSituation().getStyleCss());
								
					EnseignantVoeuLigne enseignantVoeuLigne = enseignantVoeuLigneDao.findById(enseignantChargePedagogique.getEnseignantVoeuLigne().getId());
					EnseignantVoeuLigneDto  enseignantVoeuLigneDto = new EnseignantVoeuLigneDto();
					
					RattachementMc rattachementMc = rattachementMcDao.findById(enseignantVoeuLigne.getRattachementMc().getId());
					RattachementMcDto rattachementMcDto = new   RattachementMcDto();
					
					mapper.map(rattachementMc, rattachementMcDto);
					mapper.map(enseignantVoeuLigne, enseignantVoeuLigneDto);
					mapper.map(rattachementMcDto, enseignantVoeuLigneDto);
					mapper.map(enseignantVoeuLigneDto, enseignantChargePedagogiqueDto);
					mapper.map(enseignantChargePedagogique, enseignantChargePedagogiqueDto);
					enseignantChargePedagogiqueDtos.add(enseignantChargePedagogiqueDto);
			}
			return enseignantChargePedagogiqueDtos;
		} catch (RuntimeException re) {
			log.error("findChargesPedagogiquesByFicheServicesId EnseignantChargePedagogiqueDto", re);
			throw re;
		}
	}

	/**
	 * [EnseignantFicheServicesService.validate] Method 
	 * @author rlaib  on : 27 oct. 2014  10:46:52
	 * @param enseignantFicheServicesDto
	 * @return
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public EnseignantFicheServicesDto validate(
			 Integer idFicheServices) {
			try {
			
					EnseignantFicheServicesDto enseignantFicheServicesDto = new EnseignantFicheServicesDto();
					EnseignantFicheVoeux enseignantFicheVoeux = enseignantFicheVoeuxDao.findById(idFicheServices);
					if(enseignantFicheVoeux == null) {
						return null;
					}
					EnseignantFicheServices enseignantFicheServices = enseignantFicheServicesDao.findById(idFicheServices);
					
					if(enseignantFicheServices == null)
						return null;
					
					mapper.map(enseignantFicheServices, enseignantFicheServicesDto);

					SituationEntite  situationEntiteGeneree = new SituationEntite();
					situationEntiteGeneree = situationEntiteDao.findByCodeSituationByCodeEntite(UtilConstants.ENTITE_FICHE_SERVICES_ENSEIGNANT
									, UtilConstants.SITUTAION_GENEREE_AUTO_CODE);
					if(situationEntiteGeneree != null && situationEntiteGeneree.getId() == enseignantFicheServicesDto.getIdSituation())  {
						enseignantFicheServicesDto.setEstGeneree(true);
					}
					else  {
							enseignantFicheServicesDto.setEstSoumise(false);
							SituationEntite  situationEntiteValidee= new SituationEntite();
							situationEntiteValidee = situationEntiteDao.findByCodeSituationByCodeEntite(UtilConstants.ENTITE_FICHE_SERVICES_ENSEIGNANT
										, UtilConstants.SITUTAION_VALIDEE_CODE);
								if(situationEntiteValidee != null && situationEntiteValidee.getId() == enseignantFicheServicesDto.getIdSituation()) 
									enseignantFicheServicesDto.setValidee(true);
								else
									enseignantFicheServicesDto.setValidee(false);
					}
					
					// Validation des charges pedagogique de la fiches de services
					List<EnseignantChargePedagogique>  _enseignantChargePedagogiques = new ArrayList<>(enseignantFicheServices.getEnseignantChargePedagogiques());
//							enseignantChargePedagogiqueDao.findByIdFicheServices(enseignantFicheServicesDto.getFicheVoeuxId());
					if(_enseignantChargePedagogiques != null && _enseignantChargePedagogiques.size()>0) {
						
						SituationEntite  _situationEntiteValidee= new SituationEntite();
						_situationEntiteValidee = situationEntiteDao.findByCodeSituationByCodeEntite(UtilConstants.ENTITE_CHARGE_PEDAGOGIQUE_ENSEIGNANT
									, UtilConstants.SITUTAION_VALIDEE_CODE);
						for (EnseignantChargePedagogique _enseignantChargePedagogique : _enseignantChargePedagogiques) {
							
							_enseignantChargePedagogique.setSituation(_situationEntiteValidee);
							SituationEntiteOccurrence _situationEntiteOccurrence = new SituationEntiteOccurrence();
							_situationEntiteOccurrence.setIdOccurrence(_enseignantChargePedagogique.getId());
							_situationEntiteOccurrence.setSituationEntite(_situationEntiteValidee);
							_situationEntiteOccurrence.setDateSituation(new java.util.Date());
							situationEntiteOccurrenceDao.persist(_situationEntiteOccurrence);
							
						}
//						enseignantFicheServices.setEnseignantChargePedagogiques(new HashSet<>(_enseignantChargePedagogiques));
					}
					
					SituationEntite  situationEntiteValidee= new SituationEntite();
					situationEntiteValidee = situationEntiteDao.findByCodeSituationByCodeEntite(UtilConstants.ENTITE_FICHE_SERVICES_ENSEIGNANT
								, UtilConstants.SITUTAION_VALIDEE_CODE);
					enseignantFicheServices.setSituation(situationEntiteValidee);
					enseignantFicheServices = enseignantFicheServicesDao.merge(enseignantFicheServices);
					mapper.map(enseignantFicheServices, enseignantFicheServicesDto);
					
					// Sauvegarde historique de la situation de la fiche de services
					SituationEntiteOccurrence _situationEntiteOccurrence = new SituationEntiteOccurrence();
					_situationEntiteOccurrence.setIdOccurrence(enseignantFicheServicesDto.getId());
					_situationEntiteOccurrence.setSituationEntite(situationEntiteValidee);
					_situationEntiteOccurrence.setDateSituation(new java.util.Date());
					situationEntiteOccurrenceDao.persist(_situationEntiteOccurrence);
				
					return enseignantFicheServicesDto;
		
		} catch (RuntimeException re) {
					log.error("validate EnseignantFicheServicesDto failed : ", re);
					throw re;
		}
	}
	
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public List<EnseignantChargePedagogiqueDto> saveChargesPedagogiques(List<EnseignantChargePedagogiqueDto> listCharges) {
		
		try {
				
				List<EnseignantChargePedagogiqueDto> result = new ArrayList<EnseignantChargePedagogiqueDto>();
				for (EnseignantChargePedagogiqueDto _enseignantChargePedagogiqueDto : listCharges) {
						
					EnseignantChargePedagogique _enseignantChargePedagogique = mapper.map(_enseignantChargePedagogiqueDto,
							EnseignantChargePedagogique.class);
					enseignantChargePedagogiqueDao.merge(_enseignantChargePedagogique);
					mapper.map(_enseignantChargePedagogique, _enseignantChargePedagogiqueDto);
					result.add(_enseignantChargePedagogiqueDto);
				}
				return result;
				
		} catch (RuntimeException re) {
			log.error("saveChargesPedagogiques EnseignantVoeuDto", re);
			throw re;
		}
	}

	/**
	 * [EnseignantFicheServicesService.findById] Method 
	 * @author rlaib  on : 3 nov. 2014  18:06:40
	 * @param id
	 * @return
	 */
	@Override
	public EnseignantFicheServicesDto findById(Integer id) {

		try {
			EnseignantFicheServices enseignantFicheServices = enseignantFicheServicesDao.findById(id);
			if (enseignantFicheServices != null) {
				EnseignantFicheServicesDto enseignantFicheServicesDto = new EnseignantFicheServicesDto();
					mapper.map(enseignantFicheServices, enseignantFicheServicesDto);
					return enseignantFicheServicesDto;
			}
			return null;

	} catch (RuntimeException re) {
			log.error("findById EnseignantFicheServicesDto failed : ", re);
			throw re;
	}
	}

	
}
