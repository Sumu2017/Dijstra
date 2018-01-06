package com.sumu.demo;

/**
 * 迪杰斯特拉算法
 * @author sumu
 *
 */
public class Dijstra {
	
	private final static int VERTEX_SIZE=9;
	
	/**
	 * 获取一个最短路径
	 * @param graph
	 * 1、首先初始化shortTablePath[]，将V0到其他顶点的最短路径和，默认为就是V0这一组的权值
	 * 2、获取shortTablePath[]里面的最小值min以及其下标minIndex，然后将shortTablePath[]数组跟(VminIndex数组+min)对比，如果更小则将shortTablePath[]里面的值替换
	 * 3、重复第二步骤，直到结束
	 * 核心思想:shortTablePath[]存放的是源点V0到其他顶点的最短路径，然后从1开始，先获取shortTablePath[]里面的最小值顶点，
	 * 然后再看该顶点是否可以到达其他顶点并且看看通过该顶点到达其他点的路径长度是否比源点V0到其他顶点更短，如果是就替换
	 * 举例：V0->V1=2, V1->v2=1,V0->V2=4;
	 * V0-->V2  ，一种是直接到达 距离是4  而另一种是 V0-V1-V2 距离是 3 那么最短的就是V0通V1到V2是最短的
	 * 
	 */
	public static void shortestPathDijstra(Graph graph) {
		int[] shortTablePath=new int[VERTEX_SIZE];//记录V0到其他顶点的最短路径和
		boolean[] isGetPath=new boolean[VERTEX_SIZE];
		int vertexSize = graph.getVertexSize();
		int maxWeight = Graph.getMaxWeight();
		int[][] matrix = graph.getMatrix();
		for (int i = 0; i <vertexSize ; i++) {
			shortTablePath[i]=matrix[0][i];//初始化，获取V0这一组的权值
		}
		isGetPath[0]=true;
		int min=maxWeight;
		int minIndex=0;
		for (int i = 1; i < vertexSize; i++) {
			min=maxWeight;
			for (int j = 0; j < vertexSize; j++) {
				//获取shortTablePath里面的最小值，以及下标
				int shortPath= shortTablePath[j];
				if (!isGetPath[j]&&shortPath<min) {
					min=shortPath;
					minIndex=j;
				}
			}
			isGetPath[minIndex]=true;
			for (int j = 0; j < vertexSize; j++) {
				//然后将shortTablePath[i]与(Vmin[i]+min)进行对比
				//如果shortTablePath[i]>Vmin[i]+min,则Vmin[i]+min是新的shortTablePath[i]
				//即另一条更短的路径
				int weight=matrix[minIndex][j];
				int minWeight=shortTablePath[j];
				if (!isGetPath[j]&&(weight+min)<minWeight) {
					shortTablePath[j]=weight+min;
				}
			}
		}
		
		for (int i = 0; i < vertexSize; i++) {
			System.out.println("V0到V"+i+"的最短路径和："+shortTablePath[i]);
		}
	}
	
	public static void main(String[] args) {
		Graph graph=new Graph(VERTEX_SIZE);
		graph.createGraph();
		Dijstra.shortestPathDijstra(graph);
	}
}
