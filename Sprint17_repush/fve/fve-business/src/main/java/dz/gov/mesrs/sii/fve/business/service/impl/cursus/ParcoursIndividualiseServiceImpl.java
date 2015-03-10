package dz.gov.mesrs.sii.fve.business.service.impl.cursus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.bpm.SituationEntiteDao;
import dz.gov.mesrs.sii.commons.data.dao.fve.cursus.ParcoursIndividualiseDao;
import dz.gov.mesrs.sii.commons.data.dao.fve.cursus.ParcoursIndividualiseMatiereDao;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.DossierInscriptionAdministrative;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.GroupePedagogique;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.ParcoursIndividualise;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.ParcoursIndividualiseMatiere;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DossierInscriptionAdministrativeDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.GroupePedagogiqueDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.ParcoursIndividualiseDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.ParcoursIndividualiseMatiereDto;
import dz.gov.mesrs.sii.fve.business.service.cursus.ParcoursIndividualiseService;


 

/**
 * @author BELDI Jamel  on : 14 juil. 2014  15:38:11
 */
@Service("parcoursIndividualiseService")
public class ParcoursIndividualiseServiceImpl implements ParcoursIndividualiseService  {

	@Autowired
	@Qualifier ("parcoursIndividualiseDao")
	private ParcoursIndividualiseDao parcoursIndividualiseDao;
	@Autowired
	@Qualifier ("parcoursIndividualiseMatiereDao")
	private ParcoursIndividualiseMatiereDao parcoursIndividualiseMatiereDao;
	@Autowired
	@Qualifier ("situationEntiteDao")
	private SituationEntiteDao situationEntiteDao;
	private static final Log log = LogFactory.getLog(ParcoursIndividualiseServiceImpl.class);
	
    @Autowired
	@Qualifier("mapper")
	private DozerBeanMapper mapper;
	
	
	
	public ParcoursIndividualiseServiceImpl(){

	}



	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void insertOrUpdate(
			List<ParcoursIndividualiseMatiereDto> parcoursIndividualiseMatiereList) {
		try {
			if( parcoursIndividualiseMatiereList == null || parcoursIndividualiseMatiereList.isEmpty()){
				return;
			}
			ParcoursIndividualise parcoursIndividualise = new ParcoursIndividualise();
			mapper.map(parcoursIndividualiseMatiereList.get(0), parcoursIndividualise);
			

			if (parcoursIndividualise.getId() == 0) {
				parcoursIndividualise.setDateCreation(new Date());
				parcoursIndividualiseDao.persist(parcoursIndividualise);
				for (ParcoursIndividualiseMatiereDto parcoursIndividualiseMatiereDto : parcoursIndividualiseMatiereList) {
				ParcoursIndividualiseMatiere parcoursIndividualiseMatiere = mapper.map(parcoursIndividualiseMatiereDto,
						ParcoursIndividualiseMatiere.class);				
				parcoursIndividualiseMatiere.setParcoursIndividualise(parcoursIndividualise);
				parcoursIndividualiseMatiereDao.persist(parcoursIndividualiseMatiere);
				}
			} else {
				parcoursIndividualiseDao.merge(parcoursIndividualise);
				for (ParcoursIndividualiseMatiereDto parcoursIndividualiseMatiereDto : parcoursIndividualiseMatiereList) {
					ParcoursIndividualiseMatiere parcoursIndividualiseMatiere = mapper.map(parcoursIndividualiseMatiereDto,
							ParcoursIndividualiseMatiere.class);				
					parcoursIndividualiseMatiere.setParcoursIndividualise(parcoursIndividualise);
					parcoursIndividualiseMatiereDao.merge(parcoursIndividualiseMatiere);
					}
				
				
				
			}
			
			
			log.info("insertOrUpdate success");
			
		} catch (RuntimeException e) {
			log.error("insertOrUpdate failed", e);
			throw e;

		}	
	}



	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void remove(
			ParcoursIndividualiseMatiereDto parcoursIndividualiseMatiereDto) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public ParcoursIndividualiseDto/*List<ParcoursIndividualiseMatiereDto>*/ findById(int id) {
		try {
			
			ParcoursIndividualise parcoursIndividualise = parcoursIndividualiseDao.findById(id);
			ParcoursIndividualiseDto  _parcoursIndividualiseDto = new ParcoursIndividualiseDto();
			mapper.map(parcoursIndividualise, _parcoursIndividualiseDto);
			if(parcoursIndividualise!=null){
				List<ParcoursIndividualiseMatiere> parcoursIndividualiseMatiereList = parcoursIndividualiseMatiereDao.findByIdParcours(parcoursIndividualise.getId());
				if(parcoursIndividualiseMatiereList!=null && !parcoursIndividualiseMatiereList.isEmpty() ){
					List <ParcoursIndividualiseMatiereDto> result = new ArrayList<ParcoursIndividualiseMatiereDto>();
					for (ParcoursIndividualiseMatiere parcoursIndividualiseMatiere : parcoursIndividualiseMatiereList) {
						ParcoursIndividualiseMatiereDto parcoursIndividualiseMatiereDto = new ParcoursIndividualiseMatiereDto()	;
						mapper.map(parcoursIndividualiseMatiere, parcoursIndividualiseMatiereDto);
						result.add(parcoursIndividualiseMatiereDto);
					}
					_parcoursIndividualiseDto.setParcoursIndividualiseMatiereList(result);
					return _parcoursIndividualiseDto;
				}
				
			}
			
		} catch (RuntimeException e) {
			log.error("findById failed", e);
			throw e;

		}	
		return null;
	}



