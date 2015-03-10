package dz.gov.mesrs.sii.gfc.business.service.impl;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.business.service.impl.CommonServiceImpl;
import dz.gov.mesrs.sii.commons.data.dao.CommonDao;
import dz.gov.mesrs.sii.commons.data.dao.gfc.ProduitMarcheDao;
import dz.gov.mesrs.sii.commons.data.model.gfc.ProduitMarche;
import dz.gov.mesrs.sii.gfc.business.model.dto.ProduitMarcheDto;
import dz.gov.mesrs.sii.gfc.business.service.ProduitMarcheService;

/**
 * Service Implementation for domain model class ProduitMarche.
 * 
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
@Service("produitMarcheService")
@Transactional
public class ProduitMarcheServiceImpl extends CommonServiceImpl<ProduitMarche, ProduitMarcheDto, Integer> implements
		ProduitMarcheService {

	@Autowired
	@Qualifier("produitMarcheDao")
	private ProduitMarcheDao produitMarcheDao;

	@Autowired
	private Mapper mapper;

	public ProduitMarcheServiceImpl() {

	}

	@Override
	protected CommonDao<ProduitMarche, Integer> getDao() {
		return produitMarcheDao;
	}
}