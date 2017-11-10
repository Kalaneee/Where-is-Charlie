package main;

public class DistanceBasedSearch {

	/**
	 * Computes the mean absolute error between two RGB pixels, channel by channel.
	 * @param patternPixel : a integer, the second RGB pixel.
	 * @param imagePixel : a integer, the first RGB pixel.
	 * @return a double, the value of the error for the RGB pixel pair. (an integer in [0, 255])
	 */
	public static double pixelAbsoluteError(int patternPixel, int imagePixel) {
		// c = cardinalite (longueur) de l'intervalle 
		double c = 3.0;
		// on obtient le code RGB pour le pixel concerne
		int[] patternColors = ImageProcessing.getThreeColors(patternPixel);
		int[] imageColors = ImageProcessing.getThreeColors(imagePixel);
		double somme = 0;
		for (int i = 0; i < 3; i++) {
			somme += Math.abs(patternColors[i] - imageColors[i]);
		}
		somme /= c;
		return somme;
	}

	/**
	 * Computes the mean absolute error loss of a RGB pattern if positioned
	 * at the provided row, column-coordinates in a RGB image
	 * @param row : a integer, the row-coordinate of the upper left corner of the pattern in the image.
	 * @param column : a integer, the column-coordinate of the upper left corner of the pattern in the image.
	 * @param pattern : an 2D array of integers, the RGB pattern to find
	 * @param image : an 2D array of integers, the RGB image where to look for the pattern
	 * @return a double, the mean absolute error
	 * @return a double, mean absolute error value at position (row, col) between the pattern and the part of
	 * the base image that is covered by the pattern, if the pattern is shifted by x and y.
	 */
	public static double meanAbsoluteError(int row, int col, int[][] pattern, int[][] image) {
		assert pattern.length > 0 && pattern[0].length > 0;
		assert image.length > 0 && image[0].length > 0;
		assert row + pattern.length <= image.length;
		assert col + pattern[0].length <= image[0].length;
		double pixels = pattern.length * pattern[0].length;
		double somme = 0;
		for (int i = 0; i < pattern.length; i++) {
			for (int j = 0; j < pattern[i].length; j++) {
				somme += pixelAbsoluteError(pattern[i][j], image[row + i][col + j]);
			}
		}
		somme /= pixels;	
		return somme; 
	}

	/**
	 * Compute the distanceMatrix between a RGB image and a RGB pattern
	 * @param pattern : an 2D array of integers, the RGB pattern to find
	 * @param image : an 2D array of integers, the RGB image where to look for the pattern
	 * @return a 2D array of doubles, containing for each pixel of a original RGB image, 
	 * the distance (meanAbsoluteError) between the image's window and the pattern
	 * placed over this pixel (upper-left corner) 
	 */
	public static double[][] distanceMatrix(int[][] pattern, int[][] image) {
		assert pattern.length > 0 && pattern[0].length > 0;
		assert image.length > 0 && image[0].length > 0;
		assert pattern.length <= image.length && pattern[0].length <= image[0].length;
		// On cree le tab du return de dimensions egalent au nombre de fois que l'on peut 
		// mettre le pattern dans l image
		double[][] tab = new double[image.length - pattern.length + 1][image[0].length - pattern[0].length + 1];
		for (int i = 0; i < tab.length; i++) {
			for (int j = 0; j < tab[0].length; j++) {
				tab[i][j] = meanAbsoluteError(i, j, pattern, image);
			}
		}
		return tab; 
	}
}
