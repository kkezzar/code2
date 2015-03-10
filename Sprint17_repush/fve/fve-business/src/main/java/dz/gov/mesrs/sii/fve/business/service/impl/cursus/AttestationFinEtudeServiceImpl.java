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
import dz.gov.mesrs.sii.commons.data.dao.fve.cursus.AttestationFinEtudeDao;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.AttestationFinEtude;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.Dossier;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.AttestationFinEtudeDto;
import dz.gov.mesrs.sii.fve.business.service.cursus.AttestationFinEtudeService;


 

/**
 * @author BELDI Jamel  on : 14 octo. 2014  15:38:11
 */
@Service("attestationFinEtudeService")
public class AttestationFinEtudeServiceImpl implements AttestationFinEtudeService  {

	@Autowired
	@Qualifier ("attestationFinEtudeDao")
	private AttestationFinEtudeDao attestationFinEtudeDao;
	
	@Autowired
	@Qualifier ("situationEntiteDao")
	private SituationEntiteDao situationEntiteDao;
	private static final Log log = LogFactory.getLog(AttestationFinEtudeServiceImpl.class);
	
    @Autowired
	@Qualifier("mapper")
	private DozerBeanMapper mapper;
	
	
	
	public AttestationFinEtudeServiceImpl(){

	}



	@Override
	public AttestationFinEtudeDto /*List<attestationFinEtudeMatiereDto>*/  findByIdInscriptionAdministrative(
			int idInscriptionAdministrative) {
		try{
		AttestationFinEtude attestationFinEtude = attestationFinEtudeDao.findByIdInscriptionAdministrative(idInscriptionAdministrative);
		 if(attestationFinEtude!=null && attestationFinEtude.getId()!=0){
			 return findById(attestationFinEtude.getId());
		 }
		} catch (RuntimeException e) {
			log.error("findByIdInscriptionAdministrative failed", e);
			throw e;

		}	
		return null;
	}


	
	/**
	 * [attestationFinEtudeServiceImpl.attestationFinEtudeDao] Getter 
	 * @author BELDI Jamel on : 15 octo. 2014  14:47:09
	 * @return the attestationFinEtudeDao
	 */
	public AttestationFinEtudeDao getAttestationFinEtudeDao() {
		return attestationFinEtudeDao;
	}



	/**
	 * [attestationFinEtudeServiceImpl.attestationFinEtudeDao] Setter 
	 * @author BELDI Jamel on : 15 octo. 2014  14:47:09
	 * @param attestationFinEtudeDao the attestationFinEtudeDao to set
	 */
	public void setAttestationFinEtudeDao(
			AttestationFinEtudeDao attestationFinEtudeDao) {
		this.attestationFinEtudeDao = attestationFinEtudeDao;
	}



	/**
	 * [attestationFinEtudeServiceImpl.mapper] Getter 
	 * @author BELDI Jamel on : 15 octo. 2014  14:47:09
	 * @return the mapper
	 */
	public DozerBeanMapper getMapper() {
		return mapper;
	}



	/**
	 * [attestationFinEtudeServiceImpl.mapper] Setter 
	 * @author BELDI Jamel on : 15 octo. 2014  14:47:09
	 * @param mapper the mapper to set
	 */
	public void setMapper(DozerBeanMapper mapper) {
		this.mapper = mapper;
	}



	/**
	 * [attestationFinEtudeServiceImpl.situationEntiteDao] Getter 
	 * @author BELDI Jamel on : 17 octo. 2014  11:30:27
	 * @return the situationEntiteDao
	 */
	public SituationEntiteDao getSituationEntiteDao() {
		return situationEntiteDao;
	}



	/**
	 * [attestationFinEtudeServiceImpl.situationEntiteDao] Setter 
	 * @author BELDI Jamel on : 17 octo. 2014  11:30:27
	 * @param situationEntiteDao the situationEntiteDao to set
	 */
	public void setSituationEntiteDao(SituationEntiteDao situationEntiteDao) {
		this.situationEntiteDao = situationEntiteDao;
	}



	
	@Override
	public AttestationFinEtudeDto/*List<attestationFinEtudeMatiereDto>*/ findByIdInscriptionAdministrativeAndPeriode(
			int idInscriptionAdministrative, int idPeriode) {
		try{
		AttestationFinEtude attestationFinEtude = attestationFinEtudeDao.findByIdInscriptionAdministrativeAndPeriode(idInscriptionAdministrative,idPeriode);
		 if(attestationFinEtude!=null && attestationFinEtude.getId()!=0){
			 return findById(attestationFinEtude.getId());
		 }
		} catch (RuntimeException e) {
			log.error("findByIdInscriptionAdministrative failed", e);
			throw e;

		}	
		return null;
	}





