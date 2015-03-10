/**
 *  
 * @author Mounir.MESSAOUDI on : 22 sept. 2014 10:40:43
 */
package dz.gov.mesrs.sii.fve.web.jsf.bean.emploidutemps;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import dz.gov.mesrs.sii.fve.business.model.dto.cursus.GroupePedagogiqueDto;
import dz.gov.mesrs.sii.fve.business.service.cursus.AffectationGroupePedagogiqueService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.AnneeAcademiqueService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.AtomePedagogiqueService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OffreFormationService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OuvertureOffreFormationService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.PeriodeService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.RattachementMcService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.RepartitionUeService;
import dz.gov.mesrs.sii.fve.web.jsf.bean.SessionBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.CursusConstants;

/**
 * Managed bean pour la gestion des emplois du temps
 * 
 * @author Mounir.MESSAOUDI on : 22 sept. 2014 10:40:43
 */

@ManagedBean(name = "emploiDuTempsMBean")
@ViewScoped
public class EmploiDuTempsMBean {

	@ManagedProperty(value = "#{anneeAcademiqueService}")
	private AnneeAcademiqueService anneeAcademiqueService;

	@ManagedProperty(value = "#{sessionBeanFve}")
	private SessionBean sessionBean;

	@ManagedProperty(value = "#{affectationGroupePedagogiqueService}")
	private AffectationGroupePedagogiqueService affectationGroupePedagogiqueService;

	@ManagedProperty(value = "#{periodeService}")
	private PeriodeService periodeService;

	@ManagedProperty(value = "#{ouvertureOffreFormationService}")
	private OuvertureOffreFormationService ouvertureOffreFormationService;

	@ManagedProperty(value = "#{offreFormationService}")
	private OffreFormationService offreFormationService;

	@ManagedProperty(value = "#{repartitionUeService}")
	private RepartitionUeService repartitionUeService;

	@ManagedProperty(value = "#{rattachementMcService}")
	private RattachementMcService rattachementMcService;

	@ManagedProperty(value = "#{atomePedagogiqueService}")
	private AtomePedagogiqueService atomePedagogiqueService;

	// Dtos
	private GroupePedagogiqueDto groupePedagogiqueDto;

	// UI
	private String refCodeCurrEtablissement;
	private Integer idCurrAnneeAcademique;
	private String refCodeCurrAnneeAcademique;

	private Integer idAnneeAcademique;

	private List<SelectItem> listAnneeAcademique;
	private List<SelectItem> listDomaines;
	private List<SelectItem> listFilieres;
	private List<SelectItem> periodeList;
	private List<GroupePedagogiqueDto> listGroupePedagogiqueDto;
	private List<SelectItem> offreFormationList;

	private ResourceBundle bundleCommon;
	private ResourceBundle bundleEmploisDuTemps;

	private ScheduleModel eventModel;
	private ScheduleEvent event = new DefaultScheduleEvent();

