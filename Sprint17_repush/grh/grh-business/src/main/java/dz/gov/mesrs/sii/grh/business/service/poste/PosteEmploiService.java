package dz.gov.mesrs.sii.grh.business.service.poste;

import java.util.List;

import dz.gov.mesrs.sii.commons.business.service.CommonService;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode;
import dz.gov.mesrs.sii.commons.data.model.grh.PosteEmploi;
import dz.gov.mesrs.sii.grh.business.model.dto.PosteEmploiDto;

public interface PosteEmploiService extends CommonService<PosteEmploi, PosteEmploiDto, Long> {

	int countByKeyWord(Integer etablissementId, String searchKeyWords);

	List<PosteEmploiDto> findAllByKeyWord(Integer etablissementId, String searchKeyWords, SearchMode searchMode);

}
