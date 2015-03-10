
package dz.gov.mesrs.sii.referentiel.ws.impl;




import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefParametreEtabDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefParametreEtabService;
import dz.gov.mesrs.sii.referentiel.ws.service.ParametreEtabWS;

/**
 * L'impelementation du web service refParametreEtabWS 
 * @author Mounir.MESSAOUDI on : 10 sept. 2014 17:46:43
 */
@Service("parametreEtabWSImpl")
public class ParametreEtabWSImpl implements ParametreEtabWS {
	@Autowired
	@Qualifier("refParametreEtabServiceImpl")
	private RefParametreEtabService refParametreEtabService;

	private static final Log log = LogFactory.getLog(ParametreEtabWSImpl.class);

	/**
	 * @author Mounir.MESSAOUDI on : 10 sept. 2014 18:46:13 
	 */
	public ParametreEtabWSImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public RefParametreEtabDto findByKeyEtab(String idfEtab, String key) throws Exception {
		try {
			return refParametreEtabService.findByKeyEtab(idfEtab, key);
		} catch (Exception e) {
			log.error("findByKeyEtab failed", e);
			throw e;
		}
	};

}
