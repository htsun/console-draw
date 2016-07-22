package com.harrison.springer.model;

import com.harrison.springer.model.command.CommandType;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Optional;

import static org.testng.Assert.*;

public class CommandTypeTest {

    @DataProvider(name = "commandTypeTestData")
    public Object[][] commandTypeTestData() {
        return new Object[][]{
                {"C", Optional.of(CommandType.CreateCanvas)},
                {"c", Optional.of(CommandType.CreateCanvas)},

                {"L", Optional.of(CommandType.DrawLine)},
                {"l", Optional.of(CommandType.DrawLine)},

                {"R", Optional.of(CommandType.DrawBox)},
                {"r", Optional.of(CommandType.DrawBox)},

                {"B", Optional.of(CommandType.BucketFill)},
                {"b", Optional.of(CommandType.BucketFill)},

                {"Q", Optional.of(CommandType.Quit)},
                {"q", Optional.of(CommandType.Quit)},

                {"Quit", Optional.empty()},
        };
    }

    @Test(dataProvider = "commandTypeTestData")
    public void verifyConvertTextToCommandType(String text, Optional<CommandType> expectedCommandType) {
        assertEquals(CommandType.toCommandType(text), expectedCommandType);
    }

}