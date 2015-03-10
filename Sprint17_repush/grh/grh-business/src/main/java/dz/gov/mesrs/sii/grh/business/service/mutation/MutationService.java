package dz.gov.mesrs.sii.grh.business.service.mutation;

import java.util.Date;
import java.util.List;

import dz.gov.mesrs.sii.commons.business.service.CommonService;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode;
import dz.gov.mesrs.sii.commons.data.model.grh.Mutation;
import dz.gov.mesrs.sii.grh.business.model.dto.MutationDto;

/**
 * 
 * @author Salem
 * @version V1.0
 * @date 22-10-2014
 * @description Interface Service Mutation
 * 
 */
public interface MutationService extends CommonService<Mutation, MutationDto, Integer> {
	MutationDto saveInstallationEmployeMute(MutationDto mutationDto, Date dateInstallation, Integer affectation);
	public List<MutationDto> findAllDemandesMutation(int etablissmentId, SearchMode searchMode, String searchKeyWords);
	public int countAllDemandesMutation(int etablissmentId, SearchMode searchMode, String searchKeyWords);
	public List<MutationDto> findAllDemandesSoumisePourValidation(int etablissmentId, SearchMode searchMode, String searchKeyWords);
	public int countAllDemandesSoumisePourValidation(int etablissmentId, SearchMode searchMode, String searchKeyWords);
	public List<MutationDto> findAllDecisionsMutation(int etablissmentId, SearchMode searchMode, String searchKeyWords);
	public int countAllDecisionsMutation(int etablissmentId, SearchMode searchMode, String searchKeyWords);
	public List<MutationDto> findAllMutationsTraites(int etablissmentId, SearchMode searchMode, String searchKeyWords);
	public int countAllMutationsTraites(int etablissmentId, SearchMode searchMode, String searchKeyWords);
	public List<MutationDto> findAllMutations(int etablissmentId, SearchMode searchMode, String searchKeyWords);
	public int countAllMutations(int etablissmentId, SearchMode searchMode, String searchKeyWords);
	
}