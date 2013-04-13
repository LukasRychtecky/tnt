package com.codingcrayons.tnt.component;

import java.io.IOException;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponent;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

@FacesComponent(value = "tnt.Article")
	public class Article extends UIOutput {

		@Override
		public void encodeBegin(FacesContext context) throws IOException {
			ResponseWriter writer = context.getResponseWriter();
			writer.startElement("div", null);
			writer.startElement("article", null);
			writer.writeAttribute("role", "article", null);
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
			writer.endElement("article");
			writer.endElement("div");
		}
}
