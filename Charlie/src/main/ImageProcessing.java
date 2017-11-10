package main;
public final class ImageProcessing {
	
    /**
     * Returns red component from given packed color.
     * @param rgb : a 32-bits RGB color
     * @return an integer,  between 0 and 255
     * @see #getGreen
     * @see #getBlue
     * @see #getRGB(int, int, int)
     */
    public static int getRed(int rgb) {
    	// on decale de 16 bits vers la droite pour obtenir uniquement les
    	// 8 bits correspondant au rouge.
    	// 0xff permet de selectionner seulement 8 bits
    	rgb = (rgb >> 16) & 0xff;
    	rgb = testValMax(rgb);
    	return rgb; 
    }

    /**
     * Returns green component from given packed color.
     * @param rgb : a 32-bits RGB color
     * @return an integer between 0 and 255
     * @see #getRed
     * @see #getBlue
     * @see #getRGB(int, int, int)
     */
    public static int getGreen(int rgb) {

    	rgb = (rgb >> 8) & 0xff;
    	rgb = testValMax(rgb);
    	return rgb; 
    }

    /**
     * Returns blue component from given packed color.
     * @param rgb : a 32-bits RGB color
     * @return an integer between 0 and 255
     * @see #getRed
     * @see #getGreen
     * @see #getRGB(int, int, int)
     */
    public static int getBlue(int rgb) {
    	rgb = rgb & 0xff;
    	rgb = testValMax(rgb);
    	return rgb;
    }

    /**
     * Get the three colors at the same time(red, blue and green) for a pixel 
     * and store it in the array color[].
     * @param rgb : a 32-bits RGB color
     * @return an array of int with the three colors 
     */
    public static int[] getThreeColors(int rgb) {
    	int[] tab = new int[3];
    	tab[0] = getRed(rgb);
    	tab[1] = getGreen(rgb);
    	tab[2] = getBlue(rgb);
    	return tab; 
    }
   
    /**
     * Returns the average of red, green and blue components from given packed color.
     * @param rgb : 32-bits RGB color
     * @return a double between 0 and 255
     * @see #getRed
     * @see #getGreen
     * @see #getBlue
     * @see #getRGB(int)
     */
    public static double getGray(int rgb) {
    	double moyenne = (getRed(rgb) + getGreen(rgb) + getBlue(rgb))/3.0;
    	//moyenne = testValMax(moyenne);
    	return (moyenne);
    }

    /**
     * test if value is between 0 and 255 for an integer
     * @param color : an integer 
     * @return an integer between 0 and 255
     */
    public static int testValMax(int color) {
    	if (color > 255)
    		color = 255;
    	else if (color < 0) {
    		color = 0;
    	}
    	return color;
    }
    
	/**
	 * test if value is between 0.0 and 255.0 for a double
	 * @param color : a double
	 * @return a double between 0.0 and 255.0
	 */
    public static double testValMax(double color) {
    	if (color > 255.0)
    		color = 255.0;
    	else if (color < 0.0) {
    		color = 0.0;
    	}
    	return color;
    }
    
    /**
     * Returns packed RGB components from given red, green and blue components.
     * @param red : an integer 
     * @param green : an integer 
     * @param blue : an integer
     * @return a 32-bits RGB color
     * @see #getRed
     * @see #getGreen
     * @see #getBlue
     */
    public static int getRGB(int red, int green, int blue) {
    	red = testValMax(red);
    	green = testValMax(green);
    	blue = testValMax(blue);
    	// le signe "|" n'est pas un OU mais un ET binaire qui permet de concatener les couleurs
    	// et d'obtenir un 32-bits RGB avec les valeurs de rouge, vert et bleu separement.
    	int rgb = (red << 16 | green << 8 | blue);
    	return rgb; 
    }

    /**
     * Returns packed RGB components from given gray-scale value.
     * @param gray : an integer 
     * @return a 32-bits RGB color
     * @see #getGray
     */
    public static int getRGB(double gray) {
    	gray = testValMax(gray);
    	int grayEntier = (int)Math.round(gray);
    	int rgb = getRGB(grayEntier, grayEntier, grayEntier);
    	return rgb; 
    }

    /**
     * Converts packed RGB image to gray-scale image.
     * @param image : a HxW integer array
     * @return a HxW double array
     * @see #encode
     * @see #getGray
     */
    public static double[][] toGray(int[][] image) {
    	double gray[][] = new double[image.length][image[0].length];
    	for (int i = 0; i < gray.length; i++) {
    		for (int j = 0; j < gray[i].length; j++) {
    			gray[i][j] = getGray(image[i][j]);
    		}
    	}
    	return gray;
    }

    /**
     * Converts gray-scale image to packed RGB image.
     * @param channels : a HxW double array
     * @return a HxW integer array
     * @see #decode
     * @see #getRGB(double)
     */
    public static int[][] toRGB(double[][] gray) {
    	int grayImage[][] = new int[gray.length][gray[0].length];
    	for (int i = 0; i < grayImage.length; i++) {
    		for (int j = 0; j < grayImage[i].length; j++) {
    			// chaque pixel de l'image en niveaux de gris est converti en RGB
    			grayImage[i][j] = getRGB(gray[i][j]);
    		}
    	}
    	return grayImage;
    }

    
    /**
     * Convert an arbitrary 2D double matrix into a 2D integer matrix 
     * which can be used as RGB image
     * @param matrix : the arbitrary 2D double array to convert into integer
     * @param min : a double, the minimum value the matrix could theoretically contains
     * @param max : a double, the maximum value the matrix could theoretically contains
     * @return an 2D integer array, containing a RGB mapping of the matrix 
     */
    public static int[][] matrixToRGBImage(double[][] matrix, double min, double max) {
    	assert matrix.length > 0 && matrix[0].length > 0;
    	if (min > max) {
    		double tmp = min;
    		min = max;
    		max = tmp;
    	}
    	int[][]tab = new int[matrix.length][matrix[0].length];
    	for (int i = 0; i < matrix.length; i++) {
    		for (int j = 0; j < matrix[i].length; j++) {
    			
    			tab[i][j] = getRGB(((matrix[i][j] - min) / (max - min))*255);
    		}
    	}
    	return tab;
    }
}
