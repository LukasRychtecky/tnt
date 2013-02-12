package com.codingcrayons.tnt;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;

public class Html5InputRenderer extends Renderer {

	public static final String TAG = "input";
	public static final String ATTR_ID = "id";
	public static final String ATTR_NAME = "name";
	public static final String ATTR_VALUE = "value";
	public static final String ATTR_TYPE = "type";

	@Override
	public void decode(FacesContext context, UIComponent component) {
		Html5Input input = (Html5Input) component;

		if (input.isDisabled() || input.isReadonly()) {
			return;
		}

		String clientId = input.getClientId(context);
		String submittedValue = context.getExternalContext().getRequestParameterMap().get(clientId);

		if (submittedValue != null) {
			input.setSubmittedValue(submittedValue);
		}
	}

	@Override
	public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
		Html5Input input = (Html5Input) component;

		ResponseWriter writer = context.getResponseWriter();
		String clientId = input.getClientId(context);

		writer.startElement(TAG, null);
		writer.writeAttribute(ATTR_ID, clientId, null);
		writer.writeAttribute(ATTR_NAME, clientId, null);
		writer.writeAttribute(ATTR_TYPE, input.getAttributes().get(ATTR_TYPE), ATTR_TYPE);

		Object value = input.getValue();
		if (value != null) {
			writer.writeAttribute(ATTR_VALUE, value, null);
		}

		for (ValueFreeAttributes attr : ValueFreeAttributes.values()) {
			if (input.shouldRenderHtmlAttribute(attr)) {
				writer.writeAttribute(attr.name(), "", attr.name());
			}
		}

		for (ValueAttributes attr : ValueAttributes.values()) {
			if (input.getHtmlAttributeValue(attr) != null) {
				writer.writeAttribute(attr.html(), input.getHtmlAttributeValue(attr), attr.name());
			}
		}

		writer.endElement(TAG);
	}
}
