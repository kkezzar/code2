/**
 * [dz.gov.mesrs.sii.fve.business.service.impl.bac.BacServiceImpl.java] 
 * @author  Rafik LAIB on : 21 mai 2014  15:21:01
 */
package dz.gov.mesrs.sii.fve.business.service.impl.bac;

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
import dz.gov.mesrs.sii.commons.data.dao.fve.bac.AffectationBachelierDao;
import dz.gov.mesrs.sii.commons.data.dao.fve.bac.CorrespondanceDomaineDao;
import dz.gov.mesrs.sii.commons.data.dao.fve.bac.DossierBachelierDao;
import dz.gov.mesrs.sii.commons.data.dao.fve.bac.GenerationDao;
import dz.gov.mesrs.sii.commons.data.dao.fve.bac.ImportationDao;
import dz.gov.mesrs.sii.commons.data.dao.fve.bac.NoteBachelierDao;
import dz.gov.mesrs.sii.commons.data.dao.fve.bac.TypeImportationDao;
import dz.gov.mesrs.sii.commons.data.dao.fve.cursus.DossierDao;
import dz.gov.mesrs.sii.commons.data.dao.fve.cursus.DossierEtudiantDao;
import dz.gov.mesrs.sii.commons.data.dao.fve.cursus.DossierInscriptionAdministrativeDao;
import dz.gov.mesrs.sii.commons.data.dao.fve.cursus.PieceConstitutiveDao;
import dz.gov.mesrs.sii.commons.data.model.fve.bac.AffectationBachelier;
import dz.gov.mesrs.sii.commons.data.model.fve.bac.CorrespondanceDomaine;
import dz.gov.mesrs.sii.commons.data.model.fve.bac.DossierBachelier;
import dz.gov.mesrs.sii.commons.data.model.fve.bac.Generation;
import dz.gov.mesrs.sii.commons.data.model.fve.bac.Importation;
import dz.gov.mesrs.sii.commons.data.model.fve.bac.NotesBachelier;
import dz.gov.mesrs.sii.commons.data.model.fve.bac.TypeImportation;
import dz.gov.mesrs.sii.fve.business.model.dto.bac.AffectationBachelierDto;
import dz.gov.mesrs.sii.fve.business.model.dto.bac.DossierBachelierDto;
import dz.gov.mesrs.sii.fve.business.model.dto.bac.GenerationDto;
import dz.gov.mesrs.sii.fve.business.model.dto.bac.ImportationDto;
import dz.gov.mesrs.sii.fve.business.model.dto.bac.NotesBachelierDto;
import dz.gov.mesrs.sii.fve.business.model.dto.bac.TypeImportationDto;
import dz.gov.mesrs.sii.fve.business.service.bac.BacService;
import dz.gov.mesrs.sii.fve.business.util.UtilConstants;

/**
 * @author Rafik LAIB on : 21 mai 2014 15:21:01
 */
@Service("bacService")
public class BacServiceImpl implements BacService {

	private static final Log log = LogFactory.getLog(BacServiceImpl.class);

	@Autowired
	@Qualifier("typeImportationDao")
	private TypeImportationDao typeImportationDao;

	@Autowired
	@Qualifier("importationDao")
	private ImportationDao importationDao;

	@Autowired
	@Qualifier("dossierBachelierDao")
	private DossierBachelierDao dossierBachelierDao;

	@Autowired
	@Qualifier("affectationBachelierDao")
	private AffectationBachelierDao affectationBachelierDao;

	@Autowired
	@Qualifier("dossierEtudiantDao")
	private DossierEtudiantDao dossierEtudiantDao;

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
	@Qualifier("correspondanceDomaineDao")
	private CorrespondanceDomaineDao correspondanceDomaineDao;

	@Autowired
	@Qualifier("generationDao")
	private GenerationDao generationDao;

	@Autowired
	@Qualifier("pieceConstitutiveDao")
	private PieceConstitutiveDao pieceConstitutiveDao;

	@Autowired
	@Qualifier("noteBachelierDao")
	private NoteBachelierDao noteBachelierDao;

	@Autowired
	@Qualifier("mapper")
	private DozerBeanMapper mapper;

	/**
	 * [BacServiceImpl.saveOneBachelorFile] Method overridden By : @author Rafik
	 * LAIB On : 21 mai 2014 15:21:01
	 * 
	 * @param dossierBachelierDto
	 * @return
	 */
	@Override
	public DossierBachelierDto saveOneBachelorFile(
			DossierBachelierDto dossierBachelierDto) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * [BacServiceImpl.saveListBachelorsFiles] Method overridden By : @author
	 * Rafik LAIB On : 21 mai 2014 15:21:01
	 * 
	 * @param dossierBachelierDtos
	 * @return
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public ImportationDto saveListBachelorsFiles(ImportationDto importationDto,
			List<DossierBachelierDto> dossierBachelierDtos,
			Boolean replaceLastImport) {

		try {
			// Annulation des importation existantes
			// Mise a jour de la situation
			if (replaceLastImport) {
				List<Importation> importsToCancel = importationDao
						.findByYear(importationDto.getAnneeBac());
				for (Importation importation : importsToCancel) {
					importation
							.setSituation(UtilConstants.BAC_IMPORT_STATUS_CANCELED_CODE);
					importationDao.merge(importation);
				}
			}
			Importation importation = mapper.map(importationDto,
					Importation.class);
			if (importation.getId() > 0) {
				long index = 0;
				int nbDepart = dossierBachelierDtos.size();
				int nbImportes = 0;
				int nbIgnores = 0;
				dossierBachelierDao.flushAndClear();
				for (DossierBachelierDto dossierBachelierDto : dossierBachelierDtos) {
					if (dossierBachelierDto != null) {
						DossierBachelier dossierBachelier = new DossierBachelier();
						dossierBachelier = mapper.map(dossierBachelierDto,
								DossierBachelier.class);
						dossierBachelier.setImportation(importation);
						dossierBachelier.setId(Integer
								.parseInt(dossierBachelier.getMatricule()
										+ importation.getId()));
						dossierBachelier.setImportationAffectation(null);
						try {

							dossierBachelierDao.persist(dossierBachelier);
							// importedRows.add(affectationBachelierDto);
							index++;
							nbImportes++;
							System.out.println("Dossier Bachelier N� : "
									+ String.valueOf(index) + "\t\t");
						} catch (Exception ex) {
							nbIgnores++;
						}

					}
				}
				importation.setNbDepart(nbDepart);
				importation.setNbImportes(nbImportes);
				importation.setNbIgnores(nbIgnores);
			}
			mapper.map(importation, importationDto);
			return importationDto;
		} catch (RuntimeException e) {
			log.error("saveListBachelorsFiles failed", e);
			throw e;
		}

	}

