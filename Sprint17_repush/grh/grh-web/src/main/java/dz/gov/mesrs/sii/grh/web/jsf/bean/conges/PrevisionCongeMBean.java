package dz.gov.mesrs.sii.grh.web.jsf.bean.conges;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import dz.gov.mesrs.sii.commons.data.dao.SearchMode;
import dz.gov.mesrs.sii.commons.web.util.SearchModeMapper;
import dz.gov.mesrs.sii.grh.business.exception.PrevisionCongeChevauchementException;
import dz.gov.mesrs.sii.grh.business.exception.PrevisionCongeDepassementException;
import dz.gov.mesrs.sii.grh.business.exception.PrevisionCongeHorsPeriodeException;
import dz.gov.mesrs.sii.grh.business.model.dto.AnneeGrhDto;
import dz.gov.mesrs.sii.grh.business.model.dto.DossierEmployeDto;
import dz.gov.mesrs.sii.grh.business.model.dto.PrevisionCongeDto;
import dz.gov.mesrs.sii.grh.web.jsf.bean.BaseBean;
import dz.gov.mesrs.sii.grh.web.util.CommonMessagesUtils;
import dz.gov.mesrs.sii.grh.web.util.GRHMessagesUtils;

/**
 * 
 * @author BELDI Jamel
 *
 */
@ManagedBean(name = "previsionCongeMBean")
@ViewScoped
public class PrevisionCongeMBean extends BaseBean {

	/**
	 * serial version
	 */
	private static final long serialVersionUID = 1L;
	private DossierEmployeDto employeSearchDto;
	private LazyDataModel<DossierEmployeDto> dossierEmployeModel;
	private DossierEmployeDto dossierEmployeDto;
	private List<PrevisionCongeDto> listPrevisionCongeDtos;
	private List<PrevisionCongeDto> listPrevisionCongeDtostoDelete;
	private AnneeGrhDto anneeGrh;
	private double droitConge;
	private Integer selectedPrevisionIndex;

	public PrevisionCongeMBean() {

	}

	@PostConstruct
	public void init() {
		employeSearchDto = new DossierEmployeDto();
		employeSearchDto.setRefEtablissementDto(sessionBean.getRefEtablissementDto());
		onSearchDossierEmploye();
		initCurrentYear();
		
	}

	private void initDetail() {
		dossierEmployeDto = null;
	}
	private  void initCurrentYear(){
		 try {
			anneeGrh = deduceCurrentYear();
		} catch (ParseException e) {
		}
	}


