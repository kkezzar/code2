/**
 * [dz.gov.mesrs.sii.fve.web.jsf.bean.utils.ViewScope.java] 
 * @author BELDI Jamel on : 21 juil. 2014  15:08:10
 */
package dz.gov.mesrs.sii.fve.web.jsf.bean.utils;


import java.util.Map;
 
import javax.faces.context.FacesContext;
 
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
 
/**
 * @author BELDI Jamel  on : 21 juil. 2014  15:08:32
 */
public class ViewScope implements Scope {
 
    public Object get(String name, ObjectFactory objectFactory) {
        Map<String,Object> viewMap = FacesContext.getCurrentInstance().getViewRoot().getViewMap();
 
        if(viewMap.containsKey(name)) {
            return viewMap.get(name);
        } else {
            Object object = objectFactory.getObject();
            viewMap.put(name, object);
 
            return object;
        }
    }
 
    public Object remove(String name) {
        return FacesContext.getCurrentInstance().getViewRoot().getViewMap().remove(name);
    }
 
    public String getConversationId() {
        return null;
    }
 
    public void registerDestructionCallback(String name, Runnable callback) {
        //Not supported
    }
 
    public Object resolveContextualObject(String key) {
        return null;
    }
}
