package dz.gov.mesrs.sii.commons.security.userdetails;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefAffectationDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefCompteDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEtablissementDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefGroupeDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefIndividuDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefStructureDto;

/**
 * 
 * @author Mounir.MESSAOUDI (CCM-CG)
 *
 */
public class ProgresUser extends User {

	private static final long serialVersionUID = 1L;

	private RefIndividuDto user;
	private RefCompteDto compte;

	private List<RefAffectationDto> listAffectations;

	private RefEtablissementDto currentEtablissement;
	private NomenclatureDto currentRole;
	private RefStructureDto currentStructure;
	private RefGroupeDto currentGroupe;

	private RefAffectationDto currentAffectation;

	public ProgresUser() {
		super(null, null, null);
	}

	public ProgresUser(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);

	}

	public RefIndividuDto getUser() {
		return user;
	}

	public void setUser(RefIndividuDto user) {
		this.user = user;
	}

	public RefCompteDto getCompte() {
		return compte;
	}

	public void setCompte(RefCompteDto compte) {
		this.compte = compte;
	}

	public RefEtablissementDto getCurrentEtablissement() {
		return currentEtablissement;
	}

	public void setCurrentEtablissement(RefEtablissementDto currentEtablissement) {
		this.currentEtablissement = currentEtablissement;
	}

	public NomenclatureDto getCurrentRole() {
		return currentRole;
	}

	public void setCurrentRole(NomenclatureDto currentRole) {
		this.currentRole = currentRole;
	}

	public RefStructureDto getCurrentStructure() {
		return currentStructure;
	}

	public void setCurrentStructure(RefStructureDto currentStructure) {
		this.currentStructure = currentStructure;
	}

	public RefGroupeDto getCurrentGroupe() {
		return currentGroupe;
	}

	public void setCurrentGroupe(RefGroupeDto currentGroupe) {
		this.currentGroupe = currentGroupe;
	}

	public RefAffectationDto getCurrentAffectation() {
		return currentAffectation;
	}

	public void setCurrentAffectation(RefAffectationDto currentAffectation) {
		this.currentAffectation = currentAffectation;

		this.currentGroupe = new RefGroupeDto(this.currentAffectation);
		this.currentStructure = new RefStructureDto(this.currentAffectation);
		this.currentEtablissement = new RefEtablissementDto(this.currentAffectation);

		this.currentRole = new NomenclatureDto();
		this.currentRole.setId(currentAffectation.getRoleId());
		this.currentRole.setCode(currentAffectation.getRoleCode());
		this.currentRole.setLibelleLongFr(currentAffectation.getRoleLibelleLongFr());
		this.currentRole.setLibelleLongAr(currentAffectation.getRoleLibelleLongAr());
		this.currentRole.setLibelleCourtFr(currentAffectation.getRoleLibelleCourtFr());
		this.currentRole.setLibelleCourtAr(currentAffectation.getRoleLibelleCourtAr());
	}

	public List<RefAffectationDto> getListAffectations() {
		return listAffectations;
	}

	public void setListAffectations(List<RefAffectationDto> listAffectations) {
		this.listAffectations = listAffectations;
	}

	@Override
	public String toString() {
		return "ProgresUser [user=" + user + ", compte=" + compte + "]";
	}

}