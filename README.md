# Java - Programmation principles

## Recursivity

Recursivity is a basic programmation principle.  
A recursive function is a function which call itself. A good example is the factorial calcul:

`N! = N * (N-1)!`

```
4! = 4 * (4 - 1)! = 4 * 3!
4! = 4 * 3 * (3 - 1)! = 4 * 3 * 2!
4! = 4 * 3 * 2 * (2 - 1)! = 4 * 3 * 2 * 1!
4! = 4 * 3 * 2 * 1
```

So in Java, we can write a recursive function to calculate a factorial:

```java
public static int factorial(int number) {
    if (number == 1)
        return 1;
    else
        return number * factorial(number - 1);
}
```

A POO oriented complex example is if we want to browse some files in folders that can contain themselves
subfolders that contain files, and so on.

Here, the code for a bookstore that contain categories, the categories can contain books and / or sub
categories that can contain books and / or sub categories...

Category.java

```java
public class Category {
    public int numberOfBooks;
    public String name;
    public Categories subCategories = new Categories();
}
```

Categories.java

```java
import java.util.ArrayList;

public class Categories extends ArrayList<Category> {
    private static final long serialVersionUID = 1L;
}
```

Bookstore.java

```java
public class BookStore {

    public static int countBooks(Categories categories) {
        int counter = 0;
        for (Category category : categories) {
            counter += category.numberOfBooks;
            counter += countBooks(category.subCategories);
        }
        return counter;
    }

    public static int countBooksDetails(Categories categories) {
        int counter = 0;
        for (Category category : categories) {
            System.out.println(category.name + " has " + category.numberOfBooks + " books");
            if (category.subCategories.size() > 0) {
                System.out.println("And " + category.subCategories.size() + " sub categories:");
                for (Category subCategory : category.subCategories) {
                    System.out.println(" >> sub category " + subCategory.name);
                }
            } else {
                System.out.println("And no sub categories");
            }

            counter += category.numberOfBooks;
            counter += countBooksDetails(category.subCategories);
        }
        return counter;
    }

    public static void main(String[] args) {
        // create some Category
        Category category1 = new Category();
        category1.name = "Category 1";
        category1.numberOfBooks = 3;
        Category category2 = new Category();
        category2.numberOfBooks = 2;
        category2.name = "Category 2";
        Category category3 = new Category();
        category3.numberOfBooks = 10;
        category3.name = "Category 3";
        Category category4 = new Category();
        category4.numberOfBooks = 10;
        category4.name = "Category 4";
        Category category5 = new Category();
        category5.numberOfBooks = 4;
        category5.name = "Category 5";
        Category category6 = new Category();
        category6.numberOfBooks = 24;
        category6.name = "Category 6";
        Category category7 = new Category();
        category7.numberOfBooks = 8;
        category7.name = "Category 7";

        // create a list (Categories) and put some Category in
        Categories subCategory1 = new Categories();
        subCategory1.add(category3);
        subCategory1.add(category4);

        // create a list (Categories) and put some Category in
        Categories subCategory2 = new Categories();
        subCategory2.add(category5);
        subCategory2.add(category6);
        subCategory2.add(category7);

        // give those lists as value of category subCategories
        category1.subCategories = subCategory1;
        category4.subCategories = subCategory2;

        // create a list (Categories) that contains the top Category (Category elements
        // that are not already
        // in the subCategory of a Category
        Categories bookshelf = new Categories();
        bookshelf.add(category1);
        bookshelf.add(category2);

        int numberOfBooks = countBooksDetails(bookshelf);
        System.out.println("------------------------- \n >>> The bookstore has " + numberOfBooks + " books");

    }

}
```

## Handle errors

There are diffrent kind of errors.

**Business logic errors** are special because it not about a code / programming issue (no crash, no error
message), but the result produced is not the result wanted by users. There are conception errors.

**Compilation errors** (or interpretation errors) are errors that occur when the Java compiler translate the
Java code into byte code. Those errors can be **syntax errors** or **semantic errors**.  
When using an IDE like Eclipse, these kind of error is immediatly noticed and focused, and Eclipse gives fix
proposal and/or documentation. Besides,  the Java compiler Javac can give us useful error messages. So the are
easy to find and fix.

