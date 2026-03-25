package tests;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GroupCreationTests extends TestBase {

    @Test
    public void canCreateGroup() {
        app.groups().createGroup(new GroupData("Group name", "group header", "group footer"));
    }

    @Test
    public void canCreateGroupWithEmptyName() {
        int groupCount = app.groups().getCount();
        app.groups().createGroup(new GroupData());
        int newGroupCount = app.groups().getCount();
        Assertions.assertEquals(groupCount + 1, newGroupCount);
    }

    @Test
    public void canCreateMultipleGroups() {
        int n = 5;
        int groupCount = app.groups().getCount();
        for (int i = 0; i < n; i++) {
            app.groups().createGroup(new GroupData(randomString(i), "group header", "group footer"));
        }
        int newGroupCount = app.groups().getCount();
        Assertions.assertEquals(groupCount + n, newGroupCount);
    }

    @Test
    public void canCreateGroupWithEmptyNameOnly() {
        app.groups().createGroup(new GroupData().withName("some name"));
    }

}
