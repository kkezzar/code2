/**
 * [dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.OfDataModel.java] 
 * @author rlaib on : 5 mars 2014  09:31:00
 */
package dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteOccurrenceDto;
/**
 * @author rlaib  on : 5 mars 2014  09:31:00
 */
public class SituationDataModel extends ListDataModel<SituationEntiteOccurrenceDto> implements SelectableDataModel<SituationEntiteOccurrenceDto> {
	
	 			public SituationDataModel() {  
	 			
	 			}  
	 			
	 			public SituationDataModel(List<SituationEntiteOccurrenceDto> data) {  
	 					super(data);  
	 			}  
	      
	 			@Override  
	 			public SituationEntiteOccurrenceDto getRowData(String rowKey) {  
	          
	 					@SuppressWarnings("unchecked")
						List<SituationEntiteOccurrenceDto> list = (List<SituationEntiteOccurrenceDto>) getWrappedData();  
	          
	 					for(SituationEntiteOccurrenceDto s : list) {  
	 						
	 							if(s.getId() == Integer.parseInt(rowKey))
	 								return s;  
	 					}  
	          
	 					return null;  
	 			}  
	  
	 			@Override  
	 			public Object getRowKey(SituationEntiteOccurrenceDto s) {  
	 					return s.getId();
	 			}  
	 		
	
}