	/**
	 * [BacServiceImpl.getImportById] Method overridden By : @author Rafik LAIB
	 * On : 3 juin 2014 14:15:54
	 * 
	 * @param idImport
	 * @return
	 */
	@Override
	public ImportationDto getImportById(int idImport) {

		try {
			Importation importation = importationDao.findById(idImport);
			ImportationDto importationDto = new ImportationDto();
			mapper.map(importation, importationDto);
			return importationDto;

		} catch (RuntimeException e) {

			log.error("getImportById failed", e);
			throw e;
		}
	}

	/**
	 * [BacServiceImpl.findBachelorFileById] Method overridden By : @author
	 * Rafik LAIB On : 21 mai 2014 15:21:01
	 * 
	 * @param idBachelorFile
	 * @return
	 */
	@Override
	public DossierBachelierDto findBachelorFileById(int idBachelorFile) {

		try {
			DossierBachelier dossierBachelier = dossierBachelierDao
					.findById(idBachelorFile);
			DossierBachelierDto dossierBachelierDto = new DossierBachelierDto();
			mapper.map(dossierBachelier, dossierBachelierDto);
			if (dossierBachelierDto != null) {
				Map<String, CorrespondanceDomaine> _correspondanceList = getCorrespondanceDomaines();
				// recuperation du nouveau code de la filiere affectation et du
				// libelle filiere affectation
				CorrespondanceDomaine _cd;
				String _currentCode = dossierBachelierDto
						.getRefCodeFiliereAffectation();
				if (_currentCode != null) {
					_cd = _correspondanceList.get(_currentCode);

					if (_cd != null) {
						dossierBachelierDto.setLibelleFiliereAffectation(_cd
								.toString());
						dossierBachelierDto.setCodeFiliereInscription(_cd
								.getNouveauCode());
						dossierBachelierDto.setEstClassique(_cd
								.getEstClassique());
					}
				}

			}
			return dossierBachelierDto;

		} catch (RuntimeException e) {

			log.error("findBachelorFileById failed", e);
			throw e;
		}
	}

	/**
	 * [BacServiceImpl.findBachelorFileById] Method overridden By : @author
	 * Rafik LAIB On : 25 mai 2014 17:25:03
	 * 
	 * @param idBachelorFile
	 * @return
	 */
	@Override
	public List<TypeImportationDto> getAllTypesImport() {

		try {
			List<TypeImportation> typesImportation = typeImportationDao
					.findAll();
			List<TypeImportationDto> typeImportationDtos = new ArrayList<TypeImportationDto>();
			for (TypeImportation typeImportation : typesImportation) {

				TypeImportationDto typeImportationDto = new TypeImportationDto();
				mapper.map(typeImportation, typeImportationDto);
				typeImportationDtos.add(typeImportationDto);
			}
			return typeImportationDtos;
		} catch (RuntimeException e) {

			log.error("getAllTypesImport failed", e);
			throw e;
		}
	}

	/**
	 * [BacServiceImpl.getAllImports] Method
	 * 
	 * @author rlaib on : 23 juin 2014 08:21:38
	 * @return
	 */
	@Override
	public List<ImportationDto> getAllImports() {

		try {
			List<Importation> importations = importationDao.findAll();
			List<ImportationDto> importationDtos = new ArrayList<ImportationDto>();
			for (Importation importation : importations) {

				ImportationDto importationDto = new ImportationDto();
				mapper.map(importation, importationDto);
				importationDtos.add(importationDto);
			}
			return importationDtos;
		} catch (RuntimeException e) {

			log.error("getAllImports failed", e);
			throw e;
		}
	}

	/**
	 * [BacServiceImpl.getImportTypeByCode] Method overridden By : @author Rafik
	 * LAIB On : 1 juin 2014 17:24:18
	 * 
	 * @param code
	 * @return
	 */
	@Override
	public TypeImportationDto getImportTypeByCode(String code) {

		try {
			TypeImportation typeImportation = typeImportationDao
					.findByCode(code);
			TypeImportationDto typeImportationDto = new TypeImportationDto();
			mapper.map(typeImportation, typeImportationDto);
			return typeImportationDto;

		} catch (RuntimeException e) {

			log.error("getImportTypeByCode failed", e);
			throw e;
		}
	}

