package com.codingcrayons.tnt.component;

import java.io.IOException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import javax.faces.component.UISelectItem;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertFalse;

public class HorizontalMenuTest {

	protected HorizontalMenu menu;
	protected ResponseWriter writer;
	protected FacesContext context;
	protected MenuItem[] menuItems;

	@BeforeMethod
	public void setUp() {
		menu = new HorizontalMenu();
		writer = mock(ResponseWriter.class);
		context = mock(FacesContext.class);
		when(context.getResponseWriter()).thenReturn(writer);
		menuItems = new MenuItem[] {
			mock(MenuItem.class), mock(MenuItem.class)
		};
		for (MenuItem item : menuItems) {
			menu.getChildren().add(item);
		}
	}

	@Test(dataProvider = "provideSelectItems")
	public void renderMiniVersion(UISelectItem[] items) throws IOException {

		for (UISelectItem item : items) {
			menu.getChildren().add(item);
		}

		menu.setMini(true);
		menu.encodeAll(context);
		verify(writer).startElement("nav", null);
		verify(writer).writeAttribute("class", HorizontalMenu.NAV_CLASS, null);
		verify(writer).startElement("div", null);
		verify(writer).writeAttribute("class", HorizontalMenu.SELECT_CLASS, null);
		verify(writer).startElement("select", null);
		verify(writer).writeAttribute(eq("onchange"), anyString(), eq((String)null));

		verifyOption(writer, items[0], items.length);
		verifyOption(writer, items[1], items.length);

		verify(writer).endElement("select");
		verify(writer).endElement("div");

		verify(writer).startElement("ul", null);
		verify(writer).writeAttribute("class", "hidden", null);
		verify(writer).endElement("ul");
		verify(menuItems[0]).encodeAll(context);
		verify(menuItems[1]).encodeAll(context);
		verify(writer).endElement("ul");
		verify(writer).endElement("nav");
	}

	protected void verifyOption(ResponseWriter writer, UISelectItem item, int times) throws IOException {
		verify(writer, times(times)).startElement("option", null);
		verify(writer).writeAttribute("value", item.getItemValue(), null);
		verify(writer).writeText(item.getItemLabel(), null);
		verify(writer, times(times)).endElement("option");
	}

	@Test
	public void renderResponsiveVersion() throws IOException {
		menu.encodeAll(context);
		assertFalse(menu.isMini());
		verify(writer).startElement("nav", null);
		verify(writer).writeAttribute("class", HorizontalMenu.NAV_CLASS, null);
		verify(writer, never()).startElement("div", null);

		verify(writer).startElement("ul", null);
		verify(writer, never()).writeAttribute("class", "hidden", null);
		verify(writer).endElement("ul");
		verify(menuItems[0]).encodeAll(context);
		verify(menuItems[1]).encodeAll(context);
		verify(writer).endElement("ul");
		verify(writer).endElement("nav");
	}

	@DataProvider
	public UISelectItem[][][] provideSelectItems() {
		UISelectItem item1 = mock(UISelectItem.class);
		when(item1.getItemValue()).thenReturn("item1Value");
		when(item1.getItemLabel()).thenReturn("item1Label");

		UISelectItem item2 = mock(UISelectItem.class);
		when(item2.getItemValue()).thenReturn("item2Value");
		when(item2.getItemLabel()).thenReturn("item2Label");

		return new UISelectItem[][][] {
			{
				{item1, item2}
			}
		};
	}
}
