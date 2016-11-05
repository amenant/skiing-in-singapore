package com.redmart;


import java.util.ArrayList;
import java.util.List;

public class Ski {
	
	/**
	 * Find in a map (2d array of int) the longest descending path with highest steep.
	 * @see http://geeks.redmart.com/2015/01/07/skiing-in-singapore-a-coding-diversion/
	 * @param matrix 2d array of integers representing a ski map with elevation
	 * @param width Width of map
	 * @param height Height of map
	 * @return Longest path with highest steep.
	 */
	public Path getLongestPath(int[][] matrix, int width, int height) {
	
		Path bestPath = new Path();
		bestPath.setNodes(new ArrayList<>());
		
		for (int y=0; y<height; y++) {
			for (int x=0; x<width; x++) {
				dive(bestPath, new ArrayList<>(), matrix, x, y);
			}
		}
		
		return bestPath;
	}
	
	
	/**
	 * Find the longest and steepest path in a map from a starting point.
	 * @param bestPath Best path found, this is updated over time.
	 * @param currentPath Current path while diving into the map.
	 * @param matrix Map of integers representing elevation.
	 * @param currentX current x position.
	 * @param currentY current y position.
	 */
	public void dive(Path bestPath, List<Integer> currentPath, int[][] matrix, int currentX, int currentY) {
		
		int currentValue = readInMatrix(matrix, currentX, currentY);
		boolean canGoDeeper = false;
		currentPath.add(currentValue);
		
		// Up
		if (existInMatrix(matrix, currentX, currentY-1) && readInMatrix(matrix, currentX, currentY-1) < currentValue) {
			dive(bestPath, new ArrayList<>(currentPath), matrix, currentX, currentY-1);
			canGoDeeper = true;
		}
		
		// Right
		if (existInMatrix(matrix, currentX+1, currentY) && readInMatrix(matrix, currentX+1, currentY) < currentValue) {
			dive(bestPath, new ArrayList<>(currentPath), matrix, currentX+1, currentY);
			canGoDeeper = true;
		}
		
		// Down
		if (existInMatrix(matrix, currentX, currentY+1) && readInMatrix(matrix, currentX, currentY+1) < currentValue) {
			dive(bestPath, new ArrayList<>(currentPath), matrix, currentX, currentY+1);
			canGoDeeper = true;
		}		
		
		// Left
		if (existInMatrix(matrix, currentX-1, currentY) && readInMatrix(matrix, currentX-1, currentY) < currentValue) {
			dive(bestPath, new ArrayList<>(currentPath), matrix, currentX-1, currentY);
			canGoDeeper = true;
		}
		
		
		if (!canGoDeeper) {
			int steep = currentPath.get(0) - currentPath.get(currentPath.size()-1);
			int nodes = currentPath.size();
			int bestNodeLength = bestPath.getNodes().size();
			int bestSteep = bestPath.getSteep();
			
			if (nodes > bestNodeLength || nodes == bestNodeLength && steep > bestSteep) {
				bestPath.setNodes(currentPath);
			}
		}
	}
	
	public int readInMatrix(int[][] matrix, int x, int y) {
		return matrix[y][x];
	}
	
	public boolean existInMatrix(int[][] matrix, int x, int y) {
		boolean isRow = y >= 0 && y < matrix.length;
		return isRow && matrix[y].length > x && x > 0; 
	}
	
	
}
