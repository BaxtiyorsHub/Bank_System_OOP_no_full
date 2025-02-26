package org.example.ui;

import org.example.controller.CardController;
import org.example.controller.ProfileController;
import org.example.controller.TransactionController;
import org.example.dto.ProfileRequest;
import org.example.entity.ProfileEntity;
import org.example.enums.ProfileRole;
import org.example.util.ScannerUtil;

import java.util.Random;
import java.util.UUID;

public class ProfileUi {
    private final ProfileController profileController = new ProfileController();
    private final CardController cardController = new CardController();
    private final TransactionController transactionController = new TransactionController();

    public void login(String phone, String password) {
        ProfileEntity entity = profileController.login(phone, password);
        if (entity.getRole().equals(ProfileRole.ADMIN)) {
            adminStart(entity);
        } else {
            userStart(entity);
        }
    }

    private void userStart(ProfileEntity entity) {
        while (true) {
            switch (userMenu()){
                case 1 -> myCards(entity.getId());
                case 2 -> changePassword();
                case 3 -> myTransactions();
                case 4 -> editMyProfile();
                case 5 -> makeTransaction();
                case 0 -> {return;}
            }
        }
    }

    private void makeTransaction() {

    }

    private void editMyProfile() {

    }

    private void myTransactions() {

    }

    private void changePassword() {

    }

    private void myCards(UUID id) {
        cardController.profileCards(id);
    }

    private void adminStart(ProfileEntity entity) {
        while (true) {
            switch (adminMenu()) {
                case 1 -> createCard();
                case 2 -> allCards();
                case 3 -> allProfiles();
                case 4 -> transactionsList();
                case 5 -> notActiveCards();
                case 0 -> {
                    return;
                }
            }
        }
    }

    private void notActiveCards() {
        cardController.allNotActiveCards().forEach(System.out::println);
    }

    private void transactionsList() {
        transactionController.allTransaction().forEach(System.out::println);
    }

    private void allCards() {
        cardController.allCards().forEach(System.out::println);
    }

    private void allProfiles() {
        profileController.allProfiles().forEach(System.out::println);
    }

    private void createCard() {
        String card = generateString();
        String year = generateExpiryDate();
        String response = cardController.createCard(card, year);
        System.out.println(response);
    }

    private String generateExpiryDate() {
        Random random = new Random();
        int month = random.nextInt(12) + 1; // 1 dan 12 gacha oy
        int year = random.nextInt(5) + 25; // 25 dan 54 gacha yil (2025-2054)
        return String.format("%02d/%02d", month, year);
    }

    private String generateString() {
        Random random = new Random();
        String[] prefixes = {"9860", "8600"};
        String prefix = prefixes[random.nextInt(prefixes.length)]; // Tasodifiy prefiks tanlash

        StringBuilder sb = new StringBuilder(prefix);
        for (int i = 0; i < 12; i++) { // Qolgan 12 ta raqamni random yaratish
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    private String register(ProfileRequest request) {
        return profileController.register(request);
    }


    private int adminMenu() {
        System.out.println("1.Create Card");
        System.out.println("2.All Cards");
        System.out.println("3.All Profiles");
        System.out.println("4.Transaction Lists");
        System.out.println("5.Not Active Cards");
        System.out.print("O.Exit :");
        return ScannerUtil.SCANNER_NUM.nextInt();
    }
    private int userMenu() {
        System.out.println("""
                1. My cards
                2. Change password
                3. My transactions
                4. My profile
                5. Make Transaction
                0. Exit->""");
        return ScannerUtil.SCANNER_NUM.nextInt();
    }
}
