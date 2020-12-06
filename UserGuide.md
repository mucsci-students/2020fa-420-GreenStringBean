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
### Task Bar
#### File
Save `^S`		(if no named file: name file)	--Saves file <br/>
Save As	`^Shift+S`	(file)				--Saves named file<br/>
Load `^O`		(file)				--Loads file<br/>
Exit  --Closes UML<br/>

#### View
Zoom In `^=` --Zooms in on project view<br/>
Zoom Out `^-` --Zooms out on project view<br/>
Dark Mode --Turns on dark mode<br/>

#### Project
Undo 	--Undo last edit<br/>
Redo		--Redo last undo<br/>
Create Class		(class name)			--Makes a class panel with its name<br/>

#### Relationship
Create Relationship	(classNameTo, classNameFrom, type)	--Adds a relationship with given type between two classes<br/>
Change Type		(classNameTo, classNameFrom, newType)	--Changes the relationship type between two classes	<br/>
Delete Relationship 	(classNameTo, classNameFrom)		--Deletes relationshipe between two classes<br/>
 
### Right Click Actions
#### Exisiting Class
Open Class	--Opens the class panel for editing <br/>
Close Class	--Closes class so no editing can be done<br/>
Delete Class	--Deletes class panel<br/>
Rename Class	(newClassName)	--Renames a class<br/>
Add Field (visibility, type, name) --Adds a field to a class <br/>
Add Method		(visibility, type, name, paramaters)	--Adds a method to a class <br/>

#### Existing Field
Remove Field --Removes an existing field<br/>
Rename Field		(newFieldName) 	--Renames a field in a class<br/>
Change Field Type	(newFieldType>	--Changes a field type in class<br/>
Change Field Visibility	(newVisibility) --Changes visability of a field<br/>

#### Existing Method
Remove Method --Removes an existing method<br/>
Rename Method --Renames an exisiting method<br/>
Change Method Type (newMethodType) --Changes an existing method type<br/>
Change Method Visibilty (newMethodVisibilty) --Changes an existing method visibility<br/>
Edit Parameters (paramType, name) --Edit the parameters of an existing method<br/>
- Add --Adds a parameter<br/>
- Remove --Removes a parameter<br/>
