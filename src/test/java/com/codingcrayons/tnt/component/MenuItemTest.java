package com.codingcrayons.tnt.component;

import java.io.IOException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MenuItemTest {

	protected MenuItem item;
	protected ResponseWriter writer;
	protected FacesContext context;

	@BeforeMethod
	public void setUp() {
		item = new MenuItem();
		writer = mock(ResponseWriter.class);
		context = mock(FacesContext.class);
		when(context.getResponseWriter()).thenReturn(writer);
	}

	@Test
	public void renderNoChildren() throws IOException {
		item.encodeAll(context);
		verify(writer, never()).startElement("li", null);
	}

	@Test
	public void renderOneChild() throws IOException {
		UIComponent output = mock(UIComponent.class);
		item.getChildren().add(output);

		item.encodeAll(context);
		verify(writer).startElement("li", null);
		verify(output).encodeAll(context);
		verify(writer).endElement("li");
	}
}
