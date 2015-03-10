/**
 * [dz.gov.mesrs.sii.recherche.business.service.impl.RechercheStructureServiceImpl.java] 
 * @author rlaib on : 14 d�c. 2014  17:35:31
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

import dz.gov.mesrs.sii.commons.data.dao.recherche.ProjetPartenaireDao;
import dz.gov.mesrs.sii.commons.data.model.recherche.ProjetPartenaire;
import dz.gov.mesrs.sii.recherche.business.model.dto.ProjetPartenaireDto;
import dz.gov.mesrs.sii.recherche.business.service.RechercheProjetPartenaireService;

/**
 * @author sRamdane  on : 08 fev 2015   17:35:31
 */
@Service("rechercheProjetPartenaireService")
public class RechercheProjetPartenaireServiceImpl implements RechercheProjetPartenaireService {

	@Autowired
	@Qualifier ("projetpartenaireDao")
	private ProjetPartenaireDao projetpartenaireDao;
	
	private static final Log log = LogFactory.getLog(RechercheProjetPartenaireServiceImpl.class);
	
    @Autowired
	@Qualifier("mapper")
	private DozerBeanMapper mapper;

    @Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void removeProjetPartenaire(Long partenaireId) {		
			try {
				ProjetPartenaire projetPartenaire =projetpartenaireDao.findById(partenaireId);
				if (projetPartenaire != null){projetpartenaireDao.merge(projetPartenaire);
					projetpartenaireDao.remove(partenaireId);}
			} catch (RuntimeException re) {
					throw re;
			}

		}

	
	
	@Override
	public ProjetPartenaireDto findById(Long partenaireId) {
		ProjetPartenaireDto projetPartenaireDto= new ProjetPartenaireDto() ;
		ProjetPartenaire projetPartenaire =projetpartenaireDao.findById(partenaireId);
		if (projetPartenaire != null) mapper.map(projetPartenaire, projetPartenaireDto);
		return projetPartenaireDto;
	}
		
	
	/**
	 * [RechercheGroupeService.insertOrUpdate] Method 
	 * Overridden By : @author rlaib  on : 16 dec. 2014  14:39:38
	 * @param structureDto
	 * @return
	 */
	
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public ProjetPartenaireDto savePartenaire(ProjetPartenaireDto partenaireProjet) {
		ProjetPartenaire projetPartenaire = mapper.map(partenaireProjet,ProjetPartenaire.class);
		try {
					if (projetPartenaire.getId() == null){
						projetpartenaireDao.persist(projetPartenaire);
					}else{						
						projetPartenaire= projetpartenaireDao.merge(projetPartenaire);						
					}					
					mapper.map(projetPartenaire, partenaireProjet);					
					return partenaireProjet;
		
		} catch (RuntimeException re) {
					log.error("insertOrUpdate ActiviteProjetDto failed : ", re);
					throw re;
		}
		
		
	}
	
	
	
	@Override
	public List<ProjetPartenaireDto> findListPartenaireByIdProjet(Long id) {
		List<ProjetPartenaire> listProjetPartenaire=projetpartenaireDao.findListPartenaireByIdProjet(id);
		List<ProjetPartenaireDto> listProjetPartenaireDto=new ArrayList<ProjetPartenaireDto>();
		if(listProjetPartenaire!=null)if(!listProjetPartenaire.isEmpty()){
			for(ProjetPartenaire projetPartenaire:listProjetPartenaire){
				ProjetPartenaireDto projetPartenaireDto = new ProjetPartenaireDto();
				mapper.map( projetPartenaire,projetPartenaireDto);	listProjetPartenaireDto.add(projetPartenaireDto);
			}
		}
		return listProjetPartenaireDto;
	}

	
		
	
}
