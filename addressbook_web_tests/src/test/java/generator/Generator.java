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
        var result = new ArrayList<GroupData>();
        for (int i = 0; i < count; i++) {
            result.add(new GroupData()
                    .withName(randomString(i * 5))
                    .withHeader(randomString(i * 5))
                    .withFooter(randomString(i * 5)));
        }
        return result;
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
        return result;
    }
}
