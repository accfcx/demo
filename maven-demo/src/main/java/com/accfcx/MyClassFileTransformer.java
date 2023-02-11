//package com.accfcx;
//
//
//import org.objectweb.asm.ClassReader;
//import org.objectweb.asm.ClassVisitor;
//import org.objectweb.asm.ClassWriter;
//
//import java.lang.instrument.ClassFileTransformer;
//import java.lang.instrument.IllegalClassFormatException;
//import java.security.ProtectionDomain;
//
///**
// * @author accfcx
// **/
//public class MyClassFileTransformer  implements ClassFileTransformer {
//    @Override
//    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
//        if (!className.equals("MyTest")) {
//            return classfileBuffer;
//        }
//
//        ClassReader cr = new ClassReader(classfileBuffer);
//
//        ClassWriter cw = new ClassWriter(cr, ClassWriter.COMPUTE_FRAMES);
//
//        ClassVisitor cv = new MyClassVisitor(9 << 16, cw);
//
//        cr.accept(cv, ClassReader.SKIP_DEBUG | ClassReader.SKIP_FRAMES);
//
//        return cw.toByteArray();
//    }
//}
