package dz.gov.mesrs.sii.grh.business.service.poste.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.business.service.impl.CommonServiceImpl;
import dz.gov.mesrs.sii.commons.data.dao.CommonDao;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode.Filter;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode.FilterMode;
import dz.gov.mesrs.sii.commons.data.dao.grh.PosteEmploiDao;
import dz.gov.mesrs.sii.commons.data.model.grh.PosteEmploi;
import dz.gov.mesrs.sii.grh.business.model.dto.AffectationPosteDto;
import dz.gov.mesrs.sii.grh.business.model.dto.PosteEmploiDto;
import dz.gov.mesrs.sii.grh.business.service.poste.AffectationPosteService;
import dz.gov.mesrs.sii.grh.business.service.poste.PosteEmploiService;

@Service("posteEmploiService")
@Transactional
public class PosteEmploiServiceImpl extends CommonServiceImpl<PosteEmploi, PosteEmploiDto, Long> implements
		PosteEmploiService {

	private PosteEmploiDao posteEmploiDao;
	private AffectationPosteService affectationPosteService;

	@Override
	protected CommonDao<PosteEmploi, Long> getDao() {
		return posteEmploiDao;
	}

	@Override
	public int countByKeyWord(Integer etablissementId, String searchKeyWords) {
		SearchMode searchMode = applyGenericFilter(null, etablissementId);
		return super.countByKeyWord(searchKeyWords, searchMode);
	}

	@Override
	public List<PosteEmploiDto> findAllByKeyWord(Integer etablissementId, String searchKeyWords, SearchMode searchMode) {
		searchMode = applyGenericFilter(searchMode, etablissementId);
		List<PosteEmploiDto> results = super.findAllByKeyWord(searchKeyWords, searchMode);
		if (results != null && !results.isEmpty()) {
			AffectationPosteDto affectationPoste = new AffectationPosteDto();
			for (PosteEmploiDto result : results) {
				affectationPoste.setPosteDto(result);
				List<AffectationPosteDto> affectations = affectationPosteService.findAllByExample(affectationPoste);
				if (affectations != null && !affectations.isEmpty()) {
					result.setAffectationDto(affectations.get(0));
				}
			}
		}
		return results;
	}

	private SearchMode applyGenericFilter(SearchMode searchMode, Integer etablissementId) {
		if (searchMode == null) {
			searchMode = new SearchMode();
		}
		searchMode.addFilter(new Filter("etablissement.id", etablissementId, FilterMode.EQUALS));
		return searchMode;
	}

	@Autowired
	public void setPosteEmploiDao(PosteEmploiDao posteEmploiDao) {
		this.posteEmploiDao = posteEmploiDao;
	}

	@Autowired
	public void setAffectationPosteService(AffectationPosteService affectationPosteService) {
		this.affectationPosteService = affectationPosteService;
	}

}
