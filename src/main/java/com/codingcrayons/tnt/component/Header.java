package com.codingcrayons.tnt.component;

import com.codingcrayons.tnt.core.DependenciesManager;
import java.io.IOException;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponent;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

@FacesComponent(value = "tnt.Header")
public class Header extends UIOutput {

	public Header() {
		super();
		DependenciesManager.getInstance(FacesContext.getCurrentInstance()).insertMetaViewport();
		DependenciesManager.getInstance(FacesContext.getCurrentInstance()).insertTNTCSS();
	}

	@Override
	public void encodeBegin(FacesContext context) throws IOException {
		ResponseWriter writer = context.getResponseWriter();
		writer.startElement("header", null);
		writer.writeAttribute("class", "tnt-header", null);
	}

	@Override
	public void encodeChildren(FacesContext context) throws IOException {
		for (UIComponent child : getChildren()) {
			child.encodeAll(context);
		}
	}

	@Override
	public void encodeEnd(FacesContext context) throws IOException {
		ResponseWriter writer = context.getResponseWriter();
		writer.endElement("header");
	}
}
