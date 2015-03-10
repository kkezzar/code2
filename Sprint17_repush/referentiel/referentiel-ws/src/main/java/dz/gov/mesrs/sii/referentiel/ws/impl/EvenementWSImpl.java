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
 * Classe d'impl�mentation de l'interface web service de gestion des �v�nements
 * 
 * @author Kheireddin OMRANI
 * 
 */
@Service("evenementWSImpl")
public class EvenementWSImpl implements EvenementWS {

	private static final Log log = LogFactory.getLog(EvenementWSImpl.class);

	/**
	 * Cr�e une instance par d�faut de la classe EvenementWSImpl.
	 */
	public EvenementWSImpl() {

	}

	@Autowired
	@Qualifier("refEvenementServiceImpl")
	private RefEvenementService refEvenementService;

	/**
	 * Obtient le service m�tier spring de gestion des �v�nements.
	 * 
	 * @return le service de gestion des �v�nements
	 */
	public RefEvenementService getRefEvenementServiceImpl() {
		return refEvenementService;
	}

	/**
	 * MAJ la r�f�rence vers le service m�tier spring de gestion des �v�nements.
	 * 
	 * @param refEvenementService : le nouveau service de gestion des �v�nements
	 */
	public void setRefEvenementServiceImpl(RefEvenementService refEvenementService) {
		this.refEvenementService = refEvenementService;
	}


	/**
	 * Permet de rechercher par texte int�grales des �v�nements
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
	 * Permet de rechercher des �v�nements qui ressemblent � l'objet �v�nement dto pass� en param�tre. 
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
