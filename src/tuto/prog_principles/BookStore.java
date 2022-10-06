package tuto.prog_principles;

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
