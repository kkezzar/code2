package dz.gov.mesrs.sii.commons.business.service.impl.notification;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.business.model.dto.AbstractNotificationSearchDto;
import dz.gov.mesrs.sii.commons.business.model.dto.NotificationAdminSearchDto;
import dz.gov.mesrs.sii.commons.business.model.dto.NotificationTraceDto;
import dz.gov.mesrs.sii.commons.business.service.notification.NotificationTraceService;
import dz.gov.mesrs.sii.commons.data.dao.NotifcationTraceDao;
import dz.gov.mesrs.sii.commons.data.model.NotificationTrace;
import dz.gov.mesrs.sii.commons.data.model.referentiel.Nomenclature;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefEtablissement;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefIndividu;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;


@Transactional
@Service("notificationTraceService")
public class NotificationTraceServiceImpl implements NotificationTraceService {

	private final static Logger logger = LoggerFactory.getLogger(NotificationTraceServiceImpl.class.getName());

	@Autowired
	private NotifcationTraceDao notifcationTraceDao;

	@Override
	public void save(NotificationTraceDto dto) {
		logger.debug("Saving new NotificationTrace : {}", new Object[] { dto });
		NotificationTrace notificationTrace = new NotificationTrace();
		map(notificationTrace, dto);
		notifcationTraceDao.persist(notificationTrace);
		logger.debug("NotificationTrace : {} saved", new Object[] { dto });

	}
	
	@Override
	public List<NotificationTraceDto> findAll(AbstractNotificationSearchDto searchDto) {
		logger.debug("Searching for notification : {}",new Object[] { searchDto });
		List<NotificationTrace> notificationTraces = null;
		
		if(searchDto instanceof NotificationAdminSearchDto){
			notificationTraces = notifcationTraceDao.findByEtablissementAndCriteria(searchDto.getId() , searchDto.getSearchKey()); 
		}else{
			notificationTraces = notifcationTraceDao.findByUserAndCriteria(searchDto.getId() , searchDto.getSearchKey());	
		}
		
		logger.debug("Found {} notifications", new Object[] { notificationTraces == null ? 0
						: notificationTraces.size() });
		List<NotificationTraceDto> dtos = new ArrayList<>();
		map(dtos, notificationTraces);
		return dtos;
	}

	private void map(NotificationTrace notificationTrace, NotificationTraceDto dto) {

		notificationTrace.setNotificationDate(new Date());
		
		RefEtablissement refEtablissement = new RefEtablissement();
		refEtablissement.setId(dto.getRefIdEtablissement());
		notificationTrace.setRefEtablissement(refEtablissement);

		RefIndividu individuDestinataire = new RefIndividu();
		individuDestinataire.setId(dto.getDestinataireRefIndividuId());
		notificationTrace.setRefIndividuNotifie(individuDestinataire);

		RefIndividu individuNotificateur = new RefIndividu();
		individuNotificateur.setId(dto.getNotificateurRefIndividuId());
		notificationTrace.setRefIndividuNotificateur(individuNotificateur);

		notificationTrace.setExpediteur(dto.getExpediteur());

		NomenclatureDto nomenclatureDto = dto.getModeNotificationDto();
		Nomenclature modeNotification = new Nomenclature(
				nomenclatureDto.getId(), nomenclatureDto.getCode());
		notificationTrace.setModeNotification(modeNotification);

		notificationTrace.setObjet(dto.getObjet());
		notificationTrace.setMessage(dto.getMessage());
		notificationTrace.setDestination(dto.getDestination());

	}

	

	private void map(List<NotificationTraceDto> dtos, List<NotificationTrace> notificationTraces) {
		if(notificationTraces==null){
			return;
		}
		for(NotificationTrace trace : notificationTraces){
			NomenclatureDto nomenclatureDto = new NomenclatureDto();
			nomenclatureDto.setLibelleLongFr(trace.getModeNotification().getLibelleLongFr());
			NotificationTraceDto dto = new NotificationTraceDto(trace.getId(),
					trace.getMessage(), trace.getObjet(), nomenclatureDto,
					trace.getNotificationDate(), trace.getRefIndividuNotifie()
							.getNomLatin(), trace.getRefIndividuNotifie()
							.getPrenomLatin(),trace.getExpediteur() , trace.getDestination());
			dtos.add(dto);
		}
		
	}

}
