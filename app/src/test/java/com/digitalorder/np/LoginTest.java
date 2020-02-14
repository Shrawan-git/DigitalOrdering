package com.digitalorder.np;
import com.digitalorder.np.bll.LoginBLL;
import com.digitalorder.np.bll.RegisterBLL;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class LoginTest {
    @Test
    public void testLogin(){
        LoginBLL loginBLL = new LoginBLL();
        boolean result = loginBLL.checkUser("haku123","1234");
        assertEquals(true,result);
    }

    @Test
    public void registerTest()
    {
        RegisterBLL registerBLL = new RegisterBLL();
        boolean result = registerBLL.signupUser("Sameer shrestha","sameer123","sameer12@gmail.com","9876543211","1234","","Male");
        assertEquals(true,result);
    }


}
