package main;

//Import the helper (uncomment this line)
//import provided.Helper;


/**
 * 
 * @author Giulia Murgia - Valentin Kalin
 *
 *	Where is Charlie Project 
 *
 */
public final class Main {

	/* 
	 * This class is incomplete!!
	 * 
	 * You are expected to write at least one testcase for each required method.
	 * You will find some examples of testcases below.
	 */
	
    public static void main(String[] args) {
    	testGetRed();
    	testGetGreen(); 
    	testGetBlue();
    	testGetThreeColors();
    	testGetGray();
    	testGetRGB();
    	testGetRGBGray();
    	testToGray();
    	testToRGB();
    	testMatrixToRGBImage();
    	testFindBest();
    	//testGrayscale();
    	//testFindNBest();
    	testPixelAbsoluteError();
    	testMeanAbsoluteError();
    	testDistanceMatrix();
    	//testDistanceBasedSearch();
    	testMean();
    	testWindowMean();
    	testNCCPatternEqualImage();
    	testSimilarityPatternEqualImage();
    	testSimilaritySimple();
    	//testSimilarityBasedSearch();   
    	findCharlie();
    }
    
    /*
     * Tests for Class ImageProcessing
     */
    public static void testGetRed() { 
    	int color = 0b11110000_00001111_01010101;
    	int ref = 0b11110000;
    	int red = ImageProcessing.getRed(color);
    	if (red == ref) {
    		System.out.println("Test 1 passed");
    	} else {
    		System.out.println("Test 1 failed. Returned value = " + red + " Expected value = " + ref);
    	}
    }
    
    public static void testGetGreen() { 
    	int color = 0b11110000_00001111_01010101;
    	int ref = 0b00001111;
    	int green = ImageProcessing.getGreen(color);
    	if (green == ref) {
    		System.out.println("Test 2 passed");
    	} else {
    		System.out.println("Test 2 failed. Returned value = " + green + " Expected value = " + ref);
    	}
    }
    
    public static void testGetBlue() { 
    	int color = 0b11110000_00001111_01010101;
    	int ref = 0b01010101;
    	int blue = ImageProcessing.getBlue(color);
    	if (blue == ref) {
    		System.out.println("Test 3 passed");
    	} else {
    		System.out.println("Test failed 3. Returned value = " + blue + " Expected value = " + ref);
    	}
    }
    
    public static void testGetThreeColors() { 
    	int color = 0b11110000_00001111_01010101;
    	int[] ref = {0b11110000, 0b00001111, 0b01010101};
    	int[] test = ImageProcessing.getThreeColors(color);
    	for (int i = 0; i < test.length; i++) {
    		if (test[i] != ref[i]) {
    			System.out.println("Test 4 failed. Returned value = " + test[i] + " Expected value = " + ref[i]);
    			return;
    			}
    		}
    	System.out.println("Test 4 passed");
    	}
    
    public static void testGetGray() {
    	int color = 0b11110000_00001111_01010101;
    	double ref = 340.0 / 3.0;
    	double gray = ImageProcessing.getGray(color);
    	if (gray == ref) {
    		System.out.println("Test 5 passed");
    	} else {
    		System.out.println("Test 5 failed. Returned value = " + gray + " Expected value = " + ref);
    	}
    }
    
    public static void testGetRGB() {
    	int red = 0b11110000;
    	int green = 0b00001111;
    	int blue = 0b01010101;
    	int ref = 0b11110000_00001111_01010101;
    	int color = ImageProcessing.getRGB(red, green, blue);
    	if (color == ref) {
    		System.out.println("Test 6 passed");
    	} else {
    		System.out.println("Test 6 failed. Returned value = " + color + " Expected value = " + ref);
    	}
    }
    
