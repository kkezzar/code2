/**
 * [dz.gov.mesrs.sii.scolformation.web.jsf.bean.offreformation.of.OfSupportBean.java] 
 * @author Rafik LAIB (Mac) on : 7 janv. 2014  10:16:28
 */
package dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.of;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.OffreFormationDetail;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OffreFormationDetailContentDto;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OffreFormationDetailContentService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OffreFormationDetailService;
import dz.gov.mesrs.sii.fve.web.jsf.bean.SessionBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.OfConstants;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefAffectDomLmdEtabDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefContratDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefDomaineLMDDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefFiliereLmdDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefIndividuDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefPartenaireDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefSpecialiteLmdDto;
import dz.gov.mesrs.sii.referentiel.business.service.NomenclatureService;
import dz.gov.mesrs.sii.referentiel.business.service.RefContratService;
import dz.gov.mesrs.sii.referentiel.business.service.RefDomaineLMDService;
import dz.gov.mesrs.sii.referentiel.business.service.RefFiliereLmdService;
import dz.gov.mesrs.sii.referentiel.business.service.RefIndividuService;
import dz.gov.mesrs.sii.referentiel.business.service.RefPartenaireService;
import dz.gov.mesrs.sii.referentiel.business.service.RefSpecialiteLmdService;


/**
 * @author Rafik LAIB (Mac)  on : 7 janv. 2014  10:16:28
 */
@ManagedBean(name = "ofSupportBean")
@ViewScoped
public class OfSupportBean  implements Serializable {

	/**
	 * serialVersionUID 
	 * @author rlaib  on : 8 sept. 2014  08:53:01
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * [OfSupportBean.OfSupportBean()] Constructor
	 * @author rlaib  on : 29 janv. 2014  15:47:35	
	 */
	public OfSupportBean() {


	}

	// ===================================================================  
	// Web Services for Lists
	// ===================================================================
	
	// ===================================================================  
	// Beans services
	// ===================================================================
		
	@ManagedProperty(value = "#{ofSessionBean}")
	private SessionBean ofSessionBean;
	
	@ManagedProperty(value = "#{refIndividuServiceImpl}")
	private RefIndividuService refIndividuService;
	
	@ManagedProperty(value = "#{nomenclatureServiceImpl}")
	private NomenclatureService nomenclatureService;
	
	@ManagedProperty(value = "#{refContratServiceImpl}")
	private RefContratService refContratService;
	
	@ManagedProperty(value = "#{refPartenaireServiceImpl}")
	private RefPartenaireService refPartenaireService;

	@ManagedProperty(value = "#{refDomaineLMDServiceImpl}")
	private RefDomaineLMDService refDomaineLMDService;
	
	@ManagedProperty(value = "#{refFiliereLmdServiceImpl}")
	private RefFiliereLmdService refFiliereLmdService;
	
	@ManagedProperty(value = "#{refSpecialiteLmdServiceImpl}")
	private RefSpecialiteLmdService refSpecialiteLmdService;
	
	// ===================================================================  
	// Services for Trees
	// ===================================================================
	
	@ManagedProperty(value="#{offreFormationDetailService}")
	private OffreFormationDetailService offreFormationDetailService;
	
	@ManagedProperty(value="#{offreFormationDetailContentService}")
	private OffreFormationDetailContentService offreFormationDetailContentService;
	
	// ===================================================================  
	// Lists data
	// ===================================================================
	
	@ManagedProperty(value = "#{nomenclatureLmdDictionary}")
	private Map<String, List<NomenclatureDto>> nomenclatureLmdDictionary;
	
	@ManagedProperty(value = "#{nomenclatureDomainesFilieres}")
	private Map<String, List<NomenclatureDto>> nomenclatureDomainesFilieres;

	@ManagedProperty(value = "#{nomenclatureFilieresSpecialites}")
	private Map<String, List<NomenclatureDto>> nomenclatureFilieresSpecialites;
	
	// ===================================================================  
	// Getters / Setters for Services for Lists
	// ===================================================================
	
	/**
	 * [OfSupportBean.ofSessionBean] Getter 
	 * @author rlaib on : 5 fÃƒÂ©vr. 2014  12:00:59
	 * @return the ofSessionBean
	 */
	public SessionBean getOfSessionBean() {
		return ofSessionBean;
	}

	/**
	 * [OfSupportBean.refSpecialiteLmdService] Getter 
	 * @author rlaib on : 20 nov. 2014  13:53:42
	 * @return the refSpecialiteLmdService
	 */
	public RefSpecialiteLmdService getRefSpecialiteLmdService() {
		return refSpecialiteLmdService;
	}

	/**
	 * [OfSupportBean.refSpecialiteLmdService] Setter 
	 * @author rlaib on : 20 nov. 2014  13:53:42
	 * @param refSpecialiteLmdService the refSpecialiteLmdService to set
	 */
	public void setRefSpecialiteLmdService(
			RefSpecialiteLmdService refSpecialiteLmdService) {
		this.refSpecialiteLmdService = refSpecialiteLmdService;
	}

	/**
	 * [OfSupportBean.refFiliereLmdService] Getter 
	 * @author rlaib on : 20 nov. 2014  13:52:15
	 * @return the refFiliereLmdService
	 */
	public RefFiliereLmdService getRefFiliereLmdService() {
		return refFiliereLmdService;
	}

