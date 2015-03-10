/**
 * [dz.gov.mesrs.sii.referentiel.ws.impl.PermissionWSImpl.java] 
 * @author MAKERRI Sofiane on : 29 juin 2014  14:54:47
 */
package dz.gov.mesrs.sii.referentiel.ws.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefFonctionDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefPermissionDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefFonctionService;
import dz.gov.mesrs.sii.referentiel.business.service.RefPermissionService;
import dz.gov.mesrs.sii.referentiel.ws.service.PermissionWS;

/**
 * @author MAKERRI Sofiane on : 29 juin 2014 14:54:47
 */
@Service("permissionWSImpl")
public class PermissionWSImpl implements PermissionWS {
	@Autowired
	@Qualifier("refPermissionServiceImpl")
	private RefPermissionService refPermissionService;
	@Autowired
	@Qualifier("refFonctionServiceImpl")
	private RefFonctionService refFonctionService;
	
	private static final Log log = LogFactory.getLog(PermissionWSImpl.class);

	/**
	 * [PermissionWSImpl.PermissionWSImpl()] Constructor
	 * 
	 * @author MAKERRI Sofiane on : 29 juin 2014 14:54:47
	 */
	public PermissionWSImpl() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * [PermissionWSImpl.refPermissionService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 29 juin 2014 14:55:20
	 * @return the refPermissionService
	 */
	public RefPermissionService getRefPermissionService() {
		return refPermissionService;
	}

	/**
	 * [PermissionWSImpl.refPermissionService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 29 juin 2014 14:55:20
	 * @param refPermissionService
	 *            the refPermissionService to set
	 */
	public void setRefPermissionService(
			RefPermissionService refPermissionService) {
		this.refPermissionService = refPermissionService;
	}
	
	

	/**
	 * [PermissionWSImpl.refFonctionService] Getter 
	 * @author MAKERRI Sofiane on : 1 sept. 2014  10:41:17
	 * @return the refFonctionService
	 */
	public RefFonctionService getRefFonctionService() {
		return refFonctionService;
	}

	/**
	 * [PermissionWSImpl.refFonctionService] Setter 
	 * @author MAKERRI Sofiane on : 1 sept. 2014  10:41:17
	 * @param refFonctionService the refFonctionService to set
	 */
	public void setRefFonctionService(RefFonctionService refFonctionService) {
		this.refFonctionService = refFonctionService;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.referentiel.ws.service.PermissionWS#findPermissionByIdRole
	 * (java.lang.String, int)
	 */
	@Override
	public List<RefPermissionDto> findPermissionByIdRole(String domaine, int idf)
			throws Exception {
		try {
			return refPermissionService.findByIdRole(domaine, idf);
		} catch (Exception e) {
			log.error("findGeneric Permission  failed", e);
			throw e;
		}
	}

	@Override
	public List<RefFonctionDto> findFonctionsByDomaine(String domaine)
			throws Exception {
		try {
			return refFonctionService.findAllFonctions(domaine);
		} catch (Exception e) {
			log.error("findFonctionsByDomaine  failed", e);
			throw e;
		}
	}

}
