package com.wink_172.library.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.os.StatFs;
import android.provider.MediaStore;
import android.util.Log;


import org.xutils.x;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class FileUtil {
    public enum FileType {
        IMG,
        AUDIO,
        VIDEO,
        FILE,
    }

    FileType bb = FileType.AUDIO;

    public static final String EXTERNAL_DATA_DIR = "/Android/data/"+ x.app().getPackageName()+ "/files/";

    public static boolean isExternalStorageMounted() {

        return Environment.MEDIA_MOUNTED.equals(
                Environment.getExternalStorageState()
        );
    }

    public static File getExternalStorageDir() {
        return Environment.getExternalStorageDirectory();
    }


    public static File getAppExternalDataDir() {
        if (isExternalStorageMounted()) {
            File appDir = new File(getExternalStorageDir(), EXTERNAL_DATA_DIR);
            if (!appDir.exists()) {
                synchronized (appDir) {
                    if (!appDir.exists()) {
                        appDir.mkdirs();
                    }
                }
            }
            return appDir;
        }

        return null;
    }

    /**
     * @param type the files type.
     */
    public static File getAppFilesDir(String type) {
        if (!isExternalStorageMounted()) {
            return null;
        }


        if (type == null || type.trim().equals("")) {
            return getAppExternalDataDir();
        }

        File typeDir = new File(getAppExternalDataDir(), type);
        if (!typeDir.exists()) {
            synchronized (typeDir) {
                if (!typeDir.exists()) {
                    typeDir.mkdirs();
                }
            }
        }
        return typeDir;
    }

    public static void makeRootDirectory(String filePath) {
        File file = null;
        try {

            file = new File(filePath);
            if (!file.exists()) {
                file.mkdir();
            }
        } catch (Exception e) {
            Log.i("error:", e + "");
        }
    }

    public static String getFileUrlName(String pathandname) {
        int start = pathandname.lastIndexOf("/");
        if (start != -1) {
            return pathandname.substring(start);
        } else {
            return null;
        }
    }

    private void getRandomName() {
//        File file = new File(Constants.FALNS_PLANTE_BASE_PATH);
//        if (!file.exists() || !file.isDirectory()) {
//            file.mkdirs();
//        }
//
//        String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
    }

    //    通过Uri获取路径以及文件名一种方法
    public static String getRealFilePath(final Context context, final Uri uri) {
        if (null == uri) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null)
            data = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }

    /**
     * 检查SD卡是否存在
     */
    private static boolean checkSdCard() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 获取手机SD卡总空间
     */
    private static long getSDcardTotalSize() {
        if (checkSdCard()) {
            File path = Environment.getExternalStorageDirectory();
            StatFs mStatFs = new StatFs(path.getPath());
            long blockSizeLong = mStatFs.getBlockSizeLong();
            long blockCountLong = mStatFs.getBlockCountLong();
            return blockSizeLong * blockCountLong;
        } else {
            return 0;
        }
    }

    /**
     * 获取SDka可用空间
     */
    private static long getSDcardAvailableSize() {
        if (checkSdCard()) {
            File path = Environment.getExternalStorageDirectory();
            StatFs mStatFs = new StatFs(path.getPath());
            long blockSizeLong = mStatFs.getBlockSizeLong();
            long availableBlocksLong = mStatFs.getAvailableBlocksLong();
            return blockSizeLong * availableBlocksLong;
        } else {
            return 0;
        }
    }

    /**
     * 获取手机内部存储总空间
     */
    public static long getPhoneTotalSize() {
        if (!checkSdCard()) {
            File path = Environment.getDataDirectory();
            StatFs mStatFs = new StatFs(path.getPath());
            long blockSizeLong = mStatFs.getBlockSizeLong();
            long blockCountLong = mStatFs.getBlockCountLong();
            return blockSizeLong * blockCountLong;
        } else {
            return getSDcardTotalSize();
        }
    }

    /**
     * 获取手机内存存储可用空间
     */
    public static long getPhoneAvailableSize() {
        if (!checkSdCard()) {
            File path = Environment.getDataDirectory();
            StatFs mStatFs = new StatFs(path.getPath());
            long blockSizeLong = mStatFs.getBlockSizeLong();
            long availableBlocksLong = mStatFs.getAvailableBlocksLong();
            return blockSizeLong * availableBlocksLong;
        } else
            return getSDcardAvailableSize();
    }

    public static String getFileName(String filePath) {
        File file = new File(filePath);
        return file.exists() && !file.isDirectory() && file.canRead() ? file.getName() : null;
    }

    public static boolean isLegalFile(String filePath) {
        File file = new File(filePath);
        return file.exists() && !file.isDirectory() && file.canRead();
    }

    public static long getFileLength(String filePath) throws Exception {
        if (!isLegalFile(filePath)) {
            throw new IllegalArgumentException("文件不存在、或不可读、或者是一个目录");
        } else {
            File file = new File(filePath);
            return file.length();
        }
    }

    public static FileInputStream getFileInputStream(String filePath) throws Exception {
        return new FileInputStream(filePath);
    }

    public static void closeFileStream(InputStream inputStream) {
        try {
            if (inputStream != null) {
                inputStream.close();
            }
        } catch (IOException var2) {
            var2.printStackTrace();
        }

    }

    public static String getFileContent(String filePath) throws Exception {
        int fileLength = Long.valueOf(getFileLength(filePath)).intValue();
        return getFileContent(filePath, 0L, fileLength);
    }

    public static String getFileContent(String filePath, long offset, int length) throws Exception {
        FileInputStream fileInputStream = null;

        String var5;
        try {
            fileInputStream = getFileInputStream(filePath);
            var5 = getFileContent((InputStream) fileInputStream, offset, length);
        } finally {
            closeFileStream(fileInputStream);
        }

        return var5;
    }

    public static String getFileContent(InputStream inputStream, long offset, int length) throws Exception {
        byte[] fileContent = getFileContentByte(inputStream, offset, length);
        return fileContent == null ? null : new String(fileContent, Charset.forName("ISO-8859-1"));
    }

    public static byte[] getFileContentByte(InputStream inputStream, long offset, int length) throws Exception {
        if (offset >= 0L && length >= 0) {
            byte[] tempBuf = new byte[length];
            inputStream.skip(offset);
            int readLen = inputStream.read(tempBuf);
            byte[] fileContent;
            if (readLen < 0) {
                fileContent = new byte[0];
                return fileContent;
            } else {
                if (readLen < length) {
                    fileContent = new byte[readLen];
                    System.arraycopy(tempBuf, 0, fileContent, 0, readLen);
                } else {
                    fileContent = tempBuf;
                }

                return fileContent;
            }
        } else {
            throw new Exception("getFileContent param error");
        }
    }

    public static byte[] getFileContentByte(String srcPath, long offset, int slice_size) {
        byte[] dataFile = null;
        RandomAccessFile in = null;

        try {
            byte[] tempBuf = new byte[slice_size];
            in = new RandomAccessFile(srcPath, "r");
            in.seek(offset);
            int readLen = in.read(tempBuf, 0, slice_size);
            if (readLen == slice_size) {
                dataFile = tempBuf;
            } else if (readLen < slice_size && readLen > 0) {
                dataFile = new byte[readLen];
                System.arraycopy(tempBuf, 0, dataFile, 0, readLen);
            }

            byte[] var8 = dataFile;
            return var8;
        } catch (FileNotFoundException var20) {
            var20.printStackTrace();
        } catch (IOException var21) {
            var21.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException var19) {
                    var19.printStackTrace();
                }
            }

        }

        return null;
    }

    public static void delete(String filePath) {
        File file = new File(filePath);
        if (file.exists() && file.isFile()) {
            file.delete();
        }

    }

    private InputStream getAssert(Context context) {
        InputStream is = null;
        try {
            AssetManager assetManager = context.getAssets();
//            AssetFileDescriptor fileDescriptor = assetManager.openFd("chengdu.mp3");
            is = assetManager.open(String.format("emoticon/%d.gif", 22));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return is;
    }

    private String getLrcText(String fileName) {//读取歌曲文件lirc
        String lrcText = null;
        try {
            InputStream is = x.app().getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            lrcText = new String(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lrcText;
    }

    private static final String TAG = "TAG";

    public static boolean isGifInputStream(File file) {
        InputStream fileInputStream = null;
        boolean result = false;
        try {
            fileInputStream = new FileInputStream(file);

            int length = 0;
            byte[] buf = new byte[20];  //建立缓存数组
            String str = "";
            while ((length = fileInputStream.read(buf)) != -1) {
                str = new String(buf, 0, length);
                Log.e(TAG, "buf====>>: " + str);
                break;
            }

            if (str.contains("GIF")) {//动图
                result = true;
            }
            fileInputStream.close(); //关闭资源
            fileInputStream = null;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    //判断文件是否存在
    public static boolean isCacheFileExist(String absoFilePath) {
        File file = new File(absoFilePath);
        return file.exists();
    }


    /**
     * get content from a raw resource. This can only be used with resources whose value is the name of an asset files
     * -- that is, it can be used to open drawable, sound, and raw resources; it will fail on string and color
     * resources.
     *
     * @param context
     * @param resId   The resource identifier to open, as generated by the appt tool.
     * @return
     */
    public static String geFileFromRaw(Context context, int resId) {
        if (context == null) {
            return null;
        }

        StringBuilder s = new StringBuilder();
        try {
            InputStreamReader in = new InputStreamReader(context.getResources().openRawResource(resId));
            BufferedReader br = new BufferedReader(in);
            String line;
            while ((line = br.readLine()) != null) {
                s.append(line);
            }
            return s.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static void createFolder(String path) {
        File dir = new File(path);
        if (!dir.isDirectory()) {
            dir.mkdirs();
        }
    }

    public static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    public static boolean copyDirectory(String source, String destine) {
        if (source == null || destine == null) {
            return false;
        }

        File dirSource = new File(source);
        if (!dirSource.isDirectory()) {
            return false;
        }

        File dirDestine = new File(destine);
        if (!dirDestine.exists()) {
            dirDestine.mkdirs();
        }

        String[] childList = dirSource.list();
        if (childList != null && childList.length > 0) {
            for (String fileName : childList) {
                String childSource = source + File.separator + fileName;
                String childDestine = destine + File.separator + fileName;

                File childSourceFile = new File(childSource);
                if (childSourceFile.isFile()) {
                    copyFile(childSource, childDestine);
                } else {
                    copyDirectory(childSource, childDestine);
                }
            }
        }

        return true;
    }

    public static boolean copyFile(String source, String destine) {
        if (source == null || destine == null) {
            return false;
        }

        File fileSource = new File(source);
        if (!fileSource.exists()) {
            return false;
        }

        File fileDestine = new File(destine);
        if (fileDestine.exists()) {
            return false;
        }

        boolean result = false;
        FileInputStream fis = null;
        FileOutputStream fos = null;

        try {
            int readSize = 0;
            byte[] buffer = new byte[1024];

            fileDestine.createNewFile();
            fis = new FileInputStream(fileSource);
            fos = new FileOutputStream(fileDestine);

            while ((readSize = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, readSize);
            }
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                }
            }

            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                }
            }
        }

        return result;
    }

    public static boolean unzipFile(String source, String destine) {
        if (source == null || destine == null) {
            return false;
        }

        byte[] buffer = null;
        boolean result = false;
        FileInputStream fis = null;
        ZipInputStream zis = null;

        try {
            if (!destine.endsWith(File.separator)) {
                destine += File.separator;
            }

            fis = new FileInputStream(new File(source));
            zis = new ZipInputStream(fis);

            ZipEntry zipEntry;
            while ((zipEntry = zis.getNextEntry()) != null) {
                String entryName = zipEntry.getName();
                if (zipEntry.isDirectory()) {
                    File file = new File(destine + entryName);
                    file.mkdirs();
                } else {
                    if (buffer == null) {
                        buffer = new byte[1024];
                    }

                    File file = new File(destine + entryName);
                    file.createNewFile();
                    FileOutputStream fos = new FileOutputStream(file);

                    int sizeRemains = (int) zipEntry.getSize();
                    while (sizeRemains > 0) {
                        int sizeToRead = sizeRemains;
                        if (sizeToRead > buffer.length) {
                            sizeToRead = buffer.length;
                        }

                        int sizeRealRead = zis.read(buffer, 0, sizeToRead);
                        if (sizeRealRead > 0) {
                            fos.write(buffer, 0, sizeRealRead);
                            sizeRemains -= sizeRealRead;
                        }
                    }

                    fos.close();
                }
                zis.closeEntry();
            }
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (zis != null) {
                try {
                    zis.close();
                } catch (IOException ioe) {
                }
            }

            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException ioe) {
                }
            }
        }

        return result;
    }
}
