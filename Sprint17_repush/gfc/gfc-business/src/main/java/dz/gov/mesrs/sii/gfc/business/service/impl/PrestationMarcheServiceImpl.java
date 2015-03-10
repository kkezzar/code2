package dz.gov.mesrs.sii.gfc.business.service.impl;

import java.math.BigDecimal;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.business.service.impl.CommonServiceImpl;
import dz.gov.mesrs.sii.commons.data.dao.CommonDao;
import dz.gov.mesrs.sii.commons.data.dao.gfc.PrestationMarcheDao;
import dz.gov.mesrs.sii.commons.data.model.gfc.PrestationMarche;
import dz.gov.mesrs.sii.gfc.business.model.dto.PrestationMarcheDto;
import dz.gov.mesrs.sii.gfc.business.service.PrestationMarcheService;

/**
 * Service Implementation for domain model class EvenementMarche.
 * 
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
@Service("prestationMarcheService")
@Transactional
public class PrestationMarcheServiceImpl extends CommonServiceImpl<PrestationMarche, PrestationMarcheDto, Integer>
		implements PrestationMarcheService {

	@Autowired
	@Qualifier("prestationMarcheDao")
	private PrestationMarcheDao prestationMarcheDao;

	@Autowired
	private Mapper mapper;

	public PrestationMarcheServiceImpl() {

	}

	@Override
	protected CommonDao<PrestationMarche, Integer> getDao() {
		return prestationMarcheDao;
	}

	@Override
	public BigDecimal calculerMontantTTC(PrestationMarcheDto prestationMarcheDto) {
		BigDecimal totalTTC = null;

		BigDecimal prixUnitaire = prestationMarcheDto.getPrixUnitaire();
		if (prixUnitaire == null)
			return totalTTC;

		Integer quantite = prestationMarcheDto.getQuantite();

		totalTTC = prixUnitaire.multiply(new BigDecimal(quantite));
		Float tva = prestationMarcheDto.getTauxTva();
		tva = tva / 100;
		totalTTC = totalTTC.add(totalTTC.multiply(new BigDecimal(tva)));
		return totalTTC;

	}
}