package dz.gov.mesrs.sii.commons.data.dao.recherche;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.dao.fve.scolarite.GenericFveDao;
import dz.gov.mesrs.sii.commons.data.model.gfc.Article;
import dz.gov.mesrs.sii.commons.data.model.gfc.Chapitre;
import dz.gov.mesrs.sii.commons.data.model.recherche.DemandeBudget;

/**
 * @author rlaib  on : 25 janv. 2015  11:11:13
 */
public interface DemandeBudgetDao extends GenericFveDao<DemandeBudget> {
	
	List<DemandeBudget> findByIdProgramme(Long idProgamme);
	List<DemandeBudget> findByKeyWords(String keyWord);
	List<Chapitre> findChapters();
	List<Article> findArticlesByChapterId(Integer chapterId);
	List<DemandeBudget> findDemandsToValidate(String roleCode);
}
