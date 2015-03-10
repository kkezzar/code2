/**
 * [dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.OfDataModel.java] 
 * @author rlaib on : 5 mars 2014  09:31:00
 */
package dz.gov.mesrs.sii.fve.web.jsf.bean.evaluation;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.DemandeI18nDto;
/**
 * @author rlaib  on : 5 mars 2014  09:31:00
 */
public class DemandDataModel extends ListDataModel<DemandeI18nDto> implements SelectableDataModel<DemandeI18nDto> {
	
	 			public DemandDataModel() {  
	 			
	 			}  
	 			
	 			public DemandDataModel(List<DemandeI18nDto> data) {  
	 					super(data);  
	 			}  
	      
	 			@Override  
	 			public DemandeI18nDto getRowData(String rowKey) {  
	          
	 					@SuppressWarnings("unchecked")
						List<DemandeI18nDto> list = (List<DemandeI18nDto>) getWrappedData();  
	 					for(DemandeI18nDto d : list) {  
	 							if(d.getCode().equals(rowKey))  
	 								return d;  
	 					}  
	 					return null;  
	 			}  
	  
	 			@Override  
	 			public Object getRowKey(DemandeI18nDto of) {  
	 					return of.getCode();  
	 			}  
	 		
	
}
