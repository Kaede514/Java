package com.kaede.L_IO;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @author kaede
 * @create 2022-10-13
 */

public class OtherStreamTest {

    //从键盘输入，从控制台输出
    //方式一：使用Scanner实现
    //方式二：使用System.in实现
    //IDEA单元测试中不允许输入，所有使用main()测试
    public static void main(String[] args){
        try(InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr)) {
            String data;
            while(true) {
                System.out.print("请输入字符串：");
                data = br.readLine();
                if(data.equalsIgnoreCase("q")) {
                    System.out.println("over...");
                    break;
                }
                System.out.println(data.toUpperCase());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test1() {
        try(FileOutputStream fos = new FileOutputStream("test.txt");
            //创建打印输出流，设置为自动刷新模式（写入换行符或字节'\n'时都会刷新输出缓冲区）
            PrintStream ps = new PrintStream(fos, true)) {
            if (ps != null) {
                //将标准输出流（控制台输出）改为文件
                System.setOut(ps);
            }
            for (int i = 0; i < 255; i++) {
                System.out.print((char) i);
                if(i % 50 == 0) {
                    System.out.println();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() {
        try(DataOutputStream dos = new DataOutputStream(new FileOutputStream("data.txt"));
            DataInputStream dis = new DataInputStream(new FileInputStream("data.txt"))) {
            dos.writeUTF("kaede中文");
            dos.writeInt(12);
            dos.writeBoolean(true);
            dos.flush();
            System.out.println("dis.readUTF() = " + dis.readUTF());
            System.out.println("dis.readInt() = " + dis.readInt());
            System.out.println("dis.readBoolean() = " + dis.readBoolean());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test3() {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("obj.txt"));
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("obj.txt"))) {
            A a = new A("luna", 18);
            A b = new A("sola", 20);
            oos.writeObject(a);
            oos.writeObject(b);
            oos.flush();
            A a1 = (A) ois.readObject();
            A b1 = (A) ois.readObject();
            System.out.println("a1 = " + a1);
            System.out.println("b1 = " + b1);
            System.out.println("(a == a1) = " + (a == a1));
            System.out.println("(b == b1) = " + (b == b1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test4() {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("obj.txt"))) {
            A a1 = (A) ois.readObject();
            A b1 = (A) ois.readObject();
            System.out.println("a1 = " + a1);
            System.out.println("b1 = " + b1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test5() {
        try(RandomAccessFile raf = new RandomAccessFile("kq.jpg", "r");
            RandomAccessFile raf1 = new RandomAccessFile("kq3.jpg", "rw")) {
            byte[] bytes = new byte[1024];
            int len;
            while((len = raf.read(bytes)) != -1) {
                raf1.write(bytes, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test6() {
        try(RandomAccessFile raf = new RandomAccessFile("hi.txt", "rw")) {
            raf.write("xyz".getBytes());
            raf.write("jkl".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test7() {
        try(RandomAccessFile raf = new RandomAccessFile("hi.txt", "rw")) {
            raf.seek(3);
            raf.write("xyz".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //实现数据的插入（实际上是覆盖）
    @Test
    public void test8() {
        try(RandomAccessFile raf = new RandomAccessFile("hi.txt", "rw")) {
            //插入数据的位置
            raf.seek(3);
            //保存当前指针后的所有数据到StringBuilder中
            byte[] bytes = new byte[1024];
            int len;
            StringBuilder builder = new StringBuilder((int) new File("hi.txt").length());
            while((len = raf.read(bytes)) != -1) {
                builder.append(new String(bytes, 0, len));
            }
            //保存完之后指针会到末尾的位置，将指针再次调回插入数据的位置
            raf.seek(3);
            raf.write("abc".getBytes());
            //将StringBuilder中的数据写入到文件中
            raf.write(builder.toString().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test9() {
        try(RandomAccessFile raf = new RandomAccessFile("hi.txt", "rw");
            ByteArrayOutputStream bos = new ByteArrayOutputStream((int) new File("hi.txt").length())) {
            raf.seek(3);
            byte[] bytes = new byte[1024];
            int len;
            while((len = raf.read(bytes)) != -1) {
                bos.write(bytes, 0, len);
            }
            raf.seek(3);
            raf.write("abc".getBytes());
            raf.write(bos.toString().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

class A implements Serializable {
    private static final long serialVersionUID = 427463635432L;
    private String name;
    private Integer age;
    private Integer id;

    public A(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "A{" +
            "name='" + name + '\'' +
            ", age=" + age +
            ", id=" + id +
            '}';
    }
}
