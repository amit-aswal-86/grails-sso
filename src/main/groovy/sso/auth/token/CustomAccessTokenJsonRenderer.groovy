package sso.auth.token

import grails.converters.JSON
import grails.plugin.springsecurity.rest.token.AccessToken
import grails.plugin.springsecurity.rest.token.rendering.AccessTokenJsonRenderer
import groovy.util.logging.Slf4j
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.util.Assert

@Slf4j
class CustomAccessTokenJsonRenderer implements AccessTokenJsonRenderer {

    String usernamePropertyName
    String tokenPropertyName
    String authoritiesPropertyName

    Boolean useBearerToken

    TokenRenderHelperService tokenRenderHelperService;

    @Override
    String generateJson(AccessToken accessToken) {
        Assert.isInstanceOf(UserDetails, accessToken.principal, "A UserDetails implementation is required")
        UserDetails userDetails = accessToken.principal as UserDetails

        List<String> authorities = accessToken.authorities.collect { GrantedAuthority role -> role.authority };
        def accessUrls = tokenRenderHelperService.findAllRequestMap(authorities);
        def user = tokenRenderHelperService.fetchUserByUsername(userDetails.username);

        Map result = [
                user_id: user?.id ?: 0,
                (usernamePropertyName) : userDetails.username,
                (authoritiesPropertyName) : authorities,
                accessUrls: accessUrls
        ]

        if (useBearerToken) {
            result.token_type = 'Bearer'
            result.access_token = accessToken.accessToken

            if (accessToken.expiration) {
                result.expires_in = accessToken.expiration
            }

            if (accessToken.refreshToken) result.refresh_token = accessToken.refreshToken

        } else {
            result["$tokenPropertyName".toString()] = accessToken.accessToken
        }

        JSON jsonResult = result as JSON

        log.info "Generated JSON:\n${jsonResult.toString(true)}"

        return jsonResult.toString()
    }
}
