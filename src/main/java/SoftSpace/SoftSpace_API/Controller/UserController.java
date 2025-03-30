package SoftSpace.SoftSpace_API.Controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import SoftSpace.SoftSpace_API.Model.User;
import SoftSpace.SoftSpace_API.Service.UserService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {
    @Autowired
    private UserService _userService;

    @GetMapping
    public CompletableFuture<List<User>> getUserList() {
        return _userService.GetUserListAsync();
    }
    
    @GetMapping("/{id}")
    public CompletableFuture<ResponseEntity<User>> GetUser(@PathVariable Long id) {
        return _userService.GetUserAsync(id)
            .thenApply(result -> result != null
                ? ResponseEntity.ok(result)
                : ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public CompletableFuture<ResponseEntity<Boolean>> CreateUserAsync(@RequestBody User user) {
        return _userService.CreateUserAsync(user)
            .thenApply(result -> result
                ? ResponseEntity.ok(true)
                : ResponseEntity.badRequest().build());
    }
    
    @PutMapping("/{id}")
    public CompletableFuture<ResponseEntity<Boolean>> UpdateUser(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        return _userService.UpdateUserAsync(user)
            .thenApply(result -> result 
                ? ResponseEntity.ok(true)
                : ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public CompletableFuture<ResponseEntity<Void>> DeleteUser(@PathVariable Long id)
    {
        return _userService.DeleteUserAsync(id)
            .thenApply(result -> result
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build());
    }
}
