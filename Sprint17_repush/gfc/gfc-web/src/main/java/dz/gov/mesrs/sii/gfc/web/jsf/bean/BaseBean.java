package dz.gov.mesrs.sii.gfc.web.jsf.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.bean.ManagedProperty;
import javax.faces.model.SelectItem;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import dz.gov.mesrs.sii.commons.web.jsf.bean.CommonBaseBean;
import dz.gov.mesrs.sii.gfc.business.model.dto.AffectationEtabChapitreArticleDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.AffectationEtabStrAgentComptableDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.AgentComptableDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.ArticleDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.CategorieProfessionnelleDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.ChapitreDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.CompteDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.MouvementBudgetDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.OrdonnateurDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.PartieDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.RepartitionBudgetDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.SectionDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.SousSectionDto;
import dz.gov.mesrs.sii.gfc.business.model.dto.TitreDto;
import dz.gov.mesrs.sii.gfc.web.jsf.bean.locator.ServiceLocatorBean;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEtablissementDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefStructureDto;

/**
 * 
 * @author Salem
 * @version V1.0
 * @date 27-10-2014
 * @description classe abstraite pour les instanciations courantes
 * 
 */

public abstract class BaseBean extends CommonBaseBean implements Serializable {

	private static final long serialVersionUID = 1L;

	// the logger for this class
	protected final Log logger = LogFactory.getLog(this.getClass());

	// the service locator of the business services
	@ManagedProperty(value = "#{serviceLocatorBean}")
	protected ServiceLocatorBean serviceLocator;

	/**
	 * @author Salem
	 * @version 1.0
	 * @description Constructeur du bean de base
	 */
	public BaseBean() {

	}

	/**
	 * 
	 * @param serviceLocatorBean
	 */
	public void setServiceLocator(ServiceLocatorBean serviceLocatorBean) {
		this.serviceLocator = serviceLocatorBean;
	}

	public List<SelectItem> findNomenclatureList(String ncFiliereProfessionnelleName) {
		List<NomenclatureDto> dtos = serviceLocator.getNomenclatureService().findByName(ncFiliereProfessionnelleName);
		List<SelectItem> results = new ArrayList<>();
		if (dtos != null && !dtos.isEmpty()) {
			for (NomenclatureDto dto : dtos) {
				results.add(new SelectItem(dto.getId(), dto.getLibelleLongFr()));
			}
		}
		return results;
	}

	/**
	 * find all etablissements
	 * 
	 * @author Mounir.MESSAOUDI on : 8 déc. 2014 09:25:18
	 * @return
	 */
	public List<SelectItem> findListeEtablissements() {
		List<RefEtablissementDto> dtos = this.serviceLocator.getEtablissementService().findAll();
		List<SelectItem> result = new ArrayList<>();
		if (dtos != null && !dtos.isEmpty()) {
			for (RefEtablissementDto dto : dtos) {
				result.add(new SelectItem(dto.getId(), dto.getLlEtablissementLatin()));
			}

		}

		return result;
	}

	/**
	 * find structure list by etablissement
	 * 
	 * @author Mounir.MESSAOUDI on : 8 déc. 2014 09:27:09
	 * @param etablissement
	 * @return
	 */
	public List<SelectItem> findListeStructure(RefEtablissementDto etablissement) {
		RefEtablissementDto example = new RefEtablissementDto();
		// TODO to fix ! findAllByExample n'exist pas
		List<RefStructureDto> dtos = this.serviceLocator.getStructureService().findAll(etablissement.getId());
		List<SelectItem> result = new ArrayList<>();
		if (dtos != null && !dtos.isEmpty()) {
			for (RefStructureDto dto : dtos) {
				result.add(new SelectItem(dto.getId(), dto.getLlStructureLatin()));
			}
		}

		return result;
	}

	/**
	 * find all sections
	 * 
	 * @author Mounir.MESSAOUDI on : 8 déc. 2014 09:28:50
	 * @return
	 */
	public List<SelectItem> findListeSections() {
		List<SectionDto> dtos = this.serviceLocator.getSectionService().findAll();
		List<SelectItem> result = new ArrayList<>();
		if (dtos != null && !dtos.isEmpty()) {
			for (SectionDto dto : dtos) {
				result.add(new SelectItem(dto.getId(), dto.getIntituleFr()));
			}
		}

		return result;
	}