	@Override
	public ParcoursIndividualiseDto /*List<ParcoursIndividualiseMatiereDto>*/  findByIdInscriptionAdministrative(
			int idInscriptionAdministrative) {
		try{
		ParcoursIndividualise parcoursIndividualise = parcoursIndividualiseDao.findByIdInscriptionAdministrative(idInscriptionAdministrative);
		 if(parcoursIndividualise!=null && parcoursIndividualise.getId()!=0){
			 return findById(parcoursIndividualise.getId());
		 }
		} catch (RuntimeException e) {
			log.error("findByIdInscriptionAdministrative failed", e);
			throw e;

		}	
		return null;
	}



	@Override
	public List<ParcoursIndividualiseMatiereDto> findAll() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List<ParcoursIndividualiseMatiereDto> findAdvanced(
			ParcoursIndividualiseMatiereDto ParcoursIndividualiseMatiereDto) {
		// TODO Auto-generated method stub
		return null;
	}


	
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public boolean updateSituation(int id, int idSituation) {
		try{
			ParcoursIndividualise parcoursIndividualise = parcoursIndividualiseDao.findById(id);
			if(parcoursIndividualise!=null){
				parcoursIndividualise.setSituationEntite(situationEntiteDao.findById(idSituation));
				parcoursIndividualiseDao.merge(parcoursIndividualise);
				return true;
			}
			
		} catch (RuntimeException e) {
			log.error("updateSituation failed", e);
			throw e;

		}	
		return false;
	}
	
	
	/**
	 * [ParcoursIndividualiseServiceImpl.parcoursIndividualiseDao] Getter 
	 * @author BELDI Jamel on : 15 juil. 2014  14:47:09
	 * @return the parcoursIndividualiseDao
	 */
	public ParcoursIndividualiseDao getParcoursIndividualiseDao() {
		return parcoursIndividualiseDao;
	}



	/**
	 * [ParcoursIndividualiseServiceImpl.parcoursIndividualiseDao] Setter 
	 * @author BELDI Jamel on : 15 juil. 2014  14:47:09
	 * @param parcoursIndividualiseDao the parcoursIndividualiseDao to set
	 */
	public void setParcoursIndividualiseDao(
			ParcoursIndividualiseDao parcoursIndividualiseDao) {
		this.parcoursIndividualiseDao = parcoursIndividualiseDao;
	}



