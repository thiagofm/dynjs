package org.dynjs.runtime.java;

import org.dynjs.runtime.DynObject;
import org.dynjs.runtime.ExecutionContext;
import org.dynjs.runtime.GlobalObject;
import org.dynjs.runtime.Types;

public class JavaPackage extends DynObject {

    private String path;

    public JavaPackage(GlobalObject globalObject, String path) {
        super(globalObject);
        this.path = path;
    }

    @Override
    public Object get(ExecutionContext context, String name) {
        Object result = super.get(context, name);
        if (result == Types.UNDEFINED) {

            String fullPath = this.path + "." + name;
            ClassLoader cl = context.getConfig().getClassLoader();
            try {
                Class<?> cls = cl.loadClass(fullPath);
                return cls;
            } catch (ClassNotFoundException e) {
                result = new JavaPackage(context.getGlobalObject(), this.path + "." + name);
            }
        }
        return result;
    }
    
    public String toString() {
        return "[JavaPackage: " + this.path + "]";
    }

}
