import java.util.LinkedList;
import java.math.*;

public class Automaton implements IStringMatcher {

	@Override
	public LinkedList<Integer> validShifts(String pattern, String text) {
		//TODO
		LinkedList<Integer> SS = new LinkedList<Integer>();
		int [][] f = Compute_Transition_Function(pattern);
		int n = text.length();
		int m = pattern.length();
		int q = 0;
		for(int i = 0; i < n; i++){
			q = f[q][text.charAt(i)];
			if(q == m) SS.add(i - m + 1);
		}
		return SS;
	}

	private int [][] Compute_Transition_Function(String pattern){

		int m = pattern.length();
		int [][] f = new int[m+1][226];
		int k;
		String suffix;
		String preffix;
		for(int q = 0; q <= m; q++){
			for(int a = 0; a <= 225; a++){
				k = Math.min(m+1, q+2);
				do{
					k--;
					suffix = pattern.substring(0, k);
					preffix = pattern.substring(0, q);
					preffix += (char)a;

				}while (!preffix.endsWith(suffix));
				f[q][a] = k;
			}
		}
		return f;
	}
}