package com.example.annotation_compiler;

import com.example.annotation.BindPath;
import com.google.auto.service.AutoService;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;

/**
 * <pre>
 *     author: Jafar
 *     date  : 2020/11/11
 *     desc  :
 * </pre>
 */

@AutoService(Process.class) // 固定写法 注册、标记这个类是个注解处理器
public class AnnotationCompiler extends AbstractProcessor {

    Filer filer;
    Messager messager;

    /**
     * 环境
     * @param processingEnvironment
     */
    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        filer = processingEnvironment.getFiler();
        messager = processingEnvironment.getMessager();
    }

    /**
     * 声明这个注解处理器支持的Java版本
     * @return
     */
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return processingEnv.getSourceVersion();
    }

    /**
     * 声明当前注解处理器要处理的注解
     * 我只需要去找我们的BindPath即可
     * 所以我们要依赖我们的BindPath
     * @return
     */
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types = new HashSet<>();
        types.add(BindPath.class.getCanonicalName());
        return types;
    }

    /**
     * 自动生成我们的工具类模块
     * @param set
     * @param roundEnvironment
     * @return
     */
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {

        // 获取标记到的类
        Set<? extends Element> elementsAnnotatedWith = roundEnvironment.getElementsAnnotatedWith(BindPath.class);
        // 类节点（TypeElement）、方法节点(ExecutableElement)、
        // 成员变量节点(VariableElement)、包节点(PackageElement)
        // 获取到每个注解标记的key value 装载进去
        Map<String, String> map = new HashMap<>();
        for (Element element : elementsAnnotatedWith) {
            TypeElement typeElement = (TypeElement) element;
            // 获取包名+类名
            String activityClazz = typeElement.getQualifiedName().toString();
            String key = typeElement.getAnnotation(BindPath.class).value();
            map.put(key, activityClazz + ".class");
        }

        // 去生成文件
        if (map.size() > 0) {
            // JavaPoe
            Writer writer = null;
            String utilName = "ActivityUtil" + System.currentTimeMillis();
            // 需要生成一个Java文件
            try {
                JavaFileObject sourceFile = filer.createSourceFile("com.example." + utilName);
                writer = sourceFile.openWriter();
                // 接下来使用StringBuffer来一行一行写代码
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("package com.example.arouter;\n");
                stringBuffer.append("import com.example.arouter.IRouter;\n");
                stringBuffer.append("import com.example.arouter.Router;\n");
                stringBuffer.append("public class " + utilName + " implement IRouter {\n");
                stringBuffer.append("@Override\n");
                stringBuffer.append("public void putActivity() {\n");
                Iterator<String> iterator = map.keySet().iterator();
                while (iterator.hasNext()) {
                    String key = iterator.next();
                    String activityName = map.get(key);
                    stringBuffer.append("ARouter.getInstance().addActivity(\"" + key + "\"," + activityName + ");");
                }
                stringBuffer.append("}\n}");
                writer.write(stringBuffer.toString());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (writer != null) {
                    try {
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return false;
    }
}
