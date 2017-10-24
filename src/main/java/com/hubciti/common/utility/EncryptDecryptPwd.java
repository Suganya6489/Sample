package com.hubciti.common.utility;

/**
 * Encrypt and decrypt
 */

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import org.apache.commons.codec.binary.Base64;

/**
 * reusable code for Encrypt/Decrypt functionality .
 * 
 * @author Span Infotech
 */
public class EncryptDecryptPwd {
	// Create an 8-byte initialization vector
	/*
	 * private static byte[] iv = new byte[] { (byte) 0x8E, 0x12, 0x39, (byte)
	 * 0x9C, 0x07, 0x72, 0x6F, 0x5A };
	 */

	// 8-byte Salt
	/**
	 * salt declared as array of bytes.
	 */
	private static byte[] salt = { (byte) 0x39, (byte) 0x9B, (byte) 0xC8, (byte) 0x32, (byte) 0x56, (byte) 0x35, (byte) 0xF3, (byte) 0x03 };

	// This should be your key based on which you would encryp or decrypt or the
	// other way is to use
	// the user name as key if used for login
	/**
	 * The passPhrase declared as String.
	 */
	private String passPhrase = "";

	/**
	 * The passPhraseStr declared as String.
	 */
	private String passPhraseStr = "beam9BeCR64E";

	/**
	 * The AlgorithmParameterSpec declared as String.
	 */
	private AlgorithmParameterSpec paramSpec;

	/**
	 * The hashAlgorithm declared as String.
	 */
	private String hashAlgorithm = "PBEWithMD5AndDES";

	/**
	 * Constructor .
	 * 
	 * @throws NoSuchAlgorithmException .
	 * @throws NoSuchPaddingException .
	 */
	public EncryptDecryptPwd() throws NoSuchAlgorithmException, NoSuchPaddingException {
		paramSpec = new PBEParameterSpec(salt, 20);
	}

	/**
	 * gets encryption secret key.
	 * 
	 * @return secret key .
	 * @throws InvalidKeySpecException .
	 * @throws NoSuchAlgorithmException .
	 */
	private SecretKey getEncryptionSecretKey() throws InvalidKeySpecException, NoSuchAlgorithmException {
		final KeySpec keySpec = new PBEKeySpec(passPhrase.toCharArray(), salt, salt.length);
		return SecretKeyFactory.getInstance(hashAlgorithm).generateSecret(keySpec);
	}

	/**
	 * This method is for encryption.
	 * 
	 * @param s
	 *            As string parameter
	 * @return encrypted string
	 * @throws NoSuchAlgorithmException
	 *             - Exception related to algorithm
	 * @throws NoSuchPaddingException
	 *             - Exception related to Padding
	 * @throws InvalidKeyException
	 *             - Exception related to InvalidKey
	 * @throws InvalidAlgorithmParameterException
	 *             - Exception related to InvalidAlgorithmParameter
	 * @throws InvalidKeySpecException
	 *             - Exception related to InvalidKeySpec
	 * @throws IllegalBlockSizeException
	 *             - Exception related to IllegalBlockSize
	 * @throws BadPaddingException
	 *             - Exception related to BadPaddings
	 */
	public String encrypt(String s) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException,
			InvalidKeySpecException, IllegalBlockSizeException, BadPaddingException {
		// this.passPhrase = passPhrase;
		this.passPhrase = new String(Base64.encodeBase64(passPhraseStr.trim().getBytes()));
		final Cipher enCipher = Cipher.getInstance(hashAlgorithm);
		enCipher.init(Cipher.ENCRYPT_MODE, getEncryptionSecretKey(), paramSpec);
		final byte[] encrypted = enCipher.doFinal(s.getBytes());

		// String encrypted = new
		// String(Base64.encodeBase64(passPhrase.trim().getBytes()));
		return new String(Base64.encodeBase64(encrypted));
	}

	/**
	 * gets decrypted string.
	 * 
	 * @param s
	 *            As String parameter
	 * @return decrypted string .
	 * @throws NoSuchAlgorithmException .
	 * @throws NoSuchPaddingException .
	 * @throws InvalidKeyException .
	 * @throws InvalidAlgorithmParameterException .
	 * @throws InvalidKeySpecException .
	 * @throws IOException .
	 * @throws IllegalBlockSizeException .
	 * @throws BadPaddingException .
	 */
	public String decrypt(String s) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException,
			InvalidKeySpecException, IOException, IllegalBlockSizeException, BadPaddingException {
		// Convert from base64 back to bytes
		final byte[] encrypted = Base64.decodeBase64(s.getBytes());
		// this.passPhrase = passPhrase;
		this.passPhrase = new String(Base64.encodeBase64(passPhraseStr.trim().getBytes()));
		final Cipher deCipher = Cipher.getInstance(hashAlgorithm);
		deCipher.init(Cipher.DECRYPT_MODE, getEncryptionSecretKey(), paramSpec);
		final byte[] decrypted = deCipher.doFinal(encrypted);
		return new String(decrypted);
	}

	public static void main(String a[]) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, InvalidKeySpecException, IllegalBlockSizeException, BadPaddingException, IOException

	{
		EncryptDecryptPwd entry = new EncryptDecryptPwd();
		String encrypt = "M@n@g1ng@pps";
		String Decrypt = "ZJKooMNeDpKZqO7zBc0OYg==";

		//String encrypt = ":::We@Love!!ScanSee?{People}";
//		String Decrypt = ":::We@Love!!ScanSee?{People}";

		//Sangeetha TS    90a0cadd7ef464d82dacfa7dc95bcc18

		System.out.println("Decrypt : " + entry.decrypt(Decrypt));
		System.out.println("Encrypt : " + entry. encrypt(encrypt));
		
		/*
		 * EncryptDecryptPwd entry = null; try { entry = new
		 * EncryptDecryptPwd(); } catch (NoSuchAlgorithmException e1) {
		 * e1.printStackTrace(); } catch (NoSuchPaddingException e1) {
		 * e1.printStackTrace(); } String pwd = "12345678"; // String
		 * pwd1="12345678"; try { System.out.println("decrpted pwd is " +
		 * entry.decrypt(pwd)); System.out.println("encrypted pwd is " +
		 * entry.encrypt(pwd)); } catch (InvalidKeyException e) {
		 * e.printStackTrace(); } catch (NoSuchAlgorithmException e) {
		 * e.printStackTrace(); } catch (NoSuchPaddingException e) {
		 * e.printStackTrace(); } catch (InvalidAlgorithmParameterException e) {
		 * e.printStackTrace(); } catch (InvalidKeySpecException e) {
		 * e.printStackTrace(); } catch (IllegalBlockSizeException e) {
		 * e.printStackTrace(); } catch (BadPaddingException e) {
		 * e.printStackTrace(); } catch (IOException e) { e.printStackTrace(); }
		 */
	}
}
