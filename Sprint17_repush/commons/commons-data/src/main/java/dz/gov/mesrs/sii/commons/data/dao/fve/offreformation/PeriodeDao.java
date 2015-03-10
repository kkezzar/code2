package dz.gov.mesrs.sii.commons.data.dao.fve.offreformation;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.Periode;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */

public interface PeriodeDao {

	public  void persist(Periode transientInstance);

	public  void remove(int id);

	public  Periode merge(Periode detachedInstance);

	public  Periode findById(int id);
	
	public  List<Periode> findAll();
	
	public  List<Periode> findByLevelId(int idLevel);

	public  List<Periode> findByCycleId(int idCycle);
	
	public  List<Periode> findCurrentsByLevelId(int idLevel);
	
	public  List<Periode> findByPeriodiciteCode(String periodiciteCode);
	
	public  Periode findByLevelIdByRefPeriodeCode(int idLevel, String refCodePeriode);
	
	public  Periode findByLevelIdByIdNcPeriode(Integer idLevel, String idNcPeriode);
	
}