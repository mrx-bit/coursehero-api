package kz.iitu.hackday.coursehero.config;

import kz.iitu.hackday.coursehero.entity.Role;
import kz.iitu.hackday.coursehero.entity.User;
import kz.iitu.hackday.coursehero.repository.RoleRepository;
import kz.iitu.hackday.coursehero.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OnApplicationStartUp  {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("OnApplicationStartUp.onApplicationEvent");
//        preloadRoles();
//        preloadUsers();
    }

    private void preloadRoles() {
        List<Role> roles = new ArrayList<>();
        roles.add(new Role(1L, "ADMIN"));
        roles.add(new Role(2L, "PARENT"));
        roles.add(new Role(3L, "CHILD"));

        roleRepository.saveAll(roles);
    }
    private void preloadUsers() {
        User user = new User();
        user.setId(1L);
        user.setEmail("admin@gmail.com");
        user.setPassword(passwordEncoder.encode("123"));
        user.setRoleId(1L);
        user.setEnabled(true);
        userRepository.save(user);

        User user1 = new User();
        user1.setId(2L);
        user1.setEmail("parent@gmail.com");
        user1.setPassword(passwordEncoder.encode("123"));
        user1.setRoleId(2L);
        user1.setEnabled(true);
        userRepository.save(user1);

        User user2 = new User();
        user2.setId(3L);
        user2.setEmail("child@gmail.com");
        user2.setPassword(passwordEncoder.encode("123"));
        user2.setRoleId(3L);
        user2.setParentId(2L);
        user2.setEnabled(true);
        userRepository.save(user2);
    }
}
