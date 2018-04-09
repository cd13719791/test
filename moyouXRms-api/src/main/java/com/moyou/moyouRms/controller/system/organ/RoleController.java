package com.moyou.moyouRms.controller.system.organ;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.moyou.moyouRms.constants.Const;
import com.moyou.moyouRms.constants.enums.ResponseEnum;
import com.moyou.moyouRms.controller.BaseController;
import com.moyou.moyouRms.interceptor.Page;
import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.po.system.organ.Org;
import com.moyou.moyouRms.model.po.system.organ.Role;
import com.moyou.moyouRms.model.sysBaseRole.SysBaseRole;
import com.moyou.moyouRms.model.tree.ZNodes;
import com.moyou.moyouRms.response.AjaxRes;
import com.moyou.moyouRms.response.ApiResult;
import com.moyou.moyouRms.service.system.organ.OrgService;
import com.moyou.moyouRms.service.system.organ.RoleService;
import com.moyou.moyouRms.service.system.resource.SysBaseResourcesService;
import com.moyou.moyouRms.service.system.role.SysBaseRoleService;
import com.moyou.moyouRms.util.webpage.PageData;
/*
 * 角色管理
 */
@Controller
@RequestMapping("/backstage/org/role/")
public class RoleController  extends BaseController{
	@Resource
	private SysBaseResourcesService sysBaseResourcesService;
	@Autowired
	public OrgService orgService;
	@Resource
	private SysBaseRoleService sysBaseRoleService;
	@Autowired
	public RoleService roleService;
	
	private static final String SECURITY_URL="/backstage/org/role/index";
	/**
	 * 角色首页
	 */
	@RequestMapping("index")
//	@Log(operationType="查询操作:",operationName="查询角色首页")
	public String index(Model model) throws UnsupportedEncodingException{	
		if(doSecurityIntercept(Const.RESOURCES_TYPE_MENU)){
			model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));		
			return "/system/org/role/list";
		}
		return Const.NO_AUTHORIZED_URL;
	}
	
	@RequestMapping(value="findByPage", method=RequestMethod.POST)
//	@Log(operationType="查询操作:",operationName="查询所有角色")
	@ResponseBody
	public AjaxRes findByPageBean(Page<Role> page,Role o){
		AjaxRes ar=getAjaxRes();
		if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU,SECURITY_URL))){
			try {
				Page<Role> roles=roleService.findAllRoleByPage(o, page);
				Map<String, Object> p=new HashMap<String, Object>();
				p.put("permitBtn",getPermitBtn(Const.RESOURCES_TYPE_BUTTON));
				p.put("list",roles);		
				ar.setSucceed(p);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.DATA_FAIL);
			}
		}
		return ar;
	}
	
	
	@RequestMapping(value="add", method=RequestMethod.POST)
//	@Log(operationType="添加操作:",operationName="添加角色")
	@ResponseBody
	public AjaxRes add(Role o){
		AjaxRes ar=getAjaxRes();
//		if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_FUNCTION))){		
			try {
				Org org=new Org();
				org.setId("1");
				if(StringUtils.isNotBlank(o.getOrgId())){
					org.setId(o.getOrgId());
					List<Org> orgs=orgService.find(org);
					if(orgs.size()>0){
						Org pOrg=orgs.get(0);
						String pId=pOrg.getpId();
						if(StringUtils.isNotBlank(pId)){
							o.setId(get32UUID());
							o.setCreateTime(new Date());
							roleService.insert(o);
							ar.setSucceedMsg(Const.SAVE_SUCCEED);
						}
					}
				}						
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.SAVE_FAIL);
			}
//		}
		return ar;
	}
	
	@RequestMapping(value="delBatch", method=RequestMethod.POST)
//	@Log(operationType="删除操作:",operationName="删除角色")
	@ResponseBody
	public AjaxRes delBatch(String chks){
		AjaxRes ar=getAjaxRes();
		if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_FUNCTION))){		
			try {
				String[] chk =chks.split(",");
				List<Role> list=new ArrayList<Role>();
				for(String s:chk){
					Role sd=new Role();
					sd.setId(s);
					list.add(sd);
				}
				roleService.deleteBatch(list);
				ar.setSucceedMsg(Const.DEL_SUCCEED);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.DEL_FAIL);
			}
		}
		return ar;
	}
	
	@RequestMapping(value="find", method=RequestMethod.POST)
