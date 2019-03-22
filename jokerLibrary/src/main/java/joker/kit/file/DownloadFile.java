package joker.kit.file;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadFile {
    /**
     * 下载文件 小文件~
     * 不可以在主线程运行哟~
     *
     * @param mUrl     下载路径
     * @param sdFile   存放SD卡的路径
     * @param fileName 文件名 xxx.jpg
     * @return
     * @throws Exception
     */
    public static File downloadSmall(String mUrl, String sdFile, String fileName) throws Exception {
        // 如果相等的话表示当前的sdcard挂载在手机上并且是可用的
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            URL url = new URL(mUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            // 获取到文件的大小
            //pd.setMax(conn.getContentLength());

            InputStream is = conn.getInputStream();

            File file = new File(sdFile, fileName);

            // 目录不存在创建目录
            if (!file.getParentFile().exists())
                file.getParentFile().mkdirs();
            FileOutputStream fos = new FileOutputStream(file);
            BufferedInputStream bis = new BufferedInputStream(is);
            byte[] buffer = new byte[1024];
            int len;
            int total = 0;
            while ((len = bis.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
                total += len;
                // 获取当前下载量
                //pd.setProgress(total);
            }
            fos.close();
            bis.close();
            is.close();
            return file;
        } else {
            throw new IOException("未发现有SD卡");
        }
    }

    /**
     * Android自定的下载管理（会在notification 显示下载的进度，同时可以暂停、重新连接等）
     *
     * @param mUrl     下载路径(这里不需要绝对路径 直接根目录起.../Joker/live/image/  方法里做了截取操作 )
     * @param sdFile   存放SD卡的路径
     * @param fileName 文件名 xxx.jpg
     *                 <p>
     *                 相关技术帖子
     *                 https://my.oschina.net/zbj1618/blog/1536946
     */
    public static void downloadFileSystem(Context context, String mUrl, String sdFile, String fileName) {

        //创建下载任务,downloadUrl就是下载链接
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(mUrl));
        /*
         * 指定下载路径和下载文件名
         * /Joker/live/image/
         * 这里Joker就会直接是根目录
         * */
        request.setDestinationInExternalPublicDir(sdFile.replace(SdcardUtil.sdFile(), ""), fileName);

        //获取下载管理器
        DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        //将下载任务加入下载队列，否则不会进行下载
        downloadManager.enqueue(request);
    }


    /**
     * 下载的图片插入到系统图库
     */
    public static void saveSystemImg(Context mContext, File mFile) {
        //把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(mContext.getContentResolver(),
                    mFile.getAbsolutePath(), mFile.getName(), null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //保存图片后发送广播通知更新数据库
        Uri uri = Uri.fromFile(mFile);
        mContext.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri));

    }

}
