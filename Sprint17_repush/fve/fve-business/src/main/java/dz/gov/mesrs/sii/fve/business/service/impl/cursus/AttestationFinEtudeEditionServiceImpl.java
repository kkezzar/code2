package dz.gov.mesrs.sii.fve.business.service.impl.cursus;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.commons.data.dao.bpm.SituationEntiteDao;
import dz.gov.mesrs.sii.commons.data.dao.fve.cursus.AttestationFinEtudeEditionDao;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.AttestationFinEtudeEdition;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.Dossier;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.AttestationFinEtudeEditionDto;
import dz.gov.mesrs.sii.fve.business.service.cursus.AttestationFinEtudeEditionService;

/**
 * @author BELDI Jamel  on : 14 octo. 2014  15:38:11
 */
@Service("attestationFinEtudeEditionService")
public class AttestationFinEtudeEditionServiceImpl implements AttestationFinEtudeEditionService  {

	@Autowired
	@Qualifier ("attestationFinEtudeEditionDao")
	private AttestationFinEtudeEditionDao attestationFinEtudeEditionDao;
	
	@Autowired
	@Qualifier ("situationEntiteDao")
	private SituationEntiteDao situationEntiteDao;
	private static final Log log = LogFactory.getLog(AttestationFinEtudeEditionServiceImpl.class);
	
    @Autowired
	@Qualifier("mapper")
	private DozerBeanMapper mapper;
	

	public AttestationFinEtudeEditionServiceImpl(){

	}


	
	/**
	 * [AttestationFinEtudeEditionServiceImpl.AttestationFinEtudeEditionDao] Getter 
	 * @author BELDI Jamel on : 15 octo. 2014  14:47:09
	 * @return the AttestationFinEtudeEditionDao
	 */
	public AttestationFinEtudeEditionDao getAttestationFinEtudeEditionDao() {
		return attestationFinEtudeEditionDao;
	}



	/**
	 * [AttestationFinEtudeEditionServiceImpl.AttestationFinEtudeEditionDao] Setter 
	 * @author BELDI Jamel on : 15 octo. 2014  14:47:09
	 * @param AttestationFinEtudeEditionDao the AttestationFinEtudeEditionDao to set
	 */
	public void setAttestationFinEtudeEditionDao(
			AttestationFinEtudeEditionDao attestationFinEtudeEditionDao) {
		this.attestationFinEtudeEditionDao = attestationFinEtudeEditionDao;
	}



	/**
	 * [AttestationFinEtudeEditionServiceImpl.mapper] Getter 
	 * @author BELDI Jamel on : 15 octo. 2014  14:47:09
	 * @return the mapper
	 */
	public DozerBeanMapper getMapper() {
		return mapper;
	}



	/**
	 * [AttestationFinEtudeEditionServiceImpl.mapper] Setter 
	 * @author BELDI Jamel on : 15 octo. 2014  14:47:09
	 * @param mapper the mapper to set
	 */
	public void setMapper(DozerBeanMapper mapper) {
		this.mapper = mapper;
	}



	/**
	 * [AttestationFinEtudeEditionServiceImpl.situationEntiteDao] Getter 
	 * @author BELDI Jamel on : 17 octo. 2014  11:30:27
	 * @return the situationEntiteDao
	 */
	public SituationEntiteDao getSituationEntiteDao() {
		return situationEntiteDao;
	}



	/**
	 * [AttestationFinEtudeEditionServiceImpl.situationEntiteDao] Setter 
	 * @author BELDI Jamel on : 17 octo. 2014  11:30:27
	 * @param situationEntiteDao the situationEntiteDao to set
	 */
	public void setSituationEntiteDao(SituationEntiteDao situationEntiteDao) {
		this.situationEntiteDao = situationEntiteDao;
	}




	
	
	@Override
	public void remove(AttestationFinEtudeEditionDto attestationFinEtudeEditionDto) {

		AttestationFinEtudeEdition attestationFinEtudeEdition = mapper.map(attestationFinEtudeEditionDto,
				AttestationFinEtudeEdition.class);
		
		attestationFinEtudeEdition = attestationFinEtudeEditionDao.merge(attestationFinEtudeEdition);

		attestationFinEtudeEditionDao.remove(attestationFinEtudeEdition);
	}



