package CLI;

import Interpreter.IInputProvider;

import java.util.Scanner;

public class DefaultInputProvider implements IInputProvider {
    @Override
    public String getInput(String prompt) {
        System.out.println(prompt);
        var scanner = new Scanner(System.in);
        return scanner.nextLine().trim();
    }
}
