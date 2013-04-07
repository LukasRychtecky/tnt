package com.codingcrayons.tnt.kit;

import java.io.IOException;
import java.io.Writer;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.faces.component.UICommand;
import javax.faces.component.UIComponent;
import javax.faces.component.UIForm;
import javax.faces.component.UIInput;
import javax.faces.component.UISelectBoolean;
import javax.faces.component.UISelectMany;
import javax.faces.component.UISelectOne;
import javax.faces.component.html.HtmlInputSecret;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlInputTextarea;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.context.ResponseWriterWrapper;

public class Html5ResponseWriter extends ResponseWriterWrapper {

	protected FacesContext ctx;

	protected static Set<String> FORM_ATTRS = set(
		"autocomplete"
	);

	protected static Set<String> SELECT_ATTRS = set(
		"autofocus"
	);

	protected static Set<String> TEXTAREA_ATTRS = set(
		"autofocus", "maxlength", "placeholder", "spellcheck", "wrap"
	);

	protected static Set<String> INPUT_ATTRS = set(
		"autofocus", "list", "pattern", "placeholder", "spellcheck"
	);

	protected static Set<String> INPUT_PASSWORD_ATTRS = set(
		"autofocus", "pattern", "placeholder"
	);

	protected static Set<String> INPUT_RANGE_ATTRS = set(
		"max", "min", "step"
	);

	protected static Set<String> INPUT_RANGE_TYPES = set(
		"range", "number", "date"
	);

	protected static Set<String> BUTTON_ATTRIBUTES = set(
		"autofocus"
	);

	protected ResponseWriter wrapped;

	public Html5ResponseWriter(ResponseWriter wrapped, FacesContext ctx) {
		this.wrapped = wrapped;
		this.ctx = ctx;
	}

	@Override
	public ResponseWriter cloneWithWriter(Writer writer) {
		return new Html5ResponseWriter(super.cloneWithWriter(writer), ctx);
	}

	@Override
	public void startElement(String name, UIComponent component) throws IOException {
		super.startElement(name, component);

		if (component == null) {
			// Try lookup a component, a workaround for PrimeFaces
			component = getCurrentComponent();
		}

		if (component instanceof UIForm && "form".equals(name)) {
			writeHtml5Attributes(FORM_ATTRS, component);
			writeRequiredAttribute(component);
		} else if (component instanceof UIInput) {
			if (component instanceof HtmlInputText && "input".equals(name)) {
				writeHtml5Attributes(INPUT_ATTRS, component);
				writeRequiredAttribute(component);

				Object type = component.getAttributes().get("type");
				if (type != null && INPUT_RANGE_TYPES.contains(type.toString())) {
					writeHtml5Attributes(INPUT_RANGE_ATTRS, component);
				}
			} else if (component instanceof HtmlInputSecret && "input".equals(name)) {
				writeHtml5Attributes(INPUT_PASSWORD_ATTRS, component);
				writeRequiredAttribute(component);
			} else if (component instanceof HtmlInputTextarea && "textarea".equals(name)) {
				writeHtml5Attributes(TEXTAREA_ATTRS, component);
				writeRequiredAttribute(component);
			} else if (isUISelect(component) && ("input".equals(name) || "select".equals(name))) {
				writeHtml5Attributes(SELECT_ATTRS, component);
				writeRequiredAttribute(component);
			}
		} else if (component instanceof UICommand && "input".equals(name)) {
			writeHtml5Attributes(BUTTON_ATTRIBUTES, component);
		}
	}

	@Override
	public void writeAttribute(String name, Object value, String property) throws IOException {
		if ("type".equals(name) && "text".equals(value)) {
			UIComponent component = getCurrentComponent();

			if (component instanceof HtmlInputText) {
				String type = (String)component.getAttributes().get("type");
				super.writeAttribute(name, type, null);
				return;
			}
		}

		super.writeAttribute(name, value, property);
	}

	protected UIComponent getCurrentComponent() {
		return UIComponent.getCurrentComponent(ctx);
	}

	@Override
	public ResponseWriter getWrapped() {
		return wrapped;
	}

	protected boolean isUISelect(UIComponent component) {
		return component instanceof UISelectBoolean || component instanceof UISelectOne	|| component instanceof UISelectMany;
	}

	private void writeHtml5Attributes(Set<String> names, UIComponent component) throws IOException {
		Map<String, Object> attributes = component.getAttributes();
		for (String name : names) {
			Object value = attributes.get(name);

			if (value != null) {
				if (isBoolean(value)) {
					if (Boolean.valueOf(value.toString())) super.writeAttribute(name, "", null);
				} else {
					super.writeAttribute(name, value, null);
				}
			}
		}
	}

	private void writeRequiredAttribute(UIComponent component) throws IOException {
		if (component instanceof UIInput) {
			UIInput input = (UIInput) component;
			if (input.isRequired()) {
				super.writeAttribute("required", "", "required");
			}
		}
	}

	private static Set<String> set(String... values) {
		Set<String> set = new HashSet<String>();
		Collections.addAll(set, values);
		return set;
	}

	private boolean isBoolean(Object o) {
		return "true".equalsIgnoreCase(o.toString()) || "false".equalsIgnoreCase(o.toString());
	}
}
