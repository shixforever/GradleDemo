package com.vivo.warnsdkplugin

import org.objectweb.asm.*
import org.objectweb.asm.commons.AdviceAdapter

class AopClassVisitor(nextVisitor: ClassVisitor) : ClassVisitor(Opcodes.ASM5, nextVisitor) {
    override fun visit(
        version: Int,
        access: Int,
        name: String?,
        signature: String?,
        superName: String?,
        interfaces: Array<out String>?
    ) {
        super.visit(version, access, name, signature, superName, interfaces)
    }
    override fun visitMethod(
        access: Int,
        name: String?,
        descriptor: String?,
        signature: String?,
        exceptions: Array<out String>?
    ): MethodVisitor {
        val methodVisitor = super.visitMethod(access, name, descriptor, signature, exceptions)
        val newMethodVisitor =
            object : AdviceAdapter(Opcodes.ASM5, methodVisitor, access, name, descriptor) {

                @Override
                override fun onMethodEnter() {
                    // 插桩方法
                    if (isNeedVisitMethod(name)) {
                        // 设置对象参数
                        mv.visitVarInsn(Opcodes.ALOAD, 0)
                        mv.visitMethodInsn(Opcodes.INVOKESTATIC, "com/shixforever/gradlestudy/aop/AopUtil", "hookOkHttp", "(Ljava/lang/Object;)V", false)
                        println("===完成插桩===")
                    }
                    super.onMethodEnter();
                }
            }
        return newMethodVisitor
    }

    private fun isNeedVisitMethod(name: String?): Boolean {
        if (name != null && "build" == name) {
            return true
        }
        return false
    }

}