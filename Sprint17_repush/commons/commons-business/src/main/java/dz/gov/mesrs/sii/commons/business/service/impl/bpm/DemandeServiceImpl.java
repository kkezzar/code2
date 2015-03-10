package dz.gov.mesrs.sii.commons.business.service.impl.bpm;
///**
// * [dz.gov.mesrs.sii.fve.business.service.impl.demande.DemandeServiceImpl.java] 
// * @author BELDI Jamel on : 20 avr. 2014  11:33:56
// * This allows request HABILITATION and AMANDEMENT management  
// */
//package dz.gov.mesrs.sii.commons.business.service.impl.habilitation;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map.Entry;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;
//
//import dz.gov.mesrs.sii.commons.business.model.dto.habilitation.DemandeDto;
//import dz.gov.mesrs.sii.commons.business.model.dto.habilitation.DemandeI18nDto;
//import dz.gov.mesrs.sii.commons.business.model.dto.habilitation.SituationI18nDto;
//import dz.gov.mesrs.sii.commons.business.model.dto.habilitation.TypeDemandeDto;
//import dz.gov.mesrs.sii.commons.business.service.habilitation.DemandeService;
//import dz.gov.mesrs.sii.commons.data.dao.fve.habilitation.DemandeDao;
//import dz.gov.mesrs.sii.commons.data.dao.fve.habilitation.DemandeI18nDao;
//import dz.gov.mesrs.sii.commons.data.dao.fve.habilitation.SituationEntiteDao;
//import dz.gov.mesrs.sii.commons.data.dao.fve.habilitation.TypeDemandeDao;
//import dz.gov.mesrs.sii.commons.data.model.fve.habilitation.Demande;
//import dz.gov.mesrs.sii.commons.data.model.fve.habilitation.DemandeI18n;
//import dz.gov.mesrs.sii.commons.data.model.fve.habilitation.SituationI18n;
//import dz.gov.mesrs.sii.commons.data.model.fve.habilitation.TypeDemande;
//
//
///**
// * @author BELDI Jamel  on : 20 avr. 2014  11:33:56
// */
//@Service("demandeService")
//public class DemandeServiceImpl implements DemandeService {
//	private static final Log log = LogFactory
//			.getLog(DemandeServiceImpl.class);
//	
//	@Autowired
//	@Qualifier ("demandeDao")
//	private DemandeDao demandeDao;
//	@Autowired
//	@Qualifier("mapper")
//	private org.dozer.DozerBeanMapper mapper;
//	@Autowired
//	@Qualifier ("demandeI18nDao")
//	private DemandeI18nDao demandeI18nDao;
//
//	@Autowired
//	@Qualifier ("offreFormationService")
//	private OffreFormationService offreFormationService;
//
//	
//	@Autowired
//	@Qualifier ("situationEntiteDao")
//	private SituationEntiteDao situationEntiteDao;
//	
//	@Autowired
//	@Qualifier ("typeDemandeDao")
//	private TypeDemandeDao typeDemandeDao;
//	
//	/**
//	 * [DemandeServiceImpl.DemandeServiceImpl()] Constructor
//	 * @author BELDI Jamel  on : 20 avr. 2014  11:33:56	
//	 */
//	public DemandeServiceImpl() {
//		
//	}
//
//	
//	
//	
//	/**
//	 * [DemandeServiceImpl.saveOrUpdate()] save or update DEMANDE HABILITATION or DEMANDE AMANDEMENT
//	 * @author BELDI Jamel  on : 20 avr. 2014  11:33:56	
//	 * @param DemandeDto
//	 * @return DemandeDto
//	 */
//	@Override
//	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
//	public DemandeDto saveOrUpdate(DemandeDto demandeDto,	HashMap<String, DemandeI18nDto>  mapDemandeI18n) {
//		
//		
//		try {
//			Demande demande = new Demande();
//			mapper.map(demandeDto, demande);
//			//save
//			if (demande.getId() == 0){
//				
//				if(demande.getDateDemande()!=null){
//					demande.setDateCreation(demande.getDateDemande());
//				}else{
//					demande.setDateCreation(new Date());
//				}
//				demandeDao.persist(demande);
//				//demande= demandeDao.merge(demande);
//				if(mapDemandeI18n!=null && !mapDemandeI18n.isEmpty() ){
//				for(Entry<String, DemandeI18nDto> entry : mapDemandeI18n.entrySet()) {
//					String langue = entry.getKey();
//					DemandeI18nDto demandeI18nDto = entry.getValue();
//				//	demandeI18nDto.setId(entry.hashCode());
//					demandeI18nDto.setDemandeId(demande.getId());
//				    // traitements
//					DemandeI18n demandeI18n = new DemandeI18n();
//					mapper.map(demandeI18nDto, demandeI18n);
//					demandeI18n.setLangue(langue);
//					//demandeI18n=demandeI18nDao.merge(demandeI18n);
//				    demandeI18nDao.persist(demandeI18n);
//				}
//				
//				}
//			}
//			//update
//			else{
//				demande = demandeDao.merge(demande);
//				if(mapDemandeI18n!=null && !mapDemandeI18n.isEmpty() ){
//					for(Entry<String, DemandeI18nDto> entry : mapDemandeI18n.entrySet()) {
//						String langue = entry.getKey();
//						DemandeI18nDto demandeI18nDto = entry.getValue();
//					    // traitements
//						demandeI18nDto.setDemandeId(demande.getId());
//						DemandeI18n demandeI18n = new DemandeI18n();
//						mapper.map(demandeI18nDto, demandeI18n);
//						demandeI18n.setLangue(langue);
//						
//						if(demandeI18n.getId()==0){
//					    demandeI18nDao.persist(demandeI18n);
//						}else{
//							demandeI18nDao.merge(demandeI18n);
//						}
//					}
//				}
//				
//				
//			}
//			mapper.map(demande, demandeDto);
//			log.debug("save successful");
//			return demandeDto;
//			
//		} catch (RuntimeException e) {
//			log.error("save failed", e);
//			throw e;
//		}
//	}
//
//	/**
//	 * [DemandeServiceImpl.remove()] remove DEMANDE HABILITATION or DEMANDE AMANDEMENT
//	 * @author BELDI Jamel  on : 20 avr. 2014  11:33:56	
//	 * @param DemandeDto
//	 */
//	public void remove(DemandeDto demandeDto) {
//		
//	}
//
//	
//	/**
//	 * [DemandeServiceImpl.findById()] find DEMANDE By primary key
//	 * @author BELDI Jamel  on : 20 avr. 2014  11:33:56	
//	 * @param id, primary key
//	 */
//	public DemandeDto findById(int id) {
//		DemandeDto demandeDto = null;
//		try {
//			Demande demande = demandeDao
//					.findById(id);
//			if (demande != null) {
//				demandeDto = new DemandeDto();
//				log.debug("DemandeDto found successfully with id = "
//						+ id);
//				mapper.map(demande, demandeDto);
//
//			}
//		} catch (RuntimeException e) {
//			log.error("get DemandeDto list failed", e);
//			throw e;
//		}
//		return demandeDto;
//	}
//
//	/**
//	 * [DemandeServiceImpl.findDemandeByQueryAndType()] find DEMANDEs By Query and Type of DEMANDE: HABILITATION or AMANDEMENT
//	 * @author BELDI Jamel  on : 20 avr. 2014  11:33:56	
//	 * @param query, key of search
//	 * @param type,  id of the NCTypeDemande
//	 */
//	public List<DemandeDto> findDemandeByQueryAndType(String query, int type) {
//		
//		return null;
//	}
//
//	
//	/**
//	 * [DemandeServiceImpl.findByType()] find DEMANDEs By Type of DEMANDE: HABILITATION or AMANDEMENT
//	 * @author BELDI Jamel  on : 20 avr. 2014  11:33:56	
//	 * @param type,  id of the NCTypeDemande
//	 * @return List of DemandeDto
//	 */
//	public List<DemandeDto> findByType(int type) {
//		
//		return null;
//	}
//
//	/**
//	 * [DemandeServiceImpl.findAll()] find All DEMANDE
//	 * @author BELDI Jamel  on : 20 avr. 2014  11:33:56	
//	 * @param
//	 * @return List of DemandeDto
//	 */
//	public List<DemandeDto> findAll() {
//		
//		return null;
//	}
//
//
//
//
//	/**
//	 * [DemandeServiceImpl.demandeDao] Getter 
//	 * @author BELDI Jamel on : 20 avr. 2014  11:52:45
//	 * @return the demandeDao
//	 */
//	public DemandeDao getDemandeDao() {
//		return demandeDao;
//	}
//
//
//
//
//	/**
//	 * [DemandeServiceImpl.demandeDao] Setter 
//	 * @author BELDI Jamel on : 20 avr. 2014  11:52:45
//	 * @param demandeDao the demandeDao to set
//	 */
//	public void setDemandeDao(DemandeDao demandeDao) {
//		this.demandeDao = demandeDao;
//	}
//
//
//
//	/**
//	 * [DemandeServiceImpl.mapper] Getter 
//	 * @author BELDI Jamel on : 20 avr. 2014  11:58:41
//	 * @return the mapper
//	 */
//	public org.dozer.DozerBeanMapper getMapper() {
//		return mapper;
//	}
//
//
//
//
//	/**
//	 * [DemandeServiceImpl.mapper] Setter 
//	 * @author BELDI Jamel on : 20 avr. 2014  11:58:41
//	 * @param mapper the mapper to set
//	 */
//	public void setMapper(org.dozer.DozerBeanMapper mapper) {
//		this.mapper = mapper;
//	}
//
//
//
//
//	/**
//	 * [DemandeServiceImpl.demandeI18nDao] Getter 
//	 * @author BELDI Jamel on : 24 avr. 2014  10:54:13
//	 * @return the demandeI18nDao
//	 */
//	public DemandeI18nDao getDemandeI18nDao() {
//		return demandeI18nDao;
//	}
//
//
//
//
//	/**
//	 * [DemandeServiceImpl.demandeI18nDao] Setter 
//	 * @author BELDI Jamel on : 24 avr. 2014  10:54:13
//	 * @param demandeI18nDao the demandeI18nDao to set
//	 */
//	public void setDemandeI18nDao(DemandeI18nDao demandeI18nDao) {
//		this.demandeI18nDao = demandeI18nDao;
//	}
//
//
//
//	
//
//	/**
//	 * [DemandeServiceImpl.offreFormationService] Getter 
//	 * @author BELDI Jamel on : 28 avr. 2014  17:51:48
//	 * @return the offreFormationService
//	 */
//	public OffreFormationService getOffreFormationService() {
//		return offreFormationService;
//	}
//
//
//
//
//	/**
//	 * [DemandeServiceImpl.offreFormationService] Setter 
//	 * @author BELDI Jamel on : 28 avr. 2014  17:51:48
//	 * @param offreFormationService the offreFormationService to set
//	 */
//	public void setOffreFormationService(OffreFormationService offreFormationService) {
//		this.offreFormationService = offreFormationService;
//	}
//
//
//
//	
//
//	/**
//	 * [DemandeServiceImpl.situationEntiteDao] Getter 
//	 * @author BELDI Jamel on : 4 mai 2014  11:59:03
//	 * @return the situationEntiteDao
//	 */
//	public SituationEntiteDao getSituationEntiteDao() {
//		return situationEntiteDao;
//	}
//
//
//
//
//	/**
//	 * [DemandeServiceImpl.situationEntiteDao] Setter 
//	 * @author BELDI Jamel on : 4 mai 2014  11:59:03
//	 * @param situationEntiteDao the situationEntiteDao to set
//	 */
//	public void setSituationEntiteDao(SituationEntiteDao situationEntiteDao) {
//		this.situationEntiteDao = situationEntiteDao;
//	}
//
//	@Override
//	public HashMap<String, DemandeI18nDto> findListDemandei18nByDemande(DemandeDto demandeDto) {
//		HashMap<String, DemandeI18nDto> mapDemandeI18n = new HashMap<String, DemandeI18nDto>();
//		try {
//			List<DemandeI18n> demandeI18nList = demandeI18nDao.findListDemandeI18nByDemande(demandeDto.getId());
//			List<SituationI18n>  situationI18ns = situationEntiteDao.findByIdSituationEntite(demandeDto.getIdSituation());
//			if (demandeI18nList != null) {				
//				for (DemandeI18n currentDemandeI18n : demandeI18nList) {
//					DemandeI18nDto demandeI18nDto = new DemandeI18nDto();
//					mapper.map(currentDemandeI18n, demandeI18nDto);
//					// Situation OffreFormationI18n
//					if(situationI18ns!=null && !situationI18ns.isEmpty()){
//					for (SituationI18n situationI18n : situationI18ns) {
//						
//						SituationI18nDto situationI18nDto = mapper.map(situationI18n, SituationI18nDto.class);
//						
//						if(demandeI18nDto.getLangue().equals(situationI18nDto.getLangue())) {
//							
//							demandeI18nDto.setLibelleSituation(situationI18nDto.getLibelle());
//						}
//						
//					}
//					}
//					mapDemandeI18n.put(demandeI18nDto.getLangue(), demandeI18nDto);
//				}
//				return mapDemandeI18n;
//			}
//		} catch (RuntimeException e) {
//			log.error("get demandeI18n list failed", e);
//			throw e;
//		}
//
//		return null;
//	}
//
//	@Override
//	public List<DemandeI18nDto> findAdvanced(DemandeI18nDto demandeI18nDto) {
//		List<DemandeI18nDto> result = new ArrayList<>();
//	
//		try {
//			Demande demandeSearch = mapper.map(demandeI18nDto, Demande.class);
//			List<Demande> demandeResultList = demandeDao.findAdvanced(demandeSearch);
//			if (demandeResultList != null) {				
//				for (Demande demande : demandeResultList) {
//					DemandeI18n demandeI18nSearch = mapper.map(demandeI18nDto, DemandeI18n.class);
//					demandeI18nSearch.setDemande(demande);
//					List<DemandeI18n> demandeI18nResultList = demandeI18nDao.findAdvanced(demandeI18nSearch);
//					OffreFormationDto ofDto=offreFormationService.findById(demande.getOffreFormation().getId());
//					OffreFormationI18nDto ofI18nDto = offreFormationService.findI18nByOfId(demande.getOffreFormation().getId(), demandeI18nDto.getLangue()) ;
////					OffreFormationI18nDto ofI18nDto =	new OffreFormationI18nDto();
////					if(ofDto!=null && ofDto.getI18nDtos()!=null ){
////						 ofI18nDto = ofDto.getI18nDtos().get(demandeI18nDto.getLangue());
////					}
//					if (demandeI18nResultList != null) {				
//						for (DemandeI18n demandeI18n : demandeI18nResultList) {
//							DemandeI18nDto element = mapper.map(demande, DemandeI18nDto.class);
//							 mapper.map(ofDto, element);
//							 mapper.map(ofI18nDto, element);
//							 mapper.map(demandeI18n, element);
//							result.add(element);
//						}
//				}
//				
//			}
//			}
//			return result;
//		} catch (RuntimeException e) {
//			log.error("findAdvanced failed", e);
//			throw e;
//		}
//
//		
//	}
//
//	@Override
//	public  TypeDemandeDto findTypeDemandeByCode(String codeType) {
//		
//			try {
//			
//					TypeDemande typeDemande = typeDemandeDao.findByCode(codeType);
//					return mapper.map(typeDemande, TypeDemandeDto.class);
//
//			} catch (RuntimeException e) {
//	
//					log.error("findTypeDemandeByCode failed", e);
//					throw e;
//			}
//		
//	}
//	
//	/**
//	 * [DemandeServiceImpl.findListDemandsByTypeBySituaion] Method 
//	 * overridden By :  @author  Rafik LAIB  
//	 * On : 18 mai 2014  14:01:04
//	 * @param codeTypeDemande
//	 * @param codeSituation
//	 * @param idDemande
//	 * @return
//	 */
//	@Override
//	public  List<DemandeI18nDto> findListDemandsByTypeBySituaion(String codeTypeDemande, String codeSituation,  Integer idDemande) {
//		
//		try {
//			
//					List<DemandeI18n> demandeI18ns = demandeI18nDao.findListDemandsByTypeBySituaion(codeTypeDemande, codeSituation, idDemande);
//					List<DemandeI18nDto> demandeI18nDtos = new ArrayList<DemandeI18nDto>();
//					for (DemandeI18n demandeI18n : demandeI18ns) {
//						
//						  DemandeI18nDto demandeI18nDto  = new DemandeI18nDto();
//						  Demande demande= demandeDao.findById(demandeI18n.getDemande().getId());
//						  OffreFormationDto ofDto=offreFormationService.findById(demande.getOffreFormation().getId());
//						  
//						  OffreFormationI18nDto ofI18nDto = offreFormationService.findI18nByOfId(demande.getOffreFormation().getId(), demandeI18nDto.getLangue()) ;
////						  if(ofDto!=null && ofDto.getI18nDtos()!=null ){
////							
////							  		ofI18nDto = ofDto.getI18nDtos().get(demandeI18n.getLangue());
////							}
////							
//						  mapper.map(demande, demandeI18nDto);
//						  mapper.map(ofDto, demandeI18nDto);
//						  mapper.map(ofI18nDto, demandeI18nDto);
//						  mapper.map(demandeI18n, demandeI18nDto);
//						  demandeI18nDtos.add(demandeI18nDto);
//					}
//					return demandeI18nDtos;		
//			
//		} catch (RuntimeException e) {
//			
//			log.error("findListDemandsByTypeBySituaion failed", e);
//			throw e;
//		}
//		
//	}
//	
//	
//}
