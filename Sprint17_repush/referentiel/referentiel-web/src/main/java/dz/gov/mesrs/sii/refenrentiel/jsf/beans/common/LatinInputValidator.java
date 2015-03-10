package dz.gov.mesrs.sii.refenrentiel.jsf.beans.common;

import java.lang.Character.UnicodeScript;
import java.util.Arrays;
import java.util.List;

import javax.faces.validator.FacesValidator;

@FacesValidator(value = "latinInputValidator")
public class LatinInputValidator extends GeneriqueLanguageInputValidator {

    @Override
    protected UnicodeScript getUnicodeScript() {
	return UnicodeScript.LATIN;
    }

    // '<->39,-<->45
    @Override
    protected List<Integer> getSeparationCodePoint() {
	return Arrays.asList(39, 45);
    }

}
