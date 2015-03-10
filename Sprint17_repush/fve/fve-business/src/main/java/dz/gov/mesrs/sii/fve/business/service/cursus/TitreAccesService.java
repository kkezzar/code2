package dz.gov.mesrs.sii.fve.business.service.cursus;

import java.util.List;

import dz.gov.mesrs.sii.fve.business.model.dto.cursus.TitreAccesDto;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */

public interface TitreAccesService {

	public TitreAccesDto insertOrUpdate(TitreAccesDto titreAccesDto);

	public void remove(TitreAccesDto titreAccesDto);

	public TitreAccesDto findById(int id);

	public List<TitreAccesDto> findAll();

	public TitreAccesDto findByIdDossier(int idDossier);

}