package com.aloha.autoplay.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aloha.autoplay.domain.UserAuth;
import com.aloha.autoplay.domain.Users;
import com.aloha.autoplay.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, Users> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Value("${default.password}")
    private String defaultPassword;  // 기본 비밀번호

    @Override
    public boolean login(Users user, HttpServletRequest request) throws Exception {
        // 💍 토큰 생성
        String username = user.getUsername();    // 아이디
        String password = user.getPassword();    // 암호화되지 않은 비밀번호
        UsernamePasswordAuthenticationToken token 
            = new UsernamePasswordAuthenticationToken(username, password);
        
        // 토큰을 이용하여 인증
        Authentication authentication = authenticationManager.authenticate(token);
        
        // 인증 여부 확인
        boolean result = authentication.isAuthenticated();

        // 인증이 성공하면 SecurityContext에 설정
        if (result) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
            
            // 세션에 인증 정보 설정 (세션이 없으면 새로 생성)
            HttpSession session = request.getSession(true);  // 세션이 없으면 새로 생성
            session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
        }

        return result;
    }

    @Override
    public Users select(String username) throws Exception {
        Users user = userMapper.select(username);
        return user;
    }

    @Override
    @Transactional      // 트랜잭션 처리를 설정 (회원정보, 회원권한)
    public int join(Users user) throws Exception {
        String username = user.getUsername();
        String password = user.getPassword();
        String encodedPassword = passwordEncoder.encode(password);  // 🔒 비밀번호 암호화
        user.setPassword(encodedPassword);

        // 회원 등록
        int result = userMapper.join(user);

        if( result > 0 ) {
            // 회원 기본 권한 등록
            UserAuth userAuth = new UserAuth();
            userAuth.setUsername(username);
            userAuth.setAuth("ROLE_USER");
            result = userMapper.insertAuth(userAuth);
        }
        return result;
    }

    @Override
    public int insertAuth(UserAuth userAuth) throws Exception {
        int result = userMapper.insertAuth(userAuth);
        return result;
    }

    @Override
    public long todayCount() {
        QueryWrapper<Users> query = new QueryWrapper<>();

        // 오늘 날짜
        query.apply("DATE_FORMAT(`CREATED_AT`, '%y%m%d') = DATE_FORMAT(NOW(), '%y%m%d')")
        .and(
            wrapper -> wrapper.isNotNull(true, "gender")
                         .or().isNotNull(true, "birth")
        )
        ;
        long result = this.count(query);
        return result;
    }

    @Override
    public List<Users> getList() {
        return this.list();
    }

    @Override
    public PageInfo<Users> getPageList(int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Users> list = this.list();
        PageInfo<Users> pageInfo = new PageInfo<Users>(list);
        log.info("pageInfo: {}", pageInfo);
        return pageInfo;
    }

    @Override
    public Users get(Long no) {
        QueryWrapper<Users> queryWrapper = new QueryWrapper<Users>();
        queryWrapper.eq("no", no);
        return this.getOne(queryWrapper);
    }

    @Override
    public Users getById(String id) {
        QueryWrapper<Users> queryWrapper = new QueryWrapper<Users>();
        queryWrapper.eq("id", id);
        return this.getOne(queryWrapper);
    }

    @Override
    public boolean create(Users entity) {
        return this.save(entity);
    }

    @Override
    public boolean update(Users user) {
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("no", user.getNo());
        return this.update(user, queryWrapper);
    }

    @Override
    public boolean updateById(Users entity) {
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", entity.getId());
        return this.update(entity, queryWrapper);
    }

    @Override
    public boolean delete(Long no) {
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("no", no);
        return this.remove(queryWrapper);
    }

    @Override
    public boolean deleteById(String id) {
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        return this.remove(queryWrapper);
    }

    @Override
    public long count() {
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        // queryWrapper.isNotNull(true, "gender").or().isNotNull(true, "birth");
        return userMapper.selectCount(queryWrapper);
    }

    @Override
    public Map<String, Long> genderCount() throws Exception {
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("gender", "COUNT(*) as count").groupBy("gender").having("gender IS NOT NULL");

        List<Map<String, Object>> results = userMapper.selectMaps(queryWrapper);
        return results.stream().collect(Collectors.toMap(
            result -> (String) result.get("gender"),
            result -> (Long) result.get("count")
        ));
    }

    @Override
    public Map<String, Long> ageCount() throws Exception {
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("FLOOR(DATEDIFF(CURDATE(), birth) / 365.25 / 10) * 10 AS age, COUNT(*) AS count")
                    .groupBy("age")
                    .having("age IS NOT NULL")
                    .orderByAsc("age")
                    ;

        List<Map<String, Object>> results = userMapper.selectMaps(queryWrapper);
        log.info("results: {}", results);
        return results.stream().collect(Collectors.toMap(
            result -> (String) result.get("age").toString(),
            result -> (Long) result.get("count")
        ));
    }

    @Override
    public boolean autoLogin(String usernamePrefix, HttpServletRequest request) throws Exception {
        long count = this.count();
        String username = usernamePrefix + "_" + count;
        Users user = Users.builder()
                .id(UUID.randomUUID().toString())
                .username(username)
                .password(defaultPassword) // 기본 비밀번호
                .build();
        // 회원 가입
        int result = this.join(user);
        if (result > 0) {
            // 자동 로그인
            user.setPassword(defaultPassword); // 기본 비밀번호로 설정
            boolean loginResult = this.login(user, request);
            return loginResult;
        }
        return false;
    }
    
}