	/**
	 * [BacServiceImpl.findAdvanced] Method overridden By : @author Rafik LAIB
	 * On : 1 juin 2014 17:24:12
	 * 
	 * @param searchDto
	 * @param keyWord
	 * @param withStudentFile
	 * @return
	 */
	@Override
	public List<DossierBachelierDto> findAdvanced(
			DossierBachelierDto searchDto, String keyWord,
			boolean withStudentFile,boolean hasAffectationCorrespondanceCode,  String year
			, Integer start, Integer pageSize) {

		try {
			Map<String, String> attributes = new HashMap<String, String>();
			if ((keyWord != null) && (!keyWord.trim().isEmpty())) {

				attributes.put("matricule", keyWord);
				attributes.put("nomAr", keyWord);
				attributes.put("prenomAr", keyWord);
				attributes.put("nomFr", keyWord);
				attributes.put("prenomFr", keyWord);
				attributes.put("prenomPere", keyWord);
				attributes.put("nomPrenomMere", keyWord);
				attributes.put("adresseResidence", keyWord);
				attributes.put("libelleVilleNaissance", keyWord);
				attributes.put("libelleSerieBac", keyWord);
			}

			Map<String, String> attributesDto = new HashMap<String, String>();
			Map<String, String> attributesDtoAffectation = new HashMap<String, String>();
			if (searchDto != null) {

				// Maps Attributes Dto pour Dossier Bachelier
				if (searchDto.getNomAr() != null
						&& !searchDto.getNomAr().trim().equals(""))
					attributesDto.put("nomAr", searchDto.getNomAr());

				if (searchDto.getPrenomAr() != null
						&& !searchDto.getPrenomAr().trim().equals(""))
					attributesDto.put("prenomAr", searchDto.getPrenomAr());

				if (searchDto.getNomFr() != null
						&& !searchDto.getNomFr().trim().equals(""))
					attributesDto.put("nomFr", searchDto.getNomFr());

				if (searchDto.getPrenomFr() != null
						&& !searchDto.getPrenomFr().trim().equals(""))
					attributesDto.put("prenomFr", searchDto.getPrenomFr());

				if (searchDto.getMatricule() != null
						&& !searchDto.getMatricule().trim().equals(""))
					attributesDto.put("matricule", searchDto.getMatricule());

				if (searchDto.getRefCodeSerieBac() != null
						&& !searchDto.getRefCodeSerieBac().trim().equals(""))
					attributesDto.put("refCodeSerieBac",
							searchDto.getRefCodeSerieBac());

				if (searchDto.getRefCodeSexe() != null
						&& !searchDto.getRefCodeSexe().trim().equals(""))
					attributesDto
							.put("refCodeSexe", searchDto.getRefCodeSexe());

				if (searchDto.getRefCodeWilayaBac() != null
						&& !searchDto.getRefCodeWilayaBac().trim().equals(""))
					attributesDto.put("refCodeWilayaBac",
							searchDto.getRefCodeWilayaBac());

				if (searchDto.getRefCodeWilayaNaissance() != null
						&& !searchDto.getRefCodeWilayaNaissance().trim()
								.equals(""))
					attributesDto.put("refCodeWilayaNaissance",
							searchDto.getRefCodeWilayaNaissance());

				if (searchDto.getRefCodeWilayaResidence() != null
						&& !searchDto.getRefCodeWilayaResidence().trim()
								.equals(""))
					attributesDto.put("refCodeWilayaResidence",
							searchDto.getRefCodeWilayaResidence());

				// Maps Attributes Dto pour Affectation Bachelier
				if (searchDto.getRefCodeFiliereAffectation() != null
						&& !searchDto.getRefCodeFiliereAffectation().trim()
								.equals(""))
					attributesDto.put("refCodeFiliereAffectation",
							searchDto.getRefCodeFiliereAffectation());

				if (searchDto.getRefCodeEtablissementAffectation() != null
						&& !searchDto.getRefCodeEtablissementAffectation()
								.trim().equals(""))
					attributesDto.put("refCodeEtablissementAffectation",
							searchDto.getRefCodeEtablissementAffectation());

			}

			List<DossierBachelier> dossierBacheliers = dossierBachelierDao
					.findAdvanced(attributes, attributesDto,
							attributesDtoAffectation, withStudentFile, hasAffectationCorrespondanceCode, year, start, pageSize);
			List<DossierBachelierDto> dossierBachelierDtos = new ArrayList<DossierBachelierDto>();
			Map<String, CorrespondanceDomaine> _correspondanceList = getCorrespondanceDomaines();
			for (DossierBachelier dossierBachelier : dossierBacheliers) {

				DossierBachelierDto dossierBachelierDto = new DossierBachelierDto();
				dossierBachelierDto = mapper.map(dossierBachelier,
						DossierBachelierDto.class);

				// recuperation du nouveau code de la filiere affectation et du
				// libelle filiere affectation
				CorrespondanceDomaine _cd;
				String _currentCode = dossierBachelierDto
						.getRefCodeFiliereAffectation();
				if (_currentCode != null) {
					_cd = _correspondanceList.get(_currentCode);

					if (_cd != null) {
						dossierBachelierDto.setLibelleFiliereAffectation(_cd
								.toString());
						dossierBachelierDto.setCodeFiliereInscription(_cd
								.getNouveauCode());
						dossierBachelierDto.setEstClassique(_cd
								.getEstClassique());
					}
				}
				dossierBachelierDtos.add(dossierBachelierDto);
			}
			return dossierBachelierDtos;
		}

		catch (RuntimeException re) {

			log.error("findAdvanced 'List<DossierBachelierDto>' failed", re);
			throw re;
		}
	}

