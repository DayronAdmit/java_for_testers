package tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.CommonFunction;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class GroupCreationTests extends TestBase {

    @ParameterizedTest
    @MethodSource("singleRandomGroup")
    public void canCreateGroups(GroupData group) {
        var oldGroups = app.hbm().getGroupList();
        app.groups().createGroup(group);
        var newGroups = app.hbm().getGroupList();
        var extraGroup = newGroups.stream().filter(g -> !oldGroups.contains(g)).toList();
        var extraId = extraGroup.get(0).id();
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.add(group.withId(extraId));
        Assertions.assertEquals(Set.copyOf(expectedList), Set.copyOf(newGroups));
    }

    @ParameterizedTest
    @MethodSource("negativeGroupProvider")
    public void canNotCreateGroup(GroupData group) {
        var oldGroups = app.groups().getList();
        app.groups().createGroup(group);
        var newGroups = app.groups().getList();
        Assertions.assertEquals(oldGroups, newGroups);
    }

    public static List<GroupData> groupProvider() throws IOException {
        var result = new ArrayList<GroupData>();

//        for (var name : List.of("", "group name")) {
//            for (var header : List.of("", "group header")) {
//                for (var footer : List.of("", "group footer")) {
//                    result.add(new GroupData().withName(name).withHeader(header).withFooter(footer));
//                }
//            }
//        }
        ObjectMapper mapper = new ObjectMapper();
        var value = mapper.readValue(new File("groups.json"), new TypeReference<List<GroupData>>() {
        });
        result.addAll(value);
        return result;
    }

    public static Stream<GroupData> singleRandomGroup() throws IOException {
        Supplier<GroupData> randomGroup = () -> new GroupData().withName(CommonFunction.randomString(10))
                .withHeader(CommonFunction.randomString(15))
                .withFooter(CommonFunction.randomString(20));
        return Stream.generate(randomGroup).limit(3);
    }

    public static List<GroupData> negativeGroupProvider() {
        var result = new ArrayList<GroupData>(List.of(new GroupData("", "group name'", "", "")));
        return result;
    }
}
