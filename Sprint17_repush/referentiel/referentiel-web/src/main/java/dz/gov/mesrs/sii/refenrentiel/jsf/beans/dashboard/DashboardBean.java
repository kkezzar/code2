package dz.gov.mesrs.sii.refenrentiel.jsf.beans.dashboard;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.DashboardReorderEvent;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;


@ManagedBean(name = "dashboardBean")
@RequestScoped
public class DashboardBean implements Serializable {  
	/**
	 * serialVersionUID 
	 * @author BELBRIK Oualid  on : 09 02. 2014  11:20:20
	 */
    private DashboardModel model;  
      
    public DashboardBean() {  
        model = new DefaultDashboardModel();  
        DashboardColumn column1 = new DefaultDashboardColumn();  
        DashboardColumn column2 = new DefaultDashboardColumn();  
        DashboardColumn column3 = new DefaultDashboardColumn(); 
        DashboardColumn column4 = new DefaultDashboardColumn(); 
  
        column1.addWidget("individus");  
        column1.addWidget("contrats");   
        column1.addWidget("nomenclature");  
        column1.addWidget("lieux");  
          
        column2.addWidget("groupes");  
        column2.addWidget("avenants"); 
        column2.addWidget("items"); 
        column2.addWidget("equipements"); 
     

        column3.addWidget("structures");
        column3.addWidget("evenements"); 
        column3.addWidget("domaines"); 
        column3.addWidget("permissions"); 
        
        column4.addWidget("documents"); 
        column4.addWidget("horaires"); 
        column4.addWidget("plages"); 
        column4.addWidget("comptes"); 
        

  
        
        model.addColumn(column1);  
        model.addColumn(column2);  
        model.addColumn(column3);  
        model.addColumn(column4);
    }  
      
    public void handleReorder(DashboardReorderEvent event) {  
        FacesMessage message = new FacesMessage();  
        message.setSeverity(FacesMessage.SEVERITY_INFO);  
        message.setSummary("Reordered: " + event.getWidgetId());  
        message.setDetail("Item index: " + event.getItemIndex() + ", Column index: " + event.getColumnIndex() + ", Sender index: " + event.getSenderColumnIndex());  
          
        addMessage(message);  
    }  
      
      
    private void addMessage(FacesMessage message) {  
        FacesContext.getCurrentInstance().addMessage(null, message);  
    }  
      
    public DashboardModel getModel() {  
        return model;  
    }  
}  
