package com.bdqn.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bdqn.sys.config.SystemConstant;
import com.bdqn.sys.entity.Permission;
import com.bdqn.sys.entity.User;
import com.bdqn.sys.service.PermissionService;
import com.bdqn.sys.utils.DataGridViewResult;
import com.bdqn.sys.utils.TreeNode;
import com.bdqn.sys.utils.TreeNodeBuilder;
import com.bdqn.sys.vo.PermissionVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/sys/menu")
public class MenuController {

    @Resource
    private PermissionService permissionService;


    @RequestMapping("/loadIndexLeftMenu")
    public DataGridViewResult loadIndexLeftMenu(PermissionVo permissionVo, HttpSession session){
        List<Permission> permissions = new ArrayList<Permission>();
        try {
            QueryWrapper<Permission> queryWrapper = new QueryWrapper<Permission>();
            queryWrapper.eq("type", SystemConstant.TYPE_MENU);//只查询菜单
            //获取当前登录用户
            User user = (User) session.getAttribute(SystemConstant.LOGINUSER);
            //如果当前登录用户为超级管理员，则能查看所有菜单
            if(user.getType()==SystemConstant.SUPERUSER){
                //查询菜单列表
                permissions = permissionService.list(queryWrapper);
            }else{
                //普通用户：需要根据当前用户的角色及权限加载菜单列表
                //查询菜单列表
                permissions = permissionService.list(queryWrapper);
            }
            //构建菜单节点集合
            List<TreeNode> treeNodes = new ArrayList<TreeNode>();
            for (Permission permission : permissions) {
                //判断当前节点是否展开，是则为true，否则为false
                Boolean spread = SystemConstant.OPEN_TRUE == permission.getOpen() ? true : false;
                treeNodes.add(new TreeNode(permission.getId(), permission.getPid(),
                        permission.getTitle(), permission.getIcon(),
                        permission.getHref(), spread));
            }
            //构建节点菜单层级关系(参数1：节点集合数据源，参数2：根节点编号)
            List<TreeNode> treeNodeList = TreeNodeBuilder.build(treeNodes,1);
            //将节点返回出去
            return new DataGridViewResult(treeNodeList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
