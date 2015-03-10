/**
 * [dz.gov.mesrs.sii.fve.web.jsf.bean.UtilBean.java] 
 * @author MAKERRI Sofiane on : 15 oct. 2014  08:32:34
 */
package dz.gov.mesrs.sii.fve.web.jsf.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteDto;
import dz.gov.mesrs.sii.commons.business.service.bpm.SituationService;
import dz.gov.mesrs.sii.commons.data.util.UtilConstant;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.AssociationGroupePedagogiqueDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.GroupePedagogiqueDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.MentionDto;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.CoefficientExamenDto;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.ExamenSessionDto;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.PlanningSessionDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.AnneeAcademiqueDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.AtomePedagogiqueDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.NiveauDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OffreFormationDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OuvertureOffreFormationDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.PeriodeDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.RattachementMcDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.RepartitionUeDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.UniteEnseignementDto;
import dz.gov.mesrs.sii.fve.business.service.cursus.AffectationGroupePedagogiqueService;
import dz.gov.mesrs.sii.fve.business.service.cursus.AssociationGroupePedagogiqueService;
import dz.gov.mesrs.sii.fve.business.service.cursus.ExamenSessionService;
import dz.gov.mesrs.sii.fve.business.service.cursus.GroupePedagogiqueService;
import dz.gov.mesrs.sii.fve.business.service.cursus.MentionService;
import dz.gov.mesrs.sii.fve.business.service.examen.CoefficientExamenService;
import dz.gov.mesrs.sii.fve.business.service.examen.PlanningSessionService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.AnneeAcademiqueService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.AtomePedagogiqueService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.NiveauService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OffreFormationService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OuvertureOffreFormationService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.PeriodeService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.RattachementMcService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.RepartitionUeService;
import dz.gov.mesrs.sii.fve.business.util.UtilConstants;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.CursusConstants;
import dz.gov.mesrs.sii.fve.web.jsf.bean.utils.Const;
import dz.gov.mesrs.sii.fve.web.jsf.bean.utils.Utility;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.service.NomenclatureService;

/**
 * @author MAKERRI Sofiane on : 15 oct. 2014 08:32:34
 */
/**
 * @author MAKERRI Sofiane on : 4 dÃ©c. 2014 09:51:42
 */
