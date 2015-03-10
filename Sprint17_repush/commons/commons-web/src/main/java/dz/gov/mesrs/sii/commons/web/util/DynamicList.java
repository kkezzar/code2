/**
 * [dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.DynamicList.java] 
 * @author  Rafik LAIB on : 17 mai 2014  17:31:03
 */
package dz.gov.mesrs.sii.commons.web.util;

import java.util.List;

import javax.faces.component.FacesComponent;
import javax.faces.component.UINamingContainer;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import org.primefaces.extensions.model.timeline.TimelineEvent;
import org.primefaces.extensions.model.timeline.TimelineModel;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteOccurrenceDto;


/**
 * @author  Rafik LAIB  on : 17 mai 2014  17:31:03
 */
@FacesComponent(value="dynamicList") 
@SuppressWarnings({"rawtypes", "unchecked"})
public class DynamicList extends UINamingContainer {

	private transient DataModel model;
    private TimelineModel modelTimeline;  
    private Integer modelSize;

    public DataModel getModel() {
        if (model == null) model = new ListDataModel(getList());
        return model;
    }

    /**
     * [DynamicList.getList] Method 
     * @author  Rafik LAIB  on : 18 mai 2014  20:21:48
     * @return
     */
    private List getList() { 
    	List list = (List) getAttributes().get("list");
    	if(list != null && list.size()>0)
    		modelSize = list.size();
    	else
    		modelSize =0;
        return list ;
    }

	/**
	 * [DynamicList.modelTimeline] Getter 
	 * @author  Rafik LAIB on : 18 mai 2014  20:21:33
	 * @return the modelTimeline
	 */
	public TimelineModel getModelTimeline() { 
		
		
		modelTimeline = new TimelineModel();  
		List<SituationEntiteOccurrenceDto> listSituations = (List<SituationEntiteOccurrenceDto>) this.getList();
		for (SituationEntiteOccurrenceDto situationEntiteOccurrenceDto : listSituations) {
			
			modelTimeline.add(new TimelineEvent(situationEntiteOccurrenceDto.getLibelleSituation()
					, situationEntiteOccurrenceDto.getDateSituation()));  
			
		}
		return modelTimeline;
	}

	/**
	 * [DynamicList.modelSize] Getter 
	 * @author rlaib on : 23 oct. 2014  11:33:43
	 * @return the modelSize
	 */
	public Integer getModelSize() {
		return modelSize;
	}
    
    
}
