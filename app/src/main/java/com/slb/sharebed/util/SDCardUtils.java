package com.slb.sharebed.util;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;

import java.io.File;

/**
 * SD卡工具箱
 */
public class SDCardUtils {
	/**
	 * 获取SD卡的状�?
	 * 
	 * @return
	 */
	public static String getState() {
		return Environment.getExternalStorageState();
	}

	/**
	 * SD卡是否可�?
	 * 
	 * @return 只有当SD卡已经安装并且准备好了才返回true
	 */
	public static boolean isAvailable() {
		return getState().equals(Environment.MEDIA_MOUNTED);
	}

	/**
	 * 获取SD卡的根目�?
	 * 
	 * @return null：不存在SD�?
	 */
	public static File getRootDirectory() {
		return isAvailable() ? Environment.getExternalStorageDirectory() : null;
	}

	/**
	 * 获取SD卡的根路�?
	 * 
	 * @return null：不存在SD�?
	 */
	public static String getRootPath() {
		File rootDirectory = getRootDirectory();
		return rootDirectory != null ? rootDirectory.getPath() : null;
	}

	/**
	 * 获取SD卡�?的容�?
	 * 
	 * @return 总容量；-1：SD卡不可用
	 */
	@SuppressWarnings("deprecation")
	public static long getTotalSize() {
		if (isAvailable()) {
			StatFs statFs = new StatFs(getRootDirectory().getPath());
			if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
				return statFs.getBlockCount() * statFs.getBlockSize();
			} else {
				return statFs.getBlockCount() * statFs.getBlockSize();
			}
		} else {
			return -1;
		}
	}

	/**
	 * 获取SD卡中可用的容�?
	 * 
	 * @return 可用的容量；-1：SD卡不可用
	 */
	@SuppressWarnings("deprecation")
	@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
	public static long getAvailableSize() {
		if (isAvailable()) {
			StatFs statFs = new StatFs(getRootDirectory().getPath());
			if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
				return statFs.getAvailableBlocks() * statFs.getBlockSize();
			} else {
				return statFs.getAvailableBlocks() * statFs.getBlockSize();
			}
		} else {
			return -1;
		}
	}
}