@ManagedBean(name = "utilBean")
@ViewScoped
public class UtilBean implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author MAKERRI Sofiane on : 15 oct. 2014 08:32:38
	 */
	private static final long serialVersionUID = 1L;
	@ManagedProperty("#{sessionBeanFve}")
	private SessionBean sessionBean;
	@ManagedProperty("#{ouvertureOffreFormationService}")
	private OuvertureOffreFormationService ouvertureOffreFormationService;
	@ManagedProperty("#{offreFormationService}")
	private OffreFormationService offreFormationService;
	@ManagedProperty(value = "#{repartitionUeService}")
	private RepartitionUeService repartitionUeService;
	@ManagedProperty(value = "#{rattachementMcService}")
	private RattachementMcService rattachementMcService;
	@ManagedProperty("#{anneeAcademiqueService}")
	private AnneeAcademiqueService anneeAcademiqueService;
	@ManagedProperty("#{niveauService}")
	private NiveauService niveauService;
	@ManagedProperty("#{periodeService}")
	private PeriodeService periodeService;
	@ManagedProperty("#{atomePedagogiqueService}")
	private AtomePedagogiqueService atomePedagogiqueService;
	@ManagedProperty("#{associationGroupePedagogiqueService}")
	private AssociationGroupePedagogiqueService associationGroupePedagogiqueService;
	@ManagedProperty("#{affectationGroupePedagogiqueService}")
	private AffectationGroupePedagogiqueService affectationGroupePedagogiqueService;
	@ManagedProperty("#{examenSessionService}")
	private ExamenSessionService examenSessionService;
	@ManagedProperty("#{planningSessionService}")
	private PlanningSessionService planningSessionService;
	@ManagedProperty("#{nomenclatureServiceImpl}")
	private NomenclatureService nomenclatureService;
	@ManagedProperty(value = "#{mentionService}")
	private MentionService mentionService;
	@ManagedProperty("#{groupePedagogiqueService}")
	private GroupePedagogiqueService groupePedagogiqueService;
	@ManagedProperty(value = "#{situationService}")
	private SituationService situationService;
	@ManagedProperty("#{coefficientExamenService}")
	private CoefficientExamenService coefficientExamenService;

	/**
	 * [UtilBean.UtilBean()] Constructor
	 * 
	 * @author MAKERRI Sofiane on : 15 oct. 2014 08:32:34
	 */
	public UtilBean() {
		super();
	}

	/**
	 * [UtilBean.loadOffreFormationOuverte] Method
	 * 
	 * @author MAKERRI Sofiane on : 15 oct. 2014 08:35:32
	 * @param anneeAcademiqueId
	 * @return
	 */
	public List<SelectItem> loadOffreFormationOuverte(Integer anneeAcademiqueId) {
		List<SelectItem> ofList = new ArrayList<SelectItem>();
		try {
			if (anneeAcademiqueId == null) {
				return null;
			}
			OuvertureOffreFormationDto searchDto = new OuvertureOffreFormationDto();
			searchDto.setIdEtablissement(sessionBean.getIdEtablissement());
			searchDto.setAnneeAcademiqueId(anneeAcademiqueId);
			searchDto.setEstOuverte(true);
			List<OuvertureOffreFormationDto> list = ouvertureOffreFormationService
					.findAdvanced(searchDto);
			if (list != null) {
				for (OuvertureOffreFormationDto ouvertureOffreFormationDto : list) {
					SelectItem selectItem = new SelectItem(
							ouvertureOffreFormationDto.getId(),
							ouvertureOffreFormationDto.getOfLibelleLongFr());

					ofList.add(selectItem);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ofList;

	}

	/**
	 * [UtilBean.findOofById] Method
	 * 
	 * @author MAKERRI Sofiane on : 31 déc. 2014 11:49:35
	 * @param oofId
	 * @return
	 */
	public OuvertureOffreFormationDto findOofById(Integer oofId) {
		if (oofId != null) {
			return ouvertureOffreFormationService.findById(oofId);
		}
		return null;
	}
	
	/**
	 * [UtilBean.findOfById] Method 
	 * @author MAKERRI Sofiane  on : 17 févr. 2015  13:47:27
	 * @param ofId
	 * @return
	 */
	public OffreFormationDto findOfById(Integer ofId) {
		if (ofId != null) {
			return offreFormationService.findById(ofId);
		}
		return null;
	}

	/**
	 * [UtilBean.findPeriodeById] Method
	 * 
	 * @author MAKERRI Sofiane on : 31 déc. 2014 11:50:48
	 * @param periodeId
	 * @return
	 */
	public PeriodeDto findPeriodeById(Integer periodeId) {
		if (periodeId != null) {
			return periodeService.findById(periodeId);
		}
		return null;
	}

	/**
	 * [UtilBean.findAnneeAcademiqueById] Method
	 * 
	 * @author MAKERRI Sofiane on : 31 déc. 2014 11:53:06
	 * @param anneeAcademiqueId
	 * @return
	 */
	public AnneeAcademiqueDto findAnneeAcademiqueById(Integer anneeAcademiqueId) {
		if (anneeAcademiqueId != null) {
			return anneeAcademiqueService.findById(anneeAcademiqueId);
		}
		return null;
	}

	/**
	 * [UtilBean.loadNomenclatureItem] Method
	 * 
	 * @author MAKERRI Sofiane on : 20 nov. 2014 17:19:04
	 * @param name
	 * @return
	 */
	public List<SelectItem> loadNomenclatureCodeItem(String name) {
		if (name == null) {
			return null;
		}
		List<NomenclatureDto> list = nomenclatureService.findByName(name);
		if (list != null) {
			List<SelectItem> result = new ArrayList<SelectItem>();
			for (NomenclatureDto item : list) {

				result.add(new SelectItem(item.getId(), item.getCode()));
			}
			return result;
		}
		return null;
	}

	/**
	 * [UtilBean.loadNomenclatureLibelleItem] Method
	 * 
	 * @author MAKERRI Sofiane on : 23 nov. 2014 15:19:39
	 * @param name
	 * @return
	 */
	public List<SelectItem> loadNomenclatureLibelleItem(String name) {
		if (name == null) {
			return null;
		}
		List<NomenclatureDto> list = nomenclatureService.findByName(name);
		if (list != null) {
			List<SelectItem> result = new ArrayList<SelectItem>();
			for (NomenclatureDto item : list) {

				result.add(new SelectItem(item.getId(), item.getLibelleLongFr()));
			}
			return result;
		}
		return null;
	}

	/**
	 * [UtilBean.loadMc] Method
	 * 
	 * @author MAKERRI Sofiane on : 15 oct. 2014 08:36:30
	 */
	public List<SelectItem> loadMcGroupedByUe(Integer oofId, Integer periodeId) {

		if (oofId == null || periodeId == null) {
			return null;
		}
		List<SelectItem> mcByUeList = null;
		List<UniteEnseignementDto> ueList = repartitionUeService.findUeOof(
				oofId, periodeId);
		if (ueList != null) {
			mcByUeList = new ArrayList<SelectItem>();
			for (UniteEnseignementDto _ue : ueList) {
				SelectItemGroup ue = new SelectItemGroup(_ue.getLibelleFr());
				List<RattachementMcDto> rmcList = rattachementMcService.find(
						_ue.getId(), null);
				if (rmcList != null) {
					SelectItem[] mcs = new SelectItem[rmcList.size()];
					int i = 0;
					for (RattachementMcDto rmc : rmcList) {
						SelectItem mc = new SelectItem(rmc.getId(),
								rmc.getMcLibelleFr());
						mcs[i] = mc;
						i++;
					}
					ue.setSelectItems(mcs);
				}

				mcByUeList.add(ue);
			}
		}
		return mcByUeList;
	}

	/**
	 * [UtilBean.loadRepartitionUe] Method
	 * 
	 * @author MAKERRI Sofiane on : 8 janv. 2015 10:33:58
	 * @param oofId
	 * @param periodeId
	 * @return
	 */
	public List<RepartitionUeDto> loadRepartitionUe(Integer oofId,
			Integer periodeId) {
		if (oofId == null || periodeId == null) {
			return null;
		}
		return repartitionUeService.findReparationUe(oofId, periodeId);
	}

	/**
	 * [UtilBean.loadRattachementMc] Method
	 * 
	 * @author MAKERRI Sofiane on : 6 janv. 2015 09:58:24
	 * @param oofId
	 * @param periodeId
	 * @return
	 */
	public List<RattachementMcDto> loadRattachementMc(Integer oofId,
			Integer periodeId) {
		if (oofId == null || periodeId == null) {
			return null;
		}
		return rattachementMcService.findByOffreFormationAndPeriode(oofId,
				periodeId);
	}

	/**
	 * [UtilBean.loadPeriodeByNiveau] Method
	 * 
	 * @author MAKERRI Sofiane on : 15 oct. 2014 08:42:26
	 * @param oofId
	 * @return
	 */
	public List<SelectItem> loadPeriodeByNiveau(Integer oofId) {
		if (oofId == null) {
			return null;
		}
		OuvertureOffreFormationDto _oof = ouvertureOffreFormationService
				.findById(oofId);
		List<NiveauDto> niveauList = niveauService.findByCycleId(_oof
				.getCycleId());
		List<SelectItem> periodeByNiveau = null;
		if (niveauList != null) {
			periodeByNiveau = new ArrayList<SelectItem>();
			for (NiveauDto niveauDto : niveauList) {
				SelectItemGroup niveau = new SelectItemGroup(
						niveauDto.getLibelleLongLt());
				List<PeriodeDto> periodeList = periodeService
						.findByLevelId(niveauDto.getId());
				if (periodeList != null) {
					SelectItem[] periodeItems = new SelectItem[periodeList
							.size()];
					int i = 0;
					for (PeriodeDto periodeDto : periodeList) {
						SelectItem periode = new SelectItem(periodeDto.getId(),
								periodeDto.getLibelleLongLt());
						periodeItems[i] = periode;
						i++;
					}
					niveau.setSelectItems(periodeItems);
				}
				periodeByNiveau.add(niveau);
			}
		}
		return periodeByNiveau;

	}

	/**
	 * [UtilBean.loadPeriode] Method
	 * 
	 * @author MAKERRI Sofiane on : 2 nov. 2014 10:36:27
	 * @param niveauId
	 * @return
	 */
	public List<SelectItem> loadPeriode(Integer niveauId) {
		if (niveauId == null) {
			return null;
		}
		List<PeriodeDto> periodeList = periodeService.findByLevelId(niveauId);
		if (periodeList != null) {
			List<SelectItem> result = new ArrayList<SelectItem>();
			for (PeriodeDto periodeDto : periodeList) {
				SelectItem periode = new SelectItem(periodeDto.getId(),
						periodeDto.getLibelleLongLt());
				result.add(periode);
			}
			return result;
		}
		return null;
	}

	/**
	 * [UtilBean.loadAnneeAcademique] Method
	 * 
	 * @author MAKERRI Sofiane on : 15 oct. 2014 09:21:47
	 * @return
	 */
	public List<SelectItem> loadAnneeAcademique() {
		List<SelectItem> anneeAcademiqueList = null;
		try {
			List<AnneeAcademiqueDto> list = anneeAcademiqueService.findAll();

			if (list != null) {
				anneeAcademiqueList = new ArrayList<SelectItem>();
				for (AnneeAcademiqueDto annee : list) {
					SelectItem item = new SelectItem(annee.getId(),
							annee.getCode());
					anneeAcademiqueList.add(item);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return anneeAcademiqueList;
	}

	/**
	 * [UtilBean.loadGroupePedagogiqueByAp] Method
	 * 
	 * @author MAKERRI Sofiane on : 15 oct. 2014 10:41:32
	 * @param rattachementMcId
	 * @return
	 */
	public List<SelectItem> loadGroupePedagogiqueByAp(Integer oofId,
			Integer periodeId, Integer rattachementMcId) {
		List<SelectItem> groupes = null;
		if (rattachementMcId != null) {

			List<AtomePedagogiqueDto> list = atomePedagogiqueService
					.findByRattachementMC(rattachementMcId);
			if (list != null) {
				groupes = new ArrayList<SelectItem>();
			}
			for (AtomePedagogiqueDto item : list) {
				SelectItemGroup ap = new SelectItemGroup(item.getNcTypeApCode());
				List<AssociationGroupePedagogiqueDto> assocList = associationGroupePedagogiqueService
						.findAdvanced(rattachementMcId, item.getId(), oofId,
								periodeId);
				if (assocList != null) {
					SelectItem[] groupeItems = new SelectItem[assocList.size()];
					int i = 0;
					for (AssociationGroupePedagogiqueDto assoc : assocList) {
						SelectItem groupe = new SelectItem(assoc.getId(),
								assoc.getGroupePedagogiqueNom() + "("
										+ item.getNcTypeApCode() + ")");
						groupeItems[i] = groupe;
						i++;
					}
					ap.setSelectItems(groupeItems);
				}

				groupes.add(ap);
			}
		}
		return groupes;
	}

	/**
	 * [UtilBean.loadGroupePedagogiqueByOofAndPeriode] Method
	 * 
	 * @author MAKERRI Sofiane on : 18 nov. 2014 09:45:37
	 * @param oofId
	 * @param periodeId
	 * @return
	 */
	public List<SelectItem> loadGroupePedagogiqueByOofAndPeriode(Integer oofId,
			Integer periodeId) {
		try {
			List<SelectItem> groupes = new ArrayList<SelectItem>();
			if (oofId != null && periodeId != null) {
				GroupePedagogiqueDto searchDto = new GroupePedagogiqueDto();
				searchDto.setOofId(oofId);
				searchDto.setPeriodeId(periodeId);
				List<GroupePedagogiqueDto> groupeDtos = groupePedagogiqueService
						.findAdvanced(searchDto);
				if (groupeDtos != null) {
					for (GroupePedagogiqueDto groupePedagogiqueDto : groupeDtos) {
						SelectItem item = new SelectItem(
								groupePedagogiqueDto.getId(),
								groupePedagogiqueDto.getLlGroupe());
						groupes.add(item);
					}
				}

			}
			return groupes;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * [UtilBean.loadSection] Method
	 * 
	 * @author MAKERRI Sofiane on : 22 janv. 2015 09:02:49
	 * @param oofId
	 * @param periodeId
	 * @return
	 */
	public List<SelectItem> loadSectionItem(Integer oofId, Integer periodeId) {
		try {
			List<SelectItem> groupes = new ArrayList<SelectItem>();
			if (oofId != null && periodeId != null) {
				List<GroupePedagogiqueDto> groupeDtos = groupePedagogiqueService
						.findSection(oofId, periodeId);
				if (groupeDtos != null) {
					for (GroupePedagogiqueDto groupePedagogiqueDto : groupeDtos) {
						SelectItem item = new SelectItem(
								groupePedagogiqueDto.getId(),
								groupePedagogiqueDto.getLlGroupe());
						groupes.add(item);
					}
				}

			}
			return groupes;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * [UtilBean.loadSection] Method
	 * 
	 * @author MAKERRI Sofiane on : 26 janv. 2015 09:30:34
	 * @param oofId
	 * @param periodeId
	 * @return
	 */
	public List<GroupePedagogiqueDto> loadSection(Integer oofId,
			Integer periodeId) {
		try {
			if (oofId != null && periodeId != null) {
				List<GroupePedagogiqueDto> groupeDtos = groupePedagogiqueService
						.findSection(oofId, periodeId);
				return groupeDtos;
			}
		} catch (Exception e) {
			return null;
		}
		return null;
	}

	/**
	 * [UtilBean.loadGroupeOfSection] Method
	 * 
	 * @author MAKERRI Sofiane on : 22 janv. 2015 16:12:40
	 * @param sectionId
	 * @return
	 */
	public List<SelectItem> loadGroupeOfSectionItem(Integer sectionId) {
		try {
			List<SelectItem> groupes = new ArrayList<SelectItem>();
			if (sectionId != null) {
				List<GroupePedagogiqueDto> groupeDtos = groupePedagogiqueService
						.findGroupesOfSection(sectionId);
				if (groupeDtos != null) {
					for (GroupePedagogiqueDto groupePedagogiqueDto : groupeDtos) {
						SelectItem item = new SelectItem(
								groupePedagogiqueDto.getId(),
								groupePedagogiqueDto.getLlGroupe());
						groupes.add(item);
					}
				}

			}
			return groupes;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * [UtilBean.loadGroupeOfSection] Method
	 * 
	 * @author MAKERRI Sofiane on : 26 janv. 2015 09:29:38
	 * @param sectionId
	 * @return
	 */
	public List<GroupePedagogiqueDto> loadGroupeOfSection(Integer sectionId) {
		try {
			if (sectionId != null) {
				List<GroupePedagogiqueDto> groupeDtos = groupePedagogiqueService
						.findGroupesOfSection(sectionId);
				return groupeDtos;
			}
		} catch (Exception e) {
			return null;
		}
		return null;
	}

	/**
	 * [UtilBean.loadExamenGroupedPlanning] Method
	 * 
	 * @author MAKERRI Sofiane on : 18 oct. 2014 12:17:48
	 * @param searchDto
	 * @return
	 */
	public List<SelectItem> loadExamenGroupedPlanning(
			PlanningSessionDto searchDto) {
		List<SelectItem> examens = null;
		try {
			List<PlanningSessionDto> list = planningSessionService
					.findAdvanced(searchDto);

			if (list != null) {
				examens = new ArrayList<SelectItem>();

				for (PlanningSessionDto item : list) {
					SelectItemGroup ap = new SelectItemGroup(item.getIntitule());
					List<ExamenSessionDto> examenList = examenSessionService
							.findBySession(item.getId());
					if (examenList != null) {
						SelectItem[] examenItems = new SelectItem[examenList
								.size()];
						int i = 0;
						for (ExamenSessionDto _examen : examenList) {
							SelectItem examen = new SelectItem(_examen.getId(),
									_examen.getMcLibelleFr());
							examenItems[i] = examen;
							i++;
						}
						ap.setSelectItems(examenItems);
					}

					examens.add(ap);
				}
			}
		} catch (Exception e) {
			return null;
		}
		return examens;
	}

	/**
	 * [UtilBean.loadPlanningSession] Method
	 * 
	 * @author MAKERRI Sofiane on : 2 nov. 2014 16:29:59
	 * @param searchDto
	 * @return
	 */
	public List<SelectItem> loadPlanningSession(PlanningSessionDto searchDto) {
		List<PlanningSessionDto> list = planningSessionService
				.findAdvanced(searchDto);

		if (list != null) {
			List<SelectItem> planingExamenList = new ArrayList<SelectItem>();
			for (PlanningSessionDto item : list) {
				planingExamenList.add(new SelectItem(item.getId(), item
						.getIntitule()));
			}
			return planingExamenList;
		}
		return null;
	}

	/**
	 * 
	 * [UtilBean.loadNiveaux] Method
	 * 
	 * @author BELDI Jamel on : 19 oct. 2014 10:27:04
	 * @param oofId
	 * @return
	 */
	public List<SelectItem> loadNiveaux(Integer oofId) {
		List<SelectItem> niveauList = new ArrayList<SelectItem>();
		try {
			if (oofId == null) {
				return niveauList;
			}
			OuvertureOffreFormationDto _oof = ouvertureOffreFormationService
					.findById(oofId);
			List<NiveauDto> list = niveauService.findByCycleId(_oof
					.getCycleId());

			for (NiveauDto niveauDto : list) {
				SelectItem item = new SelectItem(niveauDto.getId(),
						niveauDto.getLibelleLongLt());
				niveauList.add(item);
			}
			return niveauList;
		} catch (Exception e) {
			e.printStackTrace();
			return niveauList;
		}
	}
	
	/**
	 * [UtilBean.loadLastNiveauOfCycle] Method 
	 * @author MAKERRI Sofiane  on : 16 févr. 2015  11:29:05
	 * @param cycleId
	 * @return
	 */
	public NiveauDto loadLastNiveauOfCycle(Integer cycleId) {
		if(cycleId != null) {
			return niveauService.findLastNiveauOf(cycleId);
		}
		return null;
	}
	

	/**
	 * 
	 * [UtilBean.loadPeriodes] Method
	 * 
	 * @author BELDI Jamel on : 19 oct. 2014 10:28:48
	 * @param niveauId
	 * @return
	 */
	public List<SelectItem> loadPeriodes(Integer niveauId) {
		List<SelectItem> periodeList = new ArrayList<SelectItem>();
		try {

			if (niveauId == null) {
				return periodeList;
			}

			List<PeriodeDto> list = periodeService.findByLevelId(niveauId);

			for (PeriodeDto periodeDto : list) {
				SelectItem item = new SelectItem(periodeDto.getId(),
						periodeDto.getLibelleLongLt());
				periodeList.add(item);
			}
			return periodeList;
		} catch (Exception e) {
			e.printStackTrace();
			return periodeList;
		}
	}

	/**
	 * [UtilBean.loadTypeSessions] Method
	 * 
	 * @author MAKERRI Sofiane on : 18 nov. 2014 09:46:08
	 * @return
	 */
	public List<SelectItem> loadTypeSessions() {
		List<SelectItem> typeSessionList = new ArrayList<SelectItem>();
		try {
			List<NomenclatureDto> list = nomenclatureService
					.findByName(CursusConstants.NC_TYPE_SESSION);

			for (NomenclatureDto type : list) {
				SelectItem item = new SelectItem(type.getCode(),
						type.getLibelleLongFr());
				typeSessionList.add(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return typeSessionList;
	}

	/**
	 * [UtilBean.findCoefficientExamen] Method
	 * 
	 * @author MAKERRI Sofiane on : 6 janv. 2015 09:29:05
	 * @param oofId
	 * @param periodeId
	 * @param rattachementMcId
	 * @return
	 */
	public CoefficientExamenDto findCoefficientExamen(Integer oofId,
			Integer periodeId, Integer rattachementMcId) {
		if (rattachementMcId != null && oofId != null && periodeId != null) {
			return coefficientExamenService.findUnique(oofId, periodeId,
					rattachementMcId);
		}
		return null;
	}

	/**
	 * [UtilBean.loadDiaSituationValidate] Method
	 * 
	 * @author MAKERRI Sofiane on : 18 nov. 2014 09:47:01
	 * @return
	 */
	public SituationEntiteDto loadDiaSituationValidate() {
		return situationService.findBySituationEntiteByCodeSituation(
				UtilConstants.ENTITE_DOSSIER_CODE,
				UtilConstants.SITUTAION_VALIDEE_CODE);
	}

	/**
	 * [UtilBean.loadNcTypeSessionRemplacement] Method
	 * 
	 * @author MAKERRI Sofiane on : 18 nov. 2014 12:38:15
	 * @return
	 */
	public NomenclatureDto loadNcTypeSessionRemplacement() {
		return nomenclatureService.findByCode(CursusConstants.NC_TYPE_SESSION,
				CursusConstants.NC_TYPE_SESSION_REMPLACEMENT_CODE_VALUE);
	}

	/**
	 * [UtilBean.loadNcTypeSessionNormale] Method
	 * 
	 * @author MAKERRI Sofiane on : 18 nov. 2014 12:39:39
	 * @return
	 */
	public NomenclatureDto loadNcTypeSessionNormale() {
		return nomenclatureService.findByCode(CursusConstants.NC_TYPE_SESSION,
				CursusConstants.NC_TYPE_SESSION_NORMAL_CODE_VALUE);
	}

	/**
	 * [UtilBean.loadNcTypeSessionControleIntermeidiare] Method
	 * 
	 * @author MAKERRI Sofiane on : 11 janv. 2015 12:20:55
	 * @return
	 */
	public NomenclatureDto loadNcTypeSessionControleIntermeidiare() {
		return nomenclatureService
				.findByCode(
						CursusConstants.NC_TYPE_SESSION,
						CursusConstants.NC_TYPE_SESSION_CONTROLE_INTERMEIDIARE_CODE_VALUE);
	}

	/**
	 * 
	 * [UtilBean.loadPeriodiciteList] Method
	 * 
	 * @author BELDI Jamel on : 22 oct. 2014 18:00:31
	 * @return
	 */
	public List<SelectItem> loadPeriodiciteList() {
		List<SelectItem> periodiciteList = new ArrayList<SelectItem>();
		try {
			List<NomenclatureDto> list = nomenclatureService
					.findByName(CursusConstants.NC_PERIODICITE);
			for (NomenclatureDto periodicite : list) {
				SelectItem item = new SelectItem(periodicite.getId(),
						periodicite.getLibelleLongFr());
				periodiciteList.add(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return periodiciteList;
	}

	public List<SelectItem> loadPeriodeByPeriodicite(Integer periodiciteId) {
		List<SelectItem> periodeList = new ArrayList<SelectItem>();
		try {
			List<NomenclatureDto> list = nomenclatureService.findByReference(
					CursusConstants.NC_PERIODE_PAR_PERIODICITE, periodiciteId);

			for (NomenclatureDto periode : list) {
				SelectItem item = new SelectItem(periode.getId(),
						periode.getLibelleLongFr());
				periodeList.add(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return periodeList;
	}

	/**
	 * [UtilBean.loadTypeDecisionListAnnuel] Method
	 * 
	 * @author MAKERRI Sofiane on : 9 nov. 2014 12:59:34
	 * @return
	 */
	public List<NomenclatureDto> loadTypeDecisionListAnnuel() {
		List<NomenclatureDto> list = nomenclatureService
				.findByName(Const.NC_LMD_TYPE_DECISION_ADMISSION);
		return list;
	}

	/**
	 * [UtilBean.loadTypeDecisionListPeriode] Method
	 * 
	 * @author MAKERRI Sofiane on : 9 nov. 2014 12:59:50
	 * @return
	 */
	public List<NomenclatureDto> loadTypeDecisionListPeriode() {
		List<NomenclatureDto> list = nomenclatureService
				.findByName(Const.NC_LMD_TYPE_DECISION_ADMISSION_PERIODE);
		return list;
	}
	
	
	/**
	 * [UtilBean.loadNcSignatureDiplome] Method 
	 * @author MAKERRI Sofiane  on : 25 févr. 2015  07:45:24
	 * @return
	 */
	public List<NomenclatureDto> loadNcSignatureDiplome() {
		List<NomenclatureDto> list = nomenclatureService
				.findByName(Const.NC_LMD_SIGNATURE_DIPLOME);
		return list;
	}

	/**
	 * [UtilBean.loadTypeDcisionAdmission] Method
	 * 
	 * @author MAKERRI Sofiane on : 26 oct. 2014 15:00:35
	 * @return
	 */
	public List<SelectItem> loadTypeDecisionAdmission() {
		List<SelectItem> decisonList = new ArrayList<SelectItem>();
		List<NomenclatureDto> list = nomenclatureService
				.findByName(Const.NC_LMD_TYPE_DECISION_ADMISSION);
		if (list != null) {
			for (NomenclatureDto decison : list) {
				SelectItem item = new SelectItem(decison.getId(),
						decison.getLibelleLongFr());
				decisonList.add(item);
			}
		}
		return decisonList;
	}

	/**
	 * [UtilBean.getNomenclatureItemValue] Method
	 * 
	 * @author MAKERRI Sofiane on : 4 dÃ©c. 2014 09:51:48
	 * @param nomenclatureName
	 * @param nomenclatureItemCode
	 * @return
	 */
	public NomenclatureDto getNomenclatureItemValue(String nomenclatureName,
			String nomenclatureItemCode) {

		try {
			return nomenclatureService.findByCode(nomenclatureName,
					nomenclatureItemCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * [UtilBean.findNcRoleEnseignant] Method 
	 * @author MAKERRI Sofiane  on : 1 févr. 2015  11:30:49
	 * @return
	 */
	public NomenclatureDto findNcRoleEnseignant() {
		try {
			return getNomenclatureItemValue(CursusConstants.NC_ROLE_NAME,
					CursusConstants.ROLE_ENSEIGNANT_CODE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * [UtilBean.loadTypeDecisionAdmissionPeriode] Method
	 * 
	 * @author MAKERRI Sofiane on : 28 oct. 2014 17:13:27
	 * @return
	 */
	public List<SelectItem> loadTypeDecisionAdmissionPeriode() {
		List<SelectItem> decisonList = new ArrayList<SelectItem>();
		List<NomenclatureDto> list = nomenclatureService
				.findByName(Const.NC_LMD_TYPE_DECISION_ADMISSION_PERIODE);
		if (list != null) {
			for (NomenclatureDto decison : list) {
				SelectItem item = new SelectItem(decison.getId(),
						decison.getLibelleLongFr());
				decisonList.add(item);
			}
		}
		return decisonList;
	}

	/**
	 * [UtilBean.loadAdmisDecison] Method
	 * 
	 * @author MAKERRI Sofiane on : 26 oct. 2014 15:09:23
	 * @return
	 */
	public NomenclatureDto loadAdmisDecisonAnnuel() {
		return nomenclatureService.findByCode(
				Const.NC_LMD_TYPE_DECISION_ADMISSION,
				UtilConstant.NC_LMD_TYPE_DECISION_ADMISSION_ADMIS_VALUE);
	}

	/**
	 * [UtilBean.loadAdmisDecisonPeriode] Method
	 * 
	 * @author MAKERRI Sofiane on : 3 nov. 2014 14:25:51
	 * @return
	 */
	public NomenclatureDto loadAdmisDecisonPeriode() {
		return nomenclatureService.findByCode(
				Const.NC_LMD_TYPE_DECISION_ADMISSION_PERIODE,
				Const.NC_LMD_TYPE_DECISION_ADMISSION_VALIDATION_PERIODE_VALUE);
	}

	/**
	 * [UtilBean.loadAllMention] Method
	 * 
	 * @author MAKERRI Sofiane on : 23 oct. 2014 13:48:07
	 * @param anneeAcademiqueId
	 * @return
	 */
	public List<MentionDto> loadAllMention(Integer anneeAcademiqueId) {
		if (anneeAcademiqueId != null) {
			return mentionService.findByAnneeAcademique(anneeAcademiqueId);
		}
		return null;
	}

	/**
	 * [UtilBean.loadMentionByNote] Method
	 * 
	 * @author MAKERRI Sofiane on : 23 oct. 2014 13:49:31
	 * @param anneeAcademiqueId
	 * @param note
	 * @return
	 */
	public MentionDto loadMentionByNote(Integer anneeAcademiqueId, double note) {
		if (anneeAcademiqueId != null) {
			return mentionService.findByNote(anneeAcademiqueId, note);
		}
		return null;
	}

	/**
	 * [UtilBean.findOof] Method
	 * 
	 * @author MAKERRI Sofiane on : 2 nov. 2014 10:54:11
	 * @param oofId
	 * @return
	 */
	public OuvertureOffreFormationDto findOof(Integer oofId) {
		if (oofId != null) {
			return ouvertureOffreFormationService.findById(oofId);
		}
		return null;
	}

	/**
	 * [UtilBean.findNiveau] Method
	 * 
	 * @author MAKERRI Sofiane on : 2 nov. 2014 10:55:59
	 * @param niveauId
	 * @return
	 */
	public NiveauDto findNiveau(Integer niveauId) {
		if (niveauId != null) {
			return niveauService.findById(niveauId);
		}
		return null;
	}

	/**
	 * [UtilBean.findPeriode] Method
	 * 
	 * @author MAKERRI Sofiane on : 2 nov. 2014 16:57:35
	 * @param periodeId
	 * @return
	 */
	public PeriodeDto findPeriode(Integer periodeId) {
		if (periodeId != null) {
			return periodeService.findById(periodeId);
		}
		return null;
	}

	/**
	 * [UtilBean.findAnneeAcademique] Method
	 * 
	 * @author MAKERRI Sofiane on : 2 nov. 2014 10:57:20
	 * @param anneeAcademiqueId
	 * @return
	 */
	public AnneeAcademiqueDto findAnneeAcademique(Integer anneeAcademiqueId) {
		if (anneeAcademiqueId != null) {
			return anneeAcademiqueService.findById(anneeAcademiqueId);
		}
		return null;
	}

	/**
	 * [UtilBean.sessionBean] Getter
	 * 
	 * @author MAKERRI Sofiane on : 15 oct. 2014 08:40:20
	 * @return the sessionBean
	 */
	public SessionBean getSessionBean() {
		return sessionBean;
	}

	/**
	 * [UtilBean.sessionBean] Setter
	 * 
	 * @author MAKERRI Sofiane on : 15 oct. 2014 08:40:20
	 * @param sessionBean
	 *            the sessionBean to set
	 */
	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}

	/**
	 * [UtilBean.ouvertureOffreFormationService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 15 oct. 2014 08:40:20
	 * @return the ouvertureOffreFormationService
	 */
	public OuvertureOffreFormationService getOuvertureOffreFormationService() {
		return ouvertureOffreFormationService;
	}

	/**
	 * [UtilBean.ouvertureOffreFormationService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 15 oct. 2014 08:40:20
	 * @param ouvertureOffreFormationService
	 *            the ouvertureOffreFormationService to set
	 */
	public void setOuvertureOffreFormationService(
			OuvertureOffreFormationService ouvertureOffreFormationService) {
		this.ouvertureOffreFormationService = ouvertureOffreFormationService;
	}

	/**
	 * [UtilBean.repartitionUeService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 15 oct. 2014 08:40:20
	 * @return the repartitionUeService
	 */
	public RepartitionUeService getRepartitionUeService() {
		return repartitionUeService;
	}

	/**
	 * [UtilBean.repartitionUeService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 15 oct. 2014 08:40:20
	 * @param repartitionUeService
	 *            the repartitionUeService to set
	 */
	public void setRepartitionUeService(
			RepartitionUeService repartitionUeService) {
		this.repartitionUeService = repartitionUeService;
	}

	/**
	 * [UtilBean.rattachementMcService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 15 oct. 2014 08:40:20
	 * @return the rattachementMcService
	 */
	public RattachementMcService getRattachementMcService() {
		return rattachementMcService;
	}

	/**
	 * [UtilBean.rattachementMcService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 15 oct. 2014 08:40:20
	 * @param rattachementMcService
	 *            the rattachementMcService to set
	 */
	public void setRattachementMcService(
			RattachementMcService rattachementMcService) {
		this.rattachementMcService = rattachementMcService;
	}

	/**
	 * [UtilBean.niveauService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 15 oct. 2014 08:42:54
	 * @return the niveauService
	 */
	public NiveauService getNiveauService() {
		return niveauService;
	}

	/**
	 * [UtilBean.niveauService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 15 oct. 2014 08:42:54
	 * @param niveauService
	 *            the niveauService to set
	 */
	public void setNiveauService(NiveauService niveauService) {
		this.niveauService = niveauService;
	}

	/**
	 * [UtilBean.periodeService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 15 oct. 2014 08:42:54
	 * @return the periodeService
	 */
	public PeriodeService getPeriodeService() {
		return periodeService;
	}

	/**
	 * [UtilBean.periodeService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 15 oct. 2014 08:42:54
	 * @param periodeService
	 *            the periodeService to set
	 */
	public void setPeriodeService(PeriodeService periodeService) {
		this.periodeService = periodeService;
	}

	/**
	 * [UtilBean.anneeAcademiqueService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 15 oct. 2014 09:20:31
	 * @return the anneeAcademiqueService
	 */
	public AnneeAcademiqueService getAnneeAcademiqueService() {
		return anneeAcademiqueService;
	}

	/**
	 * [UtilBean.anneeAcademiqueService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 15 oct. 2014 09:20:31
	 * @param anneeAcademiqueService
	 *            the anneeAcademiqueService to set
	 */
	public void setAnneeAcademiqueService(
			AnneeAcademiqueService anneeAcademiqueService) {
		this.anneeAcademiqueService = anneeAcademiqueService;
	}

	/**
	 * [UtilBean.atomePedagogiqueService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 15 oct. 2014 10:42:04
	 * @return the atomePedagogiqueService
	 */
	public AtomePedagogiqueService getAtomePedagogiqueService() {
		return atomePedagogiqueService;
	}

	/**
	 * [UtilBean.atomePedagogiqueService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 15 oct. 2014 10:42:04
	 * @param atomePedagogiqueService
	 *            the atomePedagogiqueService to set
	 */
	public void setAtomePedagogiqueService(
			AtomePedagogiqueService atomePedagogiqueService) {
		this.atomePedagogiqueService = atomePedagogiqueService;
	}

	/**
	 * [UtilBean.associationGroupePedagogiqueService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 15 oct. 2014 10:42:04
	 * @return the associationGroupePedagogiqueService
	 */
	public AssociationGroupePedagogiqueService getAssociationGroupePedagogiqueService() {
		return associationGroupePedagogiqueService;
	}

	/**
	 * [UtilBean.associationGroupePedagogiqueService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 15 oct. 2014 10:42:04
	 * @param associationGroupePedagogiqueService
	 *            the associationGroupePedagogiqueService to set
	 */
	public void setAssociationGroupePedagogiqueService(
			AssociationGroupePedagogiqueService associationGroupePedagogiqueService) {
		this.associationGroupePedagogiqueService = associationGroupePedagogiqueService;
	}

	/**
	 * [UtilBean.examenSessionService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 oct. 2014 12:09:35
	 * @return the examenSessionService
	 */
	public ExamenSessionService getExamenSessionService() {
		return examenSessionService;
	}

	/**
	 * [UtilBean.examenSessionService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 oct. 2014 12:09:35
	 * @param examenSessionService
	 *            the examenSessionService to set
	 */
	public void setExamenSessionService(
			ExamenSessionService examenSessionService) {
		this.examenSessionService = examenSessionService;
	}

	/**
	 * [UtilBean.planningSessionService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 oct. 2014 12:09:35
	 * @return the planningSessionService
	 */
	public PlanningSessionService getPlanningSessionService() {
		return planningSessionService;
	}

	/**
	 * [UtilBean.planningSessionService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 oct. 2014 12:09:35
	 * @param planningSessionService
	 *            the planningSessionService to set
	 */
	public void setPlanningSessionService(
			PlanningSessionService planningSessionService) {
		this.planningSessionService = planningSessionService;
	}

	/**
	 * [UtilBean.nomenclatureService] Getter
	 * 
	 * @author BELDI Jamel on : 19 oct. 2014 10:34:22
	 * @return the nomenclatureService
	 */
	public NomenclatureService getNomenclatureService() {
		return nomenclatureService;
	}

	/**
	 * [UtilBean.nomenclatureService] Setter
	 * 
	 * @author BELDI Jamel on : 19 oct. 2014 10:34:22
	 * @param nomenclatureService
	 *            the nomenclatureService to set
	 */
	public void setNomenclatureService(NomenclatureService nomenclatureService) {
		this.nomenclatureService = nomenclatureService;
	}

	/**
	 * [UtilBean.mentionService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 23 oct. 2014 13:45:22
	 * @return the mentionService
	 */
	public MentionService getMentionService() {
		return mentionService;
	}

	/**
	 * [UtilBean.mentionService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 23 oct. 2014 13:45:22
	 * @param mentionService
	 *            the mentionService to set
	 */
	public void setMentionService(MentionService mentionService) {
		this.mentionService = mentionService;
	}

	/**
	 * [UtilBean.groupePedagogiqueService] Getter
	 * 
	 * @author BELDI Jamel on : 5 nov. 2014 17:38:45
	 * @return the groupePedagogiqueService
	 */
	public GroupePedagogiqueService getGroupePedagogiqueService() {
		return groupePedagogiqueService;
	}

	/**
	 * [UtilBean.groupePedagogiqueService] Setter
	 * 
	 * @author BELDI Jamel on : 5 nov. 2014 17:38:45
	 * @param groupePedagogiqueService
	 *            the groupePedagogiqueService to set
	 */
	public void setGroupePedagogiqueService(
			GroupePedagogiqueService groupePedagogiqueService) {
		this.groupePedagogiqueService = groupePedagogiqueService;
	}

	/**
	 * [UtilBean.situationService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 nov. 2014 09:47:53
	 * @return the situationService
	 */
	public SituationService getSituationService() {
		return situationService;
	}

	/**
	 * [UtilBean.situationService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 nov. 2014 09:47:53
	 * @param situationService
	 *            the situationService to set
	 */
	public void setSituationService(SituationService situationService) {
		this.situationService = situationService;
	}

	/**
	 * @param note
	 * @return
	 */
	public String formatNote(double note) {
		return Utility.formatNoteWith2Position(note);
	}

	/**
	 * [UtilBean.coefficientExamenService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 6 janv. 2015 09:27:55
	 * @return the coefficientExamenService
	 */
	public CoefficientExamenService getCoefficientExamenService() {
		return coefficientExamenService;
	}

	/**
	 * [UtilBean.coefficientExamenService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 6 janv. 2015 09:27:55
	 * @param coefficientExamenService
	 *            the coefficientExamenService to set
	 */
	public void setCoefficientExamenService(
			CoefficientExamenService coefficientExamenService) {
		this.coefficientExamenService = coefficientExamenService;
	}

	/**
	 * [UtilBean.affectationGroupePedagogiqueService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 27 janv. 2015 10:28:16
	 * @return the affectationGroupePedagogiqueService
	 */
	public AffectationGroupePedagogiqueService getAffectationGroupePedagogiqueService() {
		return affectationGroupePedagogiqueService;
	}

	/**
	 * [UtilBean.affectationGroupePedagogiqueService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 27 janv. 2015 10:28:16
	 * @param affectationGroupePedagogiqueService
	 *            the affectationGroupePedagogiqueService to set
	 */
	public void setAffectationGroupePedagogiqueService(
			AffectationGroupePedagogiqueService affectationGroupePedagogiqueService) {
		this.affectationGroupePedagogiqueService = affectationGroupePedagogiqueService;
	}

	/**
	 * [UtilBean.offreFormationService] Getter 
	 * @author MAKERRI Sofiane on : 17 févr. 2015  13:46:37
	 * @return the offreFormationService
	 */
	public OffreFormationService getOffreFormationService() {
		return offreFormationService;
	}

	/**
	 * [UtilBean.offreFormationService] Setter 
	 * @author MAKERRI Sofiane on : 17 févr. 2015  13:46:37
	 * @param offreFormationService the offreFormationService to set
	 */
	public void setOffreFormationService(OffreFormationService offreFormationService) {
		this.offreFormationService = offreFormationService;
	}
	
	

}
