package sc2001_project2_djikstra;

import java.util.ArrayList;

public class minHeap {
	private ArrayList<Integer> heap = new ArrayList<Integer>();
	private int size;
	private int[] d;
	
	public minHeap() {
		
	}
	
	public void makeHeap(ArrayList<Integer> pQueue) {
		for (int i : pQueue)
			heap.add(i);
		this.size = pQueue.size();
	}
	
	public void fixHeap(int index, int key) {
		int node = index + 1;
		if (node*2 > size) {
			heap.set(index, key);
			return;
		}
		else {
			int lc = node*2-1;
			int rc = node*2;
			
			if (d[key] <= d[heap.get(lc)] && (rc>=size || d[key]<=d[heap.get(rc)])) {
				heap.set(index, key);
				return;
			}
			else {
				if (rc>=size || d[heap.get(lc)]<=d[heap.get(rc)]) {
					heap.set(index, heap.get(lc));
					fixHeap(lc, key);
				}
				else {
					heap.set(index, heap.get(rc));
					fixHeap(rc, key);
				}
			}
		}
	}
	
	public void heapify(int index) {
		int node = index+1;
		if (node*2 > size) {return;}
		else {
			int lc = node*2-1;
			int rc = node*2;
			heapify(lc);
			heapify(rc);
			int root = heap.get(index);
			fixHeap(index, root);
		}
	}
	
	public int getMin(int[] d, ArrayList<Integer> pQueue) {
		this.heap.clear();
		this.d = d;
		makeHeap(pQueue);
		heapify(0);
		return heap.get(0);
	}

}