	/**
	 * [OfSupportBean.refFiliereLmdService] Setter 
	 * @author rlaib on : 20 nov. 2014  13:52:15
	 * @param refFiliereLmdService the refFiliereLmdService to set
	 */
	public void setRefFiliereLmdService(RefFiliereLmdService refFiliereLmdService) {
		this.refFiliereLmdService = refFiliereLmdService;
	}

	/**
	 * [OfSupportBean.refDomaineLMDService] Getter 
	 * @author rlaib on : 20 nov. 2014  13:50:59
	 * @return the refDomaineLMDService
	 */
	public RefDomaineLMDService getRefDomaineLMDService() {
		return refDomaineLMDService;
	}

	/**
	 * [OfSupportBean.refDomaineLMDService] Setter 
	 * @author rlaib on : 20 nov. 2014  13:50:59
	 * @param refDomaineLMDService the refDomaineLMDService to set
	 */
	public void setRefDomaineLMDService(RefDomaineLMDService refDomaineLMDService) {
		this.refDomaineLMDService = refDomaineLMDService;
	}

	/**
	 * [OfSupportBean.refPartenaireService] Getter 
	 * @author rlaib on : 20 nov. 2014  13:49:28
	 * @return the refPartenaireService
	 */
	public RefPartenaireService getRefPartenaireService() {
		return refPartenaireService;
	}

	/**
	 * [OfSupportBean.refPartenaireService] Setter 
	 * @author rlaib on : 20 nov. 2014  13:49:28
	 * @param refPartenaireService the refPartenaireService to set
	 */
	public void setRefPartenaireService(RefPartenaireService refPartenaireService) {
		this.refPartenaireService = refPartenaireService;
	}

	/**
	 * [OfSupportBean.refContratService] Getter 
	 * @author rlaib on : 20 nov. 2014  13:47:01
	 * @return the refContratService
	 */
	public RefContratService getRefContratService() {
		return refContratService;
	}

	/**
	 * [OfSupportBean.refContratService] Setter 
	 * @author rlaib on : 20 nov. 2014  13:47:01
	 * @param refContratService the refContratService to set
	 */
	public void setRefContratService(RefContratService refContratService) {
		this.refContratService = refContratService;
	}

	/**
	 * [OfSupportBean.refIndividuService] Getter 
	 * @author rlaib on : 20 nov. 2014  13:44:35
	 * @return the refIndividuService
	 */
	public RefIndividuService getRefIndividuService() {
		return refIndividuService;
	}

	/**
	 * [OfSupportBean.refIndividuService] Setter 
	 * @author rlaib on : 20 nov. 2014  13:44:35
	 * @param refIndividuService the refIndividuService to set
	 */
	public void setRefIndividuService(RefIndividuService refIndividuService) {
		this.refIndividuService = refIndividuService;
	}

	/**
	 * [OfSupportBean.nomenclatureService] Getter 
	 * @author rlaib on : 20 nov. 2014  13:44:35
	 * @return the nomenclatureService
	 */
	public NomenclatureService getNomenclatureService() {
		return nomenclatureService;
	}

	/**
	 * [OfSupportBean.nomenclatureService] Setter 
	 * @author rlaib on : 20 nov. 2014  13:44:35
	 * @param nomenclatureService the nomenclatureService to set
	 */
	public void setNomenclatureService(NomenclatureService nomenclatureService) {
		this.nomenclatureService = nomenclatureService;
	}

	/**
	 * [OfSupportBean.ofSessionBean] Setter 
	 * @author rlaib on : 5 fÃƒÂ©vr. 2014  12:00:59
	 * @param ofSessionBean the ofSessionBean to set
	 */
	public void setOfSessionBean(SessionBean ofSessionBean) {
		this.ofSessionBean = ofSessionBean;
	}
	
	/**
	 * [OfSupportBean.offreFormationDetailService] Getter 
	 * @author rlaib on : 6 fevr. 2014  15:20:20
	 * @return the offreFormationDetailService
	 */
	public OffreFormationDetailService getOffreFormationDetailService() {
		return offreFormationDetailService;
	}

	/**
	 * [OfSupportBean.offreFormationDetailService] Setter 
	 * @author rlaib on : 6 fevr. 2014  15:20:20
	 * @param offreFormationDetailService the offreFormationDetailService to set
	 */
	public void setOffreFormationDetailService(OffreFormationDetailService offreFormationDetailService) {
		this.offreFormationDetailService = offreFormationDetailService;
	}

	/**
	 * [OfSupportBean.offreFormationDetailContentService] Getter 
	 * @author rlaib on : 20 mars 2014  12:44:06
	 * @return the offreFormationDetailContentService
	 */
	public OffreFormationDetailContentService getOffreFormationDetailContentService() {
		return offreFormationDetailContentService;
	}

	/**
	 * [OfSupportBean.offreFormationDetailContentService] Setter 
	 * @author rlaib on : 20 mars 2014  12:44:06
	 * @param offreFormationDetailContentService the offreFormationDetailContentService to set
	 */
	public void setOffreFormationDetailContentService(OffreFormationDetailContentService offreFormationDetailContentService) {
		this.offreFormationDetailContentService = offreFormationDetailContentService;
	}

	// ===================================================================  
	// Getters / Setters Lists Data
	// ===================================================================
	
