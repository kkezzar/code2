package dz.gov.mesrs.sii.fve.web.jsf.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import dz.gov.mesrs.sii.fve.business.model.dto.bac.DossierBachelierDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.AbsenceMatiereDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DiplomeFinEtudeDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DossierEtudiantDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DossierInscriptionAdministrativeDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DossierTransfertDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.ExclusionDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.ReintegrationDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.AnneeAcademiqueDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OuvertureOffreFormationDto;
import dz.gov.mesrs.sii.fve.business.service.offreformation.AnneeAcademiqueService;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEtablissementDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefIndividuDto;
import dz.gov.mesrs.sii.referentiel.business.util.ConfigHolder;

/**
 * Classe permettant l'interfaeage entre les vues jsf et les classes de
 * services pour la gestion des unites d'enseignement
 * 
 * @author kheireddine omrani
 * 
 */
@ManagedBean(name = "sessionBeanFve")
@SessionScoped
public class SessionBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6792970203268407793L;
	private Integer idEtablissement;
	private String codeEtablissement;
	private String ancienCodeEtablissement;
	private String lcLatinEtablissement;
	private String lcArabeEtablissement;
	private String llLatinEtablissement;
	private String llArabeEtablissement;
	private RefEtablissementDto refEtablissementDto;
	private Integer idAnneeAcademique;
	private Short premiereAnneeAcademique;
	private Short deuxiemeAnneeAcademique;
	private String codeAnneeAcademique;

	@ManagedProperty(value = "#{anneeAcademiqueService}")
	private AnneeAcademiqueService anneeAcademiqueService;

	@ManagedProperty(value = "#{configHolder}")
	private ConfigHolder configHolder;
	private String individuIdentifiant;
	Map<String, Object> map = new HashMap<String, Object>();
	private DossierEtudiantDto dossierEtudiantSearchDto;
	private AbsenceMatiereDto absenceMatiereSearchDto;
	private ExclusionDto exclusionSearchDto;
	private DiplomeFinEtudeDto diplomeFinEtudeSearchDto;
	private ReintegrationDto reintegrationSearchDto;
	private OuvertureOffreFormationDto ouvertureOfSearchDto;
	DossierBachelierDto dossierBachelierSearchDto;
	private DossierInscriptionAdministrativeDto dossierInscriptionSearchCmpDto;
	private DossierTransfertDto dossierTransfertSearchDto;
	private DossierInscriptionAdministrativeDto printSearchDto;
	private RefIndividuDto individuSearchDto;
	private String folderPhoto;
	private String folderTemp;
	
	@ManagedProperty(value = "#{sessionBean}")
	private dz.gov.mesrs.sii.commons.web.jsf.bean.SessionBean sessionBean;


	/**
	 * Constructure du manager bean
	 */
	public SessionBean() {
		super();
		dossierEtudiantSearchDto = new DossierEtudiantDto();
		absenceMatiereSearchDto = new AbsenceMatiereDto();
		exclusionSearchDto = new ExclusionDto();
		diplomeFinEtudeSearchDto = new DiplomeFinEtudeDto();
		reintegrationSearchDto = new ReintegrationDto();
		ouvertureOfSearchDto = new OuvertureOffreFormationDto();
		dossierBachelierSearchDto = new DossierBachelierDto();
		dossierInscriptionSearchCmpDto = new DossierInscriptionAdministrativeDto();
		dossierTransfertSearchDto = new DossierTransfertDto();
		printSearchDto = new DossierInscriptionAdministrativeDto();
		individuSearchDto = new RefIndividuDto();

	}

	@PostConstruct
	public void init() {
		
		// Solution provisioire en attendant de faire le refactoring BaseBean/ServiceLocator
		idEtablissement = sessionBean.getIdEtablissement();
		codeEtablissement = sessionBean.getCodeEtablissement();
		ancienCodeEtablissement = sessionBean.getAncienCodeEtablissement();
		lcLatinEtablissement = sessionBean.getLcLatinEtablissement();
		lcArabeEtablissement = sessionBean.getLcArabeEtablissement();
		llLatinEtablissement = sessionBean.getLlLatinEtablissement();
		llArabeEtablissement = sessionBean.getLlArabeEtablissement();
		refEtablissementDto = sessionBean.getRefEtablissementDto();
		
		loadCurrentAnneeAcademique();
		loadFolderPhoto();
	}
	
	/**
	 * [SessionBean.loadFolderPhoto] Method 
	 * @author MAKERRI Sofiane  on : 6 nov. 2014  09:18:04
	 */
	private void loadFolderPhoto() {
	    folderPhoto = configHolder.getPhotoUploadFolder()+ "/";
	    folderTemp = configHolder.getDocumentTempFolder()+ "/";
	}

	/****************************************************************************/
	/********************************* getter/setter ****************************/
	/****************************************************************************/

	// ************************* getter/setter des services
	// **********************/

	// **************************** getter/setter des proprietes
	// ********************/

	public Object getValueForKey(String key) {
		return map.get(key);
	}

	/**
	 * [SessionBean.dossierEtudiantSearchDto] Getter
	 * 
	 * @author BELDI Jamel on : 26 mai 2014 15:58:52
	 * @return the dossierEtudiantSearchDto
	 */
	public DossierEtudiantDto getDossierEtudiantSearchDto() {
		return dossierEtudiantSearchDto;
	}

	/**
	 * [SessionBean.dossierEtudiantSearchDto] Setter
	 * 
	 * @author BELDI Jamel on : 26 mai 2014 15:58:52
	 * @param dossierEtudiantSearchDto
	 *            the dossierEtudiantSearchDto to set
	 */
	public void setDossierEtudiantSearchDto(
			DossierEtudiantDto dossierEtudiantSearchDto) {
		this.dossierEtudiantSearchDto = dossierEtudiantSearchDto;
	}

	/**
	 * [SessionBean.ouvertureOfSearchDto] Getter
	 * 
	 * @author BELDI Jamel on : 26 mai 2014 15:58:52
	 * @return the ouvertureOfSearchDto
	 */
	public OuvertureOffreFormationDto getOuvertureOfSearchDto() {
		return ouvertureOfSearchDto;
	}

	/**
	 * [SessionBean.ouvertureOfSearchDto] Setter
	 * 
	 * @author BELDI Jamel on : 26 mai 2014 15:58:52
	 * @param ouvertureOfSearchDto
	 *            the ouvertureOfSearchDto to set
	 */
	public void setOuvertureOfSearchDto(
			OuvertureOffreFormationDto ouvertureOfSearchDto) {
		this.ouvertureOfSearchDto = ouvertureOfSearchDto;
	}

	public void put(String key, Object value) {
		map.put(key, value);
	}

	public boolean containsKey(String key) {
		return map.containsKey(key);
	}

	public boolean containsValue(Object value) {
		return map.containsValue(value);
	}

	public Object remove(Object key) {

		return map.remove(key);
	}

	public void clearAllMapObject() {
		map.clear();
	}

	/**
	 * [SessionBean.dossierBachelierSearchDto] Getter
	 * 
	 * @author BELDI Jamel on : 29 mai 2014 18:00:09
	 * @return the dossierBachelierSearchDto
	 */
	public DossierBachelierDto getDossierBachelierSearchDto() {
		return dossierBachelierSearchDto;
	}

	/**
	 * [SessionBean.dossierBachelierSearchDto] Setter
	 * 
	 * @author BELDI Jamel on : 29 mai 2014 18:00:09
	 * @param dossierBachelierSearchDto
	 *            the dossierBachelierSearchDto to set
	 */
	public void setDossierBachelierSearchDto(
			DossierBachelierDto dossierBachelierSearchDto) {
		this.dossierBachelierSearchDto = dossierBachelierSearchDto;
	}

	public AbsenceMatiereDto getAbsenceMatiereSearchDto() {
		return absenceMatiereSearchDto;
	}

	public void setAbsenceMatiereSearchDto(
			AbsenceMatiereDto absenceMatiereSearchDto) {
		this.absenceMatiereSearchDto = absenceMatiereSearchDto;
	}

	/**
	 * [SessionBean.idEtablissement] Getter
	 * 
	 * @author MAKERRI Sofiane on : 11 juin 2014 17:05:43
	 * @return the idEtablissement
	 */
	public Integer getIdEtablissement() {
		return idEtablissement;
	}

	/**
	 * [SessionBean.idEtablissement] Setter
	 * 
	 * @author MAKERRI Sofiane on : 11 juin 2014 17:05:43
	 * @param idEtablissement
	 *            the idEtablissement to set
	 */
	public void setIdEtablissement(Integer idEtablissement) {
		this.idEtablissement = idEtablissement;
	}

	/**
	 * [SessionBean.codeEtablissement] Getter
	 * 
	 * @author MAKERRI Sofiane on : 11 juin 2014 17:05:43
	 * @return the codeEtablissement
	 */
	public String getCodeEtablissement() {
		return codeEtablissement;
	}

	/**
	 * [SessionBean.codeEtablissement] Setter
	 * 
	 * @author MAKERRI Sofiane on : 11 juin 2014 17:05:43
	 * @param codeEtablissement
	 *            the codeEtablissement to set
	 */
	public void setCodeEtablissement(String codeEtablissement) {
		this.codeEtablissement = codeEtablissement;
	}

	/**
	 * [SessionBean.lcLatinEtablissement] Getter
	 * 
	 * @author MAKERRI Sofiane on : 11 juin 2014 17:05:43
	 * @return the lcLatinEtablissement
	 */
	public String getLcLatinEtablissement() {
		return lcLatinEtablissement;
	}

	/**
	 * [SessionBean.lcLatinEtablissement] Setter
	 * 
	 * @author MAKERRI Sofiane on : 11 juin 2014 17:05:43
	 * @param lcLatinEtablissement
	 *            the lcLatinEtablissement to set
	 */
	public void setLcLatinEtablissement(String lcLatinEtablissement) {
		this.lcLatinEtablissement = lcLatinEtablissement;
	}

	/**
	 * [SessionBean.lcArabeEtablissement] Getter
	 * 
	 * @author MAKERRI Sofiane on : 11 juin 2014 17:05:43
	 * @return the lcArabeEtablissement
	 */
	public String getLcArabeEtablissement() {
		return lcArabeEtablissement;
	}

	/**
	 * [SessionBean.lcArabeEtablissement] Setter
	 * 
	 * @author MAKERRI Sofiane on : 11 juin 2014 17:05:43
	 * @param lcArabeEtablissement
	 *            the lcArabeEtablissement to set
	 */
	public void setLcArabeEtablissement(String lcArabeEtablissement) {
		this.lcArabeEtablissement = lcArabeEtablissement;
	}

	/**
	 * [SessionBean.llLatinEtablissement] Getter
	 * 
	 * @author MAKERRI Sofiane on : 11 juin 2014 17:05:43
	 * @return the llLatinEtablissement
	 */
	public String getLlLatinEtablissement() {
		return llLatinEtablissement;
	}

	/**
	 * [SessionBean.llLatinEtablissement] Setter
	 * 
	 * @author MAKERRI Sofiane on : 11 juin 2014 17:05:43
	 * @param llLatinEtablissement
	 *            the llLatinEtablissement to set
	 */
	public void setLlLatinEtablissement(String llLatinEtablissement) {
		this.llLatinEtablissement = llLatinEtablissement;
	}

	/**
	 * [SessionBean.llArabeEtablissement] Getter
	 * 
	 * @author MAKERRI Sofiane on : 11 juin 2014 17:05:43
	 * @return the llArabeEtablissement
	 */
	public String getLlArabeEtablissement() {
		return llArabeEtablissement;
	}

	/**
	 * [SessionBean.llArabeEtablissement] Setter
	 * 
	 * @author MAKERRI Sofiane on : 11 juin 2014 17:05:43
	 * @param llArabeEtablissement
	 *            the llArabeEtablissement to set
	 */
	public void setLlArabeEtablissement(String llArabeEtablissement) {
		this.llArabeEtablissement = llArabeEtablissement;
	}

	/**
	 * [SessionBean.refEtablissementDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 11 juin 2014 17:05:43
	 * @return the refEtablissementDto
	 */
	public RefEtablissementDto getRefEtablissementDto() {
		return refEtablissementDto;
	}

	/**
	 * [SessionBean.refEtablissementDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 11 juin 2014 17:05:43
	 * @param refEtablissementDto
	 *            the refEtablissementDto to set
	 */
	public void setRefEtablissementDto(RefEtablissementDto refEtablissementDto) {
		this.refEtablissementDto = refEtablissementDto;
	}

	/**
	 * [SessionBean.dossierInscritpSearchCmpDto] Getter
	 * 
	 * @author BELDI Jamel on : 12 juin 2014 10:06:24
	 * @return the dossierInscritpSearchCmpDto
	 */
	public DossierInscriptionAdministrativeDto getDossierInscriptionSearchCmpDto() {
		return dossierInscriptionSearchCmpDto;
	}

	/**
	 * [SessionBean.dossierInscritpSearchCmpDto] Setter
	 * 
	 * @author BELDI Jamel on : 12 juin 2014 10:06:24
	 * @param dossierInscritpSearchCmpDto
	 *            the dossierInscritpSearchCmpDto to set
	 */
	public void setDossierInscriptionSearchCmpDto(
			DossierInscriptionAdministrativeDto dossierInscriptionSearchCmpDto) {
		this.dossierInscriptionSearchCmpDto = dossierInscriptionSearchCmpDto;
	}

	/**
	 * [SessionBean.dossierTransfertSearchDto] Getter
	 * 
	 * @author BELDI Jamel on : 12 juin 2014 18:44:38
	 * @return the dossierTransfertSearchDto
	 */
	public DossierTransfertDto getDossierTransfertSearchDto() {
		return dossierTransfertSearchDto;
	}

	/**
	 * [SessionBean.dossierTransfertSearchDto] Setter
	 * 
	 * @author BELDI Jamel on : 12 juin 2014 18:44:38
	 * @param dossierTransfertSearchDto
	 *            the dossierTransfertSearchDto to set
	 */
	public void setDossierTransfertSearchDto(
			DossierTransfertDto dossierTransfertSearchDto) {
		this.dossierTransfertSearchDto = dossierTransfertSearchDto;
	}

	/**
	 * [SessionBean.loadAnneeAcademique] Method
	 * 
	 * @author MAKERRI Sofiane on : 16 juin 2014 17:00:40
	 */
	public void loadCurrentAnneeAcademique() {
		try {
			AnneeAcademiqueDto anneeAcademiqueDto = anneeAcademiqueService.findCurrentAnneeAcademique();
			if (anneeAcademiqueDto != null) {
				setIdAnneeAcademique(anneeAcademiqueDto.getId());
				setCodeAnneeAcademique(anneeAcademiqueDto.getCode());
				setPremiereAnneeAcademique(anneeAcademiqueDto
						.getPremiereAnnee());
				setDeuxiemeAnneeAcademique(anneeAcademiqueDto
						.getDeuxiemeAnnee());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * [SessionBean.idAnneeAcademique] Getter
	 * 
	 * @author MAKERRI Sofiane on : 16 juin 2014 16:59:08
	 * @return the idAnneeAcademique
	 */
	public Integer getIdAnneeAcademique() {
		return idAnneeAcademique;
	}

	/**
	 * [SessionBean.idAnneeAcademique] Setter
	 * 
	 * @author MAKERRI Sofiane on : 16 juin 2014 16:59:08
	 * @param idAnneeAcademique
	 *            the idAnneeAcademique to set
	 */
	public void setIdAnneeAcademique(Integer idAnneeAcademique) {
		this.idAnneeAcademique = idAnneeAcademique;
	}

	/**
	 * [SessionBean.anneeAcademiqueService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 16 juin 2014 17:00:21
	 * @return the anneeAcademiqueService
	 */
	public AnneeAcademiqueService getAnneeAcademiqueService() {
		return anneeAcademiqueService;
	}

	/**
	 * [SessionBean.anneeAcademiqueService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 16 juin 2014 17:00:21
	 * @param anneeAcademiqueService
	 *            the anneeAcademiqueService to set
	 */
	public void setAnneeAcademiqueService(
			AnneeAcademiqueService anneeAcademiqueService) {
		this.anneeAcademiqueService = anneeAcademiqueService;
	}

	/**
	 * [SessionBean.codeAnneeAcademique] Getter
	 * 
	 * @author MAKERRI Sofiane on : 17 juin 2014 16:26:41
	 * @return the codeAnneeAcademique
	 */
	public String getCodeAnneeAcademique() {
		return codeAnneeAcademique;
	}

	/**
	 * [SessionBean.codeAnneeAcademique] Setter
	 * 
	 * @author MAKERRI Sofiane on : 17 juin 2014 16:26:41
	 * @param codeAnneeAcademique
	 *            the codeAnneeAcademique to set
	 */
	public void setCodeAnneeAcademique(String codeAnneeAcademique) {
		this.codeAnneeAcademique = codeAnneeAcademique;
	}

	/**
	 * [SessionBean.printSearchDto] Getter
	 * 
	 * @author BELDI Jamel on : 22 juin 2014 15:51:08
	 * @return the printSearchDto
	 */
	public DossierInscriptionAdministrativeDto getPrintSearchDto() {
		return printSearchDto;
	}

	/**
	 * [SessionBean.printSearchDto] Setter
	 * 
	 * @author BELDI Jamel on : 22 juin 2014 15:51:08
	 * @param printSearchDto
	 *            the printSearchDto to set
	 */
	public void setPrintSearchDto(
			DossierInscriptionAdministrativeDto printSearchDto) {
		this.printSearchDto = printSearchDto;
	}

	/**
	 * [SessionBean.premiereAnneeAcademique] Getter
	 * 
	 * @author MAKERRI Sofiane on : 19 juin 2014 10:47:13
	 * @return the premiereAnneeAcademique
	 */
	public Short getPremiereAnneeAcademique() {
		return premiereAnneeAcademique;
	}

	/**
	 * [SessionBean.premiereAnneeAcademique] Setter
	 * 
	 * @author MAKERRI Sofiane on : 19 juin 2014 10:47:13
	 * @param premiereAnneeAcademique
	 *            the premiereAnneeAcademique to set
	 */
	public void setPremiereAnneeAcademique(Short premiereAnneeAcademique) {
		this.premiereAnneeAcademique = premiereAnneeAcademique;
	}

	public Short getDeuxiemeAnneeAcademique() {
		return deuxiemeAnneeAcademique;
	}

	public void setDeuxiemeAnneeAcademique(Short deuxiemeAnneeAcademique) {
		this.deuxiemeAnneeAcademique = deuxiemeAnneeAcademique;
	}

	/**
	 * [SessionBean.ancienCodeEtablissement] Getter
	 * 
	 * @author MAKERRI Sofiane on : 23 juin 2014 12:33:40
	 * @return the ancienCodeEtablissement
	 */
	public String getAncienCodeEtablissement() {
		return ancienCodeEtablissement;
	}

	/**
	 * [SessionBean.ancienCodeEtablissement] Setter
	 * 
	 * @author MAKERRI Sofiane on : 23 juin 2014 12:33:40
	 * @param ancienCodeEtablissement
	 *            the ancienCodeEtablissement to set
	 */
	public void setAncienCodeEtablissement(String ancienCodeEtablissement) {
		this.ancienCodeEtablissement = ancienCodeEtablissement;
	}

	/**
	 * <<<<<<< HEAD [SessionBean.map] Getter
	 * 
	 * @author BELBRIK Oualid on : 6 juil. 2014 13:53:12
	 * @return the map
	 */
	public Map<String, Object> getMap() {
		return map;
	}

	/**
	 * [SessionBean.map] Setter
	 * 
	 * @author BELBRIK Oualid on : 6 juil. 2014 13:53:12
	 * @param map
	 *            the map to set
	 */
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	/**
	 * [SessionBean.exclusionSearchDto] Getter
	 * 
	 * @author BELBRIK Oualid on : 6 juil. 2014 13:53:12
	 * @return the exclusionSearchDto
	 */
	public ExclusionDto getExclusionSearchDto() {
		return exclusionSearchDto;
	}

	/**
	 * [SessionBean.exclusionSearchDto] Setter
	 * 
	 * @author BELBRIK Oualid on : 6 juil. 2014 13:53:12
	 * @param exclusionSearchDto
	 *            the exclusionSearchDto to set
	 */
	public void setExclusionSearchDto(ExclusionDto exclusionSearchDto) {
		this.exclusionSearchDto = exclusionSearchDto;
	}

	public String getIndividuIdentifiant() {
		return individuIdentifiant;
	}

	/**
	 * [SessionBean.individuIdentifiant] Setter
	 * 
	 * @author MAKERRI Sofiane on : 5 juil. 2014 14:03:01
	 * @param individuIdentifiant
	 *            the individuIdentifiant to set
	 */
	public void setIndividuIdentifiant(String individuIdentifiant) {
		this.individuIdentifiant = individuIdentifiant;
	}

	/**
	 * [SessionBean.reintegrationSearchDto] Getter
	 * 
	 * @author BELBRIK Oualid on : 10 juil. 2014 17:03:41
	 * @return the reintegrationSearchDto
	 */
	public ReintegrationDto getReintegrationSearchDto() {
		return reintegrationSearchDto;
	}

	/**
	 * [SessionBean.reintegrationSearchDto] Setter
	 * 
	 * @author BELBRIK Oualid on : 10 juil. 2014 17:03:41
	 * @param reintegrationSearchDto
	 *            the reintegrationSearchDto to set
	 */
	public void setReintegrationSearchDto(
			ReintegrationDto reintegrationSearchDto) {
		this.reintegrationSearchDto = reintegrationSearchDto;
	}

	/**
	 * [SessionBean.individuSearchDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 21 juil. 2014 17:27:23
	 * @return the individuSearchDto
	 */
	public RefIndividuDto getIndividuSearchDto() {
		return individuSearchDto;
	}

	/**
	 * [SessionBean.individuSearchDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 21 juil. 2014 17:27:23
	 * @param individuSearchDto
	 *            the individuSearchDto to set
	 */
	public void setIndividuSearchDto(RefIndividuDto individuSearchDto) {
		this.individuSearchDto = individuSearchDto;
	}

	/**
	 * [SessionBean.diplomeFinEtudeSearchDto] Getter
	 * 
	 * @author BELBRIK Oualid on : 7 oct. 2014 13:38:19
	 * @return the diplomeFinEtudeSearchDto
	 */
	public DiplomeFinEtudeDto getDiplomeFinEtudeSearchDto() {
		return diplomeFinEtudeSearchDto;
	}

	/**
	 * [SessionBean.diplomeFinEtudeSearchDto] Setter
	 * 
	 * @author BELBRIK Oualid on : 7 oct. 2014 13:38:20
	 * @param diplomeFinEtudeSearchDto
	 *            the diplomeFinEtudeSearchDto to set
	 */
	public void setDiplomeFinEtudeSearchDto(
			DiplomeFinEtudeDto diplomeFinEtudeSearchDto) {
		this.diplomeFinEtudeSearchDto = diplomeFinEtudeSearchDto;
	}

	/**
	 * [SessionBean.folderPhoto] Getter 
	 * @author MAKERRI Sofiane on : 6 nov. 2014  09:16:04
	 * @return the folderPhoto
	 */
	public String getFolderPhoto() {
		return folderPhoto;
	}

	/**
	 * [SessionBean.folderPhoto] Setter 
	 * @author MAKERRI Sofiane on : 6 nov. 2014  09:16:04
	 * @param folderPhoto the folderPhoto to set
	 */
	public void setFolderPhoto(String folderPhoto) {
		this.folderPhoto = folderPhoto;
	}

	/**
	 * [SessionBean.folderTemp] Getter 
	 * @author MAKERRI Sofiane on : 6 nov. 2014  09:16:04
	 * @return the folderTemp
	 */
	public String getFolderTemp() {
		return folderTemp;
	}

	/**
	 * [SessionBean.folderTemp] Setter 
	 * @author MAKERRI Sofiane on : 6 nov. 2014  09:16:04
	 * @param folderTemp the folderTemp to set
	 */
	public void setFolderTemp(String folderTemp) {
		this.folderTemp = folderTemp;
	}

	public void setConfigHolder(ConfigHolder configHolder) {
	    this.configHolder = configHolder;
	}
	
	public ConfigHolder getConfigHolder() {
	    return configHolder;
	}

	/**
	 * [SessionBean.sessionBean] Getter 
	 * @author rlaib on : 8 déc. 2014  12:14:58
	 * @return the sessionBean
	 */
	public dz.gov.mesrs.sii.commons.web.jsf.bean.SessionBean getSessionBean() {
		return sessionBean;
	}

	/**
	 * [SessionBean.sessionBean] Setter 
	 * @author rlaib on : 8 déc. 2014  12:14:58
	 * @param sessionBean the sessionBean to set
	 */
	public void setSessionBean(dz.gov.mesrs.sii.commons.web.jsf.bean.SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}
	
	

}
