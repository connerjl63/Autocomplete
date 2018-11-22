
/*************************************************************************
 * @author Conner Lewis
 *
 * Description: A term and its weight.
 * 
 *************************************************************************/

import java.util.Comparator;

public class Term implements Comparable<Term> {

	private final String myWord;
	private final double myWeight;

	/**
	 * The constructor for the Term class. Should set the values of word and
	 * weight to the inputs, and throw the exceptions listed below
	 * 
	 * @param word
	 *            The word this term consists of
	 * @param weight
	 *            The weight of this word in the Autocomplete algorithm
	 * @throws NullPointerException
	 *             if word is null
	 * @throws IllegalArgumentException
	 *             if weight is negative
	 */
	public Term(String word, double weight) {
		if(word.equals(null)) throw new NullPointerException();
		if(weight < 0) throw new IllegalArgumentException("negative weight "+weight);

		myWord = word;
		myWeight = weight;
	}
	
	/**
	 * The default sorting of Terms is lexicographical ordering.
	 */
	public int compareTo(Term that) {
		return myWord.compareTo(that.myWord);
	}

	/**
	 * Getter methods, use these in other classes which use Term
	 */
	public String getWord() {
		return myWord;
	}

	public double getWeight() {
		return myWeight;
	}

	public String toString() {
		return String.format("(%2.1f,%s)", myWeight, myWord);
	}
	
	@Override
	public boolean equals(Object o) {
		Term other = (Term) o;
		return this.compareTo(other) == 0;
	}

	/**
	 * A Comparator for comparing Terms using a set number of the letters they
	 * start with. This Comparator may be useful in writing your implementations
	 * of Autocompletors.
	 *
	 */
	public static class PrefixOrder implements Comparator<Term> {
		private final int myPrefixSize;

		public PrefixOrder(int r) {
			this.myPrefixSize = r;
		}

		/**
		 * Compares v and w lexicographically using only their first r letters.
		 * If the first r letters are the same, then v and w should be
		 * considered equal. This method should take O(r) to run, and be
		 * independent of the length of v and w's length. You can access the
		 * Strings to compare using v.word and w.word.
		 * 
		 * @param v/w
		 *            - Two Terms whose words are being compared
		 */
		public int compare(Term v, Term w) {
			int newsize = myPrefixSize;
			int ret = 0;
			//Checks to see if either word is less than the prefix size and saves
			//the new size of the prefix being checked
			if(v.getWord().length() < newsize) {
				newsize = v.getWord().length();
			}
			if(w.getWord().length() < newsize) {
				newsize = w.getWord().length();
			}
			//Checks each individual character to see if they are equal
			//and adjusts the return value accordingly
			for(int i = 0; i <= myPrefixSize; i++) {
				if(i < newsize) {
					if(v.getWord().charAt(i) < w.getWord().charAt(i)) {
						ret += v.getWord().charAt(i)-w.getWord().charAt(i);
					}
					else if(v.getWord().charAt(i) > w.getWord().charAt(i)) { 
						ret += v.getWord().charAt(i)-w.getWord().charAt(i);
					}
				} else {
					//If either word is shorter than the prefixsize, that word
					//is immediately set lower
					if(v.getWord().length() <= newsize && newsize != myPrefixSize) {
						ret = -1;
					}
					if(w.getWord().length() <= newsize && newsize != myPrefixSize) {
						ret = 1;
					}
				}
				//exits the for loop
				if(ret != 0) i = myPrefixSize+5;
			}
			return ret;
		}
	
	}

	/**
	 * A Comparator for comparing Terms using only their weights, in descending
	 * order. This Comparator may be useful in writing your implementations of
	 * Autocompletor
	 *
	 */
	public static class ReverseWeightOrder implements Comparator<Term> {
		public int compare(Term v, Term w) {
			//Compares such that the larger weight values are placed first
			int comp = 0;
			if(v.getWeight() > w.getWeight()) comp = (int) (w.getWeight()-v.getWeight());
			if(v.getWeight() < w.getWeight()) comp = (int) (w.getWeight()-v.getWeight());

			return comp;
		}
	}

	/**
	 * A Comparator for comparing Terms using only their weights, in ascending
	 * order. This Comparator may be useful in writing your implementations of
	 * Autocompletor
	 *
	 */
	public static class WeightOrder implements Comparator<Term> {
		public int compare(Term v, Term w) {
			//Compares such that the smaller weight values are placed first
			int comp = 0;
			if(v.getWeight() > w.getWeight()) comp = (int) (v.getWeight()-w.getWeight());
			if(v.getWeight() < w.getWeight()) comp = (int) (v.getWeight()-w.getWeight());

			return comp;
		}
	}
}
