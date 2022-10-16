package com.kaede.L_Reflection;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;

/**
 * @author kaede
 * @create 2022-10-15
 */

public class ProxyTest {

    @Test
    public void testStatic() {
        ProxyClothFactory factory = new ProxyClothFactory(new NikeClothFactory());
        factory.produceCloth("skirt");
    }

    @Test
    public void testDynamic() {
        ClothFactory factory = (ClothFactory) DynamicProxyClothFactory.getProxyInstance(new NikeClothFactory());
        factory.produceCloth("shirt");
    }

}

interface ClothFactory {
    void produceCloth(String cloth);
}

//被代理类
class NikeClothFactory implements ClothFactory {
    @Override
    public void produceCloth(String cloth) {
        System.out.println("nike factory produce some " + cloth);
    }
}

//静态代理类
class ProxyClothFactory implements ClothFactory {
    private ClothFactory factory;

    public ProxyClothFactory(ClothFactory factory) {
        this.factory = factory;
    }

    @Override
    public void produceCloth(String cloth) {
        System.out.println("before method...");
        factory.produceCloth(cloth);
        System.out.println("after method...");
    }
}

//动态代理类
class DynamicProxyClothFactory {
    //需要使用被代理类的对象进行赋值
    private Object obj;

    public void bind(Object obj) {
        this.obj = obj;
    }

    //obj为被代理类对象，调用此方法返回一个代理类对象
    public static Object getProxyInstance(Object obj) {
        //类加载器 实现的接口
        Object instance = Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(),
            //通过代理类对象调用被代理类的方法时会调用该invoke()方法
            (proxy, method, args) -> {
                System.out.println("before method...");
                Object returnValue = method.invoke(obj, args);
                System.out.println("after method...");
                return returnValue;
            }
        );
        return instance;
    }
}

