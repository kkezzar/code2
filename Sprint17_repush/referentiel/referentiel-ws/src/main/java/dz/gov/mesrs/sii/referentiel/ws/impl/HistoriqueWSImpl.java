/**
 * [dz.gov.mesrs.sii.referentiel.ws.impl.HistoriqueWSImpl.java] 
 * @author MAKERRI Sofiane on : 18 juin 2014  12:26:02
 */
package dz.gov.mesrs.sii.referentiel.ws.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEvenementDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefHistoriqueDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefEvenementService;
import dz.gov.mesrs.sii.referentiel.business.service.RefHistoriqueService;
import dz.gov.mesrs.sii.referentiel.ws.service.HistoriqueWS;

/**
 * @author MAKERRI Sofiane on : 18 juin 2014 12:26:02
 */
@Service("historiqueWSImpl")
public class HistoriqueWSImpl implements HistoriqueWS {

	private static final Log log = LogFactory.getLog(HistoriqueWSImpl.class);
	@Autowired
	@Qualifier("refHistoriqueServiceImpl")
	private RefHistoriqueService refHistoriqueService;
	
	@Autowired
	@Qualifier("refEvenementServiceImpl")
	private RefEvenementService refEvenementService;

	/**
	 * [HistoriqueWSImpl.HistoriqueWSImpl()] Constructor
	 * 
	 * @author MAKERRI Sofiane on : 18 juin 2014 12:26:02
	 */
	public HistoriqueWSImpl() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * [HistoriqueWSImpl.refHistoriqueService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 juin 2014 12:27:36
	 * @return the refHistoriqueService
	 */
	public RefHistoriqueService getRefHistoriqueService() {
		return refHistoriqueService;
	}

	/**
	 * [HistoriqueWSImpl.refHistoriqueService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 juin 2014 12:27:36
	 * @param refHistoriqueService
	 *            the refHistoriqueService to set
	 */
	public void setRefHistoriqueService(
			RefHistoriqueService refHistoriqueService) {
		this.refHistoriqueService = refHistoriqueService;
	}
	
	/**
	 * [HistoriqueWSImpl.refEvenementService] Getter 
	 * @author MAKERRI Sofiane on : 19 juin 2014  08:35:25
	 * @return the refEvenementService
	 */
	public RefEvenementService getRefEvenementService() {
		return refEvenementService;
	}

	/**
	 * [HistoriqueWSImpl.refEvenementService] Setter 
	 * @author MAKERRI Sofiane on : 19 juin 2014  08:35:25
	 * @param refEvenementService the refEvenementService to set
	 */
	public void setRefEvenementService(RefEvenementService refEvenementService) {
		this.refEvenementService = refEvenementService;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.referentiel.ws.service.HistoriqueWS#saveHistorique(dz
	 * .gov.mesrs.sii.referentiel.business.model.dto.RefHistoriqueDto)
	 */
	@Override
	public RefHistoriqueDto saveHistorique(RefHistoriqueDto refHistoriqueDto)
			throws Exception {
		try {

			refHistoriqueDto = refHistoriqueService.save(refHistoriqueDto);
			log.debug("saveHistorique successful");
			return refHistoriqueDto;

		} catch (Exception e) {

			log.error("saveHistorique failed", e);
			throw e;
		}
	}

	@Override
	public List<RefHistoriqueDto> findAdvancedHistorique(
			RefHistoriqueDto refHistoriqueDto) throws Exception {
		
		List<RefHistoriqueDto> result = new ArrayList<RefHistoriqueDto>();
		try {
			result = refHistoriqueService.findAdvanced(refHistoriqueDto);
			log.debug("findAdvancedHistorique successful");
		} catch (Exception e) {
			log.error("findAdvancedHistorique failed", e);
			throw e;

		}
		return result;
	}


	@Override
	public List<RefEvenementDto> findByCodeTypeEvenement(Integer etabId,
			String codeType, Integer year) throws Exception {
		List<RefEvenementDto> result = new ArrayList<RefEvenementDto>();
		try {
			result = refEvenementService.findByCodeType(etabId, codeType, year);
			log.debug("findByCodeTypeEvenement successful");
		} catch (Exception e) {
			log.error("findByCodeTypeEvenement failed", e);
			throw e;

		}
		return result;
	}

	@Override
	public List<RefHistoriqueDto> findByPeriodeHistorique(
			RefHistoriqueDto refHistoriqueDto, Date dateDebut, Date dateFin)
			throws Exception {
		List<RefHistoriqueDto> result = new ArrayList<RefHistoriqueDto>();
		try {
			result = refHistoriqueService.findByPeriode(refHistoriqueDto, dateDebut, dateFin);
			log.debug("findByPeriodeHistorique successful");
		} catch (Exception e) {
			log.error("findByPeriodeHistorique failed", e);
			throw e;

		}
		return result;
	}

	
	
	

}
