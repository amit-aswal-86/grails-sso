grails.plugin.springsecurity.logout.postOnly = false

// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'sso.auth.SecUser'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'sso.auth.SecUserSecRole'
grails.plugin.springsecurity.authority.className = 'sso.auth.SecRole'
grails.plugin.springsecurity.requestMap.className = 'sso.auth.Requestmap'
grails.plugin.springsecurity.securityConfigType = 'Requestmap'
grails.plugin.springsecurity.controllerAnnotations.staticRules = [
	[pattern: '/',               access: ['permitAll']],
	[pattern: '/error',          access: ['permitAll']],
	[pattern: '/index',          access: ['permitAll']],
	[pattern: '/index.gsp',      access: ['permitAll']],
	[pattern: '/shutdown',       access: ['permitAll']],
	[pattern: '/assets/**',      access: ['permitAll']],
	[pattern: '/**/js/**',       access: ['permitAll']],
	[pattern: '/**/css/**',      access: ['permitAll']],
	[pattern: '/**/images/**',   access: ['permitAll']],
	[pattern: '/**/favicon.ico', access: ['permitAll']]
]

grails.plugin.springsecurity.filterChain.chainMap = [
	[pattern: '/assets/**',      filters: 'none'],
	[pattern: '/**/js/**',       filters: 'none'],
	[pattern: '/**/css/**',      filters: 'none'],
	[pattern: '/**/images/**',   filters: 'none'],
	[pattern: '/**/favicon.ico', filters: 'none'],
	[pattern: '/**',             filters: 'JOINED_FILTERS']
]

// spring security rest configuration

grails.plugin.springsecurity.rest.login.active=true
grails.plugin.springsecurity.rest.login.endpointUrl='/api/login'
grails.plugin.springsecurity.rest.login.failureStatusCode=401
grails.plugin.springsecurity.rest.login.useJsonCredentials=true
grails.plugin.springsecurity.rest.login.usernamePropertyName='username'
grails.plugin.springsecurity.rest.login.passwordPropertyName='password'

// token storage
// grails.plugin.springsecurity.rest.token.storage.useGorm = true
// grails.plugin.springsecurity.rest.token.storage.gorm.tokenDomainClassName = 'sso.auth.token.AuthenticationToken'
grails.plugin.springsecurity.rest.token.storage.useJwt = true
grails.plugin.springsecurity.rest.token.storage.jwt.useSignedJwt = true
grails.plugin.springsecurity.rest.token.storage.jwt.secret = 'sso-webportal-1234567890-grails-apps'
grails.plugin.springsecurity.rest.token.storage.jwt.expiration = 3600
// grails.plugin.springsecurity.rest.token.storage.jwt.useEncryptedJwt = true
// grails.plugin.springsecurity.rest.token.storage.jwt.expiration = 3600
// grails.plugin.springsecurity.rest.token.storage.jwt.privateKeyPath = 'E:\\Dev64x\\IdeaProjects\\grails_app\\grails-sso\\jwt_stuff\\private_key.der'
// grails.plugin.springsecurity.rest.token.storage.jwt.publicKeyPath = 'E:\\Dev64x\\IdeaProjects\\grails_app\\grails-sso\\jwt_stuff\\public_key.der'

//token generation
grails.plugin.springsecurity.rest.token.generation.useSecureRandom = false
grails.plugin.springsecurity.rest.token.generation.useUUID = false
grails.plugin.springsecurity.rest.token.generation.jwt.issuer = 'Amit Aswal The Great'
grails.plugin.springsecurity.rest.token.generation.jwt.algorithm = 'HS256'

//token validate
grails.plugin.springsecurity.rest.token.validation.useBearerToken = true
grails.plugin.springsecurity.rest.token.validation.active = true
grails.plugin.springsecurity.rest.token.validation.endpointUrl = '/api/validate'

