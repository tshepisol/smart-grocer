package za.co.soma.solutions.smart.grocer.security.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;
import za.co.soma.solutions.smart.grocer.dao.UserRepository;
import za.co.soma.solutions.smart.grocer.domain.User;

import java.util.HashMap;
import java.util.Map;


@Component
public class CustomTokenEnhancer implements TokenEnhancer {


    @Autowired
    UserRepository userRepository;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Map<String, Object> additionalInfo = new HashMap<>();

        User user = userRepository.findByUsername(authentication.getName());

        additionalInfo.put("userId", user.getId());
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);

        return accessToken;
    }
}
