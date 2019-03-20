package joker.kit.function;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

/**
 * 剪贴板相关操作方法
 * https://www.cnblogs.com/travellife/p/Android-jian-tie-ban-xiang-jie.html
 * 待续补充优化..
 */
public class ClipboardManagerJoker {
    private Context context;
    private ClipboardManager clipboard;
    private ClipData clipData;//剪贴板内容

    public ClipboardManagerJoker(Context context) {
        this.context = context;
        // 获取系统剪贴板
        clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        // 获取剪贴板的剪贴数据集
        clipData = clipboard.getPrimaryClip();
    }

    /**
     * 判断剪贴板内是否有数据
     */
    public boolean isContent() {

        if (clipData != null && clipData.getItemCount() > 0 &&
                clipData.getItemAt(0).getText() != null && !clipData.getItemAt(0).getText().toString().equals(""))
            return true;

//        return clipboard.hasPrimaryClip();

        return false;
    }

    /**
     * 获取剪贴板内容
     */
    public String getPrimaryClip() {

        if (isContent()) return clipData.getItemAt(0).getText().toString();

        return null;
    }

    /**
     * 往剪贴板灌数据
     */
    public void setPrimaryClip(String content) {
        if (content != null && !content.equals(""))
            // 把数据集设置（复制）到剪贴板
            clipboard.setPrimaryClip(ClipData.newPlainText(null, content));
        else
            System.out.println("joker.kit.function.ClipboardManagerJoker  =  传入剪贴板数据为空");
    }

    /**
     * 清空剪贴板
     */
    public void clearClip() {
        clipboard.setText("");
        clipData = clipboard.getPrimaryClip();
    }

}






















