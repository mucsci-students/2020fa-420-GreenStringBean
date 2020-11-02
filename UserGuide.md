To build to UML Console Application:

1. Compile UML-all.jar with the command "gradle build"
2. To run the program in GUI mode, use "java -jar ./build/libs/UML-all.jar"
3. To run the program in console mode, use "java -jar ./build/libs/UML-all.jar --cli"
4. Enjoy!


--------------------CLI Mode--------------------------
Methods			Arguments			Description
-------			----------			------------
quit 			<>      			-- Exits the program
help 			<>      			-- Prints help message
save 			<filename> 			-- Saves the project into a save file
load 			<filename> 			-- Loads a project provided by the user

addClass 		<name> 				-- Creates a new UML class with the given name.
deleteClass 		<class>				-- Deletes the class with the given name.
renameClass 		<class newName>			-- Renames a given class to the given name.
open 			<class>				-- Opens the given class to editing.
close 			<class>				-- Closes the given class to editint.

addField 		<class name type> 		-- Adds a field to the given class
deleteField		<class field>			-- Deletes the given field from the given class
renameField		<class field newName> 		-- Renames the given field in the given class to the given name
changeFieldType		<class field newType>		-- Changes the data type of the given field in the given class
changeFieldVisibility   <class field newVisibility>     -- Changes the visibility of the given field in the given class

addMethod		<class name type>		-- Adds a function ot the given class
deleteMethod		<class method>			-- Deletes the given function from the given class
renameMethod		<class method newName>		-- Renames the given function in the given class to the given name
changeMethodType	<class method newType>		-- Changes the return type of the given method in the given class
changeMethodVisibility  <class method newVisibility>    -- Changes the visibility of the given method in the given class

addParameter		<class method name type>	-- Adds a parameter to the given function in the given class
deleteParameter		<class method param>		-- Deletes a parameter from the given function in the given class
renameParameter		<class method param newName>	-- Renames a parameter in the given function in the given class
changeParameterType	<class method param newType>	-- Changes the data type of the given parameter in the given method in the given class

addRelationship 	<classFrom classTo>			--Adds a relationship between two given classes
deleteRelationship 	<classFrom classTo>			--Deletes a relationshop between two given classes

printClass		<class>				--Print all the attributes of the given class
printClasses		<>				--Prints the names of all the existing classes
printFields		<class>				--Prints the names of all the existing fields in the given class
printMethods		<class>				--Prints the names of all the existing methods in the given class
printRelationships	<>				--Prints all the existing relationships

-------------------GUI Mode--------------------------
Buttons			User Input			Description
-------			----------			-----------
-File-----
  undo 			<>				--Undo last edit
  redo			<>				--Redo last undo
  save			<if no named file: name file>	--Saves file 
  save as		<file>				--Saves named file
  load			<file>				--Loads file
  exit			<>				--Closes UML

-Class-----
  open class		<class name>			--Opens the class panel for editing 
  close class		<class name>			--Closes class so no editing can be done
  create class		<class name>			--Makes a class panel with its name
  delete class		<class name>			--Deletes class panel
  rename class		<class name>			--Renames a class

-Attribute-----
  create field		<className, field vis / name / type>	--Adds a field to a class 
  delete field		<className, fieldName> 			--Deletes field in a class 
  rename field		<className, FieldName, newFieldName> 	--Renames a field in a class
  change field type	<className, fieldName, fieldType>	--Changes a field type in class
  change field vis	<className, fieldName, vis>		--Changes visability of a field

  create method		<className, method vis / name / type>	--Adds a method to a class 
  delete method		<className, methodName> 		--Deletes method in a class
  rename method		<className, methodName, newMethodName> 	--Renames a method in a class
  change method type	<className, methodName, methodType>	--Changes a method type in class
  change method vis	<className, methodName, vis>		--Changes visability of a method

  create param		<className, methodName, param name / type>		--Adds a parameter to a method in a class
  delete param		<className, methodName, paramName>			--Deletes parameter in a method  
  rename param		<className, methodName, paramName, oldParamName>	--Renames a parameter in a method	
  change param type	<className, methodName, paramName, newParamType>	--Changes the parameter type in a method

-Relationship
  create relationship	<classNameTo, classNameFrom, type>	--Adds a relationship with given type between two classes
  change type		<classNameTo, classNameFrom, newType>	--Changes the relationship type between two classes	
  delete relationship 	<classNameTo, classNameFrom>		--Deletes relationshipe between two classes
  show relationships	<>					--Displays all current relationships between classes