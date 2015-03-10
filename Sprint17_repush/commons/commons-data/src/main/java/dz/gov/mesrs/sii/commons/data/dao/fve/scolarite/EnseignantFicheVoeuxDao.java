/**
 * [dz.gov.mesrs.sii.fve.business.persistence.scolarite.EnseignantFicheVoeuxDao.java] 
 * @author rlaib on : 12 oct. 2014  15:48:20
 */
package dz.gov.mesrs.sii.commons.data.dao.fve.scolarite;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.fve.scolarite.EnseignantFicheVoeux;
import dz.gov.mesrs.sii.commons.data.model.grh.DossierEmploye;

/**
 * @author rlaib  on : 12 oct. 2014  15:48:20
 */
public interface EnseignantFicheVoeuxDao extends GenericFveDao<EnseignantFicheVoeux> {

	public List<EnseignantFicheVoeux> findByEnseignantId( int idAnnee, int idEnseignant);
	
	public List<EnseignantFicheVoeux> findByEnseignantIdBySituationId(int idAnnee, int idEnseignant, int idSituation);
	
	public List<EnseignantFicheVoeux> findFichesByEtabByUserByOfByPeriodeByYear(Integer idEtab, Long idUser,Integer idAnnee, Integer idNcPeriode, boolean toSearch);
	
	public List<EnseignantFicheVoeux> findFichesVoeuxByEtabBySituation(Integer idEtab, Integer idAnnee, Integer idSituation);
	
	public List<EnseignantFicheVoeux> findAdvanced(EnseignantFicheVoeux enseignantFicheVoeux);
	
	public DossierEmploye getEnseignantByIdUser(Integer idUser);
	
	public DossierEmploye getEnseignantById(Long id);
	
	public List<DossierEmploye> getAllEnseignants();
	
	public List<DossierEmploye> searchEnseignants(Integer idEtab, String query);
}
