package joker.kit.permissions;

/**
 * 运行时权限申请时回调接口
 */
public interface PermissionCallback {
    /**用户通过申请*/
    void onSucess();

    /**用户拒绝申请*/
    void onFailure();
}
