package src;

import java.util.Scanner;

public class UserInput {

    public static final UserInput INSTANCE = new UserInput();
    private static Scanner scanner;

    private UserInput() {
        scanner = new Scanner(System.in);
    }

    public static int getInt() {
        return scanner.nextInt();
    }
}
