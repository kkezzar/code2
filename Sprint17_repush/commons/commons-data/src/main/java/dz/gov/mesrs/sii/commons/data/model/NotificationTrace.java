package dz.gov.mesrs.sii.commons.data.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import dz.gov.mesrs.sii.commons.data.model.referentiel.Nomenclature;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefEtablissement;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefIndividu;

/**
 * The persistent class for table ppm.notification_trace
 * 
 * @author abelaid
 * 
 */
@Entity
@Table(name = "notification_trace", schema = "ppm")
public class NotificationTrace implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1088763371738169373L;

	@SequenceGenerator(name = "notification_trace_id_seq", initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notification_trace_id_seq")
	@Id
	private long id;

	@Column(name = "expediteur")
	private String expediteur;

	@Column(name = "objet")
	private String objet;

	@Column(name = "message")
	private String message;

	@Column(name = "notification_date")
	private Date notificationDate;

	@ManyToOne
	@JoinColumn(name = "nc_mode_notification")
	private Nomenclature modeNotification;

	@ManyToOne
	@JoinColumn(name = "ref_individu_notifie")
	private RefIndividu refIndividuNotifie;

	@ManyToOne
	@JoinColumn(name = "ref_individu_notificateur")
	private RefIndividu refIndividuNotificateur;

	@ManyToOne
	@JoinColumn(name = "ref_etablissement")
	private RefEtablissement refEtablissement;

	private String destination;

	public NotificationTrace() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getExpediteur() {
		return expediteur;
	}

	public void setExpediteur(String expediteur) {
		this.expediteur = expediteur;
	}

	public String getObjet() {
		return objet;
	}

	public void setObjet(String objet) {
		this.objet = objet;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getNotificationDate() {
		return notificationDate;
	}

	public void setNotificationDate(Date notificationDate) {
		this.notificationDate = notificationDate;
	}

	public Nomenclature getModeNotification() {
		return modeNotification;
	}

	public void setModeNotification(Nomenclature modeNotification) {
		this.modeNotification = modeNotification;
	}

	public RefIndividu getRefIndividuNotifie() {
		return refIndividuNotifie;
	}

	public void setRefIndividuNotifie(RefIndividu refIndividuNotifie) {
		this.refIndividuNotifie = refIndividuNotifie;
	}

	public RefIndividu getRefIndividuNotificateur() {
		return refIndividuNotificateur;
	}

	public void setRefIndividuNotificateur(RefIndividu refIndividuNotificateur) {
		this.refIndividuNotificateur = refIndividuNotificateur;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public void setRefEtablissement(RefEtablissement refEtablissement) {
		this.refEtablissement = refEtablissement;
	}

	public RefEtablissement getRefEtablissement() {
		return refEtablissement;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((destination == null) ? 0 : destination.hashCode());
		result = prime * result
				+ ((expediteur == null) ? 0 : expediteur.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime
				* result
				+ ((modeNotification == null) ? 0 : modeNotification.hashCode());
		result = prime
				* result
				+ ((notificationDate == null) ? 0 : notificationDate.hashCode());
		result = prime * result + ((objet == null) ? 0 : objet.hashCode());
		result = prime
				* result
				+ ((refEtablissement == null) ? 0 : refEtablissement.hashCode());
		result = prime
				* result
				+ ((refIndividuNotificateur == null) ? 0
						: refIndividuNotificateur.hashCode());
		result = prime
				* result
				+ ((refIndividuNotifie == null) ? 0 : refIndividuNotifie
						.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NotificationTrace other = (NotificationTrace) obj;
		if (destination == null) {
			if (other.destination != null)
				return false;
		} else if (!destination.equals(other.destination))
			return false;
		if (expediteur == null) {
			if (other.expediteur != null)
				return false;
		} else if (!expediteur.equals(other.expediteur))
			return false;
		if (id != other.id)
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (modeNotification == null) {
			if (other.modeNotification != null)
				return false;
		} else if (!modeNotification.equals(other.modeNotification))
			return false;
		if (notificationDate == null) {
			if (other.notificationDate != null)
				return false;
		} else if (!notificationDate.equals(other.notificationDate))
			return false;
		if (objet == null) {
			if (other.objet != null)
				return false;
		} else if (!objet.equals(other.objet))
			return false;
		if (refEtablissement == null) {
			if (other.refEtablissement != null)
				return false;
		} else if (!refEtablissement.equals(other.refEtablissement))
			return false;
		if (refIndividuNotificateur == null) {
			if (other.refIndividuNotificateur != null)
				return false;
		} else if (!refIndividuNotificateur
				.equals(other.refIndividuNotificateur))
			return false;
		if (refIndividuNotifie == null) {
			if (other.refIndividuNotifie != null)
				return false;
		} else if (!refIndividuNotifie.equals(other.refIndividuNotifie))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "NotificationTrace [id=" + id + ", expediteur=" + expediteur
				+ ", objet=" + objet + ", message=" + message
				+ ", notificationDate=" + notificationDate
				+ ", modeNotification=" + modeNotification
				+ ", refIndividuNotifie=" + refIndividuNotifie
				+ ", refIndividuNotificateur=" + refIndividuNotificateur
				+ ", refEtablissement=" + refEtablissement + ", destination="
				+ destination + "]";
	}

}
