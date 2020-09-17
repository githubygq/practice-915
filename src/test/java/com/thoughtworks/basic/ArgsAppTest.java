package com.thoughtworks.basic;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
public class ArgsAppTest {
    Schema schema;
    ValueFactory valueFactory = new ValueFactory();
    ValueType valueTypeB = new ValueType("boolean",valueFactory);
    ValueType valueTypeI = new ValueType("integer",valueFactory);
    ValueType valueTypeS = new ValueType("String",valueFactory);
    FlagSchema flagSchemaL = new FlagSchema("l",valueTypeB);
    FlagSchema flagSchemaP = new FlagSchema("p",valueTypeI);
    FlagSchema flagSchemaD = new FlagSchema("d",valueTypeS);
    public void inital(){
        List<FlagSchema> flagSchemas = new ArrayList<>();
        flagSchemas.add(flagSchemaL);
        flagSchemas.add(flagSchemaP);
        flagSchemas.add(flagSchemaD);
        schema = new Schema(flagSchemas);
    }

    @Test
    public void command_test_right() {
        inital();
        //given
        String command = "-l true -p 8080 -d /usr/logs";
        Args args = new Args(schema);
        ArgsApp argsApp = new ArgsApp(args,command);
        //when
        String resault = argsApp.showCommand();
        //then
        assertEquals(resault,"l:true p:8080 d:/usr/logs ");
    }

    @Test
    public void command_test_no_l_command() {
        inital();
        //given
        String command = " -p 8080 -d /usr/logs";
        Args args = new Args(schema);
        ArgsApp argsApp = new ArgsApp(args,command);
        //when
        String resault = argsApp.showCommand();
        //then
        assertEquals(resault,"p:8080 d:/usr/logs l:false ");
    }

    @Test
    public void command_test_no_p_command() {
        inital();
        //given
        String command = "-l true  -d /usr/logs";
        Args args = new Args(schema);
        ArgsApp argsApp = new ArgsApp(args,command);
        //when
        String resault = argsApp.showCommand();
        //then
        assertEquals(resault,"l:true d:/usr/logs p:0 ");
    }

    @Test
    public void command_test_no_d_command() {
        inital();
        //given
        String command = "-l true -p 8080";
        Args args = new Args(schema);
        ArgsApp argsApp = new ArgsApp(args,command);
        //when
        String resault = argsApp.showCommand();
        //then
        assertEquals(resault,"l:true p:8080 d: ");
    }

    @Test
    public void command_test_wrong_command() {
        inital();
        //given
        String command = "-l true -w 8080 -d /usr/logs";
        Args args = new Args(schema);
        ArgsApp argsApp = new ArgsApp(args,command);
        //when
        String resault = argsApp.showCommand();
        //then
        assertEquals(resault,"非法的flag！");
    }

    @Test
    public void command_test_remind_l_command() {
        inital();
        //given
        String command = "-l 121 -p 8080 -d /usr/logs";
        Args args = new Args(schema);
        ArgsApp argsApp = new ArgsApp(args,command);
        //when
        String resault = argsApp.showCommand();
        //then
        assertEquals(resault,"-l 命令为非布尔类型！");
    }

    @Test
    public void command_test_remind_p_command() {
        inital();
        //given
        String command = "-l true -p sss -d /usr/logs";
        Args args = new Args(schema);
        ArgsApp argsApp = new ArgsApp(args,command);
        //when
        String resault = argsApp.showCommand();
        //then
        assertEquals(resault,"-p 命令为非数字类型！");
    }
}
