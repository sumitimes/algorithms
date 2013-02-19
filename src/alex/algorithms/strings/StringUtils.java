package alex.algorithms.strings;

public class StringUtils {

	public static String removeChars(String str, String remove) {
		char[] r = remove.toCharArray();
		char[] s = str.toCharArray();
		boolean flags[] = new boolean[128];
		for (int i = 0; i < r.length; i++) {
			flags[r[i]] = true;
		}
		int dst = 0;
		for (int i = 0; i < s.length; i++) {
			if (!flags[s[i]]) {
				s[dst++] = s[i];
			}
		}

		return new String(s, 0, dst);
	}

	public static String reverseWords(char[] f) {
		char[] buffer = new char[f.length];
		int tokenReadPos = f.length - 1;
		int writePos = 0;
		while (tokenReadPos > 0) {
			if (f[tokenReadPos] == ' ') {
				buffer[writePos++] = f[tokenReadPos--];
			} else {
				int wordEnd = tokenReadPos;
				while (tokenReadPos >= 0 && f[tokenReadPos] != ' ')
					tokenReadPos--;
				for (int i = tokenReadPos + 1; i <= wordEnd; i++) {
					buffer[writePos++] = f[i];
				}
			}
		}

		return new String(buffer, 0, writePos);
	}

	public static String numberToString(int num) {
		char[] d = new char[20];
		boolean isNegative = false;
		if (num < 0) {
			isNegative = true;
			num = -num;
		}
		int i = 0;
		do {
			d[i++] = (char) ((num % 10) + '0');
			num = num / 10;
		} while (num > 0);
		StringBuilder sb = new StringBuilder();
		if (isNegative)
			sb.append("-");
		while (i > 0) {
			sb.append(d[--i]);
		}
		return sb.toString();
	}

	public static void removeDuplicates(char[] str) {
		if (str == null || str.length < 2)
			return;
		int tail = 1;// String end without repetition.
		for (int i = 1; i < str.length; i++) {
			int j = 0;
			for (; j < tail; j++) {
				if (str[j] == str[i])
					break;// i must advance to find a different character.
			}
			if (j == tail) {// tail should be replaced
				str[tail++] = str[i];
			}
		}
		str[tail] = 0;
	}

	public static void main(String[] args) {
		System.out.println(removeChars("aaabbbbAlex", "Alex"));
		System.out.println(reverseWords("AAAA BBB CCC".toCharArray()));
		System.out.println(numberToString(01));
		char[] chars = "AAAAAAAAAAAaBBabbbc".toCharArray();
		removeDuplicates(chars);
		System.out.println(new String(chars));
	}

}