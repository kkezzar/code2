package dz.gov.mesrs.sii.fve.business.service.offreformation;

import java.util.List;

import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.NiveauDto;

/**
 * @author Rafik LAIB on : 5 avr. 2014 22:07:59
 */
public interface NiveauService {

	public NiveauDto insertOrUpdate(NiveauDto niveauDto);

	public void remove(int idNiveau);

	public NiveauDto findById(int id);

	public List<NiveauDto> findAll();

	public List<NiveauDto> findByCycleId(int idCycle);

	public List<NiveauDto> findByCycleCode(String codeCycle);

	/**
	 * Cette fonction renvoi le prochain niveau du niveau niveauDto
	 * 
	 * @author Mounir.MESSAOUDI on : 26 oct. 2014 15:06:20
	 * @param niveauDto
	 * @return
	 */
	public NiveauDto findProchainNiveauOf(NiveauDto niveauDto);

	/**
	 * Cette fonction renvoi le premier niveau dans un cycle
	 * 
	 * @author Mounir.MESSAOUDI on : 4 nov. 2014 07:59:23
	 * @param cycleId
	 * @return
	 */
	public NiveauDto findPremierNiveauOf(Integer cycleId);
	
	public NiveauDto findLastNiveauOf(Integer cycleId);
	

}