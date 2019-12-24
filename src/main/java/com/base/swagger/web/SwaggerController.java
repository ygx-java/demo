package com.base.swagger.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("swagger")
@Api(value = "测试接口", tags = "SwaggerController", description = "测试接口相关")
public class SwaggerController {

//    @Autowired
//    private UserService userService;


    /*@RequestMapping(value = "/save", method = RequestMethod.POST)
    //@ApiImplicitParam(name = "user", value = "用户实体user", required = true, dataType = "User")
    @ApiOperation(value = "创建用户", notes = "创建用户")
    public Map<String, Object> saveUser(@ApiParam(required = true, name = "user", value = "用户实体user") @RequestBody @Valid User user) {
        Map<String, Object> ret = new HashMap<>();
        try {
            if (null == user) {
                ret.put("msg", "参数不能为空");
                return ret;
            }
//            int row = userService.createUser(user);
//            if (row > 0) {
//                ret.put("msg", "添加成功");
//            } else {
//                ret.put("msg", "添加失败");
//            }
            ret.put("msg", "添加成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }*/

    /**
     * @param id
     * @return
     */
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = "application/json")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", paramType = "query")
    @ApiOperation(value = "获取用户详细信息", notes = "根据id来获取用户详细信息")
    public Map<String, Object> getUser(@RequestParam Long id) {
        Map<String, Object> ret = new HashMap<>();
        try {
//            User user = userService.selectById(id);
//            if (null == user) {
//                ret.put("msg", "用户ID：" + id + "，未找到数据");
//            } else {
//                ret.put("msg", "获取成功");
//                ret.put("data", user);
//            }
        	ret.put("msg", "获取成功");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * paramType:指定参数放在哪个地方
     * path:（用于restful接口）-->请求参数的获取：@PathVariable
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", paramType = "path")
    @ApiOperation(value = "删除用户", notes = "删除用户")
    public Map<String, Object> deleteUser(@PathVariable Long id) {
        Map<String, Object> ret = new HashMap<>();
        if (null == id) {
            ret.put("msg", "参数不能为空");
            return ret;
        }
//        int row = userService.deleteById(id);
//        if (row > 0) {
//            ret.put("msg", "删除成功");
//        } else {
//            ret.put("msg", "删除失败");
//        }
        ret.put("msg", "删除成功");
        return ret;
    }

    /**
     * ApiIgnore 使用该注解忽略这个API，不会生成接口文档。可注解在类和方法上
     * @return
     */
    @ApiIgnore
    @RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "获取用户列表", notes = "获取用户列表")
    public Map<String, Object> getAll() {
        Map<String, Object> ret = new HashMap<>();
        ret.put("msg", "获取成功");
        return ret;
    }

}
