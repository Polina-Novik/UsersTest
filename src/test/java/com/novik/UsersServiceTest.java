package com.novik;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UsersServiceTest {
    public static final String NAME = "Polina";
    private List<Users> users = new ArrayList<>();
    private final UsersService usersService = new UsersService(users);
    public static final LocalDate LOCAL_DATE = LocalDate.of(2002, 5, 28);
    public static final LocalDate WRONG_DATE = LocalDate.of(2002, 6, 29);
    private Users testUser = new Users("Polina", LocalDate.of(2002, 5, 28));
    public static final String NULL_NAME = null;
    public static final String BLANK_NAME=" ";
    public static final LocalDate NULL_DATE = null;
    private Users nullUser = null;
    private Users nullDateUser=new Users("Polina",null);




    @SneakyThrows
    @Test
    void createNewUser() {
     usersService.createNewUser(NAME,LOCAL_DATE);
assertEquals(testUser.name,usersService.getUsers().get(0).name);
assertEquals(testUser.dateOfBirth,usersService.getUsers().get(0).dateOfBirth);
    }
    @SneakyThrows
    @Test
    void removeUser() {
       usersService.createNewUser(NAME,LOCAL_DATE);
        usersService.removeUser(NAME);
        assertEquals(true,usersService.getUsers().isEmpty());
    }


    @SneakyThrows
    @Test
    void isBirthDay() {
        usersService.createNewUser(NAME,LOCAL_DATE);
        assertEquals(true, usersService.isBirthDay(users.get(0),LOCAL_DATE));
       assertEquals(false,usersService.isBirthDay(users.get(0),WRONG_DATE));
    }

    @SneakyThrows
    @Test
    void isBirthDayWithNull() {
        usersService.getUsers().add(nullUser);
        usersService.getUsers().add(nullDateUser);
        usersService.createNewUser(NAME,LOCAL_DATE);
        assertThrows(CustomFieldException.class,() ->  usersService.isBirthDay(users.get(0),LOCAL_DATE));
        assertThrows(CustomFieldException.class,() ->  usersService.isBirthDay(users.get(1),LOCAL_DATE));
        assertThrows(CustomFieldException.class,() ->  usersService.isBirthDay(users.get(2),NULL_DATE));
    }

    @Test
    void validateUser() {
        assertThrows(CustomFieldException.class,() ->  usersService.createNewUser(NULL_NAME,LOCAL_DATE));
        assertThrows(CustomFieldException.class,() ->  usersService.createNewUser(BLANK_NAME,LOCAL_DATE));
        assertThrows(CustomFieldException.class,() ->  usersService.createNewUser(NAME,NULL_DATE));
    }

}