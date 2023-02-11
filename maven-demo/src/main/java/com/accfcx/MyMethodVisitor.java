//package com.accfcx;
//
//
//import org.objectweb.asm.MethodVisitor;
//import org.objectweb.asm.commons.AdviceAdapter;
//
///**
// * MethodVisitor
// * @author accfcx
// **/
//public class MyMethodVisitor extends AdviceAdapter {
//    /**
//     * Creates a new {@link AdviceAdapter}.
//     *
//     * @param api    the ASM API version implemented by this visitor. Must be one
//     *               of {@link Opcodes#ASM4} or {@link Opcodes#ASM5}.
//     * @param mv     the method visitor to which this adapter delegates calls.
//     * @param access the method's access flags (see {@link Opcodes}).
//     * @param name   the method's name.
//     * @param desc   the method's descriptor (see {@link Type Type}).
//     */
//    protected MyMethodVisitor(int api, MethodVisitor mv, int access, String name, String desc) {
//        super(api, mv, access, name, desc);
//    }
//
//
//    @Override
//    protected void onMethodEnter() {
//        mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
//
//        mv.visitLdcInsn("enter method: " + this.getClass().getName());
//
//        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
//
//
//
//        super.onMethodEnter();
//    }
//
//    @Override
//    protected void onMethodExit(int opcode) {
//        super.onMethodExit(opcode);
//
//        mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
//        mv.visitLdcInsn("exit method: " + this.getClass().getName());
//        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
//    }
//}
