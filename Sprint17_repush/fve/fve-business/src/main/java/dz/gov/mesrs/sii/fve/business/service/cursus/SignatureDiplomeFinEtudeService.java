/**
 * [dz.gov.mesrs.sii.fve.business.service.cursus.SignatureDiplomeFinEtudeService.java] 
 * @author MAKERRI Sofiane on : 18 févr. 2015  10:08:46
 */
package dz.gov.mesrs.sii.fve.business.service.cursus;

import java.util.List;

import dz.gov.mesrs.sii.fve.business.model.dto.cursus.SignatureDiplomeFinEtudeDto;

/**
 * @author MAKERRI Sofiane on : 18 févr. 2015 10:08:46
 */

public interface SignatureDiplomeFinEtudeService {
	
	public  SignatureDiplomeFinEtudeDto insertOrUpdate(
			SignatureDiplomeFinEtudeDto signatureDiplomeFinEtudeDto);

	public void remove(SignatureDiplomeFinEtudeDto signatureDiplomeFinEtudeDto);

	public SignatureDiplomeFinEtudeDto findById(int id);

	public List<SignatureDiplomeFinEtudeDto> findAll();
	
	public SignatureDiplomeFinEtudeDto findUnique(int ncSignatureDiplomeId, long diplomeFineEtudeId, boolean attestation);

}
