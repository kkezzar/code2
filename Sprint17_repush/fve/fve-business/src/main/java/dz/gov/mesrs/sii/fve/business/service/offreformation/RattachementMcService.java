package dz.gov.mesrs.sii.fve.business.service.offreformation;

import java.util.List;

import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.RattachementMcDto;

/**
 * 
 * 
 * @author Kheireddine OMRANI
 */
public interface RattachementMcService {

	public RattachementMcDto insertOrUpdate(RattachementMcDto rattachementMcDto);

	void remove(RattachementMcDto rattachement);
	
	public RattachementMcDto findById(Integer id);

	void remove(int ueId, int mcId);

	List<RattachementMcDto> find(Integer ueId, Integer mcId);
	
	List<RattachementMcDto> findOptionnelle(Integer ueId, Integer mcId, Boolean optionnelle);
	
	List<RattachementMcDto> findByOffreFormation(Integer offreFormationId);
	
	List<RattachementMcDto> findByOffreFormationAndPeriode(Integer offreFormationId, int idPeriode);
	
	RattachementMcDto findUnique(int ueId, int mcId);

}