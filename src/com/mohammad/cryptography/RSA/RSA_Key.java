package com.mohammad.cryptography.RSA;

import java.math.BigInteger;
import java.util.Random;

public class RSA_Key {

	private BigInteger publicKEY_e;
	private BigInteger privateKEY_d;
	private BigInteger phy_N;
	private BigInteger p;
	private BigInteger q;
	
	public static void main(String[] args){
		RSA_Key key = new RSA_Key(10);
		System.out.println("Public key = "+key.getPublicKEY_e()+"\nPrivate Key = "+key.getPrivateKEY_d()+"\nPhy = "+key.getPhy_N()+"\nP = "+key.getP()+"\nQ = "+key.getQ());
	}
	
	
	public RSA_Key(BigInteger p, BigInteger q) {
		this.p = new BigInteger(p.toString());
		this.q = new BigInteger(q.toString());
		 GenerateKey();
	}
	
	
	public RSA_Key(BigInteger publicKEY_e, BigInteger privateKEY_d, BigInteger phy_N, BigInteger p, BigInteger q) {
		this.publicKEY_e = new BigInteger(publicKEY_e.toString());
		this.privateKEY_d = new BigInteger(privateKEY_d.toString());
		this.phy_N = new BigInteger(phy_N.toString());
		this.p = new BigInteger(p.toString());
		this.q = new BigInteger(q.toString());
	}


	public RSA_Key(BigInteger publicKEY_e, BigInteger privateKEY_d, BigInteger phy_N) {
		this.publicKEY_e =new BigInteger( publicKEY_e.toString());
		this.privateKEY_d =new BigInteger( privateKEY_d.toString());
		this.phy_N = new BigInteger(phy_N.toString());
	}

	public RSA_Key(int bitLength) {
		Random rand = new Random();
		p = BigInteger.probablePrime(bitLength, rand);
		q = BigInteger.probablePrime(bitLength, rand);
		 GenerateKey();
	}
	public RSA_Key() {
	}

	public void GeneratKeyUsing(BigInteger publicKEY_e, BigInteger p, BigInteger q) {
		this.p = new BigInteger(p.toString());
		this.q = new BigInteger(q.toString());
		this.publicKEY_e = new BigInteger(publicKEY_e.toString());
		phy_N = this.p.subtract(new BigInteger("1")).multiply(this.q.subtract(new BigInteger("1")));

		if(publicKEY_e.gcd(phy_N).compareTo(BigInteger.ONE) != 0){
			System.err.println("ERROR: gcd(e,phy(n)) != 1");
			publicKEY_e = null;
			privateKEY_d = null;
			p = null;
			q = null;
			phy_N = null;
			return;
		}
		this.privateKEY_d = this.publicKEY_e.modInverse(this.phy_N);

	}
	
	public void GenerateKey(){
		
		phy_N = p.subtract(new BigInteger("1")).multiply(q.subtract(new BigInteger("1")));
		publicKEY_e = phy_N.divide(new BigInteger("3")).nextProbablePrime();
		int i = 3;
		while(publicKEY_e.gcd(phy_N).compareTo(BigInteger.ONE) != 0 && i<phy_N.doubleValue()){
			publicKEY_e = phy_N.divide(new BigInteger(""+(i++))).nextProbablePrime();
		}

		if(publicKEY_e.gcd(phy_N).compareTo(BigInteger.ONE) != 0){
			System.err.println("ERROR: gcd(e,phy(n)) != 1");
			publicKEY_e = null;
			privateKEY_d = null;
			p = null;
			q = null;
			phy_N = null;
			return;
		}
		
		privateKEY_d = publicKEY_e.modInverse(phy_N);
	}
	

	public BigInteger getPublicKEY_e() {
		return publicKEY_e;
	}
	public void setPublicKEY_e(BigInteger publicKEY_e) {
		this.publicKEY_e = publicKEY_e;
	}
	public BigInteger getPrivateKEY_d() {
		return privateKEY_d;
	}
	public void setPrivateKEY_d(BigInteger privateKEY_d) {
		this.privateKEY_d = privateKEY_d;
	}
	public BigInteger getPhy_N() {
		return phy_N;
	}
	public void setPhy_N(BigInteger phy_N) {
		this.phy_N = phy_N;
	}
	public BigInteger getP() {
		return p;
	}
	public void setP(BigInteger p) {
		this.p = p;
	}
	public BigInteger getQ() {
		return q;
	}
	public void setQ(BigInteger q) {
		this.q = q;
	}
	
	
	
}
