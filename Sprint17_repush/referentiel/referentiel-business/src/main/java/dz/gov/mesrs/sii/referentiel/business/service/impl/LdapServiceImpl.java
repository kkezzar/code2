/**
 *
 */
package dz.gov.mesrs.sii.referentiel.business.service.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.referentiel.business.service.LdapService;



/**
 * @author BELDI Jamel  on : 3 mars 2014  10:35:02
 */
@Service("ldapServiceImpl")
public class LdapServiceImpl implements LdapService {

    @Override
    public boolean verifierUserLdap(String login, String password) {
        /*try {
            String INITCTX =  UtilConfig.loadPropretiesValue("ldap.init.ctx");
            String PROVIDER_URL =  UtilConfig.loadPropretiesValue("ldap.provider.url");
            String DOMAIN =  UtilConfig.loadPropretiesValue("ldap.domain");
            String SECURITY_AUTHENTICATION =  UtilConfig.loadPropretiesValue("ldap.security.authentification");
            DirContext ctx = null;
            Hashtable env = new Hashtable();
            env.put(Context.INITIAL_CONTEXT_FACTORY, INITCTX);
            env.put(Context.PROVIDER_URL, PROVIDER_URL);
            env.put(Context.SECURITY_PRINCIPAL, login + DOMAIN);
            env.put(Context.SECURITY_CREDENTIALS, password);
            env.put(Context.SECURITY_AUTHENTICATION, SECURITY_AUTHENTICATION);
            System.out.println("ok daaaaaaaaaap");
            ctx = new InitialDirContext(env);
            System.out.println("ok daaaaaaaaaap");
        } catch (Exception exc) {
            exc.printStackTrace();
            return false;
        }*/
        return true;

    }

    @Override
    public Authentication authenticate(final String userName, final boolean isAuthenticated, final Collection<String> roles) {

        Authentication authenticatedUser = new Authentication() {
            private static final long serialVersionUID = 1L;

            @Override
            public String getName() {
                if (userName != null) {
                    return userName;
                }
                return null;
            }

            @Override
            public boolean isAuthenticated() {

                return isAuthenticated;
            }

            @Override
            public Object getPrincipal() {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public Object getDetails() {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public Object getCredentials() {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                Collection authorities = new ArrayList<GrantedAuthority>();
                if (roles != null && !roles.isEmpty()) {
                	for (final String role: roles){
                    GrantedAuthority grantedAuthority = new GrantedAuthority() {
                        @Override
                        public String getAuthority() {
                            //return userDTO.getProfil();
                        	return role;
                        }
                    };
                    authorities.add(grantedAuthority);
                }
                }
                return authorities;
            }

            @Override
            public void setAuthenticated(boolean arg0)
                    throws IllegalArgumentException {
                // TODO Auto-generated method stub
            }
        };



        return authenticatedUser;
    }
}