	/**
	 * [ParcoursIndividualiseServiceImpl.parcoursIndividualiseMatiereDao] Getter 
	 * @author BELDI Jamel on : 15 juil. 2014  14:47:09
	 * @return the parcoursIndividualiseMatiereDao
	 */
	public ParcoursIndividualiseMatiereDao getParcoursIndividualiseMatiereDao() {
		return parcoursIndividualiseMatiereDao;
	}



	/**
	 * [ParcoursIndividualiseServiceImpl.parcoursIndividualiseMatiereDao] Setter 
	 * @author BELDI Jamel on : 15 juil. 2014  14:47:09
	 * @param parcoursIndividualiseMatiereDao the parcoursIndividualiseMatiereDao to set
	 */
	public void setParcoursIndividualiseMatiereDao(
			ParcoursIndividualiseMatiereDao parcoursIndividualiseMatiereDao) {
		this.parcoursIndividualiseMatiereDao = parcoursIndividualiseMatiereDao;
	}



	/**
	 * [ParcoursIndividualiseServiceImpl.mapper] Getter 
	 * @author BELDI Jamel on : 15 juil. 2014  14:47:09
	 * @return the mapper
	 */
	public DozerBeanMapper getMapper() {
		return mapper;
	}



	/**
	 * [ParcoursIndividualiseServiceImpl.mapper] Setter 
	 * @author BELDI Jamel on : 15 juil. 2014  14:47:09
	 * @param mapper the mapper to set
	 */
	public void setMapper(DozerBeanMapper mapper) {
		this.mapper = mapper;
	}



	/**
	 * [ParcoursIndividualiseServiceImpl.situationEntiteDao] Getter 
	 * @author BELDI Jamel on : 17 juil. 2014  11:30:27
	 * @return the situationEntiteDao
	 */
	public SituationEntiteDao getSituationEntiteDao() {
		return situationEntiteDao;
	}



	/**
	 * [ParcoursIndividualiseServiceImpl.situationEntiteDao] Setter 
	 * @author BELDI Jamel on : 17 juil. 2014  11:30:27
	 * @param situationEntiteDao the situationEntiteDao to set
	 */
	public void setSituationEntiteDao(SituationEntiteDao situationEntiteDao) {
		this.situationEntiteDao = situationEntiteDao;
	}



	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public List<ParcoursIndividualiseDto> findParcourstoValidate(String codeEtab) {
		try{
			List<ParcoursIndividualise> _parcoursIndividualiseList = parcoursIndividualiseDao.findToValidate(codeEtab);
			 if(_parcoursIndividualiseList!=null && !_parcoursIndividualiseList.isEmpty()){
				 List <ParcoursIndividualiseDto> result = new ArrayList<ParcoursIndividualiseDto>();
					for (ParcoursIndividualise parcoursIndividualise : _parcoursIndividualiseList) {
						ParcoursIndividualiseDto parcoursIndividualiseDto = new ParcoursIndividualiseDto()	;
						mapper.map(parcoursIndividualise, parcoursIndividualiseDto);
						List<ParcoursIndividualiseMatiere> parcoursIndividualiseMatiereList = parcoursIndividualiseMatiereDao.findByIdParcours(parcoursIndividualise.getId());
						if(parcoursIndividualiseMatiereList!=null && !parcoursIndividualiseMatiereList.isEmpty() ){
							List <ParcoursIndividualiseMatiereDto> _listMatiere = new ArrayList<ParcoursIndividualiseMatiereDto>();
							for (ParcoursIndividualiseMatiere parcoursIndividualiseMatiere : parcoursIndividualiseMatiereList) {
								ParcoursIndividualiseMatiereDto parcoursIndividualiseMatiereDto = new ParcoursIndividualiseMatiereDto()	;
								mapper.map(parcoursIndividualiseMatiere, parcoursIndividualiseMatiereDto);
								_listMatiere.add(parcoursIndividualiseMatiereDto);
							}
							parcoursIndividualiseDto.setParcoursIndividualiseMatiereList(_listMatiere);
							
						}
						result.add(parcoursIndividualiseDto);
					}
					return result;
			 }
			} catch (RuntimeException e) {
				log.error("findParcourstoValidate failed", e);
				throw e;

			}	
			return null;
	}



	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void updateParcoursIndividualiseMatiere(
			ParcoursIndividualiseMatiereDto parcoursIndividualiseMatiereDto) {
		try{
			if (parcoursIndividualiseMatiereDto==null){
				return;
			}
			ParcoursIndividualiseMatiere _parcoursIndividualiseMatiere = new ParcoursIndividualiseMatiere();
			mapper.map(parcoursIndividualiseMatiereDto, _parcoursIndividualiseMatiere);				
			parcoursIndividualiseMatiereDao.merge(_parcoursIndividualiseMatiere);
		} catch (RuntimeException e) {
			log.error("updateParcoursIndividualiseMatiere failed", e);
			throw e;

		}	
	}



