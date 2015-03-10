package dz.gov.mesrs.sii.commons.data.dao.fve.offreformation;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.ParamType;

public interface ParamTypeDao {

	public  List<ParamType> findAll();
	
	public  ParamType findParamTypeById(int idType);
	
}