	@Override
	public List<DossierBachelierDto> findAdvancedWithPaging(
			DossierBachelierDto searchDto, String keyWord,
			Boolean withStudentFile, String year, int start, int page) {

		try {
			Map<String, String> attributes = new HashMap<String, String>();
			if ((keyWord != null) && (!keyWord.trim().isEmpty())) {

				attributes.put("matricule", keyWord);
				attributes.put("nomAr", keyWord);
				attributes.put("prenomAr", keyWord);
				attributes.put("nomFr", keyWord);
				attributes.put("prenomFr", keyWord);
				attributes.put("prenomPere", keyWord);
				attributes.put("nomPrenomMere", keyWord);
				attributes.put("adresseResidence", keyWord);
				attributes.put("libelleVilleNaissance", keyWord);
				attributes.put("libelleSerieBac", keyWord);
			}

			Map<String, String> attributesDto = new HashMap<String, String>();
			Map<String, String> attributesDtoAffectation = new HashMap<String, String>();
			if (searchDto != null) {

				// Maps Attributes Dto pour Dossier Bachelier
				if (searchDto.getNomAr() != null
						&& !searchDto.getNomAr().trim().equals(""))
					attributesDto.put("nomAr", searchDto.getNomAr());

				if (searchDto.getPrenomAr() != null
						&& !searchDto.getPrenomAr().trim().equals(""))
					attributesDto.put("prenomAr", searchDto.getPrenomAr());

				if (searchDto.getNomFr() != null
						&& !searchDto.getNomFr().trim().equals(""))
					attributesDto.put("nomFr", searchDto.getNomFr());

				if (searchDto.getPrenomFr() != null
						&& !searchDto.getPrenomFr().trim().equals(""))
					attributesDto.put("prenomFr", searchDto.getPrenomFr());

				if (searchDto.getMatricule() != null
						&& !searchDto.getMatricule().trim().equals(""))
					attributesDto.put("matricule", searchDto.getMatricule());

				if (searchDto.getRefCodeSerieBac() != null
						&& !searchDto.getRefCodeSerieBac().trim().equals(""))
					attributesDto.put("refCodeSerieBac",
							searchDto.getRefCodeSerieBac());

				if (searchDto.getRefCodeSexe() != null
						&& !searchDto.getRefCodeSexe().trim().equals(""))
					attributesDto
							.put("refCodeSexe", searchDto.getRefCodeSexe());

				if (searchDto.getRefCodeWilayaBac() != null
						&& !searchDto.getRefCodeWilayaBac().trim().equals(""))
					attributesDto.put("refCodeWilayaBac",
							searchDto.getRefCodeWilayaBac());

				if (searchDto.getRefCodeWilayaNaissance() != null
						&& !searchDto.getRefCodeWilayaNaissance().trim()
								.equals(""))
					attributesDto.put("refCodeWilayaNaissance",
							searchDto.getRefCodeWilayaNaissance());

				if (searchDto.getRefCodeWilayaResidence() != null
						&& !searchDto.getRefCodeWilayaResidence().trim()
								.equals(""))
					attributesDto.put("refCodeWilayaResidence",
							searchDto.getRefCodeWilayaResidence());

				// Maps Attributes Dto pour Affectation Bachelier
				if (searchDto.getRefCodeFiliereAffectation() != null
						&& !searchDto.getRefCodeFiliereAffectation().trim()
								.equals(""))
					attributesDto.put("refCodeFiliereAffectation",
							searchDto.getRefCodeFiliereAffectation());

				if (searchDto.getRefCodeEtablissementAffectation() != null
						&& !searchDto.getRefCodeEtablissementAffectation()
								.trim().equals(""))
					attributesDto.put("refCodeEtablissementAffectation",
							searchDto.getRefCodeEtablissementAffectation());

			}

			List<DossierBachelier> dossierBacheliers = dossierBachelierDao
					.findAdvancedWithPaging(attributes, attributesDto,
							attributesDtoAffectation, withStudentFile, year,
							start, page);
			List<DossierBachelierDto> dossierBachelierDtos = new ArrayList<DossierBachelierDto>();
			Map<String, CorrespondanceDomaine> _correspondanceList = getCorrespondanceDomaines();
			for (DossierBachelier dossierBachelier : dossierBacheliers) {

				DossierBachelierDto dossierBachelierDto = new DossierBachelierDto();
				dossierBachelierDto = mapper.map(dossierBachelier,
						DossierBachelierDto.class);
				switch (dossierBachelier.getRefCodeSexe()) {

				case UtilConstants.BAC_BACHELOR_MALE_CODE:
					dossierBachelierDto
							.setPhotoEtudiant(UtilConstants.BAC_BACHELOR_PHOTO_MALE_NAME);
					break;
				case UtilConstants.BAC_BACHELOR_FEMALE_CODE:
					dossierBachelierDto
							.setPhotoEtudiant(UtilConstants.BAC_BACHELOR_PHOTO_FEMALE_NAME);
					break;

				default:
					dossierBachelierDto
							.setPhotoEtudiant(UtilConstants.BAC_BACHELOR_PHOTO_DEFAULT_NAME);
					break;
				}
				// recuperation du nouveau code de la filiere affectation et du
				// libelle filiere affectation
				CorrespondanceDomaine _cd;
				String _currentCode = dossierBachelierDto
						.getRefCodeFiliereAffectation();
				if (_currentCode != null) {
					_cd = _correspondanceList.get(_currentCode);

					if (_cd != null) {
						dossierBachelierDto.setLibelleFiliereAffectation(_cd
								.toString());
						dossierBachelierDto.setCodeFiliereInscription(_cd
								.getNouveauCode());
						dossierBachelierDto.setEstClassique(_cd
								.getEstClassique());
					}
				}
				dossierBachelierDtos.add(dossierBachelierDto);
			}
			return dossierBachelierDtos;
		}

		catch (RuntimeException re) {

			log.error("findAdvanced 'List<DossierBachelierDto>' failed", re);
			throw re;
		}
	}

