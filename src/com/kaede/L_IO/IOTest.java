package com.kaede.L_IO;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Date;

/**
 * @author kaede
 * @create 2022-10-12
 */

public class IOTest {

    @Test
    public void test() {
        //D:\Workspace\IDEA\Java
        System.out.println(System.getProperty("user.dir"));
        File file = new File("hello.txt");
        System.out.println("file = " + file);
        File file1 = new File("./hello.txt");
        System.out.println("file1 = " + file1);
        File file2 = new File("D:\\Workspace\\IDEA\\Java\\src\\com\\kaede\\L_IO\\hello.txt");
        System.out.println("file2 = " + file2);
        File file3 = new File("D:/Workspace/IDEA/Java/src/com/kaede/L_IO/hello.txt");
        System.out.println("file3 = " + file3);

        File file4 = new File("D:\\Workspace\\IDEA", "Java");
        System.out.println("file4 = " + file4);
        File file5 = new File(file4, "hello.txt");
        System.out.println("file5 = " + file5);
    }

    @Test
    public void test1() {
        File file = new File("hello.txt");
        File file1 = new File("D:\\Workspace\\IDEA\\Java\\hello.txt");
        System.out.println("file.getAbsolutePath() = " + file.getAbsolutePath());
        System.out.println("file1.getAbsolutePath() = " + file1.getAbsolutePath());
        System.out.println("file.getPath() = " + file.getPath());
        System.out.println("file1.getPath() = " + file1.getPath());
        System.out.println("file.getName() = " + file.getName());
        System.out.println("file1.getName() = " + file1.getName());
        System.out.println("file.getParent() = " + file.getParent());
        System.out.println("file1.getParent() = " + file1.getParent());
        System.out.println("file.length() = " + file.length());
        System.out.println("file1.length() = " + file1.length());
        System.out.println("file.lastModified() = " + file.lastModified());
        System.out.println("file1.lastModified() = " + file1.lastModified());
        System.out.println("new Date(file.lastModified()) = " + new Date(file.lastModified()));
        System.out.println("new Date(file1.lastModified()) = " + new Date(file1.lastModified()));
    }

    @Test
    public void test2() {
        File file = new File("D:\\Workspace\\IDEA\\Java");
        String[] list = file.list();
        for (String s : list) {
            System.out.println(s);
        }
        System.out.println("---------------------");
        File[] files = file.listFiles();
        for (File file1 : files) {
            System.out.println(file1);
        }
    }

    @Test
    public void test3() {
        File file = new File("hello.txt");
        File file1 = new File("D:\\Workspace\\IDEA\\Java\\src\\hello.txt");
        boolean renameTo = file.renameTo(file1);
        System.out.println("renameTo = " + renameTo);

        /*File file2 = new File("a");
        File file3 = new File("src/com/kaede/L_IO/a");
        boolean renameTo1 = file2.renameTo(file3);
        System.out.println("renameTo1 = " + renameTo1);*/
    }

    @Test
    public void test4() {
        File file = new File("hello.txt");
        System.out.println("file.isDirectory() = " + file.isDirectory());
        System.out.println("file.isFile() = " + file.isFile());
        System.out.println("file.exists() = " + file.exists());
        System.out.println("file.canRead() = " + file.canRead());
        System.out.println("file.canWrite() = " + file.canWrite());
        System.out.println("file.isHidden() = " + file.isHidden());
    }

    @Test
    public void test5() throws IOException {
        File file = new File("a.txt");
        System.out.println("file.createNewFile() = " + file.createNewFile());
        File file1 = new File("c\\d");
        System.out.println("file1.mkdir() = " + file1.mkdir());
        File file2 = new File("c");
        System.out.println("file2.mkdir() = " + file2.mkdir());
        //注意：只会创建目录
        File file3 = new File("e\\f.txt");
        System.out.println("file3.mkdirs() = " + file3.mkdirs());
    }

    @Test
    public void test6() {
        File file = new File("a.txt");
        System.out.println("file.delete() = " + file.delete());
        File file1 = new File("c");
        System.out.println("file1.delete() = " + file1.delete());
        File file2 = new File("e");
        System.out.println("file2.delete() = " + file2.delete());
    }

    @Test
    public void test7() {
        FileReader fr = null;
        try {
            fr = new FileReader(new File("hello.txt"));
            char[] chars = new char[1024];
            int len;
            while((len = fr.read(chars)) != -1) {
                System.out.println(String.valueOf(chars, 0, len));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void test8() {
        FileWriter fw = null;
        try {
            fw = new FileWriter(new File("hello.txt"));
            fw.write("kaede");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void test9() {
        FileReader fr = null;
        FileWriter fw = null;
        try {
            //fr = new FileReader(new File("hello.txt"));
            //fw = new FileWriter(new File("hello1.txt"));
            fr = new FileReader(new File("kq.jpg"));
            fw = new FileWriter(new File("kq1.jpg"));
            char[] chars = new char[1024];
            int len;
            while((len = fr.read(chars)) != -1) {
                fw.write(chars,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void test10() {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            //fis = new FileInputStream(new File("kq.jpg"));
            //fos = new FileOutputStream(new File("kq2.jpg"));
            fis = new FileInputStream(new File("hello.txt"));
            fos = new FileOutputStream(new File("hello2.txt"));
            byte[] bytes = new byte[1024];
            int len;
            while((len = fis.read(bytes)) != -1) {
                fos.write(bytes,0,len);
                System.out.println(new String(bytes,0,len));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void copyFile(String srcPath, String destPath) {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(new File(srcPath));
            fos = new FileOutputStream(new File(destPath));
            byte[] bytes = new byte[1024];
            int len;
            while((len = fis.read(bytes)) != -1) {
                fos.write(bytes,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
