/**
 * [dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.OfDataModel.java] 
 * @author rlaib on : 5 mars 2014  09:31:00
 */
package dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.of;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefContratDto;
/**
 * @author rlaib  on : 5 avril 2014  09:31:00
 */
public class RefContratDataModel extends ListDataModel<RefContratDto> implements SelectableDataModel<RefContratDto> {
	
	 			public RefContratDataModel() {  
	 			}  
	 			
	 			public RefContratDataModel(List<RefContratDto> data) {  
	 					super(data);  
	 			}  
	      
	 			@Override  
	 			public RefContratDto getRowData(String rowKey) {  
	          
	 					@SuppressWarnings("unchecked")
						List<RefContratDto> list = (List<RefContratDto>) getWrappedData();  
	          
	 					for(RefContratDto contract : list) {  
	 							if(contract.getIdentifiant().equals(rowKey))  
	 								return contract;  
	 					}  
	 					return null;  
	 			}  
	  
	 			@Override  
	 			public Object getRowKey(RefContratDto contract) {  
	 					return contract.getIdentifiant();  
	 			}  
	 		
	
}
