package bo.jads.myfinancesbackend.app.access;

import bo.jads.myfinancesbackend.app.dto.responses.UserResponse;
import bo.jads.myfinancesbackend.app.exceptions.users.UserNotFoundException;
import bo.jads.myfinancesbackend.app.usecases.users.GetUserById;
import bo.jads.tokenmanager.core.TokenManager;
import bo.jads.tokenmanager.exceptions.TokenDataException;
import bo.jads.tokenmanager.exceptions.TokenValidationException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@Component
public class AccessFilter extends OncePerRequestFilter {

    private final GetUserById getUserById;

    private static final List<String> UNPROTECTED_ENDPOINTS = List.of("/api/users", "/api/users/log-in");
    private static final String INVALID_TOKEN = "Invalid token.";
    private static final String UNAUTHORIZED_USER = "Unauthorized user.";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String endpointUrl = request.getRequestURI().replaceFirst("/my-finances", "");
        if (endpointUrl.startsWith("/api") && !UNPROTECTED_ENDPOINTS.contains(endpointUrl)) {
            String token = getTokenFromHeader(request, response);
            if (token == null) {
                return;
            }
            TokenManager tokenManager = TokenManager.getInstance();
            try {
                if (tokenManager.tokenExpired(token)) {
                    writeUnauthorizedResponseMessage(response, "Token expired.");
                    return;
                }
                if (!tokenManager.tokenIntegrityIsValid(token)) {
                    writeUnauthorizedResponseMessage(response, INVALID_TOKEN);
                    return;
                }
                UserResponse userResponse = tokenManager.getDataFromToken(token, UserResponse.class);
                if (userResponse == null) {
                    writeUnauthorizedResponseMessage(response, UNAUTHORIZED_USER);
                    return;
                }
                getUserById.execute(userResponse.getId());
                SessionHolder.getInstance().setLoggedInUser(userResponse);
            } catch (TokenValidationException | TokenDataException e) {
                writeUnauthorizedResponseMessage(response, INVALID_TOKEN);
                return;
            } catch (UserNotFoundException e) {
                writeUnauthorizedResponseMessage(response, UNAUTHORIZED_USER);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    private String getTokenFromHeader(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorization = request.getHeader("authorization");
        if (authorization == null || authorization.isBlank()) {
            writeUnauthorizedResponseMessage(response, "Token is required.");
            return null;
        }
        String bearerPrefix = "Bearer ";
        if (!authorization.startsWith(bearerPrefix)) {
            writeUnauthorizedResponseMessage(response, INVALID_TOKEN);
            return null;
        }
        return authorization.replaceFirst(bearerPrefix, "");
    }

    private void writeUnauthorizedResponseMessage(HttpServletResponse response, String message) throws IOException {
        response.getWriter().write(message);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
    }

}
