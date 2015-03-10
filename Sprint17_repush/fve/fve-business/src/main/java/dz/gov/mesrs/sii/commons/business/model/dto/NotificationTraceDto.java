package dz.gov.mesrs.sii.commons.business.model.dto;

import java.io.Serializable;
import java.util.Date;

import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;

public class NotificationTraceDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7311892127423479316L;

	private long id;

	/**
	 * Reference de l'individu qui a declenche la notification.
	 */
	private int notificateurRefIndividuId;

	/**
	 * Reference de l'individu notifie.
	 */
	private int destinataireRefIndividuId;

	private String destinataireRefIndividuNom;

	private String destinataireRefIndividuPrenom;
	
	/**
	 * Email ou numero de telephone 
	 */
	private String destination;

	private String expediteur;

	/**
	 * Message : Corps de l'EMAIL , message SMS ou message affiche sur l'IHM.
	 */
	private String message;

	/**
	 * Objet du mail.
	 */
	private String objet;

	/**
	 * Mode de notification : EMAIL , SMS , IHM.
	 */
	private NomenclatureDto modeNotificationDto;

	private Date dateNotification;
	
	private int refIdEtablissement;

	public NotificationTraceDto(int idEtablissement , int notificateurRefIndividuId,
			int destinataireRefIndividuId, String expediteur, String message,
			String objet, NomenclatureDto modeNotificationDto , String email) {
		super();
		this.refIdEtablissement = idEtablissement;
		this.notificateurRefIndividuId = notificateurRefIndividuId;
		this.destinataireRefIndividuId = destinataireRefIndividuId;
		this.expediteur = expediteur;
		this.message = message;
		this.objet = objet;
		this.modeNotificationDto = modeNotificationDto;
		this.destination = email;
	}

	public NotificationTraceDto(int notificateurRefIndividuId,
			int destinataireRefIndividuId, String expediteur, String message,
			NomenclatureDto modeNotificationDto) {
		super();
		this.notificateurRefIndividuId = notificateurRefIndividuId;
		this.destinataireRefIndividuId = destinataireRefIndividuId;
		this.expediteur = expediteur;
		this.message = message;
		this.modeNotificationDto = modeNotificationDto;
	}

	public NotificationTraceDto(long id, String message, String objet,
			NomenclatureDto modeNotificationDto, Date dateNotification,
			String destinataireNom, String destinatairePrenom, String expediteur , String destination) {
		super();
		this.id = id;
		this.message = message;
		this.objet = objet;
		this.modeNotificationDto = modeNotificationDto;
		this.dateNotification = dateNotification;
		this.destinataireRefIndividuNom = destinataireNom;
		this.destinataireRefIndividuPrenom = destinatairePrenom;
		this.expediteur = expediteur;
		this.destination = destination;
	}

	public int getNotificateurRefIndividuId() {
		return notificateurRefIndividuId;
	}

	public int getDestinataireRefIndividuId() {
		return destinataireRefIndividuId;
	}

	public String getExpediteur() {
		return expediteur;
	}

	public String getMessage() {
		return message;
	}

	public String getObjet() {
		return objet;
	}

	public NomenclatureDto getModeNotificationDto() {
		return modeNotificationDto;
	}

	public Date getDateNotification() {
		return dateNotification;
	}

	public long getId() {
		return id;
	}

	public String getDestinataireRefIndividuNom() {
		return destinataireRefIndividuNom;
	}

	public String getDestinataireRefIndividuPrenom() {
		return destinataireRefIndividuPrenom;
	}

	public String getDestination() {
		return destination;
	}
	
	public int getRefIdEtablissement() {
		return refIdEtablissement;
	}

	@Override
	public String toString() {
		return "NotificationTraceDto [id=" + id
				+ ", notificateurRefIndividuId=" + notificateurRefIndividuId
				+ ", destinataireRefIndividuId=" + destinataireRefIndividuId
				+ ", destinataireRefIndividuNom=" + destinataireRefIndividuNom
				+ ", destinataireRefIndividuPrenom="
				+ destinataireRefIndividuPrenom + ", destination="
				+ destination + ", expediteur=" + expediteur + ", message="
				+ message + ", objet=" + objet + ", modeNotificationDto="
				+ modeNotificationDto + ", dateNotification="
				+ dateNotification + ", refIdEtablissement="
				+ refIdEtablissement + "]";
	}


}
