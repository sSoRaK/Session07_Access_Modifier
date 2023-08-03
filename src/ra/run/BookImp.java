package ra.run;

import ra.entity.Book;

import java.util.Scanner;

public class BookImp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Book[] arrBook = new Book[50];
        Book book = new Book();
        int numBooks = 0;
        do {
            System.out.println("********************MENU********************");
            System.out.println("1. Nhập thông tin (n) sách");
            System.out.println("2. Tính lợi nhuận các sách");
            System.out.println("3. Hiển thị thông tin sách");
            System.out.println("4. Sắp xếp sách theo giá bán tăng dần");
            System.out.println("5. Sắp xếp sách theo lợi nhuận giảm dần");
            System.out.println("6. Tìm sách theo tên sách");
            System.out.println("7. Thống kê số lượng sách theo năm xuất bản");
            System.out.println("8. Thống kê số lượng sách theo tác giả");
            System.out.println("9. Thoát");
            System.out.print("Lựa chọn của bạn: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    System.out.print("Nhập số lượng sách cần nhập thông tin: ");
                    int n = Integer.parseInt(scanner.nextLine());
                    for (int i = 0; i < n; i++) {
                        arrBook[numBooks] = new Book();
                        arrBook[numBooks].inputBookData(scanner, arrBook, numBooks);
                        numBooks++;
                    }
                    break;
                case 2:
                    for (int i = 0; i < numBooks; i++) {
                        arrBook[i].setInterest();
                    }
                    System.out.println("Đã tính lợi nhuận các sách!");
                    break;
                case 3:
                    System.out.println("--------------------Book List--------------------");
                    for (int i = 0; i < numBooks; i++) {
                        arrBook[i].displayBookData();
                    }
                    break;
                case 4:
                    // copy array
                    Book[] arrSortExportPrice = new Book[arrBook.length];
                    for (int i = 0; i < arrSortExportPrice.length; i++) {
                        arrSortExportPrice[i] = arrBook[i];
                    }
                    // sort exportPrice (Ascending)
                    for (int i = 0; i < numBooks - 1; i++) {
                        for (int j = i + 1; j < numBooks; j++) {
                            if (arrSortExportPrice[i].getExportPrice() > arrSortExportPrice[j].getExportPrice()) {
                                Book temp = arrSortExportPrice[i];
                                arrSortExportPrice[i] = arrSortExportPrice[j];
                                arrSortExportPrice[j] = temp;
                            }
                        }
                    }
                    System.out.println("Danh sách xếp theo giá bán (tăng dần)");
                    for (int i = 0; i < numBooks; i++) {
                        arrSortExportPrice[i].displayBookData();
                    }
                    break;
                case 5:
                    // copy array
                    Book[] arrSortInterest = new Book[arrBook.length];
                    for (int i = 0; i < arrBook.length; i++) {
                        arrSortInterest[i] = arrBook[i];
                    }
                    // sort interest (Descending)
                    for (int i = 0; i < numBooks - 1; i++) {
                        for (int j = i + 1; j < numBooks; j++) {
                            if (arrSortInterest[i].getInterest() < arrSortInterest[j].getInterest()) {
                                Book temp = arrSortInterest[i];
                                arrSortInterest[i] = arrSortInterest[j];
                                arrSortInterest[j] = temp;
                            }
                        }
                    }
                    System.out.println("Danh sách xếp theo lợi nhuận (giảm dần)");
                    for (int i = 0; i < numBooks; i++) {
                        arrSortInterest[i].displayBookData();
                    }
                    break;
                case 6:
                    System.out.print("Nhập tên sách tìm kiếm: ");
                    String searchBookName = scanner.nextLine();
                    System.out.println("************** Thông Tin Sách Tìm Kiếm **************");
                    boolean checkSearch = false;
                    for (int i = 0; i < numBooks; i++) {
                        if (arrBook[i].getBookName().toLowerCase().contains(searchBookName.toLowerCase())) {
                            arrBook[i].displayBookData();
                            checkSearch = true;
                        }
                    }
                    if (!checkSearch) {
                        System.err.println("Không tìm thấy thông tin sách cần tìm.");
                    }
                    break;
                case 7:
                    book.countBooksByYear(arrBook, numBooks);
                    break;
                case 8:
                    book.countBooksByAuthor(arrBook, numBooks);
                    break;
                case 9:
                    System.exit(0);
                    break;
                default:
                    System.err.println("Nhập lại lựa chọn từ 1 - 9");
                    break;
            }
        } while (true);
    }
}
