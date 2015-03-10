package dz.gov.mesrs.sii.commons.data.dao.bpm;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.dao.fve.scolarite.GenericFveDao;
import dz.gov.mesrs.sii.commons.data.model.bpm.Processus;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 

public interface ProcessusDao extends GenericFveDao<Processus> {

	List<Processus> findByCodeDomaine(String codeDomaine);
	List<Processus> findByCodeEntite(String codeEntite);
	List<Processus> findByKeyWords(String keyWord);
}