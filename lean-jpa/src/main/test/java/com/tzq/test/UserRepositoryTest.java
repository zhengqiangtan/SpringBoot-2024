package com.tzq.test;
import com.tzq.jpa.dao.UserRepository;
import com.tzq.jpa.entity.dto.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserRepositoryTest {

    @Mock
    private UserRepository userRepository;

    @Before
    public void setUp() {
        // 如果需要，可以在这里进行任何通用设置
    }

    @Test
    public void findByUserName_UserExists_ReturnsUser() {
        // 准备
        String userName = "john_doe";
        User expectedUser = new User(1L,userName);
        when(userRepository.findByUserName(userName)).thenReturn(expectedUser);

        // 操作
        User actualUser = userRepository.findByUserName(userName);

        // 验证
        assertEquals(expectedUser, actualUser);
    }

    @Test
    public void findByUserName_UserDoesNotExist_ReturnsNull() {
        // 准备
        String userName = "non_existent_user";
        when(userRepository.findByUserName(userName)).thenReturn(null);

        // 操作
        User actualUser = userRepository.findByUserName(userName);

        // 验证
        assertNull(actualUser);
    }
}