	/**
	 * find all chapitres
	 * 
	 * @author Mounir.MESSAOUDI on : 8 déc. 2014 09:28:50
	 * @return
	 */
	public List<SelectItem> findListeChapitres() {
		List<ChapitreDto> dtos = this.serviceLocator.getChapitreService().findAll();
		List<SelectItem> result = new ArrayList<>();
		if (dtos != null && !dtos.isEmpty()) {
			for (ChapitreDto dto : dtos) {
				result.add(new SelectItem(dto.getId(), dto.getIntituleFr()));
			}
		}

		return result;
	}

	/**
	 * find all chapitres
	 * 
	 * @author Mounir.MESSAOUDI on : 8 déc. 2014 09:28:50
	 * @return
	 */
	public List<SelectItem> findListeChapitres(SectionDto section) {
		ChapitreDto example = new ChapitreDto();
		example.setIdSection(section);
		List<ChapitreDto> dtos = this.serviceLocator.getChapitreService().findAllByExample(example);
		List<SelectItem> result = new ArrayList<>();
		if (dtos != null && !dtos.isEmpty()) {
			for (ChapitreDto dto : dtos) {
				result.add(new SelectItem(dto.getId(), dto.getIntituleFr()));
			}
		}

		return result;
	}

	/**
	 * find all chapitres
	 * 
	 * @author Mounir.MESSAOUDI on : 8 déc. 2014 09:28:50
	 * @return
	 */
	public List<SelectItem> findListeChapitres(SectionDto section, Boolean recetteType) {
		ChapitreDto example = new ChapitreDto();
		example.setRecetteType(recetteType);
		example.setIdSection(section);
		List<ChapitreDto> dtos = this.serviceLocator.getChapitreService().findAllByExample(example);
		List<SelectItem> result = new ArrayList<>();
		if (dtos != null && !dtos.isEmpty()) {
			for (ChapitreDto dto : dtos) {
				result.add(new SelectItem(dto.getId(), dto.getIntituleFr()));
			}
		}

		return result;
	}

	/**
	 * find article list by chapitre
	 * 
	 * @author Mounir.MESSAOUDI on : 8 déc. 2014 09:30:29
	 * @param chapitre
	 * @return
	 */
	public List<SelectItem> findListeArticle(ChapitreDto chapitre) {
		ArticleDto example = new ArticleDto();
		example.setChapitre(chapitre);
		List<ArticleDto> dtos = this.serviceLocator.getArticleService().findAllByExample(example);
		List<SelectItem> result = new ArrayList<>();
		if (dtos != null && !dtos.isEmpty()) {
			for (ArticleDto dto : dtos) {
				result.add(new SelectItem(dto.getId(), dto.getIntituleFr()));
			}
		}
		return result;
	}

	/**
	 * find sous sections list by section
	 */
	public List<SelectItem> findListeSousSection(SectionDto section) {
		SousSectionDto example = new SousSectionDto();
		example.setSection(section);
		List<SousSectionDto> dtos = this.serviceLocator.getSousSectionService().findAllByExample(example);
		List<SelectItem> result = new ArrayList<>();
		if (dtos != null && !dtos.isEmpty()) {
			for (SousSectionDto dto : dtos) {
				result.add(new SelectItem(dto.getId(), dto.getIntituleFr()));
			}
		}
		return result;
	}

	/**
	 * find titre list by section
	 */
	public List<SelectItem> findListeTitres(SectionDto section) {
		TitreDto example = new TitreDto();
		example.setSection(section);
		List<TitreDto> dtos = this.serviceLocator.getTitreService().findAllByExample(example);
		List<SelectItem> result = new ArrayList<>();
		if (dtos != null && !dtos.isEmpty()) {
			for (TitreDto dto : dtos) {
				result.add(new SelectItem(dto.getId(), dto.getIntituleFr()));
			}
		}
		return result;
	}

