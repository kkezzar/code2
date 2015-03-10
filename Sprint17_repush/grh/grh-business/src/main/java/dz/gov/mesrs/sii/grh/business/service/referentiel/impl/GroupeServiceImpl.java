package dz.gov.mesrs.sii.grh.business.service.referentiel.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.grh.business.model.dto.CategorieProfessionnelleDto;
import dz.gov.mesrs.sii.grh.business.model.dto.GradeDto;
import dz.gov.mesrs.sii.grh.business.model.dto.GroupeDto;
import dz.gov.mesrs.sii.grh.business.service.referentiel.CategorieProfessionnelleService;
import dz.gov.mesrs.sii.grh.business.service.referentiel.GroupeService;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;

@Service("groupeService")
@Transactional(readOnly = true)
public class GroupeServiceImpl implements GroupeService {

	private final static Logger logger = LoggerFactory.getLogger(GroupeServiceImpl.class.getName());

	@Autowired
	private CategorieProfessionnelleService categorieProfessionnelleService;

	@Override
	public List<GroupeDto> findAll() {
		logger.debug("Service for all groupes");
		
		List<CategorieProfessionnelleDto> categorieProfessionnelleDtos = categorieProfessionnelleService.findAll();
		List<GroupeDto> groupeDtos = getGroupeList(categorieProfessionnelleDtos);
		
		return groupeDtos;
	}
	
	@Override
	public List<GroupeDto> findAllByStatutId(Integer selectedStatutId) {
		List<CategorieProfessionnelleDto> categorieProfessionnelleDtos = categorieProfessionnelleService.findAll();
		List<CategorieProfessionnelleDto> result =new ArrayList<>();
		for(CategorieProfessionnelleDto dto : categorieProfessionnelleDtos){
			List<GradeDto> grades = dto.getGradeDtos();
			if(grades != null){
				for(GradeDto gradeDto : grades){
					if(selectedStatutId.equals(gradeDto.getCorpsDto().getStatut().getId()) && !result.contains(dto)){
						result.add(dto);
						continue;	
					}
				}
			}
		}
		return getGroupeList(result);
	}
	
	private List<GroupeDto> getGroupeList(List<CategorieProfessionnelleDto> categorieProfessionnelleDtos) {
		List<GroupeDto> groupeDtos = new ArrayList<>();
		for (CategorieProfessionnelleDto categorieProfessionnelleDto : categorieProfessionnelleDtos) {
			NomenclatureDto nomenclatureGroupDto = categorieProfessionnelleDto.getNomenclatureByGroupeDto();
			GroupeDto groupeDto = new GroupeDto(nomenclatureGroupDto);
			if (groupeDtos.contains(groupeDto)) {
				if(Boolean.TRUE == categorieProfessionnelleDto.getHorsCategorie()){
					groupeDtos.get(groupeDtos.indexOf(groupeDto)).getHorsCategorieProfessionnelleDtos().add(categorieProfessionnelleDto);
				}else{
					groupeDtos.get(groupeDtos.indexOf(groupeDto)).getCategorieProfessionnelleDtos()
					.add(categorieProfessionnelleDto);
				}
				
			} else {
				if(Boolean.TRUE == categorieProfessionnelleDto.getHorsCategorie()){
					groupeDto.getHorsCategorieProfessionnelleDtos().add(categorieProfessionnelleDto);	
				}else{
					groupeDto.getCategorieProfessionnelleDtos().add(categorieProfessionnelleDto);
				}
				
				groupeDtos.add(groupeDto);
			}
		}
		logger.debug("found {} groupes ", new Object[] { groupeDtos.size() });
		return groupeDtos;
	}

	

	public void setCategorieProfessionnelleService(CategorieProfessionnelleService categorieProfessionnelleService) {
		this.categorieProfessionnelleService = categorieProfessionnelleService;
	}

	

}
