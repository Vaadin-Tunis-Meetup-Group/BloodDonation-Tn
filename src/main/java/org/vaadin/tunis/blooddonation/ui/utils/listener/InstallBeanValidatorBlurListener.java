package org.vaadin.tunis.blooddonation.ui.utils.listener;

import org.vaadin.tunis.blooddonation.ui.utils.ValidatorUtils;

import com.vaadin.event.FieldEvents.BlurEvent;
import com.vaadin.event.FieldEvents.BlurListener;
import com.vaadin.ui.Field;

public class InstallBeanValidatorBlurListener implements BlurListener {

	private Field<?> field;
	private String attribute;
	private Class cls;

	public InstallBeanValidatorBlurListener(Class cls, Field<?> field,
			String attribute) {
		this.cls = cls;
		this.field = field;
		this.attribute = attribute;
	}

	@Override
	public void blur(BlurEvent event) {

		ValidatorUtils.installSingleValidator(field, cls, attribute);
	}
}
