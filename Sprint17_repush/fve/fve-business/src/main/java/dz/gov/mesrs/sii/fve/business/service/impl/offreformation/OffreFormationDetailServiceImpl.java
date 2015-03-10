/**
 * [dz.gov.mesrs.sii.fve.business.service.impl.offreformation.OffreFormationDetailServiceImpl.java] 
 * @author rlaib on : 6 f�vr. 2014  14:44:30
 */
package dz.gov.mesrs.sii.fve.business.service.impl.offreformation;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.fve.offreformation.OffreFormationDetailDao;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.OffreFormationDetail;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OffreFormationDetailDto;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OffreFormationDetailService;

/**
 * @author rlaib  on : 6 f�vr. 2014  14:44:30
 */

@Service("offreFormationDetailService")
public class OffreFormationDetailServiceImpl implements OffreFormationDetailService {

		@Autowired
		@Qualifier ("offreFormationDetailDao")
		private OffreFormationDetailDao offreFormationDetailDao;
	
	    @Autowired
		@Qualifier("mapper")
		private DozerBeanMapper mapper;
		
		/**
		 * [OffreFormationDetailServiceImpl.OffreFormationDetailServiceImpl()] Constructor
		 * @author  Rafik LAIB  on : 5 avr. 2014  21:51:06	
		 */
		public OffreFormationDetailServiceImpl(){
	
		}

		/**
		 * [OffreFormationDetailServiceImpl.insertOrUpdate] Method 
		 * overridden By :  @author  Rafik LAIB  
		 * On : 5 avr. 2014  21:51:14
		 * @param offreFormationDetailDto
		 * @return
		 */
		@Override
		@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
		public OffreFormationDetailDto  insertOrUpdate(
				OffreFormationDetailDto offreFormationDetailDto) {
	
			OffreFormationDetail offreFormationDetail = mapper.map(offreFormationDetailDto, OffreFormationDetail.class);
			
			if (offreFormationDetail.getId() == 0) 
				offreFormationDetailDao.persist(offreFormationDetail);
			else
				offreFormationDetail = offreFormationDetailDao.merge(offreFormationDetail);
	
			mapper.map(offreFormationDetail, offreFormationDetailDto);
			
			return offreFormationDetailDto;
		}

		/**
		 * [OffreFormationDetailServiceImpl.remove] Method 
		 * overridden By :  @author  Rafik LAIB  
		 * On : 5 avr. 2014  21:51:22
		 * @param offreFormationDetailDto
		 */
		@Override
		@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
		public void remove(OffreFormationDetailDto offreFormationDetailDto) {
	
			OffreFormationDetail offreFormationDetail = mapper.map(offreFormationDetailDto, OffreFormationDetail.class);
	
			offreFormationDetailDao.remove(offreFormationDetail);
			
		}

		/**
		 * [OffreFormationDetailServiceImpl.findById] Method 
		 * overridden By :  @author  Rafik LAIB  
		 * On : 5 avr. 2014  21:52:00
		 * @param id
		 * @return
		 */
		@Override
			public OffreFormationDetailDto findById(int id) {
		
				OffreFormationDetail offreFormationDetail = offreFormationDetailDao.findById(id);
		
				if (offreFormationDetail != null)
					return mapper.map(offreFormationDetail, OffreFormationDetailDto.class);
		
				return null;
		}
	
		/**
		 * [OffreFormationDetailServiceImpl.findAll] Method 
		 * overridden By :  @author  Rafik LAIB  
		 * On : 5 avr. 2014  21:52:20
		 * @return
		 */
		@Override
		public List<OffreFormationDetail> findAll() {		
		
					return offreFormationDetailDao.findAll();
					
		}
	
	
}
