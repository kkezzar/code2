package dz.gov.mesrs.sii.fve.business.model.dto.cursus;

import java.io.Serializable;

import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.AnneeAcademiqueDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NcNamesDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefIndividuDto;

public class DemandePreinscriptionSearchDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -584232141295597985L;

	private AnneeAcademiqueDto anneeAcademiqueDto;
	
	private RefIndividuDto refIndividuDto;
	
	private NcNamesDto statusDto;
	
	private DecisionDemandePreinscriptionDto decisionDemandePreinscriptionDto;

}
