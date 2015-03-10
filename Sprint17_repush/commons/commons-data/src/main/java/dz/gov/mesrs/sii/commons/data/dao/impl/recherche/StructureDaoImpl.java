package dz.gov.mesrs.sii.commons.data.dao.impl.recherche;



import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.commons.data.dao.impl.fve.scolarite.GenericFveDaoImpl;
import dz.gov.mesrs.sii.commons.data.dao.recherche.StructureDao;
import dz.gov.mesrs.sii.commons.data.model.recherche.Structure;

/**
 * @author rlaib  on : 16 déc. 2014  14:07:53
 */
@Service ("structureDao")
public class StructureDaoImpl extends GenericFveDaoImpl<Structure> implements
			StructureDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Structure> findByEtablicement(Integer id) {
		Query query=entityManager.createQuery("select c from Structure c where c.refStructure.refEtablissement.id = :id").setParameter("id", id);
		return query.getResultList();
	}
	


}
