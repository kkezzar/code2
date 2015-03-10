/**
 * [dz.gov.mesrs.sii.referentiel.ws.impl.StructureWSImpl.java] 
 * @author rlaib on : 12 mars 2014  10:50:00
 */
package dz.gov.mesrs.sii.referentiel.ws.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefStructureDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefStructureService;
import dz.gov.mesrs.sii.referentiel.business.service.impl.RefStructureServiceImpl;
import dz.gov.mesrs.sii.referentiel.ws.service.StructureWS;

/**
 * @author rlaib  on : 12 mars 2014  10:50:00
 */
@Service("structureWSImpl")
public class StructureWSImpl implements StructureWS {
	
	
	private static final Log log = LogFactory.getLog(StructureWSImpl.class);
	
			// ===================================================================  
			// Constructor
			// ===================================================================
			/**
			 * [StructureWSImpl.StructureWSImpl()] Constructor
			 * @author rlaib  on : 12 mars 2014  10:52:10	
			 */
			public StructureWSImpl() {
		
			}
	
			// ===================================================================  
			// Properties 
			// ===================================================================
			@Autowired
			@Qualifier ("refStructureServiceImpl")
			private RefStructureService refStructureServiceImpl;

			// ===================================================================  
			// Properties Getters / Setters
			// ===================================================================
		
			/**
			 * [StructureWSImpl.refStructureServiceImpl] Getter 
			 * @author rlaib on : 12 mars 2014  10:53:48
			 * @return the refStructureServiceImpl
			 */
			public RefStructureService getRefStructureServiceImpl() {
				return refStructureServiceImpl;
			}

			/**
			 * [StructureWSImpl.refStructureServiceImpl] Setter 
			 * @author rlaib on : 12 mars 2014  10:53:48
			 * @param refStructureServiceImpl the refStructureServiceImpl to set
			 */
			public void setRefStructureServiceImpl(
					RefStructureService refStructureServiceImpl) {
				this.refStructureServiceImpl = refStructureServiceImpl;
			}
			
			// ===================================================================  
			// Implementation methods
			// ===================================================================
			public List<RefStructureDto> findGeneric(String value)
					throws Exception {
				
								List<RefStructureDto>  result = new ArrayList<RefStructureDto>();
								
								try{
												result = refStructureServiceImpl.findGeneric(value);
												log.debug("findGeneric structure successful");
												
								}catch(Exception e){
									
												log.error("findGeneric structure  failed", e);
												throw e;
										
								}
								return result;
								
			}
				
			public List<RefStructureDto> findAdvanced(RefStructureDto refStructureDto)
						throws Exception {
					
							List<RefStructureDto>  result = new ArrayList<RefStructureDto>();
							
							try{
										result = refStructureServiceImpl.findAdvanced(refStructureDto);
										log.debug("findAdvanced structure successful");
								
							}catch(Exception e){
								
										log.error("findAdvanced structure  failed", e);
										throw e;
								
							}
							return result;
							
				}
		
	
}
