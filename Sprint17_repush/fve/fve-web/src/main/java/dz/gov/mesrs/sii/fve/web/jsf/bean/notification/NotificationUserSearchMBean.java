package dz.gov.mesrs.sii.fve.web.jsf.bean.notification;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;

import dz.gov.mesrs.sii.commons.business.model.dto.NotificationTraceDto;
import dz.gov.mesrs.sii.commons.business.model.dto.NotificationUserSearchDto;
import dz.gov.mesrs.sii.commons.business.service.notification.NotificationTraceService;
import dz.gov.mesrs.sii.commons.web.jsf.bean.LoginBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.SessionBean;

@ViewScoped
@ManagedBean(name = "notificationUserSearchMBean")
public class NotificationUserSearchMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5572438368337011808L;

	private List<NotificationTraceDto> notificationTraceDtos;

	@ManagedProperty("#{searchValue}")
	private String searchValue;

	@ManagedProperty("#{notificationTraceService}")
	private NotificationTraceService notificationTraceService;

	@ManagedProperty(value = "#{loginBean}")
	private LoginBean loginBean;

	@ManagedProperty("#{sessionBeanFve}")
	private SessionBean sessionBean;

	private NotificationUserSearchDto notificationSearchDto;

	private NotificationTraceDto selectNotificationTraceDto;

	private boolean displayNotificationDetail;

	public NotificationUserSearchMBean() {
		super();
	}

	@PostConstruct
	public void init() {
		notificationSearchDto = new NotificationUserSearchDto(this.sessionBean.getSessionBean().getUser().getId());
		this.notificationTraceDtos = notificationTraceService.findAll(notificationSearchDto);
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	public void find() {
		notificationSearchDto.setSearchKey(searchValue);
		notificationTraceDtos = notificationTraceService.findAll(notificationSearchDto);
		setDisplayNotificationDetail(false);
	}

	public void onRowSelect(SelectEvent event) {
		this.selectNotificationTraceDto = (NotificationTraceDto) event.getObject();
		setDisplayNotificationDetail(true);
	}

	public List<NotificationTraceDto> getNotificationTraceDtos() {
		return notificationTraceDtos;
	}

	public void setNotificationTraceDtos(List<NotificationTraceDto> notificationTraceDtos) {
		this.notificationTraceDtos = notificationTraceDtos;
	}

	public NotificationTraceService getNotificationTraceService() {
		return notificationTraceService;
	}

	public void setNotificationTraceService(NotificationTraceService notificationTraceService) {
		this.notificationTraceService = notificationTraceService;
	}

	public String getSearchValue() {
		return searchValue;
	}

	public boolean isDisplayNotificationDetail() {
		return displayNotificationDetail;
	}

	public void setDisplayNotificationDetail(boolean displayNotificationDetail) {
		this.displayNotificationDetail = displayNotificationDetail;
	}

	// public NotificationAdminSearchDto getNotificationSearchDto() {
	// return notificationSearchDto;
	// }
	//
	// public void setNotificationSearchDto(NotificationAdminSearchDto
	// notificationSearchDto) {
	// this.notificationSearchDto = notificationSearchDto;
	// }

	public NotificationTraceDto getSelectNotificationTraceDto() {
		return selectNotificationTraceDto;
	}

	public void setSelectNotificationTraceDto(NotificationTraceDto selectNotificationTraceDto) {
		this.selectNotificationTraceDto = selectNotificationTraceDto;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}
}
