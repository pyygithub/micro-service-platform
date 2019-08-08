package com.thtf.security.app.social;

import com.thtf.security.app.exception.AppSecretException;
import com.thtf.security.core.support.ResponseCode;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.concurrent.TimeUnit;

/**
 * ========================
 * app环境下替换providerSignInUtils，避免由于没有session导致读不到社交用户信息的问题
 * Created with IntelliJ IDEA.
 * User：pyy
 * Date：2019/8/7 16:00
 * Version: v1.0
 * ========================
 */
@Component
public class AppSingUpUtils {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private UsersConnectionRepository usersConnectionRepository;

    @Autowired
    private ConnectionFactoryLocator connectionFactoryLocator;

    /**
     * 缓存社交网站用户信息到redis
     * @param request
     * @param connectionData
     */
    public void saveConnectionData(WebRequest request, ConnectionData connectionData) {
        redisTemplate.opsForValue().set(getKey(request), connectionData, 10, TimeUnit.MINUTES);
    }

    /**
     * 将缓存的社交网站用户信息与系统注册用户信息绑定
     * @param userId
     * @param request
     */
    public void doPostSignUp(String userId, WebRequest request) {
        String key = getKey(request);
        if(!redisTemplate.hasKey(key)){
            throw new AppSecretException(ResponseCode.NOT_FOUND_APPSCRIPT);
        }
        ConnectionData connectionData = (ConnectionData) redisTemplate.opsForValue().get(key);
        Connection<?> connection = connectionFactoryLocator.getConnectionFactory(connectionData.getProviderId())
                .createConnection(connectionData);
        usersConnectionRepository.createConnectionRepository(userId).addConnection(connection);

        redisTemplate.delete(key);
    }

    /**
     * 获取redis key
     * @param request
     * @return
     */
    private String getKey(WebRequest request) {
        String deviceId = request.getHeader("deviceId");
        if (StringUtils.isBlank(deviceId)) {
            throw new AppSecretException(ResponseCode.CLIENT_ID_REQUIRED);
        }
        return "security:social.connect:" + deviceId;
    }
}
