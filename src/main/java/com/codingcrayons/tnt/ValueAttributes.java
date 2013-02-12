package com.codingcrayons.tnt;

public enum ValueAttributes {

	accesskey,
	alt,
	autocomplete,
	dir,
	label,
	lang,
	maxlength,
	onblur,
	onchange,
	onclick,
	ondblclick,
	onfocus,
	onkeydown,
	onkeypress,
	onkeyup,
	onmousedown,
	onmousemove,
	onmouseout,
	onmouseover,
	onmouseup,
	onselect,
	placeholder,
	size,
	style,
	styleClass("class"),
	tabindex,
	title;

	private String html;

	private ValueAttributes() {
	}

	private ValueAttributes(String html) {
		this.html = html;
	}

	public String html() {
		return html == null ? name() : html;
	}
}
