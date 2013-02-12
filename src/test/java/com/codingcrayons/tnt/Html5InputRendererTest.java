package com.codingcrayons.tnt;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.jboss.test.faces.mockito.MockFacesEnvironment;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import javax.faces.context.ResponseWriter;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class Html5InputRendererTest {

	private MockFacesEnvironment env;
	private ResponseWriter writer;
	private Map<String, Object> attrs;
	private Html5Input input;
	private String clientId = "clientId";

	@BeforeMethod
	public void setUp() {
		env = new MockFacesEnvironment();
		writer = mock(ResponseWriter.class);
		input = mock(Html5Input.class);
		attrs = new HashMap<String, Object>();
		when(input.getAttributes()).thenReturn(attrs);
		when(env.getFacesContext().getResponseWriter()).thenReturn(writer);
		when(input.getClientId(env.getFacesContext())).thenReturn(clientId);
	}

	@AfterMethod
	public void tearDown() {
		env.release();
	}

	@Test(dataProvider = "provideInputTypes")
	public void renderRequiredInput(String type) throws IOException {
		attrs.put(Html5InputRenderer.ATTR_TYPE, type);
		Boolean required = Boolean.TRUE;
		when(input.shouldRenderHtmlAttribute(ValueFreeAttributes.required)).thenReturn(required);

		Html5InputRenderer renderer = new Html5InputRenderer();
		renderer.encodeEnd(env.getFacesContext(), input);
		verify(writer).startElement(Html5InputRenderer.TAG, null);
		verify(writer).writeAttribute(Html5InputRenderer.ATTR_ID, clientId, null);
		verify(writer).writeAttribute(Html5InputRenderer.ATTR_NAME, clientId, null);
		verify(writer).writeAttribute(ValueFreeAttributes.required.name(), "", ValueFreeAttributes.required.name());
		verify(writer).writeAttribute(Html5InputRenderer.ATTR_TYPE, type, Html5InputRenderer.ATTR_TYPE);
	}

	@Test(dataProvider = "provideInputTypes")
	public void renderReadOnlyInput(String type) throws IOException {
		attrs.put(Html5InputRenderer.ATTR_TYPE, type);
		Boolean readOnly = Boolean.TRUE;
		when(input.shouldRenderHtmlAttribute(ValueFreeAttributes.readonly)).thenReturn(readOnly);

		Html5InputRenderer renderer = new Html5InputRenderer();
		renderer.encodeEnd(env.getFacesContext(), input);
		verify(writer).startElement(Html5InputRenderer.TAG, null);
		verify(writer).writeAttribute(Html5InputRenderer.ATTR_ID, clientId, null);
		verify(writer).writeAttribute(Html5InputRenderer.ATTR_NAME, clientId, null);
		verify(writer).writeAttribute(ValueFreeAttributes.readonly.name(), "", ValueFreeAttributes.readonly.name());
		verify(writer).writeAttribute(Html5InputRenderer.ATTR_TYPE, type, Html5InputRenderer.ATTR_TYPE);
		verify(writer).endElement(Html5InputRenderer.TAG);
	}

	@Test(dataProvider = "provideInputTypes")
	public void renderAutofocusedInput(String type) throws IOException {
		attrs.put(Html5InputRenderer.ATTR_TYPE, type);
		Boolean autofocus = Boolean.TRUE;
		when(input.shouldRenderHtmlAttribute(ValueFreeAttributes.autofocus)).thenReturn(autofocus);

		Html5InputRenderer renderer = new Html5InputRenderer();
		renderer.encodeEnd(env.getFacesContext(), input);
		verify(writer).startElement(Html5InputRenderer.TAG, null);
		verify(writer).writeAttribute(Html5InputRenderer.ATTR_ID, clientId, null);
		verify(writer).writeAttribute(Html5InputRenderer.ATTR_NAME, clientId, null);
		verify(writer).writeAttribute(ValueFreeAttributes.autofocus.name(), "", ValueFreeAttributes.autofocus.name());
		verify(writer).writeAttribute(Html5InputRenderer.ATTR_TYPE, type, Html5InputRenderer.ATTR_TYPE);
		verify(writer).endElement(Html5InputRenderer.TAG);
	}

	@DataProvider
	public Object[][] provideInputTypes() {
		return new Object[][] {
			{"text"},
			{"password"},
			{"file"},
			{"image"},
			{"datetime"},
			{"datetime-local"},
			{"date"},
			{"month"},
			{"time"},
			{"week"},
			{"number"},
			{"range"},
			{"email"},
			{"url"},
			{"search"},
			{"tel"},
			{"color"}
		};
	}

	@Test(dataProvider = "provideJSCallbacks")
	public void renderTextInputWithJSCallback(ValueAttributes attr, String callback) throws IOException {
		String type = "text";
		attrs.put(Html5InputRenderer.ATTR_TYPE, type);
		when(input.getHtmlAttributeValue(attr)).thenReturn(callback);

		Html5InputRenderer renderer = new Html5InputRenderer();
		renderer.encodeEnd(env.getFacesContext(), input);
		verify(writer).startElement(Html5InputRenderer.TAG, null);
		verify(writer).writeAttribute(Html5InputRenderer.ATTR_ID, clientId, null);
		verify(writer).writeAttribute(Html5InputRenderer.ATTR_NAME, clientId, null);
		verify(writer).writeAttribute(attr.name(), callback, attr.name());
		verify(writer).writeAttribute(Html5InputRenderer.ATTR_TYPE, type, Html5InputRenderer.ATTR_TYPE);
		verify(writer).endElement(Html5InputRenderer.TAG);
	}

	@DataProvider
	public Object[][] provideJSCallbacks() {
		return new Object[][] {
			{ValueAttributes.onblur, "alert('blur');"},
			{ValueAttributes.onchange, "alert('change');"},
			{ValueAttributes.onclick, "alert('click');"},
			{ValueAttributes.ondblclick, "alert('dblclick');"},
			{ValueAttributes.onfocus, "alert('focus');"},
			{ValueAttributes.onkeydown, "alert('keydown');"},
			{ValueAttributes.onkeypress, "alert('keypress');"},
			{ValueAttributes.onkeyup, "alert('keyup');"},
			{ValueAttributes.onmousedown, "alert('mousedown');"},
			{ValueAttributes.onmousemove, "alert('mousemove');"},
			{ValueAttributes.onmouseout, "alert('mouseout');"},
			{ValueAttributes.onmouseover, "alert('mouseover');"},
			{ValueAttributes.onmouseup, "alert('mouseup');"},
			{ValueAttributes.onselect, "alert('onselect');"}
		};
	}
}
