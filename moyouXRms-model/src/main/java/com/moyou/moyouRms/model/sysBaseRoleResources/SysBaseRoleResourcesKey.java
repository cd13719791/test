package com.moyou.moyouRms.model.sysBaseRoleResources;

public class SysBaseRoleResourcesKey {
    /**
     * 
     */
    private String roleId;

    /**
     * 
     */
    private String resourcesId;

    /**
     * 
     * @return role_id 
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * 
     * @param roleId 
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    /**
     * 
     * @return resources_id 
     */
    public String getResourcesId() {
        return resourcesId;
    }

    /**
     * sysBaseRoleResourcesKey
     * @param resourcesId 
     */
    public void setResourcesId(String resourcesId) {
        this.resourcesId = resourcesId == null ? null : resourcesId.trim();
    }
}