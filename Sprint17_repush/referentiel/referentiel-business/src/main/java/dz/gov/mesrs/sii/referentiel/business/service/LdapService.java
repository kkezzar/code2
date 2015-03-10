
package dz.gov.mesrs.sii.referentiel.business.service;


import java.util.Collection;

import org.springframework.security.core.Authentication;


/**
 * @author j.beldi
 *
 */
/**
 * @author BELDI Jamel  on : 3 mars 2014  09:58:18
 */
public interface LdapService {

    boolean verifierUserLdap(String login, String password);

    Authentication authenticate(String userName, boolean isAuthenticated, Collection<String> roles);
}
