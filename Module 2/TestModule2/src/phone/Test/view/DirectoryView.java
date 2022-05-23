package phone.Test.view;

import phone.Test.model.PhoneDirectory;
import phone.Test.serviece.DirectoryService;
import phone.Test.utils.CSVUtils;
import phone.Test.utils.Validation;


import java.util.List;
import java.util.Scanner;


public class DirectoryView {
    static String path = "data/contacts.csv";
    private DirectoryService directoryService;
    private Menu menu = new Menu();
    static Scanner scanner = new Scanner(System.in);

    public DirectoryView() {
        directoryService = new DirectoryService();
    }

    public void option() {

        do {
            try {
                menu.mainMenu();
                System.out.println("Nhập Chức năng");
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        showDirectory();
                        break;
                    case 2:
                        addDirectory();
                        break;
                    case 3:
                        updatePhoneDirectory();
                        break;
                    case 4:
                        removeDirectory();
                        break;
                    case 5:
                        searchByName();
                        break;
                    case 6:
                        CSVUtils.read(path);
                        System.out.println("\n Đọc File Contact.CSV");
                        break;
                    case 7:
                        CSVUtils.write(path, directoryService.getItem());
                        System.out.println("\n Viết File Contact.CSV");
                        showDirectory();
                        break;
                    case 8:
                        menu.exit();
                        System.exit(0);
                    default:
                        System.out.println("\t Không có chức năng này! mới bạn nhập");
                }
            } catch (Exception e) {
                System.out.println("\t chức năng phải là 1 số!");
            }
        } while (true);

    }

    private void removeDirectory() {

        do {
            try {
                showDirectory();
                directoryService.getItem();
                System.out.print("Nhập số điện thoại cần xóa: \t");
                String phone = scanner.nextLine();
                PhoneDirectory phoneDirectory = directoryService.getDiectoryByPhoneNumber(phone);
                if (phoneDirectory == null) {
                    System.out.println("không tìm thấy số điện thoại cần xóa");
                } else {
                    System.out.println("\t❄ ❄ ❄ ❄ ❄ ❄ ❄REMOVE COFIRM❄ ❄ ❄ ❄ ❄ ❄ ❄");
                    System.out.println("\t❄                                          ❄");
                    System.out.println("\t❄              1. Remove                   ❄");
                    System.out.println("\t❄              2. Back                     ❄");
                    System.out.println("\t❄                                          ❄");
                    System.out.println("\t❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄");
                    System.out.print("➲ ");
                    int choice = Integer.parseInt(scanner.nextLine());
                    switch (choice) {
                        case 1:
                            directoryService.remove(phoneDirectory);
                            System.out.println("Xóa thành công");
                            showDirectory();
                            break;
                        case 2:
                            option();
                            break;
                        default:
                            System.out.println("Chọn chức năng không đúng!");
                            option();
                    }
                }
            } catch (Exception e) {
                System.out.println("Nhập không đúng");
                option();
            }
        } while (true);

    }

    private void searchByName() {
        List<PhoneDirectory> directoryList = directoryService.getItem();
        int count = 0;
        showDirectory();
        System.out.println();
        System.out.println("Nhập tên sản phẩm tìm kiếm");
        String search = scanner.nextLine().toLowerCase();
        System.out.println("Kết quả tìm kiếm từ khóa: " + search + " là: ");
        System.out.println("-----------------------------Directory-------------------------------");
        System.out.printf("%-15s %-15s %-20s %-10s %-25s %-20s %-25s \n", "Number Phone", "Team", "FullName", "Genner", "Address", "Birtdate", "Email");
        for (PhoneDirectory directory : directoryList) {
            if (directory.getFullname().toLowerCase().contains(search)) {
                count++;
                System.out.printf("%-15s %-15s %-20s %-10s %-25s %-20s %-25s \n", directory.getPhone(), directory.getTeam(), directory.getFullname(), directory.getGenner(), directory.getAddress(), directory.getBirtdate(), directory.getEmail());
            }
        }
        showReturnsearch(count);
        System.out.println();
        option();

    }

    private void showReturnsearch(int count) {
        System.out.println("có " + count + " Sản phẩm được tìm thấy!");
        char press = ' ';
        boolean isChoice;
        System.out.println();
        do {
            System.out.print("Nhấn 'R' để về menu tìm kiếm !");
            try {
                press = scanner.nextLine().charAt(0);
            } catch (Exception e) {
                press = ' ';
            }
            switch (press) {
                case 'r':
                case 'R': {
                    option();
                    isChoice = false;
                    break;
                }
                default:
                    isChoice = true;
            }
        } while (isChoice);
    }


    private void updatePhoneDirectory() {
        List<PhoneDirectory> directoryList = directoryService.getItem();
        showDirectory();
        do {
            System.out.println("Nhập số điện thoại bạn muốn thay đổi thông tin");
            String phone = scanner.nextLine();
            if (phone.isEmpty()) {
                option();
                return;
            }
            if (!directoryService.getDiectorryByNumber(phone)) {
                System.out.println("số điện thoại này không có trong danh sách");
                break;
            } else {
                for (PhoneDirectory directory : directoryList) {
                    System.out.print("Cập nhật nhóm danh bạ: ");
                    String team = scanner.nextLine();
                    directory.setTeam(team);
                    System.out.print("Cập nhật tên danh bạ: ");
                    String name = scanner.nextLine();
                    directory.setFullname(name);
                    System.out.print("cập nhật ngày sinh ");
                    String date = scanner.nextLine();
                    directory.setBirtdate(date);
                    System.out.print("cập nhật giới tính");
                    String genner = scanner.nextLine();
                    directory.setGenner(genner);
                    System.out.print("Cập nhật địa chỉ: ");
                    String address = scanner.nextLine();
                    directory.setAddress(address);
                    System.out.print("Cập nhật email: ");
                    String email = scanner.nextLine();
                    while (!Validation.isEmailValid(email)) {
                        System.out.println("Email không đúng định dạng!! hãy nhật lại");
                        System.out.print("+\t ");
                        break;
                    }
                    directory.setEmail(email);
                    CSVUtils.write(path, directoryList);
                    System.out.println("Đã cập nhật thành công");
                    showDirectory();
                }
            }
        } while (true);

    }

    private void showDirectory() {
        System.out.println("-----------------------------Directory-------------------------------");
        System.out.printf("%-15s %-15s %-20s %-10s %-25s %-20s %-25s \n", "Number Phone", "Team", "FullName", "Genner", "Address", "Birtdate", "Email");
        List<PhoneDirectory> tele = directoryService.getItem();
        for (PhoneDirectory phoneDirectory : tele) {
            System.out.printf("%-15s %-15s %-20s %-10s %-25s %-20s %-25s \n", phoneDirectory.getPhone(), phoneDirectory.getTeam(), phoneDirectory.getFullname(), phoneDirectory.getGenner(), phoneDirectory.getAddress(), phoneDirectory.getBirtdate(), phoneDirectory.getEmail());
        }
        System.out.println("-----------------------------------------------------------------------");
    }

    private void addDirectory() {

        boolean is = false;
        do {
            try {
                showDirectory();
                System.out.print("Nhập Họ và tên: \t");
                String fullname = scanner.nextLine();
                if (directoryService.getDiectoryByName(fullname)) {
                    System.out.println("Đã tồn tại!");

                } else {
                    String phonenumber;
                    while (true) {
                        System.out.print("Nhập số điện thoại:\t");
                        phonenumber = scanner.nextLine();
                        if (Validation.isPhoneValid(phonenumber)) {
                            break;
                        } else {
                            System.out.println("Không tổn tại số diện thoại này, Hãy nhập lại: ");
                        }
                    }
                    System.out.print("Nhập nhóm: \t");
                    String team = scanner.nextLine();
                    check(team);
                    System.out.print("Nhâp giới tính:\t");
                    String genner = scanner.nextLine();
                    check(genner);
                    System.out.print("Nhập địa chỉ: \t");
                    String address = scanner.nextLine();
                    check(address);
                    System.out.print("Nhập ngày sinh: \t");
                    String birtdate = scanner.nextLine();
                    check(birtdate);
                    String email;
                    while (true) {
                        System.out.println("Nhập Email: \t");
                        email = scanner.nextLine();
                        if (Validation.isEmailValid(email)) {
                            break;
                        }else {
                            System.out.println("không tồn tại email này");
                        }
                    }
                    PhoneDirectory phoneDirectory = new PhoneDirectory(phonenumber, team, fullname, genner, address, birtdate, email);
                    directoryService.add(phoneDirectory);
                    System.out.println("đã cập nhật thành công!!");
                    showDirectory();
                    is = true;
                }
            } catch (Exception e) {
                System.out.println("Nhập không đúng! hãy nhập lại");
            }
        } while (!is);

    }

    public String check(String str) {
        try {
            while (str.isEmpty()) {
                System.out.println("Không được để trống");
                System.out.print("=>");
                str = scanner.nextLine();
            }
        } catch (Exception e) {
            System.out.println("Nhập sai! vui lòng nhập lại!");
        }

        return str;
    }
}