	/**
	 * [OfSupportBean.nomenclatureLmdDictionary] Getter 
	 * @author rlaib on : 29 janv. 2014  15:43:49
	 * @return the nomenclatureLmdDictionary
	 */
	public Map<String, List<NomenclatureDto>> getNomenclatureLmdDictionary() {

				if(nomenclatureLmdDictionary == null){
		
					nomenclatureLmdDictionary = new  HashMap<>();
					nomenclatureLmdDictionary.clear();
					
					List<NomenclatureDto> listAllLmdNc = getAllLmdNomenclatures();
					
					List<NomenclatureDto> listDomaines = new ArrayList<NomenclatureDto>();
					List<NomenclatureDto> listFilieres = new ArrayList<NomenclatureDto>();
					List<NomenclatureDto> listSpecialites = new ArrayList<NomenclatureDto>();
					List<NomenclatureDto> listTypesFormation = new ArrayList<NomenclatureDto>();
					List<NomenclatureDto> listCyclesFormation = new ArrayList<NomenclatureDto>();
					List<NomenclatureDto> listVocation = new ArrayList<NomenclatureDto>();
					List<NomenclatureDto> listTypesIndividu = new ArrayList<NomenclatureDto>();
					
					try{
									for (NomenclatureDto nc : listAllLmdNc) {
										
												switch (nc.getNcName()) {
												
														// Domaines de formation
														case OfConstants.NC_NAME_DOMAINES:
															listDomaines.add(nc);
															break;
															
														// FiliÃ¨res de formation	
														case OfConstants.NC_NAME_FILIERES:
															listFilieres.add(nc);
															break;
															
														// Specialites de formation	
														case OfConstants.NC_NAME_SPECIALITES:
															listSpecialites.add(nc);
															break;
															
														// Types de formation	
														case OfConstants.NC_NAME_TYPE_FORMATION:
															listTypesFormation.add(nc);
															break;
															
														// Cycles de formation	
														case OfConstants.NC_NAME_CYCLE:
															listCyclesFormation.add(nc);
															break;
														
														// Vocations de formation	
														case OfConstants.NC_NAME_VOCATION:
															listVocation.add(nc);
															break;
															
														default:
															break;
												}
										
									}
									
									nomenclatureLmdDictionary.put(OfConstants.NC_NAME_DOMAINES, listDomaines);
									nomenclatureLmdDictionary.put(OfConstants.NC_NAME_FILIERES, listFilieres);
									nomenclatureLmdDictionary.put(OfConstants.NC_NAME_SPECIALITES, 	listSpecialites);
									nomenclatureLmdDictionary.put(OfConstants.NC_NAME_TYPE_FORMATION,  listTypesFormation);
									nomenclatureLmdDictionary.put(OfConstants.NC_NAME_CYCLE, listCyclesFormation);
									nomenclatureLmdDictionary.put(OfConstants.NC_NAME_VOCATION,  listVocation);
									
									// Type individus (role membre equipe) de formation	
									listTypesIndividu = nomenclatureService.findByName(OfConstants.NC_NAME_ROLE_EQUIPE_OF);
									nomenclatureLmdDictionary.put(OfConstants.NC_NAME_ROLE_EQUIPE_OF,  listTypesIndividu);
								
						//fillHardCodedList();
						//getLmdNomenclaturesWS();
								
					}
					catch(Exception e) {
					
						fillHardCodedList();
					}
					
				}
				return nomenclatureLmdDictionary;
	}
		
	public NomenclatureDto getNomenclatureObjectByCodeByType(String ncName, String objectCode){
		
		NomenclatureDto objectToReturn = new NomenclatureDto();
		try  {
					List<NomenclatureDto> ncList = nomenclatureService.findByName(ncName);
					for (NomenclatureDto item : ncList) {
						if(item.getCode().equals(objectCode))
							return item;
					}
					
			
		}
		catch (Exception e) {
			
			return null;
		}
		return objectToReturn;
	}
	
	/**
	 * [OfSupportBean.nomenclatureLmdDictionary] Setter 
	 * @author rlaib on : 29 janv. 2014  15:43:49
	 * @param nomenclatureLmdDictionary the nomenclatureLmdDictionary to set
	 */
	public void setNomenclatureLmdDictionary(
			Map<String, List<NomenclatureDto>> nomenclatureLmdDictionary) {
		this.nomenclatureLmdDictionary = nomenclatureLmdDictionary;
	}

	/**
	 * [OfSupportBean.nomenclatureDomainesFilieres] Getter 
	 * @author rlaib on : 5 fÃƒÂ©vr. 2014  14:58:46
	 * @return the nomenclatureDomainesFilieres
	 */
	public Map<String, List<NomenclatureDto>> getNomenclatureDomainesFilieres() {
		
		if(nomenclatureDomainesFilieres == null) {
			
			nomenclatureDomainesFilieres = new  HashMap<>();
			nomenclatureDomainesFilieres.clear();

			try{
				
		        List<NomenclatureDto> list1 =nomenclatureLmdDictionary.get(OfConstants.NC_NAME_DOMAINES);
				List<NomenclatureDto> list2 = nomenclatureLmdDictionary.get(OfConstants.NC_NAME_FILIERES);
					
				// Dispatching Filieres par Domaine
				for (NomenclatureDto dom : list1) {
					
					//System.err.println("[R2-SII-MESRS] : [ID domaine  = " +dom.getId()+"]");
					
					List<NomenclatureDto> listFilieresDomaine = new ArrayList<NomenclatureDto>();
					for (NomenclatureDto fil : list2) {
						
						   // System.err.println("[R2-SII-MESRS] : [ID domaine  = " +dom.getId()+ "]  [ID Filiere =  "+ fil.getId() +"] [Domaine ref = "+ fil.getIdReference() +"]  [Libelle filiere = "+ fil.getLibelleLongFr()+"]");
						    
						if(dom.getId().equals(fil.getIdReference())){
							listFilieresDomaine.add(fil);
							// System.err.println("[R2-SII-MESRS] : [ID domaine  = " +dom.getId()+ "]  [ID Filiere =  "+ fil.getId() +"] [Domaine ref = "+ fil.getIdReference() +"]  [Libelle filiere = "+ fil.getLibelleLongFr() + "] OK ");
						}
						
					}
					
					nomenclatureDomainesFilieres.put(dom.getCode(), listFilieresDomaine);
					
				}
					
			}
			catch(Exception e) {
			
				
			}
		}
		return nomenclatureDomainesFilieres;
	}

