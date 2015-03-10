package dz.gov.mesrs.sii.commons.data.dao.fve.cursus;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.fve.cursus.AttestationFinEtude;
/**
 * @author MESRS CCM
 * @version 1.0
 * @created 20-04-2014 16:44:07
 */
 

public interface AttestationFinEtudeDao {

	public  void persist(AttestationFinEtude transientInstance);

	public  void remove(AttestationFinEtude persistentInstance);

	public  AttestationFinEtude merge(AttestationFinEtude detachedInstance);

	public  AttestationFinEtude findById(int id);
		
	public  List<AttestationFinEtude> findAll();
		
	public  List<AttestationFinEtude> findAdvanced(AttestationFinEtude searchBo);
		
	public  AttestationFinEtude findByIdInscriptionAdministrative(int idInscriptionAdministrative);
	
	public List<AttestationFinEtude>  findToValidate(String codeEtab);
	
	public  AttestationFinEtude findByIdInscriptionAdministrativeAndPeriode(int idInscriptionAdministrative, int idPeriode);
	
	

}