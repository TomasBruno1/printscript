package CLI;

import Interpreter.InputProvider;

import java.util.Scanner;

public class DefaultInputProvider implements InputProvider {
    @Override
    public String getInput(String prompt) {
        System.out.println(prompt);
        var scanner = new Scanner(System.in);
        return scanner.nextLine().trim();
    }
}
