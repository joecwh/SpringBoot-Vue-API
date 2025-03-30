package SoftSpace.SoftSpace_API.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import SoftSpace.SoftSpace_API.Model.User;
import SoftSpace.SoftSpace_API.Repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository _repo;
    
    @Async
    public CompletableFuture<List<User>> GetUserListAsync()
    {
        return CompletableFuture.supplyAsync(() -> {
            return _repo.findAll();
        });
    }

    @Async
    public CompletableFuture<User> GetUserAsync(long id)
    {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return _repo.findById(id).orElse(null);
            } catch (Exception e) {
                e.printStackTrace(); 
                return null;
            }        
        });
    }

    @Async
    public CompletableFuture<Boolean> CreateUserAsync(User model)
    {
        return CompletableFuture.supplyAsync(() -> {
            try {
                _repo.save(model);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        });
    }   

    @Async
    public CompletableFuture<Boolean> UpdateUserAsync(User model)
    {
        return  CompletableFuture.supplyAsync(() -> {
            try {
                Optional<User> userOptional = _repo.findById(model.getId());
                if (userOptional.isEmpty()) return false;
                
                User user = userOptional.get();
                user.setName(model.getName());
                user.setEmail(model.getEmail());
                _repo.save(user);
        
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;            
            }
        });
    }   

    @Async
    public CompletableFuture<Boolean> DeleteUserAsync(Long id)
    {
        return CompletableFuture.supplyAsync(() -> {
            try {
                if (!_repo.existsById(id)) return false;

                _repo.deleteById(id);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;             
            }
        });
    }
}
