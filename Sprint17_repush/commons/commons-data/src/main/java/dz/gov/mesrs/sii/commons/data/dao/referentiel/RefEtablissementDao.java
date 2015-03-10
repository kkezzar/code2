package dz.gov.mesrs.sii.commons.data.dao.referentiel;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.referentiel.RefEtablissement;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */

public interface RefEtablissementDao {

	public void persist(RefEtablissement transientInstance);

	public void remove(RefEtablissement persistentInstance);

	public RefEtablissement merge(RefEtablissement detachedInstance);

	public RefEtablissement findById(int id);

	public List<RefEtablissement> findByQuery(String query);

	public List<RefEtablissement> findAll();

	public List<RefEtablissement> findGeneric(String value);

	public List<RefEtablissement> findAdvanced(RefEtablissement refEtablissement);

	public RefEtablissement findByIdentifiant(String identifiant);
	
	public RefEtablissement findByLlLatin(String llLatin);

	public RefEtablissement findByLlArabe(String llArabe);

	public RefEtablissement findByLcLatin(String lcLatin);

	public RefEtablissement findByLcArabe(String lcArabe);
	
	public Integer findLastOrder(String prefix, int orderLength);

}