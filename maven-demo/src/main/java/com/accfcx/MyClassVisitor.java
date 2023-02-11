//package com.accfcx;
//
//import org.objectweb.asm.ClassVisitor;
//import org.objectweb.asm.MethodVisitor;
//
///**
// * @author accfcx
// **/
//public class MyClassVisitor extends ClassVisitor {
//
//
//    protected MyClassVisitor(int api, ClassVisitor classVisitor) {
//        super(api, classVisitor);
//    }
//
//    @Override
//    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
//
//        MethodVisitor mv = cv.visitMethod(access, name, descriptor, signature, exceptions);
//
//        if (name.equals("<init>")) {
//            return mv;
//        }
//           return  new MyMethodVisitor(api, mv, access, name, descriptor);
//    }
//}
