package dz.gov.mesrs.sii.commons.data.dao.bpm;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.bpm.TypeDemande;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 

public interface TypeDemandeDao {

	public  void persist(TypeDemande transientInstance);

	public  void remove(TypeDemande persistentInstance);

	public  TypeDemande merge(TypeDemande detachedInstance);

	public  TypeDemande findById(int id);
	
	public  List<TypeDemande> findByQuery(String query) ;
	
	public  List<TypeDemande> findAll();
	
	public  TypeDemande findByCode(String codeType);


}