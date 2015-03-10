package dz.gov.mesrs.sii.fve.business.service.bac;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.fve.bac.CapaciteEtab;
import dz.gov.mesrs.sii.fve.business.model.dto.bac.CapaciteEtabDto;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 20-04-2014 16:44:07
 */

public interface CapaciteEtabService {

	public CapaciteEtabDto insertOrUpdate(CapaciteEtabDto capaciteEtabDto);

	public void remove(CapaciteEtabDto capaciteEtabDto);

	public CapaciteEtabDto findById(int id);

	public List<CapaciteEtabDto> findAll();

	/**
	 * Renvoi la liste des capcistes par annee academique
	 * 
	 * @author Mounir.MESSAOUDI on : 19 ao�t 2014 15:25:30
	 * @param idAnneeAcademique
	 * @return
	 */
	public List<CapaciteEtabDto> findByIdAnneeAcademique(int idAnneeAcademique);

	/**
	 * Recherche avancee
	 * 
	 * @author Mounir.MESSAOUDI on : 2 sept. 2014 09:28:18
	 * @param searchDto
	 * @return
	 */
	public List<CapaciteEtabDto> findAdvanced(CapaciteEtabDto searchDto);

	public CapaciteEtabDto map(CapaciteEtab capaciteEtab);

}