package dz.gov.mesrs.sii.fve.business.service.cursus;

import java.util.Date;
import java.util.List;

import dz.gov.mesrs.sii.fve.business.model.dto.examen.ExamenSessionByDateDto;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.ExamenSessionDto;

/**
 * 
 * @author BELDI Jamel on : 17 sept. 2014 17:09:22
 */

public interface ExamenSessionService {

	public ExamenSessionDto insertOrUpdate(ExamenSessionDto examenSessionDto);

	public void remove(ExamenSessionDto examenSessionDto);

	public ExamenSessionDto findById(long id);

	public List<ExamenSessionDto> findAll();

	public List<ExamenSessionDto> findBySession(long idSession);

	public List<ExamenSessionDto> findAdvanced(ExamenSessionDto examenSessionDto);

	public List<ExamenSessionByDateDto> regroupeByDate(List<ExamenSessionDto> examensSessionList);

	public ExamenSessionDto verifyExistingExamen(long idSession, int idRMC, Date dateExamen, Date heureDebut,
	        String refTypeExamen);

	public List<ExamenSessionDto> findExamenByPlanning(Long planningId);

}