	/**
	 * find titre list by section
	 */
	public List<SelectItem> findListePartieByTitre(TitreDto titre) {
		PartieDto example = new PartieDto();
		example.setTitre(titre);
		List<PartieDto> dtos = this.serviceLocator.getPartieService().findAllByExample(example);
		List<SelectItem> result = new ArrayList<>();
		if (dtos != null && !dtos.isEmpty()) {
			for (PartieDto dto : dtos) {
				result.add(new SelectItem(dto.getId(), dto.getIntituleFr()));
			}
		}
		return result;
	}

	/**
	 * find titre list by sous section
	 */
	public List<SelectItem> findListeTitres(SousSectionDto sousSection) {
		TitreDto example = new TitreDto();
		example.setSousSection(sousSection);
		List<TitreDto> dtos = this.serviceLocator.getTitreService().findAllByExample(example);
		List<SelectItem> result = new ArrayList<>();
		if (dtos != null && !dtos.isEmpty()) {
			for (TitreDto dto : dtos) {
				result.add(new SelectItem(dto.getId(), dto.getIntituleFr()));
			}
		}
		return result;
	}

	/**
	 * list des ordonnateurs par etablissement
	 */
	public List<SelectItem> findListOrdonnateurs(RefEtablissementDto refEtablissementDto) {
		OrdonnateurDto example = new OrdonnateurDto();
		example.setEtablissement(refEtablissementDto);
		List<OrdonnateurDto> dtos = this.serviceLocator.getOrdonnateurService().findAllByExample(example);
		List<SelectItem> result = new ArrayList<>();
		if (dtos != null && !dtos.isEmpty()) {
			for (OrdonnateurDto dto : dtos) {
				result.add(new SelectItem(dto.getId(), dto.getCode() + " : " + dto.getRefIndividu().getNomLatin() + " "
						+ dto.getRefIndividu().getPrenomLatin()));
			}
		}

		return result;
	}

	/**
	 * list des agents comptable par etablissement
	 */
	public List<SelectItem> findListAgents(RefEtablissementDto refEtablissementDto, RefStructureDto structure) {
		AgentComptableDto example = new AgentComptableDto();
		AffectationEtabStrAgentComptableDto affectation = new AffectationEtabStrAgentComptableDto();
		affectation.setRefEtablissement(refEtablissementDto);
		affectation.setRefStructure(structure);
		List<AffectationEtabStrAgentComptableDto> affectationDtos = this.serviceLocator
				.getAffectationEtabStrAgentComptableService().findAllByExample(affectation);
	// List<OrdonnateurDto> dtos =
		// this.serviceLocator.getOrdonnateurService().findAllByExample(example);
		List<SelectItem> result = new ArrayList<>();
		if (affectationDtos != null && !affectationDtos.isEmpty()) {
			for (AffectationEtabStrAgentComptableDto dto : affectationDtos) {
				result.add(new SelectItem(dto.getAgentComptable().getId(), dto.getAgentComptable().getCode() + ": "
						+ dto.getAgentComptable().getRefIndividu().getNomLatin() + " "
						+ dto.getAgentComptable().getRefIndividu().getPrenomLatin()));
			}
		}

		return result;
	}

	/**
	 * list des comptes par agent et type
	 */
	public List<SelectItem> findListComptes(AgentComptableDto agentComptable, NomenclatureDto type) {

		CompteDto example = new CompteDto();
		example.setAgentComptable(agentComptable);
		example.setType(type);
		List<CompteDto> comptesDtos = this.serviceLocator.getCompteService().findAllByExample(example);
		List<SelectItem> result = new ArrayList<>();
		if (comptesDtos != null && !comptesDtos.isEmpty()) {
			for (CompteDto dto : comptesDtos) {
				result.add(new SelectItem(dto.getId(), dto.getNumero()));
			}
		}

		return result;
	}

	/**
	 * list des affectations par etablissement
	 */
	public List<AffectationEtabChapitreArticleDto> findListAffectations(RefEtablissementDto refEtablissementDto) {

		AffectationEtabChapitreArticleDto affectation = new AffectationEtabChapitreArticleDto();
		affectation.setRefEtablissement(refEtablissementDto);
		List<AffectationEtabChapitreArticleDto> affectationDtos = new ArrayList<AffectationEtabChapitreArticleDto>();
		affectationDtos = this.serviceLocator.getAffectationEtabChapitreArticleService().findAllByExample(affectation);

		return affectationDtos;
	}

