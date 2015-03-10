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
import dz.gov.mesrs.sii.commons.data.dao.fve.cursus.DiplomeFinEtudeEditionDao;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.DiplomeFinEtudeEdition;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.Dossier;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DiplomeFinEtudeEditionDto;
import dz.gov.mesrs.sii.fve.business.service.cursus.DiplomeFinEtudeEditionService;


 

/**
 * @author BELDI Jamel  on : 14 octo. 2014  15:38:11
 */
@Service("diplomeFinEtudeEditionService")
public class DiplomeFinEtudeEditionServiceImpl implements DiplomeFinEtudeEditionService  {

	@Autowired
	@Qualifier ("diplomeFinEtudeEditionDao")
	private DiplomeFinEtudeEditionDao diplomeFinEtudeEditionDao;
	
	@Autowired
	@Qualifier ("situationEntiteDao")
	private SituationEntiteDao situationEntiteDao;
	private static final Log log = LogFactory.getLog(DiplomeFinEtudeEditionServiceImpl.class);
	
    @Autowired
	@Qualifier("mapper")
	private DozerBeanMapper mapper;
	
	
	
	public DiplomeFinEtudeEditionServiceImpl(){

	}


	
	/**
	 * [diplomeFinEtudeEditionServiceImpl.diplomeFinEtudeEditionDao] Getter 
	 * @author BELDI Jamel on : 15 octo. 2014  14:47:09
	 * @return the diplomeFinEtudeEditionDao
	 */
	public DiplomeFinEtudeEditionDao getDiplomeFinEtudeEditionDao() {
		return diplomeFinEtudeEditionDao;
	}



	/**
	 * [diplomeFinEtudeEditionServiceImpl.diplomeFinEtudeEditionDao] Setter 
	 * @author BELDI Jamel on : 15 octo. 2014  14:47:09
	 * @param diplomeFinEtudeEditionDao the diplomeFinEtudeEditionDao to set
	 */
	public void setDiplomeFinEtudeEditionDao(
			DiplomeFinEtudeEditionDao diplomeFinEtudeEditionDao) {
		this.diplomeFinEtudeEditionDao = diplomeFinEtudeEditionDao;
	}



	/**
	 * [diplomeFinEtudeEditionServiceImpl.mapper] Getter 
	 * @author BELDI Jamel on : 15 octo. 2014  14:47:09
	 * @return the mapper
	 */
	public DozerBeanMapper getMapper() {
		return mapper;
	}



	/**
	 * [diplomeFinEtudeEditionServiceImpl.mapper] Setter 
	 * @author BELDI Jamel on : 15 octo. 2014  14:47:09
	 * @param mapper the mapper to set
	 */
	public void setMapper(DozerBeanMapper mapper) {
		this.mapper = mapper;
	}



	/**
	 * [diplomeFinEtudeEditionServiceImpl.situationEntiteDao] Getter 
	 * @author BELDI Jamel on : 17 octo. 2014  11:30:27
	 * @return the situationEntiteDao
	 */
	public SituationEntiteDao getSituationEntiteDao() {
		return situationEntiteDao;
	}



	/**
	 * [diplomeFinEtudeEditionServiceImpl.situationEntiteDao] Setter 
	 * @author BELDI Jamel on : 17 octo. 2014  11:30:27
	 * @param situationEntiteDao the situationEntiteDao to set
	 */
	public void setSituationEntiteDao(SituationEntiteDao situationEntiteDao) {
		this.situationEntiteDao = situationEntiteDao;
	}




	
	
	@Override
	public void remove(DiplomeFinEtudeEditionDto diplomeFinEtudeEditionDto) {

		DiplomeFinEtudeEdition diplomeFinEtudeEdition = mapper.map(diplomeFinEtudeEditionDto,
				DiplomeFinEtudeEdition.class);
		
		diplomeFinEtudeEdition = diplomeFinEtudeEditionDao.merge(diplomeFinEtudeEdition);

		diplomeFinEtudeEditionDao.remove(diplomeFinEtudeEdition);
	}



	@Override
	public DiplomeFinEtudeEditionDto findById(int id) {

		DiplomeFinEtudeEdition diplomeFinEtudeEdition = diplomeFinEtudeEditionDao.findById(id);

		if (diplomeFinEtudeEdition != null)
			return mapper.map(diplomeFinEtudeEdition, DiplomeFinEtudeEditionDto.class);

		return null;
	}

