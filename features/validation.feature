Feature: Candidate Version Validation by Platform

	Background:
		Given a "java" Candidate of Version "8u111" for platform "MAC_OSX" at "http://dl/8u111-b14/jdk-8u111-macosx-x64.dmg"
		And a "java" Candidate of Version "8u111" for platform "LINUX" at "http://dl/8u111-b14/jdk-8u111-linux-x64.tar.gz"
		And a "java" Candidate of Version "8u111" for platform "CYGWIN64" at "http://dl/8u111-b14/jdk-8u111-windows-x64.exe"
		And a "scala" Candidate of Version "2.12.0" for platform "UNIVERSAL" at "http://dl/scala/2.12.0/scala-2.12.0.zip"

	Scenario: Validation succeeds for a multi-platform binary on a known platform
		When I attempt validation at endpoint /candidates/java/8u111/linux
		Then a 200 status code is received
		And the response body is "valid"

	Scenario: Validation fails for a multi-platform binary on an unsupported platform
		When I attempt validation at endpoint /candidates/java/8u111/freebsd
		Then a 404 status code is received
		And the response body is "invalid"

	Scenario: Validation fails for a multi-platform binary on an unknown platform
		When I attempt validation at endpoint /candidates/java/8u111/commodore64
		Then a 404 status code is received
		And the response body is "invalid"

	Scenario: Validation succeeds for a universal binary on a know platform
		When I attempt validation at endpoint /candidates/scala/2.12.0/linux
		Then a 200 status code is received
		And the response body is "valid"

	Scenario: Validation fails for a universal binary on an unknown platform
		When I attempt validation at endpoint /candidates/scala/2.12.0/commodore64
		Then a 404 status code is received
		And the response body is "invalid"