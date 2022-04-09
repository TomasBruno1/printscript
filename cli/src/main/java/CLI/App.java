package CLI;

import AST.Node.Node;
import ContentProvider.ContentProvider;
import ContentProvider.FileContentProvider;
import Lexer.DefaultLexer;
import Lexer.Lexer;
import Lexer.UnknownTokenException;
import Parser.DefaultParser;
import Parser.Parser;
import com.github.lalyos.jfiglet.FigletFont;
import lombok.SneakyThrows;
import org.austral.ingsis.printscript.common.Token;
import org.austral.ingsis.printscript.parser.TokenIterator;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) {
        String mode = "";
        File file = null;
        boolean isValid = false;
        printHeader();
        while (!isValid) {
            try {
                String filename = askForString("Insert file name: ");
                file = getFile(filename);
                System.out.println("File found: " + file.getAbsolutePath());
                printSpaces(2);
                mode = askForString("Insert execution mode (interpretation or validation): ");
                checkMode(mode);
                System.out.println("Selected mode: " + mode);
                printSpaces(2);
                isValid = true;
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        try {
            file = getFile("testeo.txt");
        } catch (IOException ignored) {
        }
        mode = "interpretation";

        ContentProvider aContentProvider = new FileContentProvider(file);

        try {
            Timer timer = new Timer();
            List<Token> tokens = executeLexerTask(timer, aContentProvider);
            Node root = executeParserTask(timer, aContentProvider, tokens);
            if (mode.equals(Mode.Interpretation.getMode()))
                executeInterpretationTask(timer, root);
            else if (mode.equals(Mode.Validation.getMode()))
                executeValidationTask(timer, root);
        } catch (UnknownTokenException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static List<Token> executeLexerTask(Timer timer, ContentProvider aContentProvider)
            throws UnknownTokenException {
        Lexer lexer = new DefaultLexer();
        TaskProgressPrinter.printStart(Task.Lexing);
        timer.start();
        List<Token> tokens = lexer.lex(aContentProvider);
        timer.stop();
        TaskProgressPrinter.printEnd(Task.Lexing, timer);
        return tokens;
    }

    private static Node executeParserTask(Timer timer, ContentProvider aContentProvider, List<Token> tokens) {
        Parser<Node> parser = new DefaultParser(TokenIterator.create(aContentProvider.getContent(), tokens));
        TaskProgressPrinter.printStart(Task.Parsing);
        timer.start();
        Node root = parser.parse();
        timer.stop();
        TaskProgressPrinter.printEnd(Task.Parsing, timer);
        return root;
    }

    private static void executeInterpretationTask(Timer timer, Node root) {
        TaskProgressPrinter.printStart(Task.Interpretation);
        timer.start();
        // todo implement interpretation
        System.out.println(root.toString());
        timer.stop();
        TaskProgressPrinter.printEnd(Task.Interpretation, timer);
    }

    private static void executeValidationTask(Timer timer, Node root) {
        TaskProgressPrinter.printStart(Task.Validation);
        timer.start();
        // todo implement validation
        System.out.println(root.toString());
        timer.stop();
        TaskProgressPrinter.printEnd(Task.Validation, timer);
    }

    public static String askForString(String question) {
        System.out.println(question);
        var scanner = new Scanner(System.in);
        return scanner.nextLine().trim();
    }

    public static File getFile(String filename) throws IOException {
        File file = new File(filename);
        if (!file.exists())
            throw new IOException("File not found");
        else
            return file;
    }

    public static void checkMode(String mode) throws IOException {
        List<String> options = Arrays.stream(Mode.values()).map(Mode::getMode).collect(Collectors.toList());
        if (!options.contains(mode))
            throw new IOException("Invalid mode");
    }

    @SneakyThrows
    public static void printHeader() {
        String header = FigletFont.convertOneLine("Printscript");
        System.out.println(header);
        System.out.println("Welcome to the Printscript Interpreter");
    }

    public static void printSpaces(int n) {
        for (int i = 0; i < n; i++) {
            System.out.println();
        }
    }
}