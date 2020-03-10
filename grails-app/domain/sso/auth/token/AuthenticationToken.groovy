package sso.auth.token

class AuthenticationToken {

    String tokenValue
    String username

    static mapping = {
        version false
    }

    Date dateCreated
}
