package dz.gov.mesrs.sii.commons.data.dao.fve.cursus;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.bpm.SituationEntite;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.DossierTransfert;

/**
 * @author BELDI Jamel on : 8 juin 2014 12:28:41
 */
public interface DossierTransfertDao {

	public void persist(DossierTransfert transientInstance);

	public void remove(DossierTransfert persistentInstance);

	public DossierTransfert merge(DossierTransfert detachedInstance);

	public DossierTransfert findById(int id);

	public List<DossierTransfert> findByQuery(String query);

	public List<DossierTransfert> findAll();

	public List<DossierTransfert> findAdvanced(DossierTransfert searchBo);

	/**
	 * Recherche avanncee par situation
	 * 
	 * @author Mounir.MESSAOUDI on : 2 sept. 2014 10:01:50
	 * @param searchBo
	 * @param searchSitutationEntiteBos
	 * @return
	 */
	public List<DossierTransfert> findAdvanced(DossierTransfert searchBo,
			List<SituationEntite> searchSitutationEntiteBos);

	/**
	 * Recherche le nombre de resultats
	 * 
	 * @author Mounir.MESSAOUDI on : 10 sept. 2014 12:49:55
	 * @param searchDto
	 * @return
	 */
	public Long findCountDossiersTransfert(DossierTransfert searchBo);

	/**
	 * Recherche avanncee par situation
	 * 
	 * @author Mounir.MESSAOUDI on : 15 sept. 2014 12:42:12
	 * @param searchBo
	 * @param typesDossiersTransfert
	 * @param searchSitutationEntiteBos
	 * @return
	 */
	public List<DossierTransfert> findAdvanced(
			DossierTransfert searchBo, List<String> typesDossiersTransfert,
			List<SituationEntite> searchSitutationEntiteBos);

}