package dz.gov.mesrs.sii.commons.data.dao.recherche;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.dao.fve.scolarite.GenericFveDao;
import dz.gov.mesrs.sii.commons.data.model.recherche.Structure;


/**
 * @author rlaib  on : 16 déc. 2014  13:58:17
 */
public interface StructureDao extends GenericFveDao<Structure> {

	List<Structure> findByEtablicement(Integer id);

	
}
