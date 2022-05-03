package CLI;

import Interpreter.IPrintEmitter;

public class DefaultPrintEmitter implements IPrintEmitter {
    @Override
    public void print(String s) {
        System.out.print(s);
    }
}
