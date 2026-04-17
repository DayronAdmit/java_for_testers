package generator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import model.ContactData;
import model.GroupData;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static common.CommonFunction.randomString;

public class Generator {
    @Parameter(names = {"--type", "-t"})
    String type;
    @Parameter(names = {"--output", "-0"})
    String output;
    @Parameter(names = {"--format", "-f"})
    String format;
    @Parameter(names = {"--count", "-c"})
    int count;

    public static void main(String[] args) throws IOException {
        var generator = new Generator();
        JCommander.newBuilder()
                .addObject(generator)
                .build()
                .parse(args);
        generator.run();
    }

    private Object generateData(Supplier<Object> dataSupplier) {
        return Stream.generate(dataSupplier).limit(count).collect(Collectors.toList());
    }

    private void run() throws IOException {
        var data = generate();
        save(data);
    }

    private void save(Object data) throws IOException {
        if ("json".equals(format)) {
            ObjectMapper mapper = JsonMapper.builder().enable(SerializationFeature.INDENT_OUTPUT).build();
            mapper.writeValue(new File(output), data);
        } else {
            new IllegalArgumentException("Неизветный формат файла " + format);
        }

    }

    private Object generate() {
        if ("groups".equals(type)) {
            return generateGroups();
        } else if ("contacts".equals(type)) {
            return generateContacts();
        } else
            throw new IllegalArgumentException("Неизвестный тип данных " + type);

    }

    private Object generateGroups() {

        return generateData(() -> new GroupData()
                .withName(randomString(10))
                .withHeader(randomString(10))
                .withFooter(randomString(10)));
    }

    private Object generateContacts() {
        var result = new ArrayList<ContactData>();
        for (int i = 0; i < count; i++) {
            result.add(new ContactData()
                    .withLastName(randomString(i * 5))
                    .withFirstName(randomString(i * 5))
                    .withAddress(randomString(i * 5))
                    .withEmail(randomString(i * 5))
                    .withMobilePhone(randomString(i * 5)));
        }
        return generateData(() -> new ContactData()
                .withLastName(randomString(7))
                .withFirstName(randomString(7))
                .withAddress(randomString(7))
                .withEmail(randomString(7))
                .withMobilePhone(randomString(7)));
    }
}
