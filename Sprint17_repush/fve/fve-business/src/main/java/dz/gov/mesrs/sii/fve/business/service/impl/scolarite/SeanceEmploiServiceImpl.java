package dz.gov.mesrs.sii.fve.business.service.impl.scolarite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.fve.cursus.AssociationGroupePedagogiqueDao;
import dz.gov.mesrs.sii.commons.data.dao.fve.scolarite.AffectationLieuStructureDao;
import dz.gov.mesrs.sii.commons.data.dao.fve.scolarite.CelluleHoraireDao;
import dz.gov.mesrs.sii.commons.data.dao.fve.scolarite.EnseignantChargePedagogiqueDao;
import dz.gov.mesrs.sii.commons.data.dao.fve.scolarite.PlageHoraireDao;
import dz.gov.mesrs.sii.commons.data.dao.fve.scolarite.SeanceEmploiDao;
import dz.gov.mesrs.sii.commons.data.dao.referentiel.JourDao;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.AssociationGroupePedagogique;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.AnneeAcademique;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.OuvertureOffreFormation;
import dz.gov.mesrs.sii.commons.data.model.fve.scolarite.AffectationLieuStructure;
import dz.gov.mesrs.sii.commons.data.model.fve.scolarite.CelluleHoraire;
import dz.gov.mesrs.sii.commons.data.model.fve.scolarite.PlageHoraire;
import dz.gov.mesrs.sii.commons.data.model.fve.scolarite.SeanceEmploi;
import dz.gov.mesrs.sii.commons.data.model.grh.DossierEmploye;
import dz.gov.mesrs.sii.commons.data.model.referentiel.Jour;
import dz.gov.mesrs.sii.commons.data.model.referentiel.Nomenclature;
import dz.gov.mesrs.sii.fve.business.model.dto.scolarite.EmploiDto;
import dz.gov.mesrs.sii.fve.business.model.dto.scolarite.EmploiModelDto;
import dz.gov.mesrs.sii.fve.business.model.dto.scolarite.PlageHoraireDto;
import dz.gov.mesrs.sii.fve.business.model.dto.scolarite.SeanceEmploiByPlageHoraireDto;
import dz.gov.mesrs.sii.fve.business.model.dto.scolarite.SeanceEmploiDto;
import dz.gov.mesrs.sii.fve.business.service.scolarite.SeanceEmploiService;
import dz.gov.mesrs.sii.fve.business.util.UtilConstants;

/**
 * 
 * @author BELDI Jamel on : 22 oct. 2014 12:32:14
 */

@Service("seanceEmploiService")
public class SeanceEmploiServiceImpl implements SeanceEmploiService {

	@Autowired
	@Qualifier("seanceEmploiDao")
	private SeanceEmploiDao seanceEmploiDao;
	@Autowired
	@Qualifier("plageHoraireDao")
	private PlageHoraireDao plageHoraireDao;

	@Autowired
	@Qualifier("jourDao")
	private JourDao jourDao;
	private static final Log log = LogFactory
			.getLog(SeanceEmploiServiceImpl.class);
	@Autowired
	@Qualifier("celluleHoraireDao")
	private CelluleHoraireDao celluleHoraireDao;
	@Autowired
	@Qualifier("associationGroupePedagogiqueDao")
	private AssociationGroupePedagogiqueDao associationGroupePedagogiqueDao;
	@Autowired
	@Qualifier("enseignantChargePedagogiqueDao")
	private EnseignantChargePedagogiqueDao enseignantChargePedagogiqueDao;
	@Autowired
	@Qualifier("affectationLieuStructureDao")
	private AffectationLieuStructureDao affectationLieuStructureDao;

	@Autowired
	@Qualifier("mapper")
	private DozerBeanMapper mapper;

