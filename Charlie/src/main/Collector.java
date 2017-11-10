package main;

import java.util.ArrayList;

public class Collector {

	/**
	 * Find the row, column coordinates of the best element (biggest or smallest) for the given matrix
	 * @param matrix : an 2D array of doubles
	 * @param smallestFirst : a boolean, indicates if the smallest element is the best or not (biggest is then the best)
	 * @return an array of two integer coordinates, row first and then column
	 */
	public static int[] findBest(double[][] matrix, boolean smallestFirst) {
		assert matrix.length > 0 && matrix[0].length > 0;
		double min = Double.NEGATIVE_INFINITY;
		double max = Double.POSITIVE_INFINITY;
		int[]tab = new int[2];
		// si le plus petit element est considere comme le meilleur :
		if (smallestFirst) {
			for (int i = 0; i < matrix.length; i++) {
				for (int j = 0; j < matrix[i].length; j++) {
					// on pose la 1ere valeur comme maximale, puis celle-ci
					// est comparee aux autres valeurs. 
					// si la 2eme valeur est superieure au max, alors elle devient le max
					// puis est elle aussi comparee aux autres valeurs, etc...
					if (matrix[i][j] < max) {
						max = matrix[i][j];
						tab[0] = i;
						tab[1] = j;
					}
				}
			}
		}
		// si le plus grand elements est considere comme le meilleur:
		else {
			for (int i = 0; i < matrix.length; i++) {
				for (int j = 0; j < matrix[i].length; j++) {
					if (matrix[i][j] > min) {
						min = matrix[i][j];
						tab[0] = i;
						tab[1] = j;
						}
					}
				}
		}
		return tab;
	}

	
	/**
	 * Find the row, column coordinate-pairs of the n best (biggest or smallest) elements of the given matrix
	 * @param n : an integer, the number of best elements we want to find 
	 * @param matrix : an 2D array of doubles
	 * @param smallestFirst : a boolean,  indicates if the smallest element is the best or not (biggest is the best)
	 * @return an array of size n containing row, column-coordinate pairs
	 */
	public static int[][] findNBest(int n, double[][] matrix, boolean smallestFirst) {
		assert matrix.length > 0 && matrix[0].length > 0;
		// on cree une copie de matrix pour ne pas alterer son contenu.
		double[][] copie = new double[matrix.length][matrix[0].length];
		for (int i = 0; i < copie.length; i++) {
			for (int j = 0; j < copie[i].length; j++) {
				copie[i][j] = matrix[i][j];
			}
		}
		int[][] result = new int[n][2];
		double min = Double.NEGATIVE_INFINITY;
		double max = Double.POSITIVE_INFINITY;
		for (int i = 0; i < n; i++) {
			// on trouve nos N meilleures paires:
			result[i] = findBest(copie, smallestFirst);
			if (smallestFirst) {
				// si on veut trouver les plus petits, on remplace le plus petit trouve
				// par la valeur la plus grande dans notre copie pour ne pas 
				// le prendre une deuxieme fois 
				copie[result[i][0]][result[i][1]] = max;
			}
			else {
				// si on veut trouver les plus grands, on remplace le plus grand trouve
				// par la valeur la plus petite dans notre copie pour ne pas 
				// le prendre une deuxieme fois 
				copie[result[i][0]][result[i][1]] = min;
			}
		}
		return result;
	}
	
	

	/**
	 * BONUS 
	 * Notice : Bonus points are underpriced ! 
	 * 
	 * Sorts all the row, column coordinates based on their pixel value
	 * Hint : Use recursion !
	 * @param matrix : an 2D array of doubles
	 * @return A list of points, each point is an array of length 2.
	 */
	public static ArrayList<int[]> quicksortPixelCoordinates(double[][] matrix) {

		// TODO implement me correctly for "underpriced" bonus!
		return new ArrayList<int[]>();
	}

	
	/**
	 * BONUS
	 * Notice : Bonus points are underpriced !
	 * 
	 * Use a quick sort to find the row, column coordinate-pairs of the n best (biggest or smallest) elements of the given matrix
	 * Hint : return the n first or n last elements of a sorted ArrayList  
	 * @param n : an integer, the number of best elements we want to find 
	 * @param matrix : an 2D array of doubles
	 * @param smallestFirst : a boolean, indicate if the smallest element is the best or not (biggest is the best)
	 * @return an array of size n containing row, column-coordinate pairs
	 */
	public static int[][] findNBestQuickSort(int n, double[][] matrix, boolean smallestFirst) {

    	// TODO implement me correctly for underpriced bonus!
		return new int[][]{};
	}
}
