package com.digitalorder.np;

import com.digitalorder.np.bll.RegisterBLL;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class RegisterTest {
    @Test
    public void registerTest(){
        RegisterBLL registerBLL = new RegisterBLL();
        boolean result = registerBLL.signupUser("shakya", "pkr","9876524131", "1234", "ShrawanFinal1580913707913.jpeg","male");
        assertEquals(true,result);

    }
}
