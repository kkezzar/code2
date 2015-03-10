package dz.gov.mesrs.sii.fve.business.service.offreformation;

import java.util.List;

import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.RepartitionUeDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.UniteEnseignementDto;
/**
 * 
 * @author Kheireddine OMRANI
 * 
 */
public interface RepartitionUeService {

	public RepartitionUeDto insertOrUpdate(RepartitionUeDto repartitionUeDto);

	public void remove(Integer id);

	public void remove(int parcoursId, String refCodeSemestre, int ueId);

	public List<RepartitionUeDto> find(String refCodeSemestre, Integer parcoursId,
			Integer ueId);
	
	public List<UniteEnseignementDto> findUeOof(Integer oofId, String refCodeSemestre);
		
	public List<UniteEnseignementDto> findUeOofAndPeriode(Integer oofId, int idPeriode);

	public List<UniteEnseignementDto> findUeOof(Integer oofId, Integer periodeId);
	
	public List<RepartitionUeDto> findReparationUe(Integer oofId, Integer periodeId);
	
	public List<RepartitionUeDto> findByParcoursAndPeriode(int parcoursId, int idPeriode);

	public RepartitionUeDto findById(int id);
	
	public  List<UniteEnseignementDto> getAvailableUesPeriod(int parcoursId, int idPeriode);

}
