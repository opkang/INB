package userService.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

class UserTest {

    User user = new User(Long.valueOf(1), "Nicole", "Chong", "Nicole@gmail.com");
    @Test
    void testToString() {
        String result = user.toString();
        Assertions.assertEquals("User(userID=1, firstName=Nicole, lastName=Chong, email=Nicole@gmail.com)", result);
    }
    @Test
    void testSetUserID(){
        final User userTest =new User();
        userTest.setUserID(Long.valueOf(1));
        Assertions.assertEquals(1,userTest.getUserID());
    }
    @Test
    void testSetFirstName(){
        final User userTest =new User();
        userTest.setFirstName("Nicole");
        Assertions.assertEquals("Nicole",userTest.getFirstName());
    }
    @Test
    void testSetLastName(){
        final User userTest =new User();
        userTest.setLastName("Chong");
        Assertions.assertEquals("Chong",userTest.getLastName());
    }
    @Test
    void testSetEmail(){
        final User userTest =new User();
        userTest.setEmail("Nicole@gmail.com");
        Assertions.assertEquals("Nicole@gmail.com",userTest.getEmail());
    }
}