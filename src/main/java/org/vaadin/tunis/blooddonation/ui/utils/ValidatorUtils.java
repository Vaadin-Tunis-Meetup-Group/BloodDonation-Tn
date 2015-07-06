package org.vaadin.tunis.blooddonation.ui.utils;

import java.util.Collection;

import com.vaadin.data.Validator;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.ui.Field;

public class ValidatorUtils {

	private ValidatorUtils() {
	}

	public static void installSingleValidator(Field<?> field, Class beanClass,
			String attribute) {

		Collection<Validator> validators = field.getValidators();

		if (validators == null || validators.isEmpty()) {

			field.addValidator(new BeanValidator(beanClass, attribute));
		}
	}

	public static void installFieldValidator(Field field, Validator validator) {
		field.addValidator(validator);
		

	}
}