package com.mohammad.cryptography.RSA;

import java.math.BigInteger;

public class RSA_CORE {

	private RSA_Key key;
	private BigInteger N;

	
	public static void main(String[] args){
		RSA_CORE RSA = new RSA_CORE();//new RSA_CORE(BigInteger.valueOf(7),BigInteger.valueOf(23),BigInteger.valueOf(160),BigInteger.valueOf(17), BigInteger.valueOf(11));
		RSA.RSAKeyGeneration(BigInteger.valueOf(7)/*e*/,/*p*/ BigInteger.valueOf(17),/*q*/ BigInteger.valueOf(11));
		
		String c = RSA.encrypt((char)88);
		System.out.println("\nC = "+c+"\nM = "+RSA.decrypt(c));
		
		String ss = RSA.encryptText("Cryptography");
		String Mss = RSA.decryptText(ss);
		System.out.println("Encrypted MESSAGE = "+ss+"\nDecrypted Message = "+Mss);
	}
	
	public RSA_Key getKey() {
		return this.key;
	}

	public void setKey(RSA_Key key) {
		this.key = key;
		this.N = key.getP().multiply(key.getQ());
	}
	public BigInteger getN(){
		return this.N;
	}
	public RSA_CORE(int KeyBitLength){
		this.key = new RSA_Key(KeyBitLength);
		this.N = key.getP().multiply(key.getQ());
	}
	public RSA_CORE(BigInteger publicKEY_e, BigInteger privateKEY_d, BigInteger phy_N, BigInteger p, BigInteger q){
		this.key = new RSA_Key(publicKEY_e, privateKEY_d, phy_N, p, q);
		this.N = key.getP().multiply(key.getQ());
	}
	public RSA_CORE(BigInteger publicKEY_e, BigInteger privateKEY_d, BigInteger phy_N) {
		this.key = new RSA_Key(publicKEY_e, privateKEY_d, phy_N);
		this.N = key.getP().multiply(key.getQ());
	}
	public RSA_CORE() {
	}
	
	public void RSAKeyGeneration(BigInteger publicKEY_e, BigInteger p, BigInteger q) {
		this.key = new RSA_Key();
		this.key.GeneratKeyUsing(publicKEY_e, p, q);
		this.N = key.getP().multiply(key.getQ());
	}
	public RSA_CORE(BigInteger p, BigInteger q){
		this.key = new RSA_Key(p, q);
		this.N = key.getP().multiply(key.getQ());
	}
	
	public String encrypt(char c){
		int ascii = (int)c;
		BigInteger result = BigInteger.valueOf(ascii).modPow(key.getPublicKEY_e(), this.N);
		String res_str = result.toString();
		while(res_str.length() < this.N.toString().length())
			res_str = "0"+res_str;
		return res_str;
	}
	public String encrypt(int c){
		int ascii = c;
		BigInteger result = BigInteger.valueOf(ascii).modPow(key.getPublicKEY_e(), this.N);
		String res_str = result.toString();
		while(res_str.length() < this.N.toString().length())
			res_str = "0"+res_str;
		return res_str;
	}
	public String decrypt(String s){
		BigInteger c = new BigInteger(s);
		BigInteger result = c.modPow(key.getPrivateKEY_d(), this.N);
		String res_str = result.toString();
		while(res_str.length() < this.N.toString().length())
			res_str = "0"+res_str;
		return res_str;
	}
	
	public String encryptText(String txt){
		String out = "";
		char[] allChars = txt.toCharArray();
		for(char c : allChars){
			out += encrypt(c);
		}
		
		return out;
	}
	public String decryptText(String cypher){
		String out = "";
		String rem = cypher;
		
		int len = this.N.toString().length();
		
		
		while(rem.length()>0){
			String next = rem.substring(0,len);
			rem = rem.substring(len);
			String M = decrypt(next);
			out += (char)Integer.parseInt(M);
		}
		
		return out;
		
	}
}
