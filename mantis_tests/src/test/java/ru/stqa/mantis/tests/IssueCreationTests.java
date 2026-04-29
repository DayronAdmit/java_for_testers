package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Test;
import ru.stqa.mantis.common.CommonFunction;
import ru.stqa.mantis.models.IssueData;

public class IssueCreationTests extends TestBase {

    @Test
    void canCrateIssue() {
        app.rest().createIssue(new IssueData()
                .withSummary(CommonFunction.randomString(10))
                .withDescription((CommonFunction.randomString(50)))
                .withProject(1L)
        );
    }
}
