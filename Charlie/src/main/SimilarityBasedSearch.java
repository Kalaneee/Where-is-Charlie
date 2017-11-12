package main;

public class SimilarityBasedSearch {

	/**
	 * Computes the mean value of a gray-scale image given as a 2D array 
	 * @param image : a 2D double array, the gray-scale Image
	 * @return a double value between 0 and 255 which is the mean value
	 */
	public static double mean(double[][] image) {
		assert image.length > 0 && image[0].length > 0;
		double somme = 0;
		double pixels = image.length * image[0].length;
		for (int i = 0; i < image.length; i++) {
			for (int j = 0; j < image[i].length; j++) {
				somme += image[i][j];
			}
		}
		return (somme /= pixels); 
	}
	
	/**
	 * On fait la moyenne des couleurs des pixels de notre pattern
	 * @param matrix : notre pattern, un double[][]
	 * @param row : an integer, la ligne de l image ou se situe le coin haut gauche du pattern
	 * @param col : an integer, la colonne de l image ou se situe le coin haut gauche du pattern
	 * @param width : an integer, la largeur du pattern
	 * @param height : an integer, la largeur du pattern
	 * @return a double, la moyenne des couleurs des pixels du pattern
	 */
	static double windowMean(double[][] matrix, int row, int col, int width, int height) {
		assert matrix.length > 0 && matrix[0].length > 0;
		double[][] tab = new double[width][height];
		for (int i = 0; i < tab.length; i++) {
			for (int j = 0; j < tab[i].length; j++) {
				tab[i][j] = matrix[row + i][col + j];
 			}
		}		
		return mean(tab);
	}
	
	/**
	 * Computes the Normalized Cross Correlation of a gray-scale pattern if positioned
	 * at the provided row, column-coordinate in a gray-scale image
	 * @param row : a integer, the row-coordinate of the upper left corner of the pattern in the image.
	 * @param column : a integer, the column-coordinate of the upper left corner of the pattern in the image.
	 * @param pattern : an 2D array of doubles, the gray-scale pattern to find
	 * @param image : an 2D array of double, the gray-scale image where to look for the pattern
	 * @return a double, the Normalized Cross Correlation value at position (row, col) between the pattern and the part of
	 * the base image that is covered by the pattern, if the pattern is shifted by x and y.
	 * should return -1 if the denominator is 0
	 */
	public static double normalizedCrossCorrelation(int row, int col, double[][] pattern, double[][] image) {
		assert pattern.length > 0 && pattern.length > 0;
		assert image.length > 0 && image[0].length > 0;
		assert row + pattern.length <= image.length;
		assert col + pattern[0].length <= image[0].length;
		int height = pattern[0].length;
		int width = pattern.length;
		// formule decomposee en 3 sommes : une pour le numerateur, et deux pour le denominateur
		double ncc = 0;
		double nccDenom1 = 0;
		double nccDenom2 = 0;
		double patternMean = mean(pattern);
		double portionMean = windowMean(image, row, col, width, height);
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				// somme du numerateur
				ncc += (image[row + i][col + j] - portionMean)*(pattern[i][j] - patternMean);
				// 1ere somme du denominateur
				nccDenom1 += Math.pow(image[row + i][col + j] - portionMean, 2);
				// 2eme somme du denominateur
				nccDenom2 += Math.pow(pattern[i][j] - patternMean, 2);
			}
		}
		double denom = Math.sqrt(nccDenom1 * nccDenom2);
		//si le denominateur est egal a 0, on retourne -1, sinon le ncc
		if (denom == 0)
			return -1;

		return (ncc / denom); 
	}
	
	/**
	 * Compute the similarityMatrix between a gray-scale image and a gray-scale pattern
	 * @param pattern : an 2D array of doubles, the gray-scale pattern to find
	 * @param image : an 2D array of doubles, the gray-scale image where to look for the pattern
	 * @return a 2D array of doubles, containing for each pixel of a original gray-scale image, 
	 * the similarity (normalized cross-correlation) between the image's window and the pattern
	 * placed over this pixel (upper-left corner)
	 */
	public static double[][] similarityMatrix(double[][] pattern, double[][] image) {
		assert pattern.length > 0 && pattern.length > 0;
		assert image.length > 0 && image[0].length > 0;
		// On cree le tab du return de dimensions egalent au nombre de fois que l'on peut 
		// mettre le pattern dans l'image, ex : image de longueur 6, pattern de longueur 3, on peut
		// placer 4x le pattern sans deborder de l'image
		double[][] tab = new double[image.length - pattern.length + 1][image[0].length - pattern[0].length + 1];
		for (int i = 0; i < tab.length; i++) {
			for (int j = 0; j < tab[0].length; j++) {
				tab[i][j] = normalizedCrossCorrelation(i, j, pattern, image);
			}
		}
		return tab; 
	}
}