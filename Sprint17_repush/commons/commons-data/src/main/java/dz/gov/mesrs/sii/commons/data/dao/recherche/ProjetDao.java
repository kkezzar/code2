package dz.gov.mesrs.sii.commons.data.dao.recherche;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.dao.fve.scolarite.GenericFveDao;
import dz.gov.mesrs.sii.commons.data.model.recherche.ActiviteProjet;
import dz.gov.mesrs.sii.commons.data.model.recherche.Projet;

/**
 * @author rlaib  on : 16 d�c. 2014  13:58:17
 */
public interface ProjetDao extends GenericFveDao<Projet> {

	

	List<Projet> findByKeyWords(String keyWord);

	Projet saveProjet(Projet projet, String annee, String structure);

	List<ActiviteProjet> finListActiviteByProjet(Long projetid);

	List<Projet> findByCodeProjet(String codeProjet, Integer idSituation);

	

	
}
