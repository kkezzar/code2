/**
 * [dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.examen.controlecontinu.ImageBean.java] 
 * @author MAKERRI Sofiane on : 18 oct. 2014  10:14:01
 */
package dz.gov.mesrs.sii.fve.web.jsf.bean;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import dz.gov.mesrs.sii.referentiel.business.util.ConfigHolder;

/**
 * @author MAKERRI Sofiane on : 18 oct. 2014 10:14:01
 */
@ManagedBean(name = "photoEtudiantBB")
@RequestScoped
public class PhotoEtudiantBB implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author MAKERRI Sofiane on : 18 oct. 2014 10:40:20
	 */
	private static final long serialVersionUID = 1L;

	
	@ManagedProperty(value = "#{configHolder}")
	private ConfigHolder configHolder;

	/**
	 * [ImageBean.ImageBean()] Constructor
	 * 
	 * @author MAKERRI Sofiane on : 18 oct. 2014 10:14:01
	 */
	private StreamedContent photo;
	@ManagedProperty(value = "#{sessionBeanFve}")
	private SessionBean sessionBean;
	@ManagedProperty("#{param.url}")
	private String url;

	private String height_photo;
	private String width_photo;

	public PhotoEtudiantBB() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void init() throws Exception {
        	height_photo = configHolder.getPhotoHeight();
        	width_photo = configHolder.getPhotoWidth();

	}

	/**
	 * [ImageBean.url] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 oct. 2014 10:15:03
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * [ImageBean.url] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 oct. 2014 10:15:03
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		if (url != null && (url.equals("") || url.equals("null"))) {
			this.url = null;
		} else {
			this.url = url;
		}
	}

	/**
	 * [PhotoEtudiantBB.getPhoto] Method
	 * 
	 * @author MAKERRI Sofiane on : 19 oct. 2014 10:56:26
	 * @return
	 */
	public StreamedContent getPhoto() {

		try {
			if (FacesContext.getCurrentInstance().getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
				// So, we're rendering the view. Return a stub StreamedContent
				// so that it will generate right URL.
				photo = new DefaultStreamedContent();
			} else {
				// So, browser is requesting the image. Return a real
				// StreamedContent with the image bytes.
				if (url != null && sessionBean.getFolderPhoto() != null) {
					String path = sessionBean.getFolderPhoto() + url;
					if (!Files.exists(Paths.get(path))) {
						if (sessionBean.getFolderTemp() != null) {
							path = sessionBean.getFolderTemp() + url;
						}
					}
					if (!Files.exists(Paths.get(path))) {
						return null;
					}
					String contentType = FacesContext.getCurrentInstance()
							.getExternalContext().getMimeType(path);

					InputStream inputStream = new FileInputStream(path);
					photo = new DefaultStreamedContent(inputStream, contentType);
				} else {
					photo = new DefaultStreamedContent();
				}
			}
		} catch (FileNotFoundException f) {
			photo = new DefaultStreamedContent();
		}
		return photo;
	}

	/**
	 * [PhotoEtudiantBB.sessionBean] Getter
	 * 
	 * @author MAKERRI Sofiane on : 6 nov. 2014 09:20:56
	 * @return the sessionBean
	 */
	public SessionBean getSessionBean() {
		return sessionBean;
	}

	/**
	 * [PhotoEtudiantBB.sessionBean] Setter
	 * 
	 * @author MAKERRI Sofiane on : 6 nov. 2014 09:20:56
	 * @param sessionBean
	 *            the sessionBean to set
	 */
	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}
    
        public void setConfigHolder(ConfigHolder configHolder) {
    	this.configHolder = configHolder;
        }
    
        public ConfigHolder getConfigHolder() {
    	return configHolder;
        }
	public String getHeight_photo() {
		return height_photo;
	}

	public void setHeight_photo(String height_photo) {
		this.height_photo = height_photo;
	}

	public String getWidth_photo() {
		return width_photo;
	}

	public void setWidth_photo(String width_photo) {
		this.width_photo = width_photo;
	}

}
