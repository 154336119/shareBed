package com.slb.sharebed.util;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import com.bumptech.glide.Glide;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;

public class FileUtils {
    /**
     * 获取动态文件目录
     *
     * @param context 上下文
     * @return 如果SD卡可用，就返回外部文件目录，否则返回机身自带文件目录
     */
    public static File getDynamicFilesDir(Context context) {
        if (SDCardUtils.isAvailable()) {
            File dir = context.getExternalFilesDir(null);
            if (dir == null) {
                dir = context.getFilesDir();
            }
            return dir;
        } else {
            return context.getFilesDir();
        }
    }

    /**
     * 获取动态获取缓存目录
     *
     * @param context 上下文
     * @return 如果SD卡可用，就返回外部缓存目录，否则返回机身自带缓存目录
     */
    public static File getDynamicCacheDir(Context context) {
        if (SDCardUtils.isAvailable()) {
            File dir = context.getExternalCacheDir();
            if (dir == null) {
                dir = context.getCacheDir();
            }
            return dir;
        } else {
            return context.getCacheDir();
        }
    }

    public static String getCacheSize(Context context) throws Exception {

        long size = 0;
        if (SDCardUtils.isAvailable()) {
            File dir = context.getExternalCacheDir();
            if (dir != null) {
                size = getFileSizes(dir);
            }
        }
        size += getFileSizes(context.getCacheDir());
//        size = size - getFileSizes(new File("/data/data/"
//                + context.getPackageName() + "/shared_prefs")) ;

        return FormetFileSize(size);
    }

    /**
     * 从文件目录中获取一个文件
     *
     * @param context  上下文
     * @param fileName 要获取的文件的名称
     * @return
     */
    public static File getFileFromFilesDir(Context context, String fileName) {
        return new File(context.getFilesDir().getPath() + File.separator
                + fileName);
    }