	public SeanceEmploiServiceImpl() {

	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public SeanceEmploiDto insertOrUpdate(SeanceEmploiDto seanceEmploiDto) {
		try {
			SeanceEmploi seanceEmploi = mapper.map(seanceEmploiDto,
					SeanceEmploi.class);

			if (seanceEmploi.getId() == 0) {
				seanceEmploiDao.persist(seanceEmploi);
			} else {
				seanceEmploi = seanceEmploiDao.merge(seanceEmploi);
			}
			mapper.map(seanceEmploi, seanceEmploiDto);

			log.error("insertorupdate SeanceEmploi succes..");

			return seanceEmploiDto;

		} catch (RuntimeException e) {
			log.error("insertorupdate SeanceEmploi failed--", e);
			throw e;
		}
	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void remove(SeanceEmploiDto seanceEmploiDto) {
		try {

			SeanceEmploi seanceEmploi = mapper.map(seanceEmploiDto,
					SeanceEmploi.class);

			seanceEmploi = seanceEmploiDao.merge(seanceEmploi);

			seanceEmploiDao.remove(seanceEmploi);
		} catch (RuntimeException e) {
			throw e;
		}
	}

	@Override
	public SeanceEmploiDto findById(int id) {
		try {
			SeanceEmploi seanceEmploi = seanceEmploiDao.findById(id);

			if (seanceEmploi != null)
				return mapper.map(seanceEmploi, SeanceEmploiDto.class);

			return null;
		} catch (RuntimeException e) {
			throw e;
		}
	}

	@Override
	public List<SeanceEmploiDto> findAll() {
		try {
			List<SeanceEmploi> seanceEmplois = seanceEmploiDao.findAll();

			List<SeanceEmploiDto> seanceEmploiDtos = new ArrayList<SeanceEmploiDto>();

			for (SeanceEmploi seanceEmploi : seanceEmplois) {
				seanceEmploiDtos.add(mapper.map(seanceEmploi,
						SeanceEmploiDto.class));
			}

			return seanceEmploiDtos;
		} catch (RuntimeException e) {
			throw e;
		}

	}

	// @Override
	// public List<SeanceEmploiByPlageHoraireDto> findByEmploiId(int idEmploi) {
	// try {
	// /**test**/
	// // List<SeanceEmploiDto> seances = new ArrayList<SeanceEmploiDto>();
	// // SeanceEmploiDto a = new SeanceEmploiDto();
	// // a.setGroupePedagogiqueNom("1CPA-G01");
	// // a.setRefCodeTypeAp("TD");
	// // a.setRattachementMcMcCode("SYS1");
	// // a.setRefLieuDesignation("M1");
	// // SeanceEmploiDto b = new SeanceEmploiDto();
	// // b.setGroupePedagogiqueNom("GROUPE B");
	// // seances.add(a);
	// // seances.add(b);
	//
	// List<SeanceEmploiByPlageHoraireDto> seanceEmploiByPlageHoraireDtos = new
	// ArrayList<SeanceEmploiByPlageHoraireDto>();
	// Map<String, List<SeanceEmploiDto>> test = new HashMap<String,
	// List<SeanceEmploiDto>>();
	// // test.put("DIM", seances);
	// // Find Jours
	// List<Jour> jours = jourDao.findAll();
	// if (jours == null || jours.isEmpty()) {
	// return seanceEmploiByPlageHoraireDtos;
	// }
	//
	// // Find Plage HoraireDtos
	// List<PlageHoraire> plageHoraires = plageHoraireDao.findAll();
	// if (plageHoraires == null || plageHoraires.isEmpty()) {
	// return seanceEmploiByPlageHoraireDtos;
	// }
	// // initSeanceEmploiByPlageHoraireDtoList
	// for (PlageHoraire plageHoraire : plageHoraires) {
	// PlageHoraireDto plageHoraireDto = new PlageHoraireDto();
	// mapper.map(plageHoraire, plageHoraireDto);
	// Map<String, SeanceEmploiDto> seanceEmploiDtos = new HashMap<String,
	// SeanceEmploiDto>();
	//
	// for (Jour jour : jours) {
	// CelluleHoraire celluleHoraire = celluleHoraireDao
	// .findByJourAndPlageHoraire(jour.getId(),
	// plageHoraire.getId());
	// SeanceEmploiDto seanceEmploiDto = new SeanceEmploiDto();
	// if (celluleHoraire != null) {
	// seanceEmploiDto = findByEmploiIdAndCelluleId(idEmploi,
	// celluleHoraire.getId());
	// if (seanceEmploiDto == null) {
	// seanceEmploiDto = new SeanceEmploiDto();
	// seanceEmploiDto.setEmploiId(idEmploi);
	// mapper.map(celluleHoraire, seanceEmploiDto);
	// } else {
	// // recuperer Enseignant
	// Enseignant _enseignant = findEnseignant(seanceEmploiDto
	// .getAssociationGroupePedagogiqueId()
	// .intValue());
	// if (_enseignant != null) {
	// seanceEmploiDto.setEnseignantNom(_enseignant
	// .getRefIndividu().getNomLatin());
	// seanceEmploiDto.setEnseignantPrenom(_enseignant
	// .getRefIndividu().getPrenomLatin());
	// }
	// }
	// }
	//
	// seanceEmploiDtos.put(jour.getCode(), seanceEmploiDto);
	// }
	// SeanceEmploiByPlageHoraireDto seanceEmploiByPlageHoraireDto = new
	// SeanceEmploiByPlageHoraireDto(
	// plageHoraireDto, seanceEmploiDtos);
	// //seanceEmploiByPlageHoraireDto.setTest(test);
	// seanceEmploiByPlageHoraireDtos
	// .add(seanceEmploiByPlageHoraireDto);
	// }
	// return seanceEmploiByPlageHoraireDtos;
	// } catch (RuntimeException e) {
	// throw e;
	// }
	//
	// }
	@Override
	public List<SeanceEmploiByPlageHoraireDto> findByEmploiId(
			EmploiDto emploiDto) {
		try {

			List<SeanceEmploiByPlageHoraireDto> seanceEmploiByPlageHoraireDtos = new ArrayList<SeanceEmploiByPlageHoraireDto>();
			// Find Jours
			List<Jour> jours = jourDao.findAll();
			if (jours == null || jours.isEmpty()) {
				return seanceEmploiByPlageHoraireDtos;
			}

			// Find Plage HoraireDtos
			List<PlageHoraire> plageHoraires = plageHoraireDao.findAll();
			if (plageHoraires == null || plageHoraires.isEmpty()) {
				return seanceEmploiByPlageHoraireDtos;
			}
			// initSeanceEmploiByPlageHoraireDtoList
			for (PlageHoraire plageHoraire : plageHoraires) {
				PlageHoraireDto plageHoraireDto = new PlageHoraireDto();
				mapper.map(plageHoraire, plageHoraireDto);
				Map<String, List<SeanceEmploiDto>> seanceEmploiDtos = new HashMap<String, List<SeanceEmploiDto>>();
				Map<String, Boolean> affectations = new HashMap<String, Boolean>();
				for (Jour jour : jours) {
					CelluleHoraire celluleHoraire = celluleHoraireDao
							.findByJourAndPlageHoraire(jour.getId(),
									plageHoraire.getId());

					if (celluleHoraire != null) {
						List<SeanceEmploiDto> seanceEmploiList = findByEmploiIdAndCelluleId(
								emploiDto.getId(), celluleHoraire.getId());
						boolean disabled = verifyLieux(emploiDto,
								celluleHoraire);
						if (seanceEmploiList == null
								|| seanceEmploiList.isEmpty()) {
							seanceEmploiList = new ArrayList<SeanceEmploiDto>();
							SeanceEmploiDto seanceEmploiDto = new SeanceEmploiDto();
							seanceEmploiDto.setEmploiId(emploiDto.getId());
							mapper.map(celluleHoraire, seanceEmploiDto);
							seanceEmploiList.add(seanceEmploiDto);
						} else {
							SeanceEmploiDto seanceEmploiDto = new SeanceEmploiDto();
							seanceEmploiDto.setEmploiId(emploiDto.getId());
							mapper.map(celluleHoraire, seanceEmploiDto);
							seanceEmploiList.add(seanceEmploiDto);

						}
						seanceEmploiDtos.put(jour.getCode(), seanceEmploiList);
						affectations.put(jour.getCode(), disabled);
					}

				}
				SeanceEmploiByPlageHoraireDto seanceEmploiByPlageHoraireDto = new SeanceEmploiByPlageHoraireDto(
						plageHoraireDto, seanceEmploiDtos);
				seanceEmploiByPlageHoraireDto.setAffectations(affectations);
				seanceEmploiByPlageHoraireDtos
						.add(seanceEmploiByPlageHoraireDto);
			}
			return seanceEmploiByPlageHoraireDtos;
		} catch (RuntimeException e) {
			throw e;
		}

	}

	@Override
	public List<SeanceEmploiDto> findByEmploiIdAndCelluleId(int idEmploi,
			int idCellule) {
		try {
			if (idEmploi == 0 || idCellule == 0) {
				return null;
			}
			List<SeanceEmploi> seanceEmploiList = seanceEmploiDao
					.findByEmploiIdAndCelluleId(idEmploi, idCellule);

			if (seanceEmploiList == null || seanceEmploiList.isEmpty()) {
				return null;
			}
			List<SeanceEmploiDto> result = new ArrayList<SeanceEmploiDto>();
			for (SeanceEmploi seanceEmploi : seanceEmploiList) {
				SeanceEmploiDto seanceEmploiDto = new SeanceEmploiDto();
				mapper.map(seanceEmploi, seanceEmploiDto);
				// recuperer Enseignant
				DossierEmploye _enseignant = findEnseignant(seanceEmploi
						.getAssociationGroupePedagogique().getId());
				if (_enseignant != null) {
					seanceEmploiDto.setEnseignantNom(_enseignant
							.getRefIndividu().getNomLatin());
					seanceEmploiDto.setEnseignantPrenom(_enseignant
							.getRefIndividu().getPrenomLatin());
				}
				result.add(seanceEmploiDto);
			}
			return result;
		} catch (RuntimeException e) {
			throw e;
		}

	}

	@Override
	public List<SeanceEmploiByPlageHoraireDto> findbyGroupe(
			SeanceEmploiDto searchDto) {
		try {

			List<SeanceEmploiByPlageHoraireDto> seanceEmploiByPlageHoraireDtos = new ArrayList<SeanceEmploiByPlageHoraireDto>();
			if (searchDto == null || searchDto.getGroupePedagogiqueId() == null
					|| searchDto.getGroupePedagogiqueId() == 0) {
				return seanceEmploiByPlageHoraireDtos;
			}

			// Find Jours
			List<Jour> jours = jourDao.findAll();
			if (jours == null || jours.isEmpty()) {
				return seanceEmploiByPlageHoraireDtos;
			}

			// Find Plage HoraireDtos
			List<PlageHoraire> plageHoraires = plageHoraireDao.findAll();
			if (plageHoraires == null || plageHoraires.isEmpty()) {
				return seanceEmploiByPlageHoraireDtos;
			}
			// initSeanceEmploiByPlageHoraireDtoList
			for (PlageHoraire plageHoraire : plageHoraires) {
				PlageHoraireDto plageHoraireDto = new PlageHoraireDto();
				mapper.map(plageHoraire, plageHoraireDto);
				Map<String, List<SeanceEmploiDto>> seanceEmploiDtos = new HashMap<String, List<SeanceEmploiDto>>();
				// Map<String, Boolean> affectations = new HashMap<String,
				// Boolean>();
				for (Jour jour : jours) {
					CelluleHoraire celluleHoraire = celluleHoraireDao
							.findByJourAndPlageHoraire(jour.getId(),
									plageHoraire.getId());

					if (celluleHoraire != null) {
						searchDto.setCelluleHoraireId(celluleHoraire.getId());
						List<SeanceEmploiDto> seanceEmploiList = findAdvanced(searchDto);
						if (seanceEmploiList == null
								|| seanceEmploiList.isEmpty()) {
							seanceEmploiList = new ArrayList<SeanceEmploiDto>();
							SeanceEmploiDto seanceEmploiDto = new SeanceEmploiDto();
							mapper.map(celluleHoraire, seanceEmploiDto);
							seanceEmploiList.add(seanceEmploiDto);
						}
						seanceEmploiDtos.put(jour.getCode(), seanceEmploiList);

					}

				}
				SeanceEmploiByPlageHoraireDto seanceEmploiByPlageHoraireDto = new SeanceEmploiByPlageHoraireDto(
						plageHoraireDto, seanceEmploiDtos);
				// seanceEmploiByPlageHoraireDto.setAffectations(affectations);
				seanceEmploiByPlageHoraireDtos
						.add(seanceEmploiByPlageHoraireDto);
			}
			return seanceEmploiByPlageHoraireDtos;
		} catch (RuntimeException e) {
			throw e;
		}

	}

	@Override
	public List<EmploiModelDto> convertToEmploiModel(
			List<SeanceEmploiByPlageHoraireDto> seancesList) {
		try {
			if (seancesList == null || seancesList.isEmpty()) {
				return null;
			}
			List<EmploiModelDto> result = new ArrayList<EmploiModelDto>();
			for (SeanceEmploiByPlageHoraireDto seanceEmploiByPlageHoraireDto : seancesList) {

				EmploiModelDto emploiModelDto = new EmploiModelDto();
				emploiModelDto.setPlageHoraire(seanceEmploiByPlageHoraireDto
						.getPlageHoraireDto().getLibelleFr());
				List<String> celluleDimanche = new ArrayList<String>();
				List<String> celluleLundi = new ArrayList<String>();
				List<String> celluleMardi = new ArrayList<String>();
				List<String> celluleMercredi = new ArrayList<String>();
				List<String> celluleJeudi = new ArrayList<String>();
				List<String> celluleVendredi = new ArrayList<String>();
				List<String> celluleSamedi = new ArrayList<String>();
				Map<String, List<SeanceEmploiDto>> map = seanceEmploiByPlageHoraireDto
						.getSeanceEmploiDtos();
				for (Entry<String, List<SeanceEmploiDto>> entry : map
						.entrySet()) {
					String cle = entry.getKey();
					SeanceEmploiDto valeur = (entry.getValue() != null && !entry
							.getValue().isEmpty()) ? entry.getValue().get(0)
							: new SeanceEmploiDto();
					switch (cle) {
					case UtilConstants.DIMANCHE_CODE:
						celluleDimanche.add((valeur.getRefCodeTypeAp()==null || valeur.getRattachementMcMcCode()==null)?null: valeur.getRefCodeTypeAp() + " "+ valeur.getRattachementMcMcCode());
						celluleDimanche.add(valeur.getRefLieuDesignation()==null?null:valeur.getRefLieuDesignation());
						celluleDimanche.add((valeur.getEnseignantNom()==null || valeur.getEnseignantPrenom()==null)?null:valeur.getEnseignantNom() + " "+ valeur.getEnseignantPrenom());
					case UtilConstants.LUNDI_CODE:
						celluleLundi.add((valeur.getRefCodeTypeAp()==null || valeur.getRattachementMcMcCode()==null)?null: valeur.getRefCodeTypeAp() + " "+ valeur.getRattachementMcMcCode());
						celluleLundi.add(valeur.getRefLieuDesignation()==null?null:valeur.getRefLieuDesignation());
						celluleLundi.add((valeur.getEnseignantNom()==null || valeur.getEnseignantPrenom()==null)?null:valeur.getEnseignantNom() + " "+ valeur.getEnseignantPrenom());

					case UtilConstants.MARDI_CODE:
						celluleMardi.add((valeur.getRefCodeTypeAp()==null || valeur.getRattachementMcMcCode()==null)?null: valeur.getRefCodeTypeAp() + " "+ valeur.getRattachementMcMcCode());
						celluleMardi.add(valeur.getRefLieuDesignation()==null?null:valeur.getRefLieuDesignation());
						celluleMardi.add((valeur.getEnseignantNom()==null || valeur.getEnseignantPrenom()==null)?null:valeur.getEnseignantNom() + " "+ valeur.getEnseignantPrenom());

					case UtilConstants.MERCREDI_CODE:
						celluleMercredi.add((valeur.getRefCodeTypeAp()==null || valeur.getRattachementMcMcCode()==null)?null: valeur.getRefCodeTypeAp() + " "+ valeur.getRattachementMcMcCode());
						celluleMercredi.add(valeur.getRefLieuDesignation()==null?null:valeur.getRefLieuDesignation());
						celluleMercredi.add((valeur.getEnseignantNom()==null || valeur.getEnseignantPrenom()==null)?null:valeur.getEnseignantNom() + " "+ valeur.getEnseignantPrenom());

					case UtilConstants.JEUDI_CODE:
						celluleJeudi.add((valeur.getRefCodeTypeAp()==null || valeur.getRattachementMcMcCode()==null)?null: valeur.getRefCodeTypeAp() + " "+ valeur.getRattachementMcMcCode());
						celluleJeudi.add(valeur.getRefLieuDesignation()==null?null:valeur.getRefLieuDesignation());
						celluleJeudi.add((valeur.getEnseignantNom()==null || valeur.getEnseignantPrenom()==null)?null:valeur.getEnseignantNom() + " "+ valeur.getEnseignantPrenom());

					case UtilConstants.VENDREDI_CODE:
						celluleVendredi.add((valeur.getRefCodeTypeAp()==null || valeur.getRattachementMcMcCode()==null)?null: valeur.getRefCodeTypeAp() + " "+ valeur.getRattachementMcMcCode());
						celluleVendredi.add(valeur.getRefLieuDesignation()==null?null:valeur.getRefLieuDesignation());
						celluleVendredi.add((valeur.getEnseignantNom()==null || valeur.getEnseignantPrenom()==null)?null:valeur.getEnseignantNom() + " "+ valeur.getEnseignantPrenom());
					case UtilConstants.SAMEDI_CODE:
						celluleSamedi.add((valeur.getRefCodeTypeAp()==null || valeur.getRattachementMcMcCode()==null)?null: valeur.getRefCodeTypeAp() + " "+ valeur.getRattachementMcMcCode());
						celluleSamedi.add(valeur.getRefLieuDesignation()==null?null:valeur.getRefLieuDesignation());
						celluleSamedi.add((valeur.getEnseignantNom()==null || valeur.getEnseignantPrenom()==null)?null:valeur.getEnseignantNom() + " "+ valeur.getEnseignantPrenom());

					}
				}
				emploiModelDto.setCelluleDimanche(celluleDimanche);
				emploiModelDto.setCelluleLundi(celluleLundi);
				emploiModelDto.setCelluleMardi(celluleMardi);
				emploiModelDto.setCelluleMercredi(celluleMercredi);
				emploiModelDto.setCelluleJeudi(celluleJeudi);
				emploiModelDto.setCelluleVendredi(celluleVendredi);
				emploiModelDto.setCelluleSamedi(celluleSamedi);
				result.add(emploiModelDto);
			}
			return result;
		} catch (RuntimeException e) {
			throw e;
		}
	}

	@Override
	public List<SeanceEmploiDto> findAdvanced(SeanceEmploiDto searchDto) {
		try {
			if (searchDto == null) {
				return null;
			}
			SeanceEmploi searchBo = new SeanceEmploi();
			mapper.map(searchDto, searchBo);
			List<SeanceEmploi> seanceEmploiList = seanceEmploiDao
					.findAdvanced(searchBo);

			if (seanceEmploiList == null || seanceEmploiList.isEmpty()) {
				return null;
			}
			List<SeanceEmploiDto> result = new ArrayList<SeanceEmploiDto>();
			for (SeanceEmploi seanceEmploi : seanceEmploiList) {
				SeanceEmploiDto seanceEmploiDto = new SeanceEmploiDto();
				mapper.map(seanceEmploi, seanceEmploiDto);
				// recuperer Enseignant
				DossierEmploye _enseignant = findEnseignant(seanceEmploi
						.getAssociationGroupePedagogique().getId());
				if (_enseignant != null) {
					seanceEmploiDto.setEnseignantNom(_enseignant
							.getRefIndividu().getNomLatin());
					seanceEmploiDto.setEnseignantPrenom(_enseignant
							.getRefIndividu().getPrenomLatin());
				}
				result.add(seanceEmploiDto);
			}
			return result;
		} catch (RuntimeException e) {
			throw e;
		}
	}

	private DossierEmploye findEnseignant(int idAGP) {
		try {
			if (idAGP == 0) {
				return null;
			}
			AssociationGroupePedagogique _agp = associationGroupePedagogiqueDao
					.findById(idAGP);
			if (_agp != null) {
				DossierEmploye _enseignant = enseignantChargePedagogiqueDao
						.findEnseignant(_agp.getGroupePedagogique().getId(),
								_agp.getRattachementMc().getId(), _agp.getAp()
										.getId(), _agp.getGroupePedagogique()
										.getPeriode().getNiveau().getId());
				return _enseignant;
			}

		} catch (RuntimeException e) {

		}
		return null;
	}

	private boolean verifyLieux(EmploiDto emploiDto,
			CelluleHoraire celluleHoraire) {
		try {
			AffectationLieuStructure searchBo = new AffectationLieuStructure();
			searchBo.setAnneeAcademique(new AnneeAcademique(emploiDto
					.getAnneeAcademiqueId().intValue()));

			searchBo.setNomenclatureByIdNcPeriode(new Nomenclature(emploiDto
					.getPeriodeId(), null));
			searchBo.setNomenclatureByIdNcPeriodicite(new Nomenclature(
					emploiDto.getPeriodiciteId(), null));
			searchBo.setCelluleHoraire(celluleHoraire);
			searchBo.setOuvertureOffreFormation(new OuvertureOffreFormation(
					emploiDto.getOuvertureOffreFormationId()));
			List<AffectationLieuStructure> affectationLieuStructures = affectationLieuStructureDao
					.findAdvanced(searchBo);
			if (affectationLieuStructures != null
					&& !affectationLieuStructures.isEmpty()) {
				return true;
			}
		} catch (RuntimeException e) {

		}
		return false;
	}

}
