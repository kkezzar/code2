package dz.gov.mesrs.sii.commons.data.dao.fve.cursus;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.fve.cursus.PieceConstitutive;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 

public interface PieceConstitutiveDao {

	public  void persist(PieceConstitutive transientInstance);

	public  void remove(PieceConstitutive persistentInstance);

	public  PieceConstitutive merge(PieceConstitutive detachedInstance);

	public  PieceConstitutive findById(int id);
	
	public  List<PieceConstitutive> findByQuery(String query) ;
	
	public  List<PieceConstitutive> findAll();
	
	public  List<PieceConstitutive> findByIdDossier(int id);
	
	public PieceConstitutive findByIdDossierAndCodePiece(int id, Integer pieceId);
	
	public  boolean isInRequiredValid(Integer pieceId, Integer dossierId);
	
	public boolean isValid(int dossierId, Boolean es);

	public void flushAndClear();

}