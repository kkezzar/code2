package dz.gov.mesrs.sii.fve.business.service.bac;

import java.util.List;

import dz.gov.mesrs.sii.fve.business.model.dto.bac.AccessWilayaDto;
import dz.gov.mesrs.sii.fve.business.model.dto.bac.DroitAccessWilayaDto;

/**
 * @author Mounir.messaoudi
 * @version 1.0
 * @created 20-04-2014 16:44:07
 */

public interface AccessWilayaService {

	public AccessWilayaDto insertOrUpdate(AccessWilayaDto accessWilayaDto);

	public void remove(AccessWilayaDto accessWilayaDto);

	public AccessWilayaDto findById(int id);

	public List<AccessWilayaDto> findAll();

	/**
	 * Renvoi la liste des access wilayas par annee academique
	 * 
	 * @author Mounir.MESSAOUDI on : 19 aout 2014 15:25:30
	 * @param idAnneeAcademique
	 * @return
	 */
	public List<AccessWilayaDto> findByIdAnneeAcademique(int idAnneeAcademique);

	/**
	 * Recherche avancee
	 * 
	 * @author Mounir.MESSAOUDI on : 2 sept. 2014 09:28:18
	 * @param searchBo
	 * @return
	 */
	public List<AccessWilayaDto> findAdvanced(AccessWilayaDto searchBo);

	public void remove(DroitAccessWilayaDto droitAccessWilayaDto);
}