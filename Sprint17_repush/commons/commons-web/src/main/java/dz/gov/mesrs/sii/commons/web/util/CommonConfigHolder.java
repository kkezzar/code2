package dz.gov.mesrs.sii.commons.web.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("commonConfigHolder")
public class CommonConfigHolder {
	
	@Value("${help.site.url}")
	private String helpSiteUrl;
	
	
	public String getHelpSiteUrl() {
		return helpSiteUrl;
	}
	

}