    public static void testGetRGBGray() {
    	double gray = 127.0;
    	double gray2 = -100.0;
    	int ref = 8355711;
    	int ref2 = 0;
    	int color = ImageProcessing.getRGB(gray);
    	int color2 = ImageProcessing.getRGB(gray2);
    	if (color == ref) {
    		System.out.println("Test 7a passed");
    	} else {
    		System.out.println("Test 7a failed. Returned value = " + color + " Expected value = " + ref);
    	}
    	if (color2 == ref2) {
    		System.out.println("Test 7b passed");
    	} else {
    		System.out.println("Test 7b failed. Returned value = " + color2 + " Expected value = " + ref2);
    	}
    }
    
    public static void testGrayscale() {
    	System.out.println("Test Grayscale");
     	int[][] image = Helper.read("images/food.png");
    	double[][] gray = ImageProcessing.toGray(image);
    	Helper.show(ImageProcessing.toRGB(gray), "test bw");
    }
    
    public static void testToGray() {
    	int[][] image = {{0x20c0ff, 0x123456}, {0xffffff, 0x000000}};
    	double[][] ref = {{159.666666666666666, 52.0},{255.0, 0.0} };
    	double[][] test = ImageProcessing.toGray(image);
    	for (int i = 0; i < test.length; i++) {
    		for (int j = 0; j < test[i].length; j++) {
    			if (test[i][j] != ref[i][j]) {
    	    		System.out.println("Test 8 failed. Returned value = " + test[i][j] + " Expected value = " + ref[i][j]);
    	    		return;
    			}
    		}
    	}
    	System.out.println("Test 8 passed");
    }
    
    public static void testToRGB() {
    	double[][] image = {{159.666666666666666, 52.0},{255.0, 0.0} };
    	int[][] ref = {{10526880, 3421236}, {16777215, 0}};
    	int[][] test = ImageProcessing.toRGB(image);
    	for (int i = 0; i < test.length; i++) {
    		for (int j = 0; j < test[i].length; j++) {
    			if (test[i][j] != ref[i][j]) {
    	    		System.out.println("Test 9 failed. Returned value = " + test[i][j] + " Expected value = " + ref[i][j]);
    	    		return;
    			}
    		}
    	}
    	System.out.println("Test 9 passed");
    }
    
    public static void testMatrixToRGBImage() {
    	// valeurs de ref calculees avec la forme de la donnee et la methode getRGB
    	int[][] ref = {{4408131, 0},{9211020, 16777215}};
    	double[][] image = {{150.0, 12.0},{300.0, 537.0}};
    	int [][]test = ImageProcessing.matrixToRGBImage(image, 12.0, 537.0);
    	for (int i = 0; i < test.length; i++) {
    		for (int j = 0; j < test[i].length; j++) {
    			if (test[i][j] != ref[i][j]) {
    	    		System.out.println("Test 10 failed. Returned value = " + test[i][j] + " Expected value = " + ref[i][j]);
    	    		return;
    			}
    		}
    	}
    	System.out.println("Test 10 passed");
    }
        
    /*
     * Tests for Class Collector
     */
    
    public static void testFindBest() {
    	double[][] image = {{150.0, 12.0},{300.0, 537.0}};
    	int[] ref1 = {0, 1};
    	int[] ref2 = {1, 1};
    	int[] test1 = Collector.findBest(image, true);
    	int[] test2 = Collector.findBest(image, false);
    	if (test1[0] == ref1[0] && test1[1] == ref1[1]) {
    		System.out.println("Test 11a passed");
    	} else {
    		System.out.println("Test 11a failed. Returned value = " + test1[0] + " " + test1[1] + " Expected value = " + ref1[0] + " " + ref1[1]);
    	}
    	if (test2[0] == ref2[0] && test2[1] == ref2[1]) {
    		System.out.println("Test 11b passed");
    	} else {
    		System.out.println("Test 11b failed. Returned value = " + test2[0] + " " + test2[1] + " Expected value = " + ref2[0] + " " + ref2[1]);
    	}
    }
    
