package org.zabalburu.usuarios.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Encriptar {
	public Encriptar() {

	}

	public static String getSalto(final int length) {
		byte[] salt = new byte[length];
		new SecureRandom().nextBytes(salt);
		return Base64.getEncoder().encodeToString(salt);
	}

	private static final int REPETICIONES = 65536;
	private static final int LONGITUD_CLAVE = 512;
	private static final String ALGORITMO = "PBKDF2WithHmacSHA512";

	public static String getHash(String password, String salt) {

		char[] chars = password.toCharArray();
		byte[] bytes = salt.getBytes();

		PBEKeySpec spec = new PBEKeySpec(chars, bytes, REPETICIONES, LONGITUD_CLAVE);

		Arrays.fill(chars, Character.MIN_VALUE);

		try {
			SecretKeyFactory fac = SecretKeyFactory.getInstance(ALGORITMO);
			byte[] securePassword = fac.generateSecret(spec).getEncoded();
			return Base64.getEncoder().encodeToString(securePassword);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
			System.err.println("Error en getHash()");
			return "";
		} finally {
			spec.clearPassword();
		}
	}

	public static void main(String[] args) {
		String password = "123";
		String salto = Encriptar.getSalto(10);
		System.out.println(salto);
		System.out.println(Encriptar.getHash(password, salto));
	}

}
