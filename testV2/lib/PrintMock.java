package lib;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public final class PrintMock {
    private PrintStream original;
    private ByteArrayOutputStream streamMock;

    public PrintMock() {
        original = System.out;
        streamMock = new ByteArrayOutputStream();
    }

    public void mockarSaida() {
        System.setOut(new PrintStream(streamMock));
    }

    public String getPrintCapturado() {
        return streamMock.toString().trim();
    }

    public void restaurarSaidaOriginal() {
        System.setOut(original);
    }
}
