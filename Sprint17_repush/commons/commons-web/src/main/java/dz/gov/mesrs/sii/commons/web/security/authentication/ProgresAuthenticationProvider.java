package dz.gov.mesrs.sii.commons.web.security.authentication;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Component;

import dz.gov.mesrs.sii.commons.security.userdetails.ProgresUser;
import dz.gov.mesrs.sii.commons.web.util.PasswordUtil;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefCompteDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefCompteService;
import dz.gov.mesrs.sii.referentiel.business.service.RefHoraireAccessService;
import dz.gov.mesrs.sii.referentiel.business.service.RefPlageAdresseService;

/**
 * Progres Authentication Provider qui utilise la base de donnees pour verifier
 * l'authentification d'un utilisateur.
 * 
 * @author Mounir.MESSAOUDI (CCM-CG)
 */
@Component
public class ProgresAuthenticationProvider implements AuthenticationProvider, Serializable {

	private static final long serialVersionUID = 1L;

	// the logger for this class
	protected final Log logger = LogFactory.getLog(this.getClass());

	// il sert a quoi ?
	private UserDetailsService userDetailsService;

	@Autowired
	RefCompteService refCompteService;

	@Autowired
	RefPlageAdresseService refPlageAdresseService;

	@Autowired
	RefHoraireAccessService refHoraireAccessService;

	/**
	 * Cette fonction assure l'authentification via Spring Security Elle renvoi
	 * un objet Authentication si la connexion est succes, sinon elle throw une
	 * exception de type AuthenticationException
	 * 
	 * @author Mounir.MESSAOUDI (CCM-CG)
	 */
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();

		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		HttpServletRequest request = (HttpServletRequest) context.getRequest();

		String userAddr = request.getRemoteAddr();
		Date heureAcces = new Date();
		logger.info("userAddr" + userAddr);
		logger.info("heureAcces reel:" + heureAcces);

		List<GrantedAuthority> grantedAuths = new ArrayList<>();

		heureAcces.setDate(1);
		heureAcces.setMonth(0);
		heureAcces.setYear(70);

		ProgresUser user = (ProgresUser) userDetailsService.loadUserByUsername(username);

		// RefCompteDto compteDto = refCompteService.findByLogin(username);

		if (user == null)
			throw new BadCredentialsException("Username/Password does not match for " + username);

		RefCompteDto compteDto = user.getCompte();
		if (user.getCompte() == null)
			throw new BadCredentialsException("Username/Password does not match for " + username);

		String userpassword = compteDto.getMotPasse().replaceAll("[\\p{Cc}\\p{Cf}\\p{Co}\\p{Cn}]", " ");

		String encryptedPassword = null;
		try {
			encryptedPassword = PasswordUtil.encryptAES(password).replaceAll("[\\p{Cc}\\p{Cf}\\p{Co}\\p{Cn}]", " ");
		} catch (Exception ex) {
			throw new BadCredentialsException("Username/Password does not match for " + username);
		}

		if (!StringUtils.equals(StringEscapeUtils.escapeJava(encryptedPassword),
				StringEscapeUtils.escapeJava(userpassword))) {
			throw new BadCredentialsException("Username/Password does not match for " + username);

		} else if (compteDto.getIdSituation() == null || !compteDto.getIdSituation().equals(Integer.valueOf(272))) {
			// Failed compte found on BD but is not active
			throw new LockedException("Username/Password does not match for " + username);
		} else if (compteDto.getPlageAdresseId() != null
				&& !refPlageAdresseService.verifyUserIp(userAddr, compteDto.getPlageAdresseAdresseDebut(),
						compteDto.getPlageAdresseAdresseFin())) {

			// Failed compte found on BD active but off palge
			// d'adresse
			throw new BadCredentialsException("Username/Password does not match for " + user.getUsername());

		} else if (compteDto.getHoraireAccessId() != null
				&& !refHoraireAccessService.verifyHoraireAcces(heureAcces, compteDto.getHoraireAccessHeureDebut(),
						compteDto.getHoraireAccessHeureFin())) {

			// authentification_loginFailed_off_horaire_acces
			throw new BadCredentialsException("Username/Password does not match for " + username);
		} else {
			Authentication auth = new UsernamePasswordAuthenticationToken(user, password, user.getAuthorities());

			request.getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
					SecurityContextHolder.getContext());
			SecurityContextHolder.getContext().setAuthentication(auth);
			return auth;
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

	public UserDetailsService getUserDetailsService() {
		return userDetailsService;
	}

	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

}