	@Override
	public List<ParcoursIndividualiseDto> findAdvanced(
			ParcoursIndividualiseDto dto) {
		try{
			ParcoursIndividualise _searchBo = mapper.map(dto,
					ParcoursIndividualise.class);
			List<ParcoursIndividualise> _parcoursIndividualiseList = parcoursIndividualiseDao.findAdvanced(_searchBo);
			 if(_parcoursIndividualiseList!=null && !_parcoursIndividualiseList.isEmpty()){
				 List <ParcoursIndividualiseDto> result = new ArrayList<ParcoursIndividualiseDto>();
					for (ParcoursIndividualise parcoursIndividualise : _parcoursIndividualiseList) {
						ParcoursIndividualiseDto parcoursIndividualiseDto = new ParcoursIndividualiseDto()	;
						mapper.map(parcoursIndividualise, parcoursIndividualiseDto);
						List<ParcoursIndividualiseMatiere> parcoursIndividualiseMatiereList = parcoursIndividualiseMatiereDao.findByIdParcours(parcoursIndividualise.getId());
						if(parcoursIndividualiseMatiereList!=null && !parcoursIndividualiseMatiereList.isEmpty() ){
							List <ParcoursIndividualiseMatiereDto> _listMatiere = new ArrayList<ParcoursIndividualiseMatiereDto>();
							for (ParcoursIndividualiseMatiere parcoursIndividualiseMatiere : parcoursIndividualiseMatiereList) {
								ParcoursIndividualiseMatiereDto parcoursIndividualiseMatiereDto = new ParcoursIndividualiseMatiereDto()	;
								mapper.map(parcoursIndividualiseMatiere, parcoursIndividualiseMatiereDto);
								_listMatiere.add(parcoursIndividualiseMatiereDto);
							}
							parcoursIndividualiseDto.setParcoursIndividualiseMatiereList(_listMatiere);
							
						}
						
						result.add(parcoursIndividualiseDto);
					}
					return result;
			 }
			} catch (RuntimeException e) {
				log.error("findAdvanced failed", e);
				throw e;

			}	
			return null;
	}

	@Override
	public ParcoursIndividualiseDto/*List<ParcoursIndividualiseMatiereDto>*/ findByIdInscriptionAdministrativeAndPeriode(
			int idInscriptionAdministrative, int idPeriode) {
		try{
		ParcoursIndividualise parcoursIndividualise = parcoursIndividualiseDao.findByIdInscriptionAdministrativeAndPeriode(idInscriptionAdministrative,idPeriode);
		 if(parcoursIndividualise!=null && parcoursIndividualise.getId()!=0){
			 return findById(parcoursIndividualise.getId());
		 }
		} catch (RuntimeException e) {
			log.error("findByIdInscriptionAdministrative failed", e);
			throw e;

		}	
		return null;
	}