	/**
	 * [OfSupportBean.nomenclatureDomainesFilieres] Setter 
	 * @author rlaib on : 5 fÃƒÂ©vr. 2014  14:58:46
	 * @param nomenclatureDomainesFilieres the nomenclatureDomainesFilieres to set
	 */
	public void setNomenclatureDomainesFilieres(
			Map<String, List<NomenclatureDto>> nomenclatureDomainesFilieres) {
		this.nomenclatureDomainesFilieres = nomenclatureDomainesFilieres;
	}

	/**
	 * [OfSupportBean.nomenclatureFilieresSpecialites] Getter 
	 * @author rlaib on : 5 fÃƒÂ©vr. 2014  15:20:55
	 * @return the nomenclatureFilieresSpecialites
	 */
	public Map<String, List<NomenclatureDto>> getNomenclatureFilieresSpecialites() {
		
			if(nomenclatureFilieresSpecialites == null) {
			
					nomenclatureFilieresSpecialites = new  HashMap<>();
					nomenclatureFilieresSpecialites.clear();
		
					try{
						
				        List<NomenclatureDto> list1 =nomenclatureLmdDictionary.get(OfConstants.NC_NAME_FILIERES);
						List<NomenclatureDto> list2 = nomenclatureLmdDictionary.get(OfConstants.NC_NAME_SPECIALITES);
							
						// Dispatching Specialites par Filieres
						for (NomenclatureDto  fil : list1) {
							
							//System.err.println("[R2-SII-MESRS] : [ID Filiere  = " +fil.getId()+"]");
							
							List<NomenclatureDto> listFilieresSpecialites = new ArrayList<NomenclatureDto>();
							for (NomenclatureDto spec : list2) {
								
								//System.err.println("[R2-SII-MESRS] : [ID Filiere  = " +fil.getId()+ "]  [ID Specialite =  "+ spec.getId() +"]  [Filiere ref = "+ spec.getIdReference() +"]  [Libelle Specialite = "+ spec.getLibelleLongFr()+"]");
								if(fil.getId().equals(spec.getIdReference())){
									listFilieresSpecialites.add(spec);
									//System.err.println("[R2-SII-MESRS] : [ID Filiere  = " +fil.getId()+ "]  [ID Specialite =  "+ spec.getId() +"]  [Filiere ref = "+ spec.getIdReference() +"]  [Libelle Specialite = "+ spec.getLibelleLongFr()+ "] OK");
								}
								
							}
							
							nomenclatureFilieresSpecialites.put(fil.getCode(), listFilieresSpecialites);
							
						}
							
					}
					catch(Exception e) {
					
						
					}
				}
				return nomenclatureFilieresSpecialites;
	}

	/**
	 * [OfSupportBean.nomenclatureFilieresSpecialites] Setter 
	 * @author rlaib on : 5 fÃƒÂ©vr. 2014  15:20:55
	 * @param nomenclatureFilieresSpecialites the nomenclatureFilieresSpecialites to set
	 */
	public void setNomenclatureFilieresSpecialites(Map<String, List<NomenclatureDto>> nomenclatureFilieresSpecialites) {
		this.nomenclatureFilieresSpecialites = nomenclatureFilieresSpecialites;
	}

	// ===================================================================  
	// Helper Functions
	// ===================================================================
	
