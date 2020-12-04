## To build to UML Console Application:

1. Compile UML-all.jar with the command `gradle build`
2. To run the program in GUI mode, use `java -jar ./build/libs/UML-all.jar`
3. To run the program in console mode, use `java -jar ./build/libs/UML-all.jar --cli`
4. Enjoy!


## CLI Mode
#### method(arguments) -- description
quit 			()      			-- Exits the program<br/>
help 			()      			-- Prints help message<br/>
save 			(filename) 			-- Saves the project into a save file<br/>
load 			(filename) 			-- Loads a project provided by the user<br/>


addClass 		(name)				-- Creates a new UML class with the given name<br/>
removeClass 		(class)				-- Deletes the class with the given name<br/>
renameClass 		(class newName)			-- Renames a given class to the given name<br/>
open 			(class)				-- Opens the given class to editing<br/>
close 			(class)				-- Closes the given class to editint<br/>
<br/>
addField 		(class visibilty name type)		-- Adds a field to the given class<br/>
removeField		(class field)			-- Deletes the given field from the given class<br/>
renameField		(class oldName newName) 		-- Renames the given field in the given class to the given name<br/>
changeFieldType		(class field newType)		-- Changes the data type of the given field in the given class<br/>
changeFieldVisibility   (class field newVisibility)     -- Changes the visibility of the given field in the given class<br/>
<br/>
addMethod		(class visibilty returnType name)	-- Adds a function to the given class<br/>
removeMethod		(class method)			-- Deletes the given function from the given class<br/>
renameMethod		(class oldName newName)		-- Renames the given function in the given class to the given name<br/>
changeMethodType	(class method newType)		-- Changes the return type of the given method in the given class<br/>
changeMethodVisibility  (class method newVisibility)    -- Changes the visibility of the given method in the given class<br/>
<br/>
addParameter		(class method paramType paramName)	-- Adds a parameter to the given function in the given class<br/>
removeParameter		(class method param)		-- Deletes a parameter from the given function in the given class<br/>
renameParameter		(class method param newName)	-- Renames a parameter in the given function in the given class<br/>
changeParameterType	(class method param newType)	-- Changes the data type of the given parameter in the given method in the given class<br/>
<br/>
addRelationship 	(class class type)			--Adds a relationship between two given classes<br/>
removeRelationship 	(class class)		--Deletes a relationshop between two given classes<br/>
changeRelationshipType 	(class class newType)			--Changes a relationship between two given classes<br/>
<br/>
printClass		(class)				--Print all the attributes of the given class<br/>
printClasses		()				--Prints the names of all the existing classes<br/>
printRelationships	()				--Prints all the existing relationships<br/>
<br/>
## GUI Mode
Buttons			User Input			Description
-------			----------			-----------
#### File
  undo 			<>				--Undo last edit<br/>
  redo			<>				--Redo last undo<br/>
  save			<if no named file: name file>	--Saves file <br/>
  save as		<file>				--Saves named file<br/>
  load			<file>				--Loads file<br/>
  exit			<>				--Closes UML<br/>

#### Class
  open class		<class name>			--Opens the class panel for editing <br/>
  close class		<class name>			--Closes class so no editing can be done<br/>
  create class		<class name>			--Makes a class panel with its name<br/>
  delete class		<class name>			--Deletes class panel<br/>
  rename class		<class name>			--Renames a class<br/>
  
#### Attribute
  create field		<className, field vis / name / type>	--Adds a field to a class <br/>
  delete field		<className, fieldName> 			--Deletes field in a class <br/>
  rename field		<className, FieldName, newFieldName> 	--Renames a field in a class<br/>
  change field type	<className, fieldName, fieldType>	--Changes a field type in class<br/>
  change field vis	<className, fieldName, vis>		--Changes visability of a field<br/>
<br/>
  create method		<className, method vis / name / type>	--Adds a method to a class <br/>
  delete method		<className, methodName> 		--Deletes method in a class<br/>
  rename method		<className, methodName, newMethodName> 	--Renames a method in a class<br/>
  change method type	<className, methodName, methodType>	--Changes a method type in class<br/>
  change method vis	<className, methodName, vis>		--Changes visability of a method<br/>
<br/>
  create param		<className, methodName, param name / type>		--Adds a parameter to a method in a class<br/>
  delete param		<className, methodName, paramName>			--Deletes parameter in a method  <br/>
  rename param		<className, methodName, paramName, oldParamName>	--Renames a parameter in a method	<br/>
  change param type	<className, methodName, paramName, newParamType>	--Changes the parameter type in a method<br/>
  
#### Relationship
  create relationship	<classNameTo, classNameFrom, type>	--Adds a relationship with given type between two classes<br/>
  change type		<classNameTo, classNameFrom, newType>	--Changes the relationship type between two classes	<br/>
  delete relationship 	<classNameTo, classNameFrom>		--Deletes relationshipe between two classes<br/>
  show relationships	<>					--Displays all current relationships between classes<br/>