	@Override
	public List<AttestationFinEtudeDto> findBySession(int idSession) {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public List<AttestationFinEtudeDto> findAdvanced2(AttestationFinEtudeDto searchDto) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public AttestationFinEtudeDto findByIdDossier(int idDossier) {
		// TODO Auto-generated method stub
		return null;
	}






	@Override
	public List<AttestationFinEtudeDto> findByQuery(String query) {
	
	    //List<attestationFinEtude> attestationFinEtudes = attestationFinEtudeDao.findByQuery(query);

		List<AttestationFinEtudeDto> attestationFinEtudeDtos = new ArrayList<AttestationFinEtudeDto>();

//		for (attestationFinEtude attestationFinEtude : attestationFinEtudes) {
//			attestationFinEtudeDtos.add(mapper.map(attestationFinEtude, attestationFinEtudeDto.class));
//		}

		return attestationFinEtudeDtos;
	
	}
	
	
	@Override
	public void remove(AttestationFinEtudeDto attestationFinEtudeDto) {

		AttestationFinEtude attestationFinEtude = mapper.map(attestationFinEtudeDto,
				AttestationFinEtude.class);

		attestationFinEtudeDao.remove(attestationFinEtude);
	}



	@Override
	public AttestationFinEtudeDto findById(int id) {

		AttestationFinEtude attestationFinEtude = attestationFinEtudeDao.findById(id);

		if (attestationFinEtude != null)
			return mapper.map(attestationFinEtude, AttestationFinEtudeDto.class);

		return null;
	}

	@Override
	public List<AttestationFinEtudeDto> findAll() {
		try {
			List<AttestationFinEtudeDto> result = new ArrayList<AttestationFinEtudeDto>();

			List<AttestationFinEtude> resultListDao = attestationFinEtudeDao.findAll();
			if (resultListDao != null && !resultListDao.isEmpty()) {
				for (AttestationFinEtude attestationFinEtude : resultListDao) {
					AttestationFinEtudeDto attestationFinEtudeDto = new AttestationFinEtudeDto();
					mapper.map(attestationFinEtude, attestationFinEtudeDto);
					result.add(attestationFinEtudeDto);
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
	public void delete(AttestationFinEtudeDto attestationFinEtudeDto) {
		// TODO Auto-generated method stub
		
	}


		
		
		@Override
		public List<AttestationFinEtudeDto> findAdvanced(
				AttestationFinEtudeDto dto) {
			try{
				AttestationFinEtude _searchBo = mapper.map(dto,
						AttestationFinEtude.class);
				List<AttestationFinEtude> _attestationFinEtudeList = attestationFinEtudeDao.findAdvanced(_searchBo);
				 if(_attestationFinEtudeList!=null && !_attestationFinEtudeList.isEmpty()){
					 List <AttestationFinEtudeDto> result = new ArrayList<AttestationFinEtudeDto>();
						for (AttestationFinEtude attestationFinEtude : _attestationFinEtudeList) {
							AttestationFinEtudeDto attestationFinEtudeDto = new AttestationFinEtudeDto()	;
							mapper.map(attestationFinEtude, attestationFinEtudeDto);
							
						
							
							result.add(attestationFinEtudeDto);
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
		public List<AttestationFinEtudeDto> findAttestationtoValidate(String codeEtab) {
			// TODO Auto-generated method stub
			return null;
		}



		@Override
		public AttestationFinEtudeDto insertOrUpdate(AttestationFinEtudeDto attestationFinEtudeDto) {
			try {
				Dossier dossier = new Dossier();
				mapper.map(attestationFinEtudeDto, dossier);
				AttestationFinEtude attestationFinEtude = mapper.map(attestationFinEtudeDto,
						AttestationFinEtude.class);

				if (attestationFinEtude.getId() == 0) {
					attestationFinEtudeDao.persist(attestationFinEtude);
				} else {
					attestationFinEtude = attestationFinEtudeDao.merge(attestationFinEtude);
				}
				
				mapper.map(attestationFinEtude, attestationFinEtudeDto);

				log.info("insertOrUpdate success");
				return attestationFinEtudeDto;
			} catch (Exception e) {
				log.error("insertOrUpdate failed", e);
				throw e;
			}
		}		
}
