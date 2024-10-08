package sc2001_project2_djikstra;

import java.util.ArrayList;

public class minHeap {
	private ArrayList<Integer> heap;
	private int size;
	private int[] d;
	private int heapKeyComp=0;
	
	public minHeap(int[] d, ArrayList<Integer> pQueue) {
		this.d = d;
		this.heap = pQueue;
		this.size = pQueue.size();
		heapify(0);
	}
	
	public void fixHeap(int index, int key) {
		int node = index + 1;
		// if leaf node
		if (node*2 > size) {
			heap.set(index, key);
			return;
		}
		else {
			int lc = node*2-1;
			int rc = node*2;
			// if key smallest
			heapKeyComp++;
			if (d[key] <= d[heap.get(lc)] && (rc>=size || d[key]<=d[heap.get(rc)])) {
				heap.set(index, key);
				return;
			}
			else {
				// if left node smallest OR right node doesn't exist
				if (rc>=size || d[heap.get(lc)]<=d[heap.get(rc)]) {
					heap.set(index, heap.get(lc));
					fixHeap(lc, key);
				}
				// right node smallest
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
	
	public int extractMin() {
		int min = heap.get(0);
		heap.set(0, heap.get(heap.size()-1));
		heap.remove(heap.size()-1);
		size--;
		if (size!=0)
			fixHeap(0, heap.get(0));
		return min;
	}
	
	public void insertKey(int key) {
		heap.add(0, key);
		fixHeap(0, key);
	}
	
	public int extractKeyComp() {
		int keyComp = heapKeyComp;
		heapKeyComp=0;
		return keyComp;
	}

}
