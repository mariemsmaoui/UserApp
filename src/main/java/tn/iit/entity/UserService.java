package tn.iit.entity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	@Autowired private UserRepository userRepository;
	public List<User> listAll(){
		return (List<User>) userRepository.findAll();
	}

}
