### @Extension
* Tell Jenkins to register some class
* Pre-defined extension points
* Implement the pre-existing extension point
* Just add @Extension and Jenkins load it

### Root Action


### Descritor
* Descritor is an Object which has metadata about a Describable Object

* Tell Jenkins to create a instance of what it describes

* Example:
   1. Holds key information when for save/load operations
   2. Operations called during display (like do* method)
        * Form validation(doCheck*OBJECT*)
        * Fill dropdowns (doFill*OBJECT*items)

### @DataBoundConstructor
* Allow .jelly/groovy files to pass information from browser to java

Added to the constructor of a describable object
 