**execution errors** happen during the execution process after launching the app. They happen only during  the
execution and thare are not spotted during the compilation.  
This kind of error is related to the code logic, like access to data that does not exist, or that exists but
in an other format, or the execution of an action that is not handled.  
For exampple, if we try to access an element of a list with `myList.get(15)`, the syntax is correct, but if
the list contains only 3 elements, we will have an execution error.

### Exception : execution errors

Let's take an example.

We have a class `SimpleMaths` with a method inside it to calculate the average of a list of integers.

```java
public class SimpleMaths {
    public static int calculateAverage(List<Integer> listOfIntegers) {
        int average = 0;
        for (int value : listOfIntegers) {
            average += value;
        }
        average /= listOfIntegers.size();
        return average;
    }
}
```

And we have a class `TemperatureAverage` which create a `temperaturesList` with the arguments given at the
execution, and calculate the average.

```java
public class TemperatureAverage {

    public static void main(String[] args) {
        List<Integer> temperaturesList = new ArrayList<Integer>();

        for (String stringTemperature : args) {
            int temperature = Integer.parseInt(stringTemperature);
            temperaturesList.add(temperature);
        }

        Integer averageTemperature = SimpleMaths.calculateAverage(temperaturesList);
        System.out.println("The average temperature is " + averageTemperature);
    }

}
```

If we execute with `0` and `50` in arguments, it is OK.  
But if we execute without arguments, the program tries a divide by zero, and it craches.

We can use a `if` instruction to handle this case:

```java
if (temperaturesList.size() == 0) {
    System.out.println("The list of temperatures is empty!");
} else {
    Integer averageTemperature = SimpleMaths.calculateAverage(temperaturesList);
    System.out.println("The average temperature is " + averageTemperature);
}
```

Now, it is OK without arguments, but if we execute with `0`, `50`, and `five`, the program tries to 
`parseInt()` the string `five`, and it crashes.

But when these errors occur, we get a **stack tracking** that gives error messages, and infos.

```
Exception in thread "main" java.lang.NumberFormatException: For input string: "five"
    at java.base/java.lang.NumberFormatException.forInputString(NumberFormatException.java:67)
    at java.base/java.lang.Integer.parseInt(Integer.java:668)
    at java.base/java.lang.Integer.parseInt(Integer.java:786)
    at exception.TemperatureAverage.main(TemperatureAverage.java:12)
```

### Handle exceptions : `try` / `catch`

The exception handler throws an event which interupt normal execution of the program. If this event is
detected, the problem can be solved, else, the program crashes with a stack tracking describing what happend.

To do that, we can use `try` / `catch`, try to execute some bloc code, and if an error occurs, catch it.

If an error occurs in a try bloc, that throws an error which is catched by the `catch` bloc. If nothing is
catched in the `catch` bloc, the program crashes.

So let's re-write our program:

```java
try {
    for (String stringTemperature : args) {
        int temperature = Integer.parseInt(stringTemperature);
        temperaturesList.add(temperature);
    }

    Integer averageTemperature = SimpleMaths.calculateAverage(temperaturesList);
    System.out.println("The average temperature is " + averageTemperature);
} catch (NumberFormatException event) {
    System.out.println("All arguments should be provided as numbers");
    System.exit(-1);
} catch (ArithmeticException event) {
    System.out.println("At least 1 temperature should be provided");
    System.exit(-1);
}
```

In the `catch (Exception event)` bloc, we can specify the exception type, and if an exception of this type
happen, the instructions in the bloc are executed.

