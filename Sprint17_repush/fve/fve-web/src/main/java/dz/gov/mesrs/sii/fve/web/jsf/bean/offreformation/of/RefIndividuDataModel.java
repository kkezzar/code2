/**
 * [dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.OfDataModel.java] 
 * @author rlaib on : 5 mars 2014  09:31:00
 */
package dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.of;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefIndividuDto;
/**
 * @author rlaib  on : 5 mars 2014  09:31:00
 */
public class RefIndividuDataModel extends ListDataModel<RefIndividuDto> implements SelectableDataModel<RefIndividuDto> {
	
	 			public RefIndividuDataModel() {  
	 			
	 			}  
	 			
	 			public RefIndividuDataModel(List<RefIndividuDto> data) {  
	 					super(data);  
	 			}  
	      
	 			@Override  
	 			public RefIndividuDto getRowData(String rowKey) {  
	          
	 					@SuppressWarnings("unchecked")
						List<RefIndividuDto> ofList = (List<RefIndividuDto>) getWrappedData();  
	 					for(RefIndividuDto ind : ofList) {  
	 							if(ind.getIdentifiant().equals(rowKey))  
	 								return ind;  
	 					}  
	          
	 					return null;  
	 			}  
	  
	 			@Override  
	 			public Object getRowKey(RefIndividuDto ind) {  
	 					return ind.getIdentifiant();  
	 			}  
	 		
	
}
