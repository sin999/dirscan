package ru.sin666.sbt.dir_scan;

import com.sun.corba.se.impl.ior.TaggedProfileTemplateFactoryFinderImpl;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.toMap;

public class ArgumentParserTest {

    private String EMPTY_REQUEST = "\"\\\\epbyminsd0235\\Video Materials\" \"\\\\EPUALVISA0002.kyiv.com\\Workflow\\ORG\\Employees\\Special\" \"\\\\EPUALVISA0002.kyiv.com\\Workflow\\ORG\\Employees\\Lviv\" - \"\\\\epbyminsd0235\\Video Materials\" \"\\\\EPUALVISA0002.kyiv.com\\Workflow\\ORG\\Employees\\Special\" \"\\\\EPUALVISA0002.kyiv.com\\Workflow\\ORG\\Employees\\Lviv\"";



    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Before
    public void initArgumentParserTest() throws IOException {
         prepareTestFilesystem();
    }

    private void prepareTestFilesystem() throws IOException {

        File file = folder.newFile("test");
    }


    @Test
    public void testIfFileCreated(){
    }

    @Test
    public void parseExclusionsTest() {
        System.out.println(EMPTY_REQUEST);

    }




    @Test
    public void parsingArgumentsWorkingTest() {



//        perActionListMap.entrySet().stream().map(x -> " " + x.getKey() + " " + x.getValue() + "\n").forEach(System.out::println);


    }

}
