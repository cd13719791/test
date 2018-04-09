package com.moyou.moyouRms.model.sysBaseAccountRole;

public class SysBaseAccountRole {
    /**
     * 
     */
    private String accountId;

    /**
     * 
     */
    private String roleId;

    /**
     * 
     * @return account_id 
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     * 
     * @param accountId 
     */
    public void setAccountId(String accountId) {
        this.accountId = accountId == null ? null : accountId.trim();
    }

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
}