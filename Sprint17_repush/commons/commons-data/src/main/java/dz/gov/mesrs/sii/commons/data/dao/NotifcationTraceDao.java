package dz.gov.mesrs.sii.commons.data.dao;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.NotificationTrace;

public interface NotifcationTraceDao {
	
	void persist(NotificationTrace notificationTrace);

	List<NotificationTrace> findByEtablissementAndCriteria(int idEtablissement, String researchKeyWord);
	
	List<NotificationTrace> findByUserAndCriteria(int userId, String researchKeyWord);

}
