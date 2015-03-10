package dz.gov.mesrs.sii.commons.data.dao.impl.recherche;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.commons.data.dao.impl.fve.scolarite.GenericFveDaoImpl;
import dz.gov.mesrs.sii.commons.data.dao.recherche.EquipementDao;
import dz.gov.mesrs.sii.commons.data.model.recherche.Equipement;

/**
 * @author rlaib  on : 16 déc. 2014  14:07:53
 */
@Service ("equipementDao")
public class EquipementDaoImpl extends GenericFveDaoImpl<Equipement> implements
EquipementDao {
	
	private static final Logger log = LoggerFactory.getLogger(EquipementDaoImpl.class.getName());


}
