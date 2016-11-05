package com.redmart;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class SkiTests {
	
	
	private Ski ski = new Ski();
	
	
	private int[][] getSmallMatrix() {
		int[][] matrix = new int[4][4];
		matrix[0] = new int[]{4, 8, 7, 3};
		matrix[1] = new int[]{2, 5, 9, 3};
		matrix[2] = new int[]{6, 3, 2, 5};
		matrix[3] = new int[]{4, 4, 1, 6};
		
		return matrix;
	}
	
	/**
	 * From bottom right we have 3 possible paths :
	 * - 6 5 3
	 * - 6 5 2 1 <= Best path
	 * - 6 1 
	 * 
	 * Steep is 6-1 = 5
	 * Node size is 4
	 */
	@Test
	public void testDiveNode() {
		
		int[][] matrix = getSmallMatrix();
		
		Path bestPath = new Path();
		bestPath.setNodes(new ArrayList<>());
		
		ski.dive(bestPath, new ArrayList<>(), matrix, 3, 3);
		Assertions.assertThat(bestPath.getNodes().size()).isEqualTo(4);
		Assertions.assertThat(bestPath.getSteep()).isEqualTo(5);
	}
	
	@Test
	public void testLongestPathOnSmallMap() {
		int[][] matrix = getSmallMatrix();
		
		Path bestPath = ski.getLongestPath(matrix, 4, 4);
		Assertions.assertThat(bestPath.getNodes().size()).isEqualTo(5);
		Assertions.assertThat(bestPath.getSteep()).isEqualTo(8);
	}
	
	
	@Test
	public void testSkiPath() {

		int columns = 0;
		int rows = 0;
		int[][] matrix = new int[0][0];
		
		
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("map.txt").getFile());

		try (Scanner scanner = new Scanner(file)) {

			int lineNumber = 0;
			
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				
				if (lineNumber == 0) {
					String[] sizes = line.split(" ");
					columns = Integer.parseInt(sizes[0]);
					rows = Integer.parseInt(sizes[1]);
					matrix = new int[columns][rows];
					
				} else {
					
					String[] data = line.split(" ");
					for (int i=0; i<data.length; i++) {
						matrix[lineNumber-1][i] = Integer.parseInt(data[i]);
					}					
				}
				
				lineNumber++;
			}

			scanner.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Path bestPath = ski.getLongestPath(matrix, columns, rows);
		System.out.println("Number of nodes " + bestPath.getNodes().size());
		System.out.println("Steep value " + bestPath.getSteep());
		System.out.println(bestPath.getNodes());
		
		
		Assertions.assertThat(bestPath.getNodes()).isNotEmpty();
	}

}
