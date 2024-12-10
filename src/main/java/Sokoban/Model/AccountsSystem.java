package Sokoban.Model;

import java.io.*;
import java.util.Arrays;

public class AccountsSystem {
    private  final String[] passwords = new String[10];
    private  final String[] names = new String[10];
    private int i=0;
    private int j=0;
    private  boolean L1win;
    private  boolean L2win;
    private  boolean L3win;
    private  boolean L4win;
    private  boolean L5win;

    public boolean isL1win() {
        return L1win;
    }

    public  boolean isL2win() {
        return L2win;
    }

    public boolean isL3win() {
        return L3win;
    }

    public boolean isL4win() {
        return L4win;
    }

    public  boolean isL5win() {
        return L5win;
    }
    public void addName(String a) {
        for(;i<names.length;i++){
            names[i]=a;
        }
    }
    public String[] getName() {
        return names;
    }
    public String[] getPassword() {
        return passwords;
    }
    public void addPassword(String a) {
        for(;j<passwords.length;j++){
            passwords[j]=a;
        }
    }
    public boolean checkMatch(String[]name,String[]password,String a,String b){
        if (name == null || password == null) {
            return false;
        }//防止空指针异常
        int nameIndex = Arrays.asList(name).indexOf(a);
        int passwordIndex = Arrays.asList(password).indexOf(b);
        return nameIndex == passwordIndex;
    }


    public void saveAccount(GameSystem progress){
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("accounts.ser"))) {
            out.writeObject(progress);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public GameSystem loadAccount() {
        GameSystem progress = null;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("accounts.ser"))) {
            progress = (GameSystem) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return progress;
    }
}
