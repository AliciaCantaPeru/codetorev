package marketplace.security.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class JwtTokenUtil implements Serializable {

    private final static long serialVersionUID = -3301605591108950415L;
    private final static String AUDIENCE_OTROS = "otros";
    private final static String AUDIENCE_WEB = "web";
    private final static String AUDIENCE_MOBILE = "mobile";
    private final static String AUDIENCE_TABLET = "tablet";

    @Value("${security.secret-key:}")
    private String secretKey;

    @Value("${security.expire-time:60}")
    private int expireTime;

    @Value("${security.clock-time:5}")
    private int clockTime;

    @Autowired
    private TimeProvider timeProvider;

    public String getUsernameFromToken(String token) throws Exception {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public String getUsernameFromTokenExpire(String token) throws Exception {
        int i = token.lastIndexOf('.');
        String withoutSignature = token.substring(0, i + 1);
        Jwt<Header, Claims> untrusted = Jwts.parser().parseClaimsJwt(withoutSignature);
        final Claims claims = untrusted.getBody();
        return getClaimFromToken(claims, Claims::getSubject);
    }

    public Date getIssuedAtDateFromToken(String token) throws Exception {
        return getClaimFromToken(token, Claims::getIssuedAt);
    }

    public Date getExpirationDateFromToken(String token) throws Exception {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public String getAudienceFromToken(String token) throws Exception {
        return getClaimFromToken(token, Claims::getAudience);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) throws Exception {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    public <T> T getClaimFromToken(Claims claims, Function<Claims, T> claimsResolver) throws Exception {
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) throws Exception {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .setAllowedClockSkewSeconds(clockTime * 60)
                .parseClaimsJws(token)
                .getBody();
    }

    public Boolean isTokenExpired(String token) throws Exception {
        Date expiration = getExpirationDateFromToken(token);
        Date fechaActual = timeProvider.now();
//        fechaActual = DateUtils.addMinutes(fechaActual, clockTime);
//        System.out.println(expiration);
//        System.out.println(fechaActual);
        return expiration.before(fechaActual);
    }

    public Boolean isTokenExpiredTolerancia(String token) throws Exception {
        Date expiration = getExpirationDateFromToken(token);
        Date fechaActual = timeProvider.now();
        System.out.println(expiration);
        System.out.println(fechaActual);
        return expiration.before(fechaActual);
    }

    private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
        return (lastPasswordReset != null && created.before(lastPasswordReset));
    }

    private String generateAudience() {
        String audience = AUDIENCE_OTROS;
//        if (device.isNormal()) {
        audience = AUDIENCE_WEB;
//        } else if (device.isTablet()) {
//            audience = AUDIENCE_TABLET;
//        } else if (device.isMobile()) {
//            audience = AUDIENCE_MOBILE;
//        }
        return audience;
    }

    private Boolean ignoreTokenExpiration(String token) throws Exception {
        String audience = getAudienceFromToken(token);
        return (AUDIENCE_TABLET.equals(audience) || AUDIENCE_MOBILE.equals(audience));
    }

    public String generateToken(Map<String, Object> claims, UserDetails userDetails) {
        return doGenerateToken(claims, userDetails.getUsername(), generateAudience());
    }

    private String doGenerateToken(Map<String, Object> claims, String subject, String audience) {
        final Date createdDate = timeProvider.now();
        final Date expirationDate = new Date(createdDate.getTime() + (expireTime * 1000) * 60);

        System.out.println("doGenerateToken " + createdDate);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setAudience(audience)
                .setIssuedAt(createdDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    public Boolean canTokenBeRefreshed(String token, Date lastPasswordReset) throws Exception {
        final Date created = getIssuedAtDateFromToken(token);
        return !isCreatedBeforeLastPasswordReset(created, lastPasswordReset)
                && (!isTokenExpired(token) || ignoreTokenExpiration(token));
    }

    public String refreshToken(String token) throws Exception {
        final Claims claims = getAllClaimsFromToken(token);
        claims.setIssuedAt(timeProvider.now());
        return doRefreshToken(claims);
    }

    public String doRefreshToken(Claims claims) {
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) throws Exception {
        JwtUser user = (JwtUser) userDetails;
        final String username = getUsernameFromToken(token);
        final Date created = getIssuedAtDateFromToken(token);
//        final Date expiration = getExpirationDateFromToken(token);
        return (username.equals(user.getUsername())
                && !isTokenExpired(token)
                && !isCreatedBeforeLastPasswordReset(created, user.getLastPasswordResetDate()));
    }

    public Authentication getAutenticacion(String token) throws Exception {
        if (token != null) {
            String user = Jwts.parser()
                    .setSigningKey(this.secretKey)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();

            return user != null ? new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList()) : null;
        }
        return null;
    }

}
