package com.codingcrayons.tnt.component;

import java.io.IOException;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponent;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

@FacesComponent(value = "tnt.MenuItem")
public class MenuItem extends UIOutput {

	@Override
	public void encodeAll(FacesContext context) throws IOException {
		ResponseWriter writer = context.getResponseWriter();
		for (UIComponent child : getChildren()) {
			writer.startElement("li", null);
			child.encodeAll(context);
			writer.endElement("li");
		}
	}
}
