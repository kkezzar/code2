package dz.gov.mesrs.sii.refenrentiel.jsf.beans.common;

import java.lang.Character.UnicodeScript;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.apache.commons.lang.StringUtils;

//TODO remove in commons project
public abstract class GeneriqueLanguageInputValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object object) throws ValidatorException {
	String sValue = (String) object;
	if (StringUtils.isEmpty(sValue)) {
	    return;
	} else if (!isLanguageWord(sValue)) {
	    UIInput input = (UIInput) component;
	    throw new ValidatorException(new FacesMessage(input.getValidatorMessage()));

	}

    }

    private boolean isLanguageWord(String word) {
	for (int i = 0; i < word.length(); i++) {
	    if (isEspace(word.codePointAt(i))) {
		return true;
	    }
	    if (!iValidLanguageCharacter(word.codePointAt(i)))
		return false;
	}
	return true;
    }

    private boolean isEspace(int codePointAt) {
	return codePointAt == 32;
    }

    private boolean iValidLanguageCharacter(int target) {
	if (getSeparationCodePoint().contains(target)) {
	    return true;
	} else {
	    return getUnicodeScript().equals(UnicodeScript.of(target));
	}

    }

    protected abstract UnicodeScript getUnicodeScript();

    protected abstract List<Integer> getSeparationCodePoint();

}