## 🎯**Know about the origin and the intent of true object oriented programming: What you are learning today isn't how OO was really meant to be.**
- **Origin**: OO programming was invented in the 1960s with the language **Simula**.
- **Core Intent**: Model real-world entities as **objects** that **encapsulate data and behavior**.
- **Key Principles**:
  - **Encapsulation**: Objects hide their internal state and expose behavior through methods.
  - **Messaging**: Objects communicate by sending messages (method calls).
  - **Reuse through inheritance** and **polymorphism**.
- **Modern OO misunderstandings**:
  - Many current OO languages focus mainly on **classes and inheritance**, neglecting true messaging and encapsulation.
  - OO was meant to help build software by modeling real-world interactions clearly and flexibly.
- **Takeaway**: What is often taught today is a simplified or distorted form of OO; true OO emphasizes **objects as independent agents** communicating via messages.

## 🎯**Understand what coupling is, what cohesion is and how they both impact change in code.**
### Coupling
- Refers to **how much one class/module depends on another**.
- **Low coupling** is preferred: modules are independent.
- **High coupling** is problematic: changes in one module affect others.

### Cohesion
- Refers to **how closely related the responsibilities of a class/module are**.
- **High cohesion** is preferred: a class/module has a single, clear purpose.
- **Low cohesion** means the class/module does many unrelated things.

### Why it matters
- **Low coupling** and **high cohesion** make code easier to:
  - Change
  - Test
  - Understand
- They improve code **maintainability**, **flexibility**, and **clarity**.

## 🎯**Understanding OO concepts such as abstraction and encapsulation, the idea of abstraction in java and coding in general. How does encapsulation relate to the origin of object oriented programming?**

### **Abstraction**
- means focusing on essential features of an object while hiding unnecessary details.
- It allows programmers to work with *what* an object does, not *how* it does it.
- In Java, abstraction is achieved using abstract classes and interfaces.

### **Encapsulation** 
- groups data and methods inside objects and hides internal state from outside access.
- It enforces data protection by allowing access only through public methods.
- Encapsulation is fundamental to the original concept of OOP, enabling objects to interact only via message passing.
- This isolation improves modularity, maintainability, and scalability of code.

- Overall, encapsulation and abstraction help model real-world entities effectively and protect internal object integrity.

## 🎯**Understand abstract classes and abstract methods: What are they?**
### **Abstract class vs abstract method**
- An **abstract class** is a class that cannot be instantiated.
- It may contain both:
  - **Concrete methods** (with implementation)
  - **Abstract methods** (without implementation)

- An **abstract method** is a method that:
  - Has no body
  - Must be implemented by any subclass

- Abstract classes provide a **template** for other classes.
- They are used to **define a common interface** or behavior while letting subclasses provide specific implementations.

### **Abstract class vs interface**
- **Abstract class**:
  - Used for **shared code** and **common structure**.
  - Can have fields, constructors, and method implementations.
  - Single inheritance only.

- **Interface**:
  - Used to define a **contract** (set of methods to implement).
  - No fields (except constants), no constructors.
  - Supports multiple inheritance (a class can implement multiple interfaces).

✅ Use an abstract class when classes are **closely related** and share logic.  
✅ Use interfaces when classes are **not related** but must follow the same contract.

## 🎯**Understand inheritance: what is method overloading vs method overriding?**

### **Inheritance**
- allows a class to inherit fields and methods from another class.

### **Method Overloading**
- Same method name, **different parameter list**
- Happens **within the same class**
- Used to create **method variants**

### Method Overriding
- Same method name and **same parameters**
- Happens in a **subclass**
- Used to **change the behavior** of an inherited method

## 🎯**Understand the diamond problem**

The diamond problem occurs in multiple inheritance when a class inherits from two classes that both inherit from a common superclass. It creates ambiguity about which version of a method or property the subclass should inherit.

### In Java
- Java **does not allow** multiple inheritance with classes to avoid this issue.
- Java **allows** multiple inheritance with **interfaces**.
- If two interfaces have the same default method, the subclass must override it to resolve the conflict.

### ✅ Example Resolution in Java

```java
interface A {
    default void greet() {
        System.out.println("Hello from A");
    }
}

interface B {
    default void greet() {
        System.out.println("Hello from B");
    }
}

class C implements A, B {
    @Override
    public void greet() {
        A.super.greet(); // Or B.super.greet()
        System.out.println("Hello from C");
    }
}
public class Main {
    public static void main(String[] args) {
        C obj = new C();
        obj.greet();
    }
}
//results
//Hello from A
//Hello from C
```
## 🎯**Understand why inheritance and encapsulation are most of the time conflicting (extra).**

### Encapsulation
- Hides internal implementation details.
- Promotes data protection and controlled access.

### Inheritance
- Enables code reuse by extending existing classes.
- Makes subclasses dependent on parent class implementation.

### The Conflict
- Inheritance can break encapsulation by exposing internal details of the parent class to subclasses.
- Subclasses may become tightly coupled to the parent's internal logic.
- Changes in the parent class can lead to unexpected issues in subclasses.

### Key Takeaway
Use inheritance carefully. Favor composition over inheritance when encapsulation and modularity are critical.

## 🎯**Understand polymorphism and its relation to abstraction. Know how to use polymorphism in java**

### What is Polymorphism?
- Ability of objects to take multiple forms.
- Allows treating different classes through a common interface or abstract class.

### Relation to Abstraction
- Abstraction defines the "what" (methods to implement) without the "how".
- Polymorphism enables calling these abstract methods on various object types.

### How to use in Java
- Declare variables as an interface or abstract class type.
- Assign objects of concrete classes implementing/extending these types.
- Method calls on the abstract reference invoke the concrete class implementation at runtime (dynamic binding).

### Example
```java
Animal animal = new Dog();  // Dog extends Animal
animal.makeSound();         // Calls Dog's implementation
```