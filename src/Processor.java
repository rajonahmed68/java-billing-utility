import java.io.*;

public class Processor {
    /**
     * Method to convert the received file into a csv file
     *
     * @throws IOException
     */
    public void convertReceivedFile() throws IOException {
        FileReader fileToRead = new FileReader("resource/received.txt");
        FileWriter fileToWrite = new FileWriter("resource/received_converted.csv");
        BufferedReader reader = new BufferedReader(fileToRead);
        BufferedWriter writer = new BufferedWriter(fileToWrite);
        String datestring;
        String type;
        String documentType;
        String policyNumber;
        String comma = ",";

        try {
            String line;
            String header = "Date, OperationType, DocumentType, PolicyNumber";
            writer.write(header);
            writer.newLine();
            while ((line = reader.readLine()) != null) {
                //Data mining
                datestring = line.substring(0, 25);
                type = line.substring(line.indexOf("Received message"), line.indexOf("with type"));
                documentType = line.substring(line.indexOf("'") + 1, line.indexOf("with key") - 3);
                policyNumber = line.substring(line.indexOf("key:") + 4, line.indexOf(", uuid:"));
                //Write to the file
                writer.write(datestring + comma + type + comma + documentType + comma + policyNumber);
                writer.newLine();
            }
            reader.close();
            writer.close();
        } catch (IOException e) {
            reader.close();
            writer.close();
            e.printStackTrace();
        }
    }


    public void convertSuccessFile() throws IOException {
        FileReader fileToRead = new FileReader("resource/success.txt");
        FileWriter fileToWrite = new FileWriter("resource/success_converted.csv");
        BufferedReader reader = new BufferedReader(fileToRead);
        BufferedWriter writer = new BufferedWriter(fileToWrite);
        String datestring;
        String type;
        String documentType;
        String policyNumber;
        String topic;

        try {
            String header = "Date, OperationType, DocumentType, PolicyNumber, Topic";
            writer.write(header);
            writer.newLine();
            String line;
            while ((line = reader.readLine()) != null) {
                String comma = ",";
                datestring = line.substring(0, 25);
                type = line.substring(line.indexOf("Successfully sent"), line.indexOf("sent ") + 4);
                documentType = line.substring(line.indexOf("Successfully sent") + 17, line.indexOf("message with key"));
                policyNumber = line.substring(line.indexOf("message with key") + 16, line.indexOf("to kafka topic"));
                topic = line.substring(line.indexOf("to kafka topic") + 15);
                //The writing operation of the file
                writer.write(datestring + comma + type + comma + documentType + comma + policyNumber + comma + topic);
                writer.newLine();
                //System.out.println(datestring+comma+type+comma+documentType+comma+policyNumber+comma+topic);
            }
            reader.close();
            writer.close();
        } catch (IOException e) {
            reader.close();
            writer.close();
            e.printStackTrace();
        }
    }

    /**
     * Method to convert the Retry error log into a meaningful .csv file
     *
     * @throws IOException
     */
    public void convertRetryFile() throws IOException {
        FileReader fileToRead = new FileReader("resource/retry.txt");
        FileWriter fileToWrite = new FileWriter("resource/retry_converted.csv");
        BufferedReader reader = new BufferedReader(fileToRead);
        BufferedWriter writer = new BufferedWriter(fileToWrite);

        String datestring;
        String operationType;
        String policyNumber;
        String topic;
        String comma = ",";

        try {
            String header = "Date, OperationType, PolicyNumber, Topic";
            writer.write(header);
            writer.newLine();
            String line;
            while ((line = reader.readLine()) != null) {
                //Compile data for CSV file
                datestring = line.substring(0, 25);
                operationType = line.substring(line.indexOf("Successfully sent"), line.indexOf("Successfully sent") + 17);
                policyNumber = line.substring(line.indexOf("RETRY with key") + 15).replace(".", "");
                topic = line.substring(line.indexOf("sent message to topic") + 22, line.indexOf("RETRY with key"));
                topic = topic.substring(0, topic.indexOf("DocumentEvent") + 15);

                //Write the date to the file
                writer.write(datestring + comma + operationType + comma + policyNumber + comma + topic);
                writer.newLine();
                //System.out.println(datestring+comma+status+comma+policyNumber+comma+topic);
            }
            reader.close();
            writer.close();
        } catch (IOException e) {
            reader.close();
            writer.close();
            e.printStackTrace();
        }
    }
}
