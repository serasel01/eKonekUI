
import com.example.barangayservicesui.Logging;
import com.example.barangayservicesui.utils.Admin;
import com.example.barangayservicesui.utils.TextFileReader;
import com.spire.doc.Document;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BarangayServicesUITests {
    private static final String SAMPLE_CERT = "C:\\Users\\Serasel\\IdeaProjects\\BarangayServices-client\\BarangayServices-ui\\src\\main\\resources\\com\\example\\barangayservicesui\\certificates\\stamaria\\Cert.-of-Residency-2020.docx";

    @Test
    void getCurrentDateTime(){
        Logging.printInfoLog(LocalDateTime.now().format(DateTimeFormatter.ofPattern("LLLL dd,yyyy HH:mm")));
    }

    @Test
    void openDirectory() throws IOException {
        Desktop.getDesktop()
                .open(new File("src/main/resources/CreatedCertificates"));
    }

    @Test
    void readTextFileSingleLine(){
        ArrayList<String> dataList = new TextFileReader()
                .readFileSingleLine("src/main/resources/textfiles/nationalities.txt")
                .getStringArrayDataComma();

        for (String data : dataList){
            Logging.printInfoLog(data);
        }
    }

    @Test
    void readTextFileMultipleLines(){
        ArrayList<String> dataList = new TextFileReader()
                .readFileMultipleLines("src/main/resources/textfiles/birthplace.txt");

        for (String data : dataList){
            Logging.printInfoLog(data);
        }
    }

}
