/**
 * [dz.gov.mesrs.sii.referentiel.ws.impl.IndividuWSImpl.java] 
 * @author rlaib on : 11 mars 2014  10:57:10
 */
package dz.gov.mesrs.sii.referentiel.ws.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefIndividuDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefIndividuService;
import dz.gov.mesrs.sii.referentiel.ws.service.IndividuWS;

/**
 * @author rlaib  on : 11 mars 2014  10:57:10
 */

@Service("individuWSImpl")
public class IndividuWSImpl implements IndividuWS {

		private static final Log log = LogFactory.getLog(IndividuWSImpl.class);
	
		// ===================================================================  
		// Constructor
		// ===================================================================
		/**
		 * [IndividuWSImpl.IndividuWSImpl()] Constructor
		 * @author rlaib  on : 11 mars 2014  12:24:08	
		 */
		public IndividuWSImpl() {
			
		}
		
		// ===================================================================  
		// Properties 
		// ===================================================================
		@Autowired
		@Qualifier ("refIndividuServiceImpl")
		private RefIndividuService refIndividuServiceImpl;
	
		// ===================================================================  
		// Properties Getters / Setters
		// ===================================================================
		/**
		 * [IndividuWSImpl.refIndividuServiceImpl] Getter 
		 * @author rlaib on : 11 mars 2014  11:04:18
		 * @return the refIndividuServiceImpl
		 */
		public RefIndividuService getRefIndividuServiceImpl() {
			return refIndividuServiceImpl;
		}

		/**
		 * [IndividuWSImpl.refIndividuServiceImpl] Setter 
		 * @author rlaib on : 11 mars 2014  11:04:18
		 * @param refIndividuServiceImpl the refIndividuServiceImpl to set
		 */
		public void setRefIndividuServiceImpl(RefIndividuService refIndividuServiceImpl) {
			this.refIndividuServiceImpl = refIndividuServiceImpl;
		}

		// ===================================================================  
		// Implementation methods
		// ===================================================================
		public List<RefIndividuDto> findGeneric(String value)
			throws Exception {
		
						List<RefIndividuDto>  result = new ArrayList<RefIndividuDto>();
						
						try{
										result = refIndividuServiceImpl.findGeneric(value);
										log.debug("findGeneric Individu successful");
										
						}catch(Exception e){
							
										log.error("findGeneric Individu  failed", e);
										throw e;
								
						}
						return result;
						
			}
		
		public List<RefIndividuDto> findAdvanced(RefIndividuDto refIndividuDto)
				throws Exception {
			
					List<RefIndividuDto>  result = new ArrayList<RefIndividuDto>();
					
					try{
								result = refIndividuServiceImpl.findAdvanced(refIndividuDto);
								log.debug("findAdvanced Individu successful");
						
					}catch(Exception e){
						
								log.error("findAdvanced Individu  failed", e);
								throw e;
						
					}
					return result;
					
		}
	
}
