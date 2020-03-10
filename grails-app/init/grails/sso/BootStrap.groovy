package grails.sso

import grails.plugin.springsecurity.SpringSecurityService
import sso.auth.Requestmap
import sso.auth.SecRole
import sso.auth.SecUser
import sso.auth.SecUserSecRole

class BootStrap {

    SpringSecurityService springSecurityService;

    def init = { servletContext ->

        for (String url in [
                '/',
                '/index',
                '/index.gsp',
                '/assets/**',
                '/**/js/**',
                '/**/css/**',
                '/**/images/**',
                '/**/favicon.ico',
                '/login',
                '/login.*',
                '/login/*',
                '/logout',
                '/logout.*',
                '/logout/*',
                '/api/', '/api/**',
        ]) {
            Requestmap.findByUrl(url) ?: new Requestmap(url: url, configAttribute: 'permitAll').save();
        }

        for (String url in [
                '/user', '/user/**',
                '/role', '/role/**',
                '/requestmap/', '/requestmap/**',
        ]) {
            Requestmap.findByUrl(url) ?: new Requestmap(url: url, configAttribute: 'ROLE_SUPER_ADMIN').save();
        }

        // for rest api
        String url = '/**';
        Requestmap.findByUrl(url) ?: new Requestmap(url: url, configAttribute:
                'JOINED_FILTERS,-anonymousAuthenticationFilter,-exceptionTranslationFilter,' +
                        '-authenticationProcessingFilter,-securityContextPersistenceFilter,' +
                        '-rememberMeAuthenticationFilter'
        )

        url = '/stateful/**';
        Requestmap.findByUrl(url) ?: new Requestmap(url: url, configAttribute:
                'JOINED_FILTERS,-restTokenValidationFilter,-restExceptionTranslationFilter'
        )

        springSecurityService.clearCachedRequestmaps();

        def superAdminRole = SecRole.findOrSaveByAuthority('ROLE_SUPER_ADMIN');
        def adminRole = SecRole.findOrSaveByAuthority('ROLE_ADMIN');
        def userRole = SecRole.findOrSaveByAuthority('ROLE_USER');
        def cmsAdminRole = SecRole.findOrSaveByAuthority('ROLE_CMS_ADMIN');

        def testUser = SecUser.findByUsername("amit") ?: new SecUser(username: 'amit', password: 'aswal').save();

        def authorities = testUser.getAuthorities();
        if (!authorities.contains(superAdminRole)) {
            SecUserSecRole.create testUser, superAdminRole
        }

        if (!authorities.contains(adminRole)) {
            SecUserSecRole.create testUser, adminRole
        }

        if (!authorities.contains(cmsAdminRole)) {
            SecUserSecRole.create testUser, cmsAdminRole
        }

        if (!authorities.contains(userRole)) {
            SecUserSecRole.create testUser, userRole
        }

        SecUserSecRole.withSession {
            it.flush()
            it.clear()
        }

        // to add more use
        // [username: '900395', password: 'Cro123456']
        def dataArray = [];

        dataArray?.each { data ->
          println (data);
          String username = data.username.trim();
          String password = data.password.trim();
          def user = SecUser.findByUsername(username);
          if (!user) {
            user = new SecUser(username: username, password: password).save(flush: true, failOnError: true);
            println ("Usercreated ${username} >> ${user?.id}");
          }
        }
    }
    def destroy = {
    }
}
