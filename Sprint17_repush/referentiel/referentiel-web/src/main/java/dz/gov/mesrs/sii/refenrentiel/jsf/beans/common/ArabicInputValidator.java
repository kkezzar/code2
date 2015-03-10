package dz.gov.mesrs.sii.refenrentiel.jsf.beans.common;

import java.lang.Character.UnicodeScript;
import java.util.ArrayList;
import java.util.List;

import javax.faces.validator.FacesValidator;

@FacesValidator(value = "arabicInputValidator")
public class ArabicInputValidator extends GeneriqueLanguageInputValidator {

    @Override
    protected UnicodeScript getUnicodeScript() {
	return UnicodeScript.ARABIC;
    }

    @Override
    protected List<Integer> getSeparationCodePoint() {
	return new ArrayList<Integer>();
    }

}
