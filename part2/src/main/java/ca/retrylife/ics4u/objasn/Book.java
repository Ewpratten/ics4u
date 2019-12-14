package ca.retrylife.ics4u.objasn;

/* Assignment: ICS4U1,  Thursday 12 Dec, 2019
Due Monday 15 Dec. 2019
- 5% per day late. 

NAME: Evan Pratten
Date handed in: ___________
*/

/* TODO: Read and answer the questions at the bottom of this class file.
 * Please hand in the answers by 
 * (i) printing them out, (ii) writing them out, 
 * or (iii) saving them in the Handin/harwoodm folder (under the name Book_MH.java where MH are your initials).
 */

public class Book {

    // Name
    String book = "Coral Island";

    // Chapters
    int currentChapter = 0;
    Chapter[] chapters;

    public static void main(String[] args) {

        // Create a book
        Book book = new Book("1: In which we are shipwrecked on a coral island");

        // Print book address
        System.out.println(book);

        // Print current chapter title
        System.out.println(book.getCurrentChapter().title);

        // Print current chapter number
        book.printCurrentChapter();

        // Print the chapter end
        book.getCurrentChapter().end();

        // Print current chapter number
        book.printCurrentChapter();

    }

    /**
     * Create a book from a list of names of chapters
     * 
     * @param chapterNames Names of chapters (in order)
     */
    Book(String... chapterNames) {
        // Init chapters with number of names
        chapters = new Chapter[chapterNames.length];

        // Create a new chapter with each name
        for (int i = 0; i < chapterNames.length; i++) {
            chapters[i] = new Chapter(i, chapterNames[i]);
        }
    }

    /**
     * Set the current chapter number (starting at 0), or do nothing if number is
     * invalid
     * 
     * @param n New chapter number
     */
    void setChapter(int n) {
        // Stay on current chapter if n is not in bounds
        if (n < 0 || n >= chapters.length) {
            return;
        }

        // Change the chapter
        currentChapter = n;
    }

    /**
     * Get the current chapter
     * 
     * @return Current chapter
     */
    Chapter getCurrentChapter() {
        return chapters[currentChapter];
    }

    /**
     * Print the current chapter number
     */
    void printCurrentChapter() {
        System.out.println(getCurrentChapter().getNumber());
    }

    // This will hold information about the current chapter of the book. That's why
    // the book only has 1 chapter.
    class Chapter {
        String title;
        private int n;

        protected Chapter(int num, String name) {
            this.n = num;
            this.title = name;
        }

        void end() {
            System.out.println(title + " ends on a cliffhanger");

            // Switched to use Book's setChapter. 
            // NOTE: this will not do anything if there is only 1 chapter defined
            setChapter(n + 1);

            String title = "chapter ";
            this.title = title + n;
        }

        int getNumber() {
            return n;
        }
    }
}

/*
 * QUESTIONS
 * 
 * 0. Put your name and the date that you're handing this in in the comments at
 * the top.
 * 
 * The program has some mistakes and problems. Fix them. The program must
 * compile and run and you can't delete classes, methods or variables.
 * 
 * 1. How is it possible that I can have two variables both named book (lines 19
 * and 22)? (Just to be clear, you should explain what each of the variables
 * is.)
 * 
 * 2. What does the line "Book book = new Book();" mean?
 * 
 * 3. What is line 23 actually printing out? "System.out.println(book);"
 * 
 * 4. How do I get the program to print out the "Coral Island" that is in the
 * variable book? Explain your solution.
 * 
 * 5. Write the needed code so that in main() you can set the title of the
 * chapter of the book to "1: In which we are shipwrecked on a coral island";
 * 
 * 6. Print out the title of the current chapter of the book.
 * 
 * 7. Write a getter to get the number, n, of the chapter.
 * 
 * 8. In the book class make a method printCurrentChapter() that calls the
 * chapter number getter and prints out the number. Run this method
 * (printCurrentChapter)from main.
 * 
 * 9. End the chapter and then print out the current chapter number again.
 * 
 * 10. Why does line 40 say "this.title = title + n;" and not
 * "title = title + n;"?
 * 
 * Bonus: find a way to get one or two more occurrences of the word book or Book
 * in the line : "Book book = new Book();" The program must compile and run and
 * you can't delete classes, methods or variables. (I'm able to get five "books"
 * in that line. Renaming a variable to book_book_book doesn't count.)
 */
