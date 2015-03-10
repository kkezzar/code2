/**
 * [dz.gov.mesrs.sii.commons.data.dao.fve.cursus.SignatureDiplomeFinEtudeDao.java] 
 * @author MAKERRI Sofiane on : 18 févr. 2015  09:58:36
 */
package dz.gov.mesrs.sii.commons.data.dao.fve.cursus;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.fve.cursus.SignatureDiplomeFinEtude;

/**
 * @author MAKERRI Sofiane on : 18 févr. 2015 09:58:36
 */

public interface SignatureDiplomeFinEtudeDao {

	public void persist(SignatureDiplomeFinEtude transientInstance);

	public void remove(SignatureDiplomeFinEtude persistentInstance);

	public SignatureDiplomeFinEtude merge(SignatureDiplomeFinEtude detachedInstance);

	public SignatureDiplomeFinEtude findById(int id);

	public List<SignatureDiplomeFinEtude> findAll();
	
	public SignatureDiplomeFinEtude findUnique(int ncSignatureDiplomeId, long diplomeFineEtudeId, boolean attestation);

}
