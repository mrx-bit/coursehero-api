package kz.iitu.hackday.coursehero.service.impl;

import kz.iitu.hackday.coursehero.entity.Session;
import kz.iitu.hackday.coursehero.entity.User;
import kz.iitu.hackday.coursehero.repository.UserRepository;
import kz.iitu.hackday.coursehero.service.SessionService;
import kz.iitu.hackday.coursehero.service.UserService;
import kz.iitu.hackday.coursehero.utils.constants.ErrorMessageConstants;
import kz.iitu.hackday.coursehero.utils.exceptions.NoContentFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

	private SessionService sessionService;
	private UserRepository repository;
	private BCryptPasswordEncoder passwordEncoder;

//	@Autowired
//	private BCryptPasswordEncoder passwordEncoder;

//	@Override
//	public List<User> getAll() {
//		return repository.findAll();
//	}
//
//	@Override
//	public User getByUsername(String username) {
//		return repository.findByPhone(username).orElse(null);
//	}
//
	@Override
	public User create(User user) {
		Optional<User> existing = repository.findByEmail(user.getEmail());
		existing.ifPresent(it-> {throw new IllegalArgumentException("user already exists: " + it.getEmail());});

		String hash = passwordEncoder.encode(user.getPassword());
		user.setPassword(hash);

		user = repository.save(user);

		log.info("new user has been created: {}", user.getEmail());

		return user;
	}

	@Override
	public User getUserByToken(String token) {
		log.info("UserServiceImpl.getUserByToken:token  " + token);
		String email = sessionService.getActiveSessionByToken(token).getEmail();
		return repository.findByEmail(email).orElseThrow(
				() -> new NoContentFoundException(ErrorMessageConstants.DataNotFound.MESSAGE, ErrorMessageConstants.DataNotFound.ERROR_CODE));
	}

	@Override
	public void sessionExpired(String token) {
		log.info("UserServiceImpl.getUserByToken:token  " + token);
		Session session = sessionService.getActiveSessionByToken(token);
		sessionService.closeSession(session.getId());
	}
}
