//package com.changgou.entity;
//
//
//
//import com.alibaba.fastjson.JSON;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.core.io.Resource;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.jwt.Jwt;
//import org.springframework.security.jwt.JwtHelper;
//import org.springframework.security.jwt.crypto.sign.RsaVerifier;
//import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
//import org.springframework.util.StringUtils;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//
///**
// * @Author: Ye Jian Song
// * @Description: 此工具类用于解析token令牌获得用户信息
// * @Date: Create in 19:29 2019/8/25
// */
//public class TokenDecode {
//    //公钥
//    private static final String PUBLIC_KEY = "public.key";
//
//    private static String publickey="";
//
//
//    /**
//     * 获取非对称加密公钥 Key
//     * @return 公钥 Key
//     */
//    public String getPubKey() {
//        if(!StringUtils.isEmpty(publickey)){
//            return publickey;
//        }
//        Resource resource = new ClassPathResource(PUBLIC_KEY);
//        try {
//            InputStreamReader inputStreamReader = new InputStreamReader(resource.getInputStream());
//            BufferedReader br = new BufferedReader(inputStreamReader);
//            publickey = br.lines().collect(Collectors.joining("\n"));
//            return publickey;
//        } catch (IOException ioe) {
//            return null;
//        }
//    }
//
//    /***
//     * 读取令牌数据
//     */
//    public Map<String,String> dcodeToken(String token){
//        //校验Jwt
//        Jwt jwt = JwtHelper.decodeAndVerify(token, new RsaVerifier(getPubKey()));
//
//        //获取Jwt原始内容
//        String claims = jwt.getClaims();
//        return JSON.parseObject(claims,Map.class);
//    }
//
//    /***
//     * 获取用户信息
//     * @return
//     */
//    public Map<String,String> getUserInfo(){
//        // 获取用户的详细信息
//     OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
//        String token = details.getTokenValue();
//        Map<String, String> map = dcodeToken(token);
//        return map;
//    }
//
//}
