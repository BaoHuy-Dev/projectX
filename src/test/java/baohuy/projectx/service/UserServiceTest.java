package baohuy.projectx.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import baohuy.projectx.entity.User;
import baohuy.projectx.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    //fake
    @Mock
    private UserRepository userRepository;

    //UserRepository(fake) => userService
    @InjectMocks
    private UserService userService;

    @Test
    public void creatUser_shouldReturnUser_WhenEmailValid() {
        //arrange: chuan bi
        User inputUser = new User(null, "baohuytestting", "test@gmail.com");
        User outputUser = new User(1L, "baohuytestting", "test@gmail.com");

        when(this.userRepository.existsByEmail(inputUser.getEmail())).thenReturn(false);
        when(this.userRepository.save(any())).thenReturn(outputUser);

        //act: hanh dong
        User result = this.userService.createUser(inputUser);
        //assert: so sanh
        assertEquals(1L, result.getId());

    }

    @Test
    public void creatUser_shouldThrowException_WhenEmailInvalid() {
        //arrange: chuan bi
        User inputUser = new User(null, "baohuytestting", "test@gmail.com");

        when(this.userRepository.existsByEmail(inputUser.getEmail())).thenReturn(true);

        //act: hanh dong
        Exception ex = assertThrows(IllegalArgumentException.class, ()-> {
            this.userService.createUser(inputUser);
        });

        //assert: so sanh
        assertEquals("Email already exists", ex.getMessage());

    }

    @Test
    public void updateUser_shouldReturnUser_whenValid() {
        //arrange: chuan bi
        Long inputId = 1L;
        User inputUser = new User(1L, "old name","old@gmail.com");
        User outputUser = new User(1L, "new name","new@gmail.com");


        when(this.userRepository.findById(inputId)).thenReturn(Optional.of(inputUser));
        when(this.userRepository.save(any())).thenReturn(outputUser);

        //act: hanh dong
        User result = this.userService.updateUser(inputId, inputUser);

        //assert: so sanh
        assertEquals("new name", result.getName());

    }

    @Test
    public void getAllUsers_shoudlReturnAllUsers() {
        //arrange: chuan bi
        List<User> outputUsers = new ArrayList<>();
        outputUsers.add(new User(1L, "baohuy","baohuytest@gmail.com"));
        outputUsers.add(new User(2L, "baohuy2","baohuytest2@gmail.com"));

        when(this.userRepository.findAll()).thenReturn(outputUsers);

        //act: hanh dong
        List<User> result = this.userService.getAllUsers();

        //assert: so sanh
        assertEquals(2, result.size());
        assertEquals("baohuytest@gmail.com", result.get(0).getEmail());

    }

    @Test
    public void getUserById_shouldReturnOptionalUser() {
        //arrange: chuan bi
        Long inputId = 1L;
        User inputUser = new User(1L, "baohuy","baohuy@gmail.com");
        Optional<User> userOptionalOutput = Optional.of(inputUser);

        when(this.userRepository.findById(inputId)).thenReturn(userOptionalOutput);
        //act: hanh dong
        Optional<User> result = this.userService.getUserById(inputId);

        //assert: so sanh
        assertEquals(true, result.isPresent());

    }

    @Test
    public void deleteUser_shouldReturnVoid_WhenUserExist() {
        //arrange: chuan bi
        Long inputId = 1L;
        when(this.userRepository.existsById(inputId)).thenReturn(true);

        //act: hanh dong
        this.userService.deleteUser(inputId);

        //assert: so sanh
        verify(this.userRepository).deleteById(inputId);

    }

    @Test
    public void deleteUser_shouldThrowException_WhenUserNotExist() {
        //arrange: chuan bi
        Long inputId = 1L;
        when(this.userRepository.existsById(inputId)).thenReturn(false);

        //act: hanh dong
        Exception ex = assertThrows(NoSuchElementException.class, ()-> {
            this.userService.deleteUser(inputId);
        });

        //assert: so sanh
        assertEquals("User not found", ex.getMessage());


    }
}
