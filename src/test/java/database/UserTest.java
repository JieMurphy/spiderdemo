package database;

import com.jie.model.User;
import com.jie.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserTest {
    /**
     *
     */
    @Autowired
    private UserService userService;

    @Test
    public void select() throws Exception
    {
        /*
        User user = new User("admin","123456");
        if(userService.saveUser(user) == true)
        {
            System.out.println("注册成功");
        }
        else {
            System.out.println("注册失败");
        }
        */
    }
}
