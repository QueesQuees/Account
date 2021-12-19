import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class User {
    private String email;
    private String password;
    private String sdt;
    private String hoTen;
    private Scanner sc = new Scanner(System.in);
    private final String path = "user.txt";
    private boolean isAppend = true;


    public User () {}

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User (String email, String password, String sdt, String hoTen) {
        this.email = email;
        this.password = password;
        this.sdt =sdt;
        this.hoTen = hoTen;
    }

    public String getSdt() {
        return sdt;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public void registerUser () {
        String nhapEmail = sc.nextLine();
        String nhapPassWord = sc.nextLine();

        setEmail(nhapEmail);
        setPassword(nhapPassWord);

        if (regexUser()) {
            if (checkUser()) {
                writeFile(path, toString(), isAppend);
                System.out.println("Tao tk thanh cong");
            } else  {
                System.out.println("Email hoac mat khau da ton tai");
            }
        }  else  {
            System.out.println("Email hoac mat khau khong hop le");
        }
    }

    public void loginUser () {
        String nhapEmail = sc.nextLine();
        String nhapPassWord = sc.nextLine();

        setEmail(nhapEmail);
        setPassword(nhapPassWord);

        if (regexUser()) {
            if (checkUser()) {
                System.out.println("Email hoac mat khau khong dung");
            } else {
                System.out.println("Ban dang nhap thanh cong.");
                //sau di lam gi tiep khi dang nhap duoc
            }
        } else  {
            System.out.println("Email hoac mat khau khong hop le");
        }
    }

    //kiem tra email hop le
    public boolean regexUser () {
        String regexEmail = "^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$";
        String regexPassWord = "[a-zA-Z0-9_!@#$%^&*]+";
        if (!this.getPassword().matches(regexPassWord) || !this.getEmail().matches(regexEmail) ){
            return false;
        }
        return true;
    }
    //kiem tra email trong file User.txt
    private  boolean checkUser () {
        String newUser = toString();
        String dataBaseUser = readFile(path);
        if (dataBaseUser.indexOf(newUser) == -1) {
            return true;
        }
        return false;
    }

    //luu text vao file
    public static void writeFile (String path, String text, boolean isAppend) {
        File file = new File(path);

        try {
            FileOutputStream out = new FileOutputStream(file, isAppend);
            byte[] buff = text.getBytes(StandardCharsets.UTF_8);
            out.write(buff, 0 , buff.length);

            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //doc file vaf tra lai chuoi
    public static String readFile (String path) {
        File file = new File(path);
        String text = "";
        try {
            FileInputStream input = new FileInputStream(file);
            byte[] buff = new byte[1024];
            int lenght = input.read(buff);

            while (lenght > 0) {
                String text1 = new String(buff, 0 , lenght);
                text += text1;
                lenght = input.read(buff);
            }
            input.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }

    @Override
    public String toString() {
        return
                 email + ' ' +
                 password + '\n'
                ;
    }
}
