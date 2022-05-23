package phone.Test.view;

import java.util.Scanner;

public class Menu {
    public  void mainMenu() {
        System.out.println("===============================================");
        System.out.println("=               Quản lý danh bạ               =");
        System.out.println("===============================================");
        System.out.println("Chọn chức năng theo số để tiếp tục");
        System.out.println("1. Xem danh sách");
        System.out.println("2. Thêm mới");
        System.out.println("3. Cập nhật");
        System.out.println("4. Xóa");
        System.out.println("5. Tìm kiếm");
        System.out.println("6. Đọc vào file");
        System.out.println("7. Ghi vào file");
        System.out.println("8. Thoát\n");
//        System.out.println("Chọn chức năng: \t");
    }

    public static void run() {
        DirectoryView directoryView = new DirectoryView();
        directoryView.option();
    }

    public  void exit() {
        System.out.println("\t \t.......Bạn vừa thoát chương trình");
    }
}
