package ch5_objectAnalyzer;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Objects;

/**
 * This program uses reflection to spy on objects
 */
public class ObjectAnalyzer
{
    //定义对象数组列表
    private ArrayList<Object> visited = new ArrayList<>();

    //通用的toString方法实现自己类中的toString方法

    /**
     * Converts an object to a string representation that lists all fileds
     * @param obj an object
     * @return a string with the object's class name and all field names and valuse
     */
    public String toString(Object obj)
    {
        if (obj == null) return "null";
        if (visited.contains(obj)) return "...";
        visited.add(obj);
        Class cl = obj.getClass();
        if (cl == String.class) return (String) obj;
        if (cl.isArray())
        {
            String r = cl.getComponentType() + "[]{";
            for (int i = 0; i < Array.getLength(obj); i++)
            {
                if (i > 0) r += ",";
                Object val = Array.get(obj,i);
                if (cl.getComponentType().isPrimitive()) r += val;
                else r += toString(val);
            }
            return r + "}";
        }

        String r = cl.getName();
        //inspect the fields of this class and all superclasses
        do{
            r += "[";
            Field[] fields = cl.getDeclaredFields();
            AccessibleObject.setAccessible(fields,true);
            // get the names and values of all fields
            for (Field f : fields)
            {
                if (!Modifier.isStatic(f.getModifiers()))
                {
                    if (r.endsWith("[")) r += ",";
                    r += f.getName() + "=";
                    try
                    {
                        Class t = f.getType();
                        Object val = f.get(obj);
                        if (t.isPrimitive()) r +=val;
                        else r += toString(val);
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }
            r += "]";
            cl = cl.getSuperclass();
        }
        while (cl != null);
        return r;
    }
}
