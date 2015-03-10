package dz.gov.mesrs.sii.commons.data.dao.fve.cursus;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.fve.cursus.AttestationFinEtudeEdition;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 20-04-2014 16:44:07
 */
public interface AttestationFinEtudeEditionDao {

	public  void persist(AttestationFinEtudeEdition transientInstance);

	public  void remove(AttestationFinEtudeEdition persistentInstance);

	public  AttestationFinEtudeEdition merge(AttestationFinEtudeEdition detachedInstance);

	public  AttestationFinEtudeEdition findById(int id);
		
	public  List<AttestationFinEtudeEdition> findAll();
		
	public  List<AttestationFinEtudeEdition> findAdvanced(AttestationFinEtudeEdition searchBo);
		
	public  AttestationFinEtudeEdition findByIdInscriptionAdministrative(int idInscriptionAdministrative);
	
	public List<AttestationFinEtudeEdition>  findToValidate(String codeEtab);
	
	public  AttestationFinEtudeEdition findByIdInscriptionAdministrativeAndPeriode(int idInscriptionAdministrative, int idPeriode);

	public List<AttestationFinEtudeEdition> findEditionByIdAttestation(int idAttestation);
	

		
	

	
}