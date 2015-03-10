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
import dz.gov.mesrs.sii.commons.data.dao.fve.scolarite.EnseignantServiceFaitDao;
import dz.gov.mesrs.sii.commons.data.dao.fve.scolarite.EnseignantVoeuLigneDao;
import dz.gov.mesrs.sii.commons.data.dao.fve.scolarite.TypeSeanceDao;
import dz.gov.mesrs.sii.commons.data.model.bpm.SituationEntiteOccurrence;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.RattachementMc;
import dz.gov.mesrs.sii.commons.data.model.fve.scolarite.EnseignantChargePedagogique;
import dz.gov.mesrs.sii.commons.data.model.fve.scolarite.EnseignantServiceFait;
import dz.gov.mesrs.sii.commons.data.model.fve.scolarite.EnseignantVoeuLigne;
import dz.gov.mesrs.sii.commons.data.model.fve.scolarite.TypeSeance;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.RattachementMcDto;
import dz.gov.mesrs.sii.fve.business.model.dto.scolarite.EnseignantChargePedagogiqueDto;
import dz.gov.mesrs.sii.fve.business.model.dto.scolarite.EnseignantServiceFaitDto;
import dz.gov.mesrs.sii.fve.business.model.dto.scolarite.EnseignantVoeuLigneDto;
import dz.gov.mesrs.sii.fve.business.model.dto.scolarite.TypeSeanceDto;
import dz.gov.mesrs.sii.fve.business.service.scolarite.EnseignantServiceFaitService;

/**
 * @author rlaib  on : 12 oct. 2014  16:31:31
 */
