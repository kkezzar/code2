package dz.gov.mesrs.sii.commons.security.userdetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefAffectationDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefCompteDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEtablissementDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefGroupeDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefIndividuDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefStructureDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefAffectationService;
import dz.gov.mesrs.sii.referentiel.business.service.RefCompteService;
import dz.gov.mesrs.sii.referentiel.business.service.RefIndividuService;
import dz.gov.mesrs.sii.referentiel.business.util.ConfigHolder;

/**
 * 
 * @author Mounir.MESSAOUDI (CCM-CG)
 *
 */
@Service
public class ProgresUserDetailsService implements UserDetailsService {

	@Autowired
	public ConfigHolder configHolder;
	// the service locator of the business services
	@Autowired
	public RefCompteService refCompteService;
	@Autowired
	public RefIndividuService refIndividuService;
	@Autowired
	public RefAffectationService refAffectationService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub

		RefCompteDto compte = refCompteService.findByLogin(username);
		RefIndividuDto individuDto = refIndividuService.findById(compte.getIndividuId());

		RefEtablissementDto refEtablissementDto = new RefEtablissementDto(compte.getEtabId(), compte.getEtabIdf(),
				compte.getEtabLcAr(), compte.getEtabLcFr(), compte.getEtabLlAr(), compte.getEtabLlFr(),
				compte.getEtabAncienCode());

		Collection<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		ProgresUser user = new ProgresUser(username, "", true, true, true, true, authorities);

		user.setCompte(compte);
		user.setUser(individuDto);
		user.setCurrentEtablissement(refEtablissementDto);

		// charger les affectations
		List<RefAffectationDto> listAffectation = refAffectationService.findAffectationByIdIndividu(compte
				.getIndividuId());
		if (listAffectation != null && listAffectation.size() >= 1) {
			RefAffectationDto firstRefAffectationDto = listAffectation.get(0);
			user.setCurrentRole(new NomenclatureDto(firstRefAffectationDto.getId(), firstRefAffectationDto
					.getRoleCode(), firstRefAffectationDto.getRoleLibelleLongFr(), firstRefAffectationDto
					.getRoleLibelleLongAr(), firstRefAffectationDto.getRoleLibelleCourtFr(), firstRefAffectationDto
					.getRoleLibelleCourtAr()));
			user.setCurrentGroupe(new RefGroupeDto(firstRefAffectationDto));
			user.setCurrentStructure(new RefStructureDto(firstRefAffectationDto));
			user.setCurrentEtablissement(new RefEtablissementDto(firstRefAffectationDto));

			// ajouter la liste des roles a la liste des autorites Spring
			// security
			authorities.add(new SimpleGrantedAuthority(firstRefAffectationDto.getRoleCode()));
			// for (RefAffectationDto refAffectationDto : listAffectation) {
			// authorities.add(new
			// SimpleGrantedAuthority(refAffectationDto.getRoleCode()));
			// }

			user.setListAffectations(listAffectation);
		} else
			user.setListAffectations(new ArrayList<RefAffectationDto>());

		return user;
	}
}