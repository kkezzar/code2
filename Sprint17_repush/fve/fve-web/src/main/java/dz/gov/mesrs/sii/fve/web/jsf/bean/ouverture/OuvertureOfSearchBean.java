/**
 * [dz.gov.mesrs.sii.fve.web.jsf.bean.ouverture.OuvertureOfSearchBean.java] 
 * @author BELDI Jamel on : 13 mai 2014  12:18:07
 */
package dz.gov.mesrs.sii.fve.web.jsf.bean.ouverture;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.Flash;
import javax.faces.model.SelectItem;

import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.AnneeAcademiqueDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OffreFormationDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OuvertureOffreFormationDto;
import dz.gov.mesrs.sii.fve.business.service.offreformation.AnneeAcademiqueService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OffreFormationService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OuvertureOffreFormationService;
import dz.gov.mesrs.sii.fve.business.util.UtilConstants;
import dz.gov.mesrs.sii.fve.web.jsf.bean.SessionBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.utils.Utility;

/**
 * @author BELDI Jamel on : 13 mai 2014 12:18:07
 */
@ManagedBean(name = "ouvertureOfSearchBean")
@RequestScoped
public class OuvertureOfSearchBean implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author BELDI Jamel on : 13 mai 2014 12:19:04
	 */
	private static final long serialVersionUID = 1L;
	private int exception;
	private List<OuvertureOffreFormationDto> ouvertureOfResultSearch;
	private OuvertureOffreFormationDto ouvertureOfSearchDto;
	@ManagedProperty(value = "#{offreFormationService}")
	private OffreFormationService offreFormationService;
	@ManagedProperty(value = "#{anneeAcademiqueService}")
	private AnneeAcademiqueService anneeAcademiqueService;
	@ManagedProperty(value = "#{ouvertureOffreFormationService}")
	private OuvertureOffreFormationService ouvertureOffreFormationService;
	@ManagedProperty("#{flash}")
	private Flash flash;
	@ManagedProperty("#{sessionBeanFve}")
	private SessionBean sessionBean;
	private List<SelectItem> listeOffres;
	private List<SelectItem> listeAnneeAcademiques;
	private List<SelectItem> listeEtabs;

	/**
	 * [OuvertureOfSearchBean.OuvertureOfSearchBean()] Constructor
	 * 
	 * @author BELDI Jamel on : 13 mai 2014 12:18:07
	 */
	public OuvertureOfSearchBean() {
		super();
	}

	@PostConstruct
	public void init() {
		exception = 0;
		loadSearchDto();
		loadOuvertureOfResultSearch();

	}

	private void loadSearchDto() {
		// if(ouvertureOfSearchDto==null){
		// ouvertureOfSearchDto = new OuvertureOffreFormationDto();
		// ouvertureOfSearchDto.setRefCodeEtablissement("esi");//TODO
		// Etablissement of connected User
		// }
		//
		// if(sessionBean.containsKey("ouvertureOfSearchDto")){
		// sessionBean.remove(sessionBean.getValueForKey("ouvertureOfSearchDto"));
		// }
		// sessionBean.put("ouvertureOfSearchDto", ouvertureOfSearchDto);
		ouvertureOfSearchDto = sessionBean.getOuvertureOfSearchDto();
		ouvertureOfSearchDto.setIdEtablissement(sessionBean
				.getIdEtablissement());
	}

	private void loadOuvertureOfResultSearch() {
		ouvertureOfResultSearch = new ArrayList<OuvertureOffreFormationDto>();
		try {
			if (ouvertureOfSearchDto != null) {
				List<OuvertureOffreFormationDto> ouvertureOfs = ouvertureOffreFormationService
						.findAdvanced(ouvertureOfSearchDto);
				if (ouvertureOfs != null && !ouvertureOfs.isEmpty()) {
					for (OuvertureOffreFormationDto ouvertureOffreFormationDto : ouvertureOfs) {
						OffreFormationDto ofDto = offreFormationService
								.findById(ouvertureOffreFormationDto
										.getOffreFormationId().intValue());
						ouvertureOffreFormationDto
								.setOffreFormationlibelle(ofDto
										.getLibelleLongFr());
						ouvertureOfResultSearch.add(ouvertureOffreFormationDto);
					}
				}
			}
		} catch (Exception e) {
			ouvertureOfResultSearch = new ArrayList<OuvertureOffreFormationDto>();
			exception = 1;
			Utility.showErrorMessage(e.getMessage());
			
		}

	}

	/**
	 * [OuvertureOfSearchBean.exception] Getter
	 * 
	 * @author BELDI Jamel on : 13 mai 2014 12:21:37
	 * @return the exception
	 */
	public int getException() {
		return exception;
	}

	/**
	 * [OuvertureOfSearchBean.exception] Setter
	 * 
	 * @author BELDI Jamel on : 13 mai 2014 12:21:37
	 * @param exception
	 *            the exception to set
	 */
	public void setException(int exception) {
		this.exception = exception;
	}

	/**
	 * [OuvertureOfSearchBean.ouvertureOfResultSearch] Getter
	 * 
	 * @author BELDI Jamel on : 13 mai 2014 12:21:37
	 * @return the ouvertureOfResultSearch
	 */
	public List<OuvertureOffreFormationDto> getOuvertureOfResultSearch() {
		return ouvertureOfResultSearch;
	}

	/**
	 * [OuvertureOfSearchBean.ouvertureOfResultSearch] Setter
	 * 
	 * @author BELDI Jamel on : 13 mai 2014 12:21:37
	 * @param ouvertureOfResultSearch
	 *            the ouvertureOfResultSearch to set
	 */
	public void setOuvertureOfResultSearch(
			List<OuvertureOffreFormationDto> ouvertureOfResultSearch) {
		this.ouvertureOfResultSearch = ouvertureOfResultSearch;
	}

	/**
	 * [OuvertureOfSearchBean.ouvertureOfSearchDto] Getter
	 * 
	 * @author BELDI Jamel on : 13 mai 2014 12:21:37
	 * @return the ouvertureOfSearchDto
	 */
	public OuvertureOffreFormationDto getOuvertureOfSearchDto() {
		// if(sessionBean.containsKey("ouvertureOfSearchDto")){
		// ouvertureOfSearchDto = (OuvertureOffreFormationDto)
		// sessionBean.getValueForKey("ouvertureOfSearchDto");
		// }else{
		// ouvertureOfSearchDto = new OuvertureOffreFormationDto();
		// ouvertureOfSearchDto.setRefCodeEtablissement("esi");//TODO
		// Etablissement of connected User
		//
		// }

		return ouvertureOfSearchDto;
	}

	/**
	 * [OuvertureOfSearchBean.ouvertureOfSearchDto] Setter
	 * 
	 * @author BELDI Jamel on : 13 mai 2014 12:21:37
	 * @param ouvertureOfSearchDto
	 *            the ouvertureOfSearchDto to set
	 */
	public void setOuvertureOfSearchDto(
			OuvertureOffreFormationDto ouvertureOfSearchDto) {
		this.ouvertureOfSearchDto = ouvertureOfSearchDto;
	}

	/**
	 * [OuvertureOfSearchBean.offreFormationService] Getter
	 * 
	 * @author BELDI Jamel on : 13 mai 2014 12:21:37
	 * @return the offreFormationService
	 */
	public OffreFormationService getOffreFormationService() {
		return offreFormationService;
	}

	/**
	 * [OuvertureOfSearchBean.offreFormationService] Setter
	 * 
	 * @author BELDI Jamel on : 13 mai 2014 12:21:37
	 * @param offreFormationService
	 *            the offreFormationService to set
	 */
	public void setOffreFormationService(
			OffreFormationService offreFormationService) {
		this.offreFormationService = offreFormationService;
	}

	/**
	 * [OuvertureOfSearchBean.anneeAcademiqueService] Getter
	 * 
	 * @author BELDI Jamel on : 13 mai 2014 12:21:37
	 * @return the anneeAcademiqueService
	 */
	public AnneeAcademiqueService getAnneeAcademiqueService() {
		return anneeAcademiqueService;
	}

	/**
	 * [OuvertureOfSearchBean.anneeAcademiqueService] Setter
	 * 
	 * @author BELDI Jamel on : 13 mai 2014 12:21:37
	 * @param anneeAcademiqueService
	 *            the anneeAcademiqueService to set
	 */
	public void setAnneeAcademiqueService(
			AnneeAcademiqueService anneeAcademiqueService) {
		this.anneeAcademiqueService = anneeAcademiqueService;
	}

	/**
	 * [OuvertureOfSearchBean.ouvertureOffreFormationService] Getter
	 * 
	 * @author BELDI Jamel on : 13 mai 2014 12:21:37
	 * @return the ouvertureOffreFormationService
	 */
	public OuvertureOffreFormationService getOuvertureOffreFormationService() {
		return ouvertureOffreFormationService;
	}

	/**
	 * [OuvertureOfSearchBean.ouvertureOffreFormationService] Setter
	 * 
	 * @author BELDI Jamel on : 13 mai 2014 12:21:37
	 * @param ouvertureOffreFormationService
	 *            the ouvertureOffreFormationService to set
	 */
	public void setOuvertureOffreFormationService(
			OuvertureOffreFormationService ouvertureOffreFormationService) {
		this.ouvertureOffreFormationService = ouvertureOffreFormationService;
	}

	/**
	 * [OuvertureOfSearchBean.flash] Getter
	 * 
	 * @author BELDI Jamel on : 13 mai 2014 12:21:37
	 * @return the flash
	 */
	public Flash getFlash() {
		return flash;
	}

	/**
	 * [OuvertureOfSearchBean.flash] Setter
	 * 
	 * @author BELDI Jamel on : 13 mai 2014 12:21:37
	 * @param flash
	 *            the flash to set
	 */
	public void setFlash(Flash flash) {
		this.flash = flash;
	}

	/**
	 * [OuvertureOfSearchBean.sessionBean] Getter
	 * 
	 * @author BELDI Jamel on : 13 mai 2014 12:29:37
	 * @return the sessionBean
	 */
	public SessionBean getSessionBean() {
		return sessionBean;
	}

	/**
	 * [OuvertureOfSearchBean.sessionBean] Setter
	 * 
	 * @author BELDI Jamel on : 13 mai 2014 12:29:37
	 * @param sessionBean
	 *            the sessionBean to set
	 */
	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}

	/**
	 * [OuvertureOfSearchBean.listeOffres] Getter
	 * 
	 * @author BELDI Jamel on : 13 mai 2014 16:21:47
	 * @return the listeOffres
	 */
	public List<SelectItem> getListeOffres() {
		try {
			if (listeOffres == null || listeOffres.isEmpty()) {
				listeOffres = new ArrayList<SelectItem>();
				List<OffreFormationDto> offresHabilites = offreFormationService
						.findByCodeSituation(UtilConstants.OFFRE_FORMATION_SITUTAION_HABILITEE_CODE);
				if (offresHabilites != null && !offresHabilites.isEmpty()) {
					for (OffreFormationDto ofDto : offresHabilites) {
						// OuvertureOffreFormationDto searchDto = new
						// OuvertureOffreFormationDto();
						// searchDto.setRefCodeEtablissement("esi");
						// searchDto.setOffreFormationId(ofDto.getId());
						// List<OuvertureOffreFormationDto> ouvertures =
						// ouvertureOffreFormationService.findAdvanced(searchDto
						// );
						// if(ouvertures!=null && !ouvertures.isEmpty()){
						SelectItem si = new SelectItem(ofDto.getId(),
								ofDto.getLibelleLongFr());
						listeOffres.add(si);
						// }
					}
				}
			}
		} catch (Exception e) {
			ouvertureOfResultSearch = new ArrayList<OuvertureOffreFormationDto>();
			exception = 2;
			Utility.showErrorMessage(e.getMessage());
		}
		return listeOffres;
	}

	/**
	 * [OuvertureOfSearchBean.listeOffres] Setter
	 * 
	 * @author BELDI Jamel on : 13 mai 2014 16:21:47
	 * @param listeOffres
	 *            the listeOffres to set
	 */
	public void setListeOffres(List<SelectItem> listeOffres) {
		this.listeOffres = listeOffres;
	}

	/**
	 * [OuvertureOfSearchBean.listeAnneeAcademiques] Getter
	 * 
	 * @author BELDI Jamel on : 13 mai 2014 16:21:47
	 * @return the listeAnneeAcademiques
	 */
	public List<SelectItem> getListeAnneeAcademiques() {
		try {
			if (listeAnneeAcademiques == null
					|| listeAnneeAcademiques.isEmpty()) {
				listeAnneeAcademiques = new ArrayList<SelectItem>();
				List<AnneeAcademiqueDto> liste = anneeAcademiqueService
						.findAll();
				if (liste != null && !liste.isEmpty()) {
					for (AnneeAcademiqueDto anneeAcademiqueDto : liste) {
						SelectItem si = new SelectItem(
								anneeAcademiqueDto.getId(),
								anneeAcademiqueDto.getPremiereAnnee() + "/"
										+ anneeAcademiqueDto.getDeuxiemeAnnee());
						listeAnneeAcademiques.add(si);
					}
				}
			}

		} catch (Exception e) {
			ouvertureOfResultSearch = new ArrayList<OuvertureOffreFormationDto>();
			exception = 3;
			Utility.showErrorMessage(e.getMessage());
		}
		return listeAnneeAcademiques;
	}

	/**
	 * [OuvertureOfSearchBean.listeAnneeAcademiques] Setter
	 * 
	 * @author BELDI Jamel on : 13 mai 2014 16:21:47
	 * @param listeAnneeAcademiques
	 *            the listeAnneeAcademiques to set
	 */
	public void setListeAnneeAcademiques(List<SelectItem> listeAnneeAcademiques) {
		this.listeAnneeAcademiques = listeAnneeAcademiques;
	}

	/**
	 * [OuvertureOfSearchBean.listeEtabs] Getter
	 * 
	 * @author BELDI Jamel on : 13 mai 2014 16:21:47
	 * @return the listeEtabs
	 */
	public List<SelectItem> getListeEtabs() {
		// TODO list etablissement
		listeEtabs = new ArrayList<SelectItem>();
		SelectItem si = new SelectItem("esi", "ESI");
		listeEtabs.add(si);
		return listeEtabs;
	}

	/**
	 * [OuvertureOfSearchBean.listeEtabs] Setter
	 * 
	 * @author BELDI Jamel on : 13 mai 2014 16:21:47
	 * @param listeEtabs
	 *            the listeEtabs to set
	 */
	public void setListeEtabs(List<SelectItem> listeEtabs) {
		this.listeEtabs = listeEtabs;
	}

}
