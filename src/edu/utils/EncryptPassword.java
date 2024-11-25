package edu.utils;

public class EncryptPassword {

	int gcd(int a, int b)
	{
		int r;
		while (b > 0)
		{
			r = a % b;
			a = b;
			b = r;
		}
		return a;
	}

	int rel_prime(int phi)
	{
		int rel = 5;

		while (gcd(phi, rel) != 1)
			rel++;
		return rel;
	}

	int power(int a, int b)
	{
		int temp = 1, i;
		for (i = 1; i <= b; i++)
			temp *= a;
		return temp;
	}

	int encrypt(int N, int e, int M)
	{
		int r, i = 0, prod = 1, rem_mod = 0;
		while (e > 0)
		{
			r = e % 2;
			if (i++ == 0)
				rem_mod = M % N;
			else
				rem_mod = power(rem_mod, 2) % N;
			if (r == 1)
			{
				prod *= rem_mod;
				prod = prod % N;
			}
			e = e / 2;
		}
		return prod;
	}

	int calculate_d(int phi, int e)
	{
		int x, y, x1, x2, y1, y2, temp, r, orig_phi;
		orig_phi = phi;
		x2 = 1;
		x1 = 0;
		y2 = 0;
		y1 = 1;
		while (e > 0)
		{
			temp = phi / e;
			r = phi - temp * e;
			x = x2 - temp * x1;
			y = y2 - temp * y1;
			phi = e;
			e = r;
			x2 = x1;
			x1 = x;
			y2 = y1;
			y1 = y;
			if (phi == 1)
			{
				y2 += orig_phi;
				break;
			}
		}
		return y2;
	}

	int decrypt(int c, int d, int N)
	{
		int r, i = 0, prod = 1, rem_mod = 0;
		while (d > 0)
		{
			r = d % 2;
			if (i++ == 0)
				rem_mod = c % N;
			else
				rem_mod = power(rem_mod, 2) % N;
			if (r == 1)
			{
				prod *= rem_mod;
				prod = prod % N;
			}
			d = d / 2;
		}
		return prod;
	}

	int out(int p, int q, int M)
	{
		int N = p * q;
		int phi = (p - 1) * (q - 1);
		int e = rel_prime(phi);
		int c = encrypt(N, e, M);
		return c;
		// int d = calculate_d(phi, e);
		// decrypt(c,d,N);
	}

	public String MyEnc(String str)
	{
		String answer = "";
		int len = str.length(), i;
		int p = 11, q = 23;
		for (i = 0; i < len; i++)
		{
			int val = out(p, q, str.charAt(i));
			answer += val + "-";
		}
		return answer;
	}
	
}
