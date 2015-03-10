package dz.gov.mesrs.sii.commons.data.dao.fve.offreformation;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.PeriodeParam;

public interface PeriodeParamDao {

	public  void persist(PeriodeParam transientInstance);

	public  void remove(int id);

	public  PeriodeParam merge(PeriodeParam detachedInstance);

	public  PeriodeParam findById(int id);
	
	public  List<PeriodeParam> findAll();
	
	public  List<PeriodeParam> findByPeriodByYear(int idPeriod, int idYear);
	
	public  List<PeriodeParam> findByPeriodiciteByYear(int idYear, String codePeriodicite);
	
	public  PeriodeParam findByCodeByPeriodByYear(String codeParam, int idYear, int idPeriod);
	
	public  PeriodeParam findByCodeByPeriodiciteByYear(String codeParam, int idYear, String codePeriodicite);
	
	
}