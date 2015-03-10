package dz.gov.mesrs.sii.referentiel.ws.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEvenementDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefEvenementService;
import dz.gov.mesrs.sii.referentiel.ws.service.EvenementWS;

/**
 * Classe d'implémentation de l'interface web service de gestion des évènements
 * 
 * @author Kheireddin OMRANI
 * 
 */
@Service("evenementWSImpl")
public class EvenementWSImpl implements EvenementWS {

	private static final Log log = LogFactory.getLog(EvenementWSImpl.class);

	/**
	 * Crée une instance par défaut de la classe EvenementWSImpl.
	 */
	public EvenementWSImpl() {

	}

	@Autowired
	@Qualifier("refEvenementServiceImpl")
	private RefEvenementService refEvenementService;

	/**
	 * Obtient le service métier spring de gestion des évènements.
	 * 
	 * @return le service de gestion des évènements
	 */
	public RefEvenementService getRefEvenementServiceImpl() {
		return refEvenementService;
	}

	/**
	 * MAJ la référence vers le service métier spring de gestion des évènements.
	 * 
	 * @param refEvenementService : le nouveau service de gestion des évènements
	 */
	public void setRefEvenementServiceImpl(RefEvenementService refEvenementService) {
		this.refEvenementService = refEvenementService;
	}


	/**
	 * Permet de rechercher par texte intégrales des évènements
	 */
	public List<RefEvenementDto> findGeneric(String value) throws Exception {

		List<RefEvenementDto> result = new ArrayList<RefEvenementDto>();
		try {
			result = refEvenementService.findGeneric(value);
			log.debug("findGeneric Evenement successful");
		} catch (Exception e) {
			log.error("findGeneric Evenement  failed", e);
			throw e;

		}
		return result;

	}
	
	/**
	 * Permet de rechercher des évènements qui ressemblent à l'objet évènement dto passè en paramètre. 
	 */
	public List<RefEvenementDto> findAdvanced(RefEvenementDto refEvenementDto)
			throws Exception {

		List<RefEvenementDto> result = new ArrayList<RefEvenementDto>();
		try {
			result = refEvenementService.findAdvanced(refEvenementDto);
			log.debug("findAdvanced Evenement successful");
		} catch (Exception e) {
			log.error("findAdvanced Evenement  failed", e);
			throw e;
		}
		return result;
	}

}
