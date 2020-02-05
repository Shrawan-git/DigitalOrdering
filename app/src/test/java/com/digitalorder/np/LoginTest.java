package com.digitalorder.np;
import com.digitalorder.np.bll.LoginBLL;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class LoginTest {
    @Test
    public void testLogin(){
        LoginBLL loginBLL = new LoginBLL();
        boolean result = loginBLL.checkUser("shrawan","shrawan");
        assertEquals(true,result);
    }
}
