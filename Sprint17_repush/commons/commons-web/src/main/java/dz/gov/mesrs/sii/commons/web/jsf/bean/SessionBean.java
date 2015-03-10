package dz.gov.mesrs.sii.commons.web.jsf.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import dz.gov.mesrs.sii.commons.security.userdetails.ProgresUser;
import dz.gov.mesrs.sii.commons.web.jsf.bean.locator.CommonServiceLocatorBean;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefAffectationDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefCompteDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEtablissementDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefGroupeDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefIndividuDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefStructureDto;
import dz.gov.mesrs.sii.referentiel.business.util.ConfigHolder;

/**
 * /** Cette class permet de stocker les informations de la session de
 * l'utilisateur en cours.
 * 
 * @author MAKERRI Sofiane on : 3 févr. 2015 09:16:31
 * @author Mounir.MESSAOUDI on : 08 Fev. 2015 15:53:25
 */
@ManagedBean(name = "sessionBean")
@SessionScoped
public class SessionBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private ProgresUser currentUser;

	private String folderPhoto;
	private String folderTemp;
	private String allowTypesPhoto;
	private String limitSizePhoto;
	private String heightPhoto;
	private String widthPphoto;
	private Integer fontSize;

	@ManagedProperty(value = "#{configHolder}")
	private ConfigHolder configHolder;

	// the service locator of the business services
	@ManagedProperty(value = "#{commonServiceLocatorBean}")
	protected CommonServiceLocatorBean commonServiceLocator;

	public SessionBean() {
		super();
	}

	@PostConstruct
	public void init() {
		try {
			currentUser = (ProgresUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception ex) {

		}
		loadParamPhoto();
		fontSize = 11;
	}

	/**
	 * Changer l'affectation de l'utilisateur en cours
	 * 
	 * @param refAffectationDto
	 * @throws IOException
	 * 
	 * @author Mounir.MESSAOUDI on : 08 Fev. 2015 15:53:25
	 */
	public void selectAffectation(RefAffectationDto refAffectationDto) throws IOException {
		if (refAffectationDto != null) {
			currentUser.setCurrentAffectation(refAffectationDto);

			Authentication authentication = new UsernamePasswordAuthenticationToken(currentUser,
					currentUser.getPassword(), currentUser.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);

			FacesContext ctx = FacesContext.getCurrentInstance();
			ExternalContext extContext = ctx.getExternalContext();
			String url = extContext.encodeActionURL(ctx.getApplication().getViewHandler()
					.getActionURL(ctx, "/pages/index.xhtml?faces-redirect=true"));
			FacesContext.getCurrentInstance().getExternalContext().redirect(url);
		}
	}

	private void loadParamPhoto() {
		folderPhoto = configHolder.getPhotoUploadFolder() + "/";
		folderTemp = configHolder.getDocumentTempFolder() + "/";
		allowTypesPhoto = configHolder.getPhotoAllowType();
		limitSizePhoto = configHolder.getPhotoMaxSize();
		heightPhoto = configHolder.getPhotoHeight();
		widthPphoto = configHolder.getPhotoWidth();
	}

	public RefEtablissementDto getRefEtablissementDto() {
		if (currentUser != null) {
			return currentUser.getCurrentEtablissement();
		}
		return null;
	}

	public Integer getIdEtablissement() {
		if (currentUser != null) {
			return currentUser.getCurrentEtablissement().getId();
		}
		return null;
	}

	public String getCodeEtablissement() {
		if (currentUser != null) {
			return currentUser.getCurrentEtablissement().getIdentifiant();
		}
		return null;
	}

	public String getAncienCodeEtablissement() {
		if (currentUser != null) {
			return currentUser.getCurrentEtablissement().getAncienCode();
		}
		return null;
	}

	public String getLcLatinEtablissement() {
		if (currentUser != null) {
			return currentUser.getCurrentEtablissement().getLcEtablissementLatin();
		}
		return null;
	}

	public String getLcArabeEtablissement() {
		if (currentUser != null) {
			return currentUser.getCurrentEtablissement().getLcEtablissementArabe();
		}
		return null;
	}

	public String getLlLatinEtablissement() {
		if (currentUser != null) {
			return currentUser.getCurrentEtablissement().getLlEtablissementLatin();
		}
		return null;
	}

	public String getLlArabeEtablissement() {
		if (currentUser != null) {
			return currentUser.getCurrentEtablissement().getLlEtablissementArabe();
		}
		return null;
	}

	public RefIndividuDto getUser() {
		if (currentUser != null) {
			return currentUser.getUser();
		}
		return null;
	}

	public RefCompteDto getCompte() {
		if (currentUser != null) {
			return currentUser.getCompte();
		}
		return null;
	}

	public RefEtablissementDto getCurrentEtablissement() {
		if (currentUser != null) {
			return currentUser.getCurrentEtablissement();
		}
		return null;
	}

	public ConfigHolder getConfigHolder() {
		return configHolder;
	}

	public void setConfigHolder(ConfigHolder configHolder) {
		this.configHolder = configHolder;
	}

	public List<RefAffectationDto> getRefAffectationDtos() {
		if (currentUser != null) {
			return currentUser.getListAffectations();
		}
		return null;
	}

	public NomenclatureDto getCurrentRole() {
		if (currentUser != null) {
			return currentUser.getCurrentRole();
		}
		return null;
	}

	public RefStructureDto getCurrentRefStructureDto() {
		if (currentUser != null) {
			return currentUser.getCurrentStructure();
		}
		return null;
	}

	public RefGroupeDto getCurrentRefGroupeDto() {
		if (currentUser != null) {
			return currentUser.getCurrentGroupe();
		}
		return null;
	}

	public Integer getCurrentRefStructureId() {
		if (currentUser != null) {
			return currentUser.getCurrentStructure().getId();
		}
		return null;
	}

	public Integer getCurrentRefGroupeId() {
		if (currentUser != null) {
			return currentUser.getCurrentGroupe().getId();
		}
		return null;
	}

	public String getUserName() {
		if (currentUser != null) {
			return currentUser.getUsername();
		}
		return null;
	}

	public CommonServiceLocatorBean getCommonServiceLocator() {
		return commonServiceLocator;
	}

	public void setCommonServiceLocator(CommonServiceLocatorBean commonServiceLocator) {
		this.commonServiceLocator = commonServiceLocator;
	}

	public ProgresUser getCurrentUser() {
		return currentUser;
	}

	public String getFolderPhoto() {
		return folderPhoto;
	}

	public void setFolderPhoto(String folderPhoto) {
		this.folderPhoto = folderPhoto;
	}

	public String getFolderTemp() {
		return folderTemp;
	}

	public void setFolderTemp(String folderTemp) {
		this.folderTemp = folderTemp;
	}

	public String getAllowTypesPhoto() {
		return allowTypesPhoto;
	}

	public void setAllowTypesPhoto(String allowTypesPhoto) {
		this.allowTypesPhoto = allowTypesPhoto;
	}

	public String getLimitSizePhoto() {
		return limitSizePhoto;
	}

	public void setLimitSizePhoto(String limitSizePhoto) {
		this.limitSizePhoto = limitSizePhoto;
	}

	public String getHeightPhoto() {
		return heightPhoto;
	}

	public void setHeightPhoto(String heightPhoto) {
		this.heightPhoto = heightPhoto;
	}

	public String getWidthPphoto() {
		return widthPphoto;
	}

	public void setWidthPphoto(String widthPphoto) {
		this.widthPphoto = widthPphoto;
	}

	public Integer getFontSize() {
		return fontSize;
	}

	public void setFontSize(Integer fontSize) {
		this.fontSize = fontSize;
	}

	public void zoomIn() {

		if (fontSize != null && fontSize < 18) {
			fontSize = fontSize + 1;
		}
	}

	public void zoomOut() {

		if (fontSize != null && fontSize > 7) {
			fontSize = fontSize - 1;
		}
	}
}