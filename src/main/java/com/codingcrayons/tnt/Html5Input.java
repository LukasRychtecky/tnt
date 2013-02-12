package com.codingcrayons.tnt;

import javax.faces.component.FacesComponent;
import javax.faces.component.html.HtmlInputText;

@FacesComponent(value = "tnt.input")
public class Html5Input extends HtmlInputText {

	public Html5Input() {
		super();
		setRendererType("com.codingcrayons.tnt.Html5InputRenderer");
	}

	@Override
	public String getFamily() {
		return "com.codingcrayons.tnt";
	}

	public Object getHtmlAttributeValue(ValueAttributes attr) {
		return getStateHelper().eval(attr, null);
	}

	public boolean shouldRenderHtmlAttribute(ValueFreeAttributes attr) {
		Object value = getStateHelper().eval(attr, null);
		return value != null && value.equals(Boolean.TRUE);
	}

	private void put(ValueAttributes attr, Object value) {
		getStateHelper().put(attr, value);
	}

	private void put(ValueFreeAttributes attr, Object value) {
		getStateHelper().put(attr, value);
	}

	private String getString(ValueAttributes attr) {
		return (String) getStateHelper().eval(attr, null);
	}

	private Integer getInt(ValueAttributes attr) {
		return (Integer) getStateHelper().eval(attr, null);
	}

	private Boolean getBoolean(ValueFreeAttributes attr) {
		return (Boolean) getStateHelper().eval(attr, Boolean.FALSE);
	}

	@Override
	public String getAccesskey() {
		return getString(ValueAttributes.accesskey);
	}

	@Override
	public void setAccesskey(String accesskey) {
		put(ValueAttributes.accesskey, accesskey);
	}

	@Override
	public String getAlt() {
		return getString(ValueAttributes.alt);
	}

	@Override
	public void setAlt(String alt) {
		put(ValueAttributes.alt, alt);
	}

	@Override
	public String getAutocomplete() {
		return getString(ValueAttributes.autocomplete);
	}

	@Override
	public void setAutocomplete(String autocomplete) {
		put(ValueAttributes.autocomplete, autocomplete);
	}

	public boolean isAutofocus() {
		return getBoolean(ValueFreeAttributes.autofocus);
	}

	public void setAutofocus(boolean autofocus) {
		put(ValueFreeAttributes.autofocus, autofocus);
	}

	@Override
	public String getDir() {
		return getString(ValueAttributes.dir);
	}

	@Override
	public void setDir(String dir) {
		put(ValueAttributes.dir, dir);
	}

	@Override
	public boolean isDisabled() {
		return getBoolean(ValueFreeAttributes.disabled);
	}

	@Override
	public void setDisabled(boolean disabled) {
		put(ValueFreeAttributes.disabled, disabled);
	}

	@Override
	public String getLabel() {
		return getString(ValueAttributes.label);
	}

	@Override
	public void setLabel(String label) {
		put(ValueAttributes.label, label);
	}

	@Override
	public String getLang() {
		return getString(ValueAttributes.lang);
	}

	@Override
	public void setLang(String lang) {
		put(ValueAttributes.lang, lang);
	}

	@Override
	public int getMaxlength() {
		return getInt(ValueAttributes.maxlength);
	}

	@Override
	public void setMaxlength(int maxlength) {
		put(ValueAttributes.maxlength, maxlength);
	}

	@Override
	public String getOnblur() {
		return getString(ValueAttributes.onblur);
	}

	@Override
	public void setOnblur(String onblur) {
		put(ValueAttributes.onblur, onblur);
	}

	@Override
	public String getOnchange() {
		return getString(ValueAttributes.onchange);
	}

	@Override
	public void setOnchange(String onchange) {
		put(ValueAttributes.onchange, onchange);
	}

	@Override
	public String getOnclick() {
		return getString(ValueAttributes.onclick);
	}

	@Override
	public void setOnclick(String onclick) {
		put(ValueAttributes.onclick, onclick);
	}

