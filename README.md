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
<h:inputText id="email" placeholder="Email address" value="#{user.email}"/>
```

A required text input with autofocus

```xml
<h:inputText id="username" required="true" autofocus="true" value="#{user.username}"/>
```

A text input with a pattern validator

```xml
<h:inputText id="username" pattern="[a-z][a-z]*" value="#{user.username}"/>
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

