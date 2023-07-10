package br.com.compassuol.pb.challenge.msproducts.auxAuthentication;

import br.com.compassuol.pb.challenge.msproducts.entity.Customer;
import br.com.compassuol.pb.challenge.msproducts.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Component
public class MsAuthorizationEmailPassAuthenticationProvider implements AuthenticationProvider {

    private PasswordEncoder passwordEncoder;
    private CustomerRepository userRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();
        List<Customer> user = userRepository.findByEmail(email);
        if(user.size() > 0) {
            if(passwordEncoder.matches(password, user.get(0).getPassword())) {
                List<GrantedAuthority> authorities = new ArrayList<>();
                user.get(0).getRoles().forEach(role -> {
                    authorities.add(new SimpleGrantedAuthority(role.getName()));
                });
                return new UsernamePasswordAuthenticationToken(email, password, authorities);
            } else {
                throw new BadCredentialsException("Invalid password!");
            }
        } else {
            throw new BadCredentialsException("No user registered with these details");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
