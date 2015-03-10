package dz.gov.mesrs.sii.gfc.business.service.impl;

import java.math.BigDecimal;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.business.service.impl.CommonServiceImpl;
import dz.gov.mesrs.sii.commons.data.dao.CommonDao;
import dz.gov.mesrs.sii.commons.data.dao.gfc.EquipementMarcheDao;
import dz.gov.mesrs.sii.commons.data.model.gfc.EquipementMarche;
import dz.gov.mesrs.sii.gfc.business.model.dto.EquipementMarcheDto;
import dz.gov.mesrs.sii.gfc.business.service.EquipementMarcheService;

/**
 * Service Implementation for domain model class EquipementMarche.
 * 
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
@Service("equipementMarcheService")
@Transactional
public class EquipementMarcheServiceImpl extends CommonServiceImpl<EquipementMarche, EquipementMarcheDto, Integer>
		implements EquipementMarcheService {

	@Autowired
	@Qualifier("equipementMarcheDao")
	private EquipementMarcheDao equipementMarcheDao;

	@Autowired
	private Mapper mapper;

	public EquipementMarcheServiceImpl() {

	}

	@Override
	protected CommonDao<EquipementMarche, Integer> getDao() {
		return equipementMarcheDao;
	}

	@Override
	public BigDecimal calculerMontantTTC(EquipementMarcheDto equipementMarche) {
		BigDecimal totalTTC = null;

		BigDecimal prixUnitaire = equipementMarche.getPrixUnitaire();
		if (prixUnitaire == null)
			return totalTTC;

		Integer quantite = equipementMarche.getQuantite();

		totalTTC = prixUnitaire.multiply(new BigDecimal(quantite));
		Float tva = equipementMarche.getTauxTva();
		tva = tva / 100;
		totalTTC = totalTTC.add(totalTTC.multiply(new BigDecimal(tva)));
		return totalTTC;
	}
}