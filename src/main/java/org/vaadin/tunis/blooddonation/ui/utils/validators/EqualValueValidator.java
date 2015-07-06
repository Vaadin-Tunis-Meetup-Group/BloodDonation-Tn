package org.vaadin.tunis.blooddonation.ui.utils.validators;

import com.vaadin.data.Validator;
import com.vaadin.ui.Field;

public class EqualValueValidator implements Validator {

	Field field1;
	Field field2;

	public EqualValueValidator(Field field1, Field field2) {
		this.field1 = field1;
		this.field2 = field2;
	}

	@Override
	public void validate(Object value) throws InvalidValueException {
		String value1 = (String) field1.getValue();
		String value2 = (String) field2.getValue();
		if (value1 == null || (value1 != null && !value1.equals(value2))) {
			throw new InvalidValueException(
					"These passwords don't match. Try again?");
		}
	}

}
