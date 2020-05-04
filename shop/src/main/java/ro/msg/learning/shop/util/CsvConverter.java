package ro.msg.learning.shop.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Component
@Data
public class CsvConverter<T> {

    private File uploadedFile;

    public List<T> fromCsv(Class<T> objectClass) throws IOException {

        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = mapper.schemaFor(objectClass);

        schema = schema.withUseHeader(true);
        schema = schema.withColumnSeparator(',');

        MappingIterator<T> it = mapper
                .readerFor(objectClass).with(schema).readValues(uploadedFile);

        return it.readAll();
    }

    public String toCsv(Class<T> objectClass, List<T> inputObjects) throws JsonProcessingException {
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = mapper.schemaFor(objectClass);

        schema = schema.withUseHeader(true);
        schema = schema.withColumnSeparator(',');

        return mapper.writer(schema).writeValueAsString(inputObjects);
    }
}
