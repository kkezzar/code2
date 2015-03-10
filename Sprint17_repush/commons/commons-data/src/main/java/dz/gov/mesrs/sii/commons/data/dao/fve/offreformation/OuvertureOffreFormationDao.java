package dz.gov.mesrs.sii.commons.data.dao.fve.offreformation;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.OuvertureOffreFormation;

/**
 * @author BELDI Jamel on : 12 mai 2014 14:14:41
 */
public interface OuvertureOffreFormationDao {

	public void persist(OuvertureOffreFormation transientInstance);

	public void remove(OuvertureOffreFormation persistentInstance);

	public OuvertureOffreFormation merge(
			OuvertureOffreFormation detachedInstance);

	public OuvertureOffreFormation findById(int id);

	public List<OuvertureOffreFormation> findByQuery(String query);

	public List<OuvertureOffreFormation> findAll();

	public List<OuvertureOffreFormation> findAdvanced(
			OuvertureOffreFormation searchBo);

	public List<OuvertureOffreFormation> findAdvanced(
			String refCodeEtablissement, String refCodeSpecialite,
			Integer idAnneeAcademique, Boolean estOuverte); 

}