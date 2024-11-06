package com.springsecurity.CustProdOrdePaymentService.service;


import com.springsecurity.CustProdOrdePaymentService.config.JwtService;
import com.springsecurity.CustProdOrdePaymentService.config.MyUserDetailasService;
import com.springsecurity.CustProdOrdePaymentService.exception.UnAuthorizedExceptionCls;
import com.springsecurity.CustProdOrdePaymentService.model.Login;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LoginService {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailasService myUserDetailasService;


    public String tokenGenerateJwt(Login login) {

        try {
            // Authenticate the user here
            Authentication authenticate = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword()));

            if (authenticate.isAuthenticated()) {
                log.info("User {} authenticated successfully.", login.getEmail());
                // Assuming loadUserByUsername uses the email for lookup
                return jwtService.genarateJwtToken(myUserDetailasService.loadUserByUsername(login.getEmail())); // Ensure correct method name
            } else {
                log.warn("Authentication failed: invalid credentials for user {}", login.getEmail());
                throw new UnAuthorizedExceptionCls("Authentication failed: invalid credentials.");
            }
        } catch (UnAuthorizedExceptionCls e) {
            log.info("first catch block unauthorized in login service");

            // Handle unauthorized exceptions differently if needed
            log.warn("Unauthorized access attempt: {}", e.getMessage());
            throw e; // Re-throw custom unauthorized exception
        } catch (Exception e) {
            log.info("second catch block exception in login service");
            log.error("Error in LoginService: ", e);
            throw new UnAuthorizedExceptionCls("Authentication failed due to an internal error!"); // More generic message
        }
    }
}
