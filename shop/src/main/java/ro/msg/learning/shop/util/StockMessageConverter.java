package ro.msg.learning.shop.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.Type;

@Component
@RequiredArgsConstructor
public class StockMessageConverter {

    private final MessageConverter messageConverter;

    public Object read(Type type, Class classType) throws IOException {
        return messageConverter.read(type, classType, null);
    }

    public String write(Object object, Type type) throws IOException {

        messageConverter.writeInternal(object, type, null);
        return messageConverter.getCsvData();
    }
}
