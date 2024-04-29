import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        doWork();
    }

    public static void doWork() throws IOException {
        Processor processor = new Processor();
        processor.convertReceivedFile();
        //processor.convertSuccessFile();
        //processor.convertRetryFile();
    }

}