	/**
	 * [OfSupportBean.fillHardCodedList] Method 
	 * @author rlaib  on : 4 fÃƒÂ©vr. 2014  10:00:54
	 */
	private void fillHardCodedList() {
		
		// Adding some DOMAINES
					List<NomenclatureDto> listDomaines = new ArrayList<>();
					listDomaines.clear();
					listDomaines.add(createOneNomenclatureDto(57,"ST", "Science et Technologies","Ø§Ù„Ø¹Ù„ÙˆÙ… ÙˆØ§Ù„ØªÙƒÙ†ÙˆÙ„ÙˆØ¬ÙŠØ§", null, null));
					listDomaines.add(createOneNomenclatureDto(58,"SM", "Sciences de la Matiere","Ø¹Ù„ÙˆÙ… Ø§Ù„Ù…ÙˆØ§Ø¯", null, null));
					listDomaines.add(createOneNomenclatureDto(59,"MI", "Mathematiques et Informatique","Ø§Ù„Ø±ÙŠØ§Ø¶ÙŠØ§Øª ÙˆØ¹Ù„ÙˆÙ… Ø§Ù„Ø­Ø§Ø³Ø¨ Ø§Ù„Ø¢Ù„ÙŠ", null, null));
					listDomaines.add(createOneNomenclatureDto(60,"SNV", "Sciences de la Nature et de la Vie","Ø§Ù„Ø¹Ù„ÙˆÙ… Ø§Ù„Ø·Ø¨ÙŠØ¹ÙŠØ© ÙˆØ§Ù„Ø­ÙŠØ§Ø©", null, null));
					listDomaines.add(createOneNomenclatureDto(62,"STU", "Sciences de la Nature et de l'Univers","Ø§Ù„Ø¹Ù„ÙˆÙ… Ø§Ù„ØªØ¬Ø§Ø±ÙŠØ© ÙˆØ§Ù„Ø§Ù‚ØªØµØ§Ø¯ØŒ ÙˆØ§Ù„Ø¥Ø¯Ø§Ø±Ø©", null, null));
					listDomaines.add(createOneNomenclatureDto(64,"SEGS", "Sciences Econimiques, de la Gestion et Sciences Commerciales", "Ø§Ù„Ø¹Ù„ÙˆÙ… Ø§Ù„ØªØ¬Ø§Ø±ÙŠØ© ÙˆØ§Ù„Ø§Ù‚ØªØµØ§Ø¯ØŒ ÙˆØ§Ù„Ø¥Ø¯Ø§Ø±Ø©", null, null));
					listDomaines.add(createOneNomenclatureDto(65,"DSP", "Droit et Sciences Politiques", "Ø§Ù„Ù‚Ø§Ù†ÙˆÙ† ÙˆØ§Ù„Ø¹Ù„ÙˆÙ… Ø§Ù„Ø³ÙŠØ§Ø³ÙŠØ©",  null, null));
					listDomaines.add(createOneNomenclatureDto(66,"LLE", "Lettres et Langues Etrangeres", "Ø§Ù„Ø£Ø¯Ø¨ ÙˆØ§Ù„Ù„ØºØ§Øª Ø§Ù„Ø£Ø¬Ù†Ø¨ÙŠØ©" , null, null));
					listDomaines.add(createOneNomenclatureDto(70,"SHS", "Sciences Humaines et Sociales", "Ø§Ù„Ø¹Ù„ÙˆÙ… Ø§Ù„Ø¥Ù†Ø³Ø§Ù†ÙŠØ© ÙˆØ§Ù„Ø¹Ù„ÙˆÙ… Ø§Ù„Ø§Ø¬ØªÙ…Ø§Ø¹ÙŠØ©", null, null));
					listDomaines.add(createOneNomenclatureDto(71,"STAPS", "Sciences Techniques des Activites Physiques et Sportives", "Ø§Ù„Ø¹Ù„ÙˆÙ… Ø§Ù„ØªÙ‚Ù†ÙŠØ© Ù…Ù† Ø§Ù„Ù†Ø´Ø§Ø· Ø§Ù„Ø¨Ø¯Ù†ÙŠ ÙˆØ§Ù„Ø±ÙŠØ§Ø¶Ø©", null, null));
					listDomaines.add(createOneNomenclatureDto(72,"ARTS", "ARTS", "Ø§Ù„Ù�Ù†ÙˆÙ†",  null, null));
					listDomaines.add(createOneNomenclatureDto(73,"LLA", "Langues et LittÃƒÂ©ratures Arabes", "Ù„Ù„ØºØ§Øª ÙˆØ§Ù„Ø£Ø¯Ø¨ Ø§Ù„Ø¹Ø±Ø¨", null, null));
					listDomaines.add(createOneNomenclatureDto(74,"LCA", "Langues et Culture Amazighes", "Ø§Ù„Ù„ØºØ§Øª ÙˆØ§Ù„Ø«Ù‚Ø§Ù�Ø© Ø§Ù„Ø£Ù…Ø§Ø²ÙŠØºÙŠØ©", null, null));
					
					nomenclatureLmdDictionary.put(OfConstants.NC_NAME_DOMAINES, listDomaines);
					
					// Adding some FILIERES
					List<NomenclatureDto> listFilieres = new ArrayList<>();
					listFilieres.clear();
				
					listFilieres.add(createOneNomenclatureDto(141,"EC1", "TECHNOLOGIE","ØªÙƒÙ†ÙˆÙ„ÙˆØ¬ÙŠØ§", 57, "Science et Technologies"));
					listFilieres.add(createOneNomenclatureDto(76,"12", "ARCHITECTURE ET URBANISME","Ù‡Ù†Ø¯Ø³Ø© Ù…Ø¹Ù…Ø§Ø±ÙŠØ© ÙˆØ¹Ù…Ø±Ø§Ù†", 57, "Science et Technologies"));
					listFilieres.add(createOneNomenclatureDto(77,"13", "MINES","Ù…Ù†Ø§Ø¬Ù…", 58, "Science et Technologies"));
					listFilieres.add(createOneNomenclatureDto(78,"51", "SCIENCES DE LA TERRE ET DE L UNIVERS", "Ø¹Ù„ÙˆÙ… Ø§Ù„Ø£Ø±Ø¶ ÙˆØ§Ù„ÙƒÙˆÙ†", 58, "Science et Technologies"));
					listFilieres.add(createOneNomenclatureDto(80,"52", "GESTION DES TECHNIQUES URBAINES", "ØªØ³ÙŠÙŠØ± Ø§Ù„ØªÙ‚Ù†ÙŠØ§Øª Ø§Ù„Ø­Ø¶Ø±ÙŠØ©", 58, "Science et Technologies"));
					
					nomenclatureLmdDictionary.put(OfConstants.NC_NAME_FILIERES, listFilieres);
					
					// Adding some SPECIALITES
					List<NomenclatureDto> listSpecilites = new ArrayList<>();
					listSpecilites.clear();
					listSpecilites.add(createOneNomenclatureDto(215,"THRM", "THERMIQUE", "Ø­Ø±Ø§Ø±Ø©", 141, "TECHNOLOGIE"));
					listSpecilites.add(createOneNomenclatureDto(216,"MDF", "MDF", "MDF", 141, "TECHNOLOGIE"));
					listSpecilites.add(createOneNomenclatureDto(217, "MTR", "MOTEUR", "Ù…Ø­Ø±Ùƒ", 141, "TECHNOLOGIE"));
					listSpecilites.add(createOneNomenclatureDto(218, "PSNC", "ELECTRONIQUE DE PUISSANCE","Ø¥Ù„ÙƒØªØ±ÙˆÙ†ÙŠØ§Øª Ø§Ù„Ù‚ÙˆÙ‰", 77, "TECHNOLOGIE"));
					listSpecilites.add(createOneNomenclatureDto(219, "SYST", "Micro Systemes", "Ù…Ø§ÙŠÙƒØ±ÙˆØ³ÙŠØ³ØªÙ…Ø²", 77, "TECHNOLOGIE"));
					listSpecilites.add(createOneNomenclatureDto(220, "INDS", "Informatique Industrielles", " Ø§Ø¹Ù„Ø§Ù… Ø¢Ù„ÙŠ ØµÙ†Ø§Ø¹ÙŠ", 77, "TECHNOLOGIE"));
					listSpecilites.add(createOneNomenclatureDto(221, "FRD", "Froid", "ØªØ¨Ø±ÙŠØ¯",  76, "TECHNOLOGIE"));
					listSpecilites.add(createOneNomenclatureDto(222, "CLMT", "CLIMATISATION", "ØªÙƒÙŠÙŠÙ� Ø§Ù„Ù‡ÙˆØ§Ø¡", 76, "TECHNOLOGIE"));
					listSpecilites.add(createOneNomenclatureDto(223, "CRYG", "Cryogenie", "Ù�ÙŠØ²ÙŠØ§Ø¡ Ø¯Ø±Ø¬Ø§Øª Ø§Ù„Ø­Ø±Ø§Ø±Ø© Ø§Ù„Ù…ØªØ¯Ù†ÙŠØ©", 76, "TECHNOLOGIE"));
					
				    nomenclatureLmdDictionary.put(OfConstants.NC_NAME_SPECIALITES, listSpecilites);
				    
					// Adding TYPES formation
				    List<NomenclatureDto> listTypesFormation= new ArrayList<>();
				    listTypesFormation.clear();
				    listTypesFormation.add(createOneNomenclatureDto(181, "LMD", "LMD", "", null, null));
				    listTypesFormation.add(createOneNomenclatureDto(182, "CLASS", "CLASSIQUE", "", null, null));
					nomenclatureLmdDictionary.put(OfConstants.NC_NAME_TYPE_FORMATION, listTypesFormation);
				
					// Adding CYCLES
					List<NomenclatureDto> listCycles= new ArrayList<>();
					listCycles.clear();
					
					listCycles.add(createOneNomenclatureDto(212, "L", "Licence", "", null, null));
					listCycles.add(createOneNomenclatureDto(213, "M", "Master", "" ,null, null));
					listCycles.add(createOneNomenclatureDto(214, "D", "Doctorat", "", null, null));
					nomenclatureLmdDictionary.put(OfConstants.NC_NAME_CYCLE, listCycles);
					
					// Adding VOCATIONS
					List<NomenclatureDto> listVocations = new ArrayList<>();
					listVocations.clear();
					listVocations.add(createOneNomenclatureDto(190, "Acad", "Academique", "", null, null));
					listVocations.add(createOneNomenclatureDto(191, "Pro", "Professionnalisante", "", null, null));
			        nomenclatureLmdDictionary.put(OfConstants.NC_NAME_VOCATION, listVocations);
	}
	
