# Java - Programmation principles

## Recursivity

Recursivity is a besic programmation principle.  
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