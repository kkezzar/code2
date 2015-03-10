package dz.gov.mesrs.sii.commons.data.dao.fve.cursus;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.fve.cursus.CongeAcademique;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 20-04-2014 16:44:07
 */

public interface CongeAcademiqueDao {

	public void persist(CongeAcademique transientInstance);

	public void remove(CongeAcademique persistentInstance);

	public CongeAcademique merge(CongeAcademique detachedInstance);

	public CongeAcademique findById(int id);

	public List<CongeAcademique> findAll();

	public CongeAcademique findByIdDossier(int idDossier);

	/**
	 * @author Mounir.MESSAOUDI on : 26 oct. 2014 13:09:45
	 * @param diaId
	 * @return
	 */
	CongeAcademique findByIdDossierInscriptionAdministrative(Integer diaId);

}