	/**
	 * Renvoi la liste des chapitres affectes a l'etablissement
	 * refEtablissementDto
	 * 
	 * @param refEtablissementDto
	 * @return
	 */
	public List<SelectItem> findListAffectationsChapitres(RefEtablissementDto refEtablissementDto) {
		AffectationEtabChapitreArticleDto affectation = new AffectationEtabChapitreArticleDto();
		affectation.setRefEtablissement(refEtablissementDto);
		List<AffectationEtabChapitreArticleDto> affectationDtos = new ArrayList<AffectationEtabChapitreArticleDto>();
		affectationDtos = this.serviceLocator.getAffectationEtabChapitreArticleService().findAllByExample(affectation);
		Set<SelectItem> result = new HashSet<>();
		if (affectationDtos != null && !affectationDtos.isEmpty()) {
			for (AffectationEtabChapitreArticleDto dto : affectationDtos) {
				result.add(new SelectItem(dto.getChapitre().getId(), dto.getChapitre().getCode() + " - "
						+ dto.getChapitre().getIntituleFr()));
			}
		}
		return new ArrayList<SelectItem>(result);
	}

	/**
	 * Renvoi la liste des chapitres de la section section affectes a
	 * l'etablissement refEtablissementDto
	 *
	 * @param refEtablissementDto
	 * @param sectionDto
	 * @return
	 */
	public List<SelectItem> findListAffectationsChapitres(RefEtablissementDto refEtablissementDto,
			SectionDto sectionDto, Boolean recetteType) {
		AffectationEtabChapitreArticleDto affectation = new AffectationEtabChapitreArticleDto();
		ChapitreDto chapitre = new ChapitreDto();
		chapitre.setIdSection(sectionDto);
		chapitre.setRecetteType(recetteType);

		affectation.setChapitre(chapitre);
		affectation.setRefEtablissement(refEtablissementDto);
		List<AffectationEtabChapitreArticleDto> affectationDtos = new ArrayList<AffectationEtabChapitreArticleDto>();
		affectationDtos = this.serviceLocator.getAffectationEtabChapitreArticleService().findAllByExample(affectation);
		Set<SelectItem> result = new HashSet<>();
		if (affectationDtos != null && !affectationDtos.isEmpty()) {
			// supprimer les chapitres dupliques
			Map<Integer, ChapitreDto> map = new HashMap<Integer, ChapitreDto>();
			for (AffectationEtabChapitreArticleDto dto : affectationDtos) {
				map.put(dto.getChapitre().getId(), dto.getChapitre());
			}

			for (ChapitreDto chapitreDto : map.values()) {
				result.add(new SelectItem(chapitreDto.getId(), chapitreDto.getCode() + " - "
						+ chapitreDto.getIntituleFr()));
			}

		}
		return new ArrayList<SelectItem>(result);
	}
	
	/**
	 * list mouvement par structure 
	 */
	
	public List<MouvementBudgetDto> findListeMouvemennt( RefStructureDto structure,RepartitionBudgetDto repartitionBudgetDto) {
		MouvementBudgetDto example = new MouvementBudgetDto();
		
		example.setRepartitionBudget(repartitionBudgetDto);
		example.setRefStructure(structure);
		List<MouvementBudgetDto> mouvementDtos = this.serviceLocator.getMouvementBudgetService().findAllByExample(example);
	
	

		return mouvementDtos;
	}
	
	/**
	 * 
	 * @return categroies professionnel
	 */
	public List<SelectItem> findListeCategorie() {
		List<CategorieProfessionnelleDto> dtos = this.serviceLocator.getTarifMissionService().findListCategorieProf();
		List<SelectItem> result = new ArrayList<>();
		if (dtos != null && !dtos.isEmpty()) {
			for (CategorieProfessionnelleDto dto : dtos) {
				
				result.add(new SelectItem(dto.getId(), dto.getNomenclatureByCategorieDto().getLibelleLongFr()));
			}
		}

		return result;
	}

}
