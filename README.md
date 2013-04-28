TNT faces
=========

TNT faces is a lightweight JSF 2 extension adding HTML5 validation. TNT works without boilerplate! You don't need another components.

Features
--------

* input types: email, url, date, range, whatever you want
* attributes: autofocus, pattern, placeholder, etc.

Usage
-----

Put this code into your `pom.xml` and that's all folks!
```xml
<dependencies>
	<dependency>
		<groupId>com.codingcrayons</groupId>
			<artifactId>tnt</artifactId>
			<version>0.1.1</version>
	</dependency>
</dependencies>

<repositories>
	<repository>
		<id>aspectfaces-repo</id>
		<url>http://maven.codingcrayons.com</url>
	</repository>
</repositories>
```

Examples
--------

An email input with a placeholder:

```xml
<h:inputText id="email" type="email" placeholder="Email address" value="#{user.email}"/>
```

A required text input with autofocus

```xml
<h:inputText id="username" required="true" autofocus="true" value="#{user.username}"/>
```

A text input with a pattern validator

```xml
<h:inputText id="username" pattern="[a-z][a-z]*" value="#{user.username}"/>
```

Responsive horizontal menu
--------------------------

Components dependencies are loaded automatically. If you want to handle dependencies by yourself follow these points:

Put this line into `web.xml`:

```xml
<context-param>
	<param-name>TNT.AUTO_LOAD</param-name>
	<param-value>false</param-value>
</context-param>
```

And this line into your head:

```xml
<meta name="viewport" content="initial-scale=1, maximum-scale=1"/>
```

Add `tnt.css` into your css file, path: `tnt-prod.jar/META-INF/resources/css/tnt.css`

And at least the menu, namespace: `http://codingcrayons.com/tnt`

```xml
<tnt:horizontalMenu mini="true">
	<f:selectItem itemLabel="Foo" itemValue="http://foo.com"/>
	<f:selectItem itemLabel="Bar" itemValue="http://bar.com"/>
	<tnt:menuItem><a href="http://foo.com">Foo</a></tnt:menuItem>
	<tnt:menuItem><a href="http://bar.com">Bar</a></tnt:menuItem>
</tnt:horizontalMenu>
```

Build
-----

Development:

	$ mvn package

Production:

	$ mvn package -Pprod

Testing
-------

To run the tests:

	$ mvn test

