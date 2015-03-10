package dz.gov.mesrs.sii.commons.data.dao.recherche;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.dao.fve.scolarite.GenericFveDao;
import dz.gov.mesrs.sii.commons.data.model.recherche.Partenaire;
import dz.gov.mesrs.sii.commons.data.model.recherche.ProjetPartenaire;


/**
 * @author rlaib  on : 16 déc. 2014  13:58:17
 */
public interface PartenaireDao extends GenericFveDao<Partenaire> {

	List<Partenaire> findStructureRecherchePartenaires(Long idStructure);

	void saveProjetPartenaire(ProjetPartenaire projetPartenaire);

	ProjetPartenaire mergeProjetPartenaire(ProjetPartenaire projetPartenaire);

	List<ProjetPartenaire> findListPartenaireByIdProjet(Long id);

	void removeProjetPartenaire(Long partenaireId);
}