	private NomenclatureDto createOneNomenclatureDto(Integer id, String code, String libelleFr,  String libelleAr, Integer idReference, String libelleLongFrReference){

		NomenclatureDto nc = new NomenclatureDto();
		nc.setId(id);
		nc.setCode(code);
		nc.setLibelleLongFr(libelleFr);
		nc.setLibelleLongAr(libelleAr);
		nc.setIdReference(idReference);
		nc.setLibelleLongFrRef(libelleLongFrReference);
		
		return nc;
	}

	// ===================================================================  
	// Methods
	// ===================================================================
	
	public List<RefIndividuDto> searchPerson(String text)  {
		
				 List<RefIndividuDto> result = new ArrayList<RefIndividuDto>();
				 
				 try {
					 		result =  refIndividuService.findGeneric(text);
				 }
				 catch (Exception e){
					 	
					 	   e.printStackTrace();
				 }
				return result;
	}
	
	public RefIndividuDto getPersonById(String idPerson)  {
		
				RefIndividuDto result = new RefIndividuDto();
				
				try {
							result =  refIndividuService.findById(Integer.parseInt(idPerson));
							
				}
				catch (Exception e){
					
							e.printStackTrace();
				}
				return result;
	}
	
	public List<RefContratDto> searchContract(String text)  {
		
				List<RefContratDto> result = new ArrayList<RefContratDto>();
				
				try {
							result =  refContratService.findGeneric(text,false);
				}
				catch (Exception e){
					
							e.printStackTrace();
				}
				return result;
	}
	
