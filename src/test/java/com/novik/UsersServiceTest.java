package com.novik;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UsersServiceTest {
    private static final String NAME = "Polina";

    private final UsersService usersService = new UsersService(new ArrayList<>());
    private static final LocalDate LOCAL_DATE = LocalDate.of(2002, 5, 28);
    private static final LocalDate WRONG_DATE = LocalDate.of(2002, 6, 29);
    private Users testUser = new Users("Polina", LocalDate.of(2002, 5, 28));
    private static final String NULL_NAME = null;
    private static final String BLANK_NAME=" ";
    private static final LocalDate NULL_DATE = null;
    private Users nullUser = null;
    private Users nullDateUser=new Users("Polina",null);





    @Test
    void createNewUser() throws Exception {
     usersService.createNewUser(NAME,LOCAL_DATE);
assertEquals(testUser.name,usersService.getUsers().get(0).name);
assertEquals(testUser.dateOfBirth,usersService.getUsers().get(0).dateOfBirth);
    }

    @Test
    void removeUser() throws Exception {
       usersService.createNewUser(NAME,LOCAL_DATE);
        usersService.removeUser(NAME);
        assertTrue(usersService.getUsers().isEmpty());
    }



    @Test
    void isBirthDay() throws Exception {
        usersService.createNewUser(NAME,LOCAL_DATE);

        assertTrue(usersService.isBirthDay(usersService.getUsers().get(0),LOCAL_DATE));
       assertFalse(usersService.isBirthDay(usersService.getUsers().get(0),WRONG_DATE));
    }


    @Test
    void isBirthDayWithNull() throws Exception {
        usersService.getUsers().add(nullUser);
        usersService.getUsers().add(nullDateUser);
        usersService.createNewUser(NAME,LOCAL_DATE);
        assertThrows(CustomFieldException.class,() ->  usersService.isBirthDay(usersService.getUsers().get(0),LOCAL_DATE));
        assertThrows(CustomFieldException.class,() ->  usersService.isBirthDay(usersService.getUsers().get(1),LOCAL_DATE));
        assertThrows(CustomFieldException.class,() ->  usersService.isBirthDay(usersService.getUsers().get(2),NULL_DATE));
    }

    @Test
    void validateUser() {
        assertThrows(CustomFieldException.class,() ->  usersService.createNewUser(NULL_NAME,LOCAL_DATE));
        assertThrows(CustomFieldException.class,() ->  usersService.createNewUser(BLANK_NAME,LOCAL_DATE));
        assertThrows(CustomFieldException.class,() ->  usersService.createNewUser(NAME,NULL_DATE));
    }

}