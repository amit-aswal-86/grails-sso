import grails.plugin.springsecurity.SpringSecurityUtils
import sso.auth.SecUserPasswordEncoderListener
import sso.auth.token.CustomAccessTokenJsonRenderer
import sso.auth.token.TokenRenderHelperService

// Place your Spring DSL code here
beans = {
    secUserPasswordEncoderListener(SecUserPasswordEncoderListener)

    def conf = SpringSecurityUtils.securityConfig

    tokenRenderHelperService(TokenRenderHelperService)

    accessTokenJsonRenderer(CustomAccessTokenJsonRenderer) {
        tokenRenderHelperService = ref('tokenRenderHelperService');
        usernamePropertyName = conf.rest.token.rendering.usernamePropertyName
        tokenPropertyName = conf.rest.token.rendering.tokenPropertyName
        authoritiesPropertyName = conf.rest.token.rendering.authoritiesPropertyName
        useBearerToken = conf.rest.token.validation.useBearerToken
    }
}
