package joker.kit.permissions;

import android.app.Activity;
import android.content.Context;

import java.util.List;

public class _Test {

    public void test(final Activity activity, final Context context){

        JokerCorePermissions.with(activity)
                //.constantRequest() //可设置被拒绝后继续申请，直到用户授权或者永久拒绝
                //.permission(Permission.REQUEST_INSTALL_PACKAGES, Permission.SYSTEM_ALERT_WINDOW) //支持请求安装权限和悬浮窗权限
                .permission(Permission.Group.STORAGE)
                .request(new OnPermission() {

                    @Override
                    public void hasPermission(List<String> granted, boolean isAll) {
                        if (isAll) {
                            Toast("获取权限成功");
                        } else {
                            Toast("获取权限成功，部分权限未正常授予");
                        }
                    }

                    @Override
                    public void noPermission(List<String> denied, boolean quick) {
                        if (quick) {
                            Toast("被永久拒绝授权，请手动授予权限");
                            //如果是被永久拒绝就跳转到应用权限系统设置页面
                            JokerCorePermissions.gotoPermissionSettings(context);
                        } else {
                            Toast("获取权限失败");
                        }
                    }
                });

    }

    public void Toast(String s){

    }
}
