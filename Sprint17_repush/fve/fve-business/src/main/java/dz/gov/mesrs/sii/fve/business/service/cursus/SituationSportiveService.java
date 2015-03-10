package dz.gov.mesrs.sii.fve.business.service.cursus;

import java.util.Date;
import java.util.List;

import dz.gov.mesrs.sii.fve.business.model.dto.cursus.SituationSportiveDto;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */

public interface SituationSportiveService {

	public SituationSportiveDto insertOrUpdate(
			SituationSportiveDto situationSportiveDto);

	public void remove(SituationSportiveDto situationSportiveDto);

	public SituationSportiveDto findById(int id);

	public List<SituationSportiveDto> findByQuery(String query);

	public List<SituationSportiveDto> findAll();

	public List<SituationSportiveDto> findByIdDossier(Integer id);

	public SituationSportiveDto findSituationSportive(int id, Date dateDebut,
			Date dateFin, String refCodeType);

}