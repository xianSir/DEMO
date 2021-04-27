package com.xks;

/**
 * @author xks
 * @date 2019-08-06
 * CRC校验算法
 * 地址:http://docs.sennor.net/consolePanel/insideFile?val=agreement#0_0_7
 */
public class sykean_jingxun {
    public static void main(String[] args) {
        byte[] bytes = "FEDC0216356184523200000005C3337251010009C0010008000002920000FF9B".getBytes();
        int i = sykean_jingxun.CRC16_MODBUS(bytes);
        System.out.println(Integer.toHexString(i));
    }

    /**
     * CRC16 校验寄存器赋值为 0xFFFF；
     * <p>
     * 2) 取被校验串的第一个字节赋值给临时寄存器；
     * <p>
     * 3) 临时寄存器与 CRC16 校验寄存器的高位字节进行“异或”运算，赋值给 CRC16 校验寄存器；
     * <p>
     * 4) 取 CRC16 校验寄存器最后一位赋值给检测寄存器；
     * <p>
     * 5) 把 CRC16 校验寄存器右移一位；
     * <p>
     * 6) 若检测寄存器值为 1，CRC16 校验寄存器与多项式 0xA001 进行“异或”运算，赋值给 CRC16校验寄存器；
     * <p>
     * 7) 重复步骤 4~6，直至移出 8 位；
     * <p>
     * 8) 取被校验串的下一个字节赋值给临时寄存器；
     * <p>
     * 9) 重复步骤 3~8，直至被校验串的所有字节均被校验；
     * <p>
     * 10) 返回 CRC16 校验寄存器的值。
     */
    public static int CRC16_MODBUS(byte[] buffer) {
        int i;
        int crc_reg;
        int check;
        crc_reg = 0xFFFF;
        for (i = 0; i < buffer.length; i++) {
            crc_reg = (crc_reg >> 8) ^ buffer[i];
            for (int j = 0; j < 8; j++) {
                check = crc_reg & 0x0001;
                crc_reg >>= 1;
                if(check == 0x0001) {
                    crc_reg ^= 0xA001;
                }
            }
        }
        return crc_reg;
    }
}
