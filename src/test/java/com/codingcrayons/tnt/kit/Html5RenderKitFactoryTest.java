package com.codingcrayons.tnt.kit;

import java.util.Iterator;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.faces.context.FacesContext;
import javax.faces.render.RenderKit;
import javax.faces.render.RenderKitFactory;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class Html5RenderKitFactoryTest {

	protected Html5RenderKitFactory factory;
	protected FacesContext ctx;
	protected RenderKitFactory wrapped;

	@BeforeMethod
	public void setUp() {
		wrapped = mock(RenderKitFactory.class);
		ctx = mock(FacesContext.class);
		factory = new Html5RenderKitFactory(wrapped);
	}

	@Test
	public void html5RenderKitOverridesHtmlBasicRenderKit() {
		String id = Html5RenderKitFactory.HTML_BASIC_RENDER_KIT;
		RenderKit actual = factory.getRenderKit(ctx, id);
		assertTrue(actual instanceof Html5RenderKit, "Html5RenderKit must overrides default HtmlBasicRenderKit");
	}

	@Test
	public void shouldReturnRequestedRenderKit() {
		String id = "foo";
		RenderKit foo = mock(RenderKit.class);
		when(wrapped.getRenderKit(ctx, id)).thenReturn(foo);
		assertEquals(factory.getRenderKit(ctx, id), foo);
	}

	@Test
	public void shouldReturnWrappedRenderKitIds() {
		Iterator<String> it = mock(Iterator.class);
		when(wrapped.getRenderKitIds()).thenReturn(it);
		assertEquals(factory.getRenderKitIds(), it);
	}

	@Test
	public void shouldCallWrappedAddRenderKit() {
		String id = "foo";
		RenderKit kit = mock(RenderKit.class);
		factory.addRenderKit(id, kit);
		verify(wrapped).addRenderKit(id, kit);
	}
}