@Service("enseignantServiceFaitService")
public class EnseignantServiceFaitServiceImpl implements
EnseignantServiceFaitService {

	@Autowired
	@Qualifier ("enseignantServiceFaitDao")
	private EnseignantServiceFaitDao enseignantServiceFaitDao;
	
	@Autowired
	@Qualifier ("typeSeanceDao")
	private TypeSeanceDao typeSeanceDao;
	
	@Autowired
	@Qualifier ("situationEntiteOccurrenceDao")
	private SituationEntiteOccurrenceDao  situationEntiteOccurrenceDao;
	
	@Autowired
	@Qualifier ("situationEntiteDao")
	private SituationEntiteDao situationEntiteDao;
	
	@Autowired
	@Qualifier ("enseignantChargePedagogiqueDao")
	private EnseignantChargePedagogiqueDao  enseignantChargePedagogiqueDao;
	
	private static final Log log = LogFactory.getLog(EnseignantServiceFaitServiceImpl.class);
	
    @Autowired
	@Qualifier("mapper")
	private DozerBeanMapper mapper;
    
	
	@Autowired
	@Qualifier ("enseignantVoeuLigneDao")
	private EnseignantVoeuLigneDao  enseignantVoeuLigneDao;
	
	@Autowired
	@Qualifier ("rattachementMcDao")
	private RattachementMcDao  rattachementMcDao;
	
    public EnseignantServiceFaitServiceImpl(){

	}

	/**
	 * [EnseignantServiceFaitService.insertOrUpdate] Method 
	 * @author rlaib  on : 29 oct. 2014  13:41:10
	 * @param enseignantServiceFaitDto
	 * @return
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public EnseignantServiceFaitDto insertOrUpdate(
			EnseignantServiceFaitDto enseignantServiceFaitDto) {
	
		EnseignantServiceFait enseignantServiceFait = mapper.map(enseignantServiceFaitDto,
				EnseignantServiceFait.class);
		try {
					if (enseignantServiceFait.getId() == 0){
						enseignantServiceFait = enseignantServiceFaitDao.persist(enseignantServiceFait);
							
					}else{
						enseignantServiceFait = enseignantServiceFaitDao.merge(enseignantServiceFait);
					}
					mapper.map(enseignantServiceFait, enseignantServiceFaitDto);
					// Sauvegarde historique de la situation du service fait
					SituationEntiteOccurrenceDto situationEntiteOccurrenceDto = new SituationEntiteOccurrenceDto();
					situationEntiteOccurrenceDto.setIdOccurrence(enseignantServiceFaitDto.getId());
					situationEntiteOccurrenceDto.setIdSituation(enseignantServiceFaitDto.getIdSituation());
					situationEntiteOccurrenceDto.setDateSituation(new java.util.Date());
					SituationEntiteOccurrence  situationEntiteOccurrence = mapper.map(situationEntiteOccurrenceDto, SituationEntiteOccurrence.class);
					situationEntiteOccurrenceDao.persist(situationEntiteOccurrence);
				
					return enseignantServiceFaitDto;
		
		} catch (RuntimeException re) {
					log.error("insertOrUpdate EnseignantServiceFaitDto failed : ", re);
					throw re;
		}
	}

	/**
	 * [EnseignantServiceFaitService.findById] Method 
	 * @author rlaib  on : 29 oct. 2014  13:41:10
	 * @param id
	 * @return
	 */
	@Override
	public EnseignantServiceFaitDto findById(int id) {
		
		try {
					EnseignantServiceFait enseignantServiceFait = enseignantServiceFaitDao.findById(id);
					if (enseignantServiceFait != null) {
							EnseignantServiceFaitDto enseignantServiceFaitDto = new EnseignantServiceFaitDto();
							mapper.map(enseignantServiceFait, enseignantServiceFaitDto);
							return enseignantServiceFaitDto;
					}
					return null;

			} catch (RuntimeException re) {
				log.error("findById EnseignantServiceFaitDto failed : ", re);
				throw re;
			}
	}

	/**
	 * [EnseignantServiceFaitService.findServicesFaitsByEtabByUserByOfByPeriodeByYear] Method 
	 * @author rlaib  on : 29 oct. 2014  13:41:10
	 * @param idEtab
	 * @param idUser
	 * @param idAnnee
	 * @param idOf
	 * @param idPeriode
	 * @return
	 */
	@Override
	public List<EnseignantServiceFaitDto> findServicesFaitsByEtabByUserByOfByPeriodeByYearByCharge(
			Integer idEtab, Long idUser, Integer idAnnee, Integer idOf,
			Integer idPeriode, Integer idCharge) {
		

		try {
				List<EnseignantServiceFaitDto> result = new ArrayList<EnseignantServiceFaitDto>();
				List<EnseignantServiceFait> enseignantServiceFaits = enseignantServiceFaitDao.findByEtabByUserByOfByPeriodeByYearByCharge(idEtab, idUser, idAnnee, idOf, idPeriode,idCharge);
				for (EnseignantServiceFait _enseignantServiceFait : enseignantServiceFaits) {
						
					EnseignantServiceFaitDto  _enseignantServiceFaitDto = new EnseignantServiceFaitDto();
					EnseignantChargePedagogiqueDto  _enseignantChargePedagogiqueDto = new EnseignantChargePedagogiqueDto();
					EnseignantChargePedagogique _enseignantChargePedagogique = enseignantChargePedagogiqueDao.findById(_enseignantServiceFait.getEnseignantChargePedagogique().getId());
					
					mapper.map(_enseignantChargePedagogique, _enseignantChargePedagogiqueDto);
					mapper.map(_enseignantServiceFait, _enseignantServiceFaitDto);
					mapper.map(_enseignantChargePedagogiqueDto, _enseignantServiceFaitDto);
					_enseignantServiceFaitDto.setStyleCssSituation(_enseignantServiceFait.getSituation().getSituation().getStyleCss());
					result.add(_enseignantServiceFaitDto);
				}
				return result;
	
	} catch (RuntimeException re) {
		log.error("findServicesFaitsByEtabByUserByOfByPeriodeByYear EnseignantServiceFaitDto", re);
		throw re;
	}
	}

	/**
	 * [EnseignantServiceFaitService.getAllTypesSeances] Method 
	 * @author rlaib  on : 10 nov. 2014  14:58:16
	 * @return
	 */
	@Override
	public List<TypeSeanceDto> getAllTypesSeances() {
		try {
			List<TypeSeance> typesSeances = typeSeanceDao.findAll();
			List<TypeSeanceDto> typeSeanceDtos = new ArrayList<TypeSeanceDto>();
			for (TypeSeance _typeSeance : typesSeances) {
				
				TypeSeanceDto 	_typeSeanceDto = new TypeSeanceDto();
				mapper.map(_typeSeance, _typeSeanceDto);
				typeSeanceDtos.add(_typeSeanceDto);
		}
			return typeSeanceDtos;
		}
		catch (RuntimeException re) {
				log.error("getAllTypesSeances TypeSeanceDto", re);
				throw re;
		}
	}

	/**
	 * [EnseignantServiceFaitService.findSyntheseChargesEnseignant] Method 
	 * @author Rafik  on : 8 déc. 2014  19:51:12
	 * @param idEtab
	 * @param idUser
	 * @param idAnnee
	 * @param idOf
	 * @param idPeriode
	 * @return
	 */
	@Override
	public List<Object[]> findSyntheseChargesEnseignant(
			Integer idEtab,
			Long idUser,
			Integer idAnnee, 
			Integer idPeriode) {
		
		try {
			
			if(idEtab==null || idUser==null || idAnnee==null || idPeriode==null)
				return null;
			List<Object[]> enseignantChargePedagogiques = enseignantChargePedagogiqueDao.findSyntheseChargesEnseignant(idEtab, idUser, idAnnee, idPeriode);
			List<Object[]> enseignantChargePedagogiqueDtos = new ArrayList<Object[]>();
			for (Object[] _object : enseignantChargePedagogiques) {
				
				EnseignantChargePedagogiqueDto 	enseignantChargePedagogiqueDto = new EnseignantChargePedagogiqueDto();
				EnseignantChargePedagogique  enseignantChargePedagogique = (EnseignantChargePedagogique) _object[0];
				mapper.map(enseignantChargePedagogique, enseignantChargePedagogiqueDto);
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
				
				_object[0] = enseignantChargePedagogiqueDto;
				enseignantChargePedagogiqueDtos.add(_object);
		}
			return enseignantChargePedagogiqueDtos;
		}
		catch (RuntimeException re) {
				log.error("findSyntheseChargesEnseignant EnseignantChargePedagogiqueDto", re);
				throw re;
		}
	}
	
}
