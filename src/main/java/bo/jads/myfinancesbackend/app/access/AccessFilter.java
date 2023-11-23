package bo.jads.myfinancesbackend.app.access;

import bo.jads.myfinancesbackend.app.domain.entities.User;
import bo.jads.myfinancesbackend.app.dto.responses.UserResponse;
import bo.jads.myfinancesbackend.app.exceptions.ErrorResponse;
import bo.jads.myfinancesbackend.app.exceptions.entitynotfound.UserNotFoundException;
import bo.jads.myfinancesbackend.app.usecases.users.GetUserById;
import bo.jads.tokenmanager.core.TokenManager;
import bo.jads.tokenmanager.exceptions.TokenDataException;
import bo.jads.tokenmanager.exceptions.TokenValidationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@Component
public class AccessFilter extends OncePerRequestFilter {

    private final GetUserById getUserById;

    private static final List<String> UNPROTECTED_ENDPOINTS = List.of(
            "/api/users",
            "/api/users/log-in",
            "/api/users/reset-password"
    );
    private static final String INVALID_TOKEN = "Invalid token.";

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
                    writeUnauthorizedResponse("Token expired.", request, response);
                    return;
                }
                if (!tokenManager.tokenIntegrityIsValid(token)) {
                    writeUnauthorizedResponse(INVALID_TOKEN, request, response);
                    return;
                }
                UserResponse userResponse = tokenManager.getDataFromToken(token, UserResponse.class);
                if (userResponse == null) {
                    writeUnauthorizedResponse(INVALID_TOKEN, request, response);
                    return;
                }
                User loggedInUser = getUserById.execute(userResponse.getId());
                SessionHolder.setLoggedInUserId(loggedInUser.getId());
            } catch (TokenValidationException | TokenDataException e) {
                writeUnauthorizedResponse(INVALID_TOKEN, request, response);
                return;
            } catch (UserNotFoundException e) {
                writeUnauthorizedResponse("Unauthorized user.", request, response);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    private String getTokenFromHeader(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorization == null || authorization.isBlank()) {
            writeUnauthorizedResponse("Token is required.", request, response);
            return null;
        }
        String bearerPrefix = "Bearer ";
        if (!authorization.startsWith(bearerPrefix)) {
            writeUnauthorizedResponse(INVALID_TOKEN, request, response);
            return null;
        }
        return authorization.replaceFirst(bearerPrefix, "");
    }

    private void writeUnauthorizedResponse(String message, HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatusCode.valueOf(HttpStatus.UNAUTHORIZED.value()), message, request.getRequestURI()
        );
        String responseBody = new ObjectMapper().writeValueAsString(errorResponse);
        response.getWriter().write(responseBody);
        response.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }

}
