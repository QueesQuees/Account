import java.util.Scanner;

public class UserMain {
    public static void main(String[] args) {

        boolean isController = true;
        while (isController) {
            System.out.println("So 1 Dang nhap.");
            System.out.println("So 2 tao tai khoan");
            System.out.println("So 0 thoat chuong trinh");
            Scanner sc = new Scanner(System.in);
            String choice = sc.nextLine();

            switch (choice) {
                case "1" :
                    dangNhap();
                    break;
                case "2":
                    dangKy();
                    break;
                case "0":
                    isController = false;
                    break;
                default:
                    break;
            }
        }

    }

    public static void dangNhap () {
        User user = new User();
        user.loginUser();
    }

    public static void dangKy() {
        User user = new User();
        user.registerUser();
    }




}