/**
 * [dz.gov.mesrs.sii.fve.web.jsf.bean.CounterBean.java] 
 * @author BELDI Jamel on : 21 juil. 2014  15:15:56
 */
package dz.gov.mesrs.sii.fve.web.jsf.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedProperty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dz.gov.mesrs.sii.fve.business.model.dto.cursus.SituationSportiveDto;
import dz.gov.mesrs.sii.fve.business.service.cursus.SituationSportiveService;
 
@Component
@Scope("view")
public class CounterBean {
	@Autowired
	@Qualifier ("situationSportiveService")
	private SituationSportiveService situationSportiveService;
    private int counter = 0;
    private List<SituationSportiveDto> list = new ArrayList(); 
    public int getCounter() {
        return counter;
    }
    public void setCounter(int counter) {
        this.counter = counter;
    }
 
    public void increment() {
        counter++;
    }

    
   public void  find() {
	   list = situationSportiveService.findAll();
   }
/**
 * [CounterBean.situationSportiveService] Getter 
 * @author BELDI Jamel on : 21 juil. 2014  16:00:35
 * @return the situationSportiveService
 */
public SituationSportiveService getSituationSportiveService() {
	return situationSportiveService;
}
/**
 * [CounterBean.situationSportiveService] Setter 
 * @author BELDI Jamel on : 21 juil. 2014  16:00:35
 * @param situationSportiveService the situationSportiveService to set
 */
public void setSituationSportiveService(
		SituationSportiveService situationSportiveService) {
	this.situationSportiveService = situationSportiveService;
}
/**
 * [CounterBean.list] Getter 
 * @author BELDI Jamel on : 21 juil. 2014  16:00:35
 * @return the list
 */
public List<SituationSportiveDto> getList() {
	 //list = situationSportiveService.findAll();
	return list;
}
/**
 * [CounterBean.list] Setter 
 * @author BELDI Jamel on : 21 juil. 2014  16:00:35
 * @param list the list to set
 */
public void setList(List<SituationSportiveDto> list) {
	this.list = list;
}
    
   
   
}