	@Override
	public Integer getFindAdvancedCount(
			DossierBachelierDto searchDto, String keyWord,
			boolean withStudentFile,boolean hasAffectationCorrespondanceCode,  String year) {

		try {
			Map<String, String> attributes = new HashMap<String, String>();
			if ((keyWord != null) && (!keyWord.trim().isEmpty())) {

				attributes.put("matricule", keyWord);
				attributes.put("nomAr", keyWord);
				attributes.put("prenomAr", keyWord);
				attributes.put("nomFr", keyWord);
				attributes.put("prenomFr", keyWord);
				attributes.put("prenomPere", keyWord);
				attributes.put("nomPrenomMere", keyWord);
				attributes.put("adresseResidence", keyWord);
				attributes.put("libelleVilleNaissance", keyWord);
				attributes.put("libelleSerieBac", keyWord);
			}

			Map<String, String> attributesDto = new HashMap<String, String>();
			Map<String, String> attributesDtoAffectation = new HashMap<String, String>();
			if (searchDto != null) {

				// Maps Attributes Dto pour Dossier Bachelier
				if (searchDto.getNomAr() != null
						&& !searchDto.getNomAr().trim().equals(""))
					attributesDto.put("nomAr", searchDto.getNomAr());

				if (searchDto.getPrenomAr() != null
						&& !searchDto.getPrenomAr().trim().equals(""))
					attributesDto.put("prenomAr", searchDto.getPrenomAr());

				if (searchDto.getNomFr() != null
						&& !searchDto.getNomFr().trim().equals(""))
					attributesDto.put("nomFr", searchDto.getNomFr());

				if (searchDto.getPrenomFr() != null
						&& !searchDto.getPrenomFr().trim().equals(""))
					attributesDto.put("prenomFr", searchDto.getPrenomFr());

				if (searchDto.getMatricule() != null
						&& !searchDto.getMatricule().trim().equals(""))
					attributesDto.put("matricule", searchDto.getMatricule());

				if (searchDto.getRefCodeSerieBac() != null
						&& !searchDto.getRefCodeSerieBac().trim().equals(""))
					attributesDto.put("refCodeSerieBac",
							searchDto.getRefCodeSerieBac());

				if (searchDto.getRefCodeSexe() != null
						&& !searchDto.getRefCodeSexe().trim().equals(""))
					attributesDto
							.put("refCodeSexe", searchDto.getRefCodeSexe());

				if (searchDto.getRefCodeWilayaBac() != null
						&& !searchDto.getRefCodeWilayaBac().trim().equals(""))
					attributesDto.put("refCodeWilayaBac",
							searchDto.getRefCodeWilayaBac());

				if (searchDto.getRefCodeWilayaNaissance() != null
						&& !searchDto.getRefCodeWilayaNaissance().trim()
								.equals(""))
					attributesDto.put("refCodeWilayaNaissance",
							searchDto.getRefCodeWilayaNaissance());

				if (searchDto.getRefCodeWilayaResidence() != null
						&& !searchDto.getRefCodeWilayaResidence().trim()
								.equals(""))
					attributesDto.put("refCodeWilayaResidence",
							searchDto.getRefCodeWilayaResidence());

				// Maps Attributes Dto pour Affectation Bachelier
				if (searchDto.getRefCodeFiliereAffectation() != null
						&& !searchDto.getRefCodeFiliereAffectation().trim()
								.equals(""))
					attributesDto.put("refCodeFiliereAffectation",
							searchDto.getRefCodeFiliereAffectation());

				if (searchDto.getRefCodeEtablissementAffectation() != null
						&& !searchDto.getRefCodeEtablissementAffectation()
								.trim().equals(""))
					attributesDto.put("refCodeEtablissementAffectation",
							searchDto.getRefCodeEtablissementAffectation());

			}

			Integer count = dossierBachelierDao.getFindAdvancedCount(attributes, attributesDto,
					attributesDtoAffectation, withStudentFile, hasAffectationCorrespondanceCode, year);
			return count;
		}

		catch (RuntimeException re) {

			log.error("getFindAdvancedCount failed", re);
			throw re;
		}
	}

