package com.bluetao.member.service.impl;

import com.bluetao.member.service.MemberService;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: MenberImpl
 * @PackageName com.bluetao.menber.service.impl
 * @Description: 会员服务接口
 * @Author ningqian
 * @Date 2019/05/10 1:18
 * @Version V1.0
 */
@RestController
public class MemberServiceImpl implements MemberService {
    @Override
    public String query() {
        return "这是是query";
    }
}
