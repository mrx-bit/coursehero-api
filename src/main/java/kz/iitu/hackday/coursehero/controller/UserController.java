package kz.iitu.hackday.coursehero.controller;

import io.swagger.annotations.ApiOperation;
import kz.iitu.hackday.coursehero.service.UserService;
import kz.iitu.hackday.coursehero.utils.constants.SecurityConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/hello")
	@ApiOperation(value = "Test hello method", response = ResponseEntity.class)
	public String hello() {

		log.trace("Logging at TRACE level");
		log.debug("Logging at DEBUG level");
		log.info("Logging at INFO level");
		log.warn("Logging at WARN level");
		log.error("Logging at ERROR level");

		return "Hello everyone!!! This is test method!";
	}

	@GetMapping("/currentUser")
	@ApiOperation(value = "Get current user info", response = ResponseEntity.class)
	public ResponseEntity<?> getUserInfo(@RequestHeader(value = SecurityConstants.AUTH_HEADER_NAME, required = false) final String accessToken) {
		String token = accessToken.replace("Bearer ","");
		return ResponseEntity.ok(userService.getUserByToken(token));
	}

	@PostMapping("/logout")
	@ApiOperation(value = "Get current user info", response = ResponseEntity.class)
	public ResponseEntity<?> logout(@RequestHeader(value = SecurityConstants.AUTH_HEADER_NAME, required = false) final String accessToken) {
		String token = accessToken.replace("Bearer ","");
		userService.sessionExpired(token);
		return ResponseEntity.ok().build();
	}

//	@GetMapping
////	@PostFilter("filterObject.username==principal.username or hasAuthority('ADMIN')")
//	@ApiOperation(value = "Метод для получения списка всех пользователей системы", response = ResponseEntity.class)
//	@ApiResponses(value = {
//			@ApiResponse(code = 200, message = "Successfully retrieved list"),
//			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
//			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
//			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
//	})
//	public List<User> getAllUsers() {
//		return userService.getAll();
//	}

//	@GetMapping("/current")
//	@ApiOperation(value = "Метод для получения деталей текущего пользователя", response = ResponseEntity.class)
//	@ApiResponses(value = {
//			@ApiResponse(code = 200, message = "Successfully retrieved list"),
//			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
//			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
//			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
//	})
//	public Authentication getUser(Authentication authentication) {
//		return authentication;
//	}

//	@GetMapping("/{username}")
////	@PreAuthorize("hasAuthority('ADMIN') or #username==principal.username")
//	@ApiOperation(value = "Метод для получения пользователя по username", response = ResponseEntity.class)
//	@ApiResponses(value = {
//			@ApiResponse(code = 200, message = "Successfully retrieved list"),
//			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
//			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
//			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
//	})
//	public User getUserByUsername(@PathVariable String username) {
//		return userService.getByUsername(username);
//	}
//

}