	/**
	 * recherche des employés en activité
	 */
	public void onSearchDossierEmploye() {
		try {
			loaddossierEmployeModel();
			initDetail();
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	private void loaddossierEmployeModel() {
		dossierEmployeModel = new LazyDataModel<DossierEmployeDto>() {
			private static final long serialVersionUID = -3025574151722364485L;

			@Override
			public Object getRowKey(DossierEmployeDto dossierEmployeDto) {
				return dossierEmployeDto.getId();
			}

			@Override
			public List<DossierEmployeDto> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, String> filters) {
				SearchMode searchMode = new SearchMode();
				dossierEmployeModel.setRowCount(serviceLocator.getDossierEmployeService().countAllActif(employeSearchDto));
				 searchMode = SearchModeMapper.map(first, pageSize, sortField, sortOrder);
				List<DossierEmployeDto> dtos = serviceLocator.getDossierEmployeService().findAllActif(employeSearchDto, searchMode);
				return dtos;
			}
		};

	}

	public void onRowSelect(SelectEvent event) {
		try {
			dossierEmployeDto = (DossierEmployeDto) event.getObject();
			droitConge = serviceLocator.getCongeEmployeService().calculateDroitConge(anneeGrh, dossierEmployeDto.getId(), dossierEmployeDto.getDateInstallation());
			listPrevisionCongeDtos = serviceLocator.getPrevisionCongeService().findPrevisionConges(dossierEmployeDto.getId(), anneeGrh);
		} catch (Exception e) {
			e.printStackTrace();

		}
	}
	
	
	
	
	public void addPrevision() {
		PrevisionCongeDto previsionCongeDto = new PrevisionCongeDto();
		if (this.listPrevisionCongeDtos == null) {
			this.listPrevisionCongeDtos = new ArrayList<PrevisionCongeDto>();
		}
		previsionCongeDto.setDossierEmployeDto(this.dossierEmployeDto);
		this.listPrevisionCongeDtos.add(previsionCongeDto);
	}

	public void removePrevision() {
		PrevisionCongeDto previsionCongeDto = this.listPrevisionCongeDtos.get(selectedPrevisionIndex);
		if (previsionCongeDto.getId() != null && previsionCongeDto.getId() != 0) {
			listPrevisionCongeDtostoDelete.add(previsionCongeDto);
		}
		this.listPrevisionCongeDtos.remove(previsionCongeDto);

	}
	
	public void onSave()  {
		if (validateForm()) {
		try {
			serviceLocator.getPrevisionCongeService().saveEmployePrevisionConges(listPrevisionCongeDtos, listPrevisionCongeDtostoDelete, anneeGrh, droitConge);
			afterSave();
			CommonMessagesUtils.showSuccessSaveMessage();
		} catch (PrevisionCongeHorsPeriodeException e) {
			GRHMessagesUtils.showErrorMessage("prevision_conge_date_incorrect");
			
		}catch (PrevisionCongeChevauchementException e) {
			//Les dates prévues sont en chevauchement!
			GRHMessagesUtils.showErrorMessage("prevision_conge_dates_chevauches");
		}catch (PrevisionCongeDepassementException e) {
			//Vérifier les prévisions de congé avec les droits de congé.
			GRHMessagesUtils.showErrorMessage("prevision_conge_depasse_droit");
		}
		
		}
	}
	
	private void afterSave(){
		initDetail();
	}
	
	private boolean validateForm()  {
		boolean result = true;
		
	   if(listPrevisionCongeDtos==null){
		   GRHMessagesUtils.showErrorMessage("prevision_conge_liste_previsions_vide");
		   return false;
	   }
//		
//	   else {
//		   boolean chevauchement = false;
//		   boolean horsPeriode = false;
//		   boolean depassement = false;
//		   
//		   // Ordonner la liste
//			Collections.sort(listPrevisionCongeDtos, new Comparator<PrevisionCongeDto>() {
//				@Override
//				public int compare(PrevisionCongeDto p1, PrevisionCongeDto p2) {
//				return p1.getDateDebut().compareTo(p2.getDateDebut());
//				}
//				});
//			
//			//verification
//			Double  nbjours = new Double(0);
//			Date dateFin = null;
//			for (PrevisionCongeDto _previsionCongeDto : listPrevisionCongeDtos) {
//				Date dateDebut = _previsionCongeDto.getDateDebut();
//				if(!chevauchement && dateFin!=null && dateFin.after(anneeGrh.getDateDebut())){
//					chevauchement = true;
//				}
//				dateFin = serviceLocator.getRefJourOuvreService().nextWorkingDay(dateDebut, _previsionCongeDto.getNbreJours());//UtilDate.addDays(dateDebut, _previsionCongeDto.getNbreJours());
//				if(!horsPeriode && dateDebut.before(anneeGrh.getDateDebut())|| dateDebut.after(anneeGrh.getDateFin()) || dateFin.before(anneeGrh.getDateDebut()) || dateDebut.after(anneeGrh.getDateFin()) ){
//					horsPeriode = true;
//				}
//				nbjours = nbjours + _previsionCongeDto.getNbreJours();
//				if(!depassement && nbjours>droitConge){
//					depassement = true;
//				}
//			}
//		   
//		   
//		   if (horsPeriode) {
//	   
//			//Vérifier les dates prévues avec l'année de prévision
//			GRHMessagesUtils.showErrorMessage("prevision_conge_date_incorrect");
//			result = false;
//
//		}
//		else if(chevauchement){
//			//Les dates prévues sont en chevauchement!
//			GRHMessagesUtils.showErrorMessage("prevision_conge_dates_chevauches");
//			result = false;
//		}
//		else if(depassement){
//			//Vérifier les prévisions de congé avec les droits de congé.
//			GRHMessagesUtils.showErrorMessage("prevision_conge_depasse_droit");
//			result = false;
//		}
//	   }
		
		return result;
	}
	
	
	
	
	
	
	


	
	public DossierEmployeDto getEmployeSearchDto() {
		return employeSearchDto;
	}

	public void setEmployeSearchDto(DossierEmployeDto employeSearchDto) {
		this.employeSearchDto = employeSearchDto;
	}

	public LazyDataModel<DossierEmployeDto> getDossierEmployeModel() {
		return dossierEmployeModel;
	}

	public void setDossierEmployeModel(
			LazyDataModel<DossierEmployeDto> dossierEmployeModel) {
		this.dossierEmployeModel = dossierEmployeModel;
	}

	public DossierEmployeDto getDossierEmployeDto() {
		return dossierEmployeDto;
	}

	public void setDossierEmployeDto(DossierEmployeDto dossierEmployeDto) {
		this.dossierEmployeDto = dossierEmployeDto;
	}

	

	public List<PrevisionCongeDto> getListPrevisionCongeDtos() {
		return listPrevisionCongeDtos;
	}

	public void setListPrevisionCongeDtos(
			List<PrevisionCongeDto> listPrevisionCongeDtos) {
		this.listPrevisionCongeDtos = listPrevisionCongeDtos;
	}

	

	public double getDroitConge() {
		return droitConge;
	}

	public void setDroitConge(double droitConge) {
		this.droitConge = droitConge;
	}

	public Integer getSelectedPrevisionIndex() {
		return selectedPrevisionIndex;
	}

	public void setSelectedPrevisionIndex(Integer selectedPrevisionIndex) {
		this.selectedPrevisionIndex = selectedPrevisionIndex;
	}

	public List<PrevisionCongeDto> getListPrevisionCongeDtostoDelete() {
		return listPrevisionCongeDtostoDelete;
	}

	public void setListPrevisionCongeDtostoDelete(
			List<PrevisionCongeDto> listPrevisionCongeDtostoDelete) {
		this.listPrevisionCongeDtostoDelete = listPrevisionCongeDtostoDelete;
	}

	
	public AnneeGrhDto getAnneeGrh() {
		return anneeGrh;
	}

	public void setAnneeGrh(AnneeGrhDto anneeGrh) {
		this.anneeGrh = anneeGrh;
	}

	
	
}
