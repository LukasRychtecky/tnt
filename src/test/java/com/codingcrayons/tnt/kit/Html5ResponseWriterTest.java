package com.codingcrayons.tnt.kit;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

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

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class Html5ResponseWriterTest {
	
	private static final String ATTR_REQUIRED = "required";

	protected Html5ResponseWriter writer;
	protected ResponseWriter wrapped;
	protected FacesContext ctx;
	protected Map<Object, Object> attrs;
	protected Map<String, Object> inputAttrs;

	@BeforeMethod
	public void setUp() {
		wrapped = mock(ResponseWriter.class);
		ctx = mock(FacesContext.class);
		attrs = new HashMap<Object, Object>();
		inputAttrs = new HashMap<String, Object>();
		when(ctx.getAttributes()).thenReturn(attrs);
		writer = new Html5ResponseWriter(wrapped, ctx);
	}

	@Test(dataProvider = "provideInputTypes")
	public void shouldWriteGivenTypeOfInputText(String type) throws IOException {
		String attr = "type";
		HtmlInputText input = mock(HtmlInputText.class);
		attrs.put(UIComponent.CURRENT_COMPONENT, input);

		Map<String, Object> inputAttrs = new HashMap<String, Object>();
		inputAttrs.put(attr, type);
		when(input.getAttributes()).thenReturn(inputAttrs);

		writer.writeAttribute(attr, "text", null);
		verify(wrapped).writeAttribute(attr, type, null);
		verify(wrapped, never()).writeAttribute(attr, "text", null);
	}

	@DataProvider
	public String[][] provideInputTypes() {
		return new String[][] {
			{"color"}, {"search"}, {"whatever"}
		};
	}

	@Test
	public void shouldWriteTypeTextOfInputText() throws IOException {
		String type = "text";
		String attr = "type";
		HtmlInputText input = mock(HtmlInputText.class);
		attrs.put(UIComponent.CURRENT_COMPONENT, input);

		inputAttrs.put(attr, type);
		when(input.getAttributes()).thenReturn(inputAttrs);

		writer.writeAttribute(attr, "text", null);
		verify(wrapped).writeAttribute(attr, type, null);
	}

	@Test
	public void shouldCallParentsWriteAttribute() throws IOException {
		String attr = "type";
		HtmlInputText input = mock(HtmlInputText.class);
		attrs.put(UIComponent.CURRENT_COMPONENT, input);

		writer.writeAttribute(attr, "foo", null);
		verify(input, never()).getAttributes();
	}

	@Test
	public void shouldReturnWrappedWriter() {
		assertEquals(writer.getWrapped(), wrapped);
	}

	@Test
	public void cloneMustReturnHtml5ResponseWriter() {
		ResponseWriter actual = writer.cloneWithWriter(mock(Writer.class));
		assertTrue(actual instanceof Html5ResponseWriter, "Clone must be Html5ResponseWriter");
	}

	@Test
	public void writeFormAttributes() throws IOException {
		UIForm form = mock(UIForm.class);
		String name = "form";
		String value = "on";

		String attr = "autocomplete";
		when(form.getAttributes()).thenReturn(inputAttrs);
		inputAttrs.put(attr, value);

		writer.startElement(name, form);
		verify(wrapped).writeAttribute(attr, value, null);
	}

	@Test
	public void writeRequiredInputTextAttributes() throws IOException {
		HtmlInputText input = mock(HtmlInputText.class);
		String name = "input";
		when(input.getAttributes()).thenReturn(inputAttrs);
		when(input.isRequired()).thenReturn(true);

		String autofocus = "autofocus";
		String autofocusValue = "true"; // value can be String
		inputAttrs.put(autofocus, autofocusValue);
		String placeholder = "placeholder";
		inputAttrs.put(placeholder, placeholder);

		writer.startElement(name, input);
		verify(wrapped).writeAttribute(autofocus, "", null);
		verify(wrapped).writeAttribute(placeholder, placeholder, null);
		verify(wrapped).writeAttribute(ATTR_REQUIRED, "", ATTR_REQUIRED);
	}

	@Test
	public void writeInputNumberAttributes() throws IOException {
		HtmlInputText input = mock(HtmlInputText.class);
		String name = "input";
		when(input.getAttributes()).thenReturn(inputAttrs);
		inputAttrs.put("type", "number");

		String min = "min";
		int minValue = 0;
		inputAttrs.put(min, minValue);
		String max = "max";
		int maxValue = 10;
		inputAttrs.put(max, maxValue);
		String step = "step";
		int stepValue = 2;
		inputAttrs.put(step, stepValue);

		writer.startElement(name, input);
		verify(wrapped).writeAttribute(min, minValue, null);
		verify(wrapped).writeAttribute(max, maxValue, null);
		verify(wrapped).writeAttribute(step, stepValue, null);
		verify(wrapped, never()).writeAttribute(ATTR_REQUIRED, "", ATTR_REQUIRED);
	}

	@Test
	public void writeInputSecretAttributes() throws IOException {
		HtmlInputSecret input = mock(HtmlInputSecret.class);
		String name = "input";
		when(input.getAttributes()).thenReturn(inputAttrs);

		String pattern = "pattern";
		String patternValue = "[A-Za-z]{3}";
		inputAttrs.put(pattern, patternValue);

		writer.startElement(name, input);
		verify(wrapped).writeAttribute(pattern, patternValue, null);
		verify(wrapped, never()).writeAttribute(ATTR_REQUIRED, "", ATTR_REQUIRED);
	}

	@Test
	public void writeTextAreaAttributes() throws IOException {
		HtmlInputTextarea textarea = mock(HtmlInputTextarea.class);
		String name = "textarea";
		when(textarea.getAttributes()).thenReturn(inputAttrs);

		String maxlength = "maxlength";
		int maxlengthValue = 20;
		inputAttrs.put(maxlength, maxlengthValue);
		String autofocus = "autofocus";
		Boolean autofocusValue = Boolean.FALSE;
		inputAttrs.put(autofocus, autofocusValue);
		String spellcheck = "spellcheck";
		String spellcheckValue = "true";
		inputAttrs.put(spellcheck, spellcheckValue);
		String wrap = "wrap";
		String wrapValue = "hard";
		inputAttrs.put(wrap, wrapValue);

		writer.startElement(name, textarea);
		verify(wrapped).writeAttribute(maxlength, maxlengthValue, null);
		verify(wrapped, never()).writeAttribute(autofocus, "", null);
		verify(wrapped).writeAttribute(spellcheck, "", null);
		verify(wrapped).writeAttribute(wrap, wrapValue, null);
		verify(wrapped, never()).writeAttribute(ATTR_REQUIRED, "", ATTR_REQUIRED);
	}

	@Test(dataProvider = "provideSelects")
	public void writeSelectAttributes(UIInput select) throws IOException {
		String name = "select";
		when(select.getAttributes()).thenReturn(inputAttrs);
		String autofocus = "autofocus";
		Boolean autofocusValue = Boolean.FALSE;
		inputAttrs.put(autofocus, autofocusValue);

		writer.startElement(name, select);
		verify(wrapped, never()).writeAttribute(autofocus, "", null);
		verify(wrapped, never()).writeAttribute(ATTR_REQUIRED, "", ATTR_REQUIRED);
	}

	@DataProvider
	public UIInput[][] provideSelects() {
		return new UIInput[][] {
			{mock(UISelectOne.class)}, {mock(UISelectMany.class)}
		};
	}

	@Test
	public void writeRequiredCheckboxAttributes() throws IOException {
		UISelectBoolean checkbox = mock(UISelectBoolean.class);
		String name = "select";
		when(checkbox.getAttributes()).thenReturn(inputAttrs);
		when(checkbox.isRequired()).thenReturn(true);

		String autofocus = "autofocus";
		Boolean autofocusValue = Boolean.TRUE;
		inputAttrs.put(autofocus, autofocusValue);

		writer.startElement(name, checkbox);
		verify(wrapped).writeAttribute(autofocus, "", null);
		verify(wrapped).writeAttribute(ATTR_REQUIRED, "", ATTR_REQUIRED);
	}

	@Test
	public void writeInputSubmitAttributes() throws IOException {
		UICommand submit = mock(UICommand.class);
		String name = "input";
		when(submit.getAttributes()).thenReturn(inputAttrs);

		String autofocus = "autofocus";
		Boolean autofocusValue = Boolean.FALSE;
		inputAttrs.put(autofocus, autofocusValue);

		writer.startElement(name, submit);
		verify(wrapped, never()).writeAttribute(autofocus, "", null);
	}
}
