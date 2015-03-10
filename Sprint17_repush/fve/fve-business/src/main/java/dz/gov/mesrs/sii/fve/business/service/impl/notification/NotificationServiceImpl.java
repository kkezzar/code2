package dz.gov.mesrs.sii.fve.business.service.impl.notification;

import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import dz.gov.mesrs.sii.commons.business.model.dto.NotificationTraceDto;
import dz.gov.mesrs.sii.commons.business.service.notification.NotificationTraceService;
import dz.gov.mesrs.sii.commons.business.util.CommonsConstants;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DossierTransfertDto;
import dz.gov.mesrs.sii.fve.business.service.notification.NotificationService;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.service.NomenclatureService;

@Service("notificationService")
public class NotificationServiceImpl implements NotificationService {

    private final static Logger logger = LoggerFactory.getLogger(NotificationServiceImpl.class.getName());

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private VelocityEngine velocityEngine;

    @Autowired
    private NotificationTraceService notificationTraceService;

    @Value("${fve.sender.mail.account}")
    private String fveMailAccount;

    @Value("${transfer.refused.subject}")
    private String transferRefusedSubject;

    @Value("${transfer.aproved.subject}")
    private String transferAprovedSubject;

    @Autowired
    private NomenclatureService nomenclatureService;

    @Override
    public void notifyTransferResult(int idEtablissement, DossierTransfertDto dto) {
	logger.debug("Trying to send transfer notification for DossierTransfertDto {}", new Object[] { dto.getId() });
	if (dto.getIndividuEmail() == null) {
	    logger.warn(
		    "Individu {} can't be notified for transfer decision because he doesn't have provided an email ",
		    dto.getIndividuNomLatin());
	    return;
	} else {
	    try {
		boolean aproved = dto.getIsAccordee() == Boolean.TRUE ? true : false;
		if (aproved) {
		    sendTransferAproved(idEtablissement, dto);
		} else {
		    sendTransferRefused(idEtablissement, dto);
		}
	    } catch (Exception e) {
		// TODO remonter l'exception
		logger.error("La notification n'as pu etre envoyee", e);
	    }

	}

    }

    private void sendTransferRefused(int idEtablissement, final DossierTransfertDto dto) {
	final Map<String, Object> model = new HashMap<>();
	model.put("civility", dto.getIndividuCivility());
	model.put("studentName", dto.getIndividuNomLatin());
	model.put("sector", dto.getFiliereDemandeeLibelleFr());
	model.put("askedDomain", dto.getDomaineDemandeLibelleFr());
	model.put("transferRequestDate", dto.getDateCreation());

	final String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,
		NotificationService.MAIL_TEMPLATE_FOLDER + NotificationService.TRANSFER_REFUSED_MAIL_TEMPLATE, model);

	MimeMessagePreparator preparator = new MimeMessagePreparator() {
	    public void prepare(MimeMessage mimeMessage) throws Exception {
		MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
		message.setTo(dto.getIndividuEmail());
		message.setFrom(fveMailAccount);
		message.setSubject(transferRefusedSubject);

		message.setText(text, true);

	    }
	};
	mailSender.send(preparator);
	NomenclatureDto modeNotificationDto = nomenclatureService.findByCode(
		CommonsConstants.NC_MODE_NOTIFICATION_NAME, CommonsConstants.NC_MODE_NOTIFICATION_EMAIL_CODE);
	notificationTraceService.save(new NotificationTraceDto(idEtablissement, 1, dto.getIndividuId(), fveMailAccount,
		text, transferRefusedSubject, modeNotificationDto, dto.getIndividuEmail()));

    }

    private void sendTransferAproved(int idEtablissement, final DossierTransfertDto dto) {
	final Map<String, Object> model = new HashMap<>();
	model.put("civility", dto.getIndividuCivility());
	model.put("studentName", dto.getIndividuNomLatin());
	model.put("sector", dto.getFiliereDemandeeLibelleFr());
	model.put("askedDomain", dto.getDomaineDemandeLibelleFr());
	model.put("transferRequestDate", dto.getDateCreation());

	final String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,
		NotificationService.MAIL_TEMPLATE_FOLDER + NotificationService.TRANSFER_APROVED_MAIL_TEMPLATE, model);

	MimeMessagePreparator preparator = new MimeMessagePreparator() {
	    public void prepare(MimeMessage mimeMessage) throws Exception {
		MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
		message.setTo(dto.getIndividuEmail());
		message.setFrom(fveMailAccount);
		message.setSubject(transferAprovedSubject);
		message.setText(text, true);
	    }
	};
	mailSender.send(preparator);
	NomenclatureDto modeNotificationDto = nomenclatureService.findByCode(
		CommonsConstants.NC_MODE_NOTIFICATION_NAME, CommonsConstants.NC_MODE_NOTIFICATION_EMAIL_CODE);
	notificationTraceService.save(new NotificationTraceDto(idEtablissement, 1, dto.getIndividuId(), fveMailAccount,
		text, transferAprovedSubject, modeNotificationDto, dto.getIndividuEmail()));

    }

    public void setVelocityEngine(VelocityEngine velocityEngine) {
	this.velocityEngine = velocityEngine;
    }

    public void setMailSender(JavaMailSender mailSender) {
	this.mailSender = mailSender;
    }

    public void setNotificationTraceService(NotificationTraceService notificationTraceService) {
	this.notificationTraceService = notificationTraceService;
    }

    public void setFveMailAccount(String fveMailAccount) {
	this.fveMailAccount = fveMailAccount;
    }

    public void setTransferRefusedSubject(String transferRefusedSubject) {
	this.transferRefusedSubject = transferRefusedSubject;
    }

    public void setTransferAprovedSubject(String transferAprovedSubject) {
	this.transferAprovedSubject = transferAprovedSubject;
    }

}