	@Override
	public List<DiplomeFinEtudeEditionDto> findAll() {
		try {
			List<DiplomeFinEtudeEditionDto> result = new ArrayList<DiplomeFinEtudeEditionDto>();

			List<DiplomeFinEtudeEdition> resultListDao = diplomeFinEtudeEditionDao.findAll();
			if (resultListDao != null && !resultListDao.isEmpty()) {
				for (DiplomeFinEtudeEdition diplomeFinEtudeEdition : resultListDao) {
					DiplomeFinEtudeEditionDto diplomeFinEtudeEditionDto = new DiplomeFinEtudeEditionDto();
					mapper.map(diplomeFinEtudeEdition, diplomeFinEtudeEditionDto);
					result.add(diplomeFinEtudeEditionDto);
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
	public void delete(DiplomeFinEtudeEditionDto diplomeFinEtudeEditionDto) {
		// TODO Auto-generated method stub
		
	}


		
		
		@Override
		public List<DiplomeFinEtudeEditionDto> findAdvanced(
				DiplomeFinEtudeEditionDto dto) {
			try{
				DiplomeFinEtudeEdition _searchBo = mapper.map(dto,
						DiplomeFinEtudeEdition.class);
				List<DiplomeFinEtudeEdition> _diplomeFinEtudeEditionList = diplomeFinEtudeEditionDao.findAdvanced(_searchBo);
				 if(_diplomeFinEtudeEditionList!=null && !_diplomeFinEtudeEditionList.isEmpty()){
					 List <DiplomeFinEtudeEditionDto> result = new ArrayList<DiplomeFinEtudeEditionDto>();
						for (DiplomeFinEtudeEdition diplomeFinEtudeEdition : _diplomeFinEtudeEditionList) {
							DiplomeFinEtudeEditionDto diplomeFinEtudeEditionDto = new DiplomeFinEtudeEditionDto()	;
							mapper.map(diplomeFinEtudeEdition, diplomeFinEtudeEditionDto);
							
						
							
							result.add(diplomeFinEtudeEditionDto);
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
		public DiplomeFinEtudeEditionDto insertOrUpdate(DiplomeFinEtudeEditionDto diplomeFinEtudeEditionDto) {
			try {
				Dossier dossier = new Dossier();
				mapper.map(diplomeFinEtudeEditionDto, dossier);
				DiplomeFinEtudeEdition diplomeFinEtudeEdition = mapper.map(diplomeFinEtudeEditionDto,
						DiplomeFinEtudeEdition.class);

				if (diplomeFinEtudeEdition.getId() == 0) {
					diplomeFinEtudeEditionDao.persist(diplomeFinEtudeEdition);
				} else {
					diplomeFinEtudeEdition = diplomeFinEtudeEditionDao.merge(diplomeFinEtudeEdition);
				}
				
				mapper.map(diplomeFinEtudeEdition, diplomeFinEtudeEditionDto);

				log.info("insertOrUpdate success");
				return diplomeFinEtudeEditionDto;
			} catch (Exception e) {
				log.error("insertOrUpdate failed", e);
				throw e;
			}
		}



		@Override
		public List<DiplomeFinEtudeEditionDto> findEditionByIdDiplome(int idDiplomeFinEtude) {
			List<DiplomeFinEtudeEdition> diplomeFinEtudeEditions = diplomeFinEtudeEditionDao.findEditionByIdDiplome(idDiplomeFinEtude);

			
			if(diplomeFinEtudeEditions==null || diplomeFinEtudeEditions.isEmpty()) return null;
			
			List<DiplomeFinEtudeEditionDto> diplomeFinEtudeEditionDtos = new ArrayList<DiplomeFinEtudeEditionDto>();

			for (DiplomeFinEtudeEdition diplomeFinEtudeEdition : diplomeFinEtudeEditions) {
				diplomeFinEtudeEditionDtos.add(mapper.map(diplomeFinEtudeEdition, DiplomeFinEtudeEditionDto.class));
			}

			return diplomeFinEtudeEditionDtos;		
		}		
}
