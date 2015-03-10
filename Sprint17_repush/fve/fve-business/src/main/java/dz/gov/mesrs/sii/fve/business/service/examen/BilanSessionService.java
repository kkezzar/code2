/**
 * [dz.gov.mesrs.sii.fve.business.service.cursus.BilanPeriodeService.java] 
 * @author MAKERRI Sofiane on : 22 oct. 2014  15:50:54
 */
package dz.gov.mesrs.sii.fve.business.service.examen;

import java.util.List;

import dz.gov.mesrs.sii.fve.business.model.dto.examen.BilanSessionDto;

/**
 * @author MAKERRI Sofiane on : 22 oct. 2014 15:50:54
 */
public interface BilanSessionService {

	public BilanSessionDto insertOrUpdate(BilanSessionDto bilanSessionDto);

	public void remove(BilanSessionDto bilanSessionDto);

	public BilanSessionDto findById(long id);

	public BilanSessionDto findUniquePeriode(Long deliberationId, Integer diaId);

	public BilanSessionDto findUniqueSession(Long deliberationId, Integer diaId);

	public BilanSessionDto findUniqueAnnuel(Long deliberationId, Integer diaId);

	public List<BilanSessionDto> findAll();

	public List<BilanSessionDto> findByDeliberation(Long deliberationId);

	public List<BilanSessionDto> findByDeliberationAnDia(Long deliberationId,
			Integer diaId);

	public List<BilanSessionDto> findByOofAndNiveau(Integer oofId,
			Integer niveauId);

	public List<BilanSessionDto> findByPeriode(Integer oofId, Integer periodeId);

	public List<BilanSessionDto> findBilanSession(Integer oofId,
			Integer periodeId);

	public List<BilanSessionDto> findBilanPeriodeByNiveau(Integer oofId,
			Integer niveauId);

	public List<BilanSessionDto> findBilanPeriode(Integer oofId,
			Integer periodeId);

	public List<BilanSessionDto> findBilanFinal(Integer oofId, Integer niveauId);

	public List<BilanSessionDto> findBilanAnnuel(Integer oofId, Integer niveauId);

	/**
	 * Recherche avancee dans la table des bilans annuel
	 * 
	 * @author Mounir.MESSAOUDI on : 26 oct. 2014 11:29:27
	 * @param searchDto
	 * @return
	 */

	public List<BilanSessionDto> findAdvanced(BilanSessionDto searchDto);

	/**
	 * Rechercher le bilan final par id dossier inscription administrative
	 * 
	 * @author Mounir.MESSAOUDI on : 26 oct. 2014 11:33:11
	 * @param diaId
	 * @return
	 */

	public BilanSessionDto findBilanFinalByIdDossierInscrAdmin(Integer diaId);

	public List<BilanSessionDto> findByOffAnnee(Integer anneeId, Integer oofId);

	List<BilanSessionDto> findBilanFinalDiplomeNonGenerer(Integer oofId,
			Integer niveauId);

	List<BilanSessionDto> findBilanFinCycle(Integer oofId, Integer niveauId);

}
