package dz.gov.mesrs.sii.commons.data.dao.fve.cursus;


import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.fve.cursus.AbsenceMatiere;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 
    public interface AbsenceMatiereDao {

	public  void persist(AbsenceMatiere transientInstance);

	public  void remove(AbsenceMatiere persistentInstance);

	public  AbsenceMatiere merge(AbsenceMatiere detachedInstance);

	public  AbsenceMatiere findById(int id);
	
	public  List<AbsenceMatiere> findByQuery(String query) ;
	
	public  List<AbsenceMatiere> findAll();

	public  List<AbsenceMatiere> findByCode(String codeNat);
	
	public  List<AbsenceMatiere> findAdvanced(AbsenceMatiere searchBo);
	
	public AbsenceMatiere findByIdDossier(int idDossier);

	
}