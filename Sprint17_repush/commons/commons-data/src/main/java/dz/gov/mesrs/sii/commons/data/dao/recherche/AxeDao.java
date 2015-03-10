package dz.gov.mesrs.sii.commons.data.dao.recherche;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.dao.fve.scolarite.GenericFveDao;
import dz.gov.mesrs.sii.commons.data.model.recherche.Axe;

public interface AxeDao extends GenericFveDao<Axe> {
	
	List<Axe> findByIdProgramme(Long idProgamme);
}