    /**
     * 从外部文件目录中获取一个文件
     *
     * @param context  上下文
     * @param fileName 要获取的文件的名称
     * @return null：SD卡不可用
     */
    public static File getFileFromExternalFilesDir(Context context,
                                                   String fileName) {
        if (SDCardUtils.isAvailable()) {
            File dir = context.getExternalFilesDir(null);
            if (dir != null) {
                return new File(dir.getPath() + File.separator + fileName);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * 从缓存目录中获取一个文件
     *
     * @param context  上下文
     * @param fileName 要获取的文件的名称
     * @return
     */
    public static File getFileFromCacheDir(Context context, String fileName) {
        return new File(context.getCacheDir().getPath() + File.separator
                + fileName);
    }

    /**
     * 从外部缓存目录中获取一个文件
     *
     * @param context  上下文
     * @param fileName 要获取的文件的名称
     * @return null：SD卡不可用
     */
    public static File getFileFromExternalCacheDir(Context context,
                                                   String fileName) {
        if (SDCardUtils.isAvailable()) {
            File dir = context.getExternalCacheDir();
            if (dir != null) {
                return new File(dir.getPath() + File.separator + fileName);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * 从动态文件目录中获取文件
     *
     * @param context  上下文
     * @param fileName 要获取的文件的名称
     * @return 如果SD卡可用，就返回外部文件目录中获取文件，否则从机身自带文件目录中获取文件
     */
    public static File getFileFromDynamicFilesDir(Context context,
                                                  String fileName) {
        return new File(getDynamicFilesDir(context).getPath() + File.separator
                + fileName);
    }

    /**
     * 从动态缓存目录中获取文件
     *
     * @param context  上下文
     * @param fileName 要获取的文件的名称
     * @return 如果SD卡可用，就返回外部缓存目录中获取文件，否则从机身自带缓存目录中获取文件
     */
    public static File getFileFromDynamicCacheDir(Context context,
                                                  String fileName) {
        return new File(getDynamicCacheDir(context).getPath() + File.separator
                + fileName);
    }

    public static final int SIZETYPE_B = 1;// 获取文件大小单位为B的double值
    public static final int SIZETYPE_KB = 2;// 获取文件大小单位为KB的double值
    public static final int SIZETYPE_MB = 3;// 获取文件大小单位为MB的double值
    public static final int SIZETYPE_GB = 4;// 获取文件大小单位为GB的double值

    /**
     * 获取文件指定文件的指定单位的大小
     *
     * @param filePath 文件路径
     * @param sizeType 获取大小的类型1为B、2为KB、3为MB、4为GB
     * @return double值的大小
     */
    public static double getFileOrFilesSize(String filePath, int sizeType) {
        File file = new File(filePath);
        long blockSize = 0;
        try {
            if (file.isDirectory()) {
                blockSize = getFileSizes(file);
            } else {
                blockSize = getFileSize(file);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("获取文件大小", "获取失败!");
        }
        return FormetFileSize(blockSize, sizeType);
    }

    /**
     * 调用此方法自动计算指定文件或指定文件夹的大小
     *
     * @param filePath 文件路径
     * @return 计算好的带B、KB、MB、GB的字符串
     */
    public static String getAutoFileOrFilesSize(String filePath) {
        File file = new File(filePath);
        long blockSize = 0;
        try {
            if (file.isDirectory()) {
                blockSize = getFileSizes(file);
            } else {
                blockSize = getFileSize(file);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("获取文件大小", "获取失败!");
        }
        return FormetFileSize(blockSize);
    }

    /**
     * 获取指定文件大小
     */
    private static long getFileSize(File file) throws Exception {
        long size = 0;
        if (file.exists()) {
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(file);
                size = fis.available();
            } finally {
                if (fis != null) {
                    fis.close();
                }
            }

        } else {
            file.createNewFile();
            Log.e("获取文件大小", "文件不存在!");
        }
        return size;
    }

    /**
     * 获取指定文件夹
     *
     * @param f
     * @return
     */
    private static long getFileSizes(File f) throws Exception {
        long size = 0;
        File flist[] = f.listFiles();
        for (File aFlist : flist) {
            if (aFlist.isDirectory()) {
                size = size + getFileSizes(aFlist);
            } else {
                size = size + getFileSize(aFlist);
            }
        }
        return size;
    }

    /**
     * 转换文件大小
     *
     * @param fileS
     * @return
     */
    private static String FormetFileSize(long fileS) {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        String wrongSize = "0B";
        if (fileS == 0) {
            return wrongSize;
        }
        if (fileS < 1024) {
            fileSizeString = "0KB";
//            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "KB";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "MB";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "GB";
        }
        return fileSizeString;
    }

    /**
     * 转换文件大小,指定转换的类型
     *
     * @param fileS
     * @param sizeType
     * @return
     */
    private static double FormetFileSize(long fileS, int sizeType) {
        DecimalFormat df = new DecimalFormat("#.00");
        double fileSizeLong = 0;
        switch (sizeType) {
            case SIZETYPE_B:
                fileSizeLong = Double.valueOf(df.format((double) fileS));
                break;
            case SIZETYPE_KB:
                fileSizeLong = Double.valueOf(df.format((double) fileS / 1024));
                break;
            case SIZETYPE_MB:
                fileSizeLong = Double.valueOf(df.format((double) fileS / 1048576));
                break;
            case SIZETYPE_GB:
                fileSizeLong = Double.valueOf(df
                        .format((double) fileS / 1073741824));
                break;
            default:
                break;
        }
        return fileSizeLong;
    }

    /**
     * 　　* 保存文件 　　* @param toSaveString 　　* @param filePath
     */
    public static void saveFile(String toSaveString, String filePath) {
        try {
            File saveFile = new File(filePath);
            if (!saveFile.exists()) {
                File dir = new File(saveFile.getParent());
                dir.mkdirs();
                saveFile.createNewFile();
            }
            FileOutputStream outStream = new FileOutputStream(saveFile);
            outStream.write(toSaveString.getBytes());
            outStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public static void saveFile(byte[] bytes, String savePath) throws IOException {
//        File dec = new File(KnightConstans.savePath);
//        if (!dec.exists()) {
//            dec.mkdirs();
//        }
//        File file = new File(savePath);
//        FileOutputStream outputStream = new FileOutputStream(file);
//        outputStream.write(bytes);
//        KLog.d("DOWNLOAD", "down file and save success");
//    }

    /**
     * 清除本应用内部缓存(/data/data/com.xxx.xxx/cache) * * @param context
     */
    public static void cleanInternalCache(Context context) {
        deleteFilesByDirectory(context.getCacheDir());
    }

    /**
     * 清除本应用所有数据库(/data/data/com.xxx.xxx/databases) * * @param context
     */
    public static void cleanDatabases(Context context) {
        deleteFilesByDirectory(new File("/data/data/"
                + context.getPackageName() + "/databases"));
    }

    /**
     * * 清除本应用SharedPreference(/data/data/com.xxx.xxx/shared_prefs) * * @param
     * context
     */
    public static void cleanSharedPreference(Context context) {
        deleteFilesByDirectory(new File("/data/data/"
                + context.getPackageName() + "/shared_prefs"));
    }

    /**
     * 按名字清除本应用数据库 * * @param context * @param dbName
     */
    public static void cleanDatabaseByName(Context context, String dbName) {
        context.deleteDatabase(dbName);
    }

    /**
     * 清除/data/data/com.xxx.xxx/files下的内容 * * @param context
     */
    public static void cleanFiles(Context context) {
        deleteFilesByDirectory(context.getFilesDir());
    }

    /**
     * * 清除外部cache下的内容(/mnt/sdcard/android/data/com.xxx.xxx/cache) * * @param
     * context
     */
    public static void cleanExternalCache(Context context) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            deleteFilesByDirectory(context.getExternalCacheDir());
        }
    }

    /**
     * 清除自定义路径下的文件，使用需小心，请不要误删。而且只支持目录下的文件删除 * * @param filePath
     */
    public static void cleanCustomCache(String filePath) {
        deleteFilesByDirectory(new File(filePath));
    }

    /**
     * 清除本应用所有的数据 * * @param context * @param filepath
     */
    public static void cleanApplicationData(Context context, String... filepath) {
        Glide.get(context).clearDiskCache();
        cleanInternalCache(context);
        cleanExternalCache(context);
//		cleanDatabases(context);
//		cleanSharedPreference(context);
        cleanFiles(context);
        for (String filePath : filepath) {
            cleanCustomCache(filePath);
        }
    }

    /**
     * 删除方法 这里只会删除某个文件夹下的文件，如果传入的directory是个文件，将不做处理 * * @param directory
     */
    public static boolean deleteFilesByDirectory(File dir) {
//        if (directory != null && directory.exists() && directory.isDirectory()) {
//            for (File item : directory.listFiles()) {
//                item.delete();
//            }
//        }

        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteFilesByDirectory(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();

    }

    public static String getFileContent(String path) {
        return getFileContent(new File(path));
    }

    public static String getFileContent(File f) {
        StringBuffer sb = new StringBuffer("");
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(
                    new FileInputStream(f), "utf-8"));
            String strBuffer = "";
            while ((strBuffer = br.readLine()) != null) {
                sb.append(strBuffer + "\n");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

    public static void writeFile(InputStream in, File file) throws IOException {
        if (!file.getParentFile().exists())
            file.getParentFile().mkdirs();

        if (file != null && file.exists())
            file.delete();

        FileOutputStream out = new FileOutputStream(file);
        byte[] buffer = new byte[1024 * 128];
        int len = -1;
        while ((len = in.read(buffer)) != -1) {
            out.write(buffer, 0, len);
        }
        out.flush();
        out.close();
        in.close();
    }

    public static boolean deleteFile(String sPath) {
        Boolean flag = false;
        File file = new File(sPath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }
        return flag;
    }

    /**
     * Gets the corresponding path to a file from the given content:// URI
     *
     * @param selectedVideoUri The content:// URI to find the file path from
     * @param contentResolver  The content resolver to use to perform the query.
     * @return the file path as a string
     */
    public static String getFilePathFromContentUri(Uri selectedVideoUri,
                                                   ContentResolver contentResolver) {
        String filePath;
        String[] filePathColumn = {MediaStore.MediaColumns.DATA};

        Cursor cursor = contentResolver.query(selectedVideoUri, filePathColumn, null, null, null);
//      也可用下面的方法拿到cursor
//      Cursor cursor = this.context.managedQuery(selectedVideoUri, filePathColumn, null, null, null);

        cursor.moveToFirst();

        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        filePath = cursor.getString(columnIndex);
        cursor.close();
        return filePath;
    }



}