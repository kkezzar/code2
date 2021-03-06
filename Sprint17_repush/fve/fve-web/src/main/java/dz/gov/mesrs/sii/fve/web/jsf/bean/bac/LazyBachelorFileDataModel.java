/**
 * [dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.OfLazyDataModel.java] 
 * @author rlaib on : 18 f�vr. 2014  12:12:23
 */
package dz.gov.mesrs.sii.fve.web.jsf.bean.bac;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import dz.gov.mesrs.sii.fve.business.model.dto.bac.DossierBachelierDto;
/**
 * @author rlaib  on : 18 fevr. 2014  12:12:23
 */
public class LazyBachelorFileDataModel   extends LazyDataModel<DossierBachelierDto>  {
	
	 	/**
	 * serialVersionUID 
	 * @author  Rafik LAIB  on : 21 mai 2014  17:44:38
	 */
		private static final long serialVersionUID = -3136286880749555465L;
		
		private List<DossierBachelierDto> datasource;
	    public LazyBachelorFileDataModel(List<DossierBachelierDto> datasource) {
	        this.datasource = datasource;
	    }

	    @Override
	    public DossierBachelierDto getRowData(String rowKey) {
	    	
	        for(DossierBachelierDto dto : datasource) {
	            if(dto.getMatricule().equals(rowKey))
	                return dto;
	        }

	        return null;
	    }

	    @Override
	    public Object getRowKey(DossierBachelierDto dto) {
	        return dto.getMatricule();
	    }
	    
	    @Override
	    public List<DossierBachelierDto> load(int first, int pageSize, final String sortField, final SortOrder sortOrder, Map<String, String> filters) {
	        List<DossierBachelierDto> data = new ArrayList<DossierBachelierDto>();
	        
	      //filter  
	        for(DossierBachelierDto dto : datasource) {  
	            boolean match = true;  
	  
//	            for(Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {  
//	                try {  
//	                			String filterProperty = it.next();  
//	                			String filterValue = filters.get(filterProperty);  
//	                			String fieldValue = String.valueOf(dto.getClass().getField(filterProperty).get(dto));  
//	  
//	                    if(filterValue == null || fieldValue.startsWith(filterValue)) {  
//	                        match = true;  
//	                    }  
//	                    else {  
//	                        match = false;  
//	                        break;  
//	                    }  
//	                } catch(Exception e) {  
//	                    match = false;  
//	                }   
//	            }  
//	  
	            if(match) {  
	                data.add(dto);  
	            }  
	        }  
	        
	      //sort  
	        if(sortField != null) {
	            Collections.sort(data, new Comparator<DossierBachelierDto>() {
	                @Override
	                public int compare(DossierBachelierDto obj1, DossierBachelierDto obj2) {
	                    Object value1 = null;
	                    try {
	                        value1 = DossierBachelierDto.class.getField(sortField).get(obj1);
	                        Object value2 = DossierBachelierDto.class.getField(sortField).get(obj2);
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
//	        this.setRowCount(dataSize);
	        
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