	@Override
	public List<DossierBachelierDto> findDataToGenerate(
			DossierBachelierDto searchDto, String keyWord,
			Boolean withStudentFile, String year, int start, int page) {

		try {
			Map<String, String> attributes = new HashMap<String, String>();
			if ((keyWord != null) && (!keyWord.trim().isEmpty())) {

				attributes.put("matricule", keyWord);
				attributes.put("nomAr", keyWord);
				attributes.put("prenomAr", keyWord);
				attributes.put("nomFr", keyWord);
				attributes.put("prenomFr", keyWord);
				attributes.put("prenomPere", keyWord);
				attributes.put("nomPrenomMere", keyWord);
				attributes.put("adresseResidence", keyWord);
				attributes.put("libelleVilleNaissance", keyWord);
				attributes.put("libelleSerieBac", keyWord);
			}

			Map<String, String> attributesDto = new HashMap<String, String>();
			Map<String, String> attributesDtoAffectation = new HashMap<String, String>();
			if (searchDto != null) {

				// Maps Attributes Dto pour Dossier Bachelier
				if (searchDto.getNomAr() != null
						&& !searchDto.getNomAr().trim().equals(""))
					attributesDto.put("nomAr", searchDto.getNomAr());

				if (searchDto.getPrenomAr() != null
						&& !searchDto.getPrenomAr().trim().equals(""))
					attributesDto.put("prenomAr", searchDto.getPrenomAr());

				if (searchDto.getNomFr() != null
						&& !searchDto.getNomFr().trim().equals(""))
					attributesDto.put("nomFr", searchDto.getNomFr());

				if (searchDto.getPrenomFr() != null
						&& !searchDto.getPrenomFr().trim().equals(""))
					attributesDto.put("prenomFr", searchDto.getPrenomFr());

				if (searchDto.getMatricule() != null
						&& !searchDto.getMatricule().trim().equals(""))
					attributesDto.put("matricule", searchDto.getMatricule());

				if (searchDto.getRefCodeSerieBac() != null
						&& !searchDto.getRefCodeSerieBac().trim().equals(""))
					attributesDto.put("refCodeSerieBac",
							searchDto.getRefCodeSerieBac());

				if (searchDto.getRefCodeSexe() != null
						&& !searchDto.getRefCodeSexe().trim().equals(""))
					attributesDto
							.put("refCodeSexe", searchDto.getRefCodeSexe());

				if (searchDto.getRefCodeWilayaBac() != null
						&& !searchDto.getRefCodeWilayaBac().trim().equals(""))
					attributesDto.put("refCodeWilayaBac",
							searchDto.getRefCodeWilayaBac());

				if (searchDto.getRefCodeWilayaNaissance() != null
						&& !searchDto.getRefCodeWilayaNaissance().trim()
								.equals(""))
					attributesDto.put("refCodeWilayaNaissance",
							searchDto.getRefCodeWilayaNaissance());

				if (searchDto.getRefCodeWilayaResidence() != null
						&& !searchDto.getRefCodeWilayaResidence().trim()
								.equals(""))
					attributesDto.put("refCodeWilayaResidence",
							searchDto.getRefCodeWilayaResidence());

				// Maps Attributes Dto pour Affectation Bachelier
				if (searchDto.getRefCodeFiliereAffectation() != null
						&& !searchDto.getRefCodeFiliereAffectation().trim()
								.equals(""))
					attributesDto.put("refCodeFiliereAffectation",
							searchDto.getRefCodeFiliereAffectation());

				if (searchDto.getRefCodeEtablissementAffectation() != null
						&& !searchDto.getRefCodeEtablissementAffectation()
								.trim().equals(""))
					attributesDto.put("refCodeEtablissementAffectation",
							searchDto.getRefCodeEtablissementAffectation());

			}

			List<DossierBachelier> dossierBacheliers = dossierBachelierDao
					.findAdvancedWithPaging(attributes, attributesDto,
							attributesDtoAffectation, withStudentFile, year,
							start, page);
			List<DossierBachelierDto> dossierBachelierDtos = new ArrayList<DossierBachelierDto>();
			for (DossierBachelier dossierBachelier : dossierBacheliers) {

				DossierBachelierDto dossierBachelierDto = new DossierBachelierDto();
				dossierBachelierDto = mapper.map(dossierBachelier,
						DossierBachelierDto.class);
				dossierBachelierDtos.add(dossierBachelierDto);
			}
			return dossierBachelierDtos;
		}

		catch (RuntimeException re) {

			log.error("findAdvanced 'List<DossierBachelierDto>' failed", re);
			throw re;
		}
	}

	/**
	 * [BacServiceImpl.getImportByCodeTypeByYear] Method overridden By : @author
	 * Rafik LAIB On : 1 juin 2014 17:23:55
	 * 
	 * @param typeCode
	 * @param year
	 * @return
	 */
	@Override
	public List<ImportationDto> getImportByCodeTypeByYear(String typeCode,
			String year) {

		try {
			List<Importation> importations = importationDao.findByTypeByYear(
					typeCode, year);
			List<ImportationDto> importationDtos = new ArrayList<ImportationDto>();

			for (Importation importation : importations) {

				ImportationDto importationDto = new ImportationDto();
				importationDto = mapper.map(importation, ImportationDto.class);
				importationDtos.add(importationDto);
			}
			return importationDtos;

		} catch (RuntimeException e) {

			log.error("getImportByCodeTypeByYear failed", e);
			throw e;
		}
	}

