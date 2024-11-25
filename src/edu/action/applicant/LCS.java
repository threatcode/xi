package edu.action.applicant;

public class LCS {
	
	// These are "constants" which indicate a direction in the backtracking array.
		private static final int NEITHER     = 0;
		private static final int UP          = 1;
		private static final int LEFT        = 2;
		private static final int UP_AND_LEFT = 3;

		public static String LCSAlgorithm(String a, String b) {
			int n = a.length();
			int m = b.length();
			int S[][] = new int[n+1][m+1];
			int R[][] = new int[n+1][m+1];
			int ii, jj;

			// It is important to use <=, not <.  The next two for-loops are initialization
			for(ii = 0; ii <= n; ++ii) {
				S[ii][0] = 0;
				R[ii][0] = UP;
			}
			for(jj = 0; jj <= m; ++jj) {
				S[0][jj] = 0;
				R[0][jj] = LEFT;
			}

			// This is the main dynamic programming loop that computes the score and
			// backtracking arrays.
			for(ii = 1; ii <= n; ++ii) {
				for(jj = 1; jj <= m; ++jj) { 
		
					if( a.charAt(ii-1) == b.charAt(jj-1) ) {
						S[ii][jj] = S[ii-1][jj-1] + 1;
						R[ii][jj] = UP_AND_LEFT;
					}

					else {
						S[ii][jj] = S[ii-1][jj-1] + 0;
						R[ii][jj] = NEITHER;
					}

					if( S[ii-1][jj] >= S[ii][jj] ) {	
						S[ii][jj] = S[ii-1][jj];
						R[ii][jj] = UP;
					}

					if( S[ii][jj-1] >= S[ii][jj] ) {
						S[ii][jj] = S[ii][jj-1];
						R[ii][jj] = LEFT;
					}
				}
			}

			// The length of the longest substring is S[n][m]
			ii = n; 
			jj = m;
			int pos = S[ii][jj] - 1;
			char lcs[] = new char[ pos+1 ];

			// Trace the backtracking matrix.
			while( ii > 0 || jj > 0 ) {
				if( R[ii][jj] == UP_AND_LEFT ) {
					ii--;
					jj--;
					lcs[pos--] = a.charAt(ii);
				}
		
				else if( R[ii][jj] == UP ) {
					ii--;
				}
		
				else if( R[ii][jj] == LEFT ) {
					jj--;
				}
			}

			return new String(lcs);
		}
		
		
		public static String LCSAlgorithm2(String str1, String str2)

	    {

	        int l1 = str1.length();

	        int l2 = str2.length();

	 

	        int[][] arr = new int[l1 + 1][l2 + 1];

	 

	        for (int i = l1 - 1; i >= 0; i--)

	        {

	            for (int j = l2 - 1; j >= 0; j--)

	            {

	                if (str1.charAt(i) == str2.charAt(j))

	                    arr[i][j] = arr[i + 1][j + 1] + 1;

	                else 

	                    arr[i][j] = Math.max(arr[i + 1][j], arr[i][j + 1]);

	            }

	        }

	 

	        int i = 0, j = 0;

	        StringBuffer sb = new StringBuffer();

	        while (i < l1 && j < l2) 

	        {

	            if (str1.charAt(i) == str2.charAt(j)) 

	            {

	                sb.append(str1.charAt(i));

	                i++;

	                j++;

	            }

	            else if (arr[i + 1][j] >= arr[i][j + 1]) 

	                i++;

	            else

	                j++;

	        }

	        return sb.toString();

	    }
	
	

}