	public RefContratDto getContractByCode(String codeContract)  {
		
				RefContratDto result = new RefContratDto();
				
				try {
					
						result =  refContratService.findByCode((codeContract));
					
				}
				catch (Exception e){
					
							e.printStackTrace();
				}
				return result;
	}
	
	public List<RefPartenaireDto> getPartnersByContractCode(String codeContract)  {
		
				List<RefPartenaireDto> result = new ArrayList<RefPartenaireDto>();
				
				try {
							result =  refPartenaireService.findByCodeContrat(codeContract);
				}
				catch (Exception e){
					
							e.printStackTrace();
				}
				return result;
	}
	
	public Map<Integer, List<OffreFormationDetailContentDto>> getOfComplementTreeDetailContentByType(int ofId)	{
		
					try {
						
						Map<Integer, List<OffreFormationDetailContentDto>> treesOfDetailsDataSources = new HashMap<Integer, List<OffreFormationDetailContentDto>>();
						
						List<OffreFormationDetailContentDto> detailOfDataSources =  offreFormationDetailContentService.findOfContentTreeById(ofId);
						
						//printOffreFormationDetailDtoList(detailOfDataSources);
						
						// Tree DataSource pour for [Offre Formation Complement]
						List<OffreFormationDetailContentDto> dsComplement = new ArrayList<OffreFormationDetailContentDto>();
						
						// Tree DataSource pour for [Offre Formation Evaluation]
						List<OffreFormationDetailContentDto> dsEvaluation = new ArrayList<OffreFormationDetailContentDto>();
						
						// Tree DataSource pour for [Offre Formation Mobilite]
						List<OffreFormationDetailContentDto> dsMobilite = new ArrayList<OffreFormationDetailContentDto>();
						
						for (OffreFormationDetailContentDto det : detailOfDataSources) {
							
									switch (det.getOffreFormationDetail().getTypeDetail()) {
									
												case 1:
													
													// This is a detail for the OFFRE FROMATION COMPLEMENT
													dsComplement.add(det);
													
													break;
												case 2:
													
													// This is a detail for the OFFRE FROMATION EVALUATION
													dsEvaluation.add(det);
													
													break;
												case 3:
													
													// This is a detail for the OFFRE FROMATION MOBILITE
													dsMobilite.add(det);
												
													break;
				
												default:
													break;
									}
						}
						
						treesOfDetailsDataSources.put(new Integer(1), dsComplement);
						
						treesOfDetailsDataSources.put(new Integer(2), dsEvaluation);
						
						treesOfDetailsDataSources.put(new Integer(3), dsMobilite);
				
						return 	treesOfDetailsDataSources;
						
				}
				catch (Exception e) {
					
						e.printStackTrace();
						return new HashMap<Integer, List<OffreFormationDetailContentDto>>();
				}
					
	}
	
	public Map<Integer, List<OffreFormationDetailContentDto>> getOfComplementTreeDetailContentByType() 	{
		
		try {
			
						Map<Integer, List<OffreFormationDetailContentDto>> treesOfDetailsDataSources = new HashMap<Integer, List<OffreFormationDetailContentDto>>();
						
						List<OffreFormationDetail> detailOfDataSources =  offreFormationDetailService.findAll();
						
						// Tree DataSource  for [Offre Formation Complement]
						List<OffreFormationDetailContentDto> dsComplement = new ArrayList<OffreFormationDetailContentDto>();
						
						// Tree DataSource  for [Offre Formation Evaluation]
						List<OffreFormationDetailContentDto> dsEvaluation = new ArrayList<OffreFormationDetailContentDto>();
						
						// Tree DataSource  for [Offre Formation Mobilite]
						List<OffreFormationDetailContentDto> dsMobilite = new ArrayList<OffreFormationDetailContentDto>();
						
						for (OffreFormationDetail det : detailOfDataSources) {
							
							OffreFormationDetailContentDto newItem = new OffreFormationDetailContentDto();
							newItem.setOffreFormationDetail(det);
					
							switch (det.getTypeDetail()) {
							
							case 1:
								
								// This is a detail for the OFFRE FROMATION COMPLEMENT
								dsComplement.add(newItem);
								
								break;
							case 2:
								
								// This is a detail for the OFFRE FROMATION EVALUATION
								dsEvaluation.add(newItem);
								
								break;
							case 3:
								
								// This is a detail for the OFFRE FROMATION MOBILITE
								dsMobilite.add(newItem);
								
								break;
								
							default:
								break;
							}
						}
						
						treesOfDetailsDataSources.put(new Integer(1), dsComplement);
						
						treesOfDetailsDataSources.put(new Integer(2), dsEvaluation);
						
						treesOfDetailsDataSources.put(new Integer(3), dsMobilite);
						
						return 	treesOfDetailsDataSources;
			
		}
		catch (Exception e) {
			
						e.printStackTrace();
						return new HashMap<Integer, List<OffreFormationDetailContentDto>>();
		}
		
	}
	
	public OffreFormationDetailContentDto getNodeContentDataById(int id) {
		
		try {
					return offreFormationDetailContentService.findById(id);
			
		}
		catch (Exception e) {
			
			return null;
		}
		
	}
	
	public OffreFormationDetailContentDto updateNodeContentDataById(OffreFormationDetailContentDto obj) {
		
		try {
					return offreFormationDetailContentService.insertOrUpdate(obj);
			
		}
		catch (Exception e) {
			
			return new OffreFormationDetailContentDto();
		}
		
	}
	
