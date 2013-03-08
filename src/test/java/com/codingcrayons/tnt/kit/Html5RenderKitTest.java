package com.codingcrayons.tnt.kit;

import java.io.Writer;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.RenderKit;

import static org.mockito.Mockito.mock;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class Html5RenderKitTest {

	protected RenderKit wrapped;
	protected FacesContext ctx;
	protected Html5RenderKit kit;

	@BeforeMethod
	public void setUp() {
		wrapped = mock(RenderKit.class);
		ctx = mock(FacesContext.class);
		kit = new Html5RenderKit(wrapped, ctx);
	}

	@Test
	public void shouldCreateHtml5RenderKit() {
		ResponseWriter actual = kit.createResponseWriter(mock(Writer.class), "foo", "bar");
		assertTrue(actual instanceof Html5ResponseWriter, "Html5RenderKit must creates Html5ResponseWriter");
	}

	@Test
	public void shouldReturnWrappedRenderKit() {
		assertEquals(kit.getWrapped(), wrapped);
	}
}
