/**
 * [dz.gov.mesrs.sii.fve.business.service.impl.scolarite.EnseignantFicheVoeuxServiceImpl.java] 
 * @author rlaib on : 12 oct. 2014  16:31:31
 */
package dz.gov.mesrs.sii.fve.business.service.impl.scolarite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

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
import dz.gov.mesrs.sii.commons.data.dao.fve.cursus.AffectationGroupePedagogiqueDao;
import dz.gov.mesrs.sii.commons.data.dao.fve.cursus.AssociationGroupePedagogiqueDao;
import dz.gov.mesrs.sii.commons.data.dao.fve.offreformation.AtomePedagogiqueDao;
import dz.gov.mesrs.sii.commons.data.dao.fve.offreformation.MatiereConstitutiveDao;
import dz.gov.mesrs.sii.commons.data.dao.fve.offreformation.PeriodeDao;
import dz.gov.mesrs.sii.commons.data.dao.fve.offreformation.RattachementMcDao;
import dz.gov.mesrs.sii.commons.data.dao.fve.scolarite.EnseignantChargePedagogiqueDao;
import dz.gov.mesrs.sii.commons.data.dao.fve.scolarite.EnseignantFicheServicesDao;
import dz.gov.mesrs.sii.commons.data.dao.fve.scolarite.EnseignantFicheVoeuxDao;
import dz.gov.mesrs.sii.commons.data.dao.fve.scolarite.EnseignantVoeuDao;
import dz.gov.mesrs.sii.commons.data.dao.fve.scolarite.EnseignantVoeuLigneDao;
import dz.gov.mesrs.sii.commons.data.dao.referentiel.NomenclatureDao;
import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefIndividuDao;
import dz.gov.mesrs.sii.commons.data.model.bpm.SituationEntite;
import dz.gov.mesrs.sii.commons.data.model.bpm.SituationEntiteOccurrence;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.AffectationGroupePedagogique;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.AssociationGroupePedagogique;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.Periode;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.RattachementMc;
import dz.gov.mesrs.sii.commons.data.model.fve.scolarite.EnseignantChargePedagogique;
import dz.gov.mesrs.sii.commons.data.model.fve.scolarite.EnseignantFicheServices;
import dz.gov.mesrs.sii.commons.data.model.fve.scolarite.EnseignantFicheVoeux;
import dz.gov.mesrs.sii.commons.data.model.fve.scolarite.EnseignantVoeu;
import dz.gov.mesrs.sii.commons.data.model.fve.scolarite.EnseignantVoeuLigne;
import dz.gov.mesrs.sii.commons.data.model.grh.DossierEmploye;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.AssociationGroupePedagogiqueDto;
import dz.gov.mesrs.sii.fve.business.model.dto.scolarite.DossierEmployeDto;
import dz.gov.mesrs.sii.fve.business.model.dto.scolarite.EnseignantChargePedagogiqueDto;
import dz.gov.mesrs.sii.fve.business.model.dto.scolarite.EnseignantFicheServicesDto;
import dz.gov.mesrs.sii.fve.business.model.dto.scolarite.EnseignantFicheVoeuxDto;
import dz.gov.mesrs.sii.fve.business.model.dto.scolarite.EnseignantVoeuDto;
import dz.gov.mesrs.sii.fve.business.model.dto.scolarite.EnseignantVoeuLigneDto;
import dz.gov.mesrs.sii.fve.business.service.scolarite.EnseignantFicheVoeuxService;
import dz.gov.mesrs.sii.fve.business.util.UtilConstants;

/**
 * @author rlaib  on : 12 oct. 2014  16:31:31
 */
