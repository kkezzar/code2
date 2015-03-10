package dz.gov.mesrs.sii.commons.business.service.notification;

import java.util.List;

import dz.gov.mesrs.sii.commons.business.model.dto.AbstractNotificationSearchDto;
import dz.gov.mesrs.sii.commons.business.model.dto.NotificationAdminSearchDto;
import dz.gov.mesrs.sii.commons.business.model.dto.NotificationTraceDto;


public interface NotificationTraceService {
	
	void save(NotificationTraceDto dto);
	
	List<NotificationTraceDto> findAll(AbstractNotificationSearchDto searchDto);
	
	//TODO pagination

}
