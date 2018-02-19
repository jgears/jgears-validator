# jGears Validator

## Overview
jGears Validator provides general purpose validation functionality for Java.

## Getting Started

jGears validator requires Java 1.8 or later.
You can include the library in your project as a Maven dependency by adding the following lines to your pom.xml dependencies:

    <dependency>
		<groupId>jgears</groupId>
		<artifactId>jgears-validator</artifactId>
		<version>1.0</version>
	</dependency>

## Usage
The entry point is the Validator class. There are five validator types:
 1. **IntegerValidator:** for validating integer values.
 2. **DecimalValidator:** for validating floating point values.
 3. **TextValidator:** for validating text (length, regex, ...).
 4. **PojoValidator:** for validating objects containing one or more fields.
 5. **CollectionValidator:** for validating collections of values.

All these types can be accessed using static factory methods in Validator class. For example:

    Validator.integer()
		    .minValue(0)
		    .maxValue(10)
		    .validate(x);
This will validate that x is between 0 and 10.

The validate() method returns a list of strings representing the validation error messages. If the list is empty, the value is valid. If it contains one or more messages, the value is invalid.

Optionally, you can add a field name when declaring the validator, so that the error message refers to the field name specifically:

    Validator.integer("Age")
		    .minValue(18)
		    .maxValue(80)
		    .validate(10); // Returns "Age cannot be less than 18."
If you do not provide a name for the field, the error message will refer to it simply as "Field".

You can also provide your own error message for each validation rule:

    Validator.integer()
		    .minValue(18, "Too young!")
		    .maxValue(80, "Too old!")
		    .validate(10); // Returns "Too young!"
**Pojo Validator**

Pojo validator is a generic validator that can be used to validate custom Java objects.

For example, assume we have the following custom class:

    public class Point {
	    private int x;
	    private int y;
		
		public int getX() { return x; }
		public void setX(int x) { this.x = x; }
		public int getY() { return y; }
		public void setY(int y) { this.y = y; }
    }
We can build a validator for this class, for example, to ensure that x and y are between -10 and 10:

    Validator.pojo(Point.class)
		    .notNull()
		    .field(Point::getX, Validator.integer("X")
				    .minValue(-10)
				    .maxValue(10))
			.field(Point::getY, Validator.integer("Y")
					.minValue(-10)
					.maxValue(10))
			.validate(p);
**Collection Validator**

Collection validator can be used to validate a collection of items, while also validating each individual item. For example, to validate a non-empty list of non-empty strings:

    Validator.collection(String.class)
		    .notNull()
		    .minSize(1)
		    .item(Validator.text()
				    .notNull()
				    .minLength(1))
		    .validate(list);

