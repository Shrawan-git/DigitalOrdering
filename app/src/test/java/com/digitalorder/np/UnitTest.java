package com.digitalorder.np;
import com.digitalorder.np.bll.LoginBLL;
import com.digitalorder.np.bll.OrderBLL;
import com.digitalorder.np.bll.RegisterBLL;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class UnitTest {
    @Test
    public void testLogin(){
        LoginBLL loginBLL = new LoginBLL();
        boolean result = loginBLL.checkUser("biren123","1234");
        assertEquals(true,result);
    }

    @Test
    public void registerTest()
    {
        RegisterBLL registerBLL = new RegisterBLL();
        boolean result = registerBLL.signupUser("Merrr shrestha","samuuur123","sameer12@gmail.com","9876543211","1234","","Male");
        assertEquals(true,result);
    }

    @Test
    public void orderTest()
    {
        OrderBLL orderBLL = new OrderBLL();
        boolean result = orderBLL.orderUser("Cheese burger","$90","burger");
        assertEquals(true,result);
    }
}