	/**
	 * [BacServiceImpl.saveListBachelorsAffectatiobs] Method overridden By : @author
	 * Rafik LAIB On : 2 juin 2014 12:08:35
	 * 
	 * @param importationDto
	 * @param dossierBachelierDtos
	 * @return
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public ImportationDto saveListBachelorsAffectations(
			ImportationDto bachelorFilesImportation,
			ImportationDto importationDto,
			List<AffectationBachelierDto> affectationsBachelierDtos,
			Boolean replaceLastImport) {

		try {
			if (bachelorFilesImportation == null)
				return null;

			// Annulation des importation existantes
			// Mise a jour de la situation
			if (replaceLastImport) {
				List<Importation> importsToCancel = importationDao
						.findByTypeByYear(
								importationDto.getCodeTypeImportation(),
								importationDto.getAnneeBac());
				for (Importation importation : importsToCancel) {
					importation
							.setSituation(UtilConstants.BAC_IMPORT_STATUS_CANCELED_CODE);
					importationDao.merge(importation);
				}
			}
			Importation importation = mapper.map(importationDto,
					Importation.class);

			if (importation.getId() > 0) {
				long index = 0;
				int nbDepart = affectationsBachelierDtos.size();
				int nbImportes = 0;
				int nbIgnores = 0;
				dossierBachelierDao.flushAndClear();
				for (AffectationBachelierDto affectationBachelierDto : affectationsBachelierDtos) {

					try {
						DossierBachelier dossierBachelier = dossierBachelierDao
								.findById(Integer
										.parseInt(affectationBachelierDto
												.getMatriculeBachelier()
												+ bachelorFilesImportation
														.getId()));
						dossierBachelier
								.setRefCodeEtablissement(affectationBachelierDto
										.getRefCodeEtablissement());
						dossierBachelier
								.setRefCodeFiliere(affectationBachelierDto
										.getRefCodeFiliere());
						dossierBachelier.setNumeroChoix(affectationBachelierDto
								.getNumeroChoix());
						dossierBachelier
								.setNoteAffectation(affectationBachelierDto
										.getNoteAffectation());
						dossierBachelier
								.setRefCodeEtablissementRecours(affectationBachelierDto
										.getRefCodeEtablissementRecours());
						dossierBachelier
								.setRefCodeFiliereRecours(affectationBachelierDto
										.getRefCodeFiliereRecours());
						dossierBachelier
								.setRefCodeEtablissementOrientation(affectationBachelierDto
										.getRefCodeEtablissementOrientation());
						dossierBachelier
								.setRefCodeFiliereOrientation(affectationBachelierDto
										.getRefCodeFiliereOrientation());
						dossierBachelier
								.setRefCodeTypeAffectation(affectationBachelierDto
										.getRefCodeTypeAffectation());
						dossierBachelier
								.setRefCodeEtablissementAffectation(affectationBachelierDto
										.getRefCodeEtablissementAffectation());
						dossierBachelier
								.setRefCodeFiliereAffectation(affectationBachelierDto
										.getRefCodeFiliereAffectation());
						dossierBachelier.setImportationAffectation(importation);
						dossierBachelierDao.merge(dossierBachelier);
						index++;
						nbImportes++;
						System.out.println("Affectation N � : "
								+ String.valueOf(index) + "\t\t");
					} catch (Exception ex) {
						nbIgnores++;
					}
				}
				importation.setNbDepart(nbDepart);
				importation.setNbImportes(nbImportes);
				importation.setNbIgnores(nbIgnores);
			}
			mapper.map(importation, importationDto);
			return importationDto;

		} catch (RuntimeException e) {
			log.error("saveListBachelorsAffectations failed", e);
			throw e;
		}

	}

	/**
	 * [BacServiceImpl.getListBachelorsFilesByImportId] Method overridden By : @author
	 * Rafik LAIB On : 2 juin 2014 12:08:43
	 * 
	 * @param idImport
	 * @return
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public List<DossierBachelierDto> getListBachelorsFilesByImportId(
			int idImport) {

		try {
			List<DossierBachelier> dossierBacheliers = dossierBachelierDao
					.findByImportId(idImport);
			List<DossierBachelierDto> dossierBachelierDtos = new ArrayList<DossierBachelierDto>();

			for (DossierBachelier dossierBachelier : dossierBacheliers) {

				DossierBachelierDto dossierBachelierDto = new DossierBachelierDto();
				dossierBachelierDto = mapper.map(dossierBachelier,
						DossierBachelierDto.class);
				dossierBachelierDtos.add(dossierBachelierDto);
			}
			return dossierBachelierDtos;
		} catch (RuntimeException re) {
			log.error(
					"getListBachelorsFilesByImportId 'List<DossierBachelierDto>' failed",
					re);
			throw re;
		}
	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public List<AffectationBachelierDto> getListBachelorsAffectationsByImportId(
			int idImport) {

		try {
			List<AffectationBachelier> affectationBacheliers = affectationBachelierDao
					.findByImportId(idImport);
			List<AffectationBachelierDto> affectationBachelierDtos = new ArrayList<AffectationBachelierDto>();
			for (AffectationBachelier affectationBachelier : affectationBacheliers) {

				AffectationBachelierDto affectationBachelierDto = new AffectationBachelierDto();
				affectationBachelierDto = mapper.map(affectationBachelier,
						AffectationBachelierDto.class);
				affectationBachelierDtos.add(affectationBachelierDto);
			}
			return affectationBachelierDtos;
		} catch (RuntimeException re) {
			log.error(
					"getListBachelorsAffectationsByImportId 'List<AffectationBachelierDto>' failed",
					re);
			throw re;
		}
	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public List<DossierBachelierDto> findListBachelorsAndAffectationsByYearAndEtablissement(
			String year, String codeEtablissement) {
		try {
			List<DossierBachelierDto> result = new ArrayList<DossierBachelierDto>();

			List<AffectationBachelier> listAffectations = affectationBachelierDao
					.findByYearAndEtablissement(year, codeEtablissement);
			if (listAffectations != null && !listAffectations.isEmpty()) {
				for (AffectationBachelier affectationBachelier : listAffectations) {
					DossierBachelierDto dossierBachelierDto = new DossierBachelierDto();
					mapper.map(affectationBachelier, dossierBachelierDto);
					DossierBachelier dossierBachelier = dossierBachelierDao
							.findByMatricule(affectationBachelier
									.getMatriculeBachelier());
					if (dossierBachelier != null) {
						mapper.map(dossierBachelier, dossierBachelierDto);
						result.add(dossierBachelierDto);
					}
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
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public List<String> getAllYears() {

		try {
			return importationDao.getAllYears();
		} catch (RuntimeException e) {

			log.error("getAllYears failed", e);
			throw e;
		}
	}

	/**
	 * [BacServiceImpl.getCorrespondanceDomaines] Method
	 * 
	 * @author rlaib on : 15 juil. 2014 10:45:45
	 * @return
	 */
	private Map<String, CorrespondanceDomaine> getCorrespondanceDomaines() {

		Map<String, CorrespondanceDomaine> _result = null;
		try {
			List<CorrespondanceDomaine> _list = correspondanceDomaineDao
					.findAll();
			_result = new HashMap<String, CorrespondanceDomaine>();
			for (CorrespondanceDomaine correspondanceDomaine : _list) {
				if (correspondanceDomaine.getAncienCode() != null)
					_result.put(correspondanceDomaine.getAncienCode(),
							correspondanceDomaine);
			}
			return _result;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@Override
	public List<AffectationBachelierDto> findAffectationsByIdImportByEtablissement(
			int idImport, String codeEtablissement) {

		try {
			List<AffectationBachelier> affectationBacheliers = affectationBachelierDao
					.findByIdImportByEtablissement(idImport, codeEtablissement);
			List<AffectationBachelierDto> affectationBachelierDtos = new ArrayList<AffectationBachelierDto>();
			for (AffectationBachelier affectationBachelier : affectationBacheliers) {

				AffectationBachelierDto affectationBachelierDto = new AffectationBachelierDto();
				affectationBachelierDto = mapper.map(affectationBachelier,
						AffectationBachelierDto.class);
				affectationBachelierDtos.add(affectationBachelierDto);
			}
			return affectationBachelierDtos;
		} catch (RuntimeException re) {
			log.error(
					"findAffectationsByIdImportByEtablissement 'List<AffectationBachelierDto>' failed",
					re);
			throw re;
		}
	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public ImportationDto saveImport(ImportationDto importationDto) {

		Importation importation = mapper.map(importationDto, Importation.class);

		if (importation.getId() == 0) {
			importationDao.flushAndClear();
			importationDao.persist(importation);

		} else
			importation = importationDao.merge(importation);

		mapper.map(importation, importationDto);

		return importationDto;

	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public GenerationDto saveGeneration(GenerationDto generationDto) {

		Generation generation = mapper.map(generationDto, Generation.class);

		if (generation.getId() == 0) {
			generationDao.flushAndClear();
			generationDao.persist(generation);

		} else
			generation = generationDao.merge(generation);

		mapper.map(generation, generationDto);

		return generationDto;
	}

	@Override
	public List<GenerationDto> getAllGenerations() {

		try {
			List<Generation> generations = generationDao.findAll();
			List<GenerationDto> generationDtos = new ArrayList<GenerationDto>();
			for (Generation generation : generations) {

				GenerationDto generationDto = new GenerationDto();
				mapper.map(generation, generationDto);
				generationDtos.add(generationDto);
			}
			return generationDtos;
		} catch (RuntimeException e) {

			log.error("getAllGenerations failed", e);
			throw e;
		}
	}

	@Override
	public List<GenerationDto> findGenerationsByEtab(String codeEtab) {

		try {
			List<Generation> generations = generationDao.findByEtab(codeEtab);
			List<GenerationDto> generationDtos = new ArrayList<GenerationDto>();
			for (Generation generation : generations) {

				GenerationDto generationDto = new GenerationDto();
				mapper.map(generation, generationDto);
				generationDtos.add(generationDto);
			}
			return generationDtos;
		} catch (RuntimeException e) {

			log.error("getAllGenerations failed", e);
			throw e;
		}
	}

	@Override
	public List<NotesBachelierDto> findByIdDossierBachelier(
			String matriculeBachelier) {
		try {
			List<NotesBachelier> notesBachelierBos = noteBachelierDao
					.findByIdDossierBachelier(matriculeBachelier);
			List<NotesBachelierDto> notesBachelierDtos = new ArrayList<NotesBachelierDto>();
			for (NotesBachelier noteBachelier : notesBachelierBos) {

				NotesBachelierDto noteBachelierDto = new NotesBachelierDto();
				mapper.map(noteBachelier, noteBachelierDto);
				notesBachelierDtos.add(noteBachelierDto);
			}
			return notesBachelierDtos;
		} catch (RuntimeException e) {
			log.error("getAllGenerations failed", e);
			throw e;
		}
	}

	@Override
	public List<NotesBachelierDto> findByIdDossierBachelierRefCodeMatiere(
			String matriculeBachelier, String refCodeMatiere) {
		try {
			List<NotesBachelier> notesBachelierBos = noteBachelierDao
					.findByIdDossierBachelierRefCodeMatiere(matriculeBachelier,
							refCodeMatiere);
			List<NotesBachelierDto> notesBachelierDtos = new ArrayList<NotesBachelierDto>();
			for (NotesBachelier noteBachelier : notesBachelierBos) {

				NotesBachelierDto noteBachelierDto = new NotesBachelierDto();
				mapper.map(noteBachelier, noteBachelierDto);
				notesBachelierDtos.add(noteBachelierDto);
			}
			return notesBachelierDtos;
		} catch (RuntimeException e) {
			log.error("getAllGenerations failed", e);
			throw e;
		}
	}

	/**
	 * [BacService.generateBach] Method 
	 * @author rlaib  on : 8 nov. 2014  11:37:25
	 * @param idAnneeAcademique
	 * @param codeEtablissement
	 * @return
	 */
	@Override
	public Map<String, Object> generateBach(String anneeAcademique
			, String ancienCodeEtablissement
    		, String nouveauCodeEtablissement, Integer idEtab) {
		return dossierBachelierDao.generateStudentsFiles(anneeAcademique, ancienCodeEtablissement, nouveauCodeEtablissement,idEtab);
		 
	}
}
