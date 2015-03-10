package dz.gov.mesrs.sii.fve.business.service.impl.scolarite;

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

import dz.gov.mesrs.sii.commons.data.dao.fve.scolarite.AffectationLieuStructureDao;
import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefEtablissementDao;
import dz.gov.mesrs.sii.commons.data.model.fve.scolarite.AffectationLieuStructure;
import dz.gov.mesrs.sii.fve.business.model.dto.scolarite.AffectationLieuStructureDto;
import dz.gov.mesrs.sii.fve.business.service.scolarite.AffectationLieuStructureService;

/**
 * 
 * @author BELDI Jamel  on : 7 oct. 2014  17:54:44
 */
 

@Service("affectationLieuStructureService")
public class AffectationLieuStructureServiceImpl implements AffectationLieuStructureService  {

	@Autowired
	@Qualifier ("affectationLieuStructureDao")
	private AffectationLieuStructureDao affectationLieuStructureDao;

	private static final Log log = LogFactory.getLog(AffectationLieuStructureServiceImpl.class);
	
    @Autowired
	@Qualifier("mapper")
	private DozerBeanMapper mapper;
	
    @Autowired
	@Qualifier ("refEtablissementDaoImpl")
	private RefEtablissementDao refEtablissementDao;
	
	public AffectationLieuStructureServiceImpl(){

	}


	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public AffectationLieuStructureDto  insertOrUpdate(
			AffectationLieuStructureDto affectationLieuStructureDto) {

		AffectationLieuStructure affectationLieuStructure = mapper.map(affectationLieuStructureDto,
				AffectationLieuStructure.class);
		try {
			if (affectationLieuStructure.getId() == 0){
				affectationLieuStructureDao.persist(affectationLieuStructure);
			}else{
				affectationLieuStructure = affectationLieuStructureDao.merge(affectationLieuStructure);
			}
			mapper.map(affectationLieuStructure, affectationLieuStructureDto);
			
			log.error("insertorupdate AffectationLieuStructure succes..");
			
			return affectationLieuStructureDto;
		
		} catch (RuntimeException e) {
			log.error("insertorupdate AffectationLieuStructure failed--", e);
			throw e;
		}
	}
	

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void remove(AffectationLieuStructureDto affectationLieuStructureDto) {
		try {

		AffectationLieuStructure affectationLieuStructure = mapper.map(affectationLieuStructureDto,
				AffectationLieuStructure.class);
		
		affectationLieuStructure = affectationLieuStructureDao.merge(affectationLieuStructure);

		affectationLieuStructureDao.remove(affectationLieuStructure);
		} catch (RuntimeException e) {
			throw e;
		}
	}


	@Override
	public AffectationLieuStructureDto findById(int id) {

		AffectationLieuStructure affectationLieuStructure = affectationLieuStructureDao.findById(id);

		if (affectationLieuStructure != null)
			return mapper.map(affectationLieuStructure, AffectationLieuStructureDto.class);

		return null;
	}

	
	
	@Override
	public List<AffectationLieuStructureDto> findAll() {		
		try {
		List<AffectationLieuStructure> affectationLieuStructures = affectationLieuStructureDao.findAll();

		List<AffectationLieuStructureDto> affectationLieuStructureDtos = new ArrayList<AffectationLieuStructureDto>();

		for (AffectationLieuStructure affectationLieuStructure : affectationLieuStructures) {
			affectationLieuStructureDtos.add(mapper.map(affectationLieuStructure, AffectationLieuStructureDto.class));
		}

		return affectationLieuStructureDtos;		
		} catch (RuntimeException e) {
			throw e;
		}
	}


	@Override
	public List<AffectationLieuStructureDto> findAdvanced(
			AffectationLieuStructureDto searchDto) {
		try {
			AffectationLieuStructure searchBo = new AffectationLieuStructure();
			mapper.map(searchDto, searchBo);
//			if(searchDto.getRefCodeEtablissement()!=null){
//				RefEtablissement refEtablissement = refEtablissementDao.findByIdentifiant(searchDto.getRefCodeEtablissement());
//			    RefStructure refStructure = searchBo.getRefStructure();
//			    refStructure.setRefEtablissement(refEtablissement);
//			    searchBo.setRefStructure(refStructure);
//			}
			List<AffectationLieuStructure> affectationLieuStructures = affectationLieuStructureDao.findAdvanced(searchBo);

			List<AffectationLieuStructureDto> affectationLieuStructureDtos = new ArrayList<AffectationLieuStructureDto>();
            if(affectationLieuStructures!=null && !affectationLieuStructures.isEmpty()){
			for (AffectationLieuStructure affectationLieuStructure : affectationLieuStructures) {
				affectationLieuStructureDtos.add(mapper.map(affectationLieuStructure, AffectationLieuStructureDto.class));
			}
            }
			return affectationLieuStructureDtos;		
			} catch (RuntimeException e) {
				throw e;
			}
	}
	
	
}



