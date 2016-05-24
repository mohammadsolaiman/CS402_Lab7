package com.mohammad.cryptography.RSA;

import java.math.BigInteger;
import java.util.Random;

public class Diff_Hilman {
	
	private BigInteger q, alpha, X, Y, Y_b, K;

	
	
	public static void main(String[] args){
		
		Diff_Hilman A = new Diff_Hilman(/*q*/new BigInteger("353"),/*alpha*/ new BigInteger("3"));
		
		Diff_Hilman B = new Diff_Hilman(/*q*/new BigInteger("353"),/*alpha*/ new BigInteger("3"));

		A.setX(new BigInteger("97"));
		B.setX(new BigInteger("233"));
		
		A.setY_b(B.getY());
		B.setY_b(A.getY());
		
		System.out.println("Y_a = "+A.getY()+"\nY_b = "+B.getY()+"\n\nK on B = "+B.getK()+"\nK on A = "+A.getK());
		
	}
	
	
	public BigInteger getK() {
		return K;
	}

	public void setK(BigInteger k) {
		K = k;
	}

	public BigInteger getQ() {
		return q;
	}

	public void setQ(BigInteger q) {
		this.q = q;
	}

	public BigInteger getAlpha() {
		return alpha;
	}

	public void setAlpha(BigInteger alpha) {
		this.alpha = alpha;
	}

	public BigInteger getX() {
		return X;
	}

	public void setX(BigInteger x) {
		X = new BigInteger(x.toString());
		Y = new BigInteger(alpha.modPow(X, q).toString());
	}

	public BigInteger getY() {
		return Y;
	}

	public void setY(BigInteger y) {
		Y = y;
	}

	public BigInteger getY_b() {
		return Y_b;
	}

	public void setY_b(BigInteger y_b) {
		K =y_b.modPow(this.X, this.q);
		Y_b = y_b;
	}

	public Diff_Hilman(BigInteger q, BigInteger alpha) {
		this.q = q;
		this.alpha = alpha;
		
		Random rand = new Random();
		
		X = BigInteger.valueOf(rand.nextInt((int) q.doubleValue()));
		Y = alpha.modPow(X, q);
		
	}


	
	
	

}
