/**
 * [dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.grouepedagogique.Etudiant.java] 
 * @author MAKERRI Sofiane on : 22 juil. 2014  14:16:25
 */
package dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.grouepedagogique;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.primefaces.component.picklist.PickList;
import org.primefaces.model.DualListModel;
import org.primefaces.model.StreamedContent;

/**
 * @author MAKERRI Sofiane on : 22 juil. 2014 14:16:25
 */
@ManagedBean(name = "etudiant")
@ViewScoped
@FacesConverter(forClass = Etudiant.class, value = "etudiant")
public class Etudiant implements Serializable, Converter {

	/**
	 * serialVersionUID
	 * 
	 * @author MAKERRI Sofiane on : 22 juil. 2014 14:25:09
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * [Etudiant.Etudiant()] Constructor
	 * 
	 * @author MAKERRI Sofiane on : 22 juil. 2014 14:16:25
	 */
	private String matricule;
	private String nom;
	private String prenom;
	private String dateNaissance;
	private String urlPhoto;
	private String photoName;
	private Integer idDossier;
	private Integer id;
	private Integer individuId;
	private StreamedContent photo;
	private String nomPrenom;
	private String numeroInscription;
	private Integer diplomeFinEtudeId;

	// private static Map<Object, String> entities = new WeakHashMap<Object,
	// String>();
	public Etudiant() {
		super();
	}

	/**
	 * [Etudiant.matricule] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 juil. 2014 14:20:40
	 * @return the matricule
	 */
	public String getMatricule() {
		return matricule;
	}

	/**
	 * [Etudiant.matricule] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 juil. 2014 14:20:40
	 * @param matricule
	 *            the matricule to set
	 */
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	/**
	 * [Etudiant.urlPhoto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 11 aoï¿½t 2014 16:35:33
	 * @return the urlPhoto
	 */
	public String getUrlPhoto() {
		return urlPhoto;
	}

	/**
	 * [Etudiant.urlPhoto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 11 aoï¿½t 2014 16:35:33
	 * @param urlPhoto
	 *            the urlPhoto to set
	 */
	public void setUrlPhoto(String urlPhoto) {
		this.urlPhoto = urlPhoto;
	}

	/**
	 * [Etudiant.idDossier] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 juil. 2014 14:20:40
	 * @return the idDossier
	 */
	public Integer getIdDossier() {
		return idDossier;
	}

	/**
	 * [Etudiant.idDossier] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 juil. 2014 14:20:40
	 * @param idDossier
	 *            the idDossier to set
	 */
	public void setIdDossier(Integer idDossier) {
		this.idDossier = idDossier;
	}

	/**
	 * [Etudiant.nom] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 juil. 2014 15:42:11
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * [Etudiant.nom] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 juil. 2014 15:42:11
	 * @param nom
	 *            the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * [Etudiant.prenom] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 juil. 2014 15:42:11
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * [Etudiant.prenom] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 juil. 2014 15:42:11
	 * @param prenom
	 *            the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/**
	 * [Etudiant.id] Getter
	 * 
	 * @author MAKERRI Sofiane on : 11 aoï¿½t 2014 12:18:21
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * [Etudiant.id] Setter
	 * 
	 * @author MAKERRI Sofiane on : 11 aoï¿½t 2014 12:18:21
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * [Etudiant.photo] Getter
	 * 
	 * @author MAKERRI Sofiane on : 11 aoï¿½t 2014 16:36:11
	 * @return the photo
	 */
	public StreamedContent getPhoto() {
		/*
		String path = "c:\\photos\\1.jpg";

		try {

			if (!Files.exists(Paths.get(path))) {
				return null;
			}
			String contentType = FacesContext.getCurrentInstance()
					.getExternalContext().getMimeType(path);

			InputStream inputStream = new FileInputStream(path);
			photo = new DefaultStreamedContent(inputStream, contentType);

			return photo;
		} catch (Exception e) {
            this.photo = null;
		}*/
		return photo;

	}

	/**
	 * [Etudiant.photo] Setter
	 * 
	 * @author MAKERRI Sofiane on : 11 aoï¿½t 2014 16:36:11
	 * @param photo
	 *            the photo to set
	 */
	public void setPhoto(StreamedContent photo) {
		this.photo = photo;
	}