//	@Log(operationType="查询操作:",operationName="查询角色")
	@ResponseBody
	public AjaxRes find(Role o){
		AjaxRes ar=getAjaxRes();
		if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON))){		
			try {
				List<Role> list=roleService.find(o);
				Role role=list.get(0);
				ar.setSucceed(role);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.DATA_FAIL);
			}
		}
		return ar;
	}
	
	@RequestMapping(value="update", method=RequestMethod.POST)
//	@Log(operationType="修改操作:",operationName="修改角色")
	@ResponseBody
	public AjaxRes update(Role o){
		AjaxRes ar=getAjaxRes();	
		if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON))){	
			try {
				o.setUpdateTime(new Date());
				roleService.update(o);
				ar.setSucceedMsg(Const.UPDATE_SUCCEED);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.UPDATE_FAIL);
			}
		}	
		return ar;
	}
	
	@RequestMapping(value="del", method=RequestMethod.POST)
//	@Log(operationType="删除操作:",operationName="删除角色")
	@ResponseBody
	public AjaxRes del(Role o){
		AjaxRes ar=getAjaxRes();
		if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON))){	
			try {
				roleService.delete(o);
				ar.setSucceedMsg(Const.DEL_SUCCEED);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.DEL_FAIL);
			}
		}
		return ar;
	}
	
	@RequestMapping(value="listAuthorized", method=RequestMethod.POST)
