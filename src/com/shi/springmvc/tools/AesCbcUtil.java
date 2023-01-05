package com.shi.springmvc.tools;



import org.apache.tomcat.util.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.InvalidParameterSpecException;
public class AesCbcUtil{
	static{
		 // BouncyCastle��һ����Դ�ļӽ��ܽ����������ҳ��http://www.bouncycastle.org/
        // ��Ҫ��pom����汾
        /*
         * <dependency> <groupId>org.bouncycastle</groupId>
         * <artifactId>bcprov-jdk16</artifactId> <version>1.46</version>
         * </dependency>
         * 
         */
		Security.addProvider(new BouncyCastleProvider());
	}
	/**
     * AES����
     *
     * @param data
     *            //���ģ������ܵ�����
     * @param key
     *            //��Կ
     * @param iv
     *            //ƫ����
     * @param encodingFormat
     *            //���ܺ�Ľ����Ҫ���еı���
     * @return
     * @throws Exception
     */
	public static String decrypt(String data, String key, String iv, String encodingFormat) throws Exception {
        // initialize();

        // �����ܵ�����
        byte[] dataByte = Base64.decodeBase64(data);
        // ������Կ
        byte[] keyByte = Base64.decodeBase64(key);
        // ƫ����
        byte[] ivByte = Base64.decodeBase64(iv);

        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");

            SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");

            AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
            parameters.init(new IvParameterSpec(ivByte));

            cipher.init(Cipher.DECRYPT_MODE, spec, parameters);// ��ʼ��

            byte[] resultByte = cipher.doFinal(dataByte);
            if (null != resultByte && resultByte.length > 0) {
                String result = new String(resultByte, encodingFormat);
                return result;
            }
            return null;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidParameterSpecException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }
}