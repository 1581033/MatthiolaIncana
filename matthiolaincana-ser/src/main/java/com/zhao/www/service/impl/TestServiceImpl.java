package com.zhao.www.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhao.www.entity.ServiceResult;
import com.zhao.www.entity.model.sys.SysMenu;
import com.zhao.www.entity.model.TestDao;
import com.zhao.www.entity.param.TestParam;
import com.zhao.www.mapper.TestMapper;
import com.zhao.www.service.TestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author Matthiola incana
 * @create 2021/10/13 18:34
 */
@Service
@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TestServiceImpl implements TestService{

    private final TestMapper testMapper;

    @Override
    public ServiceResult<?> test(String param) {
        List<TestDao> list = testMapper.selectBatchIds(Arrays.asList(1,"4298b71a-4db1-c4e9-9478-2cae0a5b8e59"));
        return ServiceResult.success(list);
    }

    @Override
    public ServiceResult<?> userLogin(TestParam param) {
        return ServiceResult.success("登陆成功");
    }

    @Override
    public ServiceResult<?> meun() {


        List<SysMenu> list = new ArrayList<>();
       /* SysMenu sysMenu = new SysMenu("0",null,"/","index",new Meta(null,"menu.home","false"),"BasicLayout","/dashboard/workplace");
        list.add(sysMenu);
        SysMenu sysMenu1 = new SysMenu("1","0","/dashboard","dashboard",new Meta(null,"menu.dashboard","false"),"RouteView","/dashboard/workplace");
        SysMenu sysMenu2 = new SysMenu("2","1","/dashboard/workplace","Workplace",new Meta(null,"menu.dashboard.workplace","false"),"dashboard/workplace","/dashboard/workplace");
        SysMenu sysMenu3 = new SysMenu("3","1","/dashboard/analysis/:pageNo([1-9]\\d*)?","Analysis",new Meta(null,"menu.dashboard.analysis","false"),"dashboard/Analysis","/dashboard/Analysis");
        list.add(sysMenu1);
        list.add(sysMenu2);
        list.add(sysMenu3);*/

        return ServiceResult.success(list);
    }

    @Override
    public ServiceResult<?> info() {
        String s = JSON.toJSONString("{\n" +
                "  \"roleId\": \"admin\",\n" +
                "  \"permissionId\": \"dashboard\",\n" +
                "  \"permissionName\": \"仪表盘\",\n" +
                "  \"actions\": \"[{\\\"action\\\":\\\"add\\\",\\\"defaultCheck\\\":false,\\\"describe\\\":\\\"新增\\\"},{\\\"action\\\":\\\"query\\\",\\\"defaultCheck\\\":false,\\\"describe\\\":\\\"查询\\\"},{\\\"action\\\":\\\"get\\\",\\\"defaultCheck\\\":false,\\\"describe\\\":\\\"详情\\\"},{\\\"action\\\":\\\"update\\\",\\\"defaultCheck\\\":false,\\\"describe\\\":\\\"修改\\\"},{\\\"action\\\":\\\"delete\\\",\\\"defaultCheck\\\":false,\\\"describe\\\":\\\"删除\\\"}]\",\n" +
                "  \"actionEntitySet\": [\n" +
                "    {\n" +
                "      \"action\": \"add\",\n" +
                "      \"describe\": \"新增\",\n" +
                "      \"defaultCheck\": false\n" +
                "    },\n" +
                "    {\n" +
                "      \"action\": \"query\",\n" +
                "      \"describe\": \"查询\",\n" +
                "      \"defaultCheck\": false\n" +
                "    },\n" +
                "    {\n" +
                "      \"action\": \"get\",\n" +
                "      \"describe\": \"详情\",\n" +
                "      \"defaultCheck\": false\n" +
                "    },\n" +
                "    {\n" +
                "      \"action\": \"update\",\n" +
                "      \"describe\": \"修改\",\n" +
                "      \"defaultCheck\": false\n" +
                "    },\n" +
                "    {\n" +
                "      \"action\": \"delete\",\n" +
                "      \"describe\": \"删除\",\n" +
                "      \"defaultCheck\": false\n" +
                "    }\n" +
                "  ]\n" +
                "}");
        /*SysUser sysUser = new SysUser("4291d7da9005377ec9aec4a71ea837f","天野远子","admin",null,"/avatar2.jpg","1",null,"1534837621348","0");
        */
        return ServiceResult.success(null);
    }

    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        log.info("用户名----> " + s);
        QueryWrapper<TestDao> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name",s);
        TestDao testDao = testMapper.selectOne(wrapper);
        if (testDao == null){
            throw new UsernameNotFoundException("用户名错误");
        }
        List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList("admin");
        return new User(testDao.getUserName(), new BCryptPasswordEncoder().encode("1234"), authorities);
    }
}