	@Override
	public String getOndblclick() {
		return getString(ValueAttributes.ondblclick);
	}

	@Override
	public void setOndblclick(String ondblclick) {
		put(ValueAttributes.ondblclick, ondblclick);
	}

	@Override
	public String getOnfocus() {
		return getString(ValueAttributes.onfocus);
	}

	@Override
	public void setOnfocus(String onfocus) {
		put(ValueAttributes.onfocus, onfocus);
	}

	@Override
	public String getOnkeydown() {
		return getString(ValueAttributes.onkeydown);
	}

	@Override
	public void setOnkeydown(String onkeydown) {
		put(ValueAttributes.onkeydown, onkeydown);
	}

	@Override
	public String getOnkeypress() {
		return getString(ValueAttributes.onkeypress);
	}

	@Override
	public void setOnkeypress(String onkeypress) {
		put(ValueAttributes.onkeypress, onkeypress);
	}

	@Override
	public String getOnkeyup() {
		return getString(ValueAttributes.onkeyup);
	}

	@Override
	public void setOnkeyup(String onkeyup) {
		put(ValueAttributes.onkeyup, onkeyup);
	}

	@Override
	public String getOnmousedown() {
		return getString(ValueAttributes.onmousedown);
	}

	@Override
	public void setOnmousedown(String onmousedown) {
		put(ValueAttributes.onmousedown, onmousedown);
	}

	@Override
	public String getOnmousemove() {
		return getString(ValueAttributes.onmousemove);
	}

	@Override
	public void setOnmousemove(String onmousemove) {
		put(ValueAttributes.onmousemove, onmousemove);
	}

	@Override
	public String getOnmouseout() {
		return getString(ValueAttributes.onmouseout);
	}

	@Override
	public void setOnmouseout(String onmouseout) {
		put(ValueAttributes.onmouseout, onmouseout);
	}

	@Override
	public String getOnmouseover() {
		return getString(ValueAttributes.onmouseover);
	}

	@Override
	public void setOnmouseover(String onmouseover) {
		put(ValueAttributes.onmouseover, onmouseover);
	}

	@Override
	public String getOnmouseup() {
		return getString(ValueAttributes.onmouseup);
	}

	@Override
	public void setOnmouseup(String onmouseup) {
		put(ValueAttributes.onmouseup, onmouseup);
	}

	@Override
	public String getOnselect() {
		return getString(ValueAttributes.onselect);
	}

	@Override
	public void setOnselect(String onselect) {
		put(ValueAttributes.onselect, onselect);
	}

	@Override
	public boolean isReadonly() {
		return getBoolean(ValueFreeAttributes.readonly);
	}

	@Override
	public void setReadonly(boolean readonly) {
		put(ValueFreeAttributes.readonly, readonly);
	}

	@Override
	public boolean isRequired() {
		return getBoolean(ValueFreeAttributes.required);
	}

	@Override
	public void setRequired(boolean required) {
		put(ValueFreeAttributes.required, required);
	}

	@Override
	public int getSize() {
		return getInt(ValueAttributes.size);
	}

	@Override
	public void setSize(int size) {
		put(ValueAttributes.size, size);
	}

	@Override
	public String getStyle() {
		return getString(ValueAttributes.style);
	}

	@Override
	public void setStyle(String style) {
		put(ValueAttributes.style, style);
	}

	@Override
	public String getStyleClass() {
		return getString(ValueAttributes.styleClass);
	}

	@Override
	public void setStyleClass(String styleClass) {
		put(ValueAttributes.styleClass, styleClass);
	}

	@Override
	public String getTabindex() {
		return getString(ValueAttributes.tabindex);
	}

	@Override
	public void setTabindex(String tabindex) {
		put(ValueAttributes.tabindex, tabindex);
	}

	@Override
	public String getTitle() {
		return getString(ValueAttributes.title);
	}

	@Override
	public void setTitle(String title) {
		put(ValueAttributes.title, title);
	}


}
