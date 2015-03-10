package dz.gov.mesrs.sii.commons.data.dao.recherche;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.dao.fve.scolarite.GenericFveDao;
import dz.gov.mesrs.sii.commons.data.model.bpm.Etape;
import dz.gov.mesrs.sii.commons.data.model.recherche.EtapeValidation;

/**
 * @author rlaib  on : 16 d�c. 2014  13:58:17
 */
public interface EtapeValidationDao extends GenericFveDao<EtapeValidation> {

	List<Etape> findListEtapesByIdProcesus(int processusId);

	void removeListEtapeValidationByProjet(Long selectedProjet);

	List<EtapeValidation> findListEtapeValidationByProjet(Long selectedProjet);

	EtapeValidation findByIdValidation(Long validationId);

	boolean findNonAcceptationValidation(Long selectedProjet,String codeValidation);

	
}
