package com.example.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <pre>
 *     author: Jafar
 *     date  : 2020/11/11
 *     desc  :
 *     代码的生命周期：源码期、编译期、运行期
 *     注解也是这样
 *     注解的声明周期决定了我们的代码的生命周期
 * </pre>
 */

@Retention(RetentionPolicy.CLASS)   // 声明代码的生命周期为类
@Target(ElementType.TYPE)
public @interface BindPath {
    String value();
}
