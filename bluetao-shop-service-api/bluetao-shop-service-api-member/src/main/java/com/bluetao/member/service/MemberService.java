package com.bluetao.member.service;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @ClassName: MenberInterface
 * @PackageName com.bluetao.menber.service
 * @Description: 会员服务接口
 * @Author ningqian
 * @Date 2019/05/10 1:15
 * @Version V1.0
 */
@Api(tags = "会员服务接口")
public interface MemberService {

    @ApiOperation("查询接口")
    @GetMapping("query")
    String query();
}