Handle exceptions is a big part of Java, see the Java doc for
[exceptions](https://docs.oracle.com/javase/tutorial/essential/exceptions/index.html) for more about
exceptions.

## Manipulate files

### Read files

To read files in Java, we can initiate a **FileReader** to read a file (the path is given in argument, and
from the root folder of the app if relative) and a **BufferedReader** which use the FileReader to read the
file line by line.

With a `while` loop, we can read each line of the file.

We must be careful and handle the exceptions (if the path is not correct, if the file does not exist, if we do
not have the rights to read / write the file...).

```java
try {
    // path from the project root
    // or absolute path "E:/Java/workspace/java_03_prog_principles_tuto/test.txt"
    FileReader fileReader = new FileReader("test.txt");
    BufferedReader reader = new BufferedReader(fileReader);

    try {
        String line = reader.readLine();

        while (line != null) {
            System.out.println(line);
            line = reader.readLine();
        }
    } catch (IOException e) {
        e.printStackTrace();
        System.out.println("oupsy! WTF is that exception?");
    }
} catch (FileNotFoundException e) {
    e.printStackTrace();
    System.out.println("oupsy! File not found...");
}
```

### Write in files

To write in a file in Java, we can initiate a **FileWriter** to write in a file. The path is given in
argument, and another boolean argument as second argument, `true` to write at the end of the file, `false` to
erase the content of the file and write on.

And a **BufferedWriter** which use the FileWriter to write in the file.

```java
try {
    FileWriter fileWriter = new FileWriter("test.txt", true);
    BufferedWriter writer = new BufferedWriter(fileWriter);

    writer.newLine();
    writer.write("YOLOOOOOO");
    writer.newLine();
    writer.write("HIHI HAHAH HAHAHAHAHA");

    writer.close();

} catch (FileNotFoundException e) {
    e.printStackTrace();
    System.out.println("oupsy! File not found...");
} catch (IOException e) {
    e.printStackTrace();
    System.out.println("oupsy! WTF is that exception?");
}
```

## Lambda function

Since Java 8, it is possible to get a reference to a method. It is called a **closure** , and in Java, it is
the **Lambda** functionnality. It is a new way to write more compact and readable code.

It is an alternative to anonymous class.

### Anonymous class

Almost like anonymous function in Javascript, **anonymous class** can be declared when instanciate:

```java
class Bird {
    void fly() {
        System.out.println("Just flying");
    }
}

Bird myBird = new Bird() {
    @Override
    void fly() {
        System.out.println("Flyyyyyyyyy");
    }
};

myBird.fly();
```

### functionnal interfaces and Lambda code

Java 8 introduces **functional interfaces**. They have (or will have) only 1 abstract method. When Java
executes Lambda code, it makes sure to determine the method to call.

A **Lambda expression* is a sort of re-definition of a method from a functional interface avoiding to make an
anonymous class.

The syntax looks like arrow functions is Javascript.

```java
() -> action
// or
(parameter, ...) -> action
// or
(parameter, ...) -> { do some stuff, return something }
```

First example `() -> action`:

```java
public interface Student {
    void giveSubject();
}

public static void main(String[] args) {
    Student myStudent = () -> {
        System.out.println("I study art");
    };
    myStudent.giveSubject();
}
```

Second example `(parameter, ...) -> action`:

```java
public interface Pokemon {
    void call(String name, String cry);
}

public static void main(String[] args) {
    Pokemon pikachu = (name, cry) -> {
        System.out.println("The " + name + " says: " + cry);
    };
    pikachu.call("Pikachu", "pika pika pikachuuu");

    Pokemon diglett = (name, cry) -> {
        System.out.println("A " + name + " appears: " + cry);
    };
    diglett.call("Diglett", "Digi Digi digiglett!!");
}
```

Third example `(parameter, ...) -> { do some stuff, return something }`:

```java
public interface BagOfCandies {
    int numberOfCandiesIHave(int firstTypeNumber, String firstTypeName, int secondTypeNumber,
            String secondTypeName);
}

public static void main(String[] args) {
    BagOfCandies myBag = (firstTypeNumber, firstTypeName, secondTypeNumber, secondTypeName) -> {
        System.out.println("I have " + firstTypeNumber + " " + firstTypeName);
        System.out.println("I have " + secondTypeNumber + " " + secondTypeName);
        int numberOfCandies = firstTypeNumber + secondTypeNumber;
        return numberOfCandies;
    };
    
    int numberOfCandiesIHave = myBag.numberOfCandiesIHave(5, "chewing-gums", 8, "lollipops");
    System.out.println("In total, I have " + numberOfCandiesIHave + " candies!");
}
```

So, a **Lambda expression** is a **reference to a code bloc**.

A **functionnal interface** is an **interface with just one abstract method**.

We can use a **Lambda expression** to
**implement a functionnal interface without declaring an anonymous class**.

-------------------------------------------------------

