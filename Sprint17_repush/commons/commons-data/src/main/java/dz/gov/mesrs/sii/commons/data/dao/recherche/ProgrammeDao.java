package dz.gov.mesrs.sii.commons.data.dao.recherche;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.dao.fve.scolarite.GenericFveDao;
import dz.gov.mesrs.sii.commons.data.model.recherche.Programme;

/**
 * @author rlaib  on : 25 janv. 2015  11:11:13
 */
public interface ProgrammeDao extends GenericFveDao<Programme> {
	
	List<Programme> findByType(Integer idTypeProgamme);
	List<Programme> findByKeyWords(String keyWord);
	List<Programme>  findByPeriod(Short startYear,Short endYear);
	List<Programme> findActifProgramme();
}
