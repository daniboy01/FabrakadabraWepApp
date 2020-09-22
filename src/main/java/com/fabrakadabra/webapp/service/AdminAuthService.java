package com.fabrakadabra.webapp.service;

import com.fabrakadabra.webapp.dto.AdminLoginRequest;
import com.fabrakadabra.webapp.dto.AdminRegisterRequest;
import com.fabrakadabra.webapp.dto.AuthenticationResponse;
import com.fabrakadabra.webapp.dto.RefreshTokenRequest;
import com.fabrakadabra.webapp.exception.SpringFabrakadabraException;
import com.fabrakadabra.webapp.model.Admin;
import com.fabrakadabra.webapp.model.VerificationToken;
import com.fabrakadabra.webapp.repository.AdminRepository;
import com.fabrakadabra.webapp.repository.VerificationTokenRepository;
import com.fabrakadabra.webapp.security.JwtProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AdminAuthService {
    private final PasswordEncoder passwordEncoder;
    private final AdminRepository adminRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final MailService mailService;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final RefreshTokenService refreshTokenService;

    @Transactional
    public void signup(AdminRegisterRequest adminRegisterRequest){
        Admin admin = new Admin();
        admin.setName(adminRegisterRequest.getName());
        admin.setPassword(passwordEncoder.encode(adminRegisterRequest.getPassword()));
        admin.setEmail(adminRegisterRequest.getEmail());
        admin.setCreatedAt(Instant.now());
        admin.setEnabled(false);

        adminRepository.save(admin);

        String token = generateVerificationToken(admin);
        System.out.println("http://localhost:8080/api/adminauth/adminVerification/" + token);

//        mailService.sendMail(new NotificationEmail("Please Activate your Account",
//                admin.getEmail(), "Please activate your admin account clicking the link below " +
//                "http://localhost:8080/api/adminauth/accountVerification/" + token));
    }

    private String generateVerificationToken(Admin admin) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setAdmin(admin);

        verificationTokenRepository.save(verificationToken);
        return token;
    }

    public void verifyAdmin(String token) {
        Optional<VerificationToken> verificationToken = verificationTokenRepository.findByToken(token);
        verificationToken.orElseThrow(() -> new SpringFabrakadabraException("Invalid Token"));
        fetchAdminAndEnable(verificationToken.get());
    }

    @Transactional
    void fetchAdminAndEnable(VerificationToken verificationToken) {
        String adminName = verificationToken.getAdmin().getName();
        Admin admin = adminRepository.findByName(adminName).orElseThrow(() -> new SpringFabrakadabraException("Admin not found with name - " + adminName ));
        admin.setEnabled(true);
        adminRepository.save(admin);
    }

    public AuthenticationResponse login(AdminLoginRequest loginRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getName(),
                loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String token = jwtProvider.generateToken(authenticate);
        return AuthenticationResponse.builder()
                .authenticationToken(token)
                .refreshToken(refreshTokenService.generataRefreshToken().getToken())
                .expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationMillis()))
                .adminName(loginRequest.getName())
                .build();
    }

    public AuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        refreshTokenService.validateRefreshToken(refreshTokenRequest.getRefreshToken());
        String token = jwtProvider.generateTokenWithUserName(refreshTokenRequest.getAdminName());
        return AuthenticationResponse.builder()
                .authenticationToken(token)
                .refreshToken(refreshTokenRequest.getRefreshToken())
                .expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationMillis()))
                .adminName(refreshTokenRequest.getAdminName())
                .build();
    }
}
