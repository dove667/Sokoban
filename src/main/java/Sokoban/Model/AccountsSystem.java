package Sokoban.Model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AccountsSystem {


    //账号密码
    private static final List<String> passwords = new ArrayList<>();
    private static final List<String> names = new ArrayList<>();
    private static final List<Account> accounts = new ArrayList<>();

    public static List<Account> getAccounts() {
        return accounts;
    }

    public static List<String> getNames() {
        return names;
    }

    public static List<String> getPasswords() {
        return passwords;
    }
    public static boolean checkSameName(String name){
        return names.contains(name);
    }
    public static void addAccount(Account a) {
        accounts.add(a);
    }
    public static Account getAccount(String user) {
        int index = names.indexOf(user);
        if (index >= 0) {
            return accounts.get(index);
        }
        return null;
    }
    public static void addName(String a) {
        names.add(a);
    }
    public static void addPassword(String a) {
       passwords.add(a);

    }
    public static String getName(int index) {
        return names.get(index);
    }
    public static String getPassword(int index) {
        return passwords.get(index);
    }

    public static boolean checkMatch(List<String> names,List<String> passwords,String a,String b){
        if (names == null || passwords == null) {
            return false;
        }
        int nameIndex =names.indexOf(a);
        int passwordIndex =passwords.indexOf(b);
        return nameIndex == passwordIndex;
        //防止同名账号匹配
    }

    public static boolean checkAccount(List<String> names, String nameToCheck,List<String> passwords, String passwordToCheck) {
        boolean accountValid = false;
        for (String name : names) {
            if (name.equals(nameToCheck)) {
                for (String password : passwords) {
                    if (password.equals(passwordToCheck) && checkMatch(names,passwords,nameToCheck,passwordToCheck)) {
                        accountValid = true;
                        break;
                    }
                }

            }
        }
        return accountValid;
    }

    //无法对静态成员或类进行序列化，所以需要使用内部类AccountData来保存静态成员状态

    // 用于保存静态成员状态的非静态内部类
    private static class AccountData implements Serializable {
        private final List<String> names;
        private final List<String> passwords;
        private final List<Account> accounts;

        public AccountData(List<String> names, List<String> passwords, List<Account> accounts) {
            this.names = new ArrayList<>(names);
            this.passwords = new ArrayList<>(passwords);
            this.accounts = new ArrayList<>(accounts);
        }

        public List<String> getNames() {
            return names;
        }

        public List<String> getPasswords() {
            return passwords;
        }

        public List<Account> getAccounts() {
            return accounts;
        }
    }
    //不是保存其他类中的对象，而是保存本类的内部类
    public static void saveAccounts() {
        AccountData data = new AccountData(names, passwords, accounts);
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("accounts.ser"))) {
            out.writeObject(data);
            System.out.println("保存成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadAccounts() {

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("accounts.ser"))) {
            AccountData data = (AccountData) in.readObject();
            // 将加载的数据恢复到静态成员中
            names.clear();
            names.addAll(data.getNames());
            passwords.clear();
            passwords.addAll(data.getPasswords());
            accounts.clear();
            accounts.addAll(data.getAccounts());
            System.out.println("加载成功");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
