package org.example.ui;

import org.example.dto.ProfileRequest;
import org.example.exp.ProfileNotFoundException;
import org.example.util.ScannerUtil;

public class AuthUi {
    private final ProfileUi profileUi = new ProfileUi();
    public void start() {
        while (true) {
            switch (menu()) {
                case 1 -> login();
                case 2 -> registration();
                case 0 -> {
                    return;
                }
            }
        }
    }

    private void login(){
        System.out.print("Enter phone: ");
        String phone = ScannerUtil.SCANNER_STR.next();
        System.out.print("Enter password: ");
        String password = ScannerUtil.SCANNER_STR.next();
        try {
            profileUi.login(phone, password);
        } catch (ProfileNotFoundException p) {
            System.out.println(p.getMessage());
        }
    }

    private void registration() {
        System.out.print("Enter name: ");
        String name = ScannerUtil.SCANNER_STR.next();
        System.out.print("Enter phone: ");
        String phone = ScannerUtil.SCANNER_STR.next();
        System.out.print("Enter password: ");
        String password = ScannerUtil.SCANNER_STR.next();
        ProfileRequest request = new ProfileRequest(
                name,
                phone,
                password);
        String response = profileUi.register(request);
        System.out.println(response);
    }

    private int menu() {
        System.out.print("""
                +------  MENU  ------+
                | 1 | Login          |
                | 2 | Register       |
                | 0 | Exit           |
                | Enter Option ->""");
        return ScannerUtil.SCANNER_NUM.nextInt();
    }
}