	/**
	 * [Etudiant.individuId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 12 aoï¿½t 2014 09:01:07
	 * @return the individuId
	 */
	public Integer getIndividuId() {
		return individuId;
	}

	/**
	 * [Etudiant.individuId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 12 aoï¿½t 2014 09:01:07
	 * @param individuId
	 *            the individuId to set
	 */
	public void setIndividuId(Integer individuId) {
		this.individuId = individuId;
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		Object ret = null;
		if (component instanceof PickList) {
			Object dualList = ((PickList) component).getValue();
			DualListModel dl = (DualListModel) dualList;
			for (Object o : dl.getSource()) {
				String id = ((Etudiant) o).getNumeroInscription();
				if (value.equals(id)) {
					ret = o;
					break;
				}
			}
			if (ret == null)
				for (Object o : dl.getTarget()) {
					String id = ((Etudiant) o).getNumeroInscription();
					if (value.equals(id)) {
						ret = o;
						break;
					}
				}
		}
		return ret;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		String str = null;
		if (value instanceof Etudiant) {
			str = ((Etudiant) value).getNumeroInscription();
		}
		if (str == null) {
			str = "";
		}
		return str;
	}

	/**
	 * [Etudiant.nomPrenom] Getter
	 * 
	 * @author MAKERRI Sofiane on : 2 sept. 2014 17:58:12
	 * @return the nomPrenom
	 */
	public String getNomPrenom() {
		nomPrenom = nom + " " + prenom;
		return nomPrenom;
	}

	/**
	 * [Etudiant.nomPrenom] Setter
	 * 
	 * @author MAKERRI Sofiane on : 2 sept. 2014 17:58:12
	 * @param nomPrenom
	 *            the nomPrenom to set
	 */
	public void setNomPrenom(String nomPrenom) {
		this.nomPrenom = nomPrenom;
	}

	/**
	 * [Etudiant.dateNaissance] Getter
	 * 
	 * @author MAKERRI Sofiane on : 10 sept. 2014 09:20:05
	 * @return the dateNaissance
	 */
	public String getDateNaissance() {
		return dateNaissance;
	}

	/**
	 * [Etudiant.dateNaissance] Setter
	 * 
	 * @author MAKERRI Sofiane on : 10 sept. 2014 09:20:05
	 * @param dateNaissance
	 *            the dateNaissance to set
	 */
	public void setDateNaissance(String dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	/**
	 * [Etudiant.photoName] Getter 
	 * @author MAKERRI Sofiane on : 21 sept. 2014  17:31:06
	 * @return the photoName
	 */
	public String getPhotoName() {
		return photoName;
	}

	/**
	 * [Etudiant.photoName] Setter 
	 * @author MAKERRI Sofiane on : 21 sept. 2014  17:31:06
	 * @param photoName the photoName to set
	 */
	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}

	/**
	 * [Etudiant.numeroInscription] Getter 
	 * @author MAKERRI Sofiane on : 10 févr. 2015  11:15:50
	 * @return the numeroInscription
	 */
	public String getNumeroInscription() {
		return numeroInscription;
	}

	/**
	 * [Etudiant.numeroInscription] Setter 
	 * @author MAKERRI Sofiane on : 10 févr. 2015  11:15:50
	 * @param numeroInscription the numeroInscription to set
	 */
	public void setNumeroInscription(String numeroInscription) {
		this.numeroInscription = numeroInscription;
	}

	/**
	 * [Etudiant.diplomeFinEtudeId] Getter 
	 * @author MAKERRI Sofiane on : 25 févr. 2015  09:18:48
	 * @return the diplomeFinEtudeId
	 */
	public Integer getDiplomeFinEtudeId() {
		return diplomeFinEtudeId;
	}

	/**
	 * [Etudiant.diplomeFinEtudeId] Setter 
	 * @author MAKERRI Sofiane on : 25 févr. 2015  09:18:48
	 * @param diplomeFinEtudeId the diplomeFinEtudeId to set
	 */
	public void setDiplomeFinEtudeId(Integer diplomeFinEtudeId) {
		this.diplomeFinEtudeId = diplomeFinEtudeId;
	}
	
	

}
