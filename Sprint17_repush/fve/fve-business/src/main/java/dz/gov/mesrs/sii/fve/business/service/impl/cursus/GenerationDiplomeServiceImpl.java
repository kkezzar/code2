/**
 * [dz.gov.mesrs.sii.fve.business.service.impl.bac.BacServiceImpl.java] 
 * @author  Oualid BELBRIK on : 21 octobre 2014  15:21:01
 */
package dz.gov.mesrs.sii.fve.business.service.impl.cursus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.bpm.SituationEntiteDao;
import dz.gov.mesrs.sii.commons.data.dao.fve.cursus.DiplomeFinEtudeDao;
import dz.gov.mesrs.sii.commons.data.dao.fve.cursus.DossierDao;
import dz.gov.mesrs.sii.commons.data.dao.fve.cursus.DossierInscriptionAdministrativeDao;
import dz.gov.mesrs.sii.commons.data.dao.fve.cursus.GenerationDiplomeDao;
import dz.gov.mesrs.sii.commons.data.dao.fve.cursus.PieceConstitutiveDao;
import dz.gov.mesrs.sii.commons.data.dao.fve.examen.BilanSessionDao;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.DiplomeFinEtude;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.GenerationDiplome;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DiplomeFinEtudeDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.GenerationDiplomeDto;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.BilanSessionDto;
import dz.gov.mesrs.sii.fve.business.service.cursus.GenerationDiplomeService;
import dz.gov.mesrs.sii.fve.business.util.UtilConstants;

/**
 * @author Oualid BELBRIK on : 21 mai 2014 15:21:01
 */
@Service("generationDiplomeService")
public class GenerationDiplomeServiceImpl implements GenerationDiplomeService {

	private static final Log log = LogFactory.getLog(GenerationDiplomeServiceImpl.class);

	
	@Autowired
	@Qualifier("bilanSessionDao")
	private BilanSessionDao bilanSessionDao;

	
	@Autowired
	@Qualifier("diplomeFinEtudeDao")
	private DiplomeFinEtudeDao diplomeFinEtudeDao;

	@Autowired
	@Qualifier("dossierInscriptionAdministrativeDao")
	private DossierInscriptionAdministrativeDao dossierInscriptionAdministrativeDao;

	@Autowired
	@Qualifier("dossierDao")
	private DossierDao dossierDao;

	@Autowired
	@Qualifier("situationEntiteDao")
	private SituationEntiteDao situationEntiteDao;

	
	@Autowired
	@Qualifier("generationDiplomeDao")
	private GenerationDiplomeDao generationDiplomeDao;

	@Autowired
	@Qualifier("pieceConstitutiveDao")
	private PieceConstitutiveDao pieceConstitutiveDao;

	@Autowired
	@Qualifier("mapper")
	private DozerBeanMapper mapper;


	

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public Map<String, Object> generateDiplomesFiles(List<BilanSessionDto> inputList, int idAnneeAcademique, String codeEtablissement) {

		try {
			Map<String, Object> _result = new HashMap<String, Object>();
			List<DiplomeFinEtudeDto> _diplomeFinEtudeDtos = new ArrayList<DiplomeFinEtudeDto>();
			Integer _codeAnneeAcademique = null;
		//	List<DossierInscriptionAdministrativeDto> _dossierInscriptionAdministrativeDtos = new ArrayList<DossierInscriptionAdministrativeDto>();
			
			long index = 0;
			for (BilanSessionDto _bilanSessionDto : inputList) {

				if (_codeAnneeAcademique == null)
					_codeAnneeAcademique = _bilanSessionDto.getAnneeAcademiqueId();
					
						// Generation du diplome
						DiplomeFinEtudeDto _diplomeFinEtudeDto = new DiplomeFinEtudeDto();
						_diplomeFinEtudeDto.setDateCreation(new java.util.Date());
						_diplomeFinEtudeDto.setBilanSessionId(_bilanSessionDto.getId());
						_diplomeFinEtudeDto.setAnneeAcademiqueId(_bilanSessionDto.getAnneeAcademiqueId());
						_diplomeFinEtudeDto.setDossierInscriptionAdministrativeId(_bilanSessionDto.getDossierInscriptionAdministrativeId());
						
						
						DiplomeFinEtude _diplomeFinEtude = mapper.map(_diplomeFinEtudeDto, DiplomeFinEtude.class);

						if (_diplomeFinEtude.getId() == 0) {

							diplomeFinEtudeDao.persist(_diplomeFinEtude);
						} else {
							_diplomeFinEtude.setBilanSession(null);
							_diplomeFinEtude = diplomeFinEtudeDao.merge(_diplomeFinEtude);
						}
						mapper.map(_diplomeFinEtude, _diplomeFinEtudeDto);
						_diplomeFinEtudeDtos.add(_diplomeFinEtudeDto);

						index++;
						System.out.println("Generation Diplome  : "
								+ String.valueOf(index) + "\t\t");
					
				
			}

			_result.put(UtilConstants.TYPE_DIPLOME_CODE, _diplomeFinEtudeDtos);

			return _result;
		} catch (RuntimeException e) {

			log.error("generateStudentsFiles failed", e);
			throw e;
		}
	}

	
	

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public GenerationDiplomeDto saveGenerationDiplome(GenerationDiplomeDto generationDiplomeDto) {

		GenerationDiplome generationDiplome = mapper.map(generationDiplomeDto, GenerationDiplome.class);

		if (generationDiplome.getId() == 0) {
			generationDiplomeDao.flushAndClear();
			generationDiplomeDao.persist(generationDiplome);

		} else
			generationDiplome = generationDiplomeDao.merge(generationDiplome);

		mapper.map(generationDiplome, generationDiplomeDto);

		return generationDiplomeDto;
	}

	@Override
	public List<GenerationDiplomeDto> getAllGenerations() {

		try {
			List<GenerationDiplome> generationDiplomes = generationDiplomeDao.findAll();
			List<GenerationDiplomeDto> generationDiplomeDtos = new ArrayList<GenerationDiplomeDto>();
			for (GenerationDiplome generationDiplome : generationDiplomes) {

				GenerationDiplomeDto generationDiplomeDto = new GenerationDiplomeDto();
				mapper.map(generationDiplome, generationDiplomeDto);
				generationDiplomeDtos.add(generationDiplomeDto);
			}
			return generationDiplomeDtos;
		} catch (RuntimeException e) {

			log.error("getAllGenerationDiplomes failed", e);
			throw e;
		}
	}

	@Override
	public List<GenerationDiplomeDto> findGenerationsByEtab(String codeEtab) {

		try {
			List<GenerationDiplome> generationDiplomes = generationDiplomeDao.findByEtab(codeEtab);
			List<GenerationDiplomeDto> generationDiplomeDtos = new ArrayList<GenerationDiplomeDto>();
			for (GenerationDiplome generationDiplome : generationDiplomes) {

				GenerationDiplomeDto generationDiplomeDto = new GenerationDiplomeDto();
				mapper.map(generationDiplome, generationDiplomeDto);
				generationDiplomeDtos.add(generationDiplomeDto);
			}
			return generationDiplomeDtos;
		} catch (RuntimeException e) {

			log.error("getAllGenerationDiplomes failed", e);
			throw e;
		}
	}

}
