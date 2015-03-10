package dz.gov.mesrs.sii.grh.web.jsf.bean.referentiel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import dz.gov.mesrs.sii.grh.business.model.dto.CategorieProfessionnelleDto;
import dz.gov.mesrs.sii.grh.business.model.dto.CorpsDto;
import dz.gov.mesrs.sii.grh.business.model.dto.GradeDto;
import dz.gov.mesrs.sii.grh.business.model.dto.GroupeDto;
import dz.gov.mesrs.sii.grh.business.model.dto.PosteSuperieureDto;
import dz.gov.mesrs.sii.grh.business.model.dto.StatutDto;
import dz.gov.mesrs.sii.grh.web.jsf.bean.BaseBean;

@ManagedBean(name = "grilleIndiciaireMBean")
@ViewScoped
public class GrilleIndiciaireMBean extends BaseBean {

	private static final long serialVersionUID = 2951133864084444228L;

	private List<GroupeDto> groupeDtos;
	private List<PosteSuperieureDto> posteSuperieureDtos;
	private List<SelectItem> statuts;
	private StatutDto statutDto;
	private Integer selectedStatutId;
	private boolean renderDetailStatut;
	private Map<CorpsDto, List<CategorieProfessionnelleDto>> corpsMap;

	private boolean grilleContainsHorsCategories = false;

	@PostConstruct
	public void init() {

		initStatutList();

	}

	private void initGroupeList() {
		groupeDtos = serviceLocator.getGroupeService().findAllByStatutId(selectedStatutId);
		if (groupeDtos != null && groupeDtos.size() > 0) {
			for (GroupeDto groupeDto : groupeDtos) {
				if (!groupeDto.getHorsCategorieProfessionnelleDtos().isEmpty()) {
					grilleContainsHorsCategories = true;
					break;
				}
			}
		}
	}

	private void initStatutList() {
		List<StatutDto> dtos = this.serviceLocator.getStatutService().getListValideStatuts();
		statuts = new ArrayList<>();
		for (StatutDto dto : dtos) {
			statuts.add(new SelectItem(dto.getId(), dto.getNumero() + " " + dto.getAnnee()));
		}
	}

	public void onStatutSelected() {
		// TODO
		if (selectedStatutId == null) {
			groupeDtos = null;
		} else {
			statutDto = serviceLocator.getStatutService().findUniqueByExample(new StatutDto(selectedStatutId));
			initGroupeList();
			intiPosteSuperieursList();
			initCorpsMap();
		}
	}

	private void initCorpsMap() {
		corpsMap = new HashMap<>();
		CorpsDto corpsDto = new CorpsDto();
		StatutDto statutDto = new StatutDto(selectedStatutId);
		corpsDto.setStatut(statutDto);

		List<CorpsDto> corpsDtos = serviceLocator.getCorpsService().findAllByExample(corpsDto);
		if (corpsDtos != null) {
			for (CorpsDto dto : corpsDtos) {
				List<CategorieProfessionnelleDto> categorieProfessionnelleDtos = new ArrayList<>();
				for (GradeDto gradeDto : dto.getGrades()) {
					if (categorieProfessionnelleDtos.contains(gradeDto.getCategorieProfessionnelleDto())) {
						continue;
					} else {
						categorieProfessionnelleDtos.add(gradeDto.getCategorieProfessionnelleDto());
					}
				}
				corpsMap.put(dto, categorieProfessionnelleDtos);
			}
		}

	}

	private void intiPosteSuperieursList() {
		posteSuperieureDtos = null;
		CorpsDto corpsDto = new CorpsDto();
		StatutDto statutDto = new StatutDto(selectedStatutId);
		corpsDto.setStatut(statutDto);
		PosteSuperieureDto posteSuperieureDto = new PosteSuperieureDto();
		posteSuperieureDto.setCorpsDto(corpsDto);
		posteSuperieureDtos = serviceLocator.getPosteSuperieureService().findAllByExample(posteSuperieureDto);
	}

	public List<GroupeDto> getGroupeDtos() {
		return groupeDtos;
	}

	public boolean isGrilleContainsHorsCategories() {
		return grilleContainsHorsCategories;
	}

	public List<SelectItem> getStatuts() {
		return statuts;
	}

	public Integer getSelectedStatutId() {
		return selectedStatutId;
	}

	public void setSelectedStatutId(Integer selectedStatutId) {
		this.selectedStatutId = selectedStatutId;
	}

	public boolean isRenderDetailStatut() {
		return renderDetailStatut;
	}

	public void setRenderDetailStatut(boolean renderDetailStatut) {
		this.renderDetailStatut = renderDetailStatut;
	}

	public List<PosteSuperieureDto> getPosteSuperieureDtos() {
		return posteSuperieureDtos;
	}

	public StatutDto getStatutDto() {
		return statutDto;
	}

	public Map<CorpsDto, List<CategorieProfessionnelleDto>> getCorpsMap() {
		return corpsMap;
	}

}
