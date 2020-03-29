package com.bjfu.ecloud.util;

import com.bjfu.ecloud.entity.PhysicalFile;
import com.bjfu.ecloud.entity.VirtualFile;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.hdfs.web.WebHdfsFileSystem;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionOutputStream;
import org.apache.hadoop.util.ReflectionUtils;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class HadoopUtils {

    private static final String URI = "http://47.98.33.37:14000";
    private static final int FOLDER = 0;
    private static final int FILE = 1;
    private static Configuration conf;
    private static URI uri;
    private static LinkedList<WebHdfsFileSystem> fsList;
//    private static WebHdfsFileSystem hdfsFileSystem;

    static {
        conf = new Configuration();
//        conf.set("fs.default.name", URI);
//        conf.set("hadoop.user", "alyssa");
//        conf.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem");
        try {
            uri = new URI(URI);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        fsList = new LinkedList<>();
        for(int i=3;i>0;i--){
            WebHdfsFileSystem webHdfsFileSystem = new WebHdfsFileSystem();
            try {
                webHdfsFileSystem.initialize(uri,conf);
            } catch (IOException e) {
                e.printStackTrace();
            }
            fsList.add(webHdfsFileSystem);
        }
    }

    private static Configuration getConf() {
        return conf;
    }

    public static WebHdfsFileSystem getFileSystemInstance(){
        WebHdfsFileSystem fs = fsList.poll();
        fsList.offer(fs);
        return fs;
    }

    /**
     * 创建文件/文件夹
     *
     * @param fileName
     * @throws IOException
     */
    public static void create(String fileName, int type) throws IOException {
        System.out.println("Enter Hadoop create");
        WebHdfsFileSystem fs = getFileSystemInstance();
        Path path = new Path(fileName);
        if (!fs.exists(path)) {
            if (type == FOLDER) {
                fs.mkdirs(path);
            } else {
                fs.create(path);
            }
        }
    }
//    public static void create(String fileName, int type)  {
//        try {
//
//            FileSystem fs = FileSystem.get(conf);
//            Path path = new Path(fileName);
//            if (!fs.exists(path)) {
//                if (type == FOLDER) {
//                    fs.mkdirs(path);
//                } else {
//                    fs.create(path);
//                }
//            }
//            fs.close();
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//    }

    /**
     * 删除文件/文件夹
     *
     * @param fileName
     * @throws IOException
     */
    public static void delete(String fileName) throws IOException {
        FileSystem fs = getFileSystemInstance();
        Path path = new Path(fileName);
        if (fs.exists(path)) {
            fs.delete(path, true);
        }
    }

    /**
     * 重命名文件
     *
     * @param file
     * @param dstName
     * @throws IOException
     */
    public static void rename(PhysicalFile file, String dstName) throws IOException {
        FileSystem fs = getFileSystemInstance();
        String origin = file.getHadoopPath()+"/"+file.getPhysicalFileName();
        String destination = file.getHadoopPath() + "/" + dstName;
        Path srcPath = new Path(origin);
        Path dstPath = new Path(destination);
        if (fs.exists(srcPath)) {
            fs.rename(srcPath, dstPath);
        }
    }

    /**
     * 移动文件
     *
     * @param srcFile
     * @param dstFile
     * @throws IOException
     */
    public static void move(String srcFile, String dstFile) throws IOException {
        FileSystem fs = getFileSystemInstance();
        Path srcPath = new Path(srcFile);
        Path dstPath = new Path(dstFile);
        // 目标文件的文件夹
        String dstFolder = dstFile.substring(0, dstFile.lastIndexOf("/"));
        Path dstFolderPath = new Path(dstFolder);

        if (fs.exists(srcPath)) {
            // 如果目标文件的文件夹不存在，先创建文件夹
            if (!fs.exists(dstFolderPath)) {
                fs.mkdirs(dstFolderPath);
            }
            fs.rename(srcPath, dstPath);
        }
        fs.close();
    }

    /**
     * 写文件--字符串
     *
     * @param fileName
     * @param
     * @throws IOException
     */
    public static void writeFile(String fileName, String content) throws IOException {
        FileSystem fs = getFileSystemInstance();
        Path path = new Path(fileName);
        FSDataOutputStream out = fs.create(path);
        out.writeUTF(content);
        out.close();
        fs.close();
    }

    /**
     * 写文件--输入流
     *
     * @param fileName
     * @param in
     * @throws IOException
     */
    public static void write(String fileName, InputStream in) throws IOException {
        FileSystem fs = getFileSystemInstance();
        Path path = new Path(fileName);
        OutputStream out = fs.create(path);
        IOUtils.copyBytes(in, out, 4096, true);

        IOUtils.closeStream(in);
        IOUtils.closeStream(out);
        fs.close();
    }

    /**
     * 写文件（压缩）--输入流
     *
     * @param fileName
     * @param in
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static void writeZip(String fileName, InputStream in) throws IOException, ClassNotFoundException {
        FileSystem fs = getFileSystemInstance();
        Class<?> codecClass = Class.forName("org.apache.hadoop.io.compress.GzipCodec");
        CompressionCodec codec = (CompressionCodec) ReflectionUtils.newInstance(codecClass, conf);
        FSDataOutputStream outputStream = fs.create(new Path(fileName + ".zip"));
        CompressionOutputStream out = codec.createOutputStream(outputStream);
        IOUtils.copyBytes(in, out, 4096, true);

        IOUtils.closeStream(in);
        IOUtils.closeStream(out);
        fs.close();
    }

    /**
     * 写文件（追加）--文件到文件
     *
     * @param srcFile
     * @param targetFile
     * @throws IOException
     */
    public static void writeAppend(String srcFile, String targetFile) throws IOException {
        conf.setBoolean("dfs.support.append", true);
        FileSystem fs = getFileSystemInstance();
        Path path = new Path(targetFile);
        InputStream in = new BufferedInputStream(new FileInputStream(srcFile));
        OutputStream out = fs.append(path);
        IOUtils.copyBytes(in, out, 4096, true);

        out.flush();
        in.close();
        out.close();
        fs.close();
    }

    /**
     * 写文件（追加）--输入流到文件
     *
     * @param fileName
     * @param in
     * @throws IOException
     */
    public static void writeAppend(String fileName, InputStream in) throws IOException {
        conf.setBoolean("dfs.support.append", true);
        FileSystem fs = getFileSystemInstance();
        Path path = new Path(fileName);
        OutputStream out = fs.append(path);
        IOUtils.copyBytes(in, out, 4096, true);

        out.flush();
        in.close();
        out.close();
        fs.close();
    }

    /**
     * 读文件
     *
     * @param fileName
     * @throws IOException
     */
    public static String readFile(String fileName) throws IOException {
        FileSystem fs = getFileSystemInstance();
        Path path = new Path(fileName);
        if (!fs.exists(path)) {
            fs.close();
            return null;
        }

        FSDataInputStream is = null;
        OutputStream os = new ByteArrayOutputStream();
        String str = null;
        try {
            is = fs.open(path);
            IOUtils.copyBytes(is, os, 4096, false);
            str = os.toString();
        } finally {
            IOUtils.closeStream(is);
            fs.close();
        }
        return str;
    }

    /**
     * 读文件--输出流
     *
     * @param fileName
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static OutputStream readFileToStream(String fileName) throws IOException, ClassNotFoundException {
        FileSystem fs = getFileSystemInstance();
        Path path = new Path(fileName);
        if (!fs.exists(path)) {
            fs.close();
            return null;
        }

        FSDataInputStream is = null;
        OutputStream os = new ByteArrayOutputStream();
        try {
            is = fs.open(path);
            if(fileName.endsWith(".zip")){
                Class<?> codecClass = Class.forName("org.apache.hadoop.io.compress.GzipCodec");
                CompressionCodec codec = (CompressionCodec) ReflectionUtils.newInstance(codecClass, conf);
                InputStream in = codec.createInputStream(is);
                IOUtils.copyBytes(in, os, 4096, false);
            }else{
                IOUtils.copyBytes(is, os, 4096, false);
            }
        } finally {
            IOUtils.closeStream(is);
            fs.close();
        }
        return os;
    }

    /**
     * 上传文件
     *
     * @param dstFileName
     * @throws IOException
     */
    public static void uploadFile(String srcFileName, String dstFileName) throws IOException {

        FileSystem fs = getFileSystemInstance();

        Path srcPath = new Path(srcFileName);
        Path dstPath = new Path(dstFileName);

        fs.copyFromLocalFile(srcPath, dstPath);
        fs.close();
    }

    /**
     * 打包为zip
     * @param path	hadoop文件路径
     * @param zos	zip输出流
     * @param root	打包为zip后的跟路径
     * @throws IOException
     */
    public static void doZip(Path path, ZipOutputStream zos, String root)
            throws IOException {
        FileSystem fs=getFileSystemInstance();
        //判断文件是否存在
        if(fs.exists(path)) {
            //判断文件是否为一个标准文件
            if (fs.isFile(path)) {
                //如果文件是一个标准文件,新建一个zip实体类并将它放入zip输出六中
                root =root +path.getName();
                zos.putNextEntry(new ZipEntry(root));
                //创建读取hadoop的输入流
                FSDataInputStream inputStream = null;
                //打开输入流
                inputStream=fs.open(path);
                byte[] buffer = new byte[1024];
                int r = 0;
                //如果读取到的数据不为空,输出---
                while ((r = inputStream.read(buffer)) != -1) {
                    zos.write(buffer, 0, r);
                }
                //刷新输出流
                zos.flush();
                //关闭输入流
                inputStream.close();
            } else {
                //如果为文件夹,递归查询
                root =root+path.getName()+'/';
                zos.putNextEntry(new ZipEntry(root));
                FileStatus contents[] = fs.listStatus(path); ;
                for (FileStatus f : contents) {
                    doZip(f.getPath(), zos,root);
                }
            }
        }
    }

    /**
     * 下载 文件
     *
     * @param remote
     * @param local
     * @throws IOException
     */
    public static void downloadToLocal(String remote, String local) throws IOException {
        Path path = new Path(remote);
        FileSystem fs = getFileSystemInstance();
        fs.copyToLocalFile(path, new Path(local));
        fs.close();
    }

    /**
     * 获取文件/文件夹 下的文件
     *
     * @param fileName
     * @throws IOException
     */
    public static List<PhysicalFile> getFiles(String fileName) throws IOException {
        return null;
    }

    /**
     * 获取文件/文件夹 下的所有文件
     *
     * @param fileName
     * @throws IOException
     */
    public static List<PhysicalFile> getAllFiles(String fileName) throws IOException {
        return null;
    }

}
