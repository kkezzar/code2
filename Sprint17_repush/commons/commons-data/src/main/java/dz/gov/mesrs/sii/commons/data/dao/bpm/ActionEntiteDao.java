package dz.gov.mesrs.sii.commons.data.dao.bpm;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.bpm.ActionEntite;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 

public interface ActionEntiteDao {


	public  ActionEntite findById(int id);
    public List<ActionEntite> findByEntityCode(String entityCode);
    public ActionEntite findByEntityCodeByActionCode(String entityCode,String actionCode);
	
}