	@Override
	public List<DossierInscriptionAdministrativeDto> findDossierInscription(
			DossierInscriptionAdministrativeDto searchDto, GroupePedagogiqueDto groupePedagogiqueDto,
			Integer idSituation) {
		try{
			DossierInscriptionAdministrative _searchBo = mapper.map(searchDto,
					DossierInscriptionAdministrative.class);
			GroupePedagogique _groupePedagogique = mapper.map(groupePedagogiqueDto,
					GroupePedagogique.class);
			List<DossierInscriptionAdministrative> _dossierInscriptionAdministrativeList = parcoursIndividualiseDao.findDossierInscription(_searchBo, _groupePedagogique, idSituation);
			 if(_dossierInscriptionAdministrativeList!=null && !_dossierInscriptionAdministrativeList.isEmpty()){
				 List <DossierInscriptionAdministrativeDto> result = new ArrayList<DossierInscriptionAdministrativeDto>();
					for (DossierInscriptionAdministrative dossierInscriptionAdministrative : _dossierInscriptionAdministrativeList) {
						DossierInscriptionAdministrativeDto dossierInscriptionAdministrativeDto = new DossierInscriptionAdministrativeDto()	;
						//mapSituation(dossierInscriptionAdministrativeDto, parcoursIndividualise);
						mapper.map(dossierInscriptionAdministrative, dossierInscriptionAdministrativeDto);
						result.add(dossierInscriptionAdministrativeDto);
					}
					return result;
			 }
			} catch (RuntimeException e) {
				log.error("findDossierInscription failed", e);
				throw e;

			}	
			return null;
	}



	@Override
	public List<DossierInscriptionAdministrativeDto> findDossierInscriptions(
			Integer idRattachementMc, Integer idPeriode,
			Integer idAnneeAcademique, Integer idSituation) {
		try{
			if(idRattachementMc!=null && idPeriode !=null && idAnneeAcademique !=null && idSituation !=null){
			List<DossierInscriptionAdministrative> _dossierInscriptionAdministrativeList = parcoursIndividualiseDao.findDossierInscriptions(idRattachementMc, idPeriode, idAnneeAcademique, idSituation);
			 if(_dossierInscriptionAdministrativeList!=null && !_dossierInscriptionAdministrativeList.isEmpty()){
				 List <DossierInscriptionAdministrativeDto> result = new ArrayList<DossierInscriptionAdministrativeDto>();
					for (DossierInscriptionAdministrative dossierInscriptionAdministrative : _dossierInscriptionAdministrativeList) {
						DossierInscriptionAdministrativeDto dossierInscriptionAdministrativeDto = new DossierInscriptionAdministrativeDto()	;			
						mapper.map(dossierInscriptionAdministrative, dossierInscriptionAdministrativeDto);
						result.add(dossierInscriptionAdministrativeDto);
					}
					return result;
			 }
			}
			} catch (RuntimeException e) {
				log.error("findDossierInscriptions failed", e);
				throw e;

			}	
			return null;
	}



	@Override
	public List<DossierInscriptionAdministrativeDto> findDossierInscriptionsNotSubscribedOnExamen(
			Integer idRattachementMc, Integer idPeriode,
			Integer idAnneeAcademique, Integer idSituation, Integer idExamen, Integer idNiveau, String refCodeEtablissement) {
		try{
			if(idRattachementMc!=null && idPeriode !=null && idAnneeAcademique !=null && idSituation !=null && idExamen !=null && idNiveau !=null && refCodeEtablissement !=null ){
			List<DossierInscriptionAdministrative> _dossierInscriptionAdministrativeList = parcoursIndividualiseDao.findDossierInscriptionsNotSubscribedOnExamen(idRattachementMc, idPeriode, idAnneeAcademique, idSituation, idExamen,  idNiveau, refCodeEtablissement);
			 if(_dossierInscriptionAdministrativeList!=null && !_dossierInscriptionAdministrativeList.isEmpty()){
				 List <DossierInscriptionAdministrativeDto> result = new ArrayList<DossierInscriptionAdministrativeDto>();
					for (DossierInscriptionAdministrative dossierInscriptionAdministrative : _dossierInscriptionAdministrativeList) {
						DossierInscriptionAdministrativeDto dossierInscriptionAdministrativeDto = new DossierInscriptionAdministrativeDto()	;			
						mapper.map(dossierInscriptionAdministrative, dossierInscriptionAdministrativeDto);
						result.add(dossierInscriptionAdministrativeDto);
					}
					return result;
			 }
			}
			} catch (RuntimeException e) {
				log.error("findDossierInscriptions failed", e);
				throw e;

			}	
			return null;
	}	
}



