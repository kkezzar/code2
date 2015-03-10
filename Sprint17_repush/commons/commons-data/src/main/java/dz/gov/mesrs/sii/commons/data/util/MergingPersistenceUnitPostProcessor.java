/**
 * [dz.gov.mesrs.sii.fve.business.util.MergingPersistenceUnitPostProcessor.java] 
 * @author rlaib on : 23 sept. 2014  18:40:19
 */
package dz.gov.mesrs.sii.commons.data.util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.jpa.persistenceunit.MutablePersistenceUnitInfo;
import org.springframework.orm.jpa.persistenceunit.PersistenceUnitPostProcessor;

import dz.gov.mesrs.sii.commons.data.dao.impl.fve.examen.BilanControleContinuDaoImpl;

/**
 * @author rlaib  on : 23 sept. 2014  18:40:19
 */
public class MergingPersistenceUnitPostProcessor implements
		PersistenceUnitPostProcessor {

//	Map<String, List<String>> puiClasses = new HashMap<String, List<String>>();
//
//	 public void postProcessPersistenceUnitInfo(MutablePersistenceUnitInfo pui)
//	 {
//	  List<String> classes = puiClasses.get(pui.getPersistenceUnitName());
//	  if (classes == null)
//	  {
//	   classes = new ArrayList<String>();
//	   puiClasses.put(pui.getPersistenceUnitName(), classes);
//	  }
//	  pui.getManagedClassNames().addAll(classes);
//
//	  final List<String> names = pui.getManagedClassNames();
//	  classes.addAll(pui.getManagedClassNames());
//	 }
	
//	private final static Logger log = LogFactory.getLog(MergingPersistenceUnitPostProcessor.class);
	private static final Logger log = LoggerFactory.getLogger(MergingPersistenceUnitPostProcessor.class.getName());
	private Set<String> managedClassNames = new HashSet<String>();

    @Override
    public void postProcessPersistenceUnitInfo(MutablePersistenceUnitInfo pui) {
        logDetails(pui);
        mergePuiManagedClassesWithCached(pui.getManagedClassNames());
        mergeCachedWithPuiManagedClasses(pui.getManagedClassNames());
    }

    private void mergeCachedWithPuiManagedClasses(List<String> puiClassNames) {
        managedClassNames.addAll(puiClassNames);
    }

    private void mergePuiManagedClassesWithCached(List<String> puiClassNames) {
        puiClassNames.addAll(managedClassNames);
    }

    private void logDetails(MutablePersistenceUnitInfo pui) {
        log.info("pui=" + pui.getPersistenceUnitName());
        for (String className : pui.getManagedClassNames()) {
            log.info("Persistent class=" + className);
        }
    }

	 
	 
}
