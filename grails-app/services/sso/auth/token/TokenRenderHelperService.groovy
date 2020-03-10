package sso.auth.token

import grails.gorm.transactions.Transactional
import sso.auth.Requestmap
import sso.auth.SecUser

@Transactional
class TokenRenderHelperService {

    def findAllRequestMap(List<String> roles) {
        def accessUrls = [] as TreeSet<String>;
        for (String role: roles) {
            def query = Requestmap.where {
                configAttribute =~ role
            };
            List<Requestmap> rList = query.list();
            for (Requestmap r: rList) {
                accessUrls << r.url;
            }
        }
        return accessUrls;
    }

    def fetchUserByUsername(String username) {
        SecUser secUserInstance = SecUser.findByUsername(username);
        return secUserInstance;
    }
}
