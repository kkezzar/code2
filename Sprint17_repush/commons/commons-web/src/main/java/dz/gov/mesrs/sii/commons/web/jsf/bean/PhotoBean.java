package dz.gov.mesrs.sii.commons.web.jsf.bean;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import dz.gov.mesrs.sii.commons.web.jsf.bean.SessionBean;

@ManagedBean(name = "photoBean")
@RequestScoped
public class PhotoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private StreamedContent photo;
	@ManagedProperty(value = "#{sessionBean}")
	private SessionBean sessionBean;
	@ManagedProperty("#{param.url}")
	private String url;

	public PhotoBean() {

	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		if (url != null && (url.equals("") || url.equals("null"))) {
			this.url = null;
		} else {
			this.url = url;
		}
	}

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
					String contentType = FacesContext.getCurrentInstance().getExternalContext().getMimeType(path);

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

	public SessionBean getSessionBean() {
		return sessionBean;
	}

	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}

}
