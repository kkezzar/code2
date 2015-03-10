package dz.gov.mesrs.sii.commons.web.jsf.bean;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefIndividuDto;

@ManagedBean(name = "individuCommonBean")
@ViewScoped
public class IndividuCommonBean extends CommonBaseBean {

	private static final long serialVersionUID = 1L;

	private LazyDataModel<RefIndividuDto> individuModel;
	private RefIndividuDto individuSearchDto;

	public IndividuCommonBean() {
		individuSearchDto = new RefIndividuDto();
	}

	public void onOpenDialog() {
		individuSearchDto = new RefIndividuDto();
		onSearchIndividu();
	}

	public void onSearchIndividu() {
		try {

			individuModel = new LazyDataModel<RefIndividuDto>() {
				private static final long serialVersionUID = -3025574151722364485L;

				@Override
				public Object getRowKey(RefIndividuDto refIndividuDto) {
					return refIndividuDto.getId();
				}

				@Override
				public List<RefIndividuDto> load(int first, int pageSize, String sortField, SortOrder sortOrder,
						Map<String, String> filters) {
					Boolean descending = null;
					if (sortOrder != null) {
						if (sortOrder.equals(SortOrder.DESCENDING)) {
							descending = Boolean.TRUE;
						} else {
							descending = Boolean.FALSE;
						}

					}

					individuModel.setRowCount(commonServiceLocator.getRefIndividuService().countByExample(
							individuSearchDto));
					List<RefIndividuDto> dtos = commonServiceLocator.getRefIndividuService().findByExample(
							individuSearchDto, sortField, pageSize, first, descending);
					return dtos;
				}
			};

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public LazyDataModel<RefIndividuDto> getIndividuModel() {
		return individuModel;
	}

	public void setIndividuModel(LazyDataModel<RefIndividuDto> individuModel) {
		this.individuModel = individuModel;
	}

	public RefIndividuDto getIndividuSearchDto() {
		return individuSearchDto;
	}

	public void setIndividuSearchDto(RefIndividuDto individuSearchDto) {
		this.individuSearchDto = individuSearchDto;
	}

}
