package ra.entity;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Book {
    // 1. Fields (Attributes)
    private String bookId;
    private String bookName;
    private float importPrice;
    private float exportPrice;
    private String author;
    private float interest;
    private int year;

    // 2. Constructors

    public Book() {
    }

    public Book(String bookId, String bookName, float importPrice, float exportPrice, String author, float interest, int year) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.importPrice = importPrice;
        this.exportPrice = exportPrice;
        this.author = author;
        this.interest = interest;
        this.year = year;
    }

    // 3. Methods


    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public float getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(float importPrice) {
        this.importPrice = importPrice;
    }

    public float getExportPrice() {
        return exportPrice;
    }

    public void setExportPrice(float exportPrice) {
        this.exportPrice = exportPrice;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public float getInterest() {
        return interest;
    }

    // interest – float : Lợi nhuận sách tính theo công thức
    // interest = exportPrice – importPrice
    public void setInterest() {
        this.interest = exportPrice - importPrice;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void inputBookData(Scanner scanner, Book[] arrBook, int numBooks) {
        System.out.print("Nhập mã sách: ");
        // check bookId có trùng lặp trong mảng ?
        boolean checkBookId;
        do {
            this.bookId = scanner.nextLine();
            checkBookId = uniqueBookId(arrBook, numBooks, this.bookId);
            if (!checkBookId) {
                System.err.println("Mã sách đã tồn tại, vui lòng nhập lại!");
            }
        } while (!checkBookId);

        // check bookName regex và trùng lặp
        System.out.print("Nhập tên sách: ");
        boolean checkBookName;
        do {
            this.bookName = scanner.nextLine();
            checkBookName = validBookName(this.bookName) && uniqueBookName(arrBook, numBooks, this.bookName);
            if (!checkBookName) {
                System.err.println("Vui lòng nhập đúng định dạng B+[xxx] hoặc tên sách đã tồn tại");
            }
        } while (!checkBookName);

        System.out.print("Nhập giá nhập của sách: ");
        boolean checkImportPrice;
        do {
            this.importPrice = Float.parseFloat(scanner.nextLine());
            checkImportPrice = validImportPrice(this.importPrice);
            if (!checkImportPrice) {
                System.err.println("Vui lòng nhập giá sách lớn hơn 0");
            }
        } while (!checkImportPrice);

        System.out.print("Nhập giá xuất của sách: ");
        boolean checkExportPrice;
        do {
            this.exportPrice = Float.parseFloat(scanner.nextLine());
            checkExportPrice = validExportPrice(this.exportPrice, this.importPrice);
            if (!checkExportPrice) {
                System.err.println("Giá xuất phải lớn hơn ít nhất 30% so với giá nhập");
            }
        } while (!checkExportPrice);

        System.out.print("Nhập tên tác giả: ");
        boolean checkAuthor;
        do {
            this.author = scanner.nextLine();
            checkAuthor = validAuthor(this.author);
            if (!checkAuthor) {
                System.err.println("Vui lòng nhập tên tác giả chứa 6 - 50 ký tự");
            }
        } while (!checkAuthor);

        System.out.print("Nhập năm xuất bản: ");
        boolean checkYear;
        do {
            this.year = Integer.parseInt(scanner.nextLine());
            checkYear = validYear(this.year);
            if (!checkYear) {
                System.err.println("Vui lòng nhập năm xuất bản sau năm 2000");
            }
        } while (!checkYear);
    }

    public void displayBookData() {
        System.out.printf("Mã sách: %s - Tên sách: %s - Tác giả: %s - Năm xuất bản: %d\n", this.bookId, this.bookName, this.author, this.year);
        System.out.printf("Giá nhập của sách: %.2f - Giá xuất của sách: %.2f - Lợi nhuận: %.2f\n", this.importPrice, this.exportPrice, this.interest);
    }

    // check uniqueBookId
    public boolean uniqueBookId(Book[] arrBook, int numBooks, String bookId) {
        for (int i = 0; i < numBooks; i++) {
            if (arrBook[i].bookId.equals(this.bookId)) {
                return false;
            }
        }
        return true;
    }

    // check bookNameRegex and uniqueBookName
    public boolean validBookName(String bookName) {
        String bookNameRegex = "^B[a-zA-Z0-9]{3}$";
        return Pattern.matches(bookNameRegex, bookName);
    }

    public boolean uniqueBookName(Book[] arrBook, int numBooks, String bookName) {
        for (int i = 0; i < numBooks; i++) {
            if (arrBook[i].bookName.equals(this.bookName)) {
                return false;
            }
        }
        return true;
    }

    public boolean validImportPrice(Float importPrice) {
        if (this.importPrice > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validExportPrice(Float exportPrice, Float importPrice) {
        //: Giá xuất của sách, có giá trị lớn hơn ít
        //nhất 30% so với giá nhập
//        Float isCheck = (this.importPrice * 1.3F);
        if (this.exportPrice >= (this.importPrice * 1.3)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validAuthor(String author) {
        // Tác giả, có từ 6-50 ký tự
        String authorRegex = "^(?=.{6,50}$)[A-Za-zÀ-ỹ][A-Za-zÀ-ỹ ]*$";
        return Pattern.matches(authorRegex, author);
    }

    public boolean validYear(int year) {
        // Năm xuất bản, ít nhất xuất bản sau năm 2000
        if (this.year >= 2000) {
            return true;
        } else {
            return false;
        }
    }

    public void countBooksByYear(Book[] arrBook, int numBooks) {
        if (numBooks == 0) {
            System.out.println("Không có sách nào trong danh sách.");
        }
        int[] booksByYear = new int[numBooks];
        for (int i = 0; i < numBooks; i++) {
            int year = arrBook[i].getYear();
            if (year >= 2000 && year <= 2100) {
                booksByYear[year - 2000]++;
            }
        }
        System.out.println("Thống kê sách theo năm xuất bản:");
        for (int i = 0; i < booksByYear.length; i++) {
            if (booksByYear[i] > 0) {
                System.out.printf("Năm %d: %d sách\n", i + 2000, booksByYear[i]);
            }
        }
    }

    public void countBooksByAuthor(Book[] arrBook, int numBooks) {
        if (numBooks == 0) {
            System.out.println("Không có sách nào trong danh sách.");
        }
        int[] BooksByAuthor = new int[numBooks]; // array count
        String[] authors = new String[numBooks];
        int authorCount = 0;

        for (int i = 0; i < numBooks; i++) {
            String author = arrBook[i].getAuthor();
            boolean isExist = false;
            for (int j = 0; j < authorCount; j++) {
                if (authors[j].equals(author)) {
                    BooksByAuthor[j]++;
                    isExist = true;
                    break;
                }
            }
            if (!isExist) {
                authors[authorCount] = author;
                BooksByAuthor[authorCount] = 1;
                authorCount++;
            }
        }

        System.out.println("Thống kê sách theo tác giả:");
        for (int i = 0; i < authorCount; i++) {
            System.out.printf("Tác giả %s: %d sách\n", authors[i], BooksByAuthor[i]);
        }
    }
}
