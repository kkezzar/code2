/**
 * [dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.OfDataModel.java] 
 * @author rlaib on : 5 mars 2014  09:31:00
 */
package dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.of;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OffreFormationDto;
/**
 * @author rlaib  on : 5 mars 2014  09:31:00
 */
public class OfDataModel extends ListDataModel<OffreFormationDto> implements SelectableDataModel<OffreFormationDto> {
	
	 			public OfDataModel() {  
	 			
	 			}  
	 			
	 			public OfDataModel(List<OffreFormationDto> data) {  
	 					super(data);  
	 			}  
	      
	 			@Override  
	 			public OffreFormationDto getRowData(String rowKey) {  
	          
	 					@SuppressWarnings("unchecked")
						List<OffreFormationDto> ofList = (List<OffreFormationDto>) getWrappedData();  
	          
	 					for(OffreFormationDto of : ofList) {  
	 						
	 							if(of.getCode().equals(rowKey))  
	 								return of;  
	 					}  
	          
	 					return null;  
	 			}  
	  
	 			@Override  
	 			public Object getRowKey(OffreFormationDto of) {  
	 					return of.getCode();  
	 			}  
	
}
