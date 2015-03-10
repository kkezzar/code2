package dz.gov.mesrs.sii.commons.data.dao.recherche;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.dao.fve.scolarite.GenericFveDao;
import dz.gov.mesrs.sii.commons.data.model.recherche.DemandeCredit;

/**
 * @author rlaib  on : 25 janv. 2015  11:11:13
 */
public interface DemandeCreditDao extends GenericFveDao<DemandeCredit> {
	List<DemandeCredit> findByIdDemand(Long idDemand);
}
