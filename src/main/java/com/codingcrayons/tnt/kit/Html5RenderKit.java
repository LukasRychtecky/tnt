package com.codingcrayons.tnt.kit;

import java.io.Writer;

import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.RenderKit;
import javax.faces.render.RenderKitWrapper;

public class Html5RenderKit extends RenderKitWrapper {

	protected RenderKit wrapped;
	protected FacesContext ctx;

	public Html5RenderKit(RenderKit wrapped, FacesContext ctx) {
		this.wrapped = wrapped;
		this.ctx = ctx;
	}

	@Override
	public ResponseWriter createResponseWriter(Writer writer, String contentTypeList, String characterEncoding) {
		return new Html5ResponseWriter(super.createResponseWriter(writer, contentTypeList, characterEncoding), ctx);
	}

	@Override
	public RenderKit getWrapped() {
		return wrapped;
	}
}