	public Map<Integer, List<SelectItem>> getNcByName(String ncName){
		
				Map<Integer, List<SelectItem>> result = new HashMap<Integer, List<SelectItem>>();
				List<SelectItem> resultFr = new ArrayList<SelectItem>();
				List<SelectItem> resultAr = new ArrayList<SelectItem>();
				try {
					List<NomenclatureDto> ncList = nomenclatureService.findByName(ncName);
			 		for (NomenclatureDto item : ncList) {
			 			resultFr.add(new SelectItem(item.getCode(), item.getLibelleLongFr()));
			 			resultAr.add(new SelectItem(item.getCode(), item.getLibelleLongAr()));
					}
			 		// Liste Nomeclature FR
			 		result.put(new Integer(1), resultFr);
			 		//Liste Nomeclature AR
			 		result.put(new Integer(2), resultAr);
				}
				catch (Exception e) {
					 e.printStackTrace();
				}
				return result;
	}
	
	public Map<Integer, List<SelectItem>> getNcByName(String ncName, String ncNameReference, String FiltreValue){
		
		
			Map<Integer, List<SelectItem>> result = new HashMap<Integer, List<SelectItem>>();
		
			try {
			
			
					List<SelectItem> resultFr = new ArrayList<SelectItem>();
					List<SelectItem> resultAr = new ArrayList<SelectItem>();
					
					List<NomenclatureDto> ncList = nomenclatureService.findByName(ncName);
					List<NomenclatureDto> ncListRef = nomenclatureService.findByName(ncNameReference);
					
					for (NomenclatureDto item : ncListRef) {
						
							if(item.getCode().equals(FiltreValue)) 
							
									for (NomenclatureDto item1 : ncList) {
									
											if(item.getId().equals(item1.getIdReference())){
														
												resultFr.add(new SelectItem(item1.getCode(), item1.getLibelleLongFr() + " (" + item1.getCode() + ")"  ));
												resultAr.add(new SelectItem(item1.getCode(), item1.getLibelleLongAr() + " (" + item1.getCode() + ")"));
											}
									}
					}
					
			 		// Liste Nomeclature FR par nomenclature parent
			 		result.put(new Integer(1), resultFr);
			 		
			 		//Liste Nomeclature AR par nomenclature parent
			 		result.put(new Integer(2), resultAr);

			
			}
		
			catch (Exception e) {
			
				 e.printStackTrace();
			
			}
		
			return result;
	}
	
	public List<NomenclatureDto> getAllLmdNomenclatures()  {
		
				List<NomenclatureDto> result = new ArrayList<NomenclatureDto>();
				try {
					
						result = nomenclatureService.findByDomaine(OfConstants.NC_NAME_LMD);
				}
				
				catch (Exception e) {
					 e.printStackTrace();
				}
				
				return result;
		
		}

	public List<RefDomaineLMDDto> getAllDomainesLMD()  {
		
		List<RefDomaineLMDDto> result = new ArrayList<RefDomaineLMDDto>();
		try {
				result = refDomaineLMDService.findAll();
		}
		
		catch (Exception e) {
			 e.printStackTrace();
		}
		
		return result;

	}
	
//	public List<RefAffectDomLmdEtabDto> getDomainesLMDByEtab(Integer idEtab)  {
//		
//		List<RefAffectDomLmdEtabDto> result = new ArrayList<RefAffectDomLmdEtabDto>();
//		try {
//			
//			result = refDomaineLMDService.findDomainesByEtablissement(idEtab);
//		}
//		
//		catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		return result;
//		
//	}
	
	public List<RefFiliereLmdDto> getAllFilieresLMD()  {
		
		List<RefFiliereLmdDto> result = new ArrayList<RefFiliereLmdDto>();
		try {
			
			result = refFiliereLmdService.findAll();
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	public List<RefSpecialiteLmdDto> getAllSpecialitesLMD()  {
		
		List<RefSpecialiteLmdDto> result = new ArrayList<RefSpecialiteLmdDto>();
		try {
			result = refSpecialiteLmdService.findAll();
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	public List<RefFiliereLmdDto> getFilieresLMDByDomaine(String codeDomaine)  {
		
		List<RefFiliereLmdDto> result = new ArrayList<RefFiliereLmdDto>();
		try {
			
			result = refFiliereLmdService.findByCodeDomainelmd(codeDomaine);
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	public RefFiliereLmdDto getFiliereLMDByIdentifiant(String codeFiliere)  {
		
		RefFiliereLmdDto result = new RefFiliereLmdDto();
		try {
			result = refFiliereLmdService.findByIdentifiant(codeFiliere);
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	public List<RefSpecialiteLmdDto> getSpecialitesByFiliere(String codeFiliere)  {
		
		List<RefSpecialiteLmdDto> result = new ArrayList<RefSpecialiteLmdDto>();
		try {
			
			result = refSpecialiteLmdService.findByCodeFilierelmd(codeFiliere);
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
		
	}

	// ===================================================================  
	// Navigation Actions for Menu
	// ===================================================================
	
	public String goOfSearch() {
		
		return "searchOf";
	}
	
	public String goOfAdd() {
			
		return "EditOf";
	}
	
    public String goUeSearch() {
		
		return "ueSearch";
	}
    
    public String goUeAdd() {
		
		return "ueEdit";
				
	}
    
    public String goMcSearch() {
		
  		return "mcSearch";
  	}
    
    public String goMcAdd() {
  		
  		return "mcEdit";
    }

}


