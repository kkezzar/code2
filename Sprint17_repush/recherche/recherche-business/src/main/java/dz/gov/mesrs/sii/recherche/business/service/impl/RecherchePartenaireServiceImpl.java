/**
 * [dz.gov.mesrs.sii.recherche.business.service.impl.RecherchePartenaireServiceImpl.java] 
 * @author rlaib on : 24 déc. 2014  08:49:55
 */
package dz.gov.mesrs.sii.recherche.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.recherche.PartenaireDao;
import dz.gov.mesrs.sii.commons.data.model.recherche.IndEvaluation;
import dz.gov.mesrs.sii.commons.data.model.recherche.Partenaire;
import dz.gov.mesrs.sii.commons.data.model.recherche.ProjetPartenaire;
import dz.gov.mesrs.sii.recherche.business.model.dto.IndEvaluationDto;
import dz.gov.mesrs.sii.recherche.business.model.dto.PartenaireDto;
import dz.gov.mesrs.sii.recherche.business.model.dto.ProjetPartenaireDto;
import dz.gov.mesrs.sii.recherche.business.service.RecherchePartenaireService;

/**
 * @author rlaib  on : 24 déc. 2014  08:49:55
 */
@Service("recherchePartenaireService")
public class RecherchePartenaireServiceImpl implements
		RecherchePartenaireService {

	@Autowired
	@Qualifier ("partenaireDao")
	private PartenaireDao partenaireDao;
	
	private static final Log log = LogFactory.getLog(RecherchePartenaireServiceImpl.class);
	
    @Autowired
	@Qualifier("mapper")
	private DozerBeanMapper mapper;
    
	/**
	 * [RecherchePartenaireServiceImpl.insertOrUpdate] Method 
	 * Overridden By : @author rlaib  on : 24 déc. 2014  08:49:55
	 * @param partenaireDto
	 * @return
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public PartenaireDto insertOrUpdate(PartenaireDto partenaireDto) {
			
		Partenaire partenaire = mapper.map(partenaireDto,
				Partenaire.class);
		try {
			if (partenaire.getId() == null){
				partenaireDao.persist(partenaire);
			}else{
				partenaire = partenaireDao.merge(partenaire);
			}
			mapper.map(partenaire, partenaireDto);
			return partenaireDto;

		} catch (RuntimeException re) {
			log.error("insertOrUpdate PartenaireDto failed : ", re);
			throw re;
		}
	}

	/**
	 * [RecherchePartenaireServiceImpl.remove] Method 
	 * Overridden By : @author rlaib  on : 24 déc. 2014  08:49:55
	 * @param id
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void remove(Long id) {
		try {
				partenaireDao.remove(id);
		} catch (RuntimeException re) {
				throw re;
		}

	}

	/**
	 * [RecherchePartenaireServiceImpl.findStructureRecherchePartenaires] Method 
	 * Overridden By : @author rlaib  on : 24 déc. 2014  08:49:55
	 * @param idStructure
	 * @return
	 */
	@Override
	public List<PartenaireDto> findStructureRecherchePartenaires(
			Long idStructure) {
		
		try {
			List<Partenaire> partenaires = partenaireDao.findStructureRecherchePartenaires(idStructure);
			List<PartenaireDto> partenaireDtos = new ArrayList<PartenaireDto>();
			for (Partenaire partenaire: partenaires) {
				PartenaireDto partenaireDto = new PartenaireDto();
				mapper.map(partenaire, partenaireDto);
				partenaireDtos.add(partenaireDto);
			}
			return partenaireDtos;
		} catch (RuntimeException re) {
			log.error("findStructureRecherchePartenaires PartenaireDto", re);
			throw re;
		}
	}

	
	
	
	
	
	
	
	
	
	
	
	/**
	 * [RecherchePartenaireServiceImpl.findById] Method 
	 * Overridden By : @author rlaib  on : 24 déc. 2014  08:49:55
	 * @param id
	 * @return
	 */
	@Override
	public PartenaireDto findById(Long id) {
		
		try {
			Partenaire partenaire = partenaireDao.findById(id);
			if (partenaire != null) {
					PartenaireDto partenaireDto = new PartenaireDto();
					mapper.map(partenaire, partenaireDto);
					return partenaireDto;
			}
			return null;

	} catch (RuntimeException re) {
			log.error("findById partenaireDto failed : ", re);
			throw re;
	}
	}

	

	
	
}

