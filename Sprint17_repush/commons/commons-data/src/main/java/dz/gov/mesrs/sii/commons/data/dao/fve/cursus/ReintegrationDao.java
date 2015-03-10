package dz.gov.mesrs.sii.commons.data.dao.fve.cursus;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.fve.cursus.Reintegration;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.OuvertureOffreFormation;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 019-07-2014 16:44:07
 */

public interface ReintegrationDao {

	public void persist(Reintegration transientInstance);

	public void remove(Reintegration persistentInstance);

	public Reintegration merge(Reintegration detachedInstance);

	public Reintegration findById(int id);

	public List<Reintegration> findByQuery(String query);

	public List<Reintegration> findAll();

	public List<Reintegration> findByCode(String codeNat);

	public List<Reintegration> findAdvanced(Reintegration searchBo);

	public Reintegration findByIdExclusion(int idDossier);

	public List<Reintegration> findGeneric(String value);

	public List<Reintegration> findReintegrationsByIdExclusion(int idExclusion);

	/**
	 * @author Mounir.MESSAOUDI on : 21 oct. 2014 11:38:42
	 * @param reintegrationSearchBo
	 * @param refCodeEtablissement
	 * @param searchOuverturesOffreFormationBos
	 * @return
	 */
	List<Reintegration> findAdvanced(Reintegration reintegrationSearchBo,
			String refCodeEtablissement,
			List<OuvertureOffreFormation> searchOuverturesOffreFormationBos);

}