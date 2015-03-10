package dz.gov.mesrs.sii.commons.data.dao.fve.cursus;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.bpm.SituationEntite;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.VoeuEtudiant;

/**
 * 
 * @author Mounir.MESSAOUDI on : 28 sept. 2014 17:08:04
 */
public interface VoeuEtudiantDao {

	public void persist(VoeuEtudiant transientInstance);

	public void remove(VoeuEtudiant persistentInstance);

	public VoeuEtudiant merge(VoeuEtudiant detachedInstance);

	public VoeuEtudiant findById(int id);
	
	public List<VoeuEtudiant> findAdvanced(VoeuEtudiant searchBo, List<SituationEntite> searchSitutationEntiteBos);
	
	public List<VoeuEtudiant> findByQuery(String query) ;
	
	public List<VoeuEtudiant> findAll();
	
	/**
	 * rechercher une fiche de voeux par annee academqiue et dossier etudiant
	 * @author Mounir.MESSAOUDI on : 2 oct. 2014 10:39:53 
	 * @param searchBo
	 * @return
	 */
	public VoeuEtudiant findByIdDosEtudIdAnnAcad(Integer idDossierEtudiant, Integer idAnneeAcademique);
}