@Service("enseignantFicheVoeuxService")
public class EnseignantFicheVoeuxServiceImpl implements
		EnseignantFicheVoeuxService {

	@Autowired
	@Qualifier ("enseignantFicheVoeuxDao")
	private EnseignantFicheVoeuxDao enseignantFicheVoeuxDao;
	@Autowired
	@Qualifier ("enseignantVoeuDao")
	private EnseignantVoeuDao enseignantVoeuDao;
	@Autowired
	@Qualifier ("enseignantVoeuLigneDao")
	private EnseignantVoeuLigneDao enseignantVoeuLigneDao;
	@Autowired
	@Qualifier ("situationEntiteDao")
	private SituationEntiteDao situationEntiteDao;
	@Autowired
	@Qualifier ("rattachementMcDao")
	private RattachementMcDao rattachementMcDao;
	@Autowired
	@Qualifier ("matiereConstitutiveDao")
	private MatiereConstitutiveDao  matiereConstitutiveDao;
	@Autowired
	@Qualifier ("situationEntiteOccurrenceDao")
	private SituationEntiteOccurrenceDao  situationEntiteOccurrenceDao;
	@Autowired
	@Qualifier ("enseignantFicheServicesDao")
	private EnseignantFicheServicesDao  enseignantFicheServicesDao;
	@Autowired
	@Qualifier ("associationGroupePedagogiqueDao")
	private AssociationGroupePedagogiqueDao  associationGroupePedagogiqueDao;
 
	@Autowired
	@Qualifier ("enseignantChargePedagogiqueDao")
	private EnseignantChargePedagogiqueDao  enseignantChargePedagogiqueDao;
	
	@Autowired
	@Qualifier ("atomePedagogiqueDao")
	private AtomePedagogiqueDao  atomePedagogiqueDao;
	
	@Autowired
	@Qualifier ("affectationGroupePedagogiqueDao")
	private AffectationGroupePedagogiqueDao  affectationGroupePedagogiqueDao;
	
	@Autowired
	@Qualifier ("refIndividuDaoImpl")
	private RefIndividuDao  refIndividuDaoImpl;
	
	@Autowired
	@Qualifier ("periodeDao")
	private PeriodeDao  periodeDao;
	
	@Autowired
	@Qualifier ("nomenclatureDaoImpl")
	private NomenclatureDao  nomenclatureDaoImpl ;
	
	private static final Log log = LogFactory.getLog(EnseignantFicheVoeuxServiceImpl.class);
	
    @Autowired
	@Qualifier("mapper")
	private DozerBeanMapper mapper;
    
    public EnseignantFicheVoeuxServiceImpl(){

	}

    
	/**
	 * [EnseignantFicheVoeuxServiceImpl.insertOrUpdate] Method 
	 * @author rlaib  on : 12 oct. 2014  16:31:31
	 * @param enseignantFicheVoeuxDto
	 * @return
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public EnseignantFicheVoeuxDto insertOrUpdate(
			EnseignantFicheVoeuxDto enseignantFicheVoeuxDto) {
		
		EnseignantFicheVoeux enseignantFicheVoeux = mapper.map(enseignantFicheVoeuxDto,
				EnseignantFicheVoeux.class);
		try {
					enseignantFicheVoeuxDao.flushAndClear();	
					if (enseignantFicheVoeux.getId() == 0){
						enseignantFicheVoeux = enseignantFicheVoeuxDao.persist(enseignantFicheVoeux);
							
					}else{
							enseignantFicheVoeux = enseignantFicheVoeuxDao.merge(enseignantFicheVoeux);
					}
				
					SituationEntite  situationEntiteSoumise = new SituationEntite();
					situationEntiteSoumise = situationEntiteDao.findByCodeSituationByCodeEntite(UtilConstants.ENTITE_FICHE_VOEUX_ENSEIGNANT
									, UtilConstants.SITUTAION_SOUMISE_VALIDATION_CODE);
					
					if(situationEntiteSoumise != null && situationEntiteSoumise.getId() == enseignantFicheVoeuxDto.getIdSituation())  {
						enseignantFicheVoeuxDto.setEstSoumise(true);
					}
					else  {
						enseignantFicheVoeuxDto.setEstSoumise(false);
							SituationEntite  situationEntiteValidee= new SituationEntite();
							situationEntiteValidee = situationEntiteDao.findByCodeSituationByCodeEntite(UtilConstants.ENTITE_FICHE_VOEUX_ENSEIGNANT
										, UtilConstants.SITUTAION_VALIDEE_CODE);
								if(situationEntiteValidee != null && situationEntiteValidee.getId() == enseignantFicheVoeuxDto.getIdSituation()) 
									enseignantFicheVoeuxDto.setEstValidee(true);
								else
									enseignantFicheVoeuxDto.setEstValidee(false);
					}
					mapper.map(enseignantFicheVoeux, enseignantFicheVoeuxDto);
					// Sauvegarde historique de la situation de la fiche de voeux
					SituationEntiteOccurrenceDto situationEntiteOccurrenceDto = new SituationEntiteOccurrenceDto();
					situationEntiteOccurrenceDto.setIdOccurrence(enseignantFicheVoeuxDto.getId());
					situationEntiteOccurrenceDto.setIdSituation(enseignantFicheVoeuxDto.getIdSituation());
					situationEntiteOccurrenceDto.setDateSituation(new java.util.Date());
					SituationEntiteOccurrence  situationEntiteOccurrence = mapper.map(situationEntiteOccurrenceDto, SituationEntiteOccurrence.class);
					situationEntiteOccurrenceDao.persist(situationEntiteOccurrence);
					
				
					return enseignantFicheVoeuxDto;
		
		} catch (RuntimeException re) {
					log.error("insertOrUpdate EnseignantFicheVoeuxDto failed : ", re);
					throw re;
		}
	}

	/**
	 * [EnseignantFicheVoeuxService.insertOrUpdate] Method 
	 * @author rlaib  on : 25 oct. 2014  08:41:30
	 * @param enseignantFicheServicesDto
	 * @return
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public EnseignantFicheServicesDto generate(
			EnseignantFicheServicesDto enseignantFicheServicesDto) {
		
		try {
				// Generation de la fiche de service correspondante
				EnseignantFicheServices enseignantFicheServices = new EnseignantFicheServices();
				enseignantFicheServices = mapper.map(enseignantFicheServicesDto,EnseignantFicheServices.class);
				EnseignantFicheVoeux  enseignantFicheVoeux = enseignantFicheVoeuxDao.findById(enseignantFicheServicesDto.getFicheVoeuxId());
				EnseignantFicheVoeuxDto enseignantFicheVoeuxDto = new EnseignantFicheVoeuxDto();
				mapper.map(enseignantFicheVoeux, enseignantFicheVoeuxDto);
				enseignantFicheServices.setId(enseignantFicheVoeux.getId());
				enseignantFicheServicesDao.persist(enseignantFicheServices);
				
				List<EnseignantChargePedagogique> enseignantChargePedagogiques = new ArrayList<EnseignantChargePedagogique>();
				List<EnseignantChargePedagogiqueDto> enseignantChargePedagogiqueDtos = new ArrayList<EnseignantChargePedagogiqueDto>();
	
				if(enseignantFicheServices != null) {
					 
					// Generation des charges d'enseignement equivalentes aux lignes de voeux de la fiche
					List<EnseignantVoeuLigne> _lignesValidees = enseignantVoeuLigneDao.findByIdFiche(enseignantFicheVoeux.getId());
					if(_lignesValidees != null) {
						
						SituationEntite _SituationEntiteChargePedagogique = situationEntiteDao.findByCodeSituationByCodeEntite(UtilConstants.ENTITE_CHARGE_PEDAGOGIQUE_ENSEIGNANT
								, UtilConstants.SITUTAION_GENEREE_AUTO_CODE);
						
						for (EnseignantVoeuLigne _enseignantVoeuLigne : _lignesValidees) {
							
							EnseignantVoeuLigneDto _enseignantVoeuLigneDto =  new EnseignantVoeuLigneDto();
							mapper.map(_enseignantVoeuLigne, _enseignantVoeuLigneDto);
							
							if(_enseignantVoeuLigneDto.getEstValidee()) {
								
								// recupere l'id periode correspondant au refCodePeriode
								Periode _periode = periodeDao.findByLevelIdByRefPeriodeCode(_enseignantVoeuLigneDto.getIdNiveau(), enseignantFicheVoeuxDto.getCodePeriode());
					
										List<AssociationGroupePedagogique> _groupesPedagogiquesMcAp = associationGroupePedagogiqueDao.findAdvanced(_enseignantVoeuLigneDto.getIdRattachementMc()
												,_enseignantVoeuLigneDto.getIdAp()
												, _enseignantVoeuLigneDto.getIdOffreFormation()
												, _periode.getId());
										
										if(_groupesPedagogiquesMcAp != null && _groupesPedagogiquesMcAp.size()>0) {
											// Creation des charges pedagogiques
											for (AssociationGroupePedagogique _associationGroupePedagogique : _groupesPedagogiquesMcAp) {
													
													AssociationGroupePedagogiqueDto _associationGroupePedagogiqueDto = new AssociationGroupePedagogiqueDto();
													mapper.map(_associationGroupePedagogique, _associationGroupePedagogiqueDto);
													
													EnseignantChargePedagogiqueDto _enseignantChargePedagogiqueDto = new EnseignantChargePedagogiqueDto();
													EnseignantChargePedagogique _enseignantChargePedagogique = new EnseignantChargePedagogique();
													_enseignantChargePedagogiqueDto.setFicheServiceId(enseignantFicheServices.getId());
													_enseignantChargePedagogiqueDto.setVoeuLigneId(_enseignantVoeuLigne.getId());
													_enseignantChargePedagogiqueDto.setGroupePedagogiqueId(_associationGroupePedagogiqueDto.getGroupePedagogiqueId());
													_enseignantChargePedagogiqueDto.setVolumeHoraireAp(_associationGroupePedagogiqueDto.getApVolumeHoraire());
													_enseignantChargePedagogiqueDto.setIdSituation(_SituationEntiteChargePedagogique.getId());
													
													// Calcul de l effectif groupe
													List<AffectationGroupePedagogique> _effectifGroup = new ArrayList<AffectationGroupePedagogique>();
													_effectifGroup = affectationGroupePedagogiqueDao.findByGroupePedagogiqueId(_associationGroupePedagogiqueDto.getGroupePedagogiqueId());
													_enseignantChargePedagogiqueDto.setEffectifGroupeAp(_effectifGroup.size());
													_enseignantChargePedagogiqueDto.setFicheServiceId(enseignantFicheServices.getId());
													
													_enseignantChargePedagogique = mapper.map(_enseignantChargePedagogiqueDto,
															EnseignantChargePedagogique.class);
													enseignantChargePedagogiques.add(_enseignantChargePedagogique);
													enseignantChargePedagogiqueDtos.add(_enseignantChargePedagogiqueDto);
												
											}
										}
							}
						}
						enseignantFicheServices.setEnseignantChargePedagogiques(new HashSet<>(enseignantChargePedagogiques));
						
					}
				}
				
				mapper.map(enseignantFicheVoeux, enseignantFicheServicesDto);
				
				// Sauvegarde historique de la situation de la fiche de services
//				SituationEntiteOccurrenceDto situationEntiteOccurrenceDto = new SituationEntiteOccurrenceDto();
//				situationEntiteOccurrenceDto.setIdOccurrence(enseignantFicheServicesDto.getId());
//				situationEntiteOccurrenceDto.setIdSituation(enseignantFicheServicesDto.getIdSituation());
//				situationEntiteOccurrenceDto.setDateSituation(new java.util.Date());
//				SituationEntiteOccurrence  situationEntiteOccurrence = mapper.map(situationEntiteOccurrenceDto, SituationEntiteOccurrence.class);
//				situationEntiteOccurrenceDao.persist(situationEntiteOccurrence);
				
				return enseignantFicheServicesDto;
			
		} catch (RuntimeException re) {
					log.error("generate EnseignantFicheServicesDto failed : ", re);
					throw re;
		}
		
	}

	/**
	 * [EnseignantFicheVoeuxServiceImpl.remove] Method 
	 * @author rlaib  on : 12 oct. 2014  16:31:31
	 * @param enseignantFicheVoeuxDto
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = 
	Propagation.REQUIRED)
	public void remove(Integer id , String entityName) {

		try {
				if(entityName != null && entityName.equals(EnseignantFicheVoeuxDto.class.getName())) {
					//enseignantFicheVoeuxDao.remove(id);
				}
				
				if(entityName != null && entityName.equals(EnseignantVoeuDto.class.getName())) {
					enseignantVoeuDao.remove(id);
				}
				
				if(entityName != null && entityName.equals(EnseignantVoeuLigneDto.class.getName())) {
					enseignantVoeuLigneDao.remove(id);
				}
				
			} catch (RuntimeException re) {
				log.error("remove " + entityName + " failed : ", re);
				throw re;
			}

	}

	/**
	 * [EnseignantFicheVoeuxServiceImpl.findById] Method 
	 * @author rlaib  on : 12 oct. 2014  16:31:31
	 * @param id
	 * @return
	 */
	@Override
	public EnseignantFicheVoeuxDto findById(int id) {
		
			try {
					EnseignantFicheVoeux enseignantFicheVoeux = enseignantFicheVoeuxDao.findById(id);
					if (enseignantFicheVoeux != null) {
							EnseignantFicheVoeuxDto ficheDto = new EnseignantFicheVoeuxDto();
							mapper.map(enseignantFicheVoeux, ficheDto);
							return ficheDto;
					}
					return null;
		
			} catch (RuntimeException re) {
					log.error("findById EnseignantFicheVoeuxDto failed : ", re);
					throw re;
			}
	}

	/**
	 * [EnseignantFicheVoeuxServiceImpl.findByEnseignantId] Method 
	 * @author rlaib  on : 12 oct. 2014  16:31:31
	 * @param idEnseignant
	 * @return
	 */
	@Override
	public List<EnseignantFicheVoeuxDto> findByEnseignantId(int idEnseignant, int idAnnee) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * [EnseignantFicheVoeuxService.findAdvanced] Method 
	 * @author rlaib  on : 12 oct. 2014  17:06:22
	 * @param enseignantFicheVoeuxDto
	 * @return
	 */
	@Override
	public List<EnseignantFicheVoeuxDto> findAdvanced(
			EnseignantFicheVoeuxDto enseignantFicheVoeuxDto) {
		
		try {
			EnseignantFicheVoeux enseignantFicheVoeux = mapper.map(enseignantFicheVoeuxDto,EnseignantFicheVoeux.class);
			List<EnseignantFicheVoeux> enseignantFiches= enseignantFicheVoeuxDao.findAdvanced(enseignantFicheVoeux);
			List<EnseignantFicheVoeuxDto> enseignantFicheVoeuxDtos = new ArrayList<EnseignantFicheVoeuxDto>();
			for (EnseignantFicheVoeux fiche : enseignantFiches) {

				EnseignantFicheVoeuxDto ficheDto = new EnseignantFicheVoeuxDto();
				mapper.map(fiche, ficheDto);
				enseignantFicheVoeuxDtos.add(ficheDto);
			}
			return enseignantFicheVoeuxDtos;
		} catch (RuntimeException re) {
			log.error("findAdvanced EnseignantFicheVoeuxDto", re);
			throw re;
		}
	
	}
	
	@Override
	public  List<EnseignantFicheVoeuxDto> findAll() {
		
		try {
			List<EnseignantFicheVoeux> enseignantFiches= enseignantFicheVoeuxDao.findAll();
			List<EnseignantFicheVoeuxDto> enseignantFicheVoeuxDtos = new ArrayList<EnseignantFicheVoeuxDto>();
			for (EnseignantFicheVoeux fiche : enseignantFiches) {

				EnseignantFicheVoeuxDto ficheDto = new EnseignantFicheVoeuxDto();
				mapper.map(fiche, ficheDto);
				enseignantFicheVoeuxDtos.add(ficheDto);
			}
			return enseignantFicheVoeuxDtos;
		} catch (RuntimeException re) {
			log.error("findAll EnseignantFicheVoeuxDto", re);
			throw re;
		}
	}
	
	@Override
	public  List<EnseignantFicheVoeuxDto> findFichesByEtabByUserByOfByPeriodeByYear(Integer idEtab, Long idUser,Integer idAnnee, Integer idNcPeriode, boolean toSearch) {
		
		try {
			List<EnseignantFicheVoeux> enseignantFiches= enseignantFicheVoeuxDao.findFichesByEtabByUserByOfByPeriodeByYear(idEtab,idUser,idAnnee, idNcPeriode, toSearch);
			List<EnseignantFicheVoeuxDto> enseignantFicheVoeuxDtos = new ArrayList<EnseignantFicheVoeuxDto>();
			for (EnseignantFicheVoeux fiche : enseignantFiches) {
				
				EnseignantFicheVoeuxDto ficheDto = new EnseignantFicheVoeuxDto();
				mapper.map(fiche, ficheDto);
				ficheDto.setStyleCssSituation(fiche.getSituation().getSituation().getStyleCss());
				SituationEntite  situationEntiteSoumise = new SituationEntite();
				situationEntiteSoumise = situationEntiteDao.findByCodeSituationByCodeEntite(UtilConstants.ENTITE_FICHE_VOEUX_ENSEIGNANT
								, UtilConstants.SITUTAION_SOUMISE_VALIDATION_CODE);
				
				if(situationEntiteSoumise != null && situationEntiteSoumise.getId() == ficheDto.getIdSituation())  {
						ficheDto.setEstSoumise(true);
				}
				else  {
						ficheDto.setEstSoumise(false);
						SituationEntite  situationEntiteValidee= new SituationEntite();
						situationEntiteValidee = situationEntiteDao.findByCodeSituationByCodeEntite(UtilConstants.ENTITE_FICHE_VOEUX_ENSEIGNANT
									, UtilConstants.SITUTAION_VALIDEE_CODE);
							if(situationEntiteValidee != null && situationEntiteValidee.getId() == ficheDto.getIdSituation()) 
									ficheDto.setEstValidee(true);
							else
								ficheDto.setEstValidee(false);
				}
				
				enseignantFicheVoeuxDtos.add(ficheDto);
			}
			return enseignantFicheVoeuxDtos;
		} catch (RuntimeException re) {
			log.error("findFichesByEtabByUserByOfByPeriodeByYear EnseignantFicheVoeuxDto", re);
			throw re;
		}
	}


	/**
	 * [EnseignantFicheVoeuxService.findVoeuxByIdFiche] Method 
	 * @author rlaib  on : 14 oct. 2014  14:09:46
	 * @param idFiche
	 * @return
	 */
	@Override
	public List<EnseignantVoeuDto> findVoeuxByIdFiche(int idFiche) {
		try {
			List<EnseignantVoeu> enseignantVoeux= enseignantVoeuDao.findByIdFiche(idFiche);
			List<EnseignantVoeuDto> enseignantVoeuxDtos = new ArrayList<EnseignantVoeuDto>();
			Integer order = 1;
			for (EnseignantVoeu enseignantVoeu : enseignantVoeux) {

				EnseignantVoeuDto enseignantVoeuDto = new EnseignantVoeuDto();
				mapper.map(enseignantVoeu, enseignantVoeuDto);
				enseignantVoeuDto.setOrder(order);
				order = order +1;
				enseignantVoeuxDtos.add(enseignantVoeuDto);
			}
			return enseignantVoeuxDtos;
		} catch (RuntimeException re) {
			log.error("findVoeuxByIdFiche EnseignantVoeuDto", re);
			throw re;
		}
	}


	/**
	 * [EnseignantFicheVoeuxService.saveVoeu] Method 
	 * @author rlaib  on : 14 oct. 2014  14:19:38
	 * @param enseignantVoeuDto
	 * @return
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public EnseignantVoeuDto insertOrUpdate(EnseignantVoeuDto enseignantVoeuDto) {
		
		EnseignantVoeu enseignantVoeu = mapper.map(enseignantVoeuDto,
				EnseignantVoeu.class);
		try {
					if (enseignantVoeu.getId() == 0){
						enseignantVoeuDao.persist(enseignantVoeu);
					}else{
						enseignantVoeu = enseignantVoeuDao.merge(enseignantVoeu);
					}
					mapper.map(enseignantVoeu, enseignantVoeuDto);
					return enseignantVoeuDto;
		
		} catch (RuntimeException re) {
					log.error("insertOrUpdate enseignantVoeuDto failed : ", re);
					throw re;
		}
	}
	
	/**
	 * [EnseignantFicheVoeuxServiceImpl.insertOrUpdate] Method 
	 * @author rlaib  on : 20 oct. 2014  12:37:21
	 * @param enseignantVoeuLigneDto
	 * @return
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public EnseignantVoeuLigneDto insertOrUpdate(EnseignantVoeuLigneDto enseignantVoeuLigneDto) {
		
		EnseignantVoeuLigne enseignantVoeuLigne = mapper.map(enseignantVoeuLigneDto,
				EnseignantVoeuLigne.class);
		try {
			if (enseignantVoeuLigne.getId() == 0){
				enseignantVoeuLigneDao
				.persist(enseignantVoeuLigne);
			}else{
				enseignantVoeuLigne = enseignantVoeuLigneDao.merge(enseignantVoeuLigne);
			}
			mapper.map(enseignantVoeuLigne, enseignantVoeuLigneDto);
			return enseignantVoeuLigneDto;
			
		} catch (RuntimeException re) {
			log.error("insertOrUpdate enseignantVoeuLigneDto failed : ", re);
			throw re;
		}
	}


	/**
	 * [EnseignantFicheVoeuxService.findLigneByIdVoeu] Method 
	 * @author rlaib  on : 15 oct. 2014  14:20:19
	 * @param idVoeu
	 * @return
	 */
	@Override
	public List<EnseignantVoeuLigneDto> findLignesByIdVoeu(int idVoeu) {
		try {
			List<EnseignantVoeuLigne> enseignantVoeuLignes= enseignantVoeuLigneDao.findByIdVoeu(idVoeu);
			List<EnseignantVoeuLigneDto> enseignantVoeuLigneDtos = new ArrayList<EnseignantVoeuLigneDto>();
			Integer order = 1;
			for (EnseignantVoeuLigne enseignantVoeuLigne : enseignantVoeuLignes) {

				EnseignantVoeuLigneDto enseignantVoeuLigneDto = new EnseignantVoeuLigneDto();
				mapper.map(enseignantVoeuLigne, enseignantVoeuLigneDto);
				enseignantVoeuLigneDto.setEditable(false);
				enseignantVoeuLigneDto.setOrder(order);
				order = order+1;
				RattachementMc _rattachementMc = rattachementMcDao.findById(enseignantVoeuLigneDto.getIdRattachementMc());
				enseignantVoeuLigneDto.setLibelleMc(_rattachementMc.getMatiereConstitutive().getLibelleFr());
				enseignantVoeuLigneDtos.add(enseignantVoeuLigneDto);
			}
			return enseignantVoeuLigneDtos;
		} catch (RuntimeException re) {
			log.error("findLigneByIdVoeu EnseignantVoeuLigneDto", re);
			throw re;
		}
	}
	
	@Override
	public List<EnseignantVoeuLigneDto> findLignesByIdFiche(int idFiche) {
		try {
			List<EnseignantVoeuLigne> enseignantVoeuLignes= enseignantVoeuLigneDao.findByIdFiche(idFiche);
			List<EnseignantVoeuLigneDto> enseignantVoeuLigneDtos = new ArrayList<EnseignantVoeuLigneDto>();
			Integer order = 1;
			for (EnseignantVoeuLigne enseignantVoeuLigne : enseignantVoeuLignes) {
				
				EnseignantVoeuLigneDto enseignantVoeuLigneDto = new EnseignantVoeuLigneDto();
				mapper.map(enseignantVoeuLigne, enseignantVoeuLigneDto);
				enseignantVoeuLigneDto.setEditable(false);
				enseignantVoeuLigneDto.setOrder(order);
				order = order+1;
				RattachementMc _rattachementMc = rattachementMcDao.findById(enseignantVoeuLigneDto.getIdRattachementMc());
				enseignantVoeuLigneDto.setLibelleMc(_rattachementMc.getMatiereConstitutive().getLibelleFr());
				enseignantVoeuLigneDtos.add(enseignantVoeuLigneDto);
			}
			return enseignantVoeuLigneDtos;
		} catch (RuntimeException re) {
			log.error("findLignesByIdFiche EnseignantVoeuLigneDto", re);
			throw re;
		}
	}


	/**
	 * [EnseignantFicheVoeuxService.saveVoeux] Method 
	 * @author rlaib  on : 15 oct. 2014  18:59:08
	 * @param listVoeux
	 * @return
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public List<EnseignantVoeuDto> saveVoeux(List<EnseignantVoeuDto> listVoeux) {
		
		try {
				
				List<EnseignantVoeuDto> result = new ArrayList<EnseignantVoeuDto>();
				for (EnseignantVoeuDto enseignantVoeuDto : listVoeux) {
						
					EnseignantVoeu enseignantVoeu = mapper.map(enseignantVoeuDto,
							EnseignantVoeu.class);
					
					if (enseignantVoeu.getId() == 0){
						enseignantVoeuDao.persist(enseignantVoeu);
					}else{
						enseignantVoeu = enseignantVoeuDao.merge(enseignantVoeu);
					}
					mapper.map(enseignantVoeu, enseignantVoeuDto);
					enseignantVoeuDto.setEditable(false);
					result.add(enseignantVoeuDto);
				}
			
				return result;
		} catch (RuntimeException re) {
			log.error("saveVoeux EnseignantVoeuDto", re);
			throw re;
		}
	}


	/**
	 * [EnseignantFicheVoeuxService.saveLignesVoeu] Method 
	 * @author rlaib  on : 16 oct. 2014  14:47:01
	 * @param listLignes
	 * @return
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public List<EnseignantVoeuLigneDto> saveLignesVoeu(
			List<EnseignantVoeuLigneDto> listLignes) {
			
			try {
						List<EnseignantVoeuLigneDto> result = new ArrayList<EnseignantVoeuLigneDto>();
						for (EnseignantVoeuLigneDto enseignantVoeuLigneDto : listLignes) {
				
								EnseignantVoeuLigne enseignantVoeuLigne = mapper.map(enseignantVoeuLigneDto, EnseignantVoeuLigne.class);
								if (enseignantVoeuLigne.getId() == 0){
												enseignantVoeuLigneDao.persist(enseignantVoeuLigne);
								}else{
												enseignantVoeuLigne = enseignantVoeuLigneDao.merge(enseignantVoeuLigne);
								}
								mapper.map(enseignantVoeuLigne, enseignantVoeuLigneDto);
								result.add(enseignantVoeuLigneDto);
						}
						return result;
				} catch (RuntimeException re) {
						log.error("saveLignesVoeu EnseignantVoeuDto", re);
						throw re;
				}
		}


	/**
	 * [EnseignantFicheVoeuxService.findFichesVoeuxByEtabBySituation] Method 
	 * @author rlaib  on : 20 oct. 2014  12:39:24
	 * @param idEtab
	 * @param idSituation
	 * @return
	 */
	@Override
	public List<EnseignantFicheVoeuxDto> findFichesVoeuxByEtabBySituation(
			Integer idEtab, Integer idAnnee, Integer idSituation) {
		try {
			List<EnseignantFicheVoeux> enseignantFiches= enseignantFicheVoeuxDao.findFichesVoeuxByEtabBySituation(idEtab,idAnnee, idSituation);
			List<EnseignantFicheVoeuxDto> enseignantFicheVoeuxDtos = new ArrayList<EnseignantFicheVoeuxDto>();
			for (EnseignantFicheVoeux fiche : enseignantFiches) {
				
				EnseignantFicheVoeuxDto ficheDto = new EnseignantFicheVoeuxDto();
				mapper.map(fiche, ficheDto);
				ficheDto.setStyleCssSituation(fiche.getSituation().getSituation().getStyleCss());
				SituationEntite  situationEntiteSoumise = new SituationEntite();
				situationEntiteSoumise = situationEntiteDao.findByCodeSituationByCodeEntite(UtilConstants.ENTITE_FICHE_VOEUX_ENSEIGNANT
								, UtilConstants.SITUTAION_SOUMISE_VALIDATION_CODE);
				
				if(situationEntiteSoumise != null && situationEntiteSoumise.getId() == ficheDto.getIdSituation())  {
						ficheDto.setEstSoumise(true);
				}
				else  {
						ficheDto.setEstSoumise(false);
						SituationEntite  situationEntiteValidee= new SituationEntite();
						situationEntiteValidee = situationEntiteDao.findByCodeSituationByCodeEntite(UtilConstants.ENTITE_FICHE_VOEUX_ENSEIGNANT
									, UtilConstants.SITUTAION_VALIDEE_CODE);
							if(situationEntiteValidee != null && situationEntiteValidee.getId() == ficheDto.getIdSituation()) 
									ficheDto.setEstValidee(true);
							else
								ficheDto.setEstValidee(false);
				}
				
				enseignantFicheVoeuxDtos.add(ficheDto);
			}
			return enseignantFicheVoeuxDtos;
		} catch (RuntimeException re) {
			log.error("findFichesVoeuxByEtabBySituation EnseignantFicheVoeuxDto", re);
			throw re;
		}
	}


	/**
	 * [EnseignantFicheVoeuxService.saveFicheVoeuxDetails] Method 
	 * @author rlaib  on : 22 oct. 2014  17:57:17
	 * @param listVoeux
	 * @param listLignesVoeux
	 * @return
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public Map<Integer,Object> saveFicheVoeuxDetails(EnseignantFicheVoeuxDto enseignantFicheVoeuxDto,
			List<EnseignantVoeuDto> listVoeux,
			List<EnseignantVoeuLigneDto> listLignesVoeux) {

		try {
			
				Map<Integer,Object> result = new HashMap<Integer, Object>();
				List<EnseignantVoeuDto> enseignantVoeuDtos =  new ArrayList<EnseignantVoeuDto>(); 
				List<EnseignantVoeuLigneDto> enseignantVoeuLigneDtos = new ArrayList<EnseignantVoeuLigneDto>();
				for (EnseignantVoeuDto enseignantVoeuDto : listVoeux) {
					
					EnseignantVoeu enseignantVoeu = mapper.map(enseignantVoeuDto,
							EnseignantVoeu.class);
							
							List<EnseignantVoeuLigne> enseignantVoeuLignes = new ArrayList<EnseignantVoeuLigne>();
							for (EnseignantVoeuLigneDto enseignantVoeuLigneDto : listLignesVoeux) {
									Integer _indexVoeu = listVoeux.indexOf(enseignantVoeuDto)+1;
									if(enseignantVoeuLigneDto.getIndexVoeu() == _indexVoeu) {
										
										EnseignantVoeuLigne enseignantVoeuLigne = mapper.map(enseignantVoeuLigneDto, EnseignantVoeuLigne.class);
//										if(enseignantVoeuLigne.getId() == 0) {
//											enseignantVoeuLigneDao.persist(enseignantVoeuLigne);
//										}
										enseignantVoeuLigne.setEnseignantVoeu(enseignantVoeu);
										enseignantVoeuLignes.add(enseignantVoeuLigne);
										mapper.map(enseignantVoeuLigne, enseignantVoeuLigneDto);
										enseignantVoeuLigneDtos.add(enseignantVoeuLigneDto);
									}
							}
							enseignantVoeu.setEnseignantVoeuLignes(new HashSet<>(enseignantVoeuLignes));
							if (enseignantVoeu.getId() == 0){
								enseignantVoeu =enseignantVoeuDao.persist(enseignantVoeu);
							}else{
								enseignantVoeu = enseignantVoeuDao.merge(enseignantVoeu);
							}
							mapper.map(enseignantVoeu, enseignantVoeuDto);
							enseignantVoeuDto.setEditable(false);
							enseignantVoeuDtos.add(enseignantVoeuDto);
				}	
				result.put(1, enseignantVoeuDtos);
				result.put(2, enseignantVoeuLigneDtos);
					
				// Generation de la fiche de services
				SituationEntite  situationEntiteValidee  = situationEntiteDao.findByCodeSituationByCodeEntite(UtilConstants.ENTITE_FICHE_VOEUX_ENSEIGNANT
								, UtilConstants.SITUTAION_VALIDEE_CODE);
				EnseignantFicheVoeux enseignantFicheVoeux =  new EnseignantFicheVoeux();
				enseignantFicheVoeux = mapper.map(enseignantFicheVoeuxDto, EnseignantFicheVoeux.class);
				 
				//Test s il s'agit d'une validation de la fiche de voeux.
				if(situationEntiteValidee.getId() == enseignantFicheVoeux.getSituation().getId()) {
					
						// La fiche de voeux et la fiche de services ont le meme ID OnetoOne Relationship.
						EnseignantFicheServices enseignantFicheServices = new EnseignantFicheServices();
						SituationEntite  situationEntiteGeneree = situationEntiteDao.findByCodeSituationByCodeEntite(UtilConstants.ENTITE_FICHE_SERVICES_ENSEIGNANT
										, UtilConstants.SITUTAION_GENEREE_AUTO_CODE);
						
						enseignantFicheServices.setSituation(situationEntiteGeneree);
						enseignantFicheServices.setEnseignantFicheVoeux(enseignantFicheVoeux);
						enseignantFicheServices.setId(enseignantFicheVoeux.getId());
						enseignantFicheServices = enseignantFicheServicesDao.persist(enseignantFicheServices);
						List<EnseignantChargePedagogique> enseignantChargePedagogiques = new ArrayList<EnseignantChargePedagogique>();
						List<EnseignantChargePedagogiqueDto> enseignantChargePedagogiqueDtos = new ArrayList<EnseignantChargePedagogiqueDto>();
			
						if(enseignantFicheServices != null) {
							 
							// Generation des charges d'enseignement equivalentes aux lignes de voeux de la fiche
							if(enseignantVoeuLigneDtos != null) {
								
								SituationEntite _SituationEntiteChargePedagogique = situationEntiteDao.findByCodeSituationByCodeEntite(UtilConstants.ENTITE_CHARGE_PEDAGOGIQUE_ENSEIGNANT
										, UtilConstants.SITUTAION_GENEREE_AUTO_CODE);
								
								for (EnseignantVoeuLigneDto _enseignantVoeuLigneDto : enseignantVoeuLigneDtos) {
									
//									EnseignantVoeuLigneDto _enseignantVoeuLigneDto =  new EnseignantVoeuLigneDto();
//									mapper.map(_enseignantVoeuLigne, _enseignantVoeuLigneDto);
//									
									if(_enseignantVoeuLigneDto.getEstValidee()) {
								
												List<AssociationGroupePedagogique> _groupesPedagogiquesMcAp = associationGroupePedagogiqueDao
														.findAdvanced(_enseignantVoeuLigneDto.getIdRattachementMc()
																						,_enseignantVoeuLigneDto.getIdAp()
																						, _enseignantVoeuLigneDto.getIdOffreFormation()
																						, _enseignantVoeuLigneDto.getIdPeriode());
												
												if(_groupesPedagogiquesMcAp != null && _groupesPedagogiquesMcAp.size()>0) {
													// Creation des charges pedagogiques
													for (AssociationGroupePedagogique _associationGroupePedagogique : _groupesPedagogiquesMcAp) {
															
															AssociationGroupePedagogiqueDto _associationGroupePedagogiqueDto = new AssociationGroupePedagogiqueDto();
															mapper.map(_associationGroupePedagogique, _associationGroupePedagogiqueDto);
															
															EnseignantChargePedagogiqueDto _enseignantChargePedagogiqueDto = new EnseignantChargePedagogiqueDto();
															EnseignantChargePedagogique _enseignantChargePedagogique = new EnseignantChargePedagogique();
															_enseignantChargePedagogiqueDto.setFicheServiceId(enseignantFicheServices.getId());
															_enseignantChargePedagogiqueDto.setVoeuLigneId(_enseignantVoeuLigneDto.getId());
															_enseignantChargePedagogiqueDto.setGroupePedagogiqueId(_associationGroupePedagogiqueDto.getGroupePedagogiqueId());
															_enseignantChargePedagogiqueDto.setVolumeHoraireAp(_associationGroupePedagogiqueDto.getApVolumeHoraire());
															_enseignantChargePedagogiqueDto.setIdSituation(_SituationEntiteChargePedagogique.getId());
															
															// Calcul de l effectif groupe
															List<AffectationGroupePedagogique> _effectifGroup = new ArrayList<AffectationGroupePedagogique>();
															_effectifGroup = affectationGroupePedagogiqueDao.findByGroupePedagogiqueId(_associationGroupePedagogiqueDto.getGroupePedagogiqueId());
															_enseignantChargePedagogiqueDto.setEffectifGroupeAp(_effectifGroup.size());
//															_enseignantChargePedagogiqueDto.setFicheServiceId(enseignantFicheServices.getId());
															
															_enseignantChargePedagogique = mapper.map(_enseignantChargePedagogiqueDto,
																	EnseignantChargePedagogique.class);
															_enseignantChargePedagogique.setEnseignantFicheServices(enseignantFicheServices);
															enseignantChargePedagogiques.add(_enseignantChargePedagogique);
															enseignantChargePedagogiqueDtos.add(_enseignantChargePedagogiqueDto);
														
													}
												}
									}
								}
								enseignantFicheServices.setEnseignantChargePedagogiques(new HashSet<>(enseignantChargePedagogiques));
							}
						}
				}
				
				if (enseignantFicheVoeux.getId() == 0){
					enseignantFicheVoeux = enseignantFicheVoeuxDao.persist(enseignantFicheVoeux);
						
				}else{
						enseignantFicheVoeux = enseignantFicheVoeuxDao.merge(enseignantFicheVoeux);
				}
				
				return result;
				
	} catch (RuntimeException re) {
		log.error("saveFicheVoeuxDetails Map<Integer,Object>", re);
		throw re;
	}
	}


	/**
	 * [EnseignantFicheVoeuxService.getEnseignantByIdUser] Method 
	 * @author rlaib  on : 29 oct. 2014  16:31:01
	 * @param idUser
	 * @return
	 */
	@Override
	public DossierEmployeDto getEnseignantByIdUser(Integer idUser) {
					
		try {
					DossierEmploye dossierEmploye = enseignantFicheVoeuxDao.getEnseignantByIdUser(idUser);
					if (dossierEmploye != null) {
							DossierEmployeDto dossierEmployeDto = new DossierEmployeDto();
							mapper.map(dossierEmploye, dossierEmployeDto);
							return dossierEmployeDto;
					}
					return null;

			} catch (RuntimeException re) {
					log.error("getEnseignantByIdUser DossierEmployeDto failed : ", re);
					throw re;
			}
	}


	/**
	 * [EnseignantFicheVoeuxService.getEnseignantById] Method 
	 * @author rlaib  on : 29 oct. 2014  19:06:30
	 * @param id
	 * @return
	 */
	@Override
	public DossierEmployeDto getEnseignantById(Long id) {
		try {
			DossierEmploye dossierEmploye = enseignantFicheVoeuxDao.getEnseignantById(id);
			if (dossierEmploye != null) {
					DossierEmployeDto dossierEmployeDto = new DossierEmployeDto();
					mapper.map(dossierEmploye, dossierEmployeDto);
					return dossierEmployeDto;
			}
			return null;

		} catch (RuntimeException re) {
				log.error("getEnseignantById DossierEmployeDto failed : ", re);
				throw re;
		}
	}


	/**
	 * [EnseignantFicheVoeuxService.getAllEnseignants] Method 
	 * @author rlaib  on : 30 oct. 2014  16:56:35
	 * @param id
	 * @return
	 */
	@Override
	public List<DossierEmployeDto> getAllEnseignants() {

		try {
					List<DossierEmploye> sossierEmployes= enseignantFicheVoeuxDao.getAllEnseignants();
					List<DossierEmployeDto> dossierEmployeDtos = new ArrayList<DossierEmployeDto>();
					for (DossierEmploye dossierEmploye : sossierEmployes) {
						DossierEmployeDto dossierEmployeDto = new DossierEmployeDto();
						mapper.map(dossierEmploye, dossierEmployeDto);
						dossierEmployeDtos.add(dossierEmployeDto);
					}
					return dossierEmployeDtos;
				} catch (RuntimeException re) {
					log.error("getAllEnseignants DossierEmployeDto", re);
					throw re;
				}
	}


	/**
	 * [EnseignantFicheVoeuxService.searchEnseignants] Method 
	 * @author rlaib  on : 11 nov. 2014  11:41:35
	 * @param query
	 * @return
	 */
	@Override
	public List<DossierEmployeDto> searchEnseignants(Integer idEtab, String query) {
		try {
			List<DossierEmploye> sossierEmployes= enseignantFicheVoeuxDao.searchEnseignants(idEtab, query);
			List<DossierEmployeDto> dossierEmployeDtos = new ArrayList<DossierEmployeDto>();
			for (DossierEmploye dossierEmploye : sossierEmployes) {
				DossierEmployeDto dossierEmployeDto = new DossierEmployeDto();
				mapper.map(dossierEmploye, dossierEmployeDto);
				dossierEmployeDto.setDisplayName(dossierEmployeDto.getIndividuPrenomLatin() + " " +dossierEmployeDto.getIndividuNomLatin()) ;
				dossierEmployeDtos.add(dossierEmployeDto);
			}
			return dossierEmployeDtos;
		} catch (RuntimeException re) {
			log.error("searchEnseignants DossierEmployeDto", re);
			throw re;
		}
	}
	
}