//	@Log(operationType="查询操作:",operationName="查询权限列表包含按钮")
	@ResponseBody
	public AjaxRes listAuthorized(){
		AjaxRes ar=getAjaxRes();
		if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON))){		
			try {
				PageData pd = this.getPageData();
				String roleId=pd.getString("id");
				String layer=pd.getString("layer");
				List<ZNodes> r=roleService.listAuthorized(roleId,layer);
				ar.setSucceed(r);			
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.DATA_FAIL);
			}
		}	
		return ar;
	}
	
	@RequestMapping(value="saveAuthorized", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes saveAuthorized(){
		AjaxRes ar=getAjaxRes();
		if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON,"/backstage/org/role/listAuthorized"))){
			try {
				PageData pd = this.getPageData();
				String roleId=pd.getString("id");
				String aus=pd.getString("aus");	
				String layer=pd.getString("layer");	
				roleService.saveAuthorized(roleId,aus,layer);
				ar.setSucceedMsg(Const.UPDATE_SUCCEED);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.UPDATE_FAIL);
			}
		}	
		return ar;
	}
	
	@RequestMapping(value="getOrgTree", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes getOrgTree(){
		AjaxRes ar=getAjaxRes();
		if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU,SECURITY_URL))){	
			try {
				List<ZNodes> r=orgService.getOrgTree();
				ar.setSucceed(r);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.DATA_FAIL);
			}
		}
		return ar;
	}
	
	@RequestMapping(value="getPreOrgTree", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes getPreOrgTree(){
		AjaxRes ar=getAjaxRes();
		if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU,SECURITY_URL))){	
			try {
				List<ZNodes> r=orgService.getPreOrgTree();
				ar.setSucceed(r);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.DATA_FAIL);
			}
		}	
		return ar;
	}
	
	@RequestMapping(value="addOrg", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes addOrg(Org o){
		AjaxRes ar=getAjaxRes();
		if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU,SECURITY_URL))){	
			try {
				o.setId(get32UUID());
				o.setCreateTime(new Date());
				orgService.insert(o);
				ar.setSucceedMsg(Const.SAVE_SUCCEED);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.SAVE_FAIL);
			}
		}
		return ar;
	}
	
	
	@RequestMapping(value="updateOrg", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes updateOrg(Org o){
		AjaxRes ar=getAjaxRes();	
		if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU,SECURITY_URL))){	
			try {
				o.setUpdateTime(new Date());
				orgService.update(o);
				ar.setSucceedMsg(Const.UPDATE_SUCCEED);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.UPDATE_FAIL);
			}
		}	
		return ar;
	}
	
	@RequestMapping(value="findOrg", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes findOrg(Org o){
		AjaxRes ar=getAjaxRes();
		if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU,SECURITY_URL))){	
			try {
				List<Org> list=orgService.find(o);
				Org org=list.get(0);
				ar.setSucceed(org);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.DATA_FAIL);
			}
		}
		return ar;
	}
	
	@RequestMapping(value="delOrg", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes delOrg(Org o){
		AjaxRes ar=getAjaxRes();
		if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU,SECURITY_URL))){	
			try {
				int res=orgService.delOrg(o);
				if(res==1)ar.setSucceedMsg(Const.DEL_SUCCEED);
				else      ar.setFailMsg("请先删除所有其子组织或子角色");	
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.DEL_FAIL);	
			}
		}
		return ar;
	}
	
	
	@RequestMapping(value="orglistAuthorized", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes orglistAuthorized(){
		AjaxRes ar=getAjaxRes();
		if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU,SECURITY_URL))){	
			try {
				PageData pd = this.getPageData();
				String orgId=pd.getString("id");
				String layer=pd.getString("layer");
				List<ZNodes> r=orgService.listAuthorized(orgId,layer);
				ar.setSucceed(r);			
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.DATA_FAIL);
			}
		}	
		return ar;
	}
	
	@RequestMapping(value="saveOrgAuthorized", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes saveOrgAuthorized(){
		AjaxRes ar=getAjaxRes();
		if(ar.setNoAuth(doSecurityIntercept(Const.RESOURCES_TYPE_MENU,SECURITY_URL))){	
			try {
				PageData pd = this.getPageData();
				String orgId=pd.getString("id");
				String aus=pd.getString("aus");	
				String layer=pd.getString("layer");	
				orgService.saveAuthorized(orgId,aus,layer);
				ar.setSucceedMsg(Const.UPDATE_SUCCEED);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(Const.UPDATE_FAIL);
			}
		}
		return ar;
	}
	/**
	 * 查询所有角色
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/queryRoleByPageBean")
	@ResponseBody
	public ApiResult queryRoleByPageBean(@RequestBody Map<String,Object> map){
		PageBean pb=this.getJsonWrapPageBean(map);
		pb.setResults(sysBaseRoleService.querySysBaseRoleByPageBean(pb));
		return new ApiResult(ResponseEnum.SUCCESS.getValue(),"成功",pb);
	}
	/**
	 * 添加角色
	 */
	@RequestMapping(value = "/insertSelective")
	@ResponseBody
	public ApiResult insertRole(@RequestBody SysBaseRole sysBaseRole){
		sysBaseRoleService.insertSelective(sysBaseRole);
		return new ApiResult(ResponseEnum.SUCCESS.getValue(),"成功");
	}
	/**
	 * 编辑角色
	 */
	@RequestMapping(value = "/updateRoleSelective")
	@ResponseBody
	public ApiResult updateRoleSelective(@RequestBody SysBaseRole sysBaseRole){
		Assert.notNull(sysBaseRole);
		Assert.notNull(sysBaseRole.getId());
		sysBaseRoleService.updateByPrimaryKeySelective(sysBaseRole);
		return new ApiResult(ResponseEnum.SUCCESS.getValue(),"成功");
	}
	/**
	 * 查询所有权限
	 */
	@RequestMapping(value = "/queryResources")
	@ResponseBody
	public ApiResult queryResources(@RequestBody Map<String,Object> map){
		Assert.notNull(map);
		return new ApiResult(ResponseEnum.SUCCESS.getValue(),"成功",sysBaseResourcesService.queryResourcesByParentId(map.get("id").toString(),"0"));
	}
	
	/**
	 * 修改角色权限
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/updateResourcesForRole")
	@ResponseBody
	public ApiResult updateResourcesForRole(@RequestBody Map<String,Object> map){
		Assert.notNull(map);
		String roleId=map.get("roleId").toString();
		List<String> list=(List<String>)map.get("sysBaseRoleList");
		return new ApiResult(ResponseEnum.SUCCESS.getValue(),"成功",sysBaseRoleService.updateResourcesForRole(roleId,list));
	}
	
}
