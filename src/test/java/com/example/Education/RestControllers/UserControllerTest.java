package com.example.Education.RestControllers;

import com.example.Education.DAO.UserDao;
import com.example.Education.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.test.context.event.annotation.AfterTestClass;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserDao userDao;

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    @Test
    public void testGetUserByLogin_NotExists() {
        String login = "ttt";
        User user = userDao.getUserByLogin(login);
        assertNull(user);
    }
    @Test
    public void testGetUserByLogin_Exists() {
        String login = "ttt";
        User user = userDao.getUserByLogin(login);
        assertNotNull(user);
        assertEquals(login, user.getLogin());
    }

    @Test
    public void testAddUser() {

        User user = new User();
        user.setLogin("testuser");
        user.setPassword("testpassword");


        Long userId = userDao.createUser(user);


        assertNotNull(userId);
        assertTrue(userId > 0);


        User savedUser = userDao.getUserById(userId);


        assertNotNull(savedUser);
        assertEquals(user.getLogin(), savedUser.getLogin());
    }
    @Test
    public void testDatabaseConnection() {
        assertNotNull(jdbcTemplate);
    }
    @Test
    @WithMockUser // прикрываем мок под юзера
    public void testLogout() throws Exception {
        mockMvc.perform(SecurityMockMvcRequestBuilders.logout())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"))
                .andExpect(unauthenticated());
    }
    @Test
    public void testLogoutWithoutAuthentication() throws Exception {
        mockMvc.perform(SecurityMockMvcRequestBuilders.logout())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));
    }
    @BeforeTestClass
    @Test
    public void loginIncorrect(){
        User user = new User();
        user.setLogin("5");
        assertTrue(Objects.equals(user.getLogin(), "5"));
    }
    @AfterTestClass
    @Test
    public void loginCorrect(){
        User user = new User();
        user.setLogin("null");
        assertFalse(Objects.equals(user.getLogin(), "null"));
    }
    @Test
    void passwordCorrect(){
        User user = new User();
        user.setPassword("123");
        assertNotEquals(null,user.getPassword());
    }
    @Test
    void passwordIncorrect(){
        User user = new User();
        user.setPassword(null);
        assertNotEquals(null,user.getPassword());
    }
}