	@Override
	public AttestationFinEtudeEditionDto findById(int id) {

		AttestationFinEtudeEdition attestationFinEtudeEdition = attestationFinEtudeEditionDao.findById(id);

		if (attestationFinEtudeEdition != null)
			return mapper.map(attestationFinEtudeEdition, AttestationFinEtudeEditionDto.class);

		return null;
	}

	@Override
	public List<AttestationFinEtudeEditionDto> findAll() {
		try {
			List<AttestationFinEtudeEditionDto> result = new ArrayList<AttestationFinEtudeEditionDto>();

			List<AttestationFinEtudeEdition> resultListDao = attestationFinEtudeEditionDao.findAll();
			if (resultListDao != null && !resultListDao.isEmpty()) {
				for (AttestationFinEtudeEdition attestationFinEtudeEdition : resultListDao) {
					AttestationFinEtudeEditionDto attestationFinEtudeEditionDto = new AttestationFinEtudeEditionDto();
					mapper.map(attestationFinEtudeEdition, attestationFinEtudeEditionDto);
					result.add(attestationFinEtudeEditionDto);
				}
				return result;
			}

		} catch (RuntimeException e) {
			log.error("findAll failed", e);
			throw e;

		}

		return null;
	}



	@Override
	public void delete(AttestationFinEtudeEditionDto attestationFinEtudeEditionDto) {
		// TODO Auto-generated method stub
		
	}


		
		
		@Override
		public List<AttestationFinEtudeEditionDto> findAdvanced(
				AttestationFinEtudeEditionDto dto) {
			try{
				AttestationFinEtudeEdition _searchBo = mapper.map(dto,
						AttestationFinEtudeEdition.class);
				List<AttestationFinEtudeEdition> _attestationFinEtudeEditionList = attestationFinEtudeEditionDao.findAdvanced(_searchBo);
				 if(_attestationFinEtudeEditionList!=null && !_attestationFinEtudeEditionList.isEmpty()){
					 List <AttestationFinEtudeEditionDto> result = new ArrayList<AttestationFinEtudeEditionDto>();
						for (AttestationFinEtudeEdition attestationFinEtudeEdition : _attestationFinEtudeEditionList) {
							AttestationFinEtudeEditionDto attestationFinEtudeEditionDto = new AttestationFinEtudeEditionDto()	;
							mapper.map(attestationFinEtudeEdition, attestationFinEtudeEditionDto);
							
						
							
							result.add(attestationFinEtudeEditionDto);
						}
						return result;
				 }
				} catch (RuntimeException e) {
					log.error("findAdvanced failed", e);
					throw e;

				}	
				return null;
		}


		@Override
		public AttestationFinEtudeEditionDto insertOrUpdate(AttestationFinEtudeEditionDto attestationFinEtudeEditionDto) {
			try {
				Dossier dossier = new Dossier();
				mapper.map(attestationFinEtudeEditionDto, dossier);
				AttestationFinEtudeEdition attestationFinEtudeEdition = mapper.map(attestationFinEtudeEditionDto,
						AttestationFinEtudeEdition.class);

				if (attestationFinEtudeEdition.getId() == 0) {
					attestationFinEtudeEditionDao.persist(attestationFinEtudeEdition);
				} else {
					attestationFinEtudeEdition = attestationFinEtudeEditionDao.merge(attestationFinEtudeEdition);
				}
				
				mapper.map(attestationFinEtudeEdition, attestationFinEtudeEditionDto);

				log.info("insertOrUpdate success");
				return attestationFinEtudeEditionDto;
			} catch (Exception e) {
				log.error("insertOrUpdate failed", e);
				throw e;
			}
		}



		@Override
		public List<AttestationFinEtudeEditionDto> findEditionByIdAttestation(int idAttestationFinEtude) {
			List<AttestationFinEtudeEdition> attestationFinEtudeEditions = attestationFinEtudeEditionDao.findEditionByIdAttestation(idAttestationFinEtude);

			
			if(attestationFinEtudeEditions==null || attestationFinEtudeEditions.isEmpty()) return null;
			
			List<AttestationFinEtudeEditionDto> attestationFinEtudeEditionDtos = new ArrayList<AttestationFinEtudeEditionDto>();

			for (AttestationFinEtudeEdition attestationFinEtudeEdition : attestationFinEtudeEditions) {
				attestationFinEtudeEditionDtos.add(mapper.map(attestationFinEtudeEdition, AttestationFinEtudeEditionDto.class));
			}

			return attestationFinEtudeEditionDtos;		
		}		
}