	/**
	 * @author Mounir.MESSAOUDI on : 22 sept. 2014 10:40:43
	 */
	public EmploiDuTempsMBean() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		bundleCommon = facesContext.getApplication().getResourceBundle(
				facesContext, CursusConstants.COMMON_BUNDLE_MSG_NAME);
		bundleEmploisDuTemps = facesContext.getApplication().getResourceBundle(
				facesContext, CursusConstants.EMPLOI_DU_TEMPS_BUNDLE_MSG_NAME);
	}

	/**
	 * Post constructeur
	 * 
	 * @author Mounir.MESSAOUDI on : 22 sept. 2014 10:42:54
	 */
	@PostConstruct
	public void init() {
		try {
			this.refCodeCurrEtablissement = sessionBean.getCodeEtablissement();
			this.idCurrAnneeAcademique = sessionBean.getIdAnneeAcademique();
			this.refCodeCurrAnneeAcademique = sessionBean
					.getCodeAnneeAcademique();

			this.idAnneeAcademique = this.idCurrAnneeAcademique;

			eventModel = new DefaultScheduleModel();
			eventModel.addEvent(new DefaultScheduleEvent(
					"Champions League Match", previousDay8Pm(),
					previousDay11Pm()));
			eventModel.addEvent(new DefaultScheduleEvent("Birthday Party",
					today1Pm(), today6Pm()));
			eventModel.addEvent(new DefaultScheduleEvent(
					"Breakfast at Tiffanys", nextDay9Am(), nextDay11Am()));
			eventModel.addEvent(new DefaultScheduleEvent(
					"Plant the new garden stuff", theDayAfter3Pm(),
					fourDaysLater3pm()));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public AnneeAcademiqueService getAnneeAcademiqueService() {
		return anneeAcademiqueService;
	}

	public void setAnneeAcademiqueService(
			AnneeAcademiqueService anneeAcademiqueService) {
		this.anneeAcademiqueService = anneeAcademiqueService;
	}

	public SessionBean getSessionBean() {
		return sessionBean;
	}

	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}

	public AffectationGroupePedagogiqueService getAffectationGroupePedagogiqueService() {
		return affectationGroupePedagogiqueService;
	}

	public void setAffectationGroupePedagogiqueService(
			AffectationGroupePedagogiqueService affectationGroupePedagogiqueService) {
		this.affectationGroupePedagogiqueService = affectationGroupePedagogiqueService;
	}

	public PeriodeService getPeriodeService() {
		return periodeService;
	}

	public void setPeriodeService(PeriodeService periodeService) {
		this.periodeService = periodeService;
	}

	public OuvertureOffreFormationService getOuvertureOffreFormationService() {
		return ouvertureOffreFormationService;
	}

	public void setOuvertureOffreFormationService(
			OuvertureOffreFormationService ouvertureOffreFormationService) {
		this.ouvertureOffreFormationService = ouvertureOffreFormationService;
	}

	public OffreFormationService getOffreFormationService() {
		return offreFormationService;
	}

	public void setOffreFormationService(
			OffreFormationService offreFormationService) {
		this.offreFormationService = offreFormationService;
	}

	public RepartitionUeService getRepartitionUeService() {
		return repartitionUeService;
	}

	public void setRepartitionUeService(
			RepartitionUeService repartitionUeService) {
		this.repartitionUeService = repartitionUeService;
	}

	public RattachementMcService getRattachementMcService() {
		return rattachementMcService;
	}

	public void setRattachementMcService(
			RattachementMcService rattachementMcService) {
		this.rattachementMcService = rattachementMcService;
	}

	public AtomePedagogiqueService getAtomePedagogiqueService() {
		return atomePedagogiqueService;
	}

	public void setAtomePedagogiqueService(
			AtomePedagogiqueService atomePedagogiqueService) {
		this.atomePedagogiqueService = atomePedagogiqueService;
	}

	public GroupePedagogiqueDto getGroupePedagogiqueDto() {
		return groupePedagogiqueDto;
	}

	public void setGroupePedagogiqueDto(
			GroupePedagogiqueDto groupePedagogiqueDto) {
		this.groupePedagogiqueDto = groupePedagogiqueDto;
	}

	public String getRefCodeCurrEtablissement() {
		return refCodeCurrEtablissement;
	}

	public void setRefCodeCurrEtablissement(String refCodeCurrEtablissement) {
		this.refCodeCurrEtablissement = refCodeCurrEtablissement;
	}

	public Integer getIdCurrAnneeAcademique() {
		return idCurrAnneeAcademique;
	}

	public void setIdCurrAnneeAcademique(Integer idCurrAnneeAcademique) {
		this.idCurrAnneeAcademique = idCurrAnneeAcademique;
	}

	public String getRefCodeCurrAnneeAcademique() {
		return refCodeCurrAnneeAcademique;
	}

	public void setRefCodeCurrAnneeAcademique(String refCodeCurrAnneeAcademique) {
		this.refCodeCurrAnneeAcademique = refCodeCurrAnneeAcademique;
	}

	public Integer getIdAnneeAcademique() {
		return idAnneeAcademique;
	}

	public void setIdAnneeAcademique(Integer idAnneeAcademique) {
		this.idAnneeAcademique = idAnneeAcademique;
	}

	public List<SelectItem> getListAnneeAcademique() {
		return listAnneeAcademique;
	}

	public void setListAnneeAcademique(List<SelectItem> listAnneeAcademique) {
		this.listAnneeAcademique = listAnneeAcademique;
	}

	public List<SelectItem> getListDomaines() {
		return listDomaines;
	}

	public void setListDomaines(List<SelectItem> listDomaines) {
		this.listDomaines = listDomaines;
	}

	public List<SelectItem> getListFilieres() {
		return listFilieres;
	}

	public void setListFilieres(List<SelectItem> listFilieres) {
		this.listFilieres = listFilieres;
	}

	public List<SelectItem> getPeriodeList() {
		return periodeList;
	}

	public void setPeriodeList(List<SelectItem> periodeList) {
		this.periodeList = periodeList;
	}

	public List<GroupePedagogiqueDto> getListGroupePedagogiqueDto() {
		return listGroupePedagogiqueDto;
	}

	public void setListGroupePedagogiqueDto(
			List<GroupePedagogiqueDto> listGroupePedagogiqueDto) {
		this.listGroupePedagogiqueDto = listGroupePedagogiqueDto;
	}

	public List<SelectItem> getOffreFormationList() {
		return offreFormationList;
	}

	public void setOffreFormationList(List<SelectItem> offreFormationList) {
		this.offreFormationList = offreFormationList;
	}

	public void setEventModel(ScheduleModel eventModel) {
		this.eventModel = eventModel;
	}

	public Date getRandomDate(Date base) {
		Calendar date = Calendar.getInstance();
		date.setTime(base);
		date.add(Calendar.DATE, ((int) (Math.random() * 30)) + 1); // set random
																	// day of
																	// month

		return date.getTime();
	}

	public Date getInitialDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(calendar.get(Calendar.YEAR), Calendar.FEBRUARY,
				calendar.get(Calendar.DATE), 0, 0, 0);

		return calendar.getTime();
	}

	public ScheduleModel getEventModel() {
		return eventModel;
	}

	private Calendar today() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DATE), 0, 0, 0);

		return calendar;
	}

	private Date previousDay8Pm() {
		Calendar t = (Calendar) today().clone();
		t.set(Calendar.AM_PM, Calendar.PM);
		t.set(Calendar.DATE, t.get(Calendar.DATE) - 1);
		t.set(Calendar.HOUR, 8);

		return t.getTime();
	}

	private Date previousDay11Pm() {
		Calendar t = (Calendar) today().clone();
		t.set(Calendar.AM_PM, Calendar.PM);
		t.set(Calendar.DATE, t.get(Calendar.DATE) - 1);
		t.set(Calendar.HOUR, 11);

		return t.getTime();
	}

	private Date today1Pm() {
		Calendar t = (Calendar) today().clone();
		t.set(Calendar.AM_PM, Calendar.PM);
		t.set(Calendar.HOUR, 1);

		return t.getTime();
	}

	private Date theDayAfter3Pm() {
		Calendar t = (Calendar) today().clone();
		t.set(Calendar.DATE, t.get(Calendar.DATE) + 2);
		t.set(Calendar.AM_PM, Calendar.PM);
		t.set(Calendar.HOUR, 3);

		return t.getTime();
	}

	private Date today6Pm() {
		Calendar t = (Calendar) today().clone();
		t.set(Calendar.AM_PM, Calendar.PM);
		t.set(Calendar.HOUR, 6);

		return t.getTime();
	}

	private Date nextDay9Am() {
		Calendar t = (Calendar) today().clone();
		t.set(Calendar.AM_PM, Calendar.AM);
		t.set(Calendar.DATE, t.get(Calendar.DATE) + 1);
		t.set(Calendar.HOUR, 9);

		return t.getTime();
	}

	private Date nextDay11Am() {
		Calendar t = (Calendar) today().clone();
		t.set(Calendar.AM_PM, Calendar.AM);
		t.set(Calendar.DATE, t.get(Calendar.DATE) + 1);
		t.set(Calendar.HOUR, 11);

		return t.getTime();
	}

	private Date fourDaysLater3pm() {
		Calendar t = (Calendar) today().clone();
		t.set(Calendar.AM_PM, Calendar.PM);
		t.set(Calendar.DATE, t.get(Calendar.DATE) + 4);
		t.set(Calendar.HOUR, 3);

		return t.getTime();
	}

	public ScheduleEvent getEvent() {
		return event;
	}

	public void setEvent(ScheduleEvent event) {
		this.event = event;
	}

	public void addEvent(ActionEvent actionEvent) {
		if (event.getId() == null)
			eventModel.addEvent(event);
		else
			eventModel.updateEvent(event);

		event = new DefaultScheduleEvent();
	}

	public void onEventSelect(SelectEvent selectEvent) {
		event = (ScheduleEvent) selectEvent.getObject();
	}

	public void onDateSelect(SelectEvent selectEvent) {
		event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(),
				(Date) selectEvent.getObject());
	}

	public void onEventMove(ScheduleEntryMoveEvent event) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Event moved", "Day delta:" + event.getDayDelta()
						+ ", Minute delta:" + event.getMinuteDelta());

		addMessage(message);
	}

	public void onEventResize(ScheduleEntryResizeEvent event) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Event resized", "Day delta:" + event.getDayDelta()
						+ ", Minute delta:" + event.getMinuteDelta());

		addMessage(message);
	}

	private void addMessage(FacesMessage message) {
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
}
