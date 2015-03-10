package dz.gov.mesrs.sii.commons.data.dao.referentiel;

import java.util.Date;
import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.referentiel.RefHistorique;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 20-04-2014 16:44:07
 */
 

public interface RefHistoriqueDao {

	public  void persist(RefHistorique transientInstance);

	public  void remove(RefHistorique persistentInstance);

	public  RefHistorique merge(RefHistorique detachedInstance);

	public  RefHistorique findById(int id);
		
	public  List<RefHistorique> findAll();
	
	public  List<RefHistorique> findAdvanced(RefHistorique refHistorique);
	
	public  List<RefHistorique> findByPeriode(RefHistorique refHistorique, Date dateDebut, Date dateFin);

}