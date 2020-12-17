package ioc.java.beans;

import org.springframework.beans.propertyeditors.PropertiesEditor;
import org.springframework.lang.Nullable;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import java.util.stream.Stream;

public class BeanInfoDemo {

    public static void main(String[] args) throws IntrospectionException {

        //Bean信息，Object.class是忽略分析的Class
        BeanInfo beanInfo = Introspector.getBeanInfo(Person.class,Object.class);

        //输出属性描述信息
        Stream.of(beanInfo.getPropertyDescriptors())
                .forEach(
                        propertyDescriptor -> {
                            System.out.println(propertyDescriptor);
                        }
                );

        Stream.of(beanInfo.getPropertyDescriptors())
                .forEach(
                        propertyDescriptor -> {
                            String name = propertyDescriptor.getName();
                            if("age".equals(name)){
                                propertyDescriptor.setPropertyEditorClass(StringToIntegerPropertyEditor.class);
                            }
                        }
                );

    }

    static class StringToIntegerPropertyEditor extends PropertiesEditor{
        @Override
        public void setAsText(@Nullable String text) throws IllegalArgumentException {
            this.setValue(Integer.valueOf(text));
        }
    }
}
