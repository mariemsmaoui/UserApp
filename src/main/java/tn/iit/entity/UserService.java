package tn.iit.entity;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	public List<User> listAll() {
		return (List<User>) userRepository.findAll();
	}

	public String save(User user) {
		userRepository.save(user);
		return "redirect:/users";

	}
	public User get(Integer id) throws UserNotFoundException {
		Optional<User> result = userRepository.findById(id);
		if(result.isPresent()) {
			return result.get();

		}
		throw new UserNotFoundException("could not find any users with id"+id);
		
	}
    public void delete(Integer id) throws UserNotFoundException {
        Long count = userRepository.countById(id);
        if (count == null || count == 0) {
            throw new UserNotFoundException("Could not find any users with ID " + id);
        }
        userRepository.deleteById(id);
    }


}
