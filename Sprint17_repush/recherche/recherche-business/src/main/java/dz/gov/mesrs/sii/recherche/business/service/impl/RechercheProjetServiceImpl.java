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

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.EtapeDto;
import dz.gov.mesrs.sii.commons.data.dao.recherche.ProjetDao;
import dz.gov.mesrs.sii.commons.data.model.bpm.ActionEntite;
import dz.gov.mesrs.sii.commons.data.model.bpm.Etape;
import dz.gov.mesrs.sii.commons.data.model.bpm.SituationEntite;
import dz.gov.mesrs.sii.commons.data.model.recherche.ActiviteProjet;
import dz.gov.mesrs.sii.commons.data.model.recherche.Projet;
import dz.gov.mesrs.sii.commons.data.util.UtilConstant;
import dz.gov.mesrs.sii.recherche.business.model.dto.ActiviteProjetDto;
import dz.gov.mesrs.sii.recherche.business.model.dto.ProjetDto;
import dz.gov.mesrs.sii.recherche.business.service.RechercheProjetService;

/**
 * @author sRamdane  on : 08 fev 2015   17:35:31
 */
@Service("rechercheProjetService")
public class RechercheProjetServiceImpl implements RechercheProjetService {

	@Autowired
	@Qualifier ("projetDao")
	private ProjetDao projetDao;
	
	private static final Log log = LogFactory.getLog(RechercheProjetServiceImpl.class);
	
    @Autowired
	@Qualifier("mapper")
	private DozerBeanMapper mapper;

	@Override
	public List<ProjetDto> findByCodeProjet(String codeProjet, Integer idSituation) {
		try {
			List<Projet> projetList= projetDao.findByCodeProjet(codeProjet,idSituation);
			List<ProjetDto> projetDtos = new ArrayList<ProjetDto>();
			for (Projet projet : projetList) {
				ProjetDto projetDto = new ProjetDto();
				mapper.map(projet, projetDto);
				projetDtos.add(projetDto);
			}
			return projetDtos;
		} catch (RuntimeException re) {
			log.error("findAll ProjetDto", re);
			throw re;
		}
		
	}

	@Override
	public List<ProjetDto> findByKeyWords(String keyWord) {
		try {
			List<Projet> projetList= projetDao.findByKeyWords(keyWord);
			List<ProjetDto> projetDtos = new ArrayList<ProjetDto>();
			for (Projet projet : projetList) {
				ProjetDto projetDto = new ProjetDto();
				mapper.map(projet, projetDto);
				projetDtos.add(projetDto);
			}
			return projetDtos;
		} catch (RuntimeException re) {
			log.error("findAll projetDto", re);
			throw re;
		}
		
	}
	
	
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public ProjetDto saveProjet(ProjetDto projetDto,String annee,String structure) {
		
		Projet projet = mapper.map(projetDto, Projet.class);
		try {
					if (projet != null){
						
						if((projet.getId())!= null)
							
							if(projetDao.findById(projet.getId())!= null) projet=projetDao.merge(projet);
						else
						
						projet=	projetDao.saveProjet(projet,annee,structure);
						else
							
							projet=	projetDao.saveProjet(projet,annee,structure);
						mapper.map(projet, projetDto);
						return projetDto;
					
					}else return null;					
					
		
		} catch (RuntimeException re) {
					log.error("saveOneStepValidation etapeDto failed : ", re);
					throw re;
		}
		
	}
	
	 
	
	

	
}
