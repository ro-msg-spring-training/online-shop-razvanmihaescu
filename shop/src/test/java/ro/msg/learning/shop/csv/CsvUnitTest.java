package ro.msg.learning.shop.csv;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import ro.msg.learning.shop.dtos.StockCsvDto;
import ro.msg.learning.shop.util.CsvConverter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class CsvUnitTest {

    @InjectMocks
    private CsvConverter<StockCsvDto> csvConverter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void serializeCSV() throws IOException {
        List<StockCsvDto> stockCsvDtoList = new ArrayList<>();

        for (int i = 1; i <= 3; i++)
            stockCsvDtoList.add(
                    new StockCsvDto(i, i, "Test Location " + i, "Test Product Name " + i));


        String csvString = csvConverter.toCsv(StockCsvDto.class, stockCsvDtoList);

        String[] csvStringLines = csvString.split("\n");

        assertThat(csvStringLines[0]).isEqualTo("locationName,productName,quantity,stockId");
        for (int i = 1; i < csvStringLines.length; i++)
            assertThat(csvStringLines[i]).isEqualTo("Test Location " + i + ",Test Product Name " + i + "," + i + "," + i);

    }

    @Test
    public void DeserializeCSV() throws IOException {
        csvConverter.setUploadedFile(new File("src\\test\\java\\ro\\msg\\learning\\shop\\csv\\StockCsvTest.csv"));
        List<StockCsvDto> stockCsvDtoList = csvConverter.fromCsv(StockCsvDto.class);

        for (int i = 0; i < stockCsvDtoList.size(); i++) {
            assertThat(stockCsvDtoList.get(i).getProductName()).isEqualTo("Test Product Name " + (i + 1));
        }
    }
}
