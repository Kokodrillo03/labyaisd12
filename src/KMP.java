import java.util.LinkedList;

public class KMP implements IStringMatcher {

	@Override
	public LinkedList<Integer> validShifts(String pattern, String text) {
		LinkedList<Integer> SS = new LinkedList<Integer>();
		int n = text.length();
		int m = pattern.length();
		int [] f = Compute_Prefix_Function(pattern);
		int q = 0;
		for(int i = 0; i < n; i++){
			while (q > 0 && pattern.charAt(q) != text.charAt(i)) q = f[q];
			if(pattern.charAt(q) == text.charAt(i)) q++;
			if(q == m){
				SS.add(i - m + 1);
				q = f[q-1];
			}
		}
		return SS;
	}

	private int [] Compute_Prefix_Function(String pattern){
		int m = pattern.length();
		int [] f = new int[m];
		int k;
		k = 0;

		for(int i = 2; i <= m; i++){
			while (k > 0 && pattern.charAt(k) != pattern.charAt(i-1)){
				k = f[k];
			}
			if(pattern.charAt(k) == pattern.charAt(i-1)) k++;
			f[i-1] = k;
		}

		return f;
	}

}