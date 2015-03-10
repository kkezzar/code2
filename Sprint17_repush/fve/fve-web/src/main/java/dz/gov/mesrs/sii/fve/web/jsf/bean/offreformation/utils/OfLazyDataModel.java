/**
 * [dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.OfLazyDataModel.java] 
 * @author rlaib on : 18 f�vr. 2014  12:12:23
 */
package dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SelectableDataModel;
import org.primefaces.model.SortOrder;

import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OffreFormationDto;
/**
 * @author rlaib  on : 18 f�vr. 2014  12:12:23
 */
public class OfLazyDataModel   extends LazyDataModel<OffreFormationDto>  {
	
	 	private List<OffreFormationDto> datasource;

	    public OfLazyDataModel(List<OffreFormationDto> datasource) {
	        this.datasource = datasource;
	    }

	    @Override
	    public OffreFormationDto getRowData(String rowKey) {
	    	
	        for(OffreFormationDto of : datasource) {
	            if(of.getCode().equals(rowKey))
	                return of;
	        }

	        return null;
	    }

	    @Override
	    public Object getRowKey(OffreFormationDto of) {
	        return of.getCode();
	    }
	    
	    @Override
	    public List<OffreFormationDto> load(int first, int pageSize, final String sortField, final SortOrder sortOrder, Map<String, String> filters) {
	        List<OffreFormationDto> data = new ArrayList<OffreFormationDto>();
	        
	      //filter  
	        for(OffreFormationDto of : datasource) {  
	            boolean match = true;  
	  
	            for(Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {  
	                try {  
	                    String filterProperty = it.next();  
	                    String filterValue = filters.get(filterProperty);  
	                    String fieldValue = String.valueOf(of.getClass().getField(filterProperty).get(of));  
	  
	                    if(filterValue == null || fieldValue.startsWith(filterValue)) {  
	                        match = true;  
	                    }  
	                    else {  
	                        match = false;  
	                        break;  
	                    }  
	                } catch(Exception e) {  
	                    match = false;  
	                }   
	            }  
	  
	            if(match) {  
	                data.add(of);  
	            }  
	        }  
	        
	      //sort  
	        if(sortField != null) {
	            Collections.sort(data, new Comparator<OffreFormationDto>() {
	                @Override
	                public int compare(OffreFormationDto of1, OffreFormationDto of2) {
	                    Object value1 = null;
	                    try {
	                        value1 = OffreFormationDto.class.getField(sortField).get(of1);
	                        Object value2 = OffreFormationDto.class.getField(sortField).get(of2);
	                        int value = ((Comparable)value1).compareTo(value2);
	                        return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
	                    }
	                    catch (Exception e) {
	                        throw new RuntimeException(e);
	                    }
	                }
	            }
	            );
	            
	        }
	        
	        //rowCount
	        int dataSize = data.size();
	        this.setRowCount(dataSize);
	        
	      //paginate
	        if(dataSize > pageSize) {
	            try {
	                return data.subList(first, first + pageSize);
	            }
	            catch(IndexOutOfBoundsException e) {
	                return data.subList(first, first + (dataSize % pageSize));
	            }
	        }
	        else {
	            return data;
	        }
	        
	    }
	    
	    
}