    public static void testFindNBest() {
    	System.out.println("Test findNBest");
    	double[][] t = new double[][] {{20, 30, 10, 50, 32}, {28, 39, 51, 78, 91}};
    	int[][] coords = Collector.findNBest(10, t, true);    			
    	for (int[] a : coords) {
    		int r = a[0];
    		int c = a[1];
    		System.out.println("Row=" + r + " Col=" + c + " Val=" + t[r][c]);
    	}    
    }

    /*
     * Tests for Class DistanceBasedSearch
     */
    
    public static void testDistanceBasedSearch() {
    	System.out.println("Test DistanceBasedSearch");
    	int[][] food = Helper.read("images/charlie_beach.png");
    	int[][] onions = Helper.read("images/charlie.png");
    	System.out.println("Etape 1");
    	double[][] distance = DistanceBasedSearch.distanceMatrix(onions, food); 
    	System.out.println("Etape 2");
    	Helper.show(ImageProcessing.matrixToRGBImage(distance, 0, 255), "Distance");
    	System.out.println("Etape 3");
    	int[] p = Collector.findBest(distance, true);
    	System.out.println("Etape 4");
    	Helper.drawBox(p[0], p[1], onions[0].length, onions.length, food);
    	Helper.show(food, "Found!");
    }
    
    public static void testPixelAbsoluteError() {
    	int pixelImage = 8336072;
    	int pixelPattern = 0b11110000_00001111_01010101;
    	double ref = Math.abs(0b11110000 - 127);
    	ref += Math.abs(0b00001111 - 50);
    	ref += Math.abs(0b01010101 - 200);
    	ref /= 3.0;
    	double test = DistanceBasedSearch.pixelAbsoluteError(pixelPattern, pixelImage);
    	if (test == ref) {
			System.out.println("Test 12 passed");
		} else {
			System.out.println("Test 12 failed. Returned value = " + test + " Expected value = " + ref);
		}
    }
    
    public static void testMeanAbsoluteError() {
    	int[][] testPattern = {{8336072, 16409800}, {16435400, 16396850}};
    	int[][] testImage = {{8336072, 16409800}, {16435400, 16422450}};
    	double test = DistanceBasedSearch.meanAbsoluteError(0, 0, testPattern, testImage);
    	double pixels = testPattern.length*testPattern[0].length;
    	double ref= DistanceBasedSearch.pixelAbsoluteError(16396850, 16422450)/(pixels);
    	if (test == ref) {
			System.out.println("Test 13 passed");
		} else {
			System.out.println("Test 13 failed. Returned value = " + test + " Expected value = " + ref);
		}
    }
    
    public static void testDistanceMatrix() {
    	int[][] testPattern = {{28, 54}, {35, 67}};
    	int[][] testImage = {{28, 54}, {35, 67}, {45, 64}, {43, 66}};
    	double[][] ref = {{0.0}, {2.75}, {3.0}};
    	double[][] test = DistanceBasedSearch.distanceMatrix(testPattern, testImage);
    	
    	for (int i = 0; i < test.length; i++) {
    		for (int j = 0; j < test[i].length; j++) {
    			if (test[i][j] != ref[i][j]) {
    	    		System.out.println("Test 14 failed. Returned value = " + test[i][j] + " Expected value = " + ref[i][j]);
    	    		return;
    			}
    		}
    	}
    	System.out.println("Test 14 passed");
    }
    
    /*
     * Tests for Class SimilarityBasedSearch
     */

    public static void testMean() {
    	double[][] image = {{100.0, 200.0}, {300.0, 400.0}};
    	double test = SimilarityBasedSearch.mean(image);
    	double ref = 1000/4.0;
    	if (test == ref) {
    		System.out.println("Test 15 passed");
    	} else {
    		System.out.println("Test 15 failed. Returned value = " + test + " Expected value = " + ref);
    	}	
    }
    
