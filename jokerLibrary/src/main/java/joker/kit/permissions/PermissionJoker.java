package joker.kit.permissions;

import android.app.Activity;

import com.hjq.permissions.OnPermission;
import com.hjq.permissions.XXPermissions;

import java.util.List;

import joker.kit.customView.ios.MessageDialog;
import joker.kit.utils.LogUtils;

public class PermissionJoker {


    /**
     * 获取动态权限
     * mPcallback  回调
     * String[]... permissions  权限组 Permission.SYSTEM_ALERT_WINDOW
     * 使用框架：https://github.com/getActivity/XXPermissions
     */
    public static void checkPermission(final Activity activity,
                                       final PermissionCallback mPcallback, String[]... permissions) {
        //是否有这个权限
        if (XXPermissions.isHasPermission(activity, permissions)) {
            mPcallback.onSucess();
        } else {
            XXPermissions.with(activity)
                    //可设置被拒绝后继续申请，直到用户授权或者永久拒绝
                    //.constantRequest()
                    //支持请求6.0悬浮窗权限8.0请求安装权限
                    //.permission(Permission.SYSTEM_ALERT_WINDOW, Permission.REQUEST_INSTALL_PACKAGES)
                    //不指定权限则自动获取清单中的危险权限
                    .permission(permissions)
                    .request(new OnPermission() {

                        @Override
                        public void hasPermission(List<String> granted, boolean isAll) {
                            if (isAll) {
                                LogUtils.e("获取权限成功");
                            } else {
                                LogUtils.e("获取权限成功，部分权限未正常授予");
                            }
                            mPcallback.onSucess();
                        }

                        @Override
                        public void noPermission(List<String> denied, boolean quick) {
                            if (quick) {
                                LogUtils.e("被永久拒绝授权，请手动授予权限");

                                MessageDialog dialog = new MessageDialog(activity);
                                dialog.setTitle("已永久拒绝权限申请,是否跳转到系统设置页面?");
                                dialog.setCstDialogOnClickListener(new MessageDialog.CstDialogOnClickListener() {
                                    @Override
                                    public void onClickSure() {
                                        //跳转到设置页面   如果是被永久拒绝就跳转到应用权限系统设置页面
                                        XXPermissions.gotoPermissionSettings(activity);
                                    }

                                    @Override
                                    public void onClickCancel() {

                                    }
                                });
                                dialog.show();
                            } else {
                                LogUtils.e("获取权限失败");

                            }
                            mPcallback.onFailure();
                        }
                    });
        }
    }


}
