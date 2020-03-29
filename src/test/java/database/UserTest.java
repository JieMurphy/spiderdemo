package database;

import com.jie.dao.UserMapper;
import com.jie.model.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserTest {
    /**
     *
     */
    @Autowired
    private UserMapper userMapper;

    @Test
    public void select() throws Exception
    {

        //System.out.println(list.toString());
    }
}
