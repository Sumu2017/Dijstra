package com.sumu.demo;

/**
 * �Ͻ�˹�����㷨
 * @author sumu
 *
 */
public class Dijstra {
	
	private final static int VERTEX_SIZE=9;
	
	/**
	 * ��ȡһ�����·��
	 * @param graph
	 * 1�����ȳ�ʼ��shortTablePath[]����V0��������������·���ͣ�Ĭ��Ϊ����V0��һ���Ȩֵ
	 * 2����ȡshortTablePath[]�������Сֵmin�Լ����±�minIndex��Ȼ��shortTablePath[]�����(VminIndex����+min)�Աȣ������С��shortTablePath[]�����ֵ�滻
	 * 3���ظ��ڶ����裬ֱ������
	 * ����˼��:shortTablePath[]��ŵ���Դ��V0��������������·����Ȼ���1��ʼ���Ȼ�ȡshortTablePath[]�������Сֵ���㣬
	 * Ȼ���ٿ��ö����Ƿ���Ե����������㲢�ҿ���ͨ���ö��㵽���������·�������Ƿ��Դ��V0������������̣�����Ǿ��滻
	 * ������V0->V1=2, V1->v2=1,V0->V2=4;
	 * V0-->V2  ��һ����ֱ�ӵ��� ������4  ����һ���� V0-V1-V2 ������ 3 ��ô��̵ľ���V0ͨV1��V2����̵�
	 * 
	 */
	public static void shortestPathDijstra(Graph graph) {
		int[] shortTablePath=new int[VERTEX_SIZE];//��¼V0��������������·����
		boolean[] isGetPath=new boolean[VERTEX_SIZE];
		int vertexSize = graph.getVertexSize();
		int maxWeight = Graph.getMaxWeight();
		int[][] matrix = graph.getMatrix();
		for (int i = 0; i <vertexSize ; i++) {
			shortTablePath[i]=matrix[0][i];//��ʼ������ȡV0��һ���Ȩֵ
		}
		isGetPath[0]=true;
		int min=maxWeight;
		int minIndex=0;
		for (int i = 1; i < vertexSize; i++) {
			min=maxWeight;
			for (int j = 0; j < vertexSize; j++) {
				//��ȡshortTablePath�������Сֵ���Լ��±�
				int shortPath= shortTablePath[j];
				if (!isGetPath[j]&&shortPath<min) {
					min=shortPath;
					minIndex=j;
				}
			}
			isGetPath[minIndex]=true;
			for (int j = 0; j < vertexSize; j++) {
				//Ȼ��shortTablePath[i]��(Vmin[i]+min)���жԱ�
				//���shortTablePath[i]>Vmin[i]+min,��Vmin[i]+min���µ�shortTablePath[i]
				//����һ�����̵�·��
				int weight=matrix[minIndex][j];
				int minWeight=shortTablePath[j];
				if (!isGetPath[j]&&(weight+min)<minWeight) {
					shortTablePath[j]=weight+min;
				}
			}
		}
		
		for (int i = 0; i < vertexSize; i++) {
			System.out.println("V0��V"+i+"�����·���ͣ�"+shortTablePath[i]);
		}
	}
	
	public static void main(String[] args) {
		Graph graph=new Graph(VERTEX_SIZE);
		graph.createGraph();
		Dijstra.shortestPathDijstra(graph);
	}
}