    public static void testWindowMean() {
    	double[][] matrix = {{100.0, 200.0}, {300.0, 400.0}};
    	int row = 1;
    	int col = 0;
    	int width = 1;
    	int height = 2;
    	double test = SimilarityBasedSearch.windowMean(matrix, row, col, width, height);
    	double ref = 700/2.0;
    	if (test == ref) {
    		System.out.println("Test 16 passed");
    	} else {
    		System.out.println("Test 16 failed. Returned value = " + test + " Expected value = " + ref);
    	}  	
    }
    
	public static void testNCCPatternEqualImage() {
		double[][] pattern = { { 0, 0, 0 }, { 0, 255, 0 }, { 0, 0, 0 } };
		double similarity = SimilarityBasedSearch.normalizedCrossCorrelation(0, 0, pattern, pattern);
		if (similarity == 1.0) {
			System.out.println("Test 17 passed");
		} else {
			System.out.println("Test 17 failed: expected value 1.0 but was " + similarity);
		}
	}

	public static void testSimilarityPatternEqualImage() {
		double[][] pattern = { { 0, 255 } };
		double[][] similarity = SimilarityBasedSearch.similarityMatrix(pattern, pattern);
		if (similarity.length == 1) {
			if (similarity[0][0] == 1.0) {
				System.out.println("Test 18 passed");
			} else {
				System.out.println("Test 18 failed: expected value 1.0 but was " + similarity[0][0]);
			}
		} else {
			System.out.println("Test 18 failed: expected length 1 but was " + similarity.length);
		}
	}

	public static void testSimilaritySimple() {
		double[][] image = { { 3, 2, 2, 2 }, { 0, 3, 0, 0 } };
		double[][] pattern = { { 0, 3, 0 } };
		double[][] similarity = SimilarityBasedSearch.similarityMatrix(pattern, image);

		if (similarity.length == 2 && similarity[0].length == 2) {
			if (similarity[0][0] == -0.5 && similarity[0][1] == -1.0 && similarity[1][0] == 1.0
					&& similarity[1][1] == -0.5) {
				System.out.println("Test 19 passed");
			} else {
				System.out.println("Test 19 failed: wrong values");
			}
		} else {
			System.out.println("Test 19 failed: incorrect size");
		}
	}
    
    public static void testSimilarityBasedSearch() {
    	System.out.println("Test SimilarityBasedSearch");
		int[][] food = Helper.read("images/food.png");
    	int[][] onions = Helper.read("images/onions.png");
    	double[][] foodGray = ImageProcessing.toGray(food);
    	double[][] onionsGray = ImageProcessing.toGray(onions);    	
    	double[][] similarity = SimilarityBasedSearch.similarityMatrix(onionsGray, foodGray);
    	int[][] best = Collector.findNBest(33, similarity, false);    			
    	for (int[] a : best) {
    		int r = a[0];
    		int c = a[1];
        	Helper.drawBox(r, c, onions[0].length, onions.length, food);
    	}
    	Helper.show(food, "Found again!");    	
    }
    
    public static void findCharlie() {
    	System.out.println("Find Charlie");
		int[][] beach = Helper.read("images/charlie_beach.png");
    	int[][] charlie = Helper.read("images/tete.png");
    	double[][] beachGray = ImageProcessing.toGray(beach);
    	double[][] charlieGray = ImageProcessing.toGray(charlie); 
    	System.out.println("Compute Similarity Matrix: expected time about 2 min");
    	double[][] similarity = SimilarityBasedSearch.similarityMatrix(charlieGray, beachGray);
    	System.out.println("Find N Best");
    	int[] best = Collector.findBest(similarity, false); 
    	double max = similarity[best[0]][best[1]];
    	Helper.show(ImageProcessing.matrixToRGBImage(similarity, -1, max), "Similarity");
    	Helper.drawBox(best[0], best[1], charlie[0].length, charlie.length, beach);
    	System.out.println("drawBox at (" + best[0] + "," + best[1] + ")");
    	Helper.show(beach, "Found again!");